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

public interface ICFSecProtSecUserEMConfTableObj
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
	 *	Instantiate a new SecUserEMConf instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtSecUserEMConfObj newInstance();

	/**
	 *	Instantiate a new SecUserEMConf edition of the specified SecUserEMConf instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtSecUserEMConfEditObj newEditInstance( ICFSecProtSecUserEMConfObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserEMConfObj realiseSecUserEMConf( ICFSecProtSecUserEMConfObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserEMConfObj createSecUserEMConf( ICFSecProtSecUserEMConfObj Obj );

	/**
	 *	Read a SecUserEMConf-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUserEMConf-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecUserEMConfObj readSecUserEMConf( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SecUserEMConf-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUserEMConf-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtSecUserEMConfObj readSecUserEMConf( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFSecProtSecUserEMConfObj readCachedSecUserEMConf( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSecUserEMConf( ICFSecProtSecUserEMConfObj obj );

	void deepDisposeSecUserEMConf( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserEMConfObj lockSecUserEMConf( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SecUserEMConf-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserEMConfObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> readAllSecUserEMConf();

	/**
	 *	Return a sorted map of all the SecUserEMConf-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserEMConfObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> readAllSecUserEMConf( boolean forceRead );

	List<ICFSecProtSecUserEMConfObj> readCachedAllSecUserEMConf();

	/**
	 *	Return a sorted map of a page of the SecUserEMConf-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtSecUserEMConfObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> pageAllSecUserEMConf(CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Get the ICFSecProtSecUserEMConfObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserEMConfObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserEMConfObj readSecUserEMConfByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Get the ICFSecProtSecUserEMConfObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserEMConfObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserEMConfObj readSecUserEMConfByIdIdx( ICFLibKeyHash256 SecUserId,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtSecUserEMConfObj instance for the unique UUuid6Idx key.
	 *
	 *	@param	EMConfirmationUuid6	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserEMConfObj cached instance for the unique UUuid6Idx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserEMConfObj readSecUserEMConfByUUuid6Idx(ICFLibUuid6 EMConfirmationUuid6 );

	/**
	 *	Get the ICFSecProtSecUserEMConfObj instance for the unique UUuid6Idx key.
	 *
	 *	@param	EMConfirmationUuid6	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtSecUserEMConfObj refreshed instance for the unique UUuid6Idx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtSecUserEMConfObj readSecUserEMConfByUUuid6Idx(ICFLibUuid6 EMConfirmationUuid6,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecUserEMConfObj> instances sorted by their primary keys for the duplicate ConfEMAddrIdx key.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserEMConfObj> cached instances sorted by their primary keys for the duplicate ConfEMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> readSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr );

	/**
	 *	Get the map of List<ICFSecProtSecUserEMConfObj> instances sorted by their primary keys for the duplicate ConfEMAddrIdx key.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserEMConfObj> cached instances sorted by their primary keys for the duplicate ConfEMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> readSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecUserEMConfObj> instances sorted by their primary keys for the duplicate SentStampIdx key.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserEMConfObj> cached instances sorted by their primary keys for the duplicate SentStampIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> readSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp );

	/**
	 *	Get the map of List<ICFSecProtSecUserEMConfObj> instances sorted by their primary keys for the duplicate SentStampIdx key.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserEMConfObj> cached instances sorted by their primary keys for the duplicate SentStampIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> readSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtSecUserEMConfObj> instances sorted by their primary keys for the duplicate NewAcctIdx key.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserEMConfObj> cached instances sorted by their primary keys for the duplicate NewAcctIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> readSecUserEMConfByNewAcctIdx( boolean NewAccount );

	/**
	 *	Get the map of List<ICFSecProtSecUserEMConfObj> instances sorted by their primary keys for the duplicate NewAcctIdx key.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtSecUserEMConfObj> cached instances sorted by their primary keys for the duplicate NewAcctIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> readSecUserEMConfByNewAcctIdx( boolean NewAccount,
		boolean forceRead );

	ICFSecProtSecUserEMConfObj readCachedSecUserEMConfByIdIdx( ICFLibKeyHash256 SecUserId );

	ICFSecProtSecUserEMConfObj readCachedSecUserEMConfByUUuid6Idx( ICFLibUuid6 EMConfirmationUuid6 );

	List<List<ICFSecProtSecUserEMConfObj>> readCachedSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr );

	List<List<ICFSecProtSecUserEMConfObj>> readCachedSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp );

	List<List<ICFSecProtSecUserEMConfObj>> readCachedSecUserEMConfByNewAcctIdx( boolean NewAccount );

	void deepDisposeSecUserEMConfByIdIdx( ICFLibKeyHash256 SecUserId );

	void deepDisposeSecUserEMConfByUUuid6Idx( ICFLibUuid6 EMConfirmationUuid6 );

	void deepDisposeSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr );

	void deepDisposeSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp );

	void deepDisposeSecUserEMConfByNewAcctIdx( boolean NewAccount );

	/**
	 *	Read a page of data as a List of SecUserEMConf-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ConfEMAddrIdx key attributes.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserEMConf-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> pageSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr,
		CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Read a page of data as a List of SecUserEMConf-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SentStampIdx key attributes.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserEMConf-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> pageSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp,
		CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Read a page of data as a List of SecUserEMConf-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NewAcctIdx key attributes.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserEMConf-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecProtSecUserEMConfObj> pageSecUserEMConfByNewAcctIdx( boolean NewAccount,
		CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Internal use only.
	 */
	ICFSecProtSecUserEMConfObj updateSecUserEMConf( ICFSecProtSecUserEMConfObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecUserEMConf( ICFSecProtSecUserEMConfObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecUserId	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Internal use only.
	 *
	 *	@param	EMConfirmationUuid6	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfByUUuid6Idx(ICFLibUuid6 EMConfirmationUuid6 );

	/**
	 *	Internal use only.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr );

	/**
	 *	Internal use only.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp );

	/**
	 *	Internal use only.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfByNewAcctIdx( boolean NewAccount );
}
