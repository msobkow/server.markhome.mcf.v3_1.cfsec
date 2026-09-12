// Description: Java 25 Protected Instance Edit Object interface for CFSec TableInfo.

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

package server.markhome.mcf.v3_1.cfsec.cfsecprotobj;

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
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public interface ICFSecProtTableInfoEditObj
	extends ICFSecProtTableInfoObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecProtTableInfoObj.
	 */
	ICFSecProtTableInfoObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecProtTableInfoObj.
	 */
	ICFSecProtTableInfoObj getOrigAsTableInfo();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecProtTableInfoObj create();

	/*
	 *	Update the instance.
	 */
	ICFSecProtTableInfoEditObj update();

	/*
	 *	Delete the instance.
	 */
	ICFSecProtTableInfoEditObj deleteInstance();

	/**
	 *	Get the ICFSecProtTableInfoObj instance referenced by the SuperRef key.
	 *
	 *	@return	The ICFSecProtTableInfoObj instance referenced by the SuperRef key.
	 */
	ICFSecProtTableInfoObj getOptionalParentSuperRef();

	/**
	 *	Get the optional ICFSecProtTableInfoObj instance referenced by the SuperRef key.
	 *
	 *	@return	The optional ICFSecProtTableInfoObj instance referenced by the SuperRef key.
	 */
	ICFSecProtTableInfoObj getOptionalParentSuperRef( boolean forceRead );

	/**
	 *	Set the ICFSecProtTableInfoObj instance referenced by the SuperRef key.
	 *
	 *	@param	value	the ICFSecProtTableInfoObj instance to be referenced by the SuperRef key.
	 */
	void setOptionalParentSuperRef( ICFSecProtTableInfoObj value );

	/**
	 *	Get a list ICFSecProtTableInfoObj instances referenced by the SubRefs key.
	 *
	 *	@return	The (potentially empty) list of ICFSecProtTableInfoObj instances referenced by the SubRefs key.
	 */
	List<ICFSecProtTableInfoObj> getOptionalChildrenSubRefs();

	/**
	 *	Get the required int attribute TableInfoId.
	 *
	 *	@return	The required int attribute TableInfoId.
	 */
	int getRequiredTableInfoId();

	/**
	 *	Set the required int attribute TableInfoId.
	 *
	 *	@param value The required int attribute TableInfoId value to be applied.
	 */
	void setRequiredTableInfoId(int value);

	/**
	 *	Get the required String attribute SchemaName.
	 *
	 *	@return	The required String attribute SchemaName.
	 */
	String getRequiredSchemaName();

	/**
	 *	Set the required String attribute SchemaName.
	 *
	 *	@param value The required String attribute SchemaName value to be applied.
	 */
	void setRequiredSchemaName(String value);

	/**
	 *	Get the required String attribute TableName.
	 *
	 *	@return	The required String attribute TableName.
	 */
	String getRequiredTableName();

	/**
	 *	Set the required String attribute TableName.
	 *
	 *	@param value The required String attribute TableName value to be applied.
	 */
	void setRequiredTableName(String value);

	/**
	 *	Get the optional String attribute SuperName.
	 *
	 *	@return	The optional String attribute SuperName.
	 */
	String getOptionalSuperName();

	/**
	 *	Get the required int attribute BackingClassCode.
	 *
	 *	@return	The required int attribute BackingClassCode.
	 */
	int getRequiredBackingClassCode();

	/**
	 *	Set the required int attribute BackingClassCode.
	 *
	 *	@param value The required int attribute BackingClassCode value to be applied.
	 */
	void setRequiredBackingClassCode(int value);

	/**
	 *	Get the required int attribute RuntimeClassCode.
	 *
	 *	@return	The required int attribute RuntimeClassCode.
	 */
	int getRequiredRuntimeClassCode();

	/**
	 *	Set the required int attribute RuntimeClassCode.
	 *
	 *	@param value The required int attribute RuntimeClassCode value to be applied.
	 */
	void setRequiredRuntimeClassCode(int value);

	/**
	 *	Get the required boolean attribute HasHistory.
	 *
	 *	@return	The required boolean attribute HasHistory.
	 */
	boolean getRequiredHasHistory();

	/**
	 *	Set the required boolean attribute HasHistory.
	 *
	 *	@param value The required boolean attribute HasHistory value to be applied.
	 */
	void setRequiredHasHistory(boolean value);

	/**
	 *	Get the required boolean attribute IsMutable.
	 *
	 *	@return	The required boolean attribute IsMutable.
	 */
	boolean getRequiredIsMutable();

	/**
	 *	Set the required boolean attribute IsMutable.
	 *
	 *	@param value The required boolean attribute IsMutable value to be applied.
	 */
	void setRequiredIsMutable(boolean value);

	/**
	 *	Get the required String attribute SecScopeName.
	 *
	 *	@return	The required String attribute SecScopeName.
	 */
	String getRequiredSecScopeName();

	/**
	 *	Set the required String attribute SecScopeName.
	 *
	 *	@param value The required String attribute SecScopeName value to be applied.
	 */
	void setRequiredSecScopeName(String value);

	/**
	 *	Get the required String attribute CodeVis.
	 *
	 *	@return	The required String attribute CodeVis.
	 */
	String getRequiredCodeVis();

	/**
	 *	Set the required String attribute CodeVis.
	 *
	 *	@param value The required String attribute CodeVis value to be applied.
	 */
	void setRequiredCodeVis(String value);

	public void copyRecToOrig();
	public void copyOrigToRec();

}
