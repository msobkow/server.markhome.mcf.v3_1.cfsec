// Description: Java 25 CFSec Protected Authorization Implementation

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

/*
 *	A CFSecProtAuthorization is a protected authorization ticket
 *	for the system providing services.  Most modern authorizations
 *	are based on OAuth2 tickets, but I haven't gotten there yet.
 */
public class CFSecProtAuthorization extends CFSecPubAuthorization implements ICFSecProtAuthorization, Serializable
{

	public CFSecProtAuthorization() {
		super();
	}

	public CFSecProtAuthorization(ICFLibUuid6 authUuid6, CFLibDbKeyHash256 secSessionId, CFLibDbKeyHash256 secUserId, CFLibDbKeyHash256 secClusterId, CFLibDbKeyHash256 secTenantId) {
		super(authUuid6, secSessionId, secUserId, secClusterId, secTenantId);
	}

	public CFSecProtAuthorization(ICFSecProtAuthorization src) {
		super(src);
	}

	public CFSecProtAuthorization(ICFSecPubAuthorization src) {
		super(src);
	}

}
