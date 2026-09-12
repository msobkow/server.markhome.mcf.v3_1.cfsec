// Description: Java 25 interface for a CFSec schema.

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

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.math.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.*;
import java.util.*;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import java.util.concurrent.atomic.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.buff.CFSecBuffSchema;
import server.markhome.mcf.v3_1.cfsec.cfsec.buff.CFSecBuffFactoryService;

public interface ICFSecSchema
{
	public static final String SCHEMA_NAME = "CFSec";
	public static final String DBSCHEMA_NAME = "CFSec31";
	static final AtomicReference<ApplicationContext> arApplicationContext = new AtomicReference<>(null);
	public static final CFSecPubTableData TABLE_DATA[] = {new CFSecPubTableData("CFSec", "Cluster", null, true, false, "Global", "Public"),
		new CFSecPubTableData("CFSec", "ISOCcy", null, true, false, "Global", "Public"),
		new CFSecPubTableData("CFSec", "ISOCtry", null, true, false, "Global", "Public"),
		new CFSecPubTableData("CFSec", "ISOCtryCcy", null, true, false, "Global", "Public"),
		new CFSecPubTableData("CFSec", "ISOCtryLang", null, true, false, "Global", "Public"),
		new CFSecPubTableData("CFSec", "ISOLang", null, true, false, "Global", "Public"),
		new CFSecPubTableData("CFSec", "ISOTZone", null, true, false, "Global", "Public"),
		new CFSecPubTableData("CFSec", "SecClusGrp", null, true, false, "Cluster", "Protected"),
		new CFSecPubTableData("CFSec", "SecClusGrpMemb", null, true, false, "Cluster", "Protected"),
		new CFSecPubTableData("CFSec", "SecClusRole", null, true, false, "Cluster", "Protected"),
		new CFSecPubTableData("CFSec", "SecClusRoleMemb", null, true, false, "Cluster", "Protected"),
		new CFSecPubTableData("CFSec", "SecSession", null, false, false, "System", "Public"),
		new CFSecPubTableData("CFSec", "SecSysGrp", null, true, false, "System", "Public"),
		new CFSecPubTableData("CFSec", "SecSysGrpInc", null, true, false, "System", "Protected"),
		new CFSecPubTableData("CFSec", "SecSysGrpMemb", null, true, false, "System", "Protected"),
		new CFSecPubTableData("CFSec", "SecSysRole", null, true, false, "System", "Public"),
		new CFSecPubTableData("CFSec", "SecSysRoleEnables", null, true, false, "System", "Protected"),
		new CFSecPubTableData("CFSec", "SecSysRoleMemb", null, true, false, "System", "Protected"),
		new CFSecPubTableData("CFSec", "SecTentGrp", null, true, false, "Tenant", "Protected"),
		new CFSecPubTableData("CFSec", "SecTentGrpMemb", null, true, false, "Tenant", "Protected"),
		new CFSecPubTableData("CFSec", "SecTentRole", null, true, false, "Tenant", "Protected"),
		new CFSecPubTableData("CFSec", "SecTentRoleMemb", null, true, false, "Tenant", "Protected"),
		new CFSecPubTableData("CFSec", "SecUser", null, true, false, "System", "Public"),
		new CFSecPubTableData("CFSec", "SecUserEMConf", null, true, false, "System", "Protected"),
		new CFSecPubTableData("CFSec", "SecUserPWHistory", null, false, false, "System", "Private"),
		new CFSecPubTableData("CFSec", "SecUserPWReset", null, true, false, "System", "Protected"),
		new CFSecPubTableData("CFSec", "SecUserPassword", null, false, false, "System", "Private"),
		new CFSecPubTableData("CFSec", "SysCluster", null, false, false, "System", "Public"),
		new CFSecPubTableData("CFSec", "TableInfo", null, true, false, "Global", "Public"),
		new CFSecPubTableData("CFSec", "Tenant", null, true, false, "System", "Public")};
	public static final AtomicReference<CFSecPubTableData[]> consolidatedTableData = new AtomicReference<>(null);
	public static final CFSecPubRoleInfo ROLE_INFO[] = {new CFSecPubRoleInfo("secclusmanager", "ClusRole", "sectentmanager createsecclusmemb updatesecclusmemb deletesecclusmemb createsecclusrolememb updatesecclusrolememb deletesecclusrolememb", "systemadmin"),
		new CFSecPubRoleInfo("secclusadmin", "ClusRole", "secclusmanager createsecclusgrp updatesecclusgrp deletesecclusgrp readsecclusrole updatesecclusrole createsecclusrole deletesecclusrole", "systemadmin"),
		new CFSecPubRoleInfo("secsysmanager", "SysRole", "secuser secclusmanager createsecsysmemb updatesecsysmemb deletesecsysmemb create secsysrolememb updatesecsysrolememb deletesecsysrolememb", "systemadmin"),
		new CFSecPubRoleInfo("sectentmanager", "TentRole", "secuser createsectentmemb updatesectentmemb deletesectentmemb createsectentrolememb updatesectentrolememb deletesectentrolememb", "systemadmin"),
		new CFSecPubRoleInfo("sectentadmin", "TentRole", "sectentmanager createsectentgrp updatesectentgrp deletesectentgrp createsectentrole updatesectentrole deletesectentrole", "systemadmin"),
		new CFSecPubRoleInfo("secsysadmin", "SysRole", "sectentadmin secclusadmin secsysmanager updatecluster deletecluster updatetenant deletetenant createsecsysgrp updatesecsysgrp deletesecsysgrp createsecsysrole updatesecsysrole deletesecsysrole createsecsysinc updatesecsysinc deletesecsysinc ", "systemadmin"),
		new CFSecPubRoleInfo("secuser", "SysRole", "readcluster readtenant readsecsysgrp readsecsysinc readsecsysmemb readsecsysrole readsecsysinc readsecsysroleenable readsecsysrolememb readsecclusgrp readsecclusmemb readsecclusrole readsecclusrolememb readsectentgrp readsectentmemb readsectentrole readsectentrolememb", "systemadmin")};
	public static final AtomicReference<CFSecPubRoleInfo[]> consolidatedRoleInfo = new AtomicReference<>(null);

