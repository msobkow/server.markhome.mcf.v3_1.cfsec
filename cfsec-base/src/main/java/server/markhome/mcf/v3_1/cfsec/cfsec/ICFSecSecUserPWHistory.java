// Description: Java 25 interface for a SecUserPWHistory record declementation

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

package server.markhome.mcf.v3_1.cfsec.cfsec;

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

import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/**
 *	ICFSecSecUserPWHistory persistence instances have CodeVis Private, meaning that only the back-end code for the schema implementation can access it, and even the code specific user interface cannot.
 */
public interface ICFSecSecUserPWHistory
{
	public static final String S_SECUSERID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SECUSERID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SECUSERID_INIT_VALUE );
	public static final LocalDateTime PWSETSTAMP_INIT_VALUE = CFLibXmlUtil.parseTimestamp("2020-01-01T00:00:00");
	public static final LocalDateTime PWREPLACEDSTAMP_INIT_VALUE = CFLibXmlUtil.parseTimestamp("2020-01-01T00:00:00");
	public final static int CLASS_CODE = 0xa00d;
	public final static String S_CLASS_CODE = "a00d";

	public int getClassCode();

	public ICFSecSecUserPWHistoryPKey getPKey();
	public void setPKey(ICFSecSecUserPWHistoryPKey pkey );
	public ICFLibKeyHash256 getRequiredSecUserId();

	public void setRequiredSecUserId(ICFLibKeyHash256 value);

	public LocalDateTime getRequiredPWSetStamp();

	public void setRequiredPWSetStamp(LocalDateTime value);

	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public LocalDateTime getRequiredPWReplacedStamp();
	public void setRequiredPWReplacedStamp( LocalDateTime value );
	public String getRequiredPasswordHash();
	public void setRequiredPasswordHash( String value );
	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFSecSecUserPWHistory src );

	public void setSecUserPWHistory( ICFSecSecUserPWHistory src );

	public void set( ICFSecSecUserPWHistoryH src );

	public void setSecUserPWHistory( ICFSecSecUserPWHistoryH src );

	public String getXmlAttrFragment();

	public String toString();
}
