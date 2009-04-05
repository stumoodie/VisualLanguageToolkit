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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class HqlDbSchema extends AbstractDbSchema implements ISchemaManager {
	final String schemaCreationDdl[];
	final String dropSchemaDdl[];

	public HqlDbSchema(String creationDdl[], String dropDdl[]) {
		super();
		this.schemaCreationDdl = new String[creationDdl.length];
		System.arraycopy(creationDdl, 0, this.schemaCreationDdl, 0, creationDdl.length);
		this.dropSchemaDdl = new String[dropDdl.length];
		System.arraycopy(dropDdl, 0, this.dropSchemaDdl, 0, dropDdl.length);
	}

	public HqlDbSchema(Connection connection, String creationDdl[], String dropDdl[]) {
		this(creationDdl, dropDdl);
		this.setConnection(connection);
	}

	public HqlDbSchema(Connection connection, File creationDdl, File dropDdl) {
		this(creationDdl, dropDdl);
		this.setConnection(connection);
	}	
	
	public HqlDbSchema(File creationDdl, File dropDdl) {
		super();
		BufferedReader is = null;
		try{
			is = new BufferedReader(new FileReader(creationDdl));
			List<String> ddlList = readSchemaFromFile(is);
			this.schemaCreationDdl = new String[ddlList.size()];
			ddlList.toArray(this.schemaCreationDdl);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("creationDdl not a valid file");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		try{
			is = new BufferedReader(new FileReader(dropDdl));
			List<String> ddlList = readSchemaFromFile(is);
			this.dropSchemaDdl = new String[ddlList.size()];
			ddlList.toArray(this.dropSchemaDdl);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("dropDdl not a valid file");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private List<String> readSchemaFromFile(BufferedReader is) throws IOException{
		List<String> ddlList = new ArrayList<String>();
		while(is.ready()){
			ddlList.add(is.readLine());
		}
		return ddlList;
	}
	
	protected String[] getSchemaCreationDdl() {
		return schemaCreationDdl;
	}

	protected String[] getSchemaDropDdl() {
		return dropSchemaDdl;
	}
}
