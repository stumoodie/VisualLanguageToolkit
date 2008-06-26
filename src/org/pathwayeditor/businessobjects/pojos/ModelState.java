/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.IModelState;

import uk.ed.inf.graph.compound.impl.CompoundEdge;
import uk.ed.inf.graph.compound.impl.CompoundNode;
import uk.ed.inf.graph.state.IGraphState;

/**
 * @author smoodie
 *
 */
public class ModelState implements IModelState {
	private final IGraphState<CompoundNode, CompoundEdge> state;
	
	public ModelState(IGraphState<CompoundNode, CompoundEdge> state){
		this.state = state;
	}

	IGraphState<CompoundNode, CompoundEdge> getGraphState(){
		return this.state;
	}
}
