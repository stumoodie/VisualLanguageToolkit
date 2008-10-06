/**
 * 
 */
package org.pathwayeditor.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.pathwaueditor.bussinessobjects.stubs.StubNotationSubsystemPool;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibCanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.hibernate.helpers.HibRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.management.RepositoryPersistenceManager;

/**
 * @author nhanlon
 * 
 */
public abstract class GenericTester {

	private static HibernateTestManager dbTester = null;
	private static final String HIB_CONFIG_FILE = "hibernate.cfg.xml";
	private static final File SCHEMA_CREATION_SCRIPT = new File("schema/EPE Schema Create.ddl"); 
	private static final File SCHEMA_DROP_SCRIPT = new File("schema/EPE Schema Drop.ddl"); 
	private static IRepositoryPersistenceManager bofac = null;
//	private IBusinessObjectFactory bo = bofac;
	
	public IRepositoryPersistenceManager getBusinessObjectFactory(){
		return bofac;
	}
	
	protected abstract String getTestRepositoryName();
	
	@BeforeClass
	public static void initSchema() throws Exception {
		dbTester = new HibernateTestManager(HIB_CONFIG_FILE, SCHEMA_CREATION_SCRIPT, SCHEMA_DROP_SCRIPT);
		dbTester.createSchema();
	}

	@AfterClass
	public static void dropSchema() throws Exception {
		dbTester.dropSchema();
	}

	protected abstract void doAdditionalSetUp();
	
	protected abstract void doAdditionalTearDown();

	@Before
	public void setUp() throws Exception {
		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		doSetup();
		ICanvasPersistenceHandler canvasPersistenceHandler = new HibCanvasPersistenceHandler(dbTester.getHibernateSessionFactory(),
																					new StubNotationSubsystemPool());
		IRepositoryPersistenceHandler repoHandler = new HibRepositoryPersistenceHandler(dbTester.getHibernateSessionFactory(), getTestRepositoryName());  
		bofac = new RepositoryPersistenceManager(repoHandler, canvasPersistenceHandler);
		bofac.openRepository();
		doAdditionalSetUp();
	}

	protected void saveAndCommit(Serializable in) {
		getSession().saveOrUpdate(in);
	}

	protected void doSetup() throws DataSetException, FileNotFoundException,
			Exception {
		disableConstraints();
		getDbTester().setDataSet(
				new XmlDataSet(new FileInputStream(getDbUnitDataFilePath())));
		getDbTester().onSetup();
		enableConstraints();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		doAdditionalTearDown();
		bofac.closeRepository();
		bofac = null;
		disableConstraints();
		dbTester.onTearDown();
		enableConstraints();
	}

	@SuppressWarnings("unchecked")
	protected final <T> List<T> runQuery(String query){
		Session sess = getSession();
		sess.beginTransaction();
		List<T> retVal = (List<T>)sess.createQuery(query).list();
		sess.getTransaction().commit();
		return retVal;
	}
	
	@SuppressWarnings("unchecked")
	protected final <T> T runUniqueQuery(String query){
		Session sess = getSession();
		sess.beginTransaction();
		T retVal = (T)sess.createQuery(query).uniqueResult();
		sess.getTransaction().commit();
		return retVal;
	}
	
	
	protected HibernateTestManager getDbTester() {
		return dbTester;
	}

	protected Session getSession() {
		return dbTester.getHibernateSessionFactory().getCurrentSession();
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
	
	protected void disableConstraints() throws Exception {
		Connection conn = dbTester.getConnection().getConnection();
		conn.createStatement().executeQuery("SET referential_integrity FALSE");
		conn.commit();
	}

}
