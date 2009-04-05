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
package org.pathwayeditor.businessobjects.database.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author nhanlon A ddl file is used for schema creation/destruction.
 */
public class MakeNewDatabaseDropOld {
	 public static final String CONNECTION_URL="jdbc:hsqldb:file:./DB/EmbeddedDb;ifexists=true";
	//public static final String CONNECTION_URL = "jdbc:hsqldb:hsql://localhost/epeDev";
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
