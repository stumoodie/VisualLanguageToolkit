package org.pathwayeditor.businessobjects.database.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.pathwayeditor.businessobjects.database.util.DataSourceProvider.DatabaseTypes;


/**
 * @author nhanlon Builds a hibernate session factory and also creates the
 *         database. Reads connection properties from the DataSourceProvider
 *         class, or from a given properties file, or using a given datbase
 *         type, or using the default database types for 'test' and
 *         'development'. Hibernate specific properties are read from the
 *         default hibernate.cfg.xml file. Use this class and any created
 *         sessionFactories as singletons for performance.
 * 
 */
public class MakeDatabase {
	protected Configuration hibConf;
	/**
	 * A singleton which provides connection information
	 */
	private static DataSourceProvider dataSourceProvider;
	private static boolean dataSourceCreated = false;

	public MakeDatabase() {
		if (!dataSourceCreated) {
			dataSourceProvider = new DataSourceProvider();
			dataSourceCreated = true;
		}
	}

	/**
	 * @param parameters
	 *            a properties object with connection setup data
	 */
	protected void buildParameters(Properties params) {
		hibConf = new Configuration();
		hibConf.setProperty("hibernate.dialect", params
				.getProperty("database.dialect"));
		hibConf.setProperty("hibernate.connection.username", params
				.getProperty("username"));
		hibConf.setProperty("hibernate.connection.password", params
				.getProperty("password"));
		hibConf.setProperty("hibernate.connection.driver_class", params
				.getProperty("driver"));
		hibConf.setProperty("hibernate.connection.url", params
				.getProperty("URL"));
		hibConf.setProperty("hibernate.hbm2ddl.auto", params
				.getProperty("initialise"));
		hibConf.setProperty("hibernate.c3p0.min_size","5");
		hibConf.setProperty("hibernate.c3p0.max_size","20");
		hibConf.configure();// reads properties set in the default
		// hibernate.cfg.xml file (which needs to be in the classpath root)
	}
	//TODO - test
	public SessionFactory getSessionfactory() {
		buildParameters(dataSourceProvider.getConnectionBuildParams());
		SessionFactory sessionFactory = hibConf.buildSessionFactory();
		return sessionFactory;
	}
	//TODO - test
	public SessionFactory getTestSessionfactory() {
		Properties params = dataSourceProvider.getTestConnectionBuildParams();
		params.setProperty("hibernate.connection.autocommit", "true");
		params.setProperty("hibernate.connection.release_mode", "after_transaction");
		params.setProperty("connection.isolation","0");
		buildParameters(params);
		SessionFactory sessionFactory = hibConf.buildSessionFactory();
		return sessionFactory;
	}
	//TODO - test
	public SessionFactory getDevelopmentSessionfactory() {
		buildParameters(dataSourceProvider
				.getDevelopmentConnectionBuildParams());
		SessionFactory sessionFactory = hibConf.buildSessionFactory();				
		return sessionFactory;
	}
	//TODO - test
	public SessionFactory getSessionfactory(Properties props) {
		buildParameters(props);
		SessionFactory sessionFactory = hibConf.buildSessionFactory();
		return sessionFactory;
	}
	//TODO - test
	public SessionFactory getSessionfactory(DatabaseTypes type) {
		dataSourceProvider.setDefaultDatabase(type);
		Properties params = dataSourceProvider.getTestConnectionBuildParams();
		buildParameters(params);
		SessionFactory sessionFactory = hibConf.buildSessionFactory();
		return sessionFactory;
	}

	public DataSourceProvider getDataSourceProvider() {
		return dataSourceProvider;
	}
}
