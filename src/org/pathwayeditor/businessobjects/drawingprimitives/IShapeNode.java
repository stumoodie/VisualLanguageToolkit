/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;



public interface IShapeNode extends ITypedDrawingNode {
	/**
	 * Gets the {@link IShapeAttribute} that is connected to the particular Shape Node.
	 * @return the ShapeAttribute.
	 */
	@Override
	IShapeAttribute getAttribute();

	Iterator<ICompoundEdge> sourceLinkIterator();

	int getNumSourceLinks();

	Iterator<ICompoundEdge> targetLinkIterator();
	
	/**
	 * Gets the number of the links that are targeting this Shape node.
	 * @return the number of links.
	 */
	int getNumTargetLinks();
	
}
