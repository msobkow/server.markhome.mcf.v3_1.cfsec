// Description: Java 25 Protected Instance Edit Object interface for CFSec ISOCtryCcy.

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

public interface ICFSecProtISOCtryCcyEditObj
	extends ICFSecProtISOCtryCcyObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecProtISOCtryCcyObj.
	 */
	ICFSecProtISOCtryCcyObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecProtISOCtryCcyObj.
	 */
	ICFSecProtISOCtryCcyObj getOrigAsISOCtryCcy();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecProtISOCtryCcyObj create();

	/*
	 *	Update the instance.
	 */
	ICFSecProtISOCtryCcyEditObj update();

	/*
	 *	Delete the instance.
	 */
	ICFSecProtISOCtryCcyEditObj deleteInstance();

	/**
	 *	Set the user who created this instance.
	 *
	 *	@param	value	The ICFSecProtSecUserObj instance who created this instance.
	 */
	void setCreatedBy( ICFSecProtSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was created.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setCreatedAt( LocalDateTime value );

	/**
	 *	Set the user who updated this instance.
	 *
	 *	@param	value	The ICFSecProtSecUserObj instance who updated this instance.
	 */
	void setUpdatedBy( ICFSecProtSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was updated.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setUpdatedAt( LocalDateTime value );

	/**
	 *	Get the ICFSecProtISOCtryObj instance referenced by the Ctry key.
	 *
	 *	@return	The ICFSecProtISOCtryObj instance referenced by the Ctry key.
	 */
	ICFSecProtISOCtryObj getRequiredContainerCtry();

	/**
	 *	Get the required ICFSecProtISOCtryObj instance referenced by the Ctry key.
	 *
	 *	@return	The required ICFSecProtISOCtryObj instance referenced by the Ctry key.
	 */
	ICFSecProtISOCtryObj getRequiredContainerCtry( boolean forceRead );

	/**
	 *	Set the ICFSecProtISOCtryObj instance referenced by the Ctry key.
	 *
	 *	@param	value	the ICFSecProtISOCtryObj instance to be referenced by the Ctry key.
	 */
	void setRequiredContainerCtry( ICFSecProtISOCtryObj value );

	/**
	 *	Get the ICFSecProtISOCcyObj instance referenced by the Ccy key.
	 *
	 *	@return	The ICFSecProtISOCcyObj instance referenced by the Ccy key.
	 */
	ICFSecProtISOCcyObj getRequiredParentCcy();

	/**
	 *	Get the required ICFSecProtISOCcyObj instance referenced by the Ccy key.
	 *
	 *	@return	The required ICFSecProtISOCcyObj instance referenced by the Ccy key.
	 */
	ICFSecProtISOCcyObj getRequiredParentCcy( boolean forceRead );

	/**
	 *	Set the ICFSecProtISOCcyObj instance referenced by the Ccy key.
	 *
	 *	@param	value	the ICFSecProtISOCcyObj instance to be referenced by the Ccy key.
	 */
	void setRequiredParentCcy( ICFSecProtISOCcyObj value );

	/**
	 *	Get the required short attribute ISOCtryId.
	 *
	 *	@return	The required short attribute ISOCtryId.
	 */
	short getRequiredISOCtryId();

	/**
	 *	Get the required short attribute ISOCcyId.
	 *
	 *	@return	The required short attribute ISOCcyId.
	 */
	short getRequiredISOCcyId();

	public void copyRecToOrig();
	public void copyOrigToRec();

}
