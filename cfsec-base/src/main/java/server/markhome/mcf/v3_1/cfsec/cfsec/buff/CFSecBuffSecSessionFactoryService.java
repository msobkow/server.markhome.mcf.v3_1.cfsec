
// Description: Java 25 Factory service implementation for SecSession buffers

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
 *	Java 25 Factory service implementation for SecSession buffers.
 */
@Service("cfsec31BuffSecSessionFactoryService")
public class CFSecBuffSecSessionFactoryService
	implements ICFSecSecSessionFactory
{
	public CFSecBuffSecSessionFactoryService() {
	}

	@Override
	public ICFSecSecSessionHPKey newHPKey() {
		ICFSecSecSessionHPKey hpkey =
			new CFSecBuffSecSessionHPKey();
		return( hpkey );
	}

	public CFSecBuffSecSessionHPKey ensureHPKey(ICFSecSecSessionHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFSecBuffSecSessionHPKey) {
			return( (CFSecBuffSecSessionHPKey)key );
		}
		else {
			CFSecBuffSecSessionHPKey mapped = new CFSecBuffSecSessionHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredSecSessionId( key.getRequiredSecSessionId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecSessionBySecUserIdxKey newBySecUserIdxKey() {
		ICFSecSecSessionBySecUserIdxKey key =
			new CFSecBuffSecSessionBySecUserIdxKey();
		return( key );
	}

	public CFSecBuffSecSessionBySecUserIdxKey ensureBySecUserIdxKey(ICFSecSecSessionBySecUserIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffSecSessionBySecUserIdxKey) {
			return( (CFSecBuffSecSessionBySecUserIdxKey)key );
		}
		else {
			CFSecBuffSecSessionBySecUserIdxKey mapped = new CFSecBuffSecSessionBySecUserIdxKey();
			mapped.setRequiredSecUserId( key.getRequiredSecUserId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecSessionByStartIdxKey newByStartIdxKey() {
		ICFSecSecSessionByStartIdxKey key =
			new CFSecBuffSecSessionByStartIdxKey();
		return( key );
	}

	public CFSecBuffSecSessionByStartIdxKey ensureByStartIdxKey(ICFSecSecSessionByStartIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffSecSessionByStartIdxKey) {
			return( (CFSecBuffSecSessionByStartIdxKey)key );
		}
		else {
			CFSecBuffSecSessionByStartIdxKey mapped = new CFSecBuffSecSessionByStartIdxKey();
			mapped.setRequiredSecUserId( key.getRequiredSecUserId() );
			mapped.setRequiredStart( key.getRequiredStart() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecSessionByFinishIdxKey newByFinishIdxKey() {
		ICFSecSecSessionByFinishIdxKey key =
			new CFSecBuffSecSessionByFinishIdxKey();
		return( key );
	}

	public CFSecBuffSecSessionByFinishIdxKey ensureByFinishIdxKey(ICFSecSecSessionByFinishIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffSecSessionByFinishIdxKey) {
			return( (CFSecBuffSecSessionByFinishIdxKey)key );
		}
		else {
			CFSecBuffSecSessionByFinishIdxKey mapped = new CFSecBuffSecSessionByFinishIdxKey();
			mapped.setRequiredSecUserId( key.getRequiredSecUserId() );
			mapped.setOptionalFinish( key.getOptionalFinish() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecSessionBySecProxyIdxKey newBySecProxyIdxKey() {
		ICFSecSecSessionBySecProxyIdxKey key =
			new CFSecBuffSecSessionBySecProxyIdxKey();
		return( key );
	}

	public CFSecBuffSecSessionBySecProxyIdxKey ensureBySecProxyIdxKey(ICFSecSecSessionBySecProxyIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffSecSessionBySecProxyIdxKey) {
			return( (CFSecBuffSecSessionBySecProxyIdxKey)key );
		}
		else {
			CFSecBuffSecSessionBySecProxyIdxKey mapped = new CFSecBuffSecSessionBySecProxyIdxKey();
			mapped.setOptionalSecProxyId( key.getOptionalSecProxyId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecSession newRec() {
		ICFSecSecSession rec =
			new CFSecBuffSecSession();
		return( rec );
	}

	public CFSecBuffSecSession ensureRec(ICFSecSecSession rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFSecBuffSecSession) {
			return ((CFSecBuffSecSession)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFSecSecSession.CLASS_CODE: {
					CFSecBuffSecSession mapped = new CFSecBuffSecSession();
					mapped.set(rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecSecSession",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecSecSession");
			}
		}
	}

	@Override
	public ICFSecSecSessionH newHRec() {
		ICFSecSecSessionH hrec =
			new CFSecBuffSecSessionH();
		return( hrec );
	}

	public CFSecBuffSecSessionH ensureHRec(ICFSecSecSessionH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFSecBuffSecSessionH) {
			return ((CFSecBuffSecSessionH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFSecSecSession.CLASS_CODE: {
					CFSecBuffSecSessionH mapped = new CFSecBuffSecSessionH();
					mapped.set(hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecSecSession",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecSecSession");
			}
		}
	}
}
