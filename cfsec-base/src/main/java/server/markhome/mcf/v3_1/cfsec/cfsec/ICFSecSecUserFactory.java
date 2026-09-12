
// Description: Java JPA Factory interface for SecUser.

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
 *	ICFSecSecUserFactory interface for SecUser
 */
public interface ICFSecSecUserFactory extends ICFSecProtSecUserFactory
{

	/**
	 *	Allocate a primary history key for SecUser instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecUserHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecUser instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecUserHPKey asProtected(ICFSecSecUserHPKey src);

	/**
	 *	Allocate a public primary history key for SecUser instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubSecUserHPKey asPublic(ICFSecSecUserHPKey src);

	/**
	 *	Allocate a ULoginIdx key over SecUser instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserByULoginIdxKey newByULoginIdxKey();

	/**
	 *	Allocate a protected ULoginIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserByULoginIdxKey asProtected(ICFSecSecUserByULoginIdxKey src);

	/**
	 *	Allocate a public ULoginIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecUserByULoginIdxKey asPublic(ICFSecSecUserByULoginIdxKey src);

	/**
	 *	Allocate a EMAddrIdx key over SecUser instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserByEMAddrIdxKey newByEMAddrIdxKey();

	/**
	 *	Allocate a protected EMAddrIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserByEMAddrIdxKey asProtected(ICFSecSecUserByEMAddrIdxKey src);

	/**
	 *	Allocate a SecUser interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUser newRec();

	/**
	 *	Allocate a protected SecUser interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUser asProtected(ICFSecSecUser src);

	/**
	 *	Allocate a public SecUser interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecUser asPublic(ICFSecSecUser src);

	/**
	 *	Allocate a SecUser history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserH newHRec();

	/**
	 *	Allocate a protected SecUser history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserH asProtected(ICFSecSecUserH src);

	/**
	 *	Allocate a public SecUser history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecUserH asPublic(ICFSecSecUserH src);

}
