/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.database.util.HibernateDataSource;
import org.pathwayeditor.businessobjects.database.util.InitialisedDefaultDB;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibRepositoryPersistenceHandler;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubsystemPool;

/**
 * @author smoodie
 * 
 */
public class RepositoryManagerBuilderTest {
	private static final String CONNECTION_URL = "jdbc:hsqldb:file:DB/EmbeddedDb";
	private static final String PASSWORD = "";
	private static final String USERNAME = "sa";
	private static final String DRIVER_NAME = "org.hsqldb.jdbcDriver";
	private static final String REPO_NAME = "Default";
	private static final String HIB_CONFIG_FILE = "hibernate.cfg.xml";
	private static final File SCHEMA_CREATION_SCRIPT = new File(
			"schema/EPE Schema Create.ddl");
	private static final File SCHEMA_DROP_SCRIPT = new File(
			"schema/EPE Schema Drop.ddl");
	private static final File DB_DIR = new File("DB");
	private IRepositoryPersistenceManager bofac = null;
	private HibernateDataSource dataSource;
	private IDatabaseManager databaseManager;
	
	@Before
	public void setUp() throws Exception {
		// check that DB directory exists. If it does not then create new DB
		IConnectionInfo connInfo = new DefaultConnection();
		InitialisedDefaultDB worker = new InitialisedDefaultDB(HIB_CONFIG_FILE, connInfo, SCHEMA_CREATION_SCRIPT,
				SCHEMA_DROP_SCRIPT);
		worker.removeDB();
		worker.buildSchema();
		worker.addInitialData();
		worker.shutDownDb();
		this.dataSource = new HibernateDataSource(HIB_CONFIG_FILE);
		dataSource.setConnectionInfo(connInfo);
		if(!DB_DIR.exists()){
			System.err.println("Database does not exists. The database should be created before this test case is run");
		}
		this.databaseManager = new HsqlDatabaseManager(connInfo);
		this.databaseManager.startup();
		ICanvasPersistenceHandler canvasPersistenceHandler = new HibCanvasPersistenceHandler(
				dataSource.getSessionFactory(), new StubNotationSubsystemPool());
		IRepositoryPersistenceHandler repoHandler = new HibRepositoryPersistenceHandler(
				dataSource.getSessionFactory(), connInfo.getRepositoryName());
		bofac = new RepositoryPersistenceManager(repoHandler,
				canvasPersistenceHandler);
	}

//	private void deleteDir(File directory) {
//		for (File subFile : directory.listFiles()) {
//			if (subFile.isDirectory()) {
//				deleteDir(subFile);
//			}
//			if(!subFile.delete())
//				System.err.println("Failed to remove file: " + subFile.getAbsolutePath());
//		}
//		if(!directory.delete()){
//			System.err.println("Failed to remove directory: " + directory.getAbsolutePath());
//		}
//	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		if(bofac != null)
			bofac.closeRepository();
		
		bofac = null;
		this.dataSource.getSessionFactory().close();
		this.databaseManager.shutdown();
		this.databaseManager = null;
		this.dataSource = null;
//		shutdownDB();
//		if (DB_DIR.exists()) {
//			deleteDir(DB_DIR);
//		}
	}
	
	@Test
	public void testDBFilesExist(){
		assertTrue("DB directory exists", DB_DIR.exists());
		assertTrue("DB Directory is a directory", DB_DIR.isDirectory());
		assertTrue("DB Directory is not empty", DB_DIR.list().length > 0);
	}

	@Test
	public void testRepoInitialisation()
			throws PersistenceManagerAlreadyOpenException {
		assertFalse("repo not open", bofac.isRepositoryOpen());
		bofac.openRepository();
		assertTrue("repo open", bofac.isRepositoryOpen());
	}

//	@SuppressWarnings("deprecation")
//	private void shutdownDB() throws SQLException {
//		Session sess = this.dataSource.getSessionFactory().getCurrentSession();
//		Connection conn = sess.connection();
//		conn.setAutoCommit(false);
//		Statement stmt = conn.createStatement();
//		stmt.execute("SHUTDOWN COMPACT");
//		stmt.close();
//		conn.commit();
//	}
	
	private static class DefaultConnection implements IConnectionInfo {

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.database.util.IConnectionInfo#
		 * getDriverName()
		 */
		public String getDriverName() {
			return DRIVER_NAME;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.database.util.IConnectionInfo#
		 * getPassword()
		 */
		public String getPassword() {
			return PASSWORD;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.database.util.IConnectionInfo#
		 * getRepositoryName()
		 */
		public String getRepositoryName() {
			return REPO_NAME;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pathwayeditor.businessobjects.database.util.IConnectionInfo#getUrl
		 * ()
		 */
		public String getUrl() {
			return CONNECTION_URL;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.database.util.IConnectionInfo#
		 * getUserName()
		 */
		public String getUserName() {
			return USERNAME;
		}

	}

}
