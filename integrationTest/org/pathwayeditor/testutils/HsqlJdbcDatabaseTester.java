/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.testutils;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.pathwayeditor.businessobjects.database.util.HibernateDataSource;

/**
 * @author nhanlon
 *
 */
public class HsqlJdbcDatabaseTester extends JdbcDatabaseTester{

	/**
	 * @param driverClass
	 * @param connectionUrl
	 * @throws ClassNotFoundException 
	 */
	public HsqlJdbcDatabaseTester(String driverClass, String connectionUrl) throws ClassNotFoundException {
		super(driverClass, connectionUrl);
	}

	/**
	 * @param driverClass
	 * @param connectionUrl
	 * @param userName
	 * @param password
	 * @throws ClassNotFoundException 
	 */
	public HsqlJdbcDatabaseTester(String driverClass, String connectionUrl,
			String userName, String password) throws ClassNotFoundException {
		super(driverClass,connectionUrl,userName,password);
	}
	public HsqlJdbcDatabaseTester(HibernateDataSource builder) throws ClassNotFoundException {
		super(builder.getDriverClass(),builder.getConnectionUrl(),builder.getUserName(),builder.getPassword());
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
