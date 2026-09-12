// Description: Java 25 Table Object implementation for SecSysGrpMemb.

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

public class CFSecSecSysGrpMembTableObj
	implements ICFSecSecSysGrpMembTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecSysGrpMemb.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecSysGrpMemb.CLASS_CODE;
	private Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> members;
	private Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> allSecSysGrpMemb;
	private Map< ICFSecSecSysGrpMembBySysGrpIdxKey,
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > > indexBySysGrpIdx;
	private Map< ICFSecSecSysGrpMembByLoginIdxKey,
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > > indexByLoginIdx;
	public static String TABLE_NAME = "SecSysGrpMemb";
	public static String TABLE_DBNAME = "secsysgrpmemb";

	public CFSecSecSysGrpMembTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj>();
		allSecSysGrpMemb = null;
		indexBySysGrpIdx = null;
		indexByLoginIdx = null;
	}

	public CFSecSecSysGrpMembTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj>();
		allSecSysGrpMemb = null;
		indexBySysGrpIdx = null;
		indexByLoginIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecSysGrpMembTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecSysGrpMembTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecSysGrpMemb = null;
		indexBySysGrpIdx = null;
		indexByLoginIdx = null;
		List<ICFSecSecSysGrpMembObj> toForget = new LinkedList<ICFSecSecSysGrpMembObj>();
		ICFSecSecSysGrpMembObj cur = null;
		Iterator<ICFSecSecSysGrpMembObj> iter = members.values().iterator();
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
	 *	CFSecSecSysGrpMembObj.
	 */
	@Override
	public ICFSecSecSysGrpMembObj newInstance() {
		ICFSecSecSysGrpMembObj inst = new CFSecSecSysGrpMembObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecSysGrpMembObj.
	 */
	@Override
	public ICFSecSecSysGrpMembEditObj newEditInstance( ICFSecSecSysGrpMembObj orig ) {
		ICFSecSecSysGrpMembEditObj edit = new CFSecSecSysGrpMembEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecSysGrpMembObj realiseSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj ) {
		ICFSecSecSysGrpMembObj obj = Obj;
		ICFSecSecSysGrpMembPKey pkey = obj.getPKey();
		ICFSecSecSysGrpMembObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecSysGrpMembObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpMembBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.remove( keepObj.getPKey() );
					if( mapSysGrpIdx.size() <= 0 ) {
						indexBySysGrpIdx.remove( keySysGrpIdx );
					}
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecSysGrpMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.remove( keepObj.getPKey() );
					if( mapLoginIdx.size() <= 0 ) {
						indexByLoginIdx.remove( keyLoginIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpMembBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecSysGrpMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecSysGrpMemb != null ) {
				allSecSysGrpMemb.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecSysGrpMemb != null ) {
				allSecSysGrpMemb.put( keepObj.getPKey(), keepObj );
			}

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpMembBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecSysGrpMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecSysGrpMembObj createSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj ) {
		ICFSecSecSysGrpMembObj obj = Obj;
		ICFSecSecSysGrpMemb rec = obj.getSecSysGrpMembRec();
		schema.getCFSecBackingStore().getTableSecSysGrpMemb().createSecSysGrpMemb(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey ) {
		return( readSecSysGrpMemb( pkey, false ) );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey, boolean forceRead ) {
		ICFSecSecSysGrpMembObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecSysGrpMemb readRec = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedByIdIdx( null,
						pkey.getRequiredSecSysGrpId(),
						pkey.getRequiredLoginId() );
			if( readRec != null ) {
				obj = schema.getSecSysGrpMembTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecSysGrpMembObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFLibKeyHash256 SecSysGrpId,
		String LoginId ) {
		return( readSecSysGrpMemb( SecSysGrpId,
			LoginId, false ) );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMemb( ICFLibKeyHash256 SecSysGrpId,
		String LoginId, boolean forceRead ) {
		ICFSecSecSysGrpMembObj obj = null;
		ICFSecSecSysGrpMemb readRec = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedByIdIdx( null,
			SecSysGrpId,
			LoginId );
		if( readRec != null ) {
				obj = schema.getSecSysGrpMembTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecSysGrpMembObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpMembObj readCachedSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey ) {
		ICFSecSecSysGrpMembObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecSysGrpMemb( ICFSecSecSysGrpMembObj obj )
	{
		final String S_ProcName = "CFSecSecSysGrpMembTableObj.reallyDeepDisposeSecSysGrpMemb() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecSysGrpMembPKey pkey = obj.getPKey();
		ICFSecSecSysGrpMembObj existing = readCachedSecSysGrpMemb( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecSysGrpMembBySysGrpIdxKey keySysGrpIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		keySysGrpIdx.setRequiredSecSysGrpId( existing.getRequiredSecSysGrpId() );

		ICFSecSecSysGrpMembByLoginIdxKey keyLoginIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newByLoginIdxKey();
		keyLoginIdx.setRequiredLoginId( existing.getRequiredLoginId() );



		if( indexBySysGrpIdx != null ) {
			if( indexBySysGrpIdx.containsKey( keySysGrpIdx ) ) {
				indexBySysGrpIdx.get( keySysGrpIdx ).remove( pkey );
				if( indexBySysGrpIdx.get( keySysGrpIdx ).size() <= 0 ) {
					indexBySysGrpIdx.remove( keySysGrpIdx );
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
	public void deepDisposeSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey ) {
		ICFSecSecSysGrpMembObj obj = readCachedSecSysGrpMemb( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSysGrpMembObj lockSecSysGrpMemb( ICFSecSecSysGrpMembPKey pkey ) {
		ICFSecSecSysGrpMembObj locked = null;
		ICFSecSecSysGrpMemb lockRec = schema.getCFSecBackingStore().getTableSecSysGrpMemb().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecSysGrpMembTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecSysGrpMembObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecSysGrpMemb", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readAllSecSysGrpMemb() {
		return( readAllSecSysGrpMemb( false ) );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readAllSecSysGrpMemb( boolean forceRead ) {
		final String S_ProcName = "readAllSecSysGrpMemb";
		if( ( allSecSysGrpMemb == null ) || forceRead ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> map = new HashMap<ICFSecSecSysGrpMembPKey,ICFSecSecSysGrpMembObj>();
			allSecSysGrpMemb = map;
			ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readAllDerived( null );
			ICFSecSecSysGrpMemb rec;
			ICFSecSecSysGrpMembObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
			}
		}
		int len = allSecSysGrpMemb.size();
		ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
		Iterator<ICFSecSecSysGrpMembObj> valIter = allSecSysGrpMemb.values().iterator();
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
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readCachedAllSecSysGrpMemb() {
		final String S_ProcName = "readCachedAllSecSysGrpMemb";
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>();
		if( allSecSysGrpMemb != null ) {
			int len = allSecSysGrpMemb.size();
			ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
			Iterator<ICFSecSecSysGrpMembObj> valIter = allSecSysGrpMemb.values().iterator();
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
		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecSysGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpMembObj> pageAllSecSysGrpMemb(ICFLibKeyHash256 priorSecSysGrpId,
		String priorLoginId )
	{
		final String S_ProcName = "pageAllSecSysGrpMemb";
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> map = new HashMap<ICFSecSecSysGrpMembPKey,ICFSecSecSysGrpMembObj>();
		ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().pageAllRec( null,
			priorSecSysGrpId,
			priorLoginId );
		ICFSecSecSysGrpMemb rec;
		ICFSecSecSysGrpMembObj obj;
		ICFSecSecSysGrpMembObj realised;
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecSysGrpMembObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId )
	{
		return( readSecSysGrpMembByIdIdx( SecSysGrpId,
			LoginId,
			false ) );
	}

	@Override
	public ICFSecSecSysGrpMembObj readSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId, boolean forceRead )
	{
		ICFSecSecSysGrpMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newPKey();
		pkey.setRequiredSecSysGrpId( SecSysGrpId );
		pkey.setRequiredLoginId( LoginId );
		ICFSecSecSysGrpMembObj obj = readSecSysGrpMemb( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId )
	{
		return( readSecSysGrpMembBySysGrpIdx( SecSysGrpId,
			false ) );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysGrpMembBySysGrpIdx";
		ICFSecSecSysGrpMembBySysGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict;
		if( indexBySysGrpIdx == null ) {
			indexBySysGrpIdx = new HashMap< ICFSecSecSysGrpMembBySysGrpIdxKey,
				Map< ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > >();
		}
		if( ( ! forceRead ) && indexBySysGrpIdx.containsKey( key ) ) {
			dict = indexBySysGrpIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj>();
			ICFSecSecSysGrpMembObj obj;
			ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedBySysGrpIdx( null,
				SecSysGrpId );
			ICFSecSecSysGrpMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysGrpMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySysGrpIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
		Iterator<ICFSecSecSysGrpMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readSecSysGrpMembByLoginIdx( String LoginId )
	{
		return( readSecSysGrpMembByLoginIdx( LoginId,
			false ) );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readSecSysGrpMembByLoginIdx( String LoginId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysGrpMembByLoginIdx";
		ICFSecSecSysGrpMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict;
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecSysGrpMembByLoginIdxKey,
				Map< ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > >();
		}
		if( ( ! forceRead ) && indexByLoginIdx.containsKey( key ) ) {
			dict = indexByLoginIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj>();
			ICFSecSecSysGrpMembObj obj;
			ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedByLoginIdx( null,
				LoginId );
			ICFSecSecSysGrpMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysGrpMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByLoginIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
		Iterator<ICFSecSecSysGrpMembObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecSysGrpMembObj readCachedSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId )
	{
		ICFSecSecSysGrpMembObj obj = null;
		ICFSecSecSysGrpMembPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newPKey();
		pkey.setRequiredSecSysGrpId( SecSysGrpId );
		pkey.setRequiredLoginId( LoginId );
		pkey.setRequiredSecSysGrpId( SecSysGrpId );
		pkey.setRequiredLoginId( LoginId );
		obj = readCachedSecSysGrpMemb( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readCachedSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId )
	{
		final String S_ProcName = "readCachedSecSysGrpMembBySysGrpIdx";
		ICFSecSecSysGrpMembBySysGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>();
		if( indexBySysGrpIdx != null ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict;
			if( indexBySysGrpIdx.containsKey( key ) ) {
				dict = indexBySysGrpIdx.get( key );
				int len = dict.size();
				ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
				Iterator<ICFSecSecSysGrpMembObj> valIter = dict.values().iterator();
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
			ICFSecSecSysGrpMembObj obj;
			Iterator<ICFSecSecSysGrpMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> readCachedSecSysGrpMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "readCachedSecSysGrpMembByLoginIdx";
		ICFSecSecSysGrpMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		ArrayList<ICFSecSecSysGrpMembObj> arrayList = new ArrayList<ICFSecSecSysGrpMembObj>();
		if( indexByLoginIdx != null ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict;
			if( indexByLoginIdx.containsKey( key ) ) {
				dict = indexByLoginIdx.get( key );
				int len = dict.size();
				ICFSecSecSysGrpMembObj arr[] = new ICFSecSecSysGrpMembObj[len];
				Iterator<ICFSecSecSysGrpMembObj> valIter = dict.values().iterator();
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
			ICFSecSecSysGrpMembObj obj;
			Iterator<ICFSecSecSysGrpMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysGrpMembObj> cmp = new Comparator<ICFSecSecSysGrpMembObj>() {
			@Override
			public int compare( ICFSecSecSysGrpMembObj lhs, ICFSecSecSysGrpMembObj rhs ) {
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
					ICFSecSecSysGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId )
	{
		ICFSecSecSysGrpMembObj obj = readCachedSecSysGrpMembByIdIdx( SecSysGrpId,
				LoginId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId )
	{
		final String S_ProcName = "deepDisposeSecSysGrpMembBySysGrpIdx";
		ICFSecSecSysGrpMembObj obj;
		List<ICFSecSecSysGrpMembObj> arrayList = readCachedSecSysGrpMembBySysGrpIdx( SecSysGrpId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysGrpMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecSysGrpMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "deepDisposeSecSysGrpMembByLoginIdx";
		ICFSecSecSysGrpMembObj obj;
		List<ICFSecSecSysGrpMembObj> arrayList = readCachedSecSysGrpMembByLoginIdx( LoginId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysGrpMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysGrpIdx key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpMembObj> pageSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		ICFLibKeyHash256 priorSecSysGrpId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecSysGrpMembBySysGrpIdx";
		ICFSecSecSysGrpMembBySysGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		List<ICFSecSecSysGrpMembObj> retList = new LinkedList<ICFSecSecSysGrpMembObj>();
		ICFSecSecSysGrpMembObj obj;
		ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().pageRecBySysGrpIdx( null,
				SecSysGrpId,
			priorSecSysGrpId,
			priorLoginId );
		ICFSecSecSysGrpMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysGrpMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecSysGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpMembObj> pageSecSysGrpMembByLoginIdx( String LoginId,
		ICFLibKeyHash256 priorSecSysGrpId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecSysGrpMembByLoginIdx";
		ICFSecSecSysGrpMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		List<ICFSecSecSysGrpMembObj> retList = new LinkedList<ICFSecSecSysGrpMembObj>();
		ICFSecSecSysGrpMembObj obj;
		ICFSecSecSysGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecSysGrpMemb().pageRecByLoginIdx( null,
				LoginId,
			priorSecSysGrpId,
			priorLoginId );
		ICFSecSecSysGrpMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysGrpMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysGrpMembObj realised = (ICFSecSecSysGrpMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecSysGrpMembObj updateSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj ) {
		ICFSecSecSysGrpMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysGrpMemb().updateSecSysGrpMemb( null,
			Obj.getSecSysGrpMembRec() );
		obj = (ICFSecSecSysGrpMembObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecSysGrpMemb( ICFSecSecSysGrpMembObj Obj ) {
		ICFSecSecSysGrpMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMemb( null,
			obj.getSecSysGrpMembRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecSysGrpMembByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String LoginId )
	{
		ICFSecSecSysGrpMembObj obj = readSecSysGrpMemb(SecSysGrpId,
				LoginId);
		if( obj != null ) {
			ICFSecSecSysGrpMembEditObj editObj = (ICFSecSecSysGrpMembEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecSysGrpMembEditObj)obj.beginEdit();
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
		deepDisposeSecSysGrpMembByIdIdx( SecSysGrpId,
				LoginId );
	}

	@Override
	public void deleteSecSysGrpMembBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId )
	{
		ICFSecSecSysGrpMembBySysGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		if( indexBySysGrpIdx == null ) {
			indexBySysGrpIdx = new HashMap< ICFSecSecSysGrpMembBySysGrpIdxKey,
				Map< ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > >();
		}
		if( indexBySysGrpIdx.containsKey( key ) ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict = indexBySysGrpIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMembBySysGrpIdx( null,
				SecSysGrpId );
			Iterator<ICFSecSecSysGrpMembObj> iter = dict.values().iterator();
			ICFSecSecSysGrpMembObj obj;
			List<ICFSecSecSysGrpMembObj> toForget = new LinkedList<ICFSecSecSysGrpMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySysGrpIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMembBySysGrpIdx( null,
				SecSysGrpId );
		}
		deepDisposeSecSysGrpMembBySysGrpIdx( SecSysGrpId );
	}

	@Override
	public void deleteSecSysGrpMembByLoginIdx( String LoginId )
	{
		ICFSecSecSysGrpMembByLoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecSysGrpMembByLoginIdxKey,
				Map< ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj > >();
		}
		if( indexByLoginIdx.containsKey( key ) ) {
			Map<ICFSecSecSysGrpMembPKey, ICFSecSecSysGrpMembObj> dict = indexByLoginIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMembByLoginIdx( null,
				LoginId );
			Iterator<ICFSecSecSysGrpMembObj> iter = dict.values().iterator();
			ICFSecSecSysGrpMembObj obj;
			List<ICFSecSecSysGrpMembObj> toForget = new LinkedList<ICFSecSecSysGrpMembObj>();
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
			schema.getCFSecBackingStore().getTableSecSysGrpMemb().deleteSecSysGrpMembByLoginIdx( null,
				LoginId );
		}
		deepDisposeSecSysGrpMembByLoginIdx( LoginId );
	}
}