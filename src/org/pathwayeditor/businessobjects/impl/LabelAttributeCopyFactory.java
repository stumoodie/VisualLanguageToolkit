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

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeCopyFactory;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class LabelAttributeCopyFactory implements ILabelAttributeCopyFactory {

	/**
	 * @param creationSerialCounter
	 */
	public LabelAttributeCopyFactory(IndexCounter creationSerialCounter) {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory#setElementToCopy(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setElementToCopy(IElementAttribute attributeToCopy) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory#getElementToCopy()
	 */
	@Override
	public IElementAttribute getElementToCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setDestinationAttribute(IElementAttribute attribute) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getDestinationAttribute()
	 */
	@Override
	public IElementAttribute getDestinationAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#createAttribute()
	 */
	@Override
	public IElementAttribute createAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

}
