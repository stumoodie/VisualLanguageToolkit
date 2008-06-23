package org.pathwayeditor.businessobjects.typedefn;

import java.util.Iterator;
import java.util.Set;

/**
 * Provides methods that filter out properties based on different criteria.
 * Useful for clients of object types, to enable them to get the appropriate
 * properties for the particular circumstance; 
 * @author smoodie
 *
 */
public interface IPropertyDefinitionFilter {

	/**
	 * Get the object type that this filter is acting on.
	 * @return Returns the object type. Guaranteed to be non-null.
	 */
	IObjectType getObjectType();
	
	/**
	 * Get all properties available for this object type.
	 * @return a list of property types, which can be modified or an empty list of there are no properties.
	 */
	Set<IPropertyDefinition> getAllProperties();

	/**
	 * Returns an iterator to the list of all properties. This should be the most efficient
	 * way of accessing properties of the object type.
	 * @return returns an iterator to a collection that contains no duplicate property types.
	 *  Guaranteed to be non-null.
	 */
	Iterator<IPropertyDefinition> getAllPropertiesIterator();
	
	/**
	 * Get the properties that are text properties.
	 * @return a list of property types, which can be modified or an empty list of there are no properties.
	 */
	Set<IPropertyDefinition> getTextProperties();

	/**
	 * Get the properties that are formatted text properties.
	 * @return a list of property types, which can be modified or an empty list of there are no properties.
	 */
	Set<IPropertyDefinition> getFormattedTextProperties();

	/**
	 * Get the properties that are number properties.
	 * @return a list of property types, which can be modified or an empty list of there are no properties.
	 */
	Set<IPropertyDefinition> getNumberProperties();

	/**
	 * Get the properties that are visualisable
	 * @return a list of property types, which can be modified or an empty list of there are no properties.
	 */
	Set<IPropertyDefinition> getVisualisableProperties();
	
	/**
	 * Get the properties that are editable 
	 * @return a list of property types, which can be modified or an empty list of there are no properties.
	 */
	Set<IPropertyDefinition> getEditableProperties();
}
