// Description: Java 25 Public Instance Edit Object interface for CFSec Cluster.

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

package server.markhome.mcf.v3_1.cfsec.cfsecpubobj;

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

public interface ICFSecPubClusterEditObj
	extends ICFSecPubClusterObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecPubClusterObj.
	 */
	ICFSecPubClusterObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecPubClusterObj.
	 */
	ICFSecPubClusterObj getOrigAsCluster();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecPubClusterObj create();

	/*
	 *	Update the instance.
	 */
	ICFSecPubClusterEditObj update();

	/*
	 *	Delete the instance.
	 */
	ICFSecPubClusterEditObj deleteInstance();

	/**
	 *	Set the user who created this instance.
	 *
	 *	@param	value	The ICFSecPubSecUserObj instance who created this instance.
	 */
	void setCreatedBy( ICFSecPubSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was created.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setCreatedAt( LocalDateTime value );

	/**
	 *	Set the user who updated this instance.
	 *
	 *	@param	value	The ICFSecPubSecUserObj instance who updated this instance.
	 */
	void setUpdatedBy( ICFSecPubSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was updated.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setUpdatedAt( LocalDateTime value );

	/**
	 *	Get a list ICFSecPubTenantObj instances referenced by the Tenant key.
	 *
	 *	@return	The (potentially empty) list of ICFSecPubTenantObj instances referenced by the Tenant key.
	 */
	List<ICFSecPubTenantObj> getOptionalComponentsTenant();

	/**
	 *	Get a list ICFSecPubSysClusterObj instances referenced by the SysCluster key.
	 *
	 *	@return	The (potentially empty) list of ICFSecPubSysClusterObj instances referenced by the SysCluster key.
	 */
	List<ICFSecPubSysClusterObj> getOptionalComponentsSysCluster();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute Id.
	 */
	CFLibDbKeyHash256 getRequiredId();

	/**
	 *	Set the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@param value The required CFLibDbKeyHash256 attribute Id value to be applied.
	 */
	void setRequiredId(CFLibDbKeyHash256 value);

	/**
	 *	Get the required String attribute FullDomName.
	 *
	 *	@return	The required String attribute FullDomName.
	 */
	String getRequiredFullDomName();

	/**
	 *	Set the required String attribute FullDomName.
	 *
	 *	@param value The required String attribute FullDomName value to be applied.
	 */
	void setRequiredFullDomName(String value);

	/**
	 *	Get the required String attribute Description.
	 *
	 *	@return	The required String attribute Description.
	 */
	String getRequiredDescription();

	/**
	 *	Set the required String attribute Description.
	 *
	 *	@param value The required String attribute Description value to be applied.
	 */
	void setRequiredDescription(String value);

	public void copyRecToOrig();
	public void copyOrigToRec();

}
