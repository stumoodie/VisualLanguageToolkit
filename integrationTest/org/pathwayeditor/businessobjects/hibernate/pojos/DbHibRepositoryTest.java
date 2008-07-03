/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
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
	
	private SessionFactory hibFactory; 
	Session session ;
	
	private static final String REPOSITORY_NAME = "repo name" ;
	private static final String REPOSITORY_DESCRIPTION = "repo name" ;
	private static final int REPOSITORY_VERSION = 2534;
	
	public DbHibRepositoryTest(String name){
		super( name );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:mem:testDb" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
	}

	protected IDataSet getDataSet() throws Exception{
		return new FlatXmlDataSet(new FileInputStream("integrationTest/DBData/RepositoryRefData.xml"));
	}
	
	public void testLoadRepository () throws Exception 
	{
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		Query getRepository = session.createQuery( "from HibRepository where id = '100001" ) ;
		
		HibRepository loadedRepository = (HibRepository) getRepository.list().get(0) ; 
		
		assertEquals ("same name" , REPOSITORY_NAME ,loadedRepository.getName() ) ;
		assertEquals ("same description" , REPOSITORY_DESCRIPTION ,loadedRepository.getDescription() ) ;
		assertEquals ("same built" , REPOSITORY_VERSION ,loadedRepository.getBuildNum() ) ;
		
		assertTrue ("has rootFolder", loadedRepository.getRootFolder() != null  ) ;
	}
	
	
	public void testAddSubFolder () throws Exception 
	{
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		Query getRepository = session.createQuery( "from HibRepository where id = '100001" ) ;
		
		HibRepository loadedRepository = (HibRepository) getRepository.list().get(0) ; 
		
		HibRootFolder dBRootFolder = loadedRepository.getRootFolder() ;
		
		HibRootFolder newRootFolder = new HibRootFolder () ;
		
		loadedRepository.changeRootFolder(newRootFolder) ;
		
		assertTrue ( "rootFolderChanged" , dBRootFolder != loadedRepository.getRootFolder() ) ;
		
		session.saveOrUpdate(loadedRepository) ;
		
		session.getTransaction().commit() ;
		
		session.close() ;
		
		session = hibFactory.getCurrentSession() ;
		session.beginTransaction(); 
		getRepository = session.createQuery( "from HibRepository where id = '100001" ) ;
		
	}
	
	
	public void setUp() throws Exception {
		
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
		
		Configuration hibConf = new Configuration();
		this.hibFactory = hibConf.configure("test_hibernate.cfg.xml").buildSessionFactory();
	}

	public void tearDown() throws Exception {
		if (session != null )
			session.close() ;
		
		if(this.hibFactory != null){
			this.hibFactory.close();
		}
		this.hibFactory = null;
		session = null ;
	}
}
