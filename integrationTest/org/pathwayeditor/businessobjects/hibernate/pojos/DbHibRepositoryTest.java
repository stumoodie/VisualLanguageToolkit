/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
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

	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String REPOSITORY_NAME = "repo name" ;
	private static final String REPOSITORY_DESCRIPTION = "repository description" ;
	private static final int REPOSITORY_VERSION = 2534;
	private static final String ALT_REPOSITORY_NAME = "repo name 3" ;
	private static final String ALT_REPOSITORY_DESCRIPTION = "repository description 3" ;
	private static final int ALT_REPOSITORY_VERSION = 25343;
	private static final String REF_DATA = "integrationTest/DbTestData/RepositoryRefData.xml";
	
//	private static final String SUB_FOLDER_NAME = "sub folder name" ;
	
	private static final int EMPTY_TABLE = 0 ;
	private static final int ONE_ENTRY_TABLE = 3 ;
	private static final int TWO_ENTRIES_TABLE = 2 ;
	private static final int FOUR_ENTRY_TABLE = 4 ;
	private static final String CHANGE_ROOT_EXPECTED_RESULTS = "integrationTest/DbTestData/ExpectedChangeRootData.xml";;
	
	@BeforeClass
	public static void initSchema() throws Exception{
		dbTester = new HibernateDbTester(HIB_CONFIG_FILE);
		dbTester.writeHibernateDdl();
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
	public void testWriteToDataBase () throws Exception
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		Session session = hibFactory.getCurrentSession() ;
		session.beginTransaction();
		
		HibRepository repositoryToWrite = new HibRepository (ALT_REPOSITORY_NAME, ALT_REPOSITORY_DESCRIPTION, ALT_REPOSITORY_VERSION );
		HibRootFolder rootFolderToWrite = new HibRootFolder () ;
		
		
		repositoryToWrite.addRootFolder(rootFolderToWrite) ;
		
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
	
	@Test
	public void testLoadRepository () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		Session session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		Query retrievedRepository = session.createQuery( "from HibRepository where id = '100001'" ) ;
		
		HibRepository loadedRepository = (HibRepository) retrievedRepository.uniqueResult(); 
		
		assertEquals ("same name" , REPOSITORY_NAME ,loadedRepository.getName() ) ;
		assertEquals ("same description" , REPOSITORY_DESCRIPTION ,loadedRepository.getDescription() ) ;
		assertEquals ("same built" , REPOSITORY_VERSION ,loadedRepository.getBuildNum() ) ;
		
		assertTrue ("has rootFolder", loadedRepository.getRootFolders().isEmpty() == false  ) ;
		session.getTransaction().commit();
	}
	
	public void testDatabaseDataIntegrity () throws Exception
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		Session session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
 		
		Query retrievedRepositories = session.createQuery( "from HibRepository" ) ;
		assertEquals ( "no repositories" , EMPTY_TABLE , retrievedRepositories.list().size() ) ;
		
		Query retrievedRootFolders = session.createQuery( "from HibRootFolder" ) ;
		assertEquals ( "no rootFolders" , TWO_ENTRIES_TABLE , retrievedRootFolders.list().size() ) ;
		
		Query retrievedSubFolders = session.createQuery( "from HibSubFolder" ) ;
		assertEquals ( "no subFolders" , FOUR_ENTRY_TABLE , retrievedSubFolders.list().size() ) ;
		
		Query retrievedMapDiagrams = session.createQuery( "from HibMapDiagram" ) ;
		assertEquals ( "no mapDiagrams" , ONE_ENTRY_TABLE , retrievedMapDiagrams.list().size() ) ;
		
		session.getTransaction().commit();
	}
	
	@Test
	public void testChangeRootFolder () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		{
			Session session = hibFactory.getCurrentSession();
			session.beginTransaction();
			Query retrievedRepository = session
					.createQuery("from HibRepository where id = '100001'");

			HibRepository loadedRepository = (HibRepository) retrievedRepository
					.uniqueResult();

			HibRootFolder oldRootFolder = loadedRepository.getRootFolders()
					.iterator().next();

			HibRootFolder newRootFolder = new HibRootFolder();

			loadedRepository.removeRootFolder(oldRootFolder);
			loadedRepository.addRootFolder(newRootFolder);

			assertTrue("rootFolderChanged", oldRootFolder != loadedRepository
					.getRootFolders().iterator().next());
			assertTrue("old-subfolder unassigned", oldRootFolder
					.getRepository() == null);

			// session.delete(oldRootFolder);
			session.saveOrUpdate(loadedRepository);

			session.getTransaction().commit();
		}
		{
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
		Session session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		Query retrievedRepositories = session.createQuery( "from HibRepository where id = '100001'" ) ;
		
		HibRepository repository = (HibRepository)retrievedRepositories.uniqueResult(); 
		
		session.delete(repository) ;
		
		session.getTransaction().commit() ;
	}

	@Test
	public void testDeleteRepositoryWithOnlyRootFolders () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		Session session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		Query retrievedRepositories = session.createQuery( "from HibRepository where id = '100002'" ) ;
		HibRepository repository = (HibRepository)retrievedRepositories.uniqueResult() ; 
		
		session.delete(repository) ;
		
		session.getTransaction().commit() ;
	}
}
