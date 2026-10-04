// Description: Java 25 Spring JPA Id Generator Service for CFSec

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

package server.markhome.mcf.v3_1.cfsec.cfsec.jpa;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.MCFXmlUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

/**
 *	Service for the CFSecId generation methods defined in server.markhome.mcf.v3_1.cfsec.cfsec application model.
 */
@Service("CFSec31JpaIdGenService")
public class CFSecJpaIdGenService {

    @Autowired
    @Qualifier("cfsec31EntityManagerFactory")
    private LocalContainerEntityManagerFactoryBean cfsecEntityManagerFactory;

	/**
	 *	Generate a ClusterIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateClusterIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

	/**
	*	Generate a ISOCcyIdGen short id.
	*
	*		@return The next short value for the ISOCcyIdGen type.
	*/
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	@SequenceGenerator(name = "ISOCcyIdGenSeq", allocationSize = 1, initialValue = 0, schema = "CFSec31")
	public short generateISOCcyIdGen() {
		throw new MCFNotImplementedYetException( getClass(), "generateISOCcyIdGen" );
	}

	/**
	*	Generate a TableInfoIdGen integer id.
	*
	*		@return The next integer value for the TableInfoIdGen type.
	*/
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	@SequenceGenerator(name = "cfsec_tblinfoidgenseq", allocationSize = 1, initialValue = 0, schema = "CFSec31")
	public int generateTableInfoIdGen() {
		throw new MCFNotImplementedYetException( getClass(), "generateTableInfoIdGen" );
	}

	/**
	*	Generate a ISOCtryIdGen short id.
	*
	*		@return The next short value for the ISOCtryIdGen type.
	*/
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	@SequenceGenerator(name = "ISOCtryIdGenSeq", allocationSize = 1, initialValue = 0, schema = "CFSec31")
	public short generateISOCtryIdGen() {
		throw new MCFNotImplementedYetException( getClass(), "generateISOCtryIdGen" );
	}

	/**
	*	Generate a ISOLangIdGen short id.
	*
	*		@return The next short value for the ISOLangIdGen type.
	*/
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	@SequenceGenerator(name = "ISOLangIdGenSeq", allocationSize = 1, initialValue = 0, schema = "CFSec31")
	public short generateISOLangIdGen() {
		throw new MCFNotImplementedYetException( getClass(), "generateISOLangIdGen" );
	}

	/**
	*	Generate a ISOTZoneIdGen short id.
	*
	*		@return The next short value for the ISOTZoneIdGen type.
	*/
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	@SequenceGenerator(name = "ISOTZoneIdGenSeq", allocationSize = 1, initialValue = 0, schema = "CFSec31")
	public short generateISOTZoneIdGen() {
		throw new MCFNotImplementedYetException( getClass(), "generateISOTZoneIdGen" );
	}

	/**
	 *	Generate a SecSessionIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateSecSessionIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

	/**
	 *	Generate a SecUserIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateSecUserIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

	/**
	 *	Generate a TenantIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateTenantIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

	/**
	 *	Generate a SecSysGrpIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateSecSysGrpIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

	/**
	 *	Generate a SecClusGrpIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateSecClusGrpIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

	/**
	 *	Generate a SecClusRoleIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateSecClusRoleIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

	/**
	 *	Generate a SecTentGrpIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateSecTentGrpIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

	/**
	 *	Generate a SecTentRoleIdGen MCFDbKeyHash256 id.
	 *
	 *		@return A cryptographically-random generated MCFDBKeyHash128 value
	 */
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = NoResultException.class, transactionManager = "cfsecTransactionManager")
	public MCFDbKeyHash256 generateSecTentRoleIdGen() {
		return( new MCFDbKeyHash256(0) );
	}

}
