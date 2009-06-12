/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author smoodie
 *
 */
public interface IFolderPersistenceManager extends IPersistenceManager {

	IFolder getFolder();
	
	/**
	 * Get the number of subfolders held by this folder. 
	 * @return the number of subfolders
	 */
	int numSubFolders();

	/**
	 * Can the name be used for a subfolder in this folder. This tests that the name
	 * follows the naming rules, is not-null and that the name is unique for this folder (NOT case sensitive).  
	 * @param name The name potential subfolder to be tested.
	 * @return True if creating a subfolder of this name will succeed.
	 */
	boolean canUseSubfolderName(String name);	
	
	/**
	 * Creates a new subfolder in this folder. Specifically this method creates a new folder
	 * which is added to this folder as a subfolder.
	 * Precondition: <code>canUseSubfolderName(name) == true</code>
	 * @param newSubfolderName The name of the new subfolder.
	 * @return The newly created subfolder. Guaranteed to be non-null.
	 * @throws IllegalArgumentException if <code>canUseSubfolderName(name) == false</code>.
	 * @throws IllegalArgumentException if name is null.
	 */
	ISubFolder createSubfolder(String newSubfolderName);
	
	/**
	 * Tests if the subfolder is contained in this folder.
	 * @param subFolder the subfolder to test, can be null.
	 * @return true of the subfolder is contained here, false otherwise.
	 */
	boolean containsSubfolder(ISubFolder subFolder);
	
	/**
	 * Tests if a subfolder with this name is contained in this folder.
	 * @param subFolderName the subfolder name to test, can be null.
	 * @return true if a subfolder with this name is contained in this folder, false otherwise.
	 */
	boolean containsSubfolder(String subFolderName);
	
	/**
	 * Tests if the <code>subfolder</code> is a descendent of this folder.     
	 * @param subFolder the subfolder to test, which can be null.
	 * @return true if this <code>subFolder</code> is descendent, false otherwise.
	 */
	public boolean isDescendent(ISubFolder subFolder);
	
	/**
	 * Tests is the new folder can legally be added as a subfolder of this folder.
	 * It checks if the subfolder's name will be unique and that the subfolder
	 * does not contain this folder as a child (thus creating a cycle).
	 * It must also be in the same repository.
	 * @param subFolder The subfolder to be tested. It can be null.
	 * @return true if the folder can successfully be added as a subfolder,
	 * or false if <code>subFolder</code> is null or if any of the validity 
	 * rules are violated. 
	 */
	boolean canMoveSubfolder(ISubFolder subFolder);
	
	/**
	 * Adds a copy of the specified subfolder to this parent and orphans the original subfolder.
	 * copy.equals(original) returns true.
	 *  (Also adds any contents - child folders and maps.
	 * Also removes this folder from its previous parent.
	 * More specifically, a copy of the original folder and its contents is added to the new parent. The copy will return true for equals()
	 * to the original.
	 * @param subFolder The subfolder to be copied and its copy added. It cannot be null.
	 * @return the copy subfolder of the original.
	 * @throws IllegalArgumentException if subFolder is null or
	 * if <code>canMoveSubFolder(subFolder)</code> would return false.  
	 */
	ISubFolder moveSubfolder(ISubFolder subFolder);

	/**
	 * Tests is the copied folder can legally be added as a subfolder of this folder.
	 * It checks if the subfolder's name will be unique and that the subfolder
	 * does not contain this folder as a child (thus creating a cycle). 
	 * @param subFolder The subfolder to be tested. It can be null.
	 * @return true if the copied folder can successfully be added as a subfolder,
	 * or false if <code>subFolder</code> is null or if any of the validity 
	 * rules are violated. 
	 */
	boolean canCopySubfolder(ISubFolder subFolder);

	/**
	 * Create a copy of <code>origSubfolder</code> in this subfolder. This copies the tree of repository
	 * items (subfolders and maps) from this point downwards. The new copies will have new inode values.  
	 * @param origSubfolder the original subfolder to be copied from. 
	 * @return an instance of the new folder that has been created as a result of the copy in this subfolder.
	 * @throws IllegalArgumentException if <code>canCopySubfolder() == false</code>. 
	 */
	ISubFolder createCopyOfSubfolder(ISubFolder origSubfolder);
	
