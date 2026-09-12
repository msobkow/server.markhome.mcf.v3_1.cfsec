// Description: Java 25 Table Object implementation for SecSysRoleMemb.

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

public class CFSecSecSysRoleMembTableObj
	implements ICFSecSecSysRoleMembTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecSysRoleMemb.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecSysRoleMemb.CLASS_CODE;
	private Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> members;
	private Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> allSecSysRoleMemb;
	private Map< ICFSecSecSysRoleMembBySysRoleIdxKey,
		Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > > indexBySysRoleIdx;
	private Map< ICFSecSecSysRoleMembByLoginIdxKey,
		Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > > indexByLoginIdx;
	public static String TABLE_NAME = "SecSysRoleMemb";
	public static String TABLE_DBNAME = "secsysrolememb";

	public CFSecSecSysRoleMembTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj>();
		allSecSysRoleMemb = null;
		indexBySysRoleIdx = null;
		indexByLoginIdx = null;
	}

	public CFSecSecSysRoleMembTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj>();
		allSecSysRoleMemb = null;
		indexBySysRoleIdx = null;
		indexByLoginIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecSysRoleMembTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecSysRoleMembTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecSysRoleMemb = null;
		indexBySysRoleIdx = null;
		indexByLoginIdx = null;
		List<ICFSecSecSysRoleMembObj> toForget = new LinkedList<ICFSecSecSysRoleMembObj>();
		ICFSecSecSysRoleMembObj cur = null;
		Iterator<ICFSecSecSysRoleMembObj> iter = members.values().iterator();
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
	 *	CFSecSecSysRoleMembObj.
	 */
	@Override
	public ICFSecSecSysRoleMembObj newInstance() {
		ICFSecSecSysRoleMembObj inst = new CFSecSecSysRoleMembObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecSysRoleMembObj.
	 */
	@Override
	public ICFSecSecSysRoleMembEditObj newEditInstance( ICFSecSecSysRoleMembObj orig ) {
		ICFSecSecSysRoleMembEditObj edit = new CFSecSecSysRoleMembEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecSysRoleMembObj realiseSecSysRoleMemb( ICFSecSecSysRoleMembObj Obj ) {
		ICFSecSecSysRoleMembObj obj = Obj;
		ICFSecSecSysRoleMembPKey pkey = obj.getPKey();
		ICFSecSecSysRoleMembObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecSysRoleMembObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexBySysRoleIdx != null ) {
				ICFSecSecSysRoleMembBySysRoleIdxKey keySysRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newBySysRoleIdxKey();
				keySysRoleIdx.setRequiredSecSysRoleId( keepObj.getRequiredSecSysRoleId() );
				Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > mapSysRoleIdx = indexBySysRoleIdx.get( keySysRoleIdx );
				if( mapSysRoleIdx != null ) {
					mapSysRoleIdx.remove( keepObj.getPKey() );
					if( mapSysRoleIdx.size() <= 0 ) {
						indexBySysRoleIdx.remove( keySysRoleIdx );
					}
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecSysRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.remove( keepObj.getPKey() );
					if( mapLoginIdx.size() <= 0 ) {
						indexByLoginIdx.remove( keyLoginIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexBySysRoleIdx != null ) {
				ICFSecSecSysRoleMembBySysRoleIdxKey keySysRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newBySysRoleIdxKey();
				keySysRoleIdx.setRequiredSecSysRoleId( keepObj.getRequiredSecSysRoleId() );
				Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > mapSysRoleIdx = indexBySysRoleIdx.get( keySysRoleIdx );
				if( mapSysRoleIdx != null ) {
					mapSysRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecSysRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecSysRoleMemb != null ) {
				allSecSysRoleMemb.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecSysRoleMemb != null ) {
				allSecSysRoleMemb.put( keepObj.getPKey(), keepObj );
			}

			if( indexBySysRoleIdx != null ) {
				ICFSecSecSysRoleMembBySysRoleIdxKey keySysRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newBySysRoleIdxKey();
				keySysRoleIdx.setRequiredSecSysRoleId( keepObj.getRequiredSecSysRoleId() );
				Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > mapSysRoleIdx = indexBySysRoleIdx.get( keySysRoleIdx );
				if( mapSysRoleIdx != null ) {
					mapSysRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecSysRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecSysRoleMembObj createSecSysRoleMemb( ICFSecSecSysRoleMembObj Obj ) {
		ICFSecSecSysRoleMembObj obj = Obj;
		ICFSecSecSysRoleMemb rec = obj.getSecSysRoleMembRec();
		schema.getCFSecBackingStore().getTableSecSysRoleMemb().createSecSysRoleMemb(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleMembObj readSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey ) {
		return( readSecSysRoleMemb( pkey, false ) );
	}

	@Override
	public ICFSecSecSysRoleMembObj readSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey, boolean forceRead ) {
		ICFSecSecSysRoleMembObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecSysRoleMemb readRec = schema.getCFSecBackingStore().getTableSecSysRoleMemb().readDerivedByIdIdx( null,
						pkey.getRequiredSecSysRoleId(),
						pkey.getRequiredLoginId() );
			if( readRec != null ) {
				obj = schema.getSecSysRoleMembTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecSysRoleMembObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleMembObj readSecSysRoleMemb( ICFLibKeyHash256 SecSysRoleId,
		String LoginId ) {
		return( readSecSysRoleMemb( SecSysRoleId,
			LoginId, false ) );
	}

	@Override
	public ICFSecSecSysRoleMembObj readSecSysRoleMemb( ICFLibKeyHash256 SecSysRoleId,
		String LoginId, boolean forceRead ) {
		ICFSecSecSysRoleMembObj obj = null;
		ICFSecSecSysRoleMemb readRec = schema.getCFSecBackingStore().getTableSecSysRoleMemb().readDerivedByIdIdx( null,
			SecSysRoleId,
			LoginId );
		if( readRec != null ) {
				obj = schema.getSecSysRoleMembTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecSysRoleMembObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleMembObj readCachedSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey ) {
		ICFSecSecSysRoleMembObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecSysRoleMemb( ICFSecSecSysRoleMembObj obj )
	{
		final String S_ProcName = "CFSecSecSysRoleMembTableObj.reallyDeepDisposeSecSysRoleMemb() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecSysRoleMembPKey pkey = obj.getPKey();
		ICFSecSecSysRoleMembObj existing = readCachedSecSysRoleMemb( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecSysRoleMembBySysRoleIdxKey keySysRoleIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newBySysRoleIdxKey();
		keySysRoleIdx.setRequiredSecSysRoleId( existing.getRequiredSecSysRoleId() );

		ICFSecSecSysRoleMembByLoginIdxKey keyLoginIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newByLoginIdxKey();
		keyLoginIdx.setRequiredLoginId( existing.getRequiredLoginId() );



		if( indexBySysRoleIdx != null ) {
			if( indexBySysRoleIdx.containsKey( keySysRoleIdx ) ) {
				indexBySysRoleIdx.get( keySysRoleIdx ).remove( pkey );
				if( indexBySysRoleIdx.get( keySysRoleIdx ).size() <= 0 ) {
					indexBySysRoleIdx.remove( keySysRoleIdx );
				}
			}
		}

		if( indexByLoginIdx != null ) {
			if( indexByLoginIdx.containsKey( keyLoginIdx ) ) {
				indexByLoginIdx.get( keyLoginIdx ).remove( pkey );
				if( indexByLoginIdx.get( keyLoginIdx ).size() <= 0 ) {
					indexByLoginIdx.remove( keyLoginIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey ) {
		ICFSecSecSysRoleMembObj obj = readCachedSecSysRoleMemb( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSysRoleMembObj lockSecSysRoleMemb( ICFSecSecSysRoleMembPKey pkey ) {
		ICFSecSecSysRoleMembObj locked = null;
		ICFSecSecSysRoleMemb lockRec = schema.getCFSecBackingStore().getTableSecSysRoleMemb().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecSysRoleMembTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecSysRoleMembObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecSysRoleMemb", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readAllSecSysRoleMemb() {
		return( readAllSecSysRoleMemb( false ) );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readAllSecSysRoleMemb( boolean forceRead ) {
		final String S_ProcName = "readAllSecSysRoleMemb";
		if( ( allSecSysRoleMemb == null ) || forceRead ) {
			Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> map = new HashMap<ICFSecSecSysRoleMembPKey,ICFSecSecSysRoleMembObj>();
			allSecSysRoleMemb = map;
			ICFSecSecSysRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecSysRoleMemb().readAllDerived( null );
			ICFSecSecSysRoleMemb rec;
			ICFSecSecSysRoleMembObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysRoleMembObj realised = (ICFSecSecSysRoleMembObj)obj.realise();
			}
		}
		int len = allSecSysRoleMemb.size();
		ICFSecSecSysRoleMembObj arr[] = new ICFSecSecSysRoleMembObj[len];
		Iterator<ICFSecSecSysRoleMembObj> valIter = allSecSysRoleMemb.values().iterator();
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
		ArrayList<ICFSecSecSysRoleMembObj> arrayList = new ArrayList<ICFSecSecSysRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysRoleMembObj> cmp = new Comparator<ICFSecSecSysRoleMembObj>() {
			@Override
			public int compare( ICFSecSecSysRoleMembObj lhs, ICFSecSecSysRoleMembObj rhs ) {
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
					ICFSecSecSysRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readCachedAllSecSysRoleMemb() {
		final String S_ProcName = "readCachedAllSecSysRoleMemb";
		ArrayList<ICFSecSecSysRoleMembObj> arrayList = new ArrayList<ICFSecSecSysRoleMembObj>();
		if( allSecSysRoleMemb != null ) {
			int len = allSecSysRoleMemb.size();
			ICFSecSecSysRoleMembObj arr[] = new ICFSecSecSysRoleMembObj[len];
			Iterator<ICFSecSecSysRoleMembObj> valIter = allSecSysRoleMemb.values().iterator();
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
		Comparator<ICFSecSecSysRoleMembObj> cmp = new Comparator<ICFSecSecSysRoleMembObj>() {
			public int compare( ICFSecSecSysRoleMembObj lhs, ICFSecSecSysRoleMembObj rhs ) {
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
					ICFSecSecSysRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecSysRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecSysRoleMembObj> pageAllSecSysRoleMemb(ICFLibKeyHash256 priorSecSysRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageAllSecSysRoleMemb";
		Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> map = new HashMap<ICFSecSecSysRoleMembPKey,ICFSecSecSysRoleMembObj>();
		ICFSecSecSysRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecSysRoleMemb().pageAllRec( null,
			priorSecSysRoleId,
			priorLoginId );
		ICFSecSecSysRoleMemb rec;
		ICFSecSecSysRoleMembObj obj;
		ICFSecSecSysRoleMembObj realised;
		ArrayList<ICFSecSecSysRoleMembObj> arrayList = new ArrayList<ICFSecSecSysRoleMembObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecSysRoleMembObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecSysRoleMembObj readSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId )
	{
		return( readSecSysRoleMembByIdIdx( SecSysRoleId,
			LoginId,
			false ) );
	}

	@Override
	public ICFSecSecSysRoleMembObj readSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId, boolean forceRead )
	{
		ICFSecSecSysRoleMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newPKey();
		pkey.setRequiredSecSysRoleId( SecSysRoleId );
		pkey.setRequiredLoginId( LoginId );
		ICFSecSecSysRoleMembObj obj = readSecSysRoleMemb( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		return( readSecSysRoleMembBySysRoleIdx( SecSysRoleId,
			false ) );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysRoleMembBySysRoleIdx";
		ICFSecSecSysRoleMembBySysRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newBySysRoleIdxKey();
		key.setRequiredSecSysRoleId( SecSysRoleId );
		Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> dict;
		if( indexBySysRoleIdx == null ) {
			indexBySysRoleIdx = new HashMap< ICFSecSecSysRoleMembBySysRoleIdxKey,
				Map< ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > >();
		}
		if( ( ! forceRead ) && indexBySysRoleIdx.containsKey( key ) ) {
			dict = indexBySysRoleIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj>();
			ICFSecSecSysRoleMembObj obj;
			ICFSecSecSysRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecSysRoleMemb().readDerivedBySysRoleIdx( null,
				SecSysRoleId );
			ICFSecSecSysRoleMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysRoleMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysRoleMembObj realised = (ICFSecSecSysRoleMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySysRoleIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysRoleMembObj arr[] = new ICFSecSecSysRoleMembObj[len];
		Iterator<ICFSecSecSysRoleMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysRoleMembObj> arrayList = new ArrayList<ICFSecSecSysRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysRoleMembObj> cmp = new Comparator<ICFSecSecSysRoleMembObj>() {
			@Override
			public int compare( ICFSecSecSysRoleMembObj lhs, ICFSecSecSysRoleMembObj rhs ) {
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
					ICFSecSecSysRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readSecSysRoleMembByLoginIdx( String LoginId )
	{
		return( readSecSysRoleMembByLoginIdx( LoginId,
			false ) );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readSecSysRoleMembByLoginIdx( String LoginId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysRoleMembByLoginIdx";
		ICFSecSecSysRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> dict;
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecSysRoleMembByLoginIdxKey,
				Map< ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > >();
		}
		if( ( ! forceRead ) && indexByLoginIdx.containsKey( key ) ) {
			dict = indexByLoginIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj>();
			ICFSecSecSysRoleMembObj obj;
			ICFSecSecSysRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecSysRoleMemb().readDerivedByLoginIdx( null,
				LoginId );
			ICFSecSecSysRoleMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysRoleMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysRoleMembObj realised = (ICFSecSecSysRoleMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByLoginIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysRoleMembObj arr[] = new ICFSecSecSysRoleMembObj[len];
		Iterator<ICFSecSecSysRoleMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysRoleMembObj> arrayList = new ArrayList<ICFSecSecSysRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysRoleMembObj> cmp = new Comparator<ICFSecSecSysRoleMembObj>() {
			@Override
			public int compare( ICFSecSecSysRoleMembObj lhs, ICFSecSecSysRoleMembObj rhs ) {
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
					ICFSecSecSysRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecSysRoleMembObj readCachedSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId )
	{
		ICFSecSecSysRoleMembObj obj = null;
		ICFSecSecSysRoleMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newPKey();
		pkey.setRequiredSecSysRoleId( SecSysRoleId );
		pkey.setRequiredLoginId( LoginId );
		pkey.setRequiredSecSysRoleId( SecSysRoleId );
		pkey.setRequiredLoginId( LoginId );
		obj = readCachedSecSysRoleMemb( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readCachedSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		final String S_ProcName = "readCachedSecSysRoleMembBySysRoleIdx";
		ICFSecSecSysRoleMembBySysRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newBySysRoleIdxKey();
		key.setRequiredSecSysRoleId( SecSysRoleId );
		ArrayList<ICFSecSecSysRoleMembObj> arrayList = new ArrayList<ICFSecSecSysRoleMembObj>();
		if( indexBySysRoleIdx != null ) {
			Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> dict;
			if( indexBySysRoleIdx.containsKey( key ) ) {
				dict = indexBySysRoleIdx.get( key );
				int len = dict.size();
				ICFSecSecSysRoleMembObj arr[] = new ICFSecSecSysRoleMembObj[len];
				Iterator<ICFSecSecSysRoleMembObj> valIter = dict.values().iterator();
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
			ICFSecSecSysRoleMembObj obj;
			Iterator<ICFSecSecSysRoleMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysRoleMembObj> cmp = new Comparator<ICFSecSecSysRoleMembObj>() {
			@Override
			public int compare( ICFSecSecSysRoleMembObj lhs, ICFSecSecSysRoleMembObj rhs ) {
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
					ICFSecSecSysRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecSysRoleMembObj> readCachedSecSysRoleMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "readCachedSecSysRoleMembByLoginIdx";
		ICFSecSecSysRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		ArrayList<ICFSecSecSysRoleMembObj> arrayList = new ArrayList<ICFSecSecSysRoleMembObj>();
		if( indexByLoginIdx != null ) {
			Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> dict;
			if( indexByLoginIdx.containsKey( key ) ) {
				dict = indexByLoginIdx.get( key );
				int len = dict.size();
				ICFSecSecSysRoleMembObj arr[] = new ICFSecSecSysRoleMembObj[len];
				Iterator<ICFSecSecSysRoleMembObj> valIter = dict.values().iterator();
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
			ICFSecSecSysRoleMembObj obj;
			Iterator<ICFSecSecSysRoleMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysRoleMembObj> cmp = new Comparator<ICFSecSecSysRoleMembObj>() {
			@Override
			public int compare( ICFSecSecSysRoleMembObj lhs, ICFSecSecSysRoleMembObj rhs ) {
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
					ICFSecSecSysRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId )
	{
		ICFSecSecSysRoleMembObj obj = readCachedSecSysRoleMembByIdIdx( SecSysRoleId,
				LoginId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		final String S_ProcName = "deepDisposeSecSysRoleMembBySysRoleIdx";
		ICFSecSecSysRoleMembObj obj;
		List<ICFSecSecSysRoleMembObj> arrayList = readCachedSecSysRoleMembBySysRoleIdx( SecSysRoleId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysRoleMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecSysRoleMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "deepDisposeSecSysRoleMembByLoginIdx";
		ICFSecSecSysRoleMembObj obj;
		List<ICFSecSecSysRoleMembObj> arrayList = readCachedSecSysRoleMembByLoginIdx( LoginId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysRoleMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecSysRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysRoleIdx key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysRoleMembObj> pageSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecSysRoleMembBySysRoleIdx";
		ICFSecSecSysRoleMembBySysRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newBySysRoleIdxKey();
		key.setRequiredSecSysRoleId( SecSysRoleId );
		List<ICFSecSecSysRoleMembObj> retList = new LinkedList<ICFSecSecSysRoleMembObj>();
		ICFSecSecSysRoleMembObj obj;
		ICFSecSecSysRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecSysRoleMemb().pageRecBySysRoleIdx( null,
				SecSysRoleId,
			priorSecSysRoleId,
			priorLoginId );
		ICFSecSecSysRoleMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysRoleMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysRoleMembObj realised = (ICFSecSecSysRoleMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecSysRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecSysRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysRoleMembObj> pageSecSysRoleMembByLoginIdx( String LoginId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecSysRoleMembByLoginIdx";
		ICFSecSecSysRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		List<ICFSecSecSysRoleMembObj> retList = new LinkedList<ICFSecSecSysRoleMembObj>();
		ICFSecSecSysRoleMembObj obj;
		ICFSecSecSysRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecSysRoleMemb().pageRecByLoginIdx( null,
				LoginId,
			priorSecSysRoleId,
			priorLoginId );
		ICFSecSecSysRoleMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysRoleMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysRoleMembObj realised = (ICFSecSecSysRoleMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecSysRoleMembObj updateSecSysRoleMemb( ICFSecSecSysRoleMembObj Obj ) {
		ICFSecSecSysRoleMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysRoleMemb().updateSecSysRoleMemb( null,
			Obj.getSecSysRoleMembRec() );
		obj = (ICFSecSecSysRoleMembObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecSysRoleMemb( ICFSecSecSysRoleMembObj Obj ) {
		ICFSecSecSysRoleMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysRoleMemb().deleteSecSysRoleMemb( null,
			obj.getSecSysRoleMembRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecSysRoleMembByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String LoginId )
	{
		ICFSecSecSysRoleMembObj obj = readSecSysRoleMemb(SecSysRoleId,
				LoginId);
		if( obj != null ) {
			ICFSecSecSysRoleMembEditObj editObj = (ICFSecSecSysRoleMembEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecSysRoleMembEditObj)obj.beginEdit();
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
		deepDisposeSecSysRoleMembByIdIdx( SecSysRoleId,
				LoginId );
	}

	@Override
	public void deleteSecSysRoleMembBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		ICFSecSecSysRoleMembBySysRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newBySysRoleIdxKey();
		key.setRequiredSecSysRoleId( SecSysRoleId );
		if( indexBySysRoleIdx == null ) {
			indexBySysRoleIdx = new HashMap< ICFSecSecSysRoleMembBySysRoleIdxKey,
				Map< ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > >();
		}
		if( indexBySysRoleIdx.containsKey( key ) ) {
			Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> dict = indexBySysRoleIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysRoleMemb().deleteSecSysRoleMembBySysRoleIdx( null,
				SecSysRoleId );
			Iterator<ICFSecSecSysRoleMembObj> iter = dict.values().iterator();
			ICFSecSecSysRoleMembObj obj;
			List<ICFSecSecSysRoleMembObj> toForget = new LinkedList<ICFSecSecSysRoleMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySysRoleIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysRoleMemb().deleteSecSysRoleMembBySysRoleIdx( null,
				SecSysRoleId );
		}
		deepDisposeSecSysRoleMembBySysRoleIdx( SecSysRoleId );
	}

	@Override
	public void deleteSecSysRoleMembByLoginIdx( String LoginId )
	{
		ICFSecSecSysRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecSysRoleMembByLoginIdxKey,
				Map< ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj > >();
		}
		if( indexByLoginIdx.containsKey( key ) ) {
			Map<ICFSecSecSysRoleMembPKey, ICFSecSecSysRoleMembObj> dict = indexByLoginIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysRoleMemb().deleteSecSysRoleMembByLoginIdx( null,
				LoginId );
			Iterator<ICFSecSecSysRoleMembObj> iter = dict.values().iterator();
			ICFSecSecSysRoleMembObj obj;
			List<ICFSecSecSysRoleMembObj> toForget = new LinkedList<ICFSecSecSysRoleMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByLoginIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysRoleMemb().deleteSecSysRoleMembByLoginIdx( null,
				LoginId );
		}
		deepDisposeSecSysRoleMembByLoginIdx( LoginId );
	}
}