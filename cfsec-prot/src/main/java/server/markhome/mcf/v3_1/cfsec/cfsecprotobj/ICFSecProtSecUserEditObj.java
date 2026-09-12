// Description: Java 25 Protected Instance Edit Object interface for CFSec SecUser.

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

package server.markhome.mcf.v3_1.cfsec.cfsecprotobj;

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

import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public interface ICFSecProtSecUserEditObj
	extends ICFSecProtSecUserObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecProtSecUserObj.
	 */
	ICFSecProtSecUserObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecProtSecUserObj.
	 */
	ICFSecProtSecUserObj getOrigAsSecUser();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecProtSecUserObj create();

	/*
	 *	Update the instance.
	 */
	ICFSecProtSecUserEditObj update();

	/*
	 *	Delete the instance.
	 */
	ICFSecProtSecUserEditObj deleteInstance();

	/**
	 *	Set the user who created this instance.
	 *
	 *	@param	value	The ICFSecProtSecUserObj instance who created this instance.
	 */
	void setCreatedBy( ICFSecProtSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was created.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setCreatedAt( LocalDateTime value );

	/**
	 *	Set the user who updated this instance.
	 *
	 *	@param	value	The ICFSecProtSecUserObj instance who updated this instance.
	 */
	void setUpdatedBy( ICFSecProtSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was updated.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setUpdatedAt( LocalDateTime value );

	/**
	 *	Get a list ICFSecProtSecSessionObj instances referenced by the SecSess key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSessionObj instances referenced by the SecSess key.
	 */
	List<ICFSecProtSecSessionObj> getOptionalComponentsSecSess();

	/**
	 *	Get a list ICFSecProtSecSessionObj instances referenced by the SecProxy key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSessionObj instances referenced by the SecProxy key.
	 */
	List<ICFSecProtSecSessionObj> getOptionalChildrenSecProxy();

	/**
	 *	Get the ICFSecProtSecUserEMConfObj instance referenced by the EMConf key.
	 *
	 *	@return	The ICFSecProtSecUserEMConfObj instance referenced by the EMConf key.
	 */
	ICFSecProtSecUserEMConfObj getOptionalComponentsEMConf();

	/**
	 *	Get the optional ICFSecProtSecUserEMConfObj instance referenced by the EMConf key.
	 *
	 *	@return	The optional ICFSecProtSecUserEMConfObj instance referenced by the EMConf key.
	 */
	ICFSecProtSecUserEMConfObj getOptionalComponentsEMConf( boolean forceRead );

	/**
	 *	Get the ICFSecProtSecUserPWResetObj instance referenced by the PWReset key.
	 *
	 *	@return	The ICFSecProtSecUserPWResetObj instance referenced by the PWReset key.
	 */
	ICFSecProtSecUserPWResetObj getOptionalComponentsPWReset();

	/**
	 *	Get the optional ICFSecProtSecUserPWResetObj instance referenced by the PWReset key.
	 *
	 *	@return	The optional ICFSecProtSecUserPWResetObj instance referenced by the PWReset key.
	 */
	ICFSecProtSecUserPWResetObj getOptionalComponentsPWReset( boolean forceRead );

	/**
	 *	Get a list ICFSecProtSecSysGrpMembObj instances referenced by the SysSecGrpMemb key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSysGrpMembObj instances referenced by the SysSecGrpMemb key.
	 */
	List<ICFSecProtSecSysGrpMembObj> getOptionalChildrenSysSecGrpMemb();

	/**
	 *	Get a list ICFSecProtSecClusGrpMembObj instances referenced by the ClusSecGrpMemb key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecClusGrpMembObj instances referenced by the ClusSecGrpMemb key.
	 */
	List<ICFSecProtSecClusGrpMembObj> getOptionalChildrenClusSecGrpMemb();

	/**
	 *	Get a list ICFSecProtSecTentGrpMembObj instances referenced by the TentSecGrpMemb key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecTentGrpMembObj instances referenced by the TentSecGrpMemb key.
	 */
	List<ICFSecProtSecTentGrpMembObj> getOptionalChildrenTentSecGrpMemb();

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecUserId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecUserId.
	 */
	ICFLibKeyHash256 getRequiredSecUserId();

	/**
	 *	Set the required ICFLibKeyHash256 attribute SecUserId.
	 *
	 *	@param value The required ICFLibKeyHash256 attribute SecUserId value to be applied.
	 */
	void setRequiredSecUserId(ICFLibKeyHash256 value);

	/**
	 *	Get the required String attribute LoginId.
	 *
	 *	@return	The required String attribute LoginId.
	 */
	String getRequiredLoginId();

	/**
	 *	Set the required String attribute LoginId.
	 *
	 *	@param value The required String attribute LoginId value to be applied.
	 */
	void setRequiredLoginId(String value);

	/**
	 *	Get the required ICFSecPubSchema.SecAccountStatusEnum attribute AccountStatus.
	 *
	 *	@return	The required ICFSecPubSchema.SecAccountStatusEnum attribute AccountStatus.
	 */
	ICFSecPubSchema.SecAccountStatusEnum getRequiredAccountStatus();

	/**
	 *	Set the required ICFSecPubSchema.SecAccountStatusEnum attribute AccountStatus.
	 *
	 *	@param value The required ICFSecPubSchema.SecAccountStatusEnum attribute AccountStatus value to be applied.
	 */
	void setRequiredAccountStatus(ICFSecPubSchema.SecAccountStatusEnum value);

	/**
	 *	Get the optional String attribute DfltSysGrpName.
	 *
	 *	@return	The optional String attribute DfltSysGrpName.
	 */
	String getOptionalDfltSysGrpName();

	/**
	 *	Set the optional String attribute DfltSysGrpName.
	 *
	 *	@param value The optional String attribute DfltSysGrpName value to be applied.
	 */
	void setOptionalDfltSysGrpName(String value);

	/**
	 *	Get the optional String attribute DfltClusGrpName.
	 *
	 *	@return	The optional String attribute DfltClusGrpName.
	 */
	String getOptionalDfltClusGrpName();

	/**
	 *	Set the optional String attribute DfltClusGrpName.
	 *
	 *	@param value The optional String attribute DfltClusGrpName value to be applied.
	 */
	void setOptionalDfltClusGrpName(String value);

	/**
	 *	Get the optional String attribute DfltTentGrpName.
	 *
	 *	@return	The optional String attribute DfltTentGrpName.
	 */
	String getOptionalDfltTentGrpName();

	/**
	 *	Set the optional String attribute DfltTentGrpName.
	 *
	 *	@param value The optional String attribute DfltTentGrpName value to be applied.
	 */
	void setOptionalDfltTentGrpName(String value);

	/**
	 *	Get the required String attribute EMailAddress.
	 *
	 *	@return	The required String attribute EMailAddress.
	 */
	String getRequiredEMailAddress();

	/**
	 *	Set the required String attribute EMailAddress.
	 *
	 *	@param value The required String attribute EMailAddress value to be applied.
	 */
	void setRequiredEMailAddress(String value);

	public void copyRecToOrig();
	public void copyOrigToRec();

}
