/**
 * 
 */
package org.pathwayeditor.businessobjects.database.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.management.IConnectionInfo;


/**
 * @author smoodie
 *
 */
public class DbConnectionManager implements IDbConnectionManager {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private final IConnectionInfo connectionInfo;
	private Connection conn = null;
	
	public DbConnectionManager(IConnectionInfo connectionInfo){
		this.connectionInfo = connectionInfo;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#getConnectionInfo()
	 */
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#getConnectionInfo()
	 */
	@Override
	public IConnectionInfo getConnectionInfo() {
		return this.connectionInfo;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDatabaseManager#isAlive()
	 */
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#isAlive()
	 */
	@Override
	public boolean isAlive() {
		boolean retVal = false;
		try {
			retVal = this.doesTestConnectionSucceed();
		} catch (SQLException e) {
			retVal = false;
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#doesTestConnectionSucceed()
	 */
	@Override
	public boolean doesTestConnectionSucceed() throws SQLException{
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
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#openConnection()
	 */
	@Override
	public void openConnection() throws SQLException{
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
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#getConnection()
	 */
	@Override
	public Connection getConnection(){
		return this.conn;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.database.management.IDbConnectionManager#closeConnection()
	 */
	@Override
	public void closeConnection() {
		try {
			if(this.conn != null){
				this.conn.close();
			}
		} catch (SQLException e) {
			// suppressing as this is likely to be used in a finally clause
			// and it is usually better if such methods don't raise an exception
			// as it may mask an exception that is being thrown.
			logger.error("error occurred on close and is being suppressed", e);
		}
	}
}
