package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

public interface ILabel extends IZOrderedObject, ICanvasObject {

	Location getLocation();
	
	Size getSize();
	
	IAnnotationProperty getProperty();
	
}
