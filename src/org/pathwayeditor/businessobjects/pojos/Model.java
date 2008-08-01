/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.contectadapter.IContextAdapterServiceProvider;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabel;
import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IModelState;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObject;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraphFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.impl.CompoundGraph;
import uk.ed.inf.graph.compound.impl.CompoundNode;

/**
 * @author smoodie
 *
 */
public class Model implements IModel {
	private final CompoundGraph graph;
//	private final HibModel hibGraph;
	private final Canvas canvas;

	/**
	 * Constructs an new Model for a given canvas.
	 * @param canvas
	 */
	public Model(Canvas canvas){
//		this.hibGraph = new HibModel(this, canvas.getHibObject());
		this.graph = new CompoundGraph();
		this.canvas = canvas;
	}
	
	public Model(Canvas canvas, CompoundGraph compoundGraph){
		this.canvas = canvas;
		this.graph = compoundGraph;
	}
	
//	/**
//	 * Constructs a model from  a hibernate model.
//	 * @param compoundGraphBuilder
//	 * @param hibGraph
//	 */
//	public Model(ICompoundGraphBuilder compoundGraphBuilder, HibModel hibGraph){
////		this.hibGraph = hibGraph;
//		this.graph = new CompoundGraph();
//		compoundGraphBuilder.setCompoundGraph(graph);
//		compoundGraphBuilder.setHibernateGraph(hibGraph);
//		compoundGraphBuilder.buildGraph();
//	}
	
	/**
	 * Copy constructor that copies this model to a new canvas that is provided.
	 * @param otherModel
	 * @param newCanvas
	 */
	public Model(Model otherModel, Canvas newCanvas){
		this.graph = new CompoundGraph(otherModel.graph);
		this.canvas = newCanvas;
//		this.hibGraph = new HibModel(this, newCanvas.getHibObject());
	}
	
	public Canvas getCanvas(){
//		return this.hibGraph.getCanvas().getBusinessObject();
		return this.canvas;
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
	public IModel createCopy(ICanvas iNewCanvas) {
		Canvas newCanvas = (Canvas)iNewCanvas;
		return new Model(this, newCanvas);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#createLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShape, org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public ILink createLink(ILinkObjectType linkObjectType, IShape iSrcShape, IShape iTgtShape) {
		Shape srcShape = (Shape)iSrcShape;
		Shape tgtShape = (Shape)iTgtShape;
		ILink newLink = new Link(this.getCanvas(), linkObjectType, srcShape, tgtShape);
		return newLink;
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
		return this.graph;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#labelIterator()
	 */
	public Iterator<ILabel> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
