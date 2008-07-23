package org.pathwayeditor.businessobjects.database.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author nhanlon Returns a connection or the data to make a connection. Data
 * for a test, development and default database are stored. The default database
 * may also be set at runtime. A property file may be given in the constructor; the
 * default is 'database.properties' - this file should be located on your classpath.
 * 
 */
public class DataSourceProvider {
	private String derbyTestURL;
	private String derbyDevelopmentURL;
	private String derbyDriver;
	private String derbyDevelopmentUserName;
	private String derbyDevelopmentPassword;
	private String derbyDevelopmentInitialise;
	private String derbyTestInitialise;
	private String hsqlDevelopmentInitialise;
	private String hsqlTestInitialise;
	private String derbyTestUserName;
	private String derbyTestPassword;
	private String hsqlTestURL;
	private String hsqlDevelopmentURL;
	private String hsqlDriver;
	private String hsqlDevelopmentUserName;
	private String hsqlDevelopmentPassword;
	private String hsqlTestUserName;
	private String hsqlTestPassword;
	private String databaseTestName;
	private String databaseDevelopmentName;
	private String databaseDefaultName;
	private String derbyEmbeddedDriver;


	private static final String HSQLDB_DIALECT = "org.hibernate.dialect.HSQLDialect";
	private static final String DERBY_DIALECT = "org.hibernate.dialect.DerbyDialect";
	/**
	 * setup data, must located be on classpath
	 */
	private String defaultPropertyFile = "database.properties";
	private Connection connection;

	public static enum DatabaseTypes {
		DERBY_TEST, DERBY_DEVELOPMENT, HSQLDB_TEST, HSQLDB_DEVELOPMENT, EMBEDDED_DERBY_TEST, EMBEDDED_DERBY_DEVELOPMENT
	}

	private DatabaseTypes testDatabase;
	private DatabaseTypes defaultDatabase;
	private DatabaseTypes developmentDatabase;
	private DatabaseTypes embeddedDevelopmentDatabase;
	private DatabaseTypes embeddedTestDatabase;
	private String derbyEmbeddedDevelopmentURL;
	private String derbyEmbeddedTestURL;
	private String embeddedDatabaseDevelopmentName;
	private String embeddedDatabaseTestName;
	public DataSourceProvider() {
		this(null);
	}

