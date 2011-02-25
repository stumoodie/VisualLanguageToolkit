/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.Iterator;
import java.util.List;

/**
 * @author Stuart Moodie
 *
 */
public interface IListAnnotationProperty extends IAnnotationProperty{
	
	/**
	 * Get the definition associated with this NumberProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	@Override
	IListPropertyDefinition getDefinition();
	
	/**
	 * Get the numerical value associated with this property.
	 * @return The property definition instance. Cannot be null.
	 */
	@Override
	List<String> getValue();
	
	/**
	 * Get the numerical value associated with this property.
	 */
	void addValue(String newValue);
	
	Iterator<String> getValueIterator();
}
