/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.figure.geometry.Point;


public interface IBendPointChangeEvent {

	BendPointChange getChangeType();
	
	IBendPointContainer getBendPointContainer();
	
	Point getBendPoint();
	
	int getOldIndexPos();

	int getNewIndexPos();
}
