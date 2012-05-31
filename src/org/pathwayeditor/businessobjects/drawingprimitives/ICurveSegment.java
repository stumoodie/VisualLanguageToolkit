/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICurveSegmentChangeListener;
import org.pathwayeditor.figure.geometry.Point;

/**
 * ICurveSegment
 *
 * @author Stuart Moodie
 *
 */
public interface ICurveSegment {

	void visit(ICurveSegmentVisitor visitor);
	
	void addCurveSegmentChangeListener(ICurveSegmentChangeListener l);

	void removeCurveSegmentChangeListener(ICurveSegmentChangeListener l);

	List<ICurveSegmentChangeListener> getCurveSegmentChangeListeners();

	Point getStartPoint();
	
	Point getEndPoint();

	void setEndPoint(Point newLocation);

	/**
	 * @param newLocation
	 */
	void setStartPoint(Point newLocation);

	/**
	 * @param translation
	 */
	void translate(Point translation);
	
}
