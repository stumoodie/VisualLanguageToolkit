package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;


public interface IBendPoint {
	ILinkAttribute getOwningLink();
	
	int getCreationSerial();
	
	Location getLocation();
	
	void setLocation ( Location location ) ;
	
	Size getFirstRelativeDimension();
	
	void setFirstRelativeDimension(Size newDimension);

	Size getSecondRelativeDimension();
	
	void setSecondRelativeDimension(Size newDimension);
}
