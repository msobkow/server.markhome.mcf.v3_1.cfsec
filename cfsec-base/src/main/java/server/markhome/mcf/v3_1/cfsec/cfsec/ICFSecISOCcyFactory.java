
// Description: Java JPA Factory interface for ISOCcy.

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
 *	ICFSecISOCcyFactory interface for ISOCcy
 */
public interface ICFSecISOCcyFactory extends ICFSecProtISOCcyFactory
{

	/**
	 *	Allocate a primary history key for ISOCcy instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecISOCcyHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for ISOCcy instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOCcyHPKey asProtected(ICFSecISOCcyHPKey src);

	/**
	 *	Allocate a public primary history key for ISOCcy instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOCcyHPKey asPublic(ICFSecISOCcyHPKey src);

	/**
	 *	Allocate a CcyCdIdx key over ISOCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCcyByCcyCdIdxKey newByCcyCdIdxKey();

	/**
	 *	Allocate a protected CcyCdIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCcyByCcyCdIdxKey asProtected(ICFSecISOCcyByCcyCdIdxKey src);

	/**
	 *	Allocate a public CcyCdIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCcyByCcyCdIdxKey asPublic(ICFSecISOCcyByCcyCdIdxKey src);

	/**
	 *	Allocate a CcyNmIdx key over ISOCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCcyByCcyNmIdxKey newByCcyNmIdxKey();

	/**
	 *	Allocate a protected CcyNmIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCcyByCcyNmIdxKey asProtected(ICFSecISOCcyByCcyNmIdxKey src);

	/**
	 *	Allocate a public CcyNmIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCcyByCcyNmIdxKey asPublic(ICFSecISOCcyByCcyNmIdxKey src);

	/**
	 *	Allocate a ISOCcy interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCcy newRec();

	/**
	 *	Allocate a protected ISOCcy interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCcy asProtected(ICFSecISOCcy src);

	/**
	 *	Allocate a public ISOCcy interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCcy asPublic(ICFSecISOCcy src);

	/**
	 *	Allocate a ISOCcy history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCcyH newHRec();

	/**
	 *	Allocate a protected ISOCcy history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCcyH asProtected(ICFSecISOCcyH src);

	/**
	 *	Allocate a public ISOCcy history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCcyH asPublic(ICFSecISOCcyH src);

}
