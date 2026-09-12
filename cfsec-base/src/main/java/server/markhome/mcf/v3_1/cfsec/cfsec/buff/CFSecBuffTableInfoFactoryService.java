
// Description: Java 25 Factory service implementation for TableInfo buffers

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

package server.markhome.mcf.v3_1.cfsec.cfsec.buff;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;

/*
 *	Java 25 Factory service implementation for TableInfo buffers.
 */
@Service("cfsec31BuffTableInfoFactoryService")
public class CFSecBuffTableInfoFactoryService
	implements ICFSecTableInfoFactory
{
	public CFSecBuffTableInfoFactoryService() {
	}

	@Override
	public ICFSecTableInfoHPKey newHPKey() {
		ICFSecTableInfoHPKey hpkey =
			new CFSecBuffTableInfoHPKey();
		return( hpkey );
	}

	public CFSecBuffTableInfoHPKey ensureHPKey(ICFSecTableInfoHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFSecBuffTableInfoHPKey) {
			return( (CFSecBuffTableInfoHPKey)key );
		}
		else {
			CFSecBuffTableInfoHPKey mapped = new CFSecBuffTableInfoHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredTableInfoId( key.getRequiredTableInfoId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecTableInfoByTableNameIdxKey newByTableNameIdxKey() {
		ICFSecTableInfoByTableNameIdxKey key =
			new CFSecBuffTableInfoByTableNameIdxKey();
		return( key );
	}

	public CFSecBuffTableInfoByTableNameIdxKey ensureByTableNameIdxKey(ICFSecTableInfoByTableNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffTableInfoByTableNameIdxKey) {
			return( (CFSecBuffTableInfoByTableNameIdxKey)key );
		}
		else {
			CFSecBuffTableInfoByTableNameIdxKey mapped = new CFSecBuffTableInfoByTableNameIdxKey();
			mapped.setRequiredTableName( key.getRequiredTableName() );
			return( mapped );
		}
	}

	@Override
	public ICFSecTableInfoBySuperNameIdxKey newBySuperNameIdxKey() {
		ICFSecTableInfoBySuperNameIdxKey key =
			new CFSecBuffTableInfoBySuperNameIdxKey();
		return( key );
	}

	public CFSecBuffTableInfoBySuperNameIdxKey ensureBySuperNameIdxKey(ICFSecTableInfoBySuperNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffTableInfoBySuperNameIdxKey) {
			return( (CFSecBuffTableInfoBySuperNameIdxKey)key );
		}
		else {
			CFSecBuffTableInfoBySuperNameIdxKey mapped = new CFSecBuffTableInfoBySuperNameIdxKey();
			mapped.setOptionalSuperName( key.getOptionalSuperName() );
			return( mapped );
		}
	}

	@Override
	public ICFSecTableInfoBySchemaNameIdxKey newBySchemaNameIdxKey() {
		ICFSecTableInfoBySchemaNameIdxKey key =
			new CFSecBuffTableInfoBySchemaNameIdxKey();
		return( key );
	}

	public CFSecBuffTableInfoBySchemaNameIdxKey ensureBySchemaNameIdxKey(ICFSecTableInfoBySchemaNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffTableInfoBySchemaNameIdxKey) {
			return( (CFSecBuffTableInfoBySchemaNameIdxKey)key );
		}
		else {
			CFSecBuffTableInfoBySchemaNameIdxKey mapped = new CFSecBuffTableInfoBySchemaNameIdxKey();
			mapped.setRequiredSchemaName( key.getRequiredSchemaName() );
			return( mapped );
		}
	}

	@Override
	public ICFSecTableInfoBySchemaBkCodeIdxKey newBySchemaBkCodeIdxKey() {
		ICFSecTableInfoBySchemaBkCodeIdxKey key =
			new CFSecBuffTableInfoBySchemaBkCodeIdxKey();
		return( key );
	}

	public CFSecBuffTableInfoBySchemaBkCodeIdxKey ensureBySchemaBkCodeIdxKey(ICFSecTableInfoBySchemaBkCodeIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffTableInfoBySchemaBkCodeIdxKey) {
			return( (CFSecBuffTableInfoBySchemaBkCodeIdxKey)key );
		}
		else {
			CFSecBuffTableInfoBySchemaBkCodeIdxKey mapped = new CFSecBuffTableInfoBySchemaBkCodeIdxKey();
			mapped.setRequiredSchemaName( key.getRequiredSchemaName() );
			mapped.setRequiredBackingClassCode( key.getRequiredBackingClassCode() );
			return( mapped );
		}
	}

	@Override
	public ICFSecTableInfoBySchemaRTCodeIdxKey newBySchemaRTCodeIdxKey() {
		ICFSecTableInfoBySchemaRTCodeIdxKey key =
			new CFSecBuffTableInfoBySchemaRTCodeIdxKey();
		return( key );
	}

	public CFSecBuffTableInfoBySchemaRTCodeIdxKey ensureBySchemaRTCodeIdxKey(ICFSecTableInfoBySchemaRTCodeIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffTableInfoBySchemaRTCodeIdxKey) {
			return( (CFSecBuffTableInfoBySchemaRTCodeIdxKey)key );
		}
		else {
			CFSecBuffTableInfoBySchemaRTCodeIdxKey mapped = new CFSecBuffTableInfoBySchemaRTCodeIdxKey();
			mapped.setRequiredRuntimeClassCode( key.getRequiredRuntimeClassCode() );
			return( mapped );
		}
	}

	@Override
	public ICFSecTableInfo newRec() {
		ICFSecTableInfo rec =
			new CFSecBuffTableInfo();
		return( rec );
	}

	public CFSecBuffTableInfo ensureRec(ICFSecTableInfo rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFSecBuffTableInfo) {
			return ((CFSecBuffTableInfo)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFSecTableInfo.CLASS_CODE: {
					CFSecBuffTableInfo mapped = new CFSecBuffTableInfo();
					mapped.set(rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecTableInfo",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecTableInfo");
			}
		}
	}

	@Override
	public ICFSecTableInfoH newHRec() {
		ICFSecTableInfoH hrec =
			new CFSecBuffTableInfoH();
		return( hrec );
	}

	public CFSecBuffTableInfoH ensureHRec(ICFSecTableInfoH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFSecBuffTableInfoH) {
			return ((CFSecBuffTableInfoH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFSecTableInfo.CLASS_CODE: {
					CFSecBuffTableInfoH mapped = new CFSecBuffTableInfoH();
					mapped.set(hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecTableInfo",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecTableInfo");
			}
		}
	}
}
