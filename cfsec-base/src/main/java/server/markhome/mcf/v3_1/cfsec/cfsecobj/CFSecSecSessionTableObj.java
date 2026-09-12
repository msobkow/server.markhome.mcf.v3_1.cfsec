// Description: Java 25 Table Object implementation for SecSession.

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

public class CFSecSecSessionTableObj
	implements ICFSecSecSessionTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecSession.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecSession.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> allSecSession;
	private Map< ICFSecSecSessionBySecUserIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > > indexBySecUserIdx;
	private Map< ICFSecSecSessionByStartIdxKey,
		ICFSecSecSessionObj > indexByStartIdx;
	private Map< ICFSecSecSessionByFinishIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > > indexByFinishIdx;
	private Map< ICFSecSecSessionBySecProxyIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > > indexBySecProxyIdx;
	public static String TABLE_NAME = "SecSession";
	public static String TABLE_DBNAME = "secsess";

	public CFSecSecSessionTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj>();
		allSecSession = null;
		indexBySecUserIdx = null;
		indexByStartIdx = null;
		indexByFinishIdx = null;
		indexBySecProxyIdx = null;
	}

	public CFSecSecSessionTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj>();
		allSecSession = null;
		indexBySecUserIdx = null;
		indexByStartIdx = null;
		indexByFinishIdx = null;
		indexBySecProxyIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecSessionTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecSessionTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecSession = null;
		indexBySecUserIdx = null;
		indexByStartIdx = null;
		indexByFinishIdx = null;
		indexBySecProxyIdx = null;
		List<ICFSecSecSessionObj> toForget = new LinkedList<ICFSecSecSessionObj>();
		ICFSecSecSessionObj cur = null;
		Iterator<ICFSecSecSessionObj> iter = members.values().iterator();
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
	 *	CFSecSecSessionObj.
	 */
	@Override
	public ICFSecSecSessionObj newInstance() {
		ICFSecSecSessionObj inst = new CFSecSecSessionObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecSessionObj.
	 */
	@Override
	public ICFSecSecSessionEditObj newEditInstance( ICFSecSecSessionObj orig ) {
		ICFSecSecSessionEditObj edit = new CFSecSecSessionEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecSessionObj realiseSecSession( ICFSecSecSessionObj Obj ) {
		ICFSecSecSessionObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecSessionObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecSessionObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexBySecUserIdx != null ) {
				ICFSecSecSessionBySecUserIdxKey keySecUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecUserIdxKey();
				keySecUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapSecUserIdx = indexBySecUserIdx.get( keySecUserIdx );
				if( mapSecUserIdx != null ) {
					mapSecUserIdx.remove( keepObj.getPKey() );
					if( mapSecUserIdx.size() <= 0 ) {
						indexBySecUserIdx.remove( keySecUserIdx );
					}
				}
			}

			if( indexByStartIdx != null ) {
				ICFSecSecSessionByStartIdxKey keyStartIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByStartIdxKey();
				keyStartIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				keyStartIdx.setRequiredStart( keepObj.getRequiredStart() );
				indexByStartIdx.remove( keyStartIdx );
			}

			if( indexByFinishIdx != null ) {
				ICFSecSecSessionByFinishIdxKey keyFinishIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByFinishIdxKey();
				keyFinishIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				keyFinishIdx.setOptionalFinish( keepObj.getOptionalFinish() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapFinishIdx = indexByFinishIdx.get( keyFinishIdx );
				if( mapFinishIdx != null ) {
					mapFinishIdx.remove( keepObj.getPKey() );
					if( mapFinishIdx.size() <= 0 ) {
						indexByFinishIdx.remove( keyFinishIdx );
					}
				}
			}

			if( indexBySecProxyIdx != null ) {
				ICFSecSecSessionBySecProxyIdxKey keySecProxyIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecProxyIdxKey();
				keySecProxyIdx.setOptionalSecProxyId( keepObj.getOptionalSecProxyId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapSecProxyIdx = indexBySecProxyIdx.get( keySecProxyIdx );
				if( mapSecProxyIdx != null ) {
					mapSecProxyIdx.remove( keepObj.getPKey() );
					if( mapSecProxyIdx.size() <= 0 ) {
						indexBySecProxyIdx.remove( keySecProxyIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexBySecUserIdx != null ) {
				ICFSecSecSessionBySecUserIdxKey keySecUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecUserIdxKey();
				keySecUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapSecUserIdx = indexBySecUserIdx.get( keySecUserIdx );
				if( mapSecUserIdx != null ) {
					mapSecUserIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByStartIdx != null ) {
				ICFSecSecSessionByStartIdxKey keyStartIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByStartIdxKey();
				keyStartIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				keyStartIdx.setRequiredStart( keepObj.getRequiredStart() );
				indexByStartIdx.put( keyStartIdx, keepObj );
			}

			if( indexByFinishIdx != null ) {
				ICFSecSecSessionByFinishIdxKey keyFinishIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByFinishIdxKey();
				keyFinishIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				keyFinishIdx.setOptionalFinish( keepObj.getOptionalFinish() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapFinishIdx = indexByFinishIdx.get( keyFinishIdx );
				if( mapFinishIdx != null ) {
					mapFinishIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySecProxyIdx != null ) {
				ICFSecSecSessionBySecProxyIdxKey keySecProxyIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecProxyIdxKey();
				keySecProxyIdx.setOptionalSecProxyId( keepObj.getOptionalSecProxyId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapSecProxyIdx = indexBySecProxyIdx.get( keySecProxyIdx );
				if( mapSecProxyIdx != null ) {
					mapSecProxyIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecSession != null ) {
				allSecSession.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecSession != null ) {
				allSecSession.put( keepObj.getPKey(), keepObj );
			}

			if( indexBySecUserIdx != null ) {
				ICFSecSecSessionBySecUserIdxKey keySecUserIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecUserIdxKey();
				keySecUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapSecUserIdx = indexBySecUserIdx.get( keySecUserIdx );
				if( mapSecUserIdx != null ) {
					mapSecUserIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByStartIdx != null ) {
				ICFSecSecSessionByStartIdxKey keyStartIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByStartIdxKey();
				keyStartIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				keyStartIdx.setRequiredStart( keepObj.getRequiredStart() );
				indexByStartIdx.put( keyStartIdx, keepObj );
			}

			if( indexByFinishIdx != null ) {
				ICFSecSecSessionByFinishIdxKey keyFinishIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByFinishIdxKey();
				keyFinishIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				keyFinishIdx.setOptionalFinish( keepObj.getOptionalFinish() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapFinishIdx = indexByFinishIdx.get( keyFinishIdx );
				if( mapFinishIdx != null ) {
					mapFinishIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySecProxyIdx != null ) {
				ICFSecSecSessionBySecProxyIdxKey keySecProxyIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecProxyIdxKey();
				keySecProxyIdx.setOptionalSecProxyId( keepObj.getOptionalSecProxyId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj > mapSecProxyIdx = indexBySecProxyIdx.get( keySecProxyIdx );
				if( mapSecProxyIdx != null ) {
					mapSecProxyIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecSessionObj createSecSession( ICFSecSecSessionObj Obj ) {
		ICFSecSecSessionObj obj = Obj;
		ICFSecSecSession rec = obj.getSecSessionRec();
		schema.getCFSecBackingStore().getTableSecSession().createSecSession(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecSessionObj readSecSession( $implCommaIJavaOptAtomType$ pkey ) {
		return( readSecSession( pkey, false ) );
	}

	@Override
	public ICFSecSecSessionObj readSecSession( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecSecSessionObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecSession readRec = schema.getCFSecBackingStore().getTableSecSession().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecSessionTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecSessionObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSessionObj readCachedSecSession( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecSessionObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecSession( ICFSecSecSessionObj obj )
	{
		final String S_ProcName = "CFSecSecSessionTableObj.reallyDeepDisposeSecSession() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecSessionObj existing = readCachedSecSession( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecSessionBySecUserIdxKey keySecUserIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecUserIdxKey();
		keySecUserIdx.setRequiredSecUserId( existing.getRequiredSecUserId() );

		ICFSecSecSessionByStartIdxKey keyStartIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByStartIdxKey();
		keyStartIdx.setRequiredSecUserId( existing.getRequiredSecUserId() );
		keyStartIdx.setRequiredStart( existing.getRequiredStart() );

		ICFSecSecSessionByFinishIdxKey keyFinishIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByFinishIdxKey();
		keyFinishIdx.setRequiredSecUserId( existing.getRequiredSecUserId() );
		keyFinishIdx.setOptionalFinish( existing.getOptionalFinish() );

		ICFSecSecSessionBySecProxyIdxKey keySecProxyIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecProxyIdxKey();
		keySecProxyIdx.setOptionalSecProxyId( existing.getOptionalSecProxyId() );



		if( indexBySecUserIdx != null ) {
			if( indexBySecUserIdx.containsKey( keySecUserIdx ) ) {
				indexBySecUserIdx.get( keySecUserIdx ).remove( pkey );
				if( indexBySecUserIdx.get( keySecUserIdx ).size() <= 0 ) {
					indexBySecUserIdx.remove( keySecUserIdx );
				}
			}
		}

		if( indexByStartIdx != null ) {
			indexByStartIdx.remove( keyStartIdx );
		}

		if( indexByFinishIdx != null ) {
			if( indexByFinishIdx.containsKey( keyFinishIdx ) ) {
				indexByFinishIdx.get( keyFinishIdx ).remove( pkey );
				if( indexByFinishIdx.get( keyFinishIdx ).size() <= 0 ) {
					indexByFinishIdx.remove( keyFinishIdx );
				}
			}
		}

		if( indexBySecProxyIdx != null ) {
			if( indexBySecProxyIdx.containsKey( keySecProxyIdx ) ) {
				indexBySecProxyIdx.get( keySecProxyIdx ).remove( pkey );
				if( indexBySecProxyIdx.get( keySecProxyIdx ).size() <= 0 ) {
					indexBySecProxyIdx.remove( keySecProxyIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecSession( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecSessionObj obj = readCachedSecSession( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSessionObj lockSecSession( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecSessionObj locked = null;
		ICFSecSecSession lockRec = schema.getCFSecBackingStore().getTableSecSession().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecSessionTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecSessionObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecSession", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecSessionObj> readAllSecSession() {
		return( readAllSecSession( false ) );
	}

	@Override
	public List<ICFSecSecSessionObj> readAllSecSession( boolean forceRead ) {
		final String S_ProcName = "readAllSecSession";
		if( ( allSecSession == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecSessionObj>();
			allSecSession = map;
			ICFSecSecSession[] recList = schema.getCFSecBackingStore().getTableSecSession().readAllDerived( null );
			ICFSecSecSession rec;
			ICFSecSecSessionObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSessionObj realised = (ICFSecSecSessionObj)obj.realise();
			}
		}
		int len = allSecSession.size();
		ICFSecSecSessionObj arr[] = new ICFSecSecSessionObj[len];
		Iterator<ICFSecSecSessionObj> valIter = allSecSession.values().iterator();
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
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSessionObj> cmp = new Comparator<ICFSecSecSessionObj>() {
			@Override
			public int compare( ICFSecSecSessionObj lhs, ICFSecSecSessionObj rhs ) {
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
		List<ICFSecSecSessionObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSessionObj> readCachedAllSecSession() {
		final String S_ProcName = "readCachedAllSecSession";
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>();
		if( allSecSession != null ) {
			int len = allSecSession.size();
			ICFSecSecSessionObj arr[] = new ICFSecSecSessionObj[len];
			Iterator<ICFSecSecSessionObj> valIter = allSecSession.values().iterator();
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
		Comparator<ICFSecSecSessionObj> cmp = new Comparator<ICFSecSecSessionObj>() {
			public int compare( ICFSecSecSessionObj lhs, ICFSecSecSessionObj rhs ) {
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

	/**
	 *	Return a sorted map of a page of the SecSession-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSessionObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecSessionObj> pageAllSecSession(ICFLibKeyHash256 priorSecSessionId )
	{
		final String S_ProcName = "pageAllSecSession";
		Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecSessionObj>();
		ICFSecSecSession[] recList = schema.getCFSecBackingStore().getTableSecSession().pageAllRec( null,
			priorSecSessionId );
		ICFSecSecSession rec;
		ICFSecSecSessionObj obj;
		ICFSecSecSessionObj realised;
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecSessionObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecSessionObj readSecSessionByIdIdx( ICFLibKeyHash256 SecSessionId )
	{
		return( readSecSessionByIdIdx( SecSessionId,
			false ) );
	}

	@Override
	public ICFSecSecSessionObj readSecSessionByIdIdx( ICFLibKeyHash256 SecSessionId, boolean forceRead )
	{
		ICFSecSecSessionObj obj = readSecSession( SecSessionId, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecSessionObj> readSecSessionBySecUserIdx( ICFLibKeyHash256 SecUserId )
	{
		return( readSecSessionBySecUserIdx( SecUserId,
			false ) );
	}

	@Override
	public List<ICFSecSecSessionObj> readSecSessionBySecUserIdx( ICFLibKeyHash256 SecUserId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSessionBySecUserIdx";
		ICFSecSecSessionBySecUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict;
		if( indexBySecUserIdx == null ) {
			indexBySecUserIdx = new HashMap< ICFSecSecSessionBySecUserIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecSessionObj > >();
		}
		if( ( ! forceRead ) && indexBySecUserIdx.containsKey( key ) ) {
			dict = indexBySecUserIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj>();
			ICFSecSecSessionObj obj;
			ICFSecSecSession[] recList = schema.getCFSecBackingStore().getTableSecSession().readDerivedBySecUserIdx( null,
				SecUserId );
			ICFSecSecSession rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSessionTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSessionObj realised = (ICFSecSecSessionObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySecUserIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSessionObj arr[] = new ICFSecSecSessionObj[len];
		Iterator<ICFSecSecSessionObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSessionObj> cmp = new Comparator<ICFSecSecSessionObj>() {
			@Override
			public int compare( ICFSecSecSessionObj lhs, ICFSecSecSessionObj rhs ) {
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
		List<ICFSecSecSessionObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecSessionObj readSecSessionByStartIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Start )
	{
		return( readSecSessionByStartIdx( SecUserId,
			Start,
			false ) );
	}

	@Override
	public ICFSecSecSessionObj readSecSessionByStartIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Start, boolean forceRead )
	{
		if( indexByStartIdx == null ) {
			indexByStartIdx = new HashMap< ICFSecSecSessionByStartIdxKey,
				ICFSecSecSessionObj >();
		}
		ICFSecSecSessionByStartIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByStartIdxKey();
		key.setRequiredSecUserId( SecUserId );
		key.setRequiredStart( Start );
		ICFSecSecSessionObj obj = null;
		if( ( ! forceRead ) && indexByStartIdx.containsKey( key ) ) {
			obj = indexByStartIdx.get( key );
		}
		else {
			ICFSecSecSession rec = schema.getCFSecBackingStore().getTableSecSession().readDerivedByStartIdx( null,
				SecUserId,
				Start );
			if( rec != null ) {
				obj = schema.getSecSessionTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecSessionObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecSecSessionObj> readSecSessionByFinishIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Finish )
	{
		return( readSecSessionByFinishIdx( SecUserId,
			Finish,
			false ) );
	}

	@Override
	public List<ICFSecSecSessionObj> readSecSessionByFinishIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Finish,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSessionByFinishIdx";
		ICFSecSecSessionByFinishIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByFinishIdxKey();
		key.setRequiredSecUserId( SecUserId );
		key.setOptionalFinish( Finish );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict;
		if( indexByFinishIdx == null ) {
			indexByFinishIdx = new HashMap< ICFSecSecSessionByFinishIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecSessionObj > >();
		}
		if( ( ! forceRead ) && indexByFinishIdx.containsKey( key ) ) {
			dict = indexByFinishIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj>();
			ICFSecSecSessionObj obj;
			ICFSecSecSession[] recList = schema.getCFSecBackingStore().getTableSecSession().readDerivedByFinishIdx( null,
				SecUserId,
				Finish );
			ICFSecSecSession rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSessionTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSessionObj realised = (ICFSecSecSessionObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByFinishIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSessionObj arr[] = new ICFSecSecSessionObj[len];
		Iterator<ICFSecSecSessionObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSessionObj> cmp = new Comparator<ICFSecSecSessionObj>() {
			@Override
			public int compare( ICFSecSecSessionObj lhs, ICFSecSecSessionObj rhs ) {
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
		List<ICFSecSecSessionObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSessionObj> readSecSessionBySecProxyIdx( ICFLibKeyHash256 SecProxyId )
	{
		return( readSecSessionBySecProxyIdx( SecProxyId,
			false ) );
	}

	@Override
	public List<ICFSecSecSessionObj> readSecSessionBySecProxyIdx( ICFLibKeyHash256 SecProxyId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSessionBySecProxyIdx";
		ICFSecSecSessionBySecProxyIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecProxyIdxKey();
		key.setOptionalSecProxyId( SecProxyId );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict;
		if( indexBySecProxyIdx == null ) {
			indexBySecProxyIdx = new HashMap< ICFSecSecSessionBySecProxyIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecSessionObj > >();
		}
		if( ( ! forceRead ) && indexBySecProxyIdx.containsKey( key ) ) {
			dict = indexBySecProxyIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj>();
			ICFSecSecSessionObj obj;
			ICFSecSecSession[] recList = schema.getCFSecBackingStore().getTableSecSession().readDerivedBySecProxyIdx( null,
				SecProxyId );
			ICFSecSecSession rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSessionTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSessionObj realised = (ICFSecSecSessionObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySecProxyIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSessionObj arr[] = new ICFSecSecSessionObj[len];
		Iterator<ICFSecSecSessionObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSessionObj> cmp = new Comparator<ICFSecSecSessionObj>() {
			@Override
			public int compare( ICFSecSecSessionObj lhs, ICFSecSecSessionObj rhs ) {
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
		List<ICFSecSecSessionObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecSessionObj readCachedSecSessionByIdIdx( ICFLibKeyHash256 SecSessionId )
	{
		ICFSecSecSessionObj obj = null;
		obj = readCachedSecSession( SecSessionId );
		return( obj );
	}

	@Override
	public List<ICFSecSecSessionObj> readCachedSecSessionBySecUserIdx( ICFLibKeyHash256 SecUserId )
	{
		final String S_ProcName = "readCachedSecSessionBySecUserIdx";
		ICFSecSecSessionBySecUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>();
		if( indexBySecUserIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict;
			if( indexBySecUserIdx.containsKey( key ) ) {
				dict = indexBySecUserIdx.get( key );
				int len = dict.size();
				ICFSecSecSessionObj arr[] = new ICFSecSecSessionObj[len];
				Iterator<ICFSecSecSessionObj> valIter = dict.values().iterator();
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
			ICFSecSecSessionObj obj;
			Iterator<ICFSecSecSessionObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSessionObj> cmp = new Comparator<ICFSecSecSessionObj>() {
			@Override
			public int compare( ICFSecSecSessionObj lhs, ICFSecSecSessionObj rhs ) {
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
	public ICFSecSecSessionObj readCachedSecSessionByStartIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Start )
	{
		ICFSecSecSessionObj obj = null;
		ICFSecSecSessionByStartIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByStartIdxKey();
		key.setRequiredSecUserId( SecUserId );
		key.setRequiredStart( Start );
		if( indexByStartIdx != null ) {
			if( indexByStartIdx.containsKey( key ) ) {
				obj = indexByStartIdx.get( key );
			}
			else {
				Iterator<ICFSecSecSessionObj> valIter = members.values().iterator();
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
			Iterator<ICFSecSecSessionObj> valIter = members.values().iterator();
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
	public List<ICFSecSecSessionObj> readCachedSecSessionByFinishIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Finish )
	{
		final String S_ProcName = "readCachedSecSessionByFinishIdx";
		ICFSecSecSessionByFinishIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByFinishIdxKey();
		key.setRequiredSecUserId( SecUserId );
		key.setOptionalFinish( Finish );
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>();
		if( indexByFinishIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict;
			if( indexByFinishIdx.containsKey( key ) ) {
				dict = indexByFinishIdx.get( key );
				int len = dict.size();
				ICFSecSecSessionObj arr[] = new ICFSecSecSessionObj[len];
				Iterator<ICFSecSecSessionObj> valIter = dict.values().iterator();
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
			ICFSecSecSessionObj obj;
			Iterator<ICFSecSecSessionObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSessionObj> cmp = new Comparator<ICFSecSecSessionObj>() {
			@Override
			public int compare( ICFSecSecSessionObj lhs, ICFSecSecSessionObj rhs ) {
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
	public List<ICFSecSecSessionObj> readCachedSecSessionBySecProxyIdx( ICFLibKeyHash256 SecProxyId )
	{
		final String S_ProcName = "readCachedSecSessionBySecProxyIdx";
		ICFSecSecSessionBySecProxyIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecProxyIdxKey();
		key.setOptionalSecProxyId( SecProxyId );
		ArrayList<ICFSecSecSessionObj> arrayList = new ArrayList<ICFSecSecSessionObj>();
		if( indexBySecProxyIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict;
			if( indexBySecProxyIdx.containsKey( key ) ) {
				dict = indexBySecProxyIdx.get( key );
				int len = dict.size();
				ICFSecSecSessionObj arr[] = new ICFSecSecSessionObj[len];
				Iterator<ICFSecSecSessionObj> valIter = dict.values().iterator();
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
			ICFSecSecSessionObj obj;
			Iterator<ICFSecSecSessionObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSessionObj> cmp = new Comparator<ICFSecSecSessionObj>() {
			@Override
			public int compare( ICFSecSecSessionObj lhs, ICFSecSecSessionObj rhs ) {
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
	public void deepDisposeSecSessionByIdIdx( ICFLibKeyHash256 SecSessionId )
	{
		ICFSecSecSessionObj obj = readCachedSecSessionByIdIdx( SecSessionId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSessionBySecUserIdx( ICFLibKeyHash256 SecUserId )
	{
		final String S_ProcName = "deepDisposeSecSessionBySecUserIdx";
		ICFSecSecSessionObj obj;
		List<ICFSecSecSessionObj> arrayList = readCachedSecSessionBySecUserIdx( SecUserId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSessionObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecSessionByStartIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Start )
	{
		ICFSecSecSessionObj obj = readCachedSecSessionByStartIdx( SecUserId,
				Start );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSessionByFinishIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Finish )
	{
		final String S_ProcName = "deepDisposeSecSessionByFinishIdx";
		ICFSecSecSessionObj obj;
		List<ICFSecSecSessionObj> arrayList = readCachedSecSessionByFinishIdx( SecUserId,
				Finish );
		if( arrayList != null )  {
			Iterator<ICFSecSecSessionObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecSessionBySecProxyIdx( ICFLibKeyHash256 SecProxyId )
	{
		final String S_ProcName = "deepDisposeSecSessionBySecProxyIdx";
		ICFSecSecSessionObj obj;
		List<ICFSecSecSessionObj> arrayList = readCachedSecSessionBySecProxyIdx( SecProxyId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSessionObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecSession-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SecUserIdx key attributes.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSession-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSessionObj> pageSecSessionBySecUserIdx( ICFLibKeyHash256 SecUserId,
		ICFLibKeyHash256 priorSecSessionId )
	{
		final String S_ProcName = "pageSecSessionBySecUserIdx";
		ICFSecSecSessionBySecUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		List<ICFSecSecSessionObj> retList = new LinkedList<ICFSecSecSessionObj>();
		ICFSecSecSessionObj obj;
		ICFSecSecSession[] recList = schema.getCFSecBackingStore().getTableSecSession().pageRecBySecUserIdx( null,
				SecUserId,
			priorSecSessionId );
		ICFSecSecSession rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSessionTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSessionObj realised = (ICFSecSecSessionObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecSession-derived instances sorted by their primary keys,
	 *	as identified by the duplicate FinishIdx key attributes.
	 *
	 *	@param	SecUserId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@param	Finish	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSession-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSessionObj> pageSecSessionByFinishIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Finish,
		ICFLibKeyHash256 priorSecSessionId )
	{
		final String S_ProcName = "pageSecSessionByFinishIdx";
		ICFSecSecSessionByFinishIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByFinishIdxKey();
		key.setRequiredSecUserId( SecUserId );
		key.setOptionalFinish( Finish );
		List<ICFSecSecSessionObj> retList = new LinkedList<ICFSecSecSessionObj>();
		ICFSecSecSessionObj obj;
		ICFSecSecSession[] recList = schema.getCFSecBackingStore().getTableSecSession().pageRecByFinishIdx( null,
				SecUserId,
				Finish,
			priorSecSessionId );
		ICFSecSecSession rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSessionTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSessionObj realised = (ICFSecSecSessionObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecSession-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SecProxyIdx key attributes.
	 *
	 *	@param	SecProxyId	The SecSession key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSession-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSessionObj> pageSecSessionBySecProxyIdx( ICFLibKeyHash256 SecProxyId,
		ICFLibKeyHash256 priorSecSessionId )
	{
		final String S_ProcName = "pageSecSessionBySecProxyIdx";
		ICFSecSecSessionBySecProxyIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecProxyIdxKey();
		key.setOptionalSecProxyId( SecProxyId );
		List<ICFSecSecSessionObj> retList = new LinkedList<ICFSecSecSessionObj>();
		ICFSecSecSessionObj obj;
		ICFSecSecSession[] recList = schema.getCFSecBackingStore().getTableSecSession().pageRecBySecProxyIdx( null,
				SecProxyId,
			priorSecSessionId );
		ICFSecSecSession rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSessionTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSessionObj realised = (ICFSecSecSessionObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecSessionObj updateSecSession( ICFSecSecSessionObj Obj ) {
		ICFSecSecSessionObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSession().updateSecSession( null,
			Obj.getSecSessionRec() );
		obj = (ICFSecSecSessionObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecSession( ICFSecSecSessionObj Obj ) {
		ICFSecSecSessionObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSession().deleteSecSession( null,
			obj.getSecSessionRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecSessionByIdIdx( ICFLibKeyHash256 SecSessionId )
	{
		ICFSecSecSessionObj obj = readSecSession(SecSessionId);
		if( obj != null ) {
			ICFSecSecSessionEditObj editObj = (ICFSecSecSessionEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecSessionEditObj)obj.beginEdit();
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
		deepDisposeSecSessionByIdIdx( SecSessionId );
	}

	@Override
	public void deleteSecSessionBySecUserIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecSessionBySecUserIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		if( indexBySecUserIdx == null ) {
			indexBySecUserIdx = new HashMap< ICFSecSecSessionBySecUserIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecSessionObj > >();
		}
		if( indexBySecUserIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict = indexBySecUserIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSession().deleteSecSessionBySecUserIdx( null,
				SecUserId );
			Iterator<ICFSecSecSessionObj> iter = dict.values().iterator();
			ICFSecSecSessionObj obj;
			List<ICFSecSecSessionObj> toForget = new LinkedList<ICFSecSecSessionObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySecUserIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSession().deleteSecSessionBySecUserIdx( null,
				SecUserId );
		}
		deepDisposeSecSessionBySecUserIdx( SecUserId );
	}

	@Override
	public void deleteSecSessionByStartIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Start )
	{
		if( indexByStartIdx == null ) {
			indexByStartIdx = new HashMap< ICFSecSecSessionByStartIdxKey,
				ICFSecSecSessionObj >();
		}
		ICFSecSecSessionByStartIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByStartIdxKey();
		key.setRequiredSecUserId( SecUserId );
		key.setRequiredStart( Start );
		ICFSecSecSessionObj obj = null;
		if( indexByStartIdx.containsKey( key ) ) {
			obj = indexByStartIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSession().deleteSecSessionByStartIdx( null,
				SecUserId,
				Start );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecSession().deleteSecSessionByStartIdx( null,
				SecUserId,
				Start );
		}
		deepDisposeSecSessionByStartIdx( SecUserId,
				Start );
	}

	@Override
	public void deleteSecSessionByFinishIdx( ICFLibKeyHash256 SecUserId,
		LocalDateTime Finish )
	{
		ICFSecSecSessionByFinishIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newByFinishIdxKey();
		key.setRequiredSecUserId( SecUserId );
		key.setOptionalFinish( Finish );
		if( indexByFinishIdx == null ) {
			indexByFinishIdx = new HashMap< ICFSecSecSessionByFinishIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecSessionObj > >();
		}
		if( indexByFinishIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict = indexByFinishIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSession().deleteSecSessionByFinishIdx( null,
				SecUserId,
				Finish );
			Iterator<ICFSecSecSessionObj> iter = dict.values().iterator();
			ICFSecSecSessionObj obj;
			List<ICFSecSecSessionObj> toForget = new LinkedList<ICFSecSecSessionObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByFinishIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSession().deleteSecSessionByFinishIdx( null,
				SecUserId,
				Finish );
		}
		deepDisposeSecSessionByFinishIdx( SecUserId,
				Finish );
	}

	@Override
	public void deleteSecSessionBySecProxyIdx( ICFLibKeyHash256 SecProxyId )
	{
		ICFSecSecSessionBySecProxyIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSession().newBySecProxyIdxKey();
		key.setOptionalSecProxyId( SecProxyId );
		if( indexBySecProxyIdx == null ) {
			indexBySecProxyIdx = new HashMap< ICFSecSecSessionBySecProxyIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecSessionObj > >();
		}
		if( indexBySecProxyIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecSessionObj> dict = indexBySecProxyIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSession().deleteSecSessionBySecProxyIdx( null,
				SecProxyId );
			Iterator<ICFSecSecSessionObj> iter = dict.values().iterator();
			ICFSecSecSessionObj obj;
			List<ICFSecSecSessionObj> toForget = new LinkedList<ICFSecSecSessionObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySecProxyIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSession().deleteSecSessionBySecProxyIdx( null,
				SecProxyId );
		}
		deepDisposeSecSessionBySecProxyIdx( SecProxyId );
	}
}