/**
 * 
 */
package org.pathwayeditor.testutils;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

/**
 * @author nhanlon
 *
 */
public class StubSessionFactory implements SessionFactory {

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#close()
	 */
	public void close() throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#evict(java.lang.Class)
	 */
	public void evict(Class persistentClass) throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#evict(java.lang.Class, java.io.Serializable)
	 */
	public void evict(Class persistentClass, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#evictCollection(java.lang.String)
	 */
	public void evictCollection(String roleName) throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#evictCollection(java.lang.String, java.io.Serializable)
	 */
	public void evictCollection(String roleName, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#evictEntity(java.lang.String)
	 */
	public void evictEntity(String entityName) throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#evictEntity(java.lang.String, java.io.Serializable)
	 */
	public void evictEntity(String entityName, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#evictQueries()
	 */
	public void evictQueries() throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#evictQueries(java.lang.String)
	 */
	public void evictQueries(String cacheRegion) throws HibernateException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getAllClassMetadata()
	 */
	public Map getAllClassMetadata() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getAllCollectionMetadata()
	 */
	public Map getAllCollectionMetadata() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getClassMetadata(java.lang.Class)
	 */
	public ClassMetadata getClassMetadata(Class persistentClass)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getClassMetadata(java.lang.String)
	 */
	public ClassMetadata getClassMetadata(String entityName)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getCollectionMetadata(java.lang.String)
	 */
	public CollectionMetadata getCollectionMetadata(String roleName)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getCurrentSession()
	 */
	public Session getCurrentSession() throws HibernateException {
		return new SessionStub();
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getDefinedFilterNames()
	 */
	public Set getDefinedFilterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getFilterDefinition(java.lang.String)
	 */
	public FilterDefinition getFilterDefinition(String filterName)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#getStatistics()
	 */
	public Statistics getStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#isClosed()
	 */
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#openSession()
	 */
	public Session openSession() throws HibernateException {
		return new SessionStub();
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#openSession(java.sql.Connection)
	 */
	public Session openSession(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#openSession(org.hibernate.Interceptor)
	 */
	public Session openSession(Interceptor interceptor)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#openSession(java.sql.Connection, org.hibernate.Interceptor)
	 */
	public Session openSession(Connection connection, Interceptor interceptor) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#openStatelessSession()
	 */
	public StatelessSession openStatelessSession() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.SessionFactory#openStatelessSession(java.sql.Connection)
	 */
	public StatelessSession openStatelessSession(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.naming.Referenceable#getReference()
	 */
	public Reference getReference() throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

}
