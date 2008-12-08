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

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraph;
import uk.ed.inf.graph.util.IFilterCriteria;
import uk.ed.inf.graph.util.impl.DirectedEdgeSet;
import uk.ed.inf.graph.util.impl.FilteredIterator;
import uk.ed.inf.graph.util.impl.NodeSet;

/**
 * @author smoodie
 *
 */
public class ShapeLinkSubgraph extends BaseSubCompoundGraph implements IDrawingElementSelection {
	private final HibModel model;
	private final IFilterCriteria<BaseCompoundNode> labelCriteria;
	private final IFilterCriteria<BaseCompoundNode> shapeCriteria;
	
	public ShapeLinkSubgraph(HibModel model) {
		super();
		this.model = model;
		this.createNodeSet(new NodeSet<BaseCompoundNode, BaseCompoundEdge>());
		this.createEdgeSet(new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>());
		this.labelCriteria = new IFilterCriteria<BaseCompoundNode>(){
			public boolean matched(BaseCompoundNode testObj) {
				return testObj instanceof ILabelNode;
			}
		};
		this.shapeCriteria = new IFilterCriteria<BaseCompoundNode>(){
			public boolean matched(BaseCompoundNode testObj) {
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
		return new IterationCaster<IDrawingNode, BaseCompoundNode>(super.nodeIterator());
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
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(super.edgeIterator());
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
		return new IterationCaster<IDrawingNode, BaseCompoundNode>(super.topNodeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#containsEdge(org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge)
	 */
	public boolean containsEdge(ILinkEdge linkEdge) {
		return super.containsEdge((BaseCompoundEdge)linkEdge);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#containsNode(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)
	 */
	public boolean containsNode(IDrawingNode node) {
		return super.containsNode((BaseCompoundNode)node);
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
		FilteredIterator<BaseCompoundNode> filteredIter = new FilteredIterator<BaseCompoundNode>(this.nodeIterator(), shapeCriteria);
		return new IterationCaster<IShapeNode, BaseCompoundNode>(filteredIter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#shapeNodeIterator()
	 */
	public Iterator<ILabelNode> labelNodeIterator() {
		FilteredIterator<BaseCompoundNode> filteredIter = new FilteredIterator<BaseCompoundNode>(this.nodeIterator(), labelCriteria);
		return new IterationCaster<ILabelNode, BaseCompoundNode>(filteredIter);
	}

}
