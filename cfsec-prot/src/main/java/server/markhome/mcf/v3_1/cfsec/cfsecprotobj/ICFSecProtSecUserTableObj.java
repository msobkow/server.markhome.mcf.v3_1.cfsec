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

public interface ICFSecProtSecUserTableObj
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
	 *	Instantiate a new SecUser instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecUserObj newInstance();

	/**
	 *	Instantiate a new SecUser edition of the specified SecUser instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecUserEditObj newEditInstance( ICFSecProtSecUserObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserObj realiseSecUser( ICFSecProtSecUserObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserObj createSecUser( ICFSecProtSecUserObj Obj );

	/**
	 *	Read a SecUser-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUser-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecUserObj readSecUser( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SecUser-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUser-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecUserObj readSecUser( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtSecUserObj readCachedSecUser( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSecUser( ICFSecProtSecUserObj obj );

	void deepDisposeSecUser( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserObj lockSecUser( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserObj> readAllSecUser();

	/**
	 *	Return a sorted map of all the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserObj> readAllSecUser( boolean forceRead );

	List<ICFSecProtSecUserObj> readCachedAllSecUser();

	/**
	 *	Return a sorted map of a page of the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserObj> pageAllSecUser(CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Get the ICFSecProtSecUserObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserObj readSecUserByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Get the ICFSecProtSecUserObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserObj readSecUserByIdIdx( ICFLibKeyHash256 SecUserId,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtSecUserObj instance for the unique ULoginIdx key.
	 *
	 *	@param	LoginId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserObj cached instance for the unique ULoginIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserObj readSecUserByULoginIdx(String LoginId );

	/**
	 *	Get the ICFSecProtSecUserObj instance for the unique ULoginIdx key.
	 *
	 *	@param	LoginId	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserObj refreshed instance for the unique ULoginIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserObj readSecUserByULoginIdx(String LoginId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecUserObj> instances sorted by their primary keys for the duplicate EMAddrIdx key.
	 *
	 *	@param	EMailAddress	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserObj> cached instances sorted by their primary keys for the duplicate EMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserObj> readSecUserByEMAddrIdx( String EMailAddress );

	/**
	 *	Get the map of List<ICFSecProtSecUserObj> instances sorted by their primary keys for the duplicate EMAddrIdx key.
	 *
	 *	@param	EMailAddress	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserObj> cached instances sorted by their primary keys for the duplicate EMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserObj> readSecUserByEMAddrIdx( String EMailAddress,
		boolean forceRead );

	ICFSecProtSecUserObj readCachedSecUserByIdIdx( ICFLibKeyHash256 SecUserId );

	ICFSecProtSecUserObj readCachedSecUserByULoginIdx( String LoginId );

	List<List<ICFSecProtSecUserObj>> readCachedSecUserByEMAddrIdx( String EMailAddress );

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
	List<ICFSecProtSecUserObj> pageSecUserByEMAddrIdx( String EMailAddress,
		CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserObj updateSecUser( ICFSecProtSecUserObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecUser( ICFSecProtSecUserObj Obj );

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
