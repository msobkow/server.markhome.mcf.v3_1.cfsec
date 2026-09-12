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

public interface ICFSecProtSecTentRoleMembTableObj
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
	 *	Instantiate a new SecTentRoleMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecTentRoleMembObj newInstance();

	/**
	 *	Instantiate a new SecTentRoleMemb edition of the specified SecTentRoleMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecTentRoleMembEditObj newEditInstance( ICFSecProtSecTentRoleMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentRoleMembObj realiseSecTentRoleMemb( ICFSecProtSecTentRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentRoleMembObj createSecTentRoleMemb( ICFSecProtSecTentRoleMembObj Obj );

	/**
	 *	Read a SecTentRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentRoleMembObj readSecTentRoleMemb( ICFSecProtSecTentRoleMembPKey pkey );

	/**
	 *	Read a SecTentRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentRoleMembObj readSecTentRoleMemb( ICFSecProtSecTentRoleMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecTentRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecTentRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentRoleMembObj readSecTentRoleMemb( ICFLibKeyHash256 SecTentRoleId,
		String LoginId );

	/**
	 *	Read a SecTentRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecTentRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentRoleMembObj readSecTentRoleMemb( ICFLibKeyHash256 SecTentRoleId,
		String LoginId,
		boolean forceRead );

	ICFSecProtSecTentRoleMembObj readCachedSecTentRoleMemb( ICFSecProtSecTentRoleMembPKey pkey );

	public void reallyDeepDisposeSecTentRoleMemb( ICFSecProtSecTentRoleMembObj obj );

	void deepDisposeSecTentRoleMemb( ICFSecProtSecTentRoleMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentRoleMembObj lockSecTentRoleMemb( ICFSecProtSecTentRoleMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecTentRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecTentRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecTentRoleMembObj> readAllSecTentRoleMemb();

	/**
	 *	Return a sorted map of all the SecTentRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecTentRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecTentRoleMembObj> readAllSecTentRoleMemb( boolean forceRead );

	List<ICFSecProtSecTentRoleMembObj> readCachedAllSecTentRoleMemb();

	/**
	 *	Return a sorted map of a page of the SecTentRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecTentRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecTentRoleMembObj> pageAllSecTentRoleMemb(CFLibDbKeyHash256 priorSecTentRoleId,
		String priorLoginId );

	/**
	 *	Get the ICFSecProtSecTentRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecTentRoleMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecTentRoleMembObj readSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId );

	/**
	 *	Get the ICFSecProtSecTentRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecTentRoleMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecTentRoleMembObj readSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecTentRoleMembObj> instances sorted by their primary keys for the duplicate TentRoleIdx key.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentRoleMembObj> cached instances sorted by their primary keys for the duplicate TentRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentRoleMembObj> readSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId );

	/**
	 *	Get the map of List<ICFSecProtSecTentRoleMembObj> instances sorted by their primary keys for the duplicate TentRoleIdx key.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentRoleMembObj> cached instances sorted by their primary keys for the duplicate TentRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentRoleMembObj> readSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecTentRoleMembObj> instances sorted by their primary keys for the duplicate UserIdx key.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentRoleMembObj> cached instances sorted by their primary keys for the duplicate UserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentRoleMembObj> readSecTentRoleMembByUserIdx( String LoginId );

	/**
	 *	Get the map of List<ICFSecProtSecTentRoleMembObj> instances sorted by their primary keys for the duplicate UserIdx key.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentRoleMembObj> cached instances sorted by their primary keys for the duplicate UserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentRoleMembObj> readSecTentRoleMembByUserIdx( String LoginId,
		boolean forceRead );

	ICFSecProtSecTentRoleMembObj readCachedSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId );

	List<List<ICFSecProtSecTentRoleMembObj>> readCachedSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId );

	List<List<ICFSecProtSecTentRoleMembObj>> readCachedSecTentRoleMembByUserIdx( String LoginId );

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
	List<ICFSecProtSecTentRoleMembObj> pageSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId,
		CFLibDbKeyHash256 priorSecTentRoleId,
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
	List<ICFSecProtSecTentRoleMembObj> pageSecTentRoleMembByUserIdx( String LoginId,
		CFLibDbKeyHash256 priorSecTentRoleId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentRoleMembObj updateSecTentRoleMemb( ICFSecProtSecTentRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecTentRoleMemb( ICFSecProtSecTentRoleMembObj Obj );

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
