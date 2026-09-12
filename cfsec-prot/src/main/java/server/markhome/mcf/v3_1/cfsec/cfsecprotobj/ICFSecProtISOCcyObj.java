// Description: Java 25 Object interface for CFSecProt ISOCcy.

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

public interface ICFSecProtISOCcyObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFSecProtISOCcy.CLASS_CODE, but the Obj layer relies on class code translation to map those
	 *	backing store entities to a runtime set of front-facing classcodes that the clients download and use when talking to the server implementing this code base.
	 *
	 *	@return The runtime class code used by this object. Only after the system is fully booted are these values stable and reliable.
	 */
	int getClassCode();
	/**
	 *	Get the user who created this instance.
	 *
	 *	@return	The ICFSecProtSecUserObj instance who created this instance.
	 */
	ICFSecProtSecUserObj getCreatedBy();

	/**
	 *	Get the LocalDateTime this instance was created.
	 *
	 *	@return	The LocalDateTime value for the creation time of the instance.
	 */
	LocalDateTime getCreatedAt();

	/**
	 *	Get the user who updated this instance.
	 *
	 *	@return	The ICFSecProtSecUserObj instance who updated this instance.
	 */
	ICFSecProtSecUserObj getUpdatedBy();

	/**
	 *	Get the LocalDateTime date-time this instance was updated.
	 *
	 *	@return	The LocalDateTime value for the create time of the instance.
	 */
	LocalDateTime getUpdatedAt();
	/**
	 *	Realise this instance of a ISOCcy.
	 *
	 *	@return	CFSecProtISOCcyObj instance which should be subsequently referenced.
	 */
	ICFSecProtISOCcyObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecProtISOCcyObj the reference to the cached or read (realised) instance.
	 */
	ICFSecProtISOCcyObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecProtISOCcyObj the reference to the cached or read (realised) instance.
	 */
	ICFSecProtISOCcyObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this ISOCcy instance.
	 *
	 *	@return	The newly locked ICFSecProtISOCcyEditObj edition of this instance.
	 */
	ICFSecProtISOCcyEditObj beginEdit();

	/**
	 *	End this edition of this ISOCcy instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this ISOCcy instance.
	 *
	 *	@return	The ICFSecProtISOCcyEditObj edition of this instance.
	 */
	ICFSecProtISOCcyEditObj getEdit();

	/**
	 *	Get the current edition of this ISOCcy instance as a ICFSecProtISOCcyEditObj.
	 *
	 *	@return	The ICFSecProtISOCcyEditObj edition of this instance.
	 */
	ICFSecProtISOCcyEditObj getEditAsISOCcy();

	/**
	 *	Get the ICFSecProtISOCcyTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecProtISOCcyTableObj table cache which manages this instance.
	 */
	ICFSecProtISOCcyTableObj getISOCcyTable();

	/**
	 *	Get the ICFSecProtSchemaObj schema cache which manages this instance.
	 *
	 *	@return	ICFSecProtSchemaObj schema cache which manages this instance.
	 */
	ICFSecProtSchemaObj getSchema();

	/**
	 *	Set the ICFSecProtSchemaObj schema cache which manages this instance.
	 *	Should only be used to install overloads of the buff implementation wired specifically to a transport implementation
	 *	that eventually hits a server running a JPA backend.
	 *
	 *	@param schema	ICFSecProtSchemaObj schema cache which manages this instance.
	 */
	void setSchema(ICFSecProtSchemaObj schema);

	/**
	 *	Get the ICFSecProtISOCcy instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecProtISOCcy instance which currently backs this object.
	 */
	ICFSecProtISOCcy getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecProtISOCcy value );

	/**
	 *	Get the ICFSecProtISOCcy instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecProtISOCcy instance which currently backs this object.
	 */
	ICFSecProtISOCcy getISOCcyRec();

	/**
	 *	Get the primary key of this instance.
	 *
	 *	@return	Short primary key for this instance.
	 */
	Short getPKey();

	/**
	 *	Set the primary key of this instance.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 *
	 *	@param Short primary key value for this instance.
	 */
	void setPKey( Short value );

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
	 *	Get the array of optional ICFSecProtISOCtryCcyObj array of instances referenced by the Ctry key.
	 *
	 *	@return	The optional ICFSecProtISOCtryCcyObj[] array of instances referenced by the Ctry key.
	 */
	List<ICFSecProtISOCtryCcyObj> getOptionalChildrenCtry();

	/**
	 *	Get the array of optional ICFSecProtISOCtryCcyObj array of instances referenced by the Ctry key.
	 *
	 *	@return	The optional ICFSecProtISOCtryCcyObj[] array of instances referenced by the Ctry key.
	 */
	List<ICFSecProtISOCtryCcyObj> getOptionalChildrenCtry( boolean forceRead );

	/**
	 *	Get the required short attribute ISOCcyId.
	 *
	 *	@return	The required short attribute ISOCcyId.
	 */
	short getRequiredISOCcyId();

	/**
	 *	Get the required String attribute ISOCode.
	 *
	 *	@return	The required String attribute ISOCode.
	 */
	String getRequiredISOCode();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Get the optional String attribute UnitSymbol.
	 *
	 *	@return	The optional String attribute UnitSymbol.
	 */
	String getOptionalUnitSymbol();

	/**
	 *	Get the required short attribute Precis.
	 *
	 *	@return	The required short attribute Precis.
	 */
	short getRequiredPrecis();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
