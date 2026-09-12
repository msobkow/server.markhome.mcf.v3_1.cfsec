// Description: Java 25 interface for a CFSec data object factory.

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

public interface ICFSecFactory
{
	/**
	 *	Get the Cluster Factory interface for the schema.
	 *
	 *	@return	The Cluster Factory interface for the schema.
	 */
	public ICFSecClusterFactory getFactoryCluster();

	/**
	 *	Get the ISOCcy Factory interface for the schema.
	 *
	 *	@return	The ISOCcy Factory interface for the schema.
	 */
	public ICFSecISOCcyFactory getFactoryISOCcy();

	/**
	 *	Get the ISOCtry Factory interface for the schema.
	 *
	 *	@return	The ISOCtry Factory interface for the schema.
	 */
	public ICFSecISOCtryFactory getFactoryISOCtry();

	/**
	 *	Get the ISOCtryCcy Factory interface for the schema.
	 *
	 *	@return	The ISOCtryCcy Factory interface for the schema.
	 */
	public ICFSecISOCtryCcyFactory getFactoryISOCtryCcy();

	/**
	 *	Get the ISOCtryLang Factory interface for the schema.
	 *
	 *	@return	The ISOCtryLang Factory interface for the schema.
	 */
	public ICFSecISOCtryLangFactory getFactoryISOCtryLang();

	/**
	 *	Get the ISOLang Factory interface for the schema.
	 *
	 *	@return	The ISOLang Factory interface for the schema.
	 */
	public ICFSecISOLangFactory getFactoryISOLang();

	/**
	 *	Get the ISOTZone Factory interface for the schema.
	 *
	 *	@return	The ISOTZone Factory interface for the schema.
	 */
	public ICFSecISOTZoneFactory getFactoryISOTZone();

	/**
	 *	Get the SecClusGrp Factory interface for the schema.
	 *
	 *	@return	The SecClusGrp Factory interface for the schema.
	 */
	public ICFSecSecClusGrpFactory getFactorySecClusGrp();

	/**
	 *	Get the SecClusGrpMemb Factory interface for the schema.
	 *
	 *	@return	The SecClusGrpMemb Factory interface for the schema.
	 */
	public ICFSecSecClusGrpMembFactory getFactorySecClusGrpMemb();

	/**
	 *	Get the SecClusRole Factory interface for the schema.
	 *
	 *	@return	The SecClusRole Factory interface for the schema.
	 */
	public ICFSecSecClusRoleFactory getFactorySecClusRole();

	/**
	 *	Get the SecClusRoleMemb Factory interface for the schema.
	 *
	 *	@return	The SecClusRoleMemb Factory interface for the schema.
	 */
	public ICFSecSecClusRoleMembFactory getFactorySecClusRoleMemb();

	/**
	 *	Get the SecSession Factory interface for the schema.
	 *
	 *	@return	The SecSession Factory interface for the schema.
	 */
	public ICFSecSecSessionFactory getFactorySecSession();

	/**
	 *	Get the SecSysGrp Factory interface for the schema.
	 *
	 *	@return	The SecSysGrp Factory interface for the schema.
	 */
	public ICFSecSecSysGrpFactory getFactorySecSysGrp();

	/**
	 *	Get the SecSysGrpInc Factory interface for the schema.
	 *
	 *	@return	The SecSysGrpInc Factory interface for the schema.
	 */
	public ICFSecSecSysGrpIncFactory getFactorySecSysGrpInc();

	/**
	 *	Get the SecSysGrpMemb Factory interface for the schema.
	 *
	 *	@return	The SecSysGrpMemb Factory interface for the schema.
	 */
	public ICFSecSecSysGrpMembFactory getFactorySecSysGrpMemb();

	/**
	 *	Get the SecSysRole Factory interface for the schema.
	 *
	 *	@return	The SecSysRole Factory interface for the schema.
	 */
	public ICFSecSecSysRoleFactory getFactorySecSysRole();

	/**
	 *	Get the SecSysRoleEnables Factory interface for the schema.
	 *
	 *	@return	The SecSysRoleEnables Factory interface for the schema.
	 */
	public ICFSecSecSysRoleEnablesFactory getFactorySecSysRoleEnables();

	/**
	 *	Get the SecSysRoleMemb Factory interface for the schema.
	 *
	 *	@return	The SecSysRoleMemb Factory interface for the schema.
	 */
	public ICFSecSecSysRoleMembFactory getFactorySecSysRoleMemb();

	/**
	 *	Get the SecTentGrp Factory interface for the schema.
	 *
	 *	@return	The SecTentGrp Factory interface for the schema.
	 */
	public ICFSecSecTentGrpFactory getFactorySecTentGrp();

	/**
	 *	Get the SecTentGrpMemb Factory interface for the schema.
	 *
	 *	@return	The SecTentGrpMemb Factory interface for the schema.
	 */
	public ICFSecSecTentGrpMembFactory getFactorySecTentGrpMemb();

	/**
	 *	Get the SecTentRole Factory interface for the schema.
	 *
	 *	@return	The SecTentRole Factory interface for the schema.
	 */
	public ICFSecSecTentRoleFactory getFactorySecTentRole();

	/**
	 *	Get the SecTentRoleMemb Factory interface for the schema.
	 *
	 *	@return	The SecTentRoleMemb Factory interface for the schema.
	 */
	public ICFSecSecTentRoleMembFactory getFactorySecTentRoleMemb();

	/**
	 *	Get the SecUser Factory interface for the schema.
	 *
	 *	@return	The SecUser Factory interface for the schema.
	 */
	public ICFSecSecUserFactory getFactorySecUser();

	/**
	 *	Get the SecUserEMConf Factory interface for the schema.
	 *
	 *	@return	The SecUserEMConf Factory interface for the schema.
	 */
	public ICFSecSecUserEMConfFactory getFactorySecUserEMConf();

	/**
	 *	Get the SecUserPWHistory Factory interface for the schema.
	 *
	 *	@return	The SecUserPWHistory Factory interface for the schema.
	 */
	public ICFSecSecUserPWHistoryFactory getFactorySecUserPWHistory();

	/**
	 *	Get the SecUserPWReset Factory interface for the schema.
	 *
	 *	@return	The SecUserPWReset Factory interface for the schema.
	 */
	public ICFSecSecUserPWResetFactory getFactorySecUserPWReset();

	/**
	 *	Get the SecUserPassword Factory interface for the schema.
	 *
	 *	@return	The SecUserPassword Factory interface for the schema.
	 */
	public ICFSecSecUserPasswordFactory getFactorySecUserPassword();

	/**
	 *	Get the SysCluster Factory interface for the schema.
	 *
	 *	@return	The SysCluster Factory interface for the schema.
	 */
	public ICFSecSysClusterFactory getFactorySysCluster();

	/**
	 *	Get the TableInfo Factory interface for the schema.
	 *
	 *	@return	The TableInfo Factory interface for the schema.
	 */
	public ICFSecTableInfoFactory getFactoryTableInfo();

	/**
	 *	Get the Tenant Factory interface for the schema.
	 *
	 *	@return	The Tenant Factory interface for the schema.
	 */
	public ICFSecTenantFactory getFactoryTenant();

}
