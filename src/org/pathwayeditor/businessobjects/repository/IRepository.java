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
	
	IRootFolder getRootFolder(); 
	
	Iterator<IRepositoryItem> levelOrderIterator();

}
