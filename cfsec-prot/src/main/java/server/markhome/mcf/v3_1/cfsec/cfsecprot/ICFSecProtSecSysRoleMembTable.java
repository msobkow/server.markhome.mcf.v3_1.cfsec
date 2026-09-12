
// Description: Java 25 protlic DbIO interface for SecSysRoleMemb.

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
 *	CFSecProtSecSysRoleMembTable protlic database interface for SecSysRoleMemb has CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecProtSecSysRoleMembTable
{
	public static final String TABLE_NAME = "SecSysRoleMemb";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	public ICFSecProtSecSysRoleMemb protcreateSecSysRoleMemb( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMemb rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	public ICFSecProtSecSysRoleMemb protupdateSecSysRoleMemb( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMemb rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	public void protdeleteSecSysRoleMemb( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMemb rec );
	/**
	 *	Delete the SecSysRoleMemb instance identified by the primary key attributes.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 */
	public void protdeleteSecSysRoleMembByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 argSecSysRoleId,
		String argLoginId );
	/**
	 *	Delete the SecSysRoleMemb instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	public void protdeleteSecSysRoleMembByIdIdx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMembPKey argKey );
	/**
	 *	Delete the SecSysRoleMemb instances identified by the key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 */
	public void protdeleteSecSysRoleMembBySysRoleIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 argSecSysRoleId );

	/**
	 *	Delete the SecSysRoleMemb instances identified by the key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void protdeleteSecSysRoleMembBySysRoleIdx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMembBySysRoleIdxKey argKey );
	/**
	 *	Delete the SecSysRoleMemb instances identified by the key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 */
	public void protdeleteSecSysRoleMembByLoginIdx( ICFSecPubAuthorization Authorization,
		String argLoginId );

	/**
	 *	Delete the SecSysRoleMemb instances identified by the key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void protdeleteSecSysRoleMembByLoginIdx( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMembByLoginIdxKey argKey );


	/**
	 *	Read the derived SecSysRoleMemb record instance by protected primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleMemb instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecSysRoleMemb protreadDerived( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMembPKey PKey );

	/**
	 *	Read the derived SecSysRoleMemb record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecSysRoleMemb protreadDerived( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Lock the derived SecSysRoleMemb record instance by protected primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecSysRoleMemb protlockDerived( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMembPKey PKey );

	/**
	 *	Read all SecSysRoleMemb instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecSysRoleMemb[] protreadProtAllDerived( ICFSecPubAuthorization Authorization );

	/**
	 *	Read the derived SecSysRoleMemb record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecProtSecSysRoleMemb protreadDerivedByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Read an array of the derived SecSysRoleMemb record instances identified by the duplicate key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecSysRoleMemb[] protreadDerivedBySysRoleIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Read an array of the derived SecSysRoleMemb record instances identified by the duplicate key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	public ICFSecProtSecSysRoleMemb[] protreadDerivedByLoginIdx( ICFSecPubAuthorization Authorization,
		String LoginId );

	/**
	 *	Read the specific SecSysRoleMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecSysRoleMemb protreadRec( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMembPKey PKey );

	/**
	 *	Read the specific SecSysRoleMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecSysRoleMemb protreadRec( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Lock the specific SecSysRoleMemb record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleMemb instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecSysRoleMemb protlockRec( ICFSecPubAuthorization Authorization,
		ICFSecProtSecSysRoleMembPKey PKey );

	/**
	 *	Read all the specific SecSysRoleMemb record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecSysRoleMemb instances in the database accessible for the Authorization.
	 */
	public ICFSecProtSecSysRoleMemb[] protreadAllRec( ICFSecPubAuthorization Authorization );

	/**
	 *	Read a page of all the specific SecSysRoleMemb record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecSysRoleMemb instances in the database accessible for the Authorization.
	 */
	public ICFSecProtSecSysRoleMemb[] protpageAllRec( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 priorSecSysRoleId,
		String priorLoginId );

	/**
	 *	Read the specific SecSysRoleMemb record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecSysRoleMemb protreadRecByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		String LoginId );

	/**
	 *	Read an array of the specific SecSysRoleMemb record instances identified by the duplicate key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecSysRoleMemb[] protreadRecBySysRoleIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Read an array of the specific SecSysRoleMemb record instances identified by the duplicate key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecSysRoleMemb[] protreadRecByLoginIdx( ICFSecPubAuthorization Authorization,
		String LoginId );

	/**
	 *	Read a page array of the specific SecSysRoleMemb record instances identified by the duplicate key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecSysRoleMemb[] protpageRecBySysRoleIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorLoginId );

	/**
	 *	Read a page array of the specific SecSysRoleMemb record instances identified by the duplicate key LoginIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecProtSecSysRoleMemb[] protpageRecByLoginIdx( ICFSecPubAuthorization Authorization,
		String LoginId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorLoginId );
}
