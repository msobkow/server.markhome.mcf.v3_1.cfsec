// Description: Java 25 base object instance implementation for SecSysGrp

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

public class CFSecSecSysGrpObj
	implements ICFSecSecSysGrpObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecSysGrpEditObj edit;
	protected ICFSecSchemaObj schema;
	protected $implCommaIJavaOptAtomType$ pKey;
	protected ICFSecSecSysGrp rec;
	protected List<ICFSecSecSysGrpIncObj> optionalComponentsIncByGrp;
	protected List<ICFSecSecSysGrpMembObj> optionalChildrenMembByGrp;
	protected List<ICFSecSecClusGrpObj> optionalComponentsImplClusGrp;
	protected List<ICFSecSecTentGrpObj> optionalComponentsImplTentGrp;
	protected ICFSecSecSysRoleObj optionalComponentsImplSysRole;
	protected List<ICFSecSecClusRoleObj> optionalComponentsImplClusRole;
	protected List<ICFSecSecTentRoleObj> optionalComponentsImplTentRole;
	protected List<ICFSecSecSysGrpIncObj> optionalChildrenSysGrpByName;
	protected List<ICFSecSecSysRoleEnablesObj> optionalChildrenRoleByEnableName;

	public CFSecSecSysGrpObj() {
		isNew = true;
	}

	public CFSecSecSysGrpObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecSysGrpTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSysGrp" );
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
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredInclName");
				}
				String natNextName = nextName;
				subObj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncByIdIdx( getRequiredSecSysGrpId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().readSecClusGrpByUNameIdx( getRequiredSecSysGrpId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().readSecTentGrpByUNameIdx( getRequiredSecSysGrpId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().readSecSysRoleByUNameIdx( natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().readSecClusRoleByUNameIdx( getRequiredSecSysGrpId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFSecSchemaObj)getSchema()).getSecTentRoleTableObj().readSecTentRoleByUNameIdx( getRequiredSecSysGrpId(),
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
	public ICFSecSecSysGrpObj realise() {
		ICFSecSecSysGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().realiseSecSysGrp(
			(ICFSecSecSysGrpObj)this );
		return( (ICFSecSecSysGrpObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().reallyDeepDisposeSecSysGrp( (ICFSecSecSysGrpObj)this );
	}

	@Override
	public ICFSecSecSysGrpObj read() {
		ICFSecSecSysGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByIdIdx( getPKey(), false );
		return( (ICFSecSecSysGrpObj)retobj );
	}

	@Override
	public ICFSecSecSysGrpObj read( boolean forceRead ) {
		ICFSecSecSysGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByIdIdx( getPKey(), forceRead );
		return( (ICFSecSecSysGrpObj)retobj );
	}

	@Override
	public ICFSecSecSysGrpTableObj getSecSysGrpTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj() );
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
	public ICFSecSecSysGrp getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrp().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecSysGrp().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysGrp value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecSysGrp ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecSysGrpRec" );
		}
		rec = value;
		copyRecToPKey();
	}

	@Override
	public ICFSecSecSysGrp getSecSysGrpRec() {
		return( (ICFSecSecSysGrp)getRec() );
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
	public ICFSecSecSysGrpEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecSysGrpObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecSysGrpObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().lockSecSysGrp( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().newEditInstance( lockobj );
		return( (ICFSecSecSysGrpEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecSysGrpEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecSysGrpEditObj getEditAsSecSysGrp() {
		return( (ICFSecSecSysGrpEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecSysGrp rec = getRec();
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
			ICFSecSecSysGrp rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecSysGrpId() {
		return( getPKey() );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> getOptionalComponentsIncByGrp() {
		List<ICFSecSecSysGrpIncObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncBySysGrpIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> getOptionalComponentsIncByGrp( boolean forceRead ) {
		List<ICFSecSecSysGrpIncObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncBySysGrpIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> getOptionalChildrenMembByGrp() {
		List<ICFSecSecSysGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembBySysGrpIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> getOptionalChildrenMembByGrp( boolean forceRead ) {
		List<ICFSecSecSysGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembBySysGrpIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusGrpObj> getOptionalComponentsImplClusGrp() {
		List<ICFSecSecClusGrpObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().readSecClusGrpByNameIdx( getSecSysGrpRec().getRequiredName(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusGrpObj> getOptionalComponentsImplClusGrp( boolean forceRead ) {
		List<ICFSecSecClusGrpObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().readSecClusGrpByNameIdx( getSecSysGrpRec().getRequiredName(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentGrpObj> getOptionalComponentsImplTentGrp() {
		List<ICFSecSecTentGrpObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().readSecTentGrpByNameIdx( getSecSysGrpRec().getRequiredName(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentGrpObj> getOptionalComponentsImplTentGrp( boolean forceRead ) {
		List<ICFSecSecTentGrpObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().readSecTentGrpByNameIdx( getSecSysGrpRec().getRequiredName(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFSecSecSysRoleObj getOptionalComponentsImplSysRole() {
		return( getOptionalComponentsImplSysRole( false ) );
	}

	@Override
	public ICFSecSecSysRoleObj getOptionalComponentsImplSysRole( boolean forceRead ) {
		if( ( optionalComponentsImplSysRole == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				optionalComponentsImplSysRole = ((ICFSecSchemaObj)getSchema()).getSecSysRoleTableObj().readSecSysRoleByIdIdx( getPKey(), forceRead );
			}
		}
		return( optionalComponentsImplSysRole );
	}

	@Override
	public List<ICFSecSecClusRoleObj> getOptionalComponentsImplClusRole() {
		List<ICFSecSecClusRoleObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().readSecClusRoleByNameIdx( getSecSysGrpRec().getRequiredName(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusRoleObj> getOptionalComponentsImplClusRole( boolean forceRead ) {
		List<ICFSecSecClusRoleObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusRoleTableObj().readSecClusRoleByNameIdx( getSecSysGrpRec().getRequiredName(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentRoleObj> getOptionalComponentsImplTentRole() {
		List<ICFSecSecTentRoleObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentRoleTableObj().readSecTentRoleByNameIdx( getSecSysGrpRec().getRequiredName(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentRoleObj> getOptionalComponentsImplTentRole( boolean forceRead ) {
		List<ICFSecSecTentRoleObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentRoleTableObj().readSecTentRoleByNameIdx( getSecSysGrpRec().getRequiredName(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> getOptionalChildrenSysGrpByName() {
		List<ICFSecSecSysGrpIncObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncByNameIdx( getSecSysGrpRec().getRequiredName(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> getOptionalChildrenSysGrpByName( boolean forceRead ) {
		List<ICFSecSecSysGrpIncObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncByNameIdx( getSecSysGrpRec().getRequiredName(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> getOptionalChildrenRoleByEnableName() {
		List<ICFSecSecSysRoleEnablesObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().readSecSysRoleEnablesByNameIdx( getSecSysGrpRec().getRequiredName(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> getOptionalChildrenRoleByEnableName( boolean forceRead ) {
		List<ICFSecSecSysRoleEnablesObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysRoleEnablesTableObj().readSecSysRoleEnablesByNameIdx( getSecSysGrpRec().getRequiredName(),
			forceRead );
		return( retval );
	}

	@Override
	public String getRequiredName() {
		return( getSecSysGrpRec().getRequiredName() );
	}

	@Override
	public ICFSecPubSchema.SecLevelEnum getRequiredSecLevel() {
		return( getSecSysGrpRec().getRequiredSecLevel() );
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
