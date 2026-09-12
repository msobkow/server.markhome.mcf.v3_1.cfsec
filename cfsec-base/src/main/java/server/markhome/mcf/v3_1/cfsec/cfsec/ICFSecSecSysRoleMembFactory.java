
// Description: Java JPA Factory interface for SecSysRoleMemb.

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
 *	ICFSecSecSysRoleMembFactory interface for SecSysRoleMemb
 */
public interface ICFSecSecSysRoleMembFactory extends ICFSecProtSecSysRoleMembFactory
{

	/**
	 *	Allocate a primary key for SecSysRoleMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysRoleMembPKey newPKey();

	/**
	 *	Allocate a protected key for SecSysRoleMemb instances from a private key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleMembPKey asProtected(ICFSecSecSysRoleMembPKey src);

	/**
	 *	Allocate a primary history key for SecSysRoleMemb instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecSysRoleMembHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecSysRoleMemb instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecSysRoleMembHPKey asProtected(ICFSecSecSysRoleMembHPKey src);

	/**
	 *	Allocate a SysRoleIdx key over SecSysRoleMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysRoleMembBySysRoleIdxKey newBySysRoleIdxKey();

	/**
	 *	Allocate a protected SysRoleIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleMembBySysRoleIdxKey asProtected(ICFSecSecSysRoleMembBySysRoleIdxKey src);

	/**
	 *	Allocate a LoginIdx key over SecSysRoleMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysRoleMembByLoginIdxKey newByLoginIdxKey();

	/**
	 *	Allocate a protected LoginIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleMembByLoginIdxKey asProtected(ICFSecSecSysRoleMembByLoginIdxKey src);

	/**
	 *	Allocate a SecSysRoleMemb interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysRoleMemb newRec();

	/**
	 *	Allocate a protected SecSysRoleMemb interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleMemb asProtected(ICFSecSecSysRoleMemb src);

	/**
	 *	Allocate a SecSysRoleMemb history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysRoleMembH newHRec();

	/**
	 *	Allocate a protected SecSysRoleMemb history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysRoleMembH asProtected(ICFSecSecSysRoleMembH src);

}
