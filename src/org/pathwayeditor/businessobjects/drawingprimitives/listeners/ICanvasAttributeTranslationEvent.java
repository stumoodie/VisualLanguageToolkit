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
import org.pathwayeditor.figure.geometry.Point;

/**
 * ICanvasAttributeTranslationEvent is an interface that describes an translation event on a canvas attribute.
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeTranslationEvent {

	/**
	 * Gets the canvas attribute from which this event originated.
	 * @return the canvas attribute.
	 */
	ICanvasElementAttribute getAttibuteChanged();
	
	/**
	 * Gets the change in the position of the canvas attribute's bounding rectangle.
	 * @return the change in position - the translation.
	 */
	Point getTranslationDelta();
	
}
