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

public interface ICFSecProtSecClusGrpMembTableObj
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
	 *	Instantiate a new SecClusGrpMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecClusGrpMembObj newInstance();

	/**
	 *	Instantiate a new SecClusGrpMemb edition of the specified SecClusGrpMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecClusGrpMembEditObj newEditInstance( ICFSecProtSecClusGrpMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusGrpMembObj realiseSecClusGrpMemb( ICFSecProtSecClusGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusGrpMembObj createSecClusGrpMemb( ICFSecProtSecClusGrpMembObj Obj );

	/**
	 *	Read a SecClusGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusGrpMembObj readSecClusGrpMemb( ICFSecProtSecClusGrpMembPKey pkey );

	/**
	 *	Read a SecClusGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusGrpMembObj readSecClusGrpMemb( ICFSecProtSecClusGrpMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecClusGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecClusGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusGrpMembObj readSecClusGrpMemb( ICFLibKeyHash256 SecClusGrpId,
		String LoginId );

	/**
	 *	Read a SecClusGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecClusGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusGrpMembObj readSecClusGrpMemb( ICFLibKeyHash256 SecClusGrpId,
		String LoginId,
		boolean forceRead );

	ICFSecProtSecClusGrpMembObj readCachedSecClusGrpMemb( ICFSecProtSecClusGrpMembPKey pkey );

	public void reallyDeepDisposeSecClusGrpMemb( ICFSecProtSecClusGrpMembObj obj );

	void deepDisposeSecClusGrpMemb( ICFSecProtSecClusGrpMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusGrpMembObj lockSecClusGrpMemb( ICFSecProtSecClusGrpMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecClusGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> readAllSecClusGrpMemb();

	/**
	 *	Return a sorted map of all the SecClusGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> readAllSecClusGrpMemb( boolean forceRead );

	List<ICFSecProtSecClusGrpMembObj> readCachedAllSecClusGrpMemb();

	/**
	 *	Return a sorted map of a page of the SecClusGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> pageAllSecClusGrpMemb(CFLibDbKeyHash256 priorSecClusGrpId,
		String priorLoginId );

	/**
	 *	Get the ICFSecProtSecClusGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusGrpMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusGrpMembObj readSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId );

	/**
	 *	Get the ICFSecProtSecClusGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusGrpMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusGrpMembObj readSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecClusGrpMembObj> instances sorted by their primary keys for the duplicate ClusGrpIdx key.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusGrpMembObj> cached instances sorted by their primary keys for the duplicate ClusGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> readSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId );

	/**
	 *	Get the map of List<ICFSecProtSecClusGrpMembObj> instances sorted by their primary keys for the duplicate ClusGrpIdx key.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusGrpMembObj> cached instances sorted by their primary keys for the duplicate ClusGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> readSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecClusGrpMembObj> instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusGrpMembObj> cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> readSecClusGrpMembByLoginIdx( String LoginId );

	/**
	 *	Get the map of List<ICFSecProtSecClusGrpMembObj> instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusGrpMembObj> cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> readSecClusGrpMembByLoginIdx( String LoginId,
		boolean forceRead );

	ICFSecProtSecClusGrpMembObj readCachedSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId );

	List<List<ICFSecProtSecClusGrpMembObj>> readCachedSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId );

	List<List<ICFSecProtSecClusGrpMembObj>> readCachedSecClusGrpMembByLoginIdx( String LoginId );

	void deepDisposeSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId );

	void deepDisposeSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId );

	void deepDisposeSecClusGrpMembByLoginIdx( String LoginId );

	/**
	 *	Read a page of data as a List of SecClusGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ClusGrpIdx key attributes.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> pageSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId,
		CFLibDbKeyHash256 priorSecClusGrpId,
		String priorLoginId );

	/**
	 *	Read a page of data as a List of SecClusGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpMembObj> pageSecClusGrpMembByLoginIdx( String LoginId,
		CFLibDbKeyHash256 priorSecClusGrpId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusGrpMembObj updateSecClusGrpMemb( ICFSecProtSecClusGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecClusGrpMemb( ICFSecProtSecClusGrpMembObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId );

	/**
	 *	Internal use only.
	 *
	 *	@param	LoginId	The SecClusGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecClusGrpMembByLoginIdx( String LoginId );
}
