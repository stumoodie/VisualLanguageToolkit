/**
 * 
 */
package org.pathwayeditor.businessobjects.database.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.pathwayeditor.businessobjects.management.IDbUpgrader;

/**
 * @author smoodie
 *
 */
public class DbUpdater1To2 implements IDbUpgrader {
	private static final Integer EXPECTED_SOURCE = 1;
	private static final Integer EXPECTED_TARGET = 2;
	private static final int FIRST_COL = 1;
	private Integer requestedTargetVersion = null;
	private IDbConnectionManager connManager;
	
	public DbUpdater1To2(){
	}
	
	
	public void setConnectionManager(IDbConnectionManager dbManager){
		this.connManager = dbManager;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDbUpgrader#doUpgrade(java.sql.Connection)
	 */
	public void doUpgrade() throws SQLException {
		if(!canDoUpgrade()) throw new IllegalStateException("Cannot perfrom upgrade on db version as set");

		Connection conn = this.connManager.getConnection();
		Statement stmt = null;
		PreparedStatement prepStmt = null;
		try{
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.execute("Alter table RootAttribute add column current_node_id integer");
			conn.commit();
			stmt.executeUpdate("update RootAttribute a\nset a.current_node_id = (select n.id\nfrom RootObjectNode n\nwhere n.root_attribute_id = a.id)");
			prepStmt = conn.prepareStatement("update Repository set build_num = ?");
			prepStmt.setInt(1, EXPECTED_TARGET);
			prepStmt.executeUpdate();
			conn.commit();
		}
		finally{
			if(stmt != null){
				stmt.close();
			}
			if(prepStmt != null){
				prepStmt.close();
			}
			this.connManager.closeConnection();
		}
	}
	
	public boolean canDoUpgrade() throws SQLException{
		int currVersion = readDbVersion();

		return EXPECTED_SOURCE.equals(currVersion) && this.requestedTargetVersion != null && EXPECTED_TARGET.intValue() >= getRequestedTargetVersion() && this.connManager != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDbUpgrader#setTargetVersion(int)
	 */
	public void setRequestedTargetVersion(int targetVersion) {
		this.requestedTargetVersion = targetVersion;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDbUpgrader#getTargetVersion()
	 */
	public int getRequestedTargetVersion() {
		return (this.requestedTargetVersion == null) ? VERSION_NOT_SET : this.requestedTargetVersion;
	}

	private int readDbVersion() throws SQLException{
		PreparedStatement stmt = null;
		try{
			int retVal;
			this.connManager.openConnection();
			Connection conn = this.connManager.getConnection();
			stmt = conn.prepareStatement("select build_num from Repository");
			ResultSet rc = stmt.executeQuery();
			if(rc.next()){
				retVal = rc.getInt(FIRST_COL);
			}
			else{
				throw new SQLException("The database is not propertly initialised. No version number was found");
			}
			return retVal;
		}
		finally{
			if(stmt != null){
				stmt.close();
			}
			this.connManager.closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDbUpgrader#isUpgradeRequired()
	 */
	public boolean isUpgradeRequired() throws SQLException {
		return !EXPECTED_TARGET.equals(readDbVersion());
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IDbUpgrader#getConnectionManager()
	 */
	public IDbConnectionManager getConnectionManager() {
		return this.connManager;
	}
}
