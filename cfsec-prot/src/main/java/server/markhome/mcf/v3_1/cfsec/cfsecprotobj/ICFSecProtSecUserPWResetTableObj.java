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

public interface ICFSecProtSecUserPWResetTableObj
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
	 *	Instantiate a new SecUserPWReset instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecUserPWResetObj newInstance();

	/**
	 *	Instantiate a new SecUserPWReset edition of the specified SecUserPWReset instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecUserPWResetEditObj newEditInstance( ICFSecProtSecUserPWResetObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserPWResetObj realiseSecUserPWReset( ICFSecProtSecUserPWResetObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserPWResetObj createSecUserPWReset( ICFSecProtSecUserPWResetObj Obj );

	/**
	 *	Read a SecUserPWReset-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUserPWReset-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecUserPWResetObj readSecUserPWReset( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SecUserPWReset-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUserPWReset-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecUserPWResetObj readSecUserPWReset( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtSecUserPWResetObj readCachedSecUserPWReset( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSecUserPWReset( ICFSecProtSecUserPWResetObj obj );

	void deepDisposeSecUserPWReset( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserPWResetObj lockSecUserPWReset( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecUserPWReset-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserPWResetObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> readAllSecUserPWReset();

	/**
	 *	Return a sorted map of all the SecUserPWReset-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserPWResetObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> readAllSecUserPWReset( boolean forceRead );

	List<ICFSecProtSecUserPWResetObj> readCachedAllSecUserPWReset();

	/**
	 *	Return a sorted map of a page of the SecUserPWReset-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserPWResetObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> pageAllSecUserPWReset(CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Get the ICFSecProtSecUserPWResetObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserPWResetObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserPWResetObj readSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Get the ICFSecProtSecUserPWResetObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserPWResetObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserPWResetObj readSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtSecUserPWResetObj instance for the unique UUuid6Idx key.
	 *
	 *	@param	PasswordResetUuid6	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserPWResetObj cached instance for the unique UUuid6Idx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserPWResetObj readSecUserPWResetByUUuid6Idx(ICFLibUuid6 PasswordResetUuid6 );

	/**
	 *	Get the ICFSecProtSecUserPWResetObj instance for the unique UUuid6Idx key.
	 *
	 *	@param	PasswordResetUuid6	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserPWResetObj refreshed instance for the unique UUuid6Idx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserPWResetObj readSecUserPWResetByUUuid6Idx(ICFLibUuid6 PasswordResetUuid6,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecUserPWResetObj> instances sorted by their primary keys for the duplicate SentEMAddrIdx key.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserPWResetObj> cached instances sorted by their primary keys for the duplicate SentEMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> readSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr );

	/**
	 *	Get the map of List<ICFSecProtSecUserPWResetObj> instances sorted by their primary keys for the duplicate SentEMAddrIdx key.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserPWResetObj> cached instances sorted by their primary keys for the duplicate SentEMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> readSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecUserPWResetObj> instances sorted by their primary keys for the duplicate NewAcctIdx key.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserPWResetObj> cached instances sorted by their primary keys for the duplicate NewAcctIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> readSecUserPWResetByNewAcctIdx( boolean NewAccount );

	/**
	 *	Get the map of List<ICFSecProtSecUserPWResetObj> instances sorted by their primary keys for the duplicate NewAcctIdx key.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserPWResetObj> cached instances sorted by their primary keys for the duplicate NewAcctIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> readSecUserPWResetByNewAcctIdx( boolean NewAccount,
		boolean forceRead );

	ICFSecProtSecUserPWResetObj readCachedSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId );

	ICFSecProtSecUserPWResetObj readCachedSecUserPWResetByUUuid6Idx( ICFLibUuid6 PasswordResetUuid6 );

	List<List<ICFSecProtSecUserPWResetObj>> readCachedSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr );

	List<List<ICFSecProtSecUserPWResetObj>> readCachedSecUserPWResetByNewAcctIdx( boolean NewAccount );

	void deepDisposeSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId );

	void deepDisposeSecUserPWResetByUUuid6Idx( ICFLibUuid6 PasswordResetUuid6 );

	void deepDisposeSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr );

	void deepDisposeSecUserPWResetByNewAcctIdx( boolean NewAccount );

	/**
	 *	Read a page of data as a List of SecUserPWReset-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SentEMAddrIdx key attributes.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserPWReset-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> pageSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr,
		CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Read a page of data as a List of SecUserPWReset-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NewAcctIdx key attributes.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserPWReset-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecUserPWResetObj> pageSecUserPWResetByNewAcctIdx( boolean NewAccount,
		CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserPWResetObj updateSecUserPWReset( ICFSecProtSecUserPWResetObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecUserPWReset( ICFSecProtSecUserPWResetObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecUserId	The SecUserPWReset key attribute of the instance generating the id.
	 */
	void deleteSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PasswordResetUuid6	The SecUserPWReset key attribute of the instance generating the id.
	 */
	void deleteSecUserPWResetByUUuid6Idx(ICFLibUuid6 PasswordResetUuid6 );

	/**
	 *	Internal use only.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 */
	void deleteSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr );

	/**
	 *	Internal use only.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 */
	void deleteSecUserPWResetByNewAcctIdx( boolean NewAccount );
}
