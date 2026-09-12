
// Description: Java 25 Factory service implementation for ISOLang buffers

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
 *	Java 25 Factory service implementation for ISOLang buffers.
 */
@Service("cfsec31BuffISOLangFactoryService")
public class CFSecBuffISOLangFactoryService
	implements ICFSecISOLangFactory
{
	public CFSecBuffISOLangFactoryService() {
	}

	@Override
	public ICFSecISOLangHPKey newHPKey() {
		ICFSecISOLangHPKey hpkey =
			new CFSecBuffISOLangHPKey();
		return( hpkey );
	}

	public CFSecBuffISOLangHPKey ensureHPKey(ICFSecISOLangHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFSecBuffISOLangHPKey) {
			return( (CFSecBuffISOLangHPKey)key );
		}
		else {
			CFSecBuffISOLangHPKey mapped = new CFSecBuffISOLangHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredISOLangId( key.getRequiredISOLangId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecISOLangByCode3IdxKey newByCode3IdxKey() {
		ICFSecISOLangByCode3IdxKey key =
			new CFSecBuffISOLangByCode3IdxKey();
		return( key );
	}

	public CFSecBuffISOLangByCode3IdxKey ensureByCode3IdxKey(ICFSecISOLangByCode3IdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffISOLangByCode3IdxKey) {
			return( (CFSecBuffISOLangByCode3IdxKey)key );
		}
		else {
			CFSecBuffISOLangByCode3IdxKey mapped = new CFSecBuffISOLangByCode3IdxKey();
			mapped.setRequiredISO6392Code( key.getRequiredISO6392Code() );
			return( mapped );
		}
	}

	@Override
	public ICFSecISOLangByCode2IdxKey newByCode2IdxKey() {
		ICFSecISOLangByCode2IdxKey key =
			new CFSecBuffISOLangByCode2IdxKey();
		return( key );
	}

	public CFSecBuffISOLangByCode2IdxKey ensureByCode2IdxKey(ICFSecISOLangByCode2IdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffISOLangByCode2IdxKey) {
			return( (CFSecBuffISOLangByCode2IdxKey)key );
		}
		else {
			CFSecBuffISOLangByCode2IdxKey mapped = new CFSecBuffISOLangByCode2IdxKey();
			mapped.setOptionalISO6391Code( key.getOptionalISO6391Code() );
			return( mapped );
		}
	}

	@Override
	public ICFSecISOLang newRec() {
		ICFSecISOLang rec =
			new CFSecBuffISOLang();
		return( rec );
	}

	public CFSecBuffISOLang ensureRec(ICFSecISOLang rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFSecBuffISOLang) {
			return ((CFSecBuffISOLang)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFSecISOLang.CLASS_CODE: {
					CFSecBuffISOLang mapped = new CFSecBuffISOLang();
					mapped.set(rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecISOLang",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecISOLang");
			}
		}
	}

	@Override
	public ICFSecISOLangH newHRec() {
		ICFSecISOLangH hrec =
			new CFSecBuffISOLangH();
		return( hrec );
	}

	public CFSecBuffISOLangH ensureHRec(ICFSecISOLangH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFSecBuffISOLangH) {
			return ((CFSecBuffISOLangH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFSecISOLang.CLASS_CODE: {
					CFSecBuffISOLangH mapped = new CFSecBuffISOLangH();
					mapped.set(hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecISOLang",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecISOLang");
			}
		}
	}
}
