package org.pathwayeditor.testutils;

import java.sql.Connection;

public class HqlDbSchema extends AbstractDbSchema {
	final String schemaCreationDdl[];
	final String dropSchemaDdl[];

	public HqlDbSchema(Connection connection, String creationDdl[], String dropDdl[]) {
		super(connection);
		this.schemaCreationDdl = new String[creationDdl.length];
		System.arraycopy(creationDdl, 0, this.schemaCreationDdl, 0, creationDdl.length);
		this.dropSchemaDdl = new String[dropDdl.length];
		System.arraycopy(dropDdl, 0, this.dropSchemaDdl, 0, dropDdl.length);
	}

	protected String[] getSchemaCreationDdl() {
		return schemaCreationDdl;
	}

	protected String[] getSchemaDropDdl() {
		return dropSchemaDdl;
	}
}
