// Description: Java 25 base object instance implementation for SecClusRole

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

public class CFSecSecClusRoleObj
	implements ICFSecSecClusRoleObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecClusRoleEditObj edit;
	protected ICFSecSchemaObj schema;
	protected $implCommaIJavaOptAtomType$ pKey;
	protected ICFSecSecClusRole rec;
	protected ICFSecClusterObj requiredOwnerCluster;
	protected ICFSecSecSysGrpObj requiredContainerSysRole;
	protected List<ICFSecSecClusRoleMembObj> optionalChildrenMembByGrp;

	public CFSecSecClusRoleObj() {
		isNew = true;
		requiredOwnerCluster = null;
		requiredContainerSysRole = null;
	}

	public CFSecSecClusRoleObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredOwnerCluster = null;
		requiredContainerSysRole = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecClusRoleTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecClusRole" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecSysGrpObj scope = getRequiredContainerSysRole();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredName();
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
	public ICFSecSecClusRoleObj realise() {
		ICFSecSecClusRoleObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().realiseSecClusRole(
			(ICFSecSecClusRoleObj)this );
		return( (ICFSecSecClusRoleObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().reallyDeepDisposeSecClusRole( (ICFSecSecClusRoleObj)this );
	}

	@Override
	public ICFSecSecClusRoleObj read() {
		ICFSecSecClusRoleObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().readSecClusRoleByIdIdx( getPKey(), false );
		return( (ICFSecSecClusRoleObj)retobj );
	}

	@Override
	public ICFSecSecClusRoleObj read( boolean forceRead ) {
		ICFSecSecClusRoleObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().readSecClusRoleByIdIdx( getPKey(), forceRead );
		return( (ICFSecSecClusRoleObj)retobj );
	}

	@Override
	public ICFSecSecClusRoleTableObj getSecClusRoleTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj() );
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
	public ICFSecSecClusRole getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecClusRole().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecClusRole().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecClusRole value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecClusRole ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecClusRoleRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerCluster = null;
		requiredContainerSysRole = null;
	}

	@Override
	public ICFSecSecClusRole getSecClusRoleRec() {
		return( (ICFSecSecClusRole)getRec() );
	}

	@Override
	public $implCommaIJavaOptAtomType$ getPKey() {
		return( pKey );
	}

	@Override
	public void setPKey( $implCommaIJavaOptAtomType$ value ) {
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
	public ICFSecSecClusRoleEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecClusRoleObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecClusRoleObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().lockSecClusRole( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().newEditInstance( lockobj );
		return( (ICFSecSecClusRoleEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecClusRoleEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecClusRoleEditObj getEditAsSecClusRole() {
		return( (ICFSecSecClusRoleEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecClusRole rec = getRec();
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
			ICFSecSecClusRole rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecClusRoleId() {
		return( getPKey() );
	}

	@Override
	public ICFSecClusterObj getRequiredOwnerCluster() {
		return( getRequiredOwnerCluster( false ) );
	}

	@Override
	public ICFSecClusterObj getRequiredOwnerCluster( boolean forceRead ) {
		if( ( requiredOwnerCluster == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredOwnerCluster = ((ICFSecSchemaObj)getSchema()).getClusterTableObj().readClusterByIdIdx( getSecClusRoleRec().getRequiredClusterId(), forceRead );
			}
		}
		return( requiredOwnerCluster );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerSysRole() {
		return( getRequiredContainerSysRole( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerSysRole( boolean forceRead ) {
		if( ( requiredContainerSysRole == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerSysRole = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getSecClusRoleRec().getRequiredName(), forceRead );
			}
		}
		return( requiredContainerSysRole );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> getOptionalChildrenMembByGrp() {
		List<ICFSecSecClusRoleMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusRoleMembTableObj().readSecClusRoleMembByClusRoleIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> getOptionalChildrenMembByGrp( boolean forceRead ) {
		List<ICFSecSecClusRoleMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusRoleMembTableObj().readSecClusRoleMembByClusRoleIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFLibKeyHash256 getRequiredClusterId() {
		return( getSecClusRoleRec().getRequiredClusterId() );
	}

	@Override
	public String getRequiredName() {
		return( getSecClusRoleRec().getRequiredName() );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
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
}
