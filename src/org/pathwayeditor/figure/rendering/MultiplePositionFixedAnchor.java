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

package org.pathwayeditor.figure.rendering;

import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.geometry.PointList;

/**
 * 
 * MultiplePositionFixedAnchor
 *
 * @author Stuart Moodie
 *
 */
public class MultiplePositionFixedAnchor implements IAnchorLocator {
	private final PointList refAnchorPoints;
	private final IConvexHull shape;
	private Point otherEndPoint = null;
	
	
	public MultiplePositionFixedAnchor(PointList refAnchorPoints, IConvexHull hull){
		this.refAnchorPoints = refAnchorPoints;
		this.shape = hull;
	}
	
	@Override
	public Point calcAnchorPosition(){
		Point retVal = this.refAnchorPoints.nearestPointTo(this.otherEndPoint);
		return retVal;
	}

	@Override
	public boolean canCalcAnchorPosition() {
		return this.otherEndPoint != null;
	}

	@Override
	public IConvexHull getOwningShapeHull() {
		return this.shape;
	}

	@Override
	public void setOtherEndPoint(Point otherEndPoint) {
		this.otherEndPoint  = otherEndPoint;
	}

	@Override
	public Point getOtherEndPoint() {
		return this.otherEndPoint;
	}

}
