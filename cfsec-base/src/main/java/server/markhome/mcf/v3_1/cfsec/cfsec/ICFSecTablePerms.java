// Description: Java 25 CFSec Table Permissions Interface.

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

package server.markhome.mcf.v3_1.cfsec.cfsec;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;


public interface ICFSecTablePerms
{
	/**
	 *	Is the session allowed to create Cluster instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateCluster( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Cluster instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadCluster( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Cluster instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateCluster( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Cluster instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteCluster( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ISOCcy instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateISOCcy( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ISOCcy instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadISOCcy( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ISOCcy instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateISOCcy( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ISOCcy instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteISOCcy( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ISOCtry instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateISOCtry( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ISOCtry instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadISOCtry( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ISOCtry instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateISOCtry( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ISOCtry instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteISOCtry( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ISOCtryCcy instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateISOCtryCcy( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ISOCtryCcy instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadISOCtryCcy( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ISOCtryCcy instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateISOCtryCcy( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ISOCtryCcy instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteISOCtryCcy( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ISOCtryLang instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateISOCtryLang( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ISOCtryLang instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadISOCtryLang( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ISOCtryLang instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateISOCtryLang( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ISOCtryLang instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteISOCtryLang( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ISOLang instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateISOLang( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ISOLang instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadISOLang( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ISOLang instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateISOLang( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ISOLang instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteISOLang( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create ISOTZone instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateISOTZone( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read ISOTZone instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadISOTZone( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update ISOTZone instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateISOTZone( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete ISOTZone instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteISOTZone( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecClusGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecClusGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecClusGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecClusGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecClusGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecClusGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecClusGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecClusGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecClusGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecClusGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecClusGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecClusGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecClusGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecClusGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecClusGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecClusGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecClusRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecClusRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecClusRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecClusRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecClusRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecClusRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecClusRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecClusRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecClusRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecClusRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecClusRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecClusRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecClusRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecClusRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecClusRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecClusRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecSession instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecSession( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecSession instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecSession( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecSession instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecSession( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecSession instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecSession( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecSysGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecSysGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecSysGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecSysGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecSysGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecSysGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecSysGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecSysGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecSysGrpInc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecSysGrpInc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecSysGrpInc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecSysGrpInc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecSysGrpInc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecSysGrpInc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecSysGrpInc instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecSysGrpInc( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecSysGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecSysGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecSysGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecSysGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecSysGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecSysGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecSysGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecSysGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecSysRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecSysRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecSysRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecSysRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecSysRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecSysRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecSysRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecSysRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecSysRoleEnables instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecSysRoleEnables( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecSysRoleEnables instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecSysRoleEnables( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecSysRoleEnables instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecSysRoleEnables( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecSysRoleEnables instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecSysRoleEnables( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecSysRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecSysRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecSysRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecSysRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecSysRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecSysRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecSysRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecSysRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecTentGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecTentGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecTentGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecTentGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecTentGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecTentGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecTentGrp instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecTentGrp( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecTentGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecTentGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecTentGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecTentGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecTentGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecTentGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecTentGrpMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecTentGrpMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecTentRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecTentRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecTentRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecTentRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecTentRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecTentRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecTentRole instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecTentRole( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecTentRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecTentRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecTentRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecTentRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecTentRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecTentRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecTentRoleMemb instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecTentRoleMemb( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecUser instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecUser( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecUser instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecUser( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecUser instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecUser( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecUser instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecUser( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecUserEMConf instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecUserEMConf( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecUserEMConf instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecUserEMConf( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecUserEMConf instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecUserEMConf( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecUserEMConf instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecUserEMConf( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecUserPWHistory instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecUserPWHistory( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecUserPWHistory instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecUserPWHistory( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecUserPWHistory instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecUserPWHistory( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecUserPWHistory instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecUserPWHistory( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecUserPWReset instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecUserPWReset( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecUserPWReset instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecUserPWReset( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecUserPWReset instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecUserPWReset( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecUserPWReset instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecUserPWReset( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SecUserPassword instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSecUserPassword( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SecUserPassword instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSecUserPassword( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SecUserPassword instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSecUserPassword( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SecUserPassword instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSecUserPassword( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create SysCluster instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateSysCluster( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read SysCluster instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadSysCluster( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update SysCluster instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateSysCluster( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete SysCluster instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteSysCluster( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create TableInfo instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTableInfo( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read TableInfo instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTableInfo( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update TableInfo instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTableInfo( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete TableInfo instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTableInfo( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to create Tenant instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowCreateTenant( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to read Tenant instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowReadTenant( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to update Tenant instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowUpdateTenant( CFSecAuthorization Authorization );

	/**
	 *	Is the session allowed to delete Tenant instances?
	 *
	 *	@param	Authorization	The session authorization information.
	 */
	boolean allowDeleteTenant( CFSecAuthorization Authorization );
}
