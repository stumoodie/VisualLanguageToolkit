/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;

import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphBuilder;

/**
 * @author smoodie
 *
 */
public class ShapeLinkSubgraphBuilder extends BaseSubCompoundGraphBuilder {
	private final HibModel model;
	private ShapeLinkSubgraph subGraph;
	
	public ShapeLinkSubgraphBuilder(HibModel model) {
		super();
		this.model = model;
		this.subGraph = null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseSubCompoundGraphBuilder#getGraph()
	 */
	@Override
	public HibModel getGraph() {
		return this.model;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseSubCompoundGraphBuilder#getSubgraph()
	 */
	@Override
	public ShapeLinkSubgraph getSubgraph() {
		return this.subGraph;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseSubCompoundGraphBuilder#newSubgraph()
	 */
	@Override
	protected void newSubgraph() {
		this.subGraph = new ShapeLinkSubgraph(this.model);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseSubCompoundGraphBuilder#addAdditionalEdges()
	 */
	@Override
	protected void addAdditionalEdges() {
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseSubCompoundGraphBuilder#addAdditionalNodes()
	 */
	@Override
	protected void addAdditionalNodes() {
		// TODO Auto-generated method stub
		
	}

}
