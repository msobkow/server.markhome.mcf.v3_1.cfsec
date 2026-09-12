// Description: Java 25 base object instance implementation for SecUserPWHistory

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

public class CFSecSecUserPWHistoryObj
	implements ICFSecSecUserPWHistoryObj
{
	protected boolean isNew;
	protected ICFSecSecUserPWHistoryEditObj edit;
	protected ICFSecSchemaObj schema;
	protected ICFSecSecUserPWHistoryPKey pKey;
	protected ICFSecSecUserPWHistory rec;

	public CFSecSecUserPWHistoryObj() {
		isNew = true;
	}

	public CFSecSecUserPWHistoryObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecUserPWHistoryTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecUserPWHistory" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
	}

	@Override
	public String getObjName() {
		String objName;
		LocalDateTime val = rec.getRequiredPWSetStamp();
		if (val != null) {
			objName = CFLibXmlUtil.formatTimestamp(val);
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
	public ICFSecSecUserPWHistoryObj realise() {
		ICFSecSecUserPWHistoryObj retobj = ((ICFSecSchemaObj)getSchema()).getSecUserPWHistoryTableObj().realiseSecUserPWHistory(
			(ICFSecSecUserPWHistoryObj)this );
		return( (ICFSecSecUserPWHistoryObj)retobj );
	}

	@Override
	public void forget() {
		((ICFSecSchemaObj)getSchema()).getSecUserPWHistoryTableObj().reallyDeepDisposeSecUserPWHistory( (ICFSecSecUserPWHistoryObj)this );
	}

	@Override
	public ICFSecSecUserPWHistoryObj read() {
		ICFSecSecUserPWHistoryObj retobj = ((ICFSecSchemaObj)getSchema()).getSecUserPWHistoryTableObj().readSecUserPWHistoryByIdIdx( getPKey().getRequiredSecUserId(),
			getPKey().getRequiredPWSetStamp(), false );
		return( (ICFSecSecUserPWHistoryObj)retobj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj read( boolean forceRead ) {
		ICFSecSecUserPWHistoryObj retobj = ((ICFSecSchemaObj)getSchema()).getSecUserPWHistoryTableObj().readSecUserPWHistoryByIdIdx( getPKey().getRequiredSecUserId(),
			getPKey().getRequiredPWSetStamp(), forceRead );
		return( (ICFSecSecUserPWHistoryObj)retobj );
	}

	@Override
	public ICFSecSecUserPWHistoryTableObj getSecUserPWHistoryTable() {
		return( ((ICFSecSchemaObj)getSchema()).getSecUserPWHistoryTableObj() );
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
	public ICFSecSecUserPWHistory getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecUserPWHistory().readDerivedByIdIdx( ((ICFSecSchemaObj)getSchema()).getAuthorization(),
						getPKey().getRequiredSecUserId(),
						getPKey().getRequiredPWSetStamp() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecUserPWHistory value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecUserPWHistory ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecUserPWHistoryRec" );
		}
		rec = value;
		copyRecToPKey();
	}

	@Override
	public ICFSecSecUserPWHistory getSecUserPWHistoryRec() {
		return( (ICFSecSecUserPWHistory)getRec() );
	}

	@Override
	public ICFSecSecUserPWHistoryPKey getPKey() {
		if( pKey == null ) {
			pKey = getSchema().getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newPKey();
		}
		return( pKey );
	}

	@Override
	public void setPKey( ICFSecSecUserPWHistoryPKey value ) {
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
	public ICFSecSecUserPWHistoryEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecUserPWHistoryObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecUserPWHistoryObj)this;
		}
		else {
			lockobj = ((ICFSecSchemaObj)getSchema()).getSecUserPWHistoryTableObj().lockSecUserPWHistory( getPKey() );
		}
		edit = ((ICFSecSchemaObj)getSchema()).getSecUserPWHistoryTableObj().newEditInstance( lockobj );
		return( (ICFSecSecUserPWHistoryEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecUserPWHistoryEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecUserPWHistoryEditObj getEditAsSecUserPWHistory() {
		return( (ICFSecSecUserPWHistoryEditObj)edit );
	}

	@Override
	public ICFLibKeyHash256 getRequiredSecUserId() {
		return( getPKey().getRequiredSecUserId() );
	}

	@Override
	public LocalDateTime getRequiredPWSetStamp() {
		return( getPKey().getRequiredPWSetStamp() );
	}

	@Override
	public LocalDateTime getRequiredPWReplacedStamp() {
		return( getSecUserPWHistoryRec().getRequiredPWReplacedStamp() );
	}

	@Override
	public String getRequiredPasswordHash() {
		return( getSecUserPWHistoryRec().getRequiredPasswordHash() );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecUserId(getPKey().getRequiredSecUserId());
			rec.getPKey().setRequiredPWSetStamp(getPKey().getRequiredPWSetStamp());
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecUserId(rec.getPKey().getRequiredSecUserId());
			getPKey().setRequiredPWSetStamp(rec.getPKey().getRequiredPWSetStamp());
		}
	}
}
