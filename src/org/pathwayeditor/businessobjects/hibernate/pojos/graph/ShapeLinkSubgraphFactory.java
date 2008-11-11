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
	public IDrawingElementSelection createSelection() {
		return this.createInducedSubgraph();
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
}
