
// Description: Java 25 protlic DbIO interface for SecClusRoleMemb.

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
 *	CFSecProtSecClusRoleMembTable protlic database interface for SecClusRoleMemb has CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecProtSecClusRoleMembTable
{
	public static final String TABLE_NAME = "SecClusRoleMemb";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	public ICFSecProtSecClusRoleMemb protcreateSecClusRoleMemb( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMemb rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	public ICFSecProtSecClusRoleMemb protupdateSecClusRoleMemb( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMemb rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	public void protdeleteSecClusRoleMemb( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMemb rec );
	/**
	 *	Delete the SecClusRoleMemb instance identified by the primary key attributes.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 */
	public void protdeleteSecClusRoleMembByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 argSecClusRoleId,
		String argLoginId );
	/**
	 *	Delete the SecClusRoleMemb instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	public void protdeleteSecClusRoleMembByIdIdx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMembPKey argKey );
	/**
	 *	Delete the SecClusRoleMemb instances identified by the key ClusRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 */
	public void protdeleteSecClusRoleMembByClusRoleIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 argSecClusRoleId );

	/**
	 *	Delete the SecClusRoleMemb instances identified by the key ClusRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void protdeleteSecClusRoleMembByClusRoleIdx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMembByClusRoleIdxKey argKey );
	/**
	 *	Delete the SecClusRoleMemb instances identified by the key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 */
	public void protdeleteSecClusRoleMembByLoginIdx( ICFSecPubAuthorization Authorization,
		String argLoginId );

	/**
	 *	Delete the SecClusRoleMemb instances identified by the key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void protdeleteSecClusRoleMembByLoginIdx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMembByLoginIdxKey argKey );


	/**
	 *	Read the derived SecClusRoleMemb record instance by protected primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecClusRoleMemb instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecClusRoleMemb protreadDerived( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMembPKey PKey );

	/**
	 *	Read the derived SecClusRoleMemb record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecClusRoleMemb protreadDerived( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Lock the derived SecClusRoleMemb record instance by protected primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecClusRoleMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecClusRoleMemb protlockDerived( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMembPKey PKey );

	/**
	 *	Read all SecClusRoleMemb instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecClusRoleMemb[] protreadProtAllDerived( ICFSecPubAuthorization Authorization );

	/**
	 *	Read the derived SecClusRoleMemb record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecClusRoleMemb protreadDerivedByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Read an array of the derived SecClusRoleMemb record instances identified by the duplicate key ClusRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecClusRoleMemb[] protreadDerivedByClusRoleIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecClusRoleId );

	/**
	 *	Read an array of the derived SecClusRoleMemb record instances identified by the duplicate key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecClusRoleMemb[] protreadDerivedByLoginIdx( ICFSecPubAuthorization Authorization,
		String LoginId );

	/**
	 *	Read the specific SecClusRoleMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecClusRoleMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecClusRoleMemb protreadRec( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMembPKey PKey );

	/**
	 *	Read the specific SecClusRoleMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecClusRoleMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecClusRoleMemb protreadRec( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Lock the specific SecClusRoleMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecClusRoleMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecClusRoleMemb protlockRec( ICFSecPubAuthorization Authorization,
		ICFSecProtSecClusRoleMembPKey PKey );

	/**
	 *	Read all the specific SecClusRoleMemb record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecClusRoleMemb instances in the database accessible for the Authorization.
	 */
	public ICFSecProtSecClusRoleMemb[] protreadAllRec( ICFSecPubAuthorization Authorization );

	/**
	 *	Read a page of all the specific SecClusRoleMemb record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecClusRoleMemb instances in the database accessible for the Authorization.
	 */
	public ICFSecProtSecClusRoleMemb[] protpageAllRec( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 priorSecClusRoleId,
		String priorLoginId );

	/**
	 *	Read the specific SecClusRoleMemb record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecClusRoleMemb protreadRecByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecClusRoleId,
		String LoginId );

	/**
	 *	Read an array of the specific SecClusRoleMemb record instances identified by the duplicate key ClusRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecClusRoleMemb[] protreadRecByClusRoleIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecClusRoleId );

	/**
	 *	Read an array of the specific SecClusRoleMemb record instances identified by the duplicate key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecClusRoleMemb[] protreadRecByLoginIdx( ICFSecPubAuthorization Authorization,
		String LoginId );

	/**
	 *	Read a page array of the specific SecClusRoleMemb record instances identified by the duplicate key ClusRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecClusRoleMemb[] protpageRecByClusRoleIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecClusRoleId,
		ICFLibKeyHash256 priorSecClusRoleId,
		String priorLoginId );

	/**
	 *	Read a page array of the specific SecClusRoleMemb record instances identified by the duplicate key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecClusRoleMemb[] protpageRecByLoginIdx( ICFSecPubAuthorization Authorization,
		String LoginId,
		ICFLibKeyHash256 priorSecClusRoleId,
		String priorLoginId );
}
