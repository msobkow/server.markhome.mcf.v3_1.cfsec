// Description: Java 25 edit object instance implementation for CFSec SecUserPWReset.

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

public class CFSecSecUserPWResetEditObj
	implements ICFSecSecUserPWResetEditObj
{
	protected ICFSecSecUserPWResetObj orig;
	protected ICFSecSecUserPWReset rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecSecUserObj requiredContainerUser;

	public CFSecSecUserPWResetEditObj( ICFSecSecUserPWResetObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecUserPWReset origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerUser = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecUserPWReset rec = getRec();
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
			ICFSecSecUserPWReset rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecUserPWResetTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecUserPWReset" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecUserObj scope = getRequiredContainerUser();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		ICFLibUuid6 val = rec.getRequiredPasswordResetUuid6();
		if (val != null) {
			objName = val.toString();
		}
		else {
			objName = "";
		}
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
	public ICFSecSecUserPWResetObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecUserPWResetObj retobj = getSchema().getSecUserPWResetTableObj().realiseSecUserPWReset( (ICFSecSecUserPWResetObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecUserPWReset().forget();
	}

	@Override
	public ICFSecSecUserPWResetObj read() {
		ICFSecSecUserPWResetObj retval = getOrigAsSecUserPWReset().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecUserPWResetObj read( boolean forceRead ) {
		ICFSecSecUserPWResetObj retval = getOrigAsSecUserPWReset().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecUserPWResetObj create() {
		copyRecToOrig();
		ICFSecSecUserPWResetObj retobj = ((ICFSecSchemaObj)getOrigAsSecUserPWReset().getSchema()).getSecUserPWResetTableObj().createSecUserPWReset( getOrigAsSecUserPWReset() );
		if( retobj == getOrigAsSecUserPWReset() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecUserPWResetEditObj update() {
		getSchema().getSecUserPWResetTableObj().updateSecUserPWReset( (ICFSecSecUserPWResetObj)this );
		return( null );
	}

	@Override
	public CFSecSecUserPWResetEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecUserPWResetTableObj().deleteSecUserPWReset( getOrigAsSecUserPWReset() );
		return( null );
	}

	@Override
	public ICFSecSecUserPWResetTableObj getSecUserPWResetTable() {
		return( orig.getSchema().getSecUserPWResetTableObj() );
	}

	@Override
	public ICFSecSecUserPWResetEditObj getEdit() {
		return( (ICFSecSecUserPWResetEditObj)this );
	}

	@Override
	public ICFSecSecUserPWResetEditObj getEditAsSecUserPWReset() {
		return( (ICFSecSecUserPWResetEditObj)this );
	}

	@Override
	public ICFSecSecUserPWResetEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecUserPWResetObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecUserPWResetObj getOrigAsSecUserPWReset() {
		return( (ICFSecSecUserPWResetObj)orig );
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
	public ICFSecSecUserPWReset getRec() {
		if( rec == null ) {
			rec = getOrigAsSecUserPWReset().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecUserPWReset value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerUser = null;
		}
	}

	@Override
	public ICFSecSecUserPWReset getSecUserPWResetRec() {
		return( (ICFSecSecUserPWReset)getRec() );
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
	public ICFLibKeyHash256 getRequiredSecUserId() {
		return( getPKey() );
	}

	@Override
	public String getRequiredSentToEMailAddr() {
		return( getSecUserPWResetRec().getRequiredSentToEMailAddr() );
	}

	@Override
	public void setRequiredSentToEMailAddr( String value ) {
		if( getSecUserPWResetRec().getRequiredSentToEMailAddr() != value ) {
			getSecUserPWResetRec().setRequiredSentToEMailAddr( value );
		}
	}

	@Override
	public ICFLibUuid6 getRequiredPasswordResetUuid6() {
		return( getSecUserPWResetRec().getRequiredPasswordResetUuid6() );
	}

	@Override
	public void setRequiredPasswordResetUuid6( ICFLibUuid6 value ) {
		if( getSecUserPWResetRec().getRequiredPasswordResetUuid6() != value ) {
			getSecUserPWResetRec().setRequiredPasswordResetUuid6( value );
		}
	}

	@Override
	public boolean getRequiredNewAccount() {
		return( getSecUserPWResetRec().getRequiredNewAccount() );
	}

	@Override
	public void setRequiredNewAccount( boolean value ) {
		if( getSecUserPWResetRec().getRequiredNewAccount() != value ) {
			getSecUserPWResetRec().setRequiredNewAccount( value );
		}
	}

	@Override
	public ICFSecSecUserObj getRequiredContainerUser() {
		return( getRequiredContainerUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredContainerUser( boolean forceRead ) {
		if( forceRead || ( requiredContainerUser == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecUserObj obj = ((ICFSecSchemaObj)getOrigAsSecUserPWReset().getSchema()).getSecUserTableObj().readSecUserByIdIdx( getPKey() );
				requiredContainerUser = obj;
				if( obj != null ) {
					requiredContainerUser = obj;
				}
			}
		}
		return( requiredContainerUser );
	}

	@Override
	public void setRequiredContainerUser( ICFSecSecUserObj value ) {
		if( rec == null ) {
			getSecUserPWResetRec();
		}
		if( value != null ) {
			requiredContainerUser = value;
			getSecUserPWResetRec().setRequiredContainerUser(value.getSecUserRec());
		}
		requiredContainerUser = value;
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
		ICFSecSecUserPWReset origRec = getOrigAsSecUserPWReset().getSecUserPWResetRec();
		ICFSecSecUserPWReset myRec = getSecUserPWResetRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecUserPWReset origRec = getOrigAsSecUserPWReset().getSecUserPWResetRec();
		ICFSecSecUserPWReset myRec = getSecUserPWResetRec();
		myRec.set( origRec );
	}
}
