// Description: Java 17 implJustProtementation of a SecTentGrpMemb buffer

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
import server.markhome.mcf.v3_1.cflib.xml.MCFXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

public class CFSecProtBuffSecTentGrpMemb
	implements ICFSecProtSecTentGrpMemb, Comparable<Object>, Serializable
{
	protected ICFSecProtSecTentGrpMembPKey pkey = new CFSecProtBuffSecTentGrpMembPKey();
	protected int requiredRevision;
	protected MCFDbKeyHash256 createdByUserId = MCFDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected MCFDbKeyHash256 createdBySessionId = MCFDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected MCFDbKeyHash256 updatedByUserId = MCFDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected MCFDbKeyHash256 updatedBySessionId = MCFDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();

	public CFSecProtBuffSecTentGrpMemb() {
		pkey = new CFSecProtBuffSecTentGrpMembPKey();
	}

	@Override
	public ICFSecProtSecTentGrpMembPKey getPKey() {
		return(pkey);
	}

	@Override
	public void setPKey(ICFSecProtSecTentGrpMembPKey pkey ) {
		if (pkey == null) {
			throw new MCFNullArgumentException(getClass(), "setPKey", 1, "pkey");
		}
		else {
			this.pkey = pkey;
		}
	}

	@Override
	public IMCFKeyHash256 getRequiredSecTentGrpId() {
		return(getPKey().getRequiredSecTentGrpId());
	}

	public void setRequiredSecTentGrpId( IMCFKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new MCFNullArgumentException( getClass(),
				"setRequiredSecTentGrpId",
				1,
				"value" );
		}
		getPKey().setRequiredSecTentGrpId(value);
	}

	@Override
	public String getRequiredLoginId() {
		return(getPKey().getRequiredLoginId());
	}

	public void setRequiredLoginId( String value ) {
		if( value == null ) {
			throw new MCFNullArgumentException( getClass(),
				"setRequiredLoginId",
				1,
				"value" );
		}
		else if( value.length() > 32 ) {
			throw new MCFArgumentOverflowException( getClass(),
				"setRequiredLoginId",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		getPKey().setRequiredLoginId(value);
	}

	@Override
	public ICFSecProtSecTentGrp getRequiredContainerGroup(IMCFKeyHash256 argSecTentGrpId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new MCFNullArgumentException(getClass(), "getRequiredContainerGroup", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecTentGrpTable targetTable = targetBackingCFSec.getTableSecTentGrp();
		if (targetTable == null) {
			throw new MCFNullArgumentException(getClass(), "getRequiredContainerGroup", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecTentGrp()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecTentGrpId()));
	}

	@Override
	public void setRequiredContainerGroup(IMCFKeyHash256 argSecTentGrpId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredContainerGroup-args", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecTentGrpTable targetTable = targetBackingCFSec.getTableSecTentGrp();
		if (targetTable == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredContainerGroup", 0, "ICFSecSchema.getBackingCFSec().getTableSecTentGrp()");
		}
		ICFSecProtSecTentGrp found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argSecTentGrpId);
		if (found == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredContainerGroup-args", 0, "found");
		}
		else if (found instanceof ICFSecProtSecTentGrp) || (found instanceof ICFSecProtSecTentGrp)) {
			super.setRequiredContainerGroup(argSecTentGrpId);
		requiredSecTentGrpId = argSecTentGrpId;
		}
		else {
			throw new MCFUnsupportedClassException(getClass(), "setRequiredContainerGroup-args", "found", found, "ICFSecProtSecTentGrpICFSecProtSecTentGrp");
		}
	}

	@Override
	public void setRequiredContainerGroup(ICFSecProtSecTentGrp argObj) {

		if(argObj == null) {
			throw new MCFNullArgumentException(getClass(), "setContainerGroup", 1, "argObj");
		}
		else {
			setJustProtRequiredSecTentGrpId(argObj.getRequiredSecTentGrpId());
		}
	}

	@Override
	public ICFSecProtSecUser getRequiredParentUser(String argLoginId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new MCFNullArgumentException(getClass(), "getRequiredParentUser", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecUserTable targetTable = targetBackingCFSec.getTableSecUser();
		if (targetTable == null) {
			throw new MCFNullArgumentException(getClass(), "getRequiredParentUser", 0, "ICFSecProtSchema.getBackingCFSec().getTableSecUser()");
		}
		return(targetTable.readDerivedByULoginIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredLoginId()));
	}

	@Override
	public void setRequiredParentUser(String argLoginId) {
		ICFSecProtSchema targetBackingCFSec = ICFSecProtSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredParentUser-args", 0, "ICFSecProtSchema.getBackingCFSec()");
		}
		ICFSecProtSecUserTable targetTable = targetBackingCFSec.getTableSecUser();
		if (targetTable == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredParentUser", 0, "ICFSecSchema.getBackingCFSec().getTableSecUser()");
		}
		ICFSecProtSecUser found = targetTable.readDerivedByULoginIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argLoginId);
		if (found == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredParentUser-args", 0, "found");
		}
		else if (found instanceof ICFSecProtSecUser) || (found instanceof ICFSecProtSecUser) || (found instanceof ICFSecPubSecUser)) {
			super.setRequiredParentUser(argLoginId);
		requiredLoginId = argLoginId;
		}
		else {
			throw new MCFUnsupportedClassException(getClass(), "setRequiredParentUser-args", "found", found, "ICFSecProtSecUserICFSecProtSecUserICFSecPubSecUser");
		}
	}

	@Override
	public void setRequiredParentUser(ICFSecProtSecUser argObj) {

		if(argObj == null) {
			throw new MCFNullArgumentException(getClass(), "setParentUser", 1, "argObj");
		}
		else {
			setJustProtRequiredLoginId(argObj.getRequiredLoginId());
		}
	}

	@Override
	public void setRequiredParentUser(ICFSecPubSecUser argObj) {

	}

	@Override
	public MCFDbKeyHash256 getCreatedByUserId() {
		return( createdByUserId );
	}

	@Override
	public void setJustProtCreatedByUserId( MCFDbKeyHash256 value ) {
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
	public MCFDbKeyHash256 getUpdatedByUserId() {
		return( updatedByUserId );
	}

	@Override
	public void setJustProtUpdatedByUserId( MCFDbKeyHash256 value ) {
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
		return( ICFSecProtSecTentGrpMemb.CLASS_CODE );
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecProtSecTentGrpMemb rhs ) {
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
			if( getRequiredSecTentGrpId() != null ) {
				if( rhs.getRequiredSecTentGrpId() != null ) {
					if( ! getRequiredSecTentGrpId().equals( rhs.getRequiredSecTentGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecTentGrpId() != null ) {
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
		else if( obj instanceof ICFSecProtSecTentGrpMembH rhs ) {
			if( getRequiredSecTentGrpId() != null ) {
				if( rhs.getRequiredSecTentGrpId() != null ) {
					if( ! getRequiredSecTentGrpId().equals( rhs.getRequiredSecTentGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecTentGrpId() != null ) {
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
		else if( obj instanceof ICFSecProtSecTentGrpMembHPKey ) {
			ICFSecProtSecTentGrpMembHPKey rhs = (ICFSecProtSecTentGrpMembHPKey)obj;
			if( getRequiredSecTentGrpId() != null ) {
				if( rhs.getRequiredSecTentGrpId() != null ) {
					if( ! getRequiredSecTentGrpId().equals( rhs.getRequiredSecTentGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecTentGrpId() != null ) {
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
		else if( obj instanceof ICFSecProtSecTentGrpMembByTentGrpIdxKey rhs ) {
			if( getRequiredSecTentGrpId() != null ) {
				if( rhs.getRequiredSecTentGrpId() != null ) {
					if( ! getRequiredSecTentGrpId().equals( rhs.getRequiredSecTentGrpId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecTentGrpId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecProtSecTentGrpMembByUserIdxKey rhs ) {
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
		hashCode = hashCode + getRequiredSecTentGrpId().hashCode();
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
		else if( obj instanceof ICFSecProtSecTentGrpMemb rhs ) {
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
			if (getRequiredSecTentGrpId() != null) {
				if (rhs.getRequiredSecTentGrpId() != null) {
					cmp = getRequiredSecTentGrpId().compareTo( rhs.getRequiredSecTentGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecTentGrpId() != null) {
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
		else if( obj instanceof ICFSecProtSecTentGrpMembHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecTentGrpId() != null) {
				if (rhs.getRequiredSecTentGrpId() != null) {
					cmp = getRequiredSecTentGrpId().compareTo( rhs.getRequiredSecTentGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecTentGrpId() != null) {
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
		else if( obj instanceof ICFSecProtSecTentGrpMembH rhs ) {
			cmp = 0;
			if (getRequiredSecTentGrpId() != null) {
				if (rhs.getRequiredSecTentGrpId() != null) {
					cmp = getRequiredSecTentGrpId().compareTo( rhs.getRequiredSecTentGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecTentGrpId() != null) {
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
		else if( obj instanceof ICFSecProtSecTentGrpMembByTentGrpIdxKey rhs ) {
			if (getRequiredSecTentGrpId() != null) {
				if (rhs.getRequiredSecTentGrpId() != null) {
					cmp = getRequiredSecTentGrpId().compareTo( rhs.getRequiredSecTentGrpId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecTentGrpId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecProtSecTentGrpMembByUserIdxKey rhs ) {
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
			throw new MCFUnsupportedClassException( getClass(),
				"compareTo",
				"obj",
				obj,
				null );
		}
	}

	@Override
	public void set( ICFSecProtSecTentGrpMemb src ) {
		setJustProtSecTentGrpMemb( src );
	}

	@Override
	public void setSecTentGrpMemb( ICFSecProtSecTentGrpMemb src ) {
		setJustProtRequiredContainerGroup(src.getRequiredContainerGroup());
		setJustProtRequiredParentUser(src.getRequiredParentUser());
		setJustProtRequiredSecTentGrpId(src.getRequiredSecTentGrpId());
		setJustProtRequiredLoginId(src.getRequiredLoginId());
		setJustProtRequiredRevision( src.getRequiredRevision() );
		setJustProtCreatedByUserId( src.getCreatedByUserId() );
		setJustProtCreatedAt( src.getCreatedAt() );
		setJustProtUpdatedByUserId( src.getUpdatedByUserId() );
		setJustProtUpdatedAt( src.getUpdatedAt() );
	}

	@Override
	public void set( ICFSecProtSecTentGrpMembH src ) {
		setJustProtSecTentGrpMemb( src );
	}

	@Override
	public void setSecTentGrpMemb( ICFSecProtSecTentGrpMembH src ) {
		setJustProtRequiredContainerGroup(src.getRequiredContainerGroup());
		setJustProtRequiredParentUser(src.getRequiredParentUser());
		setJustProtRequiredSecTentGrpId(src.getRequiredSecTentGrpId());
		setJustProtRequiredLoginId(src.getRequiredLoginId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + getRequiredSecTentGrpId().toString() + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredLoginId() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecProtBuffSecTentGrpMemb" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
