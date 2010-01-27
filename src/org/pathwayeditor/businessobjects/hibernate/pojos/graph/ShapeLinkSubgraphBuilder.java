/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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
		addLinkLabels();
	}
	
	private void addLinkLabels() {
		// go through links and add in all the label nodes associated with their properties.
		for(BaseCompoundEdge edge : this.getEdgeList()) {
			ILinkEdge linkEdge = (ILinkEdge)edge;
			ILinkAttribute linkAttribute = linkEdge.getAttribute();
			Iterator<ILabelNode> labelNodeIter = linkEdge.getOwningSubModel().labelIterator();
			while(labelNodeIter.hasNext()) {
				ILabelNode labelNode = labelNodeIter.next();
				ILabelAttribute labelAttrib = labelNode.getAttribute();
				if(linkAttribute.containsProperty(labelAttrib.getProperty())) {
					// has the property so add label to selection nodes
					this.getNodeList().add((BaseCompoundNode)labelNode);
					this.getTopNodeList().add((BaseCompoundNode)labelNode);
				}
				else if(linkAttribute.getSourceTerminus().containsProperty(labelAttrib.getProperty())) {
					// check source terminus for property
					this.getNodeList().add((BaseCompoundNode)labelNode);
					this.getTopNodeList().add((BaseCompoundNode)labelNode);
				}
				else if(linkAttribute.getTargetTerminus().containsProperty(labelAttrib.getProperty())) {
					// check target terminus for property
					this.getNodeList().add((BaseCompoundNode)labelNode);
					this.getTopNodeList().add((BaseCompoundNode)labelNode);
				}
			}
		}
	}

}
