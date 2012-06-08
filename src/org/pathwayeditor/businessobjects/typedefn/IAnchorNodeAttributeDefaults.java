/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.figure.geometry.Dimension;

/**
 * IAnchorNodeAttributeDefaults
 *
 * @author Stuart Moodie
 *
 */
public interface IAnchorNodeAttributeDefaults extends IDrawingNodeAttributeDefaults {

	/**
	 * Gets the initial shape definition.
	 * @return the initial shape defintion, which should not be null.
	 */
	String getShapeDefinition();

	/**
	 * Get the initial size of the anchor node.
	 * @return the node's initial size.
	 */
	Dimension getSize();
}
