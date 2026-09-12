// Description: Java 25 edit object instance implementation for CFSec SecSession.

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

public class CFSecSecSessionEditObj
	implements ICFSecSecSessionEditObj
{
	protected ICFSecSecSessionObj orig;
	protected ICFSecSecSession rec;
	protected ICFSecSecUserObj requiredContainerSecUser;
	protected ICFSecSecUserObj requiredParentSecProxy;

	public CFSecSecSessionEditObj( ICFSecSecSessionObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecSession origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerSecUser = null;
		requiredParentSecProxy = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecSessionTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSession" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecUserObj scope = getRequiredContainerSecUser();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		CFLibDbKeyHash256 val = rec.getRequiredSecSessionId();
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
	public ICFSecSecSessionObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecSessionObj retobj = getSchema().getSecSessionTableObj().realiseSecSession( (ICFSecSecSessionObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecSession().forget();
	}

	@Override
	public ICFSecSecSessionObj read() {
		ICFSecSecSessionObj retval = getOrigAsSecSession().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSessionObj read( boolean forceRead ) {
		ICFSecSecSessionObj retval = getOrigAsSecSession().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSessionObj create() {
		copyRecToOrig();
		ICFSecSecSessionObj retobj = ((ICFSecSchemaObj)getOrigAsSecSession().getSchema()).getSecSessionTableObj().createSecSession( getOrigAsSecSession() );
		if( retobj == getOrigAsSecSession() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecSessionEditObj update() {
		getSchema().getSecSessionTableObj().updateSecSession( (ICFSecSecSessionObj)this );
		return( null );
	}

	@Override
	public CFSecSecSessionEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecSessionTableObj().deleteSecSession( getOrigAsSecSession() );
		return( null );
	}

	@Override
	public ICFSecSecSessionTableObj getSecSessionTable() {
		return( orig.getSchema().getSecSessionTableObj() );
	}

	@Override
	public ICFSecSecSessionEditObj getEdit() {
		return( (ICFSecSecSessionEditObj)this );
	}

	@Override
	public ICFSecSecSessionEditObj getEditAsSecSession() {
		return( (ICFSecSecSessionEditObj)this );
	}

	@Override
	public ICFSecSecSessionEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecSessionObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecSessionObj getOrigAsSecSession() {
		return( (ICFSecSecSessionObj)orig );
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
	public ICFSecSecSession getRec() {
		if( rec == null ) {
			rec = getOrigAsSecSession().getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSession value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerSecUser = null;
			requiredParentSecProxy = null;
		}
	}

	@Override
	public ICFSecSecSession getSecSessionRec() {
		return( (ICFSecSecSession)getRec() );
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
	public ICFLibKeyHash256 getRequiredSecSessionId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredSecSessionId(ICFLibKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecUserId() {
		return( getSecSessionRec().getRequiredSecUserId() );
	}

	@Override
	public LocalDateTime getRequiredStart() {
		return( getSecSessionRec().getRequiredStart() );
	}

	@Override
	public void setRequiredStart( LocalDateTime value ) {
		if( getSecSessionRec().getRequiredStart() != value ) {
			getSecSessionRec().setRequiredStart( value );
		}
	}

	@Override
	public LocalDateTime getOptionalFinish() {
		return( getSecSessionRec().getOptionalFinish() );
	}

	@Override
	public void setOptionalFinish( LocalDateTime value ) {
		if( getSecSessionRec().getOptionalFinish() != value ) {
			getSecSessionRec().setOptionalFinish( value );
		}
	}

	@Override
	public ICFLibKeyHash256 getOptionalSecProxyId() {
		return( getSecSessionRec().getOptionalSecProxyId() );
	}

	@Override
	public ICFSecSecUserObj getRequiredContainerSecUser() {
		return( getRequiredContainerSecUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredContainerSecUser( boolean forceRead ) {
		if( forceRead || ( requiredContainerSecUser == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecUserObj obj = ((ICFSecSchemaObj)getOrigAsSecSession().getSchema()).getSecUserTableObj().readSecUserByIdIdx( getSecSessionRec().getRequiredSecUserId() );
				requiredContainerSecUser = obj;
				if( obj != null ) {
					requiredContainerSecUser = obj;
				}
			}
		}
		return( requiredContainerSecUser );
	}

	@Override
	public void setRequiredContainerSecUser( ICFSecSecUserObj value ) {
		if( rec == null ) {
			getSecSessionRec();
		}
		if( value != null ) {
			requiredContainerSecUser = value;
			getSecSessionRec().setRequiredContainerSecUser(value.getSecUserRec());
		}
		requiredContainerSecUser = value;
	}

	@Override
	public ICFSecSecUserObj getRequiredParentSecProxy() {
		return( getRequiredParentSecProxy( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentSecProxy( boolean forceRead ) {
		if( forceRead || ( requiredParentSecProxy == null ) ) {
			boolean anyMissing = false;
			if( getSecSessionRec().getOptionalSecProxyId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFSecSecUserObj obj = ((ICFSecSchemaObj)getOrigAsSecSession().getSchema()).getSecUserTableObj().readSecUserByIdIdx( getSecSessionRec().getOptionalSecProxyId() );
				requiredParentSecProxy = obj;
			}
		}
		return( requiredParentSecProxy );
	}

	@Override
	public void setRequiredParentSecProxy( ICFSecSecUserObj value ) {
		if( rec == null ) {
			getSecSessionRec();
		}
		if( value != null ) {
			requiredParentSecProxy = value;
			getSecSessionRec().setRequiredParentSecProxy(value.getSecUserRec());
		}
		else {
			requiredParentSecProxy = null;
			getSecSessionRec().setRequiredParentSecProxy((ICFSecSecUser)null);
		}
		requiredParentSecProxy = value;
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
		ICFSecSecSession origRec = getOrigAsSecSession().getSecSessionRec();
		ICFSecSecSession myRec = getSecSessionRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecSession origRec = getOrigAsSecSession().getSecSessionRec();
		ICFSecSecSession myRec = getSecSessionRec();
		myRec.set( origRec );
	}
}
