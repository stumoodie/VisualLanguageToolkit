package org.pathwayeditor.businessobjects.compoundgraph;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundGraph;

import uk.ed.inf.graph.compound.base.BaseCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphBuilder;

public class HibSubCompoundGraphBuilder extends BaseSubCompoundGraphBuilder {
	private HibSubCompoundGraph subgraph = null;
	
	public HibSubCompoundGraphBuilder(HibCompoundGraph graph) {
		super(graph);
	}

	@Override
	public BaseSubCompoundGraph getSubgraph() {
		return this.subgraph;
	}

	@Override
	protected void newSubgraph(BaseCompoundGraph compoundGraph) {
		this.subgraph = new HibSubCompoundGraph((HibCompoundGraph)compoundGraph);
	}

}
