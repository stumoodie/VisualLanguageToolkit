/**
 * 
 */
package org.pathwayeditor.testutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.pathwayeditor.businessobjects.database.util.ConnectionInfo;
import org.pathwayeditor.businessobjects.database.util.IConnectionInfo;

/**
 * @author nhanlon
 * 
 */
public abstract class PojoTester {

	private static HibernateTestManager dbTester = null;
	private SessionFactory hibFactory;
	private Session session;
	private static IConnectionInfo testInfo = new ConnectionInfo("sa","","jdbc:hsqldb:mem:testDb",
			"repo name","org.hsqldb.jdbcDriver", "org.hibernate.dialect.HSQLDialect","thread"); 

	@BeforeClass
	public static void initSchema() throws Exception {
		dbTester = new HibernateTestManager(testInfo);
		dbTester.createSchema();
	}

	@AfterClass
	public static void dropSchema() throws Exception {
		dbTester.dropSchema();
	}

	@Before
	public void setUp() throws Exception {
		this.hibFactory = dbTester.getHibernateSessionFactory();
		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		startNewTransaction();
	}

	protected void saveAndCommit(Serializable in) {
		getSession().saveOrUpdate(in);
		getSession().getTransaction().commit();
	}

	protected void startNewTransaction() {
		setSession(getHibFactory().openSession());
		getSession().beginTransaction();
	}

	protected void doSetup() throws DataSetException, FileNotFoundException,
	Exception {
		disbleConstraints() ;
		getDbTester().setDataSet(
				new XmlDataSet(new FileInputStream(getDbUnitDataFilePath())));
		getDbTester().onSetup();
		enableConstraints() ;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		disbleConstraints() ;
		dbTester.onTearDown();
		enableConstraints() ;
		if (this.hibFactory != null && !this.hibFactory.isClosed()) {
			this.hibFactory.close();
		}
		this.hibFactory = null;
	}

	protected HibernateTestManager getDbTester() {
		return this.dbTester;
	}

	protected SessionFactory getHibFactory() {
		return this.hibFactory;
	}

	protected Session getSession() {
		return this.session;
	}

	protected void setSession(Session sess) {
		this.session = sess;
	}

	/**
	 * @return path to xml file containing setup data for DBUnit
	 */
	protected abstract String getDbUnitDataFilePath();
	
	protected IDatabaseConnection getConnection ()  throws Exception
	{
		return dbTester.getConnection() ;
	}
	
	protected void enableConstraints() throws Exception {
		Connection conn = dbTester.getConnection().getConnection();
		conn.createStatement().executeQuery("SET referential_integrity TRUE");
		conn.commit();
	}
	
	protected void disbleConstraints() throws Exception {
		Connection conn = dbTester.getConnection().getConnection();
		conn.createStatement().executeQuery("SET referential_integrity FALSE");
		conn.commit();
	}

}
