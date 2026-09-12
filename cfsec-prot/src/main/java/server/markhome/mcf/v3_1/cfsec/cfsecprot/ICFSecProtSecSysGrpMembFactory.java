
// Description: Java Protected Factory interface for SecSysGrpMemb.

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

package server.markhome.mcf.v3_1.cfsec.cfsecprot;

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
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecprotobj.*;

/*
 *	ICFSecProtSecSysGrpMembFactory protected interface for SecSysGrpMemb
 */
public interface ICFSecProtSecSysGrpMembFactory
{

	/**
	 *	Allocate a primary key for SecSysGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrpMembPKey newProtPKey();

	/**
	 *	Allocate a protected primary history key for SecSysGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtSecSysGrpMembHPKey newProtHPKey();

	/**
	 *	Allocate a protected SysGrpIdx key over protected SecSysGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrpMembBySysGrpIdxKey newProtBySysGrpIdxKey();

	/**
	 *	Allocate a protected LoginIdx key over protected SecSysGrpMemb instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrpMembByLoginIdxKey newProtByLoginIdxKey();

	/**
	 *	Allocate a protected SecSysGrpMemb interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrpMemb newProtRec();

	/**
	 *	Allocate a protected SecSysGrpMemb history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtSecSysGrpMembH newProtHRec();

}
