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

public interface ICFSecProtSecTentGrpMembTableObj
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
	 *	Instantiate a new SecTentGrpMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecTentGrpMembObj newInstance();

	/**
	 *	Instantiate a new SecTentGrpMemb edition of the specified SecTentGrpMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecTentGrpMembEditObj newEditInstance( ICFSecProtSecTentGrpMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentGrpMembObj realiseSecTentGrpMemb( ICFSecProtSecTentGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentGrpMembObj createSecTentGrpMemb( ICFSecProtSecTentGrpMembObj Obj );

	/**
	 *	Read a SecTentGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentGrpMembObj readSecTentGrpMemb( ICFSecProtSecTentGrpMembPKey pkey );

	/**
	 *	Read a SecTentGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentGrpMembObj readSecTentGrpMemb( ICFSecProtSecTentGrpMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecTentGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecTentGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentGrpMembObj readSecTentGrpMemb( ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Read a SecTentGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecTentGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentGrpMembObj readSecTentGrpMemb( ICFLibKeyHash256 SecTentGrpId,
		String LoginId,
		boolean forceRead );

	ICFSecProtSecTentGrpMembObj readCachedSecTentGrpMemb( ICFSecProtSecTentGrpMembPKey pkey );

	public void reallyDeepDisposeSecTentGrpMemb( ICFSecProtSecTentGrpMembObj obj );

	void deepDisposeSecTentGrpMemb( ICFSecProtSecTentGrpMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentGrpMembObj lockSecTentGrpMemb( ICFSecProtSecTentGrpMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecTentGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecTentGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> readAllSecTentGrpMemb();

	/**
	 *	Return a sorted map of all the SecTentGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecTentGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> readAllSecTentGrpMemb( boolean forceRead );

	List<ICFSecProtSecTentGrpMembObj> readCachedAllSecTentGrpMemb();

	/**
	 *	Return a sorted map of a page of the SecTentGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecTentGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> pageAllSecTentGrpMemb(CFLibDbKeyHash256 priorSecTentGrpId,
		String priorLoginId );

	/**
	 *	Get the ICFSecProtSecTentGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecTentGrpMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecTentGrpMembObj readSecTentGrpMembByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Get the ICFSecProtSecTentGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecTentGrpMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecTentGrpMembObj readSecTentGrpMembByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecTentGrpMembObj> instances sorted by their primary keys for the duplicate TentGrpIdx key.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentGrpMembObj> cached instances sorted by their primary keys for the duplicate TentGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> readSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId );

	/**
	 *	Get the map of List<ICFSecProtSecTentGrpMembObj> instances sorted by their primary keys for the duplicate TentGrpIdx key.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentGrpMembObj> cached instances sorted by their primary keys for the duplicate TentGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> readSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecTentGrpMembObj> instances sorted by their primary keys for the duplicate UserIdx key.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentGrpMembObj> cached instances sorted by their primary keys for the duplicate UserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> readSecTentGrpMembByUserIdx( String LoginId );

	/**
	 *	Get the map of List<ICFSecProtSecTentGrpMembObj> instances sorted by their primary keys for the duplicate UserIdx key.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentGrpMembObj> cached instances sorted by their primary keys for the duplicate UserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> readSecTentGrpMembByUserIdx( String LoginId,
		boolean forceRead );

	ICFSecProtSecTentGrpMembObj readCachedSecTentGrpMembByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	List<List<ICFSecProtSecTentGrpMembObj>> readCachedSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId );

	List<List<ICFSecProtSecTentGrpMembObj>> readCachedSecTentGrpMembByUserIdx( String LoginId );

	void deepDisposeSecTentGrpMembByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	void deepDisposeSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId );

	void deepDisposeSecTentGrpMembByUserIdx( String LoginId );

	/**
	 *	Read a page of data as a List of SecTentGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate TentGrpIdx key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> pageSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 priorSecTentGrpId,
		String priorLoginId );

	/**
	 *	Read a page of data as a List of SecTentGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate UserIdx key attributes.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpMembObj> pageSecTentGrpMembByUserIdx( String LoginId,
		CFLibDbKeyHash256 priorSecTentGrpId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentGrpMembObj updateSecTentGrpMemb( ICFSecProtSecTentGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecTentGrpMemb( ICFSecProtSecTentGrpMembObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpMembByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId );

	/**
	 *	Internal use only.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpMembByUserIdx( String LoginId );
}
