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

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;

/**
 * @author smoodie
 *
 */
public class DrawingElementFacade implements IDrawingElement {
	private final ICompoundGraphElement graphElement;
	
	public DrawingElementFacade(ICompoundGraphElement graphElement){
		this.graphElement = graphElement;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getAttribute()
	 */
	@Override
	public ICanvasElementAttribute getAttribute() {
		return (ICanvasElementAttribute)this.graphElement.getAttribute();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getGraphElement()
	 */
	@Override
	public ICompoundGraphElement getGraphElement() {
		return this.graphElement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.graphElement == null) ? 0 : this.graphElement.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IDrawingElement)) {
			return false;
		}
		IDrawingElement other = (IDrawingElement) obj;
		if (this.graphElement == null) {
			if (other.getGraphElement() != null) {
				return false;
			}
		} else if (!this.graphElement.equals(other.getGraphElement())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.graphElement.getLevel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getUniqueIndex()
	 */
	@Override
	public long getUniqueIndex() {
		return this.graphElement.getIndex();
	}

}
