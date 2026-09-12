// Description: Java 25 Table Object interface for CFSec.

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

package server.markhome.mcf.v3_1.cfsec.cfsecobj;

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

public interface ICFSecSecSysGrpIncTableObj
{
	public ICFSecSchemaObj getSchema();
	public void setSchema( ICFSecSchemaObj value );

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
	 *	Instantiate a new SecSysGrpInc instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecSysGrpIncObj newInstance();

	/**
	 *	Instantiate a new SecSysGrpInc edition of the specified SecSysGrpInc instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecSysGrpIncEditObj newEditInstance( ICFSecSecSysGrpIncObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpIncObj realiseSecSysGrpInc( ICFSecSecSysGrpIncObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpIncObj createSecSysGrpInc( ICFSecSecSysGrpIncObj Obj );

	/**
	 *	Read a SecSysGrpInc-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrpInc-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpIncObj readSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey );

	/**
	 *	Read a SecSysGrpInc-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrpInc-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpIncObj readSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecSysGrpInc-derived instance by it's primary key.
	 *
	 *	@return	The SecSysGrpInc-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpIncObj readSecSysGrpInc( ICFLibKeyHash256 SecSysGrpId,
		String InclName );

	/**
	 *	Read a SecSysGrpInc-derived instance by it's primary key.
	 *
	 *	@return	The SecSysGrpInc-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpIncObj readSecSysGrpInc( ICFLibKeyHash256 SecSysGrpId,
		String InclName,
		boolean forceRead );

	ICFSecSecSysGrpIncObj readCachedSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey );

	public void reallyDeepDisposeSecSysGrpInc( ICFSecSecSysGrpIncObj obj );

	void deepDisposeSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpIncObj lockSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey );

	/**
	 *	Return a sorted list of all the SecSysGrpInc-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpIncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> readAllSecSysGrpInc();

	/**
	 *	Return a sorted map of all the SecSysGrpInc-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpIncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> readAllSecSysGrpInc( boolean forceRead );

	List<ICFSecSecSysGrpIncObj> readCachedAllSecSysGrpInc();

	/**
	 *	Return a sorted map of a page of the SecSysGrpInc-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpIncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> pageAllSecSysGrpInc(ICFLibKeyHash256 priorSecSysGrpId,
		String priorInclName );

	/**
	 *	Get the CFSecSecSysGrpIncObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@param	InclName	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysGrpIncObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysGrpIncObj readSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName );

	/**
	 *	Get the CFSecSecSysGrpIncObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@param	InclName	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysGrpIncObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysGrpIncObj readSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysGrpIncObj instances sorted by their primary keys for the duplicate SysGrpIdx key.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpIncObj cached instances sorted by their primary keys for the duplicate SysGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> readSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	/**
	 *	Get the map of CFSecSecSysGrpIncObj instances sorted by their primary keys for the duplicate SysGrpIdx key.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpIncObj cached instances sorted by their primary keys for the duplicate SysGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> readSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysGrpIncObj instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	InclName	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpIncObj cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> readSecSysGrpIncByNameIdx( String InclName );

	/**
	 *	Get the map of CFSecSecSysGrpIncObj instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	InclName	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpIncObj cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> readSecSysGrpIncByNameIdx( String InclName,
		boolean forceRead );

	ICFSecSecSysGrpIncObj readCachedSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName );

	List<ICFSecSecSysGrpIncObj> readCachedSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	List<ICFSecSecSysGrpIncObj> readCachedSecSysGrpIncByNameIdx( String InclName );

	void deepDisposeSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName );

	void deepDisposeSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	void deepDisposeSecSysGrpIncByNameIdx( String InclName );

	/**
	 *	Read a page of data as a List of SecSysGrpInc-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysGrpIdx key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpInc-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> pageSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		ICFLibKeyHash256 priorSecSysGrpId,
		String priorInclName );

	/**
	 *	Read a page of data as a List of SecSysGrpInc-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NameIdx key attributes.
	 *
	 *	@param	InclName	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpInc-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecSysGrpIncObj> pageSecSysGrpIncByNameIdx( String InclName,
		ICFLibKeyHash256 priorSecSysGrpId,
		String priorInclName );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpIncObj updateSecSysGrpInc( ICFSecSecSysGrpIncObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysGrpInc( ICFSecSecSysGrpIncObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@param	InclName	The SecSysGrpInc key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpInc key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	/**
	 *	Internal use only.
	 *
	 *	@param	InclName	The SecSysGrpInc key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpIncByNameIdx( String InclName );
}
