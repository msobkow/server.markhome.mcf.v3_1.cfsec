// Description: Java 25 edit object instance implementation for CFSec SecUserPassword.

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

package server.markhome.mcf.v3_1.cfsec.cfsecobj;

import java.math.*;
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
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

public class CFSecSecUserPasswordEditObj
	implements ICFSecSecUserPasswordEditObj
{
	protected ICFSecSecUserPasswordObj orig;
	protected ICFSecSecUserPassword rec;
	protected ICFSecSecUserObj requiredContainerUser;

	public CFSecSecUserPasswordEditObj( ICFSecSecUserPasswordObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecUserPassword origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerUser = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecUserPasswordTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecUserPassword" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecUserObj scope = getRequiredContainerUser();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		CFLibDbKeyHash256 val = rec.getRequiredSecUserId();
		if (val != null) {
			objName = val.toString();
		}
		else {
			objName = "";
		}
		return( objName );
	}

	@Override
	public ICFLibAnyObj getObjQualifier( Class qualifyingClass ) {
		ICFLibAnyObj container = this;
		if( qualifyingClass != null ) {
			while( container != null ) {
				if( container instanceof ICFSecClusterObj ) {
					break;
				}
				else if( container instanceof ICFSecTenantObj ) {
					break;
				}
				else if( qualifyingClass.isInstance( container ) ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		else {
			while( container != null ) {
				if( container instanceof ICFSecClusterObj ) {
					break;
				}
				else if( container instanceof ICFSecTenantObj ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		return( container );
	}

	@Override
	public ICFLibAnyObj getNamedObject( Class qualifyingClass, String objName ) {
		ICFLibAnyObj topContainer = getObjQualifier( qualifyingClass );
		if( topContainer == null ) {
			return( null );
		}
		ICFLibAnyObj namedObject = topContainer.getNamedObject( objName );
		return( namedObject );
	}

	@Override
	public ICFLibAnyObj getNamedObject( String objName ) {
		String nextName;
		String remainingName;
		ICFLibAnyObj subObj = null;
		ICFLibAnyObj retObj;
		int nextDot = objName.indexOf( '.' );
		if( nextDot >= 0 ) {
			nextName = objName.substring( 0, nextDot );
			remainingName = objName.substring( nextDot + 1 );
		}
		else {
			nextName = objName;
			remainingName = null;
		}
		if( remainingName == null ) {
			retObj = subObj;
		}
		else if( subObj == null ) {
			retObj = null;
		}
		else {
			retObj = subObj.getNamedObject( remainingName );
		}
		return( retObj );
	}

	@Override
	public String getObjQualifiedName() {
		String qualName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				qualName = containerName + "." + qualName;
				container = container.getObjScope();
			}
		}
		return( qualName );
	}

	@Override
	public String getObjFullName() {
		String fullName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				fullName = containerName + "." + fullName;
				container = container.getObjScope();
			}
		}
		return( fullName );
	}

	@Override
	public ICFSecSecUserPasswordObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecUserPasswordObj retobj = getSchema().getSecUserPasswordTableObj().realiseSecUserPassword( (ICFSecSecUserPasswordObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecUserPassword().forget();
	}

	@Override
	public ICFSecSecUserPasswordObj read() {
		ICFSecSecUserPasswordObj retval = getOrigAsSecUserPassword().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecUserPasswordObj read( boolean forceRead ) {
		ICFSecSecUserPasswordObj retval = getOrigAsSecUserPassword().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecUserPasswordObj create() {
		copyRecToOrig();
		ICFSecSecUserPasswordObj retobj = ((ICFSecSchemaObj)getOrigAsSecUserPassword().getSchema()).getSecUserPasswordTableObj().createSecUserPassword( getOrigAsSecUserPassword() );
		if( retobj == getOrigAsSecUserPassword() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecUserPasswordEditObj update() {
		getSchema().getSecUserPasswordTableObj().updateSecUserPassword( (ICFSecSecUserPasswordObj)this );
		return( null );
	}

	@Override
	public CFSecSecUserPasswordEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecUserPasswordTableObj().deleteSecUserPassword( getOrigAsSecUserPassword() );
		return( null );
	}

	@Override
	public ICFSecSecUserPasswordTableObj getSecUserPasswordTable() {
		return( orig.getSchema().getSecUserPasswordTableObj() );
	}

	@Override
	public ICFSecSecUserPasswordEditObj getEdit() {
		return( (ICFSecSecUserPasswordEditObj)this );
	}

	@Override
	public ICFSecSecUserPasswordEditObj getEditAsSecUserPassword() {
		return( (ICFSecSecUserPasswordEditObj)this );
	}

	@Override
	public ICFSecSecUserPasswordEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecUserPasswordObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecUserPasswordObj getOrigAsSecUserPassword() {
		return( (ICFSecSecUserPasswordObj)orig );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( orig.getSchema() );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		orig.setSchema(value);
	}

	@Override
	public ICFSecSecUserPassword getRec() {
		if( rec == null ) {
			rec = getOrigAsSecUserPassword().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecUserPassword().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecUserPassword value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerUser = null;
		}
	}

	@Override
	public ICFSecSecUserPassword getSecUserPasswordRec() {
		return( (ICFSecSecUserPassword)getRec() );
	}

	@Override
	public $implCommaIJavaOptAtomType$ getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( $implCommaIJavaOptAtomType$ value ) {
		orig.setPKey( value );
		copyPKeyToRec();
	}

	@Override
	public boolean getIsNew() {
		return( orig.getIsNew() );
	}

	@Override
	public void setIsNew( boolean value ) {
		orig.setIsNew( value );
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecUserId() {
		return( getPKey() );
	}

	@Override
	public LocalDateTime getRequiredPWSetStamp() {
		return( getSecUserPasswordRec().getRequiredPWSetStamp() );
	}

	@Override
	public void setRequiredPWSetStamp( LocalDateTime value ) {
		if( getSecUserPasswordRec().getRequiredPWSetStamp() != value ) {
			getSecUserPasswordRec().setRequiredPWSetStamp( value );
		}
	}

	@Override
	public String getRequiredPasswordHash() {
		return( getSecUserPasswordRec().getRequiredPasswordHash() );
	}

	@Override
	public void setRequiredPasswordHash( String value ) {
		if( getSecUserPasswordRec().getRequiredPasswordHash() != value ) {
			getSecUserPasswordRec().setRequiredPasswordHash( value );
		}
	}

	@Override
	public ICFSecSecUserObj getRequiredContainerUser() {
		return( getRequiredContainerUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredContainerUser( boolean forceRead ) {
		if( forceRead || ( requiredContainerUser == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecUserObj obj = ((ICFSecSchemaObj)getOrigAsSecUserPassword().getSchema()).getSecUserTableObj().readSecUserByIdIdx( getPKey() );
				requiredContainerUser = obj;
				if( obj != null ) {
					requiredContainerUser = obj;
				}
			}
		}
		return( requiredContainerUser );
	}

	@Override
	public void setRequiredContainerUser( ICFSecSecUserObj value ) {
		if( rec == null ) {
			getSecUserPasswordRec();
		}
		if( value != null ) {
			requiredContainerUser = value;
			getSecUserPasswordRec().setRequiredContainerUser(value.getSecUserRec());
		}
		requiredContainerUser = value;
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				setPKey(rec.getPKey());
			}
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecSecUserPassword origRec = getOrigAsSecUserPassword().getSecUserPasswordRec();
		ICFSecSecUserPassword myRec = getSecUserPasswordRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecUserPassword origRec = getOrigAsSecUserPassword().getSecUserPasswordRec();
		ICFSecSecUserPassword myRec = getSecUserPasswordRec();
		myRec.set( origRec );
	}
}
