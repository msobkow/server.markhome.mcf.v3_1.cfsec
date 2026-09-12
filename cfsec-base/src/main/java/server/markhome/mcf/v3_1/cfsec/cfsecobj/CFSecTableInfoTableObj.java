// Description: Java 25 Table Object implementation for TableInfo.

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

public class CFSecTableInfoTableObj
	implements ICFSecTableInfoTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecTableInfo.CLASS_CODE;
	protected static final int backingClassCode = ICFSecTableInfo.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> allTableInfo;
	private Map< ICFSecTableInfoByTableNameIdxKey,
		ICFSecTableInfoObj > indexByTableNameIdx;
	private Map< ICFSecTableInfoBySuperNameIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj > > indexBySuperNameIdx;
	private Map< ICFSecTableInfoBySchemaNameIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj > > indexBySchemaNameIdx;
	private Map< ICFSecTableInfoBySchemaBkCodeIdxKey,
		ICFSecTableInfoObj > indexBySchemaBkCodeIdx;
	private Map< ICFSecTableInfoBySchemaRTCodeIdxKey,
		ICFSecTableInfoObj > indexBySchemaRTCodeIdx;
	public static String TABLE_NAME = "TableInfo";
	public static String TABLE_DBNAME = "table_info";

	public CFSecTableInfoTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj>();
		allTableInfo = null;
		indexByTableNameIdx = null;
		indexBySuperNameIdx = null;
		indexBySchemaNameIdx = null;
		indexBySchemaBkCodeIdx = null;
		indexBySchemaRTCodeIdx = null;
	}

	public CFSecTableInfoTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj>();
		allTableInfo = null;
		indexByTableNameIdx = null;
		indexBySuperNameIdx = null;
		indexBySchemaNameIdx = null;
		indexBySchemaBkCodeIdx = null;
		indexBySchemaRTCodeIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecTableInfoTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecTableInfoTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allTableInfo = null;
		indexByTableNameIdx = null;
		indexBySuperNameIdx = null;
		indexBySchemaNameIdx = null;
		indexBySchemaBkCodeIdx = null;
		indexBySchemaRTCodeIdx = null;
		List<ICFSecTableInfoObj> toForget = new LinkedList<ICFSecTableInfoObj>();
		ICFSecTableInfoObj cur = null;
		Iterator<ICFSecTableInfoObj> iter = members.values().iterator();
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
	 *	CFSecTableInfoObj.
	 */
	@Override
	public ICFSecTableInfoObj newInstance() {
		ICFSecTableInfoObj inst = new CFSecTableInfoObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecTableInfoObj.
	 */
	@Override
	public ICFSecTableInfoEditObj newEditInstance( ICFSecTableInfoObj orig ) {
		ICFSecTableInfoEditObj edit = new CFSecTableInfoEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecTableInfoObj realiseTableInfo( ICFSecTableInfoObj Obj ) {
		ICFSecTableInfoObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecTableInfoObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecTableInfoObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTableNameIdx != null ) {
				ICFSecTableInfoByTableNameIdxKey keyTableNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newByTableNameIdxKey();
				keyTableNameIdx.setRequiredTableName( keepObj.getRequiredTableName() );
				indexByTableNameIdx.remove( keyTableNameIdx );
			}

			if( indexBySuperNameIdx != null ) {
				ICFSecTableInfoBySuperNameIdxKey keySuperNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySuperNameIdxKey();
				keySuperNameIdx.setOptionalSuperName( keepObj.getOptionalSuperName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj > mapSuperNameIdx = indexBySuperNameIdx.get( keySuperNameIdx );
				if( mapSuperNameIdx != null ) {
					mapSuperNameIdx.remove( keepObj.getPKey() );
					if( mapSuperNameIdx.size() <= 0 ) {
						indexBySuperNameIdx.remove( keySuperNameIdx );
					}
				}
			}

			if( indexBySchemaNameIdx != null ) {
				ICFSecTableInfoBySchemaNameIdxKey keySchemaNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaNameIdxKey();
				keySchemaNameIdx.setRequiredSchemaName( keepObj.getRequiredSchemaName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj > mapSchemaNameIdx = indexBySchemaNameIdx.get( keySchemaNameIdx );
				if( mapSchemaNameIdx != null ) {
					mapSchemaNameIdx.remove( keepObj.getPKey() );
					if( mapSchemaNameIdx.size() <= 0 ) {
						indexBySchemaNameIdx.remove( keySchemaNameIdx );
					}
				}
			}

			if( indexBySchemaBkCodeIdx != null ) {
				ICFSecTableInfoBySchemaBkCodeIdxKey keySchemaBkCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaBkCodeIdxKey();
				keySchemaBkCodeIdx.setRequiredSchemaName( keepObj.getRequiredSchemaName() );
				keySchemaBkCodeIdx.setRequiredBackingClassCode( keepObj.getRequiredBackingClassCode() );
				indexBySchemaBkCodeIdx.remove( keySchemaBkCodeIdx );
			}

			if( indexBySchemaRTCodeIdx != null ) {
				ICFSecTableInfoBySchemaRTCodeIdxKey keySchemaRTCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaRTCodeIdxKey();
				keySchemaRTCodeIdx.setRequiredRuntimeClassCode( keepObj.getRequiredRuntimeClassCode() );
				indexBySchemaRTCodeIdx.remove( keySchemaRTCodeIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTableNameIdx != null ) {
				ICFSecTableInfoByTableNameIdxKey keyTableNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newByTableNameIdxKey();
				keyTableNameIdx.setRequiredTableName( keepObj.getRequiredTableName() );
				indexByTableNameIdx.put( keyTableNameIdx, keepObj );
			}

			if( indexBySuperNameIdx != null ) {
				ICFSecTableInfoBySuperNameIdxKey keySuperNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySuperNameIdxKey();
				keySuperNameIdx.setOptionalSuperName( keepObj.getOptionalSuperName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj > mapSuperNameIdx = indexBySuperNameIdx.get( keySuperNameIdx );
				if( mapSuperNameIdx != null ) {
					mapSuperNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaNameIdx != null ) {
				ICFSecTableInfoBySchemaNameIdxKey keySchemaNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaNameIdxKey();
				keySchemaNameIdx.setRequiredSchemaName( keepObj.getRequiredSchemaName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj > mapSchemaNameIdx = indexBySchemaNameIdx.get( keySchemaNameIdx );
				if( mapSchemaNameIdx != null ) {
					mapSchemaNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaBkCodeIdx != null ) {
				ICFSecTableInfoBySchemaBkCodeIdxKey keySchemaBkCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaBkCodeIdxKey();
				keySchemaBkCodeIdx.setRequiredSchemaName( keepObj.getRequiredSchemaName() );
				keySchemaBkCodeIdx.setRequiredBackingClassCode( keepObj.getRequiredBackingClassCode() );
				indexBySchemaBkCodeIdx.put( keySchemaBkCodeIdx, keepObj );
			}

			if( indexBySchemaRTCodeIdx != null ) {
				ICFSecTableInfoBySchemaRTCodeIdxKey keySchemaRTCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaRTCodeIdxKey();
				keySchemaRTCodeIdx.setRequiredRuntimeClassCode( keepObj.getRequiredRuntimeClassCode() );
				indexBySchemaRTCodeIdx.put( keySchemaRTCodeIdx, keepObj );
			}

			if( allTableInfo != null ) {
				allTableInfo.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allTableInfo != null ) {
				allTableInfo.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTableNameIdx != null ) {
				ICFSecTableInfoByTableNameIdxKey keyTableNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newByTableNameIdxKey();
				keyTableNameIdx.setRequiredTableName( keepObj.getRequiredTableName() );
				indexByTableNameIdx.put( keyTableNameIdx, keepObj );
			}

			if( indexBySuperNameIdx != null ) {
				ICFSecTableInfoBySuperNameIdxKey keySuperNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySuperNameIdxKey();
				keySuperNameIdx.setOptionalSuperName( keepObj.getOptionalSuperName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj > mapSuperNameIdx = indexBySuperNameIdx.get( keySuperNameIdx );
				if( mapSuperNameIdx != null ) {
					mapSuperNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaNameIdx != null ) {
				ICFSecTableInfoBySchemaNameIdxKey keySchemaNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaNameIdxKey();
				keySchemaNameIdx.setRequiredSchemaName( keepObj.getRequiredSchemaName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj > mapSchemaNameIdx = indexBySchemaNameIdx.get( keySchemaNameIdx );
				if( mapSchemaNameIdx != null ) {
					mapSchemaNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySchemaBkCodeIdx != null ) {
				ICFSecTableInfoBySchemaBkCodeIdxKey keySchemaBkCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaBkCodeIdxKey();
				keySchemaBkCodeIdx.setRequiredSchemaName( keepObj.getRequiredSchemaName() );
				keySchemaBkCodeIdx.setRequiredBackingClassCode( keepObj.getRequiredBackingClassCode() );
				indexBySchemaBkCodeIdx.put( keySchemaBkCodeIdx, keepObj );
			}

			if( indexBySchemaRTCodeIdx != null ) {
				ICFSecTableInfoBySchemaRTCodeIdxKey keySchemaRTCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaRTCodeIdxKey();
				keySchemaRTCodeIdx.setRequiredRuntimeClassCode( keepObj.getRequiredRuntimeClassCode() );
				indexBySchemaRTCodeIdx.put( keySchemaRTCodeIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecTableInfoObj createTableInfo( ICFSecTableInfoObj Obj ) {
		ICFSecTableInfoObj obj = Obj;
		ICFSecTableInfo rec = obj.getTableInfoRec();
		schema.getCFSecBackingStore().getTableTableInfo().createTableInfo(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecTableInfoObj readTableInfo( $implCommaIJavaOptAtomType$ pkey ) {
		return( readTableInfo( pkey, false ) );
	}

	@Override
	public ICFSecTableInfoObj readTableInfo( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecTableInfoObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecTableInfo readRec = schema.getCFSecBackingStore().getTableTableInfo().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getTableInfoTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecTableInfoObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecTableInfoObj readCachedTableInfo( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecTableInfoObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeTableInfo( ICFSecTableInfoObj obj )
	{
		final String S_ProcName = "CFSecTableInfoTableObj.reallyDeepDisposeTableInfo() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecTableInfoObj existing = readCachedTableInfo( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecTableInfoByTableNameIdxKey keyTableNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newByTableNameIdxKey();
		keyTableNameIdx.setRequiredTableName( existing.getRequiredTableName() );

		ICFSecTableInfoBySuperNameIdxKey keySuperNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySuperNameIdxKey();
		keySuperNameIdx.setOptionalSuperName( existing.getOptionalSuperName() );

		ICFSecTableInfoBySchemaNameIdxKey keySchemaNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaNameIdxKey();
		keySchemaNameIdx.setRequiredSchemaName( existing.getRequiredSchemaName() );

		ICFSecTableInfoBySchemaBkCodeIdxKey keySchemaBkCodeIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaBkCodeIdxKey();
		keySchemaBkCodeIdx.setRequiredSchemaName( existing.getRequiredSchemaName() );
		keySchemaBkCodeIdx.setRequiredBackingClassCode( existing.getRequiredBackingClassCode() );

		ICFSecTableInfoBySchemaRTCodeIdxKey keySchemaRTCodeIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaRTCodeIdxKey();
		keySchemaRTCodeIdx.setRequiredRuntimeClassCode( existing.getRequiredRuntimeClassCode() );


		schema.getTableInfoTableObj().deepDisposeTableInfoBySuperNameIdx( existing.getRequiredTableName() );

		if( indexByTableNameIdx != null ) {
			indexByTableNameIdx.remove( keyTableNameIdx );
		}

		if( indexBySuperNameIdx != null ) {
			if( indexBySuperNameIdx.containsKey( keySuperNameIdx ) ) {
				indexBySuperNameIdx.get( keySuperNameIdx ).remove( pkey );
				if( indexBySuperNameIdx.get( keySuperNameIdx ).size() <= 0 ) {
					indexBySuperNameIdx.remove( keySuperNameIdx );
				}
			}
		}

		if( indexBySchemaNameIdx != null ) {
			if( indexBySchemaNameIdx.containsKey( keySchemaNameIdx ) ) {
				indexBySchemaNameIdx.get( keySchemaNameIdx ).remove( pkey );
				if( indexBySchemaNameIdx.get( keySchemaNameIdx ).size() <= 0 ) {
					indexBySchemaNameIdx.remove( keySchemaNameIdx );
				}
			}
		}

		if( indexBySchemaBkCodeIdx != null ) {
			indexBySchemaBkCodeIdx.remove( keySchemaBkCodeIdx );
		}

		if( indexBySchemaRTCodeIdx != null ) {
			indexBySchemaRTCodeIdx.remove( keySchemaRTCodeIdx );
		}


	}
	@Override
	public void deepDisposeTableInfo( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecTableInfoObj obj = readCachedTableInfo( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecTableInfoObj lockTableInfo( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecTableInfoObj locked = null;
		ICFSecTableInfo lockRec = schema.getCFSecBackingStore().getTableTableInfo().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getTableInfoTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecTableInfoObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockTableInfo", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecTableInfoObj> readAllTableInfo() {
		return( readAllTableInfo( false ) );
	}

	@Override
	public List<ICFSecTableInfoObj> readAllTableInfo( boolean forceRead ) {
		final String S_ProcName = "readAllTableInfo";
		if( ( allTableInfo == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecTableInfoObj>();
			allTableInfo = map;
			ICFSecTableInfo[] recList = schema.getCFSecBackingStore().getTableTableInfo().readAllDerived( null );
			ICFSecTableInfo rec;
			ICFSecTableInfoObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecTableInfoObj realised = (ICFSecTableInfoObj)obj.realise();
			}
		}
		int len = allTableInfo.size();
		ICFSecTableInfoObj arr[] = new ICFSecTableInfoObj[len];
		Iterator<ICFSecTableInfoObj> valIter = allTableInfo.values().iterator();
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
		ArrayList<ICFSecTableInfoObj> arrayList = new ArrayList<ICFSecTableInfoObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecTableInfoObj> cmp = new Comparator<ICFSecTableInfoObj>() {
			@Override
			public int compare( ICFSecTableInfoObj lhs, ICFSecTableInfoObj rhs ) {
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
		List<ICFSecTableInfoObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecTableInfoObj> readCachedAllTableInfo() {
		final String S_ProcName = "readCachedAllTableInfo";
		ArrayList<ICFSecTableInfoObj> arrayList = new ArrayList<ICFSecTableInfoObj>();
		if( allTableInfo != null ) {
			int len = allTableInfo.size();
			ICFSecTableInfoObj arr[] = new ICFSecTableInfoObj[len];
			Iterator<ICFSecTableInfoObj> valIter = allTableInfo.values().iterator();
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
		Comparator<ICFSecTableInfoObj> cmp = new Comparator<ICFSecTableInfoObj>() {
			public int compare( ICFSecTableInfoObj lhs, ICFSecTableInfoObj rhs ) {
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
	public ICFSecTableInfoObj readTableInfoByIdIdx( int TableInfoId )
	{
		return( readTableInfoByIdIdx( TableInfoId,
			false ) );
	}

	@Override
	public ICFSecTableInfoObj readTableInfoByIdIdx( int TableInfoId, boolean forceRead )
	{
		ICFSecTableInfoObj obj = readTableInfo( TableInfoId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecTableInfoObj readTableInfoByTableNameIdx( String TableName )
	{
		return( readTableInfoByTableNameIdx( TableName,
			false ) );
	}

	@Override
	public ICFSecTableInfoObj readTableInfoByTableNameIdx( String TableName, boolean forceRead )
	{
		if( indexByTableNameIdx == null ) {
			indexByTableNameIdx = new HashMap< ICFSecTableInfoByTableNameIdxKey,
				ICFSecTableInfoObj >();
		}
		ICFSecTableInfoByTableNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newByTableNameIdxKey();
		key.setRequiredTableName( TableName );
		ICFSecTableInfoObj obj = null;
		if( ( ! forceRead ) && indexByTableNameIdx.containsKey( key ) ) {
			obj = indexByTableNameIdx.get( key );
		}
		else {
			ICFSecTableInfo rec = schema.getCFSecBackingStore().getTableTableInfo().readDerivedByTableNameIdx( null,
				TableName );
			if( rec != null ) {
				obj = schema.getTableInfoTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecTableInfoObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecTableInfoObj> readTableInfoBySuperNameIdx( String SuperName )
	{
		return( readTableInfoBySuperNameIdx( SuperName,
			false ) );
	}

	@Override
	public List<ICFSecTableInfoObj> readTableInfoBySuperNameIdx( String SuperName,
		boolean forceRead )
	{
		final String S_ProcName = "readTableInfoBySuperNameIdx";
		ICFSecTableInfoBySuperNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySuperNameIdxKey();
		key.setOptionalSuperName( SuperName );
		Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> dict;
		if( indexBySuperNameIdx == null ) {
			indexBySuperNameIdx = new HashMap< ICFSecTableInfoBySuperNameIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecTableInfoObj > >();
		}
		if( ( ! forceRead ) && indexBySuperNameIdx.containsKey( key ) ) {
			dict = indexBySuperNameIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj>();
			ICFSecTableInfoObj obj;
			ICFSecTableInfo[] recList = schema.getCFSecBackingStore().getTableTableInfo().readDerivedBySuperNameIdx( null,
				SuperName );
			ICFSecTableInfo rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getTableInfoTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecTableInfoObj realised = (ICFSecTableInfoObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySuperNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecTableInfoObj arr[] = new ICFSecTableInfoObj[len];
		Iterator<ICFSecTableInfoObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecTableInfoObj> arrayList = new ArrayList<ICFSecTableInfoObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecTableInfoObj> cmp = new Comparator<ICFSecTableInfoObj>() {
			@Override
			public int compare( ICFSecTableInfoObj lhs, ICFSecTableInfoObj rhs ) {
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
		List<ICFSecTableInfoObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecTableInfoObj> readTableInfoBySchemaNameIdx( String SchemaName )
	{
		return( readTableInfoBySchemaNameIdx( SchemaName,
			false ) );
	}

	@Override
	public List<ICFSecTableInfoObj> readTableInfoBySchemaNameIdx( String SchemaName,
		boolean forceRead )
	{
		final String S_ProcName = "readTableInfoBySchemaNameIdx";
		ICFSecTableInfoBySchemaNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaNameIdxKey();
		key.setRequiredSchemaName( SchemaName );
		Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> dict;
		if( indexBySchemaNameIdx == null ) {
			indexBySchemaNameIdx = new HashMap< ICFSecTableInfoBySchemaNameIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecTableInfoObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaNameIdx.containsKey( key ) ) {
			dict = indexBySchemaNameIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj>();
			ICFSecTableInfoObj obj;
			ICFSecTableInfo[] recList = schema.getCFSecBackingStore().getTableTableInfo().readDerivedBySchemaNameIdx( null,
				SchemaName );
			ICFSecTableInfo rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getTableInfoTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecTableInfoObj realised = (ICFSecTableInfoObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecTableInfoObj arr[] = new ICFSecTableInfoObj[len];
		Iterator<ICFSecTableInfoObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecTableInfoObj> arrayList = new ArrayList<ICFSecTableInfoObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecTableInfoObj> cmp = new Comparator<ICFSecTableInfoObj>() {
			@Override
			public int compare( ICFSecTableInfoObj lhs, ICFSecTableInfoObj rhs ) {
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
		List<ICFSecTableInfoObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecTableInfoObj readTableInfoBySchemaBkCodeIdx( String SchemaName,
		int BackingClassCode )
	{
		return( readTableInfoBySchemaBkCodeIdx( SchemaName,
			BackingClassCode,
			false ) );
	}

	@Override
	public ICFSecTableInfoObj readTableInfoBySchemaBkCodeIdx( String SchemaName,
		int BackingClassCode, boolean forceRead )
	{
		if( indexBySchemaBkCodeIdx == null ) {
			indexBySchemaBkCodeIdx = new HashMap< ICFSecTableInfoBySchemaBkCodeIdxKey,
				ICFSecTableInfoObj >();
		}
		ICFSecTableInfoBySchemaBkCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaBkCodeIdxKey();
		key.setRequiredSchemaName( SchemaName );
		key.setRequiredBackingClassCode( BackingClassCode );
		ICFSecTableInfoObj obj = null;
		if( ( ! forceRead ) && indexBySchemaBkCodeIdx.containsKey( key ) ) {
			obj = indexBySchemaBkCodeIdx.get( key );
		}
		else {
			ICFSecTableInfo rec = schema.getCFSecBackingStore().getTableTableInfo().readDerivedBySchemaBkCodeIdx( null,
				SchemaName,
				BackingClassCode );
			if( rec != null ) {
				obj = schema.getTableInfoTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecTableInfoObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecTableInfoObj readTableInfoBySchemaRTCodeIdx( int RuntimeClassCode )
	{
		return( readTableInfoBySchemaRTCodeIdx( RuntimeClassCode,
			false ) );
	}

	@Override
	public ICFSecTableInfoObj readTableInfoBySchemaRTCodeIdx( int RuntimeClassCode, boolean forceRead )
	{
		if( indexBySchemaRTCodeIdx == null ) {
			indexBySchemaRTCodeIdx = new HashMap< ICFSecTableInfoBySchemaRTCodeIdxKey,
				ICFSecTableInfoObj >();
		}
		ICFSecTableInfoBySchemaRTCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaRTCodeIdxKey();
		key.setRequiredRuntimeClassCode( RuntimeClassCode );
		ICFSecTableInfoObj obj = null;
		if( ( ! forceRead ) && indexBySchemaRTCodeIdx.containsKey( key ) ) {
			obj = indexBySchemaRTCodeIdx.get( key );
		}
		else {
			ICFSecTableInfo rec = schema.getCFSecBackingStore().getTableTableInfo().readDerivedBySchemaRTCodeIdx( null,
				RuntimeClassCode );
			if( rec != null ) {
				obj = schema.getTableInfoTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecTableInfoObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecTableInfoObj readCachedTableInfoByIdIdx( int TableInfoId )
	{
		ICFSecTableInfoObj obj = null;
		obj = readCachedTableInfo( TableInfoId );
		return( obj );
	}

	@Override
	public ICFSecTableInfoObj readCachedTableInfoByTableNameIdx( String TableName )
	{
		ICFSecTableInfoObj obj = null;
		ICFSecTableInfoByTableNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newByTableNameIdxKey();
		key.setRequiredTableName( TableName );
		if( indexByTableNameIdx != null ) {
			if( indexByTableNameIdx.containsKey( key ) ) {
				obj = indexByTableNameIdx.get( key );
			}
			else {
				Iterator<ICFSecTableInfoObj> valIter = members.values().iterator();
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
			Iterator<ICFSecTableInfoObj> valIter = members.values().iterator();
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
	public List<ICFSecTableInfoObj> readCachedTableInfoBySuperNameIdx( String SuperName )
	{
		final String S_ProcName = "readCachedTableInfoBySuperNameIdx";
		ICFSecTableInfoBySuperNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySuperNameIdxKey();
		key.setOptionalSuperName( SuperName );
		ArrayList<ICFSecTableInfoObj> arrayList = new ArrayList<ICFSecTableInfoObj>();
		if( indexBySuperNameIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> dict;
			if( indexBySuperNameIdx.containsKey( key ) ) {
				dict = indexBySuperNameIdx.get( key );
				int len = dict.size();
				ICFSecTableInfoObj arr[] = new ICFSecTableInfoObj[len];
				Iterator<ICFSecTableInfoObj> valIter = dict.values().iterator();
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
			ICFSecTableInfoObj obj;
			Iterator<ICFSecTableInfoObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecTableInfoObj> cmp = new Comparator<ICFSecTableInfoObj>() {
			@Override
			public int compare( ICFSecTableInfoObj lhs, ICFSecTableInfoObj rhs ) {
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
	public List<ICFSecTableInfoObj> readCachedTableInfoBySchemaNameIdx( String SchemaName )
	{
		final String S_ProcName = "readCachedTableInfoBySchemaNameIdx";
		ICFSecTableInfoBySchemaNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaNameIdxKey();
		key.setRequiredSchemaName( SchemaName );
		ArrayList<ICFSecTableInfoObj> arrayList = new ArrayList<ICFSecTableInfoObj>();
		if( indexBySchemaNameIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> dict;
			if( indexBySchemaNameIdx.containsKey( key ) ) {
				dict = indexBySchemaNameIdx.get( key );
				int len = dict.size();
				ICFSecTableInfoObj arr[] = new ICFSecTableInfoObj[len];
				Iterator<ICFSecTableInfoObj> valIter = dict.values().iterator();
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
			ICFSecTableInfoObj obj;
			Iterator<ICFSecTableInfoObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecTableInfoObj> cmp = new Comparator<ICFSecTableInfoObj>() {
			@Override
			public int compare( ICFSecTableInfoObj lhs, ICFSecTableInfoObj rhs ) {
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
	public ICFSecTableInfoObj readCachedTableInfoBySchemaBkCodeIdx( String SchemaName,
		int BackingClassCode )
	{
		ICFSecTableInfoObj obj = null;
		ICFSecTableInfoBySchemaBkCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaBkCodeIdxKey();
		key.setRequiredSchemaName( SchemaName );
		key.setRequiredBackingClassCode( BackingClassCode );
		if( indexBySchemaBkCodeIdx != null ) {
			if( indexBySchemaBkCodeIdx.containsKey( key ) ) {
				obj = indexBySchemaBkCodeIdx.get( key );
			}
			else {
				Iterator<ICFSecTableInfoObj> valIter = members.values().iterator();
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
			Iterator<ICFSecTableInfoObj> valIter = members.values().iterator();
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
	public ICFSecTableInfoObj readCachedTableInfoBySchemaRTCodeIdx( int RuntimeClassCode )
	{
		ICFSecTableInfoObj obj = null;
		ICFSecTableInfoBySchemaRTCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaRTCodeIdxKey();
		key.setRequiredRuntimeClassCode( RuntimeClassCode );
		if( indexBySchemaRTCodeIdx != null ) {
			if( indexBySchemaRTCodeIdx.containsKey( key ) ) {
				obj = indexBySchemaRTCodeIdx.get( key );
			}
			else {
				Iterator<ICFSecTableInfoObj> valIter = members.values().iterator();
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
			Iterator<ICFSecTableInfoObj> valIter = members.values().iterator();
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
	public void deepDisposeTableInfoByIdIdx( int TableInfoId )
	{
		ICFSecTableInfoObj obj = readCachedTableInfoByIdIdx( TableInfoId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableInfoByTableNameIdx( String TableName )
	{
		ICFSecTableInfoObj obj = readCachedTableInfoByTableNameIdx( TableName );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableInfoBySuperNameIdx( String SuperName )
	{
		final String S_ProcName = "deepDisposeTableInfoBySuperNameIdx";
		ICFSecTableInfoObj obj;
		List<ICFSecTableInfoObj> arrayList = readCachedTableInfoBySuperNameIdx( SuperName );
		if( arrayList != null )  {
			Iterator<ICFSecTableInfoObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableInfoBySchemaNameIdx( String SchemaName )
	{
		final String S_ProcName = "deepDisposeTableInfoBySchemaNameIdx";
		ICFSecTableInfoObj obj;
		List<ICFSecTableInfoObj> arrayList = readCachedTableInfoBySchemaNameIdx( SchemaName );
		if( arrayList != null )  {
			Iterator<ICFSecTableInfoObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTableInfoBySchemaBkCodeIdx( String SchemaName,
		int BackingClassCode )
	{
		ICFSecTableInfoObj obj = readCachedTableInfoBySchemaBkCodeIdx( SchemaName,
				BackingClassCode );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTableInfoBySchemaRTCodeIdx( int RuntimeClassCode )
	{
		ICFSecTableInfoObj obj = readCachedTableInfoBySchemaRTCodeIdx( RuntimeClassCode );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecTableInfoObj updateTableInfo( ICFSecTableInfoObj Obj ) {
		ICFSecTableInfoObj obj = Obj;
		schema.getCFSecBackingStore().getTableTableInfo().updateTableInfo( null,
			Obj.getTableInfoRec() );
		obj = (ICFSecTableInfoObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteTableInfo( ICFSecTableInfoObj Obj ) {
		ICFSecTableInfoObj obj = Obj;
		schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfo( null,
			obj.getTableInfoRec() );
		Obj.forget();
	}

	@Override
	public void deleteTableInfoByIdIdx( int TableInfoId )
	{
		ICFSecTableInfoObj obj = readTableInfo(TableInfoId);
		if( obj != null ) {
			ICFSecTableInfoEditObj editObj = (ICFSecTableInfoEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecTableInfoEditObj)obj.beginEdit();
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
		deepDisposeTableInfoByIdIdx( TableInfoId );
	}

	@Override
	public void deleteTableInfoByTableNameIdx( String TableName )
	{
		if( indexByTableNameIdx == null ) {
			indexByTableNameIdx = new HashMap< ICFSecTableInfoByTableNameIdxKey,
				ICFSecTableInfoObj >();
		}
		ICFSecTableInfoByTableNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newByTableNameIdxKey();
		key.setRequiredTableName( TableName );
		ICFSecTableInfoObj obj = null;
		if( indexByTableNameIdx.containsKey( key ) ) {
			obj = indexByTableNameIdx.get( key );
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoByTableNameIdx( null,
				TableName );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoByTableNameIdx( null,
				TableName );
		}
		deepDisposeTableInfoByTableNameIdx( TableName );
	}

	@Override
	public void deleteTableInfoBySuperNameIdx( String SuperName )
	{
		ICFSecTableInfoBySuperNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySuperNameIdxKey();
		key.setOptionalSuperName( SuperName );
		if( indexBySuperNameIdx == null ) {
			indexBySuperNameIdx = new HashMap< ICFSecTableInfoBySuperNameIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecTableInfoObj > >();
		}
		if( indexBySuperNameIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> dict = indexBySuperNameIdx.get( key );
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoBySuperNameIdx( null,
				SuperName );
			Iterator<ICFSecTableInfoObj> iter = dict.values().iterator();
			ICFSecTableInfoObj obj;
			List<ICFSecTableInfoObj> toForget = new LinkedList<ICFSecTableInfoObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySuperNameIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoBySuperNameIdx( null,
				SuperName );
		}
		deepDisposeTableInfoBySuperNameIdx( SuperName );
	}

	@Override
	public void deleteTableInfoBySchemaNameIdx( String SchemaName )
	{
		ICFSecTableInfoBySchemaNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaNameIdxKey();
		key.setRequiredSchemaName( SchemaName );
		if( indexBySchemaNameIdx == null ) {
			indexBySchemaNameIdx = new HashMap< ICFSecTableInfoBySchemaNameIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecTableInfoObj > >();
		}
		if( indexBySchemaNameIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecTableInfoObj> dict = indexBySchemaNameIdx.get( key );
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoBySchemaNameIdx( null,
				SchemaName );
			Iterator<ICFSecTableInfoObj> iter = dict.values().iterator();
			ICFSecTableInfoObj obj;
			List<ICFSecTableInfoObj> toForget = new LinkedList<ICFSecTableInfoObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySchemaNameIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoBySchemaNameIdx( null,
				SchemaName );
		}
		deepDisposeTableInfoBySchemaNameIdx( SchemaName );
	}

	@Override
	public void deleteTableInfoBySchemaBkCodeIdx( String SchemaName,
		int BackingClassCode )
	{
		if( indexBySchemaBkCodeIdx == null ) {
			indexBySchemaBkCodeIdx = new HashMap< ICFSecTableInfoBySchemaBkCodeIdxKey,
				ICFSecTableInfoObj >();
		}
		ICFSecTableInfoBySchemaBkCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaBkCodeIdxKey();
		key.setRequiredSchemaName( SchemaName );
		key.setRequiredBackingClassCode( BackingClassCode );
		ICFSecTableInfoObj obj = null;
		if( indexBySchemaBkCodeIdx.containsKey( key ) ) {
			obj = indexBySchemaBkCodeIdx.get( key );
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoBySchemaBkCodeIdx( null,
				SchemaName,
				BackingClassCode );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoBySchemaBkCodeIdx( null,
				SchemaName,
				BackingClassCode );
		}
		deepDisposeTableInfoBySchemaBkCodeIdx( SchemaName,
				BackingClassCode );
	}

	@Override
	public void deleteTableInfoBySchemaRTCodeIdx( int RuntimeClassCode )
	{
		if( indexBySchemaRTCodeIdx == null ) {
			indexBySchemaRTCodeIdx = new HashMap< ICFSecTableInfoBySchemaRTCodeIdxKey,
				ICFSecTableInfoObj >();
		}
		ICFSecTableInfoBySchemaRTCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newBySchemaRTCodeIdxKey();
		key.setRequiredRuntimeClassCode( RuntimeClassCode );
		ICFSecTableInfoObj obj = null;
		if( indexBySchemaRTCodeIdx.containsKey( key ) ) {
			obj = indexBySchemaRTCodeIdx.get( key );
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoBySchemaRTCodeIdx( null,
				RuntimeClassCode );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableTableInfo().deleteTableInfoBySchemaRTCodeIdx( null,
				RuntimeClassCode );
		}
		deepDisposeTableInfoBySchemaRTCodeIdx( RuntimeClassCode );
	}
}