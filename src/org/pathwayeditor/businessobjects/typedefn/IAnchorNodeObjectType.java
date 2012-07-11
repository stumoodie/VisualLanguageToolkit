/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.typedefn;


/**
 * IAnchorNodeObjectType
 *
 * @author Stuart Moodie
 *
 */
public interface IAnchorNodeObjectType extends INodeObjectType {

	/**
	 * Gets the default attributes for this object type.
	 * @return the default attributes, which cannot be null.
	 */
	IAnchorNodeAttributeDefaults getDefaultAttributes();
	
}
