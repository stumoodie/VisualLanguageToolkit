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

package org.pathwayeditor.testfixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeResizedEvent;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public class CanvasResizeEventValidator {
	private final ICanvasElementAttribute expectedAttribute;
	private final Point translation;
	private final Dimension resize;


	public CanvasResizeEventValidator(ICanvasElementAttribute testInstance, Point translation, Dimension resize){
		this.expectedAttribute = testInstance;
		this.translation = translation;
		this.resize = resize;
	}
	
	
	public void validateEvent(ICanvasAttributeResizedEvent propChangeEvent){
		assertNotNull("event set", propChangeEvent);
		assertEquals("expected attribute", expectedAttribute, propChangeEvent.getAttibuteChanged());
		assertEquals("expected prop type", translation, propChangeEvent.getOriginChange());
		assertEquals("expected old value", resize, propChangeEvent.getSizeChange());
	}
	
}
