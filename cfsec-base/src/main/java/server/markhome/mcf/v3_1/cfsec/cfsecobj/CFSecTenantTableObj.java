// Description: Java 25 Table Object implementation for Tenant.

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

public class CFSecTenantTableObj
	implements ICFSecTenantTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecTenant.CLASS_CODE;
	protected static final int backingClassCode = ICFSecTenant.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj> allTenant;
	private Map< ICFSecTenantByClusterIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj > > indexByClusterIdx;
	private Map< ICFSecTenantByUNameIdxKey,
		ICFSecTenantObj > indexByUNameIdx;
	public static String TABLE_NAME = "Tenant";
	public static String TABLE_DBNAME = "tenant";

	public CFSecTenantTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecTenantObj>();
		allTenant = null;
		indexByClusterIdx = null;
		indexByUNameIdx = null;
	}

	public CFSecTenantTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecTenantObj>();
		allTenant = null;
		indexByClusterIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecTenantTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecTenantTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allTenant = null;
		indexByClusterIdx = null;
		indexByUNameIdx = null;
		List<ICFSecTenantObj> toForget = new LinkedList<ICFSecTenantObj>();
		ICFSecTenantObj cur = null;
		Iterator<ICFSecTenantObj> iter = members.values().iterator();
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
	 *	CFSecTenantObj.
	 */
	@Override
	public ICFSecTenantObj newInstance() {
		ICFSecTenantObj inst = new CFSecTenantObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecTenantObj.
	 */
	@Override
	public ICFSecTenantEditObj newEditInstance( ICFSecTenantObj orig ) {
		ICFSecTenantEditObj edit = new CFSecTenantEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecTenantObj realiseTenant( ICFSecTenantObj Obj ) {
		ICFSecTenantObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecTenantObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecTenantObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByClusterIdx != null ) {
				ICFSecTenantByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.remove( keepObj.getPKey() );
					if( mapClusterIdx.size() <= 0 ) {
						indexByClusterIdx.remove( keyClusterIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecTenantByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredTenantName( keepObj.getRequiredTenantName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByClusterIdx != null ) {
				ICFSecTenantByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecTenantByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredTenantName( keepObj.getRequiredTenantName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allTenant != null ) {
				allTenant.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allTenant != null ) {
				allTenant.put( keepObj.getPKey(), keepObj );
			}

			if( indexByClusterIdx != null ) {
				ICFSecTenantByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecTenantByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredTenantName( keepObj.getRequiredTenantName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecTenantObj createTenant( ICFSecTenantObj Obj ) {
		ICFSecTenantObj obj = Obj;
		ICFSecTenant rec = obj.getTenantRec();
		schema.getCFSecBackingStore().getTableTenant().createTenant(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecTenantObj readTenant( $implCommaIJavaOptAtomType$ pkey ) {
		return( readTenant( pkey, false ) );
	}

	@Override
	public ICFSecTenantObj readTenant( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecTenantObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecTenant readRec = schema.getCFSecBackingStore().getTableTenant().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getTenantTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecTenantObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecTenantObj readCachedTenant( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecTenantObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeTenant( ICFSecTenantObj obj )
	{
		final String S_ProcName = "CFSecTenantTableObj.reallyDeepDisposeTenant() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecTenantObj existing = readCachedTenant( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecTenantByClusterIdxKey keyClusterIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByClusterIdxKey();
		keyClusterIdx.setRequiredClusterId( existing.getRequiredClusterId() );

		ICFSecTenantByUNameIdxKey keyUNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByUNameIdxKey();
		keyUNameIdx.setRequiredClusterId( existing.getRequiredClusterId() );
		keyUNameIdx.setRequiredTenantName( existing.getRequiredTenantName() );


					schema.getSecTentRoleTableObj().deepDisposeSecTentRoleByTenantIdx( existing.getRequiredId() );
		ICFSecSecTentGrpObj objDelTentGrpMembs;
		List<ICFSecSecTentGrpObj> arrDelTentGrpMembs = schema.getSecTentGrpTableObj().readCachedSecTentGrpByTenantIdx( existing.getRequiredId() );
		Iterator<ICFSecSecTentGrpObj> iterDelTentGrpMembs = arrDelTentGrpMembs.iterator();
		while( iterDelTentGrpMembs.hasNext() ) {
			objDelTentGrpMembs = iterDelTentGrpMembs.next();
			if( objDelTentGrpMembs != null ) {
						schema.getSecTentGrpMembTableObj().deepDisposeSecTentGrpMembByTentGrpIdx( objDelTentGrpMembs.getRequiredSecTentGrpId() );
			}
		}
					schema.getSecTentGrpTableObj().deepDisposeSecTentGrpByTenantIdx( existing.getRequiredId() );

		if( indexByClusterIdx != null ) {
			if( indexByClusterIdx.containsKey( keyClusterIdx ) ) {
				indexByClusterIdx.get( keyClusterIdx ).remove( pkey );
				if( indexByClusterIdx.get( keyClusterIdx ).size() <= 0 ) {
					indexByClusterIdx.remove( keyClusterIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


	}
	@Override
	public void deepDisposeTenant( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecTenantObj obj = readCachedTenant( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecTenantObj lockTenant( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecTenantObj locked = null;
		ICFSecTenant lockRec = schema.getCFSecBackingStore().getTableTenant().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getTenantTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecTenantObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockTenant", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecTenantObj> readAllTenant() {
		return( readAllTenant( false ) );
	}

	@Override
	public List<ICFSecTenantObj> readAllTenant( boolean forceRead ) {
		final String S_ProcName = "readAllTenant";
		if( ( allTenant == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecTenantObj>();
			allTenant = map;
			ICFSecTenant[] recList = schema.getCFSecBackingStore().getTableTenant().readAllDerived( null );
			ICFSecTenant rec;
			ICFSecTenantObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecTenantObj realised = (ICFSecTenantObj)obj.realise();
			}
		}
		int len = allTenant.size();
		ICFSecTenantObj arr[] = new ICFSecTenantObj[len];
		Iterator<ICFSecTenantObj> valIter = allTenant.values().iterator();
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
		ArrayList<ICFSecTenantObj> arrayList = new ArrayList<ICFSecTenantObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecTenantObj> cmp = new Comparator<ICFSecTenantObj>() {
			@Override
			public int compare( ICFSecTenantObj lhs, ICFSecTenantObj rhs ) {
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
		List<ICFSecTenantObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecTenantObj> readCachedAllTenant() {
		final String S_ProcName = "readCachedAllTenant";
		ArrayList<ICFSecTenantObj> arrayList = new ArrayList<ICFSecTenantObj>();
		if( allTenant != null ) {
			int len = allTenant.size();
			ICFSecTenantObj arr[] = new ICFSecTenantObj[len];
			Iterator<ICFSecTenantObj> valIter = allTenant.values().iterator();
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
		Comparator<ICFSecTenantObj> cmp = new Comparator<ICFSecTenantObj>() {
			public int compare( ICFSecTenantObj lhs, ICFSecTenantObj rhs ) {
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
	 *	Return a sorted map of a page of the Tenant-derived instances in the database.
	 *
	 *	@return	List of ICFSecTenantObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecTenantObj> pageAllTenant(ICFLibKeyHash256 priorId )
	{
		final String S_ProcName = "pageAllTenant";
		Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecTenantObj>();
		ICFSecTenant[] recList = schema.getCFSecBackingStore().getTableTenant().pageAllRec( null,
			priorId );
		ICFSecTenant rec;
		ICFSecTenantObj obj;
		ICFSecTenantObj realised;
		ArrayList<ICFSecTenantObj> arrayList = new ArrayList<ICFSecTenantObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecTenantObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecTenantObj readTenantByIdIdx( ICFLibKeyHash256 Id )
	{
		return( readTenantByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFSecTenantObj readTenantByIdIdx( ICFLibKeyHash256 Id, boolean forceRead )
	{
		ICFSecTenantObj obj = readTenant( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecTenantObj> readTenantByClusterIdx( ICFLibKeyHash256 ClusterId )
	{
		return( readTenantByClusterIdx( ClusterId,
			false ) );
	}

	@Override
	public List<ICFSecTenantObj> readTenantByClusterIdx( ICFLibKeyHash256 ClusterId,
		boolean forceRead )
	{
		final String S_ProcName = "readTenantByClusterIdx";
		ICFSecTenantByClusterIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj> dict;
		if( indexByClusterIdx == null ) {
			indexByClusterIdx = new HashMap< ICFSecTenantByClusterIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecTenantObj > >();
		}
		if( ( ! forceRead ) && indexByClusterIdx.containsKey( key ) ) {
			dict = indexByClusterIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecTenantObj>();
			ICFSecTenantObj obj;
			ICFSecTenant[] recList = schema.getCFSecBackingStore().getTableTenant().readDerivedByClusterIdx( null,
				ClusterId );
			ICFSecTenant rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getTenantTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecTenantObj realised = (ICFSecTenantObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClusterIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecTenantObj arr[] = new ICFSecTenantObj[len];
		Iterator<ICFSecTenantObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecTenantObj> arrayList = new ArrayList<ICFSecTenantObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecTenantObj> cmp = new Comparator<ICFSecTenantObj>() {
			@Override
			public int compare( ICFSecTenantObj lhs, ICFSecTenantObj rhs ) {
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
		List<ICFSecTenantObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecTenantObj readTenantByUNameIdx( ICFLibKeyHash256 ClusterId,
		String TenantName )
	{
		return( readTenantByUNameIdx( ClusterId,
			TenantName,
			false ) );
	}

	@Override
	public ICFSecTenantObj readTenantByUNameIdx( ICFLibKeyHash256 ClusterId,
		String TenantName, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecTenantByUNameIdxKey,
				ICFSecTenantObj >();
		}
		ICFSecTenantByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredTenantName( TenantName );
		ICFSecTenantObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFSecTenant rec = schema.getCFSecBackingStore().getTableTenant().readDerivedByUNameIdx( null,
				ClusterId,
				TenantName );
			if( rec != null ) {
				obj = schema.getTenantTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecTenantObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecTenantObj readCachedTenantByIdIdx( ICFLibKeyHash256 Id )
	{
		ICFSecTenantObj obj = null;
		obj = readCachedTenant( Id );
		return( obj );
	}

	@Override
	public List<ICFSecTenantObj> readCachedTenantByClusterIdx( ICFLibKeyHash256 ClusterId )
	{
		final String S_ProcName = "readCachedTenantByClusterIdx";
		ICFSecTenantByClusterIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		ArrayList<ICFSecTenantObj> arrayList = new ArrayList<ICFSecTenantObj>();
		if( indexByClusterIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj> dict;
			if( indexByClusterIdx.containsKey( key ) ) {
				dict = indexByClusterIdx.get( key );
				int len = dict.size();
				ICFSecTenantObj arr[] = new ICFSecTenantObj[len];
				Iterator<ICFSecTenantObj> valIter = dict.values().iterator();
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
			ICFSecTenantObj obj;
			Iterator<ICFSecTenantObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecTenantObj> cmp = new Comparator<ICFSecTenantObj>() {
			@Override
			public int compare( ICFSecTenantObj lhs, ICFSecTenantObj rhs ) {
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
	public ICFSecTenantObj readCachedTenantByUNameIdx( ICFLibKeyHash256 ClusterId,
		String TenantName )
	{
		ICFSecTenantObj obj = null;
		ICFSecTenantByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredTenantName( TenantName );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFSecTenantObj> valIter = members.values().iterator();
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
			Iterator<ICFSecTenantObj> valIter = members.values().iterator();
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
	public void deepDisposeTenantByIdIdx( ICFLibKeyHash256 Id )
	{
		ICFSecTenantObj obj = readCachedTenantByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeTenantByClusterIdx( ICFLibKeyHash256 ClusterId )
	{
		final String S_ProcName = "deepDisposeTenantByClusterIdx";
		ICFSecTenantObj obj;
		List<ICFSecTenantObj> arrayList = readCachedTenantByClusterIdx( ClusterId );
		if( arrayList != null )  {
			Iterator<ICFSecTenantObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeTenantByUNameIdx( ICFLibKeyHash256 ClusterId,
		String TenantName )
	{
		ICFSecTenantObj obj = readCachedTenantByUNameIdx( ClusterId,
				TenantName );
		if( obj != null ) {
			obj.forget();
		}
	}

	/**
	 *	Read a page of data as a List of Tenant-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ClusterIdx key attributes.
	 *
	 *	@param	ClusterId	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return	A List of Tenant-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecTenantObj> pageTenantByClusterIdx( ICFLibKeyHash256 ClusterId,
		ICFLibKeyHash256 priorId )
	{
		final String S_ProcName = "pageTenantByClusterIdx";
		ICFSecTenantByClusterIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		List<ICFSecTenantObj> retList = new LinkedList<ICFSecTenantObj>();
		ICFSecTenantObj obj;
		ICFSecTenant[] recList = schema.getCFSecBackingStore().getTableTenant().pageRecByClusterIdx( null,
				ClusterId,
			priorId );
		ICFSecTenant rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getTenantTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecTenantObj realised = (ICFSecTenantObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecTenantObj updateTenant( ICFSecTenantObj Obj ) {
		ICFSecTenantObj obj = Obj;
		schema.getCFSecBackingStore().getTableTenant().updateTenant( null,
			Obj.getTenantRec() );
		obj = (ICFSecTenantObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteTenant( ICFSecTenantObj Obj ) {
		ICFSecTenantObj obj = Obj;
		schema.getCFSecBackingStore().getTableTenant().deleteTenant( null,
			obj.getTenantRec() );
		Obj.forget();
	}

	@Override
	public void deleteTenantByIdIdx( ICFLibKeyHash256 Id )
	{
		ICFSecTenantObj obj = readTenant(Id);
		if( obj != null ) {
			ICFSecTenantEditObj editObj = (ICFSecTenantEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecTenantEditObj)obj.beginEdit();
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
		deepDisposeTenantByIdIdx( Id );
	}

	@Override
	public void deleteTenantByClusterIdx( ICFLibKeyHash256 ClusterId )
	{
		ICFSecTenantByClusterIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		if( indexByClusterIdx == null ) {
			indexByClusterIdx = new HashMap< ICFSecTenantByClusterIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecTenantObj > >();
		}
		if( indexByClusterIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecTenantObj> dict = indexByClusterIdx.get( key );
			schema.getCFSecBackingStore().getTableTenant().deleteTenantByClusterIdx( null,
				ClusterId );
			Iterator<ICFSecTenantObj> iter = dict.values().iterator();
			ICFSecTenantObj obj;
			List<ICFSecTenantObj> toForget = new LinkedList<ICFSecTenantObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClusterIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableTenant().deleteTenantByClusterIdx( null,
				ClusterId );
		}
		deepDisposeTenantByClusterIdx( ClusterId );
	}

	@Override
	public void deleteTenantByUNameIdx( ICFLibKeyHash256 ClusterId,
		String TenantName )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecTenantByUNameIdxKey,
				ICFSecTenantObj >();
		}
		ICFSecTenantByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactoryTenant().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredTenantName( TenantName );
		ICFSecTenantObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFSecBackingStore().getTableTenant().deleteTenantByUNameIdx( null,
				ClusterId,
				TenantName );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableTenant().deleteTenantByUNameIdx( null,
				ClusterId,
				TenantName );
		}
		deepDisposeTenantByUNameIdx( ClusterId,
				TenantName );
	}
}