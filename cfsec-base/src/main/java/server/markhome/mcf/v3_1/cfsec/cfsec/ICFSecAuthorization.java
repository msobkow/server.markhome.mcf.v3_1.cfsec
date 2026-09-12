// Description: Java 25 Private Authorization Interface.

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
import server.markhome.mcf.v3_1.cfsec.cfsecprot.ICFSecProtAuthorization;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.ICFSecClusterObj;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.ICFSecTenantObj;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.ICFSecSecSessionObj;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.ICFSecSecUserObj;


/*
 *	An ICFSecAuthorization is an authorization ticket
 *	for the system providing services.
 */
public interface ICFSecAuthorization extends ICFSecProtAuthorization
{
	public void setAuthUuid6( ICFLibUuid6 value );

	public void setSecClusterId( CFLibDbKeyHash256 clusterId );
	public void setSecCluster(ICFSecClusterObj cluster);
	public void setSecTenantId( CFLibDbKeyHash256 tenantId );
	public void setSecTenant(ICFSecTenantObj tenant);
	public void setSecSessionId( CFLibDbKeyHash256 sessionId );
	public void setSecSession(ICFSecSecSessionObj session);
	public void setSecUserId( CFLibDbKeyHash256 userId );
	public void setSecUser(ICFSecSecUserObj user);
}
