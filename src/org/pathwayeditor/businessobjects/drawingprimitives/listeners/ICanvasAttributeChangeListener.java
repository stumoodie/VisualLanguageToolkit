/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;


public interface ICanvasAttributeChangeListener {

	void propertyChange(ICanvasAttributePropertyChangeEvent e);

	void elementTranslated(ICanvasAttributeTranslationEvent e);

	void nodeResized(ICanvasAttributeResizedEvent e);

}
