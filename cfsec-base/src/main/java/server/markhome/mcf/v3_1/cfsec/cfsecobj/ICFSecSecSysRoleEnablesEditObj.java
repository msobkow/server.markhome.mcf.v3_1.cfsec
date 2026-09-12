// Description: Java 25 Instance Edit Object interface for CFSec SecSysRoleEnables.

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
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

public interface ICFSecSecSysRoleEnablesEditObj
	extends ICFSecSecSysRoleEnablesObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecSecSysRoleEnablesObj.
	 */
	ICFSecSecSysRoleEnablesObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecSecSysRoleEnablesObj.
	 */
	ICFSecSecSysRoleEnablesObj getOrigAsSecSysRoleEnables();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecSecSysRoleEnablesObj create();

	/*
	 *	Update the instance.
	 */
	CFSecSecSysRoleEnablesEditObj update();

	/*
	 *	Delete the instance.
	 */
	CFSecSecSysRoleEnablesEditObj deleteInstance();

	/**
	 *	Set the user who created this instance.
	 *
	 *	@param	value	The ICFSecSecUserObj instance who created this instance.
	 */
	void setCreatedBy( ICFSecSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was created.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setCreatedAt( LocalDateTime value );

	/**
	 *	Set the user who updated this instance.
	 *
	 *	@param	value	The ICFSecSecUserObj instance who updated this instance.
	 */
	void setUpdatedBy( ICFSecSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was updated.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setUpdatedAt( LocalDateTime value );

	/**
	 *	Get the ICFSecSecSysRoleObj instance referenced by the SysRole key.
	 *
	 *	@return	The ICFSecSecSysRoleObj instance referenced by the SysRole key.
	 */
	ICFSecSecSysRoleObj getRequiredContainerSysRole();

	/**
	 *	Get the required ICFSecSecSysRoleObj instance referenced by the SysRole key.
	 *
	 *	@return	The required ICFSecSecSysRoleObj instance referenced by the SysRole key.
	 */
	ICFSecSecSysRoleObj getRequiredContainerSysRole( boolean forceRead );

	/**
	 *	Set the ICFSecSecSysRoleObj instance referenced by the SysRole key.
	 *
	 *	@param	value	the ICFSecSecSysRoleObj instance to be referenced by the SysRole key.
	 */
	void setRequiredContainerSysRole( ICFSecSecSysRoleObj value );

	/**
	 *	Get the ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 *
	 *	@return	The ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 */
	ICFSecSecSysGrpObj getRequiredParentEnableGroup();

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 */
	ICFSecSecSysGrpObj getRequiredParentEnableGroup( boolean forceRead );

	/**
	 *	Set the ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 *
	 *	@param	value	the ICFSecSecSysGrpObj instance to be referenced by the EnableGroup key.
	 */
	void setRequiredParentEnableGroup( ICFSecSecSysGrpObj value );

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecSysRoleId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecSysRoleId.
	 */
	ICFLibKeyHash256 getRequiredSecSysRoleId();

	/**
	 *	Get the required String attribute EnableName.
	 *
	 *	@return	The required String attribute EnableName.
	 */
	String getRequiredEnableName();

	public void copyRecToOrig();
	public void copyOrigToRec();

}
