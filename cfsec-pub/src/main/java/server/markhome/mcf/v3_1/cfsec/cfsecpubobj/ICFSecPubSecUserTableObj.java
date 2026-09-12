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

public interface ICFSecPubSecUserTableObj
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
	 *	Instantiate a new SecUser instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecPubSecUserObj newInstance();

	/**
	 *	Instantiate a new SecUser edition of the specified SecUser instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecPubSecUserEditObj newEditInstance( ICFSecPubSecUserObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecPubSecUserObj realiseSecUser( ICFSecPubSecUserObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecPubSecUserObj createSecUser( ICFSecPubSecUserObj Obj );

	/**
	 *	Read a SecUser-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUser-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecPubSecUserObj readSecUser( ICFLibKeyHash256 pkey );

	/**
	 *	Read a SecUser-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUser-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecPubSecUserObj readSecUser( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFSecPubSecUserObj readCachedSecUser( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeSecUser( ICFSecPubSecUserObj obj );

	void deepDisposeSecUser( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecPubSecUserObj lockSecUser( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubSecUserObj> readAllSecUser();

	/**
	 *	Return a sorted map of all the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubSecUserObj> readAllSecUser( boolean forceRead );

	List<ICFSecPubSecUserObj> readCachedAllSecUser();

	/**
	 *	Return a sorted map of a page of the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecPubSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecPubSecUserObj> pageAllSecUser(CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Get the CFSecPubSecUserObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubSecUserObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubSecUserObj readSecUserByIdIdx( CFLibDbKeyHash256 SecUserId );

	/**
	 *	Get the CFSecPubSecUserObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubSecUserObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubSecUserObj readSecUserByIdIdx( CFLibDbKeyHash256 SecUserId,
		boolean forceRead );

	/**
	 *	Get the CFSecPubSecUserObj instance for the unique ULoginIdx key.
	 *
	 *	@param	LoginId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubSecUserObj cached instance for the unique ULoginIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubSecUserObj readSecUserByULoginIdx(String LoginId );

	/**
	 *	Get the CFSecPubSecUserObj instance for the unique ULoginIdx key.
	 *
	 *	@param	LoginId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	CFSecPubSecUserObj refreshed instance for the unique ULoginIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecPubSecUserObj readSecUserByULoginIdx(String LoginId,
		boolean forceRead );

	ICFSecPubSecUserObj readCachedSecUserByIdIdx( CFLibDbKeyHash256 SecUserId );

	ICFSecPubSecUserObj readCachedSecUserByULoginIdx( String LoginId );

	void deepDisposeSecUserByIdIdx( CFLibDbKeyHash256 SecUserId );

	void deepDisposeSecUserByULoginIdx( String LoginId );

	/**
	 *	Internal use only.
	 */
	ICFSecPubSecUserObj updateSecUser( ICFSecPubSecUserObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecUser( ICFSecPubSecUserObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecUserId	The SecUser key attribute of the instance generating the id.
	 */
	void deleteSecUserByIdIdx( CFLibDbKeyHash256 SecUserId );

	/**
	 *	Internal use only.
	 *
	 *	@param	LoginId	The SecUser key attribute of the instance generating the id.
	 */
	void deleteSecUserByULoginIdx(String LoginId );
}
