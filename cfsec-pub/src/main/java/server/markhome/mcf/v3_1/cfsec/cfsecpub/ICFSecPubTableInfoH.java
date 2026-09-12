// Description: Java 25 public interface for a TableInfo history object

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
 *	ICFSecPubTableInfoH provides access to public history records matching the CFSecPubTableInfo object change history.
 */
public interface ICFSecPubTableInfoH
{
	public int getClassCode();

	public ICFSecPubTableInfoHPKey getPubPKey();
	public void setPubPKey( ICFSecPubTableInfoHPKey pkey );
	public CFLibDbKeyHash256 getAuditClusterId();
	public void setAuditClusterId(CFLibDbKeyHash256 auditClusterId);
	public LocalDateTime getAuditStamp();
	public void setAuditStamp(LocalDateTime auditStamp);
	public short getAuditActionId();
	public void setAuditActionId(short auditActionId);
	public int getRequiredRevision();
	public void setRequiredRevision(int revision);
	public CFLibDbKeyHash256 getAuditSessionId();
	public void setAuditSessionId(CFLibDbKeyHash256 auditSessionId);

	public int getRequiredTableInfoId();
	public void setRequiredTableInfoId( int requiredTableInfoId );

	public String getRequiredSchemaName();
	public void setRequiredSchemaName( String value );
	public String getRequiredTableName();
	public void setRequiredTableName( String value );
	public String getOptionalSuperName();
	public void setOptionalSuperName( String value );
	public int getRequiredBackingClassCode();
	public void setRequiredBackingClassCode( int value );
	public int getRequiredRuntimeClassCode();
	public void setRequiredRuntimeClassCode( int value );
	public boolean getRequiredHasHistory();
	public void setRequiredHasHistory( boolean value );
	public boolean getRequiredIsMutable();
	public void setRequiredIsMutable( boolean value );
	public String getRequiredSecScopeName();
	public void setRequiredSecScopeName( String value );
	public String getRequiredCodeVis();
	public void setRequiredCodeVis( String value );
	@Override
	public boolean equals( Object obj );

	@Override
	public int hashCode();

	//@Override
	public int compareTo( Object obj );

	public void set( ICFSecPubTableInfo src );
	public void set( ICFSecPubTableInfoH src );
	public void setTableInfo( ICFSecPubTableInfo src );
	public void setTableInfo( ICFSecPubTableInfoH src );
	public String getXmlAttrFragment();

	@Override
	public String toString();
}
