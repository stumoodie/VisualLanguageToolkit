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
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeCopyFactory;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class ShapeAttributeCopyFactory implements IShapeAttributeCopyFactory {
	private IShapeAttribute sourceAttribute;
	private ICanvasElementAttribute destnAttibute;
	private final IndexCounter creationSerialCounter;
	
	public ShapeAttributeCopyFactory(IndexCounter creationSerialCounter){
		this.sourceAttribute = null;
		this.creationSerialCounter = creationSerialCounter;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.destnAttibute != null && this.sourceAttribute != null
			&& this.destnAttibute.getObjectType().getParentingRules().isValidChild(this.sourceAttribute.getObjectType());
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#createAttribute()
	 */
	@Override
	public IShapeAttribute createAttribute() {
		return new ShapeAttribute(this.destnAttibute.getRootAttribute(), creationSerialCounter.nextIndex(), this.sourceAttribute);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setDestinationAttribute(IElementAttribute destinationAttribute) {
		this.destnAttibute = (IDrawingNodeAttribute)destinationAttribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory#getDestinationAttribute()
	 */
	@Override
	public ICanvasElementAttribute getDestinationAttribute() {
		return this.destnAttibute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory#setElementToCopy(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setElementToCopy(IElementAttribute attributeToCopy) {
		this.sourceAttribute = (IShapeAttribute)attributeToCopy;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory#getElementToCopy()
	 */
	@Override
	public IShapeAttribute getElementToCopy() {
		return this.sourceAttribute;
	}

}
