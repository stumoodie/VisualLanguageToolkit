/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

/**
 * ICurveSegmentVisitor
 *
 * @author Stuart Moodie
 *
 */
public interface ICurveSegmentVisitor {

	void visitStraightLineCurveSegment(IStraightLineCurveSegment v);
	
}
