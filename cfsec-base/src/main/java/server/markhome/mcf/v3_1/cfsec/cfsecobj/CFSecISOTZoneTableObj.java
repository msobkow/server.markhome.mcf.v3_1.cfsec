// Description: Java 25 Table Object implementation for ISOTZone.

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

public class CFSecISOTZoneTableObj
	implements ICFSecISOTZoneTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecISOTZone.CLASS_CODE;
	protected static final int backingClassCode = ICFSecISOTZone.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> allISOTZone;
	private Map< ICFSecISOTZoneByOffsetIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > > indexByOffsetIdx;
	private Map< ICFSecISOTZoneByUTZNameIdxKey,
		ICFSecISOTZoneObj > indexByUTZNameIdx;
	private Map< ICFSecISOTZoneByIso8601IdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > > indexByIso8601Idx;
	public static String TABLE_NAME = "ISOTZone";
	public static String TABLE_DBNAME = "isotz";

	public CFSecISOTZoneTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj>();
		allISOTZone = null;
		indexByOffsetIdx = null;
		indexByUTZNameIdx = null;
		indexByIso8601Idx = null;
	}

	public CFSecISOTZoneTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj>();
		allISOTZone = null;
		indexByOffsetIdx = null;
		indexByUTZNameIdx = null;
		indexByIso8601Idx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecISOTZoneTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecISOTZoneTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allISOTZone = null;
		indexByOffsetIdx = null;
		indexByUTZNameIdx = null;
		indexByIso8601Idx = null;
		List<ICFSecISOTZoneObj> toForget = new LinkedList<ICFSecISOTZoneObj>();
		ICFSecISOTZoneObj cur = null;
		Iterator<ICFSecISOTZoneObj> iter = members.values().iterator();
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
	 *	CFSecISOTZoneObj.
	 */
	@Override
	public ICFSecISOTZoneObj newInstance() {
		ICFSecISOTZoneObj inst = new CFSecISOTZoneObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecISOTZoneObj.
	 */
	@Override
	public ICFSecISOTZoneEditObj newEditInstance( ICFSecISOTZoneObj orig ) {
		ICFSecISOTZoneEditObj edit = new CFSecISOTZoneEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecISOTZoneObj realiseISOTZone( ICFSecISOTZoneObj Obj ) {
		ICFSecISOTZoneObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecISOTZoneObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecISOTZoneObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByOffsetIdx != null ) {
				ICFSecISOTZoneByOffsetIdxKey keyOffsetIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByOffsetIdxKey();
				keyOffsetIdx.setRequiredTZHourOffset( keepObj.getRequiredTZHourOffset() );
				keyOffsetIdx.setRequiredTZMinOffset( keepObj.getRequiredTZMinOffset() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > mapOffsetIdx = indexByOffsetIdx.get( keyOffsetIdx );
				if( mapOffsetIdx != null ) {
					mapOffsetIdx.remove( keepObj.getPKey() );
					if( mapOffsetIdx.size() <= 0 ) {
						indexByOffsetIdx.remove( keyOffsetIdx );
					}
				}
			}

			if( indexByUTZNameIdx != null ) {
				ICFSecISOTZoneByUTZNameIdxKey keyUTZNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByUTZNameIdxKey();
				keyUTZNameIdx.setRequiredTZName( keepObj.getRequiredTZName() );
				indexByUTZNameIdx.remove( keyUTZNameIdx );
			}

			if( indexByIso8601Idx != null ) {
				ICFSecISOTZoneByIso8601IdxKey keyIso8601Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByIso8601IdxKey();
				keyIso8601Idx.setRequiredIso8601( keepObj.getRequiredIso8601() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > mapIso8601Idx = indexByIso8601Idx.get( keyIso8601Idx );
				if( mapIso8601Idx != null ) {
					mapIso8601Idx.remove( keepObj.getPKey() );
					if( mapIso8601Idx.size() <= 0 ) {
						indexByIso8601Idx.remove( keyIso8601Idx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByOffsetIdx != null ) {
				ICFSecISOTZoneByOffsetIdxKey keyOffsetIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByOffsetIdxKey();
				keyOffsetIdx.setRequiredTZHourOffset( keepObj.getRequiredTZHourOffset() );
				keyOffsetIdx.setRequiredTZMinOffset( keepObj.getRequiredTZMinOffset() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > mapOffsetIdx = indexByOffsetIdx.get( keyOffsetIdx );
				if( mapOffsetIdx != null ) {
					mapOffsetIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUTZNameIdx != null ) {
				ICFSecISOTZoneByUTZNameIdxKey keyUTZNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByUTZNameIdxKey();
				keyUTZNameIdx.setRequiredTZName( keepObj.getRequiredTZName() );
				indexByUTZNameIdx.put( keyUTZNameIdx, keepObj );
			}

			if( indexByIso8601Idx != null ) {
				ICFSecISOTZoneByIso8601IdxKey keyIso8601Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByIso8601IdxKey();
				keyIso8601Idx.setRequiredIso8601( keepObj.getRequiredIso8601() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > mapIso8601Idx = indexByIso8601Idx.get( keyIso8601Idx );
				if( mapIso8601Idx != null ) {
					mapIso8601Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allISOTZone != null ) {
				allISOTZone.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allISOTZone != null ) {
				allISOTZone.put( keepObj.getPKey(), keepObj );
			}

			if( indexByOffsetIdx != null ) {
				ICFSecISOTZoneByOffsetIdxKey keyOffsetIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByOffsetIdxKey();
				keyOffsetIdx.setRequiredTZHourOffset( keepObj.getRequiredTZHourOffset() );
				keyOffsetIdx.setRequiredTZMinOffset( keepObj.getRequiredTZMinOffset() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > mapOffsetIdx = indexByOffsetIdx.get( keyOffsetIdx );
				if( mapOffsetIdx != null ) {
					mapOffsetIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUTZNameIdx != null ) {
				ICFSecISOTZoneByUTZNameIdxKey keyUTZNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByUTZNameIdxKey();
				keyUTZNameIdx.setRequiredTZName( keepObj.getRequiredTZName() );
				indexByUTZNameIdx.put( keyUTZNameIdx, keepObj );
			}

			if( indexByIso8601Idx != null ) {
				ICFSecISOTZoneByIso8601IdxKey keyIso8601Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByIso8601IdxKey();
				keyIso8601Idx.setRequiredIso8601( keepObj.getRequiredIso8601() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > mapIso8601Idx = indexByIso8601Idx.get( keyIso8601Idx );
				if( mapIso8601Idx != null ) {
					mapIso8601Idx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecISOTZoneObj createISOTZone( ICFSecISOTZoneObj Obj ) {
		ICFSecISOTZoneObj obj = Obj;
		ICFSecISOTZone rec = obj.getISOTZoneRec();
		schema.getCFSecBackingStore().getTableISOTZone().createISOTZone(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecISOTZoneObj readISOTZone( $implCommaIJavaOptAtomType$ pkey ) {
		return( readISOTZone( pkey, false ) );
	}

	@Override
	public ICFSecISOTZoneObj readISOTZone( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecISOTZoneObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecISOTZone readRec = schema.getCFSecBackingStore().getTableISOTZone().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getISOTZoneTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecISOTZoneObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecISOTZoneObj readCachedISOTZone( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOTZoneObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeISOTZone( ICFSecISOTZoneObj obj )
	{
		final String S_ProcName = "CFSecISOTZoneTableObj.reallyDeepDisposeISOTZone() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecISOTZoneObj existing = readCachedISOTZone( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecISOTZoneByOffsetIdxKey keyOffsetIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByOffsetIdxKey();
		keyOffsetIdx.setRequiredTZHourOffset( existing.getRequiredTZHourOffset() );
		keyOffsetIdx.setRequiredTZMinOffset( existing.getRequiredTZMinOffset() );

		ICFSecISOTZoneByUTZNameIdxKey keyUTZNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByUTZNameIdxKey();
		keyUTZNameIdx.setRequiredTZName( existing.getRequiredTZName() );

		ICFSecISOTZoneByIso8601IdxKey keyIso8601Idx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByIso8601IdxKey();
		keyIso8601Idx.setRequiredIso8601( existing.getRequiredIso8601() );



		if( indexByOffsetIdx != null ) {
			if( indexByOffsetIdx.containsKey( keyOffsetIdx ) ) {
				indexByOffsetIdx.get( keyOffsetIdx ).remove( pkey );
				if( indexByOffsetIdx.get( keyOffsetIdx ).size() <= 0 ) {
					indexByOffsetIdx.remove( keyOffsetIdx );
				}
			}
		}

		if( indexByUTZNameIdx != null ) {
			indexByUTZNameIdx.remove( keyUTZNameIdx );
		}

		if( indexByIso8601Idx != null ) {
			if( indexByIso8601Idx.containsKey( keyIso8601Idx ) ) {
				indexByIso8601Idx.get( keyIso8601Idx ).remove( pkey );
				if( indexByIso8601Idx.get( keyIso8601Idx ).size() <= 0 ) {
					indexByIso8601Idx.remove( keyIso8601Idx );
				}
			}
		}


	}
	@Override
	public void deepDisposeISOTZone( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOTZoneObj obj = readCachedISOTZone( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecISOTZoneObj lockISOTZone( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOTZoneObj locked = null;
		ICFSecISOTZone lockRec = schema.getCFSecBackingStore().getTableISOTZone().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getISOTZoneTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecISOTZoneObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockISOTZone", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecISOTZoneObj> readAllISOTZone() {
		return( readAllISOTZone( false ) );
	}

	@Override
	public List<ICFSecISOTZoneObj> readAllISOTZone( boolean forceRead ) {
		final String S_ProcName = "readAllISOTZone";
		if( ( allISOTZone == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecISOTZoneObj>();
			allISOTZone = map;
			ICFSecISOTZone[] recList = schema.getCFSecBackingStore().getTableISOTZone().readAllDerived( null );
			ICFSecISOTZone rec;
			ICFSecISOTZoneObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOTZoneObj realised = (ICFSecISOTZoneObj)obj.realise();
			}
		}
		int len = allISOTZone.size();
		ICFSecISOTZoneObj arr[] = new ICFSecISOTZoneObj[len];
		Iterator<ICFSecISOTZoneObj> valIter = allISOTZone.values().iterator();
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
		ArrayList<ICFSecISOTZoneObj> arrayList = new ArrayList<ICFSecISOTZoneObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOTZoneObj> cmp = new Comparator<ICFSecISOTZoneObj>() {
			@Override
			public int compare( ICFSecISOTZoneObj lhs, ICFSecISOTZoneObj rhs ) {
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
		List<ICFSecISOTZoneObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecISOTZoneObj> readCachedAllISOTZone() {
		final String S_ProcName = "readCachedAllISOTZone";
		ArrayList<ICFSecISOTZoneObj> arrayList = new ArrayList<ICFSecISOTZoneObj>();
		if( allISOTZone != null ) {
			int len = allISOTZone.size();
			ICFSecISOTZoneObj arr[] = new ICFSecISOTZoneObj[len];
			Iterator<ICFSecISOTZoneObj> valIter = allISOTZone.values().iterator();
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
		Comparator<ICFSecISOTZoneObj> cmp = new Comparator<ICFSecISOTZoneObj>() {
			public int compare( ICFSecISOTZoneObj lhs, ICFSecISOTZoneObj rhs ) {
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
	public ICFSecISOTZoneObj readISOTZoneByIdIdx( short ISOTZoneId )
	{
		return( readISOTZoneByIdIdx( ISOTZoneId,
			false ) );
	}

	@Override
	public ICFSecISOTZoneObj readISOTZoneByIdIdx( short ISOTZoneId, boolean forceRead )
	{
		ICFSecISOTZoneObj obj = readISOTZone( ISOTZoneId, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecISOTZoneObj> readISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset )
	{
		return( readISOTZoneByOffsetIdx( TZHourOffset,
			TZMinOffset,
			false ) );
	}

	@Override
	public List<ICFSecISOTZoneObj> readISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset,
		boolean forceRead )
	{
		final String S_ProcName = "readISOTZoneByOffsetIdx";
		ICFSecISOTZoneByOffsetIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByOffsetIdxKey();
		key.setRequiredTZHourOffset( TZHourOffset );
		key.setRequiredTZMinOffset( TZMinOffset );
		Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> dict;
		if( indexByOffsetIdx == null ) {
			indexByOffsetIdx = new HashMap< ICFSecISOTZoneByOffsetIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > >();
		}
		if( ( ! forceRead ) && indexByOffsetIdx.containsKey( key ) ) {
			dict = indexByOffsetIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj>();
			ICFSecISOTZoneObj obj;
			ICFSecISOTZone[] recList = schema.getCFSecBackingStore().getTableISOTZone().readDerivedByOffsetIdx( null,
				TZHourOffset,
				TZMinOffset );
			ICFSecISOTZone rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getISOTZoneTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOTZoneObj realised = (ICFSecISOTZoneObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByOffsetIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecISOTZoneObj arr[] = new ICFSecISOTZoneObj[len];
		Iterator<ICFSecISOTZoneObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecISOTZoneObj> arrayList = new ArrayList<ICFSecISOTZoneObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOTZoneObj> cmp = new Comparator<ICFSecISOTZoneObj>() {
			@Override
			public int compare( ICFSecISOTZoneObj lhs, ICFSecISOTZoneObj rhs ) {
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
		List<ICFSecISOTZoneObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecISOTZoneObj readISOTZoneByUTZNameIdx( String TZName )
	{
		return( readISOTZoneByUTZNameIdx( TZName,
			false ) );
	}

	@Override
	public ICFSecISOTZoneObj readISOTZoneByUTZNameIdx( String TZName, boolean forceRead )
	{
		if( indexByUTZNameIdx == null ) {
			indexByUTZNameIdx = new HashMap< ICFSecISOTZoneByUTZNameIdxKey,
				ICFSecISOTZoneObj >();
		}
		ICFSecISOTZoneByUTZNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByUTZNameIdxKey();
		key.setRequiredTZName( TZName );
		ICFSecISOTZoneObj obj = null;
		if( ( ! forceRead ) && indexByUTZNameIdx.containsKey( key ) ) {
			obj = indexByUTZNameIdx.get( key );
		}
		else {
			ICFSecISOTZone rec = schema.getCFSecBackingStore().getTableISOTZone().readDerivedByUTZNameIdx( null,
				TZName );
			if( rec != null ) {
				obj = schema.getISOTZoneTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecISOTZoneObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecISOTZoneObj> readISOTZoneByIso8601Idx( String Iso8601 )
	{
		return( readISOTZoneByIso8601Idx( Iso8601,
			false ) );
	}

	@Override
	public List<ICFSecISOTZoneObj> readISOTZoneByIso8601Idx( String Iso8601,
		boolean forceRead )
	{
		final String S_ProcName = "readISOTZoneByIso8601Idx";
		ICFSecISOTZoneByIso8601IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByIso8601IdxKey();
		key.setRequiredIso8601( Iso8601 );
		Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> dict;
		if( indexByIso8601Idx == null ) {
			indexByIso8601Idx = new HashMap< ICFSecISOTZoneByIso8601IdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > >();
		}
		if( ( ! forceRead ) && indexByIso8601Idx.containsKey( key ) ) {
			dict = indexByIso8601Idx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj>();
			ICFSecISOTZoneObj obj;
			ICFSecISOTZone[] recList = schema.getCFSecBackingStore().getTableISOTZone().readDerivedByIso8601Idx( null,
				Iso8601 );
			ICFSecISOTZone rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getISOTZoneTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOTZoneObj realised = (ICFSecISOTZoneObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByIso8601Idx.put( key, dict );
		}
		int len = dict.size();
		ICFSecISOTZoneObj arr[] = new ICFSecISOTZoneObj[len];
		Iterator<ICFSecISOTZoneObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecISOTZoneObj> arrayList = new ArrayList<ICFSecISOTZoneObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOTZoneObj> cmp = new Comparator<ICFSecISOTZoneObj>() {
			@Override
			public int compare( ICFSecISOTZoneObj lhs, ICFSecISOTZoneObj rhs ) {
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
		List<ICFSecISOTZoneObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecISOTZoneObj readCachedISOTZoneByIdIdx( short ISOTZoneId )
	{
		ICFSecISOTZoneObj obj = null;
		obj = readCachedISOTZone( ISOTZoneId );
		return( obj );
	}

	@Override
	public List<ICFSecISOTZoneObj> readCachedISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset )
	{
		final String S_ProcName = "readCachedISOTZoneByOffsetIdx";
		ICFSecISOTZoneByOffsetIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByOffsetIdxKey();
		key.setRequiredTZHourOffset( TZHourOffset );
		key.setRequiredTZMinOffset( TZMinOffset );
		ArrayList<ICFSecISOTZoneObj> arrayList = new ArrayList<ICFSecISOTZoneObj>();
		if( indexByOffsetIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> dict;
			if( indexByOffsetIdx.containsKey( key ) ) {
				dict = indexByOffsetIdx.get( key );
				int len = dict.size();
				ICFSecISOTZoneObj arr[] = new ICFSecISOTZoneObj[len];
				Iterator<ICFSecISOTZoneObj> valIter = dict.values().iterator();
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
			ICFSecISOTZoneObj obj;
			Iterator<ICFSecISOTZoneObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecISOTZoneObj> cmp = new Comparator<ICFSecISOTZoneObj>() {
			@Override
			public int compare( ICFSecISOTZoneObj lhs, ICFSecISOTZoneObj rhs ) {
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
	public ICFSecISOTZoneObj readCachedISOTZoneByUTZNameIdx( String TZName )
	{
		ICFSecISOTZoneObj obj = null;
		ICFSecISOTZoneByUTZNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByUTZNameIdxKey();
		key.setRequiredTZName( TZName );
		if( indexByUTZNameIdx != null ) {
			if( indexByUTZNameIdx.containsKey( key ) ) {
				obj = indexByUTZNameIdx.get( key );
			}
			else {
				Iterator<ICFSecISOTZoneObj> valIter = members.values().iterator();
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
			Iterator<ICFSecISOTZoneObj> valIter = members.values().iterator();
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
	public List<ICFSecISOTZoneObj> readCachedISOTZoneByIso8601Idx( String Iso8601 )
	{
		final String S_ProcName = "readCachedISOTZoneByIso8601Idx";
		ICFSecISOTZoneByIso8601IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByIso8601IdxKey();
		key.setRequiredIso8601( Iso8601 );
		ArrayList<ICFSecISOTZoneObj> arrayList = new ArrayList<ICFSecISOTZoneObj>();
		if( indexByIso8601Idx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> dict;
			if( indexByIso8601Idx.containsKey( key ) ) {
				dict = indexByIso8601Idx.get( key );
				int len = dict.size();
				ICFSecISOTZoneObj arr[] = new ICFSecISOTZoneObj[len];
				Iterator<ICFSecISOTZoneObj> valIter = dict.values().iterator();
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
			ICFSecISOTZoneObj obj;
			Iterator<ICFSecISOTZoneObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecISOTZoneObj> cmp = new Comparator<ICFSecISOTZoneObj>() {
			@Override
			public int compare( ICFSecISOTZoneObj lhs, ICFSecISOTZoneObj rhs ) {
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
	public void deepDisposeISOTZoneByIdIdx( short ISOTZoneId )
	{
		ICFSecISOTZoneObj obj = readCachedISOTZoneByIdIdx( ISOTZoneId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset )
	{
		final String S_ProcName = "deepDisposeISOTZoneByOffsetIdx";
		ICFSecISOTZoneObj obj;
		List<ICFSecISOTZoneObj> arrayList = readCachedISOTZoneByOffsetIdx( TZHourOffset,
				TZMinOffset );
		if( arrayList != null )  {
			Iterator<ICFSecISOTZoneObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeISOTZoneByUTZNameIdx( String TZName )
	{
		ICFSecISOTZoneObj obj = readCachedISOTZoneByUTZNameIdx( TZName );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeISOTZoneByIso8601Idx( String Iso8601 )
	{
		final String S_ProcName = "deepDisposeISOTZoneByIso8601Idx";
		ICFSecISOTZoneObj obj;
		List<ICFSecISOTZoneObj> arrayList = readCachedISOTZoneByIso8601Idx( Iso8601 );
		if( arrayList != null )  {
			Iterator<ICFSecISOTZoneObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFSecISOTZoneObj updateISOTZone( ICFSecISOTZoneObj Obj ) {
		ICFSecISOTZoneObj obj = Obj;
		schema.getCFSecBackingStore().getTableISOTZone().updateISOTZone( null,
			Obj.getISOTZoneRec() );
		obj = (ICFSecISOTZoneObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteISOTZone( ICFSecISOTZoneObj Obj ) {
		ICFSecISOTZoneObj obj = Obj;
		schema.getCFSecBackingStore().getTableISOTZone().deleteISOTZone( null,
			obj.getISOTZoneRec() );
		Obj.forget();
	}

	@Override
	public void deleteISOTZoneByIdIdx( short ISOTZoneId )
	{
		ICFSecISOTZoneObj obj = readISOTZone(ISOTZoneId);
		if( obj != null ) {
			ICFSecISOTZoneEditObj editObj = (ICFSecISOTZoneEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecISOTZoneEditObj)obj.beginEdit();
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
		deepDisposeISOTZoneByIdIdx( ISOTZoneId );
	}

	@Override
	public void deleteISOTZoneByOffsetIdx( short TZHourOffset,
		short TZMinOffset )
	{
		ICFSecISOTZoneByOffsetIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByOffsetIdxKey();
		key.setRequiredTZHourOffset( TZHourOffset );
		key.setRequiredTZMinOffset( TZMinOffset );
		if( indexByOffsetIdx == null ) {
			indexByOffsetIdx = new HashMap< ICFSecISOTZoneByOffsetIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > >();
		}
		if( indexByOffsetIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> dict = indexByOffsetIdx.get( key );
			schema.getCFSecBackingStore().getTableISOTZone().deleteISOTZoneByOffsetIdx( null,
				TZHourOffset,
				TZMinOffset );
			Iterator<ICFSecISOTZoneObj> iter = dict.values().iterator();
			ICFSecISOTZoneObj obj;
			List<ICFSecISOTZoneObj> toForget = new LinkedList<ICFSecISOTZoneObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByOffsetIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableISOTZone().deleteISOTZoneByOffsetIdx( null,
				TZHourOffset,
				TZMinOffset );
		}
		deepDisposeISOTZoneByOffsetIdx( TZHourOffset,
				TZMinOffset );
	}

	@Override
	public void deleteISOTZoneByUTZNameIdx( String TZName )
	{
		if( indexByUTZNameIdx == null ) {
			indexByUTZNameIdx = new HashMap< ICFSecISOTZoneByUTZNameIdxKey,
				ICFSecISOTZoneObj >();
		}
		ICFSecISOTZoneByUTZNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByUTZNameIdxKey();
		key.setRequiredTZName( TZName );
		ICFSecISOTZoneObj obj = null;
		if( indexByUTZNameIdx.containsKey( key ) ) {
			obj = indexByUTZNameIdx.get( key );
			schema.getCFSecBackingStore().getTableISOTZone().deleteISOTZoneByUTZNameIdx( null,
				TZName );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableISOTZone().deleteISOTZoneByUTZNameIdx( null,
				TZName );
		}
		deepDisposeISOTZoneByUTZNameIdx( TZName );
	}

	@Override
	public void deleteISOTZoneByIso8601Idx( String Iso8601 )
	{
		ICFSecISOTZoneByIso8601IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOTZone().newByIso8601IdxKey();
		key.setRequiredIso8601( Iso8601 );
		if( indexByIso8601Idx == null ) {
			indexByIso8601Idx = new HashMap< ICFSecISOTZoneByIso8601IdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecISOTZoneObj > >();
		}
		if( indexByIso8601Idx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOTZoneObj> dict = indexByIso8601Idx.get( key );
			schema.getCFSecBackingStore().getTableISOTZone().deleteISOTZoneByIso8601Idx( null,
				Iso8601 );
			Iterator<ICFSecISOTZoneObj> iter = dict.values().iterator();
			ICFSecISOTZoneObj obj;
			List<ICFSecISOTZoneObj> toForget = new LinkedList<ICFSecISOTZoneObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByIso8601Idx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableISOTZone().deleteISOTZoneByIso8601Idx( null,
				Iso8601 );
		}
		deepDisposeISOTZoneByIso8601Idx( Iso8601 );
	}
}