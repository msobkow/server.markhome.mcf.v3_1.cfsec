// Description: Java 17 implJustPubementation of a ISOTZone buffer

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

public class CFSecPubBuffISOTZone
	implements ICFSecPubISOTZone, Comparable<Object>, Serializable
{
	protected short requiredISOTZoneId;
	protected int requiredRevision;
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected CFLibDbKeyHash256 createdBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected CFLibDbKeyHash256 updatedBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();
	protected String requiredIso8601;
	protected String requiredTZName;
	protected short requiredTZHourOffset;
	protected short requiredTZMinOffset;
	protected String requiredDescription;
	protected boolean requiredVisible;

	public CFSecPubBuffISOTZone() {
		requiredISOTZoneId = ICFSecPubISOTZone.ISOTZONEID_INIT_VALUE;
		requiredIso8601 = ICFSecPubISOTZone.ISO8601_INIT_VALUE;
		requiredTZName = ICFSecPubISOTZone.TZNAME_INIT_VALUE;
		requiredTZHourOffset = ICFSecPubISOTZone.TZHOUROFFSET_INIT_VALUE;
		requiredTZMinOffset = ICFSecPubISOTZone.TZMINOFFSET_INIT_VALUE;
		requiredDescription = ICFSecPubISOTZone.DESCRIPTION_INIT_VALUE;
		requiredVisible = ICFSecPubISOTZone.VISIBLE_INIT_VALUE;
	}

	@Override
	public Short getPKey() {
		return (requiredISOTZoneId);
	}

	@Override
	public void setPKey(Short requiredISOTZoneId) {
		if(requiredISOTZoneId != null) {
			this.requiredISOTZoneId = requiredISOTZoneId;
		}
	}

	@Override
	public short getRequiredISOTZoneId() {
		return(requiredISOTZoneId);
	}

	@Override
	public void setRequiredISOTZoneId( short value ) {
		if( value < ICFSecPubISOTZone.ISOTZONEID_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredISOTZoneId",
				1,
				"value",
				value,
				ICFSecPubISOTZone.ISOTZONEID_MIN_VALUE );
		}
		requiredISOTZoneId = value;
	}

	@Override
	public CFLibDbKeyHash256 getCreatedByUserId() {
		return( createdByUserId );
	}

	@Override
	public void setJustPubCreatedByUserId( CFLibDbKeyHash256 value ) {
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
	public CFLibDbKeyHash256 getUpdatedByUserId() {
		return( updatedByUserId );
	}

	@Override
	public void setJustPubUpdatedByUserId( CFLibDbKeyHash256 value ) {
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
		return( ICFSecPubISOTZone.CLASS_CODE );
	}

	@Override
	public String getRequiredIso8601() {
		return(requiredIso8601);
	}

	public void setRequiredIso8601( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredIso8601",
				1,
				"value" );
		}
		else if( value.length() > 6 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredIso8601",
				1,
				"value.length()",
				value.length(),
				6 );
		}
		requiredIso8601 = value;
	}

	@Override
	public String getRequiredTZName() {
		return(requiredTZName);
	}

	public void setRequiredTZName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredTZName",
				1,
				"value" );
		}
		else if( value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredTZName",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		requiredTZName = value;
	}

	@Override
	public short getRequiredTZHourOffset() {
		return(requiredTZHourOffset);
	}

	@Override
	public void setRequiredTZHourOffset( short value ) {
		if( value < ICFSecPubISOTZone.TZHOUROFFSET_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredTZHourOffset",
				1,
				"value",
				value,
				ICFSecPubISOTZone.TZHOUROFFSET_MIN_VALUE );
		}
		if( value > ICFSecPubISOTZone.TZHOUROFFSET_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredTZHourOffset",
				1,
				"value",
				value,
				ICFSecPubISOTZone.TZHOUROFFSET_MAX_VALUE );
		}
		requiredTZHourOffset = value;
	}

	@Override
	public short getRequiredTZMinOffset() {
		return(requiredTZMinOffset);
	}

	@Override
	public void setRequiredTZMinOffset( short value ) {
		if( value < ICFSecPubISOTZone.TZMINOFFSET_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredTZMinOffset",
				1,
				"value",
				value,
				ICFSecPubISOTZone.TZMINOFFSET_MIN_VALUE );
		}
		if( value > ICFSecPubISOTZone.TZMINOFFSET_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredTZMinOffset",
				1,
				"value",
				value,
				ICFSecPubISOTZone.TZMINOFFSET_MAX_VALUE );
		}
		requiredTZMinOffset = value;
	}

	@Override
	public String getRequiredDescription() {
		return(requiredDescription);
	}

	public void setRequiredDescription( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredDescription",
				1,
				"value" );
		}
		else if( value.length() > 128 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredDescription",
				1,
				"value.length()",
				value.length(),
				128 );
		}
		requiredDescription = value;
	}

	@Override
	public boolean getRequiredVisible() {
		return(requiredVisible);
	}

	public void setRequiredVisible( boolean value ) {
		requiredVisible = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecPubISOTZone rhs ) {
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
			if( getRequiredISOTZoneId() != rhs.getRequiredISOTZoneId() ) {
				return( false );
			}
			if( getRequiredIso8601() != null ) {
				if( rhs.getRequiredIso8601() != null ) {
					if( ! getRequiredIso8601().equals( rhs.getRequiredIso8601() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIso8601() != null ) {
					return( false );
				}
			}
			if( getRequiredTZName() != null ) {
				if( rhs.getRequiredTZName() != null ) {
					if( ! getRequiredTZName().equals( rhs.getRequiredTZName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTZName() != null ) {
					return( false );
				}
			}
			if( getRequiredTZHourOffset() != rhs.getRequiredTZHourOffset() ) {
				return( false );
			}
			if( getRequiredTZMinOffset() != rhs.getRequiredTZMinOffset() ) {
				return( false );
			}
			if( getRequiredDescription() != null ) {
				if( rhs.getRequiredDescription() != null ) {
					if( ! getRequiredDescription().equals( rhs.getRequiredDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredVisible() != rhs.getRequiredVisible() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneH rhs ) {
			if( getRequiredISOTZoneId() != rhs.getRequiredISOTZoneId() ) {
				return( false );
			}
			if( getRequiredIso8601() != null ) {
				if( rhs.getRequiredIso8601() != null ) {
					if( ! getRequiredIso8601().equals( rhs.getRequiredIso8601() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIso8601() != null ) {
					return( false );
				}
			}
			if( getRequiredTZName() != null ) {
				if( rhs.getRequiredTZName() != null ) {
					if( ! getRequiredTZName().equals( rhs.getRequiredTZName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTZName() != null ) {
					return( false );
				}
			}
			if( getRequiredTZHourOffset() != rhs.getRequiredTZHourOffset() ) {
				return( false );
			}
			if( getRequiredTZMinOffset() != rhs.getRequiredTZMinOffset() ) {
				return( false );
			}
			if( getRequiredDescription() != null ) {
				if( rhs.getRequiredDescription() != null ) {
					if( ! getRequiredDescription().equals( rhs.getRequiredDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredVisible() != rhs.getRequiredVisible() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneHPKey ) {
			ICFSecPubISOTZoneHPKey rhs = (ICFSecPubISOTZoneHPKey)obj;
			if( getRequiredISOTZoneId() != rhs.getRequiredISOTZoneId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneByOffsetIdxKey rhs ) {
			if( getRequiredTZHourOffset() != rhs.getRequiredTZHourOffset() ) {
				return( false );
			}
			if( getRequiredTZMinOffset() != rhs.getRequiredTZMinOffset() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneByUTZNameIdxKey rhs ) {
			if( getRequiredTZName() != null ) {
				if( rhs.getRequiredTZName() != null ) {
					if( ! getRequiredTZName().equals( rhs.getRequiredTZName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTZName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneByIso8601IdxKey rhs ) {
			if( getRequiredIso8601() != null ) {
				if( rhs.getRequiredIso8601() != null ) {
					if( ! getRequiredIso8601().equals( rhs.getRequiredIso8601() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIso8601() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZone rhs ) {
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
			if( getRequiredISOTZoneId() != rhs.getRequiredISOTZoneId() ) {
				return( false );
			}
			if( getRequiredIso8601() != null ) {
				if( rhs.getRequiredIso8601() != null ) {
					if( ! getRequiredIso8601().equals( rhs.getRequiredIso8601() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIso8601() != null ) {
					return( false );
				}
			}
			if( getRequiredTZName() != null ) {
				if( rhs.getRequiredTZName() != null ) {
					if( ! getRequiredTZName().equals( rhs.getRequiredTZName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTZName() != null ) {
					return( false );
				}
			}
			if( getRequiredTZHourOffset() != rhs.getRequiredTZHourOffset() ) {
				return( false );
			}
			if( getRequiredTZMinOffset() != rhs.getRequiredTZMinOffset() ) {
				return( false );
			}
			if( getRequiredDescription() != null ) {
				if( rhs.getRequiredDescription() != null ) {
					if( ! getRequiredDescription().equals( rhs.getRequiredDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredVisible() != rhs.getRequiredVisible() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneH rhs ) {
			if( getRequiredISOTZoneId() != rhs.getRequiredISOTZoneId() ) {
				return( false );
			}
			if( getRequiredIso8601() != null ) {
				if( rhs.getRequiredIso8601() != null ) {
					if( ! getRequiredIso8601().equals( rhs.getRequiredIso8601() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIso8601() != null ) {
					return( false );
				}
			}
			if( getRequiredTZName() != null ) {
				if( rhs.getRequiredTZName() != null ) {
					if( ! getRequiredTZName().equals( rhs.getRequiredTZName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTZName() != null ) {
					return( false );
				}
			}
			if( getRequiredTZHourOffset() != rhs.getRequiredTZHourOffset() ) {
				return( false );
			}
			if( getRequiredTZMinOffset() != rhs.getRequiredTZMinOffset() ) {
				return( false );
			}
			if( getRequiredDescription() != null ) {
				if( rhs.getRequiredDescription() != null ) {
					if( ! getRequiredDescription().equals( rhs.getRequiredDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredVisible() != rhs.getRequiredVisible() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneHPKey rhs ) {
			if( getRequiredISOTZoneId() != rhs.getRequiredISOTZoneId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneByOffsetIdxKey rhs ) {
			if( getRequiredTZHourOffset() != rhs.getRequiredTZHourOffset() ) {
				return( false );
			}
			if( getRequiredTZMinOffset() != rhs.getRequiredTZMinOffset() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneByUTZNameIdxKey rhs ) {
			if( getRequiredTZName() != null ) {
				if( rhs.getRequiredTZName() != null ) {
					if( ! getRequiredTZName().equals( rhs.getRequiredTZName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTZName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOTZoneByIso8601IdxKey rhs ) {
			if( getRequiredIso8601() != null ) {
				if( rhs.getRequiredIso8601() != null ) {
					if( ! getRequiredIso8601().equals( rhs.getRequiredIso8601() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIso8601() != null ) {
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
		hashCode = ( hashCode * 0x10000 ) + getRequiredISOTZoneId();
		if( getRequiredIso8601() != null ) {
			hashCode = hashCode + getRequiredIso8601().hashCode();
		}
		if( getRequiredTZName() != null ) {
			hashCode = hashCode + getRequiredTZName().hashCode();
		}
		hashCode = ( hashCode * 0x10000 ) + getRequiredTZHourOffset();
		hashCode = ( hashCode * 0x10000 ) + getRequiredTZMinOffset();
		if( getRequiredDescription() != null ) {
			hashCode = hashCode + getRequiredDescription().hashCode();
		}
		if( getRequiredVisible() ) {
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
		else if( obj instanceof ICFSecPubISOTZone rhs ) {
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
			if( getRequiredISOTZoneId() < rhs.getRequiredISOTZoneId() ) {
				return( -1 );
			}
			else if( getRequiredISOTZoneId() > rhs.getRequiredISOTZoneId() ) {
				return( 1 );
			}
			if (getRequiredIso8601() != null) {
				if (rhs.getRequiredIso8601() != null) {
					cmp = getRequiredIso8601().compareTo( rhs.getRequiredIso8601() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIso8601() != null) {
				return( -1 );
			}
			if (getRequiredTZName() != null) {
				if (rhs.getRequiredTZName() != null) {
					cmp = getRequiredTZName().compareTo( rhs.getRequiredTZName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTZName() != null) {
				return( -1 );
			}
			if( getRequiredTZHourOffset() < rhs.getRequiredTZHourOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZHourOffset() > rhs.getRequiredTZHourOffset() ) {
				return( 1 );
			}
			if( getRequiredTZMinOffset() < rhs.getRequiredTZMinOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZMinOffset() > rhs.getRequiredTZMinOffset() ) {
				return( 1 );
			}
			if (getRequiredDescription() != null) {
				if (rhs.getRequiredDescription() != null) {
					cmp = getRequiredDescription().compareTo( rhs.getRequiredDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDescription() != null) {
				return( -1 );
			}
			if( getRequiredVisible() ) {
				if( ! rhs.getRequiredVisible() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredVisible() ) {
					return( -1 );
				}
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecPubISOTZoneHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if( getRequiredISOTZoneId() < rhs.getRequiredISOTZoneId() ) {
				return( -1 );
			}
			else if( getRequiredISOTZoneId() > rhs.getRequiredISOTZoneId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneH rhs ) {
			cmp = 0;
			if( getRequiredISOTZoneId() < rhs.getRequiredISOTZoneId() ) {
				return( -1 );
			}
			else if( getRequiredISOTZoneId() > rhs.getRequiredISOTZoneId() ) {
				return( 1 );
			}
			if (getRequiredIso8601() != null) {
				if (rhs.getRequiredIso8601() != null) {
					cmp = getRequiredIso8601().compareTo( rhs.getRequiredIso8601() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIso8601() != null) {
				return( -1 );
			}
			if (getRequiredTZName() != null) {
				if (rhs.getRequiredTZName() != null) {
					cmp = getRequiredTZName().compareTo( rhs.getRequiredTZName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTZName() != null) {
				return( -1 );
			}
			if( getRequiredTZHourOffset() < rhs.getRequiredTZHourOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZHourOffset() > rhs.getRequiredTZHourOffset() ) {
				return( 1 );
			}
			if( getRequiredTZMinOffset() < rhs.getRequiredTZMinOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZMinOffset() > rhs.getRequiredTZMinOffset() ) {
				return( 1 );
			}
			if (getRequiredDescription() != null) {
				if (rhs.getRequiredDescription() != null) {
					cmp = getRequiredDescription().compareTo( rhs.getRequiredDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDescription() != null) {
				return( -1 );
			}
			if( getRequiredVisible() ) {
				if( ! rhs.getRequiredVisible() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredVisible() ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneByOffsetIdxKey rhs ) {
			if( getRequiredTZHourOffset() < rhs.getRequiredTZHourOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZHourOffset() > rhs.getRequiredTZHourOffset() ) {
				return( 1 );
			}
			if( getRequiredTZMinOffset() < rhs.getRequiredTZMinOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZMinOffset() > rhs.getRequiredTZMinOffset() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneByUTZNameIdxKey rhs ) {
			if (getRequiredTZName() != null) {
				if (rhs.getRequiredTZName() != null) {
					cmp = getRequiredTZName().compareTo( rhs.getRequiredTZName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTZName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneByIso8601IdxKey rhs ) {
			if (getRequiredIso8601() != null) {
				if (rhs.getRequiredIso8601() != null) {
					cmp = getRequiredIso8601().compareTo( rhs.getRequiredIso8601() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIso8601() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZone rhs ) {
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
			if( getRequiredISOTZoneId() < rhs.getRequiredISOTZoneId() ) {
				return( -1 );
			}
			else if( getRequiredISOTZoneId() > rhs.getRequiredISOTZoneId() ) {
				return( 1 );
			}
			if (getRequiredIso8601() != null) {
				if (rhs.getRequiredIso8601() != null) {
					cmp = getRequiredIso8601().compareTo( rhs.getRequiredIso8601() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIso8601() != null) {
				return( -1 );
			}
			if (getRequiredTZName() != null) {
				if (rhs.getRequiredTZName() != null) {
					cmp = getRequiredTZName().compareTo( rhs.getRequiredTZName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTZName() != null) {
				return( -1 );
			}
			if( getRequiredTZHourOffset() < rhs.getRequiredTZHourOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZHourOffset() > rhs.getRequiredTZHourOffset() ) {
				return( 1 );
			}
			if( getRequiredTZMinOffset() < rhs.getRequiredTZMinOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZMinOffset() > rhs.getRequiredTZMinOffset() ) {
				return( 1 );
			}
			if (getRequiredDescription() != null) {
				if (rhs.getRequiredDescription() != null) {
					cmp = getRequiredDescription().compareTo( rhs.getRequiredDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDescription() != null) {
				return( -1 );
			}
			if( getRequiredVisible() ) {
				if( ! rhs.getRequiredVisible() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredVisible() ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if( getRequiredISOTZoneId() < rhs.getRequiredISOTZoneId() ) {
				return( -1 );
			}
			else if( getRequiredISOTZoneId() > rhs.getRequiredISOTZoneId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneH rhs ) {
			cmp = 0;
			if( getRequiredISOTZoneId() < rhs.getRequiredISOTZoneId() ) {
				return( -1 );
			}
			else if( getRequiredISOTZoneId() > rhs.getRequiredISOTZoneId() ) {
				return( 1 );
			}
			if (getRequiredIso8601() != null) {
				if (rhs.getRequiredIso8601() != null) {
					cmp = getRequiredIso8601().compareTo( rhs.getRequiredIso8601() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIso8601() != null) {
				return( -1 );
			}
			if (getRequiredTZName() != null) {
				if (rhs.getRequiredTZName() != null) {
					cmp = getRequiredTZName().compareTo( rhs.getRequiredTZName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTZName() != null) {
				return( -1 );
			}
			if( getRequiredTZHourOffset() < rhs.getRequiredTZHourOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZHourOffset() > rhs.getRequiredTZHourOffset() ) {
				return( 1 );
			}
			if( getRequiredTZMinOffset() < rhs.getRequiredTZMinOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZMinOffset() > rhs.getRequiredTZMinOffset() ) {
				return( 1 );
			}
			if (getRequiredDescription() != null) {
				if (rhs.getRequiredDescription() != null) {
					cmp = getRequiredDescription().compareTo( rhs.getRequiredDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredDescription() != null) {
				return( -1 );
			}
			if( getRequiredVisible() ) {
				if( ! rhs.getRequiredVisible() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredVisible() ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneByOffsetIdxKey rhs ) {
			if( getRequiredTZHourOffset() < rhs.getRequiredTZHourOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZHourOffset() > rhs.getRequiredTZHourOffset() ) {
				return( 1 );
			}
			if( getRequiredTZMinOffset() < rhs.getRequiredTZMinOffset() ) {
				return( -1 );
			}
			else if( getRequiredTZMinOffset() > rhs.getRequiredTZMinOffset() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneByUTZNameIdxKey rhs ) {
			if (getRequiredTZName() != null) {
				if (rhs.getRequiredTZName() != null) {
					cmp = getRequiredTZName().compareTo( rhs.getRequiredTZName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTZName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOTZoneByIso8601IdxKey rhs ) {
			if (getRequiredIso8601() != null) {
				if (rhs.getRequiredIso8601() != null) {
					cmp = getRequiredIso8601().compareTo( rhs.getRequiredIso8601() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIso8601() != null) {
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
	public void set( ICFSecPubISOTZone src ) {
		setJustPubISOTZone( src );
	}

	@Override
	public void setISOTZone( ICFSecPubISOTZone src ) {
		setJustPubRequiredISOTZoneId(src.getRequiredISOTZoneId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubCreatedByUserId( src.getCreatedByUserId() );
		setJustPubCreatedAt( src.getCreatedAt() );
		setJustPubUpdatedByUserId( src.getUpdatedByUserId() );
		setJustPubUpdatedAt( src.getUpdatedAt() );
		setJustPubRequiredIso8601(src.getRequiredIso8601());
		setJustPubRequiredTZName(src.getRequiredTZName());
		setJustPubRequiredTZHourOffset(src.getRequiredTZHourOffset());
		setJustPubRequiredTZMinOffset(src.getRequiredTZMinOffset());
		setJustPubRequiredDescription(src.getRequiredDescription());
		setJustPubRequiredVisible(src.getRequiredVisible());
	}

	@Override
	public void set( ICFSecPubISOTZoneH src ) {
		setJustPubISOTZone( src );
	}

	@Override
	public void setISOTZone( ICFSecPubISOTZoneH src ) {
		setJustPubRequiredISOTZoneId(src.getRequiredISOTZoneId());
		setJustPubRequiredIso8601(src.getRequiredIso8601());
		setJustPubRequiredTZName(src.getRequiredTZName());
		setJustPubRequiredTZHourOffset(src.getRequiredTZHourOffset());
		setJustPubRequiredTZMinOffset(src.getRequiredTZMinOffset());
		setJustPubRequiredDescription(src.getRequiredDescription());
		setJustPubRequiredVisible(src.getRequiredVisible());
	}

	@Override
	public void set( ICFSecPubISOTZone src ) {
		setJustPubISOTZone( src );
	}

	@Override
	public void setISOTZone( ICFSecPubISOTZone src ) {
		setJustPubRequiredISOTZoneId(src.getRequiredISOTZoneId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubCreatedByUserId( src.getCreatedByUserId() );
		setJustPubCreatedAt( src.getCreatedAt() );
		setJustPubUpdatedByUserId( src.getUpdatedByUserId() );
		setJustPubUpdatedAt( src.getUpdatedAt() );
		setJustPubRequiredIso8601(src.getRequiredIso8601());
		setJustPubRequiredTZName(src.getRequiredTZName());
		setJustPubRequiredTZHourOffset(src.getRequiredTZHourOffset());
		setJustPubRequiredTZMinOffset(src.getRequiredTZMinOffset());
		setJustPubRequiredDescription(src.getRequiredDescription());
		setJustPubRequiredVisible(src.getRequiredVisible());
	}

	@Override
	public void set( ICFSecPubISOTZoneH src ) {
		setJustPubISOTZone( src );
	}

	@Override
	public void setISOTZone( ICFSecPubISOTZoneH src ) {
		setJustPubRequiredISOTZoneId(src.getRequiredISOTZoneId());
		setJustPubRequiredIso8601(src.getRequiredIso8601());
		setJustPubRequiredTZName(src.getRequiredTZName());
		setJustPubRequiredTZHourOffset(src.getRequiredTZHourOffset());
		setJustPubRequiredTZMinOffset(src.getRequiredTZMinOffset());
		setJustPubRequiredDescription(src.getRequiredDescription());
		setJustPubRequiredVisible(src.getRequiredVisible());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + Short.toString( getRequiredISOTZoneId() ) + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredIso8601() ) + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredTZName() ) + "\"" + "\"" + Short.toString( getRequiredTZHourOffset() ) + "\"" + "\"" + Short.toString( getRequiredTZMinOffset() ) + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredDescription() ) + "\"" + (( getRequiredVisible() ) ? "\"true\"" : "\"false\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecPubBuffISOTZone" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
