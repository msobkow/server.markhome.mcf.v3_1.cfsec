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

public interface ICFSecSecTentRoleTableObj
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
	 *	Instantiate a new SecTentRole instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecTentRoleObj newInstance();

	/**
	 *	Instantiate a new SecTentRole edition of the specified SecTentRole instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecTentRoleEditObj newEditInstance( ICFSecSecTentRoleObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentRoleObj realiseSecTentRole( ICFSecSecTentRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentRoleObj createSecTentRole( ICFSecSecTentRoleObj Obj );

	/**
	 *	Read a SecTentRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentRoleObj readSecTentRole( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a SecTentRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentRoleObj readSecTentRole( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFSecSecTentRoleObj readCachedSecTentRole( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeSecTentRole( ICFSecSecTentRoleObj obj );

	void deepDisposeSecTentRole( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentRoleObj lockSecTentRole( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the SecTentRole-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentRoleObj> readAllSecTentRole();

	/**
	 *	Return a sorted map of all the SecTentRole-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentRoleObj> readAllSecTentRole( boolean forceRead );

	List<ICFSecSecTentRoleObj> readCachedAllSecTentRole();

	/**
	 *	Get the CFSecSecTentRoleObj instance for the primary key attributes.
	 *
	 *	@param	SecTentRoleId	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentRoleObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentRoleObj readSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId );

	/**
	 *	Get the CFSecSecTentRoleObj instance for the primary key attributes.
	 *
	 *	@param	SecTentRoleId	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentRoleObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentRoleObj readSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecTentRoleObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentRoleObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentRoleObj> readSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId );

	/**
	 *	Get the map of CFSecSecTentRoleObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentRoleObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentRoleObj> readSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecTentRoleObj instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentRoleObj cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentRoleObj> readSecTentRoleByNameIdx( String Name );

	/**
	 *	Get the map of CFSecSecTentRoleObj instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentRoleObj cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentRoleObj> readSecTentRoleByNameIdx( String Name,
		boolean forceRead );

	/**
	 *	Get the CFSecSecTentRoleObj instance for the unique UNameIdx key.
	 *
	 *	@param	TenantId	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentRoleObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentRoleObj readSecTentRoleByUNameIdx(ICFLibKeyHash256 TenantId,
		String Name );

	/**
	 *	Get the CFSecSecTentRoleObj instance for the unique UNameIdx key.
	 *
	 *	@param	TenantId	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentRoleObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentRoleObj readSecTentRoleByUNameIdx(ICFLibKeyHash256 TenantId,
		String Name,
		boolean forceRead );

	ICFSecSecTentRoleObj readCachedSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId );

	List<ICFSecSecTentRoleObj> readCachedSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId );

	List<ICFSecSecTentRoleObj> readCachedSecTentRoleByNameIdx( String Name );

	ICFSecSecTentRoleObj readCachedSecTentRoleByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name );

	void deepDisposeSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId );

	void deepDisposeSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId );

	void deepDisposeSecTentRoleByNameIdx( String Name );

	void deepDisposeSecTentRoleByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentRoleObj updateSecTentRole( ICFSecSecTentRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecTentRole( ICFSecSecTentRoleObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecTentRoleId	The SecTentRole key attribute of the instance generating the id.
	 */
	void deleteSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The SecTentRole key attribute of the instance generating the id.
	 */
	void deleteSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	Name	The SecTentRole key attribute of the instance generating the id.
	 */
	void deleteSecTentRoleByNameIdx( String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The SecTentRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecTentRole key attribute of the instance generating the id.
	 */
	void deleteSecTentRoleByUNameIdx(ICFLibKeyHash256 TenantId,
		String Name );
}
