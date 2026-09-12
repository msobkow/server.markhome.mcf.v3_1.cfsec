// Description: Java 25 Table Object implementation for SecClusGrpMemb.

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

public class CFSecSecClusGrpMembTableObj
	implements ICFSecSecClusGrpMembTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecClusGrpMemb.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecClusGrpMemb.CLASS_CODE;
	private Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> members;
	private Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> allSecClusGrpMemb;
	private Map< ICFSecSecClusGrpMembByClusGrpIdxKey,
		Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > > indexByClusGrpIdx;
	private Map< ICFSecSecClusGrpMembByLoginIdxKey,
		Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > > indexByLoginIdx;
	public static String TABLE_NAME = "SecClusGrpMemb";
	public static String TABLE_DBNAME = "secclusgrpmemb";

	public CFSecSecClusGrpMembTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj>();
		allSecClusGrpMemb = null;
		indexByClusGrpIdx = null;
		indexByLoginIdx = null;
	}

	public CFSecSecClusGrpMembTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj>();
		allSecClusGrpMemb = null;
		indexByClusGrpIdx = null;
		indexByLoginIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecClusGrpMembTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecClusGrpMembTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecClusGrpMemb = null;
		indexByClusGrpIdx = null;
		indexByLoginIdx = null;
		List<ICFSecSecClusGrpMembObj> toForget = new LinkedList<ICFSecSecClusGrpMembObj>();
		ICFSecSecClusGrpMembObj cur = null;
		Iterator<ICFSecSecClusGrpMembObj> iter = members.values().iterator();
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
	 *	CFSecSecClusGrpMembObj.
	 */
	@Override
	public ICFSecSecClusGrpMembObj newInstance() {
		ICFSecSecClusGrpMembObj inst = new CFSecSecClusGrpMembObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecClusGrpMembObj.
	 */
	@Override
	public ICFSecSecClusGrpMembEditObj newEditInstance( ICFSecSecClusGrpMembObj orig ) {
		ICFSecSecClusGrpMembEditObj edit = new CFSecSecClusGrpMembEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecClusGrpMembObj realiseSecClusGrpMemb( ICFSecSecClusGrpMembObj Obj ) {
		ICFSecSecClusGrpMembObj obj = Obj;
		ICFSecSecClusGrpMembPKey pkey = obj.getPKey();
		ICFSecSecClusGrpMembObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecClusGrpMembObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByClusGrpIdx != null ) {
				ICFSecSecClusGrpMembByClusGrpIdxKey keyClusGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByClusGrpIdxKey();
				keyClusGrpIdx.setRequiredSecClusGrpId( keepObj.getRequiredSecClusGrpId() );
				Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > mapClusGrpIdx = indexByClusGrpIdx.get( keyClusGrpIdx );
				if( mapClusGrpIdx != null ) {
					mapClusGrpIdx.remove( keepObj.getPKey() );
					if( mapClusGrpIdx.size() <= 0 ) {
						indexByClusGrpIdx.remove( keyClusGrpIdx );
					}
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecClusGrpMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.remove( keepObj.getPKey() );
					if( mapLoginIdx.size() <= 0 ) {
						indexByLoginIdx.remove( keyLoginIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByClusGrpIdx != null ) {
				ICFSecSecClusGrpMembByClusGrpIdxKey keyClusGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByClusGrpIdxKey();
				keyClusGrpIdx.setRequiredSecClusGrpId( keepObj.getRequiredSecClusGrpId() );
				Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > mapClusGrpIdx = indexByClusGrpIdx.get( keyClusGrpIdx );
				if( mapClusGrpIdx != null ) {
					mapClusGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecClusGrpMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecClusGrpMemb != null ) {
				allSecClusGrpMemb.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecClusGrpMemb != null ) {
				allSecClusGrpMemb.put( keepObj.getPKey(), keepObj );
			}

			if( indexByClusGrpIdx != null ) {
				ICFSecSecClusGrpMembByClusGrpIdxKey keyClusGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByClusGrpIdxKey();
				keyClusGrpIdx.setRequiredSecClusGrpId( keepObj.getRequiredSecClusGrpId() );
				Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > mapClusGrpIdx = indexByClusGrpIdx.get( keyClusGrpIdx );
				if( mapClusGrpIdx != null ) {
					mapClusGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecClusGrpMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecClusGrpMembObj createSecClusGrpMemb( ICFSecSecClusGrpMembObj Obj ) {
		ICFSecSecClusGrpMembObj obj = Obj;
		ICFSecSecClusGrpMemb rec = obj.getSecClusGrpMembRec();
		schema.getCFSecBackingStore().getTableSecClusGrpMemb().createSecClusGrpMemb(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpMembObj readSecClusGrpMemb( ICFSecSecClusGrpMembPKey pkey ) {
		return( readSecClusGrpMemb( pkey, false ) );
	}

	@Override
	public ICFSecSecClusGrpMembObj readSecClusGrpMemb( ICFSecSecClusGrpMembPKey pkey, boolean forceRead ) {
		ICFSecSecClusGrpMembObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecClusGrpMemb readRec = schema.getCFSecBackingStore().getTableSecClusGrpMemb().readDerivedByIdIdx( null,
						pkey.getRequiredSecClusGrpId(),
						pkey.getRequiredLoginId() );
			if( readRec != null ) {
				obj = schema.getSecClusGrpMembTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecClusGrpMembObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpMembObj readSecClusGrpMemb( ICFLibKeyHash256 SecClusGrpId,
		String LoginId ) {
		return( readSecClusGrpMemb( SecClusGrpId,
			LoginId, false ) );
	}

	@Override
	public ICFSecSecClusGrpMembObj readSecClusGrpMemb( ICFLibKeyHash256 SecClusGrpId,
		String LoginId, boolean forceRead ) {
		ICFSecSecClusGrpMembObj obj = null;
		ICFSecSecClusGrpMemb readRec = schema.getCFSecBackingStore().getTableSecClusGrpMemb().readDerivedByIdIdx( null,
			SecClusGrpId,
			LoginId );
		if( readRec != null ) {
				obj = schema.getSecClusGrpMembTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecClusGrpMembObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusGrpMembObj readCachedSecClusGrpMemb( ICFSecSecClusGrpMembPKey pkey ) {
		ICFSecSecClusGrpMembObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecClusGrpMemb( ICFSecSecClusGrpMembObj obj )
	{
		final String S_ProcName = "CFSecSecClusGrpMembTableObj.reallyDeepDisposeSecClusGrpMemb() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecClusGrpMembPKey pkey = obj.getPKey();
		ICFSecSecClusGrpMembObj existing = readCachedSecClusGrpMemb( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecClusGrpMembByClusGrpIdxKey keyClusGrpIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByClusGrpIdxKey();
		keyClusGrpIdx.setRequiredSecClusGrpId( existing.getRequiredSecClusGrpId() );

		ICFSecSecClusGrpMembByLoginIdxKey keyLoginIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByLoginIdxKey();
		keyLoginIdx.setRequiredLoginId( existing.getRequiredLoginId() );



		if( indexByClusGrpIdx != null ) {
			if( indexByClusGrpIdx.containsKey( keyClusGrpIdx ) ) {
				indexByClusGrpIdx.get( keyClusGrpIdx ).remove( pkey );
				if( indexByClusGrpIdx.get( keyClusGrpIdx ).size() <= 0 ) {
					indexByClusGrpIdx.remove( keyClusGrpIdx );
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
	public void deepDisposeSecClusGrpMemb( ICFSecSecClusGrpMembPKey pkey ) {
		ICFSecSecClusGrpMembObj obj = readCachedSecClusGrpMemb( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecClusGrpMembObj lockSecClusGrpMemb( ICFSecSecClusGrpMembPKey pkey ) {
		ICFSecSecClusGrpMembObj locked = null;
		ICFSecSecClusGrpMemb lockRec = schema.getCFSecBackingStore().getTableSecClusGrpMemb().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecClusGrpMembTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecClusGrpMembObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecClusGrpMemb", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readAllSecClusGrpMemb() {
		return( readAllSecClusGrpMemb( false ) );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readAllSecClusGrpMemb( boolean forceRead ) {
		final String S_ProcName = "readAllSecClusGrpMemb";
		if( ( allSecClusGrpMemb == null ) || forceRead ) {
			Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> map = new HashMap<ICFSecSecClusGrpMembPKey,ICFSecSecClusGrpMembObj>();
			allSecClusGrpMemb = map;
			ICFSecSecClusGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecClusGrpMemb().readAllDerived( null );
			ICFSecSecClusGrpMemb rec;
			ICFSecSecClusGrpMembObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpMembObj realised = (ICFSecSecClusGrpMembObj)obj.realise();
			}
		}
		int len = allSecClusGrpMemb.size();
		ICFSecSecClusGrpMembObj arr[] = new ICFSecSecClusGrpMembObj[len];
		Iterator<ICFSecSecClusGrpMembObj> valIter = allSecClusGrpMemb.values().iterator();
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
		ArrayList<ICFSecSecClusGrpMembObj> arrayList = new ArrayList<ICFSecSecClusGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpMembObj> cmp = new Comparator<ICFSecSecClusGrpMembObj>() {
			@Override
			public int compare( ICFSecSecClusGrpMembObj lhs, ICFSecSecClusGrpMembObj rhs ) {
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
					ICFSecSecClusGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readCachedAllSecClusGrpMemb() {
		final String S_ProcName = "readCachedAllSecClusGrpMemb";
		ArrayList<ICFSecSecClusGrpMembObj> arrayList = new ArrayList<ICFSecSecClusGrpMembObj>();
		if( allSecClusGrpMemb != null ) {
			int len = allSecClusGrpMemb.size();
			ICFSecSecClusGrpMembObj arr[] = new ICFSecSecClusGrpMembObj[len];
			Iterator<ICFSecSecClusGrpMembObj> valIter = allSecClusGrpMemb.values().iterator();
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
		Comparator<ICFSecSecClusGrpMembObj> cmp = new Comparator<ICFSecSecClusGrpMembObj>() {
			public int compare( ICFSecSecClusGrpMembObj lhs, ICFSecSecClusGrpMembObj rhs ) {
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
					ICFSecSecClusGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecClusGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecClusGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecClusGrpMembObj> pageAllSecClusGrpMemb(ICFLibKeyHash256 priorSecClusGrpId,
		String priorLoginId )
	{
		final String S_ProcName = "pageAllSecClusGrpMemb";
		Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> map = new HashMap<ICFSecSecClusGrpMembPKey,ICFSecSecClusGrpMembObj>();
		ICFSecSecClusGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecClusGrpMemb().pageAllRec( null,
			priorSecClusGrpId,
			priorLoginId );
		ICFSecSecClusGrpMemb rec;
		ICFSecSecClusGrpMembObj obj;
		ICFSecSecClusGrpMembObj realised;
		ArrayList<ICFSecSecClusGrpMembObj> arrayList = new ArrayList<ICFSecSecClusGrpMembObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecClusGrpMembObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecClusGrpMembObj readSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId )
	{
		return( readSecClusGrpMembByIdIdx( SecClusGrpId,
			LoginId,
			false ) );
	}

	@Override
	public ICFSecSecClusGrpMembObj readSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId, boolean forceRead )
	{
		ICFSecSecClusGrpMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newPKey();
		pkey.setRequiredSecClusGrpId( SecClusGrpId );
		pkey.setRequiredLoginId( LoginId );
		ICFSecSecClusGrpMembObj obj = readSecClusGrpMemb( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId )
	{
		return( readSecClusGrpMembByClusGrpIdx( SecClusGrpId,
			false ) );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusGrpMembByClusGrpIdx";
		ICFSecSecClusGrpMembByClusGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByClusGrpIdxKey();
		key.setRequiredSecClusGrpId( SecClusGrpId );
		Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> dict;
		if( indexByClusGrpIdx == null ) {
			indexByClusGrpIdx = new HashMap< ICFSecSecClusGrpMembByClusGrpIdxKey,
				Map< ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > >();
		}
		if( ( ! forceRead ) && indexByClusGrpIdx.containsKey( key ) ) {
			dict = indexByClusGrpIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj>();
			ICFSecSecClusGrpMembObj obj;
			ICFSecSecClusGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecClusGrpMemb().readDerivedByClusGrpIdx( null,
				SecClusGrpId );
			ICFSecSecClusGrpMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusGrpMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpMembObj realised = (ICFSecSecClusGrpMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClusGrpIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusGrpMembObj arr[] = new ICFSecSecClusGrpMembObj[len];
		Iterator<ICFSecSecClusGrpMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecClusGrpMembObj> arrayList = new ArrayList<ICFSecSecClusGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpMembObj> cmp = new Comparator<ICFSecSecClusGrpMembObj>() {
			@Override
			public int compare( ICFSecSecClusGrpMembObj lhs, ICFSecSecClusGrpMembObj rhs ) {
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
					ICFSecSecClusGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readSecClusGrpMembByLoginIdx( String LoginId )
	{
		return( readSecClusGrpMembByLoginIdx( LoginId,
			false ) );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readSecClusGrpMembByLoginIdx( String LoginId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusGrpMembByLoginIdx";
		ICFSecSecClusGrpMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> dict;
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecClusGrpMembByLoginIdxKey,
				Map< ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > >();
		}
		if( ( ! forceRead ) && indexByLoginIdx.containsKey( key ) ) {
			dict = indexByLoginIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj>();
			ICFSecSecClusGrpMembObj obj;
			ICFSecSecClusGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecClusGrpMemb().readDerivedByLoginIdx( null,
				LoginId );
			ICFSecSecClusGrpMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusGrpMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusGrpMembObj realised = (ICFSecSecClusGrpMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByLoginIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusGrpMembObj arr[] = new ICFSecSecClusGrpMembObj[len];
		Iterator<ICFSecSecClusGrpMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecClusGrpMembObj> arrayList = new ArrayList<ICFSecSecClusGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusGrpMembObj> cmp = new Comparator<ICFSecSecClusGrpMembObj>() {
			@Override
			public int compare( ICFSecSecClusGrpMembObj lhs, ICFSecSecClusGrpMembObj rhs ) {
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
					ICFSecSecClusGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecClusGrpMembObj readCachedSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId )
	{
		ICFSecSecClusGrpMembObj obj = null;
		ICFSecSecClusGrpMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newPKey();
		pkey.setRequiredSecClusGrpId( SecClusGrpId );
		pkey.setRequiredLoginId( LoginId );
		pkey.setRequiredSecClusGrpId( SecClusGrpId );
		pkey.setRequiredLoginId( LoginId );
		obj = readCachedSecClusGrpMemb( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readCachedSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId )
	{
		final String S_ProcName = "readCachedSecClusGrpMembByClusGrpIdx";
		ICFSecSecClusGrpMembByClusGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByClusGrpIdxKey();
		key.setRequiredSecClusGrpId( SecClusGrpId );
		ArrayList<ICFSecSecClusGrpMembObj> arrayList = new ArrayList<ICFSecSecClusGrpMembObj>();
		if( indexByClusGrpIdx != null ) {
			Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> dict;
			if( indexByClusGrpIdx.containsKey( key ) ) {
				dict = indexByClusGrpIdx.get( key );
				int len = dict.size();
				ICFSecSecClusGrpMembObj arr[] = new ICFSecSecClusGrpMembObj[len];
				Iterator<ICFSecSecClusGrpMembObj> valIter = dict.values().iterator();
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
			ICFSecSecClusGrpMembObj obj;
			Iterator<ICFSecSecClusGrpMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusGrpMembObj> cmp = new Comparator<ICFSecSecClusGrpMembObj>() {
			@Override
			public int compare( ICFSecSecClusGrpMembObj lhs, ICFSecSecClusGrpMembObj rhs ) {
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
					ICFSecSecClusGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> readCachedSecClusGrpMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "readCachedSecClusGrpMembByLoginIdx";
		ICFSecSecClusGrpMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		ArrayList<ICFSecSecClusGrpMembObj> arrayList = new ArrayList<ICFSecSecClusGrpMembObj>();
		if( indexByLoginIdx != null ) {
			Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> dict;
			if( indexByLoginIdx.containsKey( key ) ) {
				dict = indexByLoginIdx.get( key );
				int len = dict.size();
				ICFSecSecClusGrpMembObj arr[] = new ICFSecSecClusGrpMembObj[len];
				Iterator<ICFSecSecClusGrpMembObj> valIter = dict.values().iterator();
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
			ICFSecSecClusGrpMembObj obj;
			Iterator<ICFSecSecClusGrpMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusGrpMembObj> cmp = new Comparator<ICFSecSecClusGrpMembObj>() {
			@Override
			public int compare( ICFSecSecClusGrpMembObj lhs, ICFSecSecClusGrpMembObj rhs ) {
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
					ICFSecSecClusGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecClusGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId )
	{
		ICFSecSecClusGrpMembObj obj = readCachedSecClusGrpMembByIdIdx( SecClusGrpId,
				LoginId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId )
	{
		final String S_ProcName = "deepDisposeSecClusGrpMembByClusGrpIdx";
		ICFSecSecClusGrpMembObj obj;
		List<ICFSecSecClusGrpMembObj> arrayList = readCachedSecClusGrpMembByClusGrpIdx( SecClusGrpId );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusGrpMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecClusGrpMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "deepDisposeSecClusGrpMembByLoginIdx";
		ICFSecSecClusGrpMembObj obj;
		List<ICFSecSecClusGrpMembObj> arrayList = readCachedSecClusGrpMembByLoginIdx( LoginId );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusGrpMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecClusGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ClusGrpIdx key attributes.
	 *
	 *	@param	SecClusGrpId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecClusGrpMembObj> pageSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId,
		ICFLibKeyHash256 priorSecClusGrpId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecClusGrpMembByClusGrpIdx";
		ICFSecSecClusGrpMembByClusGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByClusGrpIdxKey();
		key.setRequiredSecClusGrpId( SecClusGrpId );
		List<ICFSecSecClusGrpMembObj> retList = new LinkedList<ICFSecSecClusGrpMembObj>();
		ICFSecSecClusGrpMembObj obj;
		ICFSecSecClusGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecClusGrpMemb().pageRecByClusGrpIdx( null,
				SecClusGrpId,
			priorSecClusGrpId,
			priorLoginId );
		ICFSecSecClusGrpMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecClusGrpMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecClusGrpMembObj realised = (ICFSecSecClusGrpMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecClusGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecClusGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecClusGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecClusGrpMembObj> pageSecClusGrpMembByLoginIdx( String LoginId,
		ICFLibKeyHash256 priorSecClusGrpId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecClusGrpMembByLoginIdx";
		ICFSecSecClusGrpMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		List<ICFSecSecClusGrpMembObj> retList = new LinkedList<ICFSecSecClusGrpMembObj>();
		ICFSecSecClusGrpMembObj obj;
		ICFSecSecClusGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecClusGrpMemb().pageRecByLoginIdx( null,
				LoginId,
			priorSecClusGrpId,
			priorLoginId );
		ICFSecSecClusGrpMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecClusGrpMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecClusGrpMembObj realised = (ICFSecSecClusGrpMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecClusGrpMembObj updateSecClusGrpMemb( ICFSecSecClusGrpMembObj Obj ) {
		ICFSecSecClusGrpMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusGrpMemb().updateSecClusGrpMemb( null,
			Obj.getSecClusGrpMembRec() );
		obj = (ICFSecSecClusGrpMembObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecClusGrpMemb( ICFSecSecClusGrpMembObj Obj ) {
		ICFSecSecClusGrpMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusGrpMemb().deleteSecClusGrpMemb( null,
			obj.getSecClusGrpMembRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecClusGrpMembByIdIdx( ICFLibKeyHash256 SecClusGrpId,
		String LoginId )
	{
		ICFSecSecClusGrpMembObj obj = readSecClusGrpMemb(SecClusGrpId,
				LoginId);
		if( obj != null ) {
			ICFSecSecClusGrpMembEditObj editObj = (ICFSecSecClusGrpMembEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecClusGrpMembEditObj)obj.beginEdit();
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
		deepDisposeSecClusGrpMembByIdIdx( SecClusGrpId,
				LoginId );
	}

	@Override
	public void deleteSecClusGrpMembByClusGrpIdx( ICFLibKeyHash256 SecClusGrpId )
	{
		ICFSecSecClusGrpMembByClusGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByClusGrpIdxKey();
		key.setRequiredSecClusGrpId( SecClusGrpId );
		if( indexByClusGrpIdx == null ) {
			indexByClusGrpIdx = new HashMap< ICFSecSecClusGrpMembByClusGrpIdxKey,
				Map< ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > >();
		}
		if( indexByClusGrpIdx.containsKey( key ) ) {
			Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> dict = indexByClusGrpIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusGrpMemb().deleteSecClusGrpMembByClusGrpIdx( null,
				SecClusGrpId );
			Iterator<ICFSecSecClusGrpMembObj> iter = dict.values().iterator();
			ICFSecSecClusGrpMembObj obj;
			List<ICFSecSecClusGrpMembObj> toForget = new LinkedList<ICFSecSecClusGrpMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClusGrpIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusGrpMemb().deleteSecClusGrpMembByClusGrpIdx( null,
				SecClusGrpId );
		}
		deepDisposeSecClusGrpMembByClusGrpIdx( SecClusGrpId );
	}

	@Override
	public void deleteSecClusGrpMembByLoginIdx( String LoginId )
	{
		ICFSecSecClusGrpMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecClusGrpMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecClusGrpMembByLoginIdxKey,
				Map< ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj > >();
		}
		if( indexByLoginIdx.containsKey( key ) ) {
			Map<ICFSecSecClusGrpMembPKey, ICFSecSecClusGrpMembObj> dict = indexByLoginIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusGrpMemb().deleteSecClusGrpMembByLoginIdx( null,
				LoginId );
			Iterator<ICFSecSecClusGrpMembObj> iter = dict.values().iterator();
			ICFSecSecClusGrpMembObj obj;
			List<ICFSecSecClusGrpMembObj> toForget = new LinkedList<ICFSecSecClusGrpMembObj>();
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
			schema.getCFSecBackingStore().getTableSecClusGrpMemb().deleteSecClusGrpMembByLoginIdx( null,
				LoginId );
		}
		deepDisposeSecClusGrpMembByLoginIdx( LoginId );
	}
}