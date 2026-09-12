
// Description: Java Protected Factory interface for ISOLang.

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
 *	ICFSecProtISOLangFactory protected interface for ISOLang
 */
public interface ICFSecProtISOLangFactory extends ICFSecPubISOLangFactory
{

	/**
	 *	Allocate a protected primary history key for ISOLang instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOLangHPKey newProtHPKey();

	/**
	 *	Allocate a public primary history key for ISOLang instances from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOLangHPKey asPublic(ICFSecProtISOLangHPKey src);

	/**
	 *	Allocate a protected Code3Idx key over protected ISOLang instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOLangByCode3IdxKey newProtByCode3IdxKey();

	/**
	 *	Allocate a public Code3Idx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOLangByCode3IdxKey asPublic(ICFSecProtISOLangByCode3IdxKey src);

	/**
	 *	Allocate a protected Code2Idx key over protected ISOLang instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOLangByCode2IdxKey newProtByCode2IdxKey();

	/**
	 *	Allocate a public Code2Idx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOLangByCode2IdxKey asPublic(ICFSecProtISOLangByCode2IdxKey src);

	/**
	 *	Allocate a protected ISOLang interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOLang newProtRec();

	/**
	 *	Allocate a public ISOLang interface from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOLang asPublic(ICFSecProtISOLang src);

	/**
	 *	Allocate a protected ISOLang history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOLangH newProtHRec();

	/**
	 *	Allocate a public ISOLang history interface implementation from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOLangH asPublic(ICFSecProtISOLangH src);

}
