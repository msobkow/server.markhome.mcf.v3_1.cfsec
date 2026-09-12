// Description: Java 25 CFSec Security Cache Base Implementation

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

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.ICFSecPubSecurityService;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.ICFSecProtSecurityService;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.ICFSecProtSecurityControl;

/*
 *	The CFSecSecurityCache is the base implementation of a security cache which is to be specialized
 *	by the JPA implementation to access the security tables directly through the JPA layer rather than going
 *	through the security layer (it wouldn't make sense for the cache to end up querying itself as to whether
 *	it is allowed to update itself.)
 *
 *	All other security cache implementations end up communicating with the JPA implementation in the end; the
 *	security web server and services with their security database. In practice, most will be running a
 *	SecurityCacheXxxClient where Xxx is the protocol name being used to communicate with a remote server (usually
 *	a web socket json messaging layer once coded.)
 *
 *	Only the security layer implements security caches; all other projects use them as-is from the layer jars
 *	of that project.
 */
abstract public class CFSecSecurityCache implements ICFSecSecurityControl, ICFSecSecurityService
{
	private ConcurrentHashMap<CFLibDbKeyHash256,UserEntry> userById = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String,UserEntry> userByLogin = new ConcurrentHashMap<>();

	/**
	 *	Construct a security cache instance.
	 *	There should only be one security cache instance in a service process, registered with the
	 *	ICFSecSchema static methods accordingly so that all the code in process can find it.
	 */
	public CFSecSecurityCache() {
	}

	/***** Backend methods to be implemented by concrete subclass */

	/**
	 *	Map the userLogin string to a userId DbKey.
	 *
	 *	@param userLogin 
	 *	@return null if the userLogin does not exist, is null, is empty, or is blank. Otherwise the DbKey for the user.
	 */
	public abstract CFLibDbKeyHash256 mapUserLoginToUserId(String userLogin);

	/**
	 *	Map the userId DbKey to the userLogin string.
	 *
	 *	@param userId
	 *	@return null if the userId does not exist or is null. Otherwise the userLogin for the user.
	 */
	public abstract String mapUserIdToUserLogin(CFLibDbKeyHash256 userId);

	/**
	 *	Probe the back-end SecRole*, SecTent* security tables, then the SecClus* security tables, and finally the SecSys* tables
	 *	in order until a probe authorizes the users access to the permission role or group.  The probed value is then "acquired" by
	 *	the cache and kept until it expires or is forgotten by the cache.
	 *
	 *	@param userId The user id for the SecUser object this query is for.
	 *	@param clusterId The cluster id of the cluster that contains the tenant being queried.
	 *	@param tenantId The tenant id being queried.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the tenant role or group, the equivalent cluster admin role or group, or the equivalent system admin role or group, otherwise false.
	 */
	public abstract boolean probeMemberOfTenantGroup(CFLibDbKeyHash256 userId, CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, String permissionName);

	/**
	 *	Probe the back-end SecRole*, SecClus* security tables, and finally the SecSys* tables in order until a probe authorizes
	 *	the users access to the permission role or group.  The probed value is then "acquired" by the cache and kept until it
	 *	expires or is forgotten by the cache.
	 *
	 *	@param userId The user id for the SecUser object this query is for.
	 *	@param clusterId The cluster id of the cluster that contains the tenant being queried.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the tenant role or group, the equivalent cluster admin role or group, or the equivalent system admin role or group, otherwise false.
	 */
	public abstract boolean probeMemberOfClusterGroup(CFLibDbKeyHash256 userId, CFLibDbKeyHash256 clusterId, String permissionName);

	/**
	 *	Probe the back-end SecRole*, and SecSys* tables until a probe authorizes the users access to the permission role or group.
	 *	The probed value is then "acquired" by the cache and kept until it expires or is forgotten by the cache.
	 *
	 *	@param userId The user id for the SecUser object this query is for.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the tenant role or group, the equivalent cluster admin role or group, or the equivalent system admin role or group, otherwise false.
	 */
	public abstract boolean probeMemberOfSystemGroup(CFLibDbKeyHash256 userId, String permissionName);

