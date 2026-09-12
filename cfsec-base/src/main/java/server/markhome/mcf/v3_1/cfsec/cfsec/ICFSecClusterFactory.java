
// Description: Java JPA Factory interface for Cluster.

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
 *	ICFSecClusterFactory interface for Cluster
 */
public interface ICFSecClusterFactory extends ICFSecProtClusterFactory
{

	/**
	 *	Allocate a primary history key for Cluster instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecClusterHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for Cluster instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtClusterHPKey asProtected(ICFSecClusterHPKey src);

	/**
	 *	Allocate a public primary history key for Cluster instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubClusterHPKey asPublic(ICFSecClusterHPKey src);

	/**
	 *	Allocate a UDomNameIdx key over Cluster instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecClusterByUDomNameIdxKey newByUDomNameIdxKey();

	/**
	 *	Allocate a protected UDomNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtClusterByUDomNameIdxKey asProtected(ICFSecClusterByUDomNameIdxKey src);

	/**
	 *	Allocate a public UDomNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubClusterByUDomNameIdxKey asPublic(ICFSecClusterByUDomNameIdxKey src);

	/**
	 *	Allocate a UDescrIdx key over Cluster instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecClusterByUDescrIdxKey newByUDescrIdxKey();

	/**
	 *	Allocate a protected UDescrIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtClusterByUDescrIdxKey asProtected(ICFSecClusterByUDescrIdxKey src);

	/**
	 *	Allocate a public UDescrIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubClusterByUDescrIdxKey asPublic(ICFSecClusterByUDescrIdxKey src);

	/**
	 *	Allocate a Cluster interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecCluster newRec();

	/**
	 *	Allocate a protected Cluster interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtCluster asProtected(ICFSecCluster src);

	/**
	 *	Allocate a public Cluster interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubCluster asPublic(ICFSecCluster src);

	/**
	 *	Allocate a Cluster history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecClusterH newHRec();

	/**
	 *	Allocate a protected Cluster history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtClusterH asProtected(ICFSecClusterH src);

	/**
	 *	Allocate a public Cluster history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubClusterH asPublic(ICFSecClusterH src);

}
