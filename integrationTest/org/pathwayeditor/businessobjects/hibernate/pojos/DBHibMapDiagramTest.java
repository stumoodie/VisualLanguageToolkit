/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;

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
	
	
	private static final int ONE_ENTRY_TABLE = 1 ;
	

	private static final String HIB_CONFIG_FILE = "test_hibernate.cfg.xml";
	private static final String EMPTY_REF_DATA = "integrationTest/DbTestData/RepositoryEmptyRefData.xml";
	
	
	
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
		
		HibRepository repositoryToWrite = new HibRepository (REPOSITORY_NAME, REPOSITORY_DESCRIPTION, REPOSITORY_VERSION );
		HibRootFolder rootFolderToWrite = new HibRootFolder () ;
		
		repositoryToWrite.changeRootFolder(rootFolderToWrite) ;
		
		
		HibMapDiagram aMapDiagram = new HibMapDiagram () ;
		HibCanvas aCanvas = new HibCanvas () ;
		
		rootFolderToWrite.addMapDiagram(aMapDiagram) ;

//		aCanvas.changeMapDiagram(aMapDiagram) ;
		
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
	
}
