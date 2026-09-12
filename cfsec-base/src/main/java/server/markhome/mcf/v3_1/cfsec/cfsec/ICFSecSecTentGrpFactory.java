
// Description: Java JPA Factory interface for SecTentGrp.

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
 *	ICFSecSecTentGrpFactory interface for SecTentGrp
 */
public interface ICFSecSecTentGrpFactory extends ICFSecProtSecTentGrpFactory
{

	/**
	 *	Allocate a primary history key for SecTentGrp instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecTentGrpHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecTentGrp instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecTentGrpHPKey asProtected(ICFSecSecTentGrpHPKey src);

	/**
	 *	Allocate a TenantIdx key over SecTentGrp instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpByTenantIdxKey newByTenantIdxKey();

	/**
	 *	Allocate a protected TenantIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpByTenantIdxKey asProtected(ICFSecSecTentGrpByTenantIdxKey src);

	/**
	 *	Allocate a NameIdx key over SecTentGrp instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpByNameIdxKey newByNameIdxKey();

	/**
	 *	Allocate a protected NameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpByNameIdxKey asProtected(ICFSecSecTentGrpByNameIdxKey src);

	/**
	 *	Allocate a UNameIdx key over SecTentGrp instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a protected UNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpByUNameIdxKey asProtected(ICFSecSecTentGrpByUNameIdxKey src);

	/**
	 *	Allocate a SecTentGrp interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrp newRec();

	/**
	 *	Allocate a protected SecTentGrp interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrp asProtected(ICFSecSecTentGrp src);

	/**
	 *	Allocate a SecTentGrp history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpH newHRec();

	/**
	 *	Allocate a protected SecTentGrp history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpH asProtected(ICFSecSecTentGrpH src);

}
