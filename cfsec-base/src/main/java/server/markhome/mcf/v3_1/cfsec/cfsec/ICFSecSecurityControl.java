// Description: Java 25 Private Security Control Interface.

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

import java.util.List;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.CFLibDbKeyHash256;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.ICFSecPubSecurityService;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.ICFSecProtSecurityService;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.ICFSecProtSecurityControl;

/*
 *	An ICFSecSecurityControl is the private security service control interface
 *	used by the system to tell registered caches what information to forget about, including
 *	users, permission groups, tenants, clusters, or the entire cache of information. Application logic
 *	should never invoke the control interface for any reason.  It is intended solely for use by the
 *	infrastructure to issue commands to security cache implementations that have registered for change
 *	and control propagation.
 */
public interface ICFSecSecurityControl extends ICFSecProtSecurityControl, ICFSecSecurityService {
}
