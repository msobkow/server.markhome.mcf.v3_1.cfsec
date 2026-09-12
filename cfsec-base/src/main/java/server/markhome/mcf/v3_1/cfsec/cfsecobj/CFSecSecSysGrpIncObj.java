// Description: Java 25 base object instance implementation for SecSysGrpInc

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

public class CFSecSecSysGrpIncObj
	implements ICFSecSecSysGrpIncObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecSysGrpIncEditObj edit;
	protected ICFSecSchemaObj schema;
	protected ICFSecSecSysGrpIncPKey pKey;
	protected ICFSecSecSysGrpInc rec;
	protected ICFSecSecSysGrpObj requiredContainerGroup;
	protected ICFSecSecSysGrpObj requiredParentSubGroup;

	public CFSecSecSysGrpIncObj() {
		isNew = true;
		requiredContainerGroup = null;
		requiredParentSubGroup = null;
	}

	public CFSecSecSysGrpIncObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerGroup = null;
		requiredParentSubGroup = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecSysGrpIncTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSysGrpInc" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecSysGrpObj scope = getRequiredContainerGroup();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredInclName();
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
	public ICFSecSecSysGrpIncObj realise() {
		ICFSecSecSysGrpIncObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().realiseSecSysGrpInc(
			(ICFSecSecSysGrpIncObj)this );
		return( (ICFSecSecSysGrpIncObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().reallyDeepDisposeSecSysGrpInc( (ICFSecSecSysGrpIncObj)this );
	}

	@Override
	public ICFSecSecSysGrpIncObj read() {
		ICFSecSecSysGrpIncObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncByIdIdx( getPKey().getRequiredSecSysGrpId(),
			getPKey().getRequiredInclName(), false );
		return( (ICFSecSecSysGrpIncObj)retobj );
	}

	@Override
	public ICFSecSecSysGrpIncObj read( boolean forceRead ) {
		ICFSecSecSysGrpIncObj retobj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncByIdIdx( getPKey().getRequiredSecSysGrpId(),
			getPKey().getRequiredInclName(), forceRead );
		return( (ICFSecSecSysGrpIncObj)retobj );
	}

	@Override
	public ICFSecSecSysGrpIncTableObj getSecSysGrpIncTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj() );
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
	public ICFSecSecSysGrpInc getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecSysGrpInc().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey().getRequiredSecSysGrpId(),
						getPKey().getRequiredInclName() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysGrpInc value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecSysGrpInc ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecSysGrpIncRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerGroup = null;
		requiredParentSubGroup = null;
	}

	@Override
	public ICFSecSecSysGrpInc getSecSysGrpIncRec() {
		return( (ICFSecSecSysGrpInc)getRec() );
	}

	@Override
	public ICFSecSecSysGrpIncPKey getPKey() {
		if( pKey == null ) {
			pKey = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newPKey();
		}
		return( pKey );
	}

	@Override
	public void setPKey( ICFSecSecSysGrpIncPKey value ) {
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
	public ICFSecSecSysGrpIncEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecSysGrpIncObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecSysGrpIncObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().lockSecSysGrpInc( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecSysGrpIncTableObj().newEditInstance( lockobj );
		return( (ICFSecSecSysGrpIncEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecSysGrpIncEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecSysGrpIncEditObj getEditAsSecSysGrpInc() {
		return( (ICFSecSecSysGrpIncEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecSysGrpInc rec = getRec();
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
			ICFSecSecSysGrpInc rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecSysGrpId() {
		return( getPKey().getRequiredSecSysGrpId() );
	}

	@Override
	public String getRequiredInclName() {
		return( getPKey().getRequiredInclName() );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerGroup() {
		return( getRequiredContainerGroup( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerGroup( boolean forceRead ) {
		if( ( requiredContainerGroup == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerGroup = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByIdIdx( getPKey().getRequiredSecSysGrpId(), forceRead );
			}
		}
		return( requiredContainerGroup );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredParentSubGroup() {
		return( getRequiredParentSubGroup( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredParentSubGroup( boolean forceRead ) {
		if( ( requiredParentSubGroup == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredParentSubGroup = ((ICFSecSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getPKey().getRequiredInclName(), forceRead );
			}
		}
		return( requiredParentSubGroup );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecSysGrpId(getPKey().getRequiredSecSysGrpId());
			rec.getPKey().setRequiredInclName(getPKey().getRequiredInclName());
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecSysGrpId(rec.getPKey().getRequiredSecSysGrpId());
			getPKey().setRequiredInclName(rec.getPKey().getRequiredInclName());
		}
	}
}
