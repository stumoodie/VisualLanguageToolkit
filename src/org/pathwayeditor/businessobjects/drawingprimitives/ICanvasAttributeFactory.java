/*
Copyright 2009-2011, Court of the University of Edinburgh

*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * ICanvasAttributeFactory is an interface that defines a factory for the creation of an ICanvasAttribute.
 * 
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeFactory extends IElementAttributeFactory {

	/**
	 * Sets the creation serial to use when creating the attribute. This value is ignored if this value is not set.
	 * @param creationSerial
	 */
	void setPreferredCreationSerial(Integer creationSerial);
	
	/**
	 * The value currently set as the preferred creation serial. 
	 * @return the value of the currently preferred creation serial, which may be null.
	 */
	Integer getPreferredCreationSerial();
	
}
