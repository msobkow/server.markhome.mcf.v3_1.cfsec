// Description: Java 17 implJustProtementation of a SecClusGrp buffer

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

public class CFSecProtBuffSecClusGrp
	implements ICFSecProtSecClusGrp, Comparable<Object>, Serializable
{
	protected ICFLibKeyHash256 requiredSecClusGrpId;
	protected int requiredRevision;
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected CFLibDbKeyHash256 createdBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected CFLibDbKeyHash256 updatedBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();
	protected ICFLibKeyHash256 requiredClusterId;
	protected String requiredName;

	public CFSecProtBuffSecClusGrp() {
		requiredSecClusGrpId = CFLibDbKeyHash256.fromHex( ICFSecProtSecClusGrp.SECCLUSGRPID_INIT_VALUE.toString() );
		requiredClusterId = CFLibDbKeyHash256.fromHex( ICFSecProtSecClusGrp.CLUSTERID_INIT_VALUE.toString() );
		requiredName = ICFSecProtSecClusGrp.NAME_INIT_VALUE;
	}

	@Override
	public ICFLibKeyHash256 getPKey() {
		return (requiredSecClusGrpId);
	}

	@Override
	public void setPKey(ICFLibKeyHash256 requiredSecClusGrpId) {
		if(requiredSecClusGrpId != null) {
			this.requiredSecClusGrpId = requiredSecClusGrpId;
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecClusGrpId() {
		return(requiredSecClusGrpId);
	}

	public void setRequiredSecClusGrpId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecClusGrpId",
				1,
				"value" );
		}
		requiredSecClusGrpId = value;
	}

	@Override
	public List<ICFSecProtSecClusGrpMemb> getOptionalChildrenMembByGrp(ICFLibKeyHash256 argSecClusGrpId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenMembByGrp", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecClusGrpMembTable targetTable = targetBackingCFSec.getTableSecClusGrpMemb();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenMembByGrp", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecClusGrpMemb()");
		}
		ICFSecProtSecClusGrpMemb[] targetArr = targetTable.readDerivedByClusGrpIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecClusGrpId());
		if( targetArr != null ) {
			List<ICFSecProtSecClusGrpMemb> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecProtSecClusGrpMemb> results = new ArrayList<>();
			return(results);
		}
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
		return( ICFSecProtSecClusGrp.CLASS_CODE );
	}

	@Override
	public ICFSecProtCluster getRequiredOwnerCluster(ICFLibKeyHash256 argClusterId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredOwnerCluster", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtClusterTable targetTable = targetBackingCFSec.getTableCluster();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredOwnerCluster", 0, "ICFSecProtSchema.getBackingCFSec().getTableCluster()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredClusterId()));
	}

	@Override
	public void setRequiredOwnerCluster(ICFLibKeyHash256 argClusterId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredOwnerCluster-args", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtClusterTable targetTable = targetBackingCFSec.getTableCluster();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredOwnerCluster", 0, "ICFSecSchema.getBackingCFSec().getTableCluster()");
		}
		ICFSecProtCluster found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argClusterId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredOwnerCluster-args", 0, "found");
		}
		else if (found instanceof ICFSecProtCluster) || (found instanceof ICFSecProtCluster) || (found instanceof ICFSecPubCluster)) {
			super.setRequiredOwnerCluster(argClusterId);
		requiredClusterId = argClusterId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredOwnerCluster-args", "found", found, "ICFSecProtClusterICFSecProtClusterICFSecPubCluster");
		}
	}

	@Override
	public void setRequiredOwnerCluster(ICFSecProtCluster argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setOwnerCluster", 1, "argObj");
		}
		else {
			setJustProtRequiredClusterId(argObj.getRequiredId());
		}
	}

	@Override
	public void setRequiredOwnerCluster(ICFSecPubCluster argObj) {

	}

	@Override
	public ICFSecProtSecSysGrp getRequiredContainerSysGrp(String argName) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSysGrp", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecSysGrpTable targetTable = targetBackingCFSec.getTableSecSysGrp();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSysGrp", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecSysGrp()");
		}
		return(targetTable.readDerivedByUNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredName()));
	}

	@Override
	public void setRequiredContainerSysGrp(String argName) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSysGrp-args", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecSysGrpTable targetTable = targetBackingCFSec.getTableSecSysGrp();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSysGrp", 0, "ICFSecSchema.getBackingCFSec().getTableSecSysGrp()");
		}
		ICFSecProtSecSysGrp found = targetTable.readDerivedByUNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argName);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSysGrp-args", 0, "found");
		}
		else if (found instanceof ICFSecProtSecSysGrp) || (found instanceof ICFSecProtSecSysGrp) || (found instanceof ICFSecPubSecSysGrp)) {
			super.setRequiredContainerSysGrp(argName);
		requiredName = argName;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredContainerSysGrp-args", "found", found, "ICFSecProtSecSysGrpICFSecProtSecSysGrpICFSecPubSecSysGrp");
		}
	}

	@Override
	public void setRequiredContainerSysGrp(ICFSecProtSecSysGrp argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerSysGrp", 1, "argObj");
		}
		else {
			setJustProtRequiredName(argObj.getRequiredName());
		}
	}

	@Override
	public void setRequiredContainerSysGrp(ICFSecPubSecSysGrp argObj) {

	}

	@Override
	public ICFLibKeyHash256 getRequiredClusterId() {
		return(requiredClusterId);
	}

	public void setRequiredClusterId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredClusterId",
				1,
				"value" );
		}
		requiredClusterId = value;
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
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecProtSecClusGrp rhs ) {
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
			if( getRequiredSecClusGrpId() != null ) {
				if( rhs.getRequiredSecClusGrpId() != null ) {
					if( ! getRequiredSecClusGrpId().equals( rhs.getRequiredSecClusGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecClusGrpId() != null ) {
					return( false );
				}
			}
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
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
			return( true );
		}
		else if( obj instanceof ICFSecProtSecClusGrpH rhs ) {
			if( getRequiredSecClusGrpId() != null ) {
				if( rhs.getRequiredSecClusGrpId() != null ) {
					if( ! getRequiredSecClusGrpId().equals( rhs.getRequiredSecClusGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecClusGrpId() != null ) {
					return( false );
				}
			}
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
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
			return( true );
		}
		else if( obj instanceof ICFSecProtSecClusGrpHPKey ) {
			ICFSecProtSecClusGrpHPKey rhs = (ICFSecProtSecClusGrpHPKey)obj;
			if( getRequiredSecClusGrpId() != null ) {
				if( rhs.getRequiredSecClusGrpId() != null ) {
					if( ! getRequiredSecClusGrpId().equals( rhs.getRequiredSecClusGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecClusGrpId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecClusGrpByClusterIdxKey rhs ) {
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecClusGrpByNameIdxKey rhs ) {
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
		else if( obj instanceof ICFSecProtSecClusGrpByUNameIdxKey rhs ) {
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
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
		hashCode = hashCode + getRequiredSecClusGrpId().hashCode();
		hashCode = hashCode + getRequiredClusterId().hashCode();
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecProtSecClusGrp rhs ) {
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
			if (getRequiredSecClusGrpId() != null) {
				if (rhs.getRequiredSecClusGrpId() != null) {
					cmp = getRequiredSecClusGrpId().compareTo( rhs.getRequiredSecClusGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecClusGrpId() != null) {
				return( -1 );
			}
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
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
			return( 0 );
 		}
		else if( obj instanceof ICFSecProtSecClusGrpHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecClusGrpId() != null) {
				if (rhs.getRequiredSecClusGrpId() != null) {
					cmp = getRequiredSecClusGrpId().compareTo( rhs.getRequiredSecClusGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecClusGrpId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecClusGrpH rhs ) {
			cmp = 0;
			if (getRequiredSecClusGrpId() != null) {
				if (rhs.getRequiredSecClusGrpId() != null) {
					cmp = getRequiredSecClusGrpId().compareTo( rhs.getRequiredSecClusGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecClusGrpId() != null) {
				return( -1 );
			}
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
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
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecClusGrpByClusterIdxKey rhs ) {
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecClusGrpByNameIdxKey rhs ) {
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
		else if( obj instanceof ICFSecProtSecClusGrpByUNameIdxKey rhs ) {
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
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
	public void set( ICFSecProtSecClusGrp src ) {
		setJustProtSecClusGrp( src );
	}

	@Override
	public void setSecClusGrp( ICFSecProtSecClusGrp src ) {
		setJustProtRequiredSecClusGrpId(src.getRequiredSecClusGrpId());
		setJustProtRequiredRevision( src.getRequiredRevision() );
		setJustProtCreatedByUserId( src.getCreatedByUserId() );
		setJustProtCreatedAt( src.getCreatedAt() );
		setJustProtUpdatedByUserId( src.getUpdatedByUserId() );
		setJustProtUpdatedAt( src.getUpdatedAt() );
		setJustProtRequiredOwnerCluster(src.getRequiredOwnerCluster());
		setJustProtRequiredContainerSysGrp(src.getRequiredContainerSysGrp());
		setJustProtRequiredClusterId(src.getRequiredClusterId());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFSecProtSecClusGrpH src ) {
		setJustProtSecClusGrp( src );
	}

	@Override
	public void setSecClusGrp( ICFSecProtSecClusGrpH src ) {
		setJustProtRequiredSecClusGrpId(src.getRequiredSecClusGrpId());
		setJustProtRequiredOwnerCluster(src.getRequiredOwnerCluster());
		setJustProtRequiredContainerSysGrp(src.getRequiredContainerSysGrp());
		setJustProtRequiredClusterId(src.getRequiredClusterId());
		setJustProtRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + getRequiredSecClusGrpId().toString() + "\"" + "\"" + getRequiredClusterId().toString() + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecProtBuffSecClusGrp" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
