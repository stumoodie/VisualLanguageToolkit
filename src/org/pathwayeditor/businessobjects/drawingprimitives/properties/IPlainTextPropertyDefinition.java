/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * IPlainTextPropertyDefinition is an interface that defines a String property.
 * 
 * @author Stuart Moodie
 *
 */
public interface IPlainTextPropertyDefinition extends IPropertyDefinition {

	@Override
	String getDefaultValue();
	
}
