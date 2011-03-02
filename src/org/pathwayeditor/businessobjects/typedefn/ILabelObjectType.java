/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.typedefn;

/**
 * ILabelObjectType is an interface defining an object type for a 
 * label. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ILabelObjectType extends INodeObjectType {

	/**
	 * Get the default/initial values to be used when creating a <code>ILabelAttribute</code> with this object type. 
	 * @return the default values for this object type.
	 */
	ILabelAttributeDefaults getDefaultAttributes();

	/**
	 * Is the property always shown or displayed.
	 * @return true if it is always displayed, false otherwise.
	 */
	boolean isAlwaysDisplayed();
	
	@Override
	ILabelObjectTypeParentingRules getParentingRules();
}
