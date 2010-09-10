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
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class LinkAttributeFactory implements ILinkAttributeFactory {
	private final IndexCounter creationSerialCounter;
	private ICanvasElementAttribute destination;
	private ILinkObjectType objectType;
	private IShapeAttribute source;
	private IShapeAttribute target;

	public LinkAttributeFactory(IndexCounter creationSerialCounter) {
		this.creationSerialCounter = creationSerialCounter;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.objectType != null && this.destination != null && this.source != null && this.target != null
			&& this.objectType.getLinkConnectionRules().isValidTarget(source.getObjectType(), target.getObjectType());
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setDestinationAttribute(IElementAttribute attribute) {
		this.destination = (ICanvasElementAttribute)attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getDestinationAttribute()
	 */
	@Override
	public IElementAttribute getDestinationAttribute() {
		return this.destination;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#createAttribute()
	 */
	@Override
	public ILinkAttribute createAttribute() {
		return new LinkAttribute(this.destination.getRootAttribute(), this.creationSerialCounter.nextIndex(), this.objectType);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory#setObjectType(org.pathwayeditor.businessobjects.typedefn.ILinkObjectType)
	 */
	@Override
	public void setObjectType(ILinkObjectType objectType) {
		this.objectType = objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory#getObjectType()
	 */
	@Override
	public ILinkObjectType getObjectType() {
		return this.objectType;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setOutAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setOutAttribute(IElementAttribute attribute) {
		this.source = (IShapeAttribute)attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getOutAttribute()
	 */
	@Override
	public IShapeAttribute getOutAttribute() {
		return this.source;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setInAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setInAttribute(IElementAttribute attribute) {
		this.target = (IShapeAttribute)attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getInAttribute()
	 */
	@Override
	public IShapeAttribute getInAttribute() {
		return this.target;
	}

}
