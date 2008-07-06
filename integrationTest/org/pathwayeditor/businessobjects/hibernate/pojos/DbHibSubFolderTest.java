/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ntsorman
 *
 */
public class DbHibSubFolderTest extends DBTestCase {
	
	private SessionFactory hibFactory; 
	private Session session ;
	
	private static final int ONE_ENTRY_TABLE = 1 ;
	private static final int THREE_ENTRIES_TABLE = 3 ;
	
	private static final String REPOSITORY_NAME = "repo name" ;
	private static final String REPOSITORY_DESCRIPTION = "repo name" ;
	private static final int REPOSITORY_VERSION = 2534;
	
	private static final String FOLDER_NAME_ONE= "Folder one" ;
	private static final String FOLDER_NAME_TWO= "Folder one" ;
	private static final String FOLDER_NAME_THREE= "Folder one" ;
	
	private static final String DIAGRAM_NAME_ONE= "Diagram one" ;
	private static final String DIAGRAM_NAME_TWO= "Diagram two" ;

	public DbHibSubFolderTest(String name){
		super( name );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:mem:testDb" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
	}

	protected IDataSet getDataSet() throws Exception{
		return new XmlDataSet(new FileInputStream("integrationTest/DBData/RepositoryRefData.xml"));
	}
	
	
	public void testWritSubFolderToDB () throws Exception 
	{
		HibRepository aRepository = new HibRepository (REPOSITORY_NAME , REPOSITORY_DESCRIPTION , REPOSITORY_VERSION) ;
		
		HibRootFolder aRootFolder = new HibRootFolder () ;
		
		HibSubFolder aFolder = new HibSubFolder (aRootFolder , FOLDER_NAME_ONE) ;
		HibSubFolder bFolder = new HibSubFolder (aFolder , FOLDER_NAME_TWO) ;
		HibSubFolder cFolder = new HibSubFolder (aFolder , FOLDER_NAME_THREE) ;
		
		HibMapDiagram aMapDiagram = new HibMapDiagram () ;
		HibMapDiagram bMapDiagram = new HibMapDiagram () ;
		
		aMapDiagram.setName(DIAGRAM_NAME_ONE) ;
		bMapDiagram.setName(DIAGRAM_NAME_TWO) ;
		
		aRepository.addRootFolder(aRootFolder) ;
		
		aRootFolder.addSubFolder(aFolder);

		aFolder.addSubFolder(bFolder) ;
		aFolder.addSubFolder(cFolder) ;
		
		aRootFolder.addMapDiagram(aMapDiagram) ;
		aFolder.addMapDiagram(bMapDiagram) ;
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction();
		
		session.save(aRepository) ;
		
		session.getTransaction().commit() ;
		
		session.close() ;
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction();
		
		Query retrievedRootFolder = session.createQuery( "from HibRootFolder") ;
		assertEquals ( "one rootFolder" , THREE_ENTRIES_TABLE ,retrievedRootFolder.list().size() ) ;
		
		Query retrievedSubFolder = session.createQuery( "from HibSubFolder") ;
		assertEquals ( "one subFolder" , ONE_ENTRY_TABLE ,retrievedSubFolder.list().size() ) ;
		
		
	}
	
	public void setUp() throws Exception {
		
		
		Configuration hibConf = new Configuration();
		this.hibFactory = hibConf.configure("test_hibernate.cfg.xml").buildSessionFactory();
		
	}

	public void tearDown() throws Exception {

		
		if(this.hibFactory != null){
			this.hibFactory.close();
		}
		this.hibFactory = null;
		session = null ;
	}
}
