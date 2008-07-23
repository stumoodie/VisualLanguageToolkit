package org.pathwayeditor.businessobjects.database.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.testutils.HibernateDbTester;
import org.pathwayeditor.testutils.StubSessionFactory;

/**
 * A Hibernate utility class providing session factory access.
 * 
 * @author Richard Adams
 * 
 */
public class HibernateUtil {

	private static HibernateDbTester dataSource = new HibernateDbTester(
			"test_hibernate.cfg.xml");
	private static SessionFactory defaultSessionFactory;
	private static SessionFactory testSessionFactory;
	private static boolean created;

	// ///////////// THIS IS THE FIX FOR LEOPARD CLASSLOADER BUG PLEASE DONT
	// REMOVE///////////
	static {
		if (System.getProperty("os.name").indexOf("Mac") != -1)
			Thread.currentThread().setContextClassLoader(
					ClassLoader.getSystemClassLoader());
	}

	/**
	 * A static method to get the default Session factory singleton; may create
	 * a new database if sessionFactory is instantiated by this method call. To
	 * change this behaviour modify //TODO - nh
	 */
	public static SessionFactory getSessionFactory() {
		if (defaultSessionFactory == null) {
			defaultSessionFactory = dataSource.getHibernateSessionFactory();
		}
		return defaultSessionFactory;
	}

	/**
	 * A static method to get the test Session factory singleton; also creates a
	 * new database if sessionFactory is instantiated by this method call.
	 */
	// public static SessionFactory getTestSessionFactory() {
	// if (testSessionFactory == null)
	// testSessionFactory = dataSource.getTestSessionfactory();
	// return testSessionFactory;
	// }
	/**
	 * 
	 */
	public static void setSessionFactoryAsDefault() {
		defaultSessionFactory = dataSource.getHibernateSessionFactory();
	}


	/**
	 * For running Unit tests; after the run is over all sessionfactories appear
	 * to revert to null so this set method should be 'safe'.
	 */
	// public static void setTestSessionFactoryAsDefault() {
	// defaultSessionFactory = getTestSessionFactory();
	// }
	public static void setStubSessionFactoryAsDefault() {
		defaultSessionFactory = new StubSessionFactory();
	}

	public static void commitAndCloseSession(Session session) {
		try {
			if (session.isOpen())
				session.getTransaction().commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	public static void commitSession(Session session) {
		try {
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
