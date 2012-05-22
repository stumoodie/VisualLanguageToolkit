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

public class ChopBoxAnchorCalculator implements IAnchorLocator {
	private final IConvexHull shape;
	private Point otherEndPoint;
	private Point requestedPoint;
	
    public ChopBoxAnchorCalculator(IConvexHull hull) {
    	this.shape = hull;
    }

	@Override
	public boolean canCalcAnchorPosition() {
		return this.otherEndPoint != null;
	}



	@Override
	public Point calcAnchorPosition() {
		IConvexHull hull = this.shape;
		return hull.getPointLineIntersects(this.otherEndPoint);
	}


	@Override
	public void setOtherEndPoint(Point otherEndPoint) {
		this.otherEndPoint = otherEndPoint;
	}


	@Override
	public Point getOtherEndPoint() {
		return this.otherEndPoint;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IAnchorLocator#setRequestedPoint(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void setRequestedPoint(Point requestedPoint) {
		this.requestedPoint = requestedPoint;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IAnchorLocator#getRequestedPoint()
	 */
	@Override
	public Point getRequestedPoint() {
		return this.requestedPoint;
	}
}