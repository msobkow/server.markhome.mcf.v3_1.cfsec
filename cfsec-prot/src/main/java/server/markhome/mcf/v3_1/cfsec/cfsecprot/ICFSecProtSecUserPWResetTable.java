
// Description: Java 25 protlic DbIO interface for SecUserPWReset.

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

package server.markhome.mcf.v3_1.cfsec.cfsecprot;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
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
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/*
 *	CFSecProtSecUserPWResetTable protlic database interface for SecUserPWReset has CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecProtSecUserPWResetTable
{
	public static final String TABLE_NAME = "SecUserPWReset";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	public ICFSecProtSecUserPWReset protcreateSecUserPWReset( ICFSecPubAuthorization Authorization,
		ICFSecProtSecUserPWReset rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	public ICFSecProtSecUserPWReset protupdateSecUserPWReset( ICFSecPubAuthorization Authorization,
		ICFSecProtSecUserPWReset rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	public void protdeleteSecUserPWReset( ICFSecPubAuthorization Authorization,
		ICFSecProtSecUserPWReset rec );
	/**
	 *	Delete the SecUserPWReset instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	public void protdeleteSecUserPWResetByIdIdx( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 argKey );
	/**
	 *	Delete the SecUserPWReset instances identified by the key UUuid6Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PasswordResetUuid6	The SecUserPWReset key attribute of the instance generating the id.
	 */
	public void protdeleteSecUserPWResetByUUuid6Idx( ICFSecPubAuthorization Authorization,
		ICFLibUuid6 argPasswordResetUuid6 );

	/**
	 *	Delete the SecUserPWReset instances identified by the key UUuid6Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void protdeleteSecUserPWResetByUUuid6Idx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecUserPWResetByUUuid6IdxKey argKey );
	/**
	 *	Delete the SecUserPWReset instances identified by the key SentEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 */
	public void protdeleteSecUserPWResetBySentEMAddrIdx( ICFSecPubAuthorization Authorization,
		String argSentToEMailAddr );

	/**
	 *	Delete the SecUserPWReset instances identified by the key SentEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void protdeleteSecUserPWResetBySentEMAddrIdx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecUserPWResetBySentEMAddrIdxKey argKey );
	/**
	 *	Delete the SecUserPWReset instances identified by the key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 */
	public void protdeleteSecUserPWResetByNewAcctIdx( ICFSecPubAuthorization Authorization,
		boolean argNewAccount );

	/**
	 *	Delete the SecUserPWReset instances identified by the key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void protdeleteSecUserPWResetByNewAcctIdx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecUserPWResetByNewAcctIdxKey argKey );


	/**
	 *	Read the derived SecUserPWReset record instance by protected primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecUserPWReset instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecUserPWReset protreadDerived( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the derived SecUserPWReset record instance by protected primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecUserPWReset instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecUserPWReset protlockDerived( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all SecUserPWReset instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecUserPWReset[] protreadProtAllDerived( ICFSecPubAuthorization Authorization );

	/**
	 *	Read the derived SecUserPWReset record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecUserId	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecUserPWReset protreadDerivedByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecUserId );

	/**
	 *	Read the derived SecUserPWReset record instance identified by the unique key UUuid6Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PasswordResetUuid6	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecUserPWReset protreadDerivedByUUuid6Idx( ICFSecPubAuthorization Authorization,
		ICFLibUuid6 PasswordResetUuid6 );

	/**
	 *	Read an array of the derived SecUserPWReset record instances identified by the duplicate key SentEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecUserPWReset[] protreadDerivedBySentEMAddrIdx( ICFSecPubAuthorization Authorization,
		String SentToEMailAddr );

	/**
	 *	Read an array of the derived SecUserPWReset record instances identified by the duplicate key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecUserPWReset[] protreadDerivedByNewAcctIdx( ICFSecPubAuthorization Authorization,
		boolean NewAccount );

	/**
	 *	Read the specific SecUserPWReset record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecUserPWReset instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecUserPWReset protreadRec( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Lock the specific SecUserPWReset record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecUserPWReset instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecUserPWReset protlockRec( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 PKey );

	/**
	 *	Read all the specific SecUserPWReset record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecUserPWReset instances in the database accessible for the Authorization.
	 */
	public ICFSecProtSecUserPWReset[] protreadAllRec( ICFSecPubAuthorization Authorization );

	/**
	 *	Read a page of all the specific SecUserPWReset record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecUserPWReset instances in the database accessible for the Authorization.
	 */
	public ICFSecProtSecUserPWReset[] protpageAllRec( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 priorSecUserId );

	/**
	 *	Read the specific SecUserPWReset record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecUserId	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecUserPWReset protreadRecByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecUserId );

	/**
	 *	Read the specific SecUserPWReset record instance identified by the unique key UUuid6Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PasswordResetUuid6	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecUserPWReset protreadRecByUUuid6Idx( ICFSecPubAuthorization Authorization,
		ICFLibUuid6 PasswordResetUuid6 );

	/**
	 *	Read an array of the specific SecUserPWReset record instances identified by the duplicate key SentEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecUserPWReset[] protreadRecBySentEMAddrIdx( ICFSecPubAuthorization Authorization,
		String SentToEMailAddr );

	/**
	 *	Read an array of the specific SecUserPWReset record instances identified by the duplicate key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecUserPWReset[] protreadRecByNewAcctIdx( ICFSecPubAuthorization Authorization,
		boolean NewAccount );

	/**
	 *	Read a page array of the specific SecUserPWReset record instances identified by the duplicate key SentEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecUserPWReset[] protpageRecBySentEMAddrIdx( ICFSecPubAuthorization Authorization,
		String SentToEMailAddr,
		ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Read a page array of the specific SecUserPWReset record instances identified by the duplicate key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecUserPWReset[] protpageRecByNewAcctIdx( ICFSecPubAuthorization Authorization,
		boolean NewAccount,
		ICFLibKeyHash256 priorSecUserId );
}
