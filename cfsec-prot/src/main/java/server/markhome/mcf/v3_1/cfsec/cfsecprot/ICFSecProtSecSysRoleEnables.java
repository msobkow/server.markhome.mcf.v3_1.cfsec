// Description: Java 25 protected interface for a SecSysRoleEnables record implementation

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
 *	ICFSecProtSecSysRoleEnables persistence instances have CodeVis Protected, meaning that any only user interfaces and application code specific to the defining schema can access it.
 */
public interface ICFSecProtSecSysRoleEnables
{
	public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
	public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final String S_SECSYSROLEID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SECSYSROLEID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SECSYSROLEID_INIT_VALUE );
	public static final String ENABLENAME_INIT_VALUE = new String( "" );
	public final static int CLASS_CODE = 0xa018;
	public final static String S_CLASS_CODE = "a018";

	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();

	public void setCreatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getCreatedAt();

	public void setCreatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getUpdatedByUserId();

	public void setUpdatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getUpdatedAt();

	public void setUpdatedAt( LocalDateTime value );

	public ICFSecProtSecSysRoleEnablesPKey getPKey();
	public void setPKey(ICFSecProtSecSysRoleEnablesPKey pkey );
	public ICFSecProtSecSysRole getRequiredContainerSysRole();

	public void setRequiredContainerSysRole(ICFLibKeyHash256 argSecSysRoleId);


	public void setRequiredContainerSysRole(ICFSecProtSecSysRole argObj);

	public ICFSecProtSecSysGrp getRequiredParentEnableGroup();

	public void setRequiredParentEnableGroup(String argEnableName);


	public void setRequiredParentEnableGroup(ICFSecProtSecSysGrp argObj);

	public ICFLibKeyHash256 getRequiredSecSysRoleId();

	public void setRequiredSecSysRoleId(ICFLibKeyHash256 value);

	public String getRequiredEnableName();

	public void setRequiredEnableName(String value);

	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFSecProtSecSysRoleEnables src );

	public void setSecSysRoleEnables( ICFSecProtSecSysRoleEnables src );

	public void set( ICFSecProtSecSysRoleEnablesH src );

	public void setSecSysRoleEnables( ICFSecProtSecSysRoleEnablesH src );

	public String getXmlAttrFragment();

	public String toString();
}
