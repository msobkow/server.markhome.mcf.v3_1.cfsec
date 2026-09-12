// Description: Java 25 Table Object implementation for SecTentRole.

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

public class CFSecSecTentRoleTableObj
	implements ICFSecSecTentRoleTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecTentRole.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecTentRole.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> allSecTentRole;
	private Map< ICFSecSecTentRoleByTenantIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > > indexByTenantIdx;
	private Map< ICFSecSecTentRoleByNameIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > > indexByNameIdx;
	private Map< ICFSecSecTentRoleByUNameIdxKey,
		ICFSecSecTentRoleObj > indexByUNameIdx;
	public static String TABLE_NAME = "SecTentRole";
	public static String TABLE_DBNAME = "sectentrole";

	public CFSecSecTentRoleTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj>();
		allSecTentRole = null;
		indexByTenantIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
	}

	public CFSecSecTentRoleTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj>();
		allSecTentRole = null;
		indexByTenantIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecTentRoleTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecTentRoleTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecTentRole = null;
		indexByTenantIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
		List<ICFSecSecTentRoleObj> toForget = new LinkedList<ICFSecSecTentRoleObj>();
		ICFSecSecTentRoleObj cur = null;
		Iterator<ICFSecSecTentRoleObj> iter = members.values().iterator();
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
	 *	CFSecSecTentRoleObj.
	 */
	@Override
	public ICFSecSecTentRoleObj newInstance() {
		ICFSecSecTentRoleObj inst = new CFSecSecTentRoleObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecTentRoleObj.
	 */
	@Override
	public ICFSecSecTentRoleEditObj newEditInstance( ICFSecSecTentRoleObj orig ) {
		ICFSecSecTentRoleEditObj edit = new CFSecSecTentRoleEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecTentRoleObj realiseSecTentRole( ICFSecSecTentRoleObj Obj ) {
		ICFSecSecTentRoleObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecTentRoleObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecTentRoleObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFSecSecTentRoleByTenantIdxKey keyTenantIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.remove( keepObj.getPKey() );
					if( mapTenantIdx.size() <= 0 ) {
						indexByTenantIdx.remove( keyTenantIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentRoleByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecTentRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFSecSecTentRoleByTenantIdxKey keyTenantIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentRoleByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecTentRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allSecTentRole != null ) {
				allSecTentRole.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecTentRole != null ) {
				allSecTentRole.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFSecSecTentRoleByTenantIdxKey keyTenantIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecTentRoleByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecTentRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecTentRoleObj createSecTentRole( ICFSecSecTentRoleObj Obj ) {
		ICFSecSecTentRoleObj obj = Obj;
		ICFSecSecTentRole rec = obj.getSecTentRoleRec();
		schema.getCFSecBackingStore().getTableSecTentRole().createSecTentRole(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecTentRoleObj readSecTentRole( $implCommaIJavaOptAtomType$ pkey ) {
		return( readSecTentRole( pkey, false ) );
	}

	@Override
	public ICFSecSecTentRoleObj readSecTentRole( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecSecTentRoleObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecTentRole readRec = schema.getCFSecBackingStore().getTableSecTentRole().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecTentRoleTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecTentRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentRoleObj readCachedSecTentRole( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecTentRoleObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecTentRole( ICFSecSecTentRoleObj obj )
	{
		final String S_ProcName = "CFSecSecTentRoleTableObj.reallyDeepDisposeSecTentRole() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecTentRoleObj existing = readCachedSecTentRole( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecTentRoleByTenantIdxKey keyTenantIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByTenantIdxKey();
		keyTenantIdx.setRequiredTenantId( existing.getRequiredTenantId() );

		ICFSecSecTentRoleByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByNameIdxKey();
		keyNameIdx.setRequiredName( existing.getRequiredName() );

		ICFSecSecTentRoleByUNameIdxKey keyUNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByUNameIdxKey();
		keyUNameIdx.setRequiredTenantId( existing.getRequiredTenantId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


		schema.getSecTentRoleMembTableObj().deepDisposeSecTentRoleMembByTentRoleIdx( existing.getRequiredSecTentRoleId() );

		if( indexByTenantIdx != null ) {
			if( indexByTenantIdx.containsKey( keyTenantIdx ) ) {
				indexByTenantIdx.get( keyTenantIdx ).remove( pkey );
				if( indexByTenantIdx.get( keyTenantIdx ).size() <= 0 ) {
					indexByTenantIdx.remove( keyTenantIdx );
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

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


	}
	@Override
	public void deepDisposeSecTentRole( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecTentRoleObj obj = readCachedSecTentRole( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecTentRoleObj lockSecTentRole( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecTentRoleObj locked = null;
		ICFSecSecTentRole lockRec = schema.getCFSecBackingStore().getTableSecTentRole().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecTentRoleTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecTentRoleObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecTentRole", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecTentRoleObj> readAllSecTentRole() {
		return( readAllSecTentRole( false ) );
	}

	@Override
	public List<ICFSecSecTentRoleObj> readAllSecTentRole( boolean forceRead ) {
		final String S_ProcName = "readAllSecTentRole";
		if( ( allSecTentRole == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecTentRoleObj>();
			allSecTentRole = map;
			ICFSecSecTentRole[] recList = schema.getCFSecBackingStore().getTableSecTentRole().readAllDerived( null );
			ICFSecSecTentRole rec;
			ICFSecSecTentRoleObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentRoleObj realised = (ICFSecSecTentRoleObj)obj.realise();
			}
		}
		int len = allSecTentRole.size();
		ICFSecSecTentRoleObj arr[] = new ICFSecSecTentRoleObj[len];
		Iterator<ICFSecSecTentRoleObj> valIter = allSecTentRole.values().iterator();
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
		ArrayList<ICFSecSecTentRoleObj> arrayList = new ArrayList<ICFSecSecTentRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentRoleObj> cmp = new Comparator<ICFSecSecTentRoleObj>() {
			@Override
			public int compare( ICFSecSecTentRoleObj lhs, ICFSecSecTentRoleObj rhs ) {
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
		List<ICFSecSecTentRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentRoleObj> readCachedAllSecTentRole() {
		final String S_ProcName = "readCachedAllSecTentRole";
		ArrayList<ICFSecSecTentRoleObj> arrayList = new ArrayList<ICFSecSecTentRoleObj>();
		if( allSecTentRole != null ) {
			int len = allSecTentRole.size();
			ICFSecSecTentRoleObj arr[] = new ICFSecSecTentRoleObj[len];
			Iterator<ICFSecSecTentRoleObj> valIter = allSecTentRole.values().iterator();
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
		Comparator<ICFSecSecTentRoleObj> cmp = new Comparator<ICFSecSecTentRoleObj>() {
			public int compare( ICFSecSecTentRoleObj lhs, ICFSecSecTentRoleObj rhs ) {
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
	public ICFSecSecTentRoleObj readSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId )
	{
		return( readSecTentRoleByIdIdx( SecTentRoleId,
			false ) );
	}

	@Override
	public ICFSecSecTentRoleObj readSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId, boolean forceRead )
	{
		ICFSecSecTentRoleObj obj = readSecTentRole( SecTentRoleId, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentRoleObj> readSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId )
	{
		return( readSecTentRoleByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFSecSecTentRoleObj> readSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentRoleByTenantIdx";
		ICFSecSecTentRoleByTenantIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFSecSecTentRoleByTenantIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj>();
			ICFSecSecTentRoleObj obj;
			ICFSecSecTentRole[] recList = schema.getCFSecBackingStore().getTableSecTentRole().readDerivedByTenantIdx( null,
				TenantId );
			ICFSecSecTentRole rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentRoleTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentRoleObj realised = (ICFSecSecTentRoleObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentRoleObj arr[] = new ICFSecSecTentRoleObj[len];
		Iterator<ICFSecSecTentRoleObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecTentRoleObj> arrayList = new ArrayList<ICFSecSecTentRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentRoleObj> cmp = new Comparator<ICFSecSecTentRoleObj>() {
			@Override
			public int compare( ICFSecSecTentRoleObj lhs, ICFSecSecTentRoleObj rhs ) {
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
		List<ICFSecSecTentRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentRoleObj> readSecTentRoleByNameIdx( String Name )
	{
		return( readSecTentRoleByNameIdx( Name,
			false ) );
	}

	@Override
	public List<ICFSecSecTentRoleObj> readSecTentRoleByNameIdx( String Name,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentRoleByNameIdx";
		ICFSecSecTentRoleByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByNameIdxKey();
		key.setRequiredName( Name );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecTentRoleByNameIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj>();
			ICFSecSecTentRoleObj obj;
			ICFSecSecTentRole[] recList = schema.getCFSecBackingStore().getTableSecTentRole().readDerivedByNameIdx( null,
				Name );
			ICFSecSecTentRole rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentRoleTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentRoleObj realised = (ICFSecSecTentRoleObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentRoleObj arr[] = new ICFSecSecTentRoleObj[len];
		Iterator<ICFSecSecTentRoleObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecTentRoleObj> arrayList = new ArrayList<ICFSecSecTentRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentRoleObj> cmp = new Comparator<ICFSecSecTentRoleObj>() {
			@Override
			public int compare( ICFSecSecTentRoleObj lhs, ICFSecSecTentRoleObj rhs ) {
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
		List<ICFSecSecTentRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecTentRoleObj readSecTentRoleByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name )
	{
		return( readSecTentRoleByUNameIdx( TenantId,
			Name,
			false ) );
	}

	@Override
	public ICFSecSecTentRoleObj readSecTentRoleByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecTentRoleByUNameIdxKey,
				ICFSecSecTentRoleObj >();
		}
		ICFSecSecTentRoleByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByUNameIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredName( Name );
		ICFSecSecTentRoleObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFSecSecTentRole rec = schema.getCFSecBackingStore().getTableSecTentRole().readDerivedByUNameIdx( null,
				TenantId,
				Name );
			if( rec != null ) {
				obj = schema.getSecTentRoleTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecTentRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentRoleObj readCachedSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId )
	{
		ICFSecSecTentRoleObj obj = null;
		obj = readCachedSecTentRole( SecTentRoleId );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentRoleObj> readCachedSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedSecTentRoleByTenantIdx";
		ICFSecSecTentRoleByTenantIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFSecSecTentRoleObj> arrayList = new ArrayList<ICFSecSecTentRoleObj>();
		if( indexByTenantIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFSecSecTentRoleObj arr[] = new ICFSecSecTentRoleObj[len];
				Iterator<ICFSecSecTentRoleObj> valIter = dict.values().iterator();
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
			ICFSecSecTentRoleObj obj;
			Iterator<ICFSecSecTentRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentRoleObj> cmp = new Comparator<ICFSecSecTentRoleObj>() {
			@Override
			public int compare( ICFSecSecTentRoleObj lhs, ICFSecSecTentRoleObj rhs ) {
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
	public List<ICFSecSecTentRoleObj> readCachedSecTentRoleByNameIdx( String Name )
	{
		final String S_ProcName = "readCachedSecTentRoleByNameIdx";
		ICFSecSecTentRoleByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByNameIdxKey();
		key.setRequiredName( Name );
		ArrayList<ICFSecSecTentRoleObj> arrayList = new ArrayList<ICFSecSecTentRoleObj>();
		if( indexByNameIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecTentRoleObj arr[] = new ICFSecSecTentRoleObj[len];
				Iterator<ICFSecSecTentRoleObj> valIter = dict.values().iterator();
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
			ICFSecSecTentRoleObj obj;
			Iterator<ICFSecSecTentRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentRoleObj> cmp = new Comparator<ICFSecSecTentRoleObj>() {
			@Override
			public int compare( ICFSecSecTentRoleObj lhs, ICFSecSecTentRoleObj rhs ) {
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
	public ICFSecSecTentRoleObj readCachedSecTentRoleByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name )
	{
		ICFSecSecTentRoleObj obj = null;
		ICFSecSecTentRoleByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByUNameIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFSecSecTentRoleObj> valIter = members.values().iterator();
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
			Iterator<ICFSecSecTentRoleObj> valIter = members.values().iterator();
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
	public void deepDisposeSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId )
	{
		ICFSecSecTentRoleObj obj = readCachedSecTentRoleByIdIdx( SecTentRoleId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeSecTentRoleByTenantIdx";
		ICFSecSecTentRoleObj obj;
		List<ICFSecSecTentRoleObj> arrayList = readCachedSecTentRoleByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentRoleObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecTentRoleByNameIdx( String Name )
	{
		final String S_ProcName = "deepDisposeSecTentRoleByNameIdx";
		ICFSecSecTentRoleObj obj;
		List<ICFSecSecTentRoleObj> arrayList = readCachedSecTentRoleByNameIdx( Name );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentRoleObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecTentRoleByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name )
	{
		ICFSecSecTentRoleObj obj = readCachedSecTentRoleByUNameIdx( TenantId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecTentRoleObj updateSecTentRole( ICFSecSecTentRoleObj Obj ) {
		ICFSecSecTentRoleObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentRole().updateSecTentRole( null,
			Obj.getSecTentRoleRec() );
		obj = (ICFSecSecTentRoleObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecTentRole( ICFSecSecTentRoleObj Obj ) {
		ICFSecSecTentRoleObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentRole().deleteSecTentRole( null,
			obj.getSecTentRoleRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecTentRoleByIdIdx( ICFLibKeyHash256 SecTentRoleId )
	{
		ICFSecSecTentRoleObj obj = readSecTentRole(SecTentRoleId);
		if( obj != null ) {
			ICFSecSecTentRoleEditObj editObj = (ICFSecSecTentRoleEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecTentRoleEditObj)obj.beginEdit();
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
		deepDisposeSecTentRoleByIdIdx( SecTentRoleId );
	}

	@Override
	public void deleteSecTentRoleByTenantIdx( ICFLibKeyHash256 TenantId )
	{
		ICFSecSecTentRoleByTenantIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFSecSecTentRoleByTenantIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> dict = indexByTenantIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentRole().deleteSecTentRoleByTenantIdx( null,
				TenantId );
			Iterator<ICFSecSecTentRoleObj> iter = dict.values().iterator();
			ICFSecSecTentRoleObj obj;
			List<ICFSecSecTentRoleObj> toForget = new LinkedList<ICFSecSecTentRoleObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentRole().deleteSecTentRoleByTenantIdx( null,
				TenantId );
		}
		deepDisposeSecTentRoleByTenantIdx( TenantId );
	}

	@Override
	public void deleteSecTentRoleByNameIdx( String Name )
	{
		ICFSecSecTentRoleByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByNameIdxKey();
		key.setRequiredName( Name );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecTentRoleByNameIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecTentRoleObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentRole().deleteSecTentRoleByNameIdx( null,
				Name );
			Iterator<ICFSecSecTentRoleObj> iter = dict.values().iterator();
			ICFSecSecTentRoleObj obj;
			List<ICFSecSecTentRoleObj> toForget = new LinkedList<ICFSecSecTentRoleObj>();
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
			schema.getCFSecBackingStore().getTableSecTentRole().deleteSecTentRoleByNameIdx( null,
				Name );
		}
		deepDisposeSecTentRoleByNameIdx( Name );
	}

	@Override
	public void deleteSecTentRoleByUNameIdx( ICFLibKeyHash256 TenantId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecTentRoleByUNameIdxKey,
				ICFSecSecTentRoleObj >();
		}
		ICFSecSecTentRoleByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecTentRole().newByUNameIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredName( Name );
		ICFSecSecTentRoleObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentRole().deleteSecTentRoleByUNameIdx( null,
				TenantId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentRole().deleteSecTentRoleByUNameIdx( null,
				TenantId,
				Name );
		}
		deepDisposeSecTentRoleByUNameIdx( TenantId,
				Name );
	}
}