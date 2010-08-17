package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IParentingValidator;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

public abstract class TypedCommonNode extends CommonNode implements ITypedDrawingNode {

	public TypedCommonNode(ICompoundNode compoundNode){
		super(compoundNode);
	}
	
	@Override
	public final IParentingValidator getParentingValidator(){
		return new IParentingValidator(){

			@Override
			public boolean canReparentTo(ILabelNode possibleChild) {
				return true;
			}

			@Override
			public boolean canReparentTo(ITypedDrawingNode possibleChild) {
				boolean retVal = false;
				if(possibleChild != null){
					retVal = getAttribute().getObjectType().getParentingRules().isValidChild(possibleChild.getAttribute().getObjectType());
				}
				return retVal;
			}

			@Override
			public boolean canReparentTo(ILinkEdge possibleChild) {
				return true;
			}

			@Override
			public boolean isValidChildOf(ILabelNode possibleParent) {
				return false;
			}

			@Override
			public boolean isValidChildOf(ITypedDrawingNode possibleParent) {
				boolean retVal = false;
				if(possibleParent != null){
					retVal = possibleParent.getAttribute().getObjectType().getParentingRules().isValidChild(getAttribute().getObjectType());
				}
				return retVal;
			}

			@Override
			public boolean isValidChildOf(ILinkEdge possibleParent) {
				return false;
			}
			
		};
	}

	@Override
	public final boolean canParent(IDrawingElement possibleChild) {
			return possibleChild.getParentingValidator().isValidChildOf(this);
	//		boolean retVal = false;
	//		if(possibleChild != null && !this.equals(possibleChild)){
	//			if(possibleChild instanceof ILabelNode){
	//				// test if the label belongs to a drawing element help in the submode
	//				// of this node (required for link labels)
	//				ILabelAttribute labelAttrib = (ILabelAttribute)possibleChild.getAttribute();
	//				retVal = this.getSubModel().equals(labelAttrib.getProperty().getOwner().getLabelSubModel());
	//			}
	//			else{
	//				ITypedDrawingNode typedNode = (ITypedDrawingNode)possibleChild;
	//				if(typedNode.getAttribute().getObjectType() != null && this.getAttribute().getObjectType() != null){
	//					IObjectTypeParentingRules rules = getAttribute().getObjectType().getParentingRules();
	//					retVal = rules.isValidChild(typedNode.getAttribute().getObjectType());
	//				}
	//			}
	//		}
	//		return retVal;
		}

	@Override
	public final boolean canParent(IObjectType childType) {
		boolean retVal = false;
		if(this.getAttribute().getObjectType() != null){
			IObjectTypeParentingRules rules = getAttribute().getObjectType().getParentingRules();
			retVal = rules.isValidChild(childType);
		}
		return retVal;
	}

	@Override
	public final boolean isValidChildOf(IDrawingElement possibleParent) {
			boolean retVal = false;
			if(possibleParent != null){
				retVal = possibleParent.getParentingValidator().canReparentTo(this);
			}
			return retVal;
	//		boolean retVal = false;
	//		if(possibleParent != null && (possibleParent instanceof ITypedDrawingNode)){
	//			ITypedDrawingNode typedNode = (ITypedDrawingNode)possibleParent;
	//			if(typedNode.getAttribute().getObjectType() != null){
	//				IObjectTypeParentingRules rules = typedNode.getAttribute().getObjectType().getParentingRules();
	//				retVal = rules.isValidChild(this.getAttribute().getObjectType());
	//			}
	//		}
	//		return retVal;
		}

	@Override
	public final boolean isValidChildOf(IObjectType parentType) {
		return getAttribute().getObjectType().getParentingRules().isValidChild(parentType);
	}

}
