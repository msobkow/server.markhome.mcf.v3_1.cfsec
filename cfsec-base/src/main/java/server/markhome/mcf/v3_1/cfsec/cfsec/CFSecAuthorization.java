// Description: Java 25 CFSec Private Authorization Implementation

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
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.ICFSecPubAuthorization;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.CFSecPubAuthorization;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.ICFSecProtAuthorization;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.CFSecProtAuthorization;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.ICFSecClusterObj;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.ICFSecTenantObj;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.ICFSecSecSessionObj;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.ICFSecSecUserObj;

/*
 *	A CFSecAuthorization is a private authorization ticket
 *	for the system providing services.  Most modern authorizations
 *	are based on OAuth2 tickets, but I haven't gotten there yet.
 */
public class CFSecAuthorization extends CFSecProtAuthorization implements ICFSecAuthorization, Serializable
{
	public CFSecAuthorization() {
		super();
	}

	public CFSecAuthorization(ICFLibUuid6 authUuid6, CFLibDbKeyHash256 secSessionId, CFLibDbKeyHash256 secUserId, CFLibDbKeyHash256 secClusterId, CFLibDbKeyHash256 secTenantId) {
		super(authUuid6, secSessionId, secUserId, secClusterId, secTenantId);
	}

	public CFSecAuthorization(ICFSecAuthorization src) {
		super(src);
	}

	public CFSecAuthorization(ICFSecProtAuthorization src) {
		super(src);
	}

	public CFSecAuthorization(ICFSecPubAuthorization src) {
		super(src);
	}

	@Override
	public void setAuthUuid6( ICFLibUuid6 value ) {
		this.authUuid6 = new CFLibUuid6(value.getBytes());
		this.authUuid6Str = this.authUuid6.toString();
	}

	@Override
	public void setSecClusterId( CFLibDbKeyHash256 clusterId ) {
		this.secClusterId = new CFLibDbKeyHash256(clusterId);
	}

	@Override
	public void setSecCluster(ICFSecClusterObj cluster) {
		if (cluster == null) {
			this.secClusterId = CFLibDbKeyHash256.nullGet();
		}
		else {
			this.secClusterId = cluster.getRequiredId();
		}
	}

	@Override
	public void setSecTenantId( CFLibDbKeyHash256 tenantId ) {
		this.secTenantId = new CFLibDbKeyHash256(tenantId);
	}

	@Override
	public void setSecTenant(ICFSecTenantObj tenant) {
		if (tenant == null) {
			this.secTenantId = CFLibDbKeyHash256.nullGet();
		}
		else {
			this.secTenantId = new CFLibDbKeyHash256(tenant.getRequiredId());
		}
	}

	@Override
	public void setSecSessionId( CFLibDbKeyHash256 sessionId ) {
		this.secSessionId = new CFLibDbKeyHash256(sessionId);
	}

	@Override
	public void setSecSession(ICFSecSecSessionObj session) {
		if (session == null) {
			this.secSessionId = CFLibDbKeyHash256.nullGet();
		}
		else {
			this.secSessionId = new CFLibDbKeyHash256(session.getRequiredSecSessionId());
		}
	}

	@Override
	public void setSecUserId( CFLibDbKeyHash256 userId ) {
		this.secUserId = new CFLibDbKeyHash256(userId);
	}

	@Override
	public void setSecUser(ICFSecSecUserObj user) {
		if (user == null) {
			this.secUserId = CFLibDbKeyHash256.nullGet();
		}
		else {
			this.secUserId = new CFLibDbKeyHash256(user.getRequiredSecUserId());
		}
	}
}
