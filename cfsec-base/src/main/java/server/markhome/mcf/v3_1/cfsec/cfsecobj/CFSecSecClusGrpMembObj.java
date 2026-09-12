// Description: Java 25 base object instance implementation for SecClusGrpMemb

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

public class CFSecSecClusGrpMembObj
	implements ICFSecSecClusGrpMembObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecClusGrpMembEditObj edit;
	protected ICFSecSchemaObj schema;
	protected ICFSecSecClusGrpMembPKey pKey;
	protected ICFSecSecClusGrpMemb rec;
	protected ICFSecSecClusGrpObj requiredContainerGroup;
	protected ICFSecSecUserObj requiredParentUser;

	public CFSecSecClusGrpMembObj() {
		isNew = true;
		requiredContainerGroup = null;
		requiredParentUser = null;
	}

	public CFSecSecClusGrpMembObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerGroup = null;
		requiredParentUser = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecClusGrpMembTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecClusGrpMemb" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecClusGrpObj scope = getRequiredContainerGroup();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredLoginId();
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
	public ICFSecSecClusGrpMembObj realise() {
		ICFSecSecClusGrpMembObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().realiseSecClusGrpMemb(
			(ICFSecSecClusGrpMembObj)this );
		return( (ICFSecSecClusGrpMembObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().reallyDeepDisposeSecClusGrpMemb( (ICFSecSecClusGrpMembObj)this );
	}

	@Override
	public ICFSecSecClusGrpMembObj read() {
		ICFSecSecClusGrpMembObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().readSecClusGrpMembByIdIdx( getPKey().getRequiredSecClusGrpId(),
			getPKey().getRequiredLoginId(), false );
		return( (ICFSecSecClusGrpMembObj)retobj );
	}

	@Override
	public ICFSecSecClusGrpMembObj read( boolean forceRead ) {
		ICFSecSecClusGrpMembObj retobj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().readSecClusGrpMembByIdIdx( getPKey().getRequiredSecClusGrpId(),
			getPKey().getRequiredLoginId(), forceRead );
		return( (ICFSecSecClusGrpMembObj)retobj );
	}

	@Override
	public ICFSecSecClusGrpMembTableObj getSecClusGrpMembTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj() );
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
	public ICFSecSecClusGrpMemb getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecClusGrpMemb().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey().getRequiredSecClusGrpId(),
						getPKey().getRequiredLoginId() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecClusGrpMemb value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecClusGrpMemb ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecClusGrpMembRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerGroup = null;
		requiredParentUser = null;
	}

	@Override
	public ICFSecSecClusGrpMemb getSecClusGrpMembRec() {
		return( (ICFSecSecClusGrpMemb)getRec() );
	}

	@Override
	public ICFSecSecClusGrpMembPKey getPKey() {
		if( pKey == null ) {
			pKey = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newPKey();
		}
		return( pKey );
	}

	@Override
	public void setPKey( ICFSecSecClusGrpMembPKey value ) {
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
	public ICFSecSecClusGrpMembEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecClusGrpMembObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecClusGrpMembObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().lockSecClusGrpMemb( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().newEditInstance( lockobj );
		return( (ICFSecSecClusGrpMembEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecClusGrpMembEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecClusGrpMembEditObj getEditAsSecClusGrpMemb() {
		return( (ICFSecSecClusGrpMembEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecClusGrpMemb rec = getRec();
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
			ICFSecSecClusGrpMemb rec = getRec();
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
		return( getPKey().getRequiredSecClusGrpId() );
	}

	@Override
	public String getRequiredLoginId() {
		return( getPKey().getRequiredLoginId() );
	}

	@Override
	public ICFSecSecClusGrpObj getRequiredContainerGroup() {
		return( getRequiredContainerGroup( false ) );
	}

	@Override
	public ICFSecSecClusGrpObj getRequiredContainerGroup( boolean forceRead ) {
		if( ( requiredContainerGroup == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerGroup = ((ICFSecSchemaObj)getSchema()).getSecClusGrpTableObj().readSecClusGrpByIdIdx( getPKey().getRequiredSecClusGrpId(), forceRead );
			}
		}
		return( requiredContainerGroup );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser() {
		return( getRequiredParentUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser( boolean forceRead ) {
		if( ( requiredParentUser == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredParentUser = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByULoginIdx( getPKey().getRequiredLoginId(), forceRead );
			}
		}
		return( requiredParentUser );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecClusGrpId(getPKey().getRequiredSecClusGrpId());
			rec.getPKey().setRequiredLoginId(getPKey().getRequiredLoginId());
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecClusGrpId(rec.getPKey().getRequiredSecClusGrpId());
			getPKey().setRequiredLoginId(rec.getPKey().getRequiredLoginId());
		}
	}
}
