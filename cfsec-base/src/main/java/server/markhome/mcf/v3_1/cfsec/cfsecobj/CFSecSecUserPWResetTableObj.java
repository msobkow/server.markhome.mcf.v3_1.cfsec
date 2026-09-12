// Description: Java 25 Table Object implementation for SecUserPWReset.

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

public class CFSecSecUserPWResetTableObj
	implements ICFSecSecUserPWResetTableObj
{
	protected ICFSecSchemaObj schema;
	protected static int runtimeClassCode = ICFSecSecUserPWReset.CLASS_CODE;
	protected static final int backingClassCode = ICFSecSecUserPWReset.CLASS_CODE;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> members;
	private Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> allSecUserPWReset;
	private Map< ICFSecSecUserPWResetByUUuid6IdxKey,
		ICFSecSecUserPWResetObj > indexByUUuid6Idx;
	private Map< ICFSecSecUserPWResetBySentEMAddrIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > > indexBySentEMAddrIdx;
	private Map< ICFSecSecUserPWResetByNewAcctIdxKey,
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > > indexByNewAcctIdx;
	public static String TABLE_NAME = "SecUserPWReset";
	public static String TABLE_DBNAME = "secusrpwrst";

	public CFSecSecUserPWResetTableObj() {
		schema = null;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj>();
		allSecUserPWReset = null;
		indexByUUuid6Idx = null;
		indexBySentEMAddrIdx = null;
		indexByNewAcctIdx = null;
	}

	public CFSecSecUserPWResetTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFSecSchemaObj)argSchema;
		members = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj>();
		allSecUserPWReset = null;
		indexByUUuid6Idx = null;
		indexBySentEMAddrIdx = null;
		indexByNewAcctIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecUserPWResetTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFSecSecUserPWResetTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allSecUserPWReset = null;
		indexByUUuid6Idx = null;
		indexBySentEMAddrIdx = null;
		indexByNewAcctIdx = null;
		List<ICFSecSecUserPWResetObj> toForget = new LinkedList<ICFSecSecUserPWResetObj>();
		ICFSecSecUserPWResetObj cur = null;
		Iterator<ICFSecSecUserPWResetObj> iter = members.values().iterator();
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
	 *	CFSecSecUserPWResetObj.
	 */
	@Override
	public ICFSecSecUserPWResetObj newInstance() {
		ICFSecSecUserPWResetObj inst = new CFSecSecUserPWResetObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFSecSecUserPWResetObj.
	 */
	@Override
	public ICFSecSecUserPWResetEditObj newEditInstance( ICFSecSecUserPWResetObj orig ) {
		ICFSecSecUserPWResetEditObj edit = new CFSecSecUserPWResetEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecUserPWResetObj realiseSecUserPWReset( ICFSecSecUserPWResetObj Obj ) {
		ICFSecSecUserPWResetObj obj = Obj;
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecUserPWResetObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecUserPWResetObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUUuid6Idx != null ) {
				ICFSecSecUserPWResetByUUuid6IdxKey keyUUuid6Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByUUuid6IdxKey();
				keyUUuid6Idx.setRequiredPasswordResetUuid6( keepObj.getRequiredPasswordResetUuid6() );
				indexByUUuid6Idx.remove( keyUUuid6Idx );
			}

			if( indexBySentEMAddrIdx != null ) {
				ICFSecSecUserPWResetBySentEMAddrIdxKey keySentEMAddrIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newBySentEMAddrIdxKey();
				keySentEMAddrIdx.setRequiredSentToEMailAddr( keepObj.getRequiredSentToEMailAddr() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > mapSentEMAddrIdx = indexBySentEMAddrIdx.get( keySentEMAddrIdx );
				if( mapSentEMAddrIdx != null ) {
					mapSentEMAddrIdx.remove( keepObj.getPKey() );
					if( mapSentEMAddrIdx.size() <= 0 ) {
						indexBySentEMAddrIdx.remove( keySentEMAddrIdx );
					}
				}
			}

			if( indexByNewAcctIdx != null ) {
				ICFSecSecUserPWResetByNewAcctIdxKey keyNewAcctIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByNewAcctIdxKey();
				keyNewAcctIdx.setRequiredNewAccount( keepObj.getRequiredNewAccount() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > mapNewAcctIdx = indexByNewAcctIdx.get( keyNewAcctIdx );
				if( mapNewAcctIdx != null ) {
					mapNewAcctIdx.remove( keepObj.getPKey() );
					if( mapNewAcctIdx.size() <= 0 ) {
						indexByNewAcctIdx.remove( keyNewAcctIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUUuid6Idx != null ) {
				ICFSecSecUserPWResetByUUuid6IdxKey keyUUuid6Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByUUuid6IdxKey();
				keyUUuid6Idx.setRequiredPasswordResetUuid6( keepObj.getRequiredPasswordResetUuid6() );
				indexByUUuid6Idx.put( keyUUuid6Idx, keepObj );
			}

			if( indexBySentEMAddrIdx != null ) {
				ICFSecSecUserPWResetBySentEMAddrIdxKey keySentEMAddrIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newBySentEMAddrIdxKey();
				keySentEMAddrIdx.setRequiredSentToEMailAddr( keepObj.getRequiredSentToEMailAddr() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > mapSentEMAddrIdx = indexBySentEMAddrIdx.get( keySentEMAddrIdx );
				if( mapSentEMAddrIdx != null ) {
					mapSentEMAddrIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNewAcctIdx != null ) {
				ICFSecSecUserPWResetByNewAcctIdxKey keyNewAcctIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByNewAcctIdxKey();
				keyNewAcctIdx.setRequiredNewAccount( keepObj.getRequiredNewAccount() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > mapNewAcctIdx = indexByNewAcctIdx.get( keyNewAcctIdx );
				if( mapNewAcctIdx != null ) {
					mapNewAcctIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecUserPWReset != null ) {
				allSecUserPWReset.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecUserPWReset != null ) {
				allSecUserPWReset.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUUuid6Idx != null ) {
				ICFSecSecUserPWResetByUUuid6IdxKey keyUUuid6Idx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByUUuid6IdxKey();
				keyUUuid6Idx.setRequiredPasswordResetUuid6( keepObj.getRequiredPasswordResetUuid6() );
				indexByUUuid6Idx.put( keyUUuid6Idx, keepObj );
			}

			if( indexBySentEMAddrIdx != null ) {
				ICFSecSecUserPWResetBySentEMAddrIdxKey keySentEMAddrIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newBySentEMAddrIdxKey();
				keySentEMAddrIdx.setRequiredSentToEMailAddr( keepObj.getRequiredSentToEMailAddr() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > mapSentEMAddrIdx = indexBySentEMAddrIdx.get( keySentEMAddrIdx );
				if( mapSentEMAddrIdx != null ) {
					mapSentEMAddrIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNewAcctIdx != null ) {
				ICFSecSecUserPWResetByNewAcctIdxKey keyNewAcctIdx =
					schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByNewAcctIdxKey();
				keyNewAcctIdx.setRequiredNewAccount( keepObj.getRequiredNewAccount() );
				Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > mapNewAcctIdx = indexByNewAcctIdx.get( keyNewAcctIdx );
				if( mapNewAcctIdx != null ) {
					mapNewAcctIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecUserPWResetObj createSecUserPWReset( ICFSecSecUserPWResetObj Obj ) {
		ICFSecSecUserPWResetObj obj = Obj;
		ICFSecSecUserPWReset rec = obj.getSecUserPWResetRec();
		schema.getCFSecBackingStore().getTableSecUserPWReset().createSecUserPWReset(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecUserPWResetObj readSecUserPWReset( $implCommaIJavaOptAtomType$ pkey ) {
		return( readSecUserPWReset( pkey, false ) );
	}

	@Override
	public ICFSecSecUserPWResetObj readSecUserPWReset( $implCommaIJavaOptAtomType$ pkey, boolean forceRead ) {
		ICFSecSecUserPWResetObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecUserPWReset readRec = schema.getCFSecBackingStore().getTableSecUserPWReset().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecUserPWResetTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecUserPWResetObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserPWResetObj readCachedSecUserPWReset( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserPWResetObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecUserPWReset( ICFSecSecUserPWResetObj obj )
	{
		final String S_ProcName = "CFSecSecUserPWResetTableObj.reallyDeepDisposeSecUserPWReset() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		$implCommaIJavaOptAtomType$ pkey = obj.getPKey();
		ICFSecSecUserPWResetObj existing = readCachedSecUserPWReset( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecUserPWResetByUUuid6IdxKey keyUUuid6Idx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByUUuid6IdxKey();
		keyUUuid6Idx.setRequiredPasswordResetUuid6( existing.getRequiredPasswordResetUuid6() );

		ICFSecSecUserPWResetBySentEMAddrIdxKey keySentEMAddrIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newBySentEMAddrIdxKey();
		keySentEMAddrIdx.setRequiredSentToEMailAddr( existing.getRequiredSentToEMailAddr() );

		ICFSecSecUserPWResetByNewAcctIdxKey keyNewAcctIdx = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByNewAcctIdxKey();
		keyNewAcctIdx.setRequiredNewAccount( existing.getRequiredNewAccount() );



		if( indexByUUuid6Idx != null ) {
			indexByUUuid6Idx.remove( keyUUuid6Idx );
		}

		if( indexBySentEMAddrIdx != null ) {
			if( indexBySentEMAddrIdx.containsKey( keySentEMAddrIdx ) ) {
				indexBySentEMAddrIdx.get( keySentEMAddrIdx ).remove( pkey );
				if( indexBySentEMAddrIdx.get( keySentEMAddrIdx ).size() <= 0 ) {
					indexBySentEMAddrIdx.remove( keySentEMAddrIdx );
				}
			}
		}

		if( indexByNewAcctIdx != null ) {
			if( indexByNewAcctIdx.containsKey( keyNewAcctIdx ) ) {
				indexByNewAcctIdx.get( keyNewAcctIdx ).remove( pkey );
				if( indexByNewAcctIdx.get( keyNewAcctIdx ).size() <= 0 ) {
					indexByNewAcctIdx.remove( keyNewAcctIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecUserPWReset( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserPWResetObj obj = readCachedSecUserPWReset( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecUserPWResetObj lockSecUserPWReset( $implCommaIJavaOptAtomType$ pkey ) {
		ICFSecSecUserPWResetObj locked = null;
		ICFSecSecUserPWReset lockRec = schema.getCFSecBackingStore().getTableSecUserPWReset().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecUserPWResetTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecUserPWResetObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecUserPWReset", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecUserPWResetObj> readAllSecUserPWReset() {
		return( readAllSecUserPWReset( false ) );
	}

	@Override
	public List<ICFSecSecUserPWResetObj> readAllSecUserPWReset( boolean forceRead ) {
		final String S_ProcName = "readAllSecUserPWReset";
		if( ( allSecUserPWReset == null ) || forceRead ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecUserPWResetObj>();
			allSecUserPWReset = map;
			ICFSecSecUserPWReset[] recList = schema.getCFSecBackingStore().getTableSecUserPWReset().readAllDerived( null );
			ICFSecSecUserPWReset rec;
			ICFSecSecUserPWResetObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserPWResetObj realised = (ICFSecSecUserPWResetObj)obj.realise();
			}
		}
		int len = allSecUserPWReset.size();
		ICFSecSecUserPWResetObj arr[] = new ICFSecSecUserPWResetObj[len];
		Iterator<ICFSecSecUserPWResetObj> valIter = allSecUserPWReset.values().iterator();
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
		ArrayList<ICFSecSecUserPWResetObj> arrayList = new ArrayList<ICFSecSecUserPWResetObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserPWResetObj> cmp = new Comparator<ICFSecSecUserPWResetObj>() {
			@Override
			public int compare( ICFSecSecUserPWResetObj lhs, ICFSecSecUserPWResetObj rhs ) {
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
		List<ICFSecSecUserPWResetObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserPWResetObj> readCachedAllSecUserPWReset() {
		final String S_ProcName = "readCachedAllSecUserPWReset";
		ArrayList<ICFSecSecUserPWResetObj> arrayList = new ArrayList<ICFSecSecUserPWResetObj>();
		if( allSecUserPWReset != null ) {
			int len = allSecUserPWReset.size();
			ICFSecSecUserPWResetObj arr[] = new ICFSecSecUserPWResetObj[len];
			Iterator<ICFSecSecUserPWResetObj> valIter = allSecUserPWReset.values().iterator();
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
		Comparator<ICFSecSecUserPWResetObj> cmp = new Comparator<ICFSecSecUserPWResetObj>() {
			public int compare( ICFSecSecUserPWResetObj lhs, ICFSecSecUserPWResetObj rhs ) {
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
	 *	Return a sorted map of a page of the SecUserPWReset-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserPWResetObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecUserPWResetObj> pageAllSecUserPWReset(ICFLibKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageAllSecUserPWReset";
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> map = new HashMap<$implCommaIJavaOptAtomType$,ICFSecSecUserPWResetObj>();
		ICFSecSecUserPWReset[] recList = schema.getCFSecBackingStore().getTableSecUserPWReset().pageAllRec( null,
			priorSecUserId );
		ICFSecSecUserPWReset rec;
		ICFSecSecUserPWResetObj obj;
		ICFSecSecUserPWResetObj realised;
		ArrayList<ICFSecSecUserPWResetObj> arrayList = new ArrayList<ICFSecSecUserPWResetObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecUserPWResetObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecUserPWResetObj readSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		return( readSecUserPWResetByIdIdx( SecUserId,
			false ) );
	}

	@Override
	public ICFSecSecUserPWResetObj readSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId, boolean forceRead )
	{
		ICFSecSecUserPWResetObj obj = readSecUserPWReset( SecUserId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecSecUserPWResetObj readSecUserPWResetByUUuid6Idx( ICFLibUuid6 PasswordResetUuid6 )
	{
		return( readSecUserPWResetByUUuid6Idx( PasswordResetUuid6,
			false ) );
	}

	@Override
	public ICFSecSecUserPWResetObj readSecUserPWResetByUUuid6Idx( ICFLibUuid6 PasswordResetUuid6, boolean forceRead )
	{
		if( indexByUUuid6Idx == null ) {
			indexByUUuid6Idx = new HashMap< ICFSecSecUserPWResetByUUuid6IdxKey,
				ICFSecSecUserPWResetObj >();
		}
		ICFSecSecUserPWResetByUUuid6IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByUUuid6IdxKey();
		key.setRequiredPasswordResetUuid6( PasswordResetUuid6 );
		ICFSecSecUserPWResetObj obj = null;
		if( ( ! forceRead ) && indexByUUuid6Idx.containsKey( key ) ) {
			obj = indexByUUuid6Idx.get( key );
		}
		else {
			ICFSecSecUserPWReset rec = schema.getCFSecBackingStore().getTableSecUserPWReset().readDerivedByUUuid6Idx( null,
				PasswordResetUuid6 );
			if( rec != null ) {
				obj = schema.getSecUserPWResetTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecUserPWResetObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecSecUserPWResetObj> readSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr )
	{
		return( readSecUserPWResetBySentEMAddrIdx( SentToEMailAddr,
			false ) );
	}

	@Override
	public List<ICFSecSecUserPWResetObj> readSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr,
		boolean forceRead )
	{
		final String S_ProcName = "readSecUserPWResetBySentEMAddrIdx";
		ICFSecSecUserPWResetBySentEMAddrIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newBySentEMAddrIdxKey();
		key.setRequiredSentToEMailAddr( SentToEMailAddr );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> dict;
		if( indexBySentEMAddrIdx == null ) {
			indexBySentEMAddrIdx = new HashMap< ICFSecSecUserPWResetBySentEMAddrIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > >();
		}
		if( ( ! forceRead ) && indexBySentEMAddrIdx.containsKey( key ) ) {
			dict = indexBySentEMAddrIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj>();
			ICFSecSecUserPWResetObj obj;
			ICFSecSecUserPWReset[] recList = schema.getCFSecBackingStore().getTableSecUserPWReset().readDerivedBySentEMAddrIdx( null,
				SentToEMailAddr );
			ICFSecSecUserPWReset rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecUserPWResetTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserPWResetObj realised = (ICFSecSecUserPWResetObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySentEMAddrIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecUserPWResetObj arr[] = new ICFSecSecUserPWResetObj[len];
		Iterator<ICFSecSecUserPWResetObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecUserPWResetObj> arrayList = new ArrayList<ICFSecSecUserPWResetObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserPWResetObj> cmp = new Comparator<ICFSecSecUserPWResetObj>() {
			@Override
			public int compare( ICFSecSecUserPWResetObj lhs, ICFSecSecUserPWResetObj rhs ) {
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
		List<ICFSecSecUserPWResetObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserPWResetObj> readSecUserPWResetByNewAcctIdx( boolean NewAccount )
	{
		return( readSecUserPWResetByNewAcctIdx( NewAccount,
			false ) );
	}

	@Override
	public List<ICFSecSecUserPWResetObj> readSecUserPWResetByNewAcctIdx( boolean NewAccount,
		boolean forceRead )
	{
		final String S_ProcName = "readSecUserPWResetByNewAcctIdx";
		ICFSecSecUserPWResetByNewAcctIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByNewAcctIdxKey();
		key.setRequiredNewAccount( NewAccount );
		Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> dict;
		if( indexByNewAcctIdx == null ) {
			indexByNewAcctIdx = new HashMap< ICFSecSecUserPWResetByNewAcctIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > >();
		}
		if( ( ! forceRead ) && indexByNewAcctIdx.containsKey( key ) ) {
			dict = indexByNewAcctIdx.get( key );
		}
		else {
			dict = new HashMap<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj>();
			ICFSecSecUserPWResetObj obj;
			ICFSecSecUserPWReset[] recList = schema.getCFSecBackingStore().getTableSecUserPWReset().readDerivedByNewAcctIdx( null,
				NewAccount );
			ICFSecSecUserPWReset rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecUserPWResetTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserPWResetObj realised = (ICFSecSecUserPWResetObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNewAcctIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecUserPWResetObj arr[] = new ICFSecSecUserPWResetObj[len];
		Iterator<ICFSecSecUserPWResetObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecUserPWResetObj> arrayList = new ArrayList<ICFSecSecUserPWResetObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserPWResetObj> cmp = new Comparator<ICFSecSecUserPWResetObj>() {
			@Override
			public int compare( ICFSecSecUserPWResetObj lhs, ICFSecSecUserPWResetObj rhs ) {
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
		List<ICFSecSecUserPWResetObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecUserPWResetObj readCachedSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserPWResetObj obj = null;
		obj = readCachedSecUserPWReset( SecUserId );
		return( obj );
	}

	@Override
	public ICFSecSecUserPWResetObj readCachedSecUserPWResetByUUuid6Idx( ICFLibUuid6 PasswordResetUuid6 )
	{
		ICFSecSecUserPWResetObj obj = null;
		ICFSecSecUserPWResetByUUuid6IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByUUuid6IdxKey();
		key.setRequiredPasswordResetUuid6( PasswordResetUuid6 );
		if( indexByUUuid6Idx != null ) {
			if( indexByUUuid6Idx.containsKey( key ) ) {
				obj = indexByUUuid6Idx.get( key );
			}
			else {
				Iterator<ICFSecSecUserPWResetObj> valIter = members.values().iterator();
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
			Iterator<ICFSecSecUserPWResetObj> valIter = members.values().iterator();
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
	public List<ICFSecSecUserPWResetObj> readCachedSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr )
	{
		final String S_ProcName = "readCachedSecUserPWResetBySentEMAddrIdx";
		ICFSecSecUserPWResetBySentEMAddrIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newBySentEMAddrIdxKey();
		key.setRequiredSentToEMailAddr( SentToEMailAddr );
		ArrayList<ICFSecSecUserPWResetObj> arrayList = new ArrayList<ICFSecSecUserPWResetObj>();
		if( indexBySentEMAddrIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> dict;
			if( indexBySentEMAddrIdx.containsKey( key ) ) {
				dict = indexBySentEMAddrIdx.get( key );
				int len = dict.size();
				ICFSecSecUserPWResetObj arr[] = new ICFSecSecUserPWResetObj[len];
				Iterator<ICFSecSecUserPWResetObj> valIter = dict.values().iterator();
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
			ICFSecSecUserPWResetObj obj;
			Iterator<ICFSecSecUserPWResetObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecUserPWResetObj> cmp = new Comparator<ICFSecSecUserPWResetObj>() {
			@Override
			public int compare( ICFSecSecUserPWResetObj lhs, ICFSecSecUserPWResetObj rhs ) {
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
	public List<ICFSecSecUserPWResetObj> readCachedSecUserPWResetByNewAcctIdx( boolean NewAccount )
	{
		final String S_ProcName = "readCachedSecUserPWResetByNewAcctIdx";
		ICFSecSecUserPWResetByNewAcctIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByNewAcctIdxKey();
		key.setRequiredNewAccount( NewAccount );
		ArrayList<ICFSecSecUserPWResetObj> arrayList = new ArrayList<ICFSecSecUserPWResetObj>();
		if( indexByNewAcctIdx != null ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> dict;
			if( indexByNewAcctIdx.containsKey( key ) ) {
				dict = indexByNewAcctIdx.get( key );
				int len = dict.size();
				ICFSecSecUserPWResetObj arr[] = new ICFSecSecUserPWResetObj[len];
				Iterator<ICFSecSecUserPWResetObj> valIter = dict.values().iterator();
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
			ICFSecSecUserPWResetObj obj;
			Iterator<ICFSecSecUserPWResetObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecUserPWResetObj> cmp = new Comparator<ICFSecSecUserPWResetObj>() {
			@Override
			public int compare( ICFSecSecUserPWResetObj lhs, ICFSecSecUserPWResetObj rhs ) {
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
	public void deepDisposeSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserPWResetObj obj = readCachedSecUserPWResetByIdIdx( SecUserId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserPWResetByUUuid6Idx( ICFLibUuid6 PasswordResetUuid6 )
	{
		ICFSecSecUserPWResetObj obj = readCachedSecUserPWResetByUUuid6Idx( PasswordResetUuid6 );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr )
	{
		final String S_ProcName = "deepDisposeSecUserPWResetBySentEMAddrIdx";
		ICFSecSecUserPWResetObj obj;
		List<ICFSecSecUserPWResetObj> arrayList = readCachedSecUserPWResetBySentEMAddrIdx( SentToEMailAddr );
		if( arrayList != null )  {
			Iterator<ICFSecSecUserPWResetObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecUserPWResetByNewAcctIdx( boolean NewAccount )
	{
		final String S_ProcName = "deepDisposeSecUserPWResetByNewAcctIdx";
		ICFSecSecUserPWResetObj obj;
		List<ICFSecSecUserPWResetObj> arrayList = readCachedSecUserPWResetByNewAcctIdx( NewAccount );
		if( arrayList != null )  {
			Iterator<ICFSecSecUserPWResetObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecUserPWReset-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SentEMAddrIdx key attributes.
	 *
	 *	@param	SentToEMailAddr	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserPWReset-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecUserPWResetObj> pageSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr,
		ICFLibKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecUserPWResetBySentEMAddrIdx";
		ICFSecSecUserPWResetBySentEMAddrIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newBySentEMAddrIdxKey();
		key.setRequiredSentToEMailAddr( SentToEMailAddr );
		List<ICFSecSecUserPWResetObj> retList = new LinkedList<ICFSecSecUserPWResetObj>();
		ICFSecSecUserPWResetObj obj;
		ICFSecSecUserPWReset[] recList = schema.getCFSecBackingStore().getTableSecUserPWReset().pageRecBySentEMAddrIdx( null,
				SentToEMailAddr,
			priorSecUserId );
		ICFSecSecUserPWReset rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecUserPWResetTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecUserPWResetObj realised = (ICFSecSecUserPWResetObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecUserPWReset-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NewAcctIdx key attributes.
	 *
	 *	@param	NewAccount	The SecUserPWReset key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserPWReset-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecUserPWResetObj> pageSecUserPWResetByNewAcctIdx( boolean NewAccount,
		ICFLibKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecUserPWResetByNewAcctIdx";
		ICFSecSecUserPWResetByNewAcctIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByNewAcctIdxKey();
		key.setRequiredNewAccount( NewAccount );
		List<ICFSecSecUserPWResetObj> retList = new LinkedList<ICFSecSecUserPWResetObj>();
		ICFSecSecUserPWResetObj obj;
		ICFSecSecUserPWReset[] recList = schema.getCFSecBackingStore().getTableSecUserPWReset().pageRecByNewAcctIdx( null,
				NewAccount,
			priorSecUserId );
		ICFSecSecUserPWReset rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecUserPWResetTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecUserPWResetObj realised = (ICFSecSecUserPWResetObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecUserPWResetObj updateSecUserPWReset( ICFSecSecUserPWResetObj Obj ) {
		ICFSecSecUserPWResetObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserPWReset().updateSecUserPWReset( null,
			Obj.getSecUserPWResetRec() );
		obj = (ICFSecSecUserPWResetObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecUserPWReset( ICFSecSecUserPWResetObj Obj ) {
		ICFSecSecUserPWResetObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserPWReset().deleteSecUserPWReset( null,
			obj.getSecUserPWResetRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecUserPWResetByIdIdx( ICFLibKeyHash256 SecUserId )
	{
		ICFSecSecUserPWResetObj obj = readSecUserPWReset(SecUserId);
		if( obj != null ) {
			ICFSecSecUserPWResetEditObj editObj = (ICFSecSecUserPWResetEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecUserPWResetEditObj)obj.beginEdit();
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
		deepDisposeSecUserPWResetByIdIdx( SecUserId );
	}

	@Override
	public void deleteSecUserPWResetByUUuid6Idx( ICFLibUuid6 PasswordResetUuid6 )
	{
		if( indexByUUuid6Idx == null ) {
			indexByUUuid6Idx = new HashMap< ICFSecSecUserPWResetByUUuid6IdxKey,
				ICFSecSecUserPWResetObj >();
		}
		ICFSecSecUserPWResetByUUuid6IdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByUUuid6IdxKey();
		key.setRequiredPasswordResetUuid6( PasswordResetUuid6 );
		ICFSecSecUserPWResetObj obj = null;
		if( indexByUUuid6Idx.containsKey( key ) ) {
			obj = indexByUUuid6Idx.get( key );
			schema.getCFSecBackingStore().getTableSecUserPWReset().deleteSecUserPWResetByUUuid6Idx( null,
				PasswordResetUuid6 );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserPWReset().deleteSecUserPWResetByUUuid6Idx( null,
				PasswordResetUuid6 );
		}
		deepDisposeSecUserPWResetByUUuid6Idx( PasswordResetUuid6 );
	}

	@Override
	public void deleteSecUserPWResetBySentEMAddrIdx( String SentToEMailAddr )
	{
		ICFSecSecUserPWResetBySentEMAddrIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newBySentEMAddrIdxKey();
		key.setRequiredSentToEMailAddr( SentToEMailAddr );
		if( indexBySentEMAddrIdx == null ) {
			indexBySentEMAddrIdx = new HashMap< ICFSecSecUserPWResetBySentEMAddrIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > >();
		}
		if( indexBySentEMAddrIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> dict = indexBySentEMAddrIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserPWReset().deleteSecUserPWResetBySentEMAddrIdx( null,
				SentToEMailAddr );
			Iterator<ICFSecSecUserPWResetObj> iter = dict.values().iterator();
			ICFSecSecUserPWResetObj obj;
			List<ICFSecSecUserPWResetObj> toForget = new LinkedList<ICFSecSecUserPWResetObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySentEMAddrIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserPWReset().deleteSecUserPWResetBySentEMAddrIdx( null,
				SentToEMailAddr );
		}
		deepDisposeSecUserPWResetBySentEMAddrIdx( SentToEMailAddr );
	}

	@Override
	public void deleteSecUserPWResetByNewAcctIdx( boolean NewAccount )
	{
		ICFSecSecUserPWResetByNewAcctIdxKey key = schema.getCFSecBackingStore().getCFSecFactory().getFactorySecUserPWReset().newByNewAcctIdxKey();
		key.setRequiredNewAccount( NewAccount );
		if( indexByNewAcctIdx == null ) {
			indexByNewAcctIdx = new HashMap< ICFSecSecUserPWResetByNewAcctIdxKey,
				Map< $implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj > >();
		}
		if( indexByNewAcctIdx.containsKey( key ) ) {
			Map<$implCommaIJavaOptAtomType$, ICFSecSecUserPWResetObj> dict = indexByNewAcctIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserPWReset().deleteSecUserPWResetByNewAcctIdx( null,
				NewAccount );
			Iterator<ICFSecSecUserPWResetObj> iter = dict.values().iterator();
			ICFSecSecUserPWResetObj obj;
			List<ICFSecSecUserPWResetObj> toForget = new LinkedList<ICFSecSecUserPWResetObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNewAcctIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserPWReset().deleteSecUserPWResetByNewAcctIdx( null,
				NewAccount );
		}
		deepDisposeSecUserPWResetByNewAcctIdx( NewAccount );
	}
}