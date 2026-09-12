
// Description: Java JPA Factory interface for SysCluster.

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
 *	ICFSecSysClusterFactory interface for SysCluster
 */
public interface ICFSecSysClusterFactory extends ICFSecProtSysClusterFactory
{

	/**
	 *	Allocate a primary history key for SysCluster instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSysClusterHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SysCluster instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSysClusterHPKey asProtected(ICFSecSysClusterHPKey src);

	/**
	 *	Allocate a public primary history key for SysCluster instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubSysClusterHPKey asPublic(ICFSecSysClusterHPKey src);

	/**
	 *	Allocate a ClusterIdx key over SysCluster instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSysClusterByClusterIdxKey newByClusterIdxKey();

	/**
	 *	Allocate a protected ClusterIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSysClusterByClusterIdxKey asProtected(ICFSecSysClusterByClusterIdxKey src);

	/**
	 *	Allocate a public ClusterIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSysClusterByClusterIdxKey asPublic(ICFSecSysClusterByClusterIdxKey src);

	/**
	 *	Allocate a SysCluster interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSysCluster newRec();

	/**
	 *	Allocate a protected SysCluster interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSysCluster asProtected(ICFSecSysCluster src);

	/**
	 *	Allocate a public SysCluster interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSysCluster asPublic(ICFSecSysCluster src);

	/**
	 *	Allocate a SysCluster history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSysClusterH newHRec();

	/**
	 *	Allocate a protected SysCluster history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSysClusterH asProtected(ICFSecSysClusterH src);

	/**
	 *	Allocate a public SysCluster history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSysClusterH asPublic(ICFSecSysClusterH src);

}
