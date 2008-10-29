/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

/**
 * @author smoodie
 *
 */
public class PersistenceManagerNotOpenException extends	PersistenceManagerException {
	private static final long serialVersionUID = 3722144822576327366L;
	private static final String MESSAGE = "Persistence Manager is not open";
	
	/**
	 * @param manager
	 */
	public PersistenceManagerNotOpenException(IPersistenceManager manager) {
		super(manager, MESSAGE);
	}

}
