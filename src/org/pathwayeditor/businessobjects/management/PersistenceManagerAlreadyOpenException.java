/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

/**
 * @author smoodie
 *
 */
public class PersistenceManagerAlreadyOpenException extends	PersistenceManagerException {
	private static final long serialVersionUID = -6802593211438960060L;
	private static final String MESSAGE = "Persistence Manager is already open";
	
	/**
	 * @param manager
	 */
	public PersistenceManagerAlreadyOpenException(IPersistenceManager manager) {
		super(manager, MESSAGE);
	}

}
