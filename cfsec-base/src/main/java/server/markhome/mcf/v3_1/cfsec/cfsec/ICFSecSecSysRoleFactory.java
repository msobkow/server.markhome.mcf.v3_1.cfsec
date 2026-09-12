
// Description: Java JPA Factory interface for SecSysRole.

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
 *	ICFSecSecSysRoleFactory interface for SecSysRole
 */
public interface ICFSecSecSysRoleFactory extends ICFSecProtSecSysRoleFactory
{

	/**
	 *	Allocate a primary history key for SecSysRole instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecSysRoleHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecSysRole instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecSysRoleHPKey asProtected(ICFSecSecSysRoleHPKey src);

	/**
	 *	Allocate a public primary history key for SecSysRole instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubSecSysRoleHPKey asPublic(ICFSecSecSysRoleHPKey src);

	/**
	 *	Allocate a UNameIdx key over SecSysRole instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysRoleByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleByUNameIdxKey asProtected(ICFSecSecSysRoleByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysRoleByUNameIdxKey asPublic(ICFSecSecSysRoleByUNameIdxKey src);

	/**
	 *	Allocate a SecSysRole interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysRole newRec();

	/**
	 *	Allocate a protected SecSysRole interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRole asProtected(ICFSecSecSysRole src);

	/**
	 *	Allocate a public SecSysRole interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysRole asPublic(ICFSecSecSysRole src);

	/**
	 *	Allocate a SecSysRole history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysRoleH newHRec();

	/**
	 *	Allocate a protected SecSysRole history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleH asProtected(ICFSecSecSysRoleH src);

	/**
	 *	Allocate a public SecSysRole history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysRoleH asPublic(ICFSecSecSysRoleH src);

}
