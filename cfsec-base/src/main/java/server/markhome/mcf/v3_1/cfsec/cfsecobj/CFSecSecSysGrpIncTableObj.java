// Description: Java 25 Table Object implementation for SecSysGrpInc.

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

public class CFSecSecSysGrpIncTableObj
	implements ICFSecSecSysGrpIncTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecSysGrpInc.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecSysGrpInc.CLASS_CODE;
	private Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> members;
	private Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> allSecSysGrpInc;
	private Map< ICFSecSecSysGrpIncBySysGrpIdxKey,
		Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > > indexBySysGrpIdx;
	private Map< ICFSecSecSysGrpIncByNameIdxKey,
		Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > > indexByNameIdx;
	public static String TABLE_NAME = "SecSysGrpInc";
	public static String TABLE_DBNAME = "secsysgrpinc";

	public CFSecSecSysGrpIncTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj>();
		allSecSysGrpInc = null;
		indexBySysGrpIdx = null;
		indexByNameIdx = null;
	}

	public CFSecSecSysGrpIncTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj>();
		allSecSysGrpInc = null;
		indexBySysGrpIdx = null;
		indexByNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecSysGrpIncTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecSysGrpIncTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecSysGrpInc = null;
		indexBySysGrpIdx = null;
		indexByNameIdx = null;
		List<ICFSecSecSysGrpIncObj> toForget = new LinkedList<ICFSecSecSysGrpIncObj>();
		ICFSecSecSysGrpIncObj cur = null;
		Iterator<ICFSecSecSysGrpIncObj> iter = members.values().iterator();
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
	 *	CFSecSecSysGrpIncObj.
	 */
	@Override
	public ICFSecSecSysGrpIncObj newInstance() {
		ICFSecSecSysGrpIncObj inst = new CFSecSecSysGrpIncObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecSysGrpIncObj.
	 */
	@Override
	public ICFSecSecSysGrpIncEditObj newEditInstance( ICFSecSecSysGrpIncObj orig ) {
		ICFSecSecSysGrpIncEditObj edit = new CFSecSecSysGrpIncEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecSysGrpIncObj realiseSecSysGrpInc( ICFSecSecSysGrpIncObj Obj ) {
		ICFSecSecSysGrpIncObj obj = Obj;
		ICFSecSecSysGrpIncPKey pkey = obj.getPKey();
		ICFSecSecSysGrpIncObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecSysGrpIncObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpIncBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.remove( keepObj.getPKey() );
					if( mapSysGrpIdx.size() <= 0 ) {
						indexBySysGrpIdx.remove( keySysGrpIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecSysGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpIncBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecSysGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecSysGrpInc != null ) {
				allSecSysGrpInc.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecSysGrpInc != null ) {
				allSecSysGrpInc.put( keepObj.getPKey(), keepObj );
			}

			if( indexBySysGrpIdx != null ) {
				ICFSecSecSysGrpIncBySysGrpIdxKey keySysGrpIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newBySysGrpIdxKey();
				keySysGrpIdx.setRequiredSecSysGrpId( keepObj.getRequiredSecSysGrpId() );
				Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > mapSysGrpIdx = indexBySysGrpIdx.get( keySysGrpIdx );
				if( mapSysGrpIdx != null ) {
					mapSysGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecSysGrpIncByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newByNameIdxKey();
				keyNameIdx.setRequiredInclName( keepObj.getRequiredInclName() );
				Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecSysGrpIncObj createSecSysGrpInc( ICFSecSecSysGrpIncObj Obj ) {
		ICFSecSecSysGrpIncObj obj = Obj;
		ICFSecSecSysGrpInc rec = obj.getSecSysGrpIncRec();
		schema.getCFSecBackingStore().getTableSecSysGrpInc().createSecSysGrpInc(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpIncObj readSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey ) {
		return( readSecSysGrpInc( pkey, false ) );
	}

	@Override
	public ICFSecSecSysGrpIncObj readSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey, boolean forceRead ) {
		ICFSecSecSysGrpIncObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecSysGrpInc readRec = schema.getCFSecBackingStore().getTableSecSysGrpInc().readDerivedByIdIdx( null,
						pkey.getRequiredSecSysGrpId(),
						pkey.getRequiredInclName() );
			if( readRec != null ) {
				obj = schema.getSecSysGrpIncTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecSysGrpIncObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpIncObj readSecSysGrpInc( ICFLibKeyHash256 SecSysGrpId,
		String InclName ) {
		return( readSecSysGrpInc( SecSysGrpId,
			InclName, false ) );
	}

	@Override
	public ICFSecSecSysGrpIncObj readSecSysGrpInc( ICFLibKeyHash256 SecSysGrpId,
		String InclName, boolean forceRead ) {
		ICFSecSecSysGrpIncObj obj = null;
		ICFSecSecSysGrpInc readRec = schema.getCFSecBackingStore().getTableSecSysGrpInc().readDerivedByIdIdx( null,
			SecSysGrpId,
			InclName );
		if( readRec != null ) {
				obj = schema.getSecSysGrpIncTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecSysGrpIncObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysGrpIncObj readCachedSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey ) {
		ICFSecSecSysGrpIncObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecSysGrpInc( ICFSecSecSysGrpIncObj obj )
	{
		final String S_ProcName = "CFSecSecSysGrpIncTableObj.reallyDeepDisposeSecSysGrpInc() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecSysGrpIncPKey pkey = obj.getPKey();
		ICFSecSecSysGrpIncObj existing = readCachedSecSysGrpInc( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecSysGrpIncBySysGrpIdxKey keySysGrpIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newBySysGrpIdxKey();
		keySysGrpIdx.setRequiredSecSysGrpId( existing.getRequiredSecSysGrpId() );

		ICFSecSecSysGrpIncByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newByNameIdxKey();
		keyNameIdx.setRequiredInclName( existing.getRequiredInclName() );



		if( indexBySysGrpIdx != null ) {
			if( indexBySysGrpIdx.containsKey( keySysGrpIdx ) ) {
				indexBySysGrpIdx.get( keySysGrpIdx ).remove( pkey );
				if( indexBySysGrpIdx.get( keySysGrpIdx ).size() <= 0 ) {
					indexBySysGrpIdx.remove( keySysGrpIdx );
				}
			}
		}

		if( indexByNameIdx != null ) {
			if( indexByNameIdx.containsKey( keyNameIdx ) ) {
				indexByNameIdx.get( keyNameIdx ).remove( pkey );
				if( indexByNameIdx.get( keyNameIdx ).size() <= 0 ) {
					indexByNameIdx.remove( keyNameIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey ) {
		ICFSecSecSysGrpIncObj obj = readCachedSecSysGrpInc( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSysGrpIncObj lockSecSysGrpInc( ICFSecSecSysGrpIncPKey pkey ) {
		ICFSecSecSysGrpIncObj locked = null;
		ICFSecSecSysGrpInc lockRec = schema.getCFSecBackingStore().getTableSecSysGrpInc().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecSysGrpIncTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecSysGrpIncObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecSysGrpInc", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readAllSecSysGrpInc() {
		return( readAllSecSysGrpInc( false ) );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readAllSecSysGrpInc( boolean forceRead ) {
		final String S_ProcName = "readAllSecSysGrpInc";
		if( ( allSecSysGrpInc == null ) || forceRead ) {
			Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> map = new HashMap<ICFSecSecSysGrpIncPKey,ICFSecSecSysGrpIncObj>();
			allSecSysGrpInc = map;
			ICFSecSecSysGrpInc[] recList = schema.getCFSecBackingStore().getTableSecSysGrpInc().readAllDerived( null );
			ICFSecSecSysGrpInc rec;
			ICFSecSecSysGrpIncObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpIncObj realised = (ICFSecSecSysGrpIncObj)obj.realise();
			}
		}
		int len = allSecSysGrpInc.size();
		ICFSecSecSysGrpIncObj arr[] = new ICFSecSecSysGrpIncObj[len];
		Iterator<ICFSecSecSysGrpIncObj> valIter = allSecSysGrpInc.values().iterator();
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
		ArrayList<ICFSecSecSysGrpIncObj> arrayList = new ArrayList<ICFSecSecSysGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpIncObj> cmp = new Comparator<ICFSecSecSysGrpIncObj>() {
			@Override
			public int compare( ICFSecSecSysGrpIncObj lhs, ICFSecSecSysGrpIncObj rhs ) {
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
					ICFSecSecSysGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readCachedAllSecSysGrpInc() {
		final String S_ProcName = "readCachedAllSecSysGrpInc";
		ArrayList<ICFSecSecSysGrpIncObj> arrayList = new ArrayList<ICFSecSecSysGrpIncObj>();
		if( allSecSysGrpInc != null ) {
			int len = allSecSysGrpInc.size();
			ICFSecSecSysGrpIncObj arr[] = new ICFSecSecSysGrpIncObj[len];
			Iterator<ICFSecSecSysGrpIncObj> valIter = allSecSysGrpInc.values().iterator();
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
		Comparator<ICFSecSecSysGrpIncObj> cmp = new Comparator<ICFSecSecSysGrpIncObj>() {
			public int compare( ICFSecSecSysGrpIncObj lhs, ICFSecSecSysGrpIncObj rhs ) {
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
					ICFSecSecSysGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecSysGrpInc-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysGrpIncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpIncObj> pageAllSecSysGrpInc(ICFLibKeyHash256 priorSecSysGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageAllSecSysGrpInc";
		Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> map = new HashMap<ICFSecSecSysGrpIncPKey,ICFSecSecSysGrpIncObj>();
		ICFSecSecSysGrpInc[] recList = schema.getCFSecBackingStore().getTableSecSysGrpInc().pageAllRec( null,
			priorSecSysGrpId,
			priorInclName );
		ICFSecSecSysGrpInc rec;
		ICFSecSecSysGrpIncObj obj;
		ICFSecSecSysGrpIncObj realised;
		ArrayList<ICFSecSecSysGrpIncObj> arrayList = new ArrayList<ICFSecSecSysGrpIncObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecSysGrpIncObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecSysGrpIncObj readSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName )
	{
		return( readSecSysGrpIncByIdIdx( SecSysGrpId,
			InclName,
			false ) );
	}

	@Override
	public ICFSecSecSysGrpIncObj readSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName, boolean forceRead )
	{
		ICFSecSecSysGrpIncPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newPKey();
		pkey.setRequiredSecSysGrpId( SecSysGrpId );
		pkey.setRequiredInclName( InclName );
		ICFSecSecSysGrpIncObj obj = readSecSysGrpInc( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId )
	{
		return( readSecSysGrpIncBySysGrpIdx( SecSysGrpId,
			false ) );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysGrpIncBySysGrpIdx";
		ICFSecSecSysGrpIncBySysGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> dict;
		if( indexBySysGrpIdx == null ) {
			indexBySysGrpIdx = new HashMap< ICFSecSecSysGrpIncBySysGrpIdxKey,
				Map< ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > >();
		}
		if( ( ! forceRead ) && indexBySysGrpIdx.containsKey( key ) ) {
			dict = indexBySysGrpIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj>();
			ICFSecSecSysGrpIncObj obj;
			ICFSecSecSysGrpInc[] recList = schema.getCFSecBackingStore().getTableSecSysGrpInc().readDerivedBySysGrpIdx( null,
				SecSysGrpId );
			ICFSecSecSysGrpInc rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysGrpIncTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpIncObj realised = (ICFSecSecSysGrpIncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySysGrpIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysGrpIncObj arr[] = new ICFSecSecSysGrpIncObj[len];
		Iterator<ICFSecSecSysGrpIncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysGrpIncObj> arrayList = new ArrayList<ICFSecSecSysGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpIncObj> cmp = new Comparator<ICFSecSecSysGrpIncObj>() {
			@Override
			public int compare( ICFSecSecSysGrpIncObj lhs, ICFSecSecSysGrpIncObj rhs ) {
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
					ICFSecSecSysGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readSecSysGrpIncByNameIdx( String InclName )
	{
		return( readSecSysGrpIncByNameIdx( InclName,
			false ) );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readSecSysGrpIncByNameIdx( String InclName,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysGrpIncByNameIdx";
		ICFSecSecSysGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecSysGrpIncByNameIdxKey,
				Map< ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj>();
			ICFSecSecSysGrpIncObj obj;
			ICFSecSecSysGrpInc[] recList = schema.getCFSecBackingStore().getTableSecSysGrpInc().readDerivedByNameIdx( null,
				InclName );
			ICFSecSecSysGrpInc rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysGrpIncTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysGrpIncObj realised = (ICFSecSecSysGrpIncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysGrpIncObj arr[] = new ICFSecSecSysGrpIncObj[len];
		Iterator<ICFSecSecSysGrpIncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysGrpIncObj> arrayList = new ArrayList<ICFSecSecSysGrpIncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysGrpIncObj> cmp = new Comparator<ICFSecSecSysGrpIncObj>() {
			@Override
			public int compare( ICFSecSecSysGrpIncObj lhs, ICFSecSecSysGrpIncObj rhs ) {
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
					ICFSecSecSysGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysGrpIncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecSysGrpIncObj readCachedSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName )
	{
		ICFSecSecSysGrpIncObj obj = null;
		ICFSecSecSysGrpIncPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newPKey();
		pkey.setRequiredSecSysGrpId( SecSysGrpId );
		pkey.setRequiredInclName( InclName );
		pkey.setRequiredSecSysGrpId( SecSysGrpId );
		pkey.setRequiredInclName( InclName );
		obj = readCachedSecSysGrpInc( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readCachedSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId )
	{
		final String S_ProcName = "readCachedSecSysGrpIncBySysGrpIdx";
		ICFSecSecSysGrpIncBySysGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		ArrayList<ICFSecSecSysGrpIncObj> arrayList = new ArrayList<ICFSecSecSysGrpIncObj>();
		if( indexBySysGrpIdx != null ) {
			Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> dict;
			if( indexBySysGrpIdx.containsKey( key ) ) {
				dict = indexBySysGrpIdx.get( key );
				int len = dict.size();
				ICFSecSecSysGrpIncObj arr[] = new ICFSecSecSysGrpIncObj[len];
				Iterator<ICFSecSecSysGrpIncObj> valIter = dict.values().iterator();
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
			ICFSecSecSysGrpIncObj obj;
			Iterator<ICFSecSecSysGrpIncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysGrpIncObj> cmp = new Comparator<ICFSecSecSysGrpIncObj>() {
			@Override
			public int compare( ICFSecSecSysGrpIncObj lhs, ICFSecSecSysGrpIncObj rhs ) {
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
					ICFSecSecSysGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> readCachedSecSysGrpIncByNameIdx( String InclName )
	{
		final String S_ProcName = "readCachedSecSysGrpIncByNameIdx";
		ICFSecSecSysGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		ArrayList<ICFSecSecSysGrpIncObj> arrayList = new ArrayList<ICFSecSecSysGrpIncObj>();
		if( indexByNameIdx != null ) {
			Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecSysGrpIncObj arr[] = new ICFSecSecSysGrpIncObj[len];
				Iterator<ICFSecSecSysGrpIncObj> valIter = dict.values().iterator();
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
			ICFSecSecSysGrpIncObj obj;
			Iterator<ICFSecSecSysGrpIncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysGrpIncObj> cmp = new Comparator<ICFSecSecSysGrpIncObj>() {
			@Override
			public int compare( ICFSecSecSysGrpIncObj lhs, ICFSecSecSysGrpIncObj rhs ) {
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
					ICFSecSecSysGrpIncPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysGrpIncPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName )
	{
		ICFSecSecSysGrpIncObj obj = readCachedSecSysGrpIncByIdIdx( SecSysGrpId,
				InclName );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId )
	{
		final String S_ProcName = "deepDisposeSecSysGrpIncBySysGrpIdx";
		ICFSecSecSysGrpIncObj obj;
		List<ICFSecSecSysGrpIncObj> arrayList = readCachedSecSysGrpIncBySysGrpIdx( SecSysGrpId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysGrpIncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecSysGrpIncByNameIdx( String InclName )
	{
		final String S_ProcName = "deepDisposeSecSysGrpIncByNameIdx";
		ICFSecSecSysGrpIncObj obj;
		List<ICFSecSecSysGrpIncObj> arrayList = readCachedSecSysGrpIncByNameIdx( InclName );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysGrpIncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecSysGrpInc-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysGrpIdx key attributes.
	 *
	 *	@param	SecSysGrpId	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpInc-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpIncObj> pageSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId,
		ICFLibKeyHash256 priorSecSysGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageSecSysGrpIncBySysGrpIdx";
		ICFSecSecSysGrpIncBySysGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		List<ICFSecSecSysGrpIncObj> retList = new LinkedList<ICFSecSecSysGrpIncObj>();
		ICFSecSecSysGrpIncObj obj;
		ICFSecSecSysGrpInc[] recList = schema.getCFSecBackingStore().getTableSecSysGrpInc().pageRecBySysGrpIdx( null,
				SecSysGrpId,
			priorSecSysGrpId,
			priorInclName );
		ICFSecSecSysGrpInc rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysGrpIncTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysGrpIncObj realised = (ICFSecSecSysGrpIncObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecSysGrpInc-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NameIdx key attributes.
	 *
	 *	@param	InclName	The SecSysGrpInc key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysGrpInc-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysGrpIncObj> pageSecSysGrpIncByNameIdx( String InclName,
		ICFLibKeyHash256 priorSecSysGrpId,
		String priorInclName )
	{
		final String S_ProcName = "pageSecSysGrpIncByNameIdx";
		ICFSecSecSysGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		List<ICFSecSecSysGrpIncObj> retList = new LinkedList<ICFSecSecSysGrpIncObj>();
		ICFSecSecSysGrpIncObj obj;
		ICFSecSecSysGrpInc[] recList = schema.getCFSecBackingStore().getTableSecSysGrpInc().pageRecByNameIdx( null,
				InclName,
			priorSecSysGrpId,
			priorInclName );
		ICFSecSecSysGrpInc rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysGrpIncTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysGrpIncObj realised = (ICFSecSecSysGrpIncObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecSysGrpIncObj updateSecSysGrpInc( ICFSecSecSysGrpIncObj Obj ) {
		ICFSecSecSysGrpIncObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysGrpInc().updateSecSysGrpInc( null,
			Obj.getSecSysGrpIncRec() );
		obj = (ICFSecSecSysGrpIncObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecSysGrpInc( ICFSecSecSysGrpIncObj Obj ) {
		ICFSecSecSysGrpIncObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysGrpInc().deleteSecSysGrpInc( null,
			obj.getSecSysGrpIncRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecSysGrpIncByIdIdx( ICFLibKeyHash256 SecSysGrpId,
		String InclName )
	{
		ICFSecSecSysGrpIncObj obj = readSecSysGrpInc(SecSysGrpId,
				InclName);
		if( obj != null ) {
			ICFSecSecSysGrpIncEditObj editObj = (ICFSecSecSysGrpIncEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecSysGrpIncEditObj)obj.beginEdit();
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
		deepDisposeSecSysGrpIncByIdIdx( SecSysGrpId,
				InclName );
	}

	@Override
	public void deleteSecSysGrpIncBySysGrpIdx( ICFLibKeyHash256 SecSysGrpId )
	{
		ICFSecSecSysGrpIncBySysGrpIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newBySysGrpIdxKey();
		key.setRequiredSecSysGrpId( SecSysGrpId );
		if( indexBySysGrpIdx == null ) {
			indexBySysGrpIdx = new HashMap< ICFSecSecSysGrpIncBySysGrpIdxKey,
				Map< ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > >();
		}
		if( indexBySysGrpIdx.containsKey( key ) ) {
			Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> dict = indexBySysGrpIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysGrpInc().deleteSecSysGrpIncBySysGrpIdx( null,
				SecSysGrpId );
			Iterator<ICFSecSecSysGrpIncObj> iter = dict.values().iterator();
			ICFSecSecSysGrpIncObj obj;
			List<ICFSecSecSysGrpIncObj> toForget = new LinkedList<ICFSecSecSysGrpIncObj>();
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
			schema.getCFSecBackingStore().getTableSecSysGrpInc().deleteSecSysGrpIncBySysGrpIdx( null,
				SecSysGrpId );
		}
		deepDisposeSecSysGrpIncBySysGrpIdx( SecSysGrpId );
	}

	@Override
	public void deleteSecSysGrpIncByNameIdx( String InclName )
	{
		ICFSecSecSysGrpIncByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysGrpInc().newByNameIdxKey();
		key.setRequiredInclName( InclName );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecSysGrpIncByNameIdxKey,
				Map< ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<ICFSecSecSysGrpIncPKey, ICFSecSecSysGrpIncObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysGrpInc().deleteSecSysGrpIncByNameIdx( null,
				InclName );
			Iterator<ICFSecSecSysGrpIncObj> iter = dict.values().iterator();
			ICFSecSecSysGrpIncObj obj;
			List<ICFSecSecSysGrpIncObj> toForget = new LinkedList<ICFSecSecSysGrpIncObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNameIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysGrpInc().deleteSecSysGrpIncByNameIdx( null,
				InclName );
		}
		deepDisposeSecSysGrpIncByNameIdx( InclName );
	}
}