/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;

/**
 * @author smoodie
 *
 */
public interface IDrawingNodeAttribute extends ICanvasAttribute {

	Location getLocation();
	
	void setLocation(Location newLocation);
	
	Size getSize();
	
	void setSize(Size newSize);
}
