// Description: Java 25 protected interface for a SysCluster record implementation

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
 *	ICFSecProtSysCluster persistence instances have CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFSecProtSysCluster
{
	public static final int SINGLETONID_MIN_VALUE = 1;
	public static final int SINGLETONID_MAX_VALUE = 1;
	public static final int SINGLETONID_INIT_VALUE = 1;
	public static final String S_CLUSTERID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 CLUSTERID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_CLUSTERID_INIT_VALUE );
	public final static int CLASS_CODE = 0xa01f;
	public final static String S_CLASS_CODE = "a01f";

	public int getClassCode();

	public Integer getPKey();
	public void setPKey(Integer requiredSingletonId);
	public int getRequiredSingletonId();
	public void setRequiredSingletonId( int value );
	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public ICFSecProtCluster getRequiredContainerCluster();

	public void setRequiredContainerCluster(ICFLibKeyHash256 argClusterId);


	public void setRequiredContainerCluster(ICFSecProtCluster argObj);

	public void setRequiredContainerCluster(ICFSecPubCluster argObj);

	public ICFLibKeyHash256 getRequiredClusterId();
	public void setRequiredClusterId( ICFLibKeyHash256 value );
	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFSecProtSysCluster src );

	public void setSysCluster( ICFSecProtSysCluster src );

	public void set( ICFSecProtSysClusterH src );

	public void setSysCluster( ICFSecProtSysClusterH src );

	public void set( ICFSecPubSysCluster src );

	public void setSysCluster( ICFSecPubSysCluster src );

	public void set( ICFSecPubSysClusterH src );

	public void setSysCluster( ICFSecPubSysClusterH src );

	public String getXmlAttrFragment();

	public String toString();
}
