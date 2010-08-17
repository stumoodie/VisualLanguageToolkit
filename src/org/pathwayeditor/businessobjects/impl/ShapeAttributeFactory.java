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

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * @author smoodie
 *
 */
public class ShapeAttributeFactory implements IElementAttributeFactory {
	private IShapeObjectType shapeObjectType;
	private final ITypedDrawingNodeAttribute parentAttribute;
	
	
	public ShapeAttributeFactory(ITypedDrawingNodeAttribute parentAttribute){
		this.parentAttribute = parentAttribute;
	}
	
	public void setObjectType(IShapeObjectType objectType){
		this.shapeObjectType = objectType;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.shapeObjectType != null && this.parentAttribute != null &&
			this.parentAttribute.getObjectType().getParentingRules().isValidChild(shapeObjectType);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#createAttribute()
	 */
	@Override
	public IElementAttribute createAttribute() {
		ICanvas canvas = this.parentAttribute.getCanvas();
		return new ShapeAttribute(canvas, canvas.getCreationSerialCounter().nextIndex(), shapeObjectType);
	}

	public ITypedDrawingNodeAttribute getParentAttribute(){
		return this.parentAttribute;
	}

	public IShapeObjectType getObjectType() {
		return this.shapeObjectType;
	}
	
}
