// Description: Java 17 implJustProtementation of a SecTentRoleMemb buffer

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

public class CFSecProtBuffSecTentRoleMemb
	implements ICFSecProtSecTentRoleMemb, Comparable<Object>, Serializable
{
	protected ICFSecProtSecTentRoleMembPKey pkey = new CFSecProtBuffSecTentRoleMembPKey();
	protected int requiredRevision;
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected CFLibDbKeyHash256 createdBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected CFLibDbKeyHash256 updatedBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();

	public CFSecProtBuffSecTentRoleMemb() {
		pkey = new CFSecProtBuffSecTentRoleMembPKey();
	}

	@Override
	public ICFSecProtSecTentRoleMembPKey getPKey() {
		return(pkey);
	}

	@Override
	public void setPKey(ICFSecProtSecTentRoleMembPKey pkey ) {
		if (pkey == null) {
			throw new CFLibNullArgumentException(getClass(), "setPKey", 1, "pkey");
		}
		else {
			this.pkey = pkey;
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecTentRoleId() {
		return(getPKey().getRequiredSecTentRoleId());
	}

	public void setRequiredSecTentRoleId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecTentRoleId",
				1,
				"value" );
		}
		getPKey().setRequiredSecTentRoleId(value);
	}

	@Override
	public String getRequiredLoginId() {
		return(getPKey().getRequiredLoginId());
	}

	public void setRequiredLoginId( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredLoginId",
				1,
				"value" );
		}
		else if( value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredLoginId",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		getPKey().setRequiredLoginId(value);
	}

	@Override
	public ICFSecProtSecTentRole getRequiredContainerRole(ICFLibKeyHash256 argSecTentRoleId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerRole", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecTentRoleTable targetTable = targetBackingCFSec.getTableSecTentRole();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerRole", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecTentRole()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecTentRoleId()));
	}

	@Override
	public void setRequiredContainerRole(ICFLibKeyHash256 argSecTentRoleId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerRole-args", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecTentRoleTable targetTable = targetBackingCFSec.getTableSecTentRole();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerRole", 0, "ICFSecSchema.getBackingCFSec().getTableSecTentRole()");
		}
		ICFSecProtSecTentRole found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argSecTentRoleId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerRole-args", 0, "found");
		}
		else if (found instanceof ICFSecProtSecTentRole) || (found instanceof ICFSecProtSecTentRole)) {
			super.setRequiredContainerRole(argSecTentRoleId);
		requiredSecTentRoleId = argSecTentRoleId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredContainerRole-args", "found", found, "ICFSecProtSecTentRoleICFSecProtSecTentRole");
		}
	}

	@Override
	public void setRequiredContainerRole(ICFSecProtSecTentRole argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerRole", 1, "argObj");
		}
		else {
			setJustProtRequiredSecTentRoleId(argObj.getRequiredSecTentRoleId());
		}
	}

	@Override
	public ICFSecProtSecUser getRequiredParentUser(String argLoginId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredParentUser", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecUserTable targetTable = targetBackingCFSec.getTableSecUser();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredParentUser", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecUser()");
		}
		return(targetTable.readDerivedByULoginIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredLoginId()));
	}

	@Override
	public void setRequiredParentUser(String argLoginId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredParentUser-args", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecUserTable targetTable = targetBackingCFSec.getTableSecUser();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredParentUser", 0, "ICFSecSchema.getBackingCFSec().getTableSecUser()");
		}
		ICFSecProtSecUser found = targetTable.readDerivedByULoginIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argLoginId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredParentUser-args", 0, "found");
		}
		else if (found instanceof ICFSecProtSecUser) || (found instanceof ICFSecProtSecUser) || (found instanceof ICFSecPubSecUser)) {
			super.setRequiredParentUser(argLoginId);
		requiredLoginId = argLoginId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredParentUser-args", "found", found, "ICFSecProtSecUserICFSecProtSecUserICFSecPubSecUser");
		}
	}

	@Override
	public void setRequiredParentUser(ICFSecProtSecUser argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setParentUser", 1, "argObj");
		}
		else {
			setJustProtRequiredLoginId(argObj.getRequiredLoginId());
		}
	}

	@Override
	public void setRequiredParentUser(ICFSecPubSecUser argObj) {

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
		return( ICFSecProtSecTentRoleMemb.CLASS_CODE );
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMemb rhs ) {
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
			if( getRequiredSecTentRoleId() != null ) {
				if( rhs.getRequiredSecTentRoleId() != null ) {
					if( ! getRequiredSecTentRoleId().equals( rhs.getRequiredSecTentRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecTentRoleId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMembH rhs ) {
			if( getRequiredSecTentRoleId() != null ) {
				if( rhs.getRequiredSecTentRoleId() != null ) {
					if( ! getRequiredSecTentRoleId().equals( rhs.getRequiredSecTentRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecTentRoleId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMembHPKey ) {
			ICFSecProtSecTentRoleMembHPKey rhs = (ICFSecProtSecTentRoleMembHPKey)obj;
			if( getRequiredSecTentRoleId() != null ) {
				if( rhs.getRequiredSecTentRoleId() != null ) {
					if( ! getRequiredSecTentRoleId().equals( rhs.getRequiredSecTentRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecTentRoleId() != null ) {
					return( false );
				}
			}
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMembByTentRoleIdxKey rhs ) {
			if( getRequiredSecTentRoleId() != null ) {
				if( rhs.getRequiredSecTentRoleId() != null ) {
					if( ! getRequiredSecTentRoleId().equals( rhs.getRequiredSecTentRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecTentRoleId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMembByUserIdxKey rhs ) {
			if( getRequiredLoginId() != null ) {
				if( rhs.getRequiredLoginId() != null ) {
					if( ! getRequiredLoginId().equals( rhs.getRequiredLoginId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredLoginId() != null ) {
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
		hashCode = hashCode + getRequiredSecTentRoleId().hashCode();
		if( getRequiredLoginId() != null ) {
			hashCode = hashCode + getRequiredLoginId().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMemb rhs ) {
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
			if (getRequiredSecTentRoleId() != null) {
				if (rhs.getRequiredSecTentRoleId() != null) {
					cmp = getRequiredSecTentRoleId().compareTo( rhs.getRequiredSecTentRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecTentRoleId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecProtSecTentRoleMembHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecTentRoleId() != null) {
				if (rhs.getRequiredSecTentRoleId() != null) {
					cmp = getRequiredSecTentRoleId().compareTo( rhs.getRequiredSecTentRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecTentRoleId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMembH rhs ) {
			cmp = 0;
			if (getRequiredSecTentRoleId() != null) {
				if (rhs.getRequiredSecTentRoleId() != null) {
					cmp = getRequiredSecTentRoleId().compareTo( rhs.getRequiredSecTentRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecTentRoleId() != null) {
				return( -1 );
			}
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMembByTentRoleIdxKey rhs ) {
			if (getRequiredSecTentRoleId() != null) {
				if (rhs.getRequiredSecTentRoleId() != null) {
					cmp = getRequiredSecTentRoleId().compareTo( rhs.getRequiredSecTentRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecTentRoleId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecTentRoleMembByUserIdxKey rhs ) {
			if (getRequiredLoginId() != null) {
				if (rhs.getRequiredLoginId() != null) {
					cmp = getRequiredLoginId().compareTo( rhs.getRequiredLoginId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredLoginId() != null) {
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
	public void set( ICFSecProtSecTentRoleMemb src ) {
		setJustProtSecTentRoleMemb( src );
	}

	@Override
	public void setSecTentRoleMemb( ICFSecProtSecTentRoleMemb src ) {
		setJustProtRequiredContainerRole(src.getRequiredContainerRole());
		setJustProtRequiredParentUser(src.getRequiredParentUser());
		setJustProtRequiredSecTentRoleId(src.getRequiredSecTentRoleId());
		setJustProtRequiredLoginId(src.getRequiredLoginId());
		setJustProtRequiredRevision( src.getRequiredRevision() );
		setJustProtCreatedByUserId( src.getCreatedByUserId() );
		setJustProtCreatedAt( src.getCreatedAt() );
		setJustProtUpdatedByUserId( src.getUpdatedByUserId() );
		setJustProtUpdatedAt( src.getUpdatedAt() );
	}

	@Override
	public void set( ICFSecProtSecTentRoleMembH src ) {
		setJustProtSecTentRoleMemb( src );
	}

	@Override
	public void setSecTentRoleMemb( ICFSecProtSecTentRoleMembH src ) {
		setJustProtRequiredContainerRole(src.getRequiredContainerRole());
		setJustProtRequiredParentUser(src.getRequiredParentUser());
		setJustProtRequiredSecTentRoleId(src.getRequiredSecTentRoleId());
		setJustProtRequiredLoginId(src.getRequiredLoginId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + getRequiredSecTentRoleId().toString() + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredLoginId() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecProtBuffSecTentRoleMemb" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
