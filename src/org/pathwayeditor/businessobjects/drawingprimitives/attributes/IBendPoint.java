package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;


public interface IBendPoint {
	ILinkAttribute getOwningLink();
	
	Location getLocation();
}
