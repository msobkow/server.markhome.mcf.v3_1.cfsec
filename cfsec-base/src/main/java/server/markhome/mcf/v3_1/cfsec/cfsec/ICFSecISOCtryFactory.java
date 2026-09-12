
// Description: Java JPA Factory interface for ISOCtry.

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
 *	ICFSecISOCtryFactory interface for ISOCtry
 */
public interface ICFSecISOCtryFactory extends ICFSecProtISOCtryFactory
{

	/**
	 *	Allocate a primary history key for ISOCtry instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecISOCtryHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for ISOCtry instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOCtryHPKey asProtected(ICFSecISOCtryHPKey src);

	/**
	 *	Allocate a public primary history key for ISOCtry instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOCtryHPKey asPublic(ICFSecISOCtryHPKey src);

	/**
	 *	Allocate a ISOCodeIdx key over ISOCtry instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryByISOCodeIdxKey newByISOCodeIdxKey();

	/**
	 *	Allocate a protected ISOCodeIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryByISOCodeIdxKey asProtected(ICFSecISOCtryByISOCodeIdxKey src);

	/**
	 *	Allocate a public ISOCodeIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryByISOCodeIdxKey asPublic(ICFSecISOCtryByISOCodeIdxKey src);

	/**
	 *	Allocate a NameIdx key over ISOCtry instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryByNameIdxKey newByNameIdxKey();

	/**
	 *	Allocate a protected NameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryByNameIdxKey asProtected(ICFSecISOCtryByNameIdxKey src);

	/**
	 *	Allocate a public NameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryByNameIdxKey asPublic(ICFSecISOCtryByNameIdxKey src);

	/**
	 *	Allocate a ISOCtry interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtry newRec();

	/**
	 *	Allocate a protected ISOCtry interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtry asProtected(ICFSecISOCtry src);

	/**
	 *	Allocate a public ISOCtry interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtry asPublic(ICFSecISOCtry src);

	/**
	 *	Allocate a ISOCtry history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryH newHRec();

	/**
	 *	Allocate a protected ISOCtry history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryH asProtected(ICFSecISOCtryH src);

	/**
	 *	Allocate a public ISOCtry history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryH asPublic(ICFSecISOCtryH src);

}
