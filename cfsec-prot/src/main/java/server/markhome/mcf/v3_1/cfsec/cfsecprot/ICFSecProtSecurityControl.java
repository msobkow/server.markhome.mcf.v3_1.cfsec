// Description: Java 25 Protected Security Control Interface.

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

package server.markhome.mcf.v3_1.cfsec.cfsecprot;

import java.util.List;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;

/*
 *	An ICFSecSecurityControl is the protected security service control interface
 *	used by the system to tell registered caches what information to forget about, including
 *	users, permission groups, tenants, clusters, or the entire cache of information. Application logic
 *	should never invoke the control interface for any reason.  It is intended solely for use by the
 *	infrastructure to issue commands to security cache implementations that have registered for change
 *	and control propagation.
 */
public interface ICFSecProtSecurityControl extends ICFSecProtSecurityService {

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
		List<String> permissionNames);

	/**
	 *	Forget about everything; reset the controlled cache.  Does not forget interest registrations.
	 */
	public void forgetAll();

}
