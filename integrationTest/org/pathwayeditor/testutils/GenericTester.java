/**
 * 
 */
package org.pathwayeditor.testutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.pathwayeditor.businessobjects.database.util.HibernateUtil;

/**
 * @author nhanlon
 * 
 */
public abstract class GenericTester {

	private static HibernateDataSource dbTester = null;
	private Session session;
	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";

	@BeforeClass
	public static void initSchema() throws Exception {
		dbTester = new HibernateDataSource(HIB_CONFIG_FILE);
		dbTester.createSchema();
	}

	@AfterClass
	public static void dropSchema() throws Exception {
		dbTester.dropSchema();
	}

	@Before
	public void setUp() throws Exception {
		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		doSetup();
	}

	protected void saveAndCommit(Serializable in) {
		getSession().saveOrUpdate(in);
	}
	

	protected void doSetup() throws DataSetException, FileNotFoundException,
			Exception {
		getDbTester().setDataSet(
				new XmlDataSet(new FileInputStream(getDbUnitDataFilePath())));
		getDbTester().onSetup();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dbTester.onTearDown();
	}

	protected HibernateDataSource getDbTester() {
		return this.dbTester;
	}

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	/**
	 * @return path to xml file containing setup data for DBUnit
	 */
	protected abstract String getDbUnitDataFilePath();
	
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
