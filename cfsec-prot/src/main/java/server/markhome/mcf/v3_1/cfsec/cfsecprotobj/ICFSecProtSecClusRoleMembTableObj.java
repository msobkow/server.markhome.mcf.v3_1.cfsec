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

public interface ICFSecProtSecClusRoleMembTableObj
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
	 *	Instantiate a new SecClusRoleMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecClusRoleMembObj newInstance();

	/**
	 *	Instantiate a new SecClusRoleMemb edition of the specified SecClusRoleMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecClusRoleMembEditObj newEditInstance( ICFSecProtSecClusRoleMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusRoleMembObj realiseSecClusRoleMemb( ICFSecProtSecClusRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusRoleMembObj createSecClusRoleMemb( ICFSecProtSecClusRoleMembObj Obj );

	/**
	 *	Read a SecClusRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusRoleMembObj readSecClusRoleMemb( ICFSecProtSecClusRoleMembPKey pkey );

	/**
	 *	Read a SecClusRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusRoleMembObj readSecClusRoleMemb( ICFSecProtSecClusRoleMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecClusRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecClusRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusRoleMembObj readSecClusRoleMemb( ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Read a SecClusRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecClusRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusRoleMembObj readSecClusRoleMemb( ICFLibKeyHash256 SecClusRoleId,
		String LoginId,
		boolean forceRead );

	ICFSecProtSecClusRoleMembObj readCachedSecClusRoleMemb( ICFSecProtSecClusRoleMembPKey pkey );

	public void reallyDeepDisposeSecClusRoleMemb( ICFSecProtSecClusRoleMembObj obj );

	void deepDisposeSecClusRoleMemb( ICFSecProtSecClusRoleMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusRoleMembObj lockSecClusRoleMemb( ICFSecProtSecClusRoleMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecClusRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusRoleMembObj> readAllSecClusRoleMemb();

	/**
	 *	Return a sorted map of all the SecClusRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusRoleMembObj> readAllSecClusRoleMemb( boolean forceRead );

	List<ICFSecProtSecClusRoleMembObj> readCachedAllSecClusRoleMemb();

	/**
	 *	Return a sorted map of a page of the SecClusRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusRoleMembObj> pageAllSecClusRoleMemb(CFLibDbKeyHash256 priorSecClusRoleId,
		String priorLoginId );

	/**
	 *	Get the ICFSecProtSecClusRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusRoleMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusRoleMembObj readSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Get the ICFSecProtSecClusRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusRoleMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusRoleMembObj readSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecClusRoleMembObj> instances sorted by their primary keys for the duplicate ClusRoleIdx key.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusRoleMembObj> cached instances sorted by their primary keys for the duplicate ClusRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusRoleMembObj> readSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId );

	/**
	 *	Get the map of List<ICFSecProtSecClusRoleMembObj> instances sorted by their primary keys for the duplicate ClusRoleIdx key.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusRoleMembObj> cached instances sorted by their primary keys for the duplicate ClusRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusRoleMembObj> readSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecClusRoleMembObj> instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusRoleMembObj> cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusRoleMembObj> readSecClusRoleMembByLoginIdx( String LoginId );

	/**
	 *	Get the map of List<ICFSecProtSecClusRoleMembObj> instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusRoleMembObj> cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusRoleMembObj> readSecClusRoleMembByLoginIdx( String LoginId,
		boolean forceRead );

	ICFSecProtSecClusRoleMembObj readCachedSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	List<List<ICFSecProtSecClusRoleMembObj>> readCachedSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId );

	List<List<ICFSecProtSecClusRoleMembObj>> readCachedSecClusRoleMembByLoginIdx( String LoginId );

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
	List<ICFSecProtSecClusRoleMembObj> pageSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId,
		CFLibDbKeyHash256 priorSecClusRoleId,
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
	List<ICFSecProtSecClusRoleMembObj> pageSecClusRoleMembByLoginIdx( String LoginId,
		CFLibDbKeyHash256 priorSecClusRoleId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusRoleMembObj updateSecClusRoleMemb( ICFSecProtSecClusRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecClusRoleMemb( ICFSecProtSecClusRoleMembObj Obj );

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
