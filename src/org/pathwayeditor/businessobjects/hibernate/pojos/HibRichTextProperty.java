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
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @deprecated Don;t use. Will be removed soon. Use PlaintextProperty instead.
 */
public class HibRichTextProperty extends HibProperty implements IHtmlAnnotationProperty {
	private static final long serialVersionUID = -4043561259224207426L;

	private IHtmlPropertyDefinition propertyDefinition;
	private String richTextValue;

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibRichTextProperty() {
	}

	public HibRichTextProperty(HibAnnotatedCanvasAttribute owner, IHtmlPropertyDefinition defn) {
		super(owner, defn);
		this.propertyDefinition = defn;
		this.richTextValue = defn.getDefaultValue();
	}

	public HibRichTextProperty(HibAnnotatedCanvasAttribute owner, HibRichTextProperty other) {
		super(owner, other);
		this.propertyDefinition = other.getDefinition();
		this.richTextValue = other.richTextValue;
	}

	public String getRichTextValue() {
		return this.richTextValue;
	}

	public void setValue(String richTextValue) {
		if (richTextValue == null)
			throw new IllegalArgumentException(
					"richText value should not be null.");
		if(!richTextValue.equals(this.richTextValue)){
			String oldValue = this.richTextValue;
			this.richTextValue = richTextValue;
			this.getListenerHandler().notifyPropertyChange(oldValue, this.richTextValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IHtmlAnnotationProperty#getDefinition()
	 */
	public IHtmlPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}
	
	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IHtmlPropertyDefinition) propertyDefinition;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getValue()
	 */
	public String getValue() {
		return this.richTextValue;
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
	}
}
