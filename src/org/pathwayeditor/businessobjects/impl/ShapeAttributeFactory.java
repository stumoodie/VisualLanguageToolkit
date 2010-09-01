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

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class ShapeAttributeFactory implements IShapeAttributeFactory {
	private IShapeObjectType shapeObjectType;
	private ICanvasElementAttribute destinationAttribute;
	private final IndexCounter creationSerialCounter;
	
	public ShapeAttributeFactory(IndexCounter creationSerialCounter){
		if(creationSerialCounter == null) throw new IllegalArgumentException("Creation serial cannot be null");
		
		this.destinationAttribute = null;
		this.shapeObjectType = null;
		this.creationSerialCounter = creationSerialCounter;
	}
	
	@Override
	public void setObjectType(IShapeObjectType objectType){
		this.shapeObjectType = objectType;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.shapeObjectType != null && this.destinationAttribute != null &&
			this.destinationAttribute.getObjectType().getParentingRules().isValidChild(shapeObjectType);
	}

	@Override
	public IShapeAttribute createAttribute() {
		if(!this.canCreateAttribute()) throw new IllegalStateException("cannot create attribute");
	
		return new ShapeAttribute(this.destinationAttribute.getRootAttribute(), creationSerialCounter.nextIndex(), shapeObjectType);
	}

	@Override
	public IShapeObjectType getObjectType() {
		return this.shapeObjectType;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setDestinationAttribute(IElementAttribute attribute) {
		this.destinationAttribute = (ICanvasElementAttribute)attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getDestinationAttribute()
	 */
	@Override
	public ICanvasElementAttribute getDestinationAttribute() {
		return this.destinationAttribute;
	}
	
}
