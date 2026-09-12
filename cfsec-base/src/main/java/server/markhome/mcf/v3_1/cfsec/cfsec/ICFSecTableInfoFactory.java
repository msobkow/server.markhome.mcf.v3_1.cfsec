
// Description: Java JPA Factory interface for TableInfo.

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
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;

import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/*
 *	ICFSecTableInfoFactory interface for TableInfo
 */
public interface ICFSecTableInfoFactory extends ICFSecProtTableInfoFactory
{

	/**
	 *	Allocate a primary history key for TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecTableInfoHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for TableInfo instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtTableInfoHPKey asProtected(ICFSecTableInfoHPKey src);

	/**
	 *	Allocate a public primary history key for TableInfo instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubTableInfoHPKey asPublic(ICFSecTableInfoHPKey src);

	/**
	 *	Allocate a TableNameIdx key over TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTableInfoByTableNameIdxKey newByTableNameIdxKey();

	/**
	 *	Allocate a protected TableNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoByTableNameIdxKey asProtected(ICFSecTableInfoByTableNameIdxKey src);

	/**
	 *	Allocate a public TableNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoByTableNameIdxKey asPublic(ICFSecTableInfoByTableNameIdxKey src);

	/**
	 *	Allocate a SuperNameIdx key over TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTableInfoBySuperNameIdxKey newBySuperNameIdxKey();

	/**
	 *	Allocate a protected SuperNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoBySuperNameIdxKey asProtected(ICFSecTableInfoBySuperNameIdxKey src);

	/**
	 *	Allocate a public SuperNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoBySuperNameIdxKey asPublic(ICFSecTableInfoBySuperNameIdxKey src);

	/**
	 *	Allocate a SchemaNameIdx key over TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTableInfoBySchemaNameIdxKey newBySchemaNameIdxKey();

	/**
	 *	Allocate a protected SchemaNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoBySchemaNameIdxKey asProtected(ICFSecTableInfoBySchemaNameIdxKey src);

	/**
	 *	Allocate a public SchemaNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoBySchemaNameIdxKey asPublic(ICFSecTableInfoBySchemaNameIdxKey src);

	/**
	 *	Allocate a SchemaBkCodeIdx key over TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTableInfoBySchemaBkCodeIdxKey newBySchemaBkCodeIdxKey();

	/**
	 *	Allocate a protected SchemaBkCodeIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoBySchemaBkCodeIdxKey asProtected(ICFSecTableInfoBySchemaBkCodeIdxKey src);

	/**
	 *	Allocate a public SchemaBkCodeIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoBySchemaBkCodeIdxKey asPublic(ICFSecTableInfoBySchemaBkCodeIdxKey src);

	/**
	 *	Allocate a SchemaRTCodeIdx key over TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTableInfoBySchemaRTCodeIdxKey newBySchemaRTCodeIdxKey();

	/**
	 *	Allocate a protected SchemaRTCodeIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoBySchemaRTCodeIdxKey asProtected(ICFSecTableInfoBySchemaRTCodeIdxKey src);

	/**
	 *	Allocate a public SchemaRTCodeIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoBySchemaRTCodeIdxKey asPublic(ICFSecTableInfoBySchemaRTCodeIdxKey src);

	/**
	 *	Allocate a TableInfo interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTableInfo newRec();

	/**
	 *	Allocate a protected TableInfo interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfo asProtected(ICFSecTableInfo src);

	/**
	 *	Allocate a public TableInfo interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfo asPublic(ICFSecTableInfo src);

	/**
	 *	Allocate a TableInfo history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecTableInfoH newHRec();

	/**
	 *	Allocate a protected TableInfo history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoH asProtected(ICFSecTableInfoH src);

	/**
	 *	Allocate a public TableInfo history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoH asPublic(ICFSecTableInfoH src);

}
