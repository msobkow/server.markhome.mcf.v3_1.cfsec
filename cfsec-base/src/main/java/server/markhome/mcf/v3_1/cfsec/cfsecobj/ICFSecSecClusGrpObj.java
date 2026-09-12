// Description: Java 25 Object interface for CFSec SecClusGrp.

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

public interface ICFSecSecClusGrpObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFSecSecClusGrp.CLASS_CODE, but the Obj layer relies on class code translation to map those
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
	 *	Realise this instance of a SecClusGrp.
	 *
	 *	@return	CFSecSecClusGrpObj instance which should be subsequently referenced.
	 */
	ICFSecSecClusGrpObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecClusGrpObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecClusGrpObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecClusGrpObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecClusGrpObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this SecClusGrp instance.
	 *
	 *	@return	The newly locked ICFSecSecClusGrpEditObj edition of this instance.
	 */
	ICFSecSecClusGrpEditObj beginEdit();

	/**
	 *	End this edition of this SecClusGrp instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this SecClusGrp instance.
	 *
	 *	@return	The ICFSecSecClusGrpEditObj edition of this instance.
	 */
	ICFSecSecClusGrpEditObj getEdit();

	/**
	 *	Get the current edition of this SecClusGrp instance as a ICFSecSecClusGrpEditObj.
	 *
	 *	@return	The ICFSecSecClusGrpEditObj edition of this instance.
	 */
	ICFSecSecClusGrpEditObj getEditAsSecClusGrp();

	/**
	 *	Get the ICFSecSecClusGrpTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecSecClusGrpTableObj table cache which manages this instance.
	 */
	ICFSecSecClusGrpTableObj getSecClusGrpTable();

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
	 *	Get the ICFSecSecClusGrp instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecClusGrp instance which currently backs this object.
	 */
	ICFSecSecClusGrp getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecSecClusGrp value );

	/**
	 *	Get the ICFSecSecClusGrp instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecClusGrp instance which currently backs this object.
	 */
	ICFSecSecClusGrp getSecClusGrpRec();

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
	 *	Get the required ICFSecClusterObj instance referenced by the Cluster key.
	 *
	 *	@return	The required ICFSecClusterObj instance referenced by the Cluster key.
	 */
	ICFSecClusterObj getRequiredOwnerCluster();

	/**
	 *	Get the required ICFSecClusterObj instance referenced by the Cluster key.
	 *
	 *	@return	The required ICFSecClusterObj instance referenced by the Cluster key.
	 */
	ICFSecClusterObj getRequiredOwnerCluster( boolean forceRead );

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 */
	ICFSecSecSysGrpObj getRequiredContainerSysGrp();

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the SysGrp key.
	 */
	ICFSecSecSysGrpObj getRequiredContainerSysGrp( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecSecClusGrpMembObj array of instances referenced by the MembByGrp key.
	 *
	 *	@return	The optional ICFSecSecClusGrpMembObj[] array of instances referenced by the MembByGrp key.
	 */
	List<ICFSecSecClusGrpMembObj> getOptionalChildrenMembByGrp();

	/**
	 *	Get the array of optional ICFSecSecClusGrpMembObj array of instances referenced by the MembByGrp key.
	 *
	 *	@return	The optional ICFSecSecClusGrpMembObj[] array of instances referenced by the MembByGrp key.
	 */
	List<ICFSecSecClusGrpMembObj> getOptionalChildrenMembByGrp( boolean forceRead );

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecClusGrpId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecClusGrpId.
	 */
	ICFLibKeyHash256 getRequiredSecClusGrpId();

	/**
	 *	Get the required ICFLibKeyHash256 attribute ClusterId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute ClusterId.
	 */
	ICFLibKeyHash256 getRequiredClusterId();

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
