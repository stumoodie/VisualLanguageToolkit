/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * Reports an event that has changed the node structure of a Model.
 * @author smoodie
 *
 */
public interface IModelEdgeChangeEvent {
	/**
	 * Determines what the change to the model's content was. 
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
	 * Gets the folder whose content has been changed by this event.
	 * @return the folder whose content was changed, which cannot be null
	 */
	IModel getChangedModel();

}
