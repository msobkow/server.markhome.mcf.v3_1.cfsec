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

public interface ICFSecProtSecSysRoleEnablesTableObj
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
	 *	Instantiate a new SecSysRoleEnables instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecSysRoleEnablesObj newInstance();

	/**
	 *	Instantiate a new SecSysRoleEnables edition of the specified SecSysRoleEnables instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecSysRoleEnablesEditObj newEditInstance( ICFSecProtSecSysRoleEnablesObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysRoleEnablesObj realiseSecSysRoleEnables( ICFSecProtSecSysRoleEnablesObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysRoleEnablesObj createSecSysRoleEnables( ICFSecProtSecSysRoleEnablesObj Obj );

	/**
	 *	Read a SecSysRoleEnables-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRoleEnables-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysRoleEnablesObj readSecSysRoleEnables( ICFSecProtSecSysRoleEnablesPKey pkey );

	/**
	 *	Read a SecSysRoleEnables-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRoleEnables-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysRoleEnablesObj readSecSysRoleEnables( ICFSecProtSecSysRoleEnablesPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecSysRoleEnables-derived instance by it's primary key.
	 *
	 *	@return	The SecSysRoleEnables-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysRoleEnablesObj readSecSysRoleEnables( ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Read a SecSysRoleEnables-derived instance by it's primary key.
	 *
	 *	@return	The SecSysRoleEnables-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysRoleEnablesObj readSecSysRoleEnables( ICFLibKeyHash256 SecSysRoleId,
		String EnableName,
		boolean forceRead );

	ICFSecProtSecSysRoleEnablesObj readCachedSecSysRoleEnables( ICFSecProtSecSysRoleEnablesPKey pkey );

	public void reallyDeepDisposeSecSysRoleEnables( ICFSecProtSecSysRoleEnablesObj obj );

	void deepDisposeSecSysRoleEnables( ICFSecProtSecSysRoleEnablesPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysRoleEnablesObj lockSecSysRoleEnables( ICFSecProtSecSysRoleEnablesPKey pkey );

	/**
	 *	Return a sorted list of all the SecSysRoleEnables-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysRoleEnablesObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> readAllSecSysRoleEnables();

	/**
	 *	Return a sorted map of all the SecSysRoleEnables-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysRoleEnablesObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> readAllSecSysRoleEnables( boolean forceRead );

	List<ICFSecProtSecSysRoleEnablesObj> readCachedAllSecSysRoleEnables();

	/**
	 *	Return a sorted map of a page of the SecSysRoleEnables-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysRoleEnablesObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> pageAllSecSysRoleEnables(CFLibDbKeyHash256 priorSecSysRoleId,
		String priorEnableName );

	/**
	 *	Get the ICFSecProtSecSysRoleEnablesObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysRoleEnablesObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysRoleEnablesObj readSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Get the ICFSecProtSecSysRoleEnablesObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysRoleEnablesObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysRoleEnablesObj readSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSysRoleEnablesObj> instances sorted by their primary keys for the duplicate SysRoleIdx key.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysRoleEnablesObj> cached instances sorted by their primary keys for the duplicate SysRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> readSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Get the map of List<ICFSecProtSecSysRoleEnablesObj> instances sorted by their primary keys for the duplicate SysRoleIdx key.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysRoleEnablesObj> cached instances sorted by their primary keys for the duplicate SysRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> readSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSysRoleEnablesObj> instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysRoleEnablesObj> cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> readSecSysRoleEnablesByNameIdx( String EnableName );

	/**
	 *	Get the map of List<ICFSecProtSecSysRoleEnablesObj> instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysRoleEnablesObj> cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysRoleEnablesObj> readSecSysRoleEnablesByNameIdx( String EnableName,
		boolean forceRead );

	ICFSecProtSecSysRoleEnablesObj readCachedSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	List<List<ICFSecProtSecSysRoleEnablesObj>> readCachedSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	List<List<ICFSecProtSecSysRoleEnablesObj>> readCachedSecSysRoleEnablesByNameIdx( String EnableName );

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
	List<ICFSecProtSecSysRoleEnablesObj> pageSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		CFLibDbKeyHash256 priorSecSysRoleId,
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
	List<ICFSecProtSecSysRoleEnablesObj> pageSecSysRoleEnablesByNameIdx( String EnableName,
		CFLibDbKeyHash256 priorSecSysRoleId,
		String priorEnableName );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysRoleEnablesObj updateSecSysRoleEnables( ICFSecProtSecSysRoleEnablesObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysRoleEnables( ICFSecProtSecSysRoleEnablesObj Obj );

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
