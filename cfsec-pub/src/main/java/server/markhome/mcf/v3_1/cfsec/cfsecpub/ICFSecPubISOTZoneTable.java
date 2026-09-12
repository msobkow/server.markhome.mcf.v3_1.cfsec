
// Description: Java 25 public DbIO interface for ISOTZone.

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
 *	CFSecPubISOTZoneTable public database interface for ISOTZone has CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFSecPubISOTZoneTable
{
	public static final String TABLE_NAME = "ISOTZone";

	/**
	 *	Create the instance in the database, and update the specified record
	 *	with the assigned primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be created.
	 */
	public ICFSecPubISOTZone pubcreateISOTZone( ICFSecPubAuthorization Authorization,
		ICFSecPubISOTZone rec );


	/**
	 *	Update the instance in the database, and update the specified record
	 *	with any calculated changes imposed by the associated stored procedure.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be updated
	 */
	public ICFSecPubISOTZone pubupdateISOTZone( ICFSecPubAuthorization Authorization,
		ICFSecPubISOTZone rec );


	/**
	 *	Delete the instance from the database.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	rec	The instance interface to be deleted.
	 */
	public void pubdeleteISOTZone( ICFSecPubAuthorization Authorization,
		ICFSecPubISOTZone rec );
	/**
	 *	Delete the ISOTZone instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The primary key identifying the instance to be deleted.
	 */
	public void pubdeleteISOTZoneByIdIdx( ICFSecPubAuthorization Authorization,
		Short argKey );
	/**
	 *	Delete the ISOTZone instances identified by the key OffsetIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TZHourOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@param	TZMinOffset	The ISOTZone key attribute of the instance generating the id.
	 */
	public void pubdeleteISOTZoneByOffsetIdx( ICFSecPubAuthorization Authorization,
		short argTZHourOffset,
		short argTZMinOffset );

	/**
	 *	Delete the ISOTZone instances identified by the key OffsetIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void pubdeleteISOTZoneByOffsetIdx( ICFSecPubAuthorization Authorization,
		ICFSecPubISOTZoneByOffsetIdxKey argKey );
	/**
	 *	Delete the ISOTZone instances identified by the key UTZNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TZName	The ISOTZone key attribute of the instance generating the id.
	 */
	public void pubdeleteISOTZoneByUTZNameIdx( ICFSecPubAuthorization Authorization,
		String argTZName );

	/**
	 *	Delete the ISOTZone instances identified by the key UTZNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void pubdeleteISOTZoneByUTZNameIdx( ICFSecPubAuthorization Authorization,
		ICFSecPubISOTZoneByUTZNameIdxKey argKey );
	/**
	 *	Delete the ISOTZone instances identified by the key Iso8601Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Iso8601	The ISOTZone key attribute of the instance generating the id.
	 */
	public void pubdeleteISOTZoneByIso8601Idx( ICFSecPubAuthorization Authorization,
		String argIso8601 );

	/**
	 *	Delete the ISOTZone instances identified by the key Iso8601Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argKey	The key identifying the instances to be deleted.
	 */
	public void pubdeleteISOTZoneByIso8601Idx( ICFSecPubAuthorization Authorization,
		ICFSecPubISOTZoneByIso8601IdxKey argKey );


	/**
	 *	Read the derived ISOTZone record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ISOTZone instance to be read.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecPubISOTZone pubreadDerived( ICFSecPubAuthorization Authorization,
		Short PKey );

	/**
	 *	Lock the derived ISOTZone record instance by primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ISOTZone instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecPubISOTZone publockDerived( ICFSecPubAuthorization Authorization,
		Short PKey );

	/**
	 *	Read all ISOTZone instances.
	 *
	 *	@param	Authorization	The session authorization information.	
	 *
	 *	@return An array of derived record instances, potentially with 0 elements in the set.
	 */
	public ICFSecPubISOTZone[] pubreadPubAllDerived( ICFSecPubAuthorization Authorization );

	/**
	 *	Read the derived ISOTZone record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISOTZoneId	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecPubISOTZone pubreadDerivedByIdIdx( ICFSecPubAuthorization Authorization,
		short ISOTZoneId );

	/**
	 *	Read an array of the derived ISOTZone record instances identified by the duplicate key OffsetIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TZHourOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@param	TZMinOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	public ICFSecPubISOTZone[] pubreadDerivedByOffsetIdx( ICFSecPubAuthorization Authorization,
		short TZHourOffset,
		short TZMinOffset );

	/**
	 *	Read the derived ISOTZone record instance identified by the unique key UTZNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TZName	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 */
	public ICFSecPubISOTZone pubreadDerivedByUTZNameIdx( ICFSecPubAuthorization Authorization,
		String TZName );

	/**
	 *	Read an array of the derived ISOTZone record instances identified by the duplicate key Iso8601Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Iso8601	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return An array of derived instances for the specified key, potentially with 0 elements in the set.
	 */
	public ICFSecPubISOTZone[] pubreadDerivedByIso8601Idx( ICFSecPubAuthorization Authorization,
		String Iso8601 );

	/**
	 *	Read the specific ISOTZone record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ISOTZone instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOTZone pubreadRec( ICFSecPubAuthorization Authorization,
		Short PKey );

	/**
	 *	Lock the specific ISOTZone record instance identified by the primary key.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	PKey	The primary key of the ISOTZone instance to be locked.
	 *
	 *	@return The record instance for the specified primary key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOTZone publockRec( ICFSecPubAuthorization Authorization,
		Short PKey );

	/**
	 *	Read all the specific ISOTZone record instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific ISOTZone instances in the database accessible for the Authorization.
	 */
	public ICFSecPubISOTZone[] pubreadAllRec( ICFSecPubAuthorization Authorization );

	/**
	 *	Read the specific ISOTZone record instance identified by the unique key IdIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	ISOTZoneId	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOTZone pubreadRecByIdIdx( ICFSecPubAuthorization Authorization,
		short ISOTZoneId );

	/**
	 *	Read an array of the specific ISOTZone record instances identified by the duplicate key OffsetIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TZHourOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@param	TZMinOffset	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOTZone[] pubreadRecByOffsetIdx( ICFSecPubAuthorization Authorization,
		short TZHourOffset,
		short TZMinOffset );

	/**
	 *	Read the specific ISOTZone record instance identified by the unique key UTZNameIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	TZName	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return The record instance for the specified key, or null if there is
	 *		no such existing key value.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOTZone pubreadRecByUTZNameIdx( ICFSecPubAuthorization Authorization,
		String TZName );

	/**
	 *	Read an array of the specific ISOTZone record instances identified by the duplicate key Iso8601Idx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	Iso8601	The ISOTZone key attribute of the instance generating the id.
	 *
	 *	@return An array of derived record instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOTZone[] pubreadRecByIso8601Idx( ICFSecPubAuthorization Authorization,
		String Iso8601 );
}
