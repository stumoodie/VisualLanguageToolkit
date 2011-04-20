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

/**
 * 
 * ICanvasAttributePropertyChangeEvent is an interface that provides a record of a change to an 
 * attribute of a canvas attribute. The nature of the change is indicated by <code>CanvasAttributePropertyChange</code>.
 *
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributePropertyChangeEvent {

	/**
	 * Gets the canvas attribute being listened to.
	 * @return the canvas attribute being listened to.
	 */
	ICanvasElementAttribute getAttribute();
	
	/**
	 * Gets the old property value. 
	 * @return the old property value, which can be null if the old value was null.
	 */
	Object getOldValue();
	
	/**
	 * Gets the new property value. 
	 * @return the new property value, which can be null if the new value was null.
	 */
	Object getNewValue();
	
	/**
	 * Get the type of the property.
	 * @return the type of the property, which cannot be null.
	 */
	CanvasAttributePropertyChange getPropertyChange();
	
}
