// Description: Java 25 Table Object implementation for SecUserPWHistory.

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

public class CFSecSecUserPWHistoryTableObj
	implements ICFSecSecUserPWHistoryTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecUserPWHistory.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecUserPWHistory.CLASS_CODE;
	private Map<ICFSecSecUserPWHistoryPKey, ICFSecSecUserPWHistoryObj> members;
	private Map<ICFSecSecUserPWHistoryPKey, ICFSecSecUserPWHistoryObj> allSecUserPWHistory;
	private Map< ICFSecSecUserPWHistoryByUserIdxKey,
		ICFSecSecUserPWHistoryObj > indexByUserIdx;
	private Map< ICFSecSecUserPWHistoryBySetStampIdxKey,
		ICFSecSecUserPWHistoryObj > indexBySetStampIdx;
	private Map< ICFSecSecUserPWHistoryByReplacedStampIdxKey,
		ICFSecSecUserPWHistoryObj > indexByReplacedStampIdx;
	public static String TABLE_NAME = "SecUserPWHistory";
	public static String TABLE_DBNAME = "secuserpwhistory";

	public CFSecSecUserPWHistoryTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecUserPWHistoryPKey, ICFSecSecUserPWHistoryObj>();
		allSecUserPWHistory = null;
		indexByUserIdx = null;
		indexBySetStampIdx = null;
		indexByReplacedStampIdx = null;
	}

	public CFSecSecUserPWHistoryTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecSecUserPWHistoryPKey, ICFSecSecUserPWHistoryObj>();
		allSecUserPWHistory = null;
		indexByUserIdx = null;
		indexBySetStampIdx = null;
		indexByReplacedStampIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecUserPWHistoryTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecUserPWHistoryTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecUserPWHistory = null;
		indexByUserIdx = null;
		indexBySetStampIdx = null;
		indexByReplacedStampIdx = null;
		List<ICFSecSecUserPWHistoryObj> toForget = new LinkedList<ICFSecSecUserPWHistoryObj>();
		ICFSecSecUserPWHistoryObj cur = null;
		Iterator<ICFSecSecUserPWHistoryObj> iter = members.values().iterator();
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
	 *	CFSecSecUserPWHistoryObj.
	 */
	@Override
	public ICFSecSecUserPWHistoryObj newInstance() {
		ICFSecSecUserPWHistoryObj inst = new CFSecSecUserPWHistoryObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecUserPWHistoryObj.
	 */
	@Override
	public ICFSecSecUserPWHistoryEditObj newEditInstance( ICFSecSecUserPWHistoryObj orig ) {
		ICFSecSecUserPWHistoryEditObj edit = new CFSecSecUserPWHistoryEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecUserPWHistoryObj realiseSecUserPWHistory( ICFSecSecUserPWHistoryObj Obj ) {
		ICFSecSecUserPWHistoryObj obj = Obj;
		ICFSecSecUserPWHistoryPKey pkey = obj.getPKey();
		ICFSecSecUserPWHistoryObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecUserPWHistoryObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUserIdx != null ) {
				ICFSecSecUserPWHistoryByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				indexByUserIdx.remove( keyUserIdx );
			}

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPWHistoryBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				indexBySetStampIdx.remove( keySetStampIdx );
			}

			if( indexByReplacedStampIdx != null ) {
				ICFSecSecUserPWHistoryByReplacedStampIdxKey keyReplacedStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByReplacedStampIdxKey();
				keyReplacedStampIdx.setRequiredPWReplacedStamp( keepObj.getRequiredPWReplacedStamp() );
				indexByReplacedStampIdx.remove( keyReplacedStampIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUserIdx != null ) {
				ICFSecSecUserPWHistoryByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				indexByUserIdx.put( keyUserIdx, keepObj );
			}

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPWHistoryBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				indexBySetStampIdx.put( keySetStampIdx, keepObj );
			}

			if( indexByReplacedStampIdx != null ) {
				ICFSecSecUserPWHistoryByReplacedStampIdxKey keyReplacedStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByReplacedStampIdxKey();
				keyReplacedStampIdx.setRequiredPWReplacedStamp( keepObj.getRequiredPWReplacedStamp() );
				indexByReplacedStampIdx.put( keyReplacedStampIdx, keepObj );
			}

			if( allSecUserPWHistory != null ) {
				allSecUserPWHistory.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecUserPWHistory != null ) {
				allSecUserPWHistory.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUserIdx != null ) {
				ICFSecSecUserPWHistoryByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				indexByUserIdx.put( keyUserIdx, keepObj );
			}

			if( indexBySetStampIdx != null ) {
				ICFSecSecUserPWHistoryBySetStampIdxKey keySetStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newBySetStampIdxKey();
				keySetStampIdx.setRequiredPWSetStamp( keepObj.getRequiredPWSetStamp() );
				indexBySetStampIdx.put( keySetStampIdx, keepObj );
			}

			if( indexByReplacedStampIdx != null ) {
				ICFSecSecUserPWHistoryByReplacedStampIdxKey keyReplacedStampIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByReplacedStampIdxKey();
				keyReplacedStampIdx.setRequiredPWReplacedStamp( keepObj.getRequiredPWReplacedStamp() );
				indexByReplacedStampIdx.put( keyReplacedStampIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj createSecUserPWHistory( ICFSecSecUserPWHistoryObj Obj ) {
		ICFSecSecUserPWHistoryObj obj = Obj;
		ICFSecSecUserPWHistory rec = obj.getSecUserPWHistoryRec();
		schema.getCFSecBackingStore().getTableSecUserPWHistory().createSecUserPWHistory(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistory( ICFSecSecUserPWHistoryPKey pkey ) {
		return( readSecUserPWHistory( pkey, false ) );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistory( ICFSecSecUserPWHistoryPKey pkey, boolean forceRead ) {
		ICFSecSecUserPWHistoryObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecUserPWHistory readRec = schema.getCFSecBackingStore().getTableSecUserPWHistory().readDerivedByIdIdx( null,
						pkey.getRequiredSecUserId(),
						pkey.getRequiredPWSetStamp() );
			if( readRec != null ) {
				obj = schema.getSecUserPWHistoryTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecUserPWHistoryObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistory( ICFLibKeyHash256 SecUserId,
		LocalDateTime PWSetStamp ) {
		return( readSecUserPWHistory( SecUserId,
			PWSetStamp, false ) );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistory( ICFLibKeyHash256 SecUserId,
		LocalDateTime PWSetStamp, boolean forceRead ) {
		ICFSecSecUserPWHistoryObj obj = null;
		ICFSecSecUserPWHistory readRec = schema.getCFSecBackingStore().getTableSecUserPWHistory().readDerivedByIdIdx( null,
			SecUserId,
			PWSetStamp );
		if( readRec != null ) {
				obj = schema.getSecUserPWHistoryTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecUserPWHistoryObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readCachedSecUserPWHistory( ICFSecSecUserPWHistoryPKey pkey ) {
		ICFSecSecUserPWHistoryObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecUserPWHistory( ICFSecSecUserPWHistoryObj obj )
	{
		final String S_ProcName = "CFSecSecUserPWHistoryTableObj.reallyDeepDisposeSecUserPWHistory() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecUserPWHistoryPKey pkey = obj.getPKey();
		ICFSecSecUserPWHistoryObj existing = readCachedSecUserPWHistory( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecUserPWHistoryByUserIdxKey keyUserIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByUserIdxKey();
		keyUserIdx.setRequiredSecUserId( existing.getRequiredSecUserId() );

		ICFSecSecUserPWHistoryBySetStampIdxKey keySetStampIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newBySetStampIdxKey();
		keySetStampIdx.setRequiredPWSetStamp( existing.getRequiredPWSetStamp() );

		ICFSecSecUserPWHistoryByReplacedStampIdxKey keyReplacedStampIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByReplacedStampIdxKey();
		keyReplacedStampIdx.setRequiredPWReplacedStamp( existing.getRequiredPWReplacedStamp() );



		if( indexByUserIdx != null ) {
			indexByUserIdx.remove( keyUserIdx );
		}

		if( indexBySetStampIdx != null ) {
			indexBySetStampIdx.remove( keySetStampIdx );
		}

		if( indexByReplacedStampIdx != null ) {
			indexByReplacedStampIdx.remove( keyReplacedStampIdx );
		}


	}
	@Override
	public void deepDisposeSecUserPWHistory( ICFSecSecUserPWHistoryPKey pkey ) {
		ICFSecSecUserPWHistoryObj obj = readCachedSecUserPWHistory( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecUserPWHistoryObj lockSecUserPWHistory( ICFSecSecUserPWHistoryPKey pkey ) {
		ICFSecSecUserPWHistoryObj locked = null;
		ICFSecSecUserPWHistory lockRec = schema.getCFSecBackingStore().getTableSecUserPWHistory().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecUserPWHistoryTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecUserPWHistoryObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecUserPWHistory", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecUserPWHistoryObj> readAllSecUserPWHistory() {
		return( readAllSecUserPWHistory( false ) );
	}

	@Override
	public List<ICFSecSecUserPWHistoryObj> readAllSecUserPWHistory( boolean forceRead ) {
		final String S_ProcName = "readAllSecUserPWHistory";
		if( ( allSecUserPWHistory == null ) || forceRead ) {
			Map<ICFSecSecUserPWHistoryPKey, ICFSecSecUserPWHistoryObj> map = new HashMap<ICFSecSecUserPWHistoryPKey,ICFSecSecUserPWHistoryObj>();
			allSecUserPWHistory = map;
			ICFSecSecUserPWHistory[] recList = schema.getCFSecBackingStore().getTableSecUserPWHistory().readAllDerived( null );
			ICFSecSecUserPWHistory rec;
			ICFSecSecUserPWHistoryObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserPWHistoryObj realised = (ICFSecSecUserPWHistoryObj)obj.realise();
			}
		}
		int len = allSecUserPWHistory.size();
		ICFSecSecUserPWHistoryObj arr[] = new ICFSecSecUserPWHistoryObj[len];
		Iterator<ICFSecSecUserPWHistoryObj> valIter = allSecUserPWHistory.values().iterator();
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
		ArrayList<ICFSecSecUserPWHistoryObj> arrayList = new ArrayList<ICFSecSecUserPWHistoryObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserPWHistoryObj> cmp = new Comparator<ICFSecSecUserPWHistoryObj>() {
			@Override
			public int compare( ICFSecSecUserPWHistoryObj lhs, ICFSecSecUserPWHistoryObj rhs ) {
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
					ICFSecSecUserPWHistoryPKey lhsPKey = lhs.getPKey();
					ICFSecSecUserPWHistoryPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecUserPWHistoryObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserPWHistoryObj> readCachedAllSecUserPWHistory() {
		final String S_ProcName = "readCachedAllSecUserPWHistory";
		ArrayList<ICFSecSecUserPWHistoryObj> arrayList = new ArrayList<ICFSecSecUserPWHistoryObj>();
		if( allSecUserPWHistory != null ) {
			int len = allSecUserPWHistory.size();
			ICFSecSecUserPWHistoryObj arr[] = new ICFSecSecUserPWHistoryObj[len];
			Iterator<ICFSecSecUserPWHistoryObj> valIter = allSecUserPWHistory.values().iterator();
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
		Comparator<ICFSecSecUserPWHistoryObj> cmp = new Comparator<ICFSecSecUserPWHistoryObj>() {
			public int compare( ICFSecSecUserPWHistoryObj lhs, ICFSecSecUserPWHistoryObj rhs ) {
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
					ICFSecSecUserPWHistoryPKey lhsPKey = lhs.getPKey();
					ICFSecSecUserPWHistoryPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecUserPWHistory-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserPWHistoryObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecUserPWHistoryObj> pageAllSecUserPWHistory(ICFLibKeyHash256 priorSecUserId,
		LocalDateTime priorPWSetStamp )
	{
		final String S_ProcName = "pageAllSecUserPWHistory";
		Map<ICFSecSecUserPWHistoryPKey, ICFSecSecUserPWHistoryObj> map = new HashMap<ICFSecSecUserPWHistoryPKey,ICFSecSecUserPWHistoryObj>();
		ICFSecSecUserPWHistory[] recList = schema.getCFSecBackingStore().getTableSecUserPWHistory().pageAllRec( null,
			priorSecUserId,
			priorPWSetStamp );
		ICFSecSecUserPWHistory rec;
		ICFSecSecUserPWHistoryObj obj;
		ICFSecSecUserPWHistoryObj realised;
		ArrayList<ICFSecSecUserPWHistoryObj> arrayList = new ArrayList<ICFSecSecUserPWHistoryObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecUserPWHistoryObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistoryByIdIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime PWSetStamp )
	{
		return( readSecUserPWHistoryByIdIdx( SecUserId,
			PWSetStamp,
			false ) );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistoryByIdIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime PWSetStamp, boolean forceRead )
	{
		ICFSecSecUserPWHistoryPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newPKey();
		pkey.setRequiredSecUserId( SecUserId );
		pkey.setRequiredPWSetStamp( PWSetStamp );
		ICFSecSecUserPWHistoryObj obj = readSecUserPWHistory( pkey, forceRead );
		return( obj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistoryByUserIdx( ICFLibKeyHash256 SecUserId )
	{
		return( readSecUserPWHistoryByUserIdx( SecUserId,
			false ) );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistoryByUserIdx( ICFLibKeyHash256 SecUserId, boolean forceRead )
	{
		if( indexByUserIdx == null ) {
			indexByUserIdx = new HashMap< ICFSecSecUserPWHistoryByUserIdxKey,
				ICFSecSecUserPWHistoryObj >();
		}
		ICFSecSecUserPWHistoryByUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		ICFSecSecUserPWHistoryObj obj = null;
		if( ( ! forceRead ) && indexByUserIdx.containsKey( key ) ) {
			obj = indexByUserIdx.get( key );
		}
		else {
			ICFSecSecUserPWHistory rec = schema.getCFSecBackingStore().getTableSecUserPWHistory().readDerivedByUserIdx( null,
				SecUserId );
			if( rec != null ) {
				obj = schema.getSecUserPWHistoryTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecUserPWHistoryObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistoryBySetStampIdx( LocalDateTime PWSetStamp )
	{
		return( readSecUserPWHistoryBySetStampIdx( PWSetStamp,
			false ) );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistoryBySetStampIdx( LocalDateTime PWSetStamp, boolean forceRead )
	{
		if( indexBySetStampIdx == null ) {
			indexBySetStampIdx = new HashMap< ICFSecSecUserPWHistoryBySetStampIdxKey,
				ICFSecSecUserPWHistoryObj >();
		}
		ICFSecSecUserPWHistoryBySetStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		ICFSecSecUserPWHistoryObj obj = null;
		if( ( ! forceRead ) && indexBySetStampIdx.containsKey( key ) ) {
			obj = indexBySetStampIdx.get( key );
		}
		else {
			ICFSecSecUserPWHistory rec = schema.getCFSecBackingStore().getTableSecUserPWHistory().readDerivedBySetStampIdx( null,
				PWSetStamp );
			if( rec != null ) {
				obj = schema.getSecUserPWHistoryTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecUserPWHistoryObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistoryByReplacedStampIdx( LocalDateTime PWReplacedStamp )
	{
		return( readSecUserPWHistoryByReplacedStampIdx( PWReplacedStamp,
			false ) );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readSecUserPWHistoryByReplacedStampIdx( LocalDateTime PWReplacedStamp, boolean forceRead )
	{
		if( indexByReplacedStampIdx == null ) {
			indexByReplacedStampIdx = new HashMap< ICFSecSecUserPWHistoryByReplacedStampIdxKey,
				ICFSecSecUserPWHistoryObj >();
		}
		ICFSecSecUserPWHistoryByReplacedStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByReplacedStampIdxKey();
		key.setRequiredPWReplacedStamp( PWReplacedStamp );
		ICFSecSecUserPWHistoryObj obj = null;
		if( ( ! forceRead ) && indexByReplacedStampIdx.containsKey( key ) ) {
			obj = indexByReplacedStampIdx.get( key );
		}
		else {
			ICFSecSecUserPWHistory rec = schema.getCFSecBackingStore().getTableSecUserPWHistory().readDerivedByReplacedStampIdx( null,
				PWReplacedStamp );
			if( rec != null ) {
				obj = schema.getSecUserPWHistoryTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecUserPWHistoryObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readCachedSecUserPWHistoryByIdIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime PWSetStamp )
	{
		ICFSecSecUserPWHistoryObj obj = null;
		ICFSecSecUserPWHistoryPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newPKey();
		pkey.setRequiredSecUserId( SecUserId );
		pkey.setRequiredPWSetStamp( PWSetStamp );
		pkey.setRequiredSecUserId( SecUserId );
		pkey.setRequiredPWSetStamp( PWSetStamp );
		obj = readCachedSecUserPWHistory( pkey );
		return( obj );
	}

	@Override
	public ICFSecSecUserPWHistoryObj readCachedSecUserPWHistoryByUserIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserPWHistoryObj obj = null;
		ICFSecSecUserPWHistoryByUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		if( indexByUserIdx != null ) {
			if( indexByUserIdx.containsKey( key ) ) {
				obj = indexByUserIdx.get( key );
			}
			else {
				Iterator<ICFSecSecUserPWHistoryObj> valIter = members.values().iterator();
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
			Iterator<ICFSecSecUserPWHistoryObj> valIter = members.values().iterator();
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
	public ICFSecSecUserPWHistoryObj readCachedSecUserPWHistoryBySetStampIdx( LocalDateTime PWSetStamp )
	{
		ICFSecSecUserPWHistoryObj obj = null;
		ICFSecSecUserPWHistoryBySetStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		if( indexBySetStampIdx != null ) {
			if( indexBySetStampIdx.containsKey( key ) ) {
				obj = indexBySetStampIdx.get( key );
			}
			else {
				Iterator<ICFSecSecUserPWHistoryObj> valIter = members.values().iterator();
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
			Iterator<ICFSecSecUserPWHistoryObj> valIter = members.values().iterator();
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
	public ICFSecSecUserPWHistoryObj readCachedSecUserPWHistoryByReplacedStampIdx( LocalDateTime PWReplacedStamp )
	{
		ICFSecSecUserPWHistoryObj obj = null;
		ICFSecSecUserPWHistoryByReplacedStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByReplacedStampIdxKey();
		key.setRequiredPWReplacedStamp( PWReplacedStamp );
		if( indexByReplacedStampIdx != null ) {
			if( indexByReplacedStampIdx.containsKey( key ) ) {
				obj = indexByReplacedStampIdx.get( key );
			}
			else {
				Iterator<ICFSecSecUserPWHistoryObj> valIter = members.values().iterator();
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
			Iterator<ICFSecSecUserPWHistoryObj> valIter = members.values().iterator();
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
	public void deepDisposeSecUserPWHistoryByIdIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime PWSetStamp )
	{
		ICFSecSecUserPWHistoryObj obj = readCachedSecUserPWHistoryByIdIdx( SecUserId,
				PWSetStamp );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserPWHistoryByUserIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserPWHistoryObj obj = readCachedSecUserPWHistoryByUserIdx( SecUserId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserPWHistoryBySetStampIdx( LocalDateTime PWSetStamp )
	{
		ICFSecSecUserPWHistoryObj obj = readCachedSecUserPWHistoryBySetStampIdx( PWSetStamp );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserPWHistoryByReplacedStampIdx( LocalDateTime PWReplacedStamp )
	{
		ICFSecSecUserPWHistoryObj obj = readCachedSecUserPWHistoryByReplacedStampIdx( PWReplacedStamp );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecUserPWHistoryObj updateSecUserPWHistory( ICFSecSecUserPWHistoryObj Obj ) {
		ICFSecSecUserPWHistoryObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserPWHistory().updateSecUserPWHistory( null,
			Obj.getSecUserPWHistoryRec() );
		obj = (ICFSecSecUserPWHistoryObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecUserPWHistory( ICFSecSecUserPWHistoryObj Obj ) {
		ICFSecSecUserPWHistoryObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserPWHistory().deleteSecUserPWHistory( null,
			obj.getSecUserPWHistoryRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecUserPWHistoryByIdIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime PWSetStamp )
	{
		ICFSecSecUserPWHistoryObj obj = readSecUserPWHistory(SecUserId,
				PWSetStamp);
		if( obj != null ) {
			ICFSecSecUserPWHistoryEditObj editObj = (ICFSecSecUserPWHistoryEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecUserPWHistoryEditObj)obj.beginEdit();
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
		deepDisposeSecUserPWHistoryByIdIdx( SecUserId,
				PWSetStamp );
	}

	@Override
	public void deleteSecUserPWHistoryByUserIdx( ICFLibKeyHash256 SecUserId )
	{
		if( indexByUserIdx == null ) {
			indexByUserIdx = new HashMap< ICFSecSecUserPWHistoryByUserIdxKey,
				ICFSecSecUserPWHistoryObj >();
		}
		ICFSecSecUserPWHistoryByUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		ICFSecSecUserPWHistoryObj obj = null;
		if( indexByUserIdx.containsKey( key ) ) {
			obj = indexByUserIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserPWHistory().deleteSecUserPWHistoryByUserIdx( null,
				SecUserId );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserPWHistory().deleteSecUserPWHistoryByUserIdx( null,
				SecUserId );
		}
		deepDisposeSecUserPWHistoryByUserIdx( SecUserId );
	}

	@Override
	public void deleteSecUserPWHistoryBySetStampIdx( LocalDateTime PWSetStamp )
	{
		if( indexBySetStampIdx == null ) {
			indexBySetStampIdx = new HashMap< ICFSecSecUserPWHistoryBySetStampIdxKey,
				ICFSecSecUserPWHistoryObj >();
		}
		ICFSecSecUserPWHistoryBySetStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newBySetStampIdxKey();
		key.setRequiredPWSetStamp( PWSetStamp );
		ICFSecSecUserPWHistoryObj obj = null;
		if( indexBySetStampIdx.containsKey( key ) ) {
			obj = indexBySetStampIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserPWHistory().deleteSecUserPWHistoryBySetStampIdx( null,
				PWSetStamp );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserPWHistory().deleteSecUserPWHistoryBySetStampIdx( null,
				PWSetStamp );
		}
		deepDisposeSecUserPWHistoryBySetStampIdx( PWSetStamp );
	}

	@Override
	public void deleteSecUserPWHistoryByReplacedStampIdx( LocalDateTime PWReplacedStamp )
	{
		if( indexByReplacedStampIdx == null ) {
			indexByReplacedStampIdx = new HashMap< ICFSecSecUserPWHistoryByReplacedStampIdxKey,
				ICFSecSecUserPWHistoryObj >();
		}
		ICFSecSecUserPWHistoryByReplacedStampIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWHistory().newByReplacedStampIdxKey();
		key.setRequiredPWReplacedStamp( PWReplacedStamp );
		ICFSecSecUserPWHistoryObj obj = null;
		if( indexByReplacedStampIdx.containsKey( key ) ) {
			obj = indexByReplacedStampIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserPWHistory().deleteSecUserPWHistoryByReplacedStampIdx( null,
				PWReplacedStamp );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserPWHistory().deleteSecUserPWHistoryByReplacedStampIdx( null,
				PWReplacedStamp );
		}
		deepDisposeSecUserPWHistoryByReplacedStampIdx( PWReplacedStamp );
	}
}