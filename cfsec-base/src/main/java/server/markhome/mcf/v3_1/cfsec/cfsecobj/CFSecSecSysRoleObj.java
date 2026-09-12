// Description: Java 25 base object instance implementation for SecSysRole

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

public class CFSecSecSysRoleObj
	implements ICFSecSecSysRoleObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecSysRoleEditObj edit;
	protected ICFSecSchemaObj schema;
	protected $implCommaIJavaOptAtomType$ pKey;
	protected ICFSecSecSysRole rec;
	protected List<ICFSecSecSysRoleEnablesObj> optionalComponentsEnabledByRole;
	protected List<ICFSecSecSysRoleMembObj> optionalChildrenMembByRole;

	public CFSecSecSysRoleObj() {
		isNew = true;
	}

	public CFSecSecSysRoleObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecSysRoleTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSysRole" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
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
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredEnableName");
				}
				String natNextName = nextName;
				subObj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().readSecSysRoleEnablesByIdIdx( getRequiredSecSysRoleId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
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
	public ICFSecSecSysRoleObj realise() {
		ICFSecSecSysRoleObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().realiseSecSysRole(
			(ICFSecSecSysRoleObj)this );
		return( (ICFSecSecSysRoleObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().reallyDeepDisposeSecSysRole( (ICFSecSecSysRoleObj)this );
	}

	@Override
	public ICFSecSecSysRoleObj read() {
		ICFSecSecSysRoleObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().readSecSysRoleByIdIdx( getPKey(), false );
		return( (ICFSecSecSysRoleObj)retobj );
	}

	@Override
	public ICFSecSecSysRoleObj read( boolean forceRead ) {
		ICFSecSecSysRoleObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().readSecSysRoleByIdIdx( getPKey(), forceRead );
		return( (ICFSecSecSysRoleObj)retobj );
	}

	@Override
	public ICFSecSecSysRoleTableObj getSecSysRoleTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj() );
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
	public ICFSecSecSysRole getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysRole().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecSysRole().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysRole value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecSysRole ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecSysRoleRec" );
		}
		rec = value;
		copyRecToPKey();
	}

	@Override
	public ICFSecSecSysRole getSecSysRoleRec() {
		return( (ICFSecSecSysRole)getRec() );
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
	public ICFSecSecSysRoleEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecSysRoleObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecSysRoleObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().lockSecSysRole( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().newEditInstance( lockobj );
		return( (ICFSecSecSysRoleEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecSysRoleEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecSysRoleEditObj getEditAsSecSysRole() {
		return( (ICFSecSecSysRoleEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecSysRole rec = getRec();
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
			ICFSecSecSysRole rec = getRec();
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
		return( getPKey() );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> getOptionalComponentsEnabledByRole() {
		List<ICFSecSecSysRoleEnablesObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().readSecSysRoleEnablesBySysRoleIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> getOptionalComponentsEnabledByRole( boolean forceRead ) {
		List<ICFSecSecSysRoleEnablesObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().readSecSysRoleEnablesBySysRoleIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> getOptionalChildrenMembByRole() {
		List<ICFSecSecSysRoleMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj().readSecSysRoleMembBySysRoleIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> getOptionalChildrenMembByRole( boolean forceRead ) {
		List<ICFSecSecSysRoleMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysRoleMembTableObj().readSecSysRoleMembBySysRoleIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public String getRequiredName() {
		return( getSecSysRoleRec().getRequiredName() );
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
