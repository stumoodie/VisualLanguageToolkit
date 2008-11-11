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
	
	/**
	 * A number that uniquely identifies this momento within the model. This should be unique for every momento
	 * created by the model.
	 * @return the version number, which must be an integer greater than zero.
	 */
	int getVersionNum();
	
	/**
	 * Equal if the model and the version number are the same.
	 * @param other the other object to compare.
	 * @return true is this momento and the other momento both have equal models and the same version num.
	 */
	boolean equals(Object other);
}
