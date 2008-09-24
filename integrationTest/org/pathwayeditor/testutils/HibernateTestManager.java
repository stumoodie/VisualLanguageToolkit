/**
 * 
 */
package org.pathwayeditor.testutils;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.database.util.HibernateDataSource;
import org.pathwayeditor.businessobjects.database.util.HqlDbSchema;
import org.pathwayeditor.businessobjects.database.util.IConnectionInfo;

/**
 * @author smoodie
 *  Creates and drops the database; also manages a hibernate session factory.
 *
 */
public class HibernateTestManager implements IDatabaseTester {
	private final IDatabaseTester delegator;
	private final HqlDbSchema schemaManager;
	private HibernateDataSource hibBuilder;
	
	public HibernateTestManager(String xmlConfigFile) {
		this(new HibernateDataSource(xmlConfigFile));
	}
	
	public HibernateTestManager(HibernateDataSource manager) {
		this.hibBuilder=manager;
		this.delegator = new HsqlJdbcDatabaseTester(hibBuilder);
		try{
			this.schemaManager = new HqlDbSchema(this.delegator.getConnection().getConnection(),
					new File("schema/EPE Schema Create.ddl"), new File("schema/EPE Schema Drop.ddl"));
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
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
		return hibBuilder.getSessionFactory();
	}
	
	/**
	 * Writes the ddl defined by hibernate to the database via the connection specified in the constructor.
	 * @throws Exception if there was an error obtaining the database connection and writing the ddl 
	 */
	public void createSchema() {
		try{
			schemaManager.createSchema();
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
			schemaManager.dropSchema();
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

	/**
	 * @param conn
	 */
	public void setConnectionInfo(IConnectionInfo conn) {
		hibBuilder=new HibernateDataSource(conn);
	}
}
