// Description: Java 25 base object instance implementation for Tenant

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

public class CFSecTenantObj
	implements ICFSecTenantObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecTenantEditObj edit;
	protected ICFSecSchemaObj schema;
	protected $implCommaIJavaOptAtomType$ pKey;
	protected ICFSecTenant rec;
	protected ICFSecClusterObj requiredContainerCluster;
	protected List<ICFSecSecTentGrpObj> optionalComponentsSecGroup;
	protected List<ICFSecSecTentRoleObj> optionalComponentsSecRole;

	public CFSecTenantObj() {
		isNew = true;
		requiredContainerCluster = null;
	}

	public CFSecTenantObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerCluster = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getTenantTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Tenant" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecClusterObj scope = getRequiredContainerCluster();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredTenantName();
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
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().readSecTentGrpByUNameIdx( getRequiredId(),
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
				subObj = ((ICFSecSchemaObj)getSchema()).getSecTentRoleTableObj().readSecTentRoleByUNameIdx( getRequiredId(),
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
	public ICFSecTenantObj realise() {
		ICFSecTenantObj retobj = ((ICFSecSchemaObj)getSchema()).getTenantTableObj().realiseTenant(
			(ICFSecTenantObj)this );
		return( (ICFSecTenantObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getTenantTableObj().reallyDeepDisposeTenant( (ICFSecTenantObj)this );
	}

	@Override
	public ICFSecTenantObj read() {
		ICFSecTenantObj retobj = ((ICFSecSchemaObj)getSchema()).getTenantTableObj().readTenantByIdIdx( getPKey(), false );
		return( (ICFSecTenantObj)retobj );
	}

	@Override
	public ICFSecTenantObj read( boolean forceRead ) {
		ICFSecTenantObj retobj = ((ICFSecSchemaObj)getSchema()).getTenantTableObj().readTenantByIdIdx( getPKey(), forceRead );
		return( (ICFSecTenantObj)retobj );
	}

	@Override
	public ICFSecTenantTableObj getTenantTable() {
		return( ((ICFSecSchemaObj)getSchema()).getTenantTableObj() );
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
	public ICFSecTenant getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableTenant().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecTenant value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecTenant ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecTenantRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerCluster = null;
	}

	@Override
	public ICFSecTenant getTenantRec() {
		return( (ICFSecTenant)getRec() );
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
	public ICFSecTenantEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecTenantObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecTenantObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getTenantTableObj().lockTenant( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getTenantTableObj().newEditInstance( lockobj );
		return( (ICFSecTenantEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecTenantEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecTenantEditObj getEditAsTenant() {
		return( (ICFSecTenantEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecTenant rec = getRec();
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
			ICFSecTenant rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public ICFLibKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public ICFSecClusterObj getRequiredContainerCluster() {
		return( getRequiredContainerCluster( false ) );
	}

	@Override
	public ICFSecClusterObj getRequiredContainerCluster( boolean forceRead ) {
		if( ( requiredContainerCluster == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerCluster = ((ICFSecSchemaObj)getSchema()).getClusterTableObj().readClusterByIdIdx( getTenantRec().getRequiredClusterId(), forceRead );
			}
		}
		return( requiredContainerCluster );
	}

	@Override
	public List<ICFSecSecTentGrpObj> getOptionalComponentsSecGroup() {
		List<ICFSecSecTentGrpObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().readSecTentGrpByTenantIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentGrpObj> getOptionalComponentsSecGroup( boolean forceRead ) {
		List<ICFSecSecTentGrpObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().readSecTentGrpByTenantIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentRoleObj> getOptionalComponentsSecRole() {
		List<ICFSecSecTentRoleObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentRoleTableObj().readSecTentRoleByTenantIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentRoleObj> getOptionalComponentsSecRole( boolean forceRead ) {
		List<ICFSecSecTentRoleObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentRoleTableObj().readSecTentRoleByTenantIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFLibKeyHash256 getRequiredClusterId() {
		return( getTenantRec().getRequiredClusterId() );
	}

	@Override
	public String getRequiredTenantName() {
		return( getTenantRec().getRequiredTenantName() );
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
