
// Description: Java JPA Factory interface for SecSysGrp.

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
 *	ICFSecSecSysGrpFactory interface for SecSysGrp
 */
public interface ICFSecSecSysGrpFactory extends ICFSecProtSecSysGrpFactory
{

	/**
	 *	Allocate a primary history key for SecSysGrp instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecSysGrpHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecSysGrp instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecSysGrpHPKey asProtected(ICFSecSecSysGrpHPKey src);

	/**
	 *	Allocate a public primary history key for SecSysGrp instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubSecSysGrpHPKey asPublic(ICFSecSecSysGrpHPKey src);

	/**
	 *	Allocate a UNameIdx key over SecSysGrp instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysGrpByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrpByUNameIdxKey asProtected(ICFSecSecSysGrpByUNameIdxKey src);

	/**
	 *	Allocate a public UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysGrpByUNameIdxKey asPublic(ICFSecSecSysGrpByUNameIdxKey src);

	/**
	 *	Allocate a SecLevelIdx key over SecSysGrp instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysGrpBySecLevelIdxKey newBySecLevelIdxKey();

	/**
	 *	Allocate a protected SecLevelIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrpBySecLevelIdxKey asProtected(ICFSecSecSysGrpBySecLevelIdxKey src);

	/**
	 *	Allocate a public SecLevelIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysGrpBySecLevelIdxKey asPublic(ICFSecSecSysGrpBySecLevelIdxKey src);

	/**
	 *	Allocate a SecSysGrp interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysGrp newRec();

	/**
	 *	Allocate a protected SecSysGrp interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrp asProtected(ICFSecSecSysGrp src);

	/**
	 *	Allocate a public SecSysGrp interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysGrp asPublic(ICFSecSecSysGrp src);

	/**
	 *	Allocate a SecSysGrp history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecSysGrpH newHRec();

	/**
	 *	Allocate a protected SecSysGrp history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrpH asProtected(ICFSecSecSysGrpH src);

	/**
	 *	Allocate a public SecSysGrp history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubSecSysGrpH asPublic(ICFSecSecSysGrpH src);

}
