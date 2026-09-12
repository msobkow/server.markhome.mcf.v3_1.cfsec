// Description: Java 25 Table Object implementation for SecTentRoleMemb.

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

public class CFSecSecTentRoleMembTableObj
	implements ICFSecSecTentRoleMembTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecTentRoleMemb.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecTentRoleMemb.CLASS_CODE;
	private Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> members;
	private Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> allSecTentRoleMemb;
	private Map< ICFSecSecTentRoleMembByTentRoleIdxKey,
		Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > > indexByTentRoleIdx;
	private Map< ICFSecSecTentRoleMembByUserIdxKey,
		Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > > indexByUserIdx;
	public static String TABLE_NAME = "SecTentRoleMemb";
	public static String TABLE_DBNAME = "sectentrolememb";

	public CFSecSecTentRoleMembTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj>();
		allSecTentRoleMemb = null;
		indexByTentRoleIdx = null;
		indexByUserIdx = null;
	}

	public CFSecSecTentRoleMembTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj>();
		allSecTentRoleMemb = null;
		indexByTentRoleIdx = null;
		indexByUserIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecTentRoleMembTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecTentRoleMembTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecTentRoleMemb = null;
		indexByTentRoleIdx = null;
		indexByUserIdx = null;
		List<ICFSecSecTentRoleMembObj> toForget = new LinkedList<ICFSecSecTentRoleMembObj>();
		ICFSecSecTentRoleMembObj cur = null;
		Iterator<ICFSecSecTentRoleMembObj> iter = members.values().iterator();
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
	 *	CFSecSecTentRoleMembObj.
	 */
	@Override
	public ICFSecSecTentRoleMembObj newInstance() {
		ICFSecSecTentRoleMembObj inst = new CFSecSecTentRoleMembObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecTentRoleMembObj.
	 */
	@Override
	public ICFSecSecTentRoleMembEditObj newEditInstance( ICFSecSecTentRoleMembObj orig ) {
		ICFSecSecTentRoleMembEditObj edit = new CFSecSecTentRoleMembEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecTentRoleMembObj realiseSecTentRoleMemb( ICFSecSecTentRoleMembObj Obj ) {
		ICFSecSecTentRoleMembObj obj = Obj;
		ICFSecSecTentRoleMembPKey pkey = obj.getPKey();
		ICFSecSecTentRoleMembObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecTentRoleMembObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTentRoleIdx != null ) {
				ICFSecSecTentRoleMembByTentRoleIdxKey keyTentRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByTentRoleIdxKey();
				keyTentRoleIdx.setRequiredSecTentRoleId( keepObj.getRequiredSecTentRoleId() );
				Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > mapTentRoleIdx = indexByTentRoleIdx.get( keyTentRoleIdx );
				if( mapTentRoleIdx != null ) {
					mapTentRoleIdx.remove( keepObj.getPKey() );
					if( mapTentRoleIdx.size() <= 0 ) {
						indexByTentRoleIdx.remove( keyTentRoleIdx );
					}
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecTentRoleMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByUserIdxKey();
				keyUserIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.remove( keepObj.getPKey() );
					if( mapUserIdx.size() <= 0 ) {
						indexByUserIdx.remove( keyUserIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTentRoleIdx != null ) {
				ICFSecSecTentRoleMembByTentRoleIdxKey keyTentRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByTentRoleIdxKey();
				keyTentRoleIdx.setRequiredSecTentRoleId( keepObj.getRequiredSecTentRoleId() );
				Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > mapTentRoleIdx = indexByTentRoleIdx.get( keyTentRoleIdx );
				if( mapTentRoleIdx != null ) {
					mapTentRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecTentRoleMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByUserIdxKey();
				keyUserIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecTentRoleMemb != null ) {
				allSecTentRoleMemb.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecTentRoleMemb != null ) {
				allSecTentRoleMemb.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTentRoleIdx != null ) {
				ICFSecSecTentRoleMembByTentRoleIdxKey keyTentRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByTentRoleIdxKey();
				keyTentRoleIdx.setRequiredSecTentRoleId( keepObj.getRequiredSecTentRoleId() );
				Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > mapTentRoleIdx = indexByTentRoleIdx.get( keyTentRoleIdx );
				if( mapTentRoleIdx != null ) {
					mapTentRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecTentRoleMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByUserIdxKey();
				keyUserIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecTentRoleMembObj createSecTentRoleMemb( ICFSecSecTentRoleMembObj Obj ) {
		ICFSecSecTentRoleMembObj obj = Obj;
		ICFSecSecTentRoleMemb rec = obj.getSecTentRoleMembRec();
		schema.getCFSecBackingStore().getTableSecTentRoleMemb().createSecTentRoleMemb(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecTentRoleMembObj readSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey ) {
		return( readSecTentRoleMemb( pkey, false ) );
	}

	@Override
	public ICFSecSecTentRoleMembObj readSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey, boolean forceRead ) {
		ICFSecSecTentRoleMembObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecTentRoleMemb readRec = schema.getCFSecBackingStore().getTableSecTentRoleMemb().readDerivedByIdIdx( null,
						pkey.getRequiredSecTentRoleId(),
						pkey.getRequiredLoginId() );
			if( readRec != null ) {
				obj = schema.getSecTentRoleMembTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecTentRoleMembObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentRoleMembObj readSecTentRoleMemb( ICFLibKeyHash256 SecTentRoleId,
		String LoginId ) {
		return( readSecTentRoleMemb( SecTentRoleId,
			LoginId, false ) );
	}

	@Override
	public ICFSecSecTentRoleMembObj readSecTentRoleMemb( ICFLibKeyHash256 SecTentRoleId,
		String LoginId, boolean forceRead ) {
		ICFSecSecTentRoleMembObj obj = null;
		ICFSecSecTentRoleMemb readRec = schema.getCFSecBackingStore().getTableSecTentRoleMemb().readDerivedByIdIdx( null,
			SecTentRoleId,
			LoginId );
		if( readRec != null ) {
				obj = schema.getSecTentRoleMembTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecTentRoleMembObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentRoleMembObj readCachedSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey ) {
		ICFSecSecTentRoleMembObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecTentRoleMemb( ICFSecSecTentRoleMembObj obj )
	{
		final String S_ProcName = "CFSecSecTentRoleMembTableObj.reallyDeepDisposeSecTentRoleMemb() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecTentRoleMembPKey pkey = obj.getPKey();
		ICFSecSecTentRoleMembObj existing = readCachedSecTentRoleMemb( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecTentRoleMembByTentRoleIdxKey keyTentRoleIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByTentRoleIdxKey();
		keyTentRoleIdx.setRequiredSecTentRoleId( existing.getRequiredSecTentRoleId() );

		ICFSecSecTentRoleMembByUserIdxKey keyUserIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByUserIdxKey();
		keyUserIdx.setRequiredLoginId( existing.getRequiredLoginId() );



		if( indexByTentRoleIdx != null ) {
			if( indexByTentRoleIdx.containsKey( keyTentRoleIdx ) ) {
				indexByTentRoleIdx.get( keyTentRoleIdx ).remove( pkey );
				if( indexByTentRoleIdx.get( keyTentRoleIdx ).size() <= 0 ) {
					indexByTentRoleIdx.remove( keyTentRoleIdx );
				}
			}
		}

		if( indexByUserIdx != null ) {
			if( indexByUserIdx.containsKey( keyUserIdx ) ) {
				indexByUserIdx.get( keyUserIdx ).remove( pkey );
				if( indexByUserIdx.get( keyUserIdx ).size() <= 0 ) {
					indexByUserIdx.remove( keyUserIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey ) {
		ICFSecSecTentRoleMembObj obj = readCachedSecTentRoleMemb( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecTentRoleMembObj lockSecTentRoleMemb( ICFSecSecTentRoleMembPKey pkey ) {
		ICFSecSecTentRoleMembObj locked = null;
		ICFSecSecTentRoleMemb lockRec = schema.getCFSecBackingStore().getTableSecTentRoleMemb().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecTentRoleMembTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecTentRoleMembObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecTentRoleMemb", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readAllSecTentRoleMemb() {
		return( readAllSecTentRoleMemb( false ) );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readAllSecTentRoleMemb( boolean forceRead ) {
		final String S_ProcName = "readAllSecTentRoleMemb";
		if( ( allSecTentRoleMemb == null ) || forceRead ) {
			Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> map = new HashMap<ICFSecSecTentRoleMembPKey,ICFSecSecTentRoleMembObj>();
			allSecTentRoleMemb = map;
			ICFSecSecTentRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecTentRoleMemb().readAllDerived( null );
			ICFSecSecTentRoleMemb rec;
			ICFSecSecTentRoleMembObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentRoleMembObj realised = (ICFSecSecTentRoleMembObj)obj.realise();
			}
		}
		int len = allSecTentRoleMemb.size();
		ICFSecSecTentRoleMembObj arr[] = new ICFSecSecTentRoleMembObj[len];
		Iterator<ICFSecSecTentRoleMembObj> valIter = allSecTentRoleMemb.values().iterator();
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
		ArrayList<ICFSecSecTentRoleMembObj> arrayList = new ArrayList<ICFSecSecTentRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentRoleMembObj> cmp = new Comparator<ICFSecSecTentRoleMembObj>() {
			@Override
			public int compare( ICFSecSecTentRoleMembObj lhs, ICFSecSecTentRoleMembObj rhs ) {
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
					ICFSecSecTentRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readCachedAllSecTentRoleMemb() {
		final String S_ProcName = "readCachedAllSecTentRoleMemb";
		ArrayList<ICFSecSecTentRoleMembObj> arrayList = new ArrayList<ICFSecSecTentRoleMembObj>();
		if( allSecTentRoleMemb != null ) {
			int len = allSecTentRoleMemb.size();
			ICFSecSecTentRoleMembObj arr[] = new ICFSecSecTentRoleMembObj[len];
			Iterator<ICFSecSecTentRoleMembObj> valIter = allSecTentRoleMemb.values().iterator();
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
		Comparator<ICFSecSecTentRoleMembObj> cmp = new Comparator<ICFSecSecTentRoleMembObj>() {
			public int compare( ICFSecSecTentRoleMembObj lhs, ICFSecSecTentRoleMembObj rhs ) {
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
					ICFSecSecTentRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecTentRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecTentRoleMembObj> pageAllSecTentRoleMemb(ICFLibKeyHash256 priorSecTentRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageAllSecTentRoleMemb";
		Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> map = new HashMap<ICFSecSecTentRoleMembPKey,ICFSecSecTentRoleMembObj>();
		ICFSecSecTentRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecTentRoleMemb().pageAllRec( null,
			priorSecTentRoleId,
			priorLoginId );
		ICFSecSecTentRoleMemb rec;
		ICFSecSecTentRoleMembObj obj;
		ICFSecSecTentRoleMembObj realised;
		ArrayList<ICFSecSecTentRoleMembObj> arrayList = new ArrayList<ICFSecSecTentRoleMembObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecTentRoleMembObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecTentRoleMembObj readSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId )
	{
		return( readSecTentRoleMembByIdIdx( SecTentRoleId,
			LoginId,
			false ) );
	}

	@Override
	public ICFSecSecTentRoleMembObj readSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId, boolean forceRead )
	{
		ICFSecSecTentRoleMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newPKey();
		pkey.setRequiredSecTentRoleId( SecTentRoleId );
		pkey.setRequiredLoginId( LoginId );
		ICFSecSecTentRoleMembObj obj = readSecTentRoleMemb( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId )
	{
		return( readSecTentRoleMembByTentRoleIdx( SecTentRoleId,
			false ) );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentRoleMembByTentRoleIdx";
		ICFSecSecTentRoleMembByTentRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByTentRoleIdxKey();
		key.setRequiredSecTentRoleId( SecTentRoleId );
		Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> dict;
		if( indexByTentRoleIdx == null ) {
			indexByTentRoleIdx = new HashMap< ICFSecSecTentRoleMembByTentRoleIdxKey,
				Map< ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > >();
		}
		if( ( ! forceRead ) && indexByTentRoleIdx.containsKey( key ) ) {
			dict = indexByTentRoleIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj>();
			ICFSecSecTentRoleMembObj obj;
			ICFSecSecTentRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecTentRoleMemb().readDerivedByTentRoleIdx( null,
				SecTentRoleId );
			ICFSecSecTentRoleMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentRoleMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentRoleMembObj realised = (ICFSecSecTentRoleMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTentRoleIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentRoleMembObj arr[] = new ICFSecSecTentRoleMembObj[len];
		Iterator<ICFSecSecTentRoleMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecTentRoleMembObj> arrayList = new ArrayList<ICFSecSecTentRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentRoleMembObj> cmp = new Comparator<ICFSecSecTentRoleMembObj>() {
			@Override
			public int compare( ICFSecSecTentRoleMembObj lhs, ICFSecSecTentRoleMembObj rhs ) {
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
					ICFSecSecTentRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readSecTentRoleMembByUserIdx( String LoginId )
	{
		return( readSecTentRoleMembByUserIdx( LoginId,
			false ) );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readSecTentRoleMembByUserIdx( String LoginId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentRoleMembByUserIdx";
		ICFSecSecTentRoleMembByUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByUserIdxKey();
		key.setRequiredLoginId( LoginId );
		Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> dict;
		if( indexByUserIdx == null ) {
			indexByUserIdx = new HashMap< ICFSecSecTentRoleMembByUserIdxKey,
				Map< ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > >();
		}
		if( ( ! forceRead ) && indexByUserIdx.containsKey( key ) ) {
			dict = indexByUserIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj>();
			ICFSecSecTentRoleMembObj obj;
			ICFSecSecTentRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecTentRoleMemb().readDerivedByUserIdx( null,
				LoginId );
			ICFSecSecTentRoleMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentRoleMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentRoleMembObj realised = (ICFSecSecTentRoleMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByUserIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentRoleMembObj arr[] = new ICFSecSecTentRoleMembObj[len];
		Iterator<ICFSecSecTentRoleMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecTentRoleMembObj> arrayList = new ArrayList<ICFSecSecTentRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentRoleMembObj> cmp = new Comparator<ICFSecSecTentRoleMembObj>() {
			@Override
			public int compare( ICFSecSecTentRoleMembObj lhs, ICFSecSecTentRoleMembObj rhs ) {
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
					ICFSecSecTentRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecTentRoleMembObj readCachedSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId )
	{
		ICFSecSecTentRoleMembObj obj = null;
		ICFSecSecTentRoleMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newPKey();
		pkey.setRequiredSecTentRoleId( SecTentRoleId );
		pkey.setRequiredLoginId( LoginId );
		pkey.setRequiredSecTentRoleId( SecTentRoleId );
		pkey.setRequiredLoginId( LoginId );
		obj = readCachedSecTentRoleMemb( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readCachedSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId )
	{
		final String S_ProcName = "readCachedSecTentRoleMembByTentRoleIdx";
		ICFSecSecTentRoleMembByTentRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByTentRoleIdxKey();
		key.setRequiredSecTentRoleId( SecTentRoleId );
		ArrayList<ICFSecSecTentRoleMembObj> arrayList = new ArrayList<ICFSecSecTentRoleMembObj>();
		if( indexByTentRoleIdx != null ) {
			Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> dict;
			if( indexByTentRoleIdx.containsKey( key ) ) {
				dict = indexByTentRoleIdx.get( key );
				int len = dict.size();
				ICFSecSecTentRoleMembObj arr[] = new ICFSecSecTentRoleMembObj[len];
				Iterator<ICFSecSecTentRoleMembObj> valIter = dict.values().iterator();
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
			ICFSecSecTentRoleMembObj obj;
			Iterator<ICFSecSecTentRoleMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentRoleMembObj> cmp = new Comparator<ICFSecSecTentRoleMembObj>() {
			@Override
			public int compare( ICFSecSecTentRoleMembObj lhs, ICFSecSecTentRoleMembObj rhs ) {
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
					ICFSecSecTentRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecTentRoleMembObj> readCachedSecTentRoleMembByUserIdx( String LoginId )
	{
		final String S_ProcName = "readCachedSecTentRoleMembByUserIdx";
		ICFSecSecTentRoleMembByUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByUserIdxKey();
		key.setRequiredLoginId( LoginId );
		ArrayList<ICFSecSecTentRoleMembObj> arrayList = new ArrayList<ICFSecSecTentRoleMembObj>();
		if( indexByUserIdx != null ) {
			Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> dict;
			if( indexByUserIdx.containsKey( key ) ) {
				dict = indexByUserIdx.get( key );
				int len = dict.size();
				ICFSecSecTentRoleMembObj arr[] = new ICFSecSecTentRoleMembObj[len];
				Iterator<ICFSecSecTentRoleMembObj> valIter = dict.values().iterator();
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
			ICFSecSecTentRoleMembObj obj;
			Iterator<ICFSecSecTentRoleMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentRoleMembObj> cmp = new Comparator<ICFSecSecTentRoleMembObj>() {
			@Override
			public int compare( ICFSecSecTentRoleMembObj lhs, ICFSecSecTentRoleMembObj rhs ) {
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
					ICFSecSecTentRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId )
	{
		ICFSecSecTentRoleMembObj obj = readCachedSecTentRoleMembByIdIdx( SecTentRoleId,
				LoginId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId )
	{
		final String S_ProcName = "deepDisposeSecTentRoleMembByTentRoleIdx";
		ICFSecSecTentRoleMembObj obj;
		List<ICFSecSecTentRoleMembObj> arrayList = readCachedSecTentRoleMembByTentRoleIdx( SecTentRoleId );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentRoleMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecTentRoleMembByUserIdx( String LoginId )
	{
		final String S_ProcName = "deepDisposeSecTentRoleMembByUserIdx";
		ICFSecSecTentRoleMembObj obj;
		List<ICFSecSecTentRoleMembObj> arrayList = readCachedSecTentRoleMembByUserIdx( LoginId );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentRoleMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecTentRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate TentRoleIdx key attributes.
	 *
	 *	@param	SecTentRoleId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecTentRoleMembObj> pageSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId,
		ICFLibKeyHash256 priorSecTentRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecTentRoleMembByTentRoleIdx";
		ICFSecSecTentRoleMembByTentRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByTentRoleIdxKey();
		key.setRequiredSecTentRoleId( SecTentRoleId );
		List<ICFSecSecTentRoleMembObj> retList = new LinkedList<ICFSecSecTentRoleMembObj>();
		ICFSecSecTentRoleMembObj obj;
		ICFSecSecTentRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecTentRoleMemb().pageRecByTentRoleIdx( null,
				SecTentRoleId,
			priorSecTentRoleId,
			priorLoginId );
		ICFSecSecTentRoleMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecTentRoleMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecTentRoleMembObj realised = (ICFSecSecTentRoleMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecTentRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate UserIdx key attributes.
	 *
	 *	@param	LoginId	The SecTentRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecTentRoleMembObj> pageSecTentRoleMembByUserIdx( String LoginId,
		ICFLibKeyHash256 priorSecTentRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecTentRoleMembByUserIdx";
		ICFSecSecTentRoleMembByUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByUserIdxKey();
		key.setRequiredLoginId( LoginId );
		List<ICFSecSecTentRoleMembObj> retList = new LinkedList<ICFSecSecTentRoleMembObj>();
		ICFSecSecTentRoleMembObj obj;
		ICFSecSecTentRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecTentRoleMemb().pageRecByUserIdx( null,
				LoginId,
			priorSecTentRoleId,
			priorLoginId );
		ICFSecSecTentRoleMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecTentRoleMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecTentRoleMembObj realised = (ICFSecSecTentRoleMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecTentRoleMembObj updateSecTentRoleMemb( ICFSecSecTentRoleMembObj Obj ) {
		ICFSecSecTentRoleMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentRoleMemb().updateSecTentRoleMemb( null,
			Obj.getSecTentRoleMembRec() );
		obj = (ICFSecSecTentRoleMembObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecTentRoleMemb( ICFSecSecTentRoleMembObj Obj ) {
		ICFSecSecTentRoleMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentRoleMemb().deleteSecTentRoleMemb( null,
			obj.getSecTentRoleMembRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecTentRoleMembByIdIdx( ICFLibKeyHash256 SecTentRoleId,
		String LoginId )
	{
		ICFSecSecTentRoleMembObj obj = readSecTentRoleMemb(SecTentRoleId,
				LoginId);
		if( obj != null ) {
			ICFSecSecTentRoleMembEditObj editObj = (ICFSecSecTentRoleMembEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecTentRoleMembEditObj)obj.beginEdit();
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
		deepDisposeSecTentRoleMembByIdIdx( SecTentRoleId,
				LoginId );
	}

	@Override
	public void deleteSecTentRoleMembByTentRoleIdx( ICFLibKeyHash256 SecTentRoleId )
	{
		ICFSecSecTentRoleMembByTentRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByTentRoleIdxKey();
		key.setRequiredSecTentRoleId( SecTentRoleId );
		if( indexByTentRoleIdx == null ) {
			indexByTentRoleIdx = new HashMap< ICFSecSecTentRoleMembByTentRoleIdxKey,
				Map< ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > >();
		}
		if( indexByTentRoleIdx.containsKey( key ) ) {
			Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> dict = indexByTentRoleIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentRoleMemb().deleteSecTentRoleMembByTentRoleIdx( null,
				SecTentRoleId );
			Iterator<ICFSecSecTentRoleMembObj> iter = dict.values().iterator();
			ICFSecSecTentRoleMembObj obj;
			List<ICFSecSecTentRoleMembObj> toForget = new LinkedList<ICFSecSecTentRoleMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTentRoleIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentRoleMemb().deleteSecTentRoleMembByTentRoleIdx( null,
				SecTentRoleId );
		}
		deepDisposeSecTentRoleMembByTentRoleIdx( SecTentRoleId );
	}

	@Override
	public void deleteSecTentRoleMembByUserIdx( String LoginId )
	{
		ICFSecSecTentRoleMembByUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRoleMemb().newByUserIdxKey();
		key.setRequiredLoginId( LoginId );
		if( indexByUserIdx == null ) {
			indexByUserIdx = new HashMap< ICFSecSecTentRoleMembByUserIdxKey,
				Map< ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj > >();
		}
		if( indexByUserIdx.containsKey( key ) ) {
			Map<ICFSecSecTentRoleMembPKey, ICFSecSecTentRoleMembObj> dict = indexByUserIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentRoleMemb().deleteSecTentRoleMembByUserIdx( null,
				LoginId );
			Iterator<ICFSecSecTentRoleMembObj> iter = dict.values().iterator();
			ICFSecSecTentRoleMembObj obj;
			List<ICFSecSecTentRoleMembObj> toForget = new LinkedList<ICFSecSecTentRoleMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByUserIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentRoleMemb().deleteSecTentRoleMembByUserIdx( null,
				LoginId );
		}
		deepDisposeSecTentRoleMembByUserIdx( LoginId );
	}
}