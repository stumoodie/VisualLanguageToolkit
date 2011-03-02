/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;

/**
 * ILabelAttributeFactory is an interface defining a factory for label attributes @{link ILabelAttribute}.
 * The factory requires an annotation property and an object type to create a label attribute.
 * The method {@link #canCreateAttribute()} should return false if either of these is not set.    
 * 
 * @author Stuart Moodie
 *
 */
public interface ILabelAttributeFactory extends ICanvasAttributeFactory {

	/**
	 * The property to associated with this label.
	 * @param annotationProperty the annotation property.
	 */
	void setProperty(IAnnotationProperty annotationProperty);

	/**
	 * The property to be associated with this label.
	 * @return the associated annotation property, this can be null if not set.
	 */
	IAnnotationProperty getProperty();
	
	/**
	 * Sets the object type to be associated with the next label attribute to be created.
	 * @param labelObjectType the object type.
	 */
	void setLabelObjectType(ILabelObjectType labelObjectType);

	/**
	 * Get the current object type associated with the next label.
	 * @return the label object type to be used for the next label attribute.
	 */
	ILabelObjectType getLabelObjectType();
	
	@Override
	ILabelAttribute createAttribute();
	
}
