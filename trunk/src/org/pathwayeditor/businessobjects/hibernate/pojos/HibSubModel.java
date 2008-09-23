package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.CompoundGraphCopyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.LabelNodeFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.LinkEdgeChildFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeLinkSubgraph;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeLinkSubgraphFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.ShapeNodeFactory;

import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.util.IDirectedEdgeSet;
import uk.ed.inf.graph.util.IFilterCriteria;
import uk.ed.inf.graph.util.impl.DirectedEdgeSet;
import uk.ed.inf.graph.util.impl.FilteredIterator;
import uk.ed.inf.graph.util.impl.NodeSet;

public class HibSubModel extends BaseChildCompoundGraph implements ISubModel {
	private Long id;
	private HibCompoundNode rootNode;
	private IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> edges = new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>();
	private final IFilterCriteria<BaseCompoundNode> labelCriteria;
	private final IFilterCriteria<BaseCompoundNode> shapeCriteria;

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibSubModel() {
		super(new CompoundGraphCopyBuilder());
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
	}

	@Override
	public HibModel getSuperGraph(){
		return (HibModel)super.getSuperGraph();
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
	public boolean canCopyHere(ICanvasObjectSelection canvasObjectSelection) {
		ShapeLinkSubgraphFactory fact = (ShapeLinkSubgraphFactory)canvasObjectSelection;
		ShapeLinkSubgraph subgraph = fact.createInducedSubgraph();
		return this.canCopyHere(subgraph);
	}

	public void copyHere(ICanvasObjectSelection canvasObjectSelection) {
		ShapeLinkSubgraphFactory fact = (ShapeLinkSubgraphFactory)canvasObjectSelection;
		ShapeLinkSubgraph subgraph = fact.createInducedSubgraph();
		this.copyHere(subgraph);
	}

	public HibModel getModel() {
		return this.getSuperGraph();
	}

	public int getNumLabels() {
		int cnt = 0;
		Iterator<ILabelNode> iter = this.labelIterator();
		while(iter.hasNext()){
			cnt++;
		}
		return cnt;
	}

	public int getNumLinks() {
		return this.getNumEdges();
	}

	public int getNumShapes() {
		int cnt = 0;
		Iterator<IShapeNode> iter = this.shapeIterator();
		while(iter.hasNext()){
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

	public void moveHere(ICanvasObjectSelection canvasObjectSelection) {
		ShapeLinkSubgraphFactory fact = (ShapeLinkSubgraphFactory)canvasObjectSelection;
		ShapeLinkSubgraph subgraph = fact.createInducedSubgraph();
		this.moveHere(subgraph);
	}

	public Iterator<IShapeNode> shapeIterator() {
		FilteredIterator<BaseCompoundNode> filteredIter = new FilteredIterator<BaseCompoundNode>(this.nodeIterator(), shapeCriteria);
		return new IterationCaster<IShapeNode, BaseCompoundNode>(filteredIter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#labelNodeFactory()
	 */
	public ILabelNodeFactory labelNodeFactory() {
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
	public boolean canMoveHere(ICanvasObjectSelection canvasObjectSelection) {
		ShapeLinkSubgraphFactory fact = (ShapeLinkSubgraphFactory)canvasObjectSelection;
		ShapeLinkSubgraph subgraph = fact.createInducedSubgraph();
		return this.canMoveHere(subgraph);
	}

}

