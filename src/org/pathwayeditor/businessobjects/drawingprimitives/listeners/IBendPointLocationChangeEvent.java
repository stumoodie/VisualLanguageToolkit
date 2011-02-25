/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.figure.geometry.Point;


public interface IBendPointLocationChangeEvent {
	
	IBendPointContainer getOwningLink();
	
	int getBendPointIndex();

	Point getOldPosition();

	Point getNewPosition();
}
