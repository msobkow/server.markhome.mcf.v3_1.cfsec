// Description: Java 25 edit object instance implementation for CFSec ISOCtryCcy.

/*
 *	server.markhome.mcf.CFSec
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFSec - Security Services
 *	
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow mark.sobkow@gmail.com
 *	
 *	These files are part of Mark's Code Fractal CFSec.
 *	
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *	
 *	http://www.apache.org/licenses/LICENSE-2.0
 *	
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 *	
 */

package server.markhome.mcf.v3_1.cfsec.cfsecobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

public class CFSecISOCtryCcyEditObj
	implements ICFSecISOCtryCcyEditObj
{
	protected ICFSecISOCtryCcyObj orig;
	protected ICFSecISOCtryCcy rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecISOCtryObj requiredContainerCtry;
	protected ICFSecISOCcyObj requiredParentCcy;

	public CFSecISOCtryCcyEditObj( ICFSecISOCtryCcyObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecISOCtryCcy origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerCtry = null;
		requiredParentCcy = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecISOCtryCcy rec = getRec();
			createdBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getCreatedByUserId() );
		}
		return( createdBy );
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( getRec().getCreatedAt() );
	}

	@Override
	public ICFSecSecUserObj getUpdatedBy() {
		if( updatedBy == null ) {
			ICFSecISOCtryCcy rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public void setCreatedBy( ICFSecSecUserObj value ) {
		createdBy = value;
		if( value != null ) {
			getRec().setCreatedByUserId( value.getRequiredSecUserId() );
		}
	}

	@Override
	public void setCreatedAt( LocalDateTime value ) {
		getRec().setCreatedAt( value );
	}

	@Override
	public void setUpdatedBy( ICFSecSecUserObj value ) {
		updatedBy = value;
		if( value != null ) {
			getRec().setUpdatedByUserId( value.getRequiredSecUserId() );
		}
	}

	@Override
	public void setUpdatedAt( LocalDateTime value ) {
		getRec().setUpdatedAt( value );
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)orig.getSchema()).getISOCtryCcyTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "ISOCtryCcy" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecISOCtryObj scope = getRequiredContainerCtry();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		short val = rec.getRequiredISOCcyId();
		objName = Short.toString( val );
		return( objName );
	}

	@Override
	public ICFLibAnyObj getObjQualifier( Class qualifyingClass ) {
		ICFLibAnyObj container = this;
		if( qualifyingClass != null ) {
			while( container != null ) {
				if( container instanceof ICFSecClusterObj ) {
					break;
				}
				else if( container instanceof ICFSecTenantObj ) {
					break;
				}
				else if( qualifyingClass.isInstance( container ) ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		else {
			while( container != null ) {
				if( container instanceof ICFSecClusterObj ) {
					break;
				}
				else if( container instanceof ICFSecTenantObj ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		return( container );
	}

	@Override
	public ICFLibAnyObj getNamedObject( Class qualifyingClass, String objName ) {
		ICFLibAnyObj topContainer = getObjQualifier( qualifyingClass );
		if( topContainer == null ) {
			return( null );
		}
		ICFLibAnyObj namedObject = topContainer.getNamedObject( objName );
		return( namedObject );
	}

	@Override
	public ICFLibAnyObj getNamedObject( String objName ) {
		String nextName;
		String remainingName;
		ICFLibAnyObj subObj = null;
		ICFLibAnyObj retObj;
		int nextDot = objName.indexOf( '.' );
		if( nextDot >= 0 ) {
			nextName = objName.substring( 0, nextDot );
			remainingName = objName.substring( nextDot + 1 );
		}
		else {
			nextName = objName;
			remainingName = null;
		}
		if( remainingName == null ) {
			retObj = subObj;
		}
		else if( subObj == null ) {
			retObj = null;
		}
		else {
			retObj = subObj.getNamedObject( remainingName );
		}
		return( retObj );
	}

	@Override
	public String getObjQualifiedName() {
		String qualName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				qualName = containerName + "." + qualName;
				container = container.getObjScope();
			}
		}
		return( qualName );
	}

	@Override
	public String getObjFullName() {
		String fullName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				fullName = containerName + "." + fullName;
				container = container.getObjScope();
			}
		}
		return( fullName );
	}

	@Override
	public ICFSecISOCtryCcyObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecISOCtryCcyObj retobj = getSchema().getISOCtryCcyTableObj().realiseISOCtryCcy( (ICFSecISOCtryCcyObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsISOCtryCcy().forget();
	}

	@Override
	public ICFSecISOCtryCcyObj read() {
		ICFSecISOCtryCcyObj retval = getOrigAsISOCtryCcy().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecISOCtryCcyObj read( boolean forceRead ) {
		ICFSecISOCtryCcyObj retval = getOrigAsISOCtryCcy().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecISOCtryCcyObj create() {
		copyRecToOrig();
		ICFSecISOCtryCcyObj retobj = ((ICFSecSchemaObj)getOrigAsISOCtryCcy().getSchema()).getISOCtryCcyTableObj().createISOCtryCcy( getOrigAsISOCtryCcy() );
		if( retobj == getOrigAsISOCtryCcy() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecISOCtryCcyEditObj update() {
		getSchema().getISOCtryCcyTableObj().updateISOCtryCcy( (ICFSecISOCtryCcyObj)this );
		return( null );
	}

	@Override
	public CFSecISOCtryCcyEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getISOCtryCcyTableObj().deleteISOCtryCcy( getOrigAsISOCtryCcy() );
		return( null );
	}

	@Override
	public ICFSecISOCtryCcyTableObj getISOCtryCcyTable() {
		return( orig.getSchema().getISOCtryCcyTableObj() );
	}

	@Override
	public ICFSecISOCtryCcyEditObj getEdit() {
		return( (ICFSecISOCtryCcyEditObj)this );
	}

	@Override
	public ICFSecISOCtryCcyEditObj getEditAsISOCtryCcy() {
		return( (ICFSecISOCtryCcyEditObj)this );
	}

	@Override
	public ICFSecISOCtryCcyEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecISOCtryCcyObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecISOCtryCcyObj getOrigAsISOCtryCcy() {
		return( (ICFSecISOCtryCcyObj)orig );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( orig.getSchema() );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		orig.setSchema(value);
	}

	@Override
	public ICFSecISOCtryCcy getRec() {
		if( rec == null ) {
			rec = getOrigAsISOCtryCcy().getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryCcy().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecISOCtryCcy value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerCtry = null;
			requiredParentCcy = null;
		}
	}

	@Override
	public ICFSecISOCtryCcy getISOCtryCcyRec() {
		return( (ICFSecISOCtryCcy)getRec() );
	}

	@Override
	public ICFSecISOCtryCcyPKey getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( ICFSecISOCtryCcyPKey value ) {
		orig.setPKey( value );
		copyPKeyToRec();
	}

	@Override
	public boolean getIsNew() {
		return( orig.getIsNew() );
	}

	@Override
	public void setIsNew( boolean value ) {
		orig.setIsNew( value );
	}

	@Override
	public short getRequiredISOCtryId() {
		return( getPKey().getRequiredISOCtryId() );
	}

	@Override
	public short getRequiredISOCcyId() {
		return( getPKey().getRequiredISOCcyId() );
	}

	@Override
	public ICFSecISOCtryObj getRequiredContainerCtry() {
		return( getRequiredContainerCtry( false ) );
	}

	@Override
	public ICFSecISOCtryObj getRequiredContainerCtry( boolean forceRead ) {
		if( forceRead || ( requiredContainerCtry == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecISOCtryObj obj = ((ICFSecSchemaObj)getOrigAsISOCtryCcy().getSchema()).getISOCtryTableObj().readISOCtryByIdIdx( getPKey().getRequiredISOCtryId() );
				requiredContainerCtry = obj;
				if( obj != null ) {
					requiredContainerCtry = obj;
				}
			}
		}
		return( requiredContainerCtry );
	}

	@Override
	public void setRequiredContainerCtry( ICFSecISOCtryObj value ) {
		if( rec == null ) {
			getISOCtryCcyRec();
		}
		if( value != null ) {
			requiredContainerCtry = value;
			getISOCtryCcyRec().setRequiredContainerCtry(value.getISOCtryRec());
		}
		requiredContainerCtry = value;
	}

	@Override
	public ICFSecISOCcyObj getRequiredParentCcy() {
		return( getRequiredParentCcy( false ) );
	}

	@Override
	public ICFSecISOCcyObj getRequiredParentCcy( boolean forceRead ) {
		if( forceRead || ( requiredParentCcy == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecISOCcyObj obj = ((ICFSecSchemaObj)getOrigAsISOCtryCcy().getSchema()).getISOCcyTableObj().readISOCcyByIdIdx( getPKey().getRequiredISOCcyId() );
				requiredParentCcy = obj;
			}
		}
		return( requiredParentCcy );
	}

	@Override
	public void setRequiredParentCcy( ICFSecISOCcyObj value ) {
		if( rec == null ) {
			getISOCtryCcyRec();
		}
		if( value != null ) {
			requiredParentCcy = value;
			getISOCtryCcyRec().setRequiredParentCcy(value.getISOCcyRec());
		}
		else {
			requiredParentCcy = null;
			getISOCtryCcyRec().setRequiredParentCcy((ICFSecISOCcy)null);
		}
		requiredParentCcy = value;
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredISOCtryId(getPKey().getRequiredISOCtryId());
			rec.getPKey().setRequiredISOCcyId(getPKey().getRequiredISOCcyId());
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredISOCtryId(rec.getPKey().getRequiredISOCtryId());
			getPKey().setRequiredISOCcyId(rec.getPKey().getRequiredISOCcyId());
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecISOCtryCcy origRec = getOrigAsISOCtryCcy().getISOCtryCcyRec();
		ICFSecISOCtryCcy myRec = getISOCtryCcyRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecISOCtryCcy origRec = getOrigAsISOCtryCcy().getISOCtryCcyRec();
		ICFSecISOCtryCcy myRec = getISOCtryCcyRec();
		myRec.set( origRec );
	}
}
