
// Description: Java Protected Factory interface for ISOCcy.

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
 *	ICFSecProtISOCcyFactory protected interface for ISOCcy
 */
public interface ICFSecProtISOCcyFactory extends ICFSecPubISOCcyFactory
{

	/**
	 *	Allocate a protected primary history key for ISOCcy instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOCcyHPKey newProtHPKey();

	/**
	 *	Allocate a public primary history key for ISOCcy instances from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOCcyHPKey asPublic(ICFSecProtISOCcyHPKey src);

	/**
	 *	Allocate a protected CcyCdIdx key over protected ISOCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCcyByCcyCdIdxKey newProtByCcyCdIdxKey();

	/**
	 *	Allocate a public CcyCdIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCcyByCcyCdIdxKey asPublic(ICFSecProtISOCcyByCcyCdIdxKey src);

	/**
	 *	Allocate a protected CcyNmIdx key over protected ISOCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCcyByCcyNmIdxKey newProtByCcyNmIdxKey();

	/**
	 *	Allocate a public CcyNmIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCcyByCcyNmIdxKey asPublic(ICFSecProtISOCcyByCcyNmIdxKey src);

	/**
	 *	Allocate a protected ISOCcy interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCcy newProtRec();

	/**
	 *	Allocate a public ISOCcy interface from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCcy asPublic(ICFSecProtISOCcy src);

	/**
	 *	Allocate a protected ISOCcy history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCcyH newProtHRec();

	/**
	 *	Allocate a public ISOCcy history interface implementation from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCcyH asPublic(ICFSecProtISOCcyH src);

}
