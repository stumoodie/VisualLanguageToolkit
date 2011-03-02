/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

/**
 * ITypedDrawingNode is an interface that defines a node that has a type associated
 * with it.
 * 
 * @author Stuart Moodie
 *
 */
public interface ITypedDrawingNode extends IDrawingNode {

	@Override
	ITypedDrawingNodeAttribute getAttribute();
	
}
