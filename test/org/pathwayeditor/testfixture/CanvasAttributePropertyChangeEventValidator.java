/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.testfixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeEvent;

/**
 * @author Stuart Moodie
 *
 */
public class CanvasAttributePropertyChangeEventValidator {
	private final ICanvasElementAttribute expectedAttribute;
	private final CanvasAttributePropertyChange propChange;
	private final Object oldValue;
	private final Object newValue;


	public CanvasAttributePropertyChangeEventValidator(ICanvasElementAttribute testInstance, CanvasAttributePropertyChange propChange, Object oldValue, Object newValue){
		this.expectedAttribute = testInstance;
		this.propChange = propChange;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	
	public void validateEvent(ICanvasAttributePropertyChangeEvent propChangeEvent){
		assertNotNull("event set", propChangeEvent);
		assertEquals("expected attribute", expectedAttribute, propChangeEvent.getAttribute());
		assertEquals("expected prop type", propChange, propChangeEvent.getPropertyChange());
		assertEquals("expected old value", this.oldValue, propChangeEvent.getOldValue());
		assertEquals("expected new value", this.newValue, propChangeEvent.getNewValue());
	}
	
}
