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

public interface ICFSecProtTenantTableObj
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
	 *	Instantiate a new Tenant instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtTenantObj newInstance();

	/**
	 *	Instantiate a new Tenant edition of the specified Tenant instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtTenantEditObj newEditInstance( ICFSecProtTenantObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtTenantObj realiseTenant( ICFSecProtTenantObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtTenantObj createTenant( ICFSecProtTenantObj Obj );

	/**
	 *	Read a Tenant-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Tenant-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtTenantObj readTenant( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Tenant-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Tenant-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtTenantObj readTenant( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtTenantObj readCachedTenant( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeTenant( ICFSecProtTenantObj obj );

	void deepDisposeTenant( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtTenantObj lockTenant( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Tenant-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtTenantObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtTenantObj> readAllTenant();

	/**
	 *	Return a sorted map of all the Tenant-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtTenantObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtTenantObj> readAllTenant( boolean forceRead );

	List<ICFSecProtTenantObj> readCachedAllTenant();

	/**
	 *	Return a sorted map of a page of the Tenant-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtTenantObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtTenantObj> pageAllTenant(CFLibDbKeyHash256 priorId );

	/**
	 *	Get the ICFSecProtTenantObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTenantObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTenantObj readTenantByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the ICFSecProtTenantObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTenantObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTenantObj readTenantByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtTenantObj> instances sorted by their primary keys for the duplicate ClusterIdx key.
	 *
	 *	@param	ClusterId	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtTenantObj> cached instances sorted by their primary keys for the duplicate ClusterIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtTenantObj> readTenantByClusterIdx( ICFLibKeyHash256 ClusterId );

	/**
	 *	Get the map of List<ICFSecProtTenantObj> instances sorted by their primary keys for the duplicate ClusterIdx key.
	 *
	 *	@param	ClusterId	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtTenantObj> cached instances sorted by their primary keys for the duplicate ClusterIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtTenantObj> readTenantByClusterIdx( ICFLibKeyHash256 ClusterId,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtTenantObj instance for the unique UNameIdx key.
	 *
	 *	@param	ClusterId	The Tenant key attribute of the instance generating the id.
	 *
	 *	@param	TenantName	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTenantObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTenantObj readTenantByUNameIdx(ICFLibKeyHash256 ClusterId,
		String TenantName );

	/**
	 *	Get the ICFSecProtTenantObj instance for the unique UNameIdx key.
	 *
	 *	@param	ClusterId	The Tenant key attribute of the instance generating the id.
	 *
	 *	@param	TenantName	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTenantObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTenantObj readTenantByUNameIdx(ICFLibKeyHash256 ClusterId,
		String TenantName,
		boolean forceRead );

	ICFSecProtTenantObj readCachedTenantByIdIdx( ICFLibKeyHash256 Id );

	List<List<ICFSecProtTenantObj>> readCachedTenantByClusterIdx( ICFLibKeyHash256 ClusterId );

	ICFSecProtTenantObj readCachedTenantByUNameIdx( ICFLibKeyHash256 ClusterId,
		String TenantName );

	void deepDisposeTenantByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeTenantByClusterIdx( ICFLibKeyHash256 ClusterId );

	void deepDisposeTenantByUNameIdx( ICFLibKeyHash256 ClusterId,
		String TenantName );

	/**
	 *	Read a page of data as a List of Tenant-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ClusterIdx key attributes.
	 *
	 *	@param	ClusterId	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return	A List of Tenant-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtTenantObj> pageTenantByClusterIdx( ICFLibKeyHash256 ClusterId,
		CFLibDbKeyHash256 priorId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtTenantObj updateTenant( ICFSecProtTenantObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTenant( ICFSecProtTenantObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Tenant key attribute of the instance generating the id.
	 */
	void deleteTenantByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ClusterId	The Tenant key attribute of the instance generating the id.
	 */
	void deleteTenantByClusterIdx( ICFLibKeyHash256 ClusterId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ClusterId	The Tenant key attribute of the instance generating the id.
	 *
	 *	@param	TenantName	The Tenant key attribute of the instance generating the id.
	 */
	void deleteTenantByUNameIdx(ICFLibKeyHash256 ClusterId,
		String TenantName );
}
