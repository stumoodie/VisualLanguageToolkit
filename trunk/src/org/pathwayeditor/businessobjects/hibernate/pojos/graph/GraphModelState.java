/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import java.util.Date;

import org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

import uk.ed.inf.bitstring.IBitString;
import uk.ed.inf.graph.basic.IBasicGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.state.IGraphState;

/**
 * @author smoodie
 *
 */
public class GraphModelState implements IGraphMomento, IGraphState<BaseCompoundNode, BaseCompoundEdge> {
	private final IModel canvas;
	private final IGraphState<BaseCompoundNode, BaseCompoundEdge> state;
	private final Date creationDate;

	public GraphModelState(IModel canvas, IGraphState<BaseCompoundNode, BaseCompoundEdge> state){
		this.state = state;
		this.canvas = canvas;
		this.creationDate = new Date();
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento#getCanvas()
	 */
	public IModel getModel() {
		return this.canvas;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento#getCreationDate()
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}

}
