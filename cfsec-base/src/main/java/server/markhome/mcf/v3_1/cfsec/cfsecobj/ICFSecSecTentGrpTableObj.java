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

public interface ICFSecSecTentGrpTableObj
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
	 *	Instantiate a new SecTentGrp instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecTentGrpObj newInstance();

	/**
	 *	Instantiate a new SecTentGrp edition of the specified SecTentGrp instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecTentGrpEditObj newEditInstance( ICFSecSecTentGrpObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentGrpObj realiseSecTentGrp( ICFSecSecTentGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentGrpObj createSecTentGrp( ICFSecSecTentGrpObj Obj );

	/**
	 *	Read a SecTentGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentGrpObj readSecTentGrp( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a SecTentGrp-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecTentGrp-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecTentGrpObj readSecTentGrp( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFSecSecTentGrpObj readCachedSecTentGrp( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeSecTentGrp( ICFSecSecTentGrpObj obj );

	void deepDisposeSecTentGrp( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentGrpObj lockSecTentGrp( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the SecTentGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentGrpObj> readAllSecTentGrp();

	/**
	 *	Return a sorted map of all the SecTentGrp-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentGrpObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecTentGrpObj> readAllSecTentGrp( boolean forceRead );

	List<ICFSecSecTentGrpObj> readCachedAllSecTentGrp();

	/**
	 *	Get the CFSecSecTentGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentGrpObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentGrpObj readSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId );

	/**
	 *	Get the CFSecSecTentGrpObj instance for the primary key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentGrpObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentGrpObj readSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecTentGrpObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentGrpObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentGrpObj> readSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId );

	/**
	 *	Get the map of CFSecSecTentGrpObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentGrpObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentGrpObj> readSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecTentGrpObj instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentGrpObj cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentGrpObj> readSecTentGrpByNameIdx( String Name );

	/**
	 *	Get the map of CFSecSecTentGrpObj instances sorted by their primary keys for the duplicate NameIdx key.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecTentGrpObj cached instances sorted by their primary keys for the duplicate NameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecTentGrpObj> readSecTentGrpByNameIdx( String Name,
		boolean forceRead );

	/**
	 *	Get the CFSecSecTentGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentGrpObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentGrpObj readSecTentGrpByUNameIdx(ICFLibKeyHash256 TenantId,
		String Name );

	/**
	 *	Get the CFSecSecTentGrpObj instance for the unique UNameIdx key.
	 *
	 *	@param	TenantId	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SecTentGrp key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecTentGrpObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecTentGrpObj readSecTentGrpByUNameIdx(ICFLibKeyHash256 TenantId,
		String Name,
		boolean forceRead );

	ICFSecSecTentGrpObj readCachedSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId );

	List<ICFSecSecTentGrpObj> readCachedSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId );

	List<ICFSecSecTentGrpObj> readCachedSecTentGrpByNameIdx( String Name );

	ICFSecSecTentGrpObj readCachedSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name );

	void deepDisposeSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId );

	void deepDisposeSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId );

	void deepDisposeSecTentGrpByNameIdx( String Name );

	void deepDisposeSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFSecSecTentGrpObj updateSecTentGrp( ICFSecSecTentGrpObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecTentGrp( ICFSecSecTentGrpObj Obj );

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
