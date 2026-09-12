// Description: Java 25 Instance Edit Object interface for CFSec SecTentGrp.

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

public interface ICFSecSecTentGrpEditObj
	extends ICFSecSecTentGrpObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecSecTentGrpObj.
	 */
	ICFSecSecTentGrpObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecSecTentGrpObj.
	 */
	ICFSecSecTentGrpObj getOrigAsSecTentGrp();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecSecTentGrpObj create();

	/*
	 *	Update the instance.
	 */
	CFSecSecTentGrpEditObj update();

	/*
	 *	Delete the instance.
	 */
	CFSecSecTentGrpEditObj deleteInstance();

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
	 *	Get the ICFSecTenantObj instance referenced by the Tenant key.
	 *
	 *	@return	The ICFSecTenantObj instance referenced by the Tenant key.
	 */
	ICFSecTenantObj getRequiredOwnerTenant();

	/**
	 *	Get the required ICFSecTenantObj instance referenced by the Tenant key.
	 *
	 *	@return	The required ICFSecTenantObj instance referenced by the Tenant key.
	 */
	ICFSecTenantObj getRequiredOwnerTenant( boolean forceRead );

	/**
	 *	Set the ICFSecTenantObj instance referenced by the Tenant key.
	 *
	 *	@param	value	the ICFSecTenantObj instance to be referenced by the Tenant key.
	 */
	void setRequiredOwnerTenant( ICFSecTenantObj value );

	/**
	 *	Get the ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 *
	 *	@return	The ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 */
	ICFSecSecSysGrpObj getRequiredContainerSysGrp();

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 */
	ICFSecSecSysGrpObj getRequiredContainerSysGrp( boolean forceRead );

	/**
	 *	Set the ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 *
	 *	@param	value	the ICFSecSecSysGrpObj instance to be referenced by the SysGrp key.
	 */
	void setRequiredContainerSysGrp( ICFSecSecSysGrpObj value );

	/**
	 *	Get a list ICFSecSecTentGrpMembObj instances referenced by the MembByGrp key.
	 *
	 *	@return	The (potentially empty) list of ICFSecSecTentGrpMembObj instances referenced by the MembByGrp key.
	 */
	List<ICFSecSecTentGrpMembObj> getOptionalChildrenMembByGrp();

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecTentGrpId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecTentGrpId.
	 */
	ICFLibKeyHash256 getRequiredSecTentGrpId();

	/**
	 *	Set the required ICFLibKeyHash256 attribute SecTentGrpId.
	 *
	 *	@param value The required ICFLibKeyHash256 attribute SecTentGrpId value to be applied.
	 */
	void setRequiredSecTentGrpId(ICFLibKeyHash256 value);

	/**
	 *	Get the required ICFLibKeyHash256 attribute TenantId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute TenantId.
	 */
	ICFLibKeyHash256 getRequiredTenantId();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	public void copyRecToOrig();
	public void copyOrigToRec();

}
