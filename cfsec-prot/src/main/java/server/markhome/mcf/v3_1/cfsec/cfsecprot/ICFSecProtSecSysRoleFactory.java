
// Description: Java Protected Factory interface for SecSysRole.

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
 *	ICFSecProtSecSysRoleFactory protected interface for SecSysRole
 */
public interface ICFSecProtSecSysRoleFactory extends ICFSecPubSecSysRoleFactory
{

	/**
	 *	Allocate a protected primary history key for SecSysRole instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecSysRoleHPKey newProtHPKey();

	/**
	 *	Allocate a public primary history key for SecSysRole instances from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubSecSysRoleHPKey asPublic(ICFSecProtSecSysRoleHPKey src);

	/**
	 *	Allocate a protected UNameIdx key over protected SecSysRole instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleByUNameIdxKey newProtByUNameIdxKey();

	/**
	 *	Allocate a public UNameIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysRoleByUNameIdxKey asPublic(ICFSecProtSecSysRoleByUNameIdxKey src);

	/**
	 *	Allocate a protected SecSysRole interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRole newProtRec();

	/**
	 *	Allocate a public SecSysRole interface from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysRole asPublic(ICFSecProtSecSysRole src);

	/**
	 *	Allocate a protected SecSysRole history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleH newProtHRec();

	/**
	 *	Allocate a public SecSysRole history interface implementation from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysRoleH asPublic(ICFSecProtSecSysRoleH src);

}
