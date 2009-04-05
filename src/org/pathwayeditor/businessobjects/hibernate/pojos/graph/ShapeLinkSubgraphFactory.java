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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphFactory;

/**
 * @author smoodie
 *
 */
public class ShapeLinkSubgraphFactory extends BaseSubCompoundGraphFactory implements ISelectionFactory {

	public ShapeLinkSubgraphFactory(HibModel model) {
		super(new ShapeLinkSubgraphBuilder(model));
	}
	
	public ShapeLinkSubgraph createSubgraph(){
		return (ShapeLinkSubgraph)super.createSubgraph();
	}

	public ShapeLinkSubgraph createInducedSubgraph(){
		return (ShapeLinkSubgraph)super.createInducedSubgraph();
	}

	public ShapeLinkSubgraph createPermissiveInducedSubgraph(){
		return (ShapeLinkSubgraph)super.createPermissiveInducedSubgraph();
	}

	/**
	 * Adds a link to the factory for future selection in a subgraph.
	 * @param selectedLink the link to add to the factory
	 * @throws ClassCastException if <code>selectedLink</code> is not of type HibLinkEdge. 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#addLink(ILinkEdge)
	 */
	public void addLink(ILinkEdge selectedLink) {
		this.addEdge((HibLinkEdge)selectedLink);
	}

	/**
	 * Adds a link to the factory for future selection in a subgraph.
	 * @param selectedShape the shape to add to the factory
	 * @throws ClassCastException if <code>selectedShape</code> is not of type HibShapeNode. 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#addDrawingNode(IDrawingNode)
	 */
	public void addDrawingNode(IDrawingNode selectedShape) {
		this.addNode((HibCompoundNode)selectedShape);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection#getModel()
	 */
	public HibModel getModel() {
		return (HibModel)super.getGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#createSelection()
	 */
	public IDrawingElementSelection createGeneralSelection() {
		return this.createPermissiveInducedSubgraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#drawingNodeIterator()
	 */
	public Iterator<IDrawingNode> drawingNodeIterator() {
		return new IterationCaster<IDrawingNode, BaseCompoundNode>(super.nodeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#linkEdgeIterator()
	 */
	public Iterator<ILinkEdge> linkEdgeIterator() {
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(super.edgeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#numDrawingNodes()
	 */
	public int numDrawingNodes() {
		return super.numNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#numLinkEdges()
	 */
	public int numLinkEdges() {
		return super.numEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#createEdgeExcludedSelection()
	 */
	public IDrawingElementSelection createEdgeExcludedSelection() {
		return this.createInducedSubgraph();
	}
}
