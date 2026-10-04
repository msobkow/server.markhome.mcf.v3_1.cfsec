// Description: Java 25 public interface for a ISOCcy history object

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
import server.markhome.mcf.v3_1.cflib.xml.MCFXmlUtil;
//import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;

/**
 *	ICFSecPubISOCcyH provides access to public history records matching the CFSecPubISOCcy object change history.
 */
public interface ICFSecPubISOCcyH
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

	public ICFSecPubISOCcyHPKey getPubPKey();
	public void setPubPKey( ICFSecPubISOCcyHPKey pkey );
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

	public short getRequiredISOCcyId();
	public void setRequiredISOCcyId( short requiredISOCcyId );

	public String getRequiredISOCode();
	public void setRequiredISOCode( String value );
	public String getRequiredName();
	public void setRequiredName( String value );
	public String getOptionalUnitSymbol();
	public void setOptionalUnitSymbol( String value );
	public short getRequiredPrecis();
	public void setRequiredPrecis( short value );
	@Override
	public boolean equals( Object obj );

	@Override
	public int hashCode();

	//@Override
	public int compareTo( Object obj );

	public void set( ICFSecPubISOCcy src );
	public void set( ICFSecPubISOCcyH src );
	public void setISOCcy( ICFSecPubISOCcy src );
	public void setISOCcy( ICFSecPubISOCcyH src );
	public String getXmlAttrFragment();

	@Override
	public String toString();
}
