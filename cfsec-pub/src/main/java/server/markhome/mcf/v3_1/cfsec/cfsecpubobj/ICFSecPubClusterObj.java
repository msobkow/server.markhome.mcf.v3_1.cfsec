// Description: Java 25 Object interface for CFSecPub Cluster.

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

package server.markhome.mcf.v3_1.cfsec.cfsecpubobj;

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

public interface ICFSecPubClusterObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFSecPubCluster.CLASS_CODE, but the Obj layer relies on class code translation to map those
	 *	backing store entities to a runtime set of front-facing classcodes that the clients download and use when talking to the server implementing this code base.
	 *
	 *	@return The runtime class code used by this object. Only after the system is fully booted are these values stable and reliable.
	 */
	int getClassCode();
	/**
	 *	Get the user who created this instance.
	 *
	 *	@return	The ICFSecPubSecUserObj instance who created this instance.
	 */
	ICFSecPubSecUserObj getCreatedBy();

	/**
	 *	Get the LocalDateTime this instance was created.
	 *
	 *	@return	The LocalDateTime value for the creation time of the instance.
	 */
	LocalDateTime getCreatedAt();

	/**
	 *	Get the user who updated this instance.
	 *
	 *	@return	The ICFSecPubSecUserObj instance who updated this instance.
	 */
	ICFSecPubSecUserObj getUpdatedBy();

	/**
	 *	Get the LocalDateTime date-time this instance was updated.
	 *
	 *	@return	The LocalDateTime value for the create time of the instance.
	 */
	LocalDateTime getUpdatedAt();
	/**
	 *	Realise this instance of a Cluster.
	 *
	 *	@return	CFSecPubClusterObj instance which should be subsequently referenced.
	 */
	ICFSecPubClusterObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecPubClusterObj the reference to the cached or read (realised) instance.
	 */
	ICFSecPubClusterObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecPubClusterObj the reference to the cached or read (realised) instance.
	 */
	ICFSecPubClusterObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this Cluster instance.
	 *
	 *	@return	The newly locked ICFSecPubClusterEditObj edition of this instance.
	 */
	ICFSecPubClusterEditObj beginEdit();

	/**
	 *	End this edition of this Cluster instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this Cluster instance.
	 *
	 *	@return	The ICFSecPubClusterEditObj edition of this instance.
	 */
	ICFSecPubClusterEditObj getEdit();

	/**
	 *	Get the current edition of this Cluster instance as a ICFSecPubClusterEditObj.
	 *
	 *	@return	The ICFSecPubClusterEditObj edition of this instance.
	 */
	ICFSecPubClusterEditObj getEditAsCluster();

	/**
	 *	Get the ICFSecPubClusterTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecPubClusterTableObj table cache which manages this instance.
	 */
	ICFSecPubClusterTableObj getClusterTable();

	/**
	 *	Get the ICFSecPubSchemaObj schema cache which manages this instance.
	 *
	 *	@return	ICFSecPubSchemaObj schema cache which manages this instance.
	 */
	ICFSecPubSchemaObj getSchema();

	/**
	 *	Set the ICFSecPubSchemaObj schema cache which manages this instance.
	 *	Should only be used to install overloads of the buff implementation wired specifically to a transport implementation
	 *	that eventually hits a server running a JPA backend.
	 *
	 *	@param schema	ICFSecPubSchemaObj schema cache which manages this instance.
	 */
	void setSchema(ICFSecPubSchemaObj schema);

	/**
	 *	Get the ICFSecPubCluster instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecPubCluster instance which currently backs this object.
	 */
	ICFSecPubCluster getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecPubCluster value );

	/**
	 *	Get the ICFSecPubCluster instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecPubCluster instance which currently backs this object.
	 */
	ICFSecPubCluster getClusterRec();

	/**
	 *	Get the primary key of this instance.
	 *
	 *	@return	ICFLibKeyHash256 primary key for this instance.
	 */
	ICFLibKeyHash256 getPKey();

	/**
	 *	Set the primary key of this instance.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 *
	 *	@param ICFLibKeyHash256 primary key value for this instance.
	 */
	void setPKey( ICFLibKeyHash256 value );

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
	 *	Get the array of optional ICFSecPubTenantObj array of instances referenced by the Tenant key.
	 *
	 *	@return	The optional ICFSecPubTenantObj[] array of instances referenced by the Tenant key.
	 */
	List<ICFSecPubTenantObj> getOptionalComponentsTenant();

	/**
	 *	Get the array of optional ICFSecPubTenantObj array of instances referenced by the Tenant key.
	 *
	 *	@return	The optional ICFSecPubTenantObj[] array of instances referenced by the Tenant key.
	 */
	List<ICFSecPubTenantObj> getOptionalComponentsTenant( boolean forceRead );

	/**
	 *	Get the array of optional ICFSecPubSysClusterObj array of instances referenced by the SysCluster key.
	 *
	 *	@return	The optional ICFSecPubSysClusterObj[] array of instances referenced by the SysCluster key.
	 */
	List<ICFSecPubSysClusterObj> getOptionalComponentsSysCluster();

	/**
	 *	Get the array of optional ICFSecPubSysClusterObj array of instances referenced by the SysCluster key.
	 *
	 *	@return	The optional ICFSecPubSysClusterObj[] array of instances referenced by the SysCluster key.
	 */
	List<ICFSecPubSysClusterObj> getOptionalComponentsSysCluster( boolean forceRead );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute Id.
	 */
	CFLibDbKeyHash256 getRequiredId();

	/**
	 *	Get the required String attribute FullDomName.
	 *
	 *	@return	The required String attribute FullDomName.
	 */
	String getRequiredFullDomName();

	/**
	 *	Get the required String attribute Description.
	 *
	 *	@return	The required String attribute Description.
	 */
	String getRequiredDescription();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
