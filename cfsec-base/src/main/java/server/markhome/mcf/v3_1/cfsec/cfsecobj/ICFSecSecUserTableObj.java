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

public interface ICFSecSecUserTableObj
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
	 *	Instantiate a new SecUser instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecUserObj newInstance();

	/**
	 *	Instantiate a new SecUser edition of the specified SecUser instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecUserEditObj newEditInstance( ICFSecSecUserObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserObj realiseSecUser( ICFSecSecUserObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserObj createSecUser( ICFSecSecUserObj Obj );

	/**
	 *	Read a SecUser-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUser-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecUserObj readSecUser( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a SecUser-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUser-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecUserObj readSecUser( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFSecSecUserObj readCachedSecUser( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeSecUser( ICFSecSecUserObj obj );

	void deepDisposeSecUser( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserObj lockSecUser( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecUserObj> readAllSecUser();

	/**
	 *	Return a sorted map of all the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecUserObj> readAllSecUser( boolean forceRead );

	List<ICFSecSecUserObj> readCachedAllSecUser();

	/**
	 *	Return a sorted map of a page of the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecUserObj> pageAllSecUser(ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Get the CFSecSecUserObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserObj readSecUserByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Get the CFSecSecUserObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserObj readSecUserByIdIdx( ICFLibKeyHash256 SecUserId,
		boolean forceRead );

	/**
	 *	Get the CFSecSecUserObj instance for the unique ULoginIdx key.
	 *
	 *	@param	LoginId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserObj cached instance for the unique ULoginIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserObj readSecUserByULoginIdx(String LoginId );

	/**
	 *	Get the CFSecSecUserObj instance for the unique ULoginIdx key.
	 *
	 *	@param	LoginId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserObj refreshed instance for the unique ULoginIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserObj readSecUserByULoginIdx(String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecUserObj instances sorted by their primary keys for the duplicate EMAddrIdx key.
	 *
	 *	@param	EMailAddress	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserObj cached instances sorted by their primary keys for the duplicate EMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserObj> readSecUserByEMAddrIdx( String EMailAddress );

	/**
	 *	Get the map of CFSecSecUserObj instances sorted by their primary keys for the duplicate EMAddrIdx key.
	 *
	 *	@param	EMailAddress	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserObj cached instances sorted by their primary keys for the duplicate EMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserObj> readSecUserByEMAddrIdx( String EMailAddress,
		boolean forceRead );

	ICFSecSecUserObj readCachedSecUserByIdIdx( ICFLibKeyHash256 SecUserId );

	ICFSecSecUserObj readCachedSecUserByULoginIdx( String LoginId );

	List<ICFSecSecUserObj> readCachedSecUserByEMAddrIdx( String EMailAddress );

	void deepDisposeSecUserByIdIdx( ICFLibKeyHash256 SecUserId );

	void deepDisposeSecUserByULoginIdx( String LoginId );

	void deepDisposeSecUserByEMAddrIdx( String EMailAddress );

	/**
	 *	Read a page of data as a List of SecUser-derived instances sorted by their primary keys,
	 *	as identified by the duplicate EMAddrIdx key attributes.
	 *
	 *	@param	EMailAddress	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUser-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecUserObj> pageSecUserByEMAddrIdx( String EMailAddress,
		ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserObj updateSecUser( ICFSecSecUserObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecUser( ICFSecSecUserObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecUserId	The SecUser key attribute of the instance generating the id.
	 */
	void deleteSecUserByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Internal use only.
	 *
	 *	@param	LoginId	The SecUser key attribute of the instance generating the id.
	 */
	void deleteSecUserByULoginIdx(String LoginId );

	/**
	 *	Internal use only.
	 *
	 *	@param	EMailAddress	The SecUser key attribute of the instance generating the id.
	 */
	void deleteSecUserByEMAddrIdx( String EMailAddress );
}
