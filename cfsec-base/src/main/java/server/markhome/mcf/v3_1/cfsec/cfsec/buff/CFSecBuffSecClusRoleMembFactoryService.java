
// Description: Java 25 Factory service implementation for SecClusRoleMemb buffers

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
 *	Java 25 Factory service implementation for SecClusRoleMemb buffers.
 */
@Service("cfsec31BuffSecClusRoleMembFactoryService")
public class CFSecBuffSecClusRoleMembFactoryService
	implements ICFSecSecClusRoleMembFactory
{
	public CFSecBuffSecClusRoleMembFactoryService() {
	}

    @Override
    public ICFSecSecClusRoleMembPKey newPKey() {
        ICFSecSecClusRoleMembPKey pkey =
            new CFSecBuffSecClusRoleMembPKey();
        return( pkey );
    }

	public CFSecBuffSecClusRoleMembPKey ensurePKey(ICFSecSecClusRoleMembPKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffSecClusRoleMembPKey) {
			return( (CFSecBuffSecClusRoleMembPKey)key );
		}
		else {
			CFSecBuffSecClusRoleMembPKey mapped = new CFSecBuffSecClusRoleMembPKey();
			mapped.setRequiredSecClusRoleId( key.getRequiredSecClusRoleId() );
			mapped.setRequiredLoginId( key.getRequiredLoginId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecClusRoleMembHPKey newHPKey() {
		ICFSecSecClusRoleMembHPKey hpkey =
			new CFSecBuffSecClusRoleMembHPKey();
		return( hpkey );
	}

	public CFSecBuffSecClusRoleMembHPKey ensureHPKey(ICFSecSecClusRoleMembHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFSecBuffSecClusRoleMembHPKey) {
			return( (CFSecBuffSecClusRoleMembHPKey)key );
		}
		else {
			CFSecBuffSecClusRoleMembHPKey mapped = new CFSecBuffSecClusRoleMembHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredSecClusRoleId( key.getRequiredSecClusRoleId() );
			mapped.setRequiredLoginId( key.getRequiredLoginId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecClusRoleMembByClusRoleIdxKey newByClusRoleIdxKey() {
		ICFSecSecClusRoleMembByClusRoleIdxKey key =
			new CFSecBuffSecClusRoleMembByClusRoleIdxKey();
		return( key );
	}

	public CFSecBuffSecClusRoleMembByClusRoleIdxKey ensureByClusRoleIdxKey(ICFSecSecClusRoleMembByClusRoleIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffSecClusRoleMembByClusRoleIdxKey) {
			return( (CFSecBuffSecClusRoleMembByClusRoleIdxKey)key );
		}
		else {
			CFSecBuffSecClusRoleMembByClusRoleIdxKey mapped = new CFSecBuffSecClusRoleMembByClusRoleIdxKey();
			mapped.setRequiredSecClusRoleId( key.getRequiredSecClusRoleId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecClusRoleMembByLoginIdxKey newByLoginIdxKey() {
		ICFSecSecClusRoleMembByLoginIdxKey key =
			new CFSecBuffSecClusRoleMembByLoginIdxKey();
		return( key );
	}

	public CFSecBuffSecClusRoleMembByLoginIdxKey ensureByLoginIdxKey(ICFSecSecClusRoleMembByLoginIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFSecBuffSecClusRoleMembByLoginIdxKey) {
			return( (CFSecBuffSecClusRoleMembByLoginIdxKey)key );
		}
		else {
			CFSecBuffSecClusRoleMembByLoginIdxKey mapped = new CFSecBuffSecClusRoleMembByLoginIdxKey();
			mapped.setRequiredLoginId( key.getRequiredLoginId() );
			return( mapped );
		}
	}

	@Override
	public ICFSecSecClusRoleMemb newRec() {
		ICFSecSecClusRoleMemb rec =
			new CFSecBuffSecClusRoleMemb();
		return( rec );
	}

	public CFSecBuffSecClusRoleMemb ensureRec(ICFSecSecClusRoleMemb rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFSecBuffSecClusRoleMemb) {
			return ((CFSecBuffSecClusRoleMemb)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFSecSecClusRoleMemb.CLASS_CODE: {
					CFSecBuffSecClusRoleMemb mapped = new CFSecBuffSecClusRoleMemb();
					mapped.set(rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecSecClusRoleMemb",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFSecSecClusRoleMemb");
			}
		}
	}

	@Override
	public ICFSecSecClusRoleMembH newHRec() {
		ICFSecSecClusRoleMembH hrec =
			new CFSecBuffSecClusRoleMembH();
		return( hrec );
	}

	public CFSecBuffSecClusRoleMembH ensureHRec(ICFSecSecClusRoleMembH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFSecBuffSecClusRoleMembH) {
			return ((CFSecBuffSecClusRoleMembH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFSecSecClusRoleMemb.CLASS_CODE: {
					CFSecBuffSecClusRoleMembH mapped = new CFSecBuffSecClusRoleMembH();
					mapped.set(hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecSecClusRoleMemb",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFSecSecClusRoleMemb");
			}
		}
	}
}
