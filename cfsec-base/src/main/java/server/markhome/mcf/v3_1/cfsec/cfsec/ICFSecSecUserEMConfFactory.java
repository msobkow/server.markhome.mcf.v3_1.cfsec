
// Description: Java JPA Factory interface for SecUserEMConf.

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

package server.markhome.mcf.v3_1.cfsec.cfsec;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;

import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/*
 *	ICFSecSecUserEMConfFactory interface for SecUserEMConf
 */
public interface ICFSecSecUserEMConfFactory extends ICFSecProtSecUserEMConfFactory
{

	/**
	 *	Allocate a primary history key for SecUserEMConf instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecUserEMConfHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecUserEMConf instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecUserEMConfHPKey asProtected(ICFSecSecUserEMConfHPKey src);

	/**
	 *	Allocate a UUuid6Idx key over SecUserEMConf instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserEMConfByUUuid6IdxKey newByUUuid6IdxKey();

	/**
	 *	Allocate a protected UUuid6Idx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserEMConfByUUuid6IdxKey asProtected(ICFSecSecUserEMConfByUUuid6IdxKey src);

	/**
	 *	Allocate a ConfEMAddrIdx key over SecUserEMConf instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserEMConfByConfEMAddrIdxKey newByConfEMAddrIdxKey();

	/**
	 *	Allocate a protected ConfEMAddrIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserEMConfByConfEMAddrIdxKey asProtected(ICFSecSecUserEMConfByConfEMAddrIdxKey src);

	/**
	 *	Allocate a SentStampIdx key over SecUserEMConf instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserEMConfBySentStampIdxKey newBySentStampIdxKey();

	/**
	 *	Allocate a protected SentStampIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserEMConfBySentStampIdxKey asProtected(ICFSecSecUserEMConfBySentStampIdxKey src);

	/**
	 *	Allocate a NewAcctIdx key over SecUserEMConf instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserEMConfByNewAcctIdxKey newByNewAcctIdxKey();

	/**
	 *	Allocate a protected NewAcctIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserEMConfByNewAcctIdxKey asProtected(ICFSecSecUserEMConfByNewAcctIdxKey src);

	/**
	 *	Allocate a SecUserEMConf interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserEMConf newRec();

	/**
	 *	Allocate a protected SecUserEMConf interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserEMConf asProtected(ICFSecSecUserEMConf src);

	/**
	 *	Allocate a SecUserEMConf history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecUserEMConfH newHRec();

	/**
	 *	Allocate a protected SecUserEMConf history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecUserEMConfH asProtected(ICFSecSecUserEMConfH src);

}
