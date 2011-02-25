/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author Stuart Moodie
 *
 */
public interface IDrawingNodeAttribute extends ICanvasElementAttribute {

	Envelope getBounds();
	
	void setBounds(Envelope newBounds);
	
	void resize(Point locationDelta, Dimension sizeDelta);
	
	
	@Override
	ICompoundNode getCurrentElement();
}