	public static CFSecPubTableData[] getTableData() {
		return TABLE_DATA;
	}

	public static CFSecPubTableData[] getConsolidatedTableData() {
		if (consolidatedTableData.get() == null) {
			ArrayList<CFSecPubTableData> lst = new ArrayList<>();
			for( CFSecPubTableData data: TABLE_DATA) {
				lst.add(data);
			}
			CFSecPubTableData arr[] = new CFSecPubTableData[lst.size()];
			int idx = 0;
			for(CFSecPubTableData data: lst) {
				arr[idx++] = data;
			}
			consolidatedTableData.compareAndSet(null, arr);
		}
		return(consolidatedTableData.get());
	}

	public static CFSecPubRoleInfo[] getRoleInfo() {
		return ROLE_INFO;
	}

	public static CFSecPubRoleInfo[] getConsolidatedRoleInfo() {
		if (consolidatedRoleInfo.get() == null) {
			ArrayList<CFSecPubRoleInfo> lst = new ArrayList<>();
			for( CFSecPubRoleInfo info: ROLE_INFO) {
				lst.add(info);
			}
			// Dependency order is the natural order of role info comparison
			lst.sort(new CFSecPubRoleInfoDependencyComparator());
			CFSecPubRoleInfo arr[] = new CFSecPubRoleInfo[lst.size()];
			int idx = 0;
			for(CFSecPubRoleInfo info: lst) {
				arr[idx++] = info;
			}
			consolidatedRoleInfo.compareAndSet(null, arr);
		}
		return(consolidatedRoleInfo.get());
	}

