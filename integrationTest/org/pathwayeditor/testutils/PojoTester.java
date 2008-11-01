/**
 * 
 */
package org.pathwayeditor.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Statement;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author nhanlon
 * 
 */
public abstract class PojoTester {

	private static HibernateTestManager dbTester = null;
//	private SessionFactory hibFactory;
//	private Session session;
	private FileInputStream loadFile = null;
	private static final String HIB_CONFIG_FILE = "hibernate.cfg.xml";
	private static final File SCHEMA_CREATION_SCRIPT = new File("schema/EPE Schema Create.ddl"); 
	private static final File SCHEMA_DROP_SCRIPT = new File("schema/EPE Schema Drop.ddl"); 

	@BeforeClass
	public static void initSchema() throws Exception {
		dbTester = new HibernateTestManager(HIB_CONFIG_FILE, SCHEMA_CREATION_SCRIPT, SCHEMA_DROP_SCRIPT);
		dbTester.createSchema();
		dbTester.createHibernateSessionFactory();
	}

	@AfterClass
	public static void dropSchema() throws Exception {
		dbTester.discardHibernateSessionFactory();
		dbTester.dropSchema();
	}

	@Before
	public void setUp() throws Exception {
//		this.hibFactory = dbTester.getSessionFactory();
		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
//		startNewTransaction();
	}

//	protected void saveAndCommit(Serializable in) {
//		getSession().saveOrUpdate(in);
//		getSession().getTransaction().commit();
//	}

//	protected void startNewTransaction() {
////		setSession(getHibFactory().openSession());
//		getSession().beginTransaction();
//	}

	protected void doSetup() throws DataSetException, FileNotFoundException,
	Exception {
		disableConstraints() ;
		this.loadFile = new FileInputStream(getDbUnitDataFilePath());
		getDbTester().setDataSet(new XmlDataSet(this.loadFile));
		getDbTester().onSetup();
		enableConstraints() ;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		disableConstraints() ;
		dbTester.onTearDown();
		if(this.loadFile != null){
			this.loadFile.close();
		}
		enableConstraints() ;
		if(this.getHibFactory().getCurrentSession().isOpen() && this.getHibFactory().getCurrentSession().getTransaction().isActive()){
			String msg = "Session and transaction have not be closed properly in class: " + this.getClass().getCanonicalName();
			System.err.println(msg);
			this.getHibFactory().getCurrentSession().getTransaction().commit();
			throw new RuntimeException(msg);
		}
	}

	protected final HibernateTestManager getDbTester() {
		return dbTester;
	}

	protected final SessionFactory getHibFactory() {
		return dbTester.getSessionFactory();
	}

//	protected Session getSession() {
//		return this.getHibFactory().getCurrentSession();
//	}

	/**
	 * @return path to xml file containing setup data for DBUnit
	 */
	protected abstract String getDbUnitDataFilePath();
	
	protected final IDatabaseConnection getConnection ()  throws Exception
	{
		return dbTester.getConnection() ;
	}
	
	protected final void enableConstraints() throws Exception {
		Connection conn = dbTester.getConnection().getConnection();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			stmt.executeQuery("SET referential_integrity TRUE");
			conn.commit();
		}
		finally{
			if(stmt != null){
				stmt.close();
			}
		}
	}
	
	protected final void disableConstraints() throws Exception {
		Connection conn = dbTester.getConnection().getConnection();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			stmt.executeQuery("SET referential_integrity FALSE");
			conn.commit();
		}
		finally{
			if(stmt != null){
				stmt.close();
			}
		}
	}

}
