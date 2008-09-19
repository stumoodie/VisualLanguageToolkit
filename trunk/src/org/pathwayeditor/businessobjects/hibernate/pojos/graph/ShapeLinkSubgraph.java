/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;

import uk.ed.inf.graph.compound.base.BaseSubCompoundGraph;

/**
 * @author smoodie
 *
 */
public class ShapeLinkSubgraph extends BaseSubCompoundGraph {
	private final HibModel model;
	
	/**
	 * @param superGraph
	 */
	public ShapeLinkSubgraph(HibModel model) {
		super();
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseSubCompoundGraph#getSuperGraph()
	 */
	@Override
	public HibModel getSuperGraph() {
		return this.model;
	}

}
