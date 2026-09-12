// Description: Java 25 public interface for a SecSession record implementation

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
 *	ICFSecPubSecSession persistence instances have CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFSecPubSecSession
{
	public static final String S_SECSESSIONID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SECSESSIONID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SECSESSIONID_INIT_VALUE );
	public static final String S_SECUSERID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SECUSERID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SECUSERID_INIT_VALUE );
	public static final LocalDateTime START_INIT_VALUE = CFLibXmlUtil.parseTimestamp("2020-01-01T00:00:00");
	public static final String S_SECPROXYID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SECPROXYID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SECPROXYID_INIT_VALUE );
	public final static int CLASS_CODE = 0xa01e;
	public final static String S_CLASS_CODE = "a01e";

	public int getClassCode();

	public ICFLibKeyHash256 getPKey();
	public void setPKey(ICFLibKeyHash256 requiredSecSessionId);
	public CFLibDbKeyHash256 getRequiredSecSessionId();
	public void setRequiredSecSessionId( CFLibDbKeyHash256 value );
	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public ICFSecPubSecUser getRequiredContainerSecUser();

	public void setRequiredContainerSecUser(ICFSecPubSecUser argObj);
	public void setRequiredContainerSecUser(ICFLibKeyHash256 argSecUserId);

	public CFLibDbKeyHash256 getRequiredSecUserId();
	public void setRequiredSecUserId( CFLibDbKeyHash256 value );
	public LocalDateTime getRequiredStart();
	public void setRequiredStart( LocalDateTime value );
	public LocalDateTime getOptionalFinish();
	public void setOptionalFinish( LocalDateTime value );
	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFSecPubSecSession src );

	public void setSecSession( ICFSecPubSecSession src );

	public void set( ICFSecPubSecSessionH src );

	public void setSecSession( ICFSecPubSecSessionH src );

	public String getXmlAttrFragment();

	public String toString();
}