	/**
	 * Remove the subfolder from this folder.
	 * This operation will also remove all descendent Maps and subfolders that belong to this subfolder.  
	 * @param subFolder the subfolder to be deleted. Cannot be null.
	 * @throws IllegalArgumentException if subFolder is null.
	 * @throws IllegalArgumentException if subFolder is not contained in this folder
	 */
	void removeSubfolder(ISubFolder subFolder);
	
	/**
	 * Remove a map in this folder, deleting it
	 * @param map to be removed
	 * @throws IllegalArgumentException if map is null.
	 * @throws IllegalArgumentException if map is not in this folder
	 */
	void removeMap(IMap map);
	
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
	 * @throws IllegalArgumentException if <code>canRename</code> returns false.
	 */
	void renameSubfolder(ISubFolder subFolder, String newFolderName);
	
	/**
	 * the number of maps belonging to this folder.
	 * @return the number of maps.
	 */
	int getNumMaps();

	/**
	 * Provide an iterator that iterates throw the maps belonging to this folder. It cannot be
	 * null, but can be empty.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<IMap> getMapIterator();
	
	/**
	 * Provide an iterator that iterates through the immediate children of this folder. It cannot be
	 * null, but can be empty.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ISubFolder> getSubFolderIterator();
	
	int getNumSubFolders();
	
	/**
	 * Can the name be used for a map in this folder. This tests that the name
	 * follows the naming rules, is not-null and that the name is unique for this folder (NOT case sensitive). 
	 * @param name The name potential map to be tested.
	 * @return True if creating a map of this name will succeed.
	 */
	boolean canUseMapName(String name);
	
	/**
	 * Creates a new map in this folder. Specifically this method creates a new map
	 * which is added to this folder as a map.
	 * Precondition: <code>canUseMapName(name) == true</code>
	 * @param newMapName The name of the new map.
	 * @return The newly created map. Guaranteed to be non-null.
	 * @throws IllegalArgumentException if <code>canUseMapName(name) == false</code>.
	 */
	IMap createMap(String newMapName);
	
	/**
	 * Tests is the copied map can be added to this folder.
	 * It checks if the map's name will be unique and that this folder
	 * does not contain this map already. 
	 * @param origMap The map to be tested. It can be null.
	 * @return true if the copied map can successfully be copied and added to this folder,
	 * or false otherwise. 
	 */
	boolean canCopyMap(IMap origMap);

	/**
	 * Creates a new map in this folder that is a copy of another map. The map 
	 * contents are also copied into the new map. The new map is assigned to this
	 * folder. Note that the map to be copied cannot be null or contained by the current
	 * folder as this would result in a name clash. 
	 * @param origMap The map to be copied from.
	 * @return The newly created map.
	 * @throws IllegalArgumentException if <code>canCopyMap(origMap) == false</code>.
	 */
	IMap createCopyOfMap(IMap origMap);
	
	/**
	 * Tests is the map can moved to this folder.
	 * It checks if the map's name will be unique and that the folder
	 * does not contain this map already. 
	 * It must also be in the same repository.
	 * @param origMap The map to be tested. It can be null.
	 * @return true if the map can successfully be moved to this folder,
	 * or false otherwise. 
	 */
	boolean canMoveMap(IMap origMap);
	
	/**
	 * Adds a copy of the map to the new folder and orphans the original map.
	 * copy.equals(original) returns true.
	 *  Requires that the folder does not already contain the map. 
	 * @param newMap The new map to add. Cannot be null.
	 * @return the copy of the original map 
	 * @throws IllegalArgumentException if <code>containsMap(newMap) == true</code> or if
	 * <code>newMap</code> is null.
	 */
	IMap moveMap(IMap newMap);

	/**
	 * Tests if the map is contained by this folder.
	 * @param newMap The map to test, which can be null.
	 * @return True if the map is contained in this folder, false otherwise.
	 */
	boolean containsMap(IMap newMap);

	/**
	 * Tests if a map with this name is contained by this folder.
	 * @param newMapName The map name to test, which can be null.
	 * @return True if a map with this name is contained in this folder, false otherwise.
	 */
	boolean containsMap(String newMapName);

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
