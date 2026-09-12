// Description: Java 25 Protected Table Object interface for CFSecProt.

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

package server.markhome.mcf.v3_1.cfsec.cfsecprotobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public interface ICFSecProtISOLangTableObj
{
	public ICFSecProtSchemaObj getSchema();
	public void setSchema( ICFSecProtSchemaObj value );

	public void minimizeMemory();

	public String getTableName();
	public String getTableDbName();

	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	public int getClassCode();

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	// public static int getBackingClassCode();

	Class getObjQualifyingClass();

	/**
	 *	Instantiate a new ISOLang instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtISOLangObj newInstance();

	/**
	 *	Instantiate a new ISOLang edition of the specified ISOLang instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtISOLangEditObj newEditInstance( ICFSecProtISOLangObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtISOLangObj realiseISOLang( ICFSecProtISOLangObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtISOLangObj createISOLang( ICFSecProtISOLangObj Obj );

	/**
	 *	Read a ISOLang-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ISOLang-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtISOLangObj readISOLang( Short pkey );

	/**
	 *	Read a ISOLang-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ISOLang-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtISOLangObj readISOLang( Short pkey,
		boolean forceRead );

	ICFSecProtISOLangObj readCachedISOLang( Short pkey );

	public void reallyDeepDisposeISOLang( ICFSecProtISOLangObj obj );

	void deepDisposeISOLang( Short pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtISOLangObj lockISOLang( Short pkey );

	/**
	 *	Return a sorted list of all the ISOLang-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtISOLangObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtISOLangObj> readAllISOLang();

	/**
	 *	Return a sorted map of all the ISOLang-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtISOLangObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtISOLangObj> readAllISOLang( boolean forceRead );

	List<ICFSecProtISOLangObj> readCachedAllISOLang();

	/**
	 *	Get the ICFSecProtISOLangObj instance for the primary key attributes.
	 *
	 *	@param	ISOLangId	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtISOLangObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtISOLangObj readISOLangByIdIdx( short ISOLangId );

	/**
	 *	Get the ICFSecProtISOLangObj instance for the primary key attributes.
	 *
	 *	@param	ISOLangId	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtISOLangObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtISOLangObj readISOLangByIdIdx( short ISOLangId,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtISOLangObj instance for the unique Code3Idx key.
	 *
	 *	@param	ISO6392Code	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtISOLangObj cached instance for the unique Code3Idx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtISOLangObj readISOLangByCode3Idx(String ISO6392Code );

	/**
	 *	Get the ICFSecProtISOLangObj instance for the unique Code3Idx key.
	 *
	 *	@param	ISO6392Code	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtISOLangObj refreshed instance for the unique Code3Idx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtISOLangObj readISOLangByCode3Idx(String ISO6392Code,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtISOLangObj> instances sorted by their primary keys for the duplicate Code2Idx key.
	 *
	 *	@param	ISO6391Code	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtISOLangObj> cached instances sorted by their primary keys for the duplicate Code2Idx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtISOLangObj> readISOLangByCode2Idx( String ISO6391Code );

	/**
	 *	Get the map of List<ICFSecProtISOLangObj> instances sorted by their primary keys for the duplicate Code2Idx key.
	 *
	 *	@param	ISO6391Code	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtISOLangObj> cached instances sorted by their primary keys for the duplicate Code2Idx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtISOLangObj> readISOLangByCode2Idx( String ISO6391Code,
		boolean forceRead );

	ICFSecProtISOLangObj readCachedISOLangByIdIdx( short ISOLangId );

	ICFSecProtISOLangObj readCachedISOLangByCode3Idx( String ISO6392Code );

	List<List<ICFSecProtISOLangObj>> readCachedISOLangByCode2Idx( String ISO6391Code );

	void deepDisposeISOLangByIdIdx( short ISOLangId );

	void deepDisposeISOLangByCode3Idx( String ISO6392Code );

	void deepDisposeISOLangByCode2Idx( String ISO6391Code );

	/**
	 *	Internal use only.
	 */
	ICFSecProtISOLangObj updateISOLang( ICFSecProtISOLangObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteISOLang( ICFSecProtISOLangObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	ISOLangId	The ISOLang key attribute of the instance generating the id.
	 */
	void deleteISOLangByIdIdx( short ISOLangId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ISO6392Code	The ISOLang key attribute of the instance generating the id.
	 */
	void deleteISOLangByCode3Idx(String ISO6392Code );

	/**
	 *	Internal use only.
	 *
	 *	@param	ISO6391Code	The ISOLang key attribute of the instance generating the id.
	 */
	void deleteISOLangByCode2Idx( String ISO6391Code );
}
