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

public interface ICFSecProtISOTZoneTableObj
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
	 *	Instantiate a new ISOTZone instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtISOTZoneObj newInstance();

	/**
	 *	Instantiate a new ISOTZone edition of the specified ISOTZone instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtISOTZoneEditObj newEditInstance( ICFSecProtISOTZoneObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtISOTZoneObj realiseISOTZone( ICFSecProtISOTZoneObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtISOTZoneObj createISOTZone( ICFSecProtISOTZoneObj Obj );

	/**
	 *	Read a ISOTZone-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ISOTZone-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtISOTZoneObj readISOTZone( Short pkey );

	/**
	 *	Read a ISOTZone-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ISOTZone-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtISOTZoneObj readISOTZone( Short pkey,
		boolean forceRead );

	ICFSecProtISOTZoneObj readCachedISOTZone( Short pkey );

	public void reallyDeepDisposeISOTZone( ICFSecProtISOTZoneObj obj );

	void deepDisposeISOTZone( Short pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtISOTZoneObj lockISOTZone( Short pkey );

	/**
	 *	Return a sorted list of all the ISOTZone-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtISOTZoneObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtISOTZoneObj> readAllISOTZone();

	/**
	 *	Return a sorted map of all the ISOTZone-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtISOTZoneObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtISOTZoneObj> readAllISOTZone( boolean forceRead );

	List<ICFSecProtISOTZoneObj> readCachedAllISOTZone();

	/**
	 *	Get the ICFSecProtISOTZoneObj instance for the primary key attributes.
	 *
	 *	@param	ISOTZoneId	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtISOTZoneObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtISOTZoneObj readISOTZoneByIdIdx( short ISOTZoneId );

	/**
	 *	Get the ICFSecProtISOTZoneObj instance for the primary key attributes.
	 *
	 *	@param	ISOTZoneId	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtISOTZoneObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtISOTZoneObj readISOTZoneByIdIdx( short ISOTZoneId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtISOTZoneObj> instances sorted by their primary keys for the duplicate OffsetIdx key.
	 *
	 *	@param	TZHourOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@param	TZMinOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtISOTZoneObj> cached instances sorted by their primary keys for the duplicate OffsetIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtISOTZoneObj> readISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset );

	/**
	 *	Get the map of List<ICFSecProtISOTZoneObj> instances sorted by their primary keys for the duplicate OffsetIdx key.
	 *
	 *	@param	TZHourOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@param	TZMinOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtISOTZoneObj> cached instances sorted by their primary keys for the duplicate OffsetIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtISOTZoneObj> readISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtISOTZoneObj instance for the unique UTZNameIdx key.
	 *
	 *	@param	TZName	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtISOTZoneObj cached instance for the unique UTZNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtISOTZoneObj readISOTZoneByUTZNameIdx(String TZName );

	/**
	 *	Get the ICFSecProtISOTZoneObj instance for the unique UTZNameIdx key.
	 *
	 *	@param	TZName	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtISOTZoneObj refreshed instance for the unique UTZNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtISOTZoneObj readISOTZoneByUTZNameIdx(String TZName,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtISOTZoneObj> instances sorted by their primary keys for the duplicate Iso8601Idx key.
	 *
	 *	@param	Iso8601	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtISOTZoneObj> cached instances sorted by their primary keys for the duplicate Iso8601Idx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtISOTZoneObj> readISOTZoneByIso8601Idx( String Iso8601 );

	/**
	 *	Get the map of List<ICFSecProtISOTZoneObj> instances sorted by their primary keys for the duplicate Iso8601Idx key.
	 *
	 *	@param	Iso8601	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtISOTZoneObj> cached instances sorted by their primary keys for the duplicate Iso8601Idx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtISOTZoneObj> readISOTZoneByIso8601Idx( String Iso8601,
		boolean forceRead );

	ICFSecProtISOTZoneObj readCachedISOTZoneByIdIdx( short ISOTZoneId );

	List<List<ICFSecProtISOTZoneObj>> readCachedISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset );

	ICFSecProtISOTZoneObj readCachedISOTZoneByUTZNameIdx( String TZName );

	List<List<ICFSecProtISOTZoneObj>> readCachedISOTZoneByIso8601Idx( String Iso8601 );

	void deepDisposeISOTZoneByIdIdx( short ISOTZoneId );

	void deepDisposeISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset );

	void deepDisposeISOTZoneByUTZNameIdx( String TZName );

	void deepDisposeISOTZoneByIso8601Idx( String Iso8601 );

	/**
	 *	Internal use only.
	 */
	ICFSecProtISOTZoneObj updateISOTZone( ICFSecProtISOTZoneObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteISOTZone( ICFSecProtISOTZoneObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	ISOTZoneId	The ISOTZone key attribute of the instance generating the id.
	 */
	void deleteISOTZoneByIdIdx( short ISOTZoneId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TZHourOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@param	TZMinOffset	The ISOTZone key attribute of the instance generating the id.
	 */
	void deleteISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset );

	/**
	 *	Internal use only.
	 *
	 *	@param	TZName	The ISOTZone key attribute of the instance generating the id.
	 */
	void deleteISOTZoneByUTZNameIdx(String TZName );

	/**
	 *	Internal use only.
	 *
	 *	@param	Iso8601	The ISOTZone key attribute of the instance generating the id.
	 */
	void deleteISOTZoneByIso8601Idx( String Iso8601 );
}
