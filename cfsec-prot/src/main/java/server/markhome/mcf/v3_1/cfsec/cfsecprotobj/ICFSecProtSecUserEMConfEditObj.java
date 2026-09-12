// Description: Java 25 Protected Instance Edit Object interface for CFSec SecUserEMConf.

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

public interface ICFSecProtSecUserEMConfEditObj
	extends ICFSecProtSecUserEMConfObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFSecProtSecUserEMConfObj.
	 */
	ICFSecProtSecUserEMConfObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFSecProtSecUserEMConfObj.
	 */
	ICFSecProtSecUserEMConfObj getOrigAsSecUserEMConf();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecProtSecUserEMConfObj create();

	/*
	 *	Update the instance.
	 */
	ICFSecProtSecUserEMConfEditObj update();

	/*
	 *	Delete the instance.
	 */
	ICFSecProtSecUserEMConfEditObj deleteInstance();

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
	 *	Get the ICFSecProtSecUserObj instance referenced by the User key.
	 *
	 *	@return	The ICFSecProtSecUserObj instance referenced by the User key.
	 */
	ICFSecProtSecUserObj getRequiredContainerUser();

	/**
	 *	Get the required ICFSecProtSecUserObj instance referenced by the User key.
	 *
	 *	@return	The required ICFSecProtSecUserObj instance referenced by the User key.
	 */
	ICFSecProtSecUserObj getRequiredContainerUser( boolean forceRead );

	/**
	 *	Set the ICFSecProtSecUserObj instance referenced by the User key.
	 *
	 *	@param	value	the ICFSecProtSecUserObj instance to be referenced by the User key.
	 */
	void setRequiredContainerUser( ICFSecProtSecUserObj value );

	/**
	 *	Get the required ICFLibKeyHash256 attribute SecUserId.
	 *
	 *	@return	The required ICFLibKeyHash256 attribute SecUserId.
	 */
	ICFLibKeyHash256 getRequiredSecUserId();

	/**
	 *	Get the required String attribute ConfirmEMailAddr.
	 *
	 *	@return	The required String attribute ConfirmEMailAddr.
	 */
	String getRequiredConfirmEMailAddr();

	/**
	 *	Set the required String attribute ConfirmEMailAddr.
	 *
	 *	@param value The required String attribute ConfirmEMailAddr value to be applied.
	 */
	void setRequiredConfirmEMailAddr(String value);

	/**
	 *	Get the required LocalDateTime attribute EMailSentStamp.
	 *
	 *	@return	The required LocalDateTime attribute EMailSentStamp.
	 */
	LocalDateTime getRequiredEMailSentStamp();

	/**
	 *	Set the required LocalDateTime attribute EMailSentStamp.
	 *
	 *	@param value The required LocalDateTime attribute EMailSentStamp value to be applied.
	 */
	void setRequiredEMailSentStamp(LocalDateTime value);

	/**
	 *	Get the required ICFLibUuid6 attribute EMConfirmationUuid6.
	 *
	 *	@return	The required ICFLibUuid6 attribute EMConfirmationUuid6.
	 */
	ICFLibUuid6 getRequiredEMConfirmationUuid6();

	/**
	 *	Set the required ICFLibUuid6 attribute EMConfirmationUuid6.
	 *
	 *	@param value The required ICFLibUuid6 attribute EMConfirmationUuid6 value to be applied.
	 */
	void setRequiredEMConfirmationUuid6(ICFLibUuid6 value);

	/**
	 *	Get the required boolean attribute NewAccount.
	 *
	 *	@return	The required boolean attribute NewAccount.
	 */
	boolean getRequiredNewAccount();

	/**
	 *	Set the required boolean attribute NewAccount.
	 *
	 *	@param value The required boolean attribute NewAccount value to be applied.
	 */
	void setRequiredNewAccount(boolean value);

	public void copyRecToOrig();
	public void copyOrigToRec();

}
