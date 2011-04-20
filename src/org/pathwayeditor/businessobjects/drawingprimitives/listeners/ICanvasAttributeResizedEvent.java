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


package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * ICanvasAttributeResizedEvent is an interface that describes an canvas attribute resize event. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeResizedEvent {

	/**
	 * Gets the canvas attribute that this event originated from. 
	 * @return the canvas attribute that initiated this event.
	 */
	ICanvasElementAttribute getAttibuteChanged();

	/**
	 * Gets the change to origin of the bounding rectangle of the canvas attribute that was made. 
	 * @return the change to the origin of the bounding rectangle.
	 */
	Point getOriginChange();
	
	/**
	 * Gets the change to the size of the bounding rectangle of the attribute that was made.
	 * @return the change to the size of the bounding rectangle of the affected attribute.
	 */
	Dimension getSizeChange();
}
