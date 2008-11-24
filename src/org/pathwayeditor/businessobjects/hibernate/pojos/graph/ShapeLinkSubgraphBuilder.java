/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
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
		// go through links and add in all the label nodes associated with their properties.
		for(BaseCompoundEdge edge : this.getEdgeList()) {
			ILinkEdge linkEdge = (ILinkEdge)edge;
			ILinkAttribute linkAttribute = linkEdge.getAttribute();
			Iterator<ILabelNode> labelNodeIter = linkEdge.getOwningSubModel().labelIterator();
			while(labelNodeIter.hasNext()) {
				ILabelNode labelNode = labelNodeIter.next();
				ILabelAttribute labelAttrib = labelNode.getAttribute();
				if(linkAttribute.getProperty(labelAttrib.getProperty().getDefinition()) != null) {
					// has the property so add label to selection nodes
					this.getNodeList().add((BaseCompoundNode)labelNode);
				}
			}
		}
	}

}
