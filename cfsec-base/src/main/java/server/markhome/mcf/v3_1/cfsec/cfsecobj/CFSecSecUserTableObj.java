// Description: Java 25 Table Object implementation for SecUser.

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

public class CFSecSecUserTableObj
	implements ICFSecSecUserTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecUser.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecUser.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj> allSecUser;
	private Map< ICFSecSecUserByULoginIdxKey,
		ICFSecSecUserObj > indexByULoginIdx;
	private Map< ICFSecSecUserByEMAddrIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj > > indexByEMAddrIdx;
	public static String TABLE_NAME = "SecUser";
	public static String TABLE_DBNAME = "secuser";

	public CFSecSecUserTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserObj>();
		allSecUser = null;
		indexByULoginIdx = null;
		indexByEMAddrIdx = null;
	}

	public CFSecSecUserTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserObj>();
		allSecUser = null;
		indexByULoginIdx = null;
		indexByEMAddrIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecUserTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecUserTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecUser = null;
		indexByULoginIdx = null;
		indexByEMAddrIdx = null;
		List<ICFSecSecUserObj> toForget = new LinkedList<ICFSecSecUserObj>();
		ICFSecSecUserObj cur = null;
		Iterator<ICFSecSecUserObj> iter = members.values().iterator();
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
	 *	CFSecSecUserObj.
	 */
	@Override
	public ICFSecSecUserObj newInstance() {
		ICFSecSecUserObj inst = new CFSecSecUserObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecUserObj.
	 */
	@Override
	public ICFSecSecUserEditObj newEditInstance( ICFSecSecUserObj orig ) {
		ICFSecSecUserEditObj edit = new CFSecSecUserEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecUserObj realiseSecUser( ICFSecSecUserObj Obj ) {
		ICFSecSecUserObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecUserObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecUserObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByULoginIdx != null ) {
				ICFSecSecUserByULoginIdxKey keyULoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByULoginIdxKey();
				keyULoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				indexByULoginIdx.remove( keyULoginIdx );
			}

			if( indexByEMAddrIdx != null ) {
				ICFSecSecUserByEMAddrIdxKey keyEMAddrIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByEMAddrIdxKey();
				keyEMAddrIdx.setRequiredEMailAddress( keepObj.getRequiredEMailAddress() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj > mapEMAddrIdx = indexByEMAddrIdx.get( keyEMAddrIdx );
				if( mapEMAddrIdx != null ) {
					mapEMAddrIdx.remove( keepObj.getPKey() );
					if( mapEMAddrIdx.size() <= 0 ) {
						indexByEMAddrIdx.remove( keyEMAddrIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByULoginIdx != null ) {
				ICFSecSecUserByULoginIdxKey keyULoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByULoginIdxKey();
				keyULoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				indexByULoginIdx.put( keyULoginIdx, keepObj );
			}

			if( indexByEMAddrIdx != null ) {
				ICFSecSecUserByEMAddrIdxKey keyEMAddrIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByEMAddrIdxKey();
				keyEMAddrIdx.setRequiredEMailAddress( keepObj.getRequiredEMailAddress() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj > mapEMAddrIdx = indexByEMAddrIdx.get( keyEMAddrIdx );
				if( mapEMAddrIdx != null ) {
					mapEMAddrIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecUser != null ) {
				allSecUser.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecUser != null ) {
				allSecUser.put( keepObj.getPKey(), keepObj );
			}

			if( indexByULoginIdx != null ) {
				ICFSecSecUserByULoginIdxKey keyULoginIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByULoginIdxKey();
				keyULoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				indexByULoginIdx.put( keyULoginIdx, keepObj );
			}

			if( indexByEMAddrIdx != null ) {
				ICFSecSecUserByEMAddrIdxKey keyEMAddrIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByEMAddrIdxKey();
				keyEMAddrIdx.setRequiredEMailAddress( keepObj.getRequiredEMailAddress() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj > mapEMAddrIdx = indexByEMAddrIdx.get( keyEMAddrIdx );
				if( mapEMAddrIdx != null ) {
					mapEMAddrIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecUserObj createSecUser( ICFSecSecUserObj Obj ) {
		ICFSecSecUserObj obj = Obj;
		ICFSecSecUser rec = obj.getSecUserRec();
		schema.getCFSecBackingStore().getTableSecUser().createSecUser(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecUserObj readSecUser( $implCommaIJavaOptAtomType$ pkey ) {
		return( readSecUser( pkey, false ) );
	}

	@Override
	public ICFSecSecUserObj readSecUser( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecSecUserObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecUser readRec = schema.getCFSecBackingStore().getTableSecUser().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecUserTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecUserObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserObj readCachedSecUser( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecUser( ICFSecSecUserObj obj )
	{
		final String S_ProcName = "CFSecSecUserTableObj.reallyDeepDisposeSecUser() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecUserObj existing = readCachedSecUser( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecUserByULoginIdxKey keyULoginIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByULoginIdxKey();
		keyULoginIdx.setRequiredLoginId( existing.getRequiredLoginId() );

		ICFSecSecUserByEMAddrIdxKey keyEMAddrIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByEMAddrIdxKey();
		keyEMAddrIdx.setRequiredEMailAddress( existing.getRequiredEMailAddress() );


					schema.getSecUserPasswordTableObj().deepDisposeSecUserPasswordByIdIdx( existing.getRequiredSecUserId() );
					schema.getSecSysGrpMembTableObj().deepDisposeSecSysGrpMembByLoginIdx( existing.getRequiredLoginId() );
					schema.getSecClusGrpMembTableObj().deepDisposeSecClusGrpMembByLoginIdx( existing.getRequiredLoginId() );
					schema.getSecTentGrpMembTableObj().deepDisposeSecTentGrpMembByUserIdx( existing.getRequiredLoginId() );
					schema.getSecUserPasswordTableObj().deepDisposeSecUserPasswordByIdIdx( existing.getRequiredSecUserId() );
					schema.getSecUserPWResetTableObj().deepDisposeSecUserPWResetByIdIdx( existing.getRequiredSecUserId() );
					schema.getSecUserEMConfTableObj().deepDisposeSecUserEMConfByIdIdx( existing.getRequiredSecUserId() );

		if( indexByULoginIdx != null ) {
			indexByULoginIdx.remove( keyULoginIdx );
		}

		if( indexByEMAddrIdx != null ) {
			if( indexByEMAddrIdx.containsKey( keyEMAddrIdx ) ) {
				indexByEMAddrIdx.get( keyEMAddrIdx ).remove( pkey );
				if( indexByEMAddrIdx.get( keyEMAddrIdx ).size() <= 0 ) {
					indexByEMAddrIdx.remove( keyEMAddrIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecUser( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserObj obj = readCachedSecUser( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecUserObj lockSecUser( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserObj locked = null;
		ICFSecSecUser lockRec = schema.getCFSecBackingStore().getTableSecUser().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecUserTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecUserObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecUser", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecUserObj> readAllSecUser() {
		return( readAllSecUser( false ) );
	}

	@Override
	public List<ICFSecSecUserObj> readAllSecUser( boolean forceRead ) {
		final String S_ProcName = "readAllSecUser";
		if( ( allSecUser == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecUserObj>();
			allSecUser = map;
			ICFSecSecUser[] recList = schema.getCFSecBackingStore().getTableSecUser().readAllDerived( null );
			ICFSecSecUser rec;
			ICFSecSecUserObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserObj realised = (ICFSecSecUserObj)obj.realise();
			}
		}
		int len = allSecUser.size();
		ICFSecSecUserObj arr[] = new ICFSecSecUserObj[len];
		Iterator<ICFSecSecUserObj> valIter = allSecUser.values().iterator();
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
		ArrayList<ICFSecSecUserObj> arrayList = new ArrayList<ICFSecSecUserObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserObj> cmp = new Comparator<ICFSecSecUserObj>() {
			@Override
			public int compare( ICFSecSecUserObj lhs, ICFSecSecUserObj rhs ) {
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
		List<ICFSecSecUserObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserObj> readCachedAllSecUser() {
		final String S_ProcName = "readCachedAllSecUser";
		ArrayList<ICFSecSecUserObj> arrayList = new ArrayList<ICFSecSecUserObj>();
		if( allSecUser != null ) {
			int len = allSecUser.size();
			ICFSecSecUserObj arr[] = new ICFSecSecUserObj[len];
			Iterator<ICFSecSecUserObj> valIter = allSecUser.values().iterator();
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
		Comparator<ICFSecSecUserObj> cmp = new Comparator<ICFSecSecUserObj>() {
			public int compare( ICFSecSecUserObj lhs, ICFSecSecUserObj rhs ) {
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
	 *	Return a sorted map of a page of the SecUser-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecUserObj> pageAllSecUser(ICFLibKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageAllSecUser";
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecUserObj>();
		ICFSecSecUser[] recList = schema.getCFSecBackingStore().getTableSecUser().pageAllRec( null,
			priorSecUserId );
		ICFSecSecUser rec;
		ICFSecSecUserObj obj;
		ICFSecSecUserObj realised;
		ArrayList<ICFSecSecUserObj> arrayList = new ArrayList<ICFSecSecUserObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecUserObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecUserObj readSecUserByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		return( readSecUserByIdIdx( SecUserId,
			false ) );
	}

	@Override
	public ICFSecSecUserObj readSecUserByIdIdx( ICFLibKeyHash256 SecUserId, boolean forceRead )
	{
		ICFSecSecUserObj obj = readSecUser( SecUserId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecSecUserObj readSecUserByULoginIdx( String LoginId )
	{
		return( readSecUserByULoginIdx( LoginId,
			false ) );
	}

	@Override
	public ICFSecSecUserObj readSecUserByULoginIdx( String LoginId, boolean forceRead )
	{
		if( indexByULoginIdx == null ) {
			indexByULoginIdx = new HashMap< ICFSecSecUserByULoginIdxKey,
				ICFSecSecUserObj >();
		}
		ICFSecSecUserByULoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByULoginIdxKey();
		key.setRequiredLoginId( LoginId );
		ICFSecSecUserObj obj = null;
		if( ( ! forceRead ) && indexByULoginIdx.containsKey( key ) ) {
			obj = indexByULoginIdx.get( key );
		}
		else {
			ICFSecSecUser rec = schema.getCFSecBackingStore().getTableSecUser().readDerivedByULoginIdx( null,
				LoginId );
			if( rec != null ) {
				obj = schema.getSecUserTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecUserObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecSecUserObj> readSecUserByEMAddrIdx( String EMailAddress )
	{
		return( readSecUserByEMAddrIdx( EMailAddress,
			false ) );
	}

	@Override
	public List<ICFSecSecUserObj> readSecUserByEMAddrIdx( String EMailAddress,
		boolean forceRead )
	{
		final String S_ProcName = "readSecUserByEMAddrIdx";
		ICFSecSecUserByEMAddrIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByEMAddrIdxKey();
		key.setRequiredEMailAddress( EMailAddress );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj> dict;
		if( indexByEMAddrIdx == null ) {
			indexByEMAddrIdx = new HashMap< ICFSecSecUserByEMAddrIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecUserObj > >();
		}
		if( ( ! forceRead ) && indexByEMAddrIdx.containsKey( key ) ) {
			dict = indexByEMAddrIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserObj>();
			ICFSecSecUserObj obj;
			ICFSecSecUser[] recList = schema.getCFSecBackingStore().getTableSecUser().readDerivedByEMAddrIdx( null,
				EMailAddress );
			ICFSecSecUser rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecUserTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserObj realised = (ICFSecSecUserObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByEMAddrIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecUserObj arr[] = new ICFSecSecUserObj[len];
		Iterator<ICFSecSecUserObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecUserObj> arrayList = new ArrayList<ICFSecSecUserObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserObj> cmp = new Comparator<ICFSecSecUserObj>() {
			@Override
			public int compare( ICFSecSecUserObj lhs, ICFSecSecUserObj rhs ) {
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
		List<ICFSecSecUserObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecUserObj readCachedSecUserByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserObj obj = null;
		obj = readCachedSecUser( SecUserId );
		return( obj );
	}

	@Override
	public ICFSecSecUserObj readCachedSecUserByULoginIdx( String LoginId )
	{
		ICFSecSecUserObj obj = null;
		ICFSecSecUserByULoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByULoginIdxKey();
		key.setRequiredLoginId( LoginId );
		if( indexByULoginIdx != null ) {
			if( indexByULoginIdx.containsKey( key ) ) {
				obj = indexByULoginIdx.get( key );
			}
			else {
				Iterator<ICFSecSecUserObj> valIter = members.values().iterator();
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
			Iterator<ICFSecSecUserObj> valIter = members.values().iterator();
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
	public List<ICFSecSecUserObj> readCachedSecUserByEMAddrIdx( String EMailAddress )
	{
		final String S_ProcName = "readCachedSecUserByEMAddrIdx";
		ICFSecSecUserByEMAddrIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByEMAddrIdxKey();
		key.setRequiredEMailAddress( EMailAddress );
		ArrayList<ICFSecSecUserObj> arrayList = new ArrayList<ICFSecSecUserObj>();
		if( indexByEMAddrIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj> dict;
			if( indexByEMAddrIdx.containsKey( key ) ) {
				dict = indexByEMAddrIdx.get( key );
				int len = dict.size();
				ICFSecSecUserObj arr[] = new ICFSecSecUserObj[len];
				Iterator<ICFSecSecUserObj> valIter = dict.values().iterator();
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
			ICFSecSecUserObj obj;
			Iterator<ICFSecSecUserObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecUserObj> cmp = new Comparator<ICFSecSecUserObj>() {
			@Override
			public int compare( ICFSecSecUserObj lhs, ICFSecSecUserObj rhs ) {
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
	public void deepDisposeSecUserByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserObj obj = readCachedSecUserByIdIdx( SecUserId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserByULoginIdx( String LoginId )
	{
		ICFSecSecUserObj obj = readCachedSecUserByULoginIdx( LoginId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserByEMAddrIdx( String EMailAddress )
	{
		final String S_ProcName = "deepDisposeSecUserByEMAddrIdx";
		ICFSecSecUserObj obj;
		List<ICFSecSecUserObj> arrayList = readCachedSecUserByEMAddrIdx( EMailAddress );
		if( arrayList != null )  {
			Iterator<ICFSecSecUserObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecUser-derived instances sorted by their primary keys,
	 *	as identified by the duplicate EMAddrIdx key attributes.
	 *
	 *	@param	EMailAddress	The SecUser key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUser-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecUserObj> pageSecUserByEMAddrIdx( String EMailAddress,
		ICFLibKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecUserByEMAddrIdx";
		ICFSecSecUserByEMAddrIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByEMAddrIdxKey();
		key.setRequiredEMailAddress( EMailAddress );
		List<ICFSecSecUserObj> retList = new LinkedList<ICFSecSecUserObj>();
		ICFSecSecUserObj obj;
		ICFSecSecUser[] recList = schema.getCFSecBackingStore().getTableSecUser().pageRecByEMAddrIdx( null,
				EMailAddress,
			priorSecUserId );
		ICFSecSecUser rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecUserTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecUserObj realised = (ICFSecSecUserObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecUserObj updateSecUser( ICFSecSecUserObj Obj ) {
		ICFSecSecUserObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUser().updateSecUser( null,
			Obj.getSecUserRec() );
		obj = (ICFSecSecUserObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecUser( ICFSecSecUserObj Obj ) {
		ICFSecSecUserObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUser().deleteSecUser( null,
			obj.getSecUserRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecUserByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserObj obj = readSecUser(SecUserId);
		if( obj != null ) {
			ICFSecSecUserEditObj editObj = (ICFSecSecUserEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecUserEditObj)obj.beginEdit();
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
		deepDisposeSecUserByIdIdx( SecUserId );
	}

	@Override
	public void deleteSecUserByULoginIdx( String LoginId )
	{
		if( indexByULoginIdx == null ) {
			indexByULoginIdx = new HashMap< ICFSecSecUserByULoginIdxKey,
				ICFSecSecUserObj >();
		}
		ICFSecSecUserByULoginIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByULoginIdxKey();
		key.setRequiredLoginId( LoginId );
		ICFSecSecUserObj obj = null;
		if( indexByULoginIdx.containsKey( key ) ) {
			obj = indexByULoginIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUser().deleteSecUserByULoginIdx( null,
				LoginId );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecUser().deleteSecUserByULoginIdx( null,
				LoginId );
		}
		deepDisposeSecUserByULoginIdx( LoginId );
	}

	@Override
	public void deleteSecUserByEMAddrIdx( String EMailAddress )
	{
		ICFSecSecUserByEMAddrIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUser().newByEMAddrIdxKey();
		key.setRequiredEMailAddress( EMailAddress );
		if( indexByEMAddrIdx == null ) {
			indexByEMAddrIdx = new HashMap< ICFSecSecUserByEMAddrIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecUserObj > >();
		}
		if( indexByEMAddrIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserObj> dict = indexByEMAddrIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUser().deleteSecUserByEMAddrIdx( null,
				EMailAddress );
			Iterator<ICFSecSecUserObj> iter = dict.values().iterator();
			ICFSecSecUserObj obj;
			List<ICFSecSecUserObj> toForget = new LinkedList<ICFSecSecUserObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByEMAddrIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecUser().deleteSecUserByEMAddrIdx( null,
				EMailAddress );
		}
		deepDisposeSecUserByEMAddrIdx( EMailAddress );
	}
}