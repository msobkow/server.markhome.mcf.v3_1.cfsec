// Description: Java 25 Table Object implementation for SecClusRoleMemb.

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

public class CFSecSecClusRoleMembTableObj
	implements ICFSecSecClusRoleMembTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecClusRoleMemb.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecClusRoleMemb.CLASS_CODE;
	private Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> members;
	private Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> allSecClusRoleMemb;
	private Map< ICFSecSecClusRoleMembByClusRoleIdxKey,
		Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > > indexByClusRoleIdx;
	private Map< ICFSecSecClusRoleMembByLoginIdxKey,
		Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > > indexByLoginIdx;
	public static String TABLE_NAME = "SecClusRoleMemb";
	public static String TABLE_DBNAME = "secclusrolememb";

	public CFSecSecClusRoleMembTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj>();
		allSecClusRoleMemb = null;
		indexByClusRoleIdx = null;
		indexByLoginIdx = null;
	}

	public CFSecSecClusRoleMembTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj>();
		allSecClusRoleMemb = null;
		indexByClusRoleIdx = null;
		indexByLoginIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecClusRoleMembTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecClusRoleMembTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecClusRoleMemb = null;
		indexByClusRoleIdx = null;
		indexByLoginIdx = null;
		List<ICFSecSecClusRoleMembObj> toForget = new LinkedList<ICFSecSecClusRoleMembObj>();
		ICFSecSecClusRoleMembObj cur = null;
		Iterator<ICFSecSecClusRoleMembObj> iter = members.values().iterator();
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
	 *	CFSecSecClusRoleMembObj.
	 */
	@Override
	public ICFSecSecClusRoleMembObj newInstance() {
		ICFSecSecClusRoleMembObj inst = new CFSecSecClusRoleMembObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecClusRoleMembObj.
	 */
	@Override
	public ICFSecSecClusRoleMembEditObj newEditInstance( ICFSecSecClusRoleMembObj orig ) {
		ICFSecSecClusRoleMembEditObj edit = new CFSecSecClusRoleMembEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecClusRoleMembObj realiseSecClusRoleMemb( ICFSecSecClusRoleMembObj Obj ) {
		ICFSecSecClusRoleMembObj obj = Obj;
		ICFSecSecClusRoleMembPKey pkey = obj.getPKey();
		ICFSecSecClusRoleMembObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecClusRoleMembObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByClusRoleIdx != null ) {
				ICFSecSecClusRoleMembByClusRoleIdxKey keyClusRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByClusRoleIdxKey();
				keyClusRoleIdx.setRequiredSecClusRoleId( keepObj.getRequiredSecClusRoleId() );
				Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > mapClusRoleIdx = indexByClusRoleIdx.get( keyClusRoleIdx );
				if( mapClusRoleIdx != null ) {
					mapClusRoleIdx.remove( keepObj.getPKey() );
					if( mapClusRoleIdx.size() <= 0 ) {
						indexByClusRoleIdx.remove( keyClusRoleIdx );
					}
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecClusRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.remove( keepObj.getPKey() );
					if( mapLoginIdx.size() <= 0 ) {
						indexByLoginIdx.remove( keyLoginIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByClusRoleIdx != null ) {
				ICFSecSecClusRoleMembByClusRoleIdxKey keyClusRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByClusRoleIdxKey();
				keyClusRoleIdx.setRequiredSecClusRoleId( keepObj.getRequiredSecClusRoleId() );
				Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > mapClusRoleIdx = indexByClusRoleIdx.get( keyClusRoleIdx );
				if( mapClusRoleIdx != null ) {
					mapClusRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecClusRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecClusRoleMemb != null ) {
				allSecClusRoleMemb.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecClusRoleMemb != null ) {
				allSecClusRoleMemb.put( keepObj.getPKey(), keepObj );
			}

			if( indexByClusRoleIdx != null ) {
				ICFSecSecClusRoleMembByClusRoleIdxKey keyClusRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByClusRoleIdxKey();
				keyClusRoleIdx.setRequiredSecClusRoleId( keepObj.getRequiredSecClusRoleId() );
				Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > mapClusRoleIdx = indexByClusRoleIdx.get( keyClusRoleIdx );
				if( mapClusRoleIdx != null ) {
					mapClusRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecClusRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecClusRoleMembObj createSecClusRoleMemb( ICFSecSecClusRoleMembObj Obj ) {
		ICFSecSecClusRoleMembObj obj = Obj;
		ICFSecSecClusRoleMemb rec = obj.getSecClusRoleMembRec();
		schema.getCFSecBackingStore().getTableSecClusRoleMemb().createSecClusRoleMemb(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecClusRoleMembObj readSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey ) {
		return( readSecClusRoleMemb( pkey, false ) );
	}

	@Override
	public ICFSecSecClusRoleMembObj readSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey, boolean forceRead ) {
		ICFSecSecClusRoleMembObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecClusRoleMemb readRec = schema.getCFSecBackingStore().getTableSecClusRoleMemb().readDerivedByIdIdx( null,
						pkey.getRequiredSecClusRoleId(),
						pkey.getRequiredLoginId() );
			if( readRec != null ) {
				obj = schema.getSecClusRoleMembTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecClusRoleMembObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusRoleMembObj readSecClusRoleMemb( ICFLibKeyHash256 SecClusRoleId,
		String LoginId ) {
		return( readSecClusRoleMemb( SecClusRoleId,
			LoginId, false ) );
	}

	@Override
	public ICFSecSecClusRoleMembObj readSecClusRoleMemb( ICFLibKeyHash256 SecClusRoleId,
		String LoginId, boolean forceRead ) {
		ICFSecSecClusRoleMembObj obj = null;
		ICFSecSecClusRoleMemb readRec = schema.getCFSecBackingStore().getTableSecClusRoleMemb().readDerivedByIdIdx( null,
			SecClusRoleId,
			LoginId );
		if( readRec != null ) {
				obj = schema.getSecClusRoleMembTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecClusRoleMembObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusRoleMembObj readCachedSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey ) {
		ICFSecSecClusRoleMembObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecClusRoleMemb( ICFSecSecClusRoleMembObj obj )
	{
		final String S_ProcName = "CFSecSecClusRoleMembTableObj.reallyDeepDisposeSecClusRoleMemb() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecClusRoleMembPKey pkey = obj.getPKey();
		ICFSecSecClusRoleMembObj existing = readCachedSecClusRoleMemb( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecClusRoleMembByClusRoleIdxKey keyClusRoleIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByClusRoleIdxKey();
		keyClusRoleIdx.setRequiredSecClusRoleId( existing.getRequiredSecClusRoleId() );

		ICFSecSecClusRoleMembByLoginIdxKey keyLoginIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByLoginIdxKey();
		keyLoginIdx.setRequiredLoginId( existing.getRequiredLoginId() );



		if( indexByClusRoleIdx != null ) {
			if( indexByClusRoleIdx.containsKey( keyClusRoleIdx ) ) {
				indexByClusRoleIdx.get( keyClusRoleIdx ).remove( pkey );
				if( indexByClusRoleIdx.get( keyClusRoleIdx ).size() <= 0 ) {
					indexByClusRoleIdx.remove( keyClusRoleIdx );
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
	public void deepDisposeSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey ) {
		ICFSecSecClusRoleMembObj obj = readCachedSecClusRoleMemb( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecClusRoleMembObj lockSecClusRoleMemb( ICFSecSecClusRoleMembPKey pkey ) {
		ICFSecSecClusRoleMembObj locked = null;
		ICFSecSecClusRoleMemb lockRec = schema.getCFSecBackingStore().getTableSecClusRoleMemb().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecClusRoleMembTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecClusRoleMembObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecClusRoleMemb", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readAllSecClusRoleMemb() {
		return( readAllSecClusRoleMemb( false ) );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readAllSecClusRoleMemb( boolean forceRead ) {
		final String S_ProcName = "readAllSecClusRoleMemb";
		if( ( allSecClusRoleMemb == null ) || forceRead ) {
			Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> map = new HashMap<ICFSecSecClusRoleMembPKey,ICFSecSecClusRoleMembObj>();
			allSecClusRoleMemb = map;
			ICFSecSecClusRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecClusRoleMemb().readAllDerived( null );
			ICFSecSecClusRoleMemb rec;
			ICFSecSecClusRoleMembObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusRoleMembObj realised = (ICFSecSecClusRoleMembObj)obj.realise();
			}
		}
		int len = allSecClusRoleMemb.size();
		ICFSecSecClusRoleMembObj arr[] = new ICFSecSecClusRoleMembObj[len];
		Iterator<ICFSecSecClusRoleMembObj> valIter = allSecClusRoleMemb.values().iterator();
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
		ArrayList<ICFSecSecClusRoleMembObj> arrayList = new ArrayList<ICFSecSecClusRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusRoleMembObj> cmp = new Comparator<ICFSecSecClusRoleMembObj>() {
			@Override
			public int compare( ICFSecSecClusRoleMembObj lhs, ICFSecSecClusRoleMembObj rhs ) {
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
					ICFSecSecClusRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readCachedAllSecClusRoleMemb() {
		final String S_ProcName = "readCachedAllSecClusRoleMemb";
		ArrayList<ICFSecSecClusRoleMembObj> arrayList = new ArrayList<ICFSecSecClusRoleMembObj>();
		if( allSecClusRoleMemb != null ) {
			int len = allSecClusRoleMemb.size();
			ICFSecSecClusRoleMembObj arr[] = new ICFSecSecClusRoleMembObj[len];
			Iterator<ICFSecSecClusRoleMembObj> valIter = allSecClusRoleMemb.values().iterator();
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
		Comparator<ICFSecSecClusRoleMembObj> cmp = new Comparator<ICFSecSecClusRoleMembObj>() {
			public int compare( ICFSecSecClusRoleMembObj lhs, ICFSecSecClusRoleMembObj rhs ) {
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
					ICFSecSecClusRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecClusRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecClusRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecClusRoleMembObj> pageAllSecClusRoleMemb(ICFLibKeyHash256 priorSecClusRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageAllSecClusRoleMemb";
		Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> map = new HashMap<ICFSecSecClusRoleMembPKey,ICFSecSecClusRoleMembObj>();
		ICFSecSecClusRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecClusRoleMemb().pageAllRec( null,
			priorSecClusRoleId,
			priorLoginId );
		ICFSecSecClusRoleMemb rec;
		ICFSecSecClusRoleMembObj obj;
		ICFSecSecClusRoleMembObj realised;
		ArrayList<ICFSecSecClusRoleMembObj> arrayList = new ArrayList<ICFSecSecClusRoleMembObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecClusRoleMembObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecClusRoleMembObj readSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId )
	{
		return( readSecClusRoleMembByIdIdx( SecClusRoleId,
			LoginId,
			false ) );
	}

	@Override
	public ICFSecSecClusRoleMembObj readSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId, boolean forceRead )
	{
		ICFSecSecClusRoleMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newPKey();
		pkey.setRequiredSecClusRoleId( SecClusRoleId );
		pkey.setRequiredLoginId( LoginId );
		ICFSecSecClusRoleMembObj obj = readSecClusRoleMemb( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId )
	{
		return( readSecClusRoleMembByClusRoleIdx( SecClusRoleId,
			false ) );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusRoleMembByClusRoleIdx";
		ICFSecSecClusRoleMembByClusRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByClusRoleIdxKey();
		key.setRequiredSecClusRoleId( SecClusRoleId );
		Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> dict;
		if( indexByClusRoleIdx == null ) {
			indexByClusRoleIdx = new HashMap< ICFSecSecClusRoleMembByClusRoleIdxKey,
				Map< ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > >();
		}
		if( ( ! forceRead ) && indexByClusRoleIdx.containsKey( key ) ) {
			dict = indexByClusRoleIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj>();
			ICFSecSecClusRoleMembObj obj;
			ICFSecSecClusRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecClusRoleMemb().readDerivedByClusRoleIdx( null,
				SecClusRoleId );
			ICFSecSecClusRoleMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusRoleMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusRoleMembObj realised = (ICFSecSecClusRoleMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClusRoleIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusRoleMembObj arr[] = new ICFSecSecClusRoleMembObj[len];
		Iterator<ICFSecSecClusRoleMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecClusRoleMembObj> arrayList = new ArrayList<ICFSecSecClusRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusRoleMembObj> cmp = new Comparator<ICFSecSecClusRoleMembObj>() {
			@Override
			public int compare( ICFSecSecClusRoleMembObj lhs, ICFSecSecClusRoleMembObj rhs ) {
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
					ICFSecSecClusRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readSecClusRoleMembByLoginIdx( String LoginId )
	{
		return( readSecClusRoleMembByLoginIdx( LoginId,
			false ) );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readSecClusRoleMembByLoginIdx( String LoginId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusRoleMembByLoginIdx";
		ICFSecSecClusRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> dict;
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecClusRoleMembByLoginIdxKey,
				Map< ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > >();
		}
		if( ( ! forceRead ) && indexByLoginIdx.containsKey( key ) ) {
			dict = indexByLoginIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj>();
			ICFSecSecClusRoleMembObj obj;
			ICFSecSecClusRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecClusRoleMemb().readDerivedByLoginIdx( null,
				LoginId );
			ICFSecSecClusRoleMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusRoleMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusRoleMembObj realised = (ICFSecSecClusRoleMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByLoginIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusRoleMembObj arr[] = new ICFSecSecClusRoleMembObj[len];
		Iterator<ICFSecSecClusRoleMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecClusRoleMembObj> arrayList = new ArrayList<ICFSecSecClusRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusRoleMembObj> cmp = new Comparator<ICFSecSecClusRoleMembObj>() {
			@Override
			public int compare( ICFSecSecClusRoleMembObj lhs, ICFSecSecClusRoleMembObj rhs ) {
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
					ICFSecSecClusRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecClusRoleMembObj readCachedSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId )
	{
		ICFSecSecClusRoleMembObj obj = null;
		ICFSecSecClusRoleMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newPKey();
		pkey.setRequiredSecClusRoleId( SecClusRoleId );
		pkey.setRequiredLoginId( LoginId );
		pkey.setRequiredSecClusRoleId( SecClusRoleId );
		pkey.setRequiredLoginId( LoginId );
		obj = readCachedSecClusRoleMemb( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readCachedSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId )
	{
		final String S_ProcName = "readCachedSecClusRoleMembByClusRoleIdx";
		ICFSecSecClusRoleMembByClusRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByClusRoleIdxKey();
		key.setRequiredSecClusRoleId( SecClusRoleId );
		ArrayList<ICFSecSecClusRoleMembObj> arrayList = new ArrayList<ICFSecSecClusRoleMembObj>();
		if( indexByClusRoleIdx != null ) {
			Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> dict;
			if( indexByClusRoleIdx.containsKey( key ) ) {
				dict = indexByClusRoleIdx.get( key );
				int len = dict.size();
				ICFSecSecClusRoleMembObj arr[] = new ICFSecSecClusRoleMembObj[len];
				Iterator<ICFSecSecClusRoleMembObj> valIter = dict.values().iterator();
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
			ICFSecSecClusRoleMembObj obj;
			Iterator<ICFSecSecClusRoleMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusRoleMembObj> cmp = new Comparator<ICFSecSecClusRoleMembObj>() {
			@Override
			public int compare( ICFSecSecClusRoleMembObj lhs, ICFSecSecClusRoleMembObj rhs ) {
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
					ICFSecSecClusRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecClusRoleMembObj> readCachedSecClusRoleMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "readCachedSecClusRoleMembByLoginIdx";
		ICFSecSecClusRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		ArrayList<ICFSecSecClusRoleMembObj> arrayList = new ArrayList<ICFSecSecClusRoleMembObj>();
		if( indexByLoginIdx != null ) {
			Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> dict;
			if( indexByLoginIdx.containsKey( key ) ) {
				dict = indexByLoginIdx.get( key );
				int len = dict.size();
				ICFSecSecClusRoleMembObj arr[] = new ICFSecSecClusRoleMembObj[len];
				Iterator<ICFSecSecClusRoleMembObj> valIter = dict.values().iterator();
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
			ICFSecSecClusRoleMembObj obj;
			Iterator<ICFSecSecClusRoleMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusRoleMembObj> cmp = new Comparator<ICFSecSecClusRoleMembObj>() {
			@Override
			public int compare( ICFSecSecClusRoleMembObj lhs, ICFSecSecClusRoleMembObj rhs ) {
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
					ICFSecSecClusRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId )
	{
		ICFSecSecClusRoleMembObj obj = readCachedSecClusRoleMembByIdIdx( SecClusRoleId,
				LoginId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId )
	{
		final String S_ProcName = "deepDisposeSecClusRoleMembByClusRoleIdx";
		ICFSecSecClusRoleMembObj obj;
		List<ICFSecSecClusRoleMembObj> arrayList = readCachedSecClusRoleMembByClusRoleIdx( SecClusRoleId );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusRoleMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecClusRoleMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "deepDisposeSecClusRoleMembByLoginIdx";
		ICFSecSecClusRoleMembObj obj;
		List<ICFSecSecClusRoleMembObj> arrayList = readCachedSecClusRoleMembByLoginIdx( LoginId );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusRoleMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecClusRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ClusRoleIdx key attributes.
	 *
	 *	@param	SecClusRoleId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecClusRoleMembObj> pageSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId,
		ICFLibKeyHash256 priorSecClusRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecClusRoleMembByClusRoleIdx";
		ICFSecSecClusRoleMembByClusRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByClusRoleIdxKey();
		key.setRequiredSecClusRoleId( SecClusRoleId );
		List<ICFSecSecClusRoleMembObj> retList = new LinkedList<ICFSecSecClusRoleMembObj>();
		ICFSecSecClusRoleMembObj obj;
		ICFSecSecClusRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecClusRoleMemb().pageRecByClusRoleIdx( null,
				SecClusRoleId,
			priorSecClusRoleId,
			priorLoginId );
		ICFSecSecClusRoleMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecClusRoleMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecClusRoleMembObj realised = (ICFSecSecClusRoleMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecClusRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecClusRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecClusRoleMembObj> pageSecClusRoleMembByLoginIdx( String LoginId,
		ICFLibKeyHash256 priorSecClusRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecClusRoleMembByLoginIdx";
		ICFSecSecClusRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		List<ICFSecSecClusRoleMembObj> retList = new LinkedList<ICFSecSecClusRoleMembObj>();
		ICFSecSecClusRoleMembObj obj;
		ICFSecSecClusRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecClusRoleMemb().pageRecByLoginIdx( null,
				LoginId,
			priorSecClusRoleId,
			priorLoginId );
		ICFSecSecClusRoleMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecClusRoleMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecClusRoleMembObj realised = (ICFSecSecClusRoleMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecClusRoleMembObj updateSecClusRoleMemb( ICFSecSecClusRoleMembObj Obj ) {
		ICFSecSecClusRoleMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusRoleMemb().updateSecClusRoleMemb( null,
			Obj.getSecClusRoleMembRec() );
		obj = (ICFSecSecClusRoleMembObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecClusRoleMemb( ICFSecSecClusRoleMembObj Obj ) {
		ICFSecSecClusRoleMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusRoleMemb().deleteSecClusRoleMemb( null,
			obj.getSecClusRoleMembRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecClusRoleMembByIdIdx( ICFLibKeyHash256 SecClusRoleId,
		String LoginId )
	{
		ICFSecSecClusRoleMembObj obj = readSecClusRoleMemb(SecClusRoleId,
				LoginId);
		if( obj != null ) {
			ICFSecSecClusRoleMembEditObj editObj = (ICFSecSecClusRoleMembEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecClusRoleMembEditObj)obj.beginEdit();
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
		deepDisposeSecClusRoleMembByIdIdx( SecClusRoleId,
				LoginId );
	}

	@Override
	public void deleteSecClusRoleMembByClusRoleIdx( ICFLibKeyHash256 SecClusRoleId )
	{
		ICFSecSecClusRoleMembByClusRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByClusRoleIdxKey();
		key.setRequiredSecClusRoleId( SecClusRoleId );
		if( indexByClusRoleIdx == null ) {
			indexByClusRoleIdx = new HashMap< ICFSecSecClusRoleMembByClusRoleIdxKey,
				Map< ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > >();
		}
		if( indexByClusRoleIdx.containsKey( key ) ) {
			Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> dict = indexByClusRoleIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusRoleMemb().deleteSecClusRoleMembByClusRoleIdx( null,
				SecClusRoleId );
			Iterator<ICFSecSecClusRoleMembObj> iter = dict.values().iterator();
			ICFSecSecClusRoleMembObj obj;
			List<ICFSecSecClusRoleMembObj> toForget = new LinkedList<ICFSecSecClusRoleMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClusRoleIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusRoleMemb().deleteSecClusRoleMembByClusRoleIdx( null,
				SecClusRoleId );
		}
		deepDisposeSecClusRoleMembByClusRoleIdx( SecClusRoleId );
	}

	@Override
	public void deleteSecClusRoleMembByLoginIdx( String LoginId )
	{
		ICFSecSecClusRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecClusRoleMembByLoginIdxKey,
				Map< ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj > >();
		}
		if( indexByLoginIdx.containsKey( key ) ) {
			Map<ICFSecSecClusRoleMembPKey, ICFSecSecClusRoleMembObj> dict = indexByLoginIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusRoleMemb().deleteSecClusRoleMembByLoginIdx( null,
				LoginId );
			Iterator<ICFSecSecClusRoleMembObj> iter = dict.values().iterator();
			ICFSecSecClusRoleMembObj obj;
			List<ICFSecSecClusRoleMembObj> toForget = new LinkedList<ICFSecSecClusRoleMembObj>();
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
			schema.getCFSecBackingStore().getTableSecClusRoleMemb().deleteSecClusRoleMembByLoginIdx( null,
				LoginId );
		}
		deepDisposeSecClusRoleMembByLoginIdx( LoginId );
	}
}