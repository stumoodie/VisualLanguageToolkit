/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.figure.geometry.Dimension;


/**
 * Define the link end appearance and default properties. The link end has a decorator at the end of
 *  the link which is typically an arrowhead or some kind and a decorator at the terminus of the link
 *  which can be any of the shape primitives. The offset defines the gap between both of these. The line
 *  drawn for the link finished at the link end decorator (hence the name).
 *    
 * @author Stuart Moodie
 *
 */
public interface ILinkTerminusDefaults {
	/**
	 * Get the offset value. The default is zero.
	 * @return the gap size, which cannot be negative: <code>getGap() >= 0</code>.
	 */
	double getGap();
	
	/**
	 * Get the arrowhead style associated with the decorator.
	 * @return A non-null instance of an arrowhead type.
	 */
	LinkEndDecoratorShape getEndDecoratorType();

	/**
	 * Get the width of the decorator (arrowhead typically). 
	 * @return a non-negative integer value.
	 */
	Dimension getEndSize();
}
