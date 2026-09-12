// Description: Java 25 public interface for a CFSec schema.

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

package server.markhome.mcf.v3_1.cfsec.cfsecpub;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.math.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.*;
import java.util.*;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import java.util.concurrent.atomic.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//import server.markhome.mcf.v3_1.cfsec.cfsecpub.buff.CFSecPubBuffFactoryService;

public interface ICFSecPubSchema
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
		new CFSecPubTableData("CFSec", "SecSession", null, false, false, "System", "Public"),
		new CFSecPubTableData("CFSec", "SecSysGrp", null, true, false, "System", "Public"),
		new CFSecPubTableData("CFSec", "SecSysRole", null, true, false, "System", "Public"),
		new CFSecPubTableData("CFSec", "SecUser", null, true, false, "System", "Public"),
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

		final static ArrayList<ICFSecPubSchema.ClassMapEntry> entries = new ArrayList<>();
		final static HashMap<Integer,ICFSecPubSchema.ClassMapEntry> mapBackingClassCodeToEntry = new HashMap<>();
		final static HashMap<Integer,ICFSecPubSchema.ClassMapEntry> mapRuntimeClassCodeToEntry = new HashMap<>();
		final static AtomicReference<ICFSecPubSchema> backingCFSec = new AtomicReference<>();
	final static AtomicReference<ICFSecPubAuthorizationCallback> authorizationCallback = new AtomicReference<>();
	
	public static ICFSecPubAuthorizationCallback getAuthorizationCallback() {
		ICFSecPubAuthorizationCallback retval = authorizationCallback.get();
		if (retval == null) {
			throw new CFLibNullArgumentException(ICFSecPubSchema.class, "getAuthorizationCallback", 0, "authorizationCallback.get()");
		}
		return( retval );
	}
	
	public static void setAuthorizationCallback(ICFSecPubAuthorizationCallback callback) {
		if (callback == null) {
			throw new CFLibNullArgumentException(ICFSecPubSchema.class, "setAuthorizationCallback", 0, "callback");
		}
		authorizationCallback.set(callback);
	}

	final static AtomicReference<ICFSecPubSecurityService> securityService = new AtomicReference<>();
	
	public static ICFSecPubSecurityService getSecurityService() {
		ICFSecPubSecurityService retval = securityService.get();
		if (retval == null) {
			throw new CFLibNullArgumentException(ICFSecPubSchema.class, "getSecurityService", 0, "securityService.get()");
		}
		return( retval );
	}
	
	public static void setSecurityService(ICFSecPubSecurityService service) {
		if (service == null) {
			throw new CFLibNullArgumentException(ICFSecPubSchema.class, "setSecurityService", 0, "service");
		}
		securityService.compareAndSet(null, service);
	}

	public enum AuditActionEnum {
		Created,
		Updated,
		Deleted,
		Mutated
	};

	static HashMap<String,AuditActionEnum> lookupAuditActionEnum = new HashMap<String,AuditActionEnum>();

	public static AuditActionEnum parseAuditActionEnum( String value ) {
		AuditActionEnum retval = parseAuditActionEnum( ICFSecPubSchema.class.getName(), value );
		return( retval );
	}

	public static AuditActionEnum parseAuditActionEnum( String fieldOrClassName, String value ) {
		final String S_ProcName = "parseAuditActionEnum";
		if( lookupAuditActionEnum.isEmpty() ) {
			lookupAuditActionEnum.put( "Created", AuditActionEnum.Created );
			lookupAuditActionEnum.put( "Updated", AuditActionEnum.Updated );
			lookupAuditActionEnum.put( "Deleted", AuditActionEnum.Deleted );
			lookupAuditActionEnum.put( "Mutated", AuditActionEnum.Mutated );
		}
		AuditActionEnum retval;
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			retval = null;
		}
		else {
			retval = lookupAuditActionEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static HashMap<Integer,AuditActionEnum> lookupOrdinalAuditActionEnum = new HashMap<Integer,AuditActionEnum>();

	public static AuditActionEnum ordinalToAuditActionEnum( String fieldOrClassName, Short value ) {
		AuditActionEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToAuditActionEnum( fieldOrClassName, Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static AuditActionEnum ordinalToAuditActionEnum( Short value ) {
		AuditActionEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToAuditActionEnum( Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static AuditActionEnum ordinalToAuditActionEnum( Integer value ) {
		AuditActionEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToAuditActionEnum( ICFSecPubSchema.class.getName(), Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static AuditActionEnum ordinalToAuditActionEnum( String fieldOrClassName, Integer value ) {
		final String S_ProcName = "ordinalToAuditActionEnum";
		if( lookupOrdinalAuditActionEnum.isEmpty() ) {
			lookupOrdinalAuditActionEnum.put( Integer.valueOf( AuditActionEnum.Created.ordinal() ), AuditActionEnum.Created );
			lookupOrdinalAuditActionEnum.put( Integer.valueOf( AuditActionEnum.Updated.ordinal() ), AuditActionEnum.Updated );
			lookupOrdinalAuditActionEnum.put( Integer.valueOf( AuditActionEnum.Deleted.ordinal() ), AuditActionEnum.Deleted );
			lookupOrdinalAuditActionEnum.put( Integer.valueOf( AuditActionEnum.Mutated.ordinal() ), AuditActionEnum.Mutated );
		}
		AuditActionEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = lookupOrdinalAuditActionEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	public enum SecLevelEnum {
		System,
		Global,
		Cluster,
		Tenant,
		SysRole,
		ClusRole,
		TentRole
	};

	static HashMap<String,SecLevelEnum> lookupSecLevelEnum = new HashMap<String,SecLevelEnum>();

	public static SecLevelEnum parseSecLevelEnum( String value ) {
		SecLevelEnum retval = parseSecLevelEnum( ICFSecPubSchema.class.getName(), value );
		return( retval );
	}

	public static SecLevelEnum parseSecLevelEnum( String fieldOrClassName, String value ) {
		final String S_ProcName = "parseSecLevelEnum";
		if( lookupSecLevelEnum.isEmpty() ) {
			lookupSecLevelEnum.put( "System", SecLevelEnum.System );
			lookupSecLevelEnum.put( "Global", SecLevelEnum.Global );
			lookupSecLevelEnum.put( "Cluster", SecLevelEnum.Cluster );
			lookupSecLevelEnum.put( "Tenant", SecLevelEnum.Tenant );
			lookupSecLevelEnum.put( "SysRole", SecLevelEnum.SysRole );
			lookupSecLevelEnum.put( "ClusRole", SecLevelEnum.ClusRole );
			lookupSecLevelEnum.put( "TentRole", SecLevelEnum.TentRole );
		}
		SecLevelEnum retval;
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			retval = null;
		}
		else {
			retval = lookupSecLevelEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static HashMap<Integer,SecLevelEnum> lookupOrdinalSecLevelEnum = new HashMap<Integer,SecLevelEnum>();

	public static SecLevelEnum ordinalToSecLevelEnum( String fieldOrClassName, Short value ) {
		SecLevelEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecLevelEnum( fieldOrClassName, Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecLevelEnum ordinalToSecLevelEnum( Short value ) {
		SecLevelEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecLevelEnum( Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecLevelEnum ordinalToSecLevelEnum( Integer value ) {
		SecLevelEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecLevelEnum( ICFSecPubSchema.class.getName(), Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecLevelEnum ordinalToSecLevelEnum( String fieldOrClassName, Integer value ) {
		final String S_ProcName = "ordinalToSecLevelEnum";
		if( lookupOrdinalSecLevelEnum.isEmpty() ) {
			lookupOrdinalSecLevelEnum.put( Integer.valueOf( SecLevelEnum.System.ordinal() ), SecLevelEnum.System );
			lookupOrdinalSecLevelEnum.put( Integer.valueOf( SecLevelEnum.Global.ordinal() ), SecLevelEnum.Global );
			lookupOrdinalSecLevelEnum.put( Integer.valueOf( SecLevelEnum.Cluster.ordinal() ), SecLevelEnum.Cluster );
			lookupOrdinalSecLevelEnum.put( Integer.valueOf( SecLevelEnum.Tenant.ordinal() ), SecLevelEnum.Tenant );
			lookupOrdinalSecLevelEnum.put( Integer.valueOf( SecLevelEnum.SysRole.ordinal() ), SecLevelEnum.SysRole );
			lookupOrdinalSecLevelEnum.put( Integer.valueOf( SecLevelEnum.ClusRole.ordinal() ), SecLevelEnum.ClusRole );
			lookupOrdinalSecLevelEnum.put( Integer.valueOf( SecLevelEnum.TentRole.ordinal() ), SecLevelEnum.TentRole );
		}
		SecLevelEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = lookupOrdinalSecLevelEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	public enum SecAccountStatusEnum {
		System,
		VerifyingEmail,
		CreatingDevKey,
		NormalUser,
		ResettingPassword,
		Locked
	};

	static HashMap<String,SecAccountStatusEnum> lookupSecAccountStatusEnum = new HashMap<String,SecAccountStatusEnum>();

	public static SecAccountStatusEnum parseSecAccountStatusEnum( String value ) {
		SecAccountStatusEnum retval = parseSecAccountStatusEnum( ICFSecPubSchema.class.getName(), value );
		return( retval );
	}

	public static SecAccountStatusEnum parseSecAccountStatusEnum( String fieldOrClassName, String value ) {
		final String S_ProcName = "parseSecAccountStatusEnum";
		if( lookupSecAccountStatusEnum.isEmpty() ) {
			lookupSecAccountStatusEnum.put( "System", SecAccountStatusEnum.System );
			lookupSecAccountStatusEnum.put( "VerifyingEmail", SecAccountStatusEnum.VerifyingEmail );
			lookupSecAccountStatusEnum.put( "CreatingDevKey", SecAccountStatusEnum.CreatingDevKey );
			lookupSecAccountStatusEnum.put( "NormalUser", SecAccountStatusEnum.NormalUser );
			lookupSecAccountStatusEnum.put( "ResettingPassword", SecAccountStatusEnum.ResettingPassword );
			lookupSecAccountStatusEnum.put( "Locked", SecAccountStatusEnum.Locked );
		}
		SecAccountStatusEnum retval;
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			retval = null;
		}
		else {
			retval = lookupSecAccountStatusEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static HashMap<Integer,SecAccountStatusEnum> lookupOrdinalSecAccountStatusEnum = new HashMap<Integer,SecAccountStatusEnum>();

	public static SecAccountStatusEnum ordinalToSecAccountStatusEnum( String fieldOrClassName, Short value ) {
		SecAccountStatusEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecAccountStatusEnum( fieldOrClassName, Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecAccountStatusEnum ordinalToSecAccountStatusEnum( Short value ) {
		SecAccountStatusEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecAccountStatusEnum( Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecAccountStatusEnum ordinalToSecAccountStatusEnum( Integer value ) {
		SecAccountStatusEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecAccountStatusEnum( ICFSecPubSchema.class.getName(), Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecAccountStatusEnum ordinalToSecAccountStatusEnum( String fieldOrClassName, Integer value ) {
		final String S_ProcName = "ordinalToSecAccountStatusEnum";
		if( lookupOrdinalSecAccountStatusEnum.isEmpty() ) {
			lookupOrdinalSecAccountStatusEnum.put( Integer.valueOf( SecAccountStatusEnum.System.ordinal() ), SecAccountStatusEnum.System );
			lookupOrdinalSecAccountStatusEnum.put( Integer.valueOf( SecAccountStatusEnum.VerifyingEmail.ordinal() ), SecAccountStatusEnum.VerifyingEmail );
			lookupOrdinalSecAccountStatusEnum.put( Integer.valueOf( SecAccountStatusEnum.CreatingDevKey.ordinal() ), SecAccountStatusEnum.CreatingDevKey );
			lookupOrdinalSecAccountStatusEnum.put( Integer.valueOf( SecAccountStatusEnum.NormalUser.ordinal() ), SecAccountStatusEnum.NormalUser );
			lookupOrdinalSecAccountStatusEnum.put( Integer.valueOf( SecAccountStatusEnum.ResettingPassword.ordinal() ), SecAccountStatusEnum.ResettingPassword );
			lookupOrdinalSecAccountStatusEnum.put( Integer.valueOf( SecAccountStatusEnum.Locked.ordinal() ), SecAccountStatusEnum.Locked );
		}
		SecAccountStatusEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = lookupOrdinalSecAccountStatusEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static final AtomicReference<CFLibDbKeyHash256> sysClusterId = new AtomicReference<>();
	static final AtomicReference<CFLibDbKeyHash256> sysTenantId = new AtomicReference<>();
	static final AtomicReference<CFLibDbKeyHash256> systemId = new AtomicReference<>();

	public static String getPasswordHash(String pw) {
		if (pw == null || pw.isEmpty()) {
			throw new CFLibNullArgumentException(ICFSecPubSchema.class, "getPasswordHash", 1, "pw");
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
			throw new CFLibNullArgumentException(ICFSecPubSchema.class, "setSysClusterId", 1, "argClusterId");
		}
		CFLibDbKeyHash256 oldid = sysClusterId.get();
		if (oldid == null) {
			sysClusterId.compareAndSet(null, argClusterId);
		}
		else if (oldid.isNull()) {
			sysClusterId.compareAndSet(oldid, argClusterId);
		}
		else {
			throw new CFLibInvalidArgumentException(ICFSecPubSchema.class, "setSysClusterId", "sysClusterId has already been set", "sysClusterId has already been set");
		}
	}

	public static CFLibDbKeyHash256 getSysTenantId() {
		return (sysTenantId.get());
	}

	public static void setSysTenantId(CFLibDbKeyHash256 argTenantId) {
		if (argTenantId == null || argTenantId.isNull()) {
			throw new CFLibNullArgumentException(ICFSecPubSchema.class, "setSysTenantId", 1, "argTenantId");
		}
		CFLibDbKeyHash256 oldid = sysTenantId.get();
		if (oldid == null) {
			sysTenantId.compareAndSet(null, argTenantId);
		}
		else if (oldid.isNull()) {
			sysTenantId.compareAndSet(oldid, argTenantId);
		}
		else {
			throw new CFLibInvalidArgumentException(ICFSecPubSchema.class, "setSysTenantId", "sysTenantId has already been set", "sysTenantId has already been set");
		}
	}

	public static CFLibDbKeyHash256 getSystemId() {
		return (systemId.get());
	}

	public static void setSystemId(CFLibDbKeyHash256 argSystemId) {
		if (argSystemId == null || argSystemId.isNull()) {
			throw new CFLibNullArgumentException(ICFSecPubSchema.class, "setSystemId", 1, "argSystemId");
		}
		CFLibDbKeyHash256 oldid = systemId.get();
		if (oldid == null) {
			systemId.compareAndSet(null, argSystemId);
		}
		else if (oldid.isNull()) {
			systemId.compareAndSet(oldid, argSystemId);
		}
		else {
			throw new CFLibInvalidArgumentException(ICFSecPubSchema.class, "setSystemId", "systemId has already been set", "systemId has already been set");
		}
	}

		public static ICFSecPubSchema getBackingCFSec() {
			return( ICFSecPubSchema.backingCFSec.get() );
		}
		
		public static void setBackingPubSchema(ICFSecPubSchema backingSchema) {
			ICFSecPubSchema.backingCFSec.set(backingSchema);
		}
		
		public ICFSecPubSchema getCFSecSchema();
		public void setCFSecSchema(ICFSecPubSchema schema);

		public static int doInitClassMapEntries(int value) {
			if (ICFSecPubSchema.entries.isEmpty()) {
				ICFSecPubSchema.ClassMapEntry entry;
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "Cluster", ICFSecPubCluster.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "ISOCcy", ICFSecPubISOCcy.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "ISOCtry", ICFSecPubISOCtry.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "ISOCtryCcy", ICFSecPubISOCtryCcy.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "ISOCtryLang", ICFSecPubISOCtryLang.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "ISOLang", ICFSecPubISOLang.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "ISOTZone", ICFSecPubISOTZone.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "SecSession", ICFSecPubSecSession.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "SecSysGrp", ICFSecPubSecSysGrp.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "SecSysRole", ICFSecPubSecSysRole.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "SecUser", ICFSecPubSecUser.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "SysCluster", ICFSecPubSysCluster.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "TableInfo", ICFSecPubTableInfo.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				entry = new ICFSecPubSchema.ClassMapEntry(ICFSecPubSchema.SCHEMA_NAME, "Tenant", ICFSecPubTenant.CLASS_CODE);
				ICFSecPubSchema.entries.add(entry);
				for( ICFSecPubSchema.ClassMapEntry cur: ICFSecPubSchema.entries) {
					cur.setRuntimeClassCode(value++);
				}
				ICFSecPubSchema.mapBackingClassCodeToEntry.clear();
				ICFSecPubSchema.mapRuntimeClassCodeToEntry.clear();
				for( ICFSecPubSchema.ClassMapEntry cur: ICFSecPubSchema.entries) {
					ICFSecPubSchema.mapBackingClassCodeToEntry.put(cur.getBackingClassCode(), cur);
					ICFSecPubSchema.mapRuntimeClassCodeToEntry.put(cur.getRuntimeClassCode(), cur);
				}
			}
			return(value);
		}
		
		public static ICFSecPubSchema.ClassMapEntry getClassMapByBackingClassCode(int code) {
			ICFSecPubSchema.ClassMapEntry entry;
			entry = ICFSecPubSchema.mapBackingClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			return( null );
		}
		
		public static ICFSecPubSchema.ClassMapEntry getClassMapByRuntimeClassCode(int code) {
			ICFSecPubSchema.ClassMapEntry entry;
			entry = ICFSecPubSchema.mapRuntimeClassCodeToEntry.get(code);
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
	 *	@return	A new ICFSecPubSchema instance.
	 */
	public ICFSecPubSchema newSchema();

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
	 *	Get the factory for CFSecPub data objects.
	 */
	// public ICFSecPubFactory getCFSecFactory();

	/**
	 *	Get the buffer factory for CFSecPub data buffers.
	 */
	// public CFSecPubBuffFactoryService getCFSecBuffFactory();

	/**
	 *	Get the Cluster Table interface for the schema.
	 *
	 *	@return	The Cluster Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubClusterTable getTableCluster();

	/**
	 *	Get the ISOCcy Table interface for the schema.
	 *
	 *	@return	The ISOCcy Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOCcyTable getTableISOCcy();

	/**
	 *	Get the ISOCtry Table interface for the schema.
	 *
	 *	@return	The ISOCtry Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOCtryTable getTableISOCtry();

	/**
	 *	Get the ISOCtryCcy Table interface for the schema.
	 *
	 *	@return	The ISOCtryCcy Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOCtryCcyTable getTableISOCtryCcy();

	/**
	 *	Get the ISOCtryLang Table interface for the schema.
	 *
	 *	@return	The ISOCtryLang Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOCtryLangTable getTableISOCtryLang();

	/**
	 *	Get the ISOLang Table interface for the schema.
	 *
	 *	@return	The ISOLang Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOLangTable getTableISOLang();

	/**
	 *	Get the ISOTZone Table interface for the schema.
	 *
	 *	@return	The ISOTZone Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubISOTZoneTable getTableISOTZone();

	/**
	 *	Get the SecSession Table interface for the schema.
	 *
	 *	@return	The SecSession Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSecSessionTable getTableSecSession();

	/**
	 *	Get the SecSysGrp Table interface for the schema.
	 *
	 *	@return	The SecSysGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSecSysGrpTable getTableSecSysGrp();

	/**
	 *	Get the SecSysRole Table interface for the schema.
	 *
	 *	@return	The SecSysRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSecSysRoleTable getTableSecSysRole();

	/**
	 *	Get the SecUser Table interface for the schema.
	 *
	 *	@return	The SecUser Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSecUserTable getTableSecUser();

	/**
	 *	Get the SysCluster Table interface for the schema.
	 *
	 *	@return	The SysCluster Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubSysClusterTable getTableSysCluster();

	/**
	 *	Get the TableInfo Table interface for the schema.
	 *
	 *	@return	The TableInfo Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubTableInfoTable getTableTableInfo();

	/**
	 *	Get the Tenant Table interface for the schema.
	 *
	 *	@return	The Tenant Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecPubTenantTable getTableTenant();

	/**
	 *	Get the Table Permissions interface for the schema.
	 *
	 *	@return	The Table Permissions interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	//public static ICFSecPubTablePerms getTablePerms();

	/**
	 *	Get the Table Permissions interface cast to this schema's implementation.
	 *
	 *	@return The Table Permissions interface for this schema.
	 */
	//public static ICFSecPubTablePerms getCFSecPubTablePerms();

	/**
	 *	Set the Table Permissions interface for the schema.  All fractal subclasses of
	 *	the ICFSecPubTablePerms implement at least that interface plus their own
	 *	accessors.
	 *
	 *	@param	value	The Table Permissions interface to be used by the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	//public static void setTablePerms( ICFSecPubTablePerms value );

	public void bootstrapSchema(CFSecPubTableData tableData[]);
	public void bootstrapAllTablesSecurity(CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, CFSecPubTableData tableData[]);
}
