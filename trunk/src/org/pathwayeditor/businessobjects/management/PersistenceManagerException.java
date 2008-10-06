/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

/**
 * @author smoodie
 *
 */
public abstract class PersistenceManagerException extends Exception {
	private static final long serialVersionUID = 7266748962157942176L;

	private final IPersistenceManager manager; 
	
	protected PersistenceManagerException(IPersistenceManager manager, String message) {
		super(message);
		this.manager = manager;
	}

	/**
	 * Returns the persistence manager that threw the exception
	 * @return the persistence manager
	 */
	public IPersistenceManager getManager() {
		return this.manager;
	}
}
