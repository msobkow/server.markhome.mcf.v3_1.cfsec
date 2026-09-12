// Description: Java 25 edit object instance implementation for CFSec SecTentRoleMemb.

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

public class CFSecSecTentRoleMembEditObj
	implements ICFSecSecTentRoleMembEditObj
{
	protected ICFSecSecTentRoleMembObj orig;
	protected ICFSecSecTentRoleMemb rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecSecTentRoleObj requiredContainerRole;
	protected ICFSecSecUserObj requiredParentUser;

	public CFSecSecTentRoleMembEditObj( ICFSecSecTentRoleMembObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecTentRoleMemb origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerRole = null;
		requiredParentUser = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecTentRoleMemb rec = getRec();
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
			ICFSecSecTentRoleMemb rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecTentRoleMembTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecTentRoleMemb" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecTentRoleObj scope = getRequiredContainerRole();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredLoginId();
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
	public ICFSecSecTentRoleMembObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecTentRoleMembObj retobj = getSchema().getSecTentRoleMembTableObj().realiseSecTentRoleMemb( (ICFSecSecTentRoleMembObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecTentRoleMemb().forget();
	}

	@Override
	public ICFSecSecTentRoleMembObj read() {
		ICFSecSecTentRoleMembObj retval = getOrigAsSecTentRoleMemb().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecTentRoleMembObj read( boolean forceRead ) {
		ICFSecSecTentRoleMembObj retval = getOrigAsSecTentRoleMemb().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecTentRoleMembObj create() {
		copyRecToOrig();
		ICFSecSecTentRoleMembObj retobj = ((ICFSecSchemaObj)getOrigAsSecTentRoleMemb().getSchema()).getSecTentRoleMembTableObj().createSecTentRoleMemb( getOrigAsSecTentRoleMemb() );
		if( retobj == getOrigAsSecTentRoleMemb() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecTentRoleMembEditObj update() {
		getSchema().getSecTentRoleMembTableObj().updateSecTentRoleMemb( (ICFSecSecTentRoleMembObj)this );
		return( null );
	}

	@Override
	public CFSecSecTentRoleMembEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecTentRoleMembTableObj().deleteSecTentRoleMemb( getOrigAsSecTentRoleMemb() );
		return( null );
	}

	@Override
	public ICFSecSecTentRoleMembTableObj getSecTentRoleMembTable() {
		return( orig.getSchema().getSecTentRoleMembTableObj() );
	}

	@Override
	public ICFSecSecTentRoleMembEditObj getEdit() {
		return( (ICFSecSecTentRoleMembEditObj)this );
	}

	@Override
	public ICFSecSecTentRoleMembEditObj getEditAsSecTentRoleMemb() {
		return( (ICFSecSecTentRoleMembEditObj)this );
	}

	@Override
	public ICFSecSecTentRoleMembEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecTentRoleMembObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecTentRoleMembObj getOrigAsSecTentRoleMemb() {
		return( (ICFSecSecTentRoleMembObj)orig );
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
	public ICFSecSecTentRoleMemb getRec() {
		if( rec == null ) {
			rec = getOrigAsSecTentRoleMemb().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecTentRoleMemb value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerRole = null;
			requiredParentUser = null;
		}
	}

	@Override
	public ICFSecSecTentRoleMemb getSecTentRoleMembRec() {
		return( (ICFSecSecTentRoleMemb)getRec() );
	}

	@Override
	public ICFSecSecTentRoleMembPKey getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( ICFSecSecTentRoleMembPKey value ) {
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
	public ICFLibKeyHash256 getRequiredSecTentRoleId() {
		return( getPKey().getRequiredSecTentRoleId() );
	}

	@Override
	public String getRequiredLoginId() {
		return( getPKey().getRequiredLoginId() );
	}

	@Override
	public ICFSecSecTentRoleObj getRequiredContainerRole() {
		return( getRequiredContainerRole( false ) );
	}

	@Override
	public ICFSecSecTentRoleObj getRequiredContainerRole( boolean forceRead ) {
		if( forceRead || ( requiredContainerRole == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecTentRoleObj obj = ((ICFSecSchemaObj)getOrigAsSecTentRoleMemb().getSchema()).getSecTentRoleTableObj().readSecTentRoleByIdIdx( getPKey().getRequiredSecTentRoleId() );
				requiredContainerRole = obj;
				if( obj != null ) {
					requiredContainerRole = obj;
				}
			}
		}
		return( requiredContainerRole );
	}

	@Override
	public void setRequiredContainerRole( ICFSecSecTentRoleObj value ) {
		if( rec == null ) {
			getSecTentRoleMembRec();
		}
		if( value != null ) {
			requiredContainerRole = value;
			getSecTentRoleMembRec().setRequiredContainerRole(value.getSecTentRoleRec());
		}
		requiredContainerRole = value;
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser() {
		return( getRequiredParentUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser( boolean forceRead ) {
		if( forceRead || ( requiredParentUser == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecUserObj obj = ((ICFSecSchemaObj)getOrigAsSecTentRoleMemb().getSchema()).getSecUserTableObj().readSecUserByULoginIdx( getPKey().getRequiredLoginId() );
				requiredParentUser = obj;
			}
		}
		return( requiredParentUser );
	}

	@Override
	public void setRequiredParentUser( ICFSecSecUserObj value ) {
		if( rec == null ) {
			getSecTentRoleMembRec();
		}
		if( value != null ) {
			requiredParentUser = value;
			getSecTentRoleMembRec().setRequiredParentUser(value.getSecUserRec());
		}
		else {
			requiredParentUser = null;
			getSecTentRoleMembRec().setRequiredParentUser((ICFSecSecUser)null);
		}
		requiredParentUser = value;
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecTentRoleId(getPKey().getRequiredSecTentRoleId());
			rec.getPKey().setRequiredLoginId(getPKey().getRequiredLoginId());
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecTentRoleId(rec.getPKey().getRequiredSecTentRoleId());
			getPKey().setRequiredLoginId(rec.getPKey().getRequiredLoginId());
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecSecTentRoleMemb origRec = getOrigAsSecTentRoleMemb().getSecTentRoleMembRec();
		ICFSecSecTentRoleMemb myRec = getSecTentRoleMembRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecTentRoleMemb origRec = getOrigAsSecTentRoleMemb().getSecTentRoleMembRec();
		ICFSecSecTentRoleMemb myRec = getSecTentRoleMembRec();
		myRec.set( origRec );
	}
}
