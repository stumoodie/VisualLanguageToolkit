/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment;
import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegmentContainer;

/**
 * ICurveSegmentContainerEvent
 *
 * @author Stuart Moodie
 *
 */
public interface ICurveSegmentContainerEvent {

	ICurveSegmentContainer getCurveSegmentContainer();
	
	List<ICurveSegment> getOriginalSegments();
	
	List<ICurveSegment> getReplacementSegments();
	
}
