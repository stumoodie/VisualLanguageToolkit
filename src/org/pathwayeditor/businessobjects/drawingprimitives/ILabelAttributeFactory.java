/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;

/**
 * @author Stuart Moodie
 *
 */
public interface ILabelAttributeFactory extends ICanvasAttributeFactory {

	void setProperty(IAnnotationProperty annotationProperty);

	IAnnotationProperty getProperty();
	
	void setLabelObjectType(ILabelObjectType labelObjectType);
	
	ILabelObjectType getLabelObjectType();
	
	@Override
	ILabelAttribute createAttribute();
	
}
