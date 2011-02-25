/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public interface IBendPointTranslationEvent {

	int numPoints();

	Iterator<Point> newPointIterator();

	Iterator<Point> oldPointIterator();

	IBendPointContainer getBendPointContainer();

	Point getDelta();

}
