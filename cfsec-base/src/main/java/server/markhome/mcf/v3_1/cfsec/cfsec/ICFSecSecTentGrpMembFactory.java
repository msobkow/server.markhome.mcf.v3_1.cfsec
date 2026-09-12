
// Description: Java JPA Factory interface for SecTentGrpMemb.

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
 *	ICFSecSecTentGrpMembFactory interface for SecTentGrpMemb
 */
public interface ICFSecSecTentGrpMembFactory extends ICFSecProtSecTentGrpMembFactory
{

	/**
	 *	Allocate a primary key for SecTentGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpMembPKey newPKey();

	/**
	 *	Allocate a protected key for SecTentGrpMemb instances from a private key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpMembPKey asProtected(ICFSecSecTentGrpMembPKey src);

	/**
	 *	Allocate a primary history key for SecTentGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecTentGrpMembHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecTentGrpMemb instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecTentGrpMembHPKey asProtected(ICFSecSecTentGrpMembHPKey src);

	/**
	 *	Allocate a TentGrpIdx key over SecTentGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpMembByTentGrpIdxKey newByTentGrpIdxKey();

	/**
	 *	Allocate a protected TentGrpIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpMembByTentGrpIdxKey asProtected(ICFSecSecTentGrpMembByTentGrpIdxKey src);

	/**
	 *	Allocate a UserIdx key over SecTentGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpMembByUserIdxKey newByUserIdxKey();

	/**
	 *	Allocate a protected UserIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpMembByUserIdxKey asProtected(ICFSecSecTentGrpMembByUserIdxKey src);

	/**
	 *	Allocate a SecTentGrpMemb interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpMemb newRec();

	/**
	 *	Allocate a protected SecTentGrpMemb interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpMemb asProtected(ICFSecSecTentGrpMemb src);

	/**
	 *	Allocate a SecTentGrpMemb history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecTentGrpMembH newHRec();

	/**
	 *	Allocate a protected SecTentGrpMemb history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecTentGrpMembH asProtected(ICFSecSecTentGrpMembH src);

}
