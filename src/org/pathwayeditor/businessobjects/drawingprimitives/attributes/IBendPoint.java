package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

import org.pathwayeditor.businessobjects.drawingprimitives.ILink;

public interface IBendPoint {
	ILink getOwningLink();
	
	Location getLocation();
}
