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

public interface ICFSecProtSecClusGrpTableObj
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
	 *	Instantiate a new SecClusGrp instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecClusGrpObj newInstance();

	/**
	 *	Instantiate a new SecClusGrp edition of the specified SecClusGrp instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecClusGrpEditObj newEditInstance( ICFSecProtSecClusGrpObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusGrpObj realiseSecClusGrp( ICFSecProtSecClusGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusGrpObj createSecClusGrp( ICFSecProtSecClusGrpObj Obj );

	/**
	 *	Read a SecClusGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusGrpObj readSecClusGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SecClusGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecClusGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecClusGrpObj readSecClusGrp( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtSecClusGrpObj readCachedSecClusGrp( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSecClusGrp( ICFSecProtSecClusGrpObj obj );

	void deepDisposeSecClusGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusGrpObj lockSecClusGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecClusGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusGrpObj> readAllSecClusGrp();

	/**
	 *	Return a sorted map of all the SecClusGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecClusGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecClusGrpObj> readAllSecClusGrp( boolean forceRead );

	List<ICFSecProtSecClusGrpObj> readCachedAllSecClusGrp();

	/**
	 *	Get the ICFSecProtSecClusGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecClusGrpId	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusGrpObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusGrpObj readSecClusGrpByIdIdx( ICFLibKeyHash256 SecClusGrpId );

	/**
	 *	Get the ICFSecProtSecClusGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecClusGrpId	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusGrpObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusGrpObj readSecClusGrpByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecClusGrpObj> instances sorted by their primary keys for the duplicate ClusterIdx key.
	 *
	 *	@param	ClusterId	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusGrpObj> cached instances sorted by their primary keys for the duplicate ClusterIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpObj> readSecClusGrpByClusterIdx( ICFLibKeyHash256 ClusterId );

	/**
	 *	Get the map of List<ICFSecProtSecClusGrpObj> instances sorted by their primary keys for the duplicate ClusterIdx key.
	 *
	 *	@param	ClusterId	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusGrpObj> cached instances sorted by their primary keys for the duplicate ClusterIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpObj> readSecClusGrpByClusterIdx( ICFLibKeyHash256 ClusterId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecClusGrpObj> instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusGrpObj> cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpObj> readSecClusGrpByNameIdx( String Name );

	/**
	 *	Get the map of List<ICFSecProtSecClusGrpObj> instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecClusGrpObj> cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecClusGrpObj> readSecClusGrpByNameIdx( String Name,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtSecClusGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	ClusterId	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusGrpObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusGrpObj readSecClusGrpByUNameIdx(ICFLibKeyHash256 ClusterId,
		String Name );

	/**
	 *	Get the ICFSecProtSecClusGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	ClusterId	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecClusGrpObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecClusGrpObj readSecClusGrpByUNameIdx(ICFLibKeyHash256 ClusterId,
		String Name,
		boolean forceRead );

	ICFSecProtSecClusGrpObj readCachedSecClusGrpByIdIdx( ICFLibKeyHash256 SecClusGrpId );

	List<List<ICFSecProtSecClusGrpObj>> readCachedSecClusGrpByClusterIdx( ICFLibKeyHash256 ClusterId );

	List<List<ICFSecProtSecClusGrpObj>> readCachedSecClusGrpByNameIdx( String Name );

	ICFSecProtSecClusGrpObj readCachedSecClusGrpByUNameIdx( ICFLibKeyHash256 ClusterId,
		String Name );

	void deepDisposeSecClusGrpByIdIdx( ICFLibKeyHash256 SecClusGrpId );

	void deepDisposeSecClusGrpByClusterIdx( ICFLibKeyHash256 ClusterId );

	void deepDisposeSecClusGrpByNameIdx( String Name );

	void deepDisposeSecClusGrpByUNameIdx( ICFLibKeyHash256 ClusterId,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecClusGrpObj updateSecClusGrp( ICFSecProtSecClusGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecClusGrp( ICFSecProtSecClusGrpObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecClusGrpId	The SecClusGrp key attribute of the instance generating the id.
	 */
	void deleteSecClusGrpByIdIdx( ICFLibKeyHash256 SecClusGrpId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ClusterId	The SecClusGrp key attribute of the instance generating the id.
	 */
	void deleteSecClusGrpByClusterIdx( ICFLibKeyHash256 ClusterId );

	/**
	 *	Internal use only.
	 *
	 *	@param	Name	The SecClusGrp key attribute of the instance generating the id.
	 */
	void deleteSecClusGrpByNameIdx( String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ClusterId	The SecClusGrp key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecClusGrp key attribute of the instance generating the id.
	 */
	void deleteSecClusGrpByUNameIdx(ICFLibKeyHash256 ClusterId,
		String Name );
}
