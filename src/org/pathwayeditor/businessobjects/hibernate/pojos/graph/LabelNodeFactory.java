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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;

import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;

/**
 * @author smoodie
 *
 */
public class LabelNodeFactory extends BaseCompoundNodeFactory implements ILabelNodeFactory {
	private HibProperty annotationProperty = null;
	private final HibCompoundNode parent;
	private HibLabelAttribute attribute;
	
	/**
	 * @param parent
	 */
	public LabelNodeFactory(HibCompoundNode parent) {
		super();
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#newNode(uk.ed.inf.graph.compound.base.BaseCompoundNode, int)
	 */
	@Override
	protected HibCompoundNode newNode(BaseCompoundNode parent, int nodeIndex) {
		HibLabelNode retVal = null;
		if(this.annotationProperty != null){
			retVal = new HibLabelNode((HibCompoundNode)parent, nodeIndex, this.annotationProperty);
		}
		else{
			retVal = new HibLabelNode((HibCompoundNode)parent, nodeIndex, this.attribute);
		}
		this.parent.getSubModel().notifyNodeStructureChange(ModelStructureChangeType.ADDED, retVal);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#getGraph()
	 */
	@Override
	public HibModel getGraph() {
		return this.parent.getModel();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#getParentNode()
	 */
	@Override
	public HibCompoundNode getParentNode() {
		return this.parent;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#createLabel()
	 */
	public HibLabelNode createLabel() {
		if(!canCreateLabelNode()) throw new IllegalStateException("Factory not initialised correctly");
		
		return (HibLabelNode)super.createNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#getOwningProperty()
	 */
	public IAnnotationProperty getOwningProperty() {
		return this.annotationProperty;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#getOwningSubCanvas()
	 */
	public ISubModel getOwningSubCanvas() {
		return this.parent.getSubModel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#isValidProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public boolean isValidProperty(IAnnotationProperty annotationProperty) {
		return annotationProperty != null &&
			((IDrawingElement)this.annotationProperty.getOwner()).getModel().equals(this.getGraph());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#setProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public void setProperty(IAnnotationProperty annotationProperty) {
		this.annotationProperty = (HibProperty)annotationProperty;
	}

	/**
	 * @return the attribute
	 */
	public HibLabelAttribute getAttribute() {
		return this.attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(HibLabelAttribute attribute) {
		this.attribute = attribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory#canCreateLabelNode()
	 */
	public boolean canCreateLabelNode() {
		return !(this.annotationProperty == null && this.attribute == null);
	}

}
