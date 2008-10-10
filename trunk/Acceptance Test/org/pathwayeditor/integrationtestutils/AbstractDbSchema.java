package org.pathwayeditor.integrationtestutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDbSchema {

	protected final Connection connection;

	public AbstractDbSchema(Connection connection) {
		this.connection = connection;
	}

	public void createSchema() throws SQLException{
		executeDdl(getSchemaCreationDdl());
	}
	
	public void dropSchema() throws SQLException{
		executeDdl(getSchemaDropDdl());
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
				stmt.close();
			}
		}
	}

}