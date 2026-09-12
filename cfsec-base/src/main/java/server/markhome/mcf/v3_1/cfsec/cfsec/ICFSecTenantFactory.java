
// Description: Java JPA Factory interface for Tenant.

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

package server.markhome.mcf.v3_1.cfsec.cfsec;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;

import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/*
 *	ICFSecTenantFactory interface for Tenant
 */
public interface ICFSecTenantFactory extends ICFSecProtTenantFactory
{

	/**
	 *	Allocate a primary history key for Tenant instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecTenantHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for Tenant instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtTenantHPKey asProtected(ICFSecTenantHPKey src);

	/**
	 *	Allocate a public primary history key for Tenant instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubTenantHPKey asPublic(ICFSecTenantHPKey src);

	/**
	 *	Allocate a ClusterIdx key over Tenant instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTenantByClusterIdxKey newByClusterIdxKey();

	/**
	 *	Allocate a protected ClusterIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTenantByClusterIdxKey asProtected(ICFSecTenantByClusterIdxKey src);

	/**
	 *	Allocate a public ClusterIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTenantByClusterIdxKey asPublic(ICFSecTenantByClusterIdxKey src);

	/**
	 *	Allocate a UNameIdx key over Tenant instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTenantByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTenantByUNameIdxKey asProtected(ICFSecTenantByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTenantByUNameIdxKey asPublic(ICFSecTenantByUNameIdxKey src);

	/**
	 *	Allocate a Tenant interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTenant newRec();

	/**
	 *	Allocate a protected Tenant interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTenant asProtected(ICFSecTenant src);

	/**
	 *	Allocate a public Tenant interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTenant asPublic(ICFSecTenant src);

	/**
	 *	Allocate a Tenant history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTenantH newHRec();

	/**
	 *	Allocate a protected Tenant history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTenantH asProtected(ICFSecTenantH src);

	/**
	 *	Allocate a public Tenant history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTenantH asPublic(ICFSecTenantH src);

}
