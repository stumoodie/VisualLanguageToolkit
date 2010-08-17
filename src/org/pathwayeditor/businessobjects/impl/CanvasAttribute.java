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
/**
 * 
 */
package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;
import uk.ac.ed.inf.graph.compound.IElementAttribute;

/**
 * @author smoodie
 *
 */
public abstract class CanvasAttribute implements ICanvasAttribute, IElementAttribute {
	private final ICanvas canvas;
	private final int creationSerial;
	private ICompoundGraphElement compoundGraphElement;
	
	public abstract boolean isValid();
	
	
	/**
	 * The normal constructor for this class to be used by classes extending this class in application code;
	 * @param canvas the canvas to which this attribute belongs, which should not be null.
	 * @param creationSerial the creation serial number that uniquely identifies this attribute within the canvas.
	 */
	protected CanvasAttribute(ICanvas canvas, int creationSerial) {
		this.canvas = canvas;
		this.creationSerial = creationSerial;
	}
	
	@Override
	public ICanvas getCanvas() {
		return this.canvas;
	}
	
	@Override
	public int getCreationSerial(){
		return this.creationSerial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.canvas == null) ? 0 : this.canvas.hashCode());
		result = prime * result + this.creationSerial;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ICanvasAttribute))
			return false;
		ICanvasAttribute other = (ICanvasAttribute) obj;
		if (this.canvas == null) {
			if (other.getCanvas() != null)
				return false;
		} else if (!this.canvas.equals(other.getCanvas()))
			return false;
		if (this.creationSerial != other.getCreationSerial())
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(canvas=");
		builder.append(this.getCanvas());
		builder.append(", serial=");
		builder.append(this.getCreationSerial());
		builder.append(")");
		return builder.toString();
	}
	
	@Override
	public int compareTo(ICanvasAttribute other){
		int retVal = this.getCanvas().compareTo(other.getCanvas());
		if(retVal == 0){
			retVal = Integer.valueOf(this.creationSerial).compareTo(Integer.valueOf(other.getCreationSerial())); 
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#getCurrentElement()
	 */
	@Override
	public ICompoundGraphElement getCurrentElement() {
		return this.compoundGraphElement;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#setCurrentElement(uk.ac.ed.inf.graph.compound.ICompoundGraphElement)
	 */
	@Override
	public void setCurrentElement(ICompoundGraphElement newOwner) {
		this.compoundGraphElement = newOwner;
	}
}
