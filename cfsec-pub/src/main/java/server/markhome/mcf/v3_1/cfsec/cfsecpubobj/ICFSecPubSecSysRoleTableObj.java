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

public interface ICFSecPubSecSysRoleTableObj
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
	 *	Instantiate a new SecSysRole instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecPubSecSysRoleObj newInstance();

	/**
	 *	Instantiate a new SecSysRole edition of the specified SecSysRole instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecPubSecSysRoleEditObj newEditInstance( ICFSecPubSecSysRoleObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecPubSecSysRoleObj realiseSecSysRole( ICFSecPubSecSysRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecPubSecSysRoleObj createSecSysRole( ICFSecPubSecSysRoleObj Obj );

	/**
	 *	Read a SecSysRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecPubSecSysRoleObj readSecSysRole( ICFLibKeyHash256 pkey );

	/**
	 *	Read a SecSysRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecPubSecSysRoleObj readSecSysRole( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFSecPubSecSysRoleObj readCachedSecSysRole( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeSecSysRole( ICFSecPubSecSysRoleObj obj );

	void deepDisposeSecSysRole( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecPubSecSysRoleObj lockSecSysRole( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecSysRole-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubSecSysRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubSecSysRoleObj> readAllSecSysRole();

	/**
	 *	Return a sorted map of all the SecSysRole-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubSecSysRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubSecSysRoleObj> readAllSecSysRole( boolean forceRead );

	List<ICFSecPubSecSysRoleObj> readCachedAllSecSysRole();

	/**
	 *	Get the CFSecPubSecSysRoleObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubSecSysRoleObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubSecSysRoleObj readSecSysRoleByIdIdx( CFLibDbKeyHash256 SecSysRoleId );

	/**
	 *	Get the CFSecPubSecSysRoleObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubSecSysRoleObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubSecSysRoleObj readSecSysRoleByIdIdx( CFLibDbKeyHash256 SecSysRoleId,
		boolean forceRead );

	/**
	 *	Get the CFSecPubSecSysRoleObj instance for the unique UNameIdx key.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubSecSysRoleObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubSecSysRoleObj readSecSysRoleByUNameIdx(String Name );

	/**
	 *	Get the CFSecPubSecSysRoleObj instance for the unique UNameIdx key.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubSecSysRoleObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubSecSysRoleObj readSecSysRoleByUNameIdx(String Name,
		boolean forceRead );

	ICFSecPubSecSysRoleObj readCachedSecSysRoleByIdIdx( CFLibDbKeyHash256 SecSysRoleId );

	ICFSecPubSecSysRoleObj readCachedSecSysRoleByUNameIdx( String Name );

	void deepDisposeSecSysRoleByIdIdx( CFLibDbKeyHash256 SecSysRoleId );

	void deepDisposeSecSysRoleByUNameIdx( String Name );

	/**
	 *	Internal use only.
	 */
	ICFSecPubSecSysRoleObj updateSecSysRole( ICFSecPubSecSysRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysRole( ICFSecPubSecSysRoleObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysRoleId	The SecSysRole key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleByIdIdx( CFLibDbKeyHash256 SecSysRoleId );

	/**
	 *	Internal use only.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleByUNameIdx(String Name );
}
