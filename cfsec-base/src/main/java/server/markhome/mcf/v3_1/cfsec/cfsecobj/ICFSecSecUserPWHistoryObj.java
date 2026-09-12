// Description: Java 25 Object interface for CFSec SecUserPWHistory.

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

public interface ICFSecSecUserPWHistoryObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFSecSecUserPWHistory.CLASS_CODE, but the Obj layer relies on class code translation to map those
	 *	backing store entities to a runtime set of front-facing classcodes that the clients download and use when talking to the server implementing this code base.
	 *
	 *	@return The runtime class code used by this object. Only after the system is fully booted are these values stable and reliable.
	 */
	int getClassCode();
	/**
	 *	Realise this instance of a SecUserPWHistory.
	 *
	 *	@return	CFSecSecUserPWHistoryObj instance which should be subsequently referenced.
	 */
	ICFSecSecUserPWHistoryObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecUserPWHistoryObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecUserPWHistoryObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecUserPWHistoryObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecUserPWHistoryObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this SecUserPWHistory instance.
	 *
	 *	@return	The newly locked ICFSecSecUserPWHistoryEditObj edition of this instance.
	 */
	ICFSecSecUserPWHistoryEditObj beginEdit();

	/**
	 *	End this edition of this SecUserPWHistory instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this SecUserPWHistory instance.
	 *
	 *	@return	The ICFSecSecUserPWHistoryEditObj edition of this instance.
	 */
	ICFSecSecUserPWHistoryEditObj getEdit();

	/**
	 *	Get the current edition of this SecUserPWHistory instance as a ICFSecSecUserPWHistoryEditObj.
	 *
	 *	@return	The ICFSecSecUserPWHistoryEditObj edition of this instance.
	 */
	ICFSecSecUserPWHistoryEditObj getEditAsSecUserPWHistory();

	/**
	 *	Get the ICFSecSecUserPWHistoryTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecSecUserPWHistoryTableObj table cache which manages this instance.
	 */
	ICFSecSecUserPWHistoryTableObj getSecUserPWHistoryTable();

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
	 *	Get the ICFSecSecUserPWHistory instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecUserPWHistory instance which currently backs this object.
	 */
	ICFSecSecUserPWHistory getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecSecUserPWHistory value );

	/**
	 *	Get the ICFSecSecUserPWHistory instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecUserPWHistory instance which currently backs this object.
	 */
	ICFSecSecUserPWHistory getSecUserPWHistoryRec();

	/**
	 *	Get the primary key of this instance.
	 *
	 *	@return	ICFSecSecUserPWHistoryPKey primary key for this instance.
	 */
	ICFSecSecUserPWHistoryPKey getPKey();

	/**
	 *	Set the primary key of this instance.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 *
	 *	@param ICFSecSecUserPWHistoryPKey primary key value for this instance.
	 */
	void setPKey( ICFSecSecUserPWHistoryPKey value );

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
	 *	Get the required ICFLibKeyHash256 attribute SecUserId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecUserId.
	 */
	ICFLibKeyHash256 getRequiredSecUserId();

	/**
	 *	Get the required LocalDateTime attribute PWSetStamp.
	 *
	 *	@return	The required LocalDateTime attribute PWSetStamp.
	 */
	LocalDateTime getRequiredPWSetStamp();

	/**
	 *	Get the required LocalDateTime attribute PWReplacedStamp.
	 *
	 *	@return	The required LocalDateTime attribute PWReplacedStamp.
	 */
	LocalDateTime getRequiredPWReplacedStamp();

	/**
	 *	Get the required String attribute PasswordHash.
	 *
	 *	@return	The required String attribute PasswordHash.
	 */
	String getRequiredPasswordHash();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
