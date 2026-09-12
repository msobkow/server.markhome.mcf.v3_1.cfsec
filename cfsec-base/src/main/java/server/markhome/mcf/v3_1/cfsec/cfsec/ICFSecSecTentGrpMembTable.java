
// Description: Java 25 DbIO interface for SecTentGrpMemb.

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
 *	CFSecSecTentGrpMembTable database interface for SecTentGrpMemb has CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecSecTentGrpMembTable
{
	public static final String TABLE_NAME = "SecTentGrpMemb";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFSecSecTentGrpMemb createSecTentGrpMemb( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMemb rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFSecSecTentGrpMemb updateSecTentGrpMemb( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMemb rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteSecTentGrpMemb( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMemb rec );
	/**
	 *	Delete the SecTentGrpMemb instance identified by the primary key attributes.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpMembByIdIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 argSecTentGrpId,
		String argLoginId );
	/**
	 *	Delete the SecTentGrpMemb instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteSecTentGrpMembByIdIdx( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMembPKey argKey );
	/**
	 *	Delete the SecTentGrpMemb instances identified by the key TentGrpIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpMembByTentGrpIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 argSecTentGrpId );

	/**
	 *	Delete the SecTentGrpMemb instances identified by the key TentGrpIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSecTentGrpMembByTentGrpIdx( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMembByTentGrpIdxKey argKey );
	/**
	 *	Delete the SecTentGrpMemb instances identified by the key UserIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 */
	void deleteSecTentGrpMembByUserIdx( ICFSecAuthorization Authorization,
		String argLoginId );

	/**
	 *	Delete the SecTentGrpMemb instances identified by the key UserIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSecTentGrpMembByUserIdx( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMembByUserIdxKey argKey );


	/**
	 *	Read the derived SecTentGrpMemb record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecTentGrpMemb instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecTentGrpMemb readDerived( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMembPKey PKey );

	/**
	 *	Read the derived SecTentGrpMemb record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecTentGrpMemb readDerived( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Lock the derived SecTentGrpMemb record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecTentGrpMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecTentGrpMemb lockDerived( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMembPKey PKey );

	/**
	 *	Read all SecTentGrpMemb instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFSecSecTentGrpMemb[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived SecTentGrpMemb record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecTentGrpMemb readDerivedByIdIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Read an array of the derived SecTentGrpMemb record instances identified by the duplicate key TentGrpIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFSecSecTentGrpMemb[] readDerivedByTentGrpIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecTentGrpId );

	/**
	 *	Read an array of the derived SecTentGrpMemb record instances identified by the duplicate key UserIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFSecSecTentGrpMemb[] readDerivedByUserIdx( ICFSecAuthorization Authorization,
		String LoginId );

	/**
	 *	Read the specific SecTentGrpMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecTentGrpMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecTentGrpMemb readRec( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMembPKey PKey );

	/**
	 *	Read the specific SecTentGrpMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecTentGrpMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecTentGrpMemb readRec( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Lock the specific SecTentGrpMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecTentGrpMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecTentGrpMemb lockRec( ICFSecAuthorization Authorization,
		ICFSecSecTentGrpMembPKey PKey );

	/**
	 *	Read all the specific SecTentGrpMemb record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecTentGrpMemb instances in the database accessible for the Authorization.
	 */
	ICFSecSecTentGrpMemb[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read a page of all the specific SecTentGrpMemb record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecTentGrpMemb instances in the database accessible for the Authorization.
	 */
	ICFSecSecTentGrpMemb[] pageAllRec( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 priorSecTentGrpId,
		String priorLoginId );

	/**
	 *	Read the specific SecTentGrpMemb record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecTentGrpMemb readRecByIdIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecTentGrpId,
		String LoginId );

	/**
	 *	Read an array of the specific SecTentGrpMemb record instances identified by the duplicate key TentGrpIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecTentGrpMemb[] readRecByTentGrpIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecTentGrpId );

	/**
	 *	Read an array of the specific SecTentGrpMemb record instances identified by the duplicate key UserIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecTentGrpMemb[] readRecByUserIdx( ICFSecAuthorization Authorization,
		String LoginId );

	/**
	 *	Read a page array of the specific SecTentGrpMemb record instances identified by the duplicate key TentGrpIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecTentGrpMemb[] pageRecByTentGrpIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecTentGrpId,
		ICFLibKeyHash256 priorSecTentGrpId,
		String priorLoginId );

	/**
	 *	Read a page array of the specific SecTentGrpMemb record instances identified by the duplicate key UserIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecTentGrpMemb[] pageRecByUserIdx( ICFSecAuthorization Authorization,
		String LoginId,
		ICFLibKeyHash256 priorSecTentGrpId,
		String priorLoginId );
}
