/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.testfixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeTranslationEvent;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public class CanvasTranslationEventValidator {
	private final ICanvasElementAttribute expectedAttribute;
	private final Point translation;


	public CanvasTranslationEventValidator(ICanvasElementAttribute testInstance, Point translation){
		this.expectedAttribute = testInstance;
		this.translation = translation;
	}
	
	
	public void validateEvent(ICanvasAttributeTranslationEvent propChangeEvent){
		assertNotNull("event set", propChangeEvent);
		assertEquals("expected attribute", expectedAttribute, propChangeEvent.getAttibuteChanged());
		assertEquals("expected prop type", translation, propChangeEvent.getTranslationDelta());
	}
	
}
