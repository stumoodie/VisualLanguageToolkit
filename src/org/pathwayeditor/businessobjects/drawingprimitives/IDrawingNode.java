/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.ICompoundNode;




/**
 * IDrawingNode defines an interface that is a facade for a compound node. @see ICompoundNode. 
 * 
 * @author Stuart Moodie
 */
public interface IDrawingNode extends IDrawingElement {

	@Override
	ICompoundNode getGraphElement();
	
	@Override
	IDrawingNodeAttribute getAttribute();
	
}
