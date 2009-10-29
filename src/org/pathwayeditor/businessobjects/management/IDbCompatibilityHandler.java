/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.sql.SQLException;

/**
 * @author smoodie
 *
 */
public interface IDbCompatibilityHandler {

	void addUpgrader(IDbUpgrader upgrader);
	
	boolean canDoUpgrade() throws SQLException;
	
	boolean isUpgradeRequired() throws SQLException;
	
	void upgrade() throws SQLException;
	
}
