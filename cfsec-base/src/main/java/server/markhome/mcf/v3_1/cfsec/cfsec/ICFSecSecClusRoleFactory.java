
// Description: Java JPA Factory interface for SecClusRole.

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
 *	ICFSecSecClusRoleFactory interface for SecClusRole
 */
public interface ICFSecSecClusRoleFactory extends ICFSecProtSecClusRoleFactory
{

	/**
	 *	Allocate a primary history key for SecClusRole instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecClusRoleHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecClusRole instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecClusRoleHPKey asProtected(ICFSecSecClusRoleHPKey src);

	/**
	 *	Allocate a ClusterIdx key over SecClusRole instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusRoleByClusterIdxKey newByClusterIdxKey();

	/**
	 *	Allocate a protected ClusterIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusRoleByClusterIdxKey asProtected(ICFSecSecClusRoleByClusterIdxKey src);

	/**
	 *	Allocate a NameIdx key over SecClusRole instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusRoleByNameIdxKey newByNameIdxKey();

	/**
	 *	Allocate a protected NameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusRoleByNameIdxKey asProtected(ICFSecSecClusRoleByNameIdxKey src);

	/**
	 *	Allocate a UNameIdx key over SecClusRole instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusRoleByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusRoleByUNameIdxKey asProtected(ICFSecSecClusRoleByUNameIdxKey src);

	/**
	 *	Allocate a SecClusRole interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusRole newRec();

	/**
	 *	Allocate a protected SecClusRole interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusRole asProtected(ICFSecSecClusRole src);

	/**
	 *	Allocate a SecClusRole history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusRoleH newHRec();

	/**
	 *	Allocate a protected SecClusRole history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusRoleH asProtected(ICFSecSecClusRoleH src);

}
