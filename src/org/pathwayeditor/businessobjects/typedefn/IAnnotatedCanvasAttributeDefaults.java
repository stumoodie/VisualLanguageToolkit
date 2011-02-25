/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @author Stuart Moodie
 *
 */
public interface IAnnotatedCanvasAttributeDefaults {


	Iterator<IPropertyDefinition> propertyDefinitionIterator();
}
