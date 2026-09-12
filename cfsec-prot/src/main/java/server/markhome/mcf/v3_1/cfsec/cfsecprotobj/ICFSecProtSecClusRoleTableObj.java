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

public interface ICFSecProtSecClusRoleTableObj
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
	 *	Instantiate a new SecClusRole instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecClusRoleObj newInstance();

	/**
	 *	Instantiate a new SecClusRole edition of the specified SecClusRole instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecClusRoleEditObj newEditInstance( ICFSecProtSecClusRoleObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusRoleObj realiseSecClusRole( ICFSecProtSecClusRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusRoleObj createSecClusRole( ICFSecProtSecClusRoleObj Obj );

	/**
	 *	Read a SecClusRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusRoleObj readSecClusRole( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SecClusRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusRoleObj readSecClusRole( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtSecClusRoleObj readCachedSecClusRole( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSecClusRole( ICFSecProtSecClusRoleObj obj );

	void deepDisposeSecClusRole( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusRoleObj lockSecClusRole( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecClusRole-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusRoleObj> readAllSecClusRole();

	/**
	 *	Return a sorted map of all the SecClusRole-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusRoleObj> readAllSecClusRole( boolean forceRead );

	List<ICFSecProtSecClusRoleObj> readCachedAllSecClusRole();

	/**
	 *	Get the ICFSecProtSecClusRoleObj instance for the primary key attributes.
	 *
	 *	@param	SecClusRoleId	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusRoleObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusRoleObj readSecClusRoleByIdIdx( ICFLibKeyHash256 SecClusRoleId );

	/**
	 *	Get the ICFSecProtSecClusRoleObj instance for the primary key attributes.
	 *
	 *	@param	SecClusRoleId	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusRoleObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusRoleObj readSecClusRoleByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecClusRoleObj> instances sorted by their primary keys for the duplicate ClusterIdx key.
	 *
	 *	@param	ClusterId	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusRoleObj> cached instances sorted by their primary keys for the duplicate ClusterIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusRoleObj> readSecClusRoleByClusterIdx( ICFLibKeyHash256 ClusterId );

	/**
	 *	Get the map of List<ICFSecProtSecClusRoleObj> instances sorted by their primary keys for the duplicate ClusterIdx key.
	 *
	 *	@param	ClusterId	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusRoleObj> cached instances sorted by their primary keys for the duplicate ClusterIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusRoleObj> readSecClusRoleByClusterIdx( ICFLibKeyHash256 ClusterId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecClusRoleObj> instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusRoleObj> cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusRoleObj> readSecClusRoleByNameIdx( String Name );

	/**
	 *	Get the map of List<ICFSecProtSecClusRoleObj> instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusRoleObj> cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusRoleObj> readSecClusRoleByNameIdx( String Name,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtSecClusRoleObj instance for the unique UNameIdx key.
	 *
	 *	@param	ClusterId	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusRoleObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusRoleObj readSecClusRoleByUNameIdx(ICFLibKeyHash256 ClusterId,
		String Name );

	/**
	 *	Get the ICFSecProtSecClusRoleObj instance for the unique UNameIdx key.
	 *
	 *	@param	ClusterId	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusRoleObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusRoleObj readSecClusRoleByUNameIdx(ICFLibKeyHash256 ClusterId,
		String Name,
		boolean forceRead );

	ICFSecProtSecClusRoleObj readCachedSecClusRoleByIdIdx( ICFLibKeyHash256 SecClusRoleId );

	List<List<ICFSecProtSecClusRoleObj>> readCachedSecClusRoleByClusterIdx( ICFLibKeyHash256 ClusterId );

	List<List<ICFSecProtSecClusRoleObj>> readCachedSecClusRoleByNameIdx( String Name );

	ICFSecProtSecClusRoleObj readCachedSecClusRoleByUNameIdx( ICFLibKeyHash256 ClusterId,
		String Name );

	void deepDisposeSecClusRoleByIdIdx( ICFLibKeyHash256 SecClusRoleId );

	void deepDisposeSecClusRoleByClusterIdx( ICFLibKeyHash256 ClusterId );

	void deepDisposeSecClusRoleByNameIdx( String Name );

	void deepDisposeSecClusRoleByUNameIdx( ICFLibKeyHash256 ClusterId,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusRoleObj updateSecClusRole( ICFSecProtSecClusRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecClusRole( ICFSecProtSecClusRoleObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecClusRoleId	The SecClusRole key attribute of the instance generating the id.
	 */
	void deleteSecClusRoleByIdIdx( ICFLibKeyHash256 SecClusRoleId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ClusterId	The SecClusRole key attribute of the instance generating the id.
	 */
	void deleteSecClusRoleByClusterIdx( ICFLibKeyHash256 ClusterId );

	/**
	 *	Internal use only.
	 *
	 *	@param	Name	The SecClusRole key attribute of the instance generating the id.
	 */
	void deleteSecClusRoleByNameIdx( String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ClusterId	The SecClusRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecClusRole key attribute of the instance generating the id.
	 */
	void deleteSecClusRoleByUNameIdx(ICFLibKeyHash256 ClusterId,
		String Name );
}
