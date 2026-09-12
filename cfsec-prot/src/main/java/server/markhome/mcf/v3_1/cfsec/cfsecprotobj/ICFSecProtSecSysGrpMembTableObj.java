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

public interface ICFSecProtSecSysGrpMembTableObj
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
	 *	Instantiate a new SecSysGrpMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecSysGrpMembObj newInstance();

	/**
	 *	Instantiate a new SecSysGrpMemb edition of the specified SecSysGrpMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecSysGrpMembEditObj newEditInstance( ICFSecProtSecSysGrpMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysGrpMembObj realiseSecSysGrpMemb( ICFSecProtSecSysGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysGrpMembObj createSecSysGrpMemb( ICFSecProtSecSysGrpMembObj Obj );

	/**
	 *	Read a SecSysGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysGrpMembObj readSecSysGrpMemb( ICFSecProtSecSysGrpMembPKey pkey );

	/**
	 *	Read a SecSysGrpMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysGrpMembObj readSecSysGrpMemb( ICFSecProtSecSysGrpMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecSysGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecSysGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysGrpMembObj readSecSysGrpMemb( ICFLibKeyHash256 SecSysGrpId,
		String LoginId );

	/**
	 *	Read a SecSysGrpMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecSysGrpMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysGrpMembObj readSecSysGrpMemb( ICFLibKeyHash256 SecSysGrpId,
		String LoginId,
		boolean forceRead );

	ICFSecProtSecSysGrpMembObj readCachedSecSysGrpMemb( ICFSecProtSecSysGrpMembPKey pkey );

	public void reallyDeepDisposeSecSysGrpMemb( ICFSecProtSecSysGrpMembObj obj );

	void deepDisposeSecSysGrpMemb( ICFSecProtSecSysGrpMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysGrpMembObj lockSecSysGrpMemb( ICFSecProtSecSysGrpMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecSysGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysGrpMembObj> readAllSecSysGrpMemb();

	/**
	 *	Return a sorted map of all the SecSysGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysGrpMembObj> readAllSecSysGrpMemb( boolean forceRead );

	List<ICFSecProtSecSysGrpMembObj> readCachedAllSecSysGrpMemb();

	/**
	 *	Return a sorted map of a page of the SecSysGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysGrpMembObj> pageAllSecSysGrpMemb(CFLibDbKeyHash256 priorSecSysGrpId,
		String priorLoginId );

	/**
	 *	Get the ICFSecProtSecSysGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysGrpMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysGrpMembObj readSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId );

	/**
	 *	Get the ICFSecProtSecSysGrpMembObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysGrpMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysGrpMembObj readSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSysGrpMembObj> instances sorted by their primary keys for the duplicate SysGrpIdx key.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysGrpMembObj> cached instances sorted by their primary keys for the duplicate SysGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysGrpMembObj> readSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	/**
	 *	Get the map of List<ICFSecProtSecSysGrpMembObj> instances sorted by their primary keys for the duplicate SysGrpIdx key.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysGrpMembObj> cached instances sorted by their primary keys for the duplicate SysGrpIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysGrpMembObj> readSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSysGrpMembObj> instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysGrpMembObj> cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysGrpMembObj> readSecSysGrpMembByLoginIdx( String LoginId );

	/**
	 *	Get the map of List<ICFSecProtSecSysGrpMembObj> instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysGrpMembObj> cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysGrpMembObj> readSecSysGrpMembByLoginIdx( String LoginId,
		boolean forceRead );

	ICFSecProtSecSysGrpMembObj readCachedSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId );

	List<List<ICFSecProtSecSysGrpMembObj>> readCachedSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId );

	List<List<ICFSecProtSecSysGrpMembObj>> readCachedSecSysGrpMembByLoginIdx( String LoginId );

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
	List<ICFSecProtSecSysGrpMembObj> pageSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		CFLibDbKeyHash256 priorSecSysGrpId,
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
	List<ICFSecProtSecSysGrpMembObj> pageSecSysGrpMembByLoginIdx( String LoginId,
		CFLibDbKeyHash256 priorSecSysGrpId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysGrpMembObj updateSecSysGrpMemb( ICFSecProtSecSysGrpMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysGrpMemb( ICFSecProtSecSysGrpMembObj Obj );

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
