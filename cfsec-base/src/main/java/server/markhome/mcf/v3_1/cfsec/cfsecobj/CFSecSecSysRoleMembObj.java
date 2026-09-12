// Description: Java 25 base object instance implementation for SecSysRoleMemb

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

public class CFSecSecSysRoleMembObj
	implements ICFSecSecSysRoleMembObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecSysRoleMembEditObj edit;
	protected ICFSecSchemaObj schema;
	protected ICFSecSecSysRoleMembPKey pKey;
	protected ICFSecSecSysRoleMemb rec;
	protected ICFSecSecSysRoleObj requiredContainerSysRole;
	protected ICFSecSecUserObj requiredParentUser;

	public CFSecSecSysRoleMembObj() {
		isNew = true;
		requiredContainerSysRole = null;
		requiredParentUser = null;
	}

	public CFSecSecSysRoleMembObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerSysRole = null;
		requiredParentUser = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecSysRoleMembTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSysRoleMemb" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecSysRoleObj scope = getRequiredContainerSysRole();
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
	public ICFSecSecSysRoleMembObj realise() {
		ICFSecSecSysRoleMembObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj().realiseSecSysRoleMemb(
			(ICFSecSecSysRoleMembObj)this );
		return( (ICFSecSecSysRoleMembObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj().reallyDeepDisposeSecSysRoleMemb( (ICFSecSecSysRoleMembObj)this );
	}

	@Override
	public ICFSecSecSysRoleMembObj read() {
		ICFSecSecSysRoleMembObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj().readSecSysRoleMembByIdIdx( getPKey().getRequiredSecSysRoleId(),
			getPKey().getRequiredLoginId(), false );
		return( (ICFSecSecSysRoleMembObj)retobj );
	}

	@Override
	public ICFSecSecSysRoleMembObj read( boolean forceRead ) {
		ICFSecSecSysRoleMembObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj().readSecSysRoleMembByIdIdx( getPKey().getRequiredSecSysRoleId(),
			getPKey().getRequiredLoginId(), forceRead );
		return( (ICFSecSecSysRoleMembObj)retobj );
	}

	@Override
	public ICFSecSecSysRoleMembTableObj getSecSysRoleMembTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = value;
	}

	@Override
	public ICFSecSecSysRoleMemb getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecSysRoleMemb().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey().getRequiredSecSysRoleId(),
						getPKey().getRequiredLoginId() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysRoleMemb value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecSysRoleMemb ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecSysRoleMembRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerSysRole = null;
		requiredParentUser = null;
	}

	@Override
	public ICFSecSecSysRoleMemb getSecSysRoleMembRec() {
		return( (ICFSecSecSysRoleMemb)getRec() );
	}

	@Override
	public ICFSecSecSysRoleMembPKey getPKey() {
		if( pKey == null ) {
			pKey = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newPKey();
		}
		return( pKey );
	}

	@Override
	public void setPKey( ICFSecSecSysRoleMembPKey value ) {
		if( pKey != value ) {
			pKey = value;
			copyPKeyToRec();
		}
	}

	@Override
	public boolean getIsNew() {
		return( isNew );
	}

	@Override
	public void setIsNew( boolean value ) {
		isNew = value;
	}

	@Override
	public ICFSecSecSysRoleMembEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecSysRoleMembObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecSysRoleMembObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj().lockSecSysRoleMemb( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj().newEditInstance( lockobj );
		return( (ICFSecSecSysRoleMembEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecSysRoleMembEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecSysRoleMembEditObj getEditAsSecSysRoleMemb() {
		return( (ICFSecSecSysRoleMembEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecSysRoleMemb rec = getRec();
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
			ICFSecSecSysRoleMemb rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecSysRoleId() {
		return( getPKey().getRequiredSecSysRoleId() );
	}

	@Override
	public String getRequiredLoginId() {
		return( getPKey().getRequiredLoginId() );
	}

	@Override
	public ICFSecSecSysRoleObj getRequiredContainerSysRole() {
		return( getRequiredContainerSysRole( false ) );
	}

	@Override
	public ICFSecSecSysRoleObj getRequiredContainerSysRole( boolean forceRead ) {
		if( ( requiredContainerSysRole == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerSysRole = ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().readSecSysRoleByIdIdx( getPKey().getRequiredSecSysRoleId(), forceRead );
			}
		}
		return( requiredContainerSysRole );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser() {
		return( getRequiredParentUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser( boolean forceRead ) {
		if( ( requiredParentUser == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredParentUser = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByULoginIdx( getPKey().getRequiredLoginId(), forceRead );
			}
		}
		return( requiredParentUser );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecSysRoleId(getPKey().getRequiredSecSysRoleId());
			rec.getPKey().setRequiredLoginId(getPKey().getRequiredLoginId());
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecSysRoleId(rec.getPKey().getRequiredSecSysRoleId());
			getPKey().setRequiredLoginId(rec.getPKey().getRequiredLoginId());
		}
	}
}
