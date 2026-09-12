// Description: Java 25 edit object instance implementation for CFSec TableInfo.

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

public class CFSecTableInfoEditObj
	implements ICFSecTableInfoEditObj
{
	protected ICFSecTableInfoObj orig;
	protected ICFSecTableInfo rec;
	protected ICFSecTableInfoObj optionalParentSuperRef;
	protected List<ICFSecTableInfoObj> optionalChildrenSubRefs;

	public CFSecTableInfoEditObj( ICFSecTableInfoObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecTableInfo origRec = orig.getRec();
		rec.set( origRec );
		optionalParentSuperRef = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)orig.getSchema()).getTableInfoTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "TableInfo" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredTableName();
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
	public ICFSecTableInfoObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecTableInfoObj retobj = getSchema().getTableInfoTableObj().realiseTableInfo( (ICFSecTableInfoObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsTableInfo().forget();
	}

	@Override
	public ICFSecTableInfoObj read() {
		ICFSecTableInfoObj retval = getOrigAsTableInfo().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecTableInfoObj read( boolean forceRead ) {
		ICFSecTableInfoObj retval = getOrigAsTableInfo().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecTableInfoObj create() {
		copyRecToOrig();
		ICFSecTableInfoObj retobj = ((ICFSecSchemaObj)getOrigAsTableInfo().getSchema()).getTableInfoTableObj().createTableInfo( getOrigAsTableInfo() );
		if( retobj == getOrigAsTableInfo() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecTableInfoEditObj update() {
		getSchema().getTableInfoTableObj().updateTableInfo( (ICFSecTableInfoObj)this );
		return( null );
	}

	@Override
	public CFSecTableInfoEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getTableInfoTableObj().deleteTableInfo( getOrigAsTableInfo() );
		return( null );
	}

	@Override
	public ICFSecTableInfoTableObj getTableInfoTable() {
		return( orig.getSchema().getTableInfoTableObj() );
	}

	@Override
	public ICFSecTableInfoEditObj getEdit() {
		return( (ICFSecTableInfoEditObj)this );
	}

	@Override
	public ICFSecTableInfoEditObj getEditAsTableInfo() {
		return( (ICFSecTableInfoEditObj)this );
	}

	@Override
	public ICFSecTableInfoEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecTableInfoObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecTableInfoObj getOrigAsTableInfo() {
		return( (ICFSecTableInfoObj)orig );
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
	public ICFSecTableInfo getRec() {
		if( rec == null ) {
			rec = getOrigAsTableInfo().getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecTableInfo value ) {
		if( rec != value ) {
			rec = value;
			optionalParentSuperRef = null;
		}
	}

	@Override
	public ICFSecTableInfo getTableInfoRec() {
		return( (ICFSecTableInfo)getRec() );
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
	public int getRequiredTableInfoId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredTableInfoId(int value) {
		if (getPKey() != value) {
			setPKey(value);
		}
	}

	@Override
	public String getRequiredSchemaName() {
		return( getTableInfoRec().getRequiredSchemaName() );
	}

	@Override
	public void setRequiredSchemaName( String value ) {
		if( getTableInfoRec().getRequiredSchemaName() != value ) {
			getTableInfoRec().setRequiredSchemaName( value );
		}
	}

	@Override
	public String getRequiredTableName() {
		return( getTableInfoRec().getRequiredTableName() );
	}

	@Override
	public void setRequiredTableName( String value ) {
		if( getTableInfoRec().getRequiredTableName() != value ) {
			getTableInfoRec().setRequiredTableName( value );
			optionalParentSuperRef = null;
			optionalChildrenSubRefs = null;
		}
	}

	@Override
	public String getOptionalSuperName() {
		return( getTableInfoRec().getOptionalSuperName() );
	}

	@Override
	public int getRequiredBackingClassCode() {
		return( getTableInfoRec().getRequiredBackingClassCode() );
	}

	@Override
	public void setRequiredBackingClassCode( int value ) {
		if( getTableInfoRec().getRequiredBackingClassCode() != value ) {
			getTableInfoRec().setRequiredBackingClassCode( value );
		}
	}

	@Override
	public int getRequiredRuntimeClassCode() {
		return( getTableInfoRec().getRequiredRuntimeClassCode() );
	}

	@Override
	public void setRequiredRuntimeClassCode( int value ) {
		if( getTableInfoRec().getRequiredRuntimeClassCode() != value ) {
			getTableInfoRec().setRequiredRuntimeClassCode( value );
		}
	}

	@Override
	public boolean getRequiredHasHistory() {
		return( getTableInfoRec().getRequiredHasHistory() );
	}

	@Override
	public void setRequiredHasHistory( boolean value ) {
		if( getTableInfoRec().getRequiredHasHistory() != value ) {
			getTableInfoRec().setRequiredHasHistory( value );
		}
	}

	@Override
	public boolean getRequiredIsMutable() {
		return( getTableInfoRec().getRequiredIsMutable() );
	}

	@Override
	public void setRequiredIsMutable( boolean value ) {
		if( getTableInfoRec().getRequiredIsMutable() != value ) {
			getTableInfoRec().setRequiredIsMutable( value );
		}
	}

	@Override
	public String getRequiredSecScopeName() {
		return( getTableInfoRec().getRequiredSecScopeName() );
	}

	@Override
	public void setRequiredSecScopeName( String value ) {
		if( getTableInfoRec().getRequiredSecScopeName() != value ) {
			getTableInfoRec().setRequiredSecScopeName( value );
		}
	}

	@Override
	public String getRequiredCodeVis() {
		return( getTableInfoRec().getRequiredCodeVis() );
	}

	@Override
	public void setRequiredCodeVis( String value ) {
		if( getTableInfoRec().getRequiredCodeVis() != value ) {
			getTableInfoRec().setRequiredCodeVis( value );
		}
	}

	@Override
	public ICFSecTableInfoObj getOptionalParentSuperRef() {
		return( getOptionalParentSuperRef( false ) );
	}

	@Override
	public ICFSecTableInfoObj getOptionalParentSuperRef( boolean forceRead ) {
		if( forceRead || ( optionalParentSuperRef == null ) ) {
			boolean anyMissing = false;
			if( getTableInfoRec().getOptionalSuperName() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFSecTableInfoObj obj = ((ICFSecSchemaObj)getOrigAsTableInfo().getSchema()).getTableInfoTableObj().readTableInfoByTableNameIdx( getTableInfoRec().getOptionalSuperName() );
				optionalParentSuperRef = obj;
			}
		}
		return( optionalParentSuperRef );
	}

	@Override
	public void setOptionalParentSuperRef( ICFSecTableInfoObj value ) {
		if( rec == null ) {
			getTableInfoRec();
		}
		if( value != null ) {
			optionalParentSuperRef = value;
			getTableInfoRec().setOptionalParentSuperRef(value.getTableInfoRec());
		}
		else {
			optionalParentSuperRef = null;
			getTableInfoRec().setOptionalParentSuperRef((ICFSecTableInfo)null);
		}
		optionalParentSuperRef = value;
	}

	@Override
	public List<ICFSecTableInfoObj> getOptionalChildrenSubRefs() {
		List<ICFSecTableInfoObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoBySuperNameIdx( getTableInfoRec().getRequiredTableName(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecTableInfoObj> getOptionalChildrenSubRefs( boolean forceRead ) {
		List<ICFSecTableInfoObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoBySuperNameIdx( getTableInfoRec().getRequiredTableName(),
			forceRead );
		return( retval );
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
		ICFSecTableInfo origRec = getOrigAsTableInfo().getTableInfoRec();
		ICFSecTableInfo myRec = getTableInfoRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecTableInfo origRec = getOrigAsTableInfo().getTableInfoRec();
		ICFSecTableInfo myRec = getTableInfoRec();
		myRec.set( origRec );
	}
}
