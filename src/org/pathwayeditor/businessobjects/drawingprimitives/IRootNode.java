/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.IRootCompoundNode;


/**
 * @author Stuart Moodie
 *
 */
public interface IRootNode extends ITypedDrawingNode {
	
	@Override
	IRootAttribute getAttribute();
	
	@Override
	IRootCompoundNode getGraphElement();
	
}
