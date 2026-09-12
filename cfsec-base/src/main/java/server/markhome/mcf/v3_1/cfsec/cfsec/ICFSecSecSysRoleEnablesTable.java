
// Description: Java 25 DbIO interface for SecSysRoleEnables.

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
 *	CFSecSecSysRoleEnablesTable database interface for SecSysRoleEnables has CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecSecSysRoleEnablesTable
{
	public static final String TABLE_NAME = "SecSysRoleEnables";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFSecSecSysRoleEnables createSecSysRoleEnables( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnables rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFSecSecSysRoleEnables updateSecSysRoleEnables( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnables rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteSecSysRoleEnables( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnables rec );
	/**
	 *	Delete the SecSysRoleEnables instance identified by the primary key attributes.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleEnablesByIdIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 argSecSysRoleId,
		String argEnableName );
	/**
	 *	Delete the SecSysRoleEnables instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteSecSysRoleEnablesByIdIdx( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnablesPKey argKey );
	/**
	 *	Delete the SecSysRoleEnables instances identified by the key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleEnablesBySysRoleIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 argSecSysRoleId );

	/**
	 *	Delete the SecSysRoleEnables instances identified by the key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSecSysRoleEnablesBySysRoleIdx( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnablesBySysRoleIdxKey argKey );
	/**
	 *	Delete the SecSysRoleEnables instances identified by the key NameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 */
	void deleteSecSysRoleEnablesByNameIdx( ICFSecAuthorization Authorization,
		String argEnableName );

	/**
	 *	Delete the SecSysRoleEnables instances identified by the key NameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteSecSysRoleEnablesByNameIdx( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnablesByNameIdxKey argKey );


	/**
	 *	Read the derived SecSysRoleEnables record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleEnables instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecSysRoleEnables readDerived( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnablesPKey PKey );

	/**
	 *	Read the derived SecSysRoleEnables record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecSysRoleEnables readDerived( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Lock the derived SecSysRoleEnables record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleEnables instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecSysRoleEnables lockDerived( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnablesPKey PKey );

	/**
	 *	Read all SecSysRoleEnables instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFSecSecSysRoleEnables[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived SecSysRoleEnables record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecSecSysRoleEnables readDerivedByIdIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Read an array of the derived SecSysRoleEnables record instances identified by the duplicate key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFSecSecSysRoleEnables[] readDerivedBySysRoleIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Read an array of the derived SecSysRoleEnables record instances identified by the duplicate key NameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFSecSecSysRoleEnables[] readDerivedByNameIdx( ICFSecAuthorization Authorization,
		String EnableName );

	/**
	 *	Read the specific SecSysRoleEnables record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleEnables instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecSysRoleEnables readRec( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnablesPKey PKey );

	/**
	 *	Read the specific SecSysRoleEnables record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleEnables instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecSysRoleEnables readRec( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Lock the specific SecSysRoleEnables record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the SecSysRoleEnables instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecSysRoleEnables lockRec( ICFSecAuthorization Authorization,
		ICFSecSecSysRoleEnablesPKey PKey );

	/**
	 *	Read all the specific SecSysRoleEnables record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecSysRoleEnables instances in the database accessible for the Authorization.
	 */
	ICFSecSecSysRoleEnables[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read a page of all the specific SecSysRoleEnables record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecSysRoleEnables instances in the database accessible for the Authorization.
	 */
	ICFSecSecSysRoleEnables[] pageAllRec( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName );

	/**
	 *	Read the specific SecSysRoleEnables record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecSysRoleEnables readRecByIdIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		String EnableName );

	/**
	 *	Read an array of the specific SecSysRoleEnables record instances identified by the duplicate key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecSysRoleEnables[] readRecBySysRoleIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId );

	/**
	 *	Read an array of the specific SecSysRoleEnables record instances identified by the duplicate key NameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecSysRoleEnables[] readRecByNameIdx( ICFSecAuthorization Authorization,
		String EnableName );

	/**
	 *	Read a page array of the specific SecSysRoleEnables record instances identified by the duplicate key SysRoleIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecSysRoleEnables[] pageRecBySysRoleIdx( ICFSecAuthorization Authorization,
		ICFLibKeyHash256 SecSysRoleId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName );

	/**
	 *	Read a page array of the specific SecSysRoleEnables record instances identified by the duplicate key NameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecSecSysRoleEnables[] pageRecByNameIdx( ICFSecAuthorization Authorization,
		String EnableName,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName );
}