	public default void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		arApplicationContext.compareAndSet(arApplicationContext.get(), applicationContext);
	}

	public static ApplicationContext getApplicationContext() {
		return( arApplicationContext.get() );
	}

	public static interface BackingRecConstructor {
		public Object instantiate();
	}
	
	public static class ClassMapEntry {
		final String schemaName;
		final String tableName;
		final int backingClassCode;
		int runtimeClassCode;
		BackingRecConstructor cbRecConstructor;
		
		public ClassMapEntry(String schemaName, String tableName, int backingClassCode) {
			this.schemaName = schemaName;
			this.tableName = tableName;
			this.backingClassCode = backingClassCode;
			runtimeClassCode = 0;
			cbRecConstructor = null;
		}
		
		public String getSchemaName() { return schemaName; }
		public String getTableName() { return tableName; }
		public int getBackingClassCode() { return backingClassCode; }

		public int getRuntimeClassCode() { return runtimeClassCode; }
		public void setRuntimeClassCode(int runtimeClassCode) { this.runtimeClassCode = runtimeClassCode; }
		
		public BackingRecConstructor getBackingRecConstructor() { return cbRecConstructor; }
		public void setBackingRecConstructor(BackingRecConstructor backingRecConstructor) { this.cbRecConstructor = backingRecConstructor; }
	}

		final static ArrayList<ICFSecSchema.ClassMapEntry> entries = new ArrayList<>();
		final static HashMap<Integer,ICFSecSchema.ClassMapEntry> mapBackingClassCodeToEntry = new HashMap<>();
		final static HashMap<Integer,ICFSecSchema.ClassMapEntry> mapRuntimeClassCodeToEntry = new HashMap<>();
		final static AtomicReference<ICFSecSchema> backingCFSec = new AtomicReference<>();
	final static AtomicReference<ICFSecAuthorizationCallback> authorizationCallback = new AtomicReference<>();
	
	public static ICFSecAuthorizationCallback getAuthorizationCallback() {
		ICFSecAuthorizationCallback retval = authorizationCallback.get();
		if (retval == null) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "getAuthorizationCallback", 0, "authorizationCallback.get()");
		}
		return( retval );
	}
	
	public static void setAuthorizationCallback(ICFSecAuthorizationCallback callback) {
		if (callback == null) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "setAuthorizationCallback", 0, "callback");
		}
		authorizationCallback.set(callback);
	}

	final static AtomicReference<ICFSecSecurityControl> securityControl = new AtomicReference<>();
	
	public static ICFSecSecurityService getSecurityService() {
		ICFSecSecurityService retval = securityControl.get();
		if (retval == null) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "getSecurityService", 0, "securityControl.get()");
		}
		return( retval );
	}
	
	public static ICFSecSecurityControl getSecurityControl() {
		ICFSecSecurityControl retval = securityControl.get();
		if (retval == null) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "getSecurityService", 0, "securityControl.get()");
		}
		return( retval );
	}
	
	public static void setSecurityControl(ICFSecSecurityControl control) {
		if (control == null) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "setSecurityControl", 0, "control");
		}
		securityControl.set(control);
	}

	static final AtomicReference<CFLibDbKeyHash256> sysClusterId = new AtomicReference<>();
	static final AtomicReference<CFLibDbKeyHash256> sysTenantId = new AtomicReference<>();
	static final AtomicReference<CFLibDbKeyHash256> systemId = new AtomicReference<>();

	public static String getPasswordHash(String pw) {
		if (pw == null || pw.isEmpty()) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "getPasswordHash", 1, "pw");
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
	}

	public static CFLibDbKeyHash256 getSysClusterId() {
		return (sysClusterId.get());
	}

	public static void setSysClusterId(CFLibDbKeyHash256 argClusterId) {
		if (argClusterId == null || argClusterId.isNull()) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "setSysClusterId", 1, "argClusterId");
		}
		CFLibDbKeyHash256 oldid = sysClusterId.get();
		if (oldid == null) {
			sysClusterId.compareAndSet(null, argClusterId);
		}
		else if (oldid.isNull()) {
			sysClusterId.compareAndSet(oldid, argClusterId);
		}
		else {
			throw new CFLibInvalidArgumentException(ICFSecSchema.class, "setSysClusterId", "sysClusterId has already been set", "sysClusterId has already been set");
		}
	}

	public static CFLibDbKeyHash256 getSysTenantId() {
		return (sysTenantId.get());
	}

	public static void setSysTenantId(CFLibDbKeyHash256 argTenantId) {
		if (argTenantId == null || argTenantId.isNull()) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "setSysTenantId", 1, "argTenantId");
		}
		CFLibDbKeyHash256 oldid = sysTenantId.get();
		if (oldid == null) {
			sysTenantId.compareAndSet(null, argTenantId);
		}
		else if (oldid.isNull()) {
			sysTenantId.compareAndSet(oldid, argTenantId);
		}
		else {
			throw new CFLibInvalidArgumentException(ICFSecSchema.class, "setSysTenantId", "sysTenantId has already been set", "sysTenantId has already been set");
		}
	}

	public static CFLibDbKeyHash256 getSystemId() {
		return (systemId.get());
	}

	public static void setSystemId(CFLibDbKeyHash256 argSystemId) {
		if (argSystemId == null || argSystemId.isNull()) {
			throw new CFLibNullArgumentException(ICFSecSchema.class, "setSystemId", 1, "argSystemId");
		}
		CFLibDbKeyHash256 oldid = systemId.get();
		if (oldid == null) {
			systemId.compareAndSet(null, argSystemId);
		}
		else if (oldid.isNull()) {
			systemId.compareAndSet(oldid, argSystemId);
		}
		else {
			throw new CFLibInvalidArgumentException(ICFSecSchema.class, "setSystemId", "systemId has already been set", "systemId has already been set");
		}
	}

		public static ICFSecSchema getBackingCFSec() {
			return( ICFSecSchema.backingCFSec.get() );
		}
		
		public static void setBackingCFSec(ICFSecSchema backingSchema) {
			ICFSecSchema.backingCFSec.set(backingSchema);
		}
		
		public ICFSecSchema getCFSecSchema();
		public void setCFSecSchema(ICFSecSchema schema);

		public static int doInitClassMapEntries(int value) {
			if (ICFSecSchema.entries.isEmpty()) {
				ICFSecSchema.ClassMapEntry entry;
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "Cluster", ICFSecCluster.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "Tenant", ICFSecTenant.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "TableInfo", ICFSecTableInfo.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "ISOCcy", ICFSecISOCcy.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "ISOCtry", ICFSecISOCtry.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "ISOCtryCcy", ICFSecISOCtryCcy.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "ISOCtryLang", ICFSecISOCtryLang.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "ISOLang", ICFSecISOLang.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "ISOTZone", ICFSecISOTZone.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecUser", ICFSecSecUser.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecUserPassword", ICFSecSecUserPassword.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecUserEMConf", ICFSecSecUserEMConf.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecUserPWReset", ICFSecSecUserPWReset.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecUserPWHistory", ICFSecSecUserPWHistory.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecSysGrp", ICFSecSecSysGrp.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecSysGrpInc", ICFSecSecSysGrpInc.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecSysGrpMemb", ICFSecSecSysGrpMemb.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecClusGrp", ICFSecSecClusGrp.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecClusGrpMemb", ICFSecSecClusGrpMemb.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecTentGrp", ICFSecSecTentGrp.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecTentGrpMemb", ICFSecSecTentGrpMemb.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecSysRole", ICFSecSecSysRole.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecSysRoleEnables", ICFSecSecSysRoleEnables.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecSysRoleMemb", ICFSecSecSysRoleMemb.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecClusRole", ICFSecSecClusRole.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecClusRoleMemb", ICFSecSecClusRoleMemb.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecTentRole", ICFSecSecTentRole.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecTentRoleMemb", ICFSecSecTentRoleMemb.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SecSession", ICFSecSecSession.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFSecSchema.SCHEMA_NAME, "SysCluster", ICFSecSysCluster.CLASS_CODE);
				ICFSecSchema.entries.add(entry);
				for( ICFSecSchema.ClassMapEntry cur: ICFSecSchema.entries) {
					cur.setRuntimeClassCode(value++);
				}
				ICFSecSchema.mapBackingClassCodeToEntry.clear();
				ICFSecSchema.mapRuntimeClassCodeToEntry.clear();
				for( ICFSecSchema.ClassMapEntry cur: ICFSecSchema.entries) {
					ICFSecSchema.mapBackingClassCodeToEntry.put(cur.getBackingClassCode(), cur);
					ICFSecSchema.mapRuntimeClassCodeToEntry.put(cur.getRuntimeClassCode(), cur);
				}
			}
			return(value);
		}
		
		public static ICFSecSchema.ClassMapEntry getClassMapByBackingClassCode(int code) {
			ICFSecSchema.ClassMapEntry entry;
			entry = ICFSecSchema.mapBackingClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			return( null );
		}
		
		public static ICFSecSchema.ClassMapEntry getClassMapByRuntimeClassCode(int code) {
			ICFSecSchema.ClassMapEntry entry;
			entry = ICFSecSchema.mapRuntimeClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			return( null );
		}
		
		public int initClassMapEntries(int value);
		public void wireRecConstructors();
		public void wireTableTableInstances();

	/**
	 *	Allocate a new schema instance.
	 *
	 *	@return	A new ICFSecSchema instance.
	 */
	public ICFSecSchema newSchema();

	/**
	 *	Get the next ISOCcyIdGen identifier.
	 *
	 *	@return	The next ISOCcyIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOCcyIdGen();

	/**
	 *	Get the next ISOCtryIdGen identifier.
	 *
	 *	@return	The next ISOCtryIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOCtryIdGen();

	/**
	 *	Get the next ISOLangIdGen identifier.
	 *
	 *	@return	The next ISOLangIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOLangIdGen();

	/**
	 *	Get the next ISOTZoneIdGen identifier.
	 *
	 *	@return	The next ISOTZoneIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOTZoneIdGen();

	/**
	 *	Get the next TableInfoIdGen identifier.
	 *
	 *	@return	The next TableInfoIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public int nextTableInfoIdGen();

	/**
	 *	Get the next ClusterIdGen identifier.
	 *
	 *	@return	The next ClusterIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextClusterIdGen();

	/**
	 *	Get the next SecSessionIdGen identifier.
	 *
	 *	@return	The next SecSessionIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecSessionIdGen();

	/**
	 *	Get the next SecUserIdGen identifier.
	 *
	 *	@return	The next SecUserIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecUserIdGen();

	/**
	 *	Get the next TenantIdGen identifier.
	 *
	 *	@return	The next TenantIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTenantIdGen();

	/**
	 *	Get the next SecSysGrpIdGen identifier.
	 *
	 *	@return	The next SecSysGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecSysGrpIdGen();

	/**
	 *	Get the next SecClusGrpIdGen identifier.
	 *
	 *	@return	The next SecClusGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecClusGrpIdGen();

	/**
	 *	Get the next SecClusRoleIdGen identifier.
	 *
	 *	@return	The next SecClusRoleIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecClusRoleIdGen();

	/**
	 *	Get the next SecTentGrpIdGen identifier.
	 *
	 *	@return	The next SecTentGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecTentGrpIdGen();

	/**
	 *	Get the next SecTentRoleIdGen identifier.
	 *
	 *	@return	The next SecTentRoleIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecTentRoleIdGen();

	/**
	 *	Get the factory for CFSec data objects.
	 */
	public ICFSecFactory getCFSecFactory();

	/**
	 *	Get the buffer factory for CFSec data buffers.
	 */
	public CFSecBuffFactoryService getCFSecBuffFactory();

	/**
	 *	Get the Cluster Table interface for the schema.
	 *
	 *	@return	The Cluster Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecClusterTable getTableCluster();

	/**
	 *	Get the ISOCcy Table interface for the schema.
	 *
	 *	@return	The ISOCcy Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCcyTable getTableISOCcy();

	/**
	 *	Get the ISOCtry Table interface for the schema.
	 *
	 *	@return	The ISOCtry Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryTable getTableISOCtry();

	/**
	 *	Get the ISOCtryCcy Table interface for the schema.
	 *
	 *	@return	The ISOCtryCcy Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryCcyTable getTableISOCtryCcy();

	/**
	 *	Get the ISOCtryLang Table interface for the schema.
	 *
	 *	@return	The ISOCtryLang Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryLangTable getTableISOCtryLang();

	/**
	 *	Get the ISOLang Table interface for the schema.
	 *
	 *	@return	The ISOLang Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOLangTable getTableISOLang();

	/**
	 *	Get the ISOTZone Table interface for the schema.
	 *
	 *	@return	The ISOTZone Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOTZoneTable getTableISOTZone();

	/**
	 *	Get the SecClusGrp Table interface for the schema.
	 *
	 *	@return	The SecClusGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpTable getTableSecClusGrp();

	/**
	 *	Get the SecClusGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecClusGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpMembTable getTableSecClusGrpMemb();

	/**
	 *	Get the SecClusRole Table interface for the schema.
	 *
	 *	@return	The SecClusRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusRoleTable getTableSecClusRole();

	/**
	 *	Get the SecClusRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecClusRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusRoleMembTable getTableSecClusRoleMemb();

	/**
	 *	Get the SecSession Table interface for the schema.
	 *
	 *	@return	The SecSession Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSessionTable getTableSecSession();

	/**
	 *	Get the SecSysGrp Table interface for the schema.
	 *
	 *	@return	The SecSysGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpTable getTableSecSysGrp();

	/**
	 *	Get the SecSysGrpInc Table interface for the schema.
	 *
	 *	@return	The SecSysGrpInc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpIncTable getTableSecSysGrpInc();

	/**
	 *	Get the SecSysGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecSysGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpMembTable getTableSecSysGrpMemb();

	/**
	 *	Get the SecSysRole Table interface for the schema.
	 *
	 *	@return	The SecSysRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysRoleTable getTableSecSysRole();

	/**
	 *	Get the SecSysRoleEnables Table interface for the schema.
	 *
	 *	@return	The SecSysRoleEnables Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysRoleEnablesTable getTableSecSysRoleEnables();

	/**
	 *	Get the SecSysRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecSysRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysRoleMembTable getTableSecSysRoleMemb();

	/**
	 *	Get the SecTentGrp Table interface for the schema.
	 *
	 *	@return	The SecTentGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpTable getTableSecTentGrp();

	/**
	 *	Get the SecTentGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecTentGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpMembTable getTableSecTentGrpMemb();

	/**
	 *	Get the SecTentRole Table interface for the schema.
	 *
	 *	@return	The SecTentRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentRoleTable getTableSecTentRole();

	/**
	 *	Get the SecTentRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecTentRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentRoleMembTable getTableSecTentRoleMemb();

	/**
	 *	Get the SecUser Table interface for the schema.
	 *
	 *	@return	The SecUser Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserTable getTableSecUser();

	/**
	 *	Get the SecUserEMConf Table interface for the schema.
	 *
	 *	@return	The SecUserEMConf Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserEMConfTable getTableSecUserEMConf();

	/**
	 *	Get the SecUserPWHistory Table interface for the schema.
	 *
	 *	@return	The SecUserPWHistory Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPWHistoryTable getTableSecUserPWHistory();

	/**
	 *	Get the SecUserPWReset Table interface for the schema.
	 *
	 *	@return	The SecUserPWReset Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPWResetTable getTableSecUserPWReset();

	/**
	 *	Get the SecUserPassword Table interface for the schema.
	 *
	 *	@return	The SecUserPassword Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPasswordTable getTableSecUserPassword();

	/**
	 *	Get the SysCluster Table interface for the schema.
	 *
	 *	@return	The SysCluster Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSysClusterTable getTableSysCluster();

	/**
	 *	Get the TableInfo Table interface for the schema.
	 *
	 *	@return	The TableInfo Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecTableInfoTable getTableTableInfo();

	/**
	 *	Get the Tenant Table interface for the schema.
	 *
	 *	@return	The Tenant Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecTenantTable getTableTenant();

	/**
	 *	Get the Table Permissions interface for the schema.
	 *
	 *	@return	The Table Permissions interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	//public static ICFSecTablePerms getTablePerms();

	/**
	 *	Get the Table Permissions interface cast to this schema's implementation.
	 *
	 *	@return The Table Permissions interface for this schema.
	 */
	//public static ICFSecTablePerms getCFSecTablePerms();

	/**
	 *	Set the Table Permissions interface for the schema.  All fractal subclasses of
	 *	the ICFSecTablePerms implement at least that interface plus their own
	 *	accessors.
	 *
	 *	@param	value	The Table Permissions interface to be used by the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	//public static void setTablePerms( ICFSecTablePerms value );

	public void bootstrapSchema(CFSecPubTableData tableData[]);
	public void bootstrapAllTablesSecurity(CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, CFSecPubTableData tableData[]);
}
