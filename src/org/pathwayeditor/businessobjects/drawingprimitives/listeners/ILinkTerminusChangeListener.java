/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;


/**
 * ILinkTerminusChangeListener is an interface that defines a listener of link terminus value
 * changes. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ILinkTerminusChangeListener {
	
	/**
	 * Notifies that a value in the listened to object has changed.
	 * @param e the event instance that describes the change. 
	 */
	void valueChangeEvent(ILinkTerminusValueChangeEvent e);

}
