/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;

/**
 * Reports an event that has changed the node structure of a SubModel.
 * @author smoodie
 *
 */
public interface ISubModelNodeChangeEvent {
	/**
	 * Determines what the change to the folder's content was. 
	 * @return the change type.
	 */
	ModelStructureChangeType getChangeType();
	
	/**
	 * Returns the item that the change occurred to. If the item
	 * was removed then this will return null 
	 * @return the repositoty item that was changed, which will be null if it has been removed.
	 */
	IDrawingNode getChangedItem();
	
	/**
	 * Gets the folder whose content has been changed by this event.
	 * @return the folder whose content was changed, which cannot be null
	 */
	ISubModel getChangedModel();

}
