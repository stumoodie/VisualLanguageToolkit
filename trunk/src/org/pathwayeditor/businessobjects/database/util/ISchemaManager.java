/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author smoodie
 *
 */
public interface ISchemaManager {

	void createSchema() throws SQLException;
	
	void dropSchema() throws SQLException;
	
	void setConnection(Connection conn);
	
	Connection getConnection();
}