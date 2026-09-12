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

public interface ICFSecSecClusRoleMembTableObj
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
	 *	Instantiate a new SecClusRoleMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecClusRoleMembObj newInstance();

	/**
	 *	Instantiate a new SecClusRoleMemb edition of the specified SecClusRoleMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecClusRoleMembEditObj newEditInstance( ICFSecSecClusRoleMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecClusRoleMembObj realiseSecClusRoleMemb( ICFSecSecClusRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecClusRoleMembObj createSecClusRoleMemb( ICFSecSecClusRoleMembObj Obj );

	/**
	 *	Read a SecClusRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecClusRoleMembObj readSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey );

	/**
	 *	Read a SecClusRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecClusRoleMembObj readSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecClusRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecClusRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecClusRoleMembObj readSecClusRoleMemb( ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Read a SecClusRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecClusRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecClusRoleMembObj readSecClusRoleMemb( ICFLibKeyHash256 SecClusRoleId,
		String LoginId,
		boolean forceRead );

	ICFSecSecClusRoleMembObj readCachedSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey );

	public void reallyDeepDisposeSecClusRoleMemb( ICFSecSecClusRoleMembObj obj );

	void deepDisposeSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecClusRoleMembObj lockSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecClusRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecClusRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> readAllSecClusRoleMemb();

	/**
	 *	Return a sorted map of all the SecClusRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecClusRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> readAllSecClusRoleMemb( boolean forceRead );

	List<ICFSecSecClusRoleMembObj> readCachedAllSecClusRoleMemb();

	/**
	 *	Return a sorted map of a page of the SecClusRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecClusRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> pageAllSecClusRoleMemb(ICFLibKeyHash256 priorSecClusRoleId,
		String priorLoginId );

	/**
	 *	Get the CFSecSecClusRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecClusRoleMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecClusRoleMembObj readSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Get the CFSecSecClusRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecClusRoleMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecClusRoleMembObj readSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecClusRoleMembObj instances sorted by their primary keys for the duplicate ClusRoleIdx key.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecClusRoleMembObj cached instances sorted by their primary keys for the duplicate ClusRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> readSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId );

	/**
	 *	Get the map of CFSecSecClusRoleMembObj instances sorted by their primary keys for the duplicate ClusRoleIdx key.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecClusRoleMembObj cached instances sorted by their primary keys for the duplicate ClusRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> readSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecClusRoleMembObj instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecClusRoleMembObj cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> readSecClusRoleMembByLoginIdx( String LoginId );

	/**
	 *	Get the map of CFSecSecClusRoleMembObj instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecClusRoleMembObj cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> readSecClusRoleMembByLoginIdx( String LoginId,
		boolean forceRead );

	ICFSecSecClusRoleMembObj readCachedSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	List<ICFSecSecClusRoleMembObj> readCachedSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId );

	List<ICFSecSecClusRoleMembObj> readCachedSecClusRoleMembByLoginIdx( String LoginId );

	void deepDisposeSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	void deepDisposeSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId );

	void deepDisposeSecClusRoleMembByLoginIdx( String LoginId );

	/**
	 *	Read a page of data as a List of SecClusRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ClusRoleIdx key attributes.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> pageSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId,
		ICFLibKeyHash256 priorSecClusRoleId,
		String priorLoginId );

	/**
	 *	Read a page of data as a List of SecClusRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecClusRoleMembObj> pageSecClusRoleMembByLoginIdx( String LoginId,
		ICFLibKeyHash256 priorSecClusRoleId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecSecClusRoleMembObj updateSecClusRoleMemb( ICFSecSecClusRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecClusRoleMemb( ICFSecSecClusRoleMembObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId );

	/**
	 *	Internal use only.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 */
	void deleteSecClusRoleMembByLoginIdx( String LoginId );
}
