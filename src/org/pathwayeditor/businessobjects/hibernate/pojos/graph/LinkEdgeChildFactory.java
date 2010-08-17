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
/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubModel;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.ICompoundNode;
import uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;

/**
 * @author smoodie
 *
 */
public class LinkEdgeChildFactory extends BaseChildCompoundEdgeFactory implements ILinkEdgeFactory {
	private final HibSubModel subModel;
	private HibShapeNode outNode = null;
	private HibShapeNode inNode = null;
	private ILinkObjectType objectType;
	private IHibNotationFactory hibNotationFactory;
	private HibLinkAttribute attribute;
	
	public LinkEdgeChildFactory(HibSubModel subCanvas) {
		super();
		this.subModel = subCanvas;
		this.hibNotationFactory = subCanvas.getModel().getHibNotationFactory();
		this.objectType = null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory#newEdge(uk.ed.inf.graph.compound.base.BaseChildCompoundGraph, int, uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected BaseCompoundEdge newEdge(BaseChildCompoundGraph owningChildGraph,
			int edgeIndex, BaseCompoundNode outNode, BaseCompoundNode inNode) {
		HibLinkAttribute linkAttribute = this.attribute;
		if(linkAttribute == null){
			HibObjectType hibObjectType = this.hibNotationFactory.getObjectType(this.getCurrentObjectType().getUniqueId());
			Canvas canvas = ((HibSubModel)owningChildGraph).getModel().getCanvas();
			int edgeCreationSerial = canvas.getCreationSerialCounter().nextIndex();
			HibLinkTerminus srcTerm = new HibLinkTerminus(canvas, canvas.getCreationSerialCounter().nextIndex(), LinkTermType.SOURCE, objectType.getSourceTerminusDefinition());
			HibLinkTerminus tgtTerm = new HibLinkTerminus(canvas, canvas.getCreationSerialCounter().nextIndex(), LinkTermType.TARGET, objectType.getTargetTerminusDefinition());
			linkAttribute = new HibLinkAttribute(canvas, edgeCreationSerial, this.getCurrentObjectType(), hibObjectType, srcTerm, tgtTerm);
		}
		HibLinkEdge retVal = new HibLinkEdge((HibSubModel)owningChildGraph, edgeIndex, (HibShapeNode)outNode, (HibShapeNode)inNode, linkAttribute);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory#getInNode()
	 */
	@Override
	protected BaseCompoundNode getInNode() {
		return this.inNode;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory#getOutNode()
	 */
	@Override
	protected BaseCompoundNode getOutNode() {
		return this.outNode;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory#setPair(uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	public void setPair(ICompoundNode outNode, ICompoundNode inNode) {
		if(!(outNode instanceof HibShapeNode) || !(inNode instanceof HibShapeNode)){
			throw new ClassCastException("outNode and inNode must both be of type HibShapeNode");
		}
		this.outNode = (HibShapeNode)outNode;
		this.inNode = (HibShapeNode)inNode;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory#canCreateEdge()
	 */
	@Override
	public boolean canCreateEdge() {
		boolean retVal = false;
		ILinkObjectType testOt = this.getCurrentObjectType();
		if(testOt != null){
			IShapeObjectType outOt = this.outNode.getAttribute().getObjectType(); 
			IShapeObjectType inOt = this.inNode.getAttribute().getObjectType(); 
			retVal = testOt.getLinkConnectionRules().isValidTarget(outOt, inOt);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory#getCurrentNodePair()
	 */
	@Override
	public ShapeNodePair getCurrentNodePair() {
		return new ShapeNodePair(this.outNode, this.inNode);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory#getGraph()
	 */
	@Override
	public HibModel getGraph() {
		return this.subModel.getSuperGraph();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory#getOwningChildGraph()
	 */
	@Override
	public HibSubModel getOwningChildGraph() {
		return this.subModel;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#canCreateLink()
	 */
	public boolean canCreateLink() {
		return this.canCreateEdge();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#createLinkEdge()
	 */
	public HibLinkEdge createLinkEdge() {
		return (HibLinkEdge)super.createEdge();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#getCurrentObjectType()
	 */
	public ILinkObjectType getCurrentObjectType() {
		return this.attribute == null ? this.objectType : this.attribute.getObjectType();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#getOwningSubCanvas()
	 */
	public HibSubModel getOwningSubCanvas() {
		return this.subModel;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#getShapeNodePair()
	 */
	public ShapeNodePair getShapeNodePair() {
		return this.getCurrentNodePair();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#isValidShapeNodePair(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode, org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public boolean isValidShapeNodePair(IShapeNode source, IShapeNode target) {
		boolean retVal = false;
		if(source != null && source instanceof HibShapeNode
				&& target != null && target instanceof HibShapeNode){
			retVal = super.isValidBaseNodePair((HibShapeNode)source, (HibShapeNode)target); 
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#setObjectType(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType)
	 */
	public void setObjectType(ILinkObjectType objectType) {
		this.objectType = objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory#setShapeNodePair(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode, org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public void setShapeNodePair(IShapeNode source, IShapeNode target) {
		if(!this.isValidShapeNodePair(source, target)) throw new IllegalArgumentException("invalid parameters");
		this.outNode = (HibShapeNode)source;
		this.inNode = (HibShapeNode)target;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.basic.IBasicEdgeFactory#isValidNodePair(uk.ed.inf.graph.basic.IBasicNode, uk.ed.inf.graph.basic.IBasicNode)
	 */
	public boolean isValidNodePair(ICompoundNode outNode, ICompoundNode inNode) {
		boolean retVal = false;
		if(super.isValidBaseNodePair(outNode, inNode)){
			retVal = outNode instanceof HibShapeNode && inNode instanceof HibShapeNode;
		}
		return retVal;
	}

	/**
	 * @param attribute
	 */
	public void setAttribute(HibLinkAttribute attribute) {
		this.attribute = attribute;
	}
}
