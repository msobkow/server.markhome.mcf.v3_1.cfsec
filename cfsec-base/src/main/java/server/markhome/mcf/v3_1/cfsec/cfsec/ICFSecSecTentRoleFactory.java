
// Description: Java JPA Factory interface for SecTentRole.

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
 *	ICFSecSecTentRoleFactory interface for SecTentRole
 */
public interface ICFSecSecTentRoleFactory extends ICFSecProtSecTentRoleFactory
{

	/**
	 *	Allocate a primary history key for SecTentRole instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecTentRoleHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecTentRole instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecTentRoleHPKey asProtected(ICFSecSecTentRoleHPKey src);

	/**
	 *	Allocate a TenantIdx key over SecTentRole instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentRoleByTenantIdxKey newByTenantIdxKey();

	/**
	 *	Allocate a protected TenantIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentRoleByTenantIdxKey asProtected(ICFSecSecTentRoleByTenantIdxKey src);

	/**
	 *	Allocate a NameIdx key over SecTentRole instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentRoleByNameIdxKey newByNameIdxKey();

	/**
	 *	Allocate a protected NameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentRoleByNameIdxKey asProtected(ICFSecSecTentRoleByNameIdxKey src);

	/**
	 *	Allocate a UNameIdx key over SecTentRole instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentRoleByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentRoleByUNameIdxKey asProtected(ICFSecSecTentRoleByUNameIdxKey src);

	/**
	 *	Allocate a SecTentRole interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentRole newRec();

	/**
	 *	Allocate a protected SecTentRole interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentRole asProtected(ICFSecSecTentRole src);

	/**
	 *	Allocate a SecTentRole history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentRoleH newHRec();

	/**
	 *	Allocate a protected SecTentRole history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentRoleH asProtected(ICFSecSecTentRoleH src);

}
