// Description: Java 25 Schema Object implementation for CFSec.

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

public class CFSecSchemaObj
	implements ICFSecSchemaObj
{
	public static final String SCHEMA_NAME = ICFSecSchema.SCHEMA_NAME;
	public static final String SCHEMA_DBNAME = ICFSecSchema.DBSCHEMA_NAME;
	protected ICFSecAuthorization authorization = null;
	protected String secClusterName = "system";
	protected String secTenantName = "system";
	protected String secUserName = "system";
	protected ICFSecClusterObj secCluster = null;
	protected CFLibDbKeyHash256 secClusterId = null;
	protected ICFSecTenantObj secTenant = null;
	protected CFLibDbKeyHash256 secTenantId = null;
	protected ICFSecSecUserObj secUser = null;
	protected CFLibDbKeyHash256 secSessionUserId = null;
	protected ICFSecSecSessionObj secSession = null;
	protected CFLibDbKeyHash256 secSessionSessionId = null;
	protected String schemaDbName = SCHEMA_DBNAME;
	protected String lowerDbSchemaName = SCHEMA_DBNAME.toLowerCase();

	protected ICFSecSchema cfsecBackingStore;
	protected ICFSecClusterTableObj clusterTableObj;
	protected ICFSecISOCcyTableObj iSOCcyTableObj;
	protected ICFSecISOCtryTableObj iSOCtryTableObj;
	protected ICFSecISOCtryCcyTableObj iSOCtryCcyTableObj;
	protected ICFSecISOCtryLangTableObj iSOCtryLangTableObj;
	protected ICFSecISOLangTableObj iSOLangTableObj;
	protected ICFSecISOTZoneTableObj iSOTZoneTableObj;
	protected ICFSecSecClusGrpTableObj secClusGrpTableObj;
	protected ICFSecSecClusGrpMembTableObj secClusGrpMembTableObj;
	protected ICFSecSecClusRoleTableObj secClusRoleTableObj;
	protected ICFSecSecClusRoleMembTableObj secClusRoleMembTableObj;
	protected ICFSecSecSessionTableObj secSessionTableObj;
	protected ICFSecSecSysGrpTableObj secSysGrpTableObj;
	protected ICFSecSecSysGrpIncTableObj secSysGrpIncTableObj;
	protected ICFSecSecSysGrpMembTableObj secSysGrpMembTableObj;
	protected ICFSecSecSysRoleTableObj secSysRoleTableObj;
	protected ICFSecSecSysRoleEnablesTableObj secSysRoleEnablesTableObj;
	protected ICFSecSecSysRoleMembTableObj secSysRoleMembTableObj;
	protected ICFSecSecTentGrpTableObj secTentGrpTableObj;
	protected ICFSecSecTentGrpMembTableObj secTentGrpMembTableObj;
	protected ICFSecSecTentRoleTableObj secTentRoleTableObj;
	protected ICFSecSecTentRoleMembTableObj secTentRoleMembTableObj;
	protected ICFSecSecUserTableObj secUserTableObj;
	protected ICFSecSecUserEMConfTableObj secUserEMConfTableObj;
	protected ICFSecSecUserPWHistoryTableObj secUserPWHistoryTableObj;
	protected ICFSecSecUserPWResetTableObj secUserPWResetTableObj;
	protected ICFSecSecUserPasswordTableObj secUserPasswordTableObj;
	protected ICFSecSysClusterTableObj sysClusterTableObj;
	protected ICFSecTableInfoTableObj tableInfoTableObj;
	protected ICFSecTenantTableObj tenantTableObj;

	public CFSecSchemaObj() {

		cfsecBackingStore = null;
		clusterTableObj = new CFSecClusterTableObj( this );
		iSOCcyTableObj = new CFSecISOCcyTableObj( this );
		iSOCtryTableObj = new CFSecISOCtryTableObj( this );
		iSOCtryCcyTableObj = new CFSecISOCtryCcyTableObj( this );
		iSOCtryLangTableObj = new CFSecISOCtryLangTableObj( this );
		iSOLangTableObj = new CFSecISOLangTableObj( this );
		iSOTZoneTableObj = new CFSecISOTZoneTableObj( this );
		secClusGrpTableObj = new CFSecSecClusGrpTableObj( this );
		secClusGrpMembTableObj = new CFSecSecClusGrpMembTableObj( this );
		secClusRoleTableObj = new CFSecSecClusRoleTableObj( this );
		secClusRoleMembTableObj = new CFSecSecClusRoleMembTableObj( this );
		secSessionTableObj = new CFSecSecSessionTableObj( this );
		secSysGrpTableObj = new CFSecSecSysGrpTableObj( this );
		secSysGrpIncTableObj = new CFSecSecSysGrpIncTableObj( this );
		secSysGrpMembTableObj = new CFSecSecSysGrpMembTableObj( this );
		secSysRoleTableObj = new CFSecSecSysRoleTableObj( this );
		secSysRoleEnablesTableObj = new CFSecSecSysRoleEnablesTableObj( this );
		secSysRoleMembTableObj = new CFSecSecSysRoleMembTableObj( this );
		secTentGrpTableObj = new CFSecSecTentGrpTableObj( this );
		secTentGrpMembTableObj = new CFSecSecTentGrpMembTableObj( this );
		secTentRoleTableObj = new CFSecSecTentRoleTableObj( this );
		secTentRoleMembTableObj = new CFSecSecTentRoleMembTableObj( this );
		secUserTableObj = new CFSecSecUserTableObj( this );
		secUserEMConfTableObj = new CFSecSecUserEMConfTableObj( this );
		secUserPWHistoryTableObj = new CFSecSecUserPWHistoryTableObj( this );
		secUserPWResetTableObj = new CFSecSecUserPWResetTableObj( this );
		secUserPasswordTableObj = new CFSecSecUserPasswordTableObj( this );
		sysClusterTableObj = new CFSecSysClusterTableObj( this );
		tableInfoTableObj = new CFSecTableInfoTableObj( this );
		tenantTableObj = new CFSecTenantTableObj( this );
		}

	public void setSecClusterName( String value ) {
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				"setClusterName",
				1,
				"value" );
		}
		secClusterName = value;
		secCluster = null;
	}

	public String getSecClusterName() {
		return( secClusterName );
	}

	public ICFSecClusterObj getSecCluster() {
		if( secCluster == null ) {
			if( authorization != null ) {
				secCluster = getClusterTableObj().readClusterByIdIdx( authorization.getSecClusterId() );
			}
			else {
				secCluster = getClusterTableObj().readClusterByUDomNameIdx( secClusterName );
				if( secCluster == null && secClusterId != null && !secClusterId.isNull() ) {
					secCluster = getClusterTableObj().readClusterByIdIdx( secClusterId );
				}
			}
			if( secCluster != null ) {
				secClusterName = secCluster.getRequiredFullDomName();
				secClusterId = secCluster.getRequiredId();
				if( authorization != null ) {
					authorization.setSecCluster( secCluster );
				}
			}
		}
		return( secCluster );
	}

	public void setSecCluster( ICFSecClusterObj value ) {
		secCluster = value;
		if( secCluster == null ) {
			return;
		}
		secClusterId = secCluster.getRequiredId();
		secClusterName = secCluster.getRequiredFullDomName();
		if( authorization != null ) {
			authorization.setSecCluster( secCluster );
		}
	}

	public CFLibDbKeyHash256 getSecClusterId() {
		return( secClusterId );
	}

	public void setSecTenantName( String value ) {
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				"setTenantName",
				1,
				"value" );
		}
		secTenantName = value;
		secTenant = null;
	}

	public String getSecTenantName() {
		return( secTenantName );
	}

	public ICFSecTenantObj getSecTenant() {
		if( secTenant == null ) {
			if( authorization != null ) {
				secTenant = getTenantTableObj().readTenantByIdIdx( authorization.getSecTenantId() );
			}
			else {
				secTenant = getTenantTableObj().readTenantByUNameIdx( getSecCluster().getRequiredId(), secTenantName );
				if( ( secTenant == null && secTenantId != null && !secTenantId.isNull() ) ) {
					secTenant = getTenantTableObj().readTenantByIdIdx( secTenantId );
				}
			}
			if( secTenant != null ) {
				secTenantName = secTenant.getRequiredTenantName();
				secTenantId = secTenant.getRequiredId();
				if( authorization != null ) {
					authorization.setSecTenant( secTenant );
				}
			}
		}
		return( secTenant );
	}

	public void setSecTenant( ICFSecTenantObj value ) {
		secTenant = value;
		if( secTenant == null ) {
			return;
		}
		secTenantId = secTenant.getRequiredId();
		secTenantName = secTenant.getRequiredTenantName();
		if( authorization != null ) {
			authorization.setSecTenant( secTenant );
		}
	}

	public CFLibDbKeyHash256 getSecTenantId() {
		return( secTenantId );
	}

	public void setSecUserName( String value ) {
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				"setUserName",
				1,
				"value" );
		}
		secUserName = value;
		secUser = null;
	}

	public String getSecUserName() {
		return( secUserName );
	}

	public ICFSecSecUserObj getSecUser() {
		if( secUser == null ) {
			if( authorization != null ) {
				secUser = getSecUserTableObj().readSecUserByIdIdx( authorization.getSecUserId() );
			}
			else {
				secUser = getSecUserTableObj().readSecUserByULoginIdx( secUserName );
				if( ( secUser == null ) && ( secSessionUserId != null ) ) {
					secUser = getSecUserTableObj().readSecUserByIdIdx( secSessionUserId );
				}
			}
			if( secUser != null ) {
				secUserName = secUser.getRequiredLoginId();
				secSessionUserId = secUser.getRequiredSecUserId();
			}
		}
		return( secUser );
	}

	public void setSecUser( ICFSecSecUserObj value ) {
		secUser = value;
		if( secUser != null ) {
			secUserName = secUser.getRequiredLoginId();
			secSessionUserId = secUser.getRequiredSecUserId();
		}
	}
	public ICFSecSecSessionObj getSecSession() {
		if( secSession == null ) {
			if( authorization != null ) {
				secSession = getSecSessionTableObj().readSecSessionByIdIdx( authorization.getSecSessionId() );
			}
			else if( secSessionSessionId != null ) {
				secSession = getSecSessionTableObj().readSecSessionByIdIdx( secSessionSessionId );
			}
			if( secSession != null ) {
				secSessionSessionId = secSession.getRequiredSecSessionId();
				secSessionUserId = secSession.getRequiredSecUserId();
			}
		}
		return( secSession );
	}

	public void setSecSession( ICFSecSecSessionObj value ) {
		secSession = value;
		if( secSession == null ) {
			return;
		}
		secSessionSessionId = secSession.getRequiredSecSessionId();
		secSessionUserId = secSession.getRequiredSecUserId();
		if( authorization != null ) {
			authorization.setSecSession( secSession );
		}
	}

	public void setSecSessionId( CFLibDbKeyHash256 value ) {
		secSessionSessionId = value;
	}

	public CFLibDbKeyHash256 getSecSessionSessionId() {
		return( secSessionSessionId );
	}

	public CFLibDbKeyHash256 getSecSessionUserId() {
		return( secSessionUserId );
	}

	public ICFSecAuthorization getAuthorization() {
		return( authorization );
	}

	public void setAuthorization( ICFSecAuthorization value ) {
		authorization = value;
	}

	@Override
	public ICFSecSchema getCFSecBackingStore() {
		return( cfsecBackingStore );
	}

	@Override
	public void setCFSecBackingStore(ICFSecSchema cfsecBackingStore) {
		if (cfsecBackingStore == null) {
			throw new CFLibNullArgumentException(getClass(), "setCFSecBackingStore", 1, "cfsecBackingStore");
		}
		this.cfsecBackingStore = cfsecBackingStore;
	}

	public String getSchemaName() {
		return( SCHEMA_NAME );
	}

	public void minimizeMemory() {
		if( clusterTableObj != null ) {
			clusterTableObj.minimizeMemory();
		}
		if( iSOCcyTableObj != null ) {
			iSOCcyTableObj.minimizeMemory();
		}
		if( iSOCtryTableObj != null ) {
			iSOCtryTableObj.minimizeMemory();
		}
		if( iSOCtryCcyTableObj != null ) {
			iSOCtryCcyTableObj.minimizeMemory();
		}
		if( iSOCtryLangTableObj != null ) {
			iSOCtryLangTableObj.minimizeMemory();
		}
		if( iSOLangTableObj != null ) {
			iSOLangTableObj.minimizeMemory();
		}
		if( iSOTZoneTableObj != null ) {
			iSOTZoneTableObj.minimizeMemory();
		}
		if( secClusGrpTableObj != null ) {
			secClusGrpTableObj.minimizeMemory();
		}
		if( secClusGrpMembTableObj != null ) {
			secClusGrpMembTableObj.minimizeMemory();
		}
		if( secClusRoleTableObj != null ) {
			secClusRoleTableObj.minimizeMemory();
		}
		if( secClusRoleMembTableObj != null ) {
			secClusRoleMembTableObj.minimizeMemory();
		}
		if( secSessionTableObj != null ) {
			secSessionTableObj.minimizeMemory();
		}
		if( secSysGrpTableObj != null ) {
			secSysGrpTableObj.minimizeMemory();
		}
		if( secSysGrpIncTableObj != null ) {
			secSysGrpIncTableObj.minimizeMemory();
		}
		if( secSysGrpMembTableObj != null ) {
			secSysGrpMembTableObj.minimizeMemory();
		}
		if( secSysRoleTableObj != null ) {
			secSysRoleTableObj.minimizeMemory();
		}
		if( secSysRoleEnablesTableObj != null ) {
			secSysRoleEnablesTableObj.minimizeMemory();
		}
		if( secSysRoleMembTableObj != null ) {
			secSysRoleMembTableObj.minimizeMemory();
		}
		if( secTentGrpTableObj != null ) {
			secTentGrpTableObj.minimizeMemory();
		}
		if( secTentGrpMembTableObj != null ) {
			secTentGrpMembTableObj.minimizeMemory();
		}
		if( secTentRoleTableObj != null ) {
			secTentRoleTableObj.minimizeMemory();
		}
		if( secTentRoleMembTableObj != null ) {
			secTentRoleMembTableObj.minimizeMemory();
		}
		if( secUserTableObj != null ) {
			secUserTableObj.minimizeMemory();
		}
		if( secUserEMConfTableObj != null ) {
			secUserEMConfTableObj.minimizeMemory();
		}
		if( secUserPWHistoryTableObj != null ) {
			secUserPWHistoryTableObj.minimizeMemory();
		}
		if( secUserPWResetTableObj != null ) {
			secUserPWResetTableObj.minimizeMemory();
		}
		if( secUserPasswordTableObj != null ) {
			secUserPasswordTableObj.minimizeMemory();
		}
		if( sysClusterTableObj != null ) {
			sysClusterTableObj.minimizeMemory();
		}
		if( tableInfoTableObj != null ) {
			tableInfoTableObj.minimizeMemory();
		}
		if( tenantTableObj != null ) {
			tenantTableObj.minimizeMemory();
		}
	}

	public void logout() {
		if( authorization == null ) {
			return;
		}
		setAuthorization( null );
		CFLibDbKeyHash256 secSessionId = authorization.getSecSessionId();
		if( secSessionId != null ) {
			ICFSecSecSessionObj secSession = getSecSessionTableObj().readSecSessionByIdIdx( secSessionId );
			if( secSession != null ) {
				if( secSession.getOptionalFinish() == null ) {
					ICFSecSecSessionEditObj editSecSession = secSession.beginEdit();
					editSecSession.setOptionalFinish( LocalDateTime.now() );
					editSecSession.update();
					editSecSession = null;
				}
			}
		}
	}

	public ICFSecClusterTableObj getClusterTableObj() {
		return( clusterTableObj );
	}

	public ICFSecISOCcyTableObj getISOCcyTableObj() {
		return( iSOCcyTableObj );
	}

	public ICFSecISOCtryTableObj getISOCtryTableObj() {
		return( iSOCtryTableObj );
	}

	public ICFSecISOCtryCcyTableObj getISOCtryCcyTableObj() {
		return( iSOCtryCcyTableObj );
	}

	public ICFSecISOCtryLangTableObj getISOCtryLangTableObj() {
		return( iSOCtryLangTableObj );
	}

	public ICFSecISOLangTableObj getISOLangTableObj() {
		return( iSOLangTableObj );
	}

	public ICFSecISOTZoneTableObj getISOTZoneTableObj() {
		return( iSOTZoneTableObj );
	}

	public ICFSecSecClusGrpTableObj getSecClusGrpTableObj() {
		return( secClusGrpTableObj );
	}

	public ICFSecSecClusGrpMembTableObj getSecClusGrpMembTableObj() {
		return( secClusGrpMembTableObj );
	}

	public ICFSecSecClusRoleTableObj getSecClusRoleTableObj() {
		return( secClusRoleTableObj );
	}

	public ICFSecSecClusRoleMembTableObj getSecClusRoleMembTableObj() {
		return( secClusRoleMembTableObj );
	}

	public ICFSecSecSessionTableObj getSecSessionTableObj() {
		return( secSessionTableObj );
	}

	public ICFSecSecSysGrpTableObj getSecSysGrpTableObj() {
		return( secSysGrpTableObj );
	}

	public ICFSecSecSysGrpIncTableObj getSecSysGrpIncTableObj() {
		return( secSysGrpIncTableObj );
	}

	public ICFSecSecSysGrpMembTableObj getSecSysGrpMembTableObj() {
		return( secSysGrpMembTableObj );
	}

	public ICFSecSecSysRoleTableObj getSecSysRoleTableObj() {
		return( secSysRoleTableObj );
	}

	public ICFSecSecSysRoleEnablesTableObj getSecSysRoleEnablesTableObj() {
		return( secSysRoleEnablesTableObj );
	}

	public ICFSecSecSysRoleMembTableObj getSecSysRoleMembTableObj() {
		return( secSysRoleMembTableObj );
	}

	public ICFSecSecTentGrpTableObj getSecTentGrpTableObj() {
		return( secTentGrpTableObj );
	}

	public ICFSecSecTentGrpMembTableObj getSecTentGrpMembTableObj() {
		return( secTentGrpMembTableObj );
	}

	public ICFSecSecTentRoleTableObj getSecTentRoleTableObj() {
		return( secTentRoleTableObj );
	}

	public ICFSecSecTentRoleMembTableObj getSecTentRoleMembTableObj() {
		return( secTentRoleMembTableObj );
	}

	public ICFSecSecUserTableObj getSecUserTableObj() {
		return( secUserTableObj );
	}

	public ICFSecSecUserEMConfTableObj getSecUserEMConfTableObj() {
		return( secUserEMConfTableObj );
	}

	public ICFSecSecUserPWHistoryTableObj getSecUserPWHistoryTableObj() {
		return( secUserPWHistoryTableObj );
	}

	public ICFSecSecUserPWResetTableObj getSecUserPWResetTableObj() {
		return( secUserPWResetTableObj );
	}

	public ICFSecSecUserPasswordTableObj getSecUserPasswordTableObj() {
		return( secUserPasswordTableObj );
	}

	public ICFSecSysClusterTableObj getSysClusterTableObj() {
		return( sysClusterTableObj );
	}

	public ICFSecTableInfoTableObj getTableInfoTableObj() {
		return( tableInfoTableObj );
	}

	public ICFSecTenantTableObj getTenantTableObj() {
		return( tenantTableObj );
	}
}
