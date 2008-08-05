/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.IModelState;

import uk.ed.inf.bitstring.IBitString;
import uk.ed.inf.graph.basic.IBasicGraph;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundEdge;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;
import uk.ed.inf.graph.state.IGraphState;

/**
 * @author smoodie
 *
 */
public class GraphModelState implements IModelState, IGraphState<ArchetypalCompoundNode, ArchetypalCompoundEdge> {
	private final IGraphState<ArchetypalCompoundNode, ArchetypalCompoundEdge> state;

	public GraphModelState(IGraphState<ArchetypalCompoundNode, ArchetypalCompoundEdge> state){
		this.state = state;
	}
	
	public IBitString getEdgeStates() {
		return this.state.getEdgeStates();
	}

	public IBasicGraph<ArchetypalCompoundNode, ArchetypalCompoundEdge> getGraph() {
		return this.state.getGraph();
	}

	public IBitString getNodeStates() {
		return this.state.getNodeStates();
	}

}
