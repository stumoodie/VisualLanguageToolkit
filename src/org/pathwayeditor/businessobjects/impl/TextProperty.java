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

package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class TextProperty extends AnnotationProperty implements IPlainTextAnnotationProperty {
	private static final long serialVersionUID = 2408749022194227955L;

	private String textValue;
	private IPlainTextPropertyDefinition propertyDefinition;

	public TextProperty(AnnotatedCanvasAttribute owner,IPlainTextPropertyDefinition defn) {
		super(owner);
		this.propertyDefinition = defn;
		this.textValue = defn.getDefaultValue();
	}

	public TextProperty(AnnotatedCanvasAttribute newOwner, TextProperty other) {
		super(newOwner);
		this.propertyDefinition = other.propertyDefinition;
		this.textValue = other.textValue;
	}

	@Override
	public void setValue(String textValue) {
		if (textValue == null)
			throw new IllegalArgumentException("Text value cannot be null.");

		if(!textValue.equals(this.textValue)){
			String oldValue = this.textValue;
			this.textValue = textValue;
			this.getListenerHandler().notifyPropertyChange(oldValue, this.textValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IPlainTextAnnotationProperty#getDefinition()
	 */
	@Override
	public IPlainTextPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getValue()
	 */
	@Override
	public String getValue() {
		return textValue;
	}

	@Override
	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IPlainTextPropertyDefinition) propertyDefinition;
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
//	 */
//	@Override
//	public boolean canVisualiseProperty() {
//		return this.propertyDefinition.isVisualisable();
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visit(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor)
	 */
	@Override
	public void visit(IAnnotationPropertyVisitor visitor) {
		visitor.visitPlainTextAnnotationProperty(this);
	}

}
