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

/**
 * IFigureChangeEvent is an interface that defines an attribute change in a
 * figure controller.
 * 
 * @author Stuart Moodie
 *
 */
public interface IFigureRenderingControllerAttributeChangeEvent {

	/**
	 * The figure controller from which this event originated.
	 * @return the figure controller, which cannot be null.
	 */
	IFigureRenderingController getFigureController();
	
	/**
	 * The attribute change type.
	 * @return the type of attribute change.
	 */
	FigureChangeType getType();
	
	/**
	 * The original value of the attribute.
	 * @return the original attribute value.
	 */
	Object getOldValue();
	
	/**
	 * The new value of the attribute.
	 * @return the new attribute value.
	 */
	Object getNewValue();
	
}
