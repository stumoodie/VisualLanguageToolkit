/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ntsorman
 *
 */
public class DbHibRepositoryTest extends DBTestCase {
	
	private IDatabaseTester databaseTester;
	private SessionFactory hibFactory; 
	private Session session ;
	
	private static final long REPOSITORY_ID = 100001 ;
	private static final String REPOSITORY_NAME = "repo name" ;
	private static final String REPOSITORY_DESCRIPTION = "repo name" ;
	private static final int REPOSITORY_VERSION = 2534;
	
	private static final String SUB_FOLDER_NAME = "sub folder name" ;
	
	private static final int EMPTY_TABLE = 0 ;
	private static final int ONE_ENTRY_TABLE = 1 ;
	private static final int TWO_ENTRIES_TABLE = 2 ;
	private static final int FOUR_ENTRY_TABLE = 4 ;
	
	
	public DbHibRepositoryTest(String name){
		super( name );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:mem:testDb" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
	}

	protected IDataSet getDataSet() throws Exception{
		return new XmlDataSet(new FileInputStream("integrationTest/DBData/RepositoryRefData.xml"));
	}
	

	public void testWriteToDataBase () throws Exception
	{
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction();
		
		HibRepository repositoryToWrite = new HibRepository (REPOSITORY_NAME, REPOSITORY_DESCRIPTION, REPOSITORY_VERSION );
		HibRootFolder rootFolderToWrite = new HibRootFolder () ;
		
		
		repositoryToWrite.changeRootFolder(rootFolderToWrite) ;
		
		HibSubFolder subFolderToWrite = new HibSubFolder ( rootFolderToWrite , SUB_FOLDER_NAME);
		rootFolderToWrite.addSubFolder(subFolderToWrite) ;
		
		session.save(repositoryToWrite) ;
		
		session.close() ;
		
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction();
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		
		Query retrievedRepository = session.createQuery( "from HibRepository") ;
		assertEquals ( "one repository" , ONE_ENTRY_TABLE ,retrievedRepository.list().size() ) ;
		
		Query retrievedRootFolder = session.createQuery( "from HibRootFolder") ;
		assertEquals ( "one rootFolder" , ONE_ENTRY_TABLE ,retrievedRootFolder.list().size() ) ;
		
		Query retrievedSubFolder = session.createQuery( "from HibSubFolder") ;
		assertEquals ( "one subFolder" , ONE_ENTRY_TABLE ,retrievedSubFolder.list().size() ) ;
		
		HibRepository loadedRepository = (HibRepository) retrievedRepository.list().get(0) ;
		HibRootFolder loadedRootFolder = (HibRootFolder) retrievedRootFolder.list().get(0) ;
		HibSubFolder loadedSubFolder= (HibSubFolder) retrievedSubFolder.list().get(0) ;
		
		assertTrue ( "repository parent of rootFolder" , loadedRepository.getRootFolder().equals(loadedRootFolder) ) ;
		assertTrue ( "rootFolder parent of subFolder" , loadedRootFolder.getSubFolders().contains(loadedSubFolder) ) ;
	}
	
	public void testLoadRepository () throws Exception 
	{
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		Query retrievedRepository = session.createQuery( "from HibRepository where id = '100001" ) ;
		
		HibRepository loadedRepository = (HibRepository) retrievedRepository.list().get(0) ; 
		
		assertEquals ("same name" , REPOSITORY_NAME ,loadedRepository.getName() ) ;
		assertEquals ("same description" , REPOSITORY_DESCRIPTION ,loadedRepository.getDescription() ) ;
		assertEquals ("same built" , REPOSITORY_VERSION ,loadedRepository.getBuildNum() ) ;
		
		assertTrue ("has rootFolder", loadedRepository.getRootFolder() != null  ) ;
		
		session.close() ;
	}
	
