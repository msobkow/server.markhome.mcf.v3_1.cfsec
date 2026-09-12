// Description: Java 25 Public Schema Object interface for CFSecPub.

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

package server.markhome.mcf.v3_1.cfsec.cfsecpubobj;

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

public interface ICFSecPubSchemaObj
{
	ICFSecPubAuthorization getAuthorization();
	void setAuthorization( ICFSecPubAuthorization value );

	String getSchemaName();

	void setSecClusterName( String value );
	ICFSecPubClusterObj getSecCluster();
	void setSecCluster( ICFSecPubClusterObj value );

	void setSecTenantName( String value );
	ICFSecPubTenantObj getSecTenant();
	void setSecTenant( ICFSecPubTenantObj value );

	void setSecUserName( String value );
	ICFSecPubSecUserObj getSecUser();
	void setSecUser( ICFSecPubSecUserObj value );

	void setSecSessionId( CFLibDbKeyHash256 value );
	ICFSecPubSecSessionObj getSecSession();
	void setSecSession( ICFSecPubSecSessionObj value );

	void logout();

	void minimizeMemory();

	public ICFSecPubSchema getCFSecPubBackingStore();
	public void setCFSecPubBackingStore(ICFSecPubSchema cfsecpubBackingStore);

	/**
	 *	Get the Cluster interface for the schema.
	 *
	 *	@return	The ICFSecPubClusterTableObj interface implementation for the schema.
	 */
	ICFSecPubClusterTableObj getClusterTableObj();

	/**
	 *	Get the ISOCcy interface for the schema.
	 *
	 *	@return	The ICFSecPubISOCcyTableObj interface implementation for the schema.
	 */
	ICFSecPubISOCcyTableObj getISOCcyTableObj();

	/**
	 *	Get the ISOCtry interface for the schema.
	 *
	 *	@return	The ICFSecPubISOCtryTableObj interface implementation for the schema.
	 */
	ICFSecPubISOCtryTableObj getISOCtryTableObj();

	/**
	 *	Get the ISOCtryCcy interface for the schema.
	 *
	 *	@return	The ICFSecPubISOCtryCcyTableObj interface implementation for the schema.
	 */
	ICFSecPubISOCtryCcyTableObj getISOCtryCcyTableObj();

	/**
	 *	Get the ISOCtryLang interface for the schema.
	 *
	 *	@return	The ICFSecPubISOCtryLangTableObj interface implementation for the schema.
	 */
	ICFSecPubISOCtryLangTableObj getISOCtryLangTableObj();

	/**
	 *	Get the ISOLang interface for the schema.
	 *
	 *	@return	The ICFSecPubISOLangTableObj interface implementation for the schema.
	 */
	ICFSecPubISOLangTableObj getISOLangTableObj();

	/**
	 *	Get the ISOTZone interface for the schema.
	 *
	 *	@return	The ICFSecPubISOTZoneTableObj interface implementation for the schema.
	 */
	ICFSecPubISOTZoneTableObj getISOTZoneTableObj();

	/**
	 *	Get the SecSession interface for the schema.
	 *
	 *	@return	The ICFSecPubSecSessionTableObj interface implementation for the schema.
	 */
	ICFSecPubSecSessionTableObj getSecSessionTableObj();

	/**
	 *	Get the SecSysGrp interface for the schema.
	 *
	 *	@return	The ICFSecPubSecSysGrpTableObj interface implementation for the schema.
	 */
	ICFSecPubSecSysGrpTableObj getSecSysGrpTableObj();

	/**
	 *	Get the SecSysRole interface for the schema.
	 *
	 *	@return	The ICFSecPubSecSysRoleTableObj interface implementation for the schema.
	 */
	ICFSecPubSecSysRoleTableObj getSecSysRoleTableObj();

	/**
	 *	Get the SecUser interface for the schema.
	 *
	 *	@return	The ICFSecPubSecUserTableObj interface implementation for the schema.
	 */
	ICFSecPubSecUserTableObj getSecUserTableObj();

	/**
	 *	Get the SysCluster interface for the schema.
	 *
	 *	@return	The ICFSecPubSysClusterTableObj interface implementation for the schema.
	 */
	ICFSecPubSysClusterTableObj getSysClusterTableObj();

	/**
	 *	Get the TableInfo interface for the schema.
	 *
	 *	@return	The ICFSecPubTableInfoTableObj interface implementation for the schema.
	 */
	ICFSecPubTableInfoTableObj getTableInfoTableObj();

	/**
	 *	Get the Tenant interface for the schema.
	 *
	 *	@return	The ICFSecPubTenantTableObj interface implementation for the schema.
	 */
	ICFSecPubTenantTableObj getTenantTableObj();
}
