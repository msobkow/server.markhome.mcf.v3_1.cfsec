// Description: Java 25 public interface for a SecUser record implementation

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

package server.markhome.mcf.v3_1.cfsec.cfsecpub;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
//import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;

/**
 *	ICFSecPubSecUser persistence instances have CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFSecPubSecUser
{
	public static final ICFSecPubSchema.SecAccountStatusEnum ACCOUNTSTATUS_MIN_VALUE = ICFSecPubSchema.SecAccountStatusEnum.System;
	public static final ICFSecPubSchema.SecAccountStatusEnum ACCOUNTSTATUS_MAX_VALUE = ICFSecPubSchema.SecAccountStatusEnum.Locked;
	public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
	public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final String S_SECUSERID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SECUSERID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SECUSERID_INIT_VALUE );
	public static final String LOGINID_INIT_VALUE = new String( "" );
	public static final ICFSecPubSchema.SecAccountStatusEnum ACCOUNTSTATUS_INIT_VALUE = ICFSecPubSchema.ordinalToSecAccountStatusEnum( 5 );
	public static final String DFLTSYSGRPNAME_INIT_VALUE = new String( "" );
	public static final String DFLTCLUSGRPNAME_INIT_VALUE = new String( "" );
	public static final String DFLTTENTGRPNAME_INIT_VALUE = new String( "" );
	public static final String EMAILADDRESS_INIT_VALUE = new String( "" );
	public final static int CLASS_CODE = 0xa009;
	public final static String S_CLASS_CODE = "a009";

	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();

	public void setCreatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getCreatedAt();

	public void setCreatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getUpdatedByUserId();

	public void setUpdatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getUpdatedAt();

	public void setUpdatedAt( LocalDateTime value );

	public ICFLibKeyHash256 getPKey();
	public void setPKey(ICFLibKeyHash256 requiredSecUserId);
	public List<ICFSecPubSecSession> getOptionalComponentsSecSess();

	public CFLibDbKeyHash256 getRequiredSecUserId();
	public void setRequiredSecUserId( CFLibDbKeyHash256 value );
	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public String getRequiredLoginId();
	public void setRequiredLoginId( String value );
	public ICFSecPubSchema.SecAccountStatusEnum getRequiredAccountStatus();
	public void setRequiredAccountStatus( ICFSecPubSchema.SecAccountStatusEnum value );
	public String getOptionalDfltSysGrpName();
	public void setOptionalDfltSysGrpName( String value );
	public String getOptionalDfltClusGrpName();
	public void setOptionalDfltClusGrpName( String value );
	public String getOptionalDfltTentGrpName();
	public void setOptionalDfltTentGrpName( String value );
	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFSecPubSecUser src );

	public void setSecUser( ICFSecPubSecUser src );

	public void set( ICFSecPubSecUserH src );

	public void setSecUser( ICFSecPubSecUserH src );

	public String getXmlAttrFragment();

	public String toString();
}
