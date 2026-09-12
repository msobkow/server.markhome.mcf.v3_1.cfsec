
// Description: Java Protected Factory interface for TableInfo.

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
 *	ICFSecProtTableInfoFactory protected interface for TableInfo
 */
public interface ICFSecProtTableInfoFactory extends ICFSecPubTableInfoFactory
{

	/**
	 *	Allocate a protected primary history key for TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtTableInfoHPKey newProtHPKey();

	/**
	 *	Allocate a public primary history key for TableInfo instances from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubTableInfoHPKey asPublic(ICFSecProtTableInfoHPKey src);

	/**
	 *	Allocate a protected TableNameIdx key over protected TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoByTableNameIdxKey newProtByTableNameIdxKey();

	/**
	 *	Allocate a public TableNameIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoByTableNameIdxKey asPublic(ICFSecProtTableInfoByTableNameIdxKey src);

	/**
	 *	Allocate a protected SuperNameIdx key over protected TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoBySuperNameIdxKey newProtBySuperNameIdxKey();

	/**
	 *	Allocate a public SuperNameIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoBySuperNameIdxKey asPublic(ICFSecProtTableInfoBySuperNameIdxKey src);

	/**
	 *	Allocate a protected SchemaNameIdx key over protected TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoBySchemaNameIdxKey newProtBySchemaNameIdxKey();

	/**
	 *	Allocate a public SchemaNameIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoBySchemaNameIdxKey asPublic(ICFSecProtTableInfoBySchemaNameIdxKey src);

	/**
	 *	Allocate a protected SchemaBkCodeIdx key over protected TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoBySchemaBkCodeIdxKey newProtBySchemaBkCodeIdxKey();

	/**
	 *	Allocate a public SchemaBkCodeIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoBySchemaBkCodeIdxKey asPublic(ICFSecProtTableInfoBySchemaBkCodeIdxKey src);

	/**
	 *	Allocate a protected SchemaRTCodeIdx key over protected TableInfo instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoBySchemaRTCodeIdxKey newProtBySchemaRTCodeIdxKey();

	/**
	 *	Allocate a public SchemaRTCodeIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoBySchemaRTCodeIdxKey asPublic(ICFSecProtTableInfoBySchemaRTCodeIdxKey src);

	/**
	 *	Allocate a protected TableInfo interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfo newProtRec();

	/**
	 *	Allocate a public TableInfo interface from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfo asPublic(ICFSecProtTableInfo src);

	/**
	 *	Allocate a protected TableInfo history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtTableInfoH newProtHRec();

	/**
	 *	Allocate a public TableInfo history interface implementation from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubTableInfoH asPublic(ICFSecProtTableInfoH src);

}
