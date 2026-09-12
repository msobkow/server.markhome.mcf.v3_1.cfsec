// Description: Java 25 protected interface for a ISOCtryCcy record implementation

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
 *	ICFSecProtISOCtryCcy persistence instances have CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFSecProtISOCtryCcy
{
	public static final short ISOCTRYID_MIN_VALUE = (short)0;
	public static final short ISOCCYID_MIN_VALUE = (short)0;
	public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
	public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final short ISOCTRYID_INIT_VALUE = (short)0;
	public static final short ISOCCYID_INIT_VALUE = (short)0;
	public final static int CLASS_CODE = 0xa005;
	public final static String S_CLASS_CODE = "a005";

	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();

	public void setCreatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getCreatedAt();

	public void setCreatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getUpdatedByUserId();

	public void setUpdatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getUpdatedAt();

	public void setUpdatedAt( LocalDateTime value );

	public ICFSecProtISOCtryCcyPKey getPKey();
	public void setPKey(ICFSecProtISOCtryCcyPKey pkey );
	public ICFSecProtISOCtry getRequiredContainerCtry();

	public void setRequiredContainerCtry(short argISOCtryId);


	public void setRequiredContainerCtry(ICFSecProtISOCtry argObj);

	public void setRequiredContainerCtry(ICFSecPubISOCtry argObj);

	public ICFSecProtISOCcy getRequiredParentCcy();

	public void setRequiredParentCcy(short argISOCcyId);


	public void setRequiredParentCcy(ICFSecProtISOCcy argObj);

	public void setRequiredParentCcy(ICFSecPubISOCcy argObj);

	public short getRequiredISOCtryId();

	public void setRequiredISOCtryId(short value);

	public short getRequiredISOCcyId();

	public void setRequiredISOCcyId(short value);

	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFSecProtISOCtryCcy src );

	public void setISOCtryCcy( ICFSecProtISOCtryCcy src );

	public void set( ICFSecProtISOCtryCcyH src );

	public void setISOCtryCcy( ICFSecProtISOCtryCcyH src );

	public void set( ICFSecPubISOCtryCcy src );

	public void setISOCtryCcy( ICFSecPubISOCtryCcy src );

	public void set( ICFSecPubISOCtryCcyH src );

	public void setISOCtryCcy( ICFSecPubISOCtryCcyH src );

	public String getXmlAttrFragment();

	public String toString();
}
