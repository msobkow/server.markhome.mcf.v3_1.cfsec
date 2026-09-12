
// Description: Java Protected Factory interface for ISOCtryLang.

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
 *	ICFSecProtISOCtryLangFactory protected interface for ISOCtryLang
 */
public interface ICFSecProtISOCtryLangFactory extends ICFSecPubISOCtryLangFactory
{

	/**
	 *	Allocate a primary key for ISOCtryLang instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLangPKey newProtPKey();

	/**
	 *	Allocate a public key for ISOCtryLang instances from a protected key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLangPKey asPublic(ICFSecProtISOCtryLangPKey src);

	/**
	 *	Allocate a protected primary history key for ISOCtryLang instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOCtryLangHPKey newProtHPKey();

	/**
	 *	Allocate a public primary history key for ISOCtryLang instances from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOCtryLangHPKey asPublic(ICFSecProtISOCtryLangHPKey src);

	/**
	 *	Allocate a protected CtryIdx key over protected ISOCtryLang instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLangByCtryIdxKey newProtByCtryIdxKey();

	/**
	 *	Allocate a public CtryIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLangByCtryIdxKey asPublic(ICFSecProtISOCtryLangByCtryIdxKey src);

	/**
	 *	Allocate a protected LangIdx key over protected ISOCtryLang instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLangByLangIdxKey newProtByLangIdxKey();

	/**
	 *	Allocate a public LangIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLangByLangIdxKey asPublic(ICFSecProtISOCtryLangByLangIdxKey src);

	/**
	 *	Allocate a protected ISOCtryLang interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLang newProtRec();

	/**
	 *	Allocate a public ISOCtryLang interface from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLang asPublic(ICFSecProtISOCtryLang src);

	/**
	 *	Allocate a protected ISOCtryLang history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOCtryLangH newProtHRec();

	/**
	 *	Allocate a public ISOCtryLang history interface implementation from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOCtryLangH asPublic(ICFSecProtISOCtryLangH src);

}
