/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/


package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICurveSegmentContainerListener;

/**
 * IBendPointContainer is an interface defining a container for bend points that is associated with a ILinkAttribute.
 * This allows the manipulation of and access to bend points.
 * Bend points are indexed from 0 to N-1 - similar to an array.
 * 
 * @author Stuart Moodie
 *
 */
public interface ICurveSegmentContainer extends IBendPointChangeListenee  {

	/**
	 * Gets the link attribute that this bend-point container is associated with.
	 * @return the link attriobute which should not be null.
	 */
	ILinkAttribute getLinkAttribute();
	
	/**
	 * Returns an iterator to the curve iterator.
	 * @return the iterator - cannot be null.
	 */
	Iterator<ICurveSegment> curveIterator();

	/**
	 * Get the number of curve segments in this container.
	 * @return the number, which cannot be negative.
	 */
	int numCurveSegments();

	boolean containsCurveSegment(int idx);
	
	ICurveSegment getCurveSegment(int idx);
	
	void addCurveSegmentContainerListener(ICurveSegmentContainerListener l); 
	
	void removeCurveSegmentContainerListener(ICurveSegmentContainerListener l); 
	
	List<ICurveSegmentContainerListener> getCurveSegmentContainerListeners();

	ICurveSegment getFirstCurveSegment(); 
	
	ICurveSegment getLastCurveSegment();

	int getCurveSegmentIndex(ICurveSegment associatedCurveSegment); 
	
}
