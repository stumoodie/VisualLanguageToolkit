/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.figure.geometry.LineSegment;

/**
 * IStraightLineCurveSegment
 *
 * @author Stuart Moodie
 *
 */
public interface IStraightLineCurveSegment extends ICurveSegment {

	LineSegment getLineSegement();
	
}
