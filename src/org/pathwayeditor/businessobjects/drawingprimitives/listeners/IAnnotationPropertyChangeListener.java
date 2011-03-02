/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

/**
 * IAnnotationPropertyChangeListener is an interface defining a listener for
 * annotation property change events.
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnnotationPropertyChangeListener {

	/**
	 * Notify the listener that a property change event has occurred.
	 * @param e an instance containing details of the event. 
	 */
	void propertyChange(IAnnotationPropertyChangeEvent e);

}
