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

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttributeSequence;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * @author smoodie
 *
 */
public class ShapeAttributeCopyFactory implements IElementAttributeFactory {
	private final IShapeAttribute sourceAttribute;
	private ICanvasElementAttribute destnAttibute;
	private final ICanvasAttributeSequence creationSerialCounter;
	private IElementAttribute outAttribute;
	private IElementAttribute inAttribute;
	
	public ShapeAttributeCopyFactory(ICanvasAttributeSequence iCanvasAttributeSequence, IShapeAttribute sourceAttribute){
		this.sourceAttribute = sourceAttribute;
		this.creationSerialCounter = iCanvasAttributeSequence;
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
		return new ShapeAttribute(this.destnAttibute.getModel(), creationSerialCounter.next(), this.sourceAttribute);
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
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setOutAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setOutAttribute(IElementAttribute attribute) {
		this.outAttribute = attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getOutAttribute()
	 */
	@Override
	public IElementAttribute getOutAttribute() {
		return this.outAttribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setInAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setInAttribute(IElementAttribute attribute) {
		this.inAttribute = attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getInAttribute()
	 */
	@Override
	public IElementAttribute getInAttribute() {
		return this.inAttribute;
	}

}
