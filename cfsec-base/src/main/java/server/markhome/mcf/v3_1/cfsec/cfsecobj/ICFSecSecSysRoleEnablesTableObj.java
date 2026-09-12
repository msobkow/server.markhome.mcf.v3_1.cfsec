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

public interface ICFSecSecSysRoleEnablesTableObj
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
	 *	Instantiate a new SecSysRoleEnables instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecSysRoleEnablesObj newInstance();

	/**
	 *	Instantiate a new SecSysRoleEnables edition of the specified SecSysRoleEnables instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecSysRoleEnablesEditObj newEditInstance( ICFSecSecSysRoleEnablesObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleEnablesObj realiseSecSysRoleEnables( ICFSecSecSysRoleEnablesObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleEnablesObj createSecSysRoleEnables( ICFSecSecSysRoleEnablesObj Obj );

	/**
	 *	Read a SecSysRoleEnables-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRoleEnables-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleEnablesObj readSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey );

	/**
	 *	Read a SecSysRoleEnables-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRoleEnables-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleEnablesObj readSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecSysRoleEnables-derived instance by it's primary key.
	 *
	 *	@return	The SecSysRoleEnables-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleEnablesObj readSecSysRoleEnables( ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Read a SecSysRoleEnables-derived instance by it's primary key.
	 *
	 *	@return	The SecSysRoleEnables-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleEnablesObj readSecSysRoleEnables( ICFLibKeyHash256 SecSysRoleId,
		String EnableName,
		boolean forceRead );

	ICFSecSecSysRoleEnablesObj readCachedSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey );

	public void reallyDeepDisposeSecSysRoleEnables( ICFSecSecSysRoleEnablesObj obj );

	void deepDisposeSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleEnablesObj lockSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey );

	/**
	 *	Return a sorted list of all the SecSysRoleEnables-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleEnablesObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> readAllSecSysRoleEnables();

	/**
	 *	Return a sorted map of all the SecSysRoleEnables-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleEnablesObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> readAllSecSysRoleEnables( boolean forceRead );

	List<ICFSecSecSysRoleEnablesObj> readCachedAllSecSysRoleEnables();

	/**
	 *	Return a sorted map of a page of the SecSysRoleEnables-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleEnablesObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> pageAllSecSysRoleEnables(ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName );

	/**
	 *	Get the CFSecSecSysRoleEnablesObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysRoleEnablesObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysRoleEnablesObj readSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Get the CFSecSecSysRoleEnablesObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysRoleEnablesObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysRoleEnablesObj readSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysRoleEnablesObj instances sorted by their primary keys for the duplicate SysRoleIdx key.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysRoleEnablesObj cached instances sorted by their primary keys for the duplicate SysRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> readSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Get the map of CFSecSecSysRoleEnablesObj instances sorted by their primary keys for the duplicate SysRoleIdx key.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysRoleEnablesObj cached instances sorted by their primary keys for the duplicate SysRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> readSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysRoleEnablesObj instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysRoleEnablesObj cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> readSecSysRoleEnablesByNameIdx( String EnableName );

	/**
	 *	Get the map of CFSecSecSysRoleEnablesObj instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysRoleEnablesObj cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> readSecSysRoleEnablesByNameIdx( String EnableName,
		boolean forceRead );

	ICFSecSecSysRoleEnablesObj readCachedSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	List<ICFSecSecSysRoleEnablesObj> readCachedSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	List<ICFSecSecSysRoleEnablesObj> readCachedSecSysRoleEnablesByNameIdx( String EnableName );

	void deepDisposeSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	void deepDisposeSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	void deepDisposeSecSysRoleEnablesByNameIdx( String EnableName );

	/**
	 *	Read a page of data as a List of SecSysRoleEnables-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysRoleIdx key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysRoleEnables-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> pageSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName );

	/**
	 *	Read a page of data as a List of SecSysRoleEnables-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NameIdx key attributes.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysRoleEnables-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecSysRoleEnablesObj> pageSecSysRoleEnablesByNameIdx( String EnableName,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleEnablesObj updateSecSysRoleEnables( ICFSecSecSysRoleEnablesObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysRoleEnables( ICFSecSecSysRoleEnablesObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Internal use only.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleEnablesByNameIdx( String EnableName );
}
