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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;

/**
 * @author nhanlon
 *
 */
public class HibShapeNode extends HibCompoundNode implements IShapeNode {
	private HibShapeAttribute shapeAttribute ;
//	private ListenableNodeStructureChangeItem listenable = new ListenableNodeStructureChangeItem(this); 
	
	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibShapeNode(){
		super();
	}
	
	/**
	 * Constructor to be used by application code.
	 * @param parentNode
	 * @param nodeIndex
	 * @param shapeAttribute
	 */
	public HibShapeNode(HibCompoundNode parentNode, int nodeIndex, HibShapeAttribute shapeAttribute){
		super(parentNode.getGraph(), parentNode, nodeIndex);
		if(parentNode == null || shapeAttribute == null) throw new IllegalArgumentException("parentNode and shapeAttribute cannot be null");
		this.shapeAttribute = shapeAttribute;
		this.shapeAttribute.setCurrentShapeNode(this);
		if(!parentNode.canParent(shapeAttribute.getObjectType())) {
			// root node has null parent
			throw new IllegalArgumentException("This not is not a valid child of it's parent: " + parentNode);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getChildModel()
	 */
	public HibSubModel getSubModel() {
		return this.getChildCompoundGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getAttribute()
	 */
	public HibShapeAttribute getAttribute() {
		return this.shapeAttribute;
	}

	public void setAttribute(HibShapeAttribute shapeAttribute) {
		this.shapeAttribute = shapeAttribute;
	}
	
//	public void changeAttribute(HibShapeAttribute newShapeAttribute){
//		if(this.shapeAttribute != null){
//			this.shapeAttribute.setShapeNode(null);
//		}
//		if(newShapeAttribute != null){
//			HibShapeNode oldShape = newShapeAttribute.getShapeNode(); 
//			if(oldShape != null){
//				oldShape.setAttribute(null);
//			}
//			newShapeAttribute.setShapeNode(this);
//		}
//		this.shapeAttribute = newShapeAttribute;
//	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getObjectType()
//	 */
//	public IShapeObjectType getObjectType() {
//		return this.shapeAttribute.getObjectType();
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getNumSourceLinks()
	 */
	public int getNumSourceLinks() {
		return this.getOutDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getNumTargetLinks()
	 */
	public int getNumTargetLinks() {
		return this.getInDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#sourceLinkIterator()
	 */
	public Iterator<ILinkEdge> sourceLinkIterator() {
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(this.getOutEdgeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#targetLinkIterator()
	 */
	public Iterator<ILinkEdge> targetLinkIterator() {
		return new IterationCaster<ILinkEdge, BaseCompoundEdge>(this.getInEdgeIterator());
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(model=");
		builder.append(this.getModel());
		builder.append(", index=");
		builder.append(this.getIndex());
		builder.append(", removed=");
		builder.append(this.isRemoved());
		builder.append(", attribute=");
		builder.append(this.getAttribute());
		builder.append(")");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNode#removalAction()
	 */
	@Override
	protected void removalAction(boolean removed) {
		if(!removed){
			this.shapeAttribute.setCurrentShapeNode(this);
		}
	}

//	public void notifySourceEdgeChange(ModelStructureChangeType type, HibLinkEdge hibLinkEdge) {
//		listenable.notifySourceNodeStructureChange(type, hibLinkEdge);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#addNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListener)
//	 */
//	public void addNodeChangeListener(INodeChangeListener listener) {
//		this.listenable.addNodeChangeListener(listener);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#nodeChangeListenerIterator()
//	 */
//	public Iterator<INodeChangeListener> nodeChangeListenerIterator() {
//		return this.listenable.nodeChangeListenerIterator();
//	}
//
//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#removeNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListener)
//	 */
//	public void removeNodeChangeListener(INodeChangeListener listener) {
//		this.listenable.removeNodeChangeListener(listener);
//	}

//	/**
//	 * @param type
//	 * @param hibLinkEdge
//	 */
//	public void notifyTargetEdgeChange(ModelStructureChangeType type, HibLinkEdge hibLinkEdge) {
//		listenable.notifyTargetNodeStructureChange(type, hibLinkEdge);
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode#isValid()
	 */
	@Override
	public boolean isValid() {
		return this.shapeAttribute != null && this.shapeAttribute.isValid();
	}
	
	public ITypedDrawingNode getParentNode() {
		return (ITypedDrawingNode)this.getHibParentNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLabelSubModel()
	 */
	public ISubModel getLabelSubModel() {
		return this.getSubModel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#canParent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)
	 */
	public boolean canParent(IDrawingNode possibleChild) {
		boolean retVal = false;
		// check it is not null and that it is not this node!
		if(possibleChild != null && !this.equals(possibleChild)){
			if(possibleChild instanceof ILabelNode){
				ILabelAttribute labelAttrib = (ILabelAttribute)possibleChild.getAttribute();
				// test if the label node shares an annotation prop with this shape
				retVal = this.getAttribute().containsProperty(labelAttrib.getProperty())
				// test if the label belongs to a drawing element help in the submode
				// of this node (required for link labels)
					|| this.getSubModel().equals(labelAttrib.getProperty().getOwner().getLabelSubModel());
			}
			else{
				ITypedDrawingNode typedNode = (ITypedDrawingNode)possibleChild;
				if(this.getAttribute().getObjectType() != null && typedNode.getAttribute().getObjectType() != null){
					IObjectTypeParentingRules rules = this.getAttribute().getObjectType().getParentingRules();
					retVal = rules.isValidChild(typedNode.getAttribute().getObjectType());
				}
			}
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#canParent(org.pathwayeditor.businessobjects.typedefn.INodeObjectType)
	 */
	public boolean canParent(INodeObjectType childType) {
		boolean retVal = false;
		// Need to check that object type has been set in which case we return false
		if(this.getAttribute().getObjectType() != null){
			retVal = this.getAttribute().getObjectType().getParentingRules().isValidChild(childType);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#isValidChild(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)
	 */
	public boolean isValidChildOf(IDrawingNode possibleParent) {
		boolean retVal = false;
		if(possibleParent instanceof ITypedDrawingNode && this.getAttribute().getObjectType() != null){
			ITypedDrawingNode typedNode = (ITypedDrawingNode)possibleParent;
			// Need to check that object type has been set in which case we return false
			if(typedNode.getAttribute().getObjectType() != null){
				IObjectTypeParentingRules rules = typedNode.getAttribute().getObjectType().getParentingRules();
				retVal = rules.isValidChild(this.getAttribute().getObjectType());
			}
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#isValidChild(org.pathwayeditor.businessobjects.typedefn.INodeObjectType)
	 */
	public boolean isValidChildOf(INodeObjectType parentType) {
		boolean retVal = false;
		if(parentType != null){
			retVal = parentType.getParentingRules().isValidChild(this.getAttribute().getObjectType());
		}
		return retVal;
	}
}
