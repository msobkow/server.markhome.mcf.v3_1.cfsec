
// Description: Java JPA Factory interface for ISOCtryLang.

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
 *	ICFSecISOCtryLangFactory interface for ISOCtryLang
 */
public interface ICFSecISOCtryLangFactory extends ICFSecProtISOCtryLangFactory
{

	/**
	 *	Allocate a primary key for ISOCtryLang instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryLangPKey newPKey();

	/**
	 *	Allocate a protected key for ISOCtryLang instances from a private key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLangPKey asProtected(ICFSecISOCtryLangPKey src);

	/**
	 *	Allocate a public key for ISOCtryLang instances from a private key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLangPKey asPublic(ICFSecISOCtryLangPKey src);

	/**
	 *	Allocate a primary history key for ISOCtryLang instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecISOCtryLangHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for ISOCtryLang instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOCtryLangHPKey asProtected(ICFSecISOCtryLangHPKey src);

	/**
	 *	Allocate a public primary history key for ISOCtryLang instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOCtryLangHPKey asPublic(ICFSecISOCtryLangHPKey src);

	/**
	 *	Allocate a CtryIdx key over ISOCtryLang instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryLangByCtryIdxKey newByCtryIdxKey();

	/**
	 *	Allocate a protected CtryIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLangByCtryIdxKey asProtected(ICFSecISOCtryLangByCtryIdxKey src);

	/**
	 *	Allocate a public CtryIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLangByCtryIdxKey asPublic(ICFSecISOCtryLangByCtryIdxKey src);

	/**
	 *	Allocate a LangIdx key over ISOCtryLang instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryLangByLangIdxKey newByLangIdxKey();

	/**
	 *	Allocate a protected LangIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLangByLangIdxKey asProtected(ICFSecISOCtryLangByLangIdxKey src);

	/**
	 *	Allocate a public LangIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLangByLangIdxKey asPublic(ICFSecISOCtryLangByLangIdxKey src);

	/**
	 *	Allocate a ISOCtryLang interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryLang newRec();

	/**
	 *	Allocate a protected ISOCtryLang interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLang asProtected(ICFSecISOCtryLang src);

	/**
	 *	Allocate a public ISOCtryLang interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLang asPublic(ICFSecISOCtryLang src);

	/**
	 *	Allocate a ISOCtryLang history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOCtryLangH newHRec();

	/**
	 *	Allocate a protected ISOCtryLang history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLangH asProtected(ICFSecISOCtryLangH src);

	/**
	 *	Allocate a public ISOCtryLang history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLangH asPublic(ICFSecISOCtryLangH src);

}
