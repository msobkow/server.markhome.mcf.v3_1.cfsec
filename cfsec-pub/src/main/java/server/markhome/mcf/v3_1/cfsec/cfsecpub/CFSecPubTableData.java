// Description: Java 25 Public CFSec TableData Implementation

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
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.ICFSecPubClusterObj;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.ICFSecPubTenantObj;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.ICFSecPubSecSessionObj;

/*
 *	The CFSecPubTableData objects are passed in to the CFSec schema implementation to be bound
 *	to the 
 */
public class CFSecPubTableData implements Serializable
{
	protected final String schemaName;
	protected final String tableName;
	protected final String superName;
	protected final boolean history;
	protected final boolean mutable;
	protected final String scope;
	protected final String codeVis;

	public CFSecPubTableData(String schemaName, String tableName, String superName, boolean history, boolean mutable, String scope, String codeVis) {
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.superName = superName;
		this.history = history;
		this.mutable = mutable;
		this.scope = scope;
		this.codeVis = codeVis;
	}

	public String getTableName() {
		return tableName;
	}

	public String getSuperName() {
		return superName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public boolean hasHistory() {
		return history;
	}

	public boolean isMutable() {
		return mutable;
	}

	public String getScope() {
		return scope;
	}

	public String getCodeVis() {
		return codeVis;
	}
}
