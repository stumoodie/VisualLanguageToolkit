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
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.util.IFilterCriteria;
import uk.ac.ed.inf.graph.util.impl.DirectedEdgeSet;
import uk.ac.ed.inf.graph.util.impl.FilteredIterator;

/**
 * @author smoodie
 *
 */
public class ShapeLinkSubgraph extends BaseSubCompoundGraph implements IDrawingElementSelection {
	private final HibModel model;
	private final IFilterCriteria<ICompoundNode> labelCriteria;
	private final IFilterCriteria<ICompoundNode> shapeCriteria;
	
	public ShapeLinkSubgraph(HibModel model) {
		super();
		this.model = model;
		this.createNodeSet(new NodeSet<ICompoundNode, ICompoundEdge>());
		this.createTopNodeSet(new NodeSet<ICompoundNode, ICompoundEdge>());
		this.createEdgeSet(new DirectedEdgeSet<ICompoundNode, ICompoundEdge>());
		this.labelCriteria = new IFilterCriteria<ICompoundNode>(){
			public boolean matched(ICompoundNode testObj) {
				return testObj instanceof ILabelNode;
			}
		};
		this.shapeCriteria = new IFilterCriteria<ICompoundNode>(){
			public boolean matched(ICompoundNode testObj) {
				return testObj instanceof IShapeNode;
			}
		};
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseSubCompoundGraph#getSuperGraph()
	 */
	@Override
	public HibModel getSuperGraph() {
		return this.model;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#drawingNodeIterator()
	 */
	public Iterator<IDrawingNode> drawingNodeIterator() {
		return new IterationCaster<IDrawingNode, ICompoundNode>(super.nodeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#getModel()
	 */
	public IModel getModel() {
		return this.getSuperGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#linkEdgeIterator()
	 */
	public Iterator<ILinkEdge> linkEdgeIterator() {
		return new IterationCaster<ILinkEdge, ICompoundEdge>(super.edgeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#numTopDrawingNodes()
	 */
	public int numTopDrawingNodes() {
		return super.getNumTopNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#topDrawingNodeIterator()
	 */
	public Iterator<IDrawingNode> topDrawingNodeIterator() {
		return new IterationCaster<IDrawingNode, ICompoundNode>(super.topNodeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#containsEdge(org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge)
	 */
	public boolean containsEdge(ILinkEdge linkEdge) {
		return super.containsEdge((ICompoundEdge)linkEdge);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#containsNode(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)
	 */
	public boolean containsNode(IDrawingNode node) {
		return super.containsNode((ICompoundNode)node);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#hasDanglingEdges()
	 */
	public boolean hasDanglingEdges() {
		// an induced subgraph cannot have dangling edges
		// perhaps an alternative implementation could be used. 
		return !super.isInducedSubgraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#shapeNodeIterator()
	 */
	public Iterator<IShapeNode> shapeNodeIterator() {
		FilteredIterator<ICompoundNode> filteredIter = new FilteredIterator<ICompoundNode>(this.nodeIterator(), shapeCriteria);
		return new IterationCaster<IShapeNode, ICompoundNode>(filteredIter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#shapeNodeIterator()
	 */
	public Iterator<ILabelNode> labelNodeIterator() {
		FilteredIterator<ICompoundNode> filteredIter = new FilteredIterator<ICompoundNode>(this.nodeIterator(), labelCriteria);
		return new IterationCaster<ILabelNode, ICompoundNode>(filteredIter);
	}

}
