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

public interface ICFSecProtSecSessionTableObj
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
	 *	Instantiate a new SecSession instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecSessionObj newInstance();

	/**
	 *	Instantiate a new SecSession edition of the specified SecSession instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecSessionEditObj newEditInstance( ICFSecProtSecSessionObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSessionObj realiseSecSession( ICFSecProtSecSessionObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSessionObj createSecSession( ICFSecProtSecSessionObj Obj );

	/**
	 *	Read a SecSession-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSession-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSessionObj readSecSession( MCFDbKeyHash256 pkey );

	/**
	 *	Read a SecSession-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecSession-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecSessionObj readSecSession( MCFDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtSecSessionObj readCachedSecSession( MCFDbKeyHash256 pkey );

	public void reallyDeepDisposeSecSession( ICFSecProtSecSessionObj obj );

	void deepDisposeSecSession( MCFDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSessionObj lockSecSession( MCFDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecSession-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSessionObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSessionObj> readAllSecSession();

	/**
	 *	Return a sorted map of all the SecSession-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSessionObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSessionObj> readAllSecSession( boolean forceRead );

	List<ICFSecProtSecSessionObj> readCachedAllSecSession();

	/**
	 *	Return a sorted map of a page of the SecSession-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecSessionObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecSessionObj> pageAllSecSession(MCFDbKeyHash256 priorSecSessionId );

	/**
	 *	Get the ICFSecProtSecSessionObj instance for the primary key attributes.
	 *
	 *	@param	SecSessionId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSessionObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSessionObj readSecSessionByIdIdx( IMCFKeyHash256 SecSessionId );

	/**
	 *	Get the ICFSecProtSecSessionObj instance for the primary key attributes.
	 *
	 *	@param	SecSessionId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSessionObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSessionObj readSecSessionByIdIdx( IMCFKeyHash256 SecSessionId,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSessionObj> instances sorted by their primary keys for the duplicate SecUserIdx key.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSessionObj> cached instances sorted by their primary keys for the duplicate SecUserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> readSecSessionBySecUserIdx( IMCFKeyHash256 SecUserId );

	/**
	 *	Get the map of List<ICFSecProtSecSessionObj> instances sorted by their primary keys for the duplicate SecUserIdx key.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSessionObj> cached instances sorted by their primary keys for the duplicate SecUserIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> readSecSessionBySecUserIdx( IMCFKeyHash256 SecUserId,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtSecSessionObj instance for the unique StartIdx key.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@param	Start	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSessionObj cached instance for the unique StartIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSessionObj readSecSessionByStartIdx(IMCFKeyHash256 SecUserId,
		LocalDateTime Start );

	/**
	 *	Get the ICFSecProtSecSessionObj instance for the unique StartIdx key.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@param	Start	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecSessionObj refreshed instance for the unique StartIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecSessionObj readSecSessionByStartIdx(IMCFKeyHash256 SecUserId,
		LocalDateTime Start,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSessionObj> instances sorted by their primary keys for the duplicate FinishIdx key.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@param	Finish	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSessionObj> cached instances sorted by their primary keys for the duplicate FinishIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> readSecSessionByFinishIdx( IMCFKeyHash256 SecUserId,
		LocalDateTime Finish );

	/**
	 *	Get the map of List<ICFSecProtSecSessionObj> instances sorted by their primary keys for the duplicate FinishIdx key.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@param	Finish	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSessionObj> cached instances sorted by their primary keys for the duplicate FinishIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> readSecSessionByFinishIdx( IMCFKeyHash256 SecUserId,
		LocalDateTime Finish,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecSessionObj> instances sorted by their primary keys for the duplicate SecProxyIdx key.
	 *
	 *	@param	SecProxyId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSessionObj> cached instances sorted by their primary keys for the duplicate SecProxyIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> readSecSessionBySecProxyIdx( MCFDbKeyHash256 SecProxyId );

	/**
	 *	Get the map of List<ICFSecProtSecSessionObj> instances sorted by their primary keys for the duplicate SecProxyIdx key.
	 *
	 *	@param	SecProxyId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecSessionObj> cached instances sorted by their primary keys for the duplicate SecProxyIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> readSecSessionBySecProxyIdx( MCFDbKeyHash256 SecProxyId,
		boolean forceRead );

	ICFSecProtSecSessionObj readCachedSecSessionByIdIdx( IMCFKeyHash256 SecSessionId );

	List<List<ICFSecProtSecSessionObj>> readCachedSecSessionBySecUserIdx( IMCFKeyHash256 SecUserId );

	ICFSecProtSecSessionObj readCachedSecSessionByStartIdx( IMCFKeyHash256 SecUserId,
		LocalDateTime Start );

	List<List<ICFSecProtSecSessionObj>> readCachedSecSessionByFinishIdx( IMCFKeyHash256 SecUserId,
		LocalDateTime Finish );

	List<List<ICFSecProtSecSessionObj>> readCachedSecSessionBySecProxyIdx( MCFDbKeyHash256 SecProxyId );

	void deepDisposeSecSessionByIdIdx( IMCFKeyHash256 SecSessionId );

	void deepDisposeSecSessionBySecUserIdx( IMCFKeyHash256 SecUserId );

	void deepDisposeSecSessionByStartIdx( IMCFKeyHash256 SecUserId,
		LocalDateTime Start );

	void deepDisposeSecSessionByFinishIdx( IMCFKeyHash256 SecUserId,
		LocalDateTime Finish );

	void deepDisposeSecSessionBySecProxyIdx( MCFDbKeyHash256 SecProxyId );

	/**
	 *	Read a page of data as a List of SecSession-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SecUserIdx key attributes.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSession-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> pageSecSessionBySecUserIdx( IMCFKeyHash256 SecUserId,
		MCFDbKeyHash256 priorSecSessionId );

	/**
	 *	Read a page of data as a List of SecSession-derived instances sorted by their primary keys,
	 *	as identified by the duplicate FinishIdx key attributes.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@param	Finish	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSession-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> pageSecSessionByFinishIdx( IMCFKeyHash256 SecUserId,
		LocalDateTime Finish,
		MCFDbKeyHash256 priorSecSessionId );

	/**
	 *	Read a page of data as a List of SecSession-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SecProxyIdx key attributes.
	 *
	 *	@param	SecProxyId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSession-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecSessionObj> pageSecSessionBySecProxyIdx( MCFDbKeyHash256 SecProxyId,
		MCFDbKeyHash256 priorSecSessionId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecSessionObj updateSecSession( ICFSecProtSecSessionObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecSession( ICFSecProtSecSessionObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecSessionId	The SecSession key attribute of the instance generating the id.
	 */
	void deleteSecSessionByIdIdx( IMCFKeyHash256 SecSessionId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 */
	void deleteSecSessionBySecUserIdx( IMCFKeyHash256 SecUserId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@param	Start	The SecSession key attribute of the instance generating the id.
	 */
	void deleteSecSessionByStartIdx(IMCFKeyHash256 SecUserId,
		LocalDateTime Start );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@param	Finish	The SecSession key attribute of the instance generating the id.
	 */
	void deleteSecSessionByFinishIdx( IMCFKeyHash256 SecUserId,
		LocalDateTime Finish );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecProxyId	The SecSession key attribute of the instance generating the id.
	 */
	void deleteSecSessionBySecProxyIdx( MCFDbKeyHash256 SecProxyId );
}
