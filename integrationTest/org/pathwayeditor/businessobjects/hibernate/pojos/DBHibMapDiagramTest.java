/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.List;

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
public class DBHibMapDiagramTest {

	private static HibernateDbTester dbTester = null;
	private SessionFactory hibFactory; 
	private Session session ;
	
	private static final String REPOSITORY_NAME = "repo name" ;
	private static final String REPOSITORY_DESCRIPTION = "repo name" ;
	private static final int REPOSITORY_VERSION = 101010;
	
	private static final String MAPDIAGRAM_NAME = "new diagram name" ;
	private static final String MAPDIAGRAM_DESCR = "new diagram description" ;
	
	private static final int ONE_ENTRY_TABLE = 1 ;
	

	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String EMPTY_REF_DATA = "integrationTest/DbRepositoryTestData/RepositoryEmptyRefData.xml";
	private static final String REF_DATA = "integrationTest/DbRepositoryTestData/RepositoryRefData.xml";
	private static final String ADDED_MAPDIAGRAM_REF_DATA = "integrationTest/DbRepositoryTestData/AddedMapDiagramRefData.xml";
	private static final String DELETED_MAPDIAGRAM_REF_DATA = "integrationTest/DbRepositoryTestData/DeletedMapDiagramRefData.xml";
	private static final String CLONED_MAPDIAGRAM_REF_DATA = "integrationTest/DbRepositoryTestData/ClonedMapDiagramRefData.xml";
	
	
	
	
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
	public void testWriteMapDiagramToDatabase () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(EMPTY_REF_DATA)));
		dbTester.onSetup();
		
		HibRepository repositoryToWrite = new HibRepository (REPOSITORY_NAME, REPOSITORY_DESCRIPTION, REPOSITORY_VERSION ,new HibRootFolder());
		HibRootFolder rootFolderToWrite = new HibRootFolder () ;
		
		repositoryToWrite.changeRootFolder(rootFolderToWrite) ;
		
		
		HibMapDiagram aMapDiagram = new HibMapDiagram () ;
		
		rootFolderToWrite.addMapDiagram(aMapDiagram) ;
		aMapDiagram.setRepository(repositoryToWrite);

		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		session.save(repositoryToWrite) ;
		
		session.getTransaction().commit() ;
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retrievedRepository = session.createQuery( "from HibRepository") ;
		assertEquals ( "one repository" , ONE_ENTRY_TABLE ,retrievedRepository.list().size() ) ;
		
		Query retrievedRootFolder = session.createQuery( "from HibRootFolder") ;
		assertEquals ( "one rootFolder" , ONE_ENTRY_TABLE ,retrievedRootFolder.list().size() ) ;
		
		Query retrievedSubFolder = session.createQuery( "from HibMapDiagram") ;
		assertEquals ( "one subFolder" , ONE_ENTRY_TABLE ,retrievedSubFolder.list().size() ) ;
		
//		Query retrievedCanvas = session.createQuery( "from HibCanvas") ;
//		assertEquals ( "one subFolder" , ONE_ENTRY_TABLE ,retrievedSubFolder.list().size() ) ;
		
	}
	
	@Test
	public void testAddNewMapDiagram () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		
		Query retreivedFolder = session.createQuery("from HibFolder where id='100005'") ;
		
		HibFolder parentFolder = (HibFolder) retreivedFolder.uniqueResult() ;
		
		HibMapDiagram towrite = new HibMapDiagram ( parentFolder , MAPDIAGRAM_NAME ) ;
		towrite.setDescription(MAPDIAGRAM_DESCR) ;
		towrite.setRepository(parentFolder.getRepository());
		
		parentFolder.addMapDiagram(towrite) ;
		
		session.saveOrUpdate(parentFolder) ;
		session.getTransaction().commit() ;
		
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				ADDED_MAPDIAGRAM_REF_DATA));
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
	public void testDeleteMapDiagram () throws Exception
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		Query retreivedMapDiagram = session.createQuery("from HibMapDiagram where id='100001'") ;
		HibMapDiagram toDelete = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		session.delete(toDelete) ;
		session.getTransaction().commit() ;
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		retreivedMapDiagram = session.createQuery("from HibMapDiagram") ;
		assertEquals(0,retreivedMapDiagram.list().size());
	}
	
	@Test
	public void testCloneMapDiagram () throws Exception 
	{
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(REF_DATA)));
		dbTester.onSetup();
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		Query retreivedMapDiagram = session.createQuery("from HibMapDiagram where id='100001'") ;
		List <HibMapDiagram> diagrams1 = session.createQuery("from HibMapDiagram").list();
		HibMapDiagram toClone = (HibMapDiagram) retreivedMapDiagram.uniqueResult() ;
		Query retreivedFolder = session.createQuery("from HibFolder where id='100004'") ;
		HibFolder parentFolder = (HibFolder) retreivedFolder.uniqueResult() ;
		HibMapDiagram cloneDiagram = new HibMapDiagram ( parentFolder , toClone ) ;
		cloneDiagram.setRepository(parentFolder.getRepository());
		parentFolder.addMapDiagram(cloneDiagram) ;
		session.saveOrUpdate(parentFolder) ;
		session.getTransaction().commit() ;
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction() ;
		List <HibMapDiagram> diagrams = session.createQuery("from HibMapDiagram").list();
		IDataSet expectedDeltas = new XmlDataSet(new FileInputStream(
				CLONED_MAPDIAGRAM_REF_DATA));
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
