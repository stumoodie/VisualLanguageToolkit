/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public interface ILinkTerminusTranslationEvent {

	Point getOldLocation();

	Point getNewLocation();

	ILinkTerminus getChangedLinkTerminus();

	Point getChangeDelta();

}
