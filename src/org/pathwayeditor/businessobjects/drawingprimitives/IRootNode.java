/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.IRootCompoundNode;


/**
 * IRootNode is an interface that defines the root node of the model. It is a facade
 * wrapping the {@link IRootCompoundNode} to make manipulation of the node and its attributes easier.
 * 
 * @author Stuart Moodie
 *
 */
public interface IRootNode extends ITypedDrawingNode {
	
	@Override
	IRootAttribute getAttribute();
	
	@Override
	IRootCompoundNode getGraphElement();
	
}
