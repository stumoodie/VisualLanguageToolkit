/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraphBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabel;
import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IModelState;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObject;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraphFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.notationservice.IContextAdapterServiceProvider;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.impl.CompoundEdge;
import uk.ed.inf.graph.compound.impl.CompoundEdgeFactory;
import uk.ed.inf.graph.compound.impl.CompoundGraph;
import uk.ed.inf.graph.compound.impl.CompoundNode;

/**
 * @author smoodie
 *
 */
public class Model implements IModel {
	private final CompoundGraph graph;
	
	public Model(Object hibGraph, ICompoundGraphBuilder compoundGraphBuilder){
		this.graph = new CompoundGraph();
		compoundGraphBuilder.setCompoundGraph(graph);
		compoundGraphBuilder.setHibernateGraph(hibGraph);
		compoundGraphBuilder.buildGraph();
	}
	
	public Model(Model otherModel){
		this.graph = new CompoundGraph(otherModel.graph);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#canCreateLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShape, org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public boolean canCreateLink(ILinkObjectType linkObjectType,
			IShape srcShape, IShape tgtShape) {
		return linkObjectType.getLinkConnectionRules().isValidTarget(srcShape.getObjectType(),
					tgtShape.getObjectType());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#createCopy()
	 */
	public IModel createCopy() {
		return new Model(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#createLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShape, org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public ILink createLink(ILinkObjectType linkObjectType, IShape srcShape, IShape tgtShape) {
		CompoundNode outNode = getNode(srcShape);
		CompoundNode inNode = getNode(tgtShape);
		CompoundEdgeFactory edgeFactory = this.graph.edgeFactory();
		edgeFactory.setColourHandler(new LinkEdgeColourHandler());
		edgeFactory.setPair(outNode, inNode);
		CompoundEdge edge = edgeFactory.createEdge();
		ILink newLink = new Link(linkObjectType, edge);
		return newLink;
	}

	/**
	 * @param srcShape
	 * @return
	 */
	private CompoundNode getNode(IShape srcShape) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getContextAdapterServiceProvider()
	 */
	public IContextAdapterServiceProvider getContextAdapterServiceProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getCurrentState()
	 */
	public IModelState getCurrentState() {
		return new ModelState(this.graph.getCurrentState());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumLinks()
	 */
	public int getNumLinks() {
		return this.graph.getNumEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumShapes()
	 */
	public int getNumShapes() {
		int count = 0;
		Iterator<CompoundNode> iter = this.graph.nodeIterator();
		while(iter.hasNext()){
			CompoundNode node = iter.next();
			if(node.getColourHandler().getColour() instanceof IShape){
				count++;
			}
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getNumShapes()
	 */
	public int getNumLabels() {
		int count = 0;
		Iterator<CompoundNode> iter = this.graph.nodeIterator();
		while(iter.hasNext()){
			CompoundNode node = iter.next();
			if(node.getColourHandler().getColour() instanceof ILabel){
				count++;
			}
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getOwningShape()
	 */
	public IShape getOwningShape() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getRootObject()
	 */
	public IRootObject getRootObject() {
		CompoundNode root = this.graph.getRoot();
		return (RootObject)root.getColourHandler().getColour();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkIterator()
	 */
	public Iterator<ILink> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#removedSubgraph(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraphFactory)
	 */
	public void removedSubgraph(ISelectionSubgraphFactory subgraph) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#restoreToState(org.pathwayeditor.businessobjects.drawingprimitives.IModelState)
	 */
	public void restoreToState(IModelState iStateToRestore) {
		ModelState stateToRestore = (ModelState)iStateToRestore;
		this.graph.restoreState(stateToRestore.getGraphState());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#selectionSubgraphFactory()
	 */
	public ISelectionSubgraphFactory selectionSubgraphFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#shapeIterator()
	 */
	public Iterator<IShape> shapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	CompoundGraph getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
