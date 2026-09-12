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

public interface ICFSecSecSysRoleTableObj
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
	 *	Instantiate a new SecSysRole instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecSysRoleObj newInstance();

	/**
	 *	Instantiate a new SecSysRole edition of the specified SecSysRole instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecSysRoleEditObj newEditInstance( ICFSecSecSysRoleObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleObj realiseSecSysRole( ICFSecSecSysRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleObj createSecSysRole( ICFSecSecSysRoleObj Obj );

	/**
	 *	Read a SecSysRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleObj readSecSysRole( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a SecSysRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSysRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecSysRoleObj readSecSysRole( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFSecSecSysRoleObj readCachedSecSysRole( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeSecSysRole( ICFSecSecSysRoleObj obj );

	void deepDisposeSecSysRole( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleObj lockSecSysRole( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the SecSysRole-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysRoleObj> readAllSecSysRole();

	/**
	 *	Return a sorted map of all the SecSysRole-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecSysRoleObj> readAllSecSysRole( boolean forceRead );

	List<ICFSecSecSysRoleObj> readCachedAllSecSysRole();

	/**
	 *	Get the CFSecSecSysRoleObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysRoleObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysRoleObj readSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Get the CFSecSecSysRoleObj instance for the primary key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysRoleObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysRoleObj readSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		boolean forceRead );

	/**
	 *	Get the CFSecSecSysRoleObj instance for the unique UNameIdx key.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysRoleObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysRoleObj readSecSysRoleByUNameIdx(String Name );

	/**
	 *	Get the CFSecSecSysRoleObj instance for the unique UNameIdx key.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecSysRoleObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecSysRoleObj readSecSysRoleByUNameIdx(String Name,
		boolean forceRead );

	ICFSecSecSysRoleObj readCachedSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId );

	ICFSecSecSysRoleObj readCachedSecSysRoleByUNameIdx( String Name );

	void deepDisposeSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId );

	void deepDisposeSecSysRoleByUNameIdx( String Name );

	/**
	 *	Internal use only.
	 */
	ICFSecSecSysRoleObj updateSecSysRole( ICFSecSecSysRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSysRole( ICFSecSecSysRoleObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSysRoleId	The SecSysRole key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Internal use only.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleByUNameIdx(String Name );
}
