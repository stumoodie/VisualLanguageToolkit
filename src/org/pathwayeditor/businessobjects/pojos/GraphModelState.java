/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.IModelState;

import uk.ed.inf.bitstring.IBitString;
import uk.ed.inf.graph.basic.IBasicGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.state.IGraphState;

/**
 * @author smoodie
 *
 */
public class GraphModelState implements IModelState, IGraphState<BaseCompoundNode, BaseCompoundEdge> {
	private final IGraphState<BaseCompoundNode, BaseCompoundEdge> state;

	public GraphModelState(IGraphState<BaseCompoundNode, BaseCompoundEdge> state){
		this.state = state;
	}
	
	public IBitString getEdgeStates() {
		return this.state.getEdgeStates();
	}

	public IBasicGraph<BaseCompoundNode, BaseCompoundEdge> getGraph() {
		return this.state.getGraph();
	}

	public IBitString getNodeStates() {
		return this.state.getNodeStates();
	}

}
