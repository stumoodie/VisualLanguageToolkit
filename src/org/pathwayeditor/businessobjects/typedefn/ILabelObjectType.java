/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.typedefn;

/**
 * @author Stuart Moodie
 *
 */
public interface ILabelObjectType extends INodeObjectType {

	ILabelAttributeDefaults getDefaultAttributes();

	/**
	 * Is the property always shown or displayed.
	 * @return true if it is always displayed, false otherwise.
	 */
	boolean isAlwaysDisplayed();
	
	@Override
	ILabelObjectTypeParentingRules getParentingRules();
}
