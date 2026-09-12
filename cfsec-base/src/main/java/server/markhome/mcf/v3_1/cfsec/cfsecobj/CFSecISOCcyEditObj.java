// Description: Java 25 edit object instance implementation for CFSec ISOCcy.

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

public class CFSecISOCcyEditObj
	implements ICFSecISOCcyEditObj
{
	protected ICFSecISOCcyObj orig;
	protected ICFSecISOCcy rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected List<ICFSecISOCtryCcyObj> optionalChildrenCtry;

	public CFSecISOCcyEditObj( ICFSecISOCcyObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecISOCcy origRec = orig.getRec();
		rec.set( origRec );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecISOCcy rec = getRec();
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
			ICFSecISOCcy rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getISOCcyTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "ISOCcy" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredISOCode();
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
	public ICFSecISOCcyObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecISOCcyObj retobj = getSchema().getISOCcyTableObj().realiseISOCcy( (ICFSecISOCcyObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsISOCcy().forget();
	}

	@Override
	public ICFSecISOCcyObj read() {
		ICFSecISOCcyObj retval = getOrigAsISOCcy().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecISOCcyObj read( boolean forceRead ) {
		ICFSecISOCcyObj retval = getOrigAsISOCcy().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecISOCcyObj create() {
		copyRecToOrig();
		ICFSecISOCcyObj retobj = ((ICFSecSchemaObj)getOrigAsISOCcy().getSchema()).getISOCcyTableObj().createISOCcy( getOrigAsISOCcy() );
		if( retobj == getOrigAsISOCcy() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecISOCcyEditObj update() {
		getSchema().getISOCcyTableObj().updateISOCcy( (ICFSecISOCcyObj)this );
		return( null );
	}

	@Override
	public CFSecISOCcyEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getISOCcyTableObj().deleteISOCcy( getOrigAsISOCcy() );
		return( null );
	}

	@Override
	public ICFSecISOCcyTableObj getISOCcyTable() {
		return( orig.getSchema().getISOCcyTableObj() );
	}

	@Override
	public ICFSecISOCcyEditObj getEdit() {
		return( (ICFSecISOCcyEditObj)this );
	}

	@Override
	public ICFSecISOCcyEditObj getEditAsISOCcy() {
		return( (ICFSecISOCcyEditObj)this );
	}

	@Override
	public ICFSecISOCcyEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecISOCcyObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecISOCcyObj getOrigAsISOCcy() {
		return( (ICFSecISOCcyObj)orig );
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
	public ICFSecISOCcy getRec() {
		if( rec == null ) {
			rec = getOrigAsISOCcy().getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryISOCcy().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecISOCcy value ) {
		if( rec != value ) {
			rec = value;
		}
	}

	@Override
	public ICFSecISOCcy getISOCcyRec() {
		return( (ICFSecISOCcy)getRec() );
	}

	@Override
	public $implCommaIJavaOptAtomType$ getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( $implCommaIJavaOptAtomType$ value ) {
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
	public short getRequiredISOCcyId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredISOCcyId(short value) {
		if (getPKey() != value) {
			setPKey(value);
			optionalChildrenCtry = null;
		}
	}

	@Override
	public String getRequiredISOCode() {
		return( getISOCcyRec().getRequiredISOCode() );
	}

	@Override
	public void setRequiredISOCode( String value ) {
		if( getISOCcyRec().getRequiredISOCode() != value ) {
			getISOCcyRec().setRequiredISOCode( value );
		}
	}

	@Override
	public String getRequiredName() {
		return( getISOCcyRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getISOCcyRec().getRequiredName() != value ) {
			getISOCcyRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalUnitSymbol() {
		return( getISOCcyRec().getOptionalUnitSymbol() );
	}

	@Override
	public void setOptionalUnitSymbol( String value ) {
		if( getISOCcyRec().getOptionalUnitSymbol() != value ) {
			getISOCcyRec().setOptionalUnitSymbol( value );
		}
	}

	@Override
	public short getRequiredPrecis() {
		return( getISOCcyRec().getRequiredPrecis() );
	}

	@Override
	public void setRequiredPrecis( short value ) {
		if( getISOCcyRec().getRequiredPrecis() != value ) {
			getISOCcyRec().setRequiredPrecis( value );
		}
	}

	@Override
	public List<ICFSecISOCtryCcyObj> getOptionalChildrenCtry() {
		List<ICFSecISOCtryCcyObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getISOCtryCcyTableObj().readISOCtryCcyByCcyIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecISOCtryCcyObj> getOptionalChildrenCtry( boolean forceRead ) {
		List<ICFSecISOCtryCcyObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getISOCtryCcyTableObj().readISOCtryCcyByCcyIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				setPKey(rec.getPKey());
			}
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecISOCcy origRec = getOrigAsISOCcy().getISOCcyRec();
		ICFSecISOCcy myRec = getISOCcyRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecISOCcy origRec = getOrigAsISOCcy().getISOCcyRec();
		ICFSecISOCcy myRec = getISOCcyRec();
		myRec.set( origRec );
	}
}
