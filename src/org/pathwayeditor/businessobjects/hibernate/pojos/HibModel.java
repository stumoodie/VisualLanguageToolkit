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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenableModelStructureChangeItem;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CanvasLinkEdgeFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CompoundGraphCopyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.GraphModelState;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeLinkSubgraph;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeLinkSubgraphFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeNodeFactory;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

import uk.ed.inf.graph.compound.ICompoundEdge;
import uk.ed.inf.graph.compound.ICompoundNode;
import uk.ed.inf.graph.compound.ISubCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;
import uk.ed.inf.graph.compound.base.BaseSubCompoundGraphFactory;
import uk.ed.inf.graph.util.IFilterCriteria;
import uk.ed.inf.graph.util.IndexCounter;
import uk.ed.inf.graph.util.impl.FilteredIterator;
import uk.ed.inf.tree.GeneralTree;
import uk.ed.inf.tree.ITree;

public class HibModel extends BaseCompoundGraph implements IModel {
//	private static final long serialVersionUID = 6646425760947242284L;
	private final static int ROOT_NODE_IDX = 0;
	private final static int INIT_EDGE_IDX = -1;
	private ITree<ICompoundNode> tree = null;
	private Long id;
	private HibRootNode rootNode;
	private IndexCounter nodeCntr = new IndexCounter(ROOT_NODE_IDX);
	private IndexCounter edgeCntr = new IndexCounter(INIT_EDGE_IDX);
	private HibCanvas canvas;
	private IHibNotationFactory hibNotationFactory;
	private ListenableModelStructureChangeItem listenerHandler = new ListenableModelStructureChangeItem();
	private final transient IndexCounter momentoCntr;
	private final IFilterCriteria<ICompoundNode> labelCriteria;
	private final IFilterCriteria<ICompoundNode> shapeCriteria;
	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibModel() {
		super(new CompoundGraphCopyBuilder());
		this.momentoCntr = new IndexCounter();
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
	
	public HibModel(HibCanvas newCanvas, IRootObjectType rootObjectType, IHibNotationFactory hibNotationFactory) {
		this();
		this.canvas = newCanvas;
		this.hibNotationFactory = hibNotationFactory;
		HibObjectType hibRootObjectType = hibNotationFactory.getObjectType(rootObjectType.getUniqueId());
		this.rootNode = new HibRootNode(this, ROOT_NODE_IDX, new HibRootAttribute(this.canvas, this.canvas.getCreationSerialCounter().nextIndex(),
				rootObjectType, hibRootObjectType));
		this.tree = new GeneralTree<ICompoundNode>(this.rootNode);
	}
	
	public HibModel(HibCanvas newCanvas, HibModel otherModel){
		super(new CompoundGraphCopyBuilder(), otherModel);
		this.canvas = newCanvas;
		this.momentoCntr = new IndexCounter();
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
		this.tree = new GeneralTree<ICompoundNode>(this.rootNode);
	}
	
	public HibRootNode getRootNode() {
		return rootNode;
	}

	@Override
	public CanvasLinkEdgeFactory edgeFactory() {
		return new CanvasLinkEdgeFactory(this, this.hibNotationFactory);
	}

	@Override
	public final IndexCounter getEdgeCounter() {
		return this.edgeCntr;
	}

	public final void setEdgeCounter(IndexCounter index) {
		this.edgeCntr = index;
	}

	@Override
	public final IndexCounter getNodeCounter() {
		return this.nodeCntr;
	}

	public final void setNodeCounter(IndexCounter index) {
		this.nodeCntr = index;
	}

	@Override
	protected ITree<ICompoundNode> getNodeTree() {
		return this.tree;
	}

	public GraphModelState getCurrentState(){
		return new GraphModelState(this, this.momentoCntr.nextIndex(), super.getCurrentState());
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#createCopy(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public IModel createCopy(ICanvas newCanvas) {
		return new HibModel((HibCanvas)newCanvas, this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#removeSubgraph(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public void removeSubgraph(IDrawingElementSelection selection) {
		ShapeLinkSubgraph subgraphFactory = (ShapeLinkSubgraph)selection;
		super.removeSubgraph(subgraphFactory);
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
	protected void createCopyOfRootNode(int newIndexValue, BaseCompoundNode otherRootNode) {
		HibRootNode otherHibRootNode = (HibRootNode)otherRootNode;
		HibRootAttribute copiedAttribute = new HibRootAttribute(this.getCanvas(), this.getCanvas().getCreationSerialCounter().nextIndex(), otherHibRootNode.getAttribute());
		this.rootNode = new HibRootNode(this, newIndexValue, copiedAttribute);
		this.tree = new GeneralTree<ICompoundNode>(this.rootNode);
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
		return new CanvasLinkEdgeFactory(this, this.hibNotationFactory);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#newCanvasObjectSelection()
	 */
	public ISelectionFactory newSelectionFactory() {
		return new ShapeLinkSubgraphFactory(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#drawingNodeIterator()
	 */
	public Iterator<IDrawingNode> drawingNodeIterator() {
		return new IterationCaster<IDrawingNode, ICompoundNode>(this.nodeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkEdgeIterator()
	 */
	public Iterator<ILinkEdge> linkEdgeIterator() {
		return new IterationCaster<ILinkEdge, ICompoundEdge>(this.edgeIterator());
	}

	/**
	 * @return the hibNotationFactory
	 */
	public IHibNotationFactory getHibNotationFactory() {
		return this.hibNotationFactory;
	}

	/**
	 * @param hibNotationFactory the hibNotationFactory to set
	 */
	public void setHibNotationFactory(IHibNotationFactory hibNotationFactory) {
		this.hibNotationFactory = hibNotationFactory;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListenee#addSubModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListener)
	 */
	public void addModelChangeListener(IModelChangeListener listener) {
		this.listenerHandler.addModelChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListenee#removeSubModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListener)
	 */
	public void removeModelChangeListener(IModelChangeListener listener) {
		this.listenerHandler.removeModelChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListenee#subModelNodeChangeListenerIterator()
	 */
	public Iterator<IModelChangeListener> modelChangeListenerIterator() {
		return this.listenerHandler.modelChangeListenerIterator();
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(rootNodeIdx=");
		builder.append(this.rootNode.getIndex());
		builder.append(", numNodes=");
		builder.append(this.getNumNodes());
		builder.append(", numEdges=");
		builder.append(this.getNumEdges());
		builder.append(")");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#canRemoveSelection(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)
	 */
	public boolean canRemoveSelection(IDrawingElementSelection selection) {
		return super.canRemoveSubgraph((ShapeLinkSubgraph)selection);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundGraph#hasPassedAdditionalValidation()
	 */
	@Override
	protected boolean hasPassedAdditionalValidation() {
		return this.tree != null && this.tree.getRootNode().equals(this.getRootNode())
			&& this.getCanvas()!= null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#labelNodeIterator()
	 */
	public Iterator<ILabelNode> labelNodeIterator() {
		FilteredIterator<ICompoundNode> filteredIter = new FilteredIterator<ICompoundNode>(this.nodeIterator(), labelCriteria);
		return new IterationCaster<ILabelNode, ICompoundNode>(filteredIter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numDrawingElements()
	 */
	public int numDrawingElements() {
		return this.numDrawingNodes() + this.numLinkEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numDrawingNode()
	 */
	public int numDrawingNodes() {
		return super.getNumNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numLabelNodes()
	 */
	public int numLabelNodes() {
		int count = 0;
		Iterator<ILabelNode> iter = this.labelNodeIterator();
		while(iter.hasNext()) {
			iter.next();
			count++;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numLinkEdges()
	 */
	public int numLinkEdges() {
		return super.getNumEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numShapeNodes()
	 */
	public int numShapeNodes() {
		int count = 0;
		Iterator<IShapeNode> iter = this.shapeNodeIterator();
		while(iter.hasNext()) {
			iter.next();
			count++;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#shapeNodeIterator()
	 */
	public Iterator<IShapeNode> shapeNodeIterator() {
		FilteredIterator<ICompoundNode> filteredIter = new FilteredIterator<ICompoundNode>(this.nodeIterator(), shapeCriteria);
		return new IterationCaster<IShapeNode, ICompoundNode>(filteredIter);
	}

	public boolean areListenersEnabled() {
		return this.listenerHandler.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenerHandler.setListenersEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundGraph#notifyCopyOperationComplete(uk.ed.inf.graph.compound.ISubCompoundGraph, uk.ed.inf.graph.compound.ISubCompoundGraph)
	 */
	@Override
	protected void notifyCopyOperationComplete(
			ISubCompoundGraph originalSelection,
			ISubCompoundGraph copiedSelection) {
		this.listenerHandler.notifyCopyOperationCompleted((IDrawingElementSelection)originalSelection, (IDrawingElementSelection)copiedSelection);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundGraph#notifyRemovalOperationComplete(uk.ed.inf.graph.compound.ISubCompoundGraph)
	 */
	@Override
	protected void notifyRemovalOperationComplete(ISubCompoundGraph subgraph) {
		this.listenerHandler.notifyRemovalOperationCompleted((IDrawingElementSelection)subgraph);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundGraph#notifyNewEdge(uk.ed.inf.graph.compound.base.BaseCompoundEdge)
	 */
	@Override
	public void notifyNewEdge(BaseCompoundEdge newEdge) {
		ISelectionFactory fact = this.newSelectionFactory();
		fact.addLink((ILinkEdge)newEdge);
		this.listenerHandler.notifyNewEdge(fact.createGeneralSelection());
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundGraph#notifyNewNode(uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	public void notifyNewNode(BaseCompoundNode newNode) {
		ISelectionFactory fact = this.newSelectionFactory();
		fact.addDrawingNode((IDrawingNode)newNode);
		this.listenerHandler.notifyNewNode(fact.createGeneralSelection());
	}
}
