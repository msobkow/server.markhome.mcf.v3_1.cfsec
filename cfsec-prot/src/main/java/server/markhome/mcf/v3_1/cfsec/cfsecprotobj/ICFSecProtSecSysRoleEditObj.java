// Description: Java 25 Protected Instance Edit Object interface for CFSec SecSysRole.

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

public interface ICFSecProtSecSysRoleEditObj
	extends ICFSecProtSecSysRoleObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecProtSecSysRoleObj.
	 */
	ICFSecProtSecSysRoleObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecProtSecSysRoleObj.
	 */
	ICFSecProtSecSysRoleObj getOrigAsSecSysRole();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecProtSecSysRoleObj create();

	/*
	 *	Update the instance.
	 */
	ICFSecProtSecSysRoleEditObj update();

	/*
	 *	Delete the instance.
	 */
	ICFSecProtSecSysRoleEditObj deleteInstance();

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
	 *	Get a list ICFSecProtSecSysRoleEnablesObj instances referenced by the EnabledByRole key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSysRoleEnablesObj instances referenced by the EnabledByRole key.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> getOptionalComponentsEnabledByRole();

	/**
	 *	Get a list ICFSecProtSecSysRoleMembObj instances referenced by the MembByRole key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtSecSysRoleMembObj instances referenced by the MembByRole key.
	 */
	List<ICFSecProtSecSysRoleMembObj> getOptionalChildrenMembByRole();

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecSysRoleId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecSysRoleId.
	 */
	ICFLibKeyHash256 getRequiredSecSysRoleId();

	/**
	 *	Set the required ICFLibKeyHash256 attribute SecSysRoleId.
	 *
	 *	@param value The required ICFLibKeyHash256 attribute SecSysRoleId value to be applied.
	 */
	void setRequiredSecSysRoleId(ICFLibKeyHash256 value);

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

	public void copyRecToOrig();
	public void copyOrigToRec();

}
