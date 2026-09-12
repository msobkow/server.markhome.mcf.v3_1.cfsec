
// Description: Java Protected Factory interface for ISOCtryCcy.

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
 *	ICFSecProtISOCtryCcyFactory protected interface for ISOCtryCcy
 */
public interface ICFSecProtISOCtryCcyFactory extends ICFSecPubISOCtryCcyFactory
{

	/**
	 *	Allocate a primary key for ISOCtryCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcyPKey newProtPKey();

	/**
	 *	Allocate a public key for ISOCtryCcy instances from a protected key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcyPKey asPublic(ICFSecProtISOCtryCcyPKey src);

	/**
	 *	Allocate a protected primary history key for ISOCtryCcy instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOCtryCcyHPKey newProtHPKey();

	/**
	 *	Allocate a public primary history key for ISOCtryCcy instances from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOCtryCcyHPKey asPublic(ICFSecProtISOCtryCcyHPKey src);

	/**
	 *	Allocate a protected CtryIdx key over protected ISOCtryCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcyByCtryIdxKey newProtByCtryIdxKey();

	/**
	 *	Allocate a public CtryIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcyByCtryIdxKey asPublic(ICFSecProtISOCtryCcyByCtryIdxKey src);

	/**
	 *	Allocate a protected CcyIdx key over protected ISOCtryCcy instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcyByCcyIdxKey newProtByCcyIdxKey();

	/**
	 *	Allocate a public CcyIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcyByCcyIdxKey asPublic(ICFSecProtISOCtryCcyByCcyIdxKey src);

	/**
	 *	Allocate a protected ISOCtryCcy interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcy newProtRec();

	/**
	 *	Allocate a public ISOCtryCcy interface from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcy asPublic(ICFSecProtISOCtryCcy src);

	/**
	 *	Allocate a protected ISOCtryCcy history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryCcyH newProtHRec();

	/**
	 *	Allocate a public ISOCtryCcy history interface implementation from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryCcyH asPublic(ICFSecProtISOCtryCcyH src);

}
