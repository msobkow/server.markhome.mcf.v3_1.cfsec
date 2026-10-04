// Description: Java 17 implJustPubementation of a ISOCtryLang buffer

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

public class CFSecPubBuffISOCtryLang
	implements ICFSecPubISOCtryLang, Comparable<Object>, Serializable
{
	protected ICFSecPubISOCtryLangPKey pkey = new CFSecPubBuffISOCtryLangPKey();
	protected int requiredRevision;
	protected MCFDbKeyHash256 createdByUserId = MCFDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected MCFDbKeyHash256 createdBySessionId = MCFDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected MCFDbKeyHash256 updatedByUserId = MCFDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected MCFDbKeyHash256 updatedBySessionId = MCFDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();

	public CFSecPubBuffISOCtryLang() {
		pkey = new CFSecPubBuffISOCtryLangPKey();
	}

	@Override
	public ICFSecPubISOCtryLangPKey getPKey() {
		return(pkey);
	}

	@Override
	public void setPKey(ICFSecPubISOCtryLangPKey pkey ) {
		if (pkey == null) {
			throw new MCFNullArgumentException(getClass(), "setPKey", 1, "pkey");
		}
		else {
			this.pkey = pkey;
		}
	}

	@Override
	public short getRequiredISOCtryId() {
		return(getPKey().getRequiredISOCtryId());
	}

	@Override
	public void setRequiredISOCtryId( short value ) {
		if( value < ICFSecPubISOCtryLang.ISOCTRYID_MIN_VALUE ) {
			throw new MCFArgumentUnderflowException( getClass(),
				"setRequiredISOCtryId",
				1,
				"value",
				value,
				ICFSecPubISOCtryLang.ISOCTRYID_MIN_VALUE );
		}
		getPKey().setRequiredISOCtryId(value);
	}

	@Override
	public short getRequiredISOLangId() {
		return(getPKey().getRequiredISOLangId());
	}

	@Override
	public void setRequiredISOLangId( short value ) {
		if( value < ICFSecPubISOCtryLang.ISOLANGID_MIN_VALUE ) {
			throw new MCFArgumentUnderflowException( getClass(),
				"setRequiredISOLangId",
				1,
				"value",
				value,
				ICFSecPubISOCtryLang.ISOLANGID_MIN_VALUE );
		}
		getPKey().setRequiredISOLangId(value);
	}

	@Override
	public ICFSecPubISOCtry getRequiredContainerCtry(short argISOCtryId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new MCFNullArgumentException(getClass(), "getRequiredContainerCtry", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubISOCtryTable targetTable = targetBackingCFSec.getTableISOCtry();
		if (targetTable == null) {
			throw new MCFNullArgumentException(getClass(), "getRequiredContainerCtry", 0, "ICFSecPubSchema.getBackingCFSec().getTableISOCtry()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredISOCtryId()));
	}

	@Override
	public void setRequiredContainerCtry(short argISOCtryId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredContainerCtry-args", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubISOCtryTable targetTable = targetBackingCFSec.getTableISOCtry();
		if (targetTable == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredContainerCtry", 0, "ICFSecSchema.getBackingCFSec().getTableISOCtry()");
		}
		ICFSecPubISOCtry found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argISOCtryId);
		if (found == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredContainerCtry-args", 0, "found");
		}
		else if (found instanceof ICFSecPubISOCtry) || (found instanceof ICFSecPubISOCtry)) {
			super.setRequiredContainerCtry(argISOCtryId);
		requiredISOCtryId = argISOCtryId;
		}
		else {
			throw new MCFUnsupportedClassException(getClass(), "setRequiredContainerCtry-args", "found", found, "ICFSecPubISOCtryICFSecPubISOCtry");
		}
	}

	@Override
	public void setRequiredContainerCtry(ICFSecPubISOCtry argObj) {

		if(argObj == null) {
			throw new MCFNullArgumentException(getClass(), "setContainerCtry", 1, "argObj");
		}
		else {
			setRequiredISOCtryId(argObj.getRequiredISOCtryId());
		}
	}

	@Override
	public ICFSecPubISOLang getRequiredParentLang(short argISOLangId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new MCFNullArgumentException(getClass(), "getRequiredParentLang", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubISOLangTable targetTable = targetBackingCFSec.getTableISOLang();
		if (targetTable == null) {
			throw new MCFNullArgumentException(getClass(), "getRequiredParentLang", 0, "ICFSecPubSchema.getBackingCFSec().getTableISOLang()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredISOLangId()));
	}

	@Override
	public void setRequiredParentLang(short argISOLangId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredParentLang-args", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubISOLangTable targetTable = targetBackingCFSec.getTableISOLang();
		if (targetTable == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredParentLang", 0, "ICFSecSchema.getBackingCFSec().getTableISOLang()");
		}
		ICFSecPubISOLang found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argISOLangId);
		if (found == null) {
			throw new MCFNullArgumentException(getClass(), "setRequiredParentLang-args", 0, "found");
		}
		else if (found instanceof ICFSecPubISOLang) || (found instanceof ICFSecPubISOLang)) {
			super.setRequiredParentLang(argISOLangId);
		requiredISOLangId = argISOLangId;
		}
		else {
			throw new MCFUnsupportedClassException(getClass(), "setRequiredParentLang-args", "found", found, "ICFSecPubISOLangICFSecPubISOLang");
		}
	}

	@Override
	public void setRequiredParentLang(ICFSecPubISOLang argObj) {

		if(argObj == null) {
			throw new MCFNullArgumentException(getClass(), "setParentLang", 1, "argObj");
		}
		else {
			setRequiredISOLangId(argObj.getRequiredISOLangId());
		}
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
		return( ICFSecPubISOCtryLang.CLASS_CODE );
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecPubISOCtryLang rhs ) {
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
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOLangId() != rhs.getRequiredISOLangId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLangH rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOLangId() != rhs.getRequiredISOLangId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLangHPKey ) {
			ICFSecPubISOCtryLangHPKey rhs = (ICFSecPubISOCtryLangHPKey)obj;
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOLangId() != rhs.getRequiredISOLangId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLangByCtryIdxKey rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLangByLangIdxKey rhs ) {
			if( getRequiredISOLangId() != rhs.getRequiredISOLangId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLang rhs ) {
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
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOLangId() != rhs.getRequiredISOLangId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLangH rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOLangId() != rhs.getRequiredISOLangId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLangHPKey rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOLangId() != rhs.getRequiredISOLangId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLangByCtryIdxKey rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryLangByLangIdxKey rhs ) {
			if( getRequiredISOLangId() != rhs.getRequiredISOLangId() ) {
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
		hashCode = ( hashCode * 0x10000 ) + getRequiredISOCtryId();
		hashCode = ( hashCode * 0x10000 ) + getRequiredISOLangId();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecPubISOCtryLang rhs ) {
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
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}
			if( getRequiredISOLangId() < rhs.getRequiredISOLangId() ) {
				return( -1 );
			}
			else if( getRequiredISOLangId() > rhs.getRequiredISOLangId() ) {
				return( 1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecPubISOCtryLangHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}
			if( getRequiredISOLangId() < rhs.getRequiredISOLangId() ) {
				return( -1 );
			}
			else if( getRequiredISOLangId() > rhs.getRequiredISOLangId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryLangH rhs ) {
			cmp = 0;
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}
			if( getRequiredISOLangId() < rhs.getRequiredISOLangId() ) {
				return( -1 );
			}
			else if( getRequiredISOLangId() > rhs.getRequiredISOLangId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryLangByCtryIdxKey rhs ) {
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryLangByLangIdxKey rhs ) {
			if( getRequiredISOLangId() < rhs.getRequiredISOLangId() ) {
				return( -1 );
			}
			else if( getRequiredISOLangId() > rhs.getRequiredISOLangId() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryLang rhs ) {
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
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}
			if( getRequiredISOLangId() < rhs.getRequiredISOLangId() ) {
				return( -1 );
			}
			else if( getRequiredISOLangId() > rhs.getRequiredISOLangId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryLangHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}
			if( getRequiredISOLangId() < rhs.getRequiredISOLangId() ) {
				return( -1 );
			}
			else if( getRequiredISOLangId() > rhs.getRequiredISOLangId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryLangH rhs ) {
			cmp = 0;
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}
			if( getRequiredISOLangId() < rhs.getRequiredISOLangId() ) {
				return( -1 );
			}
			else if( getRequiredISOLangId() > rhs.getRequiredISOLangId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryLangByCtryIdxKey rhs ) {
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryLangByLangIdxKey rhs ) {
			if( getRequiredISOLangId() < rhs.getRequiredISOLangId() ) {
				return( -1 );
			}
			else if( getRequiredISOLangId() > rhs.getRequiredISOLangId() ) {
				return( 1 );
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
	public void set( ICFSecPubISOCtryLang src ) {
		setJustPubISOCtryLang( src );
	}

	@Override
	public void setISOCtryLang( ICFSecPubISOCtryLang src ) {
		setJustPubRequiredContainerCtry(src.getRequiredContainerCtry());
		setJustPubRequiredParentLang(src.getRequiredParentLang());
		setJustPubRequiredISOCtryId(src.getRequiredISOCtryId());
		setJustPubRequiredISOLangId(src.getRequiredISOLangId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubCreatedByUserId( src.getCreatedByUserId() );
		setJustPubCreatedAt( src.getCreatedAt() );
		setJustPubUpdatedByUserId( src.getUpdatedByUserId() );
		setJustPubUpdatedAt( src.getUpdatedAt() );
	}

	@Override
	public void set( ICFSecPubISOCtryLangH src ) {
		setJustPubISOCtryLang( src );
	}

	@Override
	public void setISOCtryLang( ICFSecPubISOCtryLangH src ) {
		setJustPubRequiredContainerCtry(src.getRequiredContainerCtry());
		setJustPubRequiredParentLang(src.getRequiredParentLang());
		setJustPubRequiredISOCtryId(src.getRequiredISOCtryId());
		setJustPubRequiredISOLangId(src.getRequiredISOLangId());
	}

	@Override
	public void set( ICFSecPubISOCtryLang src ) {
		setJustPubISOCtryLang( src );
	}

	@Override
	public void setISOCtryLang( ICFSecPubISOCtryLang src ) {
		setJustPubRequiredContainerCtry(src.getRequiredContainerCtry());
		setJustPubRequiredParentLang(src.getRequiredParentLang());
		setJustPubRequiredISOCtryId(src.getRequiredISOCtryId());
		setJustPubRequiredISOLangId(src.getRequiredISOLangId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubCreatedByUserId( src.getCreatedByUserId() );
		setJustPubCreatedAt( src.getCreatedAt() );
		setJustPubUpdatedByUserId( src.getUpdatedByUserId() );
		setJustPubUpdatedAt( src.getUpdatedAt() );
	}

	@Override
	public void set( ICFSecPubISOCtryLangH src ) {
		setJustPubISOCtryLang( src );
	}

	@Override
	public void setISOCtryLang( ICFSecPubISOCtryLangH src ) {
		setJustPubRequiredContainerCtry(src.getRequiredContainerCtry());
		setJustPubRequiredParentLang(src.getRequiredParentLang());
		setJustPubRequiredISOCtryId(src.getRequiredISOCtryId());
		setJustPubRequiredISOLangId(src.getRequiredISOLangId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + Short.toString( getRequiredISOCtryId() ) + "\"" + "\"" + Short.toString( getRequiredISOLangId() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecPubBuffISOCtryLang" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
