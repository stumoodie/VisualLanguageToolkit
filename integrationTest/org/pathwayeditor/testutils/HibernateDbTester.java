/**
 * 
 */
package org.pathwayeditor.testutils;

import java.sql.Connection;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.HSQLDialect;

/**
 * @author smoodie
 *
 */
public class HibernateDbTester implements IDatabaseTester {
	private static final String HIB_PROP_DRIVER_CLASS = "hibernate.connection.driver_class";
	private static final String HIB_PROP_URL = "hibernate.connection.url";
	private static final String HIB_PROP_USERNAME = "hibernate.connection.username";
	private static final String HIB_PROP_PASSWORD = "hibernate.connection.password";
	private final IDatabaseTester delegator;
	private final Configuration hibConfig;
	
	public HibernateDbTester(String xmlConfigFile){
		Configuration hibConf = new Configuration();
		this.hibConfig = hibConf.configure(xmlConfigFile);
		String driverClass = hibConfig.getProperty(HIB_PROP_DRIVER_CLASS);
		String connectionUrl = hibConfig.getProperty(HIB_PROP_URL);
		String userName = hibConfig.getProperty(HIB_PROP_USERNAME);
		String password = hibConfig.getProperty(HIB_PROP_PASSWORD);
		this.delegator = new JdbcDatabaseTester(driverClass, connectionUrl, userName, password);
	}
	
	
	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#closeConnection(org.dbunit.database.IDatabaseConnection)
	 */
	public void closeConnection(IDatabaseConnection connection)	throws Exception {
		this.delegator.closeConnection(connection);
	}

	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#getConnection()
	 */
	public IDatabaseConnection getConnection() throws Exception {
		return this.delegator.getConnection();
	}

	public SessionFactory getHibernateSessionFactory(){
		return this.hibConfig.buildSessionFactory();
	}
	
	/**
	 * Writes the ddl defined by hibernate to the database via the connection specified in the constructor.
	 * @throws Exception if there was an error obtaining the database connection and writing the ddl 
	 */
	public void createSchema() {
		try{
			String[] ddl = hibConfig.generateSchemaCreationScript(new HSQLDialect());
			Connection conn = this.delegator.getConnection().getConnection();
			conn.setAutoCommit(false);
			for(String ddlStatement : ddl){
				conn.createStatement().execute(ddlStatement);
			}
			conn.commit();
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Writes the ddl defined by hibernate to the database via the connection specified in the constructor.
	 * @throws Exception if there was an error obtaining the database connection and writing the ddl 
	 */
	public void dropSchema() {
		try{
			String[] ddl = hibConfig.generateDropSchemaScript(new HSQLDialect());
			Connection conn = this.delegator.getConnection().getConnection();
			conn.setAutoCommit(false);
			for(String ddlStatement : ddl){
				conn.createStatement().execute(ddlStatement);
			}
			conn.commit();
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#getDataSet()
	 */
	public IDataSet getDataSet() {
		return this.delegator.getDataSet();
	}

	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#onSetup()
	 */
	public void onSetup() throws Exception {
		this.delegator.onSetup();
	}

	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#onTearDown()
	 */
	public void onTearDown() throws Exception {
		this.delegator.onTearDown();
	}

	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#setDataSet(org.dbunit.dataset.IDataSet)
	 */
	public void setDataSet(IDataSet dataSet) {
		this.delegator.setDataSet(dataSet);
	}

	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#setSchema(java.lang.String)
	 */
	public void setSchema(String schema) {
		this.delegator.setSchema(schema);
	}

	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#setSetUpOperation(org.dbunit.operation.DatabaseOperation)
	 */
	public void setSetUpOperation(DatabaseOperation setUpOperation) {
		this.delegator.setSetUpOperation(setUpOperation);
	}

	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#setTearDownOperation(org.dbunit.operation.DatabaseOperation)
	 */
	public void setTearDownOperation(DatabaseOperation tearDownOperation) {
		this.delegator.setTearDownOperation(tearDownOperation);
	}

}
