/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;

/**
 * ILinkTerminusValueChangeEvent is an interface that defines an attribute value change event in a
 * link terminus. The attribute changed is indicated by the <code>LinkTerminusChangeType</code>. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ILinkTerminusValueChangeEvent {

	/**
	 * Gets the <code>LinkTerminusChangeType</code> indicating the attribute that was changed.
	 * @return the type of the change, whic cannot be null. 
	 */
	LinkTerminusChangeType getChangeType();
	
	/**
	 * Gets the link terminus that originated this event.
	 * @return the originating link terminus.
	 */
	ILinkTerminus getChangedLinkTerminus();
	
	/**
	 * Gets the original value of the attribute. 
	 * @return the original value.
	 */
	Object getOldValue();
	
	/**
	 * Gets the new value of the attribute.
	 * @return the new value.
	 */
	Object getNewValue();
	
}
