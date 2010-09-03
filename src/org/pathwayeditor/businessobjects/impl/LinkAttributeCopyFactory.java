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

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class LinkAttributeCopyFactory implements IElementAttributeFactory {
	private final IndexCounter creationSerialCounter;
	private final ILinkAttribute attributeToCopy;
	private ICanvasElementAttribute destination;

	/**
	 * @param creationSerialCounter
	 */
	public LinkAttributeCopyFactory(IndexCounter creationSerialCounter, ILinkAttribute attributeToCopy) {
		this.creationSerialCounter = creationSerialCounter;
		this.attributeToCopy = attributeToCopy;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.attributeToCopy != null && this.destination != null;
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
	public ICanvasElementAttribute getDestinationAttribute() {
		return this.destination;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#createAttribute()
	 */
	@Override
	public ILinkAttribute createAttribute() {
		return new LinkAttribute(destination.getRootAttribute(), this.creationSerialCounter.nextIndex(), this.attributeToCopy);
	}

}
