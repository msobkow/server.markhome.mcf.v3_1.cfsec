// Description: Java 17 implJustProtementation of a SecUserPWReset buffer

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

public class CFSecProtBuffSecUserPWReset
	implements ICFSecProtSecUserPWReset, Comparable<Object>, Serializable
{
	protected ICFLibKeyHash256 requiredSecUserId;
	protected int requiredRevision;
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected CFLibDbKeyHash256 createdBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected CFLibDbKeyHash256 updatedBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();
	protected String requiredSentToEMailAddr;
	protected ICFLibUuid6 requiredPasswordResetUuid6;
	protected boolean requiredNewAccount;

	public CFSecProtBuffSecUserPWReset() {
		requiredSecUserId = CFLibDbKeyHash256.fromHex( ICFSecProtSecUserPWReset.SECUSERID_INIT_VALUE.toString() );
		requiredSentToEMailAddr = ICFSecProtSecUserPWReset.SENTTOEMAILADDR_INIT_VALUE;
		requiredNewAccount = ICFSecProtSecUserPWReset.NEWACCOUNT_INIT_VALUE;
	}

	@Override
	public ICFLibKeyHash256 getPKey() {
		return (requiredSecUserId);
	}

	@Override
	public void setPKey(ICFLibKeyHash256 requiredSecUserId) {
		if(requiredSecUserId != null) {
			this.requiredSecUserId = requiredSecUserId;
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecUserId() {
		return(requiredSecUserId);
	}

	public void setRequiredSecUserId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecUserId",
				1,
				"value" );
		}
		requiredSecUserId = value;
	}

	@Override
	public ICFSecProtSecUser getRequiredContainerUser(ICFLibKeyHash256 argSecUserId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerUser", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecUserTable targetTable = targetBackingCFSec.getTableSecUser();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerUser", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecUser()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId()));
	}

	@Override
	public void setRequiredContainerUser(ICFLibKeyHash256 argSecUserId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerUser-args", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecUserTable targetTable = targetBackingCFSec.getTableSecUser();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerUser", 0, "ICFSecSchema.getBackingCFSec().getTableSecUser()");
		}
		ICFSecProtSecUser found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argSecUserId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerUser-args", 0, "found");
		}
		else if (found instanceof ICFSecProtSecUser) || (found instanceof ICFSecProtSecUser) || (found instanceof ICFSecPubSecUser)) {
			super.setRequiredContainerUser(argSecUserId);
		requiredSecUserId = argSecUserId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredContainerUser-args", "found", found, "ICFSecProtSecUserICFSecProtSecUserICFSecPubSecUser");
		}
	}

	@Override
	public void setRequiredContainerUser(ICFSecProtSecUser argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerUser", 1, "argObj");
		}
		else {
			setJustProtRequiredSecUserId(argObj.getRequiredSecUserId());
		}
	}

	@Override
	public void setRequiredContainerUser(ICFSecPubSecUser argObj) {

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
		return( ICFSecProtSecUserPWReset.CLASS_CODE );
	}

	@Override
	public String getRequiredSentToEMailAddr() {
		return(requiredSentToEMailAddr);
	}

	public void setRequiredSentToEMailAddr( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSentToEMailAddr",
				1,
				"value" );
		}
		else if( value.length() > 512 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredSentToEMailAddr",
				1,
				"value.length()",
				value.length(),
				512 );
		}
		requiredSentToEMailAddr = value;
	}

	@Override
	public ICFLibUuid6 getRequiredPasswordResetUuid6() {
		return(requiredPasswordResetUuid6);
	}

	public void setRequiredPasswordResetUuid6( ICFLibUuid6 value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredPasswordResetUuid6",
				1,
				"value" );
		}
		requiredPasswordResetUuid6 = value;
	}

	@Override
	public boolean getRequiredNewAccount() {
		return(requiredNewAccount);
	}

	public void setRequiredNewAccount( boolean value ) {
		requiredNewAccount = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecProtSecUserPWReset rhs ) {
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
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			if( getRequiredSentToEMailAddr() != null ) {
				if( rhs.getRequiredSentToEMailAddr() != null ) {
					if( ! getRequiredSentToEMailAddr().equals( rhs.getRequiredSentToEMailAddr() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSentToEMailAddr() != null ) {
					return( false );
				}
			}
			if( getRequiredPasswordResetUuid6() != null ) {
				if( rhs.getRequiredPasswordResetUuid6() != null ) {
					if( ! getRequiredPasswordResetUuid6().equals( rhs.getRequiredPasswordResetUuid6() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPasswordResetUuid6() != null ) {
					return( false );
				}
			}
			if( getRequiredNewAccount() != rhs.getRequiredNewAccount() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetH rhs ) {
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			if( getRequiredSentToEMailAddr() != null ) {
				if( rhs.getRequiredSentToEMailAddr() != null ) {
					if( ! getRequiredSentToEMailAddr().equals( rhs.getRequiredSentToEMailAddr() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSentToEMailAddr() != null ) {
					return( false );
				}
			}
			if( getRequiredPasswordResetUuid6() != null ) {
				if( rhs.getRequiredPasswordResetUuid6() != null ) {
					if( ! getRequiredPasswordResetUuid6().equals( rhs.getRequiredPasswordResetUuid6() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPasswordResetUuid6() != null ) {
					return( false );
				}
			}
			if( getRequiredNewAccount() != rhs.getRequiredNewAccount() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetHPKey ) {
			ICFSecProtSecUserPWResetHPKey rhs = (ICFSecProtSecUserPWResetHPKey)obj;
			if( getRequiredSecUserId() != null ) {
				if( rhs.getRequiredSecUserId() != null ) {
					if( ! getRequiredSecUserId().equals( rhs.getRequiredSecUserId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecUserId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetByUUuid6IdxKey rhs ) {
			if( getRequiredPasswordResetUuid6() != null ) {
				if( rhs.getRequiredPasswordResetUuid6() != null ) {
					if( ! getRequiredPasswordResetUuid6().equals( rhs.getRequiredPasswordResetUuid6() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredPasswordResetUuid6() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetBySentEMAddrIdxKey rhs ) {
			if( getRequiredSentToEMailAddr() != null ) {
				if( rhs.getRequiredSentToEMailAddr() != null ) {
					if( ! getRequiredSentToEMailAddr().equals( rhs.getRequiredSentToEMailAddr() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSentToEMailAddr() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetByNewAcctIdxKey rhs ) {
			if( getRequiredNewAccount() != rhs.getRequiredNewAccount() ) {
				return( false );
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
		hashCode = hashCode + getRequiredSecUserId().hashCode();
		if( getRequiredSentToEMailAddr() != null ) {
			hashCode = hashCode + getRequiredSentToEMailAddr().hashCode();
		}
		hashCode = hashCode + getRequiredPasswordResetUuid6().hashCode();
		if( getRequiredNewAccount() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecProtSecUserPWReset rhs ) {
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
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			if (getRequiredSentToEMailAddr() != null) {
				if (rhs.getRequiredSentToEMailAddr() != null) {
					cmp = getRequiredSentToEMailAddr().compareTo( rhs.getRequiredSentToEMailAddr() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSentToEMailAddr() != null) {
				return( -1 );
			}
			if (getRequiredPasswordResetUuid6() != null) {
				if (rhs.getRequiredPasswordResetUuid6() != null) {
					cmp = getRequiredPasswordResetUuid6().compareTo( rhs.getRequiredPasswordResetUuid6() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPasswordResetUuid6() != null) {
				return( -1 );
			}
			if( getRequiredNewAccount() ) {
				if( ! rhs.getRequiredNewAccount() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredNewAccount() ) {
					return( -1 );
				}
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecProtSecUserPWResetHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetH rhs ) {
			cmp = 0;
			if (getRequiredSecUserId() != null) {
				if (rhs.getRequiredSecUserId() != null) {
					cmp = getRequiredSecUserId().compareTo( rhs.getRequiredSecUserId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecUserId() != null) {
				return( -1 );
			}
			if (getRequiredSentToEMailAddr() != null) {
				if (rhs.getRequiredSentToEMailAddr() != null) {
					cmp = getRequiredSentToEMailAddr().compareTo( rhs.getRequiredSentToEMailAddr() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSentToEMailAddr() != null) {
				return( -1 );
			}
			if (getRequiredPasswordResetUuid6() != null) {
				if (rhs.getRequiredPasswordResetUuid6() != null) {
					cmp = getRequiredPasswordResetUuid6().compareTo( rhs.getRequiredPasswordResetUuid6() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPasswordResetUuid6() != null) {
				return( -1 );
			}
			if( getRequiredNewAccount() ) {
				if( ! rhs.getRequiredNewAccount() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredNewAccount() ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetByUUuid6IdxKey rhs ) {
			if (getRequiredPasswordResetUuid6() != null) {
				if (rhs.getRequiredPasswordResetUuid6() != null) {
					cmp = getRequiredPasswordResetUuid6().compareTo( rhs.getRequiredPasswordResetUuid6() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredPasswordResetUuid6() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetBySentEMAddrIdxKey rhs ) {
			if (getRequiredSentToEMailAddr() != null) {
				if (rhs.getRequiredSentToEMailAddr() != null) {
					cmp = getRequiredSentToEMailAddr().compareTo( rhs.getRequiredSentToEMailAddr() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSentToEMailAddr() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecUserPWResetByNewAcctIdxKey rhs ) {
			if( getRequiredNewAccount() ) {
				if( ! rhs.getRequiredNewAccount() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredNewAccount() ) {
					return( -1 );
				}
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
	public void set( ICFSecProtSecUserPWReset src ) {
		setJustProtSecUserPWReset( src );
	}

	@Override
	public void setSecUserPWReset( ICFSecProtSecUserPWReset src ) {
		setJustProtRequiredContainerUser(src.getRequiredContainerUser());
		setJustProtRequiredSecUserId(src.getRequiredSecUserId());
		setJustProtRequiredRevision( src.getRequiredRevision() );
		setJustProtCreatedByUserId( src.getCreatedByUserId() );
		setJustProtCreatedAt( src.getCreatedAt() );
		setJustProtUpdatedByUserId( src.getUpdatedByUserId() );
		setJustProtUpdatedAt( src.getUpdatedAt() );
		setJustProtRequiredSentToEMailAddr(src.getRequiredSentToEMailAddr());
		setJustProtRequiredPasswordResetUuid6(src.getRequiredPasswordResetUuid6());
		setJustProtRequiredNewAccount(src.getRequiredNewAccount());
	}

	@Override
	public void set( ICFSecProtSecUserPWResetH src ) {
		setJustProtSecUserPWReset( src );
	}

	@Override
	public void setSecUserPWReset( ICFSecProtSecUserPWResetH src ) {
		setJustProtRequiredContainerUser(src.getRequiredContainerUser());
		setJustProtRequiredSecUserId(src.getRequiredSecUserId());
		setJustProtRequiredSentToEMailAddr(src.getRequiredSentToEMailAddr());
		setJustProtRequiredPasswordResetUuid6(src.getRequiredPasswordResetUuid6());
		setJustProtRequiredNewAccount(src.getRequiredNewAccount());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + getRequiredSecUserId().toString() + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredSentToEMailAddr() ) + "\"" + "\"" + getRequiredPasswordResetUuid6().toString() + "\"" + (( getRequiredNewAccount() ) ? "\"true\"" : "\"false\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecProtBuffSecUserPWReset" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
