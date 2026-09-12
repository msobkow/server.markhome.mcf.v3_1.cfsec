// Description: Java 17 implJustPubementation of a TableInfo buffer

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

package server.markhome.mcf.v3_1.cfsec.cfsec.buff;

import java.lang.reflect.*;
import java.io.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
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
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public class CFSecPubBuffTableInfo
	implements ICFSecPubTableInfo, Comparable<Object>, Serializable
{
	protected int requiredTableInfoId;
	protected int requiredRevision;
	protected String requiredSchemaName;
	protected String requiredTableName;
	protected String optionalSuperName;
	protected int requiredBackingClassCode;
	protected int requiredRuntimeClassCode;
	protected boolean requiredHasHistory;
	protected boolean requiredIsMutable;
	protected String requiredSecScopeName;
	protected String requiredCodeVis;

	public CFSecPubBuffTableInfo() {
		requiredTableInfoId = ICFSecPubTableInfo.TABLEINFOID_INIT_VALUE;
		requiredSchemaName = ICFSecPubTableInfo.SCHEMANAME_INIT_VALUE;
		requiredTableName = ICFSecPubTableInfo.TABLENAME_INIT_VALUE;
		optionalSuperName = null;
		requiredBackingClassCode = ICFSecPubTableInfo.BACKINGCLASSCODE_INIT_VALUE;
		requiredRuntimeClassCode = ICFSecPubTableInfo.RUNTIMECLASSCODE_INIT_VALUE;
		requiredHasHistory = ICFSecPubTableInfo.HASHISTORY_INIT_VALUE;
		requiredIsMutable = ICFSecPubTableInfo.ISMUTABLE_INIT_VALUE;
		requiredSecScopeName = ICFSecPubTableInfo.SECSCOPENAME_INIT_VALUE;
		requiredCodeVis = ICFSecPubTableInfo.CODEVIS_INIT_VALUE;
	}

	@Override
	public Integer getPKey() {
		return (requiredTableInfoId);
	}

	@Override
	public void setPKey(Integer requiredTableInfoId) {
		if(requiredTableInfoId != null) {
			this.requiredTableInfoId = requiredTableInfoId;
		}
	}

	@Override
	public int getRequiredTableInfoId() {
		return(requiredTableInfoId);
	}

	@Override
	public void setRequiredTableInfoId( int value ) {
		if( value < ICFSecPubTableInfo.TABLEINFOID_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredTableInfoId",
				1,
				"value",
				value,
				ICFSecPubTableInfo.TABLEINFOID_MIN_VALUE );
		}
		requiredTableInfoId = value;
	}

	@Override
	public int getRequiredRevision() {
		return( requiredRevision );
	}

	@Override
	public void setRequiredRevision( int value ) {
		requiredRevision = value;
	}

	@Override
	public int getClassCode() {
		return( ICFSecPubTableInfo.CLASS_CODE );
	}

	@Override
	public ICFSecPubTableInfo getOptionalParentSuperRef(String argSuperName) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalParentSuperRef", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubTableInfoTable targetTable = targetBackingCFSec.getTableTableInfo();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalParentSuperRef", 0, "ICFSecPubSchema.getBackingCFSec().getTableTableInfo()");
		}
		return(targetTable.readDerivedByTableNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getOptionalSuperName()));
	}

	@Override
	public void setOptionalParentSuperRef(String argSuperName) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalParentSuperRef-args", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubTableInfoTable targetTable = targetBackingCFSec.getTableTableInfo();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalParentSuperRef", 0, "ICFSecSchema.getBackingCFSec().getTableTableInfo()");
		}
		ICFSecPubTableInfo found = targetTable.readDerivedByTableNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argSuperName);
		if (found == null || (found != null && ((found instanceof ICFSecPubTableInfo) || (found instanceof ICFSecPubTableInfo)))) {
			super.setOptionalParentSuperRef(argSuperName);
		optionalSuperName = argSuperName;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setOptionalParentSuperRef-args", "found", found, "ICFSecPubTableInfoICFSecPubTableInfo");
		}
	}

	@Override
	public void setOptionalParentSuperRef(ICFSecPubTableInfo argObj) {

		if(argObj == null) {
			setJustPubOptionalSuperName(null);
		}
		else {
			setOptionalSuperName(argObj.getRequiredTableName());
		}
	}

	@Override
	public List<ICFSecPubTableInfo> getOptionalChildrenSubRefs(String argTableName) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSubRefs", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubTableInfoTable targetTable = targetBackingCFSec.getTableTableInfo();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getOptionalChildrenSubRefs", 0, "ICFSecPubSchema.getBackingCFSec().getTableTableInfo()");
		}
		ICFSecPubTableInfo[] targetArr = targetTable.readDerivedBySuperNameIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredTableName());
		if( targetArr != null ) {
			List<ICFSecPubTableInfo> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return(results);
		}
		else {
			List<ICFSecPubTableInfo> results = new ArrayList<>();
			return(results);
		}
	}

	@Override
	public String getRequiredSchemaName() {
		return(requiredSchemaName);
	}

	public void setRequiredSchemaName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSchemaName",
				1,
				"value" );
		}
		else if( value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredSchemaName",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		requiredSchemaName = value;
	}

	@Override
	public String getRequiredTableName() {
		return(requiredTableName);
	}

	public void setRequiredTableName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredTableName",
				1,
				"value" );
		}
		else if( value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredTableName",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		requiredTableName = value;
	}

	@Override
	public String getOptionalSuperName() {
		return(optionalSuperName);
	}

	public void setOptionalSuperName( String value ) {
		if( value != null && value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalSuperName",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		optionalSuperName = value;
	}

	@Override
	public int getRequiredBackingClassCode() {
		return(requiredBackingClassCode);
	}

	@Override
	public void setRequiredBackingClassCode( int value ) {
		if( value < ICFSecPubTableInfo.BACKINGCLASSCODE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredBackingClassCode",
				1,
				"value",
				value,
				ICFSecPubTableInfo.BACKINGCLASSCODE_MIN_VALUE );
		}
		requiredBackingClassCode = value;
	}

	@Override
	public int getRequiredRuntimeClassCode() {
		return(requiredRuntimeClassCode);
	}

	@Override
	public void setRequiredRuntimeClassCode( int value ) {
		if( value < ICFSecPubTableInfo.RUNTIMECLASSCODE_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredRuntimeClassCode",
				1,
				"value",
				value,
				ICFSecPubTableInfo.RUNTIMECLASSCODE_MIN_VALUE );
		}
		requiredRuntimeClassCode = value;
	}

	@Override
	public boolean getRequiredHasHistory() {
		return(requiredHasHistory);
	}

	public void setRequiredHasHistory( boolean value ) {
		requiredHasHistory = value;
	}

	@Override
	public boolean getRequiredIsMutable() {
		return(requiredIsMutable);
	}

	public void setRequiredIsMutable( boolean value ) {
		requiredIsMutable = value;
	}

	@Override
	public String getRequiredSecScopeName() {
		return(requiredSecScopeName);
	}

	public void setRequiredSecScopeName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSecScopeName",
				1,
				"value" );
		}
		else if( value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredSecScopeName",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		requiredSecScopeName = value;
	}

	@Override
	public String getRequiredCodeVis() {
		return(requiredCodeVis);
	}

	public void setRequiredCodeVis( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredCodeVis",
				1,
				"value" );
		}
		else if( value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredCodeVis",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		requiredCodeVis = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecPubTableInfo rhs ) {
			if( getRequiredTableInfoId() != rhs.getRequiredTableInfoId() ) {
				return( false );
			}
			if( getRequiredSchemaName() != null ) {
				if( rhs.getRequiredSchemaName() != null ) {
					if( ! getRequiredSchemaName().equals( rhs.getRequiredSchemaName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaName() != null ) {
					return( false );
				}
			}
			if( getRequiredTableName() != null ) {
				if( rhs.getRequiredTableName() != null ) {
					if( ! getRequiredTableName().equals( rhs.getRequiredTableName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					if( ! getOptionalSuperName().equals( rhs.getOptionalSuperName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( false );
				}
			}
			if( getRequiredBackingClassCode() != rhs.getRequiredBackingClassCode() ) {
				return( false );
			}
			if( getRequiredRuntimeClassCode() != rhs.getRequiredRuntimeClassCode() ) {
				return( false );
			}
			if( getRequiredHasHistory() != rhs.getRequiredHasHistory() ) {
				return( false );
			}
			if( getRequiredIsMutable() != rhs.getRequiredIsMutable() ) {
				return( false );
			}
			if( getRequiredSecScopeName() != null ) {
				if( rhs.getRequiredSecScopeName() != null ) {
					if( ! getRequiredSecScopeName().equals( rhs.getRequiredSecScopeName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecScopeName() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoH rhs ) {
			if( getRequiredTableInfoId() != rhs.getRequiredTableInfoId() ) {
				return( false );
			}
			if( getRequiredSchemaName() != null ) {
				if( rhs.getRequiredSchemaName() != null ) {
					if( ! getRequiredSchemaName().equals( rhs.getRequiredSchemaName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaName() != null ) {
					return( false );
				}
			}
			if( getRequiredTableName() != null ) {
				if( rhs.getRequiredTableName() != null ) {
					if( ! getRequiredTableName().equals( rhs.getRequiredTableName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					if( ! getOptionalSuperName().equals( rhs.getOptionalSuperName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( false );
				}
			}
			if( getRequiredBackingClassCode() != rhs.getRequiredBackingClassCode() ) {
				return( false );
			}
			if( getRequiredRuntimeClassCode() != rhs.getRequiredRuntimeClassCode() ) {
				return( false );
			}
			if( getRequiredHasHistory() != rhs.getRequiredHasHistory() ) {
				return( false );
			}
			if( getRequiredIsMutable() != rhs.getRequiredIsMutable() ) {
				return( false );
			}
			if( getRequiredSecScopeName() != null ) {
				if( rhs.getRequiredSecScopeName() != null ) {
					if( ! getRequiredSecScopeName().equals( rhs.getRequiredSecScopeName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecScopeName() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoHPKey ) {
			ICFSecPubTableInfoHPKey rhs = (ICFSecPubTableInfoHPKey)obj;
			if( getRequiredTableInfoId() != rhs.getRequiredTableInfoId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoByTableNameIdxKey rhs ) {
			if( getRequiredTableName() != null ) {
				if( rhs.getRequiredTableName() != null ) {
					if( ! getRequiredTableName().equals( rhs.getRequiredTableName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoBySuperNameIdxKey rhs ) {
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					if( ! getOptionalSuperName().equals( rhs.getOptionalSuperName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaNameIdxKey rhs ) {
			if( getRequiredSchemaName() != null ) {
				if( rhs.getRequiredSchemaName() != null ) {
					if( ! getRequiredSchemaName().equals( rhs.getRequiredSchemaName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaBkCodeIdxKey rhs ) {
			if( getRequiredSchemaName() != null ) {
				if( rhs.getRequiredSchemaName() != null ) {
					if( ! getRequiredSchemaName().equals( rhs.getRequiredSchemaName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaName() != null ) {
					return( false );
				}
			}
			if( getRequiredBackingClassCode() != rhs.getRequiredBackingClassCode() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaRTCodeIdxKey rhs ) {
			if( getRequiredRuntimeClassCode() != rhs.getRequiredRuntimeClassCode() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfo rhs ) {
			if( getRequiredTableInfoId() != rhs.getRequiredTableInfoId() ) {
				return( false );
			}
			if( getRequiredSchemaName() != null ) {
				if( rhs.getRequiredSchemaName() != null ) {
					if( ! getRequiredSchemaName().equals( rhs.getRequiredSchemaName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaName() != null ) {
					return( false );
				}
			}
			if( getRequiredTableName() != null ) {
				if( rhs.getRequiredTableName() != null ) {
					if( ! getRequiredTableName().equals( rhs.getRequiredTableName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					if( ! getOptionalSuperName().equals( rhs.getOptionalSuperName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( false );
				}
			}
			if( getRequiredBackingClassCode() != rhs.getRequiredBackingClassCode() ) {
				return( false );
			}
			if( getRequiredRuntimeClassCode() != rhs.getRequiredRuntimeClassCode() ) {
				return( false );
			}
			if( getRequiredHasHistory() != rhs.getRequiredHasHistory() ) {
				return( false );
			}
			if( getRequiredIsMutable() != rhs.getRequiredIsMutable() ) {
				return( false );
			}
			if( getRequiredSecScopeName() != null ) {
				if( rhs.getRequiredSecScopeName() != null ) {
					if( ! getRequiredSecScopeName().equals( rhs.getRequiredSecScopeName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecScopeName() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoH rhs ) {
			if( getRequiredTableInfoId() != rhs.getRequiredTableInfoId() ) {
				return( false );
			}
			if( getRequiredSchemaName() != null ) {
				if( rhs.getRequiredSchemaName() != null ) {
					if( ! getRequiredSchemaName().equals( rhs.getRequiredSchemaName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaName() != null ) {
					return( false );
				}
			}
			if( getRequiredTableName() != null ) {
				if( rhs.getRequiredTableName() != null ) {
					if( ! getRequiredTableName().equals( rhs.getRequiredTableName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					if( ! getOptionalSuperName().equals( rhs.getOptionalSuperName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( false );
				}
			}
			if( getRequiredBackingClassCode() != rhs.getRequiredBackingClassCode() ) {
				return( false );
			}
			if( getRequiredRuntimeClassCode() != rhs.getRequiredRuntimeClassCode() ) {
				return( false );
			}
			if( getRequiredHasHistory() != rhs.getRequiredHasHistory() ) {
				return( false );
			}
			if( getRequiredIsMutable() != rhs.getRequiredIsMutable() ) {
				return( false );
			}
			if( getRequiredSecScopeName() != null ) {
				if( rhs.getRequiredSecScopeName() != null ) {
					if( ! getRequiredSecScopeName().equals( rhs.getRequiredSecScopeName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSecScopeName() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoHPKey rhs ) {
			if( getRequiredTableInfoId() != rhs.getRequiredTableInfoId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoByTableNameIdxKey rhs ) {
			if( getRequiredTableName() != null ) {
				if( rhs.getRequiredTableName() != null ) {
					if( ! getRequiredTableName().equals( rhs.getRequiredTableName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoBySuperNameIdxKey rhs ) {
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					if( ! getOptionalSuperName().equals( rhs.getOptionalSuperName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaNameIdxKey rhs ) {
			if( getRequiredSchemaName() != null ) {
				if( rhs.getRequiredSchemaName() != null ) {
					if( ! getRequiredSchemaName().equals( rhs.getRequiredSchemaName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaBkCodeIdxKey rhs ) {
			if( getRequiredSchemaName() != null ) {
				if( rhs.getRequiredSchemaName() != null ) {
					if( ! getRequiredSchemaName().equals( rhs.getRequiredSchemaName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaName() != null ) {
					return( false );
				}
			}
			if( getRequiredBackingClassCode() != rhs.getRequiredBackingClassCode() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaRTCodeIdxKey rhs ) {
			if( getRequiredRuntimeClassCode() != rhs.getRequiredRuntimeClassCode() ) {
				return( false );
			}
			return( true );
		}
		else {
			boolean retval = super.equals( obj );
			return( retval );
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		hashCode = hashCode + getRequiredTableInfoId();
		if( getRequiredSchemaName() != null ) {
			hashCode = hashCode + getRequiredSchemaName().hashCode();
		}
		if( getRequiredTableName() != null ) {
			hashCode = hashCode + getRequiredTableName().hashCode();
		}
		if( getOptionalSuperName() != null ) {
			hashCode = hashCode + getOptionalSuperName().hashCode();
		}
		hashCode = hashCode + getRequiredBackingClassCode();
		hashCode = hashCode + getRequiredRuntimeClassCode();
		if( getRequiredHasHistory() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredIsMutable() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredSecScopeName() != null ) {
			hashCode = hashCode + getRequiredSecScopeName().hashCode();
		}
		if( getRequiredCodeVis() != null ) {
			hashCode = hashCode + getRequiredCodeVis().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecPubTableInfo rhs ) {
			cmp = 0;
			if( getRequiredTableInfoId() < rhs.getRequiredTableInfoId() ) {
				return( -1 );
			}
			else if( getRequiredTableInfoId() > rhs.getRequiredTableInfoId() ) {
				return( 1 );
			}
			if (getRequiredSchemaName() != null) {
				if (rhs.getRequiredSchemaName() != null) {
					cmp = getRequiredSchemaName().compareTo( rhs.getRequiredSchemaName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaName() != null) {
				return( -1 );
			}
			if (getRequiredTableName() != null) {
				if (rhs.getRequiredTableName() != null) {
					cmp = getRequiredTableName().compareTo( rhs.getRequiredTableName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableName() != null) {
				return( -1 );
			}
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					cmp = getOptionalSuperName().compareTo( rhs.getOptionalSuperName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( -1 );
				}
			}
			if( getRequiredBackingClassCode() < rhs.getRequiredBackingClassCode() ) {
				return( -1 );
			}
			else if( getRequiredBackingClassCode() > rhs.getRequiredBackingClassCode() ) {
				return( 1 );
			}
			if( getRequiredRuntimeClassCode() < rhs.getRequiredRuntimeClassCode() ) {
				return( -1 );
			}
			else if( getRequiredRuntimeClassCode() > rhs.getRequiredRuntimeClassCode() ) {
				return( 1 );
			}
			if( getRequiredHasHistory() ) {
				if( ! rhs.getRequiredHasHistory() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredHasHistory() ) {
					return( -1 );
				}
			}
			if( getRequiredIsMutable() ) {
				if( ! rhs.getRequiredIsMutable() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsMutable() ) {
					return( -1 );
				}
			}
			if (getRequiredSecScopeName() != null) {
				if (rhs.getRequiredSecScopeName() != null) {
					cmp = getRequiredSecScopeName().compareTo( rhs.getRequiredSecScopeName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecScopeName() != null) {
				return( -1 );
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecPubTableInfoHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if( getRequiredTableInfoId() < rhs.getRequiredTableInfoId() ) {
				return( -1 );
			}
			else if( getRequiredTableInfoId() > rhs.getRequiredTableInfoId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoH rhs ) {
			cmp = 0;
			if( getRequiredTableInfoId() < rhs.getRequiredTableInfoId() ) {
				return( -1 );
			}
			else if( getRequiredTableInfoId() > rhs.getRequiredTableInfoId() ) {
				return( 1 );
			}
			if (getRequiredSchemaName() != null) {
				if (rhs.getRequiredSchemaName() != null) {
					cmp = getRequiredSchemaName().compareTo( rhs.getRequiredSchemaName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaName() != null) {
				return( -1 );
			}
			if (getRequiredTableName() != null) {
				if (rhs.getRequiredTableName() != null) {
					cmp = getRequiredTableName().compareTo( rhs.getRequiredTableName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableName() != null) {
				return( -1 );
			}
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					cmp = getOptionalSuperName().compareTo( rhs.getOptionalSuperName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( -1 );
				}
			}
			if( getRequiredBackingClassCode() < rhs.getRequiredBackingClassCode() ) {
				return( -1 );
			}
			else if( getRequiredBackingClassCode() > rhs.getRequiredBackingClassCode() ) {
				return( 1 );
			}
			if( getRequiredRuntimeClassCode() < rhs.getRequiredRuntimeClassCode() ) {
				return( -1 );
			}
			else if( getRequiredRuntimeClassCode() > rhs.getRequiredRuntimeClassCode() ) {
				return( 1 );
			}
			if( getRequiredHasHistory() ) {
				if( ! rhs.getRequiredHasHistory() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredHasHistory() ) {
					return( -1 );
				}
			}
			if( getRequiredIsMutable() ) {
				if( ! rhs.getRequiredIsMutable() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsMutable() ) {
					return( -1 );
				}
			}
			if (getRequiredSecScopeName() != null) {
				if (rhs.getRequiredSecScopeName() != null) {
					cmp = getRequiredSecScopeName().compareTo( rhs.getRequiredSecScopeName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecScopeName() != null) {
				return( -1 );
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoByTableNameIdxKey rhs ) {
			if (getRequiredTableName() != null) {
				if (rhs.getRequiredTableName() != null) {
					cmp = getRequiredTableName().compareTo( rhs.getRequiredTableName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoBySuperNameIdxKey rhs ) {
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					cmp = getOptionalSuperName().compareTo( rhs.getOptionalSuperName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaNameIdxKey rhs ) {
			if (getRequiredSchemaName() != null) {
				if (rhs.getRequiredSchemaName() != null) {
					cmp = getRequiredSchemaName().compareTo( rhs.getRequiredSchemaName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaBkCodeIdxKey rhs ) {
			if (getRequiredSchemaName() != null) {
				if (rhs.getRequiredSchemaName() != null) {
					cmp = getRequiredSchemaName().compareTo( rhs.getRequiredSchemaName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaName() != null) {
				return( -1 );
			}
			if( getRequiredBackingClassCode() < rhs.getRequiredBackingClassCode() ) {
				return( -1 );
			}
			else if( getRequiredBackingClassCode() > rhs.getRequiredBackingClassCode() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaRTCodeIdxKey rhs ) {
			if( getRequiredRuntimeClassCode() < rhs.getRequiredRuntimeClassCode() ) {
				return( -1 );
			}
			else if( getRequiredRuntimeClassCode() > rhs.getRequiredRuntimeClassCode() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfo rhs ) {
			cmp = 0;
			if( getRequiredTableInfoId() < rhs.getRequiredTableInfoId() ) {
				return( -1 );
			}
			else if( getRequiredTableInfoId() > rhs.getRequiredTableInfoId() ) {
				return( 1 );
			}
			if (getRequiredSchemaName() != null) {
				if (rhs.getRequiredSchemaName() != null) {
					cmp = getRequiredSchemaName().compareTo( rhs.getRequiredSchemaName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaName() != null) {
				return( -1 );
			}
			if (getRequiredTableName() != null) {
				if (rhs.getRequiredTableName() != null) {
					cmp = getRequiredTableName().compareTo( rhs.getRequiredTableName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableName() != null) {
				return( -1 );
			}
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					cmp = getOptionalSuperName().compareTo( rhs.getOptionalSuperName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( -1 );
				}
			}
			if( getRequiredBackingClassCode() < rhs.getRequiredBackingClassCode() ) {
				return( -1 );
			}
			else if( getRequiredBackingClassCode() > rhs.getRequiredBackingClassCode() ) {
				return( 1 );
			}
			if( getRequiredRuntimeClassCode() < rhs.getRequiredRuntimeClassCode() ) {
				return( -1 );
			}
			else if( getRequiredRuntimeClassCode() > rhs.getRequiredRuntimeClassCode() ) {
				return( 1 );
			}
			if( getRequiredHasHistory() ) {
				if( ! rhs.getRequiredHasHistory() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredHasHistory() ) {
					return( -1 );
				}
			}
			if( getRequiredIsMutable() ) {
				if( ! rhs.getRequiredIsMutable() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsMutable() ) {
					return( -1 );
				}
			}
			if (getRequiredSecScopeName() != null) {
				if (rhs.getRequiredSecScopeName() != null) {
					cmp = getRequiredSecScopeName().compareTo( rhs.getRequiredSecScopeName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecScopeName() != null) {
				return( -1 );
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if( getRequiredTableInfoId() < rhs.getRequiredTableInfoId() ) {
				return( -1 );
			}
			else if( getRequiredTableInfoId() > rhs.getRequiredTableInfoId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoH rhs ) {
			cmp = 0;
			if( getRequiredTableInfoId() < rhs.getRequiredTableInfoId() ) {
				return( -1 );
			}
			else if( getRequiredTableInfoId() > rhs.getRequiredTableInfoId() ) {
				return( 1 );
			}
			if (getRequiredSchemaName() != null) {
				if (rhs.getRequiredSchemaName() != null) {
					cmp = getRequiredSchemaName().compareTo( rhs.getRequiredSchemaName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaName() != null) {
				return( -1 );
			}
			if (getRequiredTableName() != null) {
				if (rhs.getRequiredTableName() != null) {
					cmp = getRequiredTableName().compareTo( rhs.getRequiredTableName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableName() != null) {
				return( -1 );
			}
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					cmp = getOptionalSuperName().compareTo( rhs.getOptionalSuperName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( -1 );
				}
			}
			if( getRequiredBackingClassCode() < rhs.getRequiredBackingClassCode() ) {
				return( -1 );
			}
			else if( getRequiredBackingClassCode() > rhs.getRequiredBackingClassCode() ) {
				return( 1 );
			}
			if( getRequiredRuntimeClassCode() < rhs.getRequiredRuntimeClassCode() ) {
				return( -1 );
			}
			else if( getRequiredRuntimeClassCode() > rhs.getRequiredRuntimeClassCode() ) {
				return( 1 );
			}
			if( getRequiredHasHistory() ) {
				if( ! rhs.getRequiredHasHistory() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredHasHistory() ) {
					return( -1 );
				}
			}
			if( getRequiredIsMutable() ) {
				if( ! rhs.getRequiredIsMutable() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsMutable() ) {
					return( -1 );
				}
			}
			if (getRequiredSecScopeName() != null) {
				if (rhs.getRequiredSecScopeName() != null) {
					cmp = getRequiredSecScopeName().compareTo( rhs.getRequiredSecScopeName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSecScopeName() != null) {
				return( -1 );
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoByTableNameIdxKey rhs ) {
			if (getRequiredTableName() != null) {
				if (rhs.getRequiredTableName() != null) {
					cmp = getRequiredTableName().compareTo( rhs.getRequiredTableName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoBySuperNameIdxKey rhs ) {
			if( getOptionalSuperName() != null ) {
				if( rhs.getOptionalSuperName() != null ) {
					cmp = getOptionalSuperName().compareTo( rhs.getOptionalSuperName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuperName() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaNameIdxKey rhs ) {
			if (getRequiredSchemaName() != null) {
				if (rhs.getRequiredSchemaName() != null) {
					cmp = getRequiredSchemaName().compareTo( rhs.getRequiredSchemaName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaBkCodeIdxKey rhs ) {
			if (getRequiredSchemaName() != null) {
				if (rhs.getRequiredSchemaName() != null) {
					cmp = getRequiredSchemaName().compareTo( rhs.getRequiredSchemaName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaName() != null) {
				return( -1 );
			}
			if( getRequiredBackingClassCode() < rhs.getRequiredBackingClassCode() ) {
				return( -1 );
			}
			else if( getRequiredBackingClassCode() > rhs.getRequiredBackingClassCode() ) {
				return( 1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubTableInfoBySchemaRTCodeIdxKey rhs ) {
			if( getRequiredRuntimeClassCode() < rhs.getRequiredRuntimeClassCode() ) {
				return( -1 );
			}
			else if( getRequiredRuntimeClassCode() > rhs.getRequiredRuntimeClassCode() ) {
				return( 1 );
			}			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"obj",
				obj,
				null );
		}
	}

	@Override
	public void set( ICFSecPubTableInfo src ) {
		setJustPubTableInfo( src );
	}

	@Override
	public void setTableInfo( ICFSecPubTableInfo src ) {
		setJustPubRequiredTableInfoId(src.getRequiredTableInfoId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubOptionalParentSuperRef(src.getOptionalParentSuperRef());
		setJustPubRequiredSchemaName(src.getRequiredSchemaName());
		setJustPubRequiredTableName(src.getRequiredTableName());
		setJustPubOptionalSuperName(src.getOptionalSuperName());
		setJustPubRequiredBackingClassCode(src.getRequiredBackingClassCode());
		setJustPubRequiredRuntimeClassCode(src.getRequiredRuntimeClassCode());
		setJustPubRequiredHasHistory(src.getRequiredHasHistory());
		setJustPubRequiredIsMutable(src.getRequiredIsMutable());
		setJustPubRequiredSecScopeName(src.getRequiredSecScopeName());
		setJustPubRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public void set( ICFSecPubTableInfoH src ) {
		setJustPubTableInfo( src );
	}

	@Override
	public void setTableInfo( ICFSecPubTableInfoH src ) {
		setJustPubRequiredTableInfoId(src.getRequiredTableInfoId());
		setJustPubOptionalParentSuperRef(src.getOptionalParentSuperRef());
		setJustPubRequiredSchemaName(src.getRequiredSchemaName());
		setJustPubRequiredTableName(src.getRequiredTableName());
		setJustPubOptionalSuperName(src.getOptionalSuperName());
		setJustPubRequiredBackingClassCode(src.getRequiredBackingClassCode());
		setJustPubRequiredRuntimeClassCode(src.getRequiredRuntimeClassCode());
		setJustPubRequiredHasHistory(src.getRequiredHasHistory());
		setJustPubRequiredIsMutable(src.getRequiredIsMutable());
		setJustPubRequiredSecScopeName(src.getRequiredSecScopeName());
		setJustPubRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public void set( ICFSecPubTableInfo src ) {
		setJustPubTableInfo( src );
	}

	@Override
	public void setTableInfo( ICFSecPubTableInfo src ) {
		setJustPubRequiredTableInfoId(src.getRequiredTableInfoId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubOptionalParentSuperRef(src.getOptionalParentSuperRef());
		setJustPubRequiredSchemaName(src.getRequiredSchemaName());
		setJustPubRequiredTableName(src.getRequiredTableName());
		setJustPubOptionalSuperName(src.getOptionalSuperName());
		setJustPubRequiredBackingClassCode(src.getRequiredBackingClassCode());
		setJustPubRequiredRuntimeClassCode(src.getRequiredRuntimeClassCode());
		setJustPubRequiredHasHistory(src.getRequiredHasHistory());
		setJustPubRequiredIsMutable(src.getRequiredIsMutable());
		setJustPubRequiredSecScopeName(src.getRequiredSecScopeName());
		setJustPubRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public void set( ICFSecPubTableInfoH src ) {
		setJustPubTableInfo( src );
	}

	@Override
	public void setTableInfo( ICFSecPubTableInfoH src ) {
		setJustPubRequiredTableInfoId(src.getRequiredTableInfoId());
		setJustPubOptionalParentSuperRef(src.getOptionalParentSuperRef());
		setJustPubRequiredSchemaName(src.getRequiredSchemaName());
		setJustPubRequiredTableName(src.getRequiredTableName());
		setJustPubOptionalSuperName(src.getOptionalSuperName());
		setJustPubRequiredBackingClassCode(src.getRequiredBackingClassCode());
		setJustPubRequiredRuntimeClassCode(src.getRequiredRuntimeClassCode());
		setJustPubRequiredHasHistory(src.getRequiredHasHistory());
		setJustPubRequiredIsMutable(src.getRequiredIsMutable());
		setJustPubRequiredSecScopeName(src.getRequiredSecScopeName());
		setJustPubRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + Integer.toString( getRequiredTableInfoId() ) + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredSchemaName() ) + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredTableName() ) + "\"" + ( ( getOptionalSuperName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalSuperName() ) + "\"" ) + "\"" + Integer.toString( getRequiredBackingClassCode() ) + "\"" + "\"" + Integer.toString( getRequiredRuntimeClassCode() ) + "\"" + (( getRequiredHasHistory() ) ? "\"true\"" : "\"false\"" ) + (( getRequiredIsMutable() ) ? "\"true\"" : "\"false\"" ) + "\"" + StringEscapeUtils.escapeXml11( getRequiredSecScopeName() ) + "\"" + "\"" + StringEscapeUtils.escapeXml11( getRequiredCodeVis() ) + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecPubBuffTableInfo" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
