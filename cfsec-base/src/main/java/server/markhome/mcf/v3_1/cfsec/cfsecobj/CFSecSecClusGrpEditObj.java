// Description: Java 25 edit object instance implementation for CFSec SecClusGrp.

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

public class CFSecSecClusGrpEditObj
	implements ICFSecSecClusGrpEditObj
{
	protected ICFSecSecClusGrpObj orig;
	protected ICFSecSecClusGrp rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecClusterObj requiredOwnerCluster;
	protected ICFSecSecSysGrpObj requiredContainerSysGrp;
	protected List<ICFSecSecClusGrpMembObj> optionalChildrenMembByGrp;

	public CFSecSecClusGrpEditObj( ICFSecSecClusGrpObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecClusGrp origRec = orig.getRec();
		rec.set( origRec );
		requiredOwnerCluster = null;
		requiredContainerSysGrp = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecClusGrp rec = getRec();
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
			ICFSecSecClusGrp rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecClusGrpTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecClusGrp" );
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
	public ICFSecSecClusGrpObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecClusGrpObj retobj = getSchema().getSecClusGrpTableObj().realiseSecClusGrp( (ICFSecSecClusGrpObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecClusGrp().forget();
	}

	@Override
	public ICFSecSecClusGrpObj read() {
		ICFSecSecClusGrpObj retval = getOrigAsSecClusGrp().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecClusGrpObj read( boolean forceRead ) {
		ICFSecSecClusGrpObj retval = getOrigAsSecClusGrp().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecClusGrpObj create() {
		copyRecToOrig();
		ICFSecSecClusGrpObj retobj = ((ICFSecSchemaObj)getOrigAsSecClusGrp().getSchema()).getSecClusGrpTableObj().createSecClusGrp( getOrigAsSecClusGrp() );
		if( retobj == getOrigAsSecClusGrp() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecClusGrpEditObj update() {
		getSchema().getSecClusGrpTableObj().updateSecClusGrp( (ICFSecSecClusGrpObj)this );
		return( null );
	}

	@Override
	public CFSecSecClusGrpEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecClusGrpTableObj().deleteSecClusGrp( getOrigAsSecClusGrp() );
		return( null );
	}

	@Override
	public ICFSecSecClusGrpTableObj getSecClusGrpTable() {
		return( orig.getSchema().getSecClusGrpTableObj() );
	}

	@Override
	public ICFSecSecClusGrpEditObj getEdit() {
		return( (ICFSecSecClusGrpEditObj)this );
	}

	@Override
	public ICFSecSecClusGrpEditObj getEditAsSecClusGrp() {
		return( (ICFSecSecClusGrpEditObj)this );
	}

	@Override
	public ICFSecSecClusGrpEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecClusGrpObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecClusGrpObj getOrigAsSecClusGrp() {
		return( (ICFSecSecClusGrpObj)orig );
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
	public ICFSecSecClusGrp getRec() {
		if( rec == null ) {
			rec = getOrigAsSecClusGrp().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrp().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecClusGrp value ) {
		if( rec != value ) {
			rec = value;
			requiredOwnerCluster = null;
			requiredContainerSysGrp = null;
		}
	}

	@Override
	public ICFSecSecClusGrp getSecClusGrpRec() {
		return( (ICFSecSecClusGrp)getRec() );
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
	public ICFLibKeyHash256 getRequiredSecClusGrpId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredSecClusGrpId(ICFLibKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
			requiredOwnerCluster = null;
			requiredContainerSysGrp = null;
			optionalChildrenMembByGrp = null;
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredClusterId() {
		return( getSecClusGrpRec().getRequiredClusterId() );
	}

	@Override
	public String getRequiredName() {
		return( getSecClusGrpRec().getRequiredName() );
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
				ICFSecClusterObj obj = ((ICFSecSchemaObj)getOrigAsSecClusGrp().getSchema()).getClusterTableObj().readClusterByIdIdx( getSecClusGrpRec().getRequiredClusterId() );
				requiredOwnerCluster = obj;
			}
		}
		return( requiredOwnerCluster );
	}

	@Override
	public void setRequiredOwnerCluster( ICFSecClusterObj value ) {
		if( rec == null ) {
			getSecClusGrpRec();
		}
		if( value != null ) {
			requiredOwnerCluster = value;
			getSecClusGrpRec().setRequiredOwnerCluster(value.getClusterRec());
		}
		requiredOwnerCluster = value;
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
				ICFSecSecSysGrpObj obj = ((ICFSecSchemaObj)getOrigAsSecClusGrp().getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getSecClusGrpRec().getRequiredName() );
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
			getSecClusGrpRec();
		}
		if( value != null ) {
			requiredContainerSysGrp = value;
			getSecClusGrpRec().setRequiredContainerSysGrp(value.getSecSysGrpRec());
		}
		requiredContainerSysGrp = value;
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> getOptionalChildrenMembByGrp() {
		List<ICFSecSecClusGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().readSecClusGrpMembByClusGrpIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> getOptionalChildrenMembByGrp( boolean forceRead ) {
		List<ICFSecSecClusGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().readSecClusGrpMembByClusGrpIdx( getPKey(),
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
		ICFSecSecClusGrp origRec = getOrigAsSecClusGrp().getSecClusGrpRec();
		ICFSecSecClusGrp myRec = getSecClusGrpRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecClusGrp origRec = getOrigAsSecClusGrp().getSecClusGrpRec();
		ICFSecSecClusGrp myRec = getSecClusGrpRec();
		myRec.set( origRec );
	}
}
