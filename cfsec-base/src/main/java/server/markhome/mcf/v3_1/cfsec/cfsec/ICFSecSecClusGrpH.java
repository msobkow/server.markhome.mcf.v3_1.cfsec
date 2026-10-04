// Description: Java 25 interface for a SecClusGrp history object

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
import server.markhome.mcf.v3_1.cflib.xml.MCFXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/**
 *	ICFSecSecClusGrpH provides access to history records matching the CFSecSecClusGrp object change history.
 */
public interface ICFSecSecClusGrpH
{
	public int getClassCode();

	public MCFDbKeyHash256 getCreatedByUserId();
	public void setCreatedByUserId( MCFDbKeyHash256 value );
	public LocalDateTime getCreatedAt();
	public void setCreatedAt( LocalDateTime value );
	public MCFDbKeyHash256 getUpdatedByUserId();
	public void setUpdatedByUserId( MCFDbKeyHash256 value );
	public LocalDateTime getUpdatedAt();
	public void setUpdatedAt( LocalDateTime value );

	public ICFSecSecClusGrpHPKey getPKey();
	public void setPKey( ICFSecSecClusGrpHPKey pkey );
	public MCFDbKeyHash256 getAuditClusterId();
	public void setAuditClusterId(MCFDbKeyHash256 auditClusterId);
	public LocalDateTime getAuditStamp();
	public void setAuditStamp(LocalDateTime auditStamp);
	public short getAuditActionId();
	public void setAuditActionId(short auditActionId);
	public int getRequiredRevision();
	public void setRequiredRevision(int revision);
	public MCFDbKeyHash256 getAuditSessionId();
	public void setAuditSessionId(MCFDbKeyHash256 auditSessionId);

	public IMCFKeyHash256 getRequiredSecClusGrpId();
	public void setRequiredSecClusGrpId( IMCFKeyHash256 requiredSecClusGrpId );

	public IMCFKeyHash256 getRequiredClusterId();
	public void setRequiredClusterId( IMCFKeyHash256 value );
	public String getRequiredName();
	public void setRequiredName( String value );
	@Override
	public boolean equals( Object obj );

	@Override
	public int hashCode();

	//@Override
	public int compareTo( Object obj );

	public void set( ICFSecSecClusGrp src );
	public void set( ICFSecSecClusGrpH src );
	public void setSecClusGrp( ICFSecSecClusGrp src );
	public void setSecClusGrp( ICFSecSecClusGrpH src );
	public String getXmlAttrFragment();

	@Override
	public String toString();
}
