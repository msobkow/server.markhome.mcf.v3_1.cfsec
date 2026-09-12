// Description: Java 25 Object interface for CFSec SecSysGrp.

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

public interface ICFSecSecSysGrpObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFSecSecSysGrp.CLASS_CODE, but the Obj layer relies on class code translation to map those
	 *	backing store entities to a runtime set of front-facing classcodes that the clients download and use when talking to the server implementing this code base.
	 *
	 *	@return The runtime class code used by this object. Only after the system is fully booted are these values stable and reliable.
	 */
	int getClassCode();
	/**
	 *	Get the user who created this instance.
	 *
	 *	@return	The ICFSecSecUserObj instance who created this instance.
	 */
	ICFSecSecUserObj getCreatedBy();

	/**
	 *	Get the LocalDateTime this instance was created.
	 *
	 *	@return	The LocalDateTime value for the creation time of the instance.
	 */
	LocalDateTime getCreatedAt();

	/**
	 *	Get the user who updated this instance.
	 *
	 *	@return	The ICFSecSecUserObj instance who updated this instance.
	 */
	ICFSecSecUserObj getUpdatedBy();

	/**
	 *	Get the LocalDateTime date-time this instance was updated.
	 *
	 *	@return	The LocalDateTime value for the create time of the instance.
	 */
	LocalDateTime getUpdatedAt();
	/**
	 *	Realise this instance of a SecSysGrp.
	 *
	 *	@return	CFSecSecSysGrpObj instance which should be subsequently referenced.
	 */
	ICFSecSecSysGrpObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecSysGrpObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecSysGrpObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecSysGrpObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecSysGrpObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this SecSysGrp instance.
	 *
	 *	@return	The newly locked ICFSecSecSysGrpEditObj edition of this instance.
	 */
	ICFSecSecSysGrpEditObj beginEdit();

	/**
	 *	End this edition of this SecSysGrp instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this SecSysGrp instance.
	 *
	 *	@return	The ICFSecSecSysGrpEditObj edition of this instance.
	 */
	ICFSecSecSysGrpEditObj getEdit();

	/**
	 *	Get the current edition of this SecSysGrp instance as a ICFSecSecSysGrpEditObj.
	 *
	 *	@return	The ICFSecSecSysGrpEditObj edition of this instance.
	 */
	ICFSecSecSysGrpEditObj getEditAsSecSysGrp();

	/**
	 *	Get the ICFSecSecSysGrpTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecSecSysGrpTableObj table cache which manages this instance.
	 */
	ICFSecSecSysGrpTableObj getSecSysGrpTable();

	/**
	 *	Get the ICFSecSchemaObj schema cache which manages this instance.
	 *
	 *	@return	ICFSecSchemaObj schema cache which manages this instance.
	 */
	ICFSecSchemaObj getSchema();

	/**
	 *	Set the ICFSecSchemaObj schema cache which manages this instance.
	 *	Should only be used to install overloads of the buff implementation wired specifically to a transport implementation
	 *	that eventually hits a server running a JPA backend.
	 *
	 *	@param schema	ICFSecSchemaObj schema cache which manages this instance.
	 */
	void setSchema(ICFSecSchemaObj schema);

	/**
	 *	Get the ICFSecSecSysGrp instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecSysGrp instance which currently backs this object.
	 */
	ICFSecSecSysGrp getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecSecSysGrp value );

	/**
	 *	Get the ICFSecSecSysGrp instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecSysGrp instance which currently backs this object.
	 */
	ICFSecSecSysGrp getSecSysGrpRec();

	/**
	 *	Get the primary key of this instance.
	 *
	 *	@return	$implCommaIJavaOptAtomType$ primary key for this instance.
	 */
	$implCommaIJavaOptAtomType$ getPKey();

	/**
	 *	Set the primary key of this instance.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 *
	 *	@param $implCommaIJavaOptAtomType$ primary key value for this instance.
	 */
	void setPKey( $implCommaIJavaOptAtomType$ value );

	/**
	 *	Is this a new instance?
	 *
	 *	@return	True if this is a new instance, otherwise false if it has
	 *		been read, locked, or created.
	 */
	boolean getIsNew();

	/**
	 *	Indicate whether this is a new instance.
	 *	<p>
	 *	This method should only be used by implementation internals.
	 *
	 *	@param	True if this is a new instance, otherwise false.
	 */
	void setIsNew( boolean value );

	/**
	 *	Get the array of optional ICFSecSecSysGrpIncObj array of instances referenced by the IncByGrp key.
	 *
	 *	@return	The optional ICFSecSecSysGrpIncObj[] array of instances referenced by the IncByGrp key.
	 */
	List<ICFSecSecSysGrpIncObj> getOptionalComponentsIncByGrp();

	/**
	 *	Get the array of optional ICFSecSecSysGrpIncObj array of instances referenced by the IncByGrp key.
	 *
	 *	@return	The optional ICFSecSecSysGrpIncObj[] array of instances referenced by the IncByGrp key.
	 */
	List<ICFSecSecSysGrpIncObj> getOptionalComponentsIncByGrp( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecSysGrpMembObj array of instances referenced by the MembByGrp key.
	 *
	 *	@return	The optional ICFSecSecSysGrpMembObj[] array of instances referenced by the MembByGrp key.
	 */
	List<ICFSecSecSysGrpMembObj> getOptionalChildrenMembByGrp();

	/**
	 *	Get the array of optional ICFSecSecSysGrpMembObj array of instances referenced by the MembByGrp key.
	 *
	 *	@return	The optional ICFSecSecSysGrpMembObj[] array of instances referenced by the MembByGrp key.
	 */
	List<ICFSecSecSysGrpMembObj> getOptionalChildrenMembByGrp( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecClusGrpObj array of instances referenced by the ImplClusGrp key.
	 *
	 *	@return	The optional ICFSecSecClusGrpObj[] array of instances referenced by the ImplClusGrp key.
	 */
	List<ICFSecSecClusGrpObj> getOptionalComponentsImplClusGrp();

	/**
	 *	Get the array of optional ICFSecSecClusGrpObj array of instances referenced by the ImplClusGrp key.
	 *
	 *	@return	The optional ICFSecSecClusGrpObj[] array of instances referenced by the ImplClusGrp key.
	 */
	List<ICFSecSecClusGrpObj> getOptionalComponentsImplClusGrp( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecTentGrpObj array of instances referenced by the ImplTentGrp key.
	 *
	 *	@return	The optional ICFSecSecTentGrpObj[] array of instances referenced by the ImplTentGrp key.
	 */
	List<ICFSecSecTentGrpObj> getOptionalComponentsImplTentGrp();

	/**
	 *	Get the array of optional ICFSecSecTentGrpObj array of instances referenced by the ImplTentGrp key.
	 *
	 *	@return	The optional ICFSecSecTentGrpObj[] array of instances referenced by the ImplTentGrp key.
	 */
	List<ICFSecSecTentGrpObj> getOptionalComponentsImplTentGrp( boolean forceRead );

	/**
	 *	Get the optional ICFSecSecSysRoleObj instance referenced by the ImplSysRole key.
	 *
	 *	@return	The optional ICFSecSecSysRoleObj instance referenced by the ImplSysRole key.
	 */
	ICFSecSecSysRoleObj getOptionalComponentsImplSysRole();

	/**
	 *	Get the optional ICFSecSecSysRoleObj instance referenced by the ImplSysRole key.
	 *
	 *	@return	The optional ICFSecSecSysRoleObj instance referenced by the ImplSysRole key.
	 */
	ICFSecSecSysRoleObj getOptionalComponentsImplSysRole( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecClusRoleObj array of instances referenced by the ImplClusRole key.
	 *
	 *	@return	The optional ICFSecSecClusRoleObj[] array of instances referenced by the ImplClusRole key.
	 */
	List<ICFSecSecClusRoleObj> getOptionalComponentsImplClusRole();

	/**
	 *	Get the array of optional ICFSecSecClusRoleObj array of instances referenced by the ImplClusRole key.
	 *
	 *	@return	The optional ICFSecSecClusRoleObj[] array of instances referenced by the ImplClusRole key.
	 */
	List<ICFSecSecClusRoleObj> getOptionalComponentsImplClusRole( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecTentRoleObj array of instances referenced by the ImplTentRole key.
	 *
	 *	@return	The optional ICFSecSecTentRoleObj[] array of instances referenced by the ImplTentRole key.
	 */
	List<ICFSecSecTentRoleObj> getOptionalComponentsImplTentRole();

	/**
	 *	Get the array of optional ICFSecSecTentRoleObj array of instances referenced by the ImplTentRole key.
	 *
	 *	@return	The optional ICFSecSecTentRoleObj[] array of instances referenced by the ImplTentRole key.
	 */
	List<ICFSecSecTentRoleObj> getOptionalComponentsImplTentRole( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecSysGrpIncObj array of instances referenced by the SysGrpByName key.
	 *
	 *	@return	The optional ICFSecSecSysGrpIncObj[] array of instances referenced by the SysGrpByName key.
	 */
	List<ICFSecSecSysGrpIncObj> getOptionalChildrenSysGrpByName();

	/**
	 *	Get the array of optional ICFSecSecSysGrpIncObj array of instances referenced by the SysGrpByName key.
	 *
	 *	@return	The optional ICFSecSecSysGrpIncObj[] array of instances referenced by the SysGrpByName key.
	 */
	List<ICFSecSecSysGrpIncObj> getOptionalChildrenSysGrpByName( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecSysRoleEnablesObj array of instances referenced by the RoleByEnableName key.
	 *
	 *	@return	The optional ICFSecSecSysRoleEnablesObj[] array of instances referenced by the RoleByEnableName key.
	 */
	List<ICFSecSecSysRoleEnablesObj> getOptionalChildrenRoleByEnableName();

	/**
	 *	Get the array of optional ICFSecSecSysRoleEnablesObj array of instances referenced by the RoleByEnableName key.
	 *
	 *	@return	The optional ICFSecSecSysRoleEnablesObj[] array of instances referenced by the RoleByEnableName key.
	 */
	List<ICFSecSecSysRoleEnablesObj> getOptionalChildrenRoleByEnableName( boolean forceRead );

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecSysGrpId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecSysGrpId.
	 */
	ICFLibKeyHash256 getRequiredSecSysGrpId();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Get the required ICFSecPubSchema.SecLevelEnum attribute SecLevel.
	 *
	 *	@return	The required ICFSecPubSchema.SecLevelEnum attribute SecLevel.
	 */
	ICFSecPubSchema.SecLevelEnum getRequiredSecLevel();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
