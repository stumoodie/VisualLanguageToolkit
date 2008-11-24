/**
 * 
 */
package org.pathwayeditor.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager;
import org.pathwayeditor.bussinessobjects.stubs.StubNotationSubsystemPool;

/**
 * @author nhanlon
 * 
 */
public abstract class GenericTester {

	private static HibernateTestManager dbTester = null;
	private static final String HIB_CONFIG_FILE = "hibernate.cfg.xml";
	private static final File SCHEMA_CREATION_SCRIPT = new File(
			"schema/EPE Schema Create.ddl");
	private static final File SCHEMA_DROP_SCRIPT = new File(
			"schema/EPE Schema Drop.ddl");
	private IRepositoryPersistenceManager bofac = null;
	private FileInputStream loadFile;

	// private IBusinessObjectFactory bo = bofac;

	public IRepositoryPersistenceManager getRepositoryPersistenceManager() {
		return bofac;
	}

	protected abstract String getTestRepositoryName();

	@BeforeClass
	public static void initSchema() throws Exception {
		dbTester = new HibernateTestManager(HIB_CONFIG_FILE,
				SCHEMA_CREATION_SCRIPT, SCHEMA_DROP_SCRIPT);
		dbTester.createSchema();
		dbTester.createHibernateSessionFactory();
	}

	@AfterClass
	public static void dropSchema() throws Exception {
		dbTester.discardHibernateSessionFactory();
		dbTester.dropSchema();
	}

	protected abstract void doAdditionalSetUp();

	protected abstract void doAdditionalTearDown();

	@Before
	public void setUp() throws Throwable {
		try {
			dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
			dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
			doSetup();
			ICanvasPersistenceHandler canvasPersistenceHandler = new HibCanvasPersistenceHandler(
					dbTester.getSessionFactory(),
					new StubNotationSubsystemPool());
			IRepositoryPersistenceHandler repoHandler = new HibRepositoryPersistenceHandler(
					dbTester.getSessionFactory(), getTestRepositoryName());
			bofac = new RepositoryPersistenceManager(repoHandler,
					canvasPersistenceHandler);
			bofac.openRepository();
			doAdditionalSetUp();
		} catch (Throwable exc) {
			exc.printStackTrace();
			throw exc;
		}
	}

	// protected void saveAndCommit(Serializable in) {
	// getSession().saveOrUpdate(in);
	// }

	protected void doSetup() throws DataSetException, FileNotFoundException,
			Exception {
		disableConstraints();
		this.loadFile = new FileInputStream(getDbUnitDataFilePath());
		getDbTester().setDataSet(new XmlDataSet(this.loadFile));
		getDbTester().onSetup();
		enableConstraints();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Throwable {
		try {
			doAdditionalTearDown();
			if(bofac != null) {
				bofac.closeRepository();
			}
			bofac = null;
			if (dbTester.getSessionFactory().getCurrentSession().isOpen()
					&& dbTester.getSessionFactory().getCurrentSession()
					.getTransaction().isActive()) {
				String msg = "Session and transaction have not be closed properly in class: "
					+ this.getClass().getCanonicalName();
				System.err.println(msg);
				this.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			disableConstraints();
			dbTester.onTearDown();
			this.loadFile.close();
			enableConstraints();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	protected final <T> List<T> runQuery(String query) {
		Session sess = this.getSessionFactory().getCurrentSession();
		sess.beginTransaction();
		List<T> retVal = (List<T>) sess.createQuery(query).list();
		sess.getTransaction().commit();
		return retVal;
	}

	@SuppressWarnings("unchecked")
	protected final <T> T runUniqueQuery(String query) {
		Session sess = this.getSessionFactory().getCurrentSession();
		sess.beginTransaction();
		T retVal = (T) sess.createQuery(query).uniqueResult();
		sess.getTransaction().commit();
		return retVal;
	}

	protected HibernateTestManager getDbTester() {
		return dbTester;
	}

	// protected Session getSession() {
	// return dbTester.getHibernateSessionFactory().getCurrentSession();
	// }

	protected final SessionFactory getSessionFactory() {
		return dbTester.getSessionFactory();
	}

	/**
	 * @return path to xml file containing setup data for DBUnit
	 */
	protected abstract String getDbUnitDataFilePath();

	protected void enableConstraints() throws Exception {
		Connection conn = dbTester.getConnection().getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery("SET referential_integrity TRUE");
			conn.commit();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	protected void disableConstraints() throws Exception {
		Connection conn = dbTester.getConnection().getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery("SET referential_integrity FALSE");
			conn.commit();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	/**
	 * Compares the actual db contexts with expected values provided.
	 * @param mainFile the main file to use in the test. This may be the original data loaded into the db.
	 * @param deltaFile the additional changes that are added to the mainFile. If null then only the mainFile
	 * is used for comparison.  
	 * @throws Exception An exception is thrown by DBUnit during the comparison.
	 */
	protected final void compareDatabase(String mainFile, String deltaFile) throws Exception{
		IDataSet expectedDeltas = null;
		if(deltaFile != null){
			expectedDeltas = new XmlDataSet(new FileInputStream(deltaFile));
		}
		IDataSet expectedChanges = new XmlDataSet(new FileInputStream(mainFile)); 
		String testTables[] = expectedChanges.getTableNames();
		if(expectedDeltas != null){
			expectedChanges = new CompositeDataSet(expectedChanges, expectedDeltas);
			testTables = expectedDeltas.getTableNames();

		}
		
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
		for (String t : testTables) {
			ITable expectedTable = DefaultColumnFilter
					.includedColumnsTable(expectedChanges.getTable(t),
							expectedDeltas.getTable(t).getTableMetaData()
									.getColumns());
			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
					actualChanges.getTable(t), expectedDeltas.getTable(t)
							.getTableMetaData().getColumns());
			Assertion.assertEquals(new SortedTable(expectedTable),
					new SortedTable(actualTable, expectedTable
							.getTableMetaData()));
		}
	}
	
}
