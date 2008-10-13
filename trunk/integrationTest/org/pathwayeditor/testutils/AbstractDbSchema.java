package org.pathwayeditor.testutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.pathwayeditor.businessobjects.database.util.ISchemaManager;

public abstract class AbstractDbSchema implements ISchemaManager {
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