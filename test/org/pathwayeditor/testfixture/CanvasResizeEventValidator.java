/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.testfixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeResizedEvent;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
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
