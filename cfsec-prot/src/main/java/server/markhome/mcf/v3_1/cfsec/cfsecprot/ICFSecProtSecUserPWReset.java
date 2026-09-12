// Description: Java 25 protected interface for a SecUserPWReset record implementation

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

package server.markhome.mcf.v3_1.cfsec.cfsecprot;

import java.io.Serializable;
import java.math.*;
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
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/**
 *	ICFSecProtSecUserPWReset persistence instances have CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecProtSecUserPWReset
{
	public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
	public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final String S_SECUSERID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SECUSERID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SECUSERID_INIT_VALUE );
	public static final String SENTTOEMAILADDR_INIT_VALUE = new String( "" );
	public final static boolean NEWACCOUNT_INIT_VALUE = false;
	public final static int CLASS_CODE = 0xa00c;
	public final static String S_CLASS_CODE = "a00c";

	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();

	public void setCreatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getCreatedAt();

	public void setCreatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getUpdatedByUserId();

	public void setUpdatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getUpdatedAt();

	public void setUpdatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getPKey();
	public void setPKey(CFLibDbKeyHash256 requiredSecUserId);
	public ICFSecProtSecUser getRequiredContainerUser();

	public void setRequiredContainerUser(ICFLibKeyHash256 argSecUserId);


	public void setRequiredContainerUser(ICFSecProtSecUser argObj);

	public ICFLibKeyHash256 getRequiredSecUserId();
	public void setRequiredSecUserId( ICFLibKeyHash256 value );
	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public String getRequiredSentToEMailAddr();
	public void setRequiredSentToEMailAddr( String value );
	public ICFLibUuid6 getRequiredPasswordResetUuid6();
	public void setRequiredPasswordResetUuid6( ICFLibUuid6 value );
	public boolean getRequiredNewAccount();
	public void setRequiredNewAccount( boolean value );
	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFSecProtSecUserPWReset src );

	public void setSecUserPWReset( ICFSecProtSecUserPWReset src );

	public void set( ICFSecProtSecUserPWResetH src );

	public void setSecUserPWReset( ICFSecProtSecUserPWResetH src );

	public String getXmlAttrFragment();

	public String toString();
}
