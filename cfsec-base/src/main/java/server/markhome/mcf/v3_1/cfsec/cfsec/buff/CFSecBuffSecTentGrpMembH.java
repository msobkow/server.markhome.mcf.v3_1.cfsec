// Description: Java 25 implementation of a SecTentGrpMemb history buffer object

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;

public class CFSecBuffSecTentGrpMembH
    implements ICFSecSecTentGrpMembH, Comparable<Object>, Serializable
{
    protected CFSecBuffSecTentGrpMembHPKey pkey;
	protected MCFDbKeyHash256 createdByUserId = MCFDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected MCFDbKeyHash256 createdBySessionId = MCFDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected MCFDbKeyHash256 updatedByUserId = MCFDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected MCFDbKeyHash256 updatedBySessionId = MCFDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();

    public CFSecBuffSecTentGrpMembH() {
            // The primary key member attributes are initialized on construction
            pkey = new CFSecBuffSecTentGrpMembHPKey();
    }

    @Override
    public int getClassCode() {
            return( ICFSecSecTentGrpMemb.CLASS_CODE );
    }

    @Override
    public MCFDbKeyHash256 getCreatedByUserId() {
        return( createdByUserId );
    }

    @Override
    public void setCreatedByUserId( MCFDbKeyHash256 value ) {
        if (value == null || value.isNull()) {
            throw new MCFNullArgumentException(getClass(), "setCreatedByUserId", 1, "value");
        }
        createdByUserId = value;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return( createdAt );
    }

    @Override
    public void setCreatedAt( LocalDateTime value ) {
        if (value == null) {
            throw new MCFNullArgumentException(getClass(), "setCreatedAt", 1, "value");
        }
        createdAt = value;
    }

    @Override
    public MCFDbKeyHash256 getUpdatedByUserId() {
        return( updatedByUserId );
    }

    @Override
    public void setUpdatedByUserId( MCFDbKeyHash256 value ) {
        if (value == null || value.isNull()) {
            throw new MCFNullArgumentException(getClass(), "setUpdatedByUserId", 1, "value");
        }
        updatedByUserId = value;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return( updatedAt );
    }

    @Override
    public void setUpdatedAt( LocalDateTime value ) {
        if (value == null) {
            throw new MCFNullArgumentException(getClass(), "setUpdatedAt", 1, "value");
        }
        updatedAt = value;
    }

    @Override
    public ICFSecSecTentGrpMembHPKey getPKey() {
        return( pkey );
    }

    @Override
    public void setPKey( ICFSecSecTentGrpMembHPKey pkey ) {
        if (pkey != null) {
            if (pkey instanceof CFSecBuffSecTentGrpMembHPKey) {
                this.pkey = (CFSecBuffSecTentGrpMembHPKey)pkey;
            }
            else {
                throw new MCFUnsupportedClassException(getClass(), "setPKey", "pkey", pkey, "CFSecBuffSecTentGrpMembHPKey");
            }
        }
    }

    @Override
    public MCFDbKeyHash256 getAuditClusterId() {
        return pkey.getAuditClusterId();
    }

    @Override
    public void setAuditClusterId(MCFDbKeyHash256 auditClusterId) {
        pkey.setAuditClusterId(auditClusterId);
    }

    @Override
    public LocalDateTime getAuditStamp() {
        return pkey.getAuditStamp();
    }

    @Override
    public void setAuditStamp(LocalDateTime auditStamp) {
        pkey.setAuditStamp(auditStamp);
    }

    @Override
    public short getAuditActionId() {
        return pkey.getAuditActionId();
    }

    @Override
    public void setAuditActionId(short auditActionId) {
        pkey.setAuditActionId(auditActionId);
    }

    @Override
    public int getRequiredRevision() {
        return pkey.getRequiredRevision();
    }

    @Override
    public void setRequiredRevision(int revision) {
        pkey.setRequiredRevision(revision);
    }

    @Override
    public MCFDbKeyHash256 getAuditSessionId() {
        return pkey.getAuditSessionId();
    }

    @Override
    public void setAuditSessionId(MCFDbKeyHash256 auditSessionId) {
        pkey.setAuditSessionId(auditSessionId);
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
    public boolean equals( Object obj ) {
        if (obj == null) {
            return( false );
        }
        else if (obj instanceof ICFSecSecTentGrpMemb) {
            ICFSecSecTentGrpMemb rhs = (ICFSecSecTentGrpMemb)obj;
		if (getPKey() != null) {
			if (rhs.getPKey() != null) {
				if (!getPKey().equals(rhs.getPKey())) {
					return( false );
				}
			}
			else {
				return( false );
			}
		}
		else if (rhs.getPKey() != null) {
			return( false );
		}

            return( true );
        }
        else if (obj instanceof ICFSecSecTentGrpMembH) {
            ICFSecSecTentGrpMembH rhs = (ICFSecSecTentGrpMembH)obj;
		if (getPKey() != null) {
			if (rhs.getPKey() != null) {
				if (!getPKey().equals(rhs.getPKey())) {
					return( false );
				}
			}
			else {
				return( false );
			}
		}
		else if (rhs.getPKey() != null) {
			return( false );
		}

            return( true );
        }
        else if (obj instanceof ICFSecSecTentGrpMembHPKey) {
		ICFSecSecTentGrpMembHPKey rhs = (ICFSecSecTentGrpMembHPKey)obj;
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
        else if (obj instanceof ICFSecSecTentGrpMembByTentGrpIdxKey) {
            ICFSecSecTentGrpMembByTentGrpIdxKey rhs = (ICFSecSecTentGrpMembByTentGrpIdxKey)obj;
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
        else if (obj instanceof ICFSecSecTentGrpMembByUserIdxKey) {
            ICFSecSecTentGrpMembByUserIdxKey rhs = (ICFSecSecTentGrpMembByUserIdxKey)obj;
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
			return( false );
        }
    }
    
    @Override
    public int hashCode() {
        int hashCode = pkey.hashCode();
        return( hashCode & 0x7fffffff );
    }

    @Override
    public int compareTo( Object obj ) {
        int cmp;
        if (obj == null) {
            return( 1 );
        }
        else if (obj instanceof ICFSecSecTentGrpMemb) {
		ICFSecSecTentGrpMemb rhs = (ICFSecSecTentGrpMemb)obj;
		if (getPKey() != null) {
			if (rhs.getPKey() == null) {
				return( 1 );
			}
			else {
				cmp = getPKey().compareTo(rhs.getPKey());
				if (cmp != 0) {
					return( cmp );
				}
			}
		}
		else {
			if (rhs.getPKey() != null) {
				return( -1 );
			}
		}
            return( 0 );
        }
        else if (obj instanceof ICFSecSecTentGrpMembHPKey) {
        if (getPKey() != null) {
            return( getPKey().compareTo( obj ));
        }
        else {
            return( -1 );
        }
        }
        else if (obj instanceof ICFSecSecTentGrpMembH) {
		ICFSecSecTentGrpMembH rhs = (ICFSecSecTentGrpMembH)obj;
		if (getPKey() != null) {
			if (rhs.getPKey() == null) {
				return( 1 );
			}
			else {
				cmp = getPKey().compareTo(rhs.getPKey());
				if (cmp != 0) {
					return( cmp );
				}
			}
		}
		else {
			if (rhs.getPKey() != null) {
				return( -1 );
			}
		}
            return( 0 );
        }
        else if (obj instanceof ICFSecSecTentGrpMembByTentGrpIdxKey ) {
            ICFSecSecTentGrpMembByTentGrpIdxKey rhs = (ICFSecSecTentGrpMembByTentGrpIdxKey)obj;
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
            return( 0 );
        }
        else if (obj instanceof ICFSecSecTentGrpMembByUserIdxKey ) {
            ICFSecSecTentGrpMembByUserIdxKey rhs = (ICFSecSecTentGrpMembByUserIdxKey)obj;
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
        else {
            throw new MCFUnsupportedClassException( getClass(),
                "compareTo",
                "obj",
                obj,
                null );
        }
    }
	@Override
    public void set( ICFSecSecTentGrpMemb src ) {
		setSecTentGrpMemb( src );
    }

	@Override
    public void setSecTentGrpMemb( ICFSecSecTentGrpMemb src ) {
		setRequiredSecTentGrpId( src.getRequiredSecTentGrpId() );
		setRequiredLoginId( src.getRequiredLoginId() );
		setRequiredRevision( src.getRequiredRevision() );
    }

	@Override
    public void set( ICFSecSecTentGrpMembH src ) {
		setSecTentGrpMemb( src );
    }

	@Override
    public void setSecTentGrpMemb( ICFSecSecTentGrpMembH src ) {
		setRequiredSecTentGrpId( src.getRequiredSecTentGrpId() );
		setRequiredLoginId( src.getRequiredLoginId() );
		setRequiredRevision( src.getRequiredRevision() );
    }

    public String getXmlAttrFragment() {
        String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"";
        return( ret );
    }

    public String toString() {
        String ret = "<CFSecBuffSecTentGrpMembH" + getXmlAttrFragment() + "/>";
        return( ret );
    }
}
