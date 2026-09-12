
// Description: Java JPA Factory interface for SecClusGrpMemb.

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
 *	ICFSecSecClusGrpMembFactory interface for SecClusGrpMemb
 */
public interface ICFSecSecClusGrpMembFactory extends ICFSecProtSecClusGrpMembFactory
{

	/**
	 *	Allocate a primary key for SecClusGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusGrpMembPKey newPKey();

	/**
	 *	Allocate a protected key for SecClusGrpMemb instances from a private key.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusGrpMembPKey asProtected(ICFSecSecClusGrpMembPKey src);

	/**
	 *	Allocate a primary history key for SecClusGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecSecClusGrpMembHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for SecClusGrpMemb instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecClusGrpMembHPKey asProtected(ICFSecSecClusGrpMembHPKey src);

	/**
	 *	Allocate a ClusGrpIdx key over SecClusGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusGrpMembByClusGrpIdxKey newByClusGrpIdxKey();

	/**
	 *	Allocate a protected ClusGrpIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusGrpMembByClusGrpIdxKey asProtected(ICFSecSecClusGrpMembByClusGrpIdxKey src);

	/**
	 *	Allocate a LoginIdx key over SecClusGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusGrpMembByLoginIdxKey newByLoginIdxKey();

	/**
	 *	Allocate a protected LoginIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusGrpMembByLoginIdxKey asProtected(ICFSecSecClusGrpMembByLoginIdxKey src);

	/**
	 *	Allocate a SecClusGrpMemb interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusGrpMemb newRec();

	/**
	 *	Allocate a protected SecClusGrpMemb interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusGrpMemb asProtected(ICFSecSecClusGrpMemb src);

	/**
	 *	Allocate a SecClusGrpMemb history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecSecClusGrpMembH newHRec();

	/**
	 *	Allocate a protected SecClusGrpMemb history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecClusGrpMembH asProtected(ICFSecSecClusGrpMembH src);

}
