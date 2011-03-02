/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.List;

/**
 * IListPropertyDefinition is an interface that defines a list property.
 * 
 * @author Stuart Moodie
 *
 */
public interface IListPropertyDefinition extends IPropertyDefinition {

	@Override
	List<String> getDefaultValue();
	
}
