// Description: Java 25 Schema Object interface for CFSec.

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
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

public interface ICFSecSchemaObj
{
	ICFSecAuthorization getAuthorization();
	void setAuthorization( ICFSecAuthorization value );

	String getSchemaName();

	void setSecClusterName( String value );
	ICFSecClusterObj getSecCluster();
	void setSecCluster( ICFSecClusterObj value );

	void setSecTenantName( String value );
	ICFSecTenantObj getSecTenant();
	void setSecTenant( ICFSecTenantObj value );

	void setSecUserName( String value );
	ICFSecSecUserObj getSecUser();
	void setSecUser( ICFSecSecUserObj value );

	void setSecSessionId( CFLibDbKeyHash256 value );
	ICFSecSecSessionObj getSecSession();
	void setSecSession( ICFSecSecSessionObj value );

	void logout();

	void minimizeMemory();

	public ICFSecSchema getCFSecBackingStore();
	public void setCFSecBackingStore(ICFSecSchema cfsecBackingStore);

	/**
	 *	Get the Cluster interface for the schema.
	 *
	 *	@return	The ICFSecClusterTableObj interface implementation for the schema.
	 */
	ICFSecClusterTableObj getClusterTableObj();

	/**
	 *	Get the ISOCcy interface for the schema.
	 *
	 *	@return	The ICFSecISOCcyTableObj interface implementation for the schema.
	 */
	ICFSecISOCcyTableObj getISOCcyTableObj();

	/**
	 *	Get the ISOCtry interface for the schema.
	 *
	 *	@return	The ICFSecISOCtryTableObj interface implementation for the schema.
	 */
	ICFSecISOCtryTableObj getISOCtryTableObj();

	/**
	 *	Get the ISOCtryCcy interface for the schema.
	 *
	 *	@return	The ICFSecISOCtryCcyTableObj interface implementation for the schema.
	 */
	ICFSecISOCtryCcyTableObj getISOCtryCcyTableObj();

	/**
	 *	Get the ISOCtryLang interface for the schema.
	 *
	 *	@return	The ICFSecISOCtryLangTableObj interface implementation for the schema.
	 */
	ICFSecISOCtryLangTableObj getISOCtryLangTableObj();

	/**
	 *	Get the ISOLang interface for the schema.
	 *
	 *	@return	The ICFSecISOLangTableObj interface implementation for the schema.
	 */
	ICFSecISOLangTableObj getISOLangTableObj();

	/**
	 *	Get the ISOTZone interface for the schema.
	 *
	 *	@return	The ICFSecISOTZoneTableObj interface implementation for the schema.
	 */
	ICFSecISOTZoneTableObj getISOTZoneTableObj();

	/**
	 *	Get the SecClusGrp interface for the schema.
	 *
	 *	@return	The ICFSecSecClusGrpTableObj interface implementation for the schema.
	 */
	ICFSecSecClusGrpTableObj getSecClusGrpTableObj();

	/**
	 *	Get the SecClusGrpMemb interface for the schema.
	 *
	 *	@return	The ICFSecSecClusGrpMembTableObj interface implementation for the schema.
	 */
	ICFSecSecClusGrpMembTableObj getSecClusGrpMembTableObj();

	/**
	 *	Get the SecClusRole interface for the schema.
	 *
	 *	@return	The ICFSecSecClusRoleTableObj interface implementation for the schema.
	 */
	ICFSecSecClusRoleTableObj getSecClusRoleTableObj();

	/**
	 *	Get the SecClusRoleMemb interface for the schema.
	 *
	 *	@return	The ICFSecSecClusRoleMembTableObj interface implementation for the schema.
	 */
	ICFSecSecClusRoleMembTableObj getSecClusRoleMembTableObj();

	/**
	 *	Get the SecSession interface for the schema.
	 *
	 *	@return	The ICFSecSecSessionTableObj interface implementation for the schema.
	 */
	ICFSecSecSessionTableObj getSecSessionTableObj();

	/**
	 *	Get the SecSysGrp interface for the schema.
	 *
	 *	@return	The ICFSecSecSysGrpTableObj interface implementation for the schema.
	 */
	ICFSecSecSysGrpTableObj getSecSysGrpTableObj();

