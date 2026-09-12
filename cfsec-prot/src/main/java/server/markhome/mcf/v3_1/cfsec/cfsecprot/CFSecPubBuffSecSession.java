// Description: Java 17 implJustPubementation of a SecSession buffer

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
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public class CFSecPubBuffSecSession
	implements ICFSecPubSecSession, Comparable<Object>, Serializable
{
	protected ICFLibKeyHash256 requiredSecSessionId;
	protected int requiredRevision;
	protected ICFLibKeyHash256 requiredSecUserId;
	protected LocalDateTime requiredStart;
	protected LocalDateTime optionalFinish;

	public CFSecPubBuffSecSession() {
		requiredSecSessionId = CFLibDbKeyHash256.fromHex( ICFSecPubSecSession.SECSESSIONID_INIT_VALUE.toString() );
		requiredSecUserId = CFLibDbKeyHash256.fromHex( ICFSecPubSecSession.SECUSERID_INIT_VALUE.toString() );
		requiredStart = CFLibXmlUtil.parseTimestamp("2020-01-01T00:00:00");
		optionalFinish = null;
	}

	@Override
	public ICFLibKeyHash256 getPKey() {
		return (requiredSecSessionId);
	}

	@Override
	public void setPKey(ICFLibKeyHash256 requiredSecSessionId) {
		if(requiredSecSessionId != null) {
			this.requiredSecSessionId = requiredSecSessionId;
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecSessionId() {
		return(requiredSecSessionId);
	}

	public void setRequiredSecSessionId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecSessionId",
				1,
				"value" );
		}
		requiredSecSessionId = value;
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
		return( ICFSecPubSecSession.CLASS_CODE );
	}

	@Override
	public ICFSecPubSecUser getRequiredContainerSecUser(ICFLibKeyHash256 argSecUserId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSecUser", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubSecUserTable targetTable = targetBackingCFSec.getTableSecUser();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSecUser", 0, "ICFSecPubSchema.getBackingCFSec().getTableSecUser()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSecUserId()));
	}

	@Override
	public void setRequiredContainerSecUser(ICFLibKeyHash256 argSecUserId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSecUser-args", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubSecUserTable targetTable = targetBackingCFSec.getTableSecUser();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSecUser", 0, "ICFSecSchema.getBackingCFSec().getTableSecUser()");
		}
		ICFSecPubSecUser found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argSecUserId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSecUser-args", 0, "found");
		}
		else if (found instanceof ICFSecPubSecUser) || (found instanceof ICFSecPubSecUser)) {
			super.setRequiredContainerSecUser(argSecUserId);
		requiredSecUserId = argSecUserId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredContainerSecUser-args", "found", found, "ICFSecPubSecUserICFSecPubSecUser");
		}
	}

	@Override
	public void setRequiredContainerSecUser(ICFSecPubSecUser argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerSecUser", 1, "argObj");
		}
		else {
			setRequiredSecUserId(argObj.getRequiredSecUserId());
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
	public LocalDateTime getRequiredStart() {
		return(requiredStart);
	}

	public void setRequiredStart( LocalDateTime value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredStart",
				1,
				"value" );
		}
		requiredStart = value;
	}

	@Override
	public LocalDateTime getOptionalFinish() {
		return(optionalFinish);
	}

	public void setOptionalFinish( LocalDateTime value ) {
		optionalFinish = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecPubSecSession rhs ) {
			if( getRequiredSecSessionId() != null ) {
				if( rhs.getRequiredSecSessionId() != null ) {
					if( ! getRequiredSecSessionId().equals( rhs.getRequiredSecSessionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSessionId() != null ) {
					return( false );
				}
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
			if( getRequiredStart() != null ) {
				if( rhs.getRequiredStart() != null ) {
					if( ! getRequiredStart().equals( rhs.getRequiredStart() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredStart() != null ) {
					return( false );
				}
			}
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					if( ! getOptionalFinish().equals( rhs.getOptionalFinish() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSessionH rhs ) {
			if( getRequiredSecSessionId() != null ) {
				if( rhs.getRequiredSecSessionId() != null ) {
					if( ! getRequiredSecSessionId().equals( rhs.getRequiredSecSessionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSessionId() != null ) {
					return( false );
				}
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
			if( getRequiredStart() != null ) {
				if( rhs.getRequiredStart() != null ) {
					if( ! getRequiredStart().equals( rhs.getRequiredStart() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredStart() != null ) {
					return( false );
				}
			}
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					if( ! getOptionalFinish().equals( rhs.getOptionalFinish() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSessionHPKey ) {
			ICFSecPubSecSessionHPKey rhs = (ICFSecPubSecSessionHPKey)obj;
			if( getRequiredSecSessionId() != null ) {
				if( rhs.getRequiredSecSessionId() != null ) {
					if( ! getRequiredSecSessionId().equals( rhs.getRequiredSecSessionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSessionId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSessionBySecUserIdxKey rhs ) {
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
		else if( obj instanceof ICFSecPubSecSessionByStartIdxKey rhs ) {
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
			if( getRequiredStart() != null ) {
				if( rhs.getRequiredStart() != null ) {
					if( ! getRequiredStart().equals( rhs.getRequiredStart() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredStart() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSessionByFinishIdxKey rhs ) {
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
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					if( ! getOptionalFinish().equals( rhs.getOptionalFinish() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSession rhs ) {
			if( getRequiredSecSessionId() != null ) {
				if( rhs.getRequiredSecSessionId() != null ) {
					if( ! getRequiredSecSessionId().equals( rhs.getRequiredSecSessionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSessionId() != null ) {
					return( false );
				}
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
			if( getRequiredStart() != null ) {
				if( rhs.getRequiredStart() != null ) {
					if( ! getRequiredStart().equals( rhs.getRequiredStart() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredStart() != null ) {
					return( false );
				}
			}
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					if( ! getOptionalFinish().equals( rhs.getOptionalFinish() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSessionH rhs ) {
			if( getRequiredSecSessionId() != null ) {
				if( rhs.getRequiredSecSessionId() != null ) {
					if( ! getRequiredSecSessionId().equals( rhs.getRequiredSecSessionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSessionId() != null ) {
					return( false );
				}
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
			if( getRequiredStart() != null ) {
				if( rhs.getRequiredStart() != null ) {
					if( ! getRequiredStart().equals( rhs.getRequiredStart() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredStart() != null ) {
					return( false );
				}
			}
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					if( ! getOptionalFinish().equals( rhs.getOptionalFinish() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSessionHPKey rhs ) {
			if( getRequiredSecSessionId() != null ) {
				if( rhs.getRequiredSecSessionId() != null ) {
					if( ! getRequiredSecSessionId().equals( rhs.getRequiredSecSessionId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecSessionId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSessionBySecUserIdxKey rhs ) {
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
		else if( obj instanceof ICFSecPubSecSessionByStartIdxKey rhs ) {
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
			if( getRequiredStart() != null ) {
				if( rhs.getRequiredStart() != null ) {
					if( ! getRequiredStart().equals( rhs.getRequiredStart() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredStart() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSecSessionByFinishIdxKey rhs ) {
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
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					if( ! getOptionalFinish().equals( rhs.getOptionalFinish() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
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
		hashCode = hashCode + getRequiredSecSessionId().hashCode();
		hashCode = hashCode + getRequiredSecUserId().hashCode();
		if( getRequiredStart() != null ) {
			hashCode = hashCode + getRequiredStart().hashCode();
		}
		if( getOptionalFinish() != null ) {
			hashCode = hashCode + getOptionalFinish().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecPubSecSession rhs ) {
			cmp = 0;
			if (getRequiredSecSessionId() != null) {
				if (rhs.getRequiredSecSessionId() != null) {
					cmp = getRequiredSecSessionId().compareTo( rhs.getRequiredSecSessionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSessionId() != null) {
				return( -1 );
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
			if (getRequiredStart() != null) {
				if (rhs.getRequiredStart() != null) {
					cmp = getRequiredStart().compareTo( rhs.getRequiredStart() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredStart() != null) {
				return( -1 );
			}
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					cmp = getOptionalFinish().compareTo( rhs.getOptionalFinish() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( -1 );
				}
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecPubSecSessionHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecSessionId() != null) {
				if (rhs.getRequiredSecSessionId() != null) {
					cmp = getRequiredSecSessionId().compareTo( rhs.getRequiredSecSessionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSessionId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionH rhs ) {
			cmp = 0;
			if (getRequiredSecSessionId() != null) {
				if (rhs.getRequiredSecSessionId() != null) {
					cmp = getRequiredSecSessionId().compareTo( rhs.getRequiredSecSessionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSessionId() != null) {
				return( -1 );
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
			if (getRequiredStart() != null) {
				if (rhs.getRequiredStart() != null) {
					cmp = getRequiredStart().compareTo( rhs.getRequiredStart() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredStart() != null) {
				return( -1 );
			}
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					cmp = getOptionalFinish().compareTo( rhs.getOptionalFinish() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionBySecUserIdxKey rhs ) {
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
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionByStartIdxKey rhs ) {
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
			if (getRequiredStart() != null) {
				if (rhs.getRequiredStart() != null) {
					cmp = getRequiredStart().compareTo( rhs.getRequiredStart() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredStart() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionByFinishIdxKey rhs ) {
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
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					cmp = getOptionalFinish().compareTo( rhs.getOptionalFinish() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSession rhs ) {
			cmp = 0;
			if (getRequiredSecSessionId() != null) {
				if (rhs.getRequiredSecSessionId() != null) {
					cmp = getRequiredSecSessionId().compareTo( rhs.getRequiredSecSessionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSessionId() != null) {
				return( -1 );
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
			if (getRequiredStart() != null) {
				if (rhs.getRequiredStart() != null) {
					cmp = getRequiredStart().compareTo( rhs.getRequiredStart() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredStart() != null) {
				return( -1 );
			}
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					cmp = getOptionalFinish().compareTo( rhs.getOptionalFinish() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredSecSessionId() != null) {
				if (rhs.getRequiredSecSessionId() != null) {
					cmp = getRequiredSecSessionId().compareTo( rhs.getRequiredSecSessionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSessionId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionH rhs ) {
			cmp = 0;
			if (getRequiredSecSessionId() != null) {
				if (rhs.getRequiredSecSessionId() != null) {
					cmp = getRequiredSecSessionId().compareTo( rhs.getRequiredSecSessionId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecSessionId() != null) {
				return( -1 );
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
			if (getRequiredStart() != null) {
				if (rhs.getRequiredStart() != null) {
					cmp = getRequiredStart().compareTo( rhs.getRequiredStart() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredStart() != null) {
				return( -1 );
			}
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					cmp = getOptionalFinish().compareTo( rhs.getOptionalFinish() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionBySecUserIdxKey rhs ) {
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
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionByStartIdxKey rhs ) {
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
			if (getRequiredStart() != null) {
				if (rhs.getRequiredStart() != null) {
					cmp = getRequiredStart().compareTo( rhs.getRequiredStart() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredStart() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSecSessionByFinishIdxKey rhs ) {
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
			if( getOptionalFinish() != null ) {
				if( rhs.getOptionalFinish() != null ) {
					cmp = getOptionalFinish().compareTo( rhs.getOptionalFinish() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalFinish() != null ) {
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
	public void set( ICFSecPubSecSession src ) {
		setJustPubSecSession( src );
	}

	@Override
	public void setSecSession( ICFSecPubSecSession src ) {
		setJustPubRequiredSecSessionId(src.getRequiredSecSessionId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubRequiredContainerSecUser(src.getRequiredContainerSecUser());
		setJustPubRequiredSecUserId(src.getRequiredSecUserId());
		setJustPubRequiredStart(src.getRequiredStart());
		setJustPubOptionalFinish(src.getOptionalFinish());
	}

	@Override
	public void set( ICFSecPubSecSessionH src ) {
		setJustPubSecSession( src );
	}

	@Override
	public void setSecSession( ICFSecPubSecSessionH src ) {
		setJustPubRequiredSecSessionId(src.getRequiredSecSessionId());
		setJustPubRequiredContainerSecUser(src.getRequiredContainerSecUser());
		setJustPubRequiredSecUserId(src.getRequiredSecUserId());
		setJustPubRequiredStart(src.getRequiredStart());
		setJustPubOptionalFinish(src.getOptionalFinish());
	}

	@Override
	public void set( ICFSecPubSecSession src ) {
		setJustPubSecSession( src );
	}

	@Override
	public void setSecSession( ICFSecPubSecSession src ) {
		setJustPubRequiredSecSessionId(src.getRequiredSecSessionId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubRequiredContainerSecUser(src.getRequiredContainerSecUser());
		setJustPubRequiredSecUserId(src.getRequiredSecUserId());
		setJustPubRequiredStart(src.getRequiredStart());
		setJustPubOptionalFinish(src.getOptionalFinish());
	}

	@Override
	public void set( ICFSecPubSecSessionH src ) {
		setJustPubSecSession( src );
	}

	@Override
	public void setSecSession( ICFSecPubSecSessionH src ) {
		setJustPubRequiredSecSessionId(src.getRequiredSecSessionId());
		setJustPubRequiredContainerSecUser(src.getRequiredContainerSecUser());
		setJustPubRequiredSecUserId(src.getRequiredSecUserId());
		setJustPubRequiredStart(src.getRequiredStart());
		setJustPubOptionalFinish(src.getOptionalFinish());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + getRequiredSecSessionId().toString() + "\"" + "\"" + getRequiredSecUserId().toString() + "\"" + "\"" + getRequiredStart().toString() + "\"" + ( ( getOptionalFinish() == null ) ? "null" : "\"" + getOptionalFinish().toString() + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecPubBuffSecSession" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
