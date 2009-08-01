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

import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibSubModel;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;

/**
 * @author smoodie
 *
 */
public class ShapeNodeFactory extends BaseCompoundNodeFactory implements IShapeNodeFactory {
	private final IHibNotationFactory hibNotationFactory;
	private final HibCompoundNode parent;
	private IShapeObjectType shapeObjectType;
	private HibShapeAttribute shapeAttribute;
	
	/**
	 * @param parent
	 */
	public ShapeNodeFactory(HibCompoundNode parent) {
		super();
		this.hibNotationFactory = parent.getModel().getHibNotationFactory();
		this.parent = parent;
		this.shapeObjectType = null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#newNode(uk.ed.inf.graph.compound.base.BaseCompoundNode, int)
	 */
	@Override
	protected BaseCompoundNode newNode(BaseCompoundNode parent, int nodeIndex) {
		HibCompoundNode hibParent = (HibCompoundNode)parent;
		HibShapeAttribute newAttribute = this.shapeAttribute; 
		if(newAttribute == null){
			HibObjectType hibObjectType = this.hibNotationFactory.getObjectType(shapeObjectType);
			HibCanvas canvas = hibParent.getModel().getCanvas();
			newAttribute = new HibShapeAttribute(canvas, canvas.getCreationSerialCounter().nextIndex(), shapeObjectType, hibObjectType);
		}
		HibShapeNode retVal = new HibShapeNode(hibParent, nodeIndex, newAttribute);
		this.parent.getSubModel().notifyNodeStructureChange(ModelStructureChangeType.ADDED, retVal);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#getGraph()
	 */
	@Override
	public HibModel getGraph() {
		return this.parent.getGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#createShapeNode()
	 */
	public HibShapeNode createShapeNode() {
		if(!this.canCreateShapeNode())
			throw new IllegalStateException("canCreateShapeNode() == false");
		
		return (HibShapeNode)this.createNode();
	}
	
	/**
	 * Adds an attribute to be assigned to the newly created node.
	 * This is designed to help with moving nodes where a new node is created and
	 * and the old one is removed and the attribute is transferred from the old node
	 * to the new one.  
	 * @param attribute
	 */
	public void setAttribute(HibShapeAttribute attribute){
		this.shapeAttribute = attribute;
	}
	
	public HibShapeAttribute getAttribute(){
		return this.shapeAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getCurrentObjectType()
	 */
	public IShapeObjectType getCurrentObjectType() {
		return this.shapeObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getOwningSubCanvas()
	 */
	public HibSubModel getOwningSubCanvas() {
		return this.parent.getChildCompoundGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#setObjectType(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public void setObjectType(IShapeObjectType shapeObjectType) {
		this.shapeObjectType = shapeObjectType;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory#getParentNode()
	 */
	@Override
	public HibCompoundNode getParentNode() {
		return this.parent;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#canCreateShapeNode()
	 */
	public boolean canCreateShapeNode() {
		boolean retVal = false;
		IShapeObjectType testObjectType = this.shapeObjectType;
		if(this.shapeAttribute != null){
			testObjectType = this.shapeAttribute.getObjectType();
		}
		if(testObjectType != null){
			retVal = this.parent.canParent(testObjectType);
		}
		return retVal;
	}

}
