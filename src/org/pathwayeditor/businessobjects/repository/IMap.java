package org.pathwayeditor.businessobjects.repository;

/**
 * The map is akin to a "file" in the repository. It holds content, which in this cases is the diagram. The content
 * of the map (the canvas) cannot be obtained directly from the map. This is because one is likely to want to load up the whole repository in
 * one go, but for reasons of memory and speed one would not want all the diagrams to be loaded too. This is not dissimilar to a file system
 * where a special operation is required to access the contents of the file.
 *  
 * @author smoodie
 *
 */

public interface IMap extends IRepositoryItem {
	
	/**
	 * The folder that owns this map.
	 * @return an instance of the folder, cannot be null.
	 */
	IFolder getOwner();

	/**
	 * The name of the map.
	 * @return the map name, which must be a valid and cannot be null or an empty string.
	 */
	String getName();
	
	/**
	 * A description of the map and what it contains.
	 * @return the map description, which can be null.
	 */
	String getDescription();
	
	/**
	 * Sets the map desctiption.
	 * @param description the description to set, which can be null.
	 */
	void setDescription(String description);
}
