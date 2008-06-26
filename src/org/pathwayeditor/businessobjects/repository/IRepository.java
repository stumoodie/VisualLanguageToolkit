/**
 * 
 */
package org.pathwayeditor.businessobjects.repository;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface IRepository {

	String getName();
	
	String getDescription();
	
	IRootFolder getRootFolder(); 
	
	Iterator<IRepositoryItem> levelOrderIterator();

	int getBuildNum();
}
