/**
 * 
 */
package org.pathwayeditor.businessobjects.database.util;

import org.hibernate.SessionFactory;

/**
 * @author nhanlon
 *
 */
public interface IHibernateDatabaseSourceProvider {
	
	public SessionFactory getHibernateSessionFactory();

}
