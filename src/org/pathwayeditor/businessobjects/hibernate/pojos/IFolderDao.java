/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.repository.IFolder;

/**
 * @author nhanlon
 *
 */
 interface IFolderDao {
	
	/**
	 * @param toDelete a folder to delete from the database
	 */
	public void  deleteFolderTree(IFolder toDelete);

}
