// Description: Java 25 Table Object implementation for SecUserPassword.

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

public class CFSecSecUserPasswordTableObj
	implements ICFSecSecUserPasswordTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecUserPassword.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecUserPassword.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj> allSecUserPassword;
	private Map< ICFSecSecUserPasswordBySetStampIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj > > indexBySetStampIdx;
	public static String TABLE_NAME = "SecUserPassword";
	public static String TABLE_DBNAME = "secuserpw";

	public CFSecSecUserPasswordTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj>();
		allSecUserPassword = null;
		indexBySetStampIdx = null;
	}

	public CFSecSecUserPasswordTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj>();
		allSecUserPassword = null;
		indexBySetStampIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecUserPasswordTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecUserPasswordTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecUserPassword = null;
		indexBySetStampIdx = null;
		List<ICFSecSecUserPasswordObj> toForget = new LinkedList<ICFSecSecUserPasswordObj>();
		ICFSecSecUserPasswordObj cur = null;
		Iterator<ICFSecSecUserPasswordObj> iter = members.values().iterator();
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
	 *	CFSecSecUserPasswordObj.
	 */
	@Override
	public ICFSecSecUserPasswordObj newInstance() {
		ICFSecSecUserPasswordObj inst = new CFSecSecUserPasswordObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecUserPasswordObj.
	 */
	@Override
	public ICFSecSecUserPasswordEditObj newEditInstance( ICFSecSecUserPasswordObj orig ) {
		ICFSecSecUserPasswordEditObj edit = new CFSecSecUserPasswordEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecUserPasswordObj realiseSecUserPassword( ICFSecSecUserPasswordObj Obj ) {
		ICFSecSecUserPasswordObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecUserPasswordObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecUserPasswordObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPasswordBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPassword().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj > mapSetStampIdx = indexBySetStampIdx.get( keySetStampIdx );
				if( mapSetStampIdx != null ) {
					mapSetStampIdx.remove( keepObj.getPKey() );
					if( mapSetStampIdx.size() <= 0 ) {
						indexBySetStampIdx.remove( keySetStampIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPasswordBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPassword().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj > mapSetStampIdx = indexBySetStampIdx.get( keySetStampIdx );
				if( mapSetStampIdx != null ) {
					mapSetStampIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecUserPassword != null ) {
				allSecUserPassword.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecUserPassword != null ) {
				allSecUserPassword.put( keepObj.getPKey(), keepObj );
			}

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPasswordBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPassword().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj > mapSetStampIdx = indexBySetStampIdx.get( keySetStampIdx );
				if( mapSetStampIdx != null ) {
					mapSetStampIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecUserPasswordObj createSecUserPassword( ICFSecSecUserPasswordObj Obj ) {
		ICFSecSecUserPasswordObj obj = Obj;
		ICFSecSecUserPassword rec = obj.getSecUserPasswordRec();
		schema.getCFSecBackingStore().getTableSecUserPassword().createSecUserPassword(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecUserPasswordObj readSecUserPassword( $implCommaIJavaOptAtomType$ pkey ) {
		return( readSecUserPassword( pkey, false ) );
	}

	@Override
	public ICFSecSecUserPasswordObj readSecUserPassword( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecSecUserPasswordObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecUserPassword readRec = schema.getCFSecBackingStore().getTableSecUserPassword().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecUserPasswordTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecUserPasswordObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserPasswordObj readCachedSecUserPassword( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserPasswordObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecUserPassword( ICFSecSecUserPasswordObj obj )
	{
		final String S_ProcName = "CFSecSecUserPasswordTableObj.reallyDeepDisposeSecUserPassword() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecUserPasswordObj existing = readCachedSecUserPassword( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecUserPasswordBySetStampIdxKey keySetStampIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPassword().newBySetStampIdxKey();
		keySetStampIdx.setRequiredPWSetStamp( existing.getRequiredPWSetStamp() );



		if( indexBySetStampIdx != null ) {
			if( indexBySetStampIdx.containsKey( keySetStampIdx ) ) {
				indexBySetStampIdx.get( keySetStampIdx ).remove( pkey );
				if( indexBySetStampIdx.get( keySetStampIdx ).size() <= 0 ) {
					indexBySetStampIdx.remove( keySetStampIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecUserPassword( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserPasswordObj obj = readCachedSecUserPassword( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecUserPasswordObj lockSecUserPassword( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserPasswordObj locked = null;
		ICFSecSecUserPassword lockRec = schema.getCFSecBackingStore().getTableSecUserPassword().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecUserPasswordTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecUserPasswordObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecUserPassword", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readAllSecUserPassword() {
		return( readAllSecUserPassword( false ) );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readAllSecUserPassword( boolean forceRead ) {
		final String S_ProcName = "readAllSecUserPassword";
		if( ( allSecUserPassword == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecUserPasswordObj>();
			allSecUserPassword = map;
			ICFSecSecUserPassword[] recList = schema.getCFSecBackingStore().getTableSecUserPassword().readAllDerived( null );
			ICFSecSecUserPassword rec;
			ICFSecSecUserPasswordObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserPasswordObj realised = (ICFSecSecUserPasswordObj)obj.realise();
			}
		}
		int len = allSecUserPassword.size();
		ICFSecSecUserPasswordObj arr[] = new ICFSecSecUserPasswordObj[len];
		Iterator<ICFSecSecUserPasswordObj> valIter = allSecUserPassword.values().iterator();
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
		ArrayList<ICFSecSecUserPasswordObj> arrayList = new ArrayList<ICFSecSecUserPasswordObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserPasswordObj> cmp = new Comparator<ICFSecSecUserPasswordObj>() {
			@Override
			public int compare( ICFSecSecUserPasswordObj lhs, ICFSecSecUserPasswordObj rhs ) {
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
		List<ICFSecSecUserPasswordObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readCachedAllSecUserPassword() {
		final String S_ProcName = "readCachedAllSecUserPassword";
		ArrayList<ICFSecSecUserPasswordObj> arrayList = new ArrayList<ICFSecSecUserPasswordObj>();
		if( allSecUserPassword != null ) {
			int len = allSecUserPassword.size();
			ICFSecSecUserPasswordObj arr[] = new ICFSecSecUserPasswordObj[len];
			Iterator<ICFSecSecUserPasswordObj> valIter = allSecUserPassword.values().iterator();
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
		Comparator<ICFSecSecUserPasswordObj> cmp = new Comparator<ICFSecSecUserPasswordObj>() {
			public int compare( ICFSecSecUserPasswordObj lhs, ICFSecSecUserPasswordObj rhs ) {
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
	public ICFSecSecUserPasswordObj readSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		return( readSecUserPasswordByIdIdx( SecUserId,
			false ) );
	}

	@Override
	public ICFSecSecUserPasswordObj readSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId, boolean forceRead )
	{
		ICFSecSecUserPasswordObj obj = readSecUserPassword( SecUserId, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp )
	{
		return( readSecUserPasswordBySetStampIdx( PWSetStamp,
			false ) );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp,
		boolean forceRead )
	{
		final String S_ProcName = "readSecUserPasswordBySetStampIdx";
		ICFSecSecUserPasswordBySetStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPassword().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj> dict;
		if( indexBySetStampIdx == null ) {
			indexBySetStampIdx = new HashMap< ICFSecSecUserPasswordBySetStampIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj > >();
		}
		if( ( ! forceRead ) && indexBySetStampIdx.containsKey( key ) ) {
			dict = indexBySetStampIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj>();
			ICFSecSecUserPasswordObj obj;
			ICFSecSecUserPassword[] recList = schema.getCFSecBackingStore().getTableSecUserPassword().readDerivedBySetStampIdx( null,
				PWSetStamp );
			ICFSecSecUserPassword rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecUserPasswordTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserPasswordObj realised = (ICFSecSecUserPasswordObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySetStampIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecUserPasswordObj arr[] = new ICFSecSecUserPasswordObj[len];
		Iterator<ICFSecSecUserPasswordObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecUserPasswordObj> arrayList = new ArrayList<ICFSecSecUserPasswordObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserPasswordObj> cmp = new Comparator<ICFSecSecUserPasswordObj>() {
			@Override
			public int compare( ICFSecSecUserPasswordObj lhs, ICFSecSecUserPasswordObj rhs ) {
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
		List<ICFSecSecUserPasswordObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecUserPasswordObj readCachedSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserPasswordObj obj = null;
		obj = readCachedSecUserPassword( SecUserId );
		return( obj );
	}

	@Override
	public List<ICFSecSecUserPasswordObj> readCachedSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp )
	{
		final String S_ProcName = "readCachedSecUserPasswordBySetStampIdx";
		ICFSecSecUserPasswordBySetStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPassword().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		ArrayList<ICFSecSecUserPasswordObj> arrayList = new ArrayList<ICFSecSecUserPasswordObj>();
		if( indexBySetStampIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj> dict;
			if( indexBySetStampIdx.containsKey( key ) ) {
				dict = indexBySetStampIdx.get( key );
				int len = dict.size();
				ICFSecSecUserPasswordObj arr[] = new ICFSecSecUserPasswordObj[len];
				Iterator<ICFSecSecUserPasswordObj> valIter = dict.values().iterator();
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
			ICFSecSecUserPasswordObj obj;
			Iterator<ICFSecSecUserPasswordObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecUserPasswordObj> cmp = new Comparator<ICFSecSecUserPasswordObj>() {
			@Override
			public int compare( ICFSecSecUserPasswordObj lhs, ICFSecSecUserPasswordObj rhs ) {
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
	public void deepDisposeSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserPasswordObj obj = readCachedSecUserPasswordByIdIdx( SecUserId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp )
	{
		final String S_ProcName = "deepDisposeSecUserPasswordBySetStampIdx";
		ICFSecSecUserPasswordObj obj;
		List<ICFSecSecUserPasswordObj> arrayList = readCachedSecUserPasswordBySetStampIdx( PWSetStamp );
		if( arrayList != null )  {
			Iterator<ICFSecSecUserPasswordObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFSecSecUserPasswordObj updateSecUserPassword( ICFSecSecUserPasswordObj Obj ) {
		ICFSecSecUserPasswordObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserPassword().updateSecUserPassword( null,
			Obj.getSecUserPasswordRec() );
		obj = (ICFSecSecUserPasswordObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecUserPassword( ICFSecSecUserPasswordObj Obj ) {
		ICFSecSecUserPasswordObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserPassword().deleteSecUserPassword( null,
			obj.getSecUserPasswordRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecUserPasswordByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserPasswordObj obj = readSecUserPassword(SecUserId);
		if( obj != null ) {
			ICFSecSecUserPasswordEditObj editObj = (ICFSecSecUserPasswordEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecUserPasswordEditObj)obj.beginEdit();
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
		deepDisposeSecUserPasswordByIdIdx( SecUserId );
	}

	@Override
	public void deleteSecUserPasswordBySetStampIdx( LocalDateTime PWSetStamp )
	{
		ICFSecSecUserPasswordBySetStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPassword().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		if( indexBySetStampIdx == null ) {
			indexBySetStampIdx = new HashMap< ICFSecSecUserPasswordBySetStampIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj > >();
		}
		if( indexBySetStampIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPasswordObj> dict = indexBySetStampIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserPassword().deleteSecUserPasswordBySetStampIdx( null,
				PWSetStamp );
			Iterator<ICFSecSecUserPasswordObj> iter = dict.values().iterator();
			ICFSecSecUserPasswordObj obj;
			List<ICFSecSecUserPasswordObj> toForget = new LinkedList<ICFSecSecUserPasswordObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySetStampIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserPassword().deleteSecUserPasswordBySetStampIdx( null,
				PWSetStamp );
		}
		deepDisposeSecUserPasswordBySetStampIdx( PWSetStamp );
	}
}