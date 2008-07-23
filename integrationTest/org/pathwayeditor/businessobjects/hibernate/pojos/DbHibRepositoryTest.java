/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertTrue;

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
public class DbHibRepositoryTest {
	
	private static HibernateDbTester dbTester = null;
	private SessionFactory hibFactory; 
	private Session session ;

	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String ALT_REPOSITORY_NAME = "repo name 3" ;
	private static final String ALT_REPOSITORY_DESCRIPTION = "repository description 3" ;
	private static final int ALT_REPOSITORY_VERSION = 25343;
	private static final String REF_DATA = "integrationTest/DbRepositoryTestData/RepositoryRefData.xml";
	
//	private static final String SUB_FOLDER_NAME = "sub folder name" ;
	
	private static final String CHANGE_ROOT_EXPECTED_RESULTS = "integrationTest/DbRepositoryTestData/ExpectedChangeRootRefData.xml";
	private static final String DELETED_REPOSITORY_NO_SUBFOLDERS = "integrationTest/DbRepositoryTestData/OnlyOneReposirotyNoSubFoldersRefData.xml";
	private static final String DELETED_REPOSITORY_SUBFOLDERS = "integrationTest/DbRepositoryTestData/OnlyOneRepositoryWithSubFoldersRefData.xml";

	
	
	@BeforeClass
	public static void initSchema() throws Exception{
		if(dbTester == null){
			dbTester = new HibernateDbTester(HIB_CONFIG_FILE);
		}
		dbTester.createSchema();
	}
	
	@AfterClass
	public static void dropSchema() throws Exception{
		dbTester.dropSchema();
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
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		dbTester.onTearDown();
		if(this.hibFactory != null && !this.hibFactory.isClosed()){
			this.hibFactory.close();
		}
		this.hibFactory = null;
	}


	@Test
	public void testWriteToDataBase () throws Exception
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction();
		
		HibRepository repositoryToWrite = new HibRepository (ALT_REPOSITORY_NAME, ALT_REPOSITORY_DESCRIPTION, ALT_REPOSITORY_VERSION );
		HibRootFolder rootFolderToWrite = new HibRootFolder () ;
		
		
		repositoryToWrite.changeRootFolder(rootFolderToWrite) ;
		
//		HibSubFolder subFolderToWrite = new HibSubFolder ( rootFolderToWrite , SUB_FOLDER_NAME);
//		rootFolderToWrite.addSubFolder(subFolderToWrite) ;
		
		session.save(repositoryToWrite) ;
		
		session.getTransaction().commit() ;
		session = hibFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(repositoryToWrite);
		session.delete(repositoryToWrite);
		session.getTransaction().commit();
		
	}
	
	public void testDatabaseDataIntegrity () throws Exception
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		IDataSet loadedData = dbTester.getDataSet() ;
		IDataSet originalData = new XmlDataSet(new FileInputStream(REF_DATA));
		
		Assertion.assertEquals ( originalData , loadedData ) ;  
		
	}
	

		@Test
		public void testChangeRootFolder () throws Exception 
		{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		{
			session = hibFactory.getCurrentSession();
			session.beginTransaction();
			Query retrievedRepository = session
					.createQuery("from HibRepository where id = '100001'");

			HibRepository loadedRepository = (HibRepository) retrievedRepository
					.uniqueResult();

			HibRootFolder oldRootFolder = loadedRepository.getHibRootFolder();

			HibRootFolder newRootFolder = new HibRootFolder();

//			loadedRepository.changeRootFolder(newRootFolder);
			loadedRepository.changeRootFolder(null);
			
//			assertTrue("rootFolderChanged", oldRootFolder != loadedRepository.getRootFolder());
			assertTrue("old-subfolder unassigned", oldRootFolder
					.getOwningRepository() == null);

			session.delete(oldRootFolder);
//			session.saveOrUpdate(loadedRepository);

			session.getTransaction().commit();
	
			IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
					CHANGE_ROOT_EXPECTED_RESULTS));
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
	
	@Test
	public void testDeleteRepositoryWithSubFolders () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;		
		
		Query repositoryGetter = session.createQuery ( "From HibRepository where id='100001'") ;
		
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		
		HibRootFolder dbRootFolder = dbRepository.getHibRootFolder() ;
		
		session.delete(dbRepository) ;
		session.delete(dbRootFolder) ;
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_REPOSITORY_NO_SUBFOLDERS));
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
	public void testDeleteRepositoryWithOnlyRootFolders () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;		
		
		Query repositoryGetter = session.createQuery ( "From HibRepository where id='100002'") ;
		
		HibRepository dbRepository = (HibRepository) repositoryGetter.uniqueResult() ;
		
		HibRootFolder dbRootFolder = dbRepository.getHibRootFolder() ;
		
		session.delete(dbRepository) ;
		session.delete(dbRootFolder) ;
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				DELETED_REPOSITORY_SUBFOLDERS));
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