	/***** ICFSecSecurityService implementation */

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
	@Override
	public boolean isMemberOfTenantGroup(String userLogin, CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, String permissionName) {
		UserEntry entry = resolveUserEntry(userLogin);
		if( entry == null ) {
			return( false );
		}
		return( entry.isMemberOfTenantGroup(clusterId, tenantId, permissionName) );
	}

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
	@Override
	public boolean isMemberOfTenantGroup(CFLibDbKeyHash256 userId, CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, String permissionName) {
		UserEntry entry = resolveUserEntry(userId);
		if( entry == null ) {
			return( false );
		}
		return( entry.isMemberOfTenantGroup(clusterId, tenantId, permissionName) );
	}

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
	@Override
	public boolean isMemberOfClusterGroup(String userLogin, CFLibDbKeyHash256 clusterId, String permissionName) {
		UserEntry entry = resolveUserEntry(userLogin);
		if( entry == null ) {
			return( false );
		}
		return( entry.isMemberOfClusterGroup(clusterId, permissionName) );
	}

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
	@Override
	public boolean isMemberOfClusterGroup(CFLibDbKeyHash256 userId, CFLibDbKeyHash256 clusterId, String permissionName) {
		UserEntry entry = resolveUserEntry(userId);
		if( entry == null ) {
			return( false );
		}
		return( entry.isMemberOfClusterGroup(clusterId, permissionName) );
	}

	/**
	 *	Probe the SecRole*, SecSys* tables for authorization of the users access to the permission role or group as a system admin.
	 *
	 *	@param userLogin The login id from the SecUser object for the user this query is for
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the specified system role or group, otherwise false.
	 */
	@Override
	public boolean isMemberOfSystemGroup(String userLogin, String permissionName) {
		UserEntry entry = resolveUserEntry(userLogin);
		if( entry == null ) {
			return( false );
		}
		return( entry.isMemberOfSystemGroup(permissionName) );
	}

	/**
	 *	Probe the SecRole*, SecSys* tables for authorization of the users access to the permission role or group as a system admin.
	 *
	 *	@param userId The user id for the SecUser object this query is for.
	 *	@param permissionName The name of the permission role or group being queried.
	 *
	 *	@return true if the user is a member of the specified system role or group, otherwise false.
	 */
	@Override
	public boolean isMemberOfSystemGroup(CFLibDbKeyHash256 userId, String permissionName) {
		UserEntry entry = resolveUserEntry(userId);
		if( entry == null ) {
			return( false );
		}
		return( entry.isMemberOfSystemGroup(permissionName) );
	}

	/**
	 *	Register a SecurityControl implementation as an interested listener to receive propagation of the forget commands filtered by their
	 *	intersection with information in this cache.
	 *
	 *	@param securityControl If you can't control it, you can't register it.
	 *
	 *	@return true if a new interest registration was established, false if the interest had been previously established.
	 */
	@Override
	public boolean registerInterest(ICFSecProtSecurityControl securityControl) {
		throw new CFLibNotImplementedYetException(getClass(), "registerInterest");
	}

	/**
	 *	Remove a SecurityControl implementation from the registered list of listeners.
	 *
	 *	@param securityControl If you can't control it, you can't register it.
	 *
	 *	@return true if the security control was removed, false if it was not found.
	 */
	@Override
	public boolean forgetInterest(ICFSecProtSecurityControl securityControl) {
		throw new CFLibNotImplementedYetException(getClass(), "forgetInterest");
	}

	/**
	 *	Did the specified SecurityControl register interest in filtered forget commands?
	 *
	 *	@param securityControl If you can't control it, you can't register it.
	 *
	 *	@return true if the SecurityControl is in the interest registration list, otherwise false.
	 */
	@Override
	public boolean isInterested(ICFSecProtSecurityControl securityControl) {
		throw new CFLibNotImplementedYetException(getClass(), "isInterested");
	}

