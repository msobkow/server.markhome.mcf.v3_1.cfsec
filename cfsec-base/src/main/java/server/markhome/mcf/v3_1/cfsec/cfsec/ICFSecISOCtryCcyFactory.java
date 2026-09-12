
// Description: Java JPA Factory interface for ISOCtryCcy.

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
 *	ICFSecISOCtryCcyFactory interface for ISOCtryCcy
 */
public interface ICFSecISOCtryCcyFactory extends ICFSecProtISOCtryCcyFactory
{

	/**
	 *	Allocate a primary key for ISOCtryCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryCcyPKey newPKey();

	/**
	 *	Allocate a protected key for ISOCtryCcy instances from a private key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcyPKey asProtected(ICFSecISOCtryCcyPKey src);

	/**
	 *	Allocate a public key for ISOCtryCcy instances from a private key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcyPKey asPublic(ICFSecISOCtryCcyPKey src);

	/**
	 *	Allocate a primary history key for ISOCtryCcy instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecISOCtryCcyHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for ISOCtryCcy instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOCtryCcyHPKey asProtected(ICFSecISOCtryCcyHPKey src);

	/**
	 *	Allocate a public primary history key for ISOCtryCcy instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOCtryCcyHPKey asPublic(ICFSecISOCtryCcyHPKey src);

	/**
	 *	Allocate a CtryIdx key over ISOCtryCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryCcyByCtryIdxKey newByCtryIdxKey();

	/**
	 *	Allocate a protected CtryIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcyByCtryIdxKey asProtected(ICFSecISOCtryCcyByCtryIdxKey src);

	/**
	 *	Allocate a public CtryIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcyByCtryIdxKey asPublic(ICFSecISOCtryCcyByCtryIdxKey src);

	/**
	 *	Allocate a CcyIdx key over ISOCtryCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryCcyByCcyIdxKey newByCcyIdxKey();

	/**
	 *	Allocate a protected CcyIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcyByCcyIdxKey asProtected(ICFSecISOCtryCcyByCcyIdxKey src);

	/**
	 *	Allocate a public CcyIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcyByCcyIdxKey asPublic(ICFSecISOCtryCcyByCcyIdxKey src);

	/**
	 *	Allocate a ISOCtryCcy interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryCcy newRec();

	/**
	 *	Allocate a protected ISOCtryCcy interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcy asProtected(ICFSecISOCtryCcy src);

	/**
	 *	Allocate a public ISOCtryCcy interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcy asPublic(ICFSecISOCtryCcy src);

	/**
	 *	Allocate a ISOCtryCcy history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryCcyH newHRec();

	/**
	 *	Allocate a protected ISOCtryCcy history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcyH asProtected(ICFSecISOCtryCcyH src);

	/**
	 *	Allocate a public ISOCtryCcy history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcyH asPublic(ICFSecISOCtryCcyH src);

}
