/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;


/**
 * 
 * ILinkEdge is an interface defining a link egde. This is a facade that wraps an
 * instance of @{link ICompoundEdge} and makes manipulation of link more convenient. 
 *
 * @author Stuart Moodie
 *
 */
public interface ILinkEdge extends IDrawingElement {
	
	/**
	 * Gets the {@link ILinkAttribute} relates to this LinkEdge. 
	 * @return the ILinkAttribute. Cannot be null.
	 */
	@Override
	ILinkAttribute getAttribute();
	
	@Override
	ICompoundEdge getGraphElement();

	/**
	 * Gets the source (out node) for the link.
	 * @return the source compound node, which cannot be null.
	 */
	ICompoundNode getSourceShape();

	/**
	 * Gets the target (in node) for the link.
	 * @return the target compound nod, which cannot be null. 
	 */
	ICompoundNode getTargetShape();
	
}