	public void testDatabaseDataIntegrity () throws Exception
	{
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
 		
		Query retrievedRepositories = session.createQuery( "from HibRepository" ) ;
		assertEquals ( "no repositories" , EMPTY_TABLE , retrievedRepositories.list().size() ) ;
		
		Query retrievedRootFolders = session.createQuery( "from HibRootFolder" ) ;
		assertEquals ( "no rootFolders" , TWO_ENTRIES_TABLE , retrievedRootFolders.list().size() ) ;
		
		Query retrievedSubFolders = session.createQuery( "from HibSubFolder" ) ;
		assertEquals ( "no subFolders" , FOUR_ENTRY_TABLE , retrievedSubFolders.list().size() ) ;
		
		Query retrievedMapDiagrams = session.createQuery( "from HibMapDiagram" ) ;
		assertEquals ( "no mapDiagrams" , ONE_ENTRY_TABLE , retrievedMapDiagrams.list().size() ) ;
		
		session.close() ;
	}
	
	public void testChangeRootFolder () throws Exception 
	{
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		Query retrievedRepository = session.createQuery( "from HibRepository where id = '100001" ) ;
		
		HibRepository loadedRepository = (HibRepository) retrievedRepository.list().get(0) ; 
		
		HibRootFolder dBRootFolder = loadedRepository.getRootFolder() ;
		
		HibRootFolder newRootFolder = new HibRootFolder () ;
		
		loadedRepository.changeRootFolder(newRootFolder) ;
		
		assertTrue ( "rootFolderChanged" , dBRootFolder != loadedRepository.getRootFolder() ) ;
		
		session.saveOrUpdate(loadedRepository) ;
		
		session.getTransaction().commit() ;
		
		session.close() ;
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		retrievedRepository = session.createQuery( "from HibRepository where id = '100001" ) ;
		
		loadedRepository = (HibRepository) retrievedRepository.list().get(0) ; 
		
		HibRootFolder dBRootFolder2 = loadedRepository.getRootFolder() ;
		
		assertEquals ( "loaded same with saved " , newRootFolder , dBRootFolder2 ) ;
		
		session.close() ;
	}
	
	public void testDeleteRepository () throws Exception 
	{
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		Query retrievedRepositories = session.createQuery( "from HibRepository" ) ;
		
		ArrayList<HibRepository> repositories= new ArrayList<HibRepository> ( retrievedRepositories.list() ) ; 
		
		for (HibRepository repository : repositories) {
			session.delete(repository) ;
		}
		
		session.getTransaction().commit() ;
		session.close() ;
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		
		retrievedRepositories = session.createQuery( "from HibRepository" ) ;
		assertEquals ( "no repositories" , EMPTY_TABLE , retrievedRepositories.list().size() ) ;
		
		Query retrievedRootFolders = session.createQuery( "from HibRootFolder" ) ;
		assertEquals ( "no rootFolders" , EMPTY_TABLE , retrievedRootFolders.list().size() ) ;
		
		Query retrievedSubFolders = session.createQuery( "from HibSubFolder" ) ;
		assertEquals ( "no subFolders" , EMPTY_TABLE , retrievedSubFolders.list().size() ) ;
		
		Query retrievedMapDiagrams = session.createQuery( "from HibMapDiagram" ) ;
		assertEquals ( "no mapDiagrams" , EMPTY_TABLE , retrievedMapDiagrams.list().size() ) ;
		
		session.close() ;
	}
	
	public void setUp() throws Exception {
		
//		super.setUp();
		
		
		Configuration hibConf = new Configuration();
		this.hibFactory = hibConf.configure("test_hibernate.cfg.xml").buildSessionFactory();
		
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());

		
//        try
//        {
//            DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
//        }
//        finally
//        {
//            getConnection().close();
//        }
//		
//		IDataSet dataSet = getDataSet () ;
//		
//		databaseTester.setDataSet( dataSet );
//		
//		databaseTester.setDataSet( dataSet );
//		
//		 databaseTester.onSetup() ;
	}

	public void tearDown() throws Exception {

		
		if(this.hibFactory != null){
			this.hibFactory.close();
		}
		this.hibFactory = null;
		session = null ;
	}
}
