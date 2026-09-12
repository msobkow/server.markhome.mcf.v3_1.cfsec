// Description: Java 25 implementation of a SecUser buffer

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

package server.markhome.mcf.v3_1.cfsec.cfsec.buff;

import java.lang.reflect.*;
import java.io.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
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
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;

public class CFSecBuffSecUser
	implements ICFSecSecUser, Comparable<Object>, Serializable
{
	protected ICFLibKeyHash256 requiredSecUserId;
	protected int requiredRevision;
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected CFLibDbKeyHash256 createdBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected CFLibDbKeyHash256 updatedBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();
	protected String requiredLoginId;
	protected ICFSecPubSchema.SecAccountStatusEnum requiredAccountStatus;
	protected String optionalDfltSysGrpName;
	protected String optionalDfltClusGrpName;
	protected String optionalDfltTentGrpName;
	protected String requiredEMailAddress;

	public CFSecBuffSecUser() {
		requiredSecUserId = CFLibDbKeyHash256.fromHex( ICFSecPubSecUser.SECUSERID_INIT_VALUE.toString() );
		requiredLoginId = ICFSecPubSecUser.LOGINID_INIT_VALUE;
		requiredAccountStatus = ICFSecPubSecUser.ACCOUNTSTATUS_INIT_VALUE;
		optionalDfltSysGrpName = null;
		optionalDfltClusGrpName = null;
		optionalDfltTentGrpName = null;
		requiredEMailAddress = ICFSecProtSecUser.EMAILADDRESS_INIT_VALUE;
	}

	@Override
	public $implCommaIJavaOptAtomType$ getPKey() {
		return (requiredSecUserId);
	}

	@Override
	public void setPKey($implCommaIJavaOptAtomType$ requiredSecUserId) {
		if(requiredSecUserId != null) {
			this.requiredSecUserId = requiredSecUserId;
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecUserId() {
		return(requiredSecUserId);
	}

	public void setRequiredSecUserId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecUserId",
				1,
				"value" );
		}
		requiredSecUserId = value;
	}

	@Override
	public List<ICFSecSecSession> getOptionalComponentsSecSess() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsSecSess", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecSessionTable targetTable = targetBackingCFSec.getTableSecSession();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsSecSess", 0, "ICFSecSchema.getBackingCFSec().getTableSecSession()");
		}
		ICFSecSecSession[] targetArr = targetTable.readDerivedBySecUserIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		if( targetArr != null ) {
			List<ICFSecSecSession> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecSession> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecSession> getOptionalComponentsSecSess() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsSecSess", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecSessionTable targetTable = targetBackingCFSec.getTableSecSession();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsSecSess", 0, "ICFSecSchema.getBackingCFSec().getTableSecSession()");
		}
		ICFSecSecSession[] targetArr = targetTable.readDerivedBySecUserIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		if( targetArr != null ) {
			List<ICFSecSecSession> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecSession> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecSession> getOptionalComponentsSecSess() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsSecSess", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecSessionTable targetTable = targetBackingCFSec.getTableSecSession();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsSecSess", 0, "ICFSecSchema.getBackingCFSec().getTableSecSession()");
		}
		ICFSecPubSecSession[] targetArr = targetTable.readDerivedBySecUserIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		if( targetArr != null ) {
			List<ICFSecPubSecSession> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecPubSecSession> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecSession> getOptionalChildrenSecProxy() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSecProxy", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecSessionTable targetTable = targetBackingCFSec.getTableSecSession();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSecProxy", 0, "ICFSecSchema.getBackingCFSec().getTableSecSession()");
		}
		ICFSecSecSession[] targetArr = targetTable.readDerivedBySecProxyIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		if( targetArr != null ) {
			List<ICFSecSecSession> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecSession> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecSession> getOptionalChildrenSecProxy() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSecProxy", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecSessionTable targetTable = targetBackingCFSec.getTableSecSession();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSecProxy", 0, "ICFSecSchema.getBackingCFSec().getTableSecSession()");
		}
		ICFSecSecSession[] targetArr = targetTable.readDerivedBySecProxyIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		if( targetArr != null ) {
			List<ICFSecSecSession> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecSession> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public ICFSecSecUserPassword getOptionalComponentsPassword() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPassword", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecUserPasswordTable targetTable = targetBackingCFSec.getTableSecUserPassword();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPassword", 0, "ICFSecSchema.getBackingCFSec().getTableSecUserPassword()");
		}
		ICFSecSecUserPassword targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		return(targetRec);
	}

	@Override
	public ICFSecSecUserEMConf getOptionalComponentsEMConf() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsEMConf", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecUserEMConfTable targetTable = targetBackingCFSec.getTableSecUserEMConf();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsEMConf", 0, "ICFSecSchema.getBackingCFSec().getTableSecUserEMConf()");
		}
		ICFSecSecUserEMConf targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		return(targetRec);
	}

	@Override
	public ICFSecSecUserEMConf getOptionalComponentsEMConf() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsEMConf", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecUserEMConfTable targetTable = targetBackingCFSec.getTableSecUserEMConf();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsEMConf", 0, "ICFSecSchema.getBackingCFSec().getTableSecUserEMConf()");
		}
		ICFSecSecUserEMConf targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		return(targetRec);
	}

	@Override
	public ICFSecSecUserPWReset getOptionalComponentsPWReset() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPWReset", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecUserPWResetTable targetTable = targetBackingCFSec.getTableSecUserPWReset();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPWReset", 0, "ICFSecSchema.getBackingCFSec().getTableSecUserPWReset()");
		}
		ICFSecSecUserPWReset targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		return(targetRec);
	}

	@Override
	public ICFSecSecUserPWReset getOptionalComponentsPWReset() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPWReset", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecUserPWResetTable targetTable = targetBackingCFSec.getTableSecUserPWReset();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsPWReset", 0, "ICFSecSchema.getBackingCFSec().getTableSecUserPWReset()");
		}
		ICFSecSecUserPWReset targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		return(targetRec);
	}

	@Override
	public ICFSecSecUserPWHistory getOptionalChildrenPWHistory() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenPWHistory", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecUserPWHistoryTable targetTable = targetBackingCFSec.getTableSecUserPWHistory();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenPWHistory", 0, "ICFSecSchema.getBackingCFSec().getTableSecUserPWHistory()");
		}
		ICFSecSecUserPWHistory targetRec = targetTable.readDerivedByUserIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId());
		return(targetRec);
	}

	@Override
	public CFLibDbKeyHash256 getCreatedByUserId() {
		return( createdByUserId );
	}

	@Override
	public void setCreatedByUserId( CFLibDbKeyHash256 value ) {
		createdByUserId = value;
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( createdAt );
	}

	@Override
	public void setCreatedAt( LocalDateTime value ) {
		createdAt = value;
	}

	@Override
	public CFLibDbKeyHash256 getUpdatedByUserId() {
		return( updatedByUserId );
	}

	@Override
	public void setUpdatedByUserId( CFLibDbKeyHash256 value ) {
		updatedByUserId = value;
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( updatedAt );
	}

	@Override
	public void setUpdatedAt( LocalDateTime value ) {
		updatedAt = value;
	}

	@Override
	public int getRequiredRevision() {
		return( requiredRevision );
	}

	@Override
	public void setRequiredRevision( int value ) {
		requiredRevision = value;
	}

	@Override
	public int getClassCode() {
		return( ICFSecSecUser.CLASS_CODE );
	}

	@Override
	public List<ICFSecSecSysGrpMemb> getOptionalChildrenSysSecGrpMemb() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSysSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecSysGrpMembTable targetTable = targetBackingCFSec.getTableSecSysGrpMemb();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSysSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec().getTableSecSysGrpMemb()");
		}
		ICFSecSecSysGrpMemb[] targetArr = targetTable.readDerivedByLoginIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredLoginId());
		if( targetArr != null ) {
			List<ICFSecSecSysGrpMemb> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecSysGrpMemb> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecSysGrpMemb> getOptionalChildrenSysSecGrpMemb() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSysSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecSysGrpMembTable targetTable = targetBackingCFSec.getTableSecSysGrpMemb();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSysSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec().getTableSecSysGrpMemb()");
		}
		ICFSecSecSysGrpMemb[] targetArr = targetTable.readDerivedByLoginIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredLoginId());
		if( targetArr != null ) {
			List<ICFSecSecSysGrpMemb> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecSysGrpMemb> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecClusGrpMemb> getOptionalChildrenClusSecGrpMemb() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenClusSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecClusGrpMembTable targetTable = targetBackingCFSec.getTableSecClusGrpMemb();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenClusSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec().getTableSecClusGrpMemb()");
		}
		ICFSecSecClusGrpMemb[] targetArr = targetTable.readDerivedByLoginIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredLoginId());
		if( targetArr != null ) {
			List<ICFSecSecClusGrpMemb> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecClusGrpMemb> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecClusGrpMemb> getOptionalChildrenClusSecGrpMemb() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenClusSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecClusGrpMembTable targetTable = targetBackingCFSec.getTableSecClusGrpMemb();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenClusSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec().getTableSecClusGrpMemb()");
		}
		ICFSecSecClusGrpMemb[] targetArr = targetTable.readDerivedByLoginIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredLoginId());
		if( targetArr != null ) {
			List<ICFSecSecClusGrpMemb> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecClusGrpMemb> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecTentGrpMemb> getOptionalChildrenTentSecGrpMemb() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenTentSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecTentGrpMembTable targetTable = targetBackingCFSec.getTableSecTentGrpMemb();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenTentSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec().getTableSecTentGrpMemb()");
		}
		ICFSecSecTentGrpMemb[] targetArr = targetTable.readDerivedByUserIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredLoginId());
		if( targetArr != null ) {
			List<ICFSecSecTentGrpMemb> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecTentGrpMemb> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public List<ICFSecSecTentGrpMemb> getOptionalChildrenTentSecGrpMemb() {
		ICFSecSchema targetBackingCFSec = ICFSecSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenTentSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec()");
		}
		ICFSecSecTentGrpMembTable targetTable = targetBackingCFSec.getTableSecTentGrpMemb();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenTentSecGrpMemb", 0, "ICFSecSchema.getBackingCFSec().getTableSecTentGrpMemb()");
		}
		ICFSecSecTentGrpMemb[] targetArr = targetTable.readDerivedByUserIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredLoginId());
		if( targetArr != null ) {
			List<ICFSecSecTentGrpMemb> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFSecSecTentGrpMemb> results = new ArrayList<>();
			return( results );
		}
	}

	@Override
	public String getRequiredLoginId() {
		return(requiredLoginId);
	}

	public void setRequiredLoginId( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredLoginId",
				1,
				"value" );
		}
		else if( value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredLoginId",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		requiredLoginId = value;
	}

	@Override
	public ICFSecPubSchema.SecAccountStatusEnum getRequiredAccountStatus() {
		return(requiredAccountStatus);
	}

	public void setRequiredAccountStatus( ICFSecPubSchema.SecAccountStatusEnum value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredAccountStatus",
				1,
				"value" );
		}
		requiredAccountStatus = value;
	}

	@Override
	public String getOptionalDfltSysGrpName() {
		return(optionalDfltSysGrpName);
	}

	public void setOptionalDfltSysGrpName( String value ) {
		if( value != null && value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDfltSysGrpName",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		optionalDfltSysGrpName = value;
	}

	@Override
	public String getOptionalDfltClusGrpName() {
		return(optionalDfltClusGrpName);
	}

	public void setOptionalDfltClusGrpName( String value ) {
		if( value != null && value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDfltClusGrpName",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		optionalDfltClusGrpName = value;
	}

	@Override
	public String getOptionalDfltTentGrpName() {
		return(optionalDfltTentGrpName);
	}

	public void setOptionalDfltTentGrpName( String value ) {
		if( value != null && value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDfltTentGrpName",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		optionalDfltTentGrpName = value;
	}

	@Override
	public String getRequiredEMailAddress() {
		return(requiredEMailAddress);
	}

	public void setRequiredEMailAddress( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredEMailAddress",
				1,
				"value" );
		}
		else if( value.length() > 512 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredEMailAddress",
				1,
				"value.length()",
				value.length(),
				512 );
		}
		requiredEMailAddress = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecSecUser rhs ) {
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			if( getRequiredAccountStatus() != null ) {
				if( rhs.getRequiredAccountStatus() != null ) {
					if( ! getRequiredAccountStatus().equals( rhs.getRequiredAccountStatus() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAccountStatus() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					if( ! getOptionalDfltSysGrpName().equals( rhs.getOptionalDfltSysGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					if( ! getOptionalDfltClusGrpName().equals( rhs.getOptionalDfltClusGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					if( ! getOptionalDfltTentGrpName().equals( rhs.getOptionalDfltTentGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( false );
				}
			}
			if( getRequiredEMailAddress() != null ) {
				if( rhs.getRequiredEMailAddress() != null ) {
					if( ! getRequiredEMailAddress().equals( rhs.getRequiredEMailAddress() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEMailAddress() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecSecUserH rhs ) {
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			if( getRequiredAccountStatus() != null ) {
				if( rhs.getRequiredAccountStatus() != null ) {
					if( ! getRequiredAccountStatus().equals( rhs.getRequiredAccountStatus() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAccountStatus() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					if( ! getOptionalDfltSysGrpName().equals( rhs.getOptionalDfltSysGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					if( ! getOptionalDfltClusGrpName().equals( rhs.getOptionalDfltClusGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					if( ! getOptionalDfltTentGrpName().equals( rhs.getOptionalDfltTentGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( false );
				}
			}
			if( getRequiredEMailAddress() != null ) {
				if( rhs.getRequiredEMailAddress() != null ) {
					if( ! getRequiredEMailAddress().equals( rhs.getRequiredEMailAddress() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEMailAddress() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecSecUserHPKey ) {
			ICFSecSecUserHPKey rhs = (ICFSecSecUserHPKey)obj;
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecSecUserByULoginIdxKey rhs ) {
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecSecUserByEMAddrIdxKey rhs ) {
			if( getRequiredEMailAddress() != null ) {
				if( rhs.getRequiredEMailAddress() != null ) {
					if( ! getRequiredEMailAddress().equals( rhs.getRequiredEMailAddress() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEMailAddress() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecUser rhs ) {
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			if( getRequiredAccountStatus() != null ) {
				if( rhs.getRequiredAccountStatus() != null ) {
					if( ! getRequiredAccountStatus().equals( rhs.getRequiredAccountStatus() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAccountStatus() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					if( ! getOptionalDfltSysGrpName().equals( rhs.getOptionalDfltSysGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					if( ! getOptionalDfltClusGrpName().equals( rhs.getOptionalDfltClusGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					if( ! getOptionalDfltTentGrpName().equals( rhs.getOptionalDfltTentGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( false );
				}
			}
			if( getRequiredEMailAddress() != null ) {
				if( rhs.getRequiredEMailAddress() != null ) {
					if( ! getRequiredEMailAddress().equals( rhs.getRequiredEMailAddress() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEMailAddress() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecUserH rhs ) {
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			if( getRequiredAccountStatus() != null ) {
				if( rhs.getRequiredAccountStatus() != null ) {
					if( ! getRequiredAccountStatus().equals( rhs.getRequiredAccountStatus() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAccountStatus() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					if( ! getOptionalDfltSysGrpName().equals( rhs.getOptionalDfltSysGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					if( ! getOptionalDfltClusGrpName().equals( rhs.getOptionalDfltClusGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					if( ! getOptionalDfltTentGrpName().equals( rhs.getOptionalDfltTentGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( false );
				}
			}
			if( getRequiredEMailAddress() != null ) {
				if( rhs.getRequiredEMailAddress() != null ) {
					if( ! getRequiredEMailAddress().equals( rhs.getRequiredEMailAddress() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEMailAddress() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecUserHPKey rhs ) {
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecSecUserByULoginIdxKey rhs ) {
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecSecUserByEMAddrIdxKey rhs ) {
			if( getRequiredEMailAddress() != null ) {
				if( rhs.getRequiredEMailAddress() != null ) {
					if( ! getRequiredEMailAddress().equals( rhs.getRequiredEMailAddress() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredEMailAddress() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecUser rhs ) {
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			if( getRequiredAccountStatus() != null ) {
				if( rhs.getRequiredAccountStatus() != null ) {
					if( ! getRequiredAccountStatus().equals( rhs.getRequiredAccountStatus() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAccountStatus() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					if( ! getOptionalDfltSysGrpName().equals( rhs.getOptionalDfltSysGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					if( ! getOptionalDfltClusGrpName().equals( rhs.getOptionalDfltClusGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					if( ! getOptionalDfltTentGrpName().equals( rhs.getOptionalDfltTentGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecUserH rhs ) {
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			if( getRequiredAccountStatus() != null ) {
				if( rhs.getRequiredAccountStatus() != null ) {
					if( ! getRequiredAccountStatus().equals( rhs.getRequiredAccountStatus() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredAccountStatus() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					if( ! getOptionalDfltSysGrpName().equals( rhs.getOptionalDfltSysGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					if( ! getOptionalDfltClusGrpName().equals( rhs.getOptionalDfltClusGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( false );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					if( ! getOptionalDfltTentGrpName().equals( rhs.getOptionalDfltTentGrpName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecUserHPKey rhs ) {
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecSecUserByULoginIdxKey rhs ) {
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else {
			boolean retval = super.equals( obj );
			return( retval );
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		hashCode = hashCode + getCreatedByUserId().hashCode();
		hashCode = hashCode + getCreatedAt().hashCode();
		hashCode = hashCode + getUpdatedByUserId().hashCode();
		hashCode = hashCode + getUpdatedAt().hashCode();
		hashCode = hashCode + getRequiredSecUserId().hashCode();
		if( getRequiredLoginId() != null ) {
			hashCode = hashCode + getRequiredLoginId().hashCode();
		}
		hashCode = ( hashCode * 0x10000 ) + getRequiredAccountStatus().ordinal();
		if( getOptionalDfltSysGrpName() != null ) {
			hashCode = hashCode + getOptionalDfltSysGrpName().hashCode();
		}
		if( getOptionalDfltClusGrpName() != null ) {
			hashCode = hashCode + getOptionalDfltClusGrpName().hashCode();
		}
		if( getOptionalDfltTentGrpName() != null ) {
			hashCode = hashCode + getOptionalDfltTentGrpName().hashCode();
		}
		if( getRequiredEMailAddress() != null ) {
			hashCode = hashCode + getRequiredEMailAddress().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecSecUser rhs ) {
			cmp = 0;
			{
				cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}
			}
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			if (getRequiredAccountStatus() != null) {
				if (rhs.getRequiredAccountStatus() != null) {
					cmp = getRequiredAccountStatus().compareTo( rhs.getRequiredAccountStatus() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAccountStatus() != null) {
				return( -1 );
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					cmp = getOptionalDfltSysGrpName().compareTo( rhs.getOptionalDfltSysGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					cmp = getOptionalDfltClusGrpName().compareTo( rhs.getOptionalDfltClusGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					cmp = getOptionalDfltTentGrpName().compareTo( rhs.getOptionalDfltTentGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( -1 );
				}
			}
			if (getRequiredEMailAddress() != null) {
				if (rhs.getRequiredEMailAddress() != null) {
					cmp = getRequiredEMailAddress().compareTo( rhs.getRequiredEMailAddress() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEMailAddress() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecSecUserHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecSecUserH rhs ) {
			cmp = 0;
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			if (getRequiredAccountStatus() != null) {
				if (rhs.getRequiredAccountStatus() != null) {
					cmp = getRequiredAccountStatus().compareTo( rhs.getRequiredAccountStatus() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAccountStatus() != null) {
				return( -1 );
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					cmp = getOptionalDfltSysGrpName().compareTo( rhs.getOptionalDfltSysGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					cmp = getOptionalDfltClusGrpName().compareTo( rhs.getOptionalDfltClusGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					cmp = getOptionalDfltTentGrpName().compareTo( rhs.getOptionalDfltTentGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( -1 );
				}
			}
			if (getRequiredEMailAddress() != null) {
				if (rhs.getRequiredEMailAddress() != null) {
					cmp = getRequiredEMailAddress().compareTo( rhs.getRequiredEMailAddress() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEMailAddress() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecSecUserByULoginIdxKey rhs ) {
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecSecUserByEMAddrIdxKey rhs ) {
			if (getRequiredEMailAddress() != null) {
				if (rhs.getRequiredEMailAddress() != null) {
					cmp = getRequiredEMailAddress().compareTo( rhs.getRequiredEMailAddress() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEMailAddress() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecUser rhs ) {
			cmp = 0;
			{
				cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}
			}
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			if (getRequiredAccountStatus() != null) {
				if (rhs.getRequiredAccountStatus() != null) {
					cmp = getRequiredAccountStatus().compareTo( rhs.getRequiredAccountStatus() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAccountStatus() != null) {
				return( -1 );
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					cmp = getOptionalDfltSysGrpName().compareTo( rhs.getOptionalDfltSysGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					cmp = getOptionalDfltClusGrpName().compareTo( rhs.getOptionalDfltClusGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					cmp = getOptionalDfltTentGrpName().compareTo( rhs.getOptionalDfltTentGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( -1 );
				}
			}
			if (getRequiredEMailAddress() != null) {
				if (rhs.getRequiredEMailAddress() != null) {
					cmp = getRequiredEMailAddress().compareTo( rhs.getRequiredEMailAddress() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEMailAddress() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecProtSecUserHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecUserH rhs ) {
			cmp = 0;
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			if (getRequiredAccountStatus() != null) {
				if (rhs.getRequiredAccountStatus() != null) {
					cmp = getRequiredAccountStatus().compareTo( rhs.getRequiredAccountStatus() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAccountStatus() != null) {
				return( -1 );
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					cmp = getOptionalDfltSysGrpName().compareTo( rhs.getOptionalDfltSysGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					cmp = getOptionalDfltClusGrpName().compareTo( rhs.getOptionalDfltClusGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					cmp = getOptionalDfltTentGrpName().compareTo( rhs.getOptionalDfltTentGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( -1 );
				}
			}
			if (getRequiredEMailAddress() != null) {
				if (rhs.getRequiredEMailAddress() != null) {
					cmp = getRequiredEMailAddress().compareTo( rhs.getRequiredEMailAddress() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEMailAddress() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecUserByULoginIdxKey rhs ) {
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecUserByEMAddrIdxKey rhs ) {
			if (getRequiredEMailAddress() != null) {
				if (rhs.getRequiredEMailAddress() != null) {
					cmp = getRequiredEMailAddress().compareTo( rhs.getRequiredEMailAddress() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredEMailAddress() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecUser rhs ) {
			cmp = 0;
			{
				cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}
			}
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			if (getRequiredAccountStatus() != null) {
				if (rhs.getRequiredAccountStatus() != null) {
					cmp = getRequiredAccountStatus().compareTo( rhs.getRequiredAccountStatus() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAccountStatus() != null) {
				return( -1 );
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					cmp = getOptionalDfltSysGrpName().compareTo( rhs.getOptionalDfltSysGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					cmp = getOptionalDfltClusGrpName().compareTo( rhs.getOptionalDfltClusGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					cmp = getOptionalDfltTentGrpName().compareTo( rhs.getOptionalDfltTentGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecUserHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecUserH rhs ) {
			cmp = 0;
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			if (getRequiredAccountStatus() != null) {
				if (rhs.getRequiredAccountStatus() != null) {
					cmp = getRequiredAccountStatus().compareTo( rhs.getRequiredAccountStatus() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredAccountStatus() != null) {
				return( -1 );
			}
			if( getOptionalDfltSysGrpName() != null ) {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					cmp = getOptionalDfltSysGrpName().compareTo( rhs.getOptionalDfltSysGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltSysGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltClusGrpName() != null ) {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					cmp = getOptionalDfltClusGrpName().compareTo( rhs.getOptionalDfltClusGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltClusGrpName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDfltTentGrpName() != null ) {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					cmp = getOptionalDfltTentGrpName().compareTo( rhs.getOptionalDfltTentGrpName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDfltTentGrpName() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecUserByULoginIdxKey rhs ) {
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"obj",
				obj,
				null );
		}
	}

	@Override
	public void set( ICFSecSecUser src ) {
		setSecUser( src );
	}

	@Override
	public void setSecUser( ICFSecSecUser src ) {
		setRequiredSecUserId(src.getRequiredSecUserId());
		setRequiredRevision( src.getRequiredRevision() );
		setCreatedByUserId( src.getCreatedByUserId() );
		setCreatedAt( src.getCreatedAt() );
		setUpdatedByUserId( src.getUpdatedByUserId() );
		setUpdatedAt( src.getUpdatedAt() );
		setRequiredLoginId(src.getRequiredLoginId());
		setRequiredAccountStatus(src.getRequiredAccountStatus());
		setOptionalDfltSysGrpName(src.getOptionalDfltSysGrpName());
		setOptionalDfltClusGrpName(src.getOptionalDfltClusGrpName());
		setOptionalDfltTentGrpName(src.getOptionalDfltTentGrpName());
		setRequiredEMailAddress(src.getRequiredEMailAddress());
	}

	@Override
	public void set( ICFSecSecUserH src ) {
		setSecUser( src );
	}

	@Override
	public void setSecUser( ICFSecSecUserH src ) {
		setRequiredSecUserId(src.getRequiredSecUserId());
		setRequiredLoginId(src.getRequiredLoginId());
		setRequiredAccountStatus(src.getRequiredAccountStatus());
		setOptionalDfltSysGrpName(src.getOptionalDfltSysGrpName());
		setOptionalDfltClusGrpName(src.getOptionalDfltClusGrpName());
		setOptionalDfltTentGrpName(src.getOptionalDfltTentGrpName());
		setRequiredEMailAddress(src.getRequiredEMailAddress());
	}

	@Override
	public void set( ICFSecProtSecUser src ) {
		setSecUser( src );
	}

	@Override
	public void setSecUser( ICFSecProtSecUser src ) {
		setRequiredSecUserId(src.getRequiredSecUserId());
		setRequiredRevision( src.getRequiredRevision() );
		setCreatedByUserId( src.getCreatedByUserId() );
		setCreatedAt( src.getCreatedAt() );
		setUpdatedByUserId( src.getUpdatedByUserId() );
		setUpdatedAt( src.getUpdatedAt() );
		setRequiredLoginId(src.getRequiredLoginId());
		setRequiredAccountStatus(src.getRequiredAccountStatus());
		setOptionalDfltSysGrpName(src.getOptionalDfltSysGrpName());
		setOptionalDfltClusGrpName(src.getOptionalDfltClusGrpName());
		setOptionalDfltTentGrpName(src.getOptionalDfltTentGrpName());
		setRequiredEMailAddress(src.getRequiredEMailAddress());
	}

	@Override
	public void set( ICFSecProtSecUserH src ) {
		setSecUser( src );
	}

	@Override
	public void setSecUser( ICFSecProtSecUserH src ) {
		setRequiredSecUserId(src.getRequiredSecUserId());
		setRequiredLoginId(src.getRequiredLoginId());
		setRequiredAccountStatus(src.getRequiredAccountStatus());
		setOptionalDfltSysGrpName(src.getOptionalDfltSysGrpName());
		setOptionalDfltClusGrpName(src.getOptionalDfltClusGrpName());
		setOptionalDfltTentGrpName(src.getOptionalDfltTentGrpName());
		setRequiredEMailAddress(src.getRequiredEMailAddress());
	}

	@Override
	public void set( ICFSecPubSecUser src ) {
		setSecUser( src );
	}

	@Override
	public void setSecUser( ICFSecPubSecUser src ) {
		setRequiredSecUserId(src.getRequiredSecUserId());
		setRequiredRevision( src.getRequiredRevision() );
		setCreatedByUserId( src.getCreatedByUserId() );
		setCreatedAt( src.getCreatedAt() );
		setUpdatedByUserId( src.getUpdatedByUserId() );
		setUpdatedAt( src.getUpdatedAt() );
		setRequiredLoginId(src.getRequiredLoginId());
		setRequiredAccountStatus(src.getRequiredAccountStatus());
		setOptionalDfltSysGrpName(src.getOptionalDfltSysGrpName());
		setOptionalDfltClusGrpName(src.getOptionalDfltClusGrpName());
		setOptionalDfltTentGrpName(src.getOptionalDfltTentGrpName());
	}

	@Override
	public void set( ICFSecPubSecUserH src ) {
		setSecUser( src );
	}

	@Override
	public void setSecUser( ICFSecPubSecUserH src ) {
		setRequiredSecUserId(src.getRequiredSecUserId());
		setRequiredLoginId(src.getRequiredLoginId());
		setRequiredAccountStatus(src.getRequiredAccountStatus());
		setOptionalDfltSysGrpName(src.getOptionalDfltSysGrpName());
		setOptionalDfltClusGrpName(src.getOptionalDfltClusGrpName());
		setOptionalDfltTentGrpName(src.getOptionalDfltTentGrpName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = ""
			+ " RequiredSecUserId=" + "\"" + getRequiredSecUserId().toString() + "\""
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\""
			+ " RequiredSecUserId=" + "\"" + getRequiredSecUserId().toString() + "\""
			+ " RequiredLoginId=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredLoginId() ) + "\""
			+ " RequiredAccountStatus=" + "\"" + getRequiredAccountStatus().toString() + "\""
			+ " OptionalDfltSysGrpName=" + ( ( getOptionalDfltSysGrpName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDfltSysGrpName() ) + "\"" )
			+ " OptionalDfltClusGrpName=" + ( ( getOptionalDfltClusGrpName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDfltClusGrpName() ) + "\"" )
			+ " OptionalDfltTentGrpName=" + ( ( getOptionalDfltTentGrpName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDfltTentGrpName() ) + "\"" )
			+ " RequiredEMailAddress=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredEMailAddress() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecBuffSecUser" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
