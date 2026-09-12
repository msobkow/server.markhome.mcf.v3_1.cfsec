// Description: Java 25 edit object instance implementation for CFSec SecTentRole.

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

public class CFSecSecTentRoleEditObj
	implements ICFSecSecTentRoleEditObj
{
	protected ICFSecSecTentRoleObj orig;
	protected ICFSecSecTentRole rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecTenantObj requiredOwnerTenant;
	protected ICFSecSecSysGrpObj requiredContainerSysRole;
	protected List<ICFSecSecTentRoleMembObj> optionalChildrenMembByRole;

	public CFSecSecTentRoleEditObj( ICFSecSecTentRoleObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecTentRole origRec = orig.getRec();
		rec.set( origRec );
		requiredOwnerTenant = null;
		requiredContainerSysRole = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecTentRole rec = getRec();
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
			ICFSecSecTentRole rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecTentRoleTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecTentRole" );
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
	public ICFSecSecTentRoleObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecTentRoleObj retobj = getSchema().getSecTentRoleTableObj().realiseSecTentRole( (ICFSecSecTentRoleObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecTentRole().forget();
	}

	@Override
	public ICFSecSecTentRoleObj read() {
		ICFSecSecTentRoleObj retval = getOrigAsSecTentRole().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecTentRoleObj read( boolean forceRead ) {
		ICFSecSecTentRoleObj retval = getOrigAsSecTentRole().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecTentRoleObj create() {
		copyRecToOrig();
		ICFSecSecTentRoleObj retobj = ((ICFSecSchemaObj)getOrigAsSecTentRole().getSchema()).getSecTentRoleTableObj().createSecTentRole( getOrigAsSecTentRole() );
		if( retobj == getOrigAsSecTentRole() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecTentRoleEditObj update() {
		getSchema().getSecTentRoleTableObj().updateSecTentRole( (ICFSecSecTentRoleObj)this );
		return( null );
	}

	@Override
	public CFSecSecTentRoleEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecTentRoleTableObj().deleteSecTentRole( getOrigAsSecTentRole() );
		return( null );
	}

	@Override
	public ICFSecSecTentRoleTableObj getSecTentRoleTable() {
		return( orig.getSchema().getSecTentRoleTableObj() );
	}

	@Override
	public ICFSecSecTentRoleEditObj getEdit() {
		return( (ICFSecSecTentRoleEditObj)this );
	}

	@Override
	public ICFSecSecTentRoleEditObj getEditAsSecTentRole() {
		return( (ICFSecSecTentRoleEditObj)this );
	}

	@Override
	public ICFSecSecTentRoleEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecTentRoleObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecTentRoleObj getOrigAsSecTentRole() {
		return( (ICFSecSecTentRoleObj)orig );
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
	public ICFSecSecTentRole getRec() {
		if( rec == null ) {
			rec = getOrigAsSecTentRole().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecTentRole value ) {
		if( rec != value ) {
			rec = value;
			requiredOwnerTenant = null;
			requiredContainerSysRole = null;
		}
	}

	@Override
	public ICFSecSecTentRole getSecTentRoleRec() {
		return( (ICFSecSecTentRole)getRec() );
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
	public ICFLibKeyHash256 getRequiredSecTentRoleId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredSecTentRoleId(ICFLibKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
			requiredOwnerTenant = null;
			requiredContainerSysRole = null;
			optionalChildrenMembByRole = null;
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredTenantId() {
		return( getSecTentRoleRec().getRequiredTenantId() );
	}

	@Override
	public String getRequiredName() {
		return( getSecTentRoleRec().getRequiredName() );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerTenant() {
		return( getRequiredOwnerTenant( false ) );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerTenant( boolean forceRead ) {
		if( forceRead || ( requiredOwnerTenant == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecTenantObj obj = ((ICFSecSchemaObj)getOrigAsSecTentRole().getSchema()).getTenantTableObj().readTenantByIdIdx( getSecTentRoleRec().getRequiredTenantId() );
				requiredOwnerTenant = obj;
			}
		}
		return( requiredOwnerTenant );
	}

	@Override
	public void setRequiredOwnerTenant( ICFSecTenantObj value ) {
		if( rec == null ) {
			getSecTentRoleRec();
		}
		if( value != null ) {
			requiredOwnerTenant = value;
			getSecTentRoleRec().setRequiredOwnerTenant(value.getTenantRec());
		}
		requiredOwnerTenant = value;
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerSysRole() {
		return( getRequiredContainerSysRole( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerSysRole( boolean forceRead ) {
		if( forceRead || ( requiredContainerSysRole == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecSysGrpObj obj = ((ICFSecSchemaObj)getOrigAsSecTentRole().getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getSecTentRoleRec().getRequiredName() );
				requiredContainerSysRole = obj;
				if( obj != null ) {
					requiredContainerSysRole = obj;
				}
			}
		}
		return( requiredContainerSysRole );
	}

	@Override
	public void setRequiredContainerSysRole( ICFSecSecSysGrpObj value ) {
		if( rec == null ) {
			getSecTentRoleRec();
		}
		if( value != null ) {
			requiredContainerSysRole = value;
			getSecTentRoleRec().setRequiredContainerSysRole(value.getSecSysGrpRec());
		}
		requiredContainerSysRole = value;
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> getOptionalChildrenMembByRole() {
		List<ICFSecSecTentRoleMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentRoleMembTableObj().readSecTentRoleMembByTentRoleIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> getOptionalChildrenMembByRole( boolean forceRead ) {
		List<ICFSecSecTentRoleMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentRoleMembTableObj().readSecTentRoleMembByTentRoleIdx( getPKey(),
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
		ICFSecSecTentRole origRec = getOrigAsSecTentRole().getSecTentRoleRec();
		ICFSecSecTentRole myRec = getSecTentRoleRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecTentRole origRec = getOrigAsSecTentRole().getSecTentRoleRec();
		ICFSecSecTentRole myRec = getSecTentRoleRec();
		myRec.set( origRec );
	}
}
