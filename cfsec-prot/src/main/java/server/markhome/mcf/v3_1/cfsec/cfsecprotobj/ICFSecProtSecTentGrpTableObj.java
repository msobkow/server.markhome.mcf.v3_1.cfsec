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

public interface ICFSecProtSecTentGrpTableObj
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
	 *	Instantiate a new SecTentGrp instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecTentGrpObj newInstance();

	/**
	 *	Instantiate a new SecTentGrp edition of the specified SecTentGrp instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecTentGrpEditObj newEditInstance( ICFSecProtSecTentGrpObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentGrpObj realiseSecTentGrp( ICFSecProtSecTentGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentGrpObj createSecTentGrp( ICFSecProtSecTentGrpObj Obj );

	/**
	 *	Read a SecTentGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentGrpObj readSecTentGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SecTentGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecTentGrpObj readSecTentGrp( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtSecTentGrpObj readCachedSecTentGrp( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSecTentGrp( ICFSecProtSecTentGrpObj obj );

	void deepDisposeSecTentGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentGrpObj lockSecTentGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecTentGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecTentGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecTentGrpObj> readAllSecTentGrp();

	/**
	 *	Return a sorted map of all the SecTentGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecTentGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecTentGrpObj> readAllSecTentGrp( boolean forceRead );

	List<ICFSecProtSecTentGrpObj> readCachedAllSecTentGrp();

	/**
	 *	Get the ICFSecProtSecTentGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecTentGrpObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecTentGrpObj readSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId );

	/**
	 *	Get the ICFSecProtSecTentGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecTentGrpObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecTentGrpObj readSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecTentGrpObj> instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentGrpObj> cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpObj> readSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId );

	/**
	 *	Get the map of List<ICFSecProtSecTentGrpObj> instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentGrpObj> cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpObj> readSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecTentGrpObj> instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentGrpObj> cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpObj> readSecTentGrpByNameIdx( String Name );

	/**
	 *	Get the map of List<ICFSecProtSecTentGrpObj> instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecTentGrpObj> cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecTentGrpObj> readSecTentGrpByNameIdx( String Name,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtSecTentGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecTentGrpObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecTentGrpObj readSecTentGrpByUNameIdx(ICFLibKeyHash256 TenantId,
		String Name );

	/**
	 *	Get the ICFSecProtSecTentGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecTentGrpObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecTentGrpObj readSecTentGrpByUNameIdx(ICFLibKeyHash256 TenantId,
		String Name,
		boolean forceRead );

	ICFSecProtSecTentGrpObj readCachedSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId );

	List<List<ICFSecProtSecTentGrpObj>> readCachedSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId );

	List<List<ICFSecProtSecTentGrpObj>> readCachedSecTentGrpByNameIdx( String Name );

	ICFSecProtSecTentGrpObj readCachedSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name );

	void deepDisposeSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId );

	void deepDisposeSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId );

	void deepDisposeSecTentGrpByNameIdx( String Name );

	void deepDisposeSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecTentGrpObj updateSecTentGrp( ICFSecProtSecTentGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecTentGrp( ICFSecProtSecTentGrpObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecTentGrpId	The SecTentGrp key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpByNameIdx( String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpByUNameIdx(ICFLibKeyHash256 TenantId,
		String Name );
}
