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

public interface ICFSecProtSecSysGrpTableObj
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
	 *	Instantiate a new SecSysGrp instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecSysGrpObj newInstance();

	/**
	 *	Instantiate a new SecSysGrp edition of the specified SecSysGrp instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecSysGrpEditObj newEditInstance( ICFSecProtSecSysGrpObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysGrpObj realiseSecSysGrp( ICFSecProtSecSysGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysGrpObj createSecSysGrp( ICFSecProtSecSysGrpObj Obj );

	/**
	 *	Read a SecSysGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysGrpObj readSecSysGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SecSysGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSysGrpObj readSecSysGrp( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtSecSysGrpObj readCachedSecSysGrp( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSecSysGrp( ICFSecProtSecSysGrpObj obj );

	void deepDisposeSecSysGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysGrpObj lockSecSysGrp( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecSysGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysGrpObj> readAllSecSysGrp();

	/**
	 *	Return a sorted map of all the SecSysGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSysGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSysGrpObj> readAllSecSysGrp( boolean forceRead );

	List<ICFSecProtSecSysGrpObj> readCachedAllSecSysGrp();

	/**
	 *	Get the ICFSecProtSecSysGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysGrpObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysGrpObj readSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId );

	/**
	 *	Get the ICFSecProtSecSysGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysGrpObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysGrpObj readSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtSecSysGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	Name	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysGrpObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysGrpObj readSecSysGrpByUNameIdx(String Name );

	/**
	 *	Get the ICFSecProtSecSysGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	Name	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSysGrpObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSysGrpObj readSecSysGrpByUNameIdx(String Name,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSysGrpObj> instances sorted by their primary keys for the duplicate SecLevelIdx key.
	 *
	 *	@param	SecLevel	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysGrpObj> cached instances sorted by their primary keys for the duplicate SecLevelIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysGrpObj> readSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel );

	/**
	 *	Get the map of List<ICFSecProtSecSysGrpObj> instances sorted by their primary keys for the duplicate SecLevelIdx key.
	 *
	 *	@param	SecLevel	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSysGrpObj> cached instances sorted by their primary keys for the duplicate SecLevelIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSysGrpObj> readSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel,
		boolean forceRead );

	ICFSecProtSecSysGrpObj readCachedSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId );

	ICFSecProtSecSysGrpObj readCachedSecSysGrpByUNameIdx( String Name );

	List<List<ICFSecProtSecSysGrpObj>> readCachedSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel );

	void deepDisposeSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId );

	void deepDisposeSecSysGrpByUNameIdx( String Name );

	void deepDisposeSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSysGrpObj updateSecSysGrp( ICFSecProtSecSysGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysGrp( ICFSecProtSecSysGrpObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysGrpId	The SecSysGrp key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId );

	/**
	 *	Internal use only.
	 *
	 *	@param	Name	The SecSysGrp key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpByUNameIdx(String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecLevel	The SecSysGrp key attribute of the instance generating the id.
	 */
	void deleteSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel );
}
