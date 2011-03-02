/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

/**
 * LinkTerminusChangeType is an enumerated type containing the attributes of a link terminus
 * that may be changed and notified as an event.
 * 
 * @author Stuart Moodie
 *
 */
public enum LinkTerminusChangeType {
	/**
	 * The location of the terminus - the anchor position. 
	 */
	LOCATION,
	/**
	 * The type of the end decorator.
	 */
	END_DECORATOR_TYPE,
	/**
	 * The size of the end decorator.
	 */
	END_DECORATOR_SIZE,
	/**
	 * The gap between the end of the link (the anchor point) and the end of the dran link. 
	 */
	TERMINUS_GAP
}
