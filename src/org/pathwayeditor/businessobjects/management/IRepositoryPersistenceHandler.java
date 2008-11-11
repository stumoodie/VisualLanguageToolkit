/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public interface IRepositoryPersistenceHandler {

//	void setName(String name);

	String getName();

	void loadRepository();
	
	void synchroniseRepository();

	IRepository getLoadedRepository();

	/**
	 * Sets the hibernate notation factory, the owning map and loaded canvas to null. This helps avoid memory leaks
	 * since this object will not hold on to state object references and puts the object in a pristine state as if it
	 * were just initialised.
	 */
	void reset();
}
