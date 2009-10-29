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
import java.sql.Statement;

import org.hibernate.Session;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRepository;
import org.pathwayeditor.businessobjects.management.IConnectionInfo;

public class InitialisedDefaultDB {
	private static final String DB_DIR_NAME = DbProperties.getString("InitialisedDefaultDB.db_directory_name"); //$NON-NLS-1$
	// private static final String CONNECTION_URL = "jdbc:hsqldb:file:" +
	// DB_DIR_NAME + "/EmbeddedDb;shutdown=true";
	private static final String CONNECTION_URL = "jdbc:hsqldb:file:" //$NON-NLS-1$
			+ DB_DIR_NAME + DbProperties.getString("InitialisedDefaultDB.db_name"); //$NON-NLS-1$
	private static final String PASSWORD = ""; //$NON-NLS-1$
	private static final String USERNAME = "sa"; //$NON-NLS-1$
	private static final String DRIVER_NAME = "org.hsqldb.jdbcDriver"; //$NON-NLS-1$
	private static final String REPO_NAME = "Default"; //$NON-NLS-1$
	private static final String REPO_DESCRIPTION = "Default local repository"; //$NON-NLS-1$
	private static final String BUILD_NUM_STR = DbProperties.getString("InitialisedDefaultDB.schema_version_num"); //$NON-NLS-1$
	private static final int REPO_BUILD_NUM = Integer.valueOf(BUILD_NUM_STR);
	private static final File SCHEMA_DROP_FILE = new File(
			"schema/EPE Schema Drop.ddl"); //$NON-NLS-1$
	private static final String HIB_CONFIG_FILE = "hibernate.cfg.xml"; //$NON-NLS-1$
	private static final File SCHEMA_CREATE_FILE = new File(
			"schema/EPE Schema Create.ddl"); //$NON-NLS-1$
	private static final File DB_DIR = new File(DB_DIR_NAME);

	private final HibernateDataSource hibDataSource;
	private final File createScript;
	private final File dropScript;

	public InitialisedDefaultDB(String xmlConfigFile, IConnectionInfo connInfo,
			File createSchemaScript, File dropSchemaScript) {
		this(new HibernateDataSource(xmlConfigFile, connInfo),
				createSchemaScript, dropSchemaScript);
	}

	public InitialisedDefaultDB(HibernateDataSource hibDataSource,
			File createSchemaScript, File dropSchemaScript) {
		this.hibDataSource = hibDataSource;
		this.createScript = createSchemaScript;
		this.dropScript = dropSchemaScript;
	}

	public void removeSchema() throws SQLException {
		HqlDbSchema schemaManager = new HqlDbSchema(createScript, dropScript);
		dropSchema(schemaManager);
	}

	public void removeDB() {
		deleteDir(DB_DIR);
	}

	private void deleteDir(File directory) {
		if (directory.exists()) {
			for (File subFile : directory.listFiles()) {
				if (subFile.isDirectory()) {
					deleteDir(subFile);
				}
				if (!subFile.delete())
					System.err.println("Failed to remove file: " //$NON-NLS-1$
							+ subFile.getAbsolutePath());
			}
			if (!directory.delete()) {
				System.err.println("Failed to remove directory: " //$NON-NLS-1$
						+ directory.getAbsolutePath());
			}
		} else {
			System.err.println("Directory does not exists: " //$NON-NLS-1$
					+ directory.getAbsolutePath());
		}
	}

	@SuppressWarnings("deprecation")
	private void createSchema(ISchemaManager schemaManager) throws SQLException {
		Session sess = this.hibDataSource.getSessionFactory()
				.getCurrentSession();
		sess.beginTransaction();
		Connection conn = sess.connection();
		Statement stmt = conn.createStatement();
		stmt.execute("set property \"hsqldb.default_table_type\" 'cached'");
		stmt.execute("set property \"hsqldb.applog\" 3"); //$NON-NLS-1$
		stmt.close();
		schemaManager.setConnection(conn);
		schemaManager.createSchema();
		sess.getTransaction().commit();
	}

	@SuppressWarnings("deprecation")
	private void dropSchema(ISchemaManager schemaManager) throws SQLException {
		Session sess = this.hibDataSource.getSessionFactory()
				.getCurrentSession();
		sess.beginTransaction();
		Connection conn = sess.connection();
		schemaManager.setConnection(conn);
		schemaManager.dropSchema();
		sess.getTransaction().commit();
	}

	@SuppressWarnings("deprecation")
	public void buildSchema() throws SQLException {
		HqlDbSchema schemaManager = new HqlDbSchema(createScript, dropScript);
		createSchema(schemaManager);
	}

	public void addInitialData() {
		Session sess = this.hibDataSource.getSessionFactory()
				.getCurrentSession();
		sess.beginTransaction();
		HibRepository repo = new HibRepository(REPO_NAME, REPO_DESCRIPTION,
				REPO_BUILD_NUM);
		sess.save(repo);
		sess.getTransaction().commit();
	}

	public void shutDownDb() throws SQLException {
		this.hibDataSource.getSessionFactory().close();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONNECTION_URL);
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			stmt.execute("SHUTDOWN COMPACT"); //$NON-NLS-1$
			stmt.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static final void main(String[] args) {
		try {
			System.out.println("Starting ..."); //$NON-NLS-1$
			InitialisedDefaultDB worker = new InitialisedDefaultDB(
					HIB_CONFIG_FILE, new DefaultConnection(),
					SCHEMA_CREATE_FILE, SCHEMA_DROP_FILE);
			System.err.flush();
			System.out.print("Removing old database... "); //$NON-NLS-1$
			worker.removeDB();
			System.err.flush();
			System.out.println("Done."); //$NON-NLS-1$
			System.out.print("Building schema... "); //$NON-NLS-1$
			worker.buildSchema();
			System.err.flush();
			System.out.println("Done."); //$NON-NLS-1$
			System.out.print("Initialising data... "); //$NON-NLS-1$
			worker.addInitialData();
			System.err.flush();
			System.out.println("Done."); //$NON-NLS-1$
			System.out.print("Shutting down... "); //$NON-NLS-1$
			worker.shutDownDb();
			System.err.flush();
			System.out.println("Done."); //$NON-NLS-1$
			System.out.println("Database initialised successfully"); //$NON-NLS-1$
		} catch (SQLException e) {
			System.err.println("Error: initialisation failed"); //$NON-NLS-1$
			e.printStackTrace(System.err);
		} catch (Throwable e) {
			System.err.println("Something went wrong!"); //$NON-NLS-1$
			e.printStackTrace(System.err);
		}
	}

	private static class DefaultConnection implements IConnectionInfo {

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.database.util.IConnectionInfo#
		 * getDriverName()
		 */
		public String getDriverName() {
			return DRIVER_NAME;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.database.util.IConnectionInfo#
		 * getPassword()
		 */
		public String getPassword() {
			return PASSWORD;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.database.util.IConnectionInfo#
		 * getRepositoryName()
		 */
		public String getRepositoryName() {
			return REPO_NAME;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pathwayeditor.businessobjects.database.util.IConnectionInfo#getUrl
		 * ()
		 */
		public String getUrl() {
			return CONNECTION_URL;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.pathwayeditor.businessobjects.database.util.IConnectionInfo#
		 * getUserName()
		 */
		public String getUserName() {
			return USERNAME;
		}

	}
}
