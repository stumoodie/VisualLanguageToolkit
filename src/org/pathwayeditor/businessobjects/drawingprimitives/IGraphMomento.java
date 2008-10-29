/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Date;

/**
 * Stores the state of the canvas ready for it to be retrieved. 
 * @author smoodie
 *
 */
public interface IGraphMomento {

	/**
	 * The model that this is a momento of.
	 * @return the model, which cannot be null.
	 */
	IModel getModel();
	
	/**
	 * The time the snapshot was taken.
	 * @return the creation date which cannot be null.
	 */
	Date getCreationDate();
}
