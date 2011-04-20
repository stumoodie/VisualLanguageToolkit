/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

/**
 * 
 */
package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;
import uk.ac.ed.inf.graph.compound.IElementAttribute;

/**
 * @author Stuart Moodie
 *
 */
public abstract class CanvasAttribute implements ICanvasElementAttribute, IElementAttribute {
	private final IModel canvas;
	private final int creationSerial;
	private ICompoundGraphElement compoundGraphElement;
	
	/**
	 * The normal constructor for this class to be used by classes extending this class in application code;
	 * @param canvas the canvas to which this attribute belongs, which should not be null.
	 * @param creationSerial the creation serial number that uniquely identifies this attribute within the canvas.
	 */
	protected CanvasAttribute(IModel canvas, int creationSerial) {
		this.canvas = canvas;
		this.creationSerial = creationSerial;
		this.canvas.addCanvasAttribute(this);
	}
	
	@Override
	public int getCreationSerial(){
		return this.creationSerial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.creationSerial;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ICanvasElementAttribute))
			return false;
		ICanvasElementAttribute other = (ICanvasElementAttribute) obj;
		if (this.creationSerial != other.getCreationSerial())
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(serial=");
		builder.append(this.getCreationSerial());
		builder.append(")");
		return builder.toString();
	}
	
	@Override
	public int compareTo(ICanvasElementAttribute other){
		int retVal = Integer.valueOf(this.creationSerial).compareTo(Integer.valueOf(other.getCreationSerial())); 
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

	@Override
	public boolean isRemoved(){
		boolean retVal = true;
		if(this.compoundGraphElement != null){
			retVal = this.compoundGraphElement.isRemoved();
		}
		return retVal;
	}
	
	@Override
	public final IModel getModel(){
		return this.canvas;
	}
}
