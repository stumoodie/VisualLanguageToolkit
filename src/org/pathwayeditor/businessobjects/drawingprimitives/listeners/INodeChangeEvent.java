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
