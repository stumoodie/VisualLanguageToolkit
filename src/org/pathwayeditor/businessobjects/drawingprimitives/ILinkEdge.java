/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;



public interface ILinkEdge extends IDrawingElement {
	
	/**
	 * Gets the {@link ILinkAttribute} relates to this LinkEdge. 
	 * @return the ILinkAttribute. Cannot be null.
	 */
	@Override
	ILinkAttribute getAttribute();
	
	@Override
	ICompoundEdge getGraphElement();

	ICompoundNode getSourceShape();

	ICompoundNode getTargetShape();
	
}