	/**
	 *	Forget all interest registrations; use with EXTREME CAUTION.
	 */
	@Override
	public void forgetAllInterests() {
		throw new CFLibNotImplementedYetException(getClass(), "forgetAllInterests");
	}

	/***** ICFSecSecurityControl implementation */

	/**
	 *	Forget about the specified lists of objects.  Any null arguments are presumed to imply empty sets.
	 *	Caches keep concurrent hash maps of user login strings and user ids mapped to a common internal
	 *	userinfo object that maintains the caches for the user, one each for the system, the cluster, and
	 *	the tenant security probes. Each such cache is a map by permission name string.  For the system
	 *	cache, this map has Boolean data for the true/false state of the cached permission.  For the cluster
	 *	cache, this map has a sub-map of cluster ids which reference the Booleans.  Similarly, the tenant id
	 *	sub-maps reference Booleans.
	 *
	 *	The userinfo objects also track the last time the user's security information was queried, allowing for
	 *	a pruning scan to automatically expire stale, unused data should the user wander away from their sessions.
	 *
	 *	There is no "global" cache of default permissions applying to all users or anything like that, just per-user permission
	 *	caching.
	 *
	 *	@param userLogins List of user login id strings to forget about.
	 *	@param userIds List of user ids to forget about.
	 *	@param clusterIds List of cluster ids to forget about.
	 *	@param tenantIds List of tenant ids to forget about.
	 *	@param permissionNames List of permission name strings
	 */
	public void forgetAbout(List<String> userLogins,
		List<CFLibDbKeyHash256> userIds,
		List<CFLibDbKeyHash256> clusterIds,
		List<CFLibDbKeyHash256> tenantIds,
		List<String> permissionNames)
	{
		throw new CFLibNotImplementedYetException(getClass(), "forgetAbout");
	}

	/**
	 *	Forget about everything; reset the controlled cache.  Does not forget interest registrations.
	 */
	public void forgetAll() {
		throw new CFLibNotImplementedYetException(getClass(), "forgetAll");
	}

	/***** Internal Implementation Classes And Methods *****/

	private UserEntry resolveUserEntry(String userLogin) {
		if (userLogin == null || userLogin.isEmpty() || userLogin.isBlank()) {
			return( null );
		}
		UserEntry entry = userByLogin.get(userLogin);
		if (entry == null) {
			CFLibDbKeyHash256 userId = mapUserLoginToUserId(userLogin);
			if (userId != null && !userId.isNull()) {
				entry = new UserEntry();
				entry.userId = userId;
				entry.userLogin = userLogin;
				entry.acquiredMillis = System.currentTimeMillis();
				entry.lastUsedMillis = entry.acquiredMillis;
				userByLogin.putIfAbsent(entry.userLogin, entry);
				entry = userByLogin.get(userLogin);
				userById.putIfAbsent(entry.userId, entry);
			}
		}
		return( entry );
	}

	private UserEntry resolveUserEntry(CFLibDbKeyHash256 userId) {
		if (userId == null || userId.isNull()) {
			return( null );
		}
		UserEntry entry = userById.get(userId);
		if (entry == null) {
			String userLogin = mapUserIdToUserLogin(userId);
			if (userLogin != null && !userLogin.isEmpty()) {
				entry = new UserEntry();
				entry.userId = userId;
				entry.userLogin = userLogin;
				entry.acquiredMillis = System.currentTimeMillis();
				entry.lastUsedMillis = entry.acquiredMillis;
				userByLogin.putIfAbsent(entry.userLogin, entry);
				entry = userByLogin.get(userLogin);
				userById.putIfAbsent(entry.userId, entry);
			}
		}
		return( entry );
	}

	class UserEntry {
		CFLibDbKeyHash256 userId;
		String userLogin;
		long acquiredMillis;
		long lastUsedMillis;
		ConcurrentHashMap<String,SysPermission> sysPerms = new ConcurrentHashMap<>();
		ConcurrentHashMap<String,KeyedPermission> clusPerms = new ConcurrentHashMap<>();
		ConcurrentHashMap<String,KeyedPermission> tentPerms = new ConcurrentHashMap<>();

