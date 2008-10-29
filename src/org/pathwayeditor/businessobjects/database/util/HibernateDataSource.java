/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.pathwayeditor.businessobjects.management.IConnectionInfo;

/**
 * @author nhanlon 
 * Class to handle passing data to and from hibconfig
 *
 */
public class HibernateDataSource {
	public static final String HIB_PROP_DRIVER_CLASS = "hibernate.connection.driver_class";
	public static final String HIB_PROP_URL = "hibernate.connection.url";
	public static final String HIB_PROP_USERNAME = "hibernate.connection.username";
	public static final String HIB_PROP_PASSWORD = "hibernate.connection.password";
	private Configuration hibConfig;
	private String driverClass;
	private String connectionUrl;
	private String userName;
	private String password;

	/**
	 * @param xmlConfigFile
	 */
	public HibernateDataSource(String xmlConfigFile) {
		Configuration hibConf = new Configuration();
		this.hibConfig = hibConf.configure(xmlConfigFile);
		this.driverClass = hibConfig.getProperty(HIB_PROP_DRIVER_CLASS);
		this.connectionUrl = hibConfig.getProperty(HIB_PROP_URL);
		this.userName = hibConfig.getProperty(HIB_PROP_USERNAME);
		this.password = hibConfig.getProperty(HIB_PROP_PASSWORD);
	}

	public HibernateDataSource(String xmlConfigFile, IConnectionInfo connInfo) {
		this(xmlConfigFile);
		this.setConnectionInfo(connInfo);
	}

		public String getDriverClass() {
		return this.driverClass;
	}

	public String getConnectionUrl() {
		return this.connectionUrl;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * @return hibernate sessionfactory generated by this instance (note generation is expensive and so 
	 * sessionfactory should be used as a singleton)
	 */
	public SessionFactory getSessionFactory() {
		return hibConfig.buildSessionFactory();
	}

	/**
	 * @return hibconfig  - generated by this instance
	 */
	public Configuration getHibConfig() {
		return hibConfig;
	}

	/**
	 * @param conn information for a jdbc connection
	 */
	public void setConnectionInfo(IConnectionInfo conn) {
		hibConfig.setProperty(HibernateDataSource.HIB_PROP_DRIVER_CLASS,conn.getDriverName());
		hibConfig.setProperty(HibernateDataSource.HIB_PROP_URL,conn.getUrl());
		hibConfig.setProperty(HibernateDataSource.HIB_PROP_USERNAME,conn.getUserName());
		hibConfig.setProperty(HibernateDataSource.HIB_PROP_PASSWORD, conn.getPassword());
	}

}
