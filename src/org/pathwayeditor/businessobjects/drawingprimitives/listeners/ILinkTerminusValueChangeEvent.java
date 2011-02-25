/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;

/**
 * @author Stuart Moodie
 *
 */
public interface ILinkTerminusValueChangeEvent {

	LinkTerminusChangeType getChangeType();
	
	ILinkTerminus getChangedLinkTerminus();
	
	Object getOldValue();
	
	Object getNewValue();
	
}
