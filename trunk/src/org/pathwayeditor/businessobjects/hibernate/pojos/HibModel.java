package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CanvasLinkEdgeFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CompoundGraphCopyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.GraphModelState;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeLinkSubgraphFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeNodeFactory;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphFactory;
import uk.ed.inf.graph.util.IndexCounter;
import uk.ed.inf.tree.GeneralTree;
import uk.ed.inf.tree.ITree;

public class HibModel extends BaseCompoundGraph implements IModel, Serializable {
	private static final long serialVersionUID = 6646425760947242284L;
	private ITree<BaseCompoundNode> tree = null;
	private Long id;
	private HibRootNode rootNode;
	private IndexCounter nodeCntr = new IndexCounter();
	private IndexCounter edgeCntr = new IndexCounter();
	private HibCanvas canvas ;
	
	HibModel() {
		super(new CompoundGraphCopyBuilder());
	}
	
	public HibModel(HibCanvas newCanvas, IRootObjectType rootObjectType) {
		this();
		this.rootNode = new HibRootNode(this, nodeCntr.getLastIndex(), rootObjectType);
		this.tree = new GeneralTree<BaseCompoundNode>(this.rootNode);
		this.canvas = newCanvas;
	}
	
	public HibModel(HibCanvas newCanvas, HibModel otherModel){
		super(new CompoundGraphCopyBuilder(), otherModel);
		IRootObjectType otherRootObjectType = otherModel.getRootNode().getObjectType();
		this.rootNode = new HibRootNode(this, nodeCntr.getLastIndex(), otherRootObjectType);
		this.tree = new GeneralTree<BaseCompoundNode>(this.rootNode);
		this.canvas = newCanvas;
	}
	
	int getLastEdgeIndex() {
		return this.edgeCntr.getLastIndex();
	}

	void setLastEdgeIndex(int lastEdgeIndex) {
		this.edgeCntr = new IndexCounter(lastEdgeIndex);
	}

	int getLastNodeIndex() {
		return this.nodeCntr.getLastIndex();
	}

	void setLastNodeIndex(int lastNodeIndex) {
		this.nodeCntr = new IndexCounter(lastNodeIndex);
	}

	@SuppressWarnings("unused")
	private void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return id;
	}
	
	void setRootNode(HibRootNode value) {
		this.rootNode = value;
		this.tree = new GeneralTree<BaseCompoundNode>(this.rootNode);
	}
	
	public HibRootNode getRootNode() {
		return rootNode;
	}

	@Override
	public CanvasLinkEdgeFactory edgeFactory() {
		return new CanvasLinkEdgeFactory(this);
	}

	@Override
	protected IndexCounter getEdgeCounter() {
		return this.edgeCntr;
	}

	@Override
	protected IndexCounter getNodeCounter() {
		return this.nodeCntr;
	}

	@Override
	protected ITree<BaseCompoundNode> getNodeTree() {
		return this.tree;
	}

	public GraphModelState getCurrentState(){
		return new GraphModelState(this, super.getCurrentState());
	}
	
//	public HibSubCompoundGraphFactory subgraphFactory() {
//		return this.subgraphFactory;
//	}
	
//	@Override
//	public HibCompoundNode getNode(int index){
//		return (HibCompoundNode)super.getNode(index);
//	}
//
//	@Override
//	public HibLinkEdge getEdge(int index){
//		return (HibLinkEdge)super.getEdge(index);
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#createCopy(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public IModel createCopy(ICanvas newCanvas) {
		return new HibModel((HibCanvas)newCanvas, this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#removeSubgraph(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public void removeSubgraph(ICanvasObjectSelection selection) {
		ShapeLinkSubgraphFactory subgraphFactory = (ShapeLinkSubgraphFactory)selection;
		this.removeSubgraph(subgraphFactory.createInducedSubgraph());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#restoreToState(org.pathwayeditor.businessobjects.drawingprimitives.IGraphState)
	 */
	public void restoreToState(IGraphMomento stateToRestore) {
		GraphModelState state = (GraphModelState)stateToRestore;
		this.restoreState(state);
	}

	public HibCanvas getCanvas() {
		return this.canvas;
	}

	public void setCanvas(HibCanvas canvas) {
		this.canvas = canvas;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundGraph#createCopyOfRootNode(int, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected void createCopyOfRootNode(int newIndexValue,
			BaseCompoundNode otherRootNode) {
		// do nothing as it is already created.
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundGraph#nodeFactory()
	 */
	@Override
	public BaseCompoundNodeFactory nodeFactory() {
		return new ShapeNodeFactory(this.getRootNode());
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundGraph#subgraphFactory()
	 */
	@Override
	public BaseSubCompoundGraphFactory subgraphFactory() {
		return new ShapeLinkSubgraphFactory(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkEdgeFactory()
	 */
	public ILinkEdgeFactory linkEdgeFactory() {
		return new CanvasLinkEdgeFactory(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#newCanvasObjectSelection()
	 */
	public ICanvasObjectSelection newCanvasObjectSelection() {
		return new ShapeLinkSubgraphFactory(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#drawingNodeIterator()
	 */
	public Iterator<IDrawingNode> drawingNodeIterator() {
		return new IterationCaster<IDrawingNode, BaseCompoundNode>(this.nodeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkEdgeIterator()
	 */
	public Iterator<ILinkEdge> linkEdgeIterator() {
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(this.edgeIterator());
	}
}
