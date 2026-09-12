
// Description: Java 25 Factory service implementation for ISOCtry buffers

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
 *	Java 25 Factory service implementation for ISOCtry buffers.
 */
@Service("cfsec31BuffISOCtryFactoryService")
public class CFSecBuffISOCtryFactoryService
	implements ICFSecISOCtryFactory
{
	public CFSecBuffISOCtryFactoryService() {
	}

	@Override
	public ICFSecISOCtryHPKey newHPKey() {
		ICFSecISOCtryHPKey hpkey =
			new CFSecBuffISOCtryHPKey();
		return( hpkey );
	}

	public CFSecBuffISOCtryHPKey ensureHPKey(ICFSecISOCtryHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFSecBuffISOCtryHPKey) {
			return( (CFSecBuffISOCtryHPKey)key );
		}
		else {
			CFSecBuffISOCtryHPKey mapped = new CFSecBuffISOCtryHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredISOCtryId( key.getRequiredISOCtryId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecISOCtryByISOCodeIdxKey newByISOCodeIdxKey() {
		ICFSecISOCtryByISOCodeIdxKey key =
			new CFSecBuffISOCtryByISOCodeIdxKey();
		return( key );
	}

	public CFSecBuffISOCtryByISOCodeIdxKey ensureByISOCodeIdxKey(ICFSecISOCtryByISOCodeIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffISOCtryByISOCodeIdxKey) {
			return( (CFSecBuffISOCtryByISOCodeIdxKey)key );
		}
		else {
			CFSecBuffISOCtryByISOCodeIdxKey mapped = new CFSecBuffISOCtryByISOCodeIdxKey();
			mapped.setRequiredISOCode( key.getRequiredISOCode() );
			return( mapped );
		}
	}

	@Override
	public ICFSecISOCtryByNameIdxKey newByNameIdxKey() {
		ICFSecISOCtryByNameIdxKey key =
			new CFSecBuffISOCtryByNameIdxKey();
		return( key );
	}

	public CFSecBuffISOCtryByNameIdxKey ensureByNameIdxKey(ICFSecISOCtryByNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffISOCtryByNameIdxKey) {
			return( (CFSecBuffISOCtryByNameIdxKey)key );
		}
		else {
			CFSecBuffISOCtryByNameIdxKey mapped = new CFSecBuffISOCtryByNameIdxKey();
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFSecISOCtry newRec() {
		ICFSecISOCtry rec =
			new CFSecBuffISOCtry();
		return( rec );
	}

	public CFSecBuffISOCtry ensureRec(ICFSecISOCtry rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFSecBuffISOCtry) {
			return ((CFSecBuffISOCtry)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFSecISOCtry.CLASS_CODE: {
					CFSecBuffISOCtry mapped = new CFSecBuffISOCtry();
					mapped.set(rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecISOCtry",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecISOCtry");
			}
		}
	}

	@Override
	public ICFSecISOCtryH newHRec() {
		ICFSecISOCtryH hrec =
			new CFSecBuffISOCtryH();
		return( hrec );
	}

	public CFSecBuffISOCtryH ensureHRec(ICFSecISOCtryH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFSecBuffISOCtryH) {
			return ((CFSecBuffISOCtryH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFSecISOCtry.CLASS_CODE: {
					CFSecBuffISOCtryH mapped = new CFSecBuffISOCtryH();
					mapped.set(hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecISOCtry",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecISOCtry");
			}
		}
	}
}
