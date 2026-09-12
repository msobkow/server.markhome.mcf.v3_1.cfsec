// Description: Java 25 base object instance implementation for TableInfo

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

public class CFSecTableInfoObj
	implements ICFSecTableInfoObj
{
	protected boolean isNew;
	protected ICFSecTableInfoEditObj edit;
	protected ICFSecSchemaObj schema;
	protected $implCommaIJavaOptAtomType$ pKey;
	protected ICFSecTableInfo rec;
	protected ICFSecTableInfoObj optionalParentSuperRef;
	protected List<ICFSecTableInfoObj> optionalChildrenSubRefs;

	public CFSecTableInfoObj() {
		isNew = true;
		optionalParentSuperRef = null;
	}

	public CFSecTableInfoObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		optionalParentSuperRef = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getTableInfoTableObj().getClassCode() );
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
		ICFSecTableInfoObj retobj = ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().realiseTableInfo(
			(ICFSecTableInfoObj)this );
		return( (ICFSecTableInfoObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().reallyDeepDisposeTableInfo( (ICFSecTableInfoObj)this );
	}

	@Override
	public ICFSecTableInfoObj read() {
		ICFSecTableInfoObj retobj = ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoByIdIdx( getPKey(), false );
		return( (ICFSecTableInfoObj)retobj );
	}

	@Override
	public ICFSecTableInfoObj read( boolean forceRead ) {
		ICFSecTableInfoObj retobj = ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoByIdIdx( getPKey(), forceRead );
		return( (ICFSecTableInfoObj)retobj );
	}

	@Override
	public ICFSecTableInfoTableObj getTableInfoTable() {
		return( ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = value;
	}

	@Override
	public ICFSecTableInfo getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableTableInfo().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecTableInfo value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecTableInfo ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecTableInfoRec" );
		}
		rec = value;
		copyRecToPKey();
		optionalParentSuperRef = null;
	}

	@Override
	public ICFSecTableInfo getTableInfoRec() {
		return( (ICFSecTableInfo)getRec() );
	}

	@Override
	public $implCommaIJavaOptAtomType$ getPKey() {
		return( pKey );
	}

	@Override
	public void setPKey( $implCommaIJavaOptAtomType$ value ) {
		if( pKey != value ) {
       		pKey = value;
			copyPKeyToRec();
		}
	}

	@Override
	public boolean getIsNew() {
		return( isNew );
	}

	@Override
	public void setIsNew( boolean value ) {
		isNew = value;
	}

	@Override
	public ICFSecTableInfoEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecTableInfoObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecTableInfoObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().lockTableInfo( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().newEditInstance( lockobj );
		return( (ICFSecTableInfoEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecTableInfoEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecTableInfoEditObj getEditAsTableInfo() {
		return( (ICFSecTableInfoEditObj)edit );
	}

	@Override
	public int getRequiredTableInfoId() {
		return( getPKey() );
	}

	@Override
	public ICFSecTableInfoObj getOptionalParentSuperRef() {
		return( getOptionalParentSuperRef( false ) );
	}

	@Override
	public ICFSecTableInfoObj getOptionalParentSuperRef( boolean forceRead ) {
		if( ( optionalParentSuperRef == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getTableInfoRec().getOptionalSuperName() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalParentSuperRef = ((ICFSecSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoByTableNameIdx( getTableInfoRec().getOptionalSuperName(), forceRead );
			}
		}
		return( optionalParentSuperRef );
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
	public String getRequiredSchemaName() {
		return( getTableInfoRec().getRequiredSchemaName() );
	}

	@Override
	public String getRequiredTableName() {
		return( getTableInfoRec().getRequiredTableName() );
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
	public int getRequiredRuntimeClassCode() {
		return( getTableInfoRec().getRequiredRuntimeClassCode() );
	}

	@Override
	public boolean getRequiredHasHistory() {
		return( getTableInfoRec().getRequiredHasHistory() );
	}

	@Override
	public boolean getRequiredIsMutable() {
		return( getTableInfoRec().getRequiredIsMutable() );
	}

	@Override
	public String getRequiredSecScopeName() {
		return( getTableInfoRec().getRequiredSecScopeName() );
	}

	@Override
	public String getRequiredCodeVis() {
		return( getTableInfoRec().getRequiredCodeVis() );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
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
}
