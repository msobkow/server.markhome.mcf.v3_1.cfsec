// Description: Java 25 base object instance implementation for SecUser

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

public class CFSecSecUserObj
	implements ICFSecSecUserObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecUserEditObj edit;
	protected ICFSecSchemaObj schema;
	protected $implCommaIJavaOptAtomType$ pKey;
	protected ICFSecSecUser rec;
	protected List<ICFSecSecSessionObj> optionalComponentsSecSess;
	protected List<ICFSecSecSessionObj> optionalChildrenSecProxy;
	protected ICFSecSecUserPasswordObj optionalComponentsPassword;
	protected ICFSecSecUserEMConfObj optionalComponentsEMConf;
	protected ICFSecSecUserPWResetObj optionalComponentsPWReset;
	protected ICFSecSecUserPWHistoryObj optionalChildrenPWHistory;
	protected List<ICFSecSecSysGrpMembObj> optionalChildrenSysSecGrpMemb;
	protected List<ICFSecSecClusGrpMembObj> optionalChildrenClusSecGrpMemb;
	protected List<ICFSecSecTentGrpMembObj> optionalChildrenTentSecGrpMemb;

	public CFSecSecUserObj() {
		isNew = true;
	}

	public CFSecSecUserObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecUserTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecUser" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
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
		if( subObj == null ) {
			try {
				if (nextName == null || (nextName != null && nextName.isEmpty())) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredEMConfirmationUuid6");
				}
				ICFLibUuid6 natNextName = ICFLibUuid6.fromString(nextName);
				subObj = ((ICFSecSchemaObj)getSchema()).getSecUserEMConfTableObj().readSecUserEMConfByUUuid6Idx( natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null || (nextName != null && nextName.isEmpty())) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredPasswordResetUuid6");
				}
				ICFLibUuid6 natNextName = ICFLibUuid6.fromString(nextName);
				subObj = ((ICFSecSchemaObj)getSchema()).getSecUserPWResetTableObj().readSecUserPWResetByUUuid6Idx( natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
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
	public ICFSecSecUserObj realise() {
		ICFSecSecUserObj retobj = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().realiseSecUser(
			(ICFSecSecUserObj)this );
		return( (ICFSecSecUserObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecUserTableObj().reallyDeepDisposeSecUser( (ICFSecSecUserObj)this );
	}

	@Override
	public ICFSecSecUserObj read() {
		ICFSecSecUserObj retobj = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( getPKey(), false );
		return( (ICFSecSecUserObj)retobj );
	}

	@Override
	public ICFSecSecUserObj read( boolean forceRead ) {
		ICFSecSecUserObj retobj = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( getPKey(), forceRead );
		return( (ICFSecSecUserObj)retobj );
	}

	@Override
	public ICFSecSecUserTableObj getSecUserTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecUserTableObj() );
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
	public ICFSecSecUser getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecUser().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecUser value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecUser ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecUserRec" );
		}
		rec = value;
		copyRecToPKey();
	}

	@Override
	public ICFSecSecUser getSecUserRec() {
		return( (ICFSecSecUser)getRec() );
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
	public ICFSecSecUserEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecUserObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecUserObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().lockSecUser( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().newEditInstance( lockobj );
		return( (ICFSecSecUserEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecUserEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecUserEditObj getEditAsSecUser() {
		return( (ICFSecSecUserEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecUser rec = getRec();
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
			ICFSecSecUser rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecUserId() {
		return( getPKey() );
	}

	@Override
	public List<ICFSecSecSessionObj> getOptionalComponentsSecSess() {
		List<ICFSecSecSessionObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSessionTableObj().readSecSessionBySecUserIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSessionObj> getOptionalComponentsSecSess( boolean forceRead ) {
		List<ICFSecSecSessionObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSessionTableObj().readSecSessionBySecUserIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecSessionObj> getOptionalChildrenSecProxy() {
		List<ICFSecSecSessionObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSessionTableObj().readSecSessionBySecProxyIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSessionObj> getOptionalChildrenSecProxy( boolean forceRead ) {
		List<ICFSecSecSessionObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSessionTableObj().readSecSessionBySecProxyIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFSecSecUserPasswordObj getOptionalComponentsPassword() {
		return( getOptionalComponentsPassword( false ) );
	}

	@Override
	public ICFSecSecUserPasswordObj getOptionalComponentsPassword( boolean forceRead ) {
		if( ( optionalComponentsPassword == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				optionalComponentsPassword = ((ICFSecSchemaObj)getSchema()).getSecUserPasswordTableObj().readSecUserPasswordByIdIdx( getPKey(), forceRead );
			}
		}
		return( optionalComponentsPassword );
	}

	@Override
	public ICFSecSecUserEMConfObj getOptionalComponentsEMConf() {
		return( getOptionalComponentsEMConf( false ) );
	}

	@Override
	public ICFSecSecUserEMConfObj getOptionalComponentsEMConf( boolean forceRead ) {
		if( ( optionalComponentsEMConf == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				optionalComponentsEMConf = ((ICFSecSchemaObj)getSchema()).getSecUserEMConfTableObj().readSecUserEMConfByIdIdx( getPKey(), forceRead );
			}
		}
		return( optionalComponentsEMConf );
	}

	@Override
	public ICFSecSecUserPWResetObj getOptionalComponentsPWReset() {
		return( getOptionalComponentsPWReset( false ) );
	}

	@Override
	public ICFSecSecUserPWResetObj getOptionalComponentsPWReset( boolean forceRead ) {
		if( ( optionalComponentsPWReset == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				optionalComponentsPWReset = ((ICFSecSchemaObj)getSchema()).getSecUserPWResetTableObj().readSecUserPWResetByIdIdx( getPKey(), forceRead );
			}
		}
		return( optionalComponentsPWReset );
	}

	@Override
	public ICFSecSecUserPWHistoryObj getOptionalChildrenPWHistory() {
		return( getOptionalChildrenPWHistory( false ) );
	}

	@Override
	public ICFSecSecUserPWHistoryObj getOptionalChildrenPWHistory( boolean forceRead ) {
		if( ( optionalChildrenPWHistory == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				optionalChildrenPWHistory = ((ICFSecSchemaObj)getSchema()).getSecUserPWHistoryTableObj().readSecUserPWHistoryByUserIdx( getPKey(), forceRead );
			}
		}
		return( optionalChildrenPWHistory );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> getOptionalChildrenSysSecGrpMemb() {
		List<ICFSecSecSysGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembByLoginIdx( getSecUserRec().getRequiredLoginId(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> getOptionalChildrenSysSecGrpMemb( boolean forceRead ) {
		List<ICFSecSecSysGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembByLoginIdx( getSecUserRec().getRequiredLoginId(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> getOptionalChildrenClusSecGrpMemb() {
		List<ICFSecSecClusGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().readSecClusGrpMembByLoginIdx( getSecUserRec().getRequiredLoginId(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> getOptionalChildrenClusSecGrpMemb( boolean forceRead ) {
		List<ICFSecSecClusGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecClusGrpMembTableObj().readSecClusGrpMembByLoginIdx( getSecUserRec().getRequiredLoginId(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> getOptionalChildrenTentSecGrpMemb() {
		List<ICFSecSecTentGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentGrpMembTableObj().readSecTentGrpMembByUserIdx( getSecUserRec().getRequiredLoginId(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> getOptionalChildrenTentSecGrpMemb( boolean forceRead ) {
		List<ICFSecSecTentGrpMembObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getSecTentGrpMembTableObj().readSecTentGrpMembByUserIdx( getSecUserRec().getRequiredLoginId(),
			forceRead );
		return( retval );
	}

	@Override
	public String getRequiredLoginId() {
		return( getSecUserRec().getRequiredLoginId() );
	}

	@Override
	public ICFSecPubSchema.SecAccountStatusEnum getRequiredAccountStatus() {
		return( getSecUserRec().getRequiredAccountStatus() );
	}

	@Override
	public String getOptionalDfltSysGrpName() {
		return( getSecUserRec().getOptionalDfltSysGrpName() );
	}

	@Override
	public String getOptionalDfltClusGrpName() {
		return( getSecUserRec().getOptionalDfltClusGrpName() );
	}

	@Override
	public String getOptionalDfltTentGrpName() {
		return( getSecUserRec().getOptionalDfltTentGrpName() );
	}

	@Override
	public String getRequiredEMailAddress() {
		return( getSecUserRec().getRequiredEMailAddress() );
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
