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

public interface ICFSecProtSecSysRoleMembTableObj
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
	 *	Instantiate a new SecSysRoleMemb instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecSysRoleMembObj newInstance();

	/**
	 *	Instantiate a new SecSysRoleMemb edition of the specified SecSysRoleMemb instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecSysRoleMembEditObj newEditInstance( ICFSecProtSecSysRoleMembObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysRoleMembObj realiseSecSysRoleMemb( ICFSecProtSecSysRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysRoleMembObj createSecSysRoleMemb( ICFSecProtSecSysRoleMembObj Obj );

	/**
	 *	Read a SecSysRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysRoleMembObj readSecSysRoleMemb( ICFSecProtSecSysRoleMembPKey pkey );

	/**
	 *	Read a SecSysRoleMemb-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysRoleMembObj readSecSysRoleMemb( ICFSecProtSecSysRoleMembPKey pkey,
		boolean forceRead );

	/**
	 *	Read a SecSysRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecSysRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysRoleMembObj readSecSysRoleMemb( ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Read a SecSysRoleMemb-derived instance by it's primary key.
	 *
	 *	@return	The SecSysRoleMemb-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysRoleMembObj readSecSysRoleMemb( ICFLibKeyHash256 SecSysRoleId,
		String LoginId,
		boolean forceRead );

	ICFSecProtSecSysRoleMembObj readCachedSecSysRoleMemb( ICFSecProtSecSysRoleMembPKey pkey );

	public void reallyDeepDisposeSecSysRoleMemb( ICFSecProtSecSysRoleMembObj obj );

	void deepDisposeSecSysRoleMemb( ICFSecProtSecSysRoleMembPKey pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysRoleMembObj lockSecSysRoleMemb( ICFSecProtSecSysRoleMembPKey pkey );

	/**
	 *	Return a sorted list of all the SecSysRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysRoleMembObj> readAllSecSysRoleMemb();

	/**
	 *	Return a sorted map of all the SecSysRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysRoleMembObj> readAllSecSysRoleMemb( boolean forceRead );

	List<ICFSecProtSecSysRoleMembObj> readCachedAllSecSysRoleMemb();

	/**
	 *	Return a sorted map of a page of the SecSysRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysRoleMembObj> pageAllSecSysRoleMemb(CFLibDbKeyHash256 priorSecSysRoleId,
		String priorLoginId );

	/**
	 *	Get the ICFSecProtSecSysRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysRoleMembObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysRoleMembObj readSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Get the ICFSecProtSecSysRoleMembObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysRoleMembObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysRoleMembObj readSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSysRoleMembObj> instances sorted by their primary keys for the duplicate SysRoleIdx key.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysRoleMembObj> cached instances sorted by their primary keys for the duplicate SysRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysRoleMembObj> readSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Get the map of List<ICFSecProtSecSysRoleMembObj> instances sorted by their primary keys for the duplicate SysRoleIdx key.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysRoleMembObj> cached instances sorted by their primary keys for the duplicate SysRoleIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysRoleMembObj> readSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSysRoleMembObj> instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysRoleMembObj> cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysRoleMembObj> readSecSysRoleMembByLoginIdx( String LoginId );

	/**
	 *	Get the map of List<ICFSecProtSecSysRoleMembObj> instances sorted by their primary keys for the duplicate LoginIdx key.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysRoleMembObj> cached instances sorted by their primary keys for the duplicate LoginIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysRoleMembObj> readSecSysRoleMembByLoginIdx( String LoginId,
		boolean forceRead );

	ICFSecProtSecSysRoleMembObj readCachedSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	List<List<ICFSecProtSecSysRoleMembObj>> readCachedSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId );

	List<List<ICFSecProtSecSysRoleMembObj>> readCachedSecSysRoleMembByLoginIdx( String LoginId );

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
	List<ICFSecProtSecSysRoleMembObj> pageSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		CFLibDbKeyHash256 priorSecSysRoleId,
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
	List<ICFSecProtSecSysRoleMembObj> pageSecSysRoleMembByLoginIdx( String LoginId,
		CFLibDbKeyHash256 priorSecSysRoleId,
		String priorLoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysRoleMembObj updateSecSysRoleMemb( ICFSecProtSecSysRoleMembObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysRoleMemb( ICFSecProtSecSysRoleMembObj Obj );

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
