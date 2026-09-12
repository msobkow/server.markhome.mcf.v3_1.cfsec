// Description: Java 25 edit object instance implementation for CFSec SecClusRole.

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

public class CFSecSecClusRoleEditObj
	implements ICFSecSecClusRoleEditObj
{
	protected ICFSecSecClusRoleObj orig;
	protected ICFSecSecClusRole rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecClusterObj requiredOwnerCluster;
	protected ICFSecSecSysGrpObj requiredContainerSysRole;
	protected List<ICFSecSecClusRoleMembObj> optionalChildrenMembByGrp;

	public CFSecSecClusRoleEditObj( ICFSecSecClusRoleObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecClusRole origRec = orig.getRec();
		rec.set( origRec );
		requiredOwnerCluster = null;
		requiredContainerSysRole = null;
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecClusRoleTableObj().getClassCode() );
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
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecClusRoleObj retobj = getSchema().getSecClusRoleTableObj().realiseSecClusRole( (ICFSecSecClusRoleObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecClusRole().forget();
	}

	@Override
	public ICFSecSecClusRoleObj read() {
		ICFSecSecClusRoleObj retval = getOrigAsSecClusRole().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecClusRoleObj read( boolean forceRead ) {
		ICFSecSecClusRoleObj retval = getOrigAsSecClusRole().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecClusRoleObj create() {
		copyRecToOrig();
		ICFSecSecClusRoleObj retobj = ((ICFSecSchemaObj)getOrigAsSecClusRole().getSchema()).getSecClusRoleTableObj().createSecClusRole( getOrigAsSecClusRole() );
		if( retobj == getOrigAsSecClusRole() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecClusRoleEditObj update() {
		getSchema().getSecClusRoleTableObj().updateSecClusRole( (ICFSecSecClusRoleObj)this );
		return( null );
	}

	@Override
	public CFSecSecClusRoleEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecClusRoleTableObj().deleteSecClusRole( getOrigAsSecClusRole() );
		return( null );
	}

	@Override
	public ICFSecSecClusRoleTableObj getSecClusRoleTable() {
		return( orig.getSchema().getSecClusRoleTableObj() );
	}

	@Override
	public ICFSecSecClusRoleEditObj getEdit() {
		return( (ICFSecSecClusRoleEditObj)this );
	}

	@Override
	public ICFSecSecClusRoleEditObj getEditAsSecClusRole() {
		return( (ICFSecSecClusRoleEditObj)this );
	}

	@Override
	public ICFSecSecClusRoleEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecClusRoleObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecClusRoleObj getOrigAsSecClusRole() {
		return( (ICFSecSecClusRoleObj)orig );
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
	public ICFSecSecClusRole getRec() {
		if( rec == null ) {
			rec = getOrigAsSecClusRole().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecClusRole().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecClusRole value ) {
		if( rec != value ) {
			rec = value;
			requiredOwnerCluster = null;
			requiredContainerSysRole = null;
		}
	}

	@Override
	public ICFSecSecClusRole getSecClusRoleRec() {
		return( (ICFSecSecClusRole)getRec() );
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
	public ICFLibKeyHash256 getRequiredSecClusRoleId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredSecClusRoleId(ICFLibKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
			requiredOwnerCluster = null;
			requiredContainerSysRole = null;
			optionalChildrenMembByGrp = null;
		}
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
	public ICFSecClusterObj getRequiredOwnerCluster() {
		return( getRequiredOwnerCluster( false ) );
	}

	@Override
	public ICFSecClusterObj getRequiredOwnerCluster( boolean forceRead ) {
		if( forceRead || ( requiredOwnerCluster == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecClusterObj obj = ((ICFSecSchemaObj)getOrigAsSecClusRole().getSchema()).getClusterTableObj().readClusterByIdIdx( getSecClusRoleRec().getRequiredClusterId() );
				requiredOwnerCluster = obj;
			}
		}
		return( requiredOwnerCluster );
	}

	@Override
	public void setRequiredOwnerCluster( ICFSecClusterObj value ) {
		if( rec == null ) {
			getSecClusRoleRec();
		}
		if( value != null ) {
			requiredOwnerCluster = value;
			getSecClusRoleRec().setRequiredOwnerCluster(value.getClusterRec());
		}
		requiredOwnerCluster = value;
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
				ICFSecSecSysGrpObj obj = ((ICFSecSchemaObj)getOrigAsSecClusRole().getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getSecClusRoleRec().getRequiredName() );
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
			getSecClusRoleRec();
		}
		if( value != null ) {
			requiredContainerSysRole = value;
			getSecClusRoleRec().setRequiredContainerSysRole(value.getSecSysGrpRec());
		}
		requiredContainerSysRole = value;
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
		ICFSecSecClusRole origRec = getOrigAsSecClusRole().getSecClusRoleRec();
		ICFSecSecClusRole myRec = getSecClusRoleRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecClusRole origRec = getOrigAsSecClusRole().getSecClusRoleRec();
		ICFSecSecClusRole myRec = getSecClusRoleRec();
		myRec.set( origRec );
	}
}
