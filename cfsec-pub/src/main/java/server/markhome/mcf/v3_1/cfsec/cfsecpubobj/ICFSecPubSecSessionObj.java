// Description: Java 25 Object interface for CFSecPub SecSession.

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

public interface ICFSecPubSecSessionObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFSecPubSecSession.CLASS_CODE, but the Obj layer relies on class code translation to map those
	 *	backing store entities to a runtime set of front-facing classcodes that the clients download and use when talking to the server implementing this code base.
	 *
	 *	@return The runtime class code used by this object. Only after the system is fully booted are these values stable and reliable.
	 */
	int getClassCode();
	/**
	 *	Realise this instance of a SecSession.
	 *
	 *	@return	CFSecPubSecSessionObj instance which should be subsequently referenced.
	 */
	ICFSecPubSecSessionObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecPubSecSessionObj the reference to the cached or read (realised) instance.
	 */
	ICFSecPubSecSessionObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecPubSecSessionObj the reference to the cached or read (realised) instance.
	 */
	ICFSecPubSecSessionObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this SecSession instance.
	 *
	 *	@return	The newly locked ICFSecPubSecSessionEditObj edition of this instance.
	 */
	ICFSecPubSecSessionEditObj beginEdit();

	/**
	 *	End this edition of this SecSession instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this SecSession instance.
	 *
	 *	@return	The ICFSecPubSecSessionEditObj edition of this instance.
	 */
	ICFSecPubSecSessionEditObj getEdit();

	/**
	 *	Get the current edition of this SecSession instance as a ICFSecPubSecSessionEditObj.
	 *
	 *	@return	The ICFSecPubSecSessionEditObj edition of this instance.
	 */
	ICFSecPubSecSessionEditObj getEditAsSecSession();

	/**
	 *	Get the ICFSecPubSecSessionTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecPubSecSessionTableObj table cache which manages this instance.
	 */
	ICFSecPubSecSessionTableObj getSecSessionTable();

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
	 *	Get the ICFSecPubSecSession instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecPubSecSession instance which currently backs this object.
	 */
	ICFSecPubSecSession getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecPubSecSession value );

	/**
	 *	Get the ICFSecPubSecSession instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecPubSecSession instance which currently backs this object.
	 */
	ICFSecPubSecSession getSecSessionRec();

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
	 *	Get the required ICFSecPubSecUserObj instance referenced by the SecUser key.
	 *
	 *	@return	The required ICFSecPubSecUserObj instance referenced by the SecUser key.
	 */
	ICFSecPubSecUserObj getRequiredContainerSecUser();

	/**
	 *	Get the required ICFSecPubSecUserObj instance referenced by the SecUser key.
	 *
	 *	@return	The required ICFSecPubSecUserObj instance referenced by the SecUser key.
	 */
	ICFSecPubSecUserObj getRequiredContainerSecUser( boolean forceRead );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute SecSessionId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute SecSessionId.
	 */
	CFLibDbKeyHash256 getRequiredSecSessionId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute SecUserId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute SecUserId.
	 */
	CFLibDbKeyHash256 getRequiredSecUserId();

	/**
	 *	Get the required LocalDateTime attribute Start.
	 *
	 *	@return	The required LocalDateTime attribute Start.
	 */
	LocalDateTime getRequiredStart();

	/**
	 *	Get the optional LocalDateTime attribute Finish.
	 *
	 *	@return	The optional LocalDateTime attribute Finish.
	 */
	LocalDateTime getOptionalFinish();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
