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

package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ICompoundNodeFactory;

/**
 * @author smoodie
 *
 */
public class ShapeNodeFactory implements IShapeNodeFactory {
	private final ITypedDrawingNode parentNode;
	private final ICompoundNodeFactory nodeFactory;
	
	public ShapeNodeFactory(ITypedDrawingNode parentNode){
		this.parentNode = parentNode;
		this.nodeFactory = parentNode.getCompoundGraphElement().getChildCompoundGraph().nodeFactory();
		this.nodeFactory.setAttributeFactory(new ShapeAttributeFactory(parentNode.getAttribute()));
	}

	private ShapeAttributeFactory getAttributeFactory(){
		return (ShapeAttributeFactory)this.nodeFactory.getAttributeFactory();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getOwningSubCanvas()
	 */
	@Override
	public ISubModel getOwningSubCanvas() {
		return parentNode.getSubModel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#setObjectType(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	@Override
	public void setObjectType(IShapeObjectType shapeObjectType) {
		getAttributeFactory().setObjectType(shapeObjectType);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#getCurrentObjectType()
	 */
	@Override
	public IShapeObjectType getCurrentObjectType() {
		return getAttributeFactory().getObjectType();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#canCreateShapeNode()
	 */
	@Override
	public boolean canCreateShapeNode() {
		return this.nodeFactory.canCreateNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory#createShapeNode()
	 */
	@Override
	public IShapeNode createShapeNode() {
		ICompoundNode newNode = this.nodeFactory.createNode();
		IShapeNode retVal = new ShapeNode(newNode);
		retVal.getAttribute().getMapper().registerElement(retVal, newNode);
		return retVal;
	}

}
