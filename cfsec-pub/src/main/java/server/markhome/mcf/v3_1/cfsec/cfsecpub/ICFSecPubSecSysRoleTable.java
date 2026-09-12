
// Description: Java 25 public DbIO interface for SecSysRole.

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

package server.markhome.mcf.v3_1.cfsec.cfsecpub;

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
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

/*
 *	CFSecPubSecSysRoleTable public database interface for SecSysRole has CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFSecPubSecSysRoleTable
{
	public static final String TABLE_NAME = "SecSysRole";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	public ICFSecPubSecSysRole pubcreateSecSysRole( ICFSecPubAuthorization Authorization,
		ICFSecPubSecSysRole rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	public ICFSecPubSecSysRole pubupdateSecSysRole( ICFSecPubAuthorization Authorization,
		ICFSecPubSecSysRole rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	public void pubdeleteSecSysRole( ICFSecPubAuthorization Authorization,
		ICFSecPubSecSysRole rec );
	/**
	 *	Delete the SecSysRole instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	public void pubdeleteSecSysRoleByIdIdx( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 argKey );
	/**
	 *	Delete the SecSysRole instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 */
	public void pubdeleteSecSysRoleByUNameIdx( ICFSecPubAuthorization Authorization,
		String argName );

	/**
	 *	Delete the SecSysRole instances identified by the key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void pubdeleteSecSysRoleByUNameIdx( ICFSecPubAuthorization Authorization,
		ICFSecPubSecSysRoleByUNameIdxKey argKey );


	/**
	 *	Read the derived SecSysRole record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRole instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecPubSecSysRole pubreadDerived( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 PKey );

	/**
	 *	Lock the derived SecSysRole record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRole instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecPubSecSysRole publockDerived( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 PKey );

	/**
	 *	Read all SecSysRole instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	public ICFSecPubSecSysRole[] pubreadPubAllDerived( ICFSecPubAuthorization Authorization );

	/**
	 *	Read the derived SecSysRole record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecPubSecSysRole pubreadDerivedByIdIdx( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 SecSysRoleId );

	/**
	 *	Read the derived SecSysRole record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecPubSecSysRole pubreadDerivedByUNameIdx( ICFSecPubAuthorization Authorization,
		String Name );

	/**
	 *	Read the specific SecSysRole record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRole instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSecSysRole pubreadRec( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 PKey );

	/**
	 *	Lock the specific SecSysRole record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRole instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSecSysRole publockRec( ICFSecPubAuthorization Authorization,
		ICFLibKeyHash256 PKey );

	/**
	 *	Read all the specific SecSysRole record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecSysRole instances in the database accessible for the Authorization.
	 */
	public ICFSecPubSecSysRole[] pubreadAllRec( ICFSecPubAuthorization Authorization );

	/**
	 *	Read the specific SecSysRole record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSecSysRole pubreadRecByIdIdx( ICFSecPubAuthorization Authorization,
		CFLibDbKeyHash256 SecSysRoleId );

	/**
	 *	Read the specific SecSysRole record instance identified by the unique key UNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Name	The SecSysRole key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSecSysRole pubreadRecByUNameIdx( ICFSecPubAuthorization Authorization,
		String Name );
}
