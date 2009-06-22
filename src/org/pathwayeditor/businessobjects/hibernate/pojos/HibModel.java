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
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CanvasLinkEdgeFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CompoundGraphCopyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.GraphModelState;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeLinkSubgraph;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeLinkSubgraphFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeNodeFactory;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

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
	private ITree<BaseCompoundNode> tree = null;
	private Long id;
	private HibRootNode rootNode;
	private IndexCounter nodeCntr = new IndexCounter(ROOT_NODE_IDX);
	private IndexCounter edgeCntr = new IndexCounter(INIT_EDGE_IDX);
	private HibCanvas canvas;
	private IHibNotationFactory hibNotationFactory;
	private ListenableModelStructureChangeItem listenerHandler = new ListenableModelStructureChangeItem(this);
	private final transient IndexCounter momentoCntr;
	private final IFilterCriteria<BaseCompoundNode> labelCriteria;
	private final IFilterCriteria<BaseCompoundNode> shapeCriteria;
	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibModel() {
		super(new CompoundGraphCopyBuilder());
		this.momentoCntr = new IndexCounter();
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
	
	public HibModel(HibCanvas newCanvas, IRootObjectType rootObjectType, IHibNotationFactory hibNotationFactory) {
		this();
		this.canvas = newCanvas;
		this.hibNotationFactory = hibNotationFactory;
		HibObjectType hibRootObjectType = hibNotationFactory.getObjectType(rootObjectType);
		this.rootNode = new HibRootNode(this, ROOT_NODE_IDX, new HibRootAttribute(this.canvas, this.canvas.getCreationSerialCounter().nextIndex(),
				rootObjectType, hibRootObjectType));
		this.tree = new GeneralTree<BaseCompoundNode>(this.rootNode);
	}
	
	public HibModel(HibCanvas newCanvas, HibModel otherModel){
		super(new CompoundGraphCopyBuilder(), otherModel);
		this.canvas = newCanvas;
		this.momentoCntr = new IndexCounter();
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
		return new CanvasLinkEdgeFactory(this, this.hibNotationFactory);
	}

	@Override
	public final IndexCounter getEdgeCounter() {
		return this.edgeCntr;
	}

	@Override
	public final IndexCounter getNodeCounter() {
		return this.nodeCntr;
	}

	@Override
	protected ITree<BaseCompoundNode> getNodeTree() {
		return this.tree;
	}

	public GraphModelState getCurrentState(){
		return new GraphModelState(this, this.momentoCntr.nextIndex(), super.getCurrentState());
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
		this.tree = new GeneralTree<BaseCompoundNode>(this.rootNode);
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
		return new IterationCaster<IDrawingNode, BaseCompoundNode>(this.nodeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkEdgeIterator()
	 */
	public Iterator<ILinkEdge> linkEdgeIterator() {
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(this.edgeIterator());
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

	/**
	 * Notifies the mode that there has been a structural change. Should be used by submodels to notify the
	 * model that they have changes.
	 * @param type the change type.
	 * @param changedNode the node that has changes.
	 */
	void notifyNodeStructureChange(ModelStructureChangeType type, IDrawingNode changedNode){
		this.listenerHandler.notifyNodeStructureChange(type, changedNode);
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

	void notifyEdgeStructureChange(ModelStructureChangeType type, ILinkEdge changedEdge) {
		this.listenerHandler.notifyEdgeStructureChange(type, changedEdge);
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
		FilteredIterator<BaseCompoundNode> filteredIter = new FilteredIterator<BaseCompoundNode>(this.nodeIterator(), labelCriteria);
		return new IterationCaster<ILabelNode, BaseCompoundNode>(filteredIter);
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
		FilteredIterator<BaseCompoundNode> filteredIter = new FilteredIterator<BaseCompoundNode>(this.nodeIterator(), shapeCriteria);
		return new IterationCaster<IShapeNode, BaseCompoundNode>(filteredIter);
	}

	public boolean areListenersEnabled() {
		return this.listenerHandler.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenerHandler.setListenersEnabled(enabled);
	}
}