		boolean isMemberOfTenantGroup(CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, String permissionName) {
			KeyedPermission keyperm = tentPerms.get(permissionName);
			if (keyperm == null) {
				boolean result = probeMemberOfTenantGroup(userId, clusterId, tenantId, permissionName);
				keyperm = new KeyedPermission();
				keyperm.permissionName = permissionName;
				keyperm.acquiredMillis = System.currentTimeMillis();
				keyperm.lastUsedMillis = System.currentTimeMillis();
				tentPerms.putIfAbsent(keyperm.permissionName, keyperm);
				keyperm = tentPerms.get(permissionName);
				Boolean val = keyperm.keyMap.get(tenantId);
				if (val == null) {
					keyperm.keyMap.putIfAbsent(tenantId, result);
					val = keyperm.keyMap.get(tenantId);
				}
				lastUsedMillis = System.currentTimeMillis();
				return (val);
			}
			else {
				Boolean val = keyperm.keyMap.get(tenantId);
				if (val == null) {
					boolean result = probeMemberOfTenantGroup(userId, clusterId, tenantId, permissionName);
					keyperm.keyMap.putIfAbsent(tenantId, result);
					val = keyperm.keyMap.get(tenantId);
				}
				keyperm.lastUsedMillis = System.currentTimeMillis();
				lastUsedMillis = System.currentTimeMillis();
				return (val);
			}
		}

		boolean isMemberOfClusterGroup(CFLibDbKeyHash256 clusterId, String permissionName) {
			KeyedPermission keyperm = clusPerms.get(permissionName);
			if (keyperm == null) {
				boolean result = probeMemberOfClusterGroup(userId, clusterId, permissionName);
				keyperm = new KeyedPermission();
				keyperm.permissionName = permissionName;
				keyperm.acquiredMillis = System.currentTimeMillis();
				keyperm.lastUsedMillis = System.currentTimeMillis();
				clusPerms.putIfAbsent(keyperm.permissionName, keyperm);
				keyperm = clusPerms.get(permissionName);
				Boolean val = keyperm.keyMap.get(clusterId);
				if (val == null) {
					keyperm.keyMap.putIfAbsent(clusterId, result);
					val = keyperm.keyMap.get(clusterId);
				}
				lastUsedMillis = System.currentTimeMillis();
				return (val);
			}
			else {
				Boolean val = keyperm.keyMap.get(clusterId);
				if (val == null) {
					boolean result = probeMemberOfClusterGroup(userId, clusterId, permissionName);
					keyperm.keyMap.putIfAbsent(clusterId, result);
					val = keyperm.keyMap.get(clusterId);
				}
				keyperm.lastUsedMillis = System.currentTimeMillis();
				lastUsedMillis = System.currentTimeMillis();
				return (val);
			}
		}

		boolean isMemberOfSystemGroup(String permissionName) {
			SysPermission sysperm = sysPerms.get(permissionName);
			if (sysperm == null) {
				boolean result = probeMemberOfSystemGroup(userId, permissionName);
				sysperm = new SysPermission();
				sysperm.permissionName = permissionName;
				sysperm.acquiredMillis = System.currentTimeMillis();
				sysperm.lastUsedMillis = sysperm.acquiredMillis;
				sysperm.granted = result;
				sysPerms.putIfAbsent(sysperm.permissionName, sysperm);
				sysperm = sysPerms.get(permissionName);
			}
			else {
				sysperm.lastUsedMillis = System.currentTimeMillis();
			}
			lastUsedMillis = System.currentTimeMillis();
			return( sysperm.granted );
		}
	}

	class PermissionBase {
		String permissionName; // key
		long acquiredMillis;
		long lastUsedMillis;
	}

	class SysPermission extends PermissionBase {
		boolean granted;
	}

	class KeyedPermission extends PermissionBase {
		ConcurrentHashMap<CFLibDbKeyHash256,Boolean> keyMap = new ConcurrentHashMap<>();
	}
}
