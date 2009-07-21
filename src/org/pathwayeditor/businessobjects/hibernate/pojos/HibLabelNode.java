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

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

/**
 * @author nhanlon
 *
 */
public class HibLabelNode extends HibCompoundNode implements ILabelNode {
	private HibLabelAttribute labelAttribute;
	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibLabelNode(){
		super();
	}
	
	
	public HibLabelNode(HibCompoundNode parent, int index, HibProperty property){
		super(parent.getModel(), parent, index);
		if(parent == null || property == null) throw new IllegalArgumentException("parameters parent and property cannot be null");
		ILabelAttributeDefaults labelAttributeDefaults = property.getDefinition().getLabelDefaults(); 
		int newSerial = this.getModel().getCanvas().getCreationSerialCounter().nextIndex();
//		this.changeLabelAttribute(new HibLabelAttribute(this.getModel().getCanvas(), newSerial, property, labelAttributeDefaults));
		this.labelAttribute = new HibLabelAttribute(this.getModel().getCanvas(), newSerial, property, labelAttributeDefaults);
		this.labelAttribute.setCurrentDrawingElement(this);
	}
	
	
	public HibLabelNode(HibCompoundNode parent, int index, HibLabelAttribute attribute){
		super(parent.getModel(), parent, index);
		if(parent == null || attribute == null) throw new IllegalArgumentException("parameters parent and attribute cannot be null");
		this.labelAttribute = attribute;
		this.labelAttribute.setCurrentDrawingElement(this);
	}
	
	
//	/**
//	 * @param hibLabelAttribute
//	 */
//	public void changeLabelAttribute(HibLabelAttribute hibLabelAttribute) {
//		if(this.labelAttribute != null){
//			this.labelAttribute.setLabelNode(null) ;
//		}
//		if(hibLabelAttribute != null){
//			hibLabelAttribute.setLabelNode(this);
//		}
//		this.labelAttribute = hibLabelAttribute;
//
//	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode#getAttribute()
	 */
	public HibLabelAttribute getAttribute() {
		return this.labelAttribute;
	}

	void setAttribute(HibLabelAttribute labelAttribute) {
		this.labelAttribute = labelAttribute;
		this.labelAttribute.setCurrentDrawingElement(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getObjectType()
	 */
	public INodeObjectType getObjectType() {
		return this.labelAttribute.getObjectType();
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
		ModelStructureChangeType type;
		if(removed){
			type = ModelStructureChangeType.DELETED;
			// remove the label from the associated property
//			this.getAttribute().getVisualisableProperty().setLabel(null);
		}
		else{
			type = ModelStructureChangeType.ADDED;
			this.labelAttribute.setCurrentDrawingElement(this);
//			this.getAttribute().getVisualisableProperty().setLabel(this.getAttribute());
		}
		this.getParentNode().getSubModel().notifyNodeStructureChange(type, this);
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode#isValid()
	 */
	@Override
	public boolean isValid() {
		return this.labelAttribute != null && this.labelAttribute.isValid();
	}

	@Override
	public HibCompoundNode getParentNode() {
		return this.getHibParentNode();
	}


	// This method should not be used as one cannot have a label of a label. 
	public HibSubModel getLabelSubModel() {
		throw new UnsupportedOperationException("Cannot have a label of a label");
	}
}
