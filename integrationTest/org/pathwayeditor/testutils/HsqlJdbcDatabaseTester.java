/**
 * 
 */
package org.pathwayeditor.testutils;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;

/**
 * @author nhanlon
 *
 */
public class HsqlJdbcDatabaseTester extends JdbcDatabaseTester{

	/**
	 * @param driverClass
	 * @param connectionUrl
	 */
	public HsqlJdbcDatabaseTester(String driverClass, String connectionUrl) {
		super(driverClass, connectionUrl);
	}

	/**
	 * @param driverClass
	 * @param connectionUrl
	 * @param userName
	 * @param password
	 */
	public HsqlJdbcDatabaseTester(String driverClass, String connectionUrl,
			String userName, String password) {
		super(driverClass,connectionUrl,userName,password);
	}
	
	/* (non-Javadoc)
	 * @see org.dbunit.JdbcDatabaseTester#getConnection()
	 */
	@Override
	public IDatabaseConnection getConnection() throws Exception {
		IDatabaseConnection conn=super.getConnection();
		DatabaseConfig config = conn.getConfig();
	     config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, 
	                        new HsqldbDataTypeFactory());
	     return conn;
	}

}
