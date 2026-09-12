// Description: Java 25 edit object instance implementation for CFSec ISOLang.

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

public class CFSecISOLangEditObj
	implements ICFSecISOLangEditObj
{
	protected ICFSecISOLangObj orig;
	protected ICFSecISOLang rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected List<ICFSecISOCtryLangObj> optionalChildrenCtry;

	public CFSecISOLangEditObj( ICFSecISOLangObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecISOLang origRec = orig.getRec();
		rec.set( origRec );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecISOLang rec = getRec();
			createdBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getCreatedByUserId() );
		}
		return( createdBy );
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( getRec().getCreatedAt() );
	}

	@Override
	public ICFSecSecUserObj getUpdatedBy() {
		if( updatedBy == null ) {
			ICFSecISOLang rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public void setCreatedBy( ICFSecSecUserObj value ) {
		createdBy = value;
		if( value != null ) {
			getRec().setCreatedByUserId( value.getRequiredSecUserId() );
		}
	}

	@Override
	public void setCreatedAt( LocalDateTime value ) {
		getRec().setCreatedAt( value );
	}

	@Override
	public void setUpdatedBy( ICFSecSecUserObj value ) {
		updatedBy = value;
		if( value != null ) {
			getRec().setUpdatedByUserId( value.getRequiredSecUserId() );
		}
	}

	@Override
	public void setUpdatedAt( LocalDateTime value ) {
		getRec().setUpdatedAt( value );
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)orig.getSchema()).getISOLangTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "ISOLang" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredISO6392Code();
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
	public ICFSecISOLangObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecISOLangObj retobj = getSchema().getISOLangTableObj().realiseISOLang( (ICFSecISOLangObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsISOLang().forget();
	}

	@Override
	public ICFSecISOLangObj read() {
		ICFSecISOLangObj retval = getOrigAsISOLang().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecISOLangObj read( boolean forceRead ) {
		ICFSecISOLangObj retval = getOrigAsISOLang().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecISOLangObj create() {
		copyRecToOrig();
		ICFSecISOLangObj retobj = ((ICFSecSchemaObj)getOrigAsISOLang().getSchema()).getISOLangTableObj().createISOLang( getOrigAsISOLang() );
		if( retobj == getOrigAsISOLang() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecISOLangEditObj update() {
		getSchema().getISOLangTableObj().updateISOLang( (ICFSecISOLangObj)this );
		return( null );
	}

	@Override
	public CFSecISOLangEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getISOLangTableObj().deleteISOLang( getOrigAsISOLang() );
		return( null );
	}

	@Override
	public ICFSecISOLangTableObj getISOLangTable() {
		return( orig.getSchema().getISOLangTableObj() );
	}

	@Override
	public ICFSecISOLangEditObj getEdit() {
		return( (ICFSecISOLangEditObj)this );
	}

	@Override
	public ICFSecISOLangEditObj getEditAsISOLang() {
		return( (ICFSecISOLangEditObj)this );
	}

	@Override
	public ICFSecISOLangEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecISOLangObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecISOLangObj getOrigAsISOLang() {
		return( (ICFSecISOLangObj)orig );
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
	public ICFSecISOLang getRec() {
		if( rec == null ) {
			rec = getOrigAsISOLang().getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecISOLang value ) {
		if( rec != value ) {
			rec = value;
		}
	}

	@Override
	public ICFSecISOLang getISOLangRec() {
		return( (ICFSecISOLang)getRec() );
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
	public short getRequiredISOLangId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredISOLangId(short value) {
		if (getPKey() != value) {
			setPKey(value);
			optionalChildrenCtry = null;
		}
	}

	@Override
	public String getRequiredISO6392Code() {
		return( getISOLangRec().getRequiredISO6392Code() );
	}

	@Override
	public void setRequiredISO6392Code( String value ) {
		if( getISOLangRec().getRequiredISO6392Code() != value ) {
			getISOLangRec().setRequiredISO6392Code( value );
		}
	}

	@Override
	public String getOptionalISO6391Code() {
		return( getISOLangRec().getOptionalISO6391Code() );
	}

	@Override
	public void setOptionalISO6391Code( String value ) {
		if( getISOLangRec().getOptionalISO6391Code() != value ) {
			getISOLangRec().setOptionalISO6391Code( value );
		}
	}

	@Override
	public String getRequiredEnglishName() {
		return( getISOLangRec().getRequiredEnglishName() );
	}

	@Override
	public void setRequiredEnglishName( String value ) {
		if( getISOLangRec().getRequiredEnglishName() != value ) {
			getISOLangRec().setRequiredEnglishName( value );
		}
	}

	@Override
	public List<ICFSecISOCtryLangObj> getOptionalChildrenCtry() {
		List<ICFSecISOCtryLangObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj().readISOCtryLangByLangIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecISOCtryLangObj> getOptionalChildrenCtry( boolean forceRead ) {
		List<ICFSecISOCtryLangObj> retval;
		retval = ((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj().readISOCtryLangByLangIdx( getPKey(),
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
		ICFSecISOLang origRec = getOrigAsISOLang().getISOLangRec();
		ICFSecISOLang myRec = getISOLangRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecISOLang origRec = getOrigAsISOLang().getISOLangRec();
		ICFSecISOLang myRec = getISOLangRec();
		myRec.set( origRec );
	}
}
