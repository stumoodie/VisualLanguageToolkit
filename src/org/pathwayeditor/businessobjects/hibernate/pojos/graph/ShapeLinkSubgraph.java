/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraph;
import uk.ed.inf.graph.util.impl.DirectedEdgeSet;
import uk.ed.inf.graph.util.impl.NodeSet;

/**
 * @author smoodie
 *
 */
public class ShapeLinkSubgraph extends BaseSubCompoundGraph {
	private final HibModel model;
	
	public ShapeLinkSubgraph(HibModel model) {
		super();
		this.model = model;
		this.createNodeSet(new NodeSet<BaseCompoundNode, BaseCompoundEdge>());
		this.createEdgeSet(new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>());
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseSubCompoundGraph#getSuperGraph()
	 */
	@Override
	public HibModel getSuperGraph() {
		return this.model;
	}

}