	/**
	 *	Get the SecSysGrpInc interface for the schema.
	 *
	 *	@return	The ICFSecSecSysGrpIncTableObj interface implementation for the schema.
	 */
	ICFSecSecSysGrpIncTableObj getSecSysGrpIncTableObj();

	/**
	 *	Get the SecSysGrpMemb interface for the schema.
	 *
	 *	@return	The ICFSecSecSysGrpMembTableObj interface implementation for the schema.
	 */
	ICFSecSecSysGrpMembTableObj getSecSysGrpMembTableObj();

	/**
	 *	Get the SecSysRole interface for the schema.
	 *
	 *	@return	The ICFSecSecSysRoleTableObj interface implementation for the schema.
	 */
	ICFSecSecSysRoleTableObj getSecSysRoleTableObj();

	/**
	 *	Get the SecSysRoleEnables interface for the schema.
	 *
	 *	@return	The ICFSecSecSysRoleEnablesTableObj interface implementation for the schema.
	 */
	ICFSecSecSysRoleEnablesTableObj getSecSysRoleEnablesTableObj();

	/**
	 *	Get the SecSysRoleMemb interface for the schema.
	 *
	 *	@return	The ICFSecSecSysRoleMembTableObj interface implementation for the schema.
	 */
	ICFSecSecSysRoleMembTableObj getSecSysRoleMembTableObj();

	/**
	 *	Get the SecTentGrp interface for the schema.
	 *
	 *	@return	The ICFSecSecTentGrpTableObj interface implementation for the schema.
	 */
	ICFSecSecTentGrpTableObj getSecTentGrpTableObj();

	/**
	 *	Get the SecTentGrpMemb interface for the schema.
	 *
	 *	@return	The ICFSecSecTentGrpMembTableObj interface implementation for the schema.
	 */
	ICFSecSecTentGrpMembTableObj getSecTentGrpMembTableObj();

	/**
	 *	Get the SecTentRole interface for the schema.
	 *
	 *	@return	The ICFSecSecTentRoleTableObj interface implementation for the schema.
	 */
	ICFSecSecTentRoleTableObj getSecTentRoleTableObj();

	/**
	 *	Get the SecTentRoleMemb interface for the schema.
	 *
	 *	@return	The ICFSecSecTentRoleMembTableObj interface implementation for the schema.
	 */
	ICFSecSecTentRoleMembTableObj getSecTentRoleMembTableObj();

	/**
	 *	Get the SecUser interface for the schema.
	 *
	 *	@return	The ICFSecSecUserTableObj interface implementation for the schema.
	 */
	ICFSecSecUserTableObj getSecUserTableObj();

	/**
	 *	Get the SecUserEMConf interface for the schema.
	 *
	 *	@return	The ICFSecSecUserEMConfTableObj interface implementation for the schema.
	 */
	ICFSecSecUserEMConfTableObj getSecUserEMConfTableObj();

	/**
	 *	Get the SecUserPWHistory interface for the schema.
	 *
	 *	@return	The ICFSecSecUserPWHistoryTableObj interface implementation for the schema.
	 */
	ICFSecSecUserPWHistoryTableObj getSecUserPWHistoryTableObj();

	/**
	 *	Get the SecUserPWReset interface for the schema.
	 *
	 *	@return	The ICFSecSecUserPWResetTableObj interface implementation for the schema.
	 */
	ICFSecSecUserPWResetTableObj getSecUserPWResetTableObj();

	/**
	 *	Get the SecUserPassword interface for the schema.
	 *
	 *	@return	The ICFSecSecUserPasswordTableObj interface implementation for the schema.
	 */
	ICFSecSecUserPasswordTableObj getSecUserPasswordTableObj();

	/**
	 *	Get the SysCluster interface for the schema.
	 *
	 *	@return	The ICFSecSysClusterTableObj interface implementation for the schema.
	 */
	ICFSecSysClusterTableObj getSysClusterTableObj();

	/**
	 *	Get the TableInfo interface for the schema.
	 *
	 *	@return	The ICFSecTableInfoTableObj interface implementation for the schema.
	 */
	ICFSecTableInfoTableObj getTableInfoTableObj();

	/**
	 *	Get the Tenant interface for the schema.
	 *
	 *	@return	The ICFSecTenantTableObj interface implementation for the schema.
	 */
	ICFSecTenantTableObj getTenantTableObj();
}
