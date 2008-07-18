/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pathwayeditor.testutils.HibernateDbTester;



/**
 * @author ntsorman
 *
 */
public class DbHibRootFolderTest {

	private static HibernateDbTester dbTester = null;
	private SessionFactory hibFactory; 
	private Session session ;
	
	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String REF_DATA = "integrationTest/DbRepositoryTestData/RepositoryRefData.xml";
	private static final String ADDED_SUBFOLDER_REF_DATA = "integrationTest/DbRepositoryTestData/AddedSubFolderToRootFolderRefData.xml";
	private static final String DELETED_ROOT_REF_DATA = "integrationTest/DbRepositoryTestData/DeletedRootFolderRefData.xml";
	private static final String CLONED_ROOTFOLDER_REF_DATA = "integrationTest/DbRepositoryTestData/ClonedRootFolderRefData.xml";
	
	
	
	private static final String SUB_FOLDER_NAME = "subfolder5" ;
	
	
	@BeforeClass
	public static void initSchema() throws Exception{
		dbTester = new HibernateDbTester(HIB_CONFIG_FILE);
		dbTester.createSchema();
	}
	
	@AfterClass
	public static void dropSchema() throws Exception{
		dbTester.dropSchema() ;
	}

	
	@Before
	public void setUp() throws Exception {
		this.hibFactory = dbTester.getHibernateSessionFactory();
		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dbTester.onTearDown();
		if(this.hibFactory != null && !this.hibFactory.isClosed()){
			this.hibFactory.close();
		}
		this.hibFactory = null;
	}
	
	@Test
	public void testAddFoldersToRootFolder () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query rootFolderGetter = session.createQuery ( "From HibRootFolder where id='100006'") ;
		
		HibRootFolder dbRootFolder = (HibRootFolder) rootFolderGetter.uniqueResult() ;
		
		HibSubFolder subFolderToAdd = new HibSubFolder ( dbRootFolder , SUB_FOLDER_NAME ) ;
		
		dbRootFolder.addSubFolder(subFolderToAdd) ;
		
		session.saveOrUpdate(dbRootFolder) ;
		
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_SUBFOLDER_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		
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
	
	@Test
	public void testDeleteRootFolder () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query rootFolderGetter = session.createQuery ( "From HibRootFolder where id='100001'") ;
		
		HibRootFolder dbRootFolder = (HibRootFolder) rootFolderGetter.uniqueResult() ;
		dbRootFolder.changeRepository(null);
		session.delete(dbRootFolder) ;
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_ROOT_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
		
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
	
	@Test
	public void testCloneRootFolder () throws Exception
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		Query repositoryGetter = session.createQuery ( "From HibRepository where id='100002'") ;
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		HibRootFolder oldRootFolder = dbRepository.getRootFolder();
		HibRootFolder cloneOfRootFolder = new HibRootFolder ( dbRepository , oldRootFolder ) ;
		dbRepository.setRootFolder(null);
		session.delete(oldRootFolder) ;
		session.flush();
		dbRepository.changeRootFolder(cloneOfRootFolder) ;
		session.saveOrUpdate(dbRepository) ;
		session.getTransaction().commit() ;
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CLONED_ROOTFOLDER_REF_DATA));
		String testTables[] = expectedDeltas.getTableNames();
		IDataSet actualChanges = dbTester.getConnection().createDataSet(testTables);
		IDataSet expectedChanges = new CompositeDataSet(expectedDeltas);
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
