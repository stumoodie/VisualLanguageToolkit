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
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class CanvasElementAttributeCopyFactory implements IElementAttributeCopyFactory {
	private final IndexCounter creationSerialCounter;
	private ICanvasElementAttribute destination;
	private IElementAttributeCopyFactory copyFact;
	
	public CanvasElementAttributeCopyFactory(IndexCounter indexCounter){
		if(indexCounter == null) throw new IllegalArgumentException("Counter cannot be null");
		
		this.creationSerialCounter = indexCounter;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		boolean retVal = false;
		if(this.destination != null && this.copyFact != null){
			copyFact.setDestinationAttribute(destination);
			retVal = copyFact.canCreateAttribute();
		}
		return retVal;
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
	public IElementAttribute createAttribute() {
		copyFact.setDestinationAttribute(destination);
		return copyFact.createAttribute();
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory#setElementToCopy(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setElementToCopy(IElementAttribute attributeToCopy) {
		ICanvasElementAttribute source = (ICanvasElementAttribute)attributeToCopy;
		source.visit(new ICanvasElementAttributeVisitor(){

			@Override
			public void visitRoot(IRootAttribute attribute) {
				throw new IllegalStateException("Should not be copying the root attribute using this factory");
			}

			@Override
			public void visitShape(IShapeAttribute attribute) {
				copyFact = new ShapeAttributeCopyFactory(creationSerialCounter); 
			}

			@Override
			public void visitLink(ILinkAttribute attribute) {
				copyFact = new LinkAttributeCopyFactory(creationSerialCounter); 
			}

			@Override
			public void visitLabel(ILabelAttribute attribute) {
				copyFact = new LabelAttributeCopyFactory(creationSerialCounter); 
			}
			
		});
		copyFact.setElementToCopy(source);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory#getElementToCopy()
	 */
	@Override
	public IElementAttribute getElementToCopy() {
		return this.copyFact != null ? copyFact.getElementToCopy() : null;
	}

}
