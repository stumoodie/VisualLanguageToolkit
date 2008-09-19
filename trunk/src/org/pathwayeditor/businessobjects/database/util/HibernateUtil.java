package org.pathwayeditor.businessobjects.database.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.testutils.StubSessionFactory;

/**
 * A Hibernate utility class providing session factory access.
 * 
 * @author Richard Adams
 * 
 */
public class HibernateUtil {
	private static HibernateDataSource dataSource = new HibernateDataSource(
			"hibernate.cfg.xml");
	private static SessionFactory defaultSessionFactory;
	private static Session session;

	// ///////////// THIS IS THE FIX FOR LEOPARD CLASSLOADER BUG PLEASE DONT
	// REMOVE///////////
	static {
		if (System.getProperty("os.name").indexOf("Mac") != -1)
			Thread.currentThread().setContextClassLoader(
					ClassLoader.getSystemClassLoader());
	}
	static {
		defaultSessionFactory = dataSource.getSessionFactory();
	}

	/**
	 * A static method to get the default Session factory singleton; may create
	 * a new database if sessionFactory is instantiated by this method call. To
	 * change this behaviour modify //TODO - nh
	 */
	public static SessionFactory getSessionFactory() {
		if (defaultSessionFactory == null) {
			defaultSessionFactory = dataSource.getSessionFactory();
		}
		return defaultSessionFactory;
	}
	
	public static void setTestSessionFactoryAsDefault(String testFileName) {
		dataSource = new HibernateDataSource(testFileName);
		defaultSessionFactory = dataSource.getSessionFactory();
	}

	public static void setStubSessionFactoryAsDefault() {
		defaultSessionFactory = new StubSessionFactory();
	}

	public static void commit(Session session) {
		session.getTransaction().commit();
	}

	public static void commit() {
		session = defaultSessionFactory.getCurrentSession();
		if (session.getTransaction() != null
				&& session.getTransaction().isActive())
			session.getTransaction().commit();
	}

	/**
	 * @return
	 */
	public static Session getSession() {
		session = defaultSessionFactory.getCurrentSession();
		session.beginTransaction();
		return session;
	}

	/**
	 * @param conn
	 */
	public static void setConnectionInfo(IConnectionInfo conn) {
		dataSource.setConnectionInfo(conn);
		defaultSessionFactory = dataSource.getSessionFactory();
	}

}
