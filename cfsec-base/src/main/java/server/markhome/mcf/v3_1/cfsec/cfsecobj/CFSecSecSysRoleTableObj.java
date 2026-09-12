// Description: Java 25 Table Object implementation for SecSysRole.

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

public class CFSecSecSysRoleTableObj
	implements ICFSecSecSysRoleTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecSysRole.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecSysRole.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecSysRoleObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecSysRoleObj> allSecSysRole;
	private Map< ICFSecSecSysRoleByUNameIdxKey,
		ICFSecSecSysRoleObj > indexByUNameIdx;
	public static String TABLE_NAME = "SecSysRole";
	public static String TABLE_DBNAME = "secsysrole";

	public CFSecSecSysRoleTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecSysRoleObj>();
		allSecSysRole = null;
		indexByUNameIdx = null;
	}

	public CFSecSecSysRoleTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecSysRoleObj>();
		allSecSysRole = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecSysRoleTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecSysRoleTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecSysRole = null;
		indexByUNameIdx = null;
		List<ICFSecSecSysRoleObj> toForget = new LinkedList<ICFSecSecSysRoleObj>();
		ICFSecSecSysRoleObj cur = null;
		Iterator<ICFSecSecSysRoleObj> iter = members.values().iterator();
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
	 *	CFSecSecSysRoleObj.
	 */
	@Override
	public ICFSecSecSysRoleObj newInstance() {
		ICFSecSecSysRoleObj inst = new CFSecSecSysRoleObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecSysRoleObj.
	 */
	@Override
	public ICFSecSecSysRoleEditObj newEditInstance( ICFSecSecSysRoleObj orig ) {
		ICFSecSecSysRoleEditObj edit = new CFSecSecSysRoleEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecSysRoleObj realiseSecSysRole( ICFSecSecSysRoleObj Obj ) {
		ICFSecSecSysRoleObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecSysRoleObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecSysRoleObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFSecSecSysRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFSecSecSysRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allSecSysRole != null ) {
				allSecSysRole.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecSysRole != null ) {
				allSecSysRole.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecSysRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecSysRoleObj createSecSysRole( ICFSecSecSysRoleObj Obj ) {
		ICFSecSecSysRoleObj obj = Obj;
		ICFSecSecSysRole rec = obj.getSecSysRoleRec();
		schema.getCFSecBackingStore().getTableSecSysRole().createSecSysRole(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleObj readSecSysRole( $implCommaIJavaOptAtomType$ pkey ) {
		return( readSecSysRole( pkey, false ) );
	}

	@Override
	public ICFSecSecSysRoleObj readSecSysRole( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecSecSysRoleObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecSysRole readRec = schema.getCFSecBackingStore().getTableSecSysRole().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecSysRoleTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecSysRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleObj readCachedSecSysRole( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecSysRoleObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecSysRole( ICFSecSecSysRoleObj obj )
	{
		final String S_ProcName = "CFSecSecSysRoleTableObj.reallyDeepDisposeSecSysRole() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecSysRoleObj existing = readCachedSecSysRole( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecSysRoleByUNameIdxKey keyUNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRole().newByUNameIdxKey();
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


					schema.getSecSysRoleMembTableObj().deepDisposeSecSysRoleMembBySysRoleIdx( existing.getRequiredSecSysRoleId() );
					schema.getSecSysRoleEnablesTableObj().deepDisposeSecSysRoleEnablesBySysRoleIdx( existing.getRequiredSecSysRoleId() );

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


	}
	@Override
	public void deepDisposeSecSysRole( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecSysRoleObj obj = readCachedSecSysRole( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSysRoleObj lockSecSysRole( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecSysRoleObj locked = null;
		ICFSecSecSysRole lockRec = schema.getCFSecBackingStore().getTableSecSysRole().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecSysRoleTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecSysRoleObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecSysRole", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecSysRoleObj> readAllSecSysRole() {
		return( readAllSecSysRole( false ) );
	}

	@Override
	public List<ICFSecSecSysRoleObj> readAllSecSysRole( boolean forceRead ) {
		final String S_ProcName = "readAllSecSysRole";
		if( ( allSecSysRole == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecSysRoleObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecSysRoleObj>();
			allSecSysRole = map;
			ICFSecSecSysRole[] recList = schema.getCFSecBackingStore().getTableSecSysRole().readAllDerived( null );
			ICFSecSecSysRole rec;
			ICFSecSecSysRoleObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysRoleObj realised = (ICFSecSecSysRoleObj)obj.realise();
			}
		}
		int len = allSecSysRole.size();
		ICFSecSecSysRoleObj arr[] = new ICFSecSecSysRoleObj[len];
		Iterator<ICFSecSecSysRoleObj> valIter = allSecSysRole.values().iterator();
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
		ArrayList<ICFSecSecSysRoleObj> arrayList = new ArrayList<ICFSecSecSysRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysRoleObj> cmp = new Comparator<ICFSecSecSysRoleObj>() {
			@Override
			public int compare( ICFSecSecSysRoleObj lhs, ICFSecSecSysRoleObj rhs ) {
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
		List<ICFSecSecSysRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysRoleObj> readCachedAllSecSysRole() {
		final String S_ProcName = "readCachedAllSecSysRole";
		ArrayList<ICFSecSecSysRoleObj> arrayList = new ArrayList<ICFSecSecSysRoleObj>();
		if( allSecSysRole != null ) {
			int len = allSecSysRole.size();
			ICFSecSecSysRoleObj arr[] = new ICFSecSecSysRoleObj[len];
			Iterator<ICFSecSecSysRoleObj> valIter = allSecSysRole.values().iterator();
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
		Comparator<ICFSecSecSysRoleObj> cmp = new Comparator<ICFSecSecSysRoleObj>() {
			public int compare( ICFSecSecSysRoleObj lhs, ICFSecSecSysRoleObj rhs ) {
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
	public ICFSecSecSysRoleObj readSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		return( readSecSysRoleByIdIdx( SecSysRoleId,
			false ) );
	}

	@Override
	public ICFSecSecSysRoleObj readSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId, boolean forceRead )
	{
		ICFSecSecSysRoleObj obj = readSecSysRole( SecSysRoleId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleObj readSecSysRoleByUNameIdx( String Name )
	{
		return( readSecSysRoleByUNameIdx( Name,
			false ) );
	}

	@Override
	public ICFSecSecSysRoleObj readSecSysRoleByUNameIdx( String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecSysRoleByUNameIdxKey,
				ICFSecSecSysRoleObj >();
		}
		ICFSecSecSysRoleByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRole().newByUNameIdxKey();
		key.setRequiredName( Name );
		ICFSecSecSysRoleObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFSecSecSysRole rec = schema.getCFSecBackingStore().getTableSecSysRole().readDerivedByUNameIdx( null,
				Name );
			if( rec != null ) {
				obj = schema.getSecSysRoleTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecSysRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleObj readCachedSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		ICFSecSecSysRoleObj obj = null;
		obj = readCachedSecSysRole( SecSysRoleId );
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleObj readCachedSecSysRoleByUNameIdx( String Name )
	{
		ICFSecSecSysRoleObj obj = null;
		ICFSecSecSysRoleByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRole().newByUNameIdxKey();
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFSecSecSysRoleObj> valIter = members.values().iterator();
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
			Iterator<ICFSecSecSysRoleObj> valIter = members.values().iterator();
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
	public void deepDisposeSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		ICFSecSecSysRoleObj obj = readCachedSecSysRoleByIdIdx( SecSysRoleId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSysRoleByUNameIdx( String Name )
	{
		ICFSecSecSysRoleObj obj = readCachedSecSysRoleByUNameIdx( Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSysRoleObj updateSecSysRole( ICFSecSecSysRoleObj Obj ) {
		ICFSecSecSysRoleObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysRole().updateSecSysRole( null,
			Obj.getSecSysRoleRec() );
		obj = (ICFSecSecSysRoleObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecSysRole( ICFSecSecSysRoleObj Obj ) {
		ICFSecSecSysRoleObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysRole().deleteSecSysRole( null,
			obj.getSecSysRoleRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecSysRoleByIdIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		ICFSecSecSysRoleObj obj = readSecSysRole(SecSysRoleId);
		if( obj != null ) {
			ICFSecSecSysRoleEditObj editObj = (ICFSecSecSysRoleEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecSysRoleEditObj)obj.beginEdit();
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
		deepDisposeSecSysRoleByIdIdx( SecSysRoleId );
	}

	@Override
	public void deleteSecSysRoleByUNameIdx( String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecSysRoleByUNameIdxKey,
				ICFSecSecSysRoleObj >();
		}
		ICFSecSecSysRoleByUNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRole().newByUNameIdxKey();
		key.setRequiredName( Name );
		ICFSecSecSysRoleObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysRole().deleteSecSysRoleByUNameIdx( null,
				Name );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysRole().deleteSecSysRoleByUNameIdx( null,
				Name );
		}
		deepDisposeSecSysRoleByUNameIdx( Name );
	}
}