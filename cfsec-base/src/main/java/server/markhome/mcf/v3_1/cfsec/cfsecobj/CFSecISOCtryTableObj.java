// Description: Java 25 Table Object implementation for ISOCtry.

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

public class CFSecISOCtryTableObj
	implements ICFSecISOCtryTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecISOCtry.CLASS_CODE;
	protected static final int backingClassCode = ICFSecISOCtry.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecISOCtryObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecISOCtryObj> allISOCtry;
	private Map< ICFSecISOCtryByISOCodeIdxKey,
		ICFSecISOCtryObj > indexByISOCodeIdx;
	private Map< ICFSecISOCtryByNameIdxKey,
		ICFSecISOCtryObj > indexByNameIdx;
	public static String TABLE_NAME = "ISOCtry";
	public static String TABLE_DBNAME = "iso_cntry";

	public CFSecISOCtryTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOCtryObj>();
		allISOCtry = null;
		indexByISOCodeIdx = null;
		indexByNameIdx = null;
	}

	public CFSecISOCtryTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecISOCtryObj>();
		allISOCtry = null;
		indexByISOCodeIdx = null;
		indexByNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecISOCtryTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecISOCtryTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allISOCtry = null;
		indexByISOCodeIdx = null;
		indexByNameIdx = null;
		List<ICFSecISOCtryObj> toForget = new LinkedList<ICFSecISOCtryObj>();
		ICFSecISOCtryObj cur = null;
		Iterator<ICFSecISOCtryObj> iter = members.values().iterator();
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
	 *	CFSecISOCtryObj.
	 */
	@Override
	public ICFSecISOCtryObj newInstance() {
		ICFSecISOCtryObj inst = new CFSecISOCtryObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecISOCtryObj.
	 */
	@Override
	public ICFSecISOCtryEditObj newEditInstance( ICFSecISOCtryObj orig ) {
		ICFSecISOCtryEditObj edit = new CFSecISOCtryEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecISOCtryObj realiseISOCtry( ICFSecISOCtryObj Obj ) {
		ICFSecISOCtryObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecISOCtryObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecISOCtryObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByISOCodeIdx != null ) {
				ICFSecISOCtryByISOCodeIdxKey keyISOCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByISOCodeIdxKey();
				keyISOCodeIdx.setRequiredISOCode( keepObj.getRequiredISOCode() );
				indexByISOCodeIdx.remove( keyISOCodeIdx );
			}

			if( indexByNameIdx != null ) {
				ICFSecISOCtryByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByNameIdx.remove( keyNameIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByISOCodeIdx != null ) {
				ICFSecISOCtryByISOCodeIdxKey keyISOCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByISOCodeIdxKey();
				keyISOCodeIdx.setRequiredISOCode( keepObj.getRequiredISOCode() );
				indexByISOCodeIdx.put( keyISOCodeIdx, keepObj );
			}

			if( indexByNameIdx != null ) {
				ICFSecISOCtryByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByNameIdx.put( keyNameIdx, keepObj );
			}

			if( allISOCtry != null ) {
				allISOCtry.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allISOCtry != null ) {
				allISOCtry.put( keepObj.getPKey(), keepObj );
			}

			if( indexByISOCodeIdx != null ) {
				ICFSecISOCtryByISOCodeIdxKey keyISOCodeIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByISOCodeIdxKey();
				keyISOCodeIdx.setRequiredISOCode( keepObj.getRequiredISOCode() );
				indexByISOCodeIdx.put( keyISOCodeIdx, keepObj );
			}

			if( indexByNameIdx != null ) {
				ICFSecISOCtryByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByNameIdx.put( keyNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecISOCtryObj createISOCtry( ICFSecISOCtryObj Obj ) {
		ICFSecISOCtryObj obj = Obj;
		ICFSecISOCtry rec = obj.getISOCtryRec();
		schema.getCFSecBackingStore().getTableISOCtry().createISOCtry(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecISOCtryObj readISOCtry( $implCommaIJavaOptAtomType$ pkey ) {
		return( readISOCtry( pkey, false ) );
	}

	@Override
	public ICFSecISOCtryObj readISOCtry( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecISOCtryObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecISOCtry readRec = schema.getCFSecBackingStore().getTableISOCtry().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getISOCtryTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecISOCtryObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecISOCtryObj readCachedISOCtry( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOCtryObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeISOCtry( ICFSecISOCtryObj obj )
	{
		final String S_ProcName = "CFSecISOCtryTableObj.reallyDeepDisposeISOCtry() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecISOCtryObj existing = readCachedISOCtry( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecISOCtryByISOCodeIdxKey keyISOCodeIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByISOCodeIdxKey();
		keyISOCodeIdx.setRequiredISOCode( existing.getRequiredISOCode() );

		ICFSecISOCtryByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByNameIdxKey();
		keyNameIdx.setRequiredName( existing.getRequiredName() );


					schema.getISOCtryLangTableObj().deepDisposeISOCtryLangByCtryIdx( existing.getRequiredISOCtryId() );
					schema.getISOCtryCcyTableObj().deepDisposeISOCtryCcyByCtryIdx( existing.getRequiredISOCtryId() );

		if( indexByISOCodeIdx != null ) {
			indexByISOCodeIdx.remove( keyISOCodeIdx );
		}

		if( indexByNameIdx != null ) {
			indexByNameIdx.remove( keyNameIdx );
		}


	}
	@Override
	public void deepDisposeISOCtry( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOCtryObj obj = readCachedISOCtry( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecISOCtryObj lockISOCtry( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecISOCtryObj locked = null;
		ICFSecISOCtry lockRec = schema.getCFSecBackingStore().getTableISOCtry().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getISOCtryTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecISOCtryObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockISOCtry", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecISOCtryObj> readAllISOCtry() {
		return( readAllISOCtry( false ) );
	}

	@Override
	public List<ICFSecISOCtryObj> readAllISOCtry( boolean forceRead ) {
		final String S_ProcName = "readAllISOCtry";
		if( ( allISOCtry == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecISOCtryObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecISOCtryObj>();
			allISOCtry = map;
			ICFSecISOCtry[] recList = schema.getCFSecBackingStore().getTableISOCtry().readAllDerived( null );
			ICFSecISOCtry rec;
			ICFSecISOCtryObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecISOCtryObj realised = (ICFSecISOCtryObj)obj.realise();
			}
		}
		int len = allISOCtry.size();
		ICFSecISOCtryObj arr[] = new ICFSecISOCtryObj[len];
		Iterator<ICFSecISOCtryObj> valIter = allISOCtry.values().iterator();
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
		ArrayList<ICFSecISOCtryObj> arrayList = new ArrayList<ICFSecISOCtryObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecISOCtryObj> cmp = new Comparator<ICFSecISOCtryObj>() {
			@Override
			public int compare( ICFSecISOCtryObj lhs, ICFSecISOCtryObj rhs ) {
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
		List<ICFSecISOCtryObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecISOCtryObj> readCachedAllISOCtry() {
		final String S_ProcName = "readCachedAllISOCtry";
		ArrayList<ICFSecISOCtryObj> arrayList = new ArrayList<ICFSecISOCtryObj>();
		if( allISOCtry != null ) {
			int len = allISOCtry.size();
			ICFSecISOCtryObj arr[] = new ICFSecISOCtryObj[len];
			Iterator<ICFSecISOCtryObj> valIter = allISOCtry.values().iterator();
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
		Comparator<ICFSecISOCtryObj> cmp = new Comparator<ICFSecISOCtryObj>() {
			public int compare( ICFSecISOCtryObj lhs, ICFSecISOCtryObj rhs ) {
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
	public ICFSecISOCtryObj readISOCtryByIdIdx( short ISOCtryId )
	{
		return( readISOCtryByIdIdx( ISOCtryId,
			false ) );
	}

	@Override
	public ICFSecISOCtryObj readISOCtryByIdIdx( short ISOCtryId, boolean forceRead )
	{
		ICFSecISOCtryObj obj = readISOCtry( ISOCtryId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecISOCtryObj readISOCtryByISOCodeIdx( String ISOCode )
	{
		return( readISOCtryByISOCodeIdx( ISOCode,
			false ) );
	}

	@Override
	public ICFSecISOCtryObj readISOCtryByISOCodeIdx( String ISOCode, boolean forceRead )
	{
		if( indexByISOCodeIdx == null ) {
			indexByISOCodeIdx = new HashMap< ICFSecISOCtryByISOCodeIdxKey,
				ICFSecISOCtryObj >();
		}
		ICFSecISOCtryByISOCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByISOCodeIdxKey();
		key.setRequiredISOCode( ISOCode );
		ICFSecISOCtryObj obj = null;
		if( ( ! forceRead ) && indexByISOCodeIdx.containsKey( key ) ) {
			obj = indexByISOCodeIdx.get( key );
		}
		else {
			ICFSecISOCtry rec = schema.getCFSecBackingStore().getTableISOCtry().readDerivedByISOCodeIdx( null,
				ISOCode );
			if( rec != null ) {
				obj = schema.getISOCtryTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecISOCtryObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecISOCtryObj readISOCtryByNameIdx( String Name )
	{
		return( readISOCtryByNameIdx( Name,
			false ) );
	}

	@Override
	public ICFSecISOCtryObj readISOCtryByNameIdx( String Name, boolean forceRead )
	{
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecISOCtryByNameIdxKey,
				ICFSecISOCtryObj >();
		}
		ICFSecISOCtryByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByNameIdxKey();
		key.setRequiredName( Name );
		ICFSecISOCtryObj obj = null;
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			obj = indexByNameIdx.get( key );
		}
		else {
			ICFSecISOCtry rec = schema.getCFSecBackingStore().getTableISOCtry().readDerivedByNameIdx( null,
				Name );
			if( rec != null ) {
				obj = schema.getISOCtryTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecISOCtryObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecISOCtryObj readCachedISOCtryByIdIdx( short ISOCtryId )
	{
		ICFSecISOCtryObj obj = null;
		obj = readCachedISOCtry( ISOCtryId );
		return( obj );
	}

	@Override
	public ICFSecISOCtryObj readCachedISOCtryByISOCodeIdx( String ISOCode )
	{
		ICFSecISOCtryObj obj = null;
		ICFSecISOCtryByISOCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByISOCodeIdxKey();
		key.setRequiredISOCode( ISOCode );
		if( indexByISOCodeIdx != null ) {
			if( indexByISOCodeIdx.containsKey( key ) ) {
				obj = indexByISOCodeIdx.get( key );
			}
			else {
				Iterator<ICFSecISOCtryObj> valIter = members.values().iterator();
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
			Iterator<ICFSecISOCtryObj> valIter = members.values().iterator();
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
	public ICFSecISOCtryObj readCachedISOCtryByNameIdx( String Name )
	{
		ICFSecISOCtryObj obj = null;
		ICFSecISOCtryByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByNameIdxKey();
		key.setRequiredName( Name );
		if( indexByNameIdx != null ) {
			if( indexByNameIdx.containsKey( key ) ) {
				obj = indexByNameIdx.get( key );
			}
			else {
				Iterator<ICFSecISOCtryObj> valIter = members.values().iterator();
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
			Iterator<ICFSecISOCtryObj> valIter = members.values().iterator();
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
	public void deepDisposeISOCtryByIdIdx( short ISOCtryId )
	{
		ICFSecISOCtryObj obj = readCachedISOCtryByIdIdx( ISOCtryId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeISOCtryByISOCodeIdx( String ISOCode )
	{
		ICFSecISOCtryObj obj = readCachedISOCtryByISOCodeIdx( ISOCode );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeISOCtryByNameIdx( String Name )
	{
		ICFSecISOCtryObj obj = readCachedISOCtryByNameIdx( Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecISOCtryObj updateISOCtry( ICFSecISOCtryObj Obj ) {
		ICFSecISOCtryObj obj = Obj;
		schema.getCFSecBackingStore().getTableISOCtry().updateISOCtry( null,
			Obj.getISOCtryRec() );
		obj = (ICFSecISOCtryObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteISOCtry( ICFSecISOCtryObj Obj ) {
		ICFSecISOCtryObj obj = Obj;
		schema.getCFSecBackingStore().getTableISOCtry().deleteISOCtry( null,
			obj.getISOCtryRec() );
		Obj.forget();
	}

	@Override
	public void deleteISOCtryByIdIdx( short ISOCtryId )
	{
		ICFSecISOCtryObj obj = readISOCtry(ISOCtryId);
		if( obj != null ) {
			ICFSecISOCtryEditObj editObj = (ICFSecISOCtryEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecISOCtryEditObj)obj.beginEdit();
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
		deepDisposeISOCtryByIdIdx( ISOCtryId );
	}

	@Override
	public void deleteISOCtryByISOCodeIdx( String ISOCode )
	{
		if( indexByISOCodeIdx == null ) {
			indexByISOCodeIdx = new HashMap< ICFSecISOCtryByISOCodeIdxKey,
				ICFSecISOCtryObj >();
		}
		ICFSecISOCtryByISOCodeIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByISOCodeIdxKey();
		key.setRequiredISOCode( ISOCode );
		ICFSecISOCtryObj obj = null;
		if( indexByISOCodeIdx.containsKey( key ) ) {
			obj = indexByISOCodeIdx.get( key );
			schema.getCFSecBackingStore().getTableISOCtry().deleteISOCtryByISOCodeIdx( null,
				ISOCode );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableISOCtry().deleteISOCtryByISOCodeIdx( null,
				ISOCode );
		}
		deepDisposeISOCtryByISOCodeIdx( ISOCode );
	}

	@Override
	public void deleteISOCtryByNameIdx( String Name )
	{
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecISOCtryByNameIdxKey,
				ICFSecISOCtryObj >();
		}
		ICFSecISOCtryByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryISOCtry().newByNameIdxKey();
		key.setRequiredName( Name );
		ICFSecISOCtryObj obj = null;
		if( indexByNameIdx.containsKey( key ) ) {
			obj = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableISOCtry().deleteISOCtryByNameIdx( null,
				Name );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableISOCtry().deleteISOCtryByNameIdx( null,
				Name );
		}
		deepDisposeISOCtryByNameIdx( Name );
	}
}