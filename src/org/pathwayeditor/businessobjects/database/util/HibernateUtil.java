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
	private static HibernateDataSource dataSource;
	private static SessionFactory defaultSessionFactory;
	private static Session session;
	private static boolean testingOnly;

	// ///////////// THIS IS THE FIX FOR LEOPARD CLASSLOADER BUG PLEASE DONT
	// REMOVE///////////
	static {
		if (System.getProperty("os.name").indexOf("Mac") != -1)
			Thread.currentThread().setContextClassLoader(
					ClassLoader.getSystemClassLoader());
	}

	/**
	 * A static method to get the default Session factory singleton; may create
	 * a new database if sessionFactory is instantiated by this method call.
	 */
	public static SessionFactory getSessionFactory() {
		if(dataSource==null)
			dataSource=new HibernateDataSource(new ConnectionInfo());
		if (defaultSessionFactory == null) {
			defaultSessionFactory = dataSource.getSessionFactory();
		}
		return defaultSessionFactory;
	}

	public static void setStubSessionFactoryAsDefault() {
		defaultSessionFactory = new StubSessionFactory();
		testingOnly=true;
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
		if(testingOnly)
			return;
			dataSource=new HibernateDataSource(conn); //TODO - when we have multiple respositories we will keep a cache of datasources
		defaultSessionFactory = dataSource.getSessionFactory();
	}

}
