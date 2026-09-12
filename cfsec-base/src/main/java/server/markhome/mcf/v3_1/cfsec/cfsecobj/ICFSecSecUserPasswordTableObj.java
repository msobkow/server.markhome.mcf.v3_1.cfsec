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

public interface ICFSecSecUserPasswordTableObj
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
	 *	Instantiate a new SecUserPassword instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecSecUserPasswordObj newInstance();

	/**
	 *	Instantiate a new SecUserPassword edition of the specified SecUserPassword instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecSecUserPasswordEditObj newEditInstance( ICFSecSecUserPasswordObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserPasswordObj realiseSecUserPassword( ICFSecSecUserPasswordObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserPasswordObj createSecUserPassword( ICFSecSecUserPasswordObj Obj );

	/**
	 *	Read a SecUserPassword-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUserPassword-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecUserPasswordObj readSecUserPassword( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a SecUserPassword-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SecUserPassword-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecSecUserPasswordObj readSecUserPassword( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFSecSecUserPasswordObj readCachedSecUserPassword( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeSecUserPassword( ICFSecSecUserPasswordObj obj );

	void deepDisposeSecUserPassword( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserPasswordObj lockSecUserPassword( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the SecUserPassword-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserPasswordObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecUserPasswordObj> readAllSecUserPassword();

	/**
	 *	Return a sorted map of all the SecUserPassword-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserPasswordObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecSecUserPasswordObj> readAllSecUserPassword( boolean forceRead );

	List<ICFSecSecUserPasswordObj> readCachedAllSecUserPassword();

	/**
	 *	Get the CFSecSecUserPasswordObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUserPassword key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserPasswordObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserPasswordObj readSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Get the CFSecSecUserPasswordObj instance for the primary key attributes.
	 *
	 *	@param	SecUserId	The SecUserPassword key attribute of the instance generating the id.
	 *
	 *	@return	CFSecSecUserPasswordObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecSecUserPasswordObj readSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId,
		boolean forceRead );

	/**
	 *	Get the map of CFSecSecUserPasswordObj instances sorted by their primary keys for the duplicate SetStampIdx key.
	 *
	 *	@param	PWSetStamp	The SecUserPassword key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserPasswordObj cached instances sorted by their primary keys for the duplicate SetStampIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserPasswordObj> readSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp );

	/**
	 *	Get the map of CFSecSecUserPasswordObj instances sorted by their primary keys for the duplicate SetStampIdx key.
	 *
	 *	@param	PWSetStamp	The SecUserPassword key attribute of the instance generating the id.
	 *
	 *	@return	List of CFSecSecUserPasswordObj cached instances sorted by their primary keys for the duplicate SetStampIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecSecUserPasswordObj> readSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp,
		boolean forceRead );

	ICFSecSecUserPasswordObj readCachedSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId );

	List<ICFSecSecUserPasswordObj> readCachedSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp );

	void deepDisposeSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId );

	void deepDisposeSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp );

	/**
	 *	Internal use only.
	 */
	ICFSecSecUserPasswordObj updateSecUserPassword( ICFSecSecUserPasswordObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSecUserPassword( ICFSecSecUserPasswordObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	SecUserId	The SecUserPassword key attribute of the instance generating the id.
	 */
	void deleteSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PWSetStamp	The SecUserPassword key attribute of the instance generating the id.
	 */
	void deleteSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp );
}
