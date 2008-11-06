/**
 * 
 */
package org.pathwayeditor.businessobjects.repository;

/**
 * @author smoodie
 *
 */
public interface IRepositoryItemChangeListener {

	void propertyChange(IRepositoryPropertyChangeEvent e);
	
	void descendentChange(IFolderContentChangeEvent e);
	
}
