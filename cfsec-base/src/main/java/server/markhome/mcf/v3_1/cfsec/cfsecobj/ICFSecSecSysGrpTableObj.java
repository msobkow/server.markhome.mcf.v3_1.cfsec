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

public interface ICFSecSecSysGrpTableObj
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
	 *	Instantiate a new SecSysGrp instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecSysGrpObj newInstance();

	/**
	 *	Instantiate a new SecSysGrp edition of the specified SecSysGrp instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecSysGrpEditObj newEditInstance( ICFSecSecSysGrpObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpObj realiseSecSysGrp( ICFSecSecSysGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpObj createSecSysGrp( ICFSecSecSysGrpObj Obj );

	/**
	 *	Read a SecSysGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpObj readSecSysGrp( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a SecSysGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysGrpObj readSecSysGrp( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFSecSecSysGrpObj readCachedSecSysGrp( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeSecSysGrp( ICFSecSecSysGrpObj obj );

	void deepDisposeSecSysGrp( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpObj lockSecSysGrp( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the SecSysGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysGrpObj> readAllSecSysGrp();

	/**
	 *	Return a sorted map of all the SecSysGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysGrpObj> readAllSecSysGrp( boolean forceRead );

	List<ICFSecSecSysGrpObj> readCachedAllSecSysGrp();

	/**
	 *	Get the CFSecSecSysGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysGrpObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysGrpObj readSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId );

	/**
	 *	Get the CFSecSecSysGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysGrpObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysGrpObj readSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		boolean forceRead );

	/**
	 *	Get the CFSecSecSysGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	Name	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysGrpObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysGrpObj readSecSysGrpByUNameIdx(String Name );

	/**
	 *	Get the CFSecSecSysGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	Name	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysGrpObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysGrpObj readSecSysGrpByUNameIdx(String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecSysGrpObj instances sorted by their primary keys for the duplicate SecLevelIdx key.
	 *
	 *	@param	SecLevel	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpObj cached instances sorted by their primary keys for the duplicate SecLevelIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpObj> readSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel );

	/**
	 *	Get the map of CFSecSecSysGrpObj instances sorted by their primary keys for the duplicate SecLevelIdx key.
	 *
	 *	@param	SecLevel	The SecSysGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecSysGrpObj cached instances sorted by their primary keys for the duplicate SecLevelIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecSysGrpObj> readSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel,
		boolean forceRead );

	ICFSecSecSysGrpObj readCachedSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId );

	ICFSecSecSysGrpObj readCachedSecSysGrpByUNameIdx( String Name );

	List<ICFSecSecSysGrpObj> readCachedSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel );

	void deepDisposeSecSysGrpByIdIdx( ICFLibKeyHash256 SecSysGrpId );

	void deepDisposeSecSysGrpByUNameIdx( String Name );

	void deepDisposeSecSysGrpBySecLevelIdx( ICFSecPubSchema.SecLevelEnum SecLevel );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysGrpObj updateSecSysGrp( ICFSecSecSysGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysGrp( ICFSecSecSysGrpObj Obj );

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
