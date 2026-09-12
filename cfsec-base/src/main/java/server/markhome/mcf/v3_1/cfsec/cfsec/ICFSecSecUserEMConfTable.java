
// Description: Java 25 DbIO interface for SecUserEMConf.

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

package server.markhome.mcf.v3_1.cfsec.cfsec;

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

import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/*
 *	CFSecSecUserEMConfTable database interface for SecUserEMConf has CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecSecUserEMConfTable
{
	public static final String TABLE_NAME = "SecUserEMConf";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFSecSecUserEMConf createSecUserEMConf( ICFSecAuthorization Authorization,
		ICFSecSecUserEMConf rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFSecSecUserEMConf updateSecUserEMConf( ICFSecAuthorization Authorization,
		ICFSecSecUserEMConf rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteSecUserEMConf( ICFSecAuthorization Authorization,
		ICFSecSecUserEMConf rec );
	/**
	 *	Delete the SecUserEMConf instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteSecUserEMConfByIdIdx( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ argKey );
	/**
	 *	Delete the SecUserEMConf instances identified by the key UUuid6Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EMConfirmationUuid6	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfByUUuid6Idx( ICFSecAuthorization Authorization,
		ICFLibUuid6 argEMConfirmationUuid6 );

	/**
	 *	Delete the SecUserEMConf instances identified by the key UUuid6Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSecUserEMConfByUUuid6Idx( ICFSecAuthorization Authorization,
		ICFSecSecUserEMConfByUUuid6IdxKey argKey );
	/**
	 *	Delete the SecUserEMConf instances identified by the key ConfEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfByConfEMAddrIdx( ICFSecAuthorization Authorization,
		String argConfirmEMailAddr );

	/**
	 *	Delete the SecUserEMConf instances identified by the key ConfEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSecUserEMConfByConfEMAddrIdx( ICFSecAuthorization Authorization,
		ICFSecSecUserEMConfByConfEMAddrIdxKey argKey );
	/**
	 *	Delete the SecUserEMConf instances identified by the key SentStampIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfBySentStampIdx( ICFSecAuthorization Authorization,
		LocalDateTime argEMailSentStamp );

	/**
	 *	Delete the SecUserEMConf instances identified by the key SentStampIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSecUserEMConfBySentStampIdx( ICFSecAuthorization Authorization,
		ICFSecSecUserEMConfBySentStampIdxKey argKey );
	/**
	 *	Delete the SecUserEMConf instances identified by the key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 */
	void deleteSecUserEMConfByNewAcctIdx( ICFSecAuthorization Authorization,
		boolean argNewAccount );

	/**
	 *	Delete the SecUserEMConf instances identified by the key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSecUserEMConfByNewAcctIdx( ICFSecAuthorization Authorization,
		ICFSecSecUserEMConfByNewAcctIdxKey argKey );


	/**
	 *	Read the derived SecUserEMConf record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecUserEMConf instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecUserEMConf readDerived( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ PKey );

	/**
	 *	Lock the derived SecUserEMConf record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecUserEMConf instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecUserEMConf lockDerived( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ PKey );

	/**
	 *	Read all SecUserEMConf instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFSecSecUserEMConf[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived SecUserEMConf record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecUserId	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecUserEMConf readDerivedByIdIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecUserId );

	/**
	 *	Read the derived SecUserEMConf record instance identified by the unique key UUuid6Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EMConfirmationUuid6	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecUserEMConf readDerivedByUUuid6Idx( ICFSecAuthorization Authorization,
		ICFLibUuid6 EMConfirmationUuid6 );

	/**
	 *	Read an array of the derived SecUserEMConf record instances identified by the duplicate key ConfEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFSecSecUserEMConf[] readDerivedByConfEMAddrIdx( ICFSecAuthorization Authorization,
		String ConfirmEMailAddr );

	/**
	 *	Read an array of the derived SecUserEMConf record instances identified by the duplicate key SentStampIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFSecSecUserEMConf[] readDerivedBySentStampIdx( ICFSecAuthorization Authorization,
		LocalDateTime EMailSentStamp );

	/**
	 *	Read an array of the derived SecUserEMConf record instances identified by the duplicate key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFSecSecUserEMConf[] readDerivedByNewAcctIdx( ICFSecAuthorization Authorization,
		boolean NewAccount );

	/**
	 *	Read the specific SecUserEMConf record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecUserEMConf instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf readRec( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ PKey );

	/**
	 *	Lock the specific SecUserEMConf record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecUserEMConf instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf lockRec( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ PKey );

	/**
	 *	Read all the specific SecUserEMConf record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecUserEMConf instances in the database accessible for the Authorization.
	 */
	ICFSecSecUserEMConf[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read a page of all the specific SecUserEMConf record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecUserEMConf instances in the database accessible for the Authorization.
	 */
	ICFSecSecUserEMConf[] pageAllRec( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Read the specific SecUserEMConf record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecUserId	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf readRecByIdIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecUserId );

	/**
	 *	Read the specific SecUserEMConf record instance identified by the unique key UUuid6Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EMConfirmationUuid6	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf readRecByUUuid6Idx( ICFSecAuthorization Authorization,
		ICFLibUuid6 EMConfirmationUuid6 );

	/**
	 *	Read an array of the specific SecUserEMConf record instances identified by the duplicate key ConfEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf[] readRecByConfEMAddrIdx( ICFSecAuthorization Authorization,
		String ConfirmEMailAddr );

	/**
	 *	Read an array of the specific SecUserEMConf record instances identified by the duplicate key SentStampIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf[] readRecBySentStampIdx( ICFSecAuthorization Authorization,
		LocalDateTime EMailSentStamp );

	/**
	 *	Read an array of the specific SecUserEMConf record instances identified by the duplicate key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf[] readRecByNewAcctIdx( ICFSecAuthorization Authorization,
		boolean NewAccount );

	/**
	 *	Read a page array of the specific SecUserEMConf record instances identified by the duplicate key ConfEMAddrIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf[] pageRecByConfEMAddrIdx( ICFSecAuthorization Authorization,
		String ConfirmEMailAddr,
		ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Read a page array of the specific SecUserEMConf record instances identified by the duplicate key SentStampIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf[] pageRecBySentStampIdx( ICFSecAuthorization Authorization,
		LocalDateTime EMailSentStamp,
		ICFLibKeyHash256 priorSecUserId );

	/**
	 *	Read a page array of the specific SecUserEMConf record instances identified by the duplicate key NewAcctIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecUserEMConf[] pageRecByNewAcctIdx( ICFSecAuthorization Authorization,
		boolean NewAccount,
		ICFLibKeyHash256 priorSecUserId );
}
