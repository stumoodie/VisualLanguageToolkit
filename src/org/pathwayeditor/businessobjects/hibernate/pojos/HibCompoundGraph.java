package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.Iterator;

import org.pathwayeditor.businessobjects.compoundgraph.HibCompoundEdgeFactory;
import org.pathwayeditor.businessobjects.compoundgraph.HibCompoundGraphCopyBuilder;
import org.pathwayeditor.businessobjects.compoundgraph.HibCompoundNodeFactory;
import org.pathwayeditor.businessobjects.compoundgraph.HibSubCompoundGraphBuilder;
import org.pathwayeditor.businessobjects.compoundgraph.HibSubCompoundGraphFactory;
import org.pathwayeditor.businessobjects.contextadapter.IContextAdapterServiceProvider;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph;
import org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObjectNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.pojos.GraphModelState;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.base.BaseCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.util.IndexCounter;
import uk.ed.inf.tree.GeneralTree;
import uk.ed.inf.tree.ITree;

public class HibCompoundGraph extends BaseCompoundGraph implements ICompoundGraph, Serializable {
	private static final long serialVersionUID = 6646425760947242284L;
	private static final int ROOT_NODE_INDEX = 0;
	private HibCompoundNodeFactory nodeFactory;
	private final HibCompoundEdgeFactory edgeFactory;
	private final HibSubCompoundGraphFactory subgraphFactory;
	private ITree<BaseCompoundNode> tree = null;
	private Long id;
	private String name; 
	private HibCompoundNode rootNode;
//	private int lastNodeIndex = 0;
//	private int lastEdgeIndex = 0;
	private IndexCounter nodeCntr = new IndexCounter();
	private IndexCounter edgeCntr = new IndexCounter();
	
	HibCompoundGraph() {
		super(new HibCompoundGraphCopyBuilder());
		this.name = null;
		this.nodeFactory = null;
		this.edgeFactory = new HibCompoundEdgeFactory(this);
		this.subgraphFactory = new HibSubCompoundGraphFactory(new HibSubCompoundGraphBuilder(this));
	}
	
	public HibCompoundGraph(String name) {
		this();
		this.name = name;
		this.rootNode = new HibCompoundNode(this, ROOT_NODE_INDEX);
		this.nodeFactory = new HibCompoundNodeFactory(this.getRootNode());
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

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
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
	}
	
	public HibCompoundNode getRootNode() {
		return rootNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibCompoundGraph))
			return false;
		final HibCompoundGraph other = (HibCompoundGraph) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public HibCompoundEdgeFactory edgeFactory() {
		return this.edgeFactory;
	}

	@Override
	protected IndexCounter getEdgeCounter() {
		if(this.edgeCntr == null){
			this.edgeCntr = new IndexCounter(this.getLastEdgeIndex());
		}
		return this.edgeCntr;
	}

	@Override
	protected IndexCounter getNodeCounter() {
		if(this.nodeCntr == null){
			this.nodeCntr = new IndexCounter(this.getLastNodeIndex());
		}
		return this.nodeCntr;
	}

	@Override
	protected ITree<BaseCompoundNode> getNodeTree() {
		if(this.tree == null){
			this.tree = new GeneralTree<BaseCompoundNode>(this.getRootNode());
		}

		return this.tree;
	}

	@Override
	protected int getRootNodeIndex() {
		return ROOT_NODE_INDEX;
	}

	@Override
	public HibCompoundNodeFactory nodeFactory() {
		if(this.nodeFactory == null){
			this.nodeFactory = new HibCompoundNodeFactory(this.getRootNode());
		}
		return this.nodeFactory;
	}

	public IGraphMomento getMomento(){
		return new GraphModelState(super.getCurrentState());
	}
	
	public HibSubCompoundGraphFactory subgraphFactory() {
		return this.subgraphFactory;
	}
	
	@Override
	public HibCompoundNode getNode(int index){
		return (HibCompoundNode)super.getNode(index);
	}

	@Override
	public HibLinkEdge getEdge(int index){
		return (HibLinkEdge)super.getEdge(index);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#canCreateLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute, org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute)
	 */
	public boolean canCreateLink(ILinkObjectType linkObjectType,
			IShapeAttribute srcShape, IShapeAttribute tgtShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#createCopy(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public ICompoundGraph createCopy(ICanvas newCanvas) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#createLink(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType, org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute, org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute)
	 */
	public ILinkAttribute createLink(ILinkObjectType linkObjectType,
			IShapeAttribute srcShape, IShapeAttribute tgtShape) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#getContextAdapterServiceProvider()
	 */
	public IContextAdapterServiceProvider getContextAdapterServiceProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#getNumLabels()
	 */
	public int getNumLabels() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#getNumLinks()
	 */
	public int getNumLinks() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#getNumShapes()
	 */
	public int getNumShapes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#getRootObject()
	 */
	public IRootObjectNode getRootObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#labelIterator()
	 */
	public Iterator<ILabelAttribute> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#linkIterator()
	 */
	public Iterator<ILinkAttribute> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#removeSubgraph(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public void removeSubgraph(ICanvasObjectSelection selection) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#restoreToState(org.pathwayeditor.businessobjects.drawingprimitives.IGraphState)
	 */
	public void restoreToState(IGraphMomento stateToRestore) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph#shapeIterator()
	 */
	public Iterator<IShapeAttribute> shapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
