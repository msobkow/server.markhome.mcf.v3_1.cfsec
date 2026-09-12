
// Description: Java Protected Factory interface for ISOTZone.

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
 *	ICFSecProtISOTZoneFactory protected interface for ISOTZone
 */
public interface ICFSecProtISOTZoneFactory extends ICFSecPubISOTZoneFactory
{

	/**
	 *	Allocate a protected primary history key for ISOTZone instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOTZoneHPKey newProtHPKey();

	/**
	 *	Allocate a public primary history key for ISOTZone instances from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOTZoneHPKey asPublic(ICFSecProtISOTZoneHPKey src);

	/**
	 *	Allocate a protected OffsetIdx key over protected ISOTZone instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZoneByOffsetIdxKey newProtByOffsetIdxKey();

	/**
	 *	Allocate a public OffsetIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZoneByOffsetIdxKey asPublic(ICFSecProtISOTZoneByOffsetIdxKey src);

	/**
	 *	Allocate a protected UTZNameIdx key over protected ISOTZone instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZoneByUTZNameIdxKey newProtByUTZNameIdxKey();

	/**
	 *	Allocate a public UTZNameIdx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZoneByUTZNameIdxKey asPublic(ICFSecProtISOTZoneByUTZNameIdxKey src);

	/**
	 *	Allocate a protected Iso8601Idx key over protected ISOTZone instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZoneByIso8601IdxKey newProtByIso8601IdxKey();

	/**
	 *	Allocate a public Iso8601Idx key from a protected instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZoneByIso8601IdxKey asPublic(ICFSecProtISOTZoneByIso8601IdxKey src);

	/**
	 *	Allocate a protected ISOTZone interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZone newProtRec();

	/**
	 *	Allocate a public ISOTZone interface from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZone asPublic(ICFSecProtISOTZone src);

	/**
	 *	Allocate a protected ISOTZone history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZoneH newProtHRec();

	/**
	 *	Allocate a public ISOTZone history interface implementation from a protected interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZoneH asPublic(ICFSecProtISOTZoneH src);

}
