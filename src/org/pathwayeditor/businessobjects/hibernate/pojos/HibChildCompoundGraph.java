package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.compoundgraph.HibChildCompoundEdgeFactory;
import org.pathwayeditor.businessobjects.compoundgraph.HibCompoundGraphCopyBuilder;
import org.pathwayeditor.businessobjects.compoundgraph.HibCompoundNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph;
import org.pathwayeditor.businessobjects.drawingprimitives.ICompoundGraph;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.util.IDirectedEdgeSet;
import uk.ed.inf.graph.util.impl.DirectedEdgeSet;
import uk.ed.inf.graph.util.impl.NodeSet;

public class HibChildCompoundGraph extends BaseChildCompoundGraph implements IChildCompoundGraph {
	private HibCompoundNodeFactory nodeFactory;
	private HibChildCompoundEdgeFactory edgeFactory;
	private Long id;
	private HibCompoundNode rootNode;
	private IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> edges = new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>();

	HibChildCompoundGraph() {
		super(new HibCompoundGraphCopyBuilder());
		this.rootNode = null;
		this.nodeFactory = null;
		this.edgeFactory = null;
		this.createNodeSet(new NodeSet<BaseCompoundNode, BaseCompoundEdge>());
		this.createEdgeSet(this.edges);
	}

	public HibChildCompoundGraph(HibCompoundNode rootNode) {
		this();
		this.rootNode = rootNode;
		this.nodeFactory = new HibCompoundNodeFactory(this.getRootNode());
		this.edgeFactory = new HibChildCompoundEdgeFactory(this.getRootNode());
	}

	@Override
	public HibCompoundGraph getSuperGraph(){
		return (HibCompoundGraph)super.getSuperGraph();
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
	}

	IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> getEdges() {
		return this.edges;
	}

//	public void addEdge(HibLinkEdge newEdge) {
//		if (newEdge == null)
//			throw new IllegalArgumentException("newEdge cannot be null");
//
//		HibChildCompoundGraph oldOwningChildGraph = newEdge.getOwningChildGraph();
//		if (oldOwningChildGraph != null) {
//			oldOwningChildGraph.edges.remove(newEdge);
//		}
//		this.edges.add(newEdge);
//		newEdge.setOwningChildGraph(this);
//	}

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
		if (!(obj instanceof HibChildCompoundGraph))
			return false;
		final HibChildCompoundGraph other = (HibChildCompoundGraph) obj;
		if (rootNode == null) {
			if (other.rootNode != null)
				return false;
		} else if (!rootNode.equals(other.rootNode))
			return false;
		return true;
	}

	@Override
	public HibChildCompoundEdgeFactory edgeFactory() {
		if(this.edgeFactory == null){
			this.edgeFactory = new HibChildCompoundEdgeFactory(this.getRootNode());
		}
		return this.edgeFactory;
	}

	@Override
	public HibCompoundNodeFactory nodeFactory() {
		if(this.nodeFactory == null){
			this.nodeFactory = new HibCompoundNodeFactory(this.getRootNode());
		}
		return this.nodeFactory;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#canCopyHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public boolean canCopyHere(ICanvasObjectSelection canvasObjectSelection) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#canCreateShape(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public boolean canCreateShape(IShapeObjectType newShapeType) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#canMoveHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public boolean canMoveHere(ICanvasObjectSelection canvasObjectSelection) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#copyHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public void copyHere(ICanvasObjectSelection canvasObjectSelection) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#createShape(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public IShapeAttribute createShape(IShapeObjectType shapeType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#getChildCompoundGraph()
	 */
	public IChildCompoundGraph getChildCompoundGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#getGraph()
	 */
	public ICompoundGraph getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#getNumLabels()
	 */
	public int getNumLabels() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#getNumLinks()
	 */
	public int getNumLinks() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#getNumShapes()
	 */
	public int getNumShapes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#labelIterator()
	 */
	public Iterator<ILabelAttribute> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#linkIterator()
	 */
	public Iterator<ILinkAttribute> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#moveHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection)
	 */
	public void moveHere(ICanvasObjectSelection canvasObjectSelection) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph#shapeIterator()
	 */
	public Iterator<IShapeAttribute> shapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}

