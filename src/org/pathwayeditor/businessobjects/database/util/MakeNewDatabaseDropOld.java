/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author nhanlon Drops the (default) database and recreates; a ddl file is used.
 */
public class MakeNewDatabaseDropOld {
	// public static final String CONNECTION_URL="jdbc:hsqldb:mem:dev";
	public static final String CONNECTION_URL = IConnectionInfo.DATABASE_DEFAULT_URL;
	private static final String PASSWORD = "";
	private static final String USERNAME = "sa";
	private static Connection conn;
	private static HqlDbSchema schemaManager;

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
		schemaManager = new HqlDbSchema(conn, new File(
				"schema/EPE Schema Create.ddl"), new File(
				"schema/EPE Schema Drop.ddl"));
		try {
			schemaManager.dropSchema();
			schemaManager.createSchema();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
