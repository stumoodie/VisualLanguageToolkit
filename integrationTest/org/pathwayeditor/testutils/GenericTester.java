/**
 * 
 */
package org.pathwayeditor.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.Column;
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
import org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandlerFactory;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandlerFactory;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubsystemPool;

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
			ICanvasPersistenceHandlerFactory canvasPersistenceHandler = new HibCanvasPersistenceHandlerFactory(
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
	 * @throws IOException 
	 * @throws Exception An exception is thrown by DBUnit during the comparison.
	 */
	protected final void compareDatabase(String mainFile, String deltaFile) throws Exception{
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(deltaFile));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(new XmlDataSet(new FileInputStream(mainFile)), expectedDeltas);
		for (String t : testTables) {
//			List<String> columnList = new ArrayList<String>();
//			for(Column expectedColumn : expectedDeltas.getTable(t).getTableMetaData().getColumns()) {
//				columnList.add(expectedColumn.getColumnName());
//			}
//			String[] columnNameList = columnList.toArray(new String[0]);
			String[] columnNameList = getFilterColumnNames(expectedDeltas.getTable(t));
			doTableComparison(actualChanges.getTable(t), expectedChanges.getTable(t), columnNameList);
		}
	}

	private String[] getFilterColumnNames(ITable expectedFilterTable) throws DataSetException {
		List<String> columnList = new ArrayList<String>();
		for(Column expectedColumn : expectedFilterTable.getTableMetaData().getColumns()) {
			columnList.add(expectedColumn.getColumnName());
		}
		return columnList.toArray(new String[columnList.size()]);
	}
	
	private void doTableComparison(ITable rawActualTable, ITable rawExpectedTable, String[] columnNameList) throws DatabaseUnitException {
		ITable expectedTable = DefaultColumnFilter.includedColumnsTable(rawExpectedTable, columnNameList);
		ITable actualTable = DefaultColumnFilter.includedColumnsTable(rawActualTable, columnNameList);
		SortedTable expectedSortTable = new SortedTable(expectedTable);
		expectedSortTable.setUseComparable(false);
		SortedTable actualSortTable = new SortedTable(actualTable, columnNameList);
		actualSortTable.setUseComparable(false);
		Assertion.assertEquals(expectedSortTable, actualSortTable);
	}
	
	protected final void compareDatabase(String mainFile) throws Exception{
		IDataSet expectedChanges = new XmlDataSet(new FileInputStream(mainFile)); 
		String testTables[] = expectedChanges.getTableNames();
		
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
		for (String t : testTables) {
//			ITable expectedTable = DefaultColumnFilter
//					.includedColumnsTable(expectedChanges.getTable(t),
//							expectedChanges.getTable(t).getTableMetaData()
//									.getColumns());
//			ITable actualTable = DefaultColumnFilter.includedColumnsTable(
//					actualChanges.getTable(t), expectedChanges.getTable(t)
//							.getTableMetaData().getColumns());
//			SortedTable expectedSortTable = new SortedTable(expectedTable);
//			expectedSortTable.setUseComparable(true);
//			SortedTable actualSortTable = new SortedTable(actualTable, expectedTable.getTableMetaData());
//			actualSortTable.setUseComparable(true);
//			Assertion.assertEquals(expectedSortTable, actualSortTable);
			String[] columnNameList = getFilterColumnNames(expectedChanges.getTable(t));
			doTableComparison(actualChanges.getTable(t), expectedChanges.getTable(t), columnNameList);
		}
	}
}
