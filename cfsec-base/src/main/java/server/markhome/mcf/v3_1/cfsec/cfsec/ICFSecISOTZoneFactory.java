
// Description: Java JPA Factory interface for ISOTZone.

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
 *	ICFSecISOTZoneFactory interface for ISOTZone
 */
public interface ICFSecISOTZoneFactory extends ICFSecProtISOTZoneFactory
{

	/**
	 *	Allocate a primary history key for ISOTZone instances.
	 *
	 *	@return	The new instance.
	 */
	ICFSecISOTZoneHPKey newHPKey();

	/**
	 *	Allocate a protected primary history key for ISOTZone instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecProtISOTZoneHPKey asProtected(ICFSecISOTZoneHPKey src);

	/**
	 *	Allocate a public primary history key for ISOTZone instances from a private instance.
	 *
	 *	@return	The new instance.
	 */
	ICFSecPubISOTZoneHPKey asPublic(ICFSecISOTZoneHPKey src);

	/**
	 *	Allocate a OffsetIdx key over ISOTZone instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOTZoneByOffsetIdxKey newByOffsetIdxKey();

	/**
	 *	Allocate a protected OffsetIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZoneByOffsetIdxKey asProtected(ICFSecISOTZoneByOffsetIdxKey src);

	/**
	 *	Allocate a public OffsetIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZoneByOffsetIdxKey asPublic(ICFSecISOTZoneByOffsetIdxKey src);

	/**
	 *	Allocate a UTZNameIdx key over ISOTZone instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOTZoneByUTZNameIdxKey newByUTZNameIdxKey();

	/**
	 *	Allocate a protected UTZNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZoneByUTZNameIdxKey asProtected(ICFSecISOTZoneByUTZNameIdxKey src);

	/**
	 *	Allocate a public UTZNameIdx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZoneByUTZNameIdxKey asPublic(ICFSecISOTZoneByUTZNameIdxKey src);

	/**
	 *	Allocate a Iso8601Idx key over ISOTZone instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOTZoneByIso8601IdxKey newByIso8601IdxKey();

	/**
	 *	Allocate a protected Iso8601Idx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZoneByIso8601IdxKey asProtected(ICFSecISOTZoneByIso8601IdxKey src);

	/**
	 *	Allocate a public Iso8601Idx key from a private instance.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZoneByIso8601IdxKey asPublic(ICFSecISOTZoneByIso8601IdxKey src);

	/**
	 *	Allocate a ISOTZone interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOTZone newRec();

	/**
	 *	Allocate a protected ISOTZone interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZone asProtected(ICFSecISOTZone src);

	/**
	 *	Allocate a public ISOTZone interface from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZone asPublic(ICFSecISOTZone src);

	/**
	 *	Allocate a ISOTZone history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecISOTZoneH newHRec();

	/**
	 *	Allocate a protected ISOTZone history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecProtISOTZoneH asProtected(ICFSecISOTZoneH src);

	/**
	 *	Allocate a public ISOTZone history interface implementation from a private interface.
	 *
	 *	@return	The new instance.
	 */
	public ICFSecPubISOTZoneH asPublic(ICFSecISOTZoneH src);

}
