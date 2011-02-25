/*
Copyright 2009-2011, Court of the University of Edinburgh
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
