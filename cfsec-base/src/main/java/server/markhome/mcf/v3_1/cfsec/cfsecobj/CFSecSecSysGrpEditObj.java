// Description: Java 25 edit object instance implementation for CFSec SecSysGrp.

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

public class CFSecSecSysGrpEditObj
	implements ICFSecSecSysGrpEditObj
{
	protected ICFSecSecSysGrpObj orig;
	protected ICFSecSecSysGrp rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected List<ICFSecSecSysGrpIncObj> optionalComponentsIncByGrp;
	protected List<ICFSecSecSysGrpMembObj> optionalChildrenMembByGrp;
	protected List<ICFSecSecClusGrpObj> optionalComponentsImplClusGrp;
	protected List<ICFSecSecTentGrpObj> optionalComponentsImplTentGrp;
	protected ICFSecSecSysRoleObj optionalComponentsImplSysRole;
	protected List<ICFSecSecClusRoleObj> optionalComponentsImplClusRole;
	protected List<ICFSecSecTentRoleObj> optionalComponentsImplTentRole;
	protected List<ICFSecSecSysGrpIncObj> optionalChildrenSysGrpByName;
	protected List<ICFSecSecSysRoleEnablesObj> optionalChildrenRoleByEnableName;

	public CFSecSecSysGrpEditObj( ICFSecSecSysGrpObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecSysGrp origRec = orig.getRec();
		rec.set( origRec );
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecSysGrpTableObj().getClassCode() );
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
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecSysGrpObj retobj = getSchema().getSecSysGrpTableObj().realiseSecSysGrp( (ICFSecSecSysGrpObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecSysGrp().forget();
	}

	@Override
	public ICFSecSecSysGrpObj read() {
		ICFSecSecSysGrpObj retval = getOrigAsSecSysGrp().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSysGrpObj read( boolean forceRead ) {
		ICFSecSecSysGrpObj retval = getOrigAsSecSysGrp().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSysGrpObj create() {
		copyRecToOrig();
		ICFSecSecSysGrpObj retobj = ((ICFSecSchemaObj)getOrigAsSecSysGrp().getSchema()).getSecSysGrpTableObj().createSecSysGrp( getOrigAsSecSysGrp() );
		if( retobj == getOrigAsSecSysGrp() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecSysGrpEditObj update() {
		getSchema().getSecSysGrpTableObj().updateSecSysGrp( (ICFSecSecSysGrpObj)this );
		return( null );
	}

	@Override
	public CFSecSecSysGrpEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecSysGrpTableObj().deleteSecSysGrp( getOrigAsSecSysGrp() );
		return( null );
	}

	@Override
	public ICFSecSecSysGrpTableObj getSecSysGrpTable() {
		return( orig.getSchema().getSecSysGrpTableObj() );
	}

	@Override
	public ICFSecSecSysGrpEditObj getEdit() {
		return( (ICFSecSecSysGrpEditObj)this );
	}

	@Override
	public ICFSecSecSysGrpEditObj getEditAsSecSysGrp() {
		return( (ICFSecSecSysGrpEditObj)this );
	}

	@Override
	public ICFSecSecSysGrpEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecSysGrpObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecSysGrpObj getOrigAsSecSysGrp() {
		return( (ICFSecSecSysGrpObj)orig );
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
	public ICFSecSecSysGrp getRec() {
		if( rec == null ) {
			rec = getOrigAsSecSysGrp().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrp().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysGrp value ) {
		if( rec != value ) {
			rec = value;
		}
	}

	@Override
	public ICFSecSecSysGrp getSecSysGrpRec() {
		return( (ICFSecSecSysGrp)getRec() );
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
	public ICFLibKeyHash256 getRequiredSecSysGrpId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredSecSysGrpId(ICFLibKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
			optionalComponentsIncByGrp = null;
			optionalChildrenMembByGrp = null;
			optionalComponentsImplClusGrp = null;
			optionalComponentsImplTentGrp = null;
			optionalComponentsImplSysRole = null;
			optionalComponentsImplClusRole = null;
			optionalComponentsImplTentRole = null;
			optionalChildrenSysGrpByName = null;
			optionalChildrenRoleByEnableName = null;
		}
	}

	@Override
	public String getRequiredName() {
		return( getSecSysGrpRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getSecSysGrpRec().getRequiredName() != value ) {
			getSecSysGrpRec().setRequiredName( value );
			optionalComponentsIncByGrp = null;
			optionalChildrenMembByGrp = null;
			optionalComponentsImplClusGrp = null;
			optionalComponentsImplTentGrp = null;
			optionalComponentsImplSysRole = null;
			optionalComponentsImplClusRole = null;
			optionalComponentsImplTentRole = null;
			optionalChildrenSysGrpByName = null;
			optionalChildrenRoleByEnableName = null;
		}
	}

	@Override
	public ICFSecPubSchema.SecLevelEnum getRequiredSecLevel() {
		return( getSecSysGrpRec().getRequiredSecLevel() );
	}

	@Override
	public void setRequiredSecLevel( ICFSecPubSchema.SecLevelEnum value ) {
		if( getSecSysGrpRec().getRequiredSecLevel() != value ) {
			getSecSysGrpRec().setRequiredSecLevel( value );
		}
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
		if( forceRead || ( optionalComponentsImplSysRole == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecSysRoleObj obj = ((ICFSecSchemaObj)getOrigAsSecSysGrp().getSchema()).getSecSysRoleTableObj().readSecSysRoleByIdIdx( getPKey() );
				optionalComponentsImplSysRole = obj;
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
		ICFSecSecSysGrp origRec = getOrigAsSecSysGrp().getSecSysGrpRec();
		ICFSecSecSysGrp myRec = getSecSysGrpRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecSysGrp origRec = getOrigAsSecSysGrp().getSecSysGrpRec();
		ICFSecSecSysGrp myRec = getSecSysGrpRec();
		myRec.set( origRec );
	}
}
