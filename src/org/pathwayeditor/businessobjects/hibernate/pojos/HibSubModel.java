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

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelSubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenableModelStructureChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CompoundGraphCopyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CompoundGraphMoveBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.LabelNodeFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.LinkEdgeChildFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeLinkSubgraph;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeNodeFactory;

import uk.ed.inf.graph.compound.ISubCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.util.IDirectedEdgeSet;
import uk.ed.inf.graph.util.IFilterCriteria;
import uk.ed.inf.graph.util.impl.DirectedEdgeSet;
import uk.ed.inf.graph.util.impl.FilteredIterator;
import uk.ed.inf.graph.util.impl.NodeSet;

public class HibSubModel extends BaseChildCompoundGraph implements ILabelSubModel {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private Long id = null;
	private HibCompoundNode rootNode;
	private IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> edges = new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>();
	private final IFilterCriteria<BaseCompoundNode> labelCriteria;
	private final IFilterCriteria<BaseCompoundNode> shapeCriteria;
	private final ListenableModelStructureChangeItem listenerHandler = new ListenableModelStructureChangeItem();

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibSubModel() {
		super(new CompoundGraphCopyBuilder(), new CompoundGraphMoveBuilder());
		this.rootNode = null;
		this.createNodeSet(new NodeSet<BaseCompoundNode, BaseCompoundEdge>());
		this.createEdgeSet(this.edges);
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

	public HibSubModel(HibCompoundNode rootNode) {
		this();
		this.rootNode = rootNode;
		this.createNodeSet(this.rootNode.getChildren());
	}

	@Override
	public HibModel getSuperGraph(){
		return (HibModel)super.getSuperGraph();
	}
	
	@Override
	protected void addNewNode(BaseCompoundNode node) {
		super.addNewNode(node);
	}
	
	@Override
	protected void addNewEdge(BaseCompoundEdge edge) {
		super.addNewEdge(edge);
	}
	
	@Override
	public HibLinkEdge getEdge(int index){
		return (HibLinkEdge)super.getEdge(index);
	}
	
	@Override
	public HibCompoundNode getNode(int index){
		return (HibCompoundNode)super.getNode(index);
	}
	
	@SuppressWarnings("unused")
	private void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return id;
	}

	void setRootNode(HibCompoundNode value) {
		this.rootNode = value;
		this.createNodeSet(this.rootNode.getChildren());
	}

	public HibCompoundNode getRootNode() {
		return rootNode;
	}

	void setEdges(IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> value) {
		this.edges = value;
		this.createEdgeSet(this.edges);
	}

	IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> getEdges() {
		return this.edges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rootNode == null) ? 0 : rootNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibSubModel))
			return false;
		final HibSubModel other = (HibSubModel) obj;
		if (rootNode == null) {
			if (other.rootNode != null)
				return false;
		} else if (!rootNode.equals(other.rootNode))
			return false;
		return true;
	}

	@Override
	public LinkEdgeChildFactory edgeFactory() {
		return new LinkEdgeChildFactory(this);
	}

	@Override
	public ShapeNodeFactory nodeFactory() {
		return new ShapeNodeFactory(this.getRootNode());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubMode#canCopyHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public boolean canCopyHere(IDrawingElementSelection canvasObjectSelection) {
		boolean retVal = false;
		if(canvasObjectSelection != null) {
			ShapeLinkSubgraph subgraph = (ShapeLinkSubgraph)canvasObjectSelection;
			retVal = this.getModel().getCanvas().getNotationSubsystem().equals(subgraph.getModel().getCanvas().getNotationSubsystem())
				// don't need to test for dangling links as this is done by the superclass in method below. 
				&& super.canCopyHere(subgraph);
			// now check object types of top nodes
			Iterator<IDrawingNode> iter = canvasObjectSelection.topDrawingNodeIterator();
			while(iter.hasNext() && retVal) {
				IDrawingNode node = iter.next();
				// ignore labels - just looks at types
				if(node instanceof ITypedDrawingNode){
					ITypedDrawingNode typesNode = (ITypedDrawingNode)node;
					retVal = this.getRootNode().canParent(typesNode.getAttribute().getObjectType());
				}
				if(node instanceof ILabelNode){
					ILabelNode labelNode = (ILabelNode)node;
					IAnnotatedObject annotObj = labelNode.getAttribute().getProperty().getOwner();
					if(annotObj instanceof IShapeAttribute){
						retVal = subgraph.containsNode(((IShapeAttribute)annotObj).getCurrentDrawingElement());
					}
					else if(annotObj instanceof ILinkTerminus){
						retVal = subgraph.containsEdge(((ILinkTerminus)annotObj).getOwningLink().getCurrentDrawingElement());
					}
					else{
						retVal = subgraph.containsEdge(((ILinkAttribute)annotObj).getCurrentDrawingElement());
					}
				}
			}
		}
		return retVal;
	}

	public void copyHere(IDrawingElementSelection canvasObjectSelection) {
		if(!canCopyHere(canvasObjectSelection)) throw new IllegalArgumentException("canvasObjectSelection cannot be copied to this submodel");
		ShapeLinkSubgraph subgraph = (ShapeLinkSubgraph)canvasObjectSelection;
		super.copyHere(subgraph);
	}

	public HibModel getModel() {
		return this.getSuperGraph();
	}

	public int numDrawingElements() {
		return this.numDrawingNodes() + this.numLinkEdges();
	}
	
	public int numDrawingNodes() {
		return super.getNumNodes();
	}
	
	public int numLabelNodes() {
		int cnt = 0;
		Iterator<ILabelNode> iter = this.labelIterator();
		while(iter.hasNext()){
			iter.next() ;
			cnt++;
		}
		return cnt;
	}

	public int numLinkEdges() {
		return this.getNumEdges();
	}

	public int numShapeNodes() {
		int cnt = 0;
		Iterator<IShapeNode> iter = this.shapeNodeIterator();
		while(iter.hasNext()){
			iter.next() ;
			cnt++;
		}
		return cnt;
	}

	public Iterator<ILabelNode> labelIterator() {
		FilteredIterator<BaseCompoundNode> filteredIter = new FilteredIterator<BaseCompoundNode>(this.nodeIterator(), labelCriteria);
		return new IterationCaster<ILabelNode, BaseCompoundNode>(filteredIter);
	}

	public Iterator<ILinkEdge> linkIterator() {
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(this.edgeIterator());
	}

	public void moveHere(IDrawingElementSelection canvasObjectSelection) {
		if(!canMoveHere(canvasObjectSelection)) throw new IllegalArgumentException("canvasObjectSelection cannot be moved to this submodel");
		ShapeLinkSubgraph subgraph = (ShapeLinkSubgraph)canvasObjectSelection;
		super.moveHere(subgraph);
	}

	public Iterator<IShapeNode> shapeNodeIterator() {
		FilteredIterator<BaseCompoundNode> filteredIter = new FilteredIterator<BaseCompoundNode>(this.nodeIterator(), shapeCriteria);
		return new IterationCaster<IShapeNode, BaseCompoundNode>(filteredIter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#labelNodeFactory()
	 */
	public LabelNodeFactory labelNodeFactory() {
		return new LabelNodeFactory(this.getRootNode());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#linkEdgeFactory()
	 */
	public LinkEdgeChildFactory linkEdgeFactory() {
		return new LinkEdgeChildFactory(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#shapeNodeFactory()
	 */
	public ShapeNodeFactory shapeNodeFactory() {
		return new ShapeNodeFactory(this.getRootNode());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#canMoveHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public boolean canMoveHere(IDrawingElementSelection canvasObjectSelection) {
		boolean retVal = false;
		if(canvasObjectSelection != null) {
			ShapeLinkSubgraph subgraph = (ShapeLinkSubgraph)canvasObjectSelection;
			retVal = this.getModel().equals(subgraph.getModel())
				// don't need to test for dangling links as this is done by the superclass in method below. 
				&& super.canMoveHere(subgraph);
			// now check object types of top nodes
			Iterator<IDrawingNode> iter = canvasObjectSelection.topDrawingNodeIterator();
			while(iter.hasNext() && retVal) {
				IDrawingNode node = iter.next();
				retVal = this.getRootNode().canParent(node);
			}
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundGraph#hasPassedAdditionalValidation()
	 */
	@Override
	protected boolean hasPassedAdditionalValidation() {
		boolean retVal = true;
		if(this.rootNode != null && this.edges != null && this.getRootNode().isValid()) {
			for(BaseCompoundEdge edge : this.edges) {
				HibLinkEdge linkEdge = (HibLinkEdge)edge;
				if(!linkEdge.isValid()) {
					logger.error("LinkEdge: " + edge + "is invalid.");
					retVal = false;
					break;
				}
			}
		}
		else {
			logger.error("Node: " + rootNode + " is invalid.");
			retVal = false;
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#getCopiedElements()
	 */
	public IDrawingElementSelection getCopiedElements() {
		return (ShapeLinkSubgraph)this.getCopiedComponents();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#getMovedElements()
	 */
	public IDrawingElementSelection getMovedElements() {
		return (ShapeLinkSubgraph)this.getMovedComponents();
	}
	
	public Iterator<HibCompoundNode> allNodesIterator(){
		return new IterationCaster<HibCompoundNode, BaseCompoundNode>(super.unfilteredNodeIterator());
	}

	public Iterator<HibLinkEdge> allEdgesIterator(){
		return new IterationCaster<HibLinkEdge, BaseCompoundEdge>(super.unfilteredEdgeIterator());
	}

	public boolean areListenersEnabled() {
		return this.listenerHandler.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenerHandler.setListenersEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#drawingNodeIterator()
	 */
	public Iterator<IDrawingNode> drawingNodeIterator() {
		return new IterationCaster<IDrawingNode, BaseCompoundNode>(super.nodeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#levelOrderTraveralIterator()
	 */
	public Iterator<IDrawingNode> levelOrderTraveralIterator() {
		return new IterationCaster<IDrawingNode, BaseCompoundNode>(this.getRootNode().levelOrderIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#preOrderTraveralIterator()
	 */
	public Iterator<IDrawingNode> preOrderTraveralIterator() {
		return new IterationCaster<IDrawingNode, BaseCompoundNode>(this.getRootNode().preOrderIterator());
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundGraph#notifyCopyOperationComplete(uk.ed.inf.graph.compound.ISubCompoundGraph, uk.ed.inf.graph.compound.ISubCompoundGraph)
	 */
	@Override
	protected void notifyCopyOperationComplete(
			ISubCompoundGraph<? extends BaseCompoundNode, ? extends BaseCompoundEdge> originalSubgraph,
			ISubCompoundGraph<? extends BaseCompoundNode, ? extends BaseCompoundEdge> copiedSubgraph) {
		this.listenerHandler.notifyCopyOperationCompleted((IDrawingElementSelection)originalSubgraph, (IDrawingElementSelection)copiedSubgraph);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundGraph#notifyMoveOperationComplete(uk.ed.inf.graph.compound.ISubCompoundGraph, uk.ed.inf.graph.compound.ISubCompoundGraph)
	 */
	@Override
	protected void notifyMoveOperationComplete(
			ISubCompoundGraph<? extends BaseCompoundNode, ? extends BaseCompoundEdge> originalSubgraph,
			ISubCompoundGraph<? extends BaseCompoundNode, ? extends BaseCompoundEdge> movedSubgraph) {
		this.listenerHandler.notifyMoveOperationCompleted((IDrawingElementSelection)originalSubgraph, (IDrawingElementSelection)movedSubgraph);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#addModelChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener)
	 */
	public void addModelChangeListener(IModelChangeListener listener) {
		this.listenerHandler.addModelChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#modelChangeListenerIterator()
	 */
	public Iterator<IModelChangeListener> modelChangeListenerIterator() {
		return this.listenerHandler.modelChangeListenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#removeModelChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener)
	 */
	public void removeModelChangeListener(IModelChangeListener listener) {
		this.listenerHandler.removeModelChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundGraph#notifyNewEdge(uk.ed.inf.graph.compound.base.BaseCompoundEdge)
	 */
	@Override
	public void notifyNewEdge(BaseCompoundEdge newEdge) {
		ISelectionFactory fact = this.getModel().newSelectionFactory();
		fact.addLink((ILinkEdge)newEdge);
		this.listenerHandler.notifyNewEdge(fact.createGeneralSelection());
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundGraph#notifyNewNode(uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	public void notifyNewNode(BaseCompoundNode newNode) {
		ISelectionFactory fact = this.getModel().newSelectionFactory();
		fact.addDrawingNode((IDrawingNode)newNode);
		this.listenerHandler.notifyNewNode(fact.createGeneralSelection());
	}
}

