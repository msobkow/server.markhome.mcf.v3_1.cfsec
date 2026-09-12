// Description: Java 25 buffer implementation of a CFSec factory service.

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;

@Service("cfsec31BuffFactoryService")
public class CFSecBuffFactoryService
	implements ICFSecFactory
{

	@Autowired
	@Qualifier("cfsec31BuffClusterFactoryService")
	protected CFSecBuffClusterFactoryService clusterFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffISOCcyFactoryService")
	protected CFSecBuffISOCcyFactoryService isoccyFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffISOCtryFactoryService")
	protected CFSecBuffISOCtryFactoryService isoctryFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffISOCtryCcyFactoryService")
	protected CFSecBuffISOCtryCcyFactoryService isoctryccyFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffISOCtryLangFactoryService")
	protected CFSecBuffISOCtryLangFactoryService isoctrylangFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffISOLangFactoryService")
	protected CFSecBuffISOLangFactoryService isolangFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffISOTZoneFactoryService")
	protected CFSecBuffISOTZoneFactoryService isotzoneFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecClusGrpFactoryService")
	protected CFSecBuffSecClusGrpFactoryService secclusgrpFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecClusGrpMembFactoryService")
	protected CFSecBuffSecClusGrpMembFactoryService secclusgrpmembFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecClusRoleFactoryService")
	protected CFSecBuffSecClusRoleFactoryService secclusroleFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecClusRoleMembFactoryService")
	protected CFSecBuffSecClusRoleMembFactoryService secclusrolemembFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecSessionFactoryService")
	protected CFSecBuffSecSessionFactoryService secsessionFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecSysGrpFactoryService")
	protected CFSecBuffSecSysGrpFactoryService secsysgrpFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecSysGrpIncFactoryService")
	protected CFSecBuffSecSysGrpIncFactoryService secsysgrpincFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecSysGrpMembFactoryService")
	protected CFSecBuffSecSysGrpMembFactoryService secsysgrpmembFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecSysRoleFactoryService")
	protected CFSecBuffSecSysRoleFactoryService secsysroleFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecSysRoleEnablesFactoryService")
	protected CFSecBuffSecSysRoleEnablesFactoryService secsysroleenablesFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecSysRoleMembFactoryService")
	protected CFSecBuffSecSysRoleMembFactoryService secsysrolemembFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecTentGrpFactoryService")
	protected CFSecBuffSecTentGrpFactoryService sectentgrpFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecTentGrpMembFactoryService")
	protected CFSecBuffSecTentGrpMembFactoryService sectentgrpmembFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecTentRoleFactoryService")
	protected CFSecBuffSecTentRoleFactoryService sectentroleFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecTentRoleMembFactoryService")
	protected CFSecBuffSecTentRoleMembFactoryService sectentrolemembFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecUserFactoryService")
	protected CFSecBuffSecUserFactoryService secuserFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecUserEMConfFactoryService")
	protected CFSecBuffSecUserEMConfFactoryService secuseremconfFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecUserPWHistoryFactoryService")
	protected CFSecBuffSecUserPWHistoryFactoryService secuserpwhistoryFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecUserPWResetFactoryService")
	protected CFSecBuffSecUserPWResetFactoryService secuserpwresetFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSecUserPasswordFactoryService")
	protected CFSecBuffSecUserPasswordFactoryService secuserpasswordFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffSysClusterFactoryService")
	protected CFSecBuffSysClusterFactoryService sysclusterFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffTableInfoFactoryService")
	protected CFSecBuffTableInfoFactoryService tableinfoFactoryService;

	@Autowired
	@Qualifier("cfsec31BuffTenantFactoryService")
	protected CFSecBuffTenantFactoryService tenantFactoryService;


	public CFSecBuffFactoryService() { }

	@Override
	public ICFSecClusterFactory getFactoryCluster() {
		return(clusterFactoryService);
	}

	public CFSecBuffClusterFactoryService getClusterFactoryService() {
		return(clusterFactoryService);
	}

	@Override
	public ICFSecISOCcyFactory getFactoryISOCcy() {
		return(isoccyFactoryService);
	}

	public CFSecBuffISOCcyFactoryService getISOCcyFactoryService() {
		return(isoccyFactoryService);
	}

	@Override
	public ICFSecISOCtryFactory getFactoryISOCtry() {
		return(isoctryFactoryService);
	}

	public CFSecBuffISOCtryFactoryService getISOCtryFactoryService() {
		return(isoctryFactoryService);
	}

	@Override
	public ICFSecISOCtryCcyFactory getFactoryISOCtryCcy() {
		return(isoctryccyFactoryService);
	}

	public CFSecBuffISOCtryCcyFactoryService getISOCtryCcyFactoryService() {
		return(isoctryccyFactoryService);
	}

	@Override
	public ICFSecISOCtryLangFactory getFactoryISOCtryLang() {
		return(isoctrylangFactoryService);
	}

	public CFSecBuffISOCtryLangFactoryService getISOCtryLangFactoryService() {
		return(isoctrylangFactoryService);
	}

	@Override
	public ICFSecISOLangFactory getFactoryISOLang() {
		return(isolangFactoryService);
	}

	public CFSecBuffISOLangFactoryService getISOLangFactoryService() {
		return(isolangFactoryService);
	}

	@Override
	public ICFSecISOTZoneFactory getFactoryISOTZone() {
		return(isotzoneFactoryService);
	}

	public CFSecBuffISOTZoneFactoryService getISOTZoneFactoryService() {
		return(isotzoneFactoryService);
	}

	@Override
	public ICFSecSecClusGrpFactory getFactorySecClusGrp() {
		return(secclusgrpFactoryService);
	}

	public CFSecBuffSecClusGrpFactoryService getSecClusGrpFactoryService() {
		return(secclusgrpFactoryService);
	}

	@Override
	public ICFSecSecClusGrpMembFactory getFactorySecClusGrpMemb() {
		return(secclusgrpmembFactoryService);
	}

	public CFSecBuffSecClusGrpMembFactoryService getSecClusGrpMembFactoryService() {
		return(secclusgrpmembFactoryService);
	}

	@Override
	public ICFSecSecClusRoleFactory getFactorySecClusRole() {
		return(secclusroleFactoryService);
	}

	public CFSecBuffSecClusRoleFactoryService getSecClusRoleFactoryService() {
		return(secclusroleFactoryService);
	}

	@Override
	public ICFSecSecClusRoleMembFactory getFactorySecClusRoleMemb() {
		return(secclusrolemembFactoryService);
	}

	public CFSecBuffSecClusRoleMembFactoryService getSecClusRoleMembFactoryService() {
		return(secclusrolemembFactoryService);
	}

	@Override
	public ICFSecSecSessionFactory getFactorySecSession() {
		return(secsessionFactoryService);
	}

	public CFSecBuffSecSessionFactoryService getSecSessionFactoryService() {
		return(secsessionFactoryService);
	}

	@Override
	public ICFSecSecSysGrpFactory getFactorySecSysGrp() {
		return(secsysgrpFactoryService);
	}

	public CFSecBuffSecSysGrpFactoryService getSecSysGrpFactoryService() {
		return(secsysgrpFactoryService);
	}

	@Override
	public ICFSecSecSysGrpIncFactory getFactorySecSysGrpInc() {
		return(secsysgrpincFactoryService);
	}

	public CFSecBuffSecSysGrpIncFactoryService getSecSysGrpIncFactoryService() {
		return(secsysgrpincFactoryService);
	}

	@Override
	public ICFSecSecSysGrpMembFactory getFactorySecSysGrpMemb() {
		return(secsysgrpmembFactoryService);
	}

	public CFSecBuffSecSysGrpMembFactoryService getSecSysGrpMembFactoryService() {
		return(secsysgrpmembFactoryService);
	}

	@Override
	public ICFSecSecSysRoleFactory getFactorySecSysRole() {
		return(secsysroleFactoryService);
	}

	public CFSecBuffSecSysRoleFactoryService getSecSysRoleFactoryService() {
		return(secsysroleFactoryService);
	}

	@Override
	public ICFSecSecSysRoleEnablesFactory getFactorySecSysRoleEnables() {
		return(secsysroleenablesFactoryService);
	}

	public CFSecBuffSecSysRoleEnablesFactoryService getSecSysRoleEnablesFactoryService() {
		return(secsysroleenablesFactoryService);
	}

	@Override
	public ICFSecSecSysRoleMembFactory getFactorySecSysRoleMemb() {
		return(secsysrolemembFactoryService);
	}

	public CFSecBuffSecSysRoleMembFactoryService getSecSysRoleMembFactoryService() {
		return(secsysrolemembFactoryService);
	}

	@Override
	public ICFSecSecTentGrpFactory getFactorySecTentGrp() {
		return(sectentgrpFactoryService);
	}

	public CFSecBuffSecTentGrpFactoryService getSecTentGrpFactoryService() {
		return(sectentgrpFactoryService);
	}

	@Override
	public ICFSecSecTentGrpMembFactory getFactorySecTentGrpMemb() {
		return(sectentgrpmembFactoryService);
	}

	public CFSecBuffSecTentGrpMembFactoryService getSecTentGrpMembFactoryService() {
		return(sectentgrpmembFactoryService);
	}

	@Override
	public ICFSecSecTentRoleFactory getFactorySecTentRole() {
		return(sectentroleFactoryService);
	}

	public CFSecBuffSecTentRoleFactoryService getSecTentRoleFactoryService() {
		return(sectentroleFactoryService);
	}

	@Override
	public ICFSecSecTentRoleMembFactory getFactorySecTentRoleMemb() {
		return(sectentrolemembFactoryService);
	}

	public CFSecBuffSecTentRoleMembFactoryService getSecTentRoleMembFactoryService() {
		return(sectentrolemembFactoryService);
	}

	@Override
	public ICFSecSecUserFactory getFactorySecUser() {
		return(secuserFactoryService);
	}

	public CFSecBuffSecUserFactoryService getSecUserFactoryService() {
		return(secuserFactoryService);
	}

	@Override
	public ICFSecSecUserEMConfFactory getFactorySecUserEMConf() {
		return(secuseremconfFactoryService);
	}

	public CFSecBuffSecUserEMConfFactoryService getSecUserEMConfFactoryService() {
		return(secuseremconfFactoryService);
	}

	@Override
	public ICFSecSecUserPWHistoryFactory getFactorySecUserPWHistory() {
		return(secuserpwhistoryFactoryService);
	}

	public CFSecBuffSecUserPWHistoryFactoryService getSecUserPWHistoryFactoryService() {
		return(secuserpwhistoryFactoryService);
	}

	@Override
	public ICFSecSecUserPWResetFactory getFactorySecUserPWReset() {
		return(secuserpwresetFactoryService);
	}

	public CFSecBuffSecUserPWResetFactoryService getSecUserPWResetFactoryService() {
		return(secuserpwresetFactoryService);
	}

	@Override
	public ICFSecSecUserPasswordFactory getFactorySecUserPassword() {
		return(secuserpasswordFactoryService);
	}

	public CFSecBuffSecUserPasswordFactoryService getSecUserPasswordFactoryService() {
		return(secuserpasswordFactoryService);
	}

	@Override
	public ICFSecSysClusterFactory getFactorySysCluster() {
		return(sysclusterFactoryService);
	}

	public CFSecBuffSysClusterFactoryService getSysClusterFactoryService() {
		return(sysclusterFactoryService);
	}

	@Override
	public ICFSecTableInfoFactory getFactoryTableInfo() {
		return(tableinfoFactoryService);
	}

	public CFSecBuffTableInfoFactoryService getTableInfoFactoryService() {
		return(tableinfoFactoryService);
	}

	@Override
	public ICFSecTenantFactory getFactoryTenant() {
		return(tenantFactoryService);
	}

	public CFSecBuffTenantFactoryService getTenantFactoryService() {
		return(tenantFactoryService);
	}

}
