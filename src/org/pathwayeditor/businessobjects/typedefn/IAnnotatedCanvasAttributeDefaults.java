/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * IAnnotatedCanvasAttributeDefaults is an interface defining the default attributes
 * for a {@link ICanvasElementAttribute} that also implements {@link IAnnotatedObject}. This and its subclasses provide default
 * values when a new subtype of <code>ICanvasElementAttribute</code> is constructed.
 * Typically an instance of this interface is associated with an object type.  
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnnotatedCanvasAttributeDefaults {


	/**
	 * Get a new iterator that iterators over all the property definitions in this
	 * instance. 
	 * @return the new iterator of property definitions.
	 */
	Iterator<IPropertyDefinition> propertyDefinitionIterator();
}
