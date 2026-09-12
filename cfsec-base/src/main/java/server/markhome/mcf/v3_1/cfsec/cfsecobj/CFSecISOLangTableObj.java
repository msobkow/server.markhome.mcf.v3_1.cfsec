// Description: Java 25 Table Object implementation for ISOLang.

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

public class CFSecISOLangTableObj
	implements ICFSecISOLangTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecISOLang.CLASS_CODE;
	protected static final int backingClassCode = ICFSecISOLang.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj> allISOLang;
	private Map< ICFSecISOLangByCode3IdxKey,
		ICFSecISOLangObj > indexByCode3Idx;
	private Map< ICFSecISOLangByCode2IdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj > > indexByCode2Idx;
	public static String TABLE_NAME = "ISOLang";
	public static String TABLE_DBNAME = "iso_lang";

	public CFSecISOLangTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOLangObj>();
		allISOLang = null;
		indexByCode3Idx = null;
		indexByCode2Idx = null;
	}

	public CFSecISOLangTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOLangObj>();
		allISOLang = null;
		indexByCode3Idx = null;
		indexByCode2Idx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecISOLangTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecISOLangTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allISOLang = null;
		indexByCode3Idx = null;
		indexByCode2Idx = null;
		List<ICFSecISOLangObj> toForget = new LinkedList<ICFSecISOLangObj>();
		ICFSecISOLangObj cur = null;
		Iterator<ICFSecISOLangObj> iter = members.values().iterator();
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
	 *	CFSecISOLangObj.
	 */
	@Override
	public ICFSecISOLangObj newInstance() {
		ICFSecISOLangObj inst = new CFSecISOLangObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecISOLangObj.
	 */
	@Override
	public ICFSecISOLangEditObj newEditInstance( ICFSecISOLangObj orig ) {
		ICFSecISOLangEditObj edit = new CFSecISOLangEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecISOLangObj realiseISOLang( ICFSecISOLangObj Obj ) {
		ICFSecISOLangObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecISOLangObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecISOLangObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByCode3Idx != null ) {
				ICFSecISOLangByCode3IdxKey keyCode3Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode3IdxKey();
				keyCode3Idx.setRequiredISO6392Code( keepObj.getRequiredISO6392Code() );
				indexByCode3Idx.remove( keyCode3Idx );
			}

			if( indexByCode2Idx != null ) {
				ICFSecISOLangByCode2IdxKey keyCode2Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode2IdxKey();
				keyCode2Idx.setOptionalISO6391Code( keepObj.getOptionalISO6391Code() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj > mapCode2Idx = indexByCode2Idx.get( keyCode2Idx );
				if( mapCode2Idx != null ) {
					mapCode2Idx.remove( keepObj.getPKey() );
					if( mapCode2Idx.size() <= 0 ) {
						indexByCode2Idx.remove( keyCode2Idx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByCode3Idx != null ) {
				ICFSecISOLangByCode3IdxKey keyCode3Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode3IdxKey();
				keyCode3Idx.setRequiredISO6392Code( keepObj.getRequiredISO6392Code() );
				indexByCode3Idx.put( keyCode3Idx, keepObj );
			}

			if( indexByCode2Idx != null ) {
				ICFSecISOLangByCode2IdxKey keyCode2Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode2IdxKey();
				keyCode2Idx.setOptionalISO6391Code( keepObj.getOptionalISO6391Code() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj > mapCode2Idx = indexByCode2Idx.get( keyCode2Idx );
				if( mapCode2Idx != null ) {
					mapCode2Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allISOLang != null ) {
				allISOLang.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allISOLang != null ) {
				allISOLang.put( keepObj.getPKey(), keepObj );
			}

			if( indexByCode3Idx != null ) {
				ICFSecISOLangByCode3IdxKey keyCode3Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode3IdxKey();
				keyCode3Idx.setRequiredISO6392Code( keepObj.getRequiredISO6392Code() );
				indexByCode3Idx.put( keyCode3Idx, keepObj );
			}

			if( indexByCode2Idx != null ) {
				ICFSecISOLangByCode2IdxKey keyCode2Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode2IdxKey();
				keyCode2Idx.setOptionalISO6391Code( keepObj.getOptionalISO6391Code() );
				Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj > mapCode2Idx = indexByCode2Idx.get( keyCode2Idx );
				if( mapCode2Idx != null ) {
					mapCode2Idx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecISOLangObj createISOLang( ICFSecISOLangObj Obj ) {
		ICFSecISOLangObj obj = Obj;
		ICFSecISOLang rec = obj.getISOLangRec();
		schema.getCFSecBackingStore().getTableISOLang().createISOLang(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecISOLangObj readISOLang( $implCommaIJavaOptAtomType$ pkey ) {
		return( readISOLang( pkey, false ) );
	}

	@Override
	public ICFSecISOLangObj readISOLang( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecISOLangObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecISOLang readRec = schema.getCFSecBackingStore().getTableISOLang().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getISOLangTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecISOLangObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecISOLangObj readCachedISOLang( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOLangObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeISOLang( ICFSecISOLangObj obj )
	{
		final String S_ProcName = "CFSecISOLangTableObj.reallyDeepDisposeISOLang() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecISOLangObj existing = readCachedISOLang( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecISOLangByCode3IdxKey keyCode3Idx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode3IdxKey();
		keyCode3Idx.setRequiredISO6392Code( existing.getRequiredISO6392Code() );

		ICFSecISOLangByCode2IdxKey keyCode2Idx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode2IdxKey();
		keyCode2Idx.setOptionalISO6391Code( existing.getOptionalISO6391Code() );


		schema.getISOCtryLangTableObj().deepDisposeISOCtryLangByLangIdx( existing.getRequiredISOLangId() );

		if( indexByCode3Idx != null ) {
			indexByCode3Idx.remove( keyCode3Idx );
		}

		if( indexByCode2Idx != null ) {
			if( indexByCode2Idx.containsKey( keyCode2Idx ) ) {
				indexByCode2Idx.get( keyCode2Idx ).remove( pkey );
				if( indexByCode2Idx.get( keyCode2Idx ).size() <= 0 ) {
					indexByCode2Idx.remove( keyCode2Idx );
				}
			}
		}


	}
	@Override
	public void deepDisposeISOLang( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOLangObj obj = readCachedISOLang( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecISOLangObj lockISOLang( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOLangObj locked = null;
		ICFSecISOLang lockRec = schema.getCFSecBackingStore().getTableISOLang().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getISOLangTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecISOLangObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockISOLang", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecISOLangObj> readAllISOLang() {
		return( readAllISOLang( false ) );
	}

	@Override
	public List<ICFSecISOLangObj> readAllISOLang( boolean forceRead ) {
		final String S_ProcName = "readAllISOLang";
		if( ( allISOLang == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecISOLangObj>();
			allISOLang = map;
			ICFSecISOLang[] recList = schema.getCFSecBackingStore().getTableISOLang().readAllDerived( null );
			ICFSecISOLang rec;
			ICFSecISOLangObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOLangObj realised = (ICFSecISOLangObj)obj.realise();
			}
		}
		int len = allISOLang.size();
		ICFSecISOLangObj arr[] = new ICFSecISOLangObj[len];
		Iterator<ICFSecISOLangObj> valIter = allISOLang.values().iterator();
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
		ArrayList<ICFSecISOLangObj> arrayList = new ArrayList<ICFSecISOLangObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOLangObj> cmp = new Comparator<ICFSecISOLangObj>() {
			@Override
			public int compare( ICFSecISOLangObj lhs, ICFSecISOLangObj rhs ) {
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
		List<ICFSecISOLangObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecISOLangObj> readCachedAllISOLang() {
		final String S_ProcName = "readCachedAllISOLang";
		ArrayList<ICFSecISOLangObj> arrayList = new ArrayList<ICFSecISOLangObj>();
		if( allISOLang != null ) {
			int len = allISOLang.size();
			ICFSecISOLangObj arr[] = new ICFSecISOLangObj[len];
			Iterator<ICFSecISOLangObj> valIter = allISOLang.values().iterator();
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
		Comparator<ICFSecISOLangObj> cmp = new Comparator<ICFSecISOLangObj>() {
			public int compare( ICFSecISOLangObj lhs, ICFSecISOLangObj rhs ) {
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
	public ICFSecISOLangObj readISOLangByIdIdx( short ISOLangId )
	{
		return( readISOLangByIdIdx( ISOLangId,
			false ) );
	}

	@Override
	public ICFSecISOLangObj readISOLangByIdIdx( short ISOLangId, boolean forceRead )
	{
		ICFSecISOLangObj obj = readISOLang( ISOLangId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecISOLangObj readISOLangByCode3Idx( String ISO6392Code )
	{
		return( readISOLangByCode3Idx( ISO6392Code,
			false ) );
	}

	@Override
	public ICFSecISOLangObj readISOLangByCode3Idx( String ISO6392Code, boolean forceRead )
	{
		if( indexByCode3Idx == null ) {
			indexByCode3Idx = new HashMap< ICFSecISOLangByCode3IdxKey,
				ICFSecISOLangObj >();
		}
		ICFSecISOLangByCode3IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode3IdxKey();
		key.setRequiredISO6392Code( ISO6392Code );
		ICFSecISOLangObj obj = null;
		if( ( ! forceRead ) && indexByCode3Idx.containsKey( key ) ) {
			obj = indexByCode3Idx.get( key );
		}
		else {
			ICFSecISOLang rec = schema.getCFSecBackingStore().getTableISOLang().readDerivedByCode3Idx( null,
				ISO6392Code );
			if( rec != null ) {
				obj = schema.getISOLangTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecISOLangObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecISOLangObj> readISOLangByCode2Idx( String ISO6391Code )
	{
		return( readISOLangByCode2Idx( ISO6391Code,
			false ) );
	}

	@Override
	public List<ICFSecISOLangObj> readISOLangByCode2Idx( String ISO6391Code,
		boolean forceRead )
	{
		final String S_ProcName = "readISOLangByCode2Idx";
		ICFSecISOLangByCode2IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode2IdxKey();
		key.setOptionalISO6391Code( ISO6391Code );
		Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj> dict;
		if( indexByCode2Idx == null ) {
			indexByCode2Idx = new HashMap< ICFSecISOLangByCode2IdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecISOLangObj > >();
		}
		if( ( ! forceRead ) && indexByCode2Idx.containsKey( key ) ) {
			dict = indexByCode2Idx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOLangObj>();
			ICFSecISOLangObj obj;
			ICFSecISOLang[] recList = schema.getCFSecBackingStore().getTableISOLang().readDerivedByCode2Idx( null,
				ISO6391Code );
			ICFSecISOLang rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getISOLangTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOLangObj realised = (ICFSecISOLangObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByCode2Idx.put( key, dict );
		}
		int len = dict.size();
		ICFSecISOLangObj arr[] = new ICFSecISOLangObj[len];
		Iterator<ICFSecISOLangObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecISOLangObj> arrayList = new ArrayList<ICFSecISOLangObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOLangObj> cmp = new Comparator<ICFSecISOLangObj>() {
			@Override
			public int compare( ICFSecISOLangObj lhs, ICFSecISOLangObj rhs ) {
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
		List<ICFSecISOLangObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecISOLangObj readCachedISOLangByIdIdx( short ISOLangId )
	{
		ICFSecISOLangObj obj = null;
		obj = readCachedISOLang( ISOLangId );
		return( obj );
	}

	@Override
	public ICFSecISOLangObj readCachedISOLangByCode3Idx( String ISO6392Code )
	{
		ICFSecISOLangObj obj = null;
		ICFSecISOLangByCode3IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode3IdxKey();
		key.setRequiredISO6392Code( ISO6392Code );
		if( indexByCode3Idx != null ) {
			if( indexByCode3Idx.containsKey( key ) ) {
				obj = indexByCode3Idx.get( key );
			}
			else {
				Iterator<ICFSecISOLangObj> valIter = members.values().iterator();
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
			Iterator<ICFSecISOLangObj> valIter = members.values().iterator();
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
	public List<ICFSecISOLangObj> readCachedISOLangByCode2Idx( String ISO6391Code )
	{
		final String S_ProcName = "readCachedISOLangByCode2Idx";
		ICFSecISOLangByCode2IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode2IdxKey();
		key.setOptionalISO6391Code( ISO6391Code );
		ArrayList<ICFSecISOLangObj> arrayList = new ArrayList<ICFSecISOLangObj>();
		if( indexByCode2Idx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj> dict;
			if( indexByCode2Idx.containsKey( key ) ) {
				dict = indexByCode2Idx.get( key );
				int len = dict.size();
				ICFSecISOLangObj arr[] = new ICFSecISOLangObj[len];
				Iterator<ICFSecISOLangObj> valIter = dict.values().iterator();
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
			ICFSecISOLangObj obj;
			Iterator<ICFSecISOLangObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecISOLangObj> cmp = new Comparator<ICFSecISOLangObj>() {
			@Override
			public int compare( ICFSecISOLangObj lhs, ICFSecISOLangObj rhs ) {
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
	public void deepDisposeISOLangByIdIdx( short ISOLangId )
	{
		ICFSecISOLangObj obj = readCachedISOLangByIdIdx( ISOLangId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeISOLangByCode3Idx( String ISO6392Code )
	{
		ICFSecISOLangObj obj = readCachedISOLangByCode3Idx( ISO6392Code );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeISOLangByCode2Idx( String ISO6391Code )
	{
		final String S_ProcName = "deepDisposeISOLangByCode2Idx";
		ICFSecISOLangObj obj;
		List<ICFSecISOLangObj> arrayList = readCachedISOLangByCode2Idx( ISO6391Code );
		if( arrayList != null )  {
			Iterator<ICFSecISOLangObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFSecISOLangObj updateISOLang( ICFSecISOLangObj Obj ) {
		ICFSecISOLangObj obj = Obj;
		schema.getCFSecBackingStore().getTableISOLang().updateISOLang( null,
			Obj.getISOLangRec() );
		obj = (ICFSecISOLangObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteISOLang( ICFSecISOLangObj Obj ) {
		ICFSecISOLangObj obj = Obj;
		schema.getCFSecBackingStore().getTableISOLang().deleteISOLang( null,
			obj.getISOLangRec() );
		Obj.forget();
	}

	@Override
	public void deleteISOLangByIdIdx( short ISOLangId )
	{
		ICFSecISOLangObj obj = readISOLang(ISOLangId);
		if( obj != null ) {
			ICFSecISOLangEditObj editObj = (ICFSecISOLangEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecISOLangEditObj)obj.beginEdit();
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
		deepDisposeISOLangByIdIdx( ISOLangId );
	}

	@Override
	public void deleteISOLangByCode3Idx( String ISO6392Code )
	{
		if( indexByCode3Idx == null ) {
			indexByCode3Idx = new HashMap< ICFSecISOLangByCode3IdxKey,
				ICFSecISOLangObj >();
		}
		ICFSecISOLangByCode3IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode3IdxKey();
		key.setRequiredISO6392Code( ISO6392Code );
		ICFSecISOLangObj obj = null;
		if( indexByCode3Idx.containsKey( key ) ) {
			obj = indexByCode3Idx.get( key );
			schema.getCFSecBackingStore().getTableISOLang().deleteISOLangByCode3Idx( null,
				ISO6392Code );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableISOLang().deleteISOLangByCode3Idx( null,
				ISO6392Code );
		}
		deepDisposeISOLangByCode3Idx( ISO6392Code );
	}

	@Override
	public void deleteISOLangByCode2Idx( String ISO6391Code )
	{
		ICFSecISOLangByCode2IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOLang().newByCode2IdxKey();
		key.setOptionalISO6391Code( ISO6391Code );
		if( indexByCode2Idx == null ) {
			indexByCode2Idx = new HashMap< ICFSecISOLangByCode2IdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecISOLangObj > >();
		}
		if( indexByCode2Idx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOLangObj> dict = indexByCode2Idx.get( key );
			schema.getCFSecBackingStore().getTableISOLang().deleteISOLangByCode2Idx( null,
				ISO6391Code );
			Iterator<ICFSecISOLangObj> iter = dict.values().iterator();
			ICFSecISOLangObj obj;
			List<ICFSecISOLangObj> toForget = new LinkedList<ICFSecISOLangObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByCode2Idx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableISOLang().deleteISOLangByCode2Idx( null,
				ISO6391Code );
		}
		deepDisposeISOLangByCode2Idx( ISO6391Code );
	}
}