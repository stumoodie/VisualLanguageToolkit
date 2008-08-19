package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

public interface ILabelAttribute extends IZOrderedObject, ICanvasAttribute {

	Location getLocation();
	void setLocation ( Location location) ;
	
	Size getSize();
	void setSize ( Size size ) ;
	
	IAnnotationProperty getProperty();

	ILabelNode getLabelNode();
	
	RGB getBackgroundColor () ;
	void setBackgroundColor ( RGB color) ;
	
	boolean isDisplayed () ;
	void setDisplayed (boolean displayed) ;
	
	boolean isFillSet () ;
	void setFillSet ( boolean fillSet) ;
}
