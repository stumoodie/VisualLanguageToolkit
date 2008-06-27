/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ntsorman
 *
 */
public class DbHibRepositoryTest extends DBTestCase {
	
	private SessionFactory hibFactory; 
	
	public DbHibRepositoryTest(String name){
		super( name );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:mem:testDb" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
	}

	protected IDataSet getDataSet() throws Exception{
		return new FlatXmlDataSet(new FileInputStream("dataset.xml"));
	}
	
//	 protected IDatabaseConnection getConnection() throws Exception {
//			Class.forName("org.hsqldb.jdbcDriver");
//			String url = "jdbc:hsqldb:mem:testDb";
//			Connection conn = DriverManager.getConnection(url, "sa", "");
//		    conn.setAutoCommit(true);
//		    DatabaseConnection connection = new DatabaseConnection(conn);
//			DatabaseConfig config = connection.getConfig();
//		    config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
//		            new HsqldbDataTypeFactory());
//		    return connection;
//		  }

//		  protected DatabaseOperation getTearDownOperation() {
//		    //return DatabaseOperation.DELETE_ALL;
//		    return DatabaseOperation.NONE;
//		  }
//		  
//		  public DatabaseOperation getSetUpOperation() throws Exception {
//			IDatabaseConnection connection = getConnection();
//			Connection conn = connection.getConnection();
//			conn.setAutoCommit(false);
//			conn.commit();
//			conn.close();
//		    return DatabaseOperation.CLEAN_INSERT;
//		}


	public void setUp() throws Exception {
		Configuration hibConf = new Configuration();
		this.hibFactory = hibConf.configure("test_hibernate.cfg.xml").buildSessionFactory();
	}

	public void tearDown() throws Exception {
		if(this.hibFactory != null){
			this.hibFactory.close();
		}
		this.hibFactory = null;
	}
}
