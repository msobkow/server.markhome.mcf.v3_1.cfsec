// Description: Java 17 implJustPubementation of a ISOCtryCcy buffer

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

public class CFSecPubBuffISOCtryCcy
	implements ICFSecPubISOCtryCcy, Comparable<Object>, Serializable
{
	protected ICFSecPubISOCtryCcyPKey pkey = new CFSecPubBuffISOCtryCcyPKey();
	protected int requiredRevision;
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_CREATED_BY);
	protected CFLibDbKeyHash256 createdBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFSecPubSecUser.S_INIT_UPDATED_BY);
	protected CFLibDbKeyHash256 updatedBySessionId = CFLibDbKeyHash256.fromHex(ICFSecPubSecSession.S_SECSESSIONID_INIT_VALUE);
	protected LocalDateTime updatedAt = LocalDateTime.now();

	public CFSecPubBuffISOCtryCcy() {
		pkey = new CFSecPubBuffISOCtryCcyPKey();
	}

	@Override
	public ICFSecPubISOCtryCcyPKey getPKey() {
		return(pkey);
	}

	@Override
	public void setPKey(ICFSecPubISOCtryCcyPKey pkey ) {
		if (pkey == null) {
			throw new CFLibNullArgumentException(getClass(), "setPKey", 1, "pkey");
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
		if( value < ICFSecPubISOCtryCcy.ISOCTRYID_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredISOCtryId",
				1,
				"value",
				value,
				ICFSecPubISOCtryCcy.ISOCTRYID_MIN_VALUE );
		}
		getPKey().setRequiredISOCtryId(value);
	}

	@Override
	public short getRequiredISOCcyId() {
		return(getPKey().getRequiredISOCcyId());
	}

	@Override
	public void setRequiredISOCcyId( short value ) {
		if( value < ICFSecPubISOCtryCcy.ISOCCYID_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredISOCcyId",
				1,
				"value",
				value,
				ICFSecPubISOCtryCcy.ISOCCYID_MIN_VALUE );
		}
		getPKey().setRequiredISOCcyId(value);
	}

	@Override
	public ICFSecPubISOCtry getRequiredContainerCtry(short argISOCtryId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerCtry", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubISOCtryTable targetTable = targetBackingCFSec.getTableISOCtry();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerCtry", 0, "ICFSecPubSchema.getBackingCFSec().getTableISOCtry()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredISOCtryId()));
	}

	@Override
	public void setRequiredContainerCtry(short argISOCtryId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerCtry-args", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubISOCtryTable targetTable = targetBackingCFSec.getTableISOCtry();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerCtry", 0, "ICFSecSchema.getBackingCFSec().getTableISOCtry()");
		}
		ICFSecPubISOCtry found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argISOCtryId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerCtry-args", 0, "found");
		}
		else if (found instanceof ICFSecPubISOCtry) || (found instanceof ICFSecPubISOCtry)) {
			super.setRequiredContainerCtry(argISOCtryId);
		requiredISOCtryId = argISOCtryId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredContainerCtry-args", "found", found, "ICFSecPubISOCtryICFSecPubISOCtry");
		}
	}

	@Override
	public void setRequiredContainerCtry(ICFSecPubISOCtry argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerCtry", 1, "argObj");
		}
		else {
			setRequiredISOCtryId(argObj.getRequiredISOCtryId());
		}
	}

	@Override
	public ICFSecPubISOCcy getRequiredParentCcy(short argISOCcyId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredParentCcy", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubISOCcyTable targetTable = targetBackingCFSec.getTableISOCcy();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredParentCcy", 0, "ICFSecPubSchema.getBackingCFSec().getTableISOCcy()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredISOCcyId()));
	}

	@Override
	public void setRequiredParentCcy(short argISOCcyId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredParentCcy-args", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubISOCcyTable targetTable = targetBackingCFSec.getTableISOCcy();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredParentCcy", 0, "ICFSecSchema.getBackingCFSec().getTableISOCcy()");
		}
		ICFSecPubISOCcy found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argISOCcyId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredParentCcy-args", 0, "found");
		}
		else if (found instanceof ICFSecPubISOCcy) || (found instanceof ICFSecPubISOCcy)) {
			super.setRequiredParentCcy(argISOCcyId);
		requiredISOCcyId = argISOCcyId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredParentCcy-args", "found", found, "ICFSecPubISOCcyICFSecPubISOCcy");
		}
	}

	@Override
	public void setRequiredParentCcy(ICFSecPubISOCcy argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setParentCcy", 1, "argObj");
		}
		else {
			setRequiredISOCcyId(argObj.getRequiredISOCcyId());
		}
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
		return( ICFSecPubISOCtryCcy.CLASS_CODE );
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecPubISOCtryCcy rhs ) {
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
			if( getRequiredISOCcyId() != rhs.getRequiredISOCcyId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyH rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOCcyId() != rhs.getRequiredISOCcyId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyHPKey ) {
			ICFSecPubISOCtryCcyHPKey rhs = (ICFSecPubISOCtryCcyHPKey)obj;
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOCcyId() != rhs.getRequiredISOCcyId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyByCtryIdxKey rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyByCcyIdxKey rhs ) {
			if( getRequiredISOCcyId() != rhs.getRequiredISOCcyId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcy rhs ) {
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
			if( getRequiredISOCcyId() != rhs.getRequiredISOCcyId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyH rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOCcyId() != rhs.getRequiredISOCcyId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyHPKey rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			if( getRequiredISOCcyId() != rhs.getRequiredISOCcyId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyByCtryIdxKey rhs ) {
			if( getRequiredISOCtryId() != rhs.getRequiredISOCtryId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyByCcyIdxKey rhs ) {
			if( getRequiredISOCcyId() != rhs.getRequiredISOCcyId() ) {
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
		hashCode = ( hashCode * 0x10000 ) + getRequiredISOCcyId();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcy rhs ) {
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
			if( getRequiredISOCcyId() < rhs.getRequiredISOCcyId() ) {
				return( -1 );
			}
			else if( getRequiredISOCcyId() > rhs.getRequiredISOCcyId() ) {
				return( 1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecPubISOCtryCcyHPKey rhs ) {
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
			if( getRequiredISOCcyId() < rhs.getRequiredISOCcyId() ) {
				return( -1 );
			}
			else if( getRequiredISOCcyId() > rhs.getRequiredISOCcyId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyH rhs ) {
			cmp = 0;
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}
			if( getRequiredISOCcyId() < rhs.getRequiredISOCcyId() ) {
				return( -1 );
			}
			else if( getRequiredISOCcyId() > rhs.getRequiredISOCcyId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyByCtryIdxKey rhs ) {
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyByCcyIdxKey rhs ) {
			if( getRequiredISOCcyId() < rhs.getRequiredISOCcyId() ) {
				return( -1 );
			}
			else if( getRequiredISOCcyId() > rhs.getRequiredISOCcyId() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcy rhs ) {
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
			if( getRequiredISOCcyId() < rhs.getRequiredISOCcyId() ) {
				return( -1 );
			}
			else if( getRequiredISOCcyId() > rhs.getRequiredISOCcyId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyHPKey rhs ) {
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
			if( getRequiredISOCcyId() < rhs.getRequiredISOCcyId() ) {
				return( -1 );
			}
			else if( getRequiredISOCcyId() > rhs.getRequiredISOCcyId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyH rhs ) {
			cmp = 0;
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}
			if( getRequiredISOCcyId() < rhs.getRequiredISOCcyId() ) {
				return( -1 );
			}
			else if( getRequiredISOCcyId() > rhs.getRequiredISOCcyId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyByCtryIdxKey rhs ) {
			if( getRequiredISOCtryId() < rhs.getRequiredISOCtryId() ) {
				return( -1 );
			}
			else if( getRequiredISOCtryId() > rhs.getRequiredISOCtryId() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubISOCtryCcyByCcyIdxKey rhs ) {
			if( getRequiredISOCcyId() < rhs.getRequiredISOCcyId() ) {
				return( -1 );
			}
			else if( getRequiredISOCcyId() > rhs.getRequiredISOCcyId() ) {
				return( 1 );
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
	public void set( ICFSecPubISOCtryCcy src ) {
		setJustPubISOCtryCcy( src );
	}

	@Override
	public void setISOCtryCcy( ICFSecPubISOCtryCcy src ) {
		setJustPubRequiredContainerCtry(src.getRequiredContainerCtry());
		setJustPubRequiredParentCcy(src.getRequiredParentCcy());
		setJustPubRequiredISOCtryId(src.getRequiredISOCtryId());
		setJustPubRequiredISOCcyId(src.getRequiredISOCcyId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubCreatedByUserId( src.getCreatedByUserId() );
		setJustPubCreatedAt( src.getCreatedAt() );
		setJustPubUpdatedByUserId( src.getUpdatedByUserId() );
		setJustPubUpdatedAt( src.getUpdatedAt() );
	}

	@Override
	public void set( ICFSecPubISOCtryCcyH src ) {
		setJustPubISOCtryCcy( src );
	}

	@Override
	public void setISOCtryCcy( ICFSecPubISOCtryCcyH src ) {
		setJustPubRequiredContainerCtry(src.getRequiredContainerCtry());
		setJustPubRequiredParentCcy(src.getRequiredParentCcy());
		setJustPubRequiredISOCtryId(src.getRequiredISOCtryId());
		setJustPubRequiredISOCcyId(src.getRequiredISOCcyId());
	}

	@Override
	public void set( ICFSecPubISOCtryCcy src ) {
		setJustPubISOCtryCcy( src );
	}

	@Override
	public void setISOCtryCcy( ICFSecPubISOCtryCcy src ) {
		setJustPubRequiredContainerCtry(src.getRequiredContainerCtry());
		setJustPubRequiredParentCcy(src.getRequiredParentCcy());
		setJustPubRequiredISOCtryId(src.getRequiredISOCtryId());
		setJustPubRequiredISOCcyId(src.getRequiredISOCcyId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubCreatedByUserId( src.getCreatedByUserId() );
		setJustPubCreatedAt( src.getCreatedAt() );
		setJustPubUpdatedByUserId( src.getUpdatedByUserId() );
		setJustPubUpdatedAt( src.getUpdatedAt() );
	}

	@Override
	public void set( ICFSecPubISOCtryCcyH src ) {
		setJustPubISOCtryCcy( src );
	}

	@Override
	public void setISOCtryCcy( ICFSecPubISOCtryCcyH src ) {
		setJustPubRequiredContainerCtry(src.getRequiredContainerCtry());
		setJustPubRequiredParentCcy(src.getRequiredParentCcy());
		setJustPubRequiredISOCtryId(src.getRequiredISOCtryId());
		setJustPubRequiredISOCcyId(src.getRequiredISOCcyId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + Short.toString( getRequiredISOCtryId() ) + "\"" + "\"" + Short.toString( getRequiredISOCcyId() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecPubBuffISOCtryCcy" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
