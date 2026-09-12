// Description: Java 25 Object interface for CFSec SecTentRole.

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

public interface ICFSecSecTentRoleObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFSecSecTentRole.CLASS_CODE, but the Obj layer relies on class code translation to map those
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
	 *	Realise this instance of a SecTentRole.
	 *
	 *	@return	CFSecSecTentRoleObj instance which should be subsequently referenced.
	 */
	ICFSecSecTentRoleObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecTentRoleObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecTentRoleObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecTentRoleObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecTentRoleObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this SecTentRole instance.
	 *
	 *	@return	The newly locked ICFSecSecTentRoleEditObj edition of this instance.
	 */
	ICFSecSecTentRoleEditObj beginEdit();

	/**
	 *	End this edition of this SecTentRole instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this SecTentRole instance.
	 *
	 *	@return	The ICFSecSecTentRoleEditObj edition of this instance.
	 */
	ICFSecSecTentRoleEditObj getEdit();

	/**
	 *	Get the current edition of this SecTentRole instance as a ICFSecSecTentRoleEditObj.
	 *
	 *	@return	The ICFSecSecTentRoleEditObj edition of this instance.
	 */
	ICFSecSecTentRoleEditObj getEditAsSecTentRole();

	/**
	 *	Get the ICFSecSecTentRoleTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecSecTentRoleTableObj table cache which manages this instance.
	 */
	ICFSecSecTentRoleTableObj getSecTentRoleTable();

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
	 *	Get the ICFSecSecTentRole instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecTentRole instance which currently backs this object.
	 */
	ICFSecSecTentRole getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecSecTentRole value );

	/**
	 *	Get the ICFSecSecTentRole instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecTentRole instance which currently backs this object.
	 */
	ICFSecSecTentRole getSecTentRoleRec();

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
	 *	Get the required ICFSecTenantObj instance referenced by the Tenant key.
	 *
	 *	@return	The required ICFSecTenantObj instance referenced by the Tenant key.
	 */
	ICFSecTenantObj getRequiredOwnerTenant();

	/**
	 *	Get the required ICFSecTenantObj instance referenced by the Tenant key.
	 *
	 *	@return	The required ICFSecTenantObj instance referenced by the Tenant key.
	 */
	ICFSecTenantObj getRequiredOwnerTenant( boolean forceRead );

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the SysRole key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the SysRole key.
	 */
	ICFSecSecSysGrpObj getRequiredContainerSysRole();

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the SysRole key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the SysRole key.
	 */
	ICFSecSecSysGrpObj getRequiredContainerSysRole( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecTentRoleMembObj array of instances referenced by the MembByRole key.
	 *
	 *	@return	The optional ICFSecSecTentRoleMembObj[] array of instances referenced by the MembByRole key.
	 */
	List<ICFSecSecTentRoleMembObj> getOptionalChildrenMembByRole();

	/**
	 *	Get the array of optional ICFSecSecTentRoleMembObj array of instances referenced by the MembByRole key.
	 *
	 *	@return	The optional ICFSecSecTentRoleMembObj[] array of instances referenced by the MembByRole key.
	 */
	List<ICFSecSecTentRoleMembObj> getOptionalChildrenMembByRole( boolean forceRead );

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecTentRoleId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecTentRoleId.
	 */
	ICFLibKeyHash256 getRequiredSecTentRoleId();

	/**
	 *	Get the required ICFLibKeyHash256 attribute TenantId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute TenantId.
	 */
	ICFLibKeyHash256 getRequiredTenantId();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
