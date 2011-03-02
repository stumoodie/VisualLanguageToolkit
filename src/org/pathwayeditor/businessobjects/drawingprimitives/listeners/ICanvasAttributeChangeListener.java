/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

/**
 * 
 * ICanvasAttributeChangeListener is an interface that defines a listener for changes to the Canvas attribute.
 *
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeChangeListener {

	/**
	 * Notify that an attribute of the Canvas Attribute has changed.
	 * @param e an instance providing details of the change that was made.
	 */
	void propertyChange(ICanvasAttributePropertyChangeEvent e);

	/**
	 * Notify that the canvas attribute being listened to has been translated. 
	 * @param e the translation event providing details of the translation.
	 */
	void elementTranslated(ICanvasAttributeTranslationEvent e);

	/**
	 * Notify that the canvas attribute being listened to has been resized.
	 * @param e the resize event providing details of the translation.
	 */
	void nodeResized(ICanvasAttributeResizedEvent e);

}
