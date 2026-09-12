// Description: Java 25 base object instance implementation for SecSysRoleEnables

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

public class CFSecSecSysRoleEnablesObj
	implements ICFSecSecSysRoleEnablesObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecSysRoleEnablesEditObj edit;
	protected ICFSecSchemaObj schema;
	protected ICFSecSecSysRoleEnablesPKey pKey;
	protected ICFSecSecSysRoleEnables rec;
	protected ICFSecSecSysRoleObj requiredContainerSysRole;
	protected ICFSecSecSysGrpObj requiredParentEnableGroup;

	public CFSecSecSysRoleEnablesObj() {
		isNew = true;
		requiredContainerSysRole = null;
		requiredParentEnableGroup = null;
	}

	public CFSecSecSysRoleEnablesObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerSysRole = null;
		requiredParentEnableGroup = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecSysRoleEnablesTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSysRoleEnables" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecSysRoleObj scope = getRequiredContainerSysRole();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredEnableName();
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
	public ICFSecSecSysRoleEnablesObj realise() {
		ICFSecSecSysRoleEnablesObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().realiseSecSysRoleEnables(
			(ICFSecSecSysRoleEnablesObj)this );
		return( (ICFSecSecSysRoleEnablesObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().reallyDeepDisposeSecSysRoleEnables( (ICFSecSecSysRoleEnablesObj)this );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj read() {
		ICFSecSecSysRoleEnablesObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().readSecSysRoleEnablesByIdIdx( getPKey().getRequiredSecSysRoleId(),
			getPKey().getRequiredEnableName(), false );
		return( (ICFSecSecSysRoleEnablesObj)retobj );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj read( boolean forceRead ) {
		ICFSecSecSysRoleEnablesObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().readSecSysRoleEnablesByIdIdx( getPKey().getRequiredSecSysRoleId(),
			getPKey().getRequiredEnableName(), forceRead );
		return( (ICFSecSecSysRoleEnablesObj)retobj );
	}

	@Override
	public ICFSecSecSysRoleEnablesTableObj getSecSysRoleEnablesTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj() );
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
	public ICFSecSecSysRoleEnables getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecSysRoleEnables().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey().getRequiredSecSysRoleId(),
						getPKey().getRequiredEnableName() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysRoleEnables value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecSysRoleEnables ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecSysRoleEnablesRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerSysRole = null;
		requiredParentEnableGroup = null;
	}

	@Override
	public ICFSecSecSysRoleEnables getSecSysRoleEnablesRec() {
		return( (ICFSecSecSysRoleEnables)getRec() );
	}

	@Override
	public ICFSecSecSysRoleEnablesPKey getPKey() {
		if( pKey == null ) {
			pKey = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newPKey();
		}
		return( pKey );
	}

	@Override
	public void setPKey( ICFSecSecSysRoleEnablesPKey value ) {
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
	public ICFSecSecSysRoleEnablesEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecSysRoleEnablesObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecSysRoleEnablesObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().lockSecSysRoleEnables( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().newEditInstance( lockobj );
		return( (ICFSecSecSysRoleEnablesEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecSysRoleEnablesEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecSysRoleEnablesEditObj getEditAsSecSysRoleEnables() {
		return( (ICFSecSecSysRoleEnablesEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecSysRoleEnables rec = getRec();
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
			ICFSecSecSysRoleEnables rec = getRec();
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
	public String getRequiredEnableName() {
		return( getPKey().getRequiredEnableName() );
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
	public ICFSecSecSysGrpObj getRequiredParentEnableGroup() {
		return( getRequiredParentEnableGroup( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredParentEnableGroup( boolean forceRead ) {
		if( ( requiredParentEnableGroup == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredParentEnableGroup = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getPKey().getRequiredEnableName(), forceRead );
			}
		}
		return( requiredParentEnableGroup );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecSysRoleId(getPKey().getRequiredSecSysRoleId());
			rec.getPKey().setRequiredEnableName(getPKey().getRequiredEnableName());
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecSysRoleId(rec.getPKey().getRequiredSecSysRoleId());
			getPKey().setRequiredEnableName(rec.getPKey().getRequiredEnableName());
		}
	}
}
