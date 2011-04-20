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


package org.pathwayeditor.testfixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasPropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeEvent;

/**
 * @author Stuart Moodie
 *
 */
public class CanvasOnlyPropertyChangeEventValidator {
	private final ICanvas expectedCanvas;
	private final CanvasPropertyChange propChange;
	private final Object oldValue;
	private final Object newValue;


	public CanvasOnlyPropertyChangeEventValidator(ICanvas testInstance, CanvasPropertyChange propChange, Object oldValue, Object newValue){
		this.expectedCanvas = testInstance;
		this.propChange = propChange;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	
	public void validateEvent(ICanvasPropertyChangeEvent propChangeEvent){
		assertNotNull("event set", propChangeEvent);
		assertEquals("expected attribute", expectedCanvas, propChangeEvent.getCanvas());
		assertEquals("expected prop type", propChange, propChangeEvent.getPropertyChange());
		assertEquals("expected old value", this.oldValue, propChangeEvent.getOldValue());
		assertEquals("expected new value", this.newValue, propChangeEvent.getNewValue());
	}
	
}
