// Description: Java 25 base object instance implementation for SecClusGrp

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

public class CFSecSecClusGrpObj
	implements ICFSecSecClusGrpObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecClusGrpEditObj edit;
	protected ICFSecSchemaObj schema;
	protected $implCommaIJavaOptAtomType$ pKey;
	protected ICFSecSecClusGrp rec;
	protected ICFSecClusterObj requiredOwnerCluster;
	protected ICFSecSecSysGrpObj requiredContainerSysGrp;
	protected List<ICFSecSecClusGrpMembObj> optionalChildrenMembByGrp;

	public CFSecSecClusGrpObj() {
		isNew = true;
		requiredOwnerCluster = null;
		requiredContainerSysGrp = null;
	}

	public CFSecSecClusGrpObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredOwnerCluster = null;
		requiredContainerSysGrp = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecClusGrpTableObj().getClassCode() );
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
		ICFSecSecClusGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().realiseSecClusGrp(
			(ICFSecSecClusGrpObj)this );
		return( (ICFSecSecClusGrpObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().reallyDeepDisposeSecClusGrp( (ICFSecSecClusGrpObj)this );
	}

	@Override
	public ICFSecSecClusGrpObj read() {
		ICFSecSecClusGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().readSecClusGrpByIdIdx( getPKey(), false );
		return( (ICFSecSecClusGrpObj)retobj );
	}

	@Override
	public ICFSecSecClusGrpObj read( boolean forceRead ) {
		ICFSecSecClusGrpObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().readSecClusGrpByIdIdx( getPKey(), forceRead );
		return( (ICFSecSecClusGrpObj)retobj );
	}

	@Override
	public ICFSecSecClusGrpTableObj getSecClusGrpTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj() );
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
	public ICFSecSecClusGrp getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrp().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecClusGrp().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecClusGrp value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecClusGrp ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecClusGrpRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerCluster = null;
		requiredContainerSysGrp = null;
	}

	@Override
	public ICFSecSecClusGrp getSecClusGrpRec() {
		return( (ICFSecSecClusGrp)getRec() );
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
	public ICFSecSecClusGrpEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecClusGrpObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecClusGrpObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().lockSecClusGrp( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().newEditInstance( lockobj );
		return( (ICFSecSecClusGrpEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecClusGrpEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecClusGrpEditObj getEditAsSecClusGrp() {
		return( (ICFSecSecClusGrpEditObj)edit );
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
	public ICFLibKeyHash256 getRequiredSecClusGrpId() {
		return( getPKey() );
	}

	@Override
	public ICFSecClusterObj getRequiredOwnerCluster() {
		return( getRequiredOwnerCluster( false ) );
	}

	@Override
	public ICFSecClusterObj getRequiredOwnerCluster( boolean forceRead ) {
		if( ( requiredOwnerCluster == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredOwnerCluster = ((ICFSecSchemaObj)getSchema()).getClusterTableObj().readClusterByIdIdx( getSecClusGrpRec().getRequiredClusterId(), forceRead );
			}
		}
		return( requiredOwnerCluster );
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
				requiredContainerSysGrp = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getSecClusGrpRec().getRequiredName(), forceRead );
			}
		}
		return( requiredContainerSysGrp );
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
	public ICFLibKeyHash256 getRequiredClusterId() {
		return( getSecClusGrpRec().getRequiredClusterId() );
	}

	@Override
	public String getRequiredName() {
		return( getSecClusGrpRec().getRequiredName() );
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
