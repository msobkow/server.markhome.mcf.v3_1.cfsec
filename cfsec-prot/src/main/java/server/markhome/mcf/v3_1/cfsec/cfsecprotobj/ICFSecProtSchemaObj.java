// Description: Java 25 Protected Schema Object interface for CFSecProt.

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

package server.markhome.mcf.v3_1.cfsec.cfsecprotobj;

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
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public interface ICFSecProtSchemaObj
{
	ICFSecProtAuthorization getAuthorization();
	void setAuthorization( ICFSecProtAuthorization value );

	String getSchemaName();

	void setSecClusterName( String value );
	ICFSecProtClusterObj getSecCluster();
	void setSecCluster( ICFSecProtClusterObj value );

	void setSecTenantName( String value );
	ICFSecProtTenantObj getSecTenant();
	void setSecTenant( ICFSecProtTenantObj value );

	void setSecUserName( String value );
	ICFSecProtSecUserObj getSecUser();
	void setSecUser( ICFSecProtSecUserObj value );

	void setSecSessionId( CFLibDbKeyHash256 value );
	ICFSecProtSecSessionObj getSecSession();
	void setSecSession( ICFSecProtSecSessionObj value );

	void logout();

	void minimizeMemory();

	public ICFSecProtSchema getCFSecProtBackingStore();
	public void setCFSecProtBackingStore(ICFSecProtSchema cfsecprotBackingStore);

	/**
	 *	Get the Cluster interface for the schema.
	 *
	 *	@return	The ICFSecProtClusterTableObj interface implementation for the schema.
	 */
	ICFSecProtClusterTableObj getClusterTableObj();

	/**
	 *	Get the ISOCcy interface for the schema.
	 *
	 *	@return	The ICFSecProtISOCcyTableObj interface implementation for the schema.
	 */
	ICFSecProtISOCcyTableObj getISOCcyTableObj();

	/**
	 *	Get the ISOCtry interface for the schema.
	 *
	 *	@return	The ICFSecProtISOCtryTableObj interface implementation for the schema.
	 */
	ICFSecProtISOCtryTableObj getISOCtryTableObj();

	/**
	 *	Get the ISOCtryCcy interface for the schema.
	 *
	 *	@return	The ICFSecProtISOCtryCcyTableObj interface implementation for the schema.
	 */
	ICFSecProtISOCtryCcyTableObj getISOCtryCcyTableObj();

	/**
	 *	Get the ISOCtryLang interface for the schema.
	 *
	 *	@return	The ICFSecProtISOCtryLangTableObj interface implementation for the schema.
	 */
	ICFSecProtISOCtryLangTableObj getISOCtryLangTableObj();

	/**
	 *	Get the ISOLang interface for the schema.
	 *
	 *	@return	The ICFSecProtISOLangTableObj interface implementation for the schema.
	 */
	ICFSecProtISOLangTableObj getISOLangTableObj();

	/**
	 *	Get the ISOTZone interface for the schema.
	 *
	 *	@return	The ICFSecProtISOTZoneTableObj interface implementation for the schema.
	 */
	ICFSecProtISOTZoneTableObj getISOTZoneTableObj();

	/**
	 *	Get the SecClusGrp interface for the schema.
	 *
	 *	@return	The ICFSecProtSecClusGrpTableObj interface implementation for the schema.
	 */
	ICFSecProtSecClusGrpTableObj getSecClusGrpTableObj();

	/**
	 *	Get the SecClusGrpMemb interface for the schema.
	 *
	 *	@return	The ICFSecProtSecClusGrpMembTableObj interface implementation for the schema.
	 */
	ICFSecProtSecClusGrpMembTableObj getSecClusGrpMembTableObj();

	/**
	 *	Get the SecClusRole interface for the schema.
	 *
	 *	@return	The ICFSecProtSecClusRoleTableObj interface implementation for the schema.
	 */
	ICFSecProtSecClusRoleTableObj getSecClusRoleTableObj();

	/**
	 *	Get the SecClusRoleMemb interface for the schema.
	 *
	 *	@return	The ICFSecProtSecClusRoleMembTableObj interface implementation for the schema.
	 */
	ICFSecProtSecClusRoleMembTableObj getSecClusRoleMembTableObj();

	/**
	 *	Get the SecSession interface for the schema.
	 *
	 *	@return	The ICFSecProtSecSessionTableObj interface implementation for the schema.
	 */
	ICFSecProtSecSessionTableObj getSecSessionTableObj();

	/**
	 *	Get the SecSysGrp interface for the schema.
	 *
	 *	@return	The ICFSecProtSecSysGrpTableObj interface implementation for the schema.
	 */
	ICFSecProtSecSysGrpTableObj getSecSysGrpTableObj();

	/**
	 *	Get the SecSysGrpInc interface for the schema.
	 *
	 *	@return	The ICFSecProtSecSysGrpIncTableObj interface implementation for the schema.
	 */
	ICFSecProtSecSysGrpIncTableObj getSecSysGrpIncTableObj();

	/**
	 *	Get the SecSysGrpMemb interface for the schema.
	 *
	 *	@return	The ICFSecProtSecSysGrpMembTableObj interface implementation for the schema.
	 */
	ICFSecProtSecSysGrpMembTableObj getSecSysGrpMembTableObj();

	/**
	 *	Get the SecSysRole interface for the schema.
	 *
	 *	@return	The ICFSecProtSecSysRoleTableObj interface implementation for the schema.
	 */
	ICFSecProtSecSysRoleTableObj getSecSysRoleTableObj();

	/**
	 *	Get the SecSysRoleEnables interface for the schema.
	 *
	 *	@return	The ICFSecProtSecSysRoleEnablesTableObj interface implementation for the schema.
	 */
	ICFSecProtSecSysRoleEnablesTableObj getSecSysRoleEnablesTableObj();

	/**
	 *	Get the SecSysRoleMemb interface for the schema.
	 *
	 *	@return	The ICFSecProtSecSysRoleMembTableObj interface implementation for the schema.
	 */
	ICFSecProtSecSysRoleMembTableObj getSecSysRoleMembTableObj();

	/**
	 *	Get the SecTentGrp interface for the schema.
	 *
	 *	@return	The ICFSecProtSecTentGrpTableObj interface implementation for the schema.
	 */
	ICFSecProtSecTentGrpTableObj getSecTentGrpTableObj();

	/**
	 *	Get the SecTentGrpMemb interface for the schema.
	 *
	 *	@return	The ICFSecProtSecTentGrpMembTableObj interface implementation for the schema.
	 */
	ICFSecProtSecTentGrpMembTableObj getSecTentGrpMembTableObj();

	/**
	 *	Get the SecTentRole interface for the schema.
	 *
	 *	@return	The ICFSecProtSecTentRoleTableObj interface implementation for the schema.
	 */
	ICFSecProtSecTentRoleTableObj getSecTentRoleTableObj();

	/**
	 *	Get the SecTentRoleMemb interface for the schema.
	 *
	 *	@return	The ICFSecProtSecTentRoleMembTableObj interface implementation for the schema.
	 */
	ICFSecProtSecTentRoleMembTableObj getSecTentRoleMembTableObj();

	/**
	 *	Get the SecUser interface for the schema.
	 *
	 *	@return	The ICFSecProtSecUserTableObj interface implementation for the schema.
	 */
	ICFSecProtSecUserTableObj getSecUserTableObj();

	/**
	 *	Get the SecUserEMConf interface for the schema.
	 *
	 *	@return	The ICFSecProtSecUserEMConfTableObj interface implementation for the schema.
	 */
	ICFSecProtSecUserEMConfTableObj getSecUserEMConfTableObj();

	/**
	 *	Get the SecUserPWReset interface for the schema.
	 *
	 *	@return	The ICFSecProtSecUserPWResetTableObj interface implementation for the schema.
	 */
	ICFSecProtSecUserPWResetTableObj getSecUserPWResetTableObj();

	/**
	 *	Get the SysCluster interface for the schema.
	 *
	 *	@return	The ICFSecProtSysClusterTableObj interface implementation for the schema.
	 */
	ICFSecProtSysClusterTableObj getSysClusterTableObj();

	/**
	 *	Get the TableInfo interface for the schema.
	 *
	 *	@return	The ICFSecProtTableInfoTableObj interface implementation for the schema.
	 */
	ICFSecProtTableInfoTableObj getTableInfoTableObj();

	/**
	 *	Get the Tenant interface for the schema.
	 *
	 *	@return	The ICFSecProtTenantTableObj interface implementation for the schema.
	 */
	ICFSecProtTenantTableObj getTenantTableObj();
}
