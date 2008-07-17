package org.pathwayeditor.businessobjects.repository;

import java.util.Iterator;

public interface IFolder extends IRepositoryItem {

	/**
	 * Get the number of subfolders held by this folder. 
	 * @return the number of subfolders
	 */
	int numSubFolders();

	/**
	 * Can the name be used for a subfolder in this folder. This tests that the name
	 * follows the naming rules, is not-null and that the name is unique for this folder.  
	 * @param name The name potential subfolder to be tested.
	 * @return True if creating a subfolder of this name will succeed.
	 */
	boolean canUseSubfolderName(String name);
	
	/**
	 * Creates a new subfolder in this folder. Specifically this method creates a new folder
	 * which is added to this folder as a subfolder.
	 * Precondition: <code>canUseSubfolderName(name) == true</code>
	 * @param name The name of the new subfolder.
	 * @return The newly created subfolder. Guaranteed to be non-null.
	 * @throws IllegalArgumentException if <code>canUseSubfolderName(name) == false</code>.
	 */
	ISubFolder createSubfolder(String newSubfolderName);
	
	/**
	 * Tests is the new folder can legally be added as a subfolder of this folder.
	 * It checks if the subfolder's name will be unique and that the subfolder
	 * does not contain this folder as a child (thus creating a cycle). 
	 * @param subFolder The subfolder to be tested. It can be null.
	 * @return true if the folder can successfully be added as a subfolder,
	 * or false if <code>subFolder</code> is null or if any of the validity 
	 * rules are violated. 
	 */
	boolean canMoveSubfolder(ISubFolder subFolder);
	
	/**
	 * Adds the specified folder as a subfolder of this folder.
	 * @param subFolder The subfolder to be added. It cannot be null.
	 * @throws IllegalArgumentException if subFolder is null or
	 * if <code>canAddSubFolder(subFolder)</code> would return false.  
	 */
	void moveSubfolder(ISubFolder subFolder);

	/**
	 * Tests if the subfolder is contained in this subfolder.
	 * @param subFolder the subfolder to test, can be null.
	 * @return true of the subfolder is contained here, false otherwise.
	 */
	boolean containsSubfolder(ISubFolder subFolder);

	
	/**
	 * Create a copy of <code>origSubfolder</code> in this subfolder. This copies the tree of repository
	 * items (subfolders and maps) from this point downwards. The new copies will have new inode values.  
	 * @param origSubfolder the original subfolder to be copied from. 
	 * @return an instance of the new folder that has been created as a result of the copy in this subfolder. 
	 */
	ISubFolder createCopyOfSubfolder(ISubFolder origSubfolder);
	
	/**
	 * Remove the subfolder from here and destroy it and the tree of repository items from this point downwards.
	 * This operation will removed Maps and their contents too.  
	 * @param subFolder the subfolder to be deleted. Cannot be null.
	 * @throws IllegalArgumentException if subFolder is null.
	 */
	void removeSubfolder(ISubFolder subFolder);
	
	/**
	 * Can the <code>subFolder</code> in this folder be renamed to <code>newFolderName</code> ? If no other subfolder
	 * or map is using this name then it can be used. Also <code>newFolderName</code> must be a valid name. The method test
	 * subfolders in this folder so if this is not the parent of <code>subFolder</code> the test will fail.
	 * @param subFolder the subfolder, which must belong to this subfolder and cannot be null. 
	 * @param newFolderName the new name to test.
	 * @return true if <code>subFolder</code> belongs to this folder and changing its name to <code>newFolderName</code>
	 *  is likely to succeed. False otherwise.
	 */
	boolean canRenameSubfolder(ISubFolder subFolder, String newFolderName);
	
	/**
	 * Rename <code>subFolder</code> (which must belong to this folder) to <code>newFolderName</code>.
	 * <p>
	 * Precondition:<p>
	 * <code>canRenameSubfolder(subFolder, newFolderName) == true</code>.  
	 * @param subFolder the subfolder to rename which must have this folder as its parent folder.
	 * @param newFolderName the new name of the subfolder, which cannot be null.
	 * @throws IllegalArgumentException if <code>subFolder</code> is not a child of this folder.
	 * @throws IllegalArgumentException if <code>newFolderName</code> is null.
	 */
	void renameSubfolder(ISubFolder subFolder, String newFolderName);
	
	/**
	 * the number of maps belonging to this folder.
	 * @return the number of maps.
	 */
	int numMaps();

	/**
	 * Provide an iterator that iterates throw the maps belonging to this folder. It cannot be
	 * null, but can be empty.
	 * @return
	 */
	Iterator<? extends IMap> getMapIterator();
	
	/**
	 * Can the name be used for a map in this folder. This tests that the name
	 * follows the naming rules, is not-null and that the name is unique for this folder.  
	 * @param name The name potential map to be tested.
	 * @return True if creating a map of this name will succeed.
	 */
	boolean canUseMapName(String name);
	
	/**
	 * Creates a new map in this folder. Specifically this method creates a new folder
	 * which is added to this folder as a map.
	 * Precondition: <code>canUseMapName(name) == true</code>
	 * @param name The name of the new map.
	 * @return The newly created map. Guaranteed to be non-null.
	 * @throws IllegalArgumentException if <code>canUseMapName(name) == false</code>.
	 */
	IMap createMap(String newMapName);
	
	/**
	 * Creates a new map in this folder that is a copy of another map. The map 
	 * contents are also copied into the new map. The new map is assigned to this
	 * folder. Note that the map to be copied cannot be null or contained by the current
	 * folder as this would result in a name clash. 
	 * @param origMap The map to be copied from.
	 * @return The newly created map.
	 * @throws IllegalArgumentException if the origMap is null or if <code>containsMap(origMap) == true</code>.
	 */
	IMap createCopyOfMap(IMap origMap);
	
	/**
	 * Adds the map to the folder and removes it from it's original location.
	 *  Requires that the folder does not already contain the map. 
	 * @param newMap The new map to add. Cannot be null.
	 * @throws IllegalArgumentException if <code>containsMap(newMap) == true</code> or if
	 * <code>newMap</code> is null.
	 */
	void moveMap(IMap newMap);

	/**
	 * Tests if the map is contained by this folder.
	 * @param newMap The map to test, which can be null.
	 * @return True if the map is contained in this folder, false otherwise.
	 */
	boolean containsMap(IMap newMap);

	/**
	 * Tests of a map can be renamed to the new name. This requires the new name
	 * to be a legal name for a map and that it is not already contained in the
	 * folder.
	 * @param map The map to be renamed. It can be null.
	 * @param newMapName The new name for the map. It can be null.
	 * @return true if the map can be renamed using the new name, false otherwise.
	 */
	boolean canRenameMap(IMap map, String newMapName);

	/**
	 * Renames a map to the new name within the folder.
	 * @param map The map to be renamed.
	 * @param newMapName The new name for the map.
	 * @throws IllegalArgumentException if <code>canRenameMap(map, newMapName) == false</code>.
	 */
	void renameMap(IMap map, String newMapName);
}
