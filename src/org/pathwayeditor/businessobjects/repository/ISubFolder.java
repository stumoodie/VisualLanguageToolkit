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
