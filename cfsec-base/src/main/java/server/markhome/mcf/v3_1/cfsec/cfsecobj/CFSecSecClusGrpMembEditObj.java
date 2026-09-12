// Description: Java 25 edit object instance implementation for CFSec SecClusGrpMemb.

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

public class CFSecSecClusGrpMembEditObj
	implements ICFSecSecClusGrpMembEditObj
{
	protected ICFSecSecClusGrpMembObj orig;
	protected ICFSecSecClusGrpMemb rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecSecClusGrpObj requiredContainerGroup;
	protected ICFSecSecUserObj requiredParentUser;

	public CFSecSecClusGrpMembEditObj( ICFSecSecClusGrpMembObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecClusGrpMemb origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerGroup = null;
		requiredParentUser = null;
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecClusGrpMembTableObj().getClassCode() );
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
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecClusGrpMembObj retobj = getSchema().getSecClusGrpMembTableObj().realiseSecClusGrpMemb( (ICFSecSecClusGrpMembObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecClusGrpMemb().forget();
	}

	@Override
	public ICFSecSecClusGrpMembObj read() {
		ICFSecSecClusGrpMembObj retval = getOrigAsSecClusGrpMemb().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecClusGrpMembObj read( boolean forceRead ) {
		ICFSecSecClusGrpMembObj retval = getOrigAsSecClusGrpMemb().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecClusGrpMembObj create() {
		copyRecToOrig();
		ICFSecSecClusGrpMembObj retobj = ((ICFSecSchemaObj)getOrigAsSecClusGrpMemb().getSchema()).getSecClusGrpMembTableObj().createSecClusGrpMemb( getOrigAsSecClusGrpMemb() );
		if( retobj == getOrigAsSecClusGrpMemb() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecClusGrpMembEditObj update() {
		getSchema().getSecClusGrpMembTableObj().updateSecClusGrpMemb( (ICFSecSecClusGrpMembObj)this );
		return( null );
	}

	@Override
	public CFSecSecClusGrpMembEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecClusGrpMembTableObj().deleteSecClusGrpMemb( getOrigAsSecClusGrpMemb() );
		return( null );
	}

	@Override
	public ICFSecSecClusGrpMembTableObj getSecClusGrpMembTable() {
		return( orig.getSchema().getSecClusGrpMembTableObj() );
	}

	@Override
	public ICFSecSecClusGrpMembEditObj getEdit() {
		return( (ICFSecSecClusGrpMembEditObj)this );
	}

	@Override
	public ICFSecSecClusGrpMembEditObj getEditAsSecClusGrpMemb() {
		return( (ICFSecSecClusGrpMembEditObj)this );
	}

	@Override
	public ICFSecSecClusGrpMembEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecClusGrpMembObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecClusGrpMembObj getOrigAsSecClusGrpMemb() {
		return( (ICFSecSecClusGrpMembObj)orig );
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
	public ICFSecSecClusGrpMemb getRec() {
		if( rec == null ) {
			rec = getOrigAsSecClusGrpMemb().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecClusGrpMemb value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerGroup = null;
			requiredParentUser = null;
		}
	}

	@Override
	public ICFSecSecClusGrpMemb getSecClusGrpMembRec() {
		return( (ICFSecSecClusGrpMemb)getRec() );
	}

	@Override
	public ICFSecSecClusGrpMembPKey getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( ICFSecSecClusGrpMembPKey value ) {
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
		if( forceRead || ( requiredContainerGroup == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecClusGrpObj obj = ((ICFSecSchemaObj)getOrigAsSecClusGrpMemb().getSchema()).getSecClusGrpTableObj().readSecClusGrpByIdIdx( getPKey().getRequiredSecClusGrpId() );
				requiredContainerGroup = obj;
				if( obj != null ) {
					requiredContainerGroup = obj;
				}
			}
		}
		return( requiredContainerGroup );
	}

	@Override
	public void setRequiredContainerGroup( ICFSecSecClusGrpObj value ) {
		if( rec == null ) {
			getSecClusGrpMembRec();
		}
		if( value != null ) {
			requiredContainerGroup = value;
			getSecClusGrpMembRec().setRequiredContainerGroup(value.getSecClusGrpRec());
		}
		requiredContainerGroup = value;
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser() {
		return( getRequiredParentUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser( boolean forceRead ) {
		if( forceRead || ( requiredParentUser == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecUserObj obj = ((ICFSecSchemaObj)getOrigAsSecClusGrpMemb().getSchema()).getSecUserTableObj().readSecUserByULoginIdx( getPKey().getRequiredLoginId() );
				requiredParentUser = obj;
			}
		}
		return( requiredParentUser );
	}

	@Override
	public void setRequiredParentUser( ICFSecSecUserObj value ) {
		if( rec == null ) {
			getSecClusGrpMembRec();
		}
		if( value != null ) {
			requiredParentUser = value;
			getSecClusGrpMembRec().setRequiredParentUser(value.getSecUserRec());
		}
		else {
			requiredParentUser = null;
			getSecClusGrpMembRec().setRequiredParentUser((ICFSecSecUser)null);
		}
		requiredParentUser = value;
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecClusGrpId(getPKey().getRequiredSecClusGrpId());
			rec.getPKey().setRequiredLoginId(getPKey().getRequiredLoginId());
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecClusGrpId(rec.getPKey().getRequiredSecClusGrpId());
			getPKey().setRequiredLoginId(rec.getPKey().getRequiredLoginId());
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecSecClusGrpMemb origRec = getOrigAsSecClusGrpMemb().getSecClusGrpMembRec();
		ICFSecSecClusGrpMemb myRec = getSecClusGrpMembRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecClusGrpMemb origRec = getOrigAsSecClusGrpMemb().getSecClusGrpMembRec();
		ICFSecSecClusGrpMemb myRec = getSecClusGrpMembRec();
		myRec.set( origRec );
	}
}
