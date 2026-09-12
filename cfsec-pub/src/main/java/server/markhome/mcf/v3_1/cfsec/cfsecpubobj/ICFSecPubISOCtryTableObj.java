// Description: Java 25 Public Table Object interface for CFSecPub.

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

package server.markhome.mcf.v3_1.cfsec.cfsecpubobj;

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

public interface ICFSecPubISOCtryTableObj
{
	public ICFSecPubSchemaObj getSchema();
	public void setSchema( ICFSecPubSchemaObj value );

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
	 *	Instantiate a new ISOCtry instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecPubISOCtryObj newInstance();

	/**
	 *	Instantiate a new ISOCtry edition of the specified ISOCtry instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecPubISOCtryEditObj newEditInstance( ICFSecPubISOCtryObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecPubISOCtryObj realiseISOCtry( ICFSecPubISOCtryObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecPubISOCtryObj createISOCtry( ICFSecPubISOCtryObj Obj );

	/**
	 *	Read a ISOCtry-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ISOCtry-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecPubISOCtryObj readISOCtry( Short pkey );

	/**
	 *	Read a ISOCtry-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ISOCtry-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecPubISOCtryObj readISOCtry( Short pkey,
		boolean forceRead );

	ICFSecPubISOCtryObj readCachedISOCtry( Short pkey );

	public void reallyDeepDisposeISOCtry( ICFSecPubISOCtryObj obj );

	void deepDisposeISOCtry( Short pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecPubISOCtryObj lockISOCtry( Short pkey );

	/**
	 *	Return a sorted list of all the ISOCtry-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubISOCtryObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubISOCtryObj> readAllISOCtry();

	/**
	 *	Return a sorted map of all the ISOCtry-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubISOCtryObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubISOCtryObj> readAllISOCtry( boolean forceRead );

	List<ICFSecPubISOCtryObj> readCachedAllISOCtry();

	/**
	 *	Get the CFSecPubISOCtryObj instance for the primary key attributes.
	 *
	 *	@param	ISOCtryId	The ISOCtry key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubISOCtryObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubISOCtryObj readISOCtryByIdIdx( short ISOCtryId );

	/**
	 *	Get the CFSecPubISOCtryObj instance for the primary key attributes.
	 *
	 *	@param	ISOCtryId	The ISOCtry key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubISOCtryObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubISOCtryObj readISOCtryByIdIdx( short ISOCtryId,
		boolean forceRead );

	/**
	 *	Get the CFSecPubISOCtryObj instance for the unique ISOCodeIdx key.
	 *
	 *	@param	ISOCode	The ISOCtry key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubISOCtryObj cached instance for the unique ISOCodeIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubISOCtryObj readISOCtryByISOCodeIdx(String ISOCode );

	/**
	 *	Get the CFSecPubISOCtryObj instance for the unique ISOCodeIdx key.
	 *
	 *	@param	ISOCode	The ISOCtry key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubISOCtryObj refreshed instance for the unique ISOCodeIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubISOCtryObj readISOCtryByISOCodeIdx(String ISOCode,
		boolean forceRead );

	/**
	 *	Get the CFSecPubISOCtryObj instance for the unique NameIdx key.
	 *
	 *	@param	Name	The ISOCtry key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubISOCtryObj cached instance for the unique NameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubISOCtryObj readISOCtryByNameIdx(String Name );

	/**
	 *	Get the CFSecPubISOCtryObj instance for the unique NameIdx key.
	 *
	 *	@param	Name	The ISOCtry key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubISOCtryObj refreshed instance for the unique NameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubISOCtryObj readISOCtryByNameIdx(String Name,
		boolean forceRead );

	ICFSecPubISOCtryObj readCachedISOCtryByIdIdx( short ISOCtryId );

	ICFSecPubISOCtryObj readCachedISOCtryByISOCodeIdx( String ISOCode );

	ICFSecPubISOCtryObj readCachedISOCtryByNameIdx( String Name );

	void deepDisposeISOCtryByIdIdx( short ISOCtryId );

	void deepDisposeISOCtryByISOCodeIdx( String ISOCode );

	void deepDisposeISOCtryByNameIdx( String Name );

	/**
	 *	Internal use only.
	 */
	ICFSecPubISOCtryObj updateISOCtry( ICFSecPubISOCtryObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteISOCtry( ICFSecPubISOCtryObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	ISOCtryId	The ISOCtry key attribute of the instance generating the id.
	 */
	void deleteISOCtryByIdIdx( short ISOCtryId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ISOCode	The ISOCtry key attribute of the instance generating the id.
	 */
	void deleteISOCtryByISOCodeIdx(String ISOCode );

	/**
	 *	Internal use only.
	 *
	 *	@param	Name	The ISOCtry key attribute of the instance generating the id.
	 */
	void deleteISOCtryByNameIdx(String Name );
}
