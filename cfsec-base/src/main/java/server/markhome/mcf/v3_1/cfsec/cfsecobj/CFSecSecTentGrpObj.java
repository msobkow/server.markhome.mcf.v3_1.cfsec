// Description: Java 25 base object instance implementation for SecTentGrp

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

public class CFSecSecTentGrpObj
	implements ICFSecSecTentGrpObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecTentGrpEditObj edit;
	protected ICFSecSchemaObj schema;
	protected $implCommaIJavaOptAtomType$ pKey;
	protected ICFSecSecTentGrp rec;
	protected ICFSecTenantObj requiredOwnerTenant;
	protected ICFSecSecSysGrpObj requiredContainerSysGrp;
	protected List<ICFSecSecTentGrpMembObj> optionalChildrenMembByGrp;

	public CFSecSecTentGrpObj() {
		isNew = true;
		requiredOwnerTenant = null;
		requiredContainerSysGrp = null;
	}

	public CFSecSecTentGrpObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredOwnerTenant = null;
		requiredContainerSysGrp = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecTentGrpTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecTentGrp" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecSysGrpObj scope = getRequiredContainerSysGrp();
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
	public ICFSecSecTentGrpObj realise() {
		ICFSecSecTentGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().realiseSecTentGrp(
			(ICFSecSecTentGrpObj)this );
		return( (ICFSecSecTentGrpObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().reallyDeepDisposeSecTentGrp( (ICFSecSecTentGrpObj)this );
	}

	@Override
	public ICFSecSecTentGrpObj read() {
		ICFSecSecTentGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().readSecTentGrpByIdIdx( getPKey(), false );
		return( (ICFSecSecTentGrpObj)retobj );
	}

	@Override
	public ICFSecSecTentGrpObj read( boolean forceRead ) {
		ICFSecSecTentGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().readSecTentGrpByIdIdx( getPKey(), forceRead );
		return( (ICFSecSecTentGrpObj)retobj );
	}

	@Override
	public ICFSecSecTentGrpTableObj getSecTentGrpTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj() );
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
	public ICFSecSecTentGrp getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecTentGrp().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecTentGrp value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecTentGrp ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecTentGrpRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		requiredContainerSysGrp = null;
	}

	@Override
	public ICFSecSecTentGrp getSecTentGrpRec() {
		return( (ICFSecSecTentGrp)getRec() );
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
	public ICFSecSecTentGrpEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecTentGrpObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecTentGrpObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().lockSecTentGrp( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecTentGrpTableObj().newEditInstance( lockobj );
		return( (ICFSecSecTentGrpEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecTentGrpEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecTentGrpEditObj getEditAsSecTentGrp() {
		return( (ICFSecSecTentGrpEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecTentGrp rec = getRec();
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
			ICFSecSecTentGrp rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecTentGrpId() {
		return( getPKey() );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerTenant() {
		return( getRequiredOwnerTenant( false ) );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerTenant( boolean forceRead ) {
		if( ( requiredOwnerTenant == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredOwnerTenant = ((ICFSecSchemaObj)getSchema()).getTenantTableObj().readTenantByIdIdx( getSecTentGrpRec().getRequiredTenantId(), forceRead );
			}
		}
		return( requiredOwnerTenant );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerSysGrp() {
		return( getRequiredContainerSysGrp( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerSysGrp( boolean forceRead ) {
		if( ( requiredContainerSysGrp == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerSysGrp = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getSecTentGrpRec().getRequiredName(), forceRead );
			}
		}
		return( requiredContainerSysGrp );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> getOptionalChildrenMembByGrp() {
		List<ICFSecSecTentGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentGrpMembTableObj().readSecTentGrpMembByTentGrpIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> getOptionalChildrenMembByGrp( boolean forceRead ) {
		List<ICFSecSecTentGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentGrpMembTableObj().readSecTentGrpMembByTentGrpIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFLibKeyHash256 getRequiredTenantId() {
		return( getSecTentGrpRec().getRequiredTenantId() );
	}

	@Override
	public String getRequiredName() {
		return( getSecTentGrpRec().getRequiredName() );
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
