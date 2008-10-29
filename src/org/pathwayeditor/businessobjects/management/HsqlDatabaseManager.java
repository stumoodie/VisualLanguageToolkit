/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author smoodie
 *
 */
public class HsqlDatabaseManager implements IDatabaseManager {
	private final IConnectionInfo connectionInfo;
	private Connection conn = null;
	
	public HsqlDatabaseManager(IConnectionInfo connectionInfo){
		this.connectionInfo = connectionInfo;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#getConnectionInfo()
	 */
	public IConnectionInfo getConnectionInfo() {
		return this.connectionInfo;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#isAlive()
	 */
	public boolean isAlive() {
		boolean retVal = false;
		try {
			retVal = this.doesTestConnectionSucceed();
		} catch (SQLException e) {
			retVal = false;
		}
		return retVal;
	}
	
	private boolean doesTestConnectionSucceed() throws SQLException{
		boolean retVal = false;
		ResultSet rs = null;
		try{
			this.openConnection();
			Connection conn = this.getConnection();
			rs = conn.getMetaData().getTypeInfo();
			retVal = true;
		}
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// Suppress. as an exception here is likely to be related to a previous
					// exception, which we don't want to mask 
				}
			}
			this.closeConnection();
		}
		return retVal;
	}
	
	private void openConnection() throws SQLException{
		try{
			Class.forName(this.connectionInfo.getDriverName());
			this.conn  = DriverManager.getConnection(this.connectionInfo.getUrl());
			this.conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		catch(SQLException e){
			if(this.conn != null){
				this.conn.close();
			}
			throw e;
		}
	}
	
	
	private Connection getConnection(){
		return this.conn;
	}
	
	private void closeConnection() {
		try {
			if(this.conn != null){
				this.conn.close();
			}
		} catch (SQLException e) {
			// suppressing as this is likely to be used in a finally clause
			// and it is usually better if such methods don't raise an exception
			// as it may mask an exception that is being thrown.
		}
	}

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
		this.openConnection();
		Connection conn = this.getConnection();
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
			this.closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#startup()
	 */
	public void startup() throws SQLException {
		this.doesTestConnectionSucceed();
	}

}
