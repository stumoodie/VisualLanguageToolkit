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
	 * @return
	 */
	IModel getModel();
	
	/**
	 * The time the snapshot was taken.
	 * @return
	 */
	Date getCreationDate();
}
