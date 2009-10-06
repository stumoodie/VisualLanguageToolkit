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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class HibTextProperty extends HibProperty implements IPlainTextAnnotationProperty {
	private static final long serialVersionUID = 2408749022194227955L;

	private String textValue;
	private IPlainTextPropertyDefinition propertyDefinition;

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibTextProperty() {
	}

	public HibTextProperty(HibAnnotatedCanvasAttribute owner,IPlainTextPropertyDefinition defn) {
		super(owner, defn);
		this.propertyDefinition = defn;
		this.textValue = defn.getDefaultValue();
	}

	public HibTextProperty(HibAnnotatedCanvasAttribute newOwner, HibTextProperty other) {
		super(newOwner, other);
		this.propertyDefinition = other.propertyDefinition;
		this.textValue = other.textValue;
	}

	public void setValue(String textValue) {
		if (textValue == null)
			throw new IllegalArgumentException("Text value cannot be null.");

		if(!textValue.equals(this.textValue)){
			String oldValue = this.textValue;
			this.textValue = textValue;
			this.getListenerHandler().notifyPropertyChange(this.propertyDefinition, oldValue, this.textValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IPlainTextAnnotationProperty#getDefinition()
	 */
	public IPlainTextPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getValue()
	 */
	public String getValue() {
		return textValue;
	}

	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IPlainTextPropertyDefinition) propertyDefinition;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
	 */
	public boolean canVisualiseProperty() {
		return this.propertyDefinition.isVisualisable();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visit(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor)
	 */
	public void visit(IAnnotationPropertyVisitor visitor) {
		visitor.visitPlainTextAnnotationProperty(this);
	}

}
