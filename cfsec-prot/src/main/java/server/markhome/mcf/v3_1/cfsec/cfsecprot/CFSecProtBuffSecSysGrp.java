// Description: Java 17 implJustProtementation of a SecSysGrp buffer

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
import java.io.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

public class CFSecProtBuffSecSysGrp
	implements ICFSecProtSecSysGrp, Comparable<Object>, Serializable
{
	protected ICFLibKeyHash256 requiredSecSysGrpId;
	protected int requiredRevision;
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected CFLibDbKeyHash256 createdBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected CFLibDbKeyHash256 updatedBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();
	protected String requiredName;
	protected ICFSecPubSchema.SecLevelEnum requiredSecLevel;

	public CFSecProtBuffSecSysGrp() {
		requiredSecSysGrpId = CFLibDbKeyHash256.fromHex( ICFSecPubSecSysGrp.SECSYSGRPID_INIT_VALUE.toString() );
		requiredName = ICFSecPubSecSysGrp.NAME_INIT_VALUE;
		requiredSecLevel = ICFSecPubSecSysGrp.SECLEVEL_INIT_VALUE;
	}

	@Override
	public ICFLibKeyHash256 getPKey() {
		return (requiredSecSysGrpId);
	}

	@Override
	public void setPKey(ICFLibKeyHash256 requiredSecSysGrpId) {
		if(requiredSecSysGrpId != null) {
			this.requiredSecSysGrpId = requiredSecSysGrpId;
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecSysGrpId() {
		return(requiredSecSysGrpId);
	}

	public void setRequiredSecSysGrpId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecSysGrpId",
				1,
				"value" );
		}
		requiredSecSysGrpId = value;
	}

	@Override
	public List<ICFSecProtSecSysGrpInc> getOptionalComponentsIncByGrp(ICFLibKeyHash256 argSecSysGrpId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsIncByGrp", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecSysGrpIncTable targetTable = targetBackingCFSec.getTableSecSysGrpInc();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsIncByGrp", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecSysGrpInc()");
		}
		ICFSecProtSecSysGrpInc[] targetArr = targetTable.readDerivedBySysGrpIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecSysGrpId());
		if( targetArr != null ) {
			List<ICFSecProtSecSysGrpInc> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecSysGrpInc> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public List<ICFSecProtSecSysGrpMemb> getOptionalChildrenMembByGrp(ICFLibKeyHash256 argSecSysGrpId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenMembByGrp", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecSysGrpMembTable targetTable = targetBackingCFSec.getTableSecSysGrpMemb();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenMembByGrp", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecSysGrpMemb()");
		}
		ICFSecProtSecSysGrpMemb[] targetArr = targetTable.readDerivedBySysGrpIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecSysGrpId());
		if( targetArr != null ) {
			List<ICFSecProtSecSysGrpMemb> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecSysGrpMemb> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public ICFSecProtSecSysRole getOptionalComponentsImplSysRole(ICFLibKeyHash256 argSecSysGrpId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplSysRole", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecSysRoleTable targetTable = targetBackingCFSec.getTableSecSysRole();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplSysRole", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecSysRole()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecSysGrpId()));
	}

	@Override
	public CFLibDbKeyHash256 getCreatedByUserId() {
		return( createdByUserId );
	}

	@Override
	public void setJustProtCreatedByUserId( CFLibDbKeyHash256 value ) {
		createdByUserId = value;
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( createdAt );
	}

	@Override
	public void setJustProtCreatedAt( LocalDateTime value ) {
		createdAt = value;
	}

	@Override
	public CFLibDbKeyHash256 getUpdatedByUserId() {
		return( updatedByUserId );
	}

	@Override
	public void setJustProtUpdatedByUserId( CFLibDbKeyHash256 value ) {
		updatedByUserId = value;
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( updatedAt );
	}

	@Override
	public void setJustProtUpdatedAt( LocalDateTime value ) {
		updatedAt = value;
	}

	@Override
	public int getRequiredRevision() {
		return( requiredRevision );
	}

	@Override
	public void setRequiredRevision( int value ) {
		requiredRevision = value;
	}

	@Override
	public int getClassCode() {
		return( ICFSecProtSecSysGrp.CLASS_CODE );
	}

	@Override
	public List<ICFSecProtSecClusGrp> getOptionalComponentsImplClusGrp(String argName) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplClusGrp", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecClusGrpTable targetTable = targetBackingCFSec.getTableSecClusGrp();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplClusGrp", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecClusGrp()");
		}
		ICFSecProtSecClusGrp[] targetArr = targetTable.readDerivedByNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredName());
		if( targetArr != null ) {
			List<ICFSecProtSecClusGrp> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecClusGrp> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public List<ICFSecProtSecTentGrp> getOptionalComponentsImplTentGrp(String argName) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplTentGrp", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecTentGrpTable targetTable = targetBackingCFSec.getTableSecTentGrp();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplTentGrp", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecTentGrp()");
		}
		ICFSecProtSecTentGrp[] targetArr = targetTable.readDerivedByNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredName());
		if( targetArr != null ) {
			List<ICFSecProtSecTentGrp> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecTentGrp> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public List<ICFSecProtSecClusRole> getOptionalComponentsImplClusRole(String argName) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplClusRole", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecClusRoleTable targetTable = targetBackingCFSec.getTableSecClusRole();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplClusRole", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecClusRole()");
		}
		ICFSecProtSecClusRole[] targetArr = targetTable.readDerivedByNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredName());
		if( targetArr != null ) {
			List<ICFSecProtSecClusRole> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecClusRole> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public List<ICFSecProtSecTentRole> getOptionalComponentsImplTentRole(String argName) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplTentRole", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecTentRoleTable targetTable = targetBackingCFSec.getTableSecTentRole();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalComponentsImplTentRole", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecTentRole()");
		}
		ICFSecProtSecTentRole[] targetArr = targetTable.readDerivedByNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredName());
		if( targetArr != null ) {
			List<ICFSecProtSecTentRole> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecTentRole> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public List<ICFSecProtSecSysGrpInc> getOptionalChildrenSysGrpByName(String argName) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSysGrpByName", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecSysGrpIncTable targetTable = targetBackingCFSec.getTableSecSysGrpInc();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSysGrpByName", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecSysGrpInc()");
		}
		ICFSecProtSecSysGrpInc[] targetArr = targetTable.readDerivedByNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredName());
		if( targetArr != null ) {
			List<ICFSecProtSecSysGrpInc> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecSysGrpInc> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public List<ICFSecProtSecSysRoleEnables> getOptionalChildrenRoleByEnableName(String argName) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenRoleByEnableName", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecSysRoleEnablesTable targetTable = targetBackingCFSec.getTableSecSysRoleEnables();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenRoleByEnableName", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecSysRoleEnables()");
		}
		ICFSecProtSecSysRoleEnables[] targetArr = targetTable.readDerivedByNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredName());
		if( targetArr != null ) {
			List<ICFSecProtSecSysRoleEnables> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecSysRoleEnables> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public String getRequiredName() {
		return(requiredName);
	}

	public void setRequiredName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredName",
				1,
				"value" );
		}
		else if( value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredName",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		requiredName = value;
	}

	@Override
	public ICFSecPubSchema.SecLevelEnum getRequiredSecLevel() {
		return(requiredSecLevel);
	}

	public void setRequiredSecLevel( ICFSecPubSchema.SecLevelEnum value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecLevel",
				1,
				"value" );
		}
		requiredSecLevel = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecProtSecSysGrp rhs ) {
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredSecSysGrpId() != null ) {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					if( ! getRequiredSecSysGrpId().equals( rhs.getRequiredSecSysGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getRequiredSecLevel() != null ) {
				if( rhs.getRequiredSecLevel() != null ) {
					if( ! getRequiredSecLevel().equals( rhs.getRequiredSecLevel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecLevel() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecSysGrpH rhs ) {
			if( getRequiredSecSysGrpId() != null ) {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					if( ! getRequiredSecSysGrpId().equals( rhs.getRequiredSecSysGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getRequiredSecLevel() != null ) {
				if( rhs.getRequiredSecLevel() != null ) {
					if( ! getRequiredSecLevel().equals( rhs.getRequiredSecLevel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecLevel() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecSysGrpHPKey ) {
			ICFSecProtSecSysGrpHPKey rhs = (ICFSecProtSecSysGrpHPKey)obj;
			if( getRequiredSecSysGrpId() != null ) {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					if( ! getRequiredSecSysGrpId().equals( rhs.getRequiredSecSysGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecSysGrpByUNameIdxKey rhs ) {
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecSysGrpBySecLevelIdxKey rhs ) {
			if( getRequiredSecLevel() != null ) {
				if( rhs.getRequiredSecLevel() != null ) {
					if( ! getRequiredSecLevel().equals( rhs.getRequiredSecLevel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecLevel() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSysGrp rhs ) {
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredSecSysGrpId() != null ) {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					if( ! getRequiredSecSysGrpId().equals( rhs.getRequiredSecSysGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getRequiredSecLevel() != null ) {
				if( rhs.getRequiredSecLevel() != null ) {
					if( ! getRequiredSecLevel().equals( rhs.getRequiredSecLevel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecLevel() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSysGrpH rhs ) {
			if( getRequiredSecSysGrpId() != null ) {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					if( ! getRequiredSecSysGrpId().equals( rhs.getRequiredSecSysGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getRequiredSecLevel() != null ) {
				if( rhs.getRequiredSecLevel() != null ) {
					if( ! getRequiredSecLevel().equals( rhs.getRequiredSecLevel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecLevel() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSysGrpHPKey rhs ) {
			if( getRequiredSecSysGrpId() != null ) {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					if( ! getRequiredSecSysGrpId().equals( rhs.getRequiredSecSysGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysGrpId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecSysGrpByUNameIdxKey rhs ) {
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecSysGrpBySecLevelIdxKey rhs ) {
			if( getRequiredSecLevel() != null ) {
				if( rhs.getRequiredSecLevel() != null ) {
					if( ! getRequiredSecLevel().equals( rhs.getRequiredSecLevel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecLevel() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else {
			boolean retval = super.equals( obj );
			return( retval );
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		hashCode = hashCode + getCreatedByUserId().hashCode();
		hashCode = hashCode + getCreatedAt().hashCode();
		hashCode = hashCode + getUpdatedByUserId().hashCode();
		hashCode = hashCode + getUpdatedAt().hashCode();
		hashCode = hashCode + getRequiredSecSysGrpId().hashCode();
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		hashCode = ( hashCode * 0x10000 ) + getRequiredSecLevel().ordinal();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecProtSecSysGrp rhs ) {
			cmp = 0;
			{
				cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}
			}
			if (getRequiredSecSysGrpId() != null) {
				if (rhs.getRequiredSecSysGrpId() != null) {
					cmp = getRequiredSecSysGrpId().compareTo( rhs.getRequiredSecSysGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysGrpId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if (getRequiredSecLevel() != null) {
				if (rhs.getRequiredSecLevel() != null) {
					cmp = getRequiredSecLevel().compareTo( rhs.getRequiredSecLevel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecLevel() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecProtSecSysGrpHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecSysGrpId() != null) {
				if (rhs.getRequiredSecSysGrpId() != null) {
					cmp = getRequiredSecSysGrpId().compareTo( rhs.getRequiredSecSysGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysGrpId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecSysGrpH rhs ) {
			cmp = 0;
			if (getRequiredSecSysGrpId() != null) {
				if (rhs.getRequiredSecSysGrpId() != null) {
					cmp = getRequiredSecSysGrpId().compareTo( rhs.getRequiredSecSysGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysGrpId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if (getRequiredSecLevel() != null) {
				if (rhs.getRequiredSecLevel() != null) {
					cmp = getRequiredSecLevel().compareTo( rhs.getRequiredSecLevel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecLevel() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecSysGrpByUNameIdxKey rhs ) {
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecSysGrpBySecLevelIdxKey rhs ) {
			if (getRequiredSecLevel() != null) {
				if (rhs.getRequiredSecLevel() != null) {
					cmp = getRequiredSecLevel().compareTo( rhs.getRequiredSecLevel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecLevel() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSysGrp rhs ) {
			cmp = 0;
			{
				cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}
			}
			if (getRequiredSecSysGrpId() != null) {
				if (rhs.getRequiredSecSysGrpId() != null) {
					cmp = getRequiredSecSysGrpId().compareTo( rhs.getRequiredSecSysGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysGrpId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if (getRequiredSecLevel() != null) {
				if (rhs.getRequiredSecLevel() != null) {
					cmp = getRequiredSecLevel().compareTo( rhs.getRequiredSecLevel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecLevel() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSysGrpHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecSysGrpId() != null) {
				if (rhs.getRequiredSecSysGrpId() != null) {
					cmp = getRequiredSecSysGrpId().compareTo( rhs.getRequiredSecSysGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysGrpId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSysGrpH rhs ) {
			cmp = 0;
			if (getRequiredSecSysGrpId() != null) {
				if (rhs.getRequiredSecSysGrpId() != null) {
					cmp = getRequiredSecSysGrpId().compareTo( rhs.getRequiredSecSysGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysGrpId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if (getRequiredSecLevel() != null) {
				if (rhs.getRequiredSecLevel() != null) {
					cmp = getRequiredSecLevel().compareTo( rhs.getRequiredSecLevel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecLevel() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSysGrpByUNameIdxKey rhs ) {
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSysGrpBySecLevelIdxKey rhs ) {
			if (getRequiredSecLevel() != null) {
				if (rhs.getRequiredSecLevel() != null) {
					cmp = getRequiredSecLevel().compareTo( rhs.getRequiredSecLevel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecLevel() != null) {
				return( -1 );
			}			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"obj",
				obj,
				null );
		}
	}

	@Override
	public void set( ICFSecProtSecSysGrp src ) {
		setJustProtSecSysGrp( src );
	}

	@Override
	public void setSecSysGrp( ICFSecProtSecSysGrp src ) {
		setJustProtRequiredSecSysGrpId(src.getRequiredSecSysGrpId());
		setJustProtRequiredRevision( src.getRequiredRevision() );
		setJustProtCreatedByUserId( src.getCreatedByUserId() );
		setJustProtCreatedAt( src.getCreatedAt() );
		setJustProtUpdatedByUserId( src.getUpdatedByUserId() );
		setJustProtUpdatedAt( src.getUpdatedAt() );
		setJustProtRequiredName(src.getRequiredName());
		setJustProtRequiredSecLevel(src.getRequiredSecLevel());
	}

	@Override
	public void set( ICFSecProtSecSysGrpH src ) {
		setJustProtSecSysGrp( src );
	}

	@Override
	public void setSecSysGrp( ICFSecProtSecSysGrpH src ) {
		setJustProtRequiredSecSysGrpId(src.getRequiredSecSysGrpId());
		setJustProtRequiredName(src.getRequiredName());
		setJustProtRequiredSecLevel(src.getRequiredSecLevel());
	}

	@Override
	public void set( ICFSecPubSecSysGrp src ) {
		setJustProtSecSysGrp( src );
	}

	@Override
	public void setSecSysGrp( ICFSecPubSecSysGrp src ) {
		setJustProtRequiredSecSysGrpId(src.getRequiredSecSysGrpId());
		setJustProtRequiredRevision( src.getRequiredRevision() );
		setJustProtCreatedByUserId( src.getCreatedByUserId() );
		setJustProtCreatedAt( src.getCreatedAt() );
		setJustProtUpdatedByUserId( src.getUpdatedByUserId() );
		setJustProtUpdatedAt( src.getUpdatedAt() );
		setJustProtRequiredName(src.getRequiredName());
		setJustProtRequiredSecLevel(src.getRequiredSecLevel());
	}

	@Override
	public void set( ICFSecPubSecSysGrpH src ) {
		setJustProtSecSysGrp( src );
	}

	@Override
	public void setSecSysGrp( ICFSecPubSecSysGrpH src ) {
		setJustProtRequiredSecSysGrpId(src.getRequiredSecSysGrpId());
		setJustProtRequiredName(src.getRequiredName());
		setJustProtRequiredSecLevel(src.getRequiredSecLevel());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + getRequiredSecSysGrpId().toString() + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"" + "\"" + getRequiredSecLevel().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecProtBuffSecSysGrp" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
