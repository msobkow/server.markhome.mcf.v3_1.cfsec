
// Description: Java JPA Factory interface for SecUserPWReset.

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
 *	ICFSecSecUserPWResetFactory interface for SecUserPWReset
 */
public interface ICFSecSecUserPWResetFactory extends ICFSecProtSecUserPWResetFactory
{

	/**
	 *	Allocate a primary history key for SecUserPWReset instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecUserPWResetHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecUserPWReset instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecUserPWResetHPKey asProtected(ICFSecSecUserPWResetHPKey src);

	/**
	 *	Allocate a UUuid6Idx key over SecUserPWReset instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserPWResetByUUuid6IdxKey newByUUuid6IdxKey();

	/**
	 *	Allocate a protected UUuid6Idx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserPWResetByUUuid6IdxKey asProtected(ICFSecSecUserPWResetByUUuid6IdxKey src);

	/**
	 *	Allocate a SentEMAddrIdx key over SecUserPWReset instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserPWResetBySentEMAddrIdxKey newBySentEMAddrIdxKey();

	/**
	 *	Allocate a protected SentEMAddrIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserPWResetBySentEMAddrIdxKey asProtected(ICFSecSecUserPWResetBySentEMAddrIdxKey src);

	/**
	 *	Allocate a NewAcctIdx key over SecUserPWReset instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserPWResetByNewAcctIdxKey newByNewAcctIdxKey();

	/**
	 *	Allocate a protected NewAcctIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserPWResetByNewAcctIdxKey asProtected(ICFSecSecUserPWResetByNewAcctIdxKey src);

	/**
	 *	Allocate a SecUserPWReset interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserPWReset newRec();

	/**
	 *	Allocate a protected SecUserPWReset interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserPWReset asProtected(ICFSecSecUserPWReset src);

	/**
	 *	Allocate a SecUserPWReset history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserPWResetH newHRec();

	/**
	 *	Allocate a protected SecUserPWReset history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserPWResetH asProtected(ICFSecSecUserPWResetH src);

}
