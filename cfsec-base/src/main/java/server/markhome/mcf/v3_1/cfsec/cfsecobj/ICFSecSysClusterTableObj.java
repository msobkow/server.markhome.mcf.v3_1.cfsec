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

public interface ICFSecSysClusterTableObj
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
	 *	Instantiate a new SysCluster instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSysClusterObj newInstance();

	/**
	 *	Instantiate a new SysCluster edition of the specified SysCluster instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSysClusterEditObj newEditInstance( ICFSecSysClusterObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSysClusterObj realiseSysCluster( ICFSecSysClusterObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSysClusterObj createSysCluster( ICFSecSysClusterObj Obj );

	/**
	 *	Read a SysCluster-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SysCluster-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSysClusterObj readSysCluster( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a SysCluster-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SysCluster-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSysClusterObj readSysCluster( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFSecSysClusterObj readCachedSysCluster( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeSysCluster( ICFSecSysClusterObj obj );

	void deepDisposeSysCluster( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSysClusterObj lockSysCluster( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the SysCluster-derived instances in the database.
	 *
	 *	@return	List of ICFSecSysClusterObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSysClusterObj> readAllSysCluster();

	/**
	 *	Return a sorted map of all the SysCluster-derived instances in the database.
	 *
	 *	@return	List of ICFSecSysClusterObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSysClusterObj> readAllSysCluster( boolean forceRead );

	List<ICFSecSysClusterObj> readCachedAllSysCluster();

	/**
	 *	Get the CFSecSysClusterObj instance for the primary key attributes.
	 *
	 *	@param	SingletonId	The SysCluster key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSysClusterObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSysClusterObj readSysClusterByIdIdx( int SingletonId );

	/**
	 *	Get the CFSecSysClusterObj instance for the primary key attributes.
	 *
	 *	@param	SingletonId	The SysCluster key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSysClusterObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSysClusterObj readSysClusterByIdIdx( int SingletonId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSysClusterObj instances sorted by their primary keys for the duplicate ClusterIdx key.
	 *
	 *	@param	ClusterId	The SysCluster key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSysClusterObj cached instances sorted by their primary keys for the duplicate ClusterIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSysClusterObj> readSysClusterByClusterIdx( ICFLibKeyHash256 ClusterId );

	/**
	 *	Get the map of CFSecSysClusterObj instances sorted by their primary keys for the duplicate ClusterIdx key.
	 *
	 *	@param	ClusterId	The SysCluster key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSysClusterObj cached instances sorted by their primary keys for the duplicate ClusterIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSysClusterObj> readSysClusterByClusterIdx( ICFLibKeyHash256 ClusterId,
		boolean forceRead );

	ICFSecSysClusterObj readCachedSysClusterByIdIdx( int SingletonId );

	List<ICFSecSysClusterObj> readCachedSysClusterByClusterIdx( ICFLibKeyHash256 ClusterId );

	void deepDisposeSysClusterByIdIdx( int SingletonId );

	void deepDisposeSysClusterByClusterIdx( ICFLibKeyHash256 ClusterId );

	/**
	 *	Internal use only.
	 */
	ICFSecSysClusterObj updateSysCluster( ICFSecSysClusterObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSysCluster( ICFSecSysClusterObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SingletonId	The SysCluster key attribute of the instance generating the id.
	 */
	void deleteSysClusterByIdIdx( int SingletonId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ClusterId	The SysCluster key attribute of the instance generating the id.
	 */
	void deleteSysClusterByClusterIdx( ICFLibKeyHash256 ClusterId );
}
