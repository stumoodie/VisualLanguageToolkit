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
 * IListAnnotationProperty is an interface the defines an annotation property containing a list of values.
 * 
 * @author Stuart Moodie
 *
 */
public interface IListAnnotationProperty extends IAnnotationProperty{
	
	/**
	 * Get the definition associated with this property.
	 * @return The property definition instance. Cannot be null.
	 */
	@Override
	IListPropertyDefinition getDefinition();
	
	/**
	 * Get the value associated with this property.
	 * @return a list of values, which cannot be null, but the list may be empty. The list should be a copy or immutable.
	 */
	@Override
	List<String> getValue();

	/**
	 * Adds a new value to the annotation list.
	 * @param newValue the new value to add to the property list.
	 */
	void addValue(String newValue);
	
	/**
	 * Gets a new iterator to all the values in this list
	 * @return a new iterator of list annotation property values.
	 */
	Iterator<String> getValueIterator();
}
