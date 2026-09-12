// Description: Java 25 Table Object implementation for SecSysRoleEnables.

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

public class CFSecSecSysRoleEnablesTableObj
	implements ICFSecSecSysRoleEnablesTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecSysRoleEnables.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecSysRoleEnables.CLASS_CODE;
	private Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> members;
	private Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> allSecSysRoleEnables;
	private Map< ICFSecSecSysRoleEnablesBySysRoleIdxKey,
		Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > > indexBySysRoleIdx;
	private Map< ICFSecSecSysRoleEnablesByNameIdxKey,
		Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > > indexByNameIdx;
	public static String TABLE_NAME = "SecSysRoleEnables";
	public static String TABLE_DBNAME = "secsysroleenables";

	public CFSecSecSysRoleEnablesTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj>();
		allSecSysRoleEnables = null;
		indexBySysRoleIdx = null;
		indexByNameIdx = null;
	}

	public CFSecSecSysRoleEnablesTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj>();
		allSecSysRoleEnables = null;
		indexBySysRoleIdx = null;
		indexByNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecSysRoleEnablesTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecSysRoleEnablesTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecSysRoleEnables = null;
		indexBySysRoleIdx = null;
		indexByNameIdx = null;
		List<ICFSecSecSysRoleEnablesObj> toForget = new LinkedList<ICFSecSecSysRoleEnablesObj>();
		ICFSecSecSysRoleEnablesObj cur = null;
		Iterator<ICFSecSecSysRoleEnablesObj> iter = members.values().iterator();
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
	 *	CFSecSecSysRoleEnablesObj.
	 */
	@Override
	public ICFSecSecSysRoleEnablesObj newInstance() {
		ICFSecSecSysRoleEnablesObj inst = new CFSecSecSysRoleEnablesObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecSysRoleEnablesObj.
	 */
	@Override
	public ICFSecSecSysRoleEnablesEditObj newEditInstance( ICFSecSecSysRoleEnablesObj orig ) {
		ICFSecSecSysRoleEnablesEditObj edit = new CFSecSecSysRoleEnablesEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj realiseSecSysRoleEnables( ICFSecSecSysRoleEnablesObj Obj ) {
		ICFSecSecSysRoleEnablesObj obj = Obj;
		ICFSecSecSysRoleEnablesPKey pkey = obj.getPKey();
		ICFSecSecSysRoleEnablesObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecSysRoleEnablesObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexBySysRoleIdx != null ) {
				ICFSecSecSysRoleEnablesBySysRoleIdxKey keySysRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newBySysRoleIdxKey();
				keySysRoleIdx.setRequiredSecSysRoleId( keepObj.getRequiredSecSysRoleId() );
				Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > mapSysRoleIdx = indexBySysRoleIdx.get( keySysRoleIdx );
				if( mapSysRoleIdx != null ) {
					mapSysRoleIdx.remove( keepObj.getPKey() );
					if( mapSysRoleIdx.size() <= 0 ) {
						indexBySysRoleIdx.remove( keySysRoleIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecSysRoleEnablesByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newByNameIdxKey();
				keyNameIdx.setRequiredEnableName( keepObj.getRequiredEnableName() );
				Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexBySysRoleIdx != null ) {
				ICFSecSecSysRoleEnablesBySysRoleIdxKey keySysRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newBySysRoleIdxKey();
				keySysRoleIdx.setRequiredSecSysRoleId( keepObj.getRequiredSecSysRoleId() );
				Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > mapSysRoleIdx = indexBySysRoleIdx.get( keySysRoleIdx );
				if( mapSysRoleIdx != null ) {
					mapSysRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecSysRoleEnablesByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newByNameIdxKey();
				keyNameIdx.setRequiredEnableName( keepObj.getRequiredEnableName() );
				Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecSysRoleEnables != null ) {
				allSecSysRoleEnables.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecSysRoleEnables != null ) {
				allSecSysRoleEnables.put( keepObj.getPKey(), keepObj );
			}

			if( indexBySysRoleIdx != null ) {
				ICFSecSecSysRoleEnablesBySysRoleIdxKey keySysRoleIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newBySysRoleIdxKey();
				keySysRoleIdx.setRequiredSecSysRoleId( keepObj.getRequiredSecSysRoleId() );
				Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > mapSysRoleIdx = indexBySysRoleIdx.get( keySysRoleIdx );
				if( mapSysRoleIdx != null ) {
					mapSysRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecSysRoleEnablesByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newByNameIdxKey();
				keyNameIdx.setRequiredEnableName( keepObj.getRequiredEnableName() );
				Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj createSecSysRoleEnables( ICFSecSecSysRoleEnablesObj Obj ) {
		ICFSecSecSysRoleEnablesObj obj = Obj;
		ICFSecSecSysRoleEnables rec = obj.getSecSysRoleEnablesRec();
		schema.getCFSecBackingStore().getTableSecSysRoleEnables().createSecSysRoleEnables(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj readSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey ) {
		return( readSecSysRoleEnables( pkey, false ) );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj readSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey, boolean forceRead ) {
		ICFSecSecSysRoleEnablesObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecSysRoleEnables readRec = schema.getCFSecBackingStore().getTableSecSysRoleEnables().readDerivedByIdIdx( null,
						pkey.getRequiredSecSysRoleId(),
						pkey.getRequiredEnableName() );
			if( readRec != null ) {
				obj = schema.getSecSysRoleEnablesTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecSysRoleEnablesObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj readSecSysRoleEnables( ICFLibKeyHash256 SecSysRoleId,
		String EnableName ) {
		return( readSecSysRoleEnables( SecSysRoleId,
			EnableName, false ) );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj readSecSysRoleEnables( ICFLibKeyHash256 SecSysRoleId,
		String EnableName, boolean forceRead ) {
		ICFSecSecSysRoleEnablesObj obj = null;
		ICFSecSecSysRoleEnables readRec = schema.getCFSecBackingStore().getTableSecSysRoleEnables().readDerivedByIdIdx( null,
			SecSysRoleId,
			EnableName );
		if( readRec != null ) {
				obj = schema.getSecSysRoleEnablesTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecSysRoleEnablesObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj readCachedSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey ) {
		ICFSecSecSysRoleEnablesObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecSysRoleEnables( ICFSecSecSysRoleEnablesObj obj )
	{
		final String S_ProcName = "CFSecSecSysRoleEnablesTableObj.reallyDeepDisposeSecSysRoleEnables() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecSysRoleEnablesPKey pkey = obj.getPKey();
		ICFSecSecSysRoleEnablesObj existing = readCachedSecSysRoleEnables( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecSysRoleEnablesBySysRoleIdxKey keySysRoleIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newBySysRoleIdxKey();
		keySysRoleIdx.setRequiredSecSysRoleId( existing.getRequiredSecSysRoleId() );

		ICFSecSecSysRoleEnablesByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newByNameIdxKey();
		keyNameIdx.setRequiredEnableName( existing.getRequiredEnableName() );



		if( indexBySysRoleIdx != null ) {
			if( indexBySysRoleIdx.containsKey( keySysRoleIdx ) ) {
				indexBySysRoleIdx.get( keySysRoleIdx ).remove( pkey );
				if( indexBySysRoleIdx.get( keySysRoleIdx ).size() <= 0 ) {
					indexBySysRoleIdx.remove( keySysRoleIdx );
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
	public void deepDisposeSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey ) {
		ICFSecSecSysRoleEnablesObj obj = readCachedSecSysRoleEnables( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecSysRoleEnablesObj lockSecSysRoleEnables( ICFSecSecSysRoleEnablesPKey pkey ) {
		ICFSecSecSysRoleEnablesObj locked = null;
		ICFSecSecSysRoleEnables lockRec = schema.getCFSecBackingStore().getTableSecSysRoleEnables().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecSysRoleEnablesTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecSysRoleEnablesObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecSysRoleEnables", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readAllSecSysRoleEnables() {
		return( readAllSecSysRoleEnables( false ) );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readAllSecSysRoleEnables( boolean forceRead ) {
		final String S_ProcName = "readAllSecSysRoleEnables";
		if( ( allSecSysRoleEnables == null ) || forceRead ) {
			Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> map = new HashMap<ICFSecSecSysRoleEnablesPKey,ICFSecSecSysRoleEnablesObj>();
			allSecSysRoleEnables = map;
			ICFSecSecSysRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecSysRoleEnables().readAllDerived( null );
			ICFSecSecSysRoleEnables rec;
			ICFSecSecSysRoleEnablesObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysRoleEnablesObj realised = (ICFSecSecSysRoleEnablesObj)obj.realise();
			}
		}
		int len = allSecSysRoleEnables.size();
		ICFSecSecSysRoleEnablesObj arr[] = new ICFSecSecSysRoleEnablesObj[len];
		Iterator<ICFSecSecSysRoleEnablesObj> valIter = allSecSysRoleEnables.values().iterator();
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
		ArrayList<ICFSecSecSysRoleEnablesObj> arrayList = new ArrayList<ICFSecSecSysRoleEnablesObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysRoleEnablesObj> cmp = new Comparator<ICFSecSecSysRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecSysRoleEnablesObj lhs, ICFSecSecSysRoleEnablesObj rhs ) {
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
					ICFSecSecSysRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysRoleEnablesObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readCachedAllSecSysRoleEnables() {
		final String S_ProcName = "readCachedAllSecSysRoleEnables";
		ArrayList<ICFSecSecSysRoleEnablesObj> arrayList = new ArrayList<ICFSecSecSysRoleEnablesObj>();
		if( allSecSysRoleEnables != null ) {
			int len = allSecSysRoleEnables.size();
			ICFSecSecSysRoleEnablesObj arr[] = new ICFSecSecSysRoleEnablesObj[len];
			Iterator<ICFSecSecSysRoleEnablesObj> valIter = allSecSysRoleEnables.values().iterator();
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
		Comparator<ICFSecSecSysRoleEnablesObj> cmp = new Comparator<ICFSecSecSysRoleEnablesObj>() {
			public int compare( ICFSecSecSysRoleEnablesObj lhs, ICFSecSecSysRoleEnablesObj rhs ) {
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
					ICFSecSecSysRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecSysRoleEnables-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecSysRoleEnablesObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecSysRoleEnablesObj> pageAllSecSysRoleEnables(ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName )
	{
		final String S_ProcName = "pageAllSecSysRoleEnables";
		Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> map = new HashMap<ICFSecSecSysRoleEnablesPKey,ICFSecSecSysRoleEnablesObj>();
		ICFSecSecSysRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecSysRoleEnables().pageAllRec( null,
			priorSecSysRoleId,
			priorEnableName );
		ICFSecSecSysRoleEnables rec;
		ICFSecSecSysRoleEnablesObj obj;
		ICFSecSecSysRoleEnablesObj realised;
		ArrayList<ICFSecSecSysRoleEnablesObj> arrayList = new ArrayList<ICFSecSecSysRoleEnablesObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecSysRoleEnablesObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj readSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName )
	{
		return( readSecSysRoleEnablesByIdIdx( SecSysRoleId,
			EnableName,
			false ) );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj readSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName, boolean forceRead )
	{
		ICFSecSecSysRoleEnablesPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newPKey();
		pkey.setRequiredSecSysRoleId( SecSysRoleId );
		pkey.setRequiredEnableName( EnableName );
		ICFSecSecSysRoleEnablesObj obj = readSecSysRoleEnables( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		return( readSecSysRoleEnablesBySysRoleIdx( SecSysRoleId,
			false ) );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysRoleEnablesBySysRoleIdx";
		ICFSecSecSysRoleEnablesBySysRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newBySysRoleIdxKey();
		key.setRequiredSecSysRoleId( SecSysRoleId );
		Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> dict;
		if( indexBySysRoleIdx == null ) {
			indexBySysRoleIdx = new HashMap< ICFSecSecSysRoleEnablesBySysRoleIdxKey,
				Map< ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > >();
		}
		if( ( ! forceRead ) && indexBySysRoleIdx.containsKey( key ) ) {
			dict = indexBySysRoleIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj>();
			ICFSecSecSysRoleEnablesObj obj;
			ICFSecSecSysRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecSysRoleEnables().readDerivedBySysRoleIdx( null,
				SecSysRoleId );
			ICFSecSecSysRoleEnables rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysRoleEnablesTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysRoleEnablesObj realised = (ICFSecSecSysRoleEnablesObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySysRoleIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysRoleEnablesObj arr[] = new ICFSecSecSysRoleEnablesObj[len];
		Iterator<ICFSecSecSysRoleEnablesObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysRoleEnablesObj> arrayList = new ArrayList<ICFSecSecSysRoleEnablesObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysRoleEnablesObj> cmp = new Comparator<ICFSecSecSysRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecSysRoleEnablesObj lhs, ICFSecSecSysRoleEnablesObj rhs ) {
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
					ICFSecSecSysRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysRoleEnablesObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readSecSysRoleEnablesByNameIdx( String EnableName )
	{
		return( readSecSysRoleEnablesByNameIdx( EnableName,
			false ) );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readSecSysRoleEnablesByNameIdx( String EnableName,
		boolean forceRead )
	{
		final String S_ProcName = "readSecSysRoleEnablesByNameIdx";
		ICFSecSecSysRoleEnablesByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newByNameIdxKey();
		key.setRequiredEnableName( EnableName );
		Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecSysRoleEnablesByNameIdxKey,
				Map< ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj>();
			ICFSecSecSysRoleEnablesObj obj;
			ICFSecSecSysRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecSysRoleEnables().readDerivedByNameIdx( null,
				EnableName );
			ICFSecSecSysRoleEnables rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecSysRoleEnablesTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecSysRoleEnablesObj realised = (ICFSecSecSysRoleEnablesObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecSysRoleEnablesObj arr[] = new ICFSecSecSysRoleEnablesObj[len];
		Iterator<ICFSecSecSysRoleEnablesObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecSysRoleEnablesObj> arrayList = new ArrayList<ICFSecSecSysRoleEnablesObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecSysRoleEnablesObj> cmp = new Comparator<ICFSecSecSysRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecSysRoleEnablesObj lhs, ICFSecSecSysRoleEnablesObj rhs ) {
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
					ICFSecSecSysRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecSysRoleEnablesObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj readCachedSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName )
	{
		ICFSecSecSysRoleEnablesObj obj = null;
		ICFSecSecSysRoleEnablesPKey pkey = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newPKey();
		pkey.setRequiredSecSysRoleId( SecSysRoleId );
		pkey.setRequiredEnableName( EnableName );
		pkey.setRequiredSecSysRoleId( SecSysRoleId );
		pkey.setRequiredEnableName( EnableName );
		obj = readCachedSecSysRoleEnables( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readCachedSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		final String S_ProcName = "readCachedSecSysRoleEnablesBySysRoleIdx";
		ICFSecSecSysRoleEnablesBySysRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newBySysRoleIdxKey();
		key.setRequiredSecSysRoleId( SecSysRoleId );
		ArrayList<ICFSecSecSysRoleEnablesObj> arrayList = new ArrayList<ICFSecSecSysRoleEnablesObj>();
		if( indexBySysRoleIdx != null ) {
			Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> dict;
			if( indexBySysRoleIdx.containsKey( key ) ) {
				dict = indexBySysRoleIdx.get( key );
				int len = dict.size();
				ICFSecSecSysRoleEnablesObj arr[] = new ICFSecSecSysRoleEnablesObj[len];
				Iterator<ICFSecSecSysRoleEnablesObj> valIter = dict.values().iterator();
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
			ICFSecSecSysRoleEnablesObj obj;
			Iterator<ICFSecSecSysRoleEnablesObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysRoleEnablesObj> cmp = new Comparator<ICFSecSecSysRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecSysRoleEnablesObj lhs, ICFSecSecSysRoleEnablesObj rhs ) {
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
					ICFSecSecSysRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecSysRoleEnablesObj> readCachedSecSysRoleEnablesByNameIdx( String EnableName )
	{
		final String S_ProcName = "readCachedSecSysRoleEnablesByNameIdx";
		ICFSecSecSysRoleEnablesByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newByNameIdxKey();
		key.setRequiredEnableName( EnableName );
		ArrayList<ICFSecSecSysRoleEnablesObj> arrayList = new ArrayList<ICFSecSecSysRoleEnablesObj>();
		if( indexByNameIdx != null ) {
			Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecSysRoleEnablesObj arr[] = new ICFSecSecSysRoleEnablesObj[len];
				Iterator<ICFSecSecSysRoleEnablesObj> valIter = dict.values().iterator();
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
			ICFSecSecSysRoleEnablesObj obj;
			Iterator<ICFSecSecSysRoleEnablesObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecSysRoleEnablesObj> cmp = new Comparator<ICFSecSecSysRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecSysRoleEnablesObj lhs, ICFSecSecSysRoleEnablesObj rhs ) {
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
					ICFSecSecSysRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecSysRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName )
	{
		ICFSecSecSysRoleEnablesObj obj = readCachedSecSysRoleEnablesByIdIdx( SecSysRoleId,
				EnableName );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		final String S_ProcName = "deepDisposeSecSysRoleEnablesBySysRoleIdx";
		ICFSecSecSysRoleEnablesObj obj;
		List<ICFSecSecSysRoleEnablesObj> arrayList = readCachedSecSysRoleEnablesBySysRoleIdx( SecSysRoleId );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysRoleEnablesObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecSysRoleEnablesByNameIdx( String EnableName )
	{
		final String S_ProcName = "deepDisposeSecSysRoleEnablesByNameIdx";
		ICFSecSecSysRoleEnablesObj obj;
		List<ICFSecSecSysRoleEnablesObj> arrayList = readCachedSecSysRoleEnablesByNameIdx( EnableName );
		if( arrayList != null )  {
			Iterator<ICFSecSecSysRoleEnablesObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecSysRoleEnables-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SysRoleIdx key attributes.
	 *
	 *	@param	SecSysRoleId	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysRoleEnables-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysRoleEnablesObj> pageSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName )
	{
		final String S_ProcName = "pageSecSysRoleEnablesBySysRoleIdx";
		ICFSecSecSysRoleEnablesBySysRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newBySysRoleIdxKey();
		key.setRequiredSecSysRoleId( SecSysRoleId );
		List<ICFSecSecSysRoleEnablesObj> retList = new LinkedList<ICFSecSecSysRoleEnablesObj>();
		ICFSecSecSysRoleEnablesObj obj;
		ICFSecSecSysRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecSysRoleEnables().pageRecBySysRoleIdx( null,
				SecSysRoleId,
			priorSecSysRoleId,
			priorEnableName );
		ICFSecSecSysRoleEnables rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysRoleEnablesTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysRoleEnablesObj realised = (ICFSecSecSysRoleEnablesObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecSysRoleEnables-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NameIdx key attributes.
	 *
	 *	@param	EnableName	The SecSysRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecSysRoleEnables-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecSysRoleEnablesObj> pageSecSysRoleEnablesByNameIdx( String EnableName,
		ICFLibKeyHash256 priorSecSysRoleId,
		String priorEnableName )
	{
		final String S_ProcName = "pageSecSysRoleEnablesByNameIdx";
		ICFSecSecSysRoleEnablesByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newByNameIdxKey();
		key.setRequiredEnableName( EnableName );
		List<ICFSecSecSysRoleEnablesObj> retList = new LinkedList<ICFSecSecSysRoleEnablesObj>();
		ICFSecSecSysRoleEnablesObj obj;
		ICFSecSecSysRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecSysRoleEnables().pageRecByNameIdx( null,
				EnableName,
			priorSecSysRoleId,
			priorEnableName );
		ICFSecSecSysRoleEnables rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecSysRoleEnablesTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecSysRoleEnablesObj realised = (ICFSecSecSysRoleEnablesObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecSysRoleEnablesObj updateSecSysRoleEnables( ICFSecSecSysRoleEnablesObj Obj ) {
		ICFSecSecSysRoleEnablesObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysRoleEnables().updateSecSysRoleEnables( null,
			Obj.getSecSysRoleEnablesRec() );
		obj = (ICFSecSecSysRoleEnablesObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecSysRoleEnables( ICFSecSecSysRoleEnablesObj Obj ) {
		ICFSecSecSysRoleEnablesObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecSysRoleEnables().deleteSecSysRoleEnables( null,
			obj.getSecSysRoleEnablesRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecSysRoleEnablesByIdIdx( ICFLibKeyHash256 SecSysRoleId,
		String EnableName )
	{
		ICFSecSecSysRoleEnablesObj obj = readSecSysRoleEnables(SecSysRoleId,
				EnableName);
		if( obj != null ) {
			ICFSecSecSysRoleEnablesEditObj editObj = (ICFSecSecSysRoleEnablesEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecSysRoleEnablesEditObj)obj.beginEdit();
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
		deepDisposeSecSysRoleEnablesByIdIdx( SecSysRoleId,
				EnableName );
	}

	@Override
	public void deleteSecSysRoleEnablesBySysRoleIdx( ICFLibKeyHash256 SecSysRoleId )
	{
		ICFSecSecSysRoleEnablesBySysRoleIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newBySysRoleIdxKey();
		key.setRequiredSecSysRoleId( SecSysRoleId );
		if( indexBySysRoleIdx == null ) {
			indexBySysRoleIdx = new HashMap< ICFSecSecSysRoleEnablesBySysRoleIdxKey,
				Map< ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > >();
		}
		if( indexBySysRoleIdx.containsKey( key ) ) {
			Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> dict = indexBySysRoleIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysRoleEnables().deleteSecSysRoleEnablesBySysRoleIdx( null,
				SecSysRoleId );
			Iterator<ICFSecSecSysRoleEnablesObj> iter = dict.values().iterator();
			ICFSecSecSysRoleEnablesObj obj;
			List<ICFSecSecSysRoleEnablesObj> toForget = new LinkedList<ICFSecSecSysRoleEnablesObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySysRoleIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecSysRoleEnables().deleteSecSysRoleEnablesBySysRoleIdx( null,
				SecSysRoleId );
		}
		deepDisposeSecSysRoleEnablesBySysRoleIdx( SecSysRoleId );
	}

	@Override
	public void deleteSecSysRoleEnablesByNameIdx( String EnableName )
	{
		ICFSecSecSysRoleEnablesByNameIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecSysRoleEnables().newByNameIdxKey();
		key.setRequiredEnableName( EnableName );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecSysRoleEnablesByNameIdxKey,
				Map< ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<ICFSecSecSysRoleEnablesPKey, ICFSecSecSysRoleEnablesObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecSysRoleEnables().deleteSecSysRoleEnablesByNameIdx( null,
				EnableName );
			Iterator<ICFSecSecSysRoleEnablesObj> iter = dict.values().iterator();
			ICFSecSecSysRoleEnablesObj obj;
			List<ICFSecSecSysRoleEnablesObj> toForget = new LinkedList<ICFSecSecSysRoleEnablesObj>();
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
			schema.getCFSecBackingStore().getTableSecSysRoleEnables().deleteSecSysRoleEnablesByNameIdx( null,
				EnableName );
		}
		deepDisposeSecSysRoleEnablesByNameIdx( EnableName );
	}
}