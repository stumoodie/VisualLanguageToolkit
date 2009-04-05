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
package org.pathwayeditor.businessobjects.database.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDbSchema {
	protected Connection connection;

	public AbstractDbSchema(){
		this.connection = null;
	}
	
	public AbstractDbSchema(Connection connection) {
		this.connection = connection;
	}

	public void createSchema() throws SQLException{
		executeDdl(getSchemaCreationDdl());
	}
	
	public void dropSchema() throws SQLException{
		executeDdl(getSchemaDropDdl());
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	protected abstract String[] getSchemaCreationDdl();

	protected abstract String[] getSchemaDropDdl();

	private void executeDdl(String[] ddlArray) throws SQLException {
		for (String ddl : ddlArray) {
			Statement stmt = null;
			try{
				stmt = this.connection.createStatement();
				stmt.execute(ddl);
			}
			finally{
				if(stmt != null){
					stmt.close();
				}
			}
		}
	}
}