// Description: Java 25 Table Object implementation for SecTentGrp.

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

import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprot.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

public class CFSecSecTentGrpTableObj
	implements ICFSecSecTentGrpTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecTentGrp.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecTentGrp.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> allSecTentGrp;
	private Map< ICFSecSecTentGrpByTenantIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > > indexByTenantIdx;
	private Map< ICFSecSecTentGrpByNameIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > > indexByNameIdx;
	private Map< ICFSecSecTentGrpByUNameIdxKey,
		ICFSecSecTentGrpObj > indexByUNameIdx;
	public static String TABLE_NAME = "SecTentGrp";
	public static String TABLE_DBNAME = "sectentgrp";

	public CFSecSecTentGrpTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj>();
		allSecTentGrp = null;
		indexByTenantIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
	}

	public CFSecSecTentGrpTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj>();
		allSecTentGrp = null;
		indexByTenantIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecTentGrpTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFSecSecTentGrpTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = (ICFSecSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSecTentGrp = null;
		indexByTenantIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
		List<ICFSecSecTentGrpObj> toForget = new LinkedList<ICFSecSecTentGrpObj>();
		ICFSecSecTentGrpObj cur = null;
		Iterator<ICFSecSecTentGrpObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecTentGrpObj.
	 */
	@Override
	public ICFSecSecTentGrpObj newInstance() {
		ICFSecSecTentGrpObj inst = new CFSecSecTentGrpObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecTentGrpObj.
	 */
	@Override
	public ICFSecSecTentGrpEditObj newEditInstance( ICFSecSecTentGrpObj orig ) {
		ICFSecSecTentGrpEditObj edit = new CFSecSecTentGrpEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecTentGrpObj realiseSecTentGrp( ICFSecSecTentGrpObj Obj ) {
		ICFSecSecTentGrpObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecTentGrpObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecTentGrpObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFSecSecTentGrpByTenantIdxKey keyTenantIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.remove( keepObj.getPKey() );
					if( mapTenantIdx.size() <= 0 ) {
						indexByTenantIdx.remove( keyTenantIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentGrpByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecTentGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFSecSecTentGrpByTenantIdxKey keyTenantIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentGrpByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecTentGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allSecTentGrp != null ) {
				allSecTentGrp.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecTentGrp != null ) {
				allSecTentGrp.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFSecSecTentGrpByTenantIdxKey keyTenantIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentGrpByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecTentGrpByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByUNameIdxKey();
				keyUNameIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecTentGrpObj createSecTentGrp( ICFSecSecTentGrpObj Obj ) {
		ICFSecSecTentGrpObj obj = Obj;
		ICFSecSecTentGrp rec = obj.getSecTentGrpRec();
		schema.getCFSecBackingStore().getTableSecTentGrp().createSecTentGrp(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpObj readSecTentGrp( $implCommaIJavaOptAtomType$ pkey ) {
		return( readSecTentGrp( pkey, false ) );
	}

	@Override
	public ICFSecSecTentGrpObj readSecTentGrp( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecSecTentGrpObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecTentGrp readRec = schema.getCFSecBackingStore().getTableSecTentGrp().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecTentGrpTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecTentGrpObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpObj readCachedSecTentGrp( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecTentGrpObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecTentGrp( ICFSecSecTentGrpObj obj )
	{
		final String S_ProcName = "CFSecSecTentGrpTableObj.reallyDeepDisposeSecTentGrp() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecTentGrpObj existing = readCachedSecTentGrp( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecTentGrpByTenantIdxKey keyTenantIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByTenantIdxKey();
		keyTenantIdx.setRequiredTenantId( existing.getRequiredTenantId() );

		ICFSecSecTentGrpByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByNameIdxKey();
		keyNameIdx.setRequiredName( existing.getRequiredName() );

		ICFSecSecTentGrpByUNameIdxKey keyUNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByUNameIdxKey();
		keyUNameIdx.setRequiredTenantId( existing.getRequiredTenantId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


					schema.getSecTentGrpMembTableObj().deepDisposeSecTentGrpMembByTentGrpIdx( existing.getRequiredSecTentGrpId() );

		if( indexByTenantIdx != null ) {
			if( indexByTenantIdx.containsKey( keyTenantIdx ) ) {
				indexByTenantIdx.get( keyTenantIdx ).remove( pkey );
				if( indexByTenantIdx.get( keyTenantIdx ).size() <= 0 ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}
		}

		if( indexByNameIdx != null ) {
			if( indexByNameIdx.containsKey( keyNameIdx ) ) {
				indexByNameIdx.get( keyNameIdx ).remove( pkey );
				if( indexByNameIdx.get( keyNameIdx ).size() <= 0 ) {
					indexByNameIdx.remove( keyNameIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


	}
	@Override
	public void deepDisposeSecTentGrp( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecTentGrpObj obj = readCachedSecTentGrp( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecTentGrpObj lockSecTentGrp( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecTentGrpObj locked = null;
		ICFSecSecTentGrp lockRec = schema.getCFSecBackingStore().getTableSecTentGrp().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecTentGrpTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecTentGrpObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecTentGrp", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readAllSecTentGrp() {
		return( readAllSecTentGrp( false ) );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readAllSecTentGrp( boolean forceRead ) {
		final String S_ProcName = "readAllSecTentGrp";
		if( ( allSecTentGrp == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecTentGrpObj>();
			allSecTentGrp = map;
			ICFSecSecTentGrp[] recList = schema.getCFSecBackingStore().getTableSecTentGrp().readAllDerived( null );
			ICFSecSecTentGrp rec;
			ICFSecSecTentGrpObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpObj realised = (ICFSecSecTentGrpObj)obj.realise();
			}
		}
		int len = allSecTentGrp.size();
		ICFSecSecTentGrpObj arr[] = new ICFSecSecTentGrpObj[len];
		Iterator<ICFSecSecTentGrpObj> valIter = allSecTentGrp.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecTentGrpObj> arrayList = new ArrayList<ICFSecSecTentGrpObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpObj> cmp = new Comparator<ICFSecSecTentGrpObj>() {
			@Override
			public int compare( ICFSecSecTentGrpObj lhs, ICFSecSecTentGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readCachedAllSecTentGrp() {
		final String S_ProcName = "readCachedAllSecTentGrp";
		ArrayList<ICFSecSecTentGrpObj> arrayList = new ArrayList<ICFSecSecTentGrpObj>();
		if( allSecTentGrp != null ) {
			int len = allSecTentGrp.size();
			ICFSecSecTentGrpObj arr[] = new ICFSecSecTentGrpObj[len];
			Iterator<ICFSecSecTentGrpObj> valIter = allSecTentGrp.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFSecSecTentGrpObj> cmp = new Comparator<ICFSecSecTentGrpObj>() {
			public int compare( ICFSecSecTentGrpObj lhs, ICFSecSecTentGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecTentGrpObj readSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId )
	{
		return( readSecTentGrpByIdIdx( SecTentGrpId,
			false ) );
	}

	@Override
	public ICFSecSecTentGrpObj readSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId, boolean forceRead )
	{
		ICFSecSecTentGrpObj obj = readSecTentGrp( SecTentGrpId, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId )
	{
		return( readSecTentGrpByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentGrpByTenantIdx";
		ICFSecSecTentGrpByTenantIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFSecSecTentGrpByTenantIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj>();
			ICFSecSecTentGrpObj obj;
			ICFSecSecTentGrp[] recList = schema.getCFSecBackingStore().getTableSecTentGrp().readDerivedByTenantIdx( null,
				TenantId );
			ICFSecSecTentGrp rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentGrpTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpObj realised = (ICFSecSecTentGrpObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentGrpObj arr[] = new ICFSecSecTentGrpObj[len];
		Iterator<ICFSecSecTentGrpObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecTentGrpObj> arrayList = new ArrayList<ICFSecSecTentGrpObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpObj> cmp = new Comparator<ICFSecSecTentGrpObj>() {
			@Override
			public int compare( ICFSecSecTentGrpObj lhs, ICFSecSecTentGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readSecTentGrpByNameIdx( String Name )
	{
		return( readSecTentGrpByNameIdx( Name,
			false ) );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readSecTentGrpByNameIdx( String Name,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentGrpByNameIdx";
		ICFSecSecTentGrpByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByNameIdxKey();
		key.setRequiredName( Name );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecTentGrpByNameIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj>();
			ICFSecSecTentGrpObj obj;
			ICFSecSecTentGrp[] recList = schema.getCFSecBackingStore().getTableSecTentGrp().readDerivedByNameIdx( null,
				Name );
			ICFSecSecTentGrp rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentGrpTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpObj realised = (ICFSecSecTentGrpObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentGrpObj arr[] = new ICFSecSecTentGrpObj[len];
		Iterator<ICFSecSecTentGrpObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecTentGrpObj> arrayList = new ArrayList<ICFSecSecTentGrpObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpObj> cmp = new Comparator<ICFSecSecTentGrpObj>() {
			@Override
			public int compare( ICFSecSecTentGrpObj lhs, ICFSecSecTentGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecTentGrpObj readSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name )
	{
		return( readSecTentGrpByUNameIdx( TenantId,
			Name,
			false ) );
	}

	@Override
	public ICFSecSecTentGrpObj readSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecTentGrpByUNameIdxKey,
				ICFSecSecTentGrpObj >();
		}
		ICFSecSecTentGrpByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByUNameIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredName( Name );
		ICFSecSecTentGrpObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFSecSecTentGrp rec = schema.getCFSecBackingStore().getTableSecTentGrp().readDerivedByUNameIdx( null,
				TenantId,
				Name );
			if( rec != null ) {
				obj = schema.getSecTentGrpTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecTentGrpObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpObj readCachedSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId )
	{
		ICFSecSecTentGrpObj obj = null;
		obj = readCachedSecTentGrp( SecTentGrpId );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readCachedSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedSecTentGrpByTenantIdx";
		ICFSecSecTentGrpByTenantIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFSecSecTentGrpObj> arrayList = new ArrayList<ICFSecSecTentGrpObj>();
		if( indexByTenantIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFSecSecTentGrpObj arr[] = new ICFSecSecTentGrpObj[len];
				Iterator<ICFSecSecTentGrpObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecTentGrpObj obj;
			Iterator<ICFSecSecTentGrpObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentGrpObj> cmp = new Comparator<ICFSecSecTentGrpObj>() {
			@Override
			public int compare( ICFSecSecTentGrpObj lhs, ICFSecSecTentGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecTentGrpObj> readCachedSecTentGrpByNameIdx( String Name )
	{
		final String S_ProcName = "readCachedSecTentGrpByNameIdx";
		ICFSecSecTentGrpByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByNameIdxKey();
		key.setRequiredName( Name );
		ArrayList<ICFSecSecTentGrpObj> arrayList = new ArrayList<ICFSecSecTentGrpObj>();
		if( indexByNameIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecTentGrpObj arr[] = new ICFSecSecTentGrpObj[len];
				Iterator<ICFSecSecTentGrpObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecTentGrpObj obj;
			Iterator<ICFSecSecTentGrpObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentGrpObj> cmp = new Comparator<ICFSecSecTentGrpObj>() {
			@Override
			public int compare( ICFSecSecTentGrpObj lhs, ICFSecSecTentGrpObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					$implCommaIJavaOptAtomType$ lhsPKey = lhs.getPKey();
					$implCommaIJavaOptAtomType$ rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecTentGrpObj readCachedSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name )
	{
		ICFSecSecTentGrpObj obj = null;
		ICFSecSecTentGrpByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByUNameIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFSecSecTentGrpObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFSecSecTentGrpObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId )
	{
		ICFSecSecTentGrpObj obj = readCachedSecTentGrpByIdIdx( SecTentGrpId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeSecTentGrpByTenantIdx";
		ICFSecSecTentGrpObj obj;
		List<ICFSecSecTentGrpObj> arrayList = readCachedSecTentGrpByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentGrpObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecTentGrpByNameIdx( String Name )
	{
		final String S_ProcName = "deepDisposeSecTentGrpByNameIdx";
		ICFSecSecTentGrpObj obj;
		List<ICFSecSecTentGrpObj> arrayList = readCachedSecTentGrpByNameIdx( Name );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentGrpObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name )
	{
		ICFSecSecTentGrpObj obj = readCachedSecTentGrpByUNameIdx( TenantId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecTentGrpObj updateSecTentGrp( ICFSecSecTentGrpObj Obj ) {
		ICFSecSecTentGrpObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentGrp().updateSecTentGrp( null,
			Obj.getSecTentGrpRec() );
		obj = (ICFSecSecTentGrpObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecTentGrp( ICFSecSecTentGrpObj Obj ) {
		ICFSecSecTentGrpObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentGrp().deleteSecTentGrp( null,
			obj.getSecTentGrpRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecTentGrpByIdIdx( ICFLibKeyHash256 SecTentGrpId )
	{
		ICFSecSecTentGrpObj obj = readSecTentGrp(SecTentGrpId);
		if( obj != null ) {
			ICFSecSecTentGrpEditObj editObj = (ICFSecSecTentGrpEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecTentGrpEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSecTentGrpByIdIdx( SecTentGrpId );
	}

	@Override
	public void deleteSecTentGrpByTenantIdx( ICFLibKeyHash256 TenantId )
	{
		ICFSecSecTentGrpByTenantIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFSecSecTentGrpByTenantIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> dict = indexByTenantIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentGrp().deleteSecTentGrpByTenantIdx( null,
				TenantId );
			Iterator<ICFSecSecTentGrpObj> iter = dict.values().iterator();
			ICFSecSecTentGrpObj obj;
			List<ICFSecSecTentGrpObj> toForget = new LinkedList<ICFSecSecTentGrpObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentGrp().deleteSecTentGrpByTenantIdx( null,
				TenantId );
		}
		deepDisposeSecTentGrpByTenantIdx( TenantId );
	}

	@Override
	public void deleteSecTentGrpByNameIdx( String Name )
	{
		ICFSecSecTentGrpByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByNameIdxKey();
		key.setRequiredName( Name );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecTentGrpByNameIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentGrpObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentGrp().deleteSecTentGrpByNameIdx( null,
				Name );
			Iterator<ICFSecSecTentGrpObj> iter = dict.values().iterator();
			ICFSecSecTentGrpObj obj;
			List<ICFSecSecTentGrpObj> toForget = new LinkedList<ICFSecSecTentGrpObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNameIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentGrp().deleteSecTentGrpByNameIdx( null,
				Name );
		}
		deepDisposeSecTentGrpByNameIdx( Name );
	}

	@Override
	public void deleteSecTentGrpByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecTentGrpByUNameIdxKey,
				ICFSecSecTentGrpObj >();
		}
		ICFSecSecTentGrpByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentGrp().newByUNameIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredName( Name );
		ICFSecSecTentGrpObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentGrp().deleteSecTentGrpByUNameIdx( null,
				TenantId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentGrp().deleteSecTentGrpByUNameIdx( null,
				TenantId,
				Name );
		}
		deepDisposeSecTentGrpByUNameIdx( TenantId,
				Name );
	}
}