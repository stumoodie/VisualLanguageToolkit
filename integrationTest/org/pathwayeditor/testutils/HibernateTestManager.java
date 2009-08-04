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
package org.pathwayeditor.testutils;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.IOperationListener;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.database.util.HibernateDataSource;
import org.pathwayeditor.businessobjects.database.util.HqlDbSchema;
import org.pathwayeditor.businessobjects.management.IConnectionInfo;

/**
 * @author smoodie
 *  Creates and drops the database; also manages a hibernate session factory.
 *
 */
public class HibernateTestManager implements IDatabaseTester {
	private final IDatabaseTester delegator;
	private final HqlDbSchema schemaManager;
	private HibernateDataSource hibBuilder;
	private SessionFactory sessionFactory = null;
	
	public HibernateTestManager(String xmlConfigFile, File createSchemaScript, File dropSchemaScript) {
		this(new HibernateDataSource(xmlConfigFile), createSchemaScript, dropSchemaScript);
	}
	
	public HibernateTestManager(HibernateDataSource manager, File createSchemaScript, File dropSchemaScript) {
		try{
			this.hibBuilder=manager;
			this.delegator = new HsqlJdbcDatabaseTester(hibBuilder);
//			this.schemaManager = new HqlDbSchema(this.delegator.getConnection().getConnection(),
//					new File("schema/EPE Schema Create.ddl"), new File("schema/EPE Schema Drop.ddl"));
			this.schemaManager = new HqlDbSchema(this.delegator.getConnection().getConnection(), createSchemaScript, dropSchemaScript);
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

	/**
	 * Returns the session factory to be used for testing. Will only initialise the session factory once
	 * and so this instance will return the same session factory instance for every call of this method.
	 */
	public void createHibernateSessionFactory(){
		if(sessionFactory == null){
			sessionFactory = hibBuilder.getSessionFactory();
		}
		else{
			throw new IllegalStateException("Hibernate factor has not been discarded. This will result in a resource leak");
		}
	}
	
	public SessionFactory getSessionFactory(){
		if(this.sessionFactory == null){
			throw new IllegalStateException("Hibernate session has not been created");
		}
		return this.sessionFactory;
	}
	
	public void discardHibernateSessionFactory(){
		if(sessionFactory != null){
			this.sessionFactory.close();
			this.sessionFactory = null;
		}
	}
	
	/**
	 * Writes the ddl defined by hibernate to the database via the connection specified in the constructor.
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
		hibBuilder.setConnectionInfo(conn);
	}

	/* (non-Javadoc)
	 * @see org.dbunit.IDatabaseTester#setOperationListener(org.dbunit.IOperationListener)
	 */
	public void setOperationListener(IOperationListener arg0) {
		
	}
}
