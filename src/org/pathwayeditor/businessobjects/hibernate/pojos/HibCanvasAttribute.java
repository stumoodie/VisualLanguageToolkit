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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * @author smoodie
 *
 */
public abstract class HibCanvasAttribute implements ICanvasAttribute {
	private Long id;
	private HibCanvas canvas;
	private int creationSerial;
	
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	public abstract boolean isValid();
	
	
	protected HibCanvasAttribute() {
		
	}
	
	/**
	 * The normal constructor for this class to be used by classes extending this class in application code;
	 * @param canvas the canvas to which this attribute belongs, which should not be null.
	 * @param creationSerial the creation serial number that uniquely identifies this attribute within the canvas.
	 */
	protected HibCanvasAttribute(HibCanvas canvas, int creationSerial) {
		this.id = null;
		this.canvas = canvas;
		this.creationSerial = creationSerial;
		this.canvas.getCanvasAttributes().add(this);
	}
	
	public Long getId() {
		return this.id;
	}

	public HibCanvas getCanvas() {
		return this.canvas;
	}
	

	void setCanvas(HibCanvas canvas) {
		this.canvas = canvas;
	}
	
	void setCreationSerial(int creationSerial){
		this.creationSerial = creationSerial;
	}
	
	public int getCreationSerial(){
		return this.creationSerial;
	}

	
	public abstract void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException;

	public abstract HibObjectType getHibObjectType();
	
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
}
