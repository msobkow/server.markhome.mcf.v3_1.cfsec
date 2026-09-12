
// Description: Java 25 DbIO interface for ISOLang.

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
 *	CFSecISOLangTable database interface for ISOLang has CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFSecISOLangTable
{
	public static final String TABLE_NAME = "ISOLang";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	ICFSecISOLang createISOLang( ICFSecAuthorization Authorization,
		ICFSecISOLang rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	ICFSecISOLang updateISOLang( ICFSecAuthorization Authorization,
		ICFSecISOLang rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	void deleteISOLang( ICFSecAuthorization Authorization,
		ICFSecISOLang rec );
	/**
	 *	Delete the ISOLang instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	void deleteISOLangByIdIdx( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ argKey );
	/**
	 *	Delete the ISOLang instances identified by the key Code3Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISO6392Code	The ISOLang key attribute of the instance generating the id.
	 */
	void deleteISOLangByCode3Idx( ICFSecAuthorization Authorization,
		String argISO6392Code );

	/**
	 *	Delete the ISOLang instances identified by the key Code3Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteISOLangByCode3Idx( ICFSecAuthorization Authorization,
		ICFSecISOLangByCode3IdxKey argKey );
	/**
	 *	Delete the ISOLang instances identified by the key Code2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISO6391Code	The ISOLang key attribute of the instance generating the id.
	 */
	void deleteISOLangByCode2Idx( ICFSecAuthorization Authorization,
		String argISO6391Code );

	/**
	 *	Delete the ISOLang instances identified by the key Code2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	void deleteISOLangByCode2Idx( ICFSecAuthorization Authorization,
		ICFSecISOLangByCode2IdxKey argKey );


	/**
	 *	Read the derived ISOLang record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ISOLang instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecISOLang readDerived( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ PKey );

	/**
	 *	Lock the derived ISOLang record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ISOLang instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecISOLang lockDerived( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ PKey );

	/**
	 *	Read all ISOLang instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	ICFSecISOLang[] readAllDerived( ICFSecAuthorization Authorization );

	/**
	 *	Read the derived ISOLang record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISOLangId	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecISOLang readDerivedByIdIdx( ICFSecAuthorization Authorization,
		short ISOLangId );

	/**
	 *	Read the derived ISOLang record instance identified by the unique key Code3Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISO6392Code	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	ICFSecISOLang readDerivedByCode3Idx( ICFSecAuthorization Authorization,
		String ISO6392Code );

	/**
	 *	Read an array of the derived ISOLang record instances identified by the duplicate key Code2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISO6391Code	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	ICFSecISOLang[] readDerivedByCode2Idx( ICFSecAuthorization Authorization,
		String ISO6391Code );

	/**
	 *	Read the specific ISOLang record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ISOLang instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecISOLang readRec( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ PKey );

	/**
	 *	Lock the specific ISOLang record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ISOLang instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecISOLang lockRec( ICFSecAuthorization Authorization,
		$implCommaIJavaOptAtomType$ PKey );

	/**
	 *	Read all the specific ISOLang record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific ISOLang instances in the database accessible for the Authorization.
	 */
	ICFSecISOLang[] readAllRec( ICFSecAuthorization Authorization );

	/**
	 *	Read the specific ISOLang record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISOLangId	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecISOLang readRecByIdIdx( ICFSecAuthorization Authorization,
		short ISOLangId );

	/**
	 *	Read the specific ISOLang record instance identified by the unique key Code3Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISO6392Code	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecISOLang readRecByCode3Idx( ICFSecAuthorization Authorization,
		String ISO6392Code );

	/**
	 *	Read an array of the specific ISOLang record instances identified by the duplicate key Code2Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISO6391Code	The ISOLang key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	ICFSecISOLang[] readRecByCode2Idx( ICFSecAuthorization Authorization,
		String ISO6391Code );
}
