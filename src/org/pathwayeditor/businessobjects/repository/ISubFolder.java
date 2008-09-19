/**
 * 
 */
package org.pathwayeditor.businessobjects.repository;

/**
 * A subfolder has a parent folder and can contain repsoitory items. It has a name that must be unique among the maps and 
 * subfolders within the parent folder.
 * 
 * @author smoodie
 *
 */
public interface ISubFolder extends IFolder {

	/**
	 * Get the name of the subfolder.
	 * @return name of the subfolder, which cannot be null and must be a valid subfolder name.
	 */
	String getName();
	
	/**
	 * Get the parent folder of this subfolder.
	 * @return the parent folder, which cannot be null.
	 */
	IFolder getParent();
	
	void setDescription(String description);
	
	String getDescription();
	
}
