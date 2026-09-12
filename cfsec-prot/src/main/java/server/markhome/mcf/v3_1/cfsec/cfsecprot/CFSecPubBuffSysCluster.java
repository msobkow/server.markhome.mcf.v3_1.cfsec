// Description: Java 17 implJustPubementation of a SysCluster buffer

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

public class CFSecPubBuffSysCluster
	implements ICFSecPubSysCluster, Comparable<Object>, Serializable
{
	protected int requiredSingletonId;
	protected int requiredRevision;
	protected ICFLibKeyHash256 requiredClusterId;

	public CFSecPubBuffSysCluster() {
		requiredSingletonId = ICFSecPubSysCluster.SINGLETONID_INIT_VALUE;
		requiredClusterId = CFLibDbKeyHash256.fromHex( ICFSecPubSysCluster.CLUSTERID_INIT_VALUE.toString() );
	}

	@Override
	public Integer getPKey() {
		return (requiredSingletonId);
	}

	@Override
	public void setPKey(Integer requiredSingletonId) {
		if(requiredSingletonId != null) {
			this.requiredSingletonId = requiredSingletonId;
		}
	}

	@Override
	public int getRequiredSingletonId() {
		return(requiredSingletonId);
	}

	@Override
	public void setRequiredSingletonId( int value ) {
		if( value < ICFSecPubSysCluster.SINGLETONID_MIN_VALUE ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				"setRequiredSingletonId",
				1,
				"value",
				value,
				ICFSecPubSysCluster.SINGLETONID_MIN_VALUE );
		}
		if( value > ICFSecSysCluster.SINGLETONID_MAX_VALUE ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredSingletonId",
				1,
				"value",
				value,
				ICFSecSysCluster.SINGLETONID_MAX_VALUE );
		}
		requiredSingletonId = value;
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
		return( ICFSecPubSysCluster.CLASS_CODE );
	}

	@Override
	public ICFSecPubCluster getRequiredContainerCluster(ICFLibKeyHash256 argClusterId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerCluster", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubClusterTable targetTable = targetBackingCFSec.getTableCluster();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerCluster", 0, "ICFSecPubSchema.getBackingCFSec().getTableCluster()");
		}
		return(targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredClusterId()));
	}

	@Override
	public void setRequiredContainerCluster(ICFLibKeyHash256 argClusterId) {
		ICFSecPubSchema targetBackingCFSec = ICFSecPubSchema.getBackingCFSec();
		if (targetBackingCFSec == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerCluster-args", 0, "ICFSecPubSchema.getBackingCFSec()");
		}
		ICFSecPubClusterTable targetTable = targetBackingCFSec.getTableCluster();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerCluster", 0, "ICFSecSchema.getBackingCFSec().getTableCluster()");
		}
		ICFSecPubCluster found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argClusterId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerCluster-args", 0, "found");
		}
		else if (found instanceof ICFSecPubCluster) || (found instanceof ICFSecPubCluster)) {
			super.setRequiredContainerCluster(argClusterId);
		requiredClusterId = argClusterId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setRequiredContainerCluster-args", "found", found, "ICFSecPubClusterICFSecPubCluster");
		}
	}

	@Override
	public void setRequiredContainerCluster(ICFSecPubCluster argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerCluster", 1, "argObj");
		}
		else {
			setRequiredClusterId(argObj.getRequiredId());
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredClusterId() {
		return(requiredClusterId);
	}

	public void setRequiredClusterId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredClusterId",
				1,
				"value" );
		}
		requiredClusterId = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFSecPubSysCluster rhs ) {
			if( getRequiredSingletonId() != rhs.getRequiredSingletonId() ) {
				return( false );
			}
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSysClusterH rhs ) {
			if( getRequiredSingletonId() != rhs.getRequiredSingletonId() ) {
				return( false );
			}
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSysClusterHPKey ) {
			ICFSecPubSysClusterHPKey rhs = (ICFSecPubSysClusterHPKey)obj;
			if( getRequiredSingletonId() != rhs.getRequiredSingletonId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSysClusterByClusterIdxKey rhs ) {
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSysCluster rhs ) {
			if( getRequiredSingletonId() != rhs.getRequiredSingletonId() ) {
				return( false );
			}
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSysClusterH rhs ) {
			if( getRequiredSingletonId() != rhs.getRequiredSingletonId() ) {
				return( false );
			}
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSysClusterHPKey rhs ) {
			if( getRequiredSingletonId() != rhs.getRequiredSingletonId() ) {
				return( false );
			}
			return( true );
		}
		else if( obj instanceof ICFSecPubSysClusterByClusterIdxKey rhs ) {
			if( getRequiredClusterId() != null ) {
				if( rhs.getRequiredClusterId() != null ) {
					if( ! getRequiredClusterId().equals( rhs.getRequiredClusterId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredClusterId() != null ) {
					return( false );
				}
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
		hashCode = hashCode + getRequiredSingletonId();
		hashCode = hashCode + getRequiredClusterId().hashCode();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFSecPubSysCluster rhs ) {
			cmp = 0;
			if( getRequiredSingletonId() < rhs.getRequiredSingletonId() ) {
				return( -1 );
			}
			else if( getRequiredSingletonId() > rhs.getRequiredSingletonId() ) {
				return( 1 );
			}
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFSecPubSysClusterHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if( getRequiredSingletonId() < rhs.getRequiredSingletonId() ) {
				return( -1 );
			}
			else if( getRequiredSingletonId() > rhs.getRequiredSingletonId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSysClusterH rhs ) {
			cmp = 0;
			if( getRequiredSingletonId() < rhs.getRequiredSingletonId() ) {
				return( -1 );
			}
			else if( getRequiredSingletonId() > rhs.getRequiredSingletonId() ) {
				return( 1 );
			}
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSysClusterByClusterIdxKey rhs ) {
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFSecPubSysCluster rhs ) {
			cmp = 0;
			if( getRequiredSingletonId() < rhs.getRequiredSingletonId() ) {
				return( -1 );
			}
			else if( getRequiredSingletonId() > rhs.getRequiredSingletonId() ) {
				return( 1 );
			}
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSysClusterHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if( getRequiredSingletonId() < rhs.getRequiredSingletonId() ) {
				return( -1 );
			}
			else if( getRequiredSingletonId() > rhs.getRequiredSingletonId() ) {
				return( 1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSysClusterH rhs ) {
			cmp = 0;
			if( getRequiredSingletonId() < rhs.getRequiredSingletonId() ) {
				return( -1 );
			}
			else if( getRequiredSingletonId() > rhs.getRequiredSingletonId() ) {
				return( 1 );
			}
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFSecPubSysClusterByClusterIdxKey rhs ) {
			if (getRequiredClusterId() != null) {
				if (rhs.getRequiredClusterId() != null) {
					cmp = getRequiredClusterId().compareTo( rhs.getRequiredClusterId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredClusterId() != null) {
				return( -1 );
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
	public void set( ICFSecPubSysCluster src ) {
		setJustPubSysCluster( src );
	}

	@Override
	public void setSysCluster( ICFSecPubSysCluster src ) {
		setJustPubRequiredSingletonId(src.getRequiredSingletonId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubRequiredContainerCluster(src.getRequiredContainerCluster());
		setJustPubRequiredClusterId(src.getRequiredClusterId());
	}

	@Override
	public void set( ICFSecPubSysClusterH src ) {
		setJustPubSysCluster( src );
	}

	@Override
	public void setSysCluster( ICFSecPubSysClusterH src ) {
		setJustPubRequiredSingletonId(src.getRequiredSingletonId());
		setJustPubRequiredContainerCluster(src.getRequiredContainerCluster());
		setJustPubRequiredClusterId(src.getRequiredClusterId());
	}

	@Override
	public void set( ICFSecPubSysCluster src ) {
		setJustPubSysCluster( src );
	}

	@Override
	public void setSysCluster( ICFSecPubSysCluster src ) {
		setJustPubRequiredSingletonId(src.getRequiredSingletonId());
		setJustPubRequiredRevision( src.getRequiredRevision() );
		setJustPubRequiredContainerCluster(src.getRequiredContainerCluster());
		setJustPubRequiredClusterId(src.getRequiredClusterId());
	}

	@Override
	public void set( ICFSecPubSysClusterH src ) {
		setJustPubSysCluster( src );
	}

	@Override
	public void setSysCluster( ICFSecPubSysClusterH src ) {
		setJustPubRequiredSingletonId(src.getRequiredSingletonId());
		setJustPubRequiredContainerCluster(src.getRequiredContainerCluster());
		setJustPubRequiredClusterId(src.getRequiredClusterId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = pkey.getXmlAttrFragment()
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\"" + "\"" + Integer.toString( getRequiredSingletonId() ) + "\"" + "\"" + getRequiredClusterId().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFSecPubBuffSysCluster" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
