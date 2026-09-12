// Description: Java 25 base object instance implementation for ISOCtryLang

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

public class CFSecISOCtryLangObj
	implements ICFSecISOCtryLangObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecISOCtryLangEditObj edit;
	protected ICFSecSchemaObj schema;
	protected ICFSecISOCtryLangPKey pKey;
	protected ICFSecISOCtryLang rec;
	protected ICFSecISOCtryObj requiredContainerCtry;
	protected ICFSecISOLangObj requiredParentLang;

	public CFSecISOCtryLangObj() {
		isNew = true;
		requiredContainerCtry = null;
		requiredParentLang = null;
	}

	public CFSecISOCtryLangObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerCtry = null;
		requiredParentLang = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getISOCtryLangTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "ISOCtryLang" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecISOCtryObj scope = getRequiredContainerCtry();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		short val = rec.getRequiredISOLangId();
		objName = Short.toString( val );
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
	public ICFSecISOCtryLangObj realise() {
		ICFSecISOCtryLangObj retobj = ((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj().realiseISOCtryLang(
			(ICFSecISOCtryLangObj)this );
		return( (ICFSecISOCtryLangObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj().reallyDeepDisposeISOCtryLang( (ICFSecISOCtryLangObj)this );
	}

	@Override
	public ICFSecISOCtryLangObj read() {
		ICFSecISOCtryLangObj retobj = ((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj().readISOCtryLangByIdIdx( getPKey().getRequiredISOCtryId(),
			getPKey().getRequiredISOLangId(), false );
		return( (ICFSecISOCtryLangObj)retobj );
	}

	@Override
	public ICFSecISOCtryLangObj read( boolean forceRead ) {
		ICFSecISOCtryLangObj retobj = ((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj().readISOCtryLangByIdIdx( getPKey().getRequiredISOCtryId(),
			getPKey().getRequiredISOLangId(), forceRead );
		return( (ICFSecISOCtryLangObj)retobj );
	}

	@Override
	public ICFSecISOCtryLangTableObj getISOCtryLangTable() {
		return( ((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj() );
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
	public ICFSecISOCtryLang getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableISOCtryLang().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey().getRequiredISOCtryId(),
						getPKey().getRequiredISOLangId() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecISOCtryLang value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecISOCtryLang ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecISOCtryLangRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerCtry = null;
		requiredParentLang = null;
	}

	@Override
	public ICFSecISOCtryLang getISOCtryLangRec() {
		return( (ICFSecISOCtryLang)getRec() );
	}

	@Override
	public ICFSecISOCtryLangPKey getPKey() {
		if( pKey == null ) {
			pKey = getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newPKey();
		}
		return( pKey );
	}

	@Override
	public void setPKey( ICFSecISOCtryLangPKey value ) {
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
	public ICFSecISOCtryLangEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecISOCtryLangObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecISOCtryLangObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj().lockISOCtryLang( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getISOCtryLangTableObj().newEditInstance( lockobj );
		return( (ICFSecISOCtryLangEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecISOCtryLangEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecISOCtryLangEditObj getEditAsISOCtryLang() {
		return( (ICFSecISOCtryLangEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecISOCtryLang rec = getRec();
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
			ICFSecISOCtryLang rec = getRec();
			updatedBy = ((ICFSecSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public short getRequiredISOCtryId() {
		return( getPKey().getRequiredISOCtryId() );
	}

	@Override
	public short getRequiredISOLangId() {
		return( getPKey().getRequiredISOLangId() );
	}

	@Override
	public ICFSecISOCtryObj getRequiredContainerCtry() {
		return( getRequiredContainerCtry( false ) );
	}

	@Override
	public ICFSecISOCtryObj getRequiredContainerCtry( boolean forceRead ) {
		if( ( requiredContainerCtry == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerCtry = ((ICFSecSchemaObj)getSchema()).getISOCtryTableObj().readISOCtryByIdIdx( getPKey().getRequiredISOCtryId(), forceRead );
			}
		}
		return( requiredContainerCtry );
	}

	@Override
	public ICFSecISOLangObj getRequiredParentLang() {
		return( getRequiredParentLang( false ) );
	}

	@Override
	public ICFSecISOLangObj getRequiredParentLang( boolean forceRead ) {
		if( ( requiredParentLang == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredParentLang = ((ICFSecSchemaObj)getSchema()).getISOLangTableObj().readISOLangByIdIdx( getPKey().getRequiredISOLangId(), forceRead );
			}
		}
		return( requiredParentLang );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredISOCtryId(getPKey().getRequiredISOCtryId());
			rec.getPKey().setRequiredISOLangId(getPKey().getRequiredISOLangId());
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredISOCtryId(rec.getPKey().getRequiredISOCtryId());
			getPKey().setRequiredISOLangId(rec.getPKey().getRequiredISOLangId());
		}
	}
}
