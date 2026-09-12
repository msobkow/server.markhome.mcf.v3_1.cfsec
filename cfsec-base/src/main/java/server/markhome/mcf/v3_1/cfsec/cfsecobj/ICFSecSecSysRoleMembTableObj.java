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

public interface ICFSecSecSysRoleMembTableObj
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
	 *	Instantiate a new SecSysRoleMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecSysRoleMembObj newInstance();

	/**
	 *	Instantiate a new SecSysRoleMemb edition of the specified SecSysRoleMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecSysRoleMembEditObj newEditInstance( ICFSecSecSysRoleMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleMembObj realiseSecSysRoleMemb( ICFSecSecSysRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleMembObj createSecSysRoleMemb( ICFSecSecSysRoleMembObj Obj );

	/**
	 *	Read a SecSysRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleMembObj readSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey );

	/**
	 *	Read a SecSysRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleMembObj readSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecSysRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecSysRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleMembObj readSecSysRoleMemb( ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Read a SecSysRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecSysRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleMembObj readSecSysRoleMemb( ICFLibKeyHash256 SecSysRoleId,
		String LoginId,
		boolean forceRead );

	ICFSecSecSysRoleMembObj readCachedSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey );

	public void reallyDeepDisposeSecSysRoleMemb( ICFSecSecSysRoleMembObj obj );

	void deepDisposeSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleMembObj lockSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecSysRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> readAllSecSysRoleMemb();

	/**
	 *	Return a sorted map of all the SecSysRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> readAllSecSysRoleMemb( boolean forceRead );

	List<ICFSecSecSysRoleMembObj> readCachedAllSecSysRoleMemb();

	/**
	 *	Return a sorted map of a page of the SecSysRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> pageAllSecSysRoleMemb(ICFLibKeyHash256 priorSecSysRoleId,
		String priorLoginId );

	/**
	 *	Get the CFSecSecSysRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysRoleMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysRoleMembObj readSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Get the CFSecSecSysRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysRoleMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysRoleMembObj readSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysRoleMembObj instances sorted by their primary keys for the duplicate SysRoleIdx key.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysRoleMembObj cached instances sorted by their primary keys for the duplicate SysRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> readSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Get the map of CFSecSecSysRoleMembObj instances sorted by their primary keys for the duplicate SysRoleIdx key.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysRoleMembObj cached instances sorted by their primary keys for the duplicate SysRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> readSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysRoleMembObj instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysRoleMembObj cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> readSecSysRoleMembByLoginIdx( String LoginId );

	/**
	 *	Get the map of CFSecSecSysRoleMembObj instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysRoleMembObj cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> readSecSysRoleMembByLoginIdx( String LoginId,
		boolean forceRead );

	ICFSecSecSysRoleMembObj readCachedSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	List<ICFSecSecSysRoleMembObj> readCachedSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	List<ICFSecSecSysRoleMembObj> readCachedSecSysRoleMembByLoginIdx( String LoginId );

	void deepDisposeSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	void deepDisposeSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	void deepDisposeSecSysRoleMembByLoginIdx( String LoginId );

	/**
	 *	Read a page of data as a List of SecSysRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysRoleIdx key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> pageSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorLoginId );

	/**
	 *	Read a page of data as a List of SecSysRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecSysRoleMembObj> pageSecSysRoleMembByLoginIdx( String LoginId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleMembObj updateSecSysRoleMemb( ICFSecSecSysRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysRoleMemb( ICFSecSecSysRoleMembObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Internal use only.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleMembByLoginIdx( String LoginId );
}
