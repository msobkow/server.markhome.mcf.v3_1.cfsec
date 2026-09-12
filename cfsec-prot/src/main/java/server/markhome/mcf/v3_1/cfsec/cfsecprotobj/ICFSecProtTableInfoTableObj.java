// Description: Java 25 Protected Table Object interface for CFSecProt.

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

package server.markhome.mcf.v3_1.cfsec.cfsecprotobj;

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
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public interface ICFSecProtTableInfoTableObj
{
	public ICFSecProtSchemaObj getSchema();
	public void setSchema( ICFSecProtSchemaObj value );

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
	 *	Instantiate a new TableInfo instance.
	 *
	 *	@return	A new instance.
	 */
	ICFSecProtTableInfoObj newInstance();

	/**
	 *	Instantiate a new TableInfo edition of the specified TableInfo instance.
	 *
	 *	@return	A new edition.
	 */
	ICFSecProtTableInfoEditObj newEditInstance( ICFSecProtTableInfoObj orig );

	/**
	 *	Internal use only.
	 */
	ICFSecProtTableInfoObj realiseTableInfo( ICFSecProtTableInfoObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFSecProtTableInfoObj createTableInfo( ICFSecProtTableInfoObj Obj );

	/**
	 *	Read a TableInfo-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TableInfo-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtTableInfoObj readTableInfo( Integer pkey );

	/**
	 *	Read a TableInfo-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TableInfo-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFSecProtTableInfoObj readTableInfo( Integer pkey,
		boolean forceRead );

	ICFSecProtTableInfoObj readCachedTableInfo( Integer pkey );

	public void reallyDeepDisposeTableInfo( ICFSecProtTableInfoObj obj );

	void deepDisposeTableInfo( Integer pkey );

	/**
	 *	Internal use only.
	 */
	ICFSecProtTableInfoObj lockTableInfo( Integer pkey );

	/**
	 *	Return a sorted list of all the TableInfo-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtTableInfoObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtTableInfoObj> readAllTableInfo();

	/**
	 *	Return a sorted map of all the TableInfo-derived instances in the database.
	 *
	 *	@return	List of ICFSecProtTableInfoObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFSecProtTableInfoObj> readAllTableInfo( boolean forceRead );

	List<ICFSecProtTableInfoObj> readCachedAllTableInfo();

	/**
	 *	Get the ICFSecProtTableInfoObj instance for the primary key attributes.
	 *
	 *	@param	TableInfoId	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTableInfoObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTableInfoObj readTableInfoByIdIdx( int TableInfoId );

	/**
	 *	Get the ICFSecProtTableInfoObj instance for the primary key attributes.
	 *
	 *	@param	TableInfoId	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTableInfoObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTableInfoObj readTableInfoByIdIdx( int TableInfoId,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtTableInfoObj instance for the unique TableNameIdx key.
	 *
	 *	@param	TableName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTableInfoObj cached instance for the unique TableNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTableInfoObj readTableInfoByTableNameIdx(String TableName );

	/**
	 *	Get the ICFSecProtTableInfoObj instance for the unique TableNameIdx key.
	 *
	 *	@param	TableName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTableInfoObj refreshed instance for the unique TableNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTableInfoObj readTableInfoByTableNameIdx(String TableName,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtTableInfoObj> instances sorted by their primary keys for the duplicate SuperNameIdx key.
	 *
	 *	@param	SuperName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtTableInfoObj> cached instances sorted by their primary keys for the duplicate SuperNameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtTableInfoObj> readTableInfoBySuperNameIdx( String SuperName );

	/**
	 *	Get the map of List<ICFSecProtTableInfoObj> instances sorted by their primary keys for the duplicate SuperNameIdx key.
	 *
	 *	@param	SuperName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtTableInfoObj> cached instances sorted by their primary keys for the duplicate SuperNameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtTableInfoObj> readTableInfoBySuperNameIdx( String SuperName,
		boolean forceRead );

	/**
	 *	Get the map of List<ICFSecProtTableInfoObj> instances sorted by their primary keys for the duplicate SchemaNameIdx key.
	 *
	 *	@param	SchemaName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtTableInfoObj> cached instances sorted by their primary keys for the duplicate SchemaNameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtTableInfoObj> readTableInfoBySchemaNameIdx( String SchemaName );

	/**
	 *	Get the map of List<ICFSecProtTableInfoObj> instances sorted by their primary keys for the duplicate SchemaNameIdx key.
	 *
	 *	@param	SchemaName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	List of List<ICFSecProtTableInfoObj> cached instances sorted by their primary keys for the duplicate SchemaNameIdx key,
	 *		which may be an empty set.
	 */
	List<ICFSecProtTableInfoObj> readTableInfoBySchemaNameIdx( String SchemaName,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtTableInfoObj instance for the unique SchemaBkCodeIdx key.
	 *
	 *	@param	SchemaName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@param	BackingClassCode	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTableInfoObj cached instance for the unique SchemaBkCodeIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTableInfoObj readTableInfoBySchemaBkCodeIdx(String SchemaName,
		int BackingClassCode );

	/**
	 *	Get the ICFSecProtTableInfoObj instance for the unique SchemaBkCodeIdx key.
	 *
	 *	@param	SchemaName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@param	BackingClassCode	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTableInfoObj refreshed instance for the unique SchemaBkCodeIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTableInfoObj readTableInfoBySchemaBkCodeIdx(String SchemaName,
		int BackingClassCode,
		boolean forceRead );

	/**
	 *	Get the ICFSecProtTableInfoObj instance for the unique SchemaRTCodeIdx key.
	 *
	 *	@param	RuntimeClassCode	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTableInfoObj cached instance for the unique SchemaRTCodeIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTableInfoObj readTableInfoBySchemaRTCodeIdx(int RuntimeClassCode );

	/**
	 *	Get the ICFSecProtTableInfoObj instance for the unique SchemaRTCodeIdx key.
	 *
	 *	@param	RuntimeClassCode	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@return	ICFSecProtTableInfoObj refreshed instance for the unique SchemaRTCodeIdx key, or
	 *		null if no such instance exists.
	 */
	ICFSecProtTableInfoObj readTableInfoBySchemaRTCodeIdx(int RuntimeClassCode,
		boolean forceRead );

	ICFSecProtTableInfoObj readCachedTableInfoByIdIdx( int TableInfoId );

	ICFSecProtTableInfoObj readCachedTableInfoByTableNameIdx( String TableName );

	List<List<ICFSecProtTableInfoObj>> readCachedTableInfoBySuperNameIdx( String SuperName );

	List<List<ICFSecProtTableInfoObj>> readCachedTableInfoBySchemaNameIdx( String SchemaName );

	ICFSecProtTableInfoObj readCachedTableInfoBySchemaBkCodeIdx( String SchemaName,
		int BackingClassCode );

	ICFSecProtTableInfoObj readCachedTableInfoBySchemaRTCodeIdx( int RuntimeClassCode );

	void deepDisposeTableInfoByIdIdx( int TableInfoId );

	void deepDisposeTableInfoByTableNameIdx( String TableName );

	void deepDisposeTableInfoBySuperNameIdx( String SuperName );

	void deepDisposeTableInfoBySchemaNameIdx( String SchemaName );

	void deepDisposeTableInfoBySchemaBkCodeIdx( String SchemaName,
		int BackingClassCode );

	void deepDisposeTableInfoBySchemaRTCodeIdx( int RuntimeClassCode );

	/**
	 *	Internal use only.
	 */
	ICFSecProtTableInfoObj updateTableInfo( ICFSecProtTableInfoObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTableInfo( ICFSecProtTableInfoObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableInfoId	The TableInfo key attribute of the instance generating the id.
	 */
	void deleteTableInfoByIdIdx( int TableInfoId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableName	The TableInfo key attribute of the instance generating the id.
	 */
	void deleteTableInfoByTableNameIdx(String TableName );

	/**
	 *	Internal use only.
	 *
	 *	@param	SuperName	The TableInfo key attribute of the instance generating the id.
	 */
	void deleteTableInfoBySuperNameIdx( String SuperName );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaName	The TableInfo key attribute of the instance generating the id.
	 */
	void deleteTableInfoBySchemaNameIdx( String SchemaName );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaName	The TableInfo key attribute of the instance generating the id.
	 *
	 *	@param	BackingClassCode	The TableInfo key attribute of the instance generating the id.
	 */
	void deleteTableInfoBySchemaBkCodeIdx(String SchemaName,
		int BackingClassCode );

	/**
	 *	Internal use only.
	 *
	 *	@param	RuntimeClassCode	The TableInfo key attribute of the instance generating the id.
	 */
	void deleteTableInfoBySchemaRTCodeIdx(int RuntimeClassCode );
}
