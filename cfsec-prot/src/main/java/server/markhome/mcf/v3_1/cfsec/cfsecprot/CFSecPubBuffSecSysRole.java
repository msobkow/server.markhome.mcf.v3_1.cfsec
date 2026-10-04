// Description: Java 17 implJustPubementation of a SecSysRole buffer

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
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public class CFSecPubBuffSecSysRole
	implements ICFSecPubSecSysRole, Comparable<Object>, Serializable
{
	protected IMCFKeyHash256 requiredSecSysRoleId;
	protected int requiredRevision;
	protected MCFDbKeyHash256 createdByUserId = MCFDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected MCFDbKeyHash256 createdBySessionId = MCFDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected MCFDbKeyHash256 updatedByUserId = MCFDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected MCFDbKeyHash256 updatedBySessionId = MCFDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();
	protected String requiredName;

	public CFSecPubBuffSecSysRole() {
		requiredSecSysRoleId = MCFDbKeyHash256.fromHex( ICFSecPubSecSysRole.SECSYSROLEID_INIT_VALUE.toString() );
		requiredName = ICFSecPubSecSysRole.NAME_INIT_VALUE;
	}

	@Override
	public IMCFKeyHash256 getPKey() {
		return (requiredSecSysRoleId);
	}

	@Override
	public void setPKey(IMCFKeyHash256 requiredSecSysRoleId) {
		if(requiredSecSysRoleId != null) {
			this.requiredSecSysRoleId = requiredSecSysRoleId;
		}
	}

	@Override
	public IMCFKeyHash256 getRequiredSecSysRoleId() {
		return(requiredSecSysRoleId);
	}

	public void setRequiredSecSysRoleId( IMCFKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new MCFNullArgumentException( getClass(),
				"setRequiredSecSysRoleId",
				1,
				"value" );
		}
		requiredSecSysRoleId = value;
	}

	@Override
	public MCFDbKeyHash256 getCreatedByUserId() {
		return( createdByUserId );
	}

	@Override
	public void setJustPubCreatedByUserId( MCFDbKeyHash256 value ) {
		createdByUserId = value;
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( createdAt );
	}

	@Override
	public void setJustPubCreatedAt( LocalDateTime value ) {
		createdAt = value;
	}

	@Override
	public MCFDbKeyHash256 getUpdatedByUserId() {
		return( updatedByUserId );
	}

	@Override
	public void setJustPubUpdatedByUserId( MCFDbKeyHash256 value ) {
		updatedByUserId = value;
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( updatedAt );
	}

	@Override
	public void setJustPubUpdatedAt( LocalDateTime value ) {
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
		return( ICFSecPubSecSysRole.CLASS_CODE );
	}

	@Override
	public String getRequiredName() {
		return(requiredName);
	}

	public void setRequiredName( String value ) {
		if( value == null ) {
			throw new MCFNullArgumentException( getClass(),
				"setRequiredName",
				1,
				"value" );
		}
		else if( value.length() > 64 ) {
			throw new MCFArgumentOverflowException( getClass(),
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
		else if( obj instanceof ICFSecPubSecSysRole rhs ) {
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
			if( getRequiredSecSysRoleId() != null ) {
				if( rhs.getRequiredSecSysRoleId() != null ) {
					if( ! getRequiredSecSysRoleId().equals( rhs.getRequiredSecSysRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysRoleId() != null ) {
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
		else if( obj instanceof ICFSecPubSecSysRoleH rhs ) {
			if( getRequiredSecSysRoleId() != null ) {
				if( rhs.getRequiredSecSysRoleId() != null ) {
					if( ! getRequiredSecSysRoleId().equals( rhs.getRequiredSecSysRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysRoleId() != null ) {
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
		else if( obj instanceof ICFSecPubSecSysRoleHPKey ) {
			ICFSecPubSecSysRoleHPKey rhs = (ICFSecPubSecSysRoleHPKey)obj;
			if( getRequiredSecSysRoleId() != null ) {
				if( rhs.getRequiredSecSysRoleId() != null ) {
					if( ! getRequiredSecSysRoleId().equals( rhs.getRequiredSecSysRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysRoleId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSysRoleByUNameIdxKey rhs ) {
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
		else if( obj instanceof ICFSecPubSecSysRole rhs ) {
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
			if( getRequiredSecSysRoleId() != null ) {
				if( rhs.getRequiredSecSysRoleId() != null ) {
					if( ! getRequiredSecSysRoleId().equals( rhs.getRequiredSecSysRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysRoleId() != null ) {
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
		else if( obj instanceof ICFSecPubSecSysRoleH rhs ) {
			if( getRequiredSecSysRoleId() != null ) {
				if( rhs.getRequiredSecSysRoleId() != null ) {
					if( ! getRequiredSecSysRoleId().equals( rhs.getRequiredSecSysRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysRoleId() != null ) {
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
		else if( obj instanceof ICFSecPubSecSysRoleHPKey rhs ) {
			if( getRequiredSecSysRoleId() != null ) {
				if( rhs.getRequiredSecSysRoleId() != null ) {
					if( ! getRequiredSecSysRoleId().equals( rhs.getRequiredSecSysRoleId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSysRoleId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSysRoleByUNameIdxKey rhs ) {
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
		hashCode = hashCode + getRequiredSecSysRoleId().hashCode();
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
		else if( obj instanceof ICFSecPubSecSysRole rhs ) {
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
			if (getRequiredSecSysRoleId() != null) {
				if (rhs.getRequiredSecSysRoleId() != null) {
					cmp = getRequiredSecSysRoleId().compareTo( rhs.getRequiredSecSysRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysRoleId() != null) {
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
		else if( obj instanceof ICFSecPubSecSysRoleHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecSysRoleId() != null) {
				if (rhs.getRequiredSecSysRoleId() != null) {
					cmp = getRequiredSecSysRoleId().compareTo( rhs.getRequiredSecSysRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysRoleId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSysRoleH rhs ) {
			cmp = 0;
			if (getRequiredSecSysRoleId() != null) {
				if (rhs.getRequiredSecSysRoleId() != null) {
					cmp = getRequiredSecSysRoleId().compareTo( rhs.getRequiredSecSysRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysRoleId() != null) {
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
		else if( obj instanceof ICFSecPubSecSysRoleByUNameIdxKey rhs ) {
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
		else if( obj instanceof ICFSecPubSecSysRole rhs ) {
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
			if (getRequiredSecSysRoleId() != null) {
				if (rhs.getRequiredSecSysRoleId() != null) {
					cmp = getRequiredSecSysRoleId().compareTo( rhs.getRequiredSecSysRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysRoleId() != null) {
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
		else if( obj instanceof ICFSecPubSecSysRoleHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecSysRoleId() != null) {
				if (rhs.getRequiredSecSysRoleId() != null) {
					cmp = getRequiredSecSysRoleId().compareTo( rhs.getRequiredSecSysRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysRoleId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSysRoleH rhs ) {
			cmp = 0;
			if (getRequiredSecSysRoleId() != null) {
				if (rhs.getRequiredSecSysRoleId() != null) {
					cmp = getRequiredSecSysRoleId().compareTo( rhs.getRequiredSecSysRoleId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSysRoleId() != null) {
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
		else if( obj instanceof ICFSecPubSecSysRoleByUNameIdxKey rhs ) {
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
			throw new MCFUnsupportedClassException( getClass(),
				"compareTo",
				"obj",
				obj,
				null );
		}
	}

	@Override
	public void set( ICFSecPubSecSysRole src ) {
		setJustPubSecSysRole( src );
	}

	@Override
	public void setSecSysRole( ICFSecPubSecSysRole src ) {
		setJustPubRequiredSecSysRoleId(src.getRequiredSecSysRoleId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubCreatedByUserId( src.getCreatedByUserId() );
		setJustPubCreatedAt( src.getCreatedAt() );
		setJustPubUpdatedByUserId( src.getUpdatedByUserId() );
		setJustPubUpdatedAt( src.getUpdatedAt() );
		setJustPubRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFSecPubSecSysRoleH src ) {
		setJustPubSecSysRole( src );
	}

	@Override
	public void setSecSysRole( ICFSecPubSecSysRoleH src ) {
		setJustPubRequiredSecSysRoleId(src.getRequiredSecSysRoleId());
		setJustPubRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFSecPubSecSysRole src ) {
		setJustPubSecSysRole( src );
	}

	@Override
	public void setSecSysRole( ICFSecPubSecSysRole src ) {
		setJustPubRequiredSecSysRoleId(src.getRequiredSecSysRoleId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubCreatedByUserId( src.getCreatedByUserId() );
		setJustPubCreatedAt( src.getCreatedAt() );
		setJustPubUpdatedByUserId( src.getUpdatedByUserId() );
		setJustPubUpdatedAt( src.getUpdatedAt() );
		setJustPubRequiredName(src.getRequiredName());
	}

	@Override
	public void set( ICFSecPubSecSysRoleH src ) {
		setJustPubSecSysRole( src );
	}

	@Override
	public void setSecSysRole( ICFSecPubSecSysRoleH src ) {
		setJustPubRequiredSecSysRoleId(src.getRequiredSecSysRoleId());
		setJustPubRequiredName(src.getRequiredName());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + getRequiredSecSysRoleId().toString() + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecPubBuffSecSysRole" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
