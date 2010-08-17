/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/*******************************************************************************
* Copyright (c) 2003 EMP Project and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Common Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/cpl-v10.html
*
* Contributors:
* EMP Project - initial API and implementation
*******************************************************************************/

package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;

public class ChopBoxAnchorCalculator implements IAnchorLocator {
	private final IConvexHull shape;
	private Point otherEndPoint;
	private Point requestedPosn;
	
    public ChopBoxAnchorCalculator(IConvexHull hull) {
    	this.shape = hull;
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
	public Point calcAnchorPosition() {
		IConvexHull hull = this.shape;
		return hull.getPointLineIntersects(this.otherEndPoint);
	}


	@Override
	public void setOtherEndPoint(Point otherEndPoint) {
		this.otherEndPoint = otherEndPoint;
	}


	public void setRequestedPosition(Point requestedPosn) {
		this.requestedPosn = requestedPosn;
	}


	@Override
	public Point getOtherEndPoint() {
		return this.otherEndPoint;
	}


	public Point getRequestedPosition() {
		return this.requestedPosn;
	}

}