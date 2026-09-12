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

public interface ICFSecSecSysGrpMembTableObj
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
	 *	Instantiate a new SecSysGrpMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecSysGrpMembObj newInstance();

	/**
	 *	Instantiate a new SecSysGrpMemb edition of the specified SecSysGrpMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecSysGrpMembEditObj newEditInstance( ICFSecSecSysGrpMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpMembObj realiseSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpMembObj createSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj );

	/**
	 *	Read a SecSysGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey );

	/**
	 *	Read a SecSysGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecSysGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecSysGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFLibKeyHash256 SecSysGrpId,
		String LoginId );

	/**
	 *	Read a SecSysGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecSysGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFLibKeyHash256 SecSysGrpId,
		String LoginId,
		boolean forceRead );

	ICFSecSecSysGrpMembObj readCachedSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey );

	public void reallyDeepDisposeSecSysGrpMemb( ICFSecSecSysGrpMembObj obj );

	void deepDisposeSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpMembObj lockSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecSysGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> readAllSecSysGrpMemb();

	/**
	 *	Return a sorted map of all the SecSysGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> readAllSecSysGrpMemb( boolean forceRead );

	List<ICFSecSecSysGrpMembObj> readCachedAllSecSysGrpMemb();

	/**
	 *	Return a sorted map of a page of the SecSysGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> pageAllSecSysGrpMemb(ICFLibKeyHash256 priorSecSysGrpId,
		String priorLoginId );

	/**
	 *	Get the CFSecSecSysGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysGrpMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysGrpMembObj readSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId );

	/**
	 *	Get the CFSecSecSysGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysGrpMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysGrpMembObj readSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysGrpMembObj instances sorted by their primary keys for the duplicate SysGrpIdx key.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpMembObj cached instances sorted by their primary keys for the duplicate SysGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> readSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	/**
	 *	Get the map of CFSecSecSysGrpMembObj instances sorted by their primary keys for the duplicate SysGrpIdx key.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpMembObj cached instances sorted by their primary keys for the duplicate SysGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> readSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysGrpMembObj instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpMembObj cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> readSecSysGrpMembByLoginIdx( String LoginId );

	/**
	 *	Get the map of CFSecSecSysGrpMembObj instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpMembObj cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> readSecSysGrpMembByLoginIdx( String LoginId,
		boolean forceRead );

	ICFSecSecSysGrpMembObj readCachedSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId );

	List<ICFSecSecSysGrpMembObj> readCachedSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	List<ICFSecSecSysGrpMembObj> readCachedSecSysGrpMembByLoginIdx( String LoginId );

	void deepDisposeSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId );

	void deepDisposeSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	void deepDisposeSecSysGrpMembByLoginIdx( String LoginId );

	/**
	 *	Read a page of data as a List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysGrpIdx key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> pageSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		ICFLibKeyHash256 priorSecSysGrpId,
		String priorLoginId );

	/**
	 *	Read a page of data as a List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecSysGrpMembObj> pageSecSysGrpMembByLoginIdx( String LoginId,
		ICFLibKeyHash256 priorSecSysGrpId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpMembObj updateSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	/**
	 *	Internal use only.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpMembByLoginIdx( String LoginId );
}
