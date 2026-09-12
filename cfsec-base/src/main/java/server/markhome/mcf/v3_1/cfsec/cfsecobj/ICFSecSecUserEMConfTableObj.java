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

public interface ICFSecSecUserEMConfTableObj
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
	 *	Instantiate a new SecUserEMConf instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecUserEMConfObj newInstance();

	/**
	 *	Instantiate a new SecUserEMConf edition of the specified SecUserEMConf instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecUserEMConfEditObj newEditInstance( ICFSecSecUserEMConfObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserEMConfObj realiseSecUserEMConf( ICFSecSecUserEMConfObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserEMConfObj createSecUserEMConf( ICFSecSecUserEMConfObj Obj );

	/**
	 *	Read a SecUserEMConf-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUserEMConf-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecUserEMConfObj readSecUserEMConf( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a SecUserEMConf-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUserEMConf-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecUserEMConfObj readSecUserEMConf( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFSecSecUserEMConfObj readCachedSecUserEMConf( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeSecUserEMConf( ICFSecSecUserEMConfObj obj );

	void deepDisposeSecUserEMConf( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserEMConfObj lockSecUserEMConf( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the SecUserEMConf-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserEMConfObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecUserEMConfObj> readAllSecUserEMConf();

	/**
	 *	Return a sorted map of all the SecUserEMConf-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserEMConfObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecUserEMConfObj> readAllSecUserEMConf( boolean forceRead );

	List<ICFSecSecUserEMConfObj> readCachedAllSecUserEMConf();

	/**
	 *	Return a sorted map of a page of the SecUserEMConf-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserEMConfObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecUserEMConfObj> pageAllSecUserEMConf(ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Get the CFSecSecUserEMConfObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserEMConfObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserEMConfObj readSecUserEMConfByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Get the CFSecSecUserEMConfObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserEMConfObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserEMConfObj readSecUserEMConfByIdIdx( ICFLibKeyHash256 SecUserId,
		boolean forceRead );

	/**
	 *	Get the CFSecSecUserEMConfObj instance for the unique UUuid6Idx key.
	 *
	 *	@param	EMConfirmationUuid6	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserEMConfObj cached instance for the unique UUuid6Idx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserEMConfObj readSecUserEMConfByUUuid6Idx(ICFLibUuid6 EMConfirmationUuid6 );

	/**
	 *	Get the CFSecSecUserEMConfObj instance for the unique UUuid6Idx key.
	 *
	 *	@param	EMConfirmationUuid6	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserEMConfObj refreshed instance for the unique UUuid6Idx key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserEMConfObj readSecUserEMConfByUUuid6Idx(ICFLibUuid6 EMConfirmationUuid6,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecUserEMConfObj instances sorted by their primary keys for the duplicate ConfEMAddrIdx key.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserEMConfObj cached instances sorted by their primary keys for the duplicate ConfEMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserEMConfObj> readSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr );

	/**
	 *	Get the map of CFSecSecUserEMConfObj instances sorted by their primary keys for the duplicate ConfEMAddrIdx key.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserEMConfObj cached instances sorted by their primary keys for the duplicate ConfEMAddrIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserEMConfObj> readSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecUserEMConfObj instances sorted by their primary keys for the duplicate SentStampIdx key.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserEMConfObj cached instances sorted by their primary keys for the duplicate SentStampIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserEMConfObj> readSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp );

	/**
	 *	Get the map of CFSecSecUserEMConfObj instances sorted by their primary keys for the duplicate SentStampIdx key.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserEMConfObj cached instances sorted by their primary keys for the duplicate SentStampIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserEMConfObj> readSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecUserEMConfObj instances sorted by their primary keys for the duplicate NewAcctIdx key.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserEMConfObj cached instances sorted by their primary keys for the duplicate NewAcctIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserEMConfObj> readSecUserEMConfByNewAcctIdx( boolean NewAccount );

	/**
	 *	Get the map of CFSecSecUserEMConfObj instances sorted by their primary keys for the duplicate NewAcctIdx key.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserEMConfObj cached instances sorted by their primary keys for the duplicate NewAcctIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserEMConfObj> readSecUserEMConfByNewAcctIdx( boolean NewAccount,
		boolean forceRead );

	ICFSecSecUserEMConfObj readCachedSecUserEMConfByIdIdx( ICFLibKeyHash256 SecUserId );

	ICFSecSecUserEMConfObj readCachedSecUserEMConfByUUuid6Idx( ICFLibUuid6 EMConfirmationUuid6 );

	List<ICFSecSecUserEMConfObj> readCachedSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr );

	List<ICFSecSecUserEMConfObj> readCachedSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp );

	List<ICFSecSecUserEMConfObj> readCachedSecUserEMConfByNewAcctIdx( boolean NewAccount );

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
	List<ICFSecSecUserEMConfObj> pageSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr,
		ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Read a page of data as a List of SecUserEMConf-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SentStampIdx key attributes.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserEMConf-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecUserEMConfObj> pageSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp,
		ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Read a page of data as a List of SecUserEMConf-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NewAcctIdx key attributes.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserEMConf-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	List<ICFSecSecUserEMConfObj> pageSecUserEMConfByNewAcctIdx( boolean NewAccount,
		ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserEMConfObj updateSecUserEMConf( ICFSecSecUserEMConfObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecUserEMConf( ICFSecSecUserEMConfObj Obj );

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
