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

public interface ICFSecSecTentGrpMembTableObj
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
	 *	Instantiate a new SecTentGrpMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecTentGrpMembObj newInstance();

	/**
	 *	Instantiate a new SecTentGrpMemb edition of the specified SecTentGrpMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecTentGrpMembEditObj newEditInstance( ICFSecSecTentGrpMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentGrpMembObj realiseSecTentGrpMemb( ICFSecSecTentGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentGrpMembObj createSecTentGrpMemb( ICFSecSecTentGrpMembObj Obj );

	/**
	 *	Read a SecTentGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentGrpMembObj readSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey );

	/**
	 *	Read a SecTentGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentGrpMembObj readSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecTentGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecTentGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentGrpMembObj readSecTentGrpMemb( ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Read a SecTentGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecTentGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentGrpMembObj readSecTentGrpMemb( ICFLibKeyHash256 SecTentGrpId,
		String LoginId,
		boolean forceRead );

	ICFSecSecTentGrpMembObj readCachedSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey );

	public void reallyDeepDisposeSecTentGrpMemb( ICFSecSecTentGrpMembObj obj );

	void deepDisposeSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentGrpMembObj lockSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecTentGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentGrpMembObj> readAllSecTentGrpMemb();

	/**
	 *	Return a sorted map of all the SecTentGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentGrpMembObj> readAllSecTentGrpMemb( boolean forceRead );

	List<ICFSecSecTentGrpMembObj> readCachedAllSecTentGrpMemb();

	/**
	 *	Return a sorted map of a page of the SecTentGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentGrpMembObj> pageAllSecTentGrpMemb(ICFLibKeyHash256 priorSecTentGrpId,
		String priorLoginId );

	/**
	 *	Get the CFSecSecTentGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentGrpMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentGrpMembObj readSecTentGrpMembByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Get the CFSecSecTentGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentGrpMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentGrpMembObj readSecTentGrpMembByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecTentGrpMembObj instances sorted by their primary keys for the duplicate TentGrpIdx key.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentGrpMembObj cached instances sorted by their primary keys for the duplicate TentGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentGrpMembObj> readSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId );

	/**
	 *	Get the map of CFSecSecTentGrpMembObj instances sorted by their primary keys for the duplicate TentGrpIdx key.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentGrpMembObj cached instances sorted by their primary keys for the duplicate TentGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentGrpMembObj> readSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecTentGrpMembObj instances sorted by their primary keys for the duplicate UserIdx key.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentGrpMembObj cached instances sorted by their primary keys for the duplicate UserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentGrpMembObj> readSecTentGrpMembByUserIdx( String LoginId );

	/**
	 *	Get the map of CFSecSecTentGrpMembObj instances sorted by their primary keys for the duplicate UserIdx key.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentGrpMembObj cached instances sorted by their primary keys for the duplicate UserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentGrpMembObj> readSecTentGrpMembByUserIdx( String LoginId,
		boolean forceRead );

	ICFSecSecTentGrpMembObj readCachedSecTentGrpMembByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	List<ICFSecSecTentGrpMembObj> readCachedSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId );

	List<ICFSecSecTentGrpMembObj> readCachedSecTentGrpMembByUserIdx( String LoginId );

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
	List<ICFSecSecTentGrpMembObj> pageSecTentGrpMembByTentGrpIdx( ICFLibKeyHash256 SecTentGrpId,
		ICFLibKeyHash256 priorSecTentGrpId,
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
	List<ICFSecSecTentGrpMembObj> pageSecTentGrpMembByUserIdx( String LoginId,
		ICFLibKeyHash256 priorSecTentGrpId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentGrpMembObj updateSecTentGrpMemb( ICFSecSecTentGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecTentGrpMemb( ICFSecSecTentGrpMembObj Obj );

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
