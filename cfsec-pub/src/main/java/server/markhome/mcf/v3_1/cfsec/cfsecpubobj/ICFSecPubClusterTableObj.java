// Description: Java 25 Public Table Object interface for CFSecPub.

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

package server.markhome.mcf.v3_1.cfsec.cfsecpubobj;

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

public interface ICFSecPubClusterTableObj
{
	public ICFSecPubSchemaObj getSchema();
	public void setSchema( ICFSecPubSchemaObj value );

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
	 *	Instantiate a new Cluster instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecPubClusterObj newInstance();

	/**
	 *	Instantiate a new Cluster edition of the specified Cluster instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecPubClusterEditObj newEditInstance( ICFSecPubClusterObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecPubClusterObj realiseCluster( ICFSecPubClusterObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecPubClusterObj createCluster( ICFSecPubClusterObj Obj );

	/**
	 *	Read a Cluster-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Cluster-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecPubClusterObj readCluster( ICFLibKeyHash256 pkey );

	/**
	 *	Read a Cluster-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Cluster-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecPubClusterObj readCluster( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFSecPubClusterObj readCachedCluster( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeCluster( ICFSecPubClusterObj obj );

	void deepDisposeCluster( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecPubClusterObj lockCluster( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Cluster-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubClusterObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubClusterObj> readAllCluster();

	/**
	 *	Return a sorted map of all the Cluster-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubClusterObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubClusterObj> readAllCluster( boolean forceRead );

	List<ICFSecPubClusterObj> readCachedAllCluster();

	/**
	 *	Return a sorted map of a page of the Cluster-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubClusterObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubClusterObj> pageAllCluster(CFLibDbKeyHash256 priorId );

	/**
	 *	Get the CFSecPubClusterObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Cluster key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubClusterObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubClusterObj readClusterByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFSecPubClusterObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Cluster key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubClusterObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubClusterObj readClusterByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFSecPubClusterObj instance for the unique UDomNameIdx key.
	 *
	 *	@param	FullDomName	The Cluster key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubClusterObj cached instance for the unique UDomNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubClusterObj readClusterByUDomNameIdx(String FullDomName );

	/**
	 *	Get the CFSecPubClusterObj instance for the unique UDomNameIdx key.
	 *
	 *	@param	FullDomName	The Cluster key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubClusterObj refreshed instance for the unique UDomNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubClusterObj readClusterByUDomNameIdx(String FullDomName,
		boolean forceRead );

	/**
	 *	Get the CFSecPubClusterObj instance for the unique UDescrIdx key.
	 *
	 *	@param	Description	The Cluster key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubClusterObj cached instance for the unique UDescrIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubClusterObj readClusterByUDescrIdx(String Description );

	/**
	 *	Get the CFSecPubClusterObj instance for the unique UDescrIdx key.
	 *
	 *	@param	Description	The Cluster key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubClusterObj refreshed instance for the unique UDescrIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubClusterObj readClusterByUDescrIdx(String Description,
		boolean forceRead );

	ICFSecPubClusterObj readCachedClusterByIdIdx( CFLibDbKeyHash256 Id );

	ICFSecPubClusterObj readCachedClusterByUDomNameIdx( String FullDomName );

	ICFSecPubClusterObj readCachedClusterByUDescrIdx( String Description );

	void deepDisposeClusterByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeClusterByUDomNameIdx( String FullDomName );

	void deepDisposeClusterByUDescrIdx( String Description );

	/**
	 *	Internal use only.
	 */
	ICFSecPubClusterObj updateCluster( ICFSecPubClusterObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteCluster( ICFSecPubClusterObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Cluster key attribute of the instance generating the id.
	 */
	void deleteClusterByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	FullDomName	The Cluster key attribute of the instance generating the id.
	 */
	void deleteClusterByUDomNameIdx(String FullDomName );

	/**
	 *	Internal use only.
	 *
	 *	@param	Description	The Cluster key attribute of the instance generating the id.
	 */
	void deleteClusterByUDescrIdx(String Description );
}
