package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;


public interface IBendPoint {
	ILinkAttribute getOwningLink();
	
	int getCreationSerial();
	
	Location getLocation();
	
	void setLocation ( Location location ) ;
	
	Location getFirstRelativeDimension();
	
	void setFirstRelativeDimension(Location newDimension);

	Location getSecondRelativeDimension();
	
	void setSecondRelativeDimension(Location newDimension);
}
