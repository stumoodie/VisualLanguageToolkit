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
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;

/**
 * @author smoodie
 *
 */
public interface INodeChangeEvent {
	/**
	 * Determines what the change to the node's structure. 
	 * @return the change type.
	 */
	ModelStructureChangeType getChangeType();
	
	/**
	 * Returns the item that the change occurred to. If the item
	 * was removed then this will return null 
	 * @return the item that was changed, which will be null if it has been removed.
	 */
	ILinkEdge getChangedItem();
	
	/**
	 * Gets the node whose structure has been changed by this event.
	 * @return the folder whose content was changed, which cannot be null
	 */
	IDrawingNode getChangedModel();

}
