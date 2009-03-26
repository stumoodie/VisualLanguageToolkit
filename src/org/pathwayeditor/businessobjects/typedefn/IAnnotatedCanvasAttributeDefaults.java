/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @author smoodie
 *
 */
public interface IAnnotatedCanvasAttributeDefaults {


	Iterator<IPropertyDefinition> propertyDefinitionIterator();
}
