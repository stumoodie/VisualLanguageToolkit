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
package org.pathwayeditor.businessobjects.database.management;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.pathwayeditor.businessobjects.management.IConnectionInfo;
import org.pathwayeditor.businessobjects.management.IDatabaseManager;

/**
 * @author smoodie
 *
 */
public class HsqlDatabaseManager implements IDatabaseManager {
//	private final IConnectionInfo connectionInfo;
//	private Connection conn = null;
	private final IDbConnectionManager connManager;
	
	
	public HsqlDatabaseManager(IConnectionInfo connectionInfo){
//		this.connectionInfo = connectionInfo;
		this.connManager = new DbConnectionManager(connectionInfo);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#getConnectionInfo()
	 */
	public IConnectionInfo getConnectionInfo() {
		return this.connManager.getConnectionInfo();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#isAlive()
	 */
	public boolean isAlive() {
		return this.connManager.isAlive();
	}
	
//	private boolean doesTestConnectionSucceed() throws SQLException{
//		boolean retVal = false;
//		ResultSet rs = null;
//		try{
//			this.openConnection();
//			Connection conn = this.getConnection();
//			rs = conn.getMetaData().getTypeInfo();
//			retVal = true;
//		}
//		finally{
//			if(rs != null){
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					// Suppress. as an exception here is likely to be related to a previous
//					// exception, which we don't want to mask 
//				}
//			}
//			this.closeConnection();
//		}
//		return retVal;
//	}
	
//	private void openConnection() throws SQLException{
//		try{
//			Class.forName(this.connectionInfo.getDriverName());
//			this.conn  = DriverManager.getConnection(this.connectionInfo.getUrl());
//			this.conn.setAutoCommit(true);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//		catch(SQLException e){
//			if(this.conn != null){
//				this.conn.close();
//			}
//			throw e;
//		}
//	}
	
	
//	private Connection getConnection(){
//		return this.conn;
//	}
//	
//	private void closeConnection() {
//		try {
//			if(this.conn != null){
//				this.conn.close();
//			}
//		} catch (SQLException e) {
//			// suppressing as this is likely to be used in a finally clause
//			// and it is usually better if such methods don't raise an exception
//			// as it may mask an exception that is being thrown.
//		}
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#isStartupImplemented()
	 */
	public boolean isStartupImplemented() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#shutdown()
	 */
	public void shutdown() throws SQLException {
		this.connManager.openConnection();
		Connection conn = this.connManager.getConnection();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			stmt.execute("SHUTDOWN COMPACT");
		}
		finally{
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// Suppress. as an exception here is likely to be related to a previous
					// exception, which we don't want to mask 
				}
			}
			this.connManager.closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#startup()
	 */
	public void startup() throws SQLException {
		this.connManager.doesTestConnectionSucceed();
	}

}
