
// Description: Java JPA Factory interface for SecSession.

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
 *	ICFSecSecSessionFactory interface for SecSession
 */
public interface ICFSecSecSessionFactory extends ICFSecProtSecSessionFactory
{

	/**
	 *	Allocate a primary history key for SecSession instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecSessionHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecSession instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecSessionHPKey asProtected(ICFSecSecSessionHPKey src);

	/**
	 *	Allocate a public primary history key for SecSession instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubSecSessionHPKey asPublic(ICFSecSecSessionHPKey src);

	/**
	 *	Allocate a SecUserIdx key over SecSession instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSessionBySecUserIdxKey newBySecUserIdxKey();

	/**
	 *	Allocate a protected SecUserIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSessionBySecUserIdxKey asProtected(ICFSecSecSessionBySecUserIdxKey src);

	/**
	 *	Allocate a public SecUserIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSessionBySecUserIdxKey asPublic(ICFSecSecSessionBySecUserIdxKey src);

	/**
	 *	Allocate a StartIdx key over SecSession instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSessionByStartIdxKey newByStartIdxKey();

	/**
	 *	Allocate a protected StartIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSessionByStartIdxKey asProtected(ICFSecSecSessionByStartIdxKey src);

	/**
	 *	Allocate a public StartIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSessionByStartIdxKey asPublic(ICFSecSecSessionByStartIdxKey src);

	/**
	 *	Allocate a FinishIdx key over SecSession instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSessionByFinishIdxKey newByFinishIdxKey();

	/**
	 *	Allocate a protected FinishIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSessionByFinishIdxKey asProtected(ICFSecSecSessionByFinishIdxKey src);

	/**
	 *	Allocate a public FinishIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSessionByFinishIdxKey asPublic(ICFSecSecSessionByFinishIdxKey src);

	/**
	 *	Allocate a SecProxyIdx key over SecSession instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSessionBySecProxyIdxKey newBySecProxyIdxKey();

	/**
	 *	Allocate a protected SecProxyIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSessionBySecProxyIdxKey asProtected(ICFSecSecSessionBySecProxyIdxKey src);

	/**
	 *	Allocate a SecSession interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSession newRec();

	/**
	 *	Allocate a protected SecSession interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSession asProtected(ICFSecSecSession src);

	/**
	 *	Allocate a public SecSession interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSession asPublic(ICFSecSecSession src);

	/**
	 *	Allocate a SecSession history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSessionH newHRec();

	/**
	 *	Allocate a protected SecSession history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSessionH asProtected(ICFSecSecSessionH src);

	/**
	 *	Allocate a public SecSession history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSessionH asPublic(ICFSecSecSessionH src);

}