	/**
	 * @param propertyFileStr read setup properties from this file instead of database.properties
	 */
	public DataSourceProvider(String propertyFileStr) {
		if (propertyFileStr != null)
			this.defaultPropertyFile = propertyFileStr;
		try {
			readProperties();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private DatabaseTypes makeDataBaseType(String databaseName) {
		if (databaseName.equals(DatabaseTypes.HSQLDB_TEST.toString()))
			return DatabaseTypes.HSQLDB_TEST;
		else if (databaseName.equals(DatabaseTypes.DERBY_TEST.toString()))
			return DatabaseTypes.DERBY_TEST;
		else if (databaseName.equals(DatabaseTypes.EMBEDDED_DERBY_TEST.toString()))
			return DatabaseTypes.EMBEDDED_DERBY_TEST;
		else if (databaseName
				.equals(DatabaseTypes.HSQLDB_DEVELOPMENT.toString()))
			return DatabaseTypes.HSQLDB_DEVELOPMENT;
		else if (databaseName.equals(DatabaseTypes.DERBY_DEVELOPMENT
				.toString()))
			return DatabaseTypes.DERBY_DEVELOPMENT;
		else if (databaseName.equals(DatabaseTypes.EMBEDDED_DERBY_DEVELOPMENT
				.toString()))
			return DatabaseTypes.EMBEDDED_DERBY_DEVELOPMENT;
		else
			throw new RuntimeException(
					"incorrect database name given in properties file");
	}

	protected void readProperties() throws IOException {
		readProperties(null);
	}

	protected void readProperties(Properties props) throws IOException {
		Properties properties = null;
		if (props == null) {
			properties = new Properties();
			InputStream is = DataSourceProvider.class
					.getResourceAsStream(defaultPropertyFile);
			if (is == null)
				throw new FileNotFoundException();
			properties.load(is);
		} else
			properties = props;
		embeddedDatabaseDevelopmentName=properties.getProperty("embedded.database.development.name");
		embeddedDatabaseTestName=properties.getProperty("embedded.database.test.name");
		databaseTestName = properties.getProperty("database.test.name");
		databaseDevelopmentName = properties
				.getProperty("database.development.name");
		databaseDefaultName=properties
		.getProperty("database.default.name");
		derbyDriver = properties.getProperty("derby.driver");
		derbyEmbeddedDriver = properties.getProperty("derby.embedded.driver");
		derbyDevelopmentURL = properties.getProperty("derby.development.url");
		derbyEmbeddedDevelopmentURL = properties.getProperty("derby.embedded.development.url");
		derbyDevelopmentUserName = properties
				.getProperty("derby.development.username");
		derbyDevelopmentPassword = properties
				.getProperty("derby.development.password");
		derbyTestURL = properties.getProperty("derby.test.url");
		derbyEmbeddedTestURL = properties.getProperty("derby.embedded.test.url");
		derbyTestUserName = properties.getProperty("derby.test.username");
		derbyTestPassword = properties.getProperty("derby.test.password");
		hsqlDriver = properties.getProperty("hsql.driver");
		hsqlTestURL = properties.getProperty("hsql.test.url");
		hsqlTestUserName = properties.getProperty("hsql.test.username");
		hsqlTestPassword = properties.getProperty("hsql.test.password");
		hsqlDevelopmentURL = properties.getProperty("hsql.development.url");
		hsqlDevelopmentUserName = properties
				.getProperty("hsql.development.username");
		hsqlDevelopmentPassword = properties
				.getProperty("hsql.development.password");
		derbyDevelopmentInitialise= properties
		.getProperty("derby.development.initialise");
		derbyTestInitialise= properties
		.getProperty("derby.test.initialise");
		hsqlDevelopmentInitialise= properties
		.getProperty("hsql.development.initialise");
		hsqlTestInitialise= properties
		.getProperty("hsql.test.initialise");
		this.defaultDatabase=makeDataBaseType(databaseDefaultName);
		this.testDatabase=makeDataBaseType(databaseTestName);
		this.embeddedTestDatabase=makeDataBaseType(embeddedDatabaseTestName);
		this.developmentDatabase=makeDataBaseType(databaseDevelopmentName);
		this.embeddedDevelopmentDatabase=makeDataBaseType(embeddedDatabaseDevelopmentName);
	}

	private Properties setupConnectionParameters(DatabaseTypes chosenDatabase) {
		switch (chosenDatabase) {
		case DERBY_TEST:
			return buildConnectionParameters(derbyDriver, derbyTestURL,
					derbyTestUserName, derbyTestPassword, DERBY_DIALECT, derbyTestInitialise);
		case EMBEDDED_DERBY_TEST:
			return buildConnectionParameters(derbyEmbeddedDriver, derbyEmbeddedTestURL,
					derbyTestUserName, derbyTestPassword, DERBY_DIALECT, derbyTestInitialise);
		case DERBY_DEVELOPMENT:
			return buildConnectionParameters(derbyDriver, derbyDevelopmentURL,
					derbyDevelopmentUserName, derbyDevelopmentPassword, DERBY_DIALECT, derbyDevelopmentInitialise);
		case EMBEDDED_DERBY_DEVELOPMENT:
			return buildConnectionParameters(derbyEmbeddedDriver, derbyEmbeddedDevelopmentURL,
					derbyDevelopmentUserName, derbyDevelopmentPassword, DERBY_DIALECT, derbyDevelopmentInitialise);	
		case HSQLDB_TEST:
			return buildConnectionParameters(hsqlDriver, hsqlTestURL,
					hsqlTestUserName, hsqlTestPassword, HSQLDB_DIALECT, hsqlTestInitialise);
		case HSQLDB_DEVELOPMENT:
			return buildConnectionParameters(hsqlDriver, hsqlDevelopmentURL,
					hsqlDevelopmentUserName, hsqlDevelopmentPassword, HSQLDB_DIALECT, hsqlDevelopmentInitialise);
		default:
			throw new RuntimeException("unrecognised database type");
		}

	}

	protected Properties buildConnectionParameters(String driver, String URL,
			String username, String password, String dialect, String initialisePolicy) {
		Properties connectionBuildParameters = new Properties();
		connectionBuildParameters.setProperty("driver", driver);
		connectionBuildParameters.setProperty("URL", URL);
		connectionBuildParameters.setProperty("username", username);
		connectionBuildParameters.setProperty("password", password);
		connectionBuildParameters.setProperty("database.dialect", dialect);
		connectionBuildParameters.setProperty("initialise", initialisePolicy);
		return connectionBuildParameters;
	}

	private Connection buildConnection(Properties connectionBuildParameters) {
		Connection conn = null;
		try {
			Class.forName(connectionBuildParameters.getProperty("driver"));
			conn = DriverManager.getConnection(connectionBuildParameters
					.getProperty("URL"), connectionBuildParameters
					.getProperty("username"), connectionBuildParameters
					.getProperty("password"));
			conn.setAutoCommit(true);
		} catch (SQLException sql) {
			conn = null;
			throw new RuntimeException(sql);
		} catch (ClassNotFoundException e) {
			conn = null;
			throw new RuntimeException(e);
		} finally {
			if (conn == null)
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
		}
		return conn;
	}
	
	public DatabaseTypes getDatabase(){
		return defaultDatabase;
	}
	
	public DatabaseTypes getTestDatabase(){
		return testDatabase;
	}
	
	public DatabaseTypes getDevelopmentDatabase(){
		return developmentDatabase;
	}

	public Connection getConnection() {
		connection = buildConnection(
				setupConnectionParameters(defaultDatabase));
		return connection;
	}
	
	public Connection getTestConnection() {
		connection = buildConnection(
				setupConnectionParameters(testDatabase));
		return connection;
	}
	
	public Connection getDevelopmentConnection() {
		connection = buildConnection(
				setupConnectionParameters(developmentDatabase));
		return connection;
	}
	

	public Connection getConnection(DatabaseTypes type) {
		connection = buildConnection(
				setupConnectionParameters(type));
		return connection;
	}

	public void setDefaultDatabase(DatabaseTypes database) {
		this.defaultDatabase=database;
	}	

	public Properties getConnectionBuildParams() {
		return setupConnectionParameters(defaultDatabase);
	}
	
	public Properties getTestConnectionBuildParams() {
		return setupConnectionParameters(testDatabase);
	}
	
	public Properties getDevelopmentConnectionBuildParams() {
		return setupConnectionParameters(developmentDatabase);
	}

	public Properties getEmbeddedDevelopmentConnectionBuildParams() {
		return setupConnectionParameters(embeddedDevelopmentDatabase);
	}
	
	public Properties getEmbeddedTestConnectionBuildParams() {
		return setupConnectionParameters(embeddedTestDatabase);
	}
	
	protected String getDerbyTestURL() {
		return derbyTestURL;
	}
	

	protected String getDerbyTestUserName() {
		return derbyTestUserName;
	}

	protected String getHsqlTestURL() {
		return hsqlTestURL;
	}

	protected String getHsqlDevelopmentURL() {
		return hsqlDevelopmentURL;
	}

	protected String getHsqlDriver() {
		return hsqlDriver;
	}

	protected String getHsqlDevelopmentUserName() {
		return hsqlDevelopmentUserName;
	}

	protected String getHsqlDevelopmentPassword() {
		return hsqlDevelopmentPassword;
	}

	protected String getHsqlTestUserName() {
		return hsqlTestUserName;
	}

	protected String getHsqlTestPassword() {
		return hsqlTestPassword;
	}

	protected String getDerbyTestPassword() {
		return derbyTestPassword;
	}

	protected String getDerbyDevelopmentURL() {
		return derbyDevelopmentURL;
	}

	protected String getDerbyDevelopmentUserName() {
		return derbyDevelopmentUserName;
	}

	protected String getDerbyDevelopmentPassword() {
		return derbyDevelopmentPassword;
	}

	protected String getDerbyDriver() {
		return derbyDriver;
	}

	protected String getDerbyDevelopmentInitialise() {
		return derbyDevelopmentInitialise;
	}

	protected String getDerbyTestInitialise() {
		return derbyTestInitialise;
	}

	protected String getHsqlDevelopmentInitialise() {
		return hsqlDevelopmentInitialise;
	}

	protected String getHsqlTestInitialise() {
		return hsqlTestInitialise;
	}



}
