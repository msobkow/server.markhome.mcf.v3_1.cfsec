// Description: Java 25 interface for a SecClusGrpMemb record declementation

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
 *	ICFSecSecClusGrpMemb persistence instances have CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecSecClusGrpMemb
{
	public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
	public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final String S_SECCLUSGRPID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SECCLUSGRPID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SECCLUSGRPID_INIT_VALUE );
	public static final String LOGINID_INIT_VALUE = new String( "" );
	public final static int CLASS_CODE = 0xa013;
	public final static String S_CLASS_CODE = "a013";

	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();

	public void setCreatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getCreatedAt();

	public void setCreatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getUpdatedByUserId();

	public void setUpdatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getUpdatedAt();

	public void setUpdatedAt( LocalDateTime value );

	public ICFSecSecClusGrpMembPKey getPKey();
	public void setPKey(ICFSecSecClusGrpMembPKey pkey );
	public ICFSecSecClusGrp getRequiredContainerGroup();

	public void setRequiredContainerGroup(ICFLibKeyHash256 argSecClusGrpId);

	public void setRequiredContainerGroup(ICFSecSecClusGrp argObj);

	public void setRequiredContainerGroup(ICFSecProtSecClusGrp argObj);

	public ICFSecSecUser getRequiredParentUser();

	public void setRequiredParentUser(String argLoginId);

	public void setRequiredParentUser(ICFSecSecUser argObj);

	public void setRequiredParentUser(ICFSecProtSecUser argObj);

	public void setRequiredParentUser(ICFSecPubSecUser argObj);

	public ICFLibKeyHash256 getRequiredSecClusGrpId();

	public void setRequiredSecClusGrpId(ICFLibKeyHash256 value);

	public String getRequiredLoginId();

	public void setRequiredLoginId(String value);

	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFSecSecClusGrpMemb src );

	public void setSecClusGrpMemb( ICFSecSecClusGrpMemb src );

	public void set( ICFSecSecClusGrpMembH src );

	public void setSecClusGrpMemb( ICFSecSecClusGrpMembH src );

	public void set( ICFSecProtSecClusGrpMemb src );

	public void setSecClusGrpMemb( ICFSecProtSecClusGrpMemb src );

	public void set( ICFSecProtSecClusGrpMembH src );

	public void setSecClusGrpMemb( ICFSecProtSecClusGrpMembH src );

	public String getXmlAttrFragment();

	public String toString();
}
