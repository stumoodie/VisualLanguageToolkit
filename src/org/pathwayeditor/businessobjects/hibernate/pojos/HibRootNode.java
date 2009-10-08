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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;

/**
 * @author nhanlon
 *
 */
public class HibRootNode extends HibCompoundNode implements IRootNode {
	private HibRootAttribute canvasAttribute = null;
	
	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibRootNode() {
		super();
	}

	/**
	 * Constructor that creates a new instance of the root node. 
	 * @param model
	 * @param nodeIdx
	 */
	public HibRootNode(HibModel model, int nodeIdx, HibRootAttribute rootAttribute) {
		super(model, null, nodeIdx);
		this.canvasAttribute = rootAttribute;
		this.canvasAttribute.setRootNode(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getAttribute()
	 */
	public HibRootAttribute getAttribute() {
		return this.canvasAttribute;
	}

	void setAttribute(HibRootAttribute rootAttribute) {
		this.canvasAttribute = rootAttribute;
	}
	
	public HibRootNode getParentNode() {
		return this;
	}
	
//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getObjectType()
//	 */
//	public IRootObjectType getObjectType() {
//		return this.canvasAttribute.getObjectType();
//	}

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
		// will never be removed so do nothing.
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode#isValid()
	 */
	@Override
	public boolean isValid() {
		return this.canvasAttribute != null && this.canvasAttribute.isValid()
			// althought the compound graph expects the root node to return a reference to itself
			// the hibernate schema expects the parent to have a root that it null.
			&& super.getHibParentNode() == null;
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
		if(possibleChild != null && !this.equals(possibleChild)){
			if(possibleChild instanceof ILabelNode){
				// test if the label belongs to a drawing element help in the submode
				// of this node (required for link labels)
				ILabelAttribute labelAttrib = (ILabelAttribute)possibleChild.getAttribute();
				retVal = this.getSubModel().equals(labelAttrib.getProperty().getOwner().getLabelSubModel());
			}
			else{
				ITypedDrawingNode typedNode = (ITypedDrawingNode)possibleChild;
				if(typedNode.getAttribute().getObjectType() != null && this.getAttribute().getObjectType() != null){
					IRootObjectParentingRules rules = this.getAttribute().getObjectType().getParentingRules();
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
		if(this.getAttribute().getObjectType() != null){
			IRootObjectParentingRules rules = this.getAttribute().getObjectType().getParentingRules();
			retVal = rules.isValidChild(childType);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#isValidChild(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)
	 */
	public boolean isValidChildOf(IDrawingNode possibleParent) {
		boolean retVal = false;
		if(possibleParent != null){
			if(possibleParent instanceof ITypedDrawingNode){
				ITypedDrawingNode typedNode = (ITypedDrawingNode)possibleParent;
				if(typedNode.getAttribute().getObjectType() != null){
					IObjectTypeParentingRules rules = typedNode.getAttribute().getObjectType().getParentingRules();
					retVal = rules.isValidChild(this.getAttribute().getObjectType());
				}
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
