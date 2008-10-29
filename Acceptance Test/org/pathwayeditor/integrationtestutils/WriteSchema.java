/**
 * 
 */
package org.pathwayeditor.integrationtestutils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.HSQLDialect;

/**
 * @author smoodie
 *
 */
public class WriteSchema {
	private final Configuration config;
	
	public WriteSchema(){
		config = new Configuration();
		config.configure("test_hibernate.cfg.xml");
//		config.setProperty(HIB_PROP_DRIVER_CLASS, JDBC_DRIVER_CLASS);
//		config.setProperty(HIB_PROP_URL, JDBC_URL);
//		config.setProperty(HIB_PROP_USERNAME, JDBC_USERNAME);
//		config.setProperty(HIB_PROP_PASSWORD, JDBC_PASSWORD);
	}

	public void writeCreationDdl(OutputStream out) throws IOException{
		String createSchemaDdl[] = config.generateSchemaCreationScript(new HSQLDialect());
		writeToFile(createSchemaDdl, out);
	}
	
	public void writeDropDdl(OutputStream out) throws IOException{
		String dropSchemaDdl[] = config.generateDropSchemaScript(new HSQLDialect());
		writeToFile(dropSchemaDdl, out);
	}
	
	private void writeToFile(String ddl[], OutputStream stream) throws IOException{
		OutputStreamWriter writer = new OutputStreamWriter(stream);
		for(String ddlStmt : ddl){
			writer.write("\"");
			writer.write(ddlStmt);
			writer.write("\",\n");
//			System.out.println("DEBUG: " + ddlStmt);
		}
		writer.flush();
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		WriteSchema writeSchema = new WriteSchema();
		System.out.println("Creation DDL ======================");
		writeSchema.writeCreationDdl(System.out);
		System.out.println("===================================");
		System.out.println("Drop DDL ==========================");
		writeSchema.writeDropDdl(System.out);
		System.out.println("===================================");
	}

}
