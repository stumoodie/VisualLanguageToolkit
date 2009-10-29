/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.sql.SQLException;

import org.pathwayeditor.businessobjects.database.management.IDbConnectionManager;

/**
 * @author smoodie
 *
 */
public interface IDbUpgrader {
	public static final int VERSION_NOT_SET = -1;
	
	void setRequestedTargetVersion(int requiredVersion);

	int getRequestedTargetVersion();

	void doUpgrade() throws SQLException;

	boolean canDoUpgrade() throws SQLException;
	
	boolean isUpgradeRequired() throws SQLException;

	void setConnectionManager(IDbConnectionManager connManager);
	
	IDbConnectionManager getConnectionManager();
	
}
