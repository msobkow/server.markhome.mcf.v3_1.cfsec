// Description: Java 25 Table Object implementation for ISOCtryLang.

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

public class CFSecISOCtryLangTableObj
	implements ICFSecISOCtryLangTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecISOCtryLang.CLASS_CODE;
	protected static final int backingClassCode = ICFSecISOCtryLang.CLASS_CODE;
	private Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> members;
	private Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> allISOCtryLang;
	private Map< ICFSecISOCtryLangByCtryIdxKey,
		Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > > indexByCtryIdx;
	private Map< ICFSecISOCtryLangByLangIdxKey,
		Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > > indexByLangIdx;
	public static String TABLE_NAME = "ISOCtryLang";
	public static String TABLE_DBNAME = "iso_cntrylng";

	public CFSecISOCtryLangTableObj() {
		schema = null;
		members = new HashMap<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj>();
		allISOCtryLang = null;
		indexByCtryIdx = null;
		indexByLangIdx = null;
	}

	public CFSecISOCtryLangTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj>();
		allISOCtryLang = null;
		indexByCtryIdx = null;
		indexByLangIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecISOCtryLangTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecISOCtryLangTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allISOCtryLang = null;
		indexByCtryIdx = null;
		indexByLangIdx = null;
		List<ICFSecISOCtryLangObj> toForget = new LinkedList<ICFSecISOCtryLangObj>();
		ICFSecISOCtryLangObj cur = null;
		Iterator<ICFSecISOCtryLangObj> iter = members.values().iterator();
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
	 *	CFSecISOCtryLangObj.
	 */
	@Override
	public ICFSecISOCtryLangObj newInstance() {
		ICFSecISOCtryLangObj inst = new CFSecISOCtryLangObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecISOCtryLangObj.
	 */
	@Override
	public ICFSecISOCtryLangEditObj newEditInstance( ICFSecISOCtryLangObj orig ) {
		ICFSecISOCtryLangEditObj edit = new CFSecISOCtryLangEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecISOCtryLangObj realiseISOCtryLang( ICFSecISOCtryLangObj Obj ) {
		ICFSecISOCtryLangObj obj = Obj;
		ICFSecISOCtryLangPKey pkey = obj.getPKey();
		ICFSecISOCtryLangObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecISOCtryLangObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByCtryIdx != null ) {
				ICFSecISOCtryLangByCtryIdxKey keyCtryIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByCtryIdxKey();
				keyCtryIdx.setRequiredISOCtryId( keepObj.getRequiredISOCtryId() );
				Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > mapCtryIdx = indexByCtryIdx.get( keyCtryIdx );
				if( mapCtryIdx != null ) {
					mapCtryIdx.remove( keepObj.getPKey() );
					if( mapCtryIdx.size() <= 0 ) {
						indexByCtryIdx.remove( keyCtryIdx );
					}
				}
			}

			if( indexByLangIdx != null ) {
				ICFSecISOCtryLangByLangIdxKey keyLangIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByLangIdxKey();
				keyLangIdx.setRequiredISOLangId( keepObj.getRequiredISOLangId() );
				Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > mapLangIdx = indexByLangIdx.get( keyLangIdx );
				if( mapLangIdx != null ) {
					mapLangIdx.remove( keepObj.getPKey() );
					if( mapLangIdx.size() <= 0 ) {
						indexByLangIdx.remove( keyLangIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByCtryIdx != null ) {
				ICFSecISOCtryLangByCtryIdxKey keyCtryIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByCtryIdxKey();
				keyCtryIdx.setRequiredISOCtryId( keepObj.getRequiredISOCtryId() );
				Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > mapCtryIdx = indexByCtryIdx.get( keyCtryIdx );
				if( mapCtryIdx != null ) {
					mapCtryIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLangIdx != null ) {
				ICFSecISOCtryLangByLangIdxKey keyLangIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByLangIdxKey();
				keyLangIdx.setRequiredISOLangId( keepObj.getRequiredISOLangId() );
				Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > mapLangIdx = indexByLangIdx.get( keyLangIdx );
				if( mapLangIdx != null ) {
					mapLangIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allISOCtryLang != null ) {
				allISOCtryLang.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allISOCtryLang != null ) {
				allISOCtryLang.put( keepObj.getPKey(), keepObj );
			}

			if( indexByCtryIdx != null ) {
				ICFSecISOCtryLangByCtryIdxKey keyCtryIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByCtryIdxKey();
				keyCtryIdx.setRequiredISOCtryId( keepObj.getRequiredISOCtryId() );
				Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > mapCtryIdx = indexByCtryIdx.get( keyCtryIdx );
				if( mapCtryIdx != null ) {
					mapCtryIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLangIdx != null ) {
				ICFSecISOCtryLangByLangIdxKey keyLangIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByLangIdxKey();
				keyLangIdx.setRequiredISOLangId( keepObj.getRequiredISOLangId() );
				Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > mapLangIdx = indexByLangIdx.get( keyLangIdx );
				if( mapLangIdx != null ) {
					mapLangIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecISOCtryLangObj createISOCtryLang( ICFSecISOCtryLangObj Obj ) {
		ICFSecISOCtryLangObj obj = Obj;
		ICFSecISOCtryLang rec = obj.getISOCtryLangRec();
		schema.getCFSecBackingStore().getTableISOCtryLang().createISOCtryLang(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecISOCtryLangObj readISOCtryLang( ICFSecISOCtryLangPKey pkey ) {
		return( readISOCtryLang( pkey, false ) );
	}

	@Override
	public ICFSecISOCtryLangObj readISOCtryLang( ICFSecISOCtryLangPKey pkey, boolean forceRead ) {
		ICFSecISOCtryLangObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecISOCtryLang readRec = schema.getCFSecBackingStore().getTableISOCtryLang().readDerivedByIdIdx( null,
						pkey.getRequiredISOCtryId(),
						pkey.getRequiredISOLangId() );
			if( readRec != null ) {
				obj = schema.getISOCtryLangTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecISOCtryLangObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecISOCtryLangObj readISOCtryLang( short ISOCtryId,
		short ISOLangId ) {
		return( readISOCtryLang( ISOCtryId,
			ISOLangId, false ) );
	}

	@Override
	public ICFSecISOCtryLangObj readISOCtryLang( short ISOCtryId,
		short ISOLangId, boolean forceRead ) {
		ICFSecISOCtryLangObj obj = null;
		ICFSecISOCtryLang readRec = schema.getCFSecBackingStore().getTableISOCtryLang().readDerivedByIdIdx( null,
			ISOCtryId,
			ISOLangId );
		if( readRec != null ) {
				obj = schema.getISOCtryLangTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecISOCtryLangObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecISOCtryLangObj readCachedISOCtryLang( ICFSecISOCtryLangPKey pkey ) {
		ICFSecISOCtryLangObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeISOCtryLang( ICFSecISOCtryLangObj obj )
	{
		final String S_ProcName = "CFSecISOCtryLangTableObj.reallyDeepDisposeISOCtryLang() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecISOCtryLangPKey pkey = obj.getPKey();
		ICFSecISOCtryLangObj existing = readCachedISOCtryLang( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecISOCtryLangByCtryIdxKey keyCtryIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByCtryIdxKey();
		keyCtryIdx.setRequiredISOCtryId( existing.getRequiredISOCtryId() );

		ICFSecISOCtryLangByLangIdxKey keyLangIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByLangIdxKey();
		keyLangIdx.setRequiredISOLangId( existing.getRequiredISOLangId() );



		if( indexByCtryIdx != null ) {
			if( indexByCtryIdx.containsKey( keyCtryIdx ) ) {
				indexByCtryIdx.get( keyCtryIdx ).remove( pkey );
				if( indexByCtryIdx.get( keyCtryIdx ).size() <= 0 ) {
					indexByCtryIdx.remove( keyCtryIdx );
				}
			}
		}

		if( indexByLangIdx != null ) {
			if( indexByLangIdx.containsKey( keyLangIdx ) ) {
				indexByLangIdx.get( keyLangIdx ).remove( pkey );
				if( indexByLangIdx.get( keyLangIdx ).size() <= 0 ) {
					indexByLangIdx.remove( keyLangIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeISOCtryLang( ICFSecISOCtryLangPKey pkey ) {
		ICFSecISOCtryLangObj obj = readCachedISOCtryLang( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecISOCtryLangObj lockISOCtryLang( ICFSecISOCtryLangPKey pkey ) {
		ICFSecISOCtryLangObj locked = null;
		ICFSecISOCtryLang lockRec = schema.getCFSecBackingStore().getTableISOCtryLang().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getISOCtryLangTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecISOCtryLangObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockISOCtryLang", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readAllISOCtryLang() {
		return( readAllISOCtryLang( false ) );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readAllISOCtryLang( boolean forceRead ) {
		final String S_ProcName = "readAllISOCtryLang";
		if( ( allISOCtryLang == null ) || forceRead ) {
			Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> map = new HashMap<ICFSecISOCtryLangPKey,ICFSecISOCtryLangObj>();
			allISOCtryLang = map;
			ICFSecISOCtryLang[] recList = schema.getCFSecBackingStore().getTableISOCtryLang().readAllDerived( null );
			ICFSecISOCtryLang rec;
			ICFSecISOCtryLangObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOCtryLangObj realised = (ICFSecISOCtryLangObj)obj.realise();
			}
		}
		int len = allISOCtryLang.size();
		ICFSecISOCtryLangObj arr[] = new ICFSecISOCtryLangObj[len];
		Iterator<ICFSecISOCtryLangObj> valIter = allISOCtryLang.values().iterator();
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
		ArrayList<ICFSecISOCtryLangObj> arrayList = new ArrayList<ICFSecISOCtryLangObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOCtryLangObj> cmp = new Comparator<ICFSecISOCtryLangObj>() {
			@Override
			public int compare( ICFSecISOCtryLangObj lhs, ICFSecISOCtryLangObj rhs ) {
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
					ICFSecISOCtryLangPKey lhsPKey = lhs.getPKey();
					ICFSecISOCtryLangPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecISOCtryLangObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readCachedAllISOCtryLang() {
		final String S_ProcName = "readCachedAllISOCtryLang";
		ArrayList<ICFSecISOCtryLangObj> arrayList = new ArrayList<ICFSecISOCtryLangObj>();
		if( allISOCtryLang != null ) {
			int len = allISOCtryLang.size();
			ICFSecISOCtryLangObj arr[] = new ICFSecISOCtryLangObj[len];
			Iterator<ICFSecISOCtryLangObj> valIter = allISOCtryLang.values().iterator();
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
		Comparator<ICFSecISOCtryLangObj> cmp = new Comparator<ICFSecISOCtryLangObj>() {
			public int compare( ICFSecISOCtryLangObj lhs, ICFSecISOCtryLangObj rhs ) {
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
					ICFSecISOCtryLangPKey lhsPKey = lhs.getPKey();
					ICFSecISOCtryLangPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecISOCtryLangObj readISOCtryLangByIdIdx( short ISOCtryId,
		short ISOLangId )
	{
		return( readISOCtryLangByIdIdx( ISOCtryId,
			ISOLangId,
			false ) );
	}

	@Override
	public ICFSecISOCtryLangObj readISOCtryLangByIdIdx( short ISOCtryId,
		short ISOLangId, boolean forceRead )
	{
		ICFSecISOCtryLangPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newPKey();
		pkey.setRequiredISOCtryId( ISOCtryId );
		pkey.setRequiredISOLangId( ISOLangId );
		ICFSecISOCtryLangObj obj = readISOCtryLang( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readISOCtryLangByCtryIdx( short ISOCtryId )
	{
		return( readISOCtryLangByCtryIdx( ISOCtryId,
			false ) );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readISOCtryLangByCtryIdx( short ISOCtryId,
		boolean forceRead )
	{
		final String S_ProcName = "readISOCtryLangByCtryIdx";
		ICFSecISOCtryLangByCtryIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByCtryIdxKey();
		key.setRequiredISOCtryId( ISOCtryId );
		Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> dict;
		if( indexByCtryIdx == null ) {
			indexByCtryIdx = new HashMap< ICFSecISOCtryLangByCtryIdxKey,
				Map< ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > >();
		}
		if( ( ! forceRead ) && indexByCtryIdx.containsKey( key ) ) {
			dict = indexByCtryIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj>();
			ICFSecISOCtryLangObj obj;
			ICFSecISOCtryLang[] recList = schema.getCFSecBackingStore().getTableISOCtryLang().readDerivedByCtryIdx( null,
				ISOCtryId );
			ICFSecISOCtryLang rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getISOCtryLangTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOCtryLangObj realised = (ICFSecISOCtryLangObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByCtryIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecISOCtryLangObj arr[] = new ICFSecISOCtryLangObj[len];
		Iterator<ICFSecISOCtryLangObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecISOCtryLangObj> arrayList = new ArrayList<ICFSecISOCtryLangObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOCtryLangObj> cmp = new Comparator<ICFSecISOCtryLangObj>() {
			@Override
			public int compare( ICFSecISOCtryLangObj lhs, ICFSecISOCtryLangObj rhs ) {
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
					ICFSecISOCtryLangPKey lhsPKey = lhs.getPKey();
					ICFSecISOCtryLangPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecISOCtryLangObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readISOCtryLangByLangIdx( short ISOLangId )
	{
		return( readISOCtryLangByLangIdx( ISOLangId,
			false ) );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readISOCtryLangByLangIdx( short ISOLangId,
		boolean forceRead )
	{
		final String S_ProcName = "readISOCtryLangByLangIdx";
		ICFSecISOCtryLangByLangIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByLangIdxKey();
		key.setRequiredISOLangId( ISOLangId );
		Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> dict;
		if( indexByLangIdx == null ) {
			indexByLangIdx = new HashMap< ICFSecISOCtryLangByLangIdxKey,
				Map< ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > >();
		}
		if( ( ! forceRead ) && indexByLangIdx.containsKey( key ) ) {
			dict = indexByLangIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj>();
			ICFSecISOCtryLangObj obj;
			ICFSecISOCtryLang[] recList = schema.getCFSecBackingStore().getTableISOCtryLang().readDerivedByLangIdx( null,
				ISOLangId );
			ICFSecISOCtryLang rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getISOCtryLangTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOCtryLangObj realised = (ICFSecISOCtryLangObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByLangIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecISOCtryLangObj arr[] = new ICFSecISOCtryLangObj[len];
		Iterator<ICFSecISOCtryLangObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecISOCtryLangObj> arrayList = new ArrayList<ICFSecISOCtryLangObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOCtryLangObj> cmp = new Comparator<ICFSecISOCtryLangObj>() {
			@Override
			public int compare( ICFSecISOCtryLangObj lhs, ICFSecISOCtryLangObj rhs ) {
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
					ICFSecISOCtryLangPKey lhsPKey = lhs.getPKey();
					ICFSecISOCtryLangPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecISOCtryLangObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecISOCtryLangObj readCachedISOCtryLangByIdIdx( short ISOCtryId,
		short ISOLangId )
	{
		ICFSecISOCtryLangObj obj = null;
		ICFSecISOCtryLangPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newPKey();
		pkey.setRequiredISOCtryId( ISOCtryId );
		pkey.setRequiredISOLangId( ISOLangId );
		pkey.setRequiredISOCtryId( ISOCtryId );
		pkey.setRequiredISOLangId( ISOLangId );
		obj = readCachedISOCtryLang( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readCachedISOCtryLangByCtryIdx( short ISOCtryId )
	{
		final String S_ProcName = "readCachedISOCtryLangByCtryIdx";
		ICFSecISOCtryLangByCtryIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByCtryIdxKey();
		key.setRequiredISOCtryId( ISOCtryId );
		ArrayList<ICFSecISOCtryLangObj> arrayList = new ArrayList<ICFSecISOCtryLangObj>();
		if( indexByCtryIdx != null ) {
			Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> dict;
			if( indexByCtryIdx.containsKey( key ) ) {
				dict = indexByCtryIdx.get( key );
				int len = dict.size();
				ICFSecISOCtryLangObj arr[] = new ICFSecISOCtryLangObj[len];
				Iterator<ICFSecISOCtryLangObj> valIter = dict.values().iterator();
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
			ICFSecISOCtryLangObj obj;
			Iterator<ICFSecISOCtryLangObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecISOCtryLangObj> cmp = new Comparator<ICFSecISOCtryLangObj>() {
			@Override
			public int compare( ICFSecISOCtryLangObj lhs, ICFSecISOCtryLangObj rhs ) {
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
					ICFSecISOCtryLangPKey lhsPKey = lhs.getPKey();
					ICFSecISOCtryLangPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecISOCtryLangObj> readCachedISOCtryLangByLangIdx( short ISOLangId )
	{
		final String S_ProcName = "readCachedISOCtryLangByLangIdx";
		ICFSecISOCtryLangByLangIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByLangIdxKey();
		key.setRequiredISOLangId( ISOLangId );
		ArrayList<ICFSecISOCtryLangObj> arrayList = new ArrayList<ICFSecISOCtryLangObj>();
		if( indexByLangIdx != null ) {
			Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> dict;
			if( indexByLangIdx.containsKey( key ) ) {
				dict = indexByLangIdx.get( key );
				int len = dict.size();
				ICFSecISOCtryLangObj arr[] = new ICFSecISOCtryLangObj[len];
				Iterator<ICFSecISOCtryLangObj> valIter = dict.values().iterator();
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
			ICFSecISOCtryLangObj obj;
			Iterator<ICFSecISOCtryLangObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecISOCtryLangObj> cmp = new Comparator<ICFSecISOCtryLangObj>() {
			@Override
			public int compare( ICFSecISOCtryLangObj lhs, ICFSecISOCtryLangObj rhs ) {
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
					ICFSecISOCtryLangPKey lhsPKey = lhs.getPKey();
					ICFSecISOCtryLangPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeISOCtryLangByIdIdx( short ISOCtryId,
		short ISOLangId )
	{
		ICFSecISOCtryLangObj obj = readCachedISOCtryLangByIdIdx( ISOCtryId,
				ISOLangId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeISOCtryLangByCtryIdx( short ISOCtryId )
	{
		final String S_ProcName = "deepDisposeISOCtryLangByCtryIdx";
		ICFSecISOCtryLangObj obj;
		List<ICFSecISOCtryLangObj> arrayList = readCachedISOCtryLangByCtryIdx( ISOCtryId );
		if( arrayList != null )  {
			Iterator<ICFSecISOCtryLangObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeISOCtryLangByLangIdx( short ISOLangId )
	{
		final String S_ProcName = "deepDisposeISOCtryLangByLangIdx";
		ICFSecISOCtryLangObj obj;
		List<ICFSecISOCtryLangObj> arrayList = readCachedISOCtryLangByLangIdx( ISOLangId );
		if( arrayList != null )  {
			Iterator<ICFSecISOCtryLangObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFSecISOCtryLangObj updateISOCtryLang( ICFSecISOCtryLangObj Obj ) {
		ICFSecISOCtryLangObj obj = Obj;
		schema.getCFSecBackingStore().getTableISOCtryLang().updateISOCtryLang( null,
			Obj.getISOCtryLangRec() );
		obj = (ICFSecISOCtryLangObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteISOCtryLang( ICFSecISOCtryLangObj Obj ) {
		ICFSecISOCtryLangObj obj = Obj;
		schema.getCFSecBackingStore().getTableISOCtryLang().deleteISOCtryLang( null,
			obj.getISOCtryLangRec() );
		Obj.forget();
	}

	@Override
	public void deleteISOCtryLangByIdIdx( short ISOCtryId,
		short ISOLangId )
	{
		ICFSecISOCtryLangObj obj = readISOCtryLang(ISOCtryId,
				ISOLangId);
		if( obj != null ) {
			ICFSecISOCtryLangEditObj editObj = (ICFSecISOCtryLangEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecISOCtryLangEditObj)obj.beginEdit();
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
		deepDisposeISOCtryLangByIdIdx( ISOCtryId,
				ISOLangId );
	}

	@Override
	public void deleteISOCtryLangByCtryIdx( short ISOCtryId )
	{
		ICFSecISOCtryLangByCtryIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByCtryIdxKey();
		key.setRequiredISOCtryId( ISOCtryId );
		if( indexByCtryIdx == null ) {
			indexByCtryIdx = new HashMap< ICFSecISOCtryLangByCtryIdxKey,
				Map< ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > >();
		}
		if( indexByCtryIdx.containsKey( key ) ) {
			Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> dict = indexByCtryIdx.get( key );
			schema.getCFSecBackingStore().getTableISOCtryLang().deleteISOCtryLangByCtryIdx( null,
				ISOCtryId );
			Iterator<ICFSecISOCtryLangObj> iter = dict.values().iterator();
			ICFSecISOCtryLangObj obj;
			List<ICFSecISOCtryLangObj> toForget = new LinkedList<ICFSecISOCtryLangObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByCtryIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableISOCtryLang().deleteISOCtryLangByCtryIdx( null,
				ISOCtryId );
		}
		deepDisposeISOCtryLangByCtryIdx( ISOCtryId );
	}

	@Override
	public void deleteISOCtryLangByLangIdx( short ISOLangId )
	{
		ICFSecISOCtryLangByLangIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtryLang().newByLangIdxKey();
		key.setRequiredISOLangId( ISOLangId );
		if( indexByLangIdx == null ) {
			indexByLangIdx = new HashMap< ICFSecISOCtryLangByLangIdxKey,
				Map< ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj > >();
		}
		if( indexByLangIdx.containsKey( key ) ) {
			Map<ICFSecISOCtryLangPKey, ICFSecISOCtryLangObj> dict = indexByLangIdx.get( key );
			schema.getCFSecBackingStore().getTableISOCtryLang().deleteISOCtryLangByLangIdx( null,
				ISOLangId );
			Iterator<ICFSecISOCtryLangObj> iter = dict.values().iterator();
			ICFSecISOCtryLangObj obj;
			List<ICFSecISOCtryLangObj> toForget = new LinkedList<ICFSecISOCtryLangObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByLangIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableISOCtryLang().deleteISOCtryLangByLangIdx( null,
				ISOLangId );
		}
		deepDisposeISOCtryLangByLangIdx( ISOLangId );
	}
}