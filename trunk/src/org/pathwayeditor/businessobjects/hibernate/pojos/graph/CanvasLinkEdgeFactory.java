/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubModel;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;

/**
 * Creates an edge between any two nodes on the canvas.
 * @author smoodie
 *
 */
public class CanvasLinkEdgeFactory extends BaseCompoundEdgeFactory implements ILinkEdgeFactory {
	private final HibModel canvas;
	private final IHibNotationFactory hibNotationFactory; 
	private ILinkObjectType objectType;
	private HibShapeNode outNode;
	private HibShapeNode inNode;
	
	public CanvasLinkEdgeFactory(HibModel canvas, IHibNotationFactory hibNotationFactory) {
		super();
		this.canvas = canvas;
		this.hibNotationFactory = hibNotationFactory;
	}

	public void setObjectType(ILinkObjectType objectType){
		this.objectType = objectType;
	}
	
	/**
	 * @param outNode the source HibShapeNode of the link
	 * @param inNode the target HibShapeNode of the link
	 * @throws ClassCastException if outNode or inNode ar not of type HibShapeNode.
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory#setPair(BaseCompoundNode, BaseCompoundNode)
	 */
	@Override
	public void setPair(BaseCompoundNode outNode, BaseCompoundNode inNode){
		if(outNode == null || inNode == null) throw new NullPointerException("outNode and inNode cannot be null");
		if(!(outNode instanceof HibShapeNode) || !(inNode instanceof HibShapeNode)){
			throw new ClassCastException("parameters outNode and inNode must both be of type IShapeNode");
		}
		this.outNode = (HibShapeNode)outNode;
		this.inNode = (HibShapeNode)inNode;
	}
	
	/**
	 * Tests if an edge can be created given the current state of the factory.
	 * A new edge (in our case a LinkEdge) can be created if the the shape node pair is valid and
	 * can form a link of the specified link object type.
	 * @returns true if the above conditions are true, false otherwise.
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory#canCreateEdge()
	 */
	@Override
	public boolean canCreateEdge(){
		boolean retVal = false;
		if(this.objectType != null){
			ILinkConnectionRules connectionRules = this.objectType.getLinkConnectionRules();
			retVal = connectionRules.isValidTarget(this.outNode.getAttribute().getObjectType(), this.inNode.getAttribute().getObjectType());
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory#newEdge(uk.ed.inf.graph.compound.base.BaseChildCompoundGraph, int, uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected HibLinkEdge newEdge(BaseChildCompoundGraph owningGraph,
			int edgeIndex, BaseCompoundNode outNode, BaseCompoundNode inNode) {
		HibObjectType hibObjectType = this.hibNotationFactory.getObjectType(this.objectType);
		HibCanvas canvas = ((HibSubModel)owningGraph).getModel().getCanvas();
		int edgeCreationSerial = canvas.getAttributeSerialCounter().nextIndex();
		HibLinkAttribute linkAttribute = new HibLinkAttribute(canvas, edgeCreationSerial, this.objectType, hibObjectType);
		return new HibLinkEdge((HibSubModel)owningGraph, edgeIndex, (HibShapeNode)outNode, (HibShapeNode)inNode, linkAttribute);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory#getInNode()
	 */
	@Override
	protected HibShapeNode getInNode() {
		return this.inNode;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory#getOutNode()
	 */
	@Override
	protected HibShapeNode getOutNode() {
		return this.outNode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#createLinkEdge()
	 */
	public HibLinkEdge createLinkEdge() {
		return (HibLinkEdge)this.createEdge();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#getCurrentObjectType()
	 */
	public ILinkObjectType getCurrentObjectType() {
		return this.objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#getOwningSubCanvas()
	 */
	public ISubModel getOwningSubCanvas() {
		return this.getOwningChildGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#getShapeNodePair()
	 */
	public ShapeNodePair getShapeNodePair() {
		return this.getCurrentNodePair();
	}

	/**
	 * Checks that the node pair is valid. Here this means that both nodes must be non-null, of type HibShapeNode
	 * and belong to the same graph as this factory.
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#isValidShapeNodePair(IShapeNode, IShapeNode)
	 */
	public boolean isValidShapeNodePair(IShapeNode source, IShapeNode target) {
		return this.isValidNodePair(outNode, inNode);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#setNodePair(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode, org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public void setShapeNodePair(IShapeNode source, IShapeNode target) {
		this.setShapeNodePair(source, target);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory#getCurrentNodePair()
	 */
	@Override
	public ShapeNodePair getCurrentNodePair() {
		return new ShapeNodePair(this.outNode, this.inNode);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory#getGraph()
	 */
	@Override
	public HibModel getGraph() {
		return this.canvas;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.ICompoundEdgeFactory#getOwningChildGraph()
	 */
	public HibSubModel getOwningChildGraph() {
		return (HibSubModel)super.getOwningChildGraph();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.basic.IBasicEdgeFactory#isValidNodePair(uk.ed.inf.graph.basic.IBasicNode, uk.ed.inf.graph.basic.IBasicNode)
	 */
	public boolean isValidNodePair(BaseCompoundNode outNode, BaseCompoundNode inNode) {
		return outNode != null && inNode != null && outNode instanceof HibShapeNode
		&& inNode instanceof HibShapeNode && outNode.getGraph().equals(this.getGraph())
		&& inNode.getGraph().equals(outNode.getGraph());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#canCreateLink()
	 */
	public boolean canCreateLink() {
		return this.canCreateEdge();
	}

}
