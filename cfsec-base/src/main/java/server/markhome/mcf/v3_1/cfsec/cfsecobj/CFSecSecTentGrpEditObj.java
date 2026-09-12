// Description: Java 25 edit object instance implementation for CFSec SecTentGrp.

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

public class CFSecSecTentGrpEditObj
	implements ICFSecSecTentGrpEditObj
{
	protected ICFSecSecTentGrpObj orig;
	protected ICFSecSecTentGrp rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecTenantObj requiredOwnerTenant;
	protected ICFSecSecSysGrpObj requiredContainerSysGrp;
	protected List<ICFSecSecTentGrpMembObj> optionalChildrenMembByGrp;

	public CFSecSecTentGrpEditObj( ICFSecSecTentGrpObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecTentGrp origRec = orig.getRec();
		rec.set( origRec );
		requiredOwnerTenant = null;
		requiredContainerSysGrp = null;
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecTentGrpTableObj().getClassCode() );
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
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecTentGrpObj retobj = getSchema().getSecTentGrpTableObj().realiseSecTentGrp( (ICFSecSecTentGrpObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecTentGrp().forget();
	}

	@Override
	public ICFSecSecTentGrpObj read() {
		ICFSecSecTentGrpObj retval = getOrigAsSecTentGrp().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecTentGrpObj read( boolean forceRead ) {
		ICFSecSecTentGrpObj retval = getOrigAsSecTentGrp().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecTentGrpObj create() {
		copyRecToOrig();
		ICFSecSecTentGrpObj retobj = ((ICFSecSchemaObj)getOrigAsSecTentGrp().getSchema()).getSecTentGrpTableObj().createSecTentGrp( getOrigAsSecTentGrp() );
		if( retobj == getOrigAsSecTentGrp() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecTentGrpEditObj update() {
		getSchema().getSecTentGrpTableObj().updateSecTentGrp( (ICFSecSecTentGrpObj)this );
		return( null );
	}

	@Override
	public CFSecSecTentGrpEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecTentGrpTableObj().deleteSecTentGrp( getOrigAsSecTentGrp() );
		return( null );
	}

	@Override
	public ICFSecSecTentGrpTableObj getSecTentGrpTable() {
		return( orig.getSchema().getSecTentGrpTableObj() );
	}

	@Override
	public ICFSecSecTentGrpEditObj getEdit() {
		return( (ICFSecSecTentGrpEditObj)this );
	}

	@Override
	public ICFSecSecTentGrpEditObj getEditAsSecTentGrp() {
		return( (ICFSecSecTentGrpEditObj)this );
	}

	@Override
	public ICFSecSecTentGrpEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecTentGrpObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecTentGrpObj getOrigAsSecTentGrp() {
		return( (ICFSecSecTentGrpObj)orig );
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
	public ICFSecSecTentGrp getRec() {
		if( rec == null ) {
			rec = getOrigAsSecTentGrp().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecTentGrp value ) {
		if( rec != value ) {
			rec = value;
			requiredOwnerTenant = null;
			requiredContainerSysGrp = null;
		}
	}

	@Override
	public ICFSecSecTentGrp getSecTentGrpRec() {
		return( (ICFSecSecTentGrp)getRec() );
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
	public ICFLibKeyHash256 getRequiredSecTentGrpId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredSecTentGrpId(ICFLibKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
			requiredOwnerTenant = null;
			requiredContainerSysGrp = null;
			optionalChildrenMembByGrp = null;
		}
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
	public ICFSecTenantObj getRequiredOwnerTenant() {
		return( getRequiredOwnerTenant( false ) );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerTenant( boolean forceRead ) {
		if( forceRead || ( requiredOwnerTenant == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecTenantObj obj = ((ICFSecSchemaObj)getOrigAsSecTentGrp().getSchema()).getTenantTableObj().readTenantByIdIdx( getSecTentGrpRec().getRequiredTenantId() );
				requiredOwnerTenant = obj;
			}
		}
		return( requiredOwnerTenant );
	}

	@Override
	public void setRequiredOwnerTenant( ICFSecTenantObj value ) {
		if( rec == null ) {
			getSecTentGrpRec();
		}
		if( value != null ) {
			requiredOwnerTenant = value;
			getSecTentGrpRec().setRequiredOwnerTenant(value.getTenantRec());
		}
		requiredOwnerTenant = value;
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerSysGrp() {
		return( getRequiredContainerSysGrp( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerSysGrp( boolean forceRead ) {
		if( forceRead || ( requiredContainerSysGrp == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecSysGrpObj obj = ((ICFSecSchemaObj)getOrigAsSecTentGrp().getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getSecTentGrpRec().getRequiredName() );
				requiredContainerSysGrp = obj;
				if( obj != null ) {
					requiredContainerSysGrp = obj;
				}
			}
		}
		return( requiredContainerSysGrp );
	}

	@Override
	public void setRequiredContainerSysGrp( ICFSecSecSysGrpObj value ) {
		if( rec == null ) {
			getSecTentGrpRec();
		}
		if( value != null ) {
			requiredContainerSysGrp = value;
			getSecTentGrpRec().setRequiredContainerSysGrp(value.getSecSysGrpRec());
		}
		requiredContainerSysGrp = value;
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
		ICFSecSecTentGrp origRec = getOrigAsSecTentGrp().getSecTentGrpRec();
		ICFSecSecTentGrp myRec = getSecTentGrpRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecTentGrp origRec = getOrigAsSecTentGrp().getSecTentGrpRec();
		ICFSecSecTentGrp myRec = getSecTentGrpRec();
		myRec.set( origRec );
	}
}
