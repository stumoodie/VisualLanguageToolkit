package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;

public interface ILabel extends IZOrderedObject {

	Location getLocation();
	
	Size getSize();
	
}
