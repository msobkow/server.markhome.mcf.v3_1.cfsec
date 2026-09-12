// Description: Java 25 Public Security Service Interface.

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

package server.markhome.mcf.v3_1.cfsec.cfsecpub;

import server.markhome.mcf.v3_1.cflib.keyhash.*;

/*
 *	An ICFSecPubSecurityService is the public service interface used by
 *	normal applications to query the security server or a security server cache.
 */
public interface ICFSecPubSecurityService {

	/**
	 *	Probe the SecRole*, SecTent* security tables, then the SecClus* security tables, and finally the SecSys* tables
	 *	in order until a probe authorizes the users access to the permission role or group. There is no need to invoke
	 *	isMemberOfClusterGroup() or isMemberOfSystemGroup() if you invoke this method.
	 *
	 *	@param userLogin The login id from the SecUser object for the user this query is for.
	 *	@param clusterId The cluster id of the cluster that contains the tenant being queried.
	 *	@param tenantId The tenant id being queried.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the tenant role or group, the equivalent cluster admin role or group, or the equivalent system admin role or group, otherwise false.
	 */
	public boolean isMemberOfTenantGroup(String userLogin, CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, String permissionName);

	/**
	 *	Probe the SecRole*, SecTent* security tables, then the SecClus* security tables, and finally the SecSys* tables
	 *	in order until a probe authorizes the users access to the permission role or group. There is no need to invoke
	 *	isMemberOfClusterGroup() or isMemberOfSystemGroup() if you invoke this method.
	 *
	 *	@param userId The user id for the SecUser object this query is for.
	 *	@param clusterId The cluster id of the cluster that contains the tenant being queried.
	 *	@param tenantId The tenant id being queried.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the tenant role or group, the equivalent cluster admin role or group, or the equivalent system admin role or group, otherwise false.
	 */
	public boolean isMemberOfTenantGroup(CFLibDbKeyHash256 userId, CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, String permissionName);

	/**
	 *	Probe the SecRole*, SecClus* security tables, and then the SecSys* tables until a probe authorizes the users access
	 *	to the permission role or group as a cluster or system admin. There is no need to invoke isMemberOfSystemGroup()
	 *	if you invoke this method.
	 *
	 *	@param userLogin The login id from the SecUser object for the user this query is for.
	 *	@param clusterId The cluster id being queried.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the cluster role or group or the equivalent system admin role or group, otherwise false.
	 */
	public boolean isMemberOfClusterGroup(String userLogin, CFLibDbKeyHash256 clusterId, String permissionName);

	/**
	 *	Probe the SecRole*, SecClus* security tables, and then the SecSys* tables until a probe authorizes the users access
	 *	to the permission role or group as a cluster or system admin. There is no need to invoke isMemberOfSystemGroup()
	 *	if you invoke this method.
	 *
	 *	@param userId The user id for the SecUser object this query is for.
	 *	@param clusterId The cluster id being queried.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the cluster role or group or the equivalent system admin role or group, otherwise false.
	 */
	public boolean isMemberOfClusterGroup(CFLibDbKeyHash256 userId, CFLibDbKeyHash256 clusterId, String permissionName);

	/**
	 *	Probe the SecRole*, SecSys* tables for authorization of the users access to the permission role or group as a system admin.
	 *
	 *	@param userLogin The login id from the SecUser object for the user this query is for
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the specified system role or group, otherwise false.
	 */
	public boolean isMemberOfSystemGroup(String userLogin, String permissionName);

	/**
	 *	Probe the SecRole*, SecSys* tables for authorization of the users access to the permission role or group as a system admin.
	 *
	 *	@param userId The user id for the SecUser object this query is for.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the specified system role or group, otherwise false.
	 */
	public boolean isMemberOfSystemGroup(CFLibDbKeyHash256 userId, String permissionName);
}
