// Description: Java 25 edit object instance implementation for CFSec SecSysRoleEnables.

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

public class CFSecSecSysRoleEnablesEditObj
	implements ICFSecSecSysRoleEnablesEditObj
{
	protected ICFSecSecSysRoleEnablesObj orig;
	protected ICFSecSecSysRoleEnables rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecSecSysRoleObj requiredContainerSysRole;
	protected ICFSecSecSysGrpObj requiredParentEnableGroup;

	public CFSecSecSysRoleEnablesEditObj( ICFSecSecSysRoleEnablesObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecSysRoleEnables origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerSysRole = null;
		requiredParentEnableGroup = null;
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecSysRoleEnablesTableObj().getClassCode() );
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
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecSysRoleEnablesObj retobj = getSchema().getSecSysRoleEnablesTableObj().realiseSecSysRoleEnables( (ICFSecSecSysRoleEnablesObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecSysRoleEnables().forget();
	}

	@Override
	public ICFSecSecSysRoleEnablesObj read() {
		ICFSecSecSysRoleEnablesObj retval = getOrigAsSecSysRoleEnables().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj read( boolean forceRead ) {
		ICFSecSecSysRoleEnablesObj retval = getOrigAsSecSysRoleEnables().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj create() {
		copyRecToOrig();
		ICFSecSecSysRoleEnablesObj retobj = ((ICFSecSchemaObj)getOrigAsSecSysRoleEnables().getSchema()).getSecSysRoleEnablesTableObj().createSecSysRoleEnables( getOrigAsSecSysRoleEnables() );
		if( retobj == getOrigAsSecSysRoleEnables() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecSysRoleEnablesEditObj update() {
		getSchema().getSecSysRoleEnablesTableObj().updateSecSysRoleEnables( (ICFSecSecSysRoleEnablesObj)this );
		return( null );
	}

	@Override
	public CFSecSecSysRoleEnablesEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecSysRoleEnablesTableObj().deleteSecSysRoleEnables( getOrigAsSecSysRoleEnables() );
		return( null );
	}

	@Override
	public ICFSecSecSysRoleEnablesTableObj getSecSysRoleEnablesTable() {
		return( orig.getSchema().getSecSysRoleEnablesTableObj() );
	}

	@Override
	public ICFSecSecSysRoleEnablesEditObj getEdit() {
		return( (ICFSecSecSysRoleEnablesEditObj)this );
	}

	@Override
	public ICFSecSecSysRoleEnablesEditObj getEditAsSecSysRoleEnables() {
		return( (ICFSecSecSysRoleEnablesEditObj)this );
	}

	@Override
	public ICFSecSecSysRoleEnablesEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecSysRoleEnablesObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj getOrigAsSecSysRoleEnables() {
		return( (ICFSecSecSysRoleEnablesObj)orig );
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
	public ICFSecSecSysRoleEnables getRec() {
		if( rec == null ) {
			rec = getOrigAsSecSysRoleEnables().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysRoleEnables value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerSysRole = null;
			requiredParentEnableGroup = null;
		}
	}

	@Override
	public ICFSecSecSysRoleEnables getSecSysRoleEnablesRec() {
		return( (ICFSecSecSysRoleEnables)getRec() );
	}

	@Override
	public ICFSecSecSysRoleEnablesPKey getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( ICFSecSecSysRoleEnablesPKey value ) {
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
		if( forceRead || ( requiredContainerSysRole == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecSysRoleObj obj = ((ICFSecSchemaObj)getOrigAsSecSysRoleEnables().getSchema()).getSecSysRoleTableObj().readSecSysRoleByIdIdx( getPKey().getRequiredSecSysRoleId() );
				requiredContainerSysRole = obj;
				if( obj != null ) {
					requiredContainerSysRole = obj;
				}
			}
		}
		return( requiredContainerSysRole );
	}

	@Override
	public void setRequiredContainerSysRole( ICFSecSecSysRoleObj value ) {
		if( rec == null ) {
			getSecSysRoleEnablesRec();
		}
		if( value != null ) {
			requiredContainerSysRole = value;
			getSecSysRoleEnablesRec().setRequiredContainerSysRole(value.getSecSysRoleRec());
		}
		requiredContainerSysRole = value;
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredParentEnableGroup() {
		return( getRequiredParentEnableGroup( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredParentEnableGroup( boolean forceRead ) {
		if( forceRead || ( requiredParentEnableGroup == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecSysGrpObj obj = ((ICFSecSchemaObj)getOrigAsSecSysRoleEnables().getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getPKey().getRequiredEnableName() );
				requiredParentEnableGroup = obj;
			}
		}
		return( requiredParentEnableGroup );
	}

	@Override
	public void setRequiredParentEnableGroup( ICFSecSecSysGrpObj value ) {
		if( rec == null ) {
			getSecSysRoleEnablesRec();
		}
		if( value != null ) {
			requiredParentEnableGroup = value;
			getSecSysRoleEnablesRec().setRequiredParentEnableGroup(value.getSecSysGrpRec());
		}
		else {
			requiredParentEnableGroup = null;
			getSecSysRoleEnablesRec().setRequiredParentEnableGroup((ICFSecSecSysGrp)null);
		}
		requiredParentEnableGroup = value;
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecSysRoleId(getPKey().getRequiredSecSysRoleId());
			rec.getPKey().setRequiredEnableName(getPKey().getRequiredEnableName());
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecSysRoleId(rec.getPKey().getRequiredSecSysRoleId());
			getPKey().setRequiredEnableName(rec.getPKey().getRequiredEnableName());
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecSecSysRoleEnables origRec = getOrigAsSecSysRoleEnables().getSecSysRoleEnablesRec();
		ICFSecSecSysRoleEnables myRec = getSecSysRoleEnablesRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecSysRoleEnables origRec = getOrigAsSecSysRoleEnables().getSecSysRoleEnablesRec();
		ICFSecSecSysRoleEnables myRec = getSecSysRoleEnablesRec();
		myRec.set( origRec );
	}
}
