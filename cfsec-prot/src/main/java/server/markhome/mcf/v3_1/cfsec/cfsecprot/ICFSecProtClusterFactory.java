
// Description: Java Protected Factory interface for Cluster.

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
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/*
 *	ICFSecProtClusterFactory protected interface for Cluster
 */
public interface ICFSecProtClusterFactory extends ICFSecPubClusterFactory
{

	/**
	 *	Allocate a protected primary history key for Cluster instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtClusterHPKey newProtHPKey();

	/**
	 *	Allocate a public primary history key for Cluster instances from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubClusterHPKey asPublic(ICFSecProtClusterHPKey src);

	/**
	 *	Allocate a protected UDomNameIdx key over protected Cluster instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtClusterByUDomNameIdxKey newProtByUDomNameIdxKey();

	/**
	 *	Allocate a public UDomNameIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubClusterByUDomNameIdxKey asPublic(ICFSecProtClusterByUDomNameIdxKey src);

	/**
	 *	Allocate a protected UDescrIdx key over protected Cluster instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtClusterByUDescrIdxKey newProtByUDescrIdxKey();

	/**
	 *	Allocate a public UDescrIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubClusterByUDescrIdxKey asPublic(ICFSecProtClusterByUDescrIdxKey src);

	/**
	 *	Allocate a protected Cluster interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtCluster newProtRec();

	/**
	 *	Allocate a public Cluster interface from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubCluster asPublic(ICFSecProtCluster src);

	/**
	 *	Allocate a protected Cluster history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtClusterH newProtHRec();

	/**
	 *	Allocate a public Cluster history interface implementation from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubClusterH asPublic(ICFSecProtClusterH src);

}
