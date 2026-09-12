// Description: Java 25 Protected Instance Edit Object interface for CFSec SecSysGrp.

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

public interface ICFSecProtSecSysGrpEditObj
	extends ICFSecProtSecSysGrpObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecProtSecSysGrpObj.
	 */
	ICFSecProtSecSysGrpObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecProtSecSysGrpObj.
	 */
	ICFSecProtSecSysGrpObj getOrigAsSecSysGrp();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecProtSecSysGrpObj create();

	/*
	 *	Update the instance.
	 */
	ICFSecProtSecSysGrpEditObj update();

	/*
	 *	Delete the instance.
	 */
	ICFSecProtSecSysGrpEditObj deleteInstance();

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
	 *	Get a list ICFSecProtSecSysGrpIncObj instances referenced by the IncByGrp key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSysGrpIncObj instances referenced by the IncByGrp key.
	 */
	List<ICFSecProtSecSysGrpIncObj> getOptionalComponentsIncByGrp();

	/**
	 *	Get a list ICFSecProtSecSysGrpMembObj instances referenced by the MembByGrp key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSysGrpMembObj instances referenced by the MembByGrp key.
	 */
	List<ICFSecProtSecSysGrpMembObj> getOptionalChildrenMembByGrp();

	/**
	 *	Get a list ICFSecProtSecClusGrpObj instances referenced by the ImplClusGrp key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecClusGrpObj instances referenced by the ImplClusGrp key.
	 */
	List<ICFSecProtSecClusGrpObj> getOptionalComponentsImplClusGrp();

	/**
	 *	Get a list ICFSecProtSecTentGrpObj instances referenced by the ImplTentGrp key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecTentGrpObj instances referenced by the ImplTentGrp key.
	 */
	List<ICFSecProtSecTentGrpObj> getOptionalComponentsImplTentGrp();

	/**
	 *	Get the ICFSecProtSecSysRoleObj instance referenced by the ImplSysRole key.
	 *
	 *	@return	The ICFSecProtSecSysRoleObj instance referenced by the ImplSysRole key.
	 */
	ICFSecProtSecSysRoleObj getOptionalComponentsImplSysRole();

	/**
	 *	Get the optional ICFSecProtSecSysRoleObj instance referenced by the ImplSysRole key.
	 *
	 *	@return	The optional ICFSecProtSecSysRoleObj instance referenced by the ImplSysRole key.
	 */
	ICFSecProtSecSysRoleObj getOptionalComponentsImplSysRole( boolean forceRead );

	/**
	 *	Get a list ICFSecProtSecClusRoleObj instances referenced by the ImplClusRole key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecClusRoleObj instances referenced by the ImplClusRole key.
	 */
	List<ICFSecProtSecClusRoleObj> getOptionalComponentsImplClusRole();

	/**
	 *	Get a list ICFSecProtSecTentRoleObj instances referenced by the ImplTentRole key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecTentRoleObj instances referenced by the ImplTentRole key.
	 */
	List<ICFSecProtSecTentRoleObj> getOptionalComponentsImplTentRole();

	/**
	 *	Get a list ICFSecProtSecSysGrpIncObj instances referenced by the SysGrpByName key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSysGrpIncObj instances referenced by the SysGrpByName key.
	 */
	List<ICFSecProtSecSysGrpIncObj> getOptionalChildrenSysGrpByName();

	/**
	 *	Get a list ICFSecProtSecSysRoleEnablesObj instances referenced by the RoleByEnableName key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSysRoleEnablesObj instances referenced by the RoleByEnableName key.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> getOptionalChildrenRoleByEnableName();

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecSysGrpId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecSysGrpId.
	 */
	ICFLibKeyHash256 getRequiredSecSysGrpId();

	/**
	 *	Set the required ICFLibKeyHash256 attribute SecSysGrpId.
	 *
	 *	@param value The required ICFLibKeyHash256 attribute SecSysGrpId value to be applied.
	 */
	void setRequiredSecSysGrpId(ICFLibKeyHash256 value);

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Set the required String attribute Name.
	 *
	 *	@param value The required String attribute Name value to be applied.
	 */
	void setRequiredName(String value);

	/**
	 *	Get the required ICFSecPubSchema.SecLevelEnum attribute SecLevel.
	 *
	 *	@return	The required ICFSecPubSchema.SecLevelEnum attribute SecLevel.
	 */
	ICFSecPubSchema.SecLevelEnum getRequiredSecLevel();

	/**
	 *	Set the required ICFSecPubSchema.SecLevelEnum attribute SecLevel.
	 *
	 *	@param value The required ICFSecPubSchema.SecLevelEnum attribute SecLevel value to be applied.
	 */
	void setRequiredSecLevel(ICFSecPubSchema.SecLevelEnum value);

	public void copyRecToOrig();
	public void copyOrigToRec();

}
