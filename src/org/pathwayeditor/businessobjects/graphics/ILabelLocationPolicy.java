package org.pathwayeditor.businessobjects.graphics;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;

public interface ILabelLocationPolicy {

	ICanvasAttribute getOwner();
	
	Location nextLabelLocation();
	
}
