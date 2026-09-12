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

public interface ICFSecSecTentRoleMembTableObj
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
	 *	Instantiate a new SecTentRoleMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecTentRoleMembObj newInstance();

	/**
	 *	Instantiate a new SecTentRoleMemb edition of the specified SecTentRoleMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecTentRoleMembEditObj newEditInstance( ICFSecSecTentRoleMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentRoleMembObj realiseSecTentRoleMemb( ICFSecSecTentRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentRoleMembObj createSecTentRoleMemb( ICFSecSecTentRoleMembObj Obj );

	/**
	 *	Read a SecTentRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentRoleMembObj readSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey );

	/**
	 *	Read a SecTentRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentRoleMembObj readSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecTentRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecTentRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentRoleMembObj readSecTentRoleMemb( ICFLibKeyHash256 SecTentRoleId,
		String LoginId );

	/**
	 *	Read a SecTentRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecTentRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentRoleMembObj readSecTentRoleMemb( ICFLibKeyHash256 SecTentRoleId,
		String LoginId,
		boolean forceRead );

	ICFSecSecTentRoleMembObj readCachedSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey );

	public void reallyDeepDisposeSecTentRoleMemb( ICFSecSecTentRoleMembObj obj );

	void deepDisposeSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentRoleMembObj lockSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecTentRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> readAllSecTentRoleMemb();

	/**
	 *	Return a sorted map of all the SecTentRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> readAllSecTentRoleMemb( boolean forceRead );

	List<ICFSecSecTentRoleMembObj> readCachedAllSecTentRoleMemb();

	/**
	 *	Return a sorted map of a page of the SecTentRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> pageAllSecTentRoleMemb(ICFLibKeyHash256 priorSecTentRoleId,
		String priorLoginId );

	/**
	 *	Get the CFSecSecTentRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentRoleMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentRoleMembObj readSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId );

	/**
	 *	Get the CFSecSecTentRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentRoleMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentRoleMembObj readSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecTentRoleMembObj instances sorted by their primary keys for the duplicate TentRoleIdx key.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentRoleMembObj cached instances sorted by their primary keys for the duplicate TentRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> readSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId );

	/**
	 *	Get the map of CFSecSecTentRoleMembObj instances sorted by their primary keys for the duplicate TentRoleIdx key.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentRoleMembObj cached instances sorted by their primary keys for the duplicate TentRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> readSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecTentRoleMembObj instances sorted by their primary keys for the duplicate UserIdx key.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentRoleMembObj cached instances sorted by their primary keys for the duplicate UserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> readSecTentRoleMembByUserIdx( String LoginId );

	/**
	 *	Get the map of CFSecSecTentRoleMembObj instances sorted by their primary keys for the duplicate UserIdx key.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentRoleMembObj cached instances sorted by their primary keys for the duplicate UserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> readSecTentRoleMembByUserIdx( String LoginId,
		boolean forceRead );

	ICFSecSecTentRoleMembObj readCachedSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId );

	List<ICFSecSecTentRoleMembObj> readCachedSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId );

	List<ICFSecSecTentRoleMembObj> readCachedSecTentRoleMembByUserIdx( String LoginId );

	void deepDisposeSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId );

	void deepDisposeSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId );

	void deepDisposeSecTentRoleMembByUserIdx( String LoginId );

	/**
	 *	Read a page of data as a List of SecTentRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate TentRoleIdx key attributes.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> pageSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId,
		ICFLibKeyHash256 priorSecTentRoleId,
		String priorLoginId );

	/**
	 *	Read a page of data as a List of SecTentRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate UserIdx key attributes.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecTentRoleMembObj> pageSecTentRoleMembByUserIdx( String LoginId,
		ICFLibKeyHash256 priorSecTentRoleId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentRoleMembObj updateSecTentRoleMemb( ICFSecSecTentRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecTentRoleMemb( ICFSecSecTentRoleMembObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId );

	/**
	 *	Internal use only.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentRoleMembByUserIdx( String LoginId );
}
