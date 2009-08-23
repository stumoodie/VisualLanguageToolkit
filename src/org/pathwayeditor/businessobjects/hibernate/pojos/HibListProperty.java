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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;

public class HibListProperty extends HibProperty implements IListAnnotationProperty {
	private static final long serialVersionUID = 3772272140640145846L;

	private List<String> values;
	private IListPropertyDefinition propertyDefinition;
	
	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibListProperty() {
		this.values = new ArrayList<String>(0);
	}

	public HibListProperty(HibAnnotatedCanvasAttribute owner, IListPropertyDefinition propDefn) {
		super(owner, propDefn);
		this.values = new ArrayList<String>();
		this.propertyDefinition = propDefn;
	}

	public HibListProperty(HibAnnotatedCanvasAttribute owner, HibListProperty other) {
		super(owner, other);
		this.propertyDefinition = other.propertyDefinition;
		this.values = new ArrayList<String>(other.values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getDefinition()
	 */
	public IListPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getValue()
	 */
	public List<String> getValue() {
		return this.values;
	}
	
	void setValue(List<String> value){
		this.values = value;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty#addValue(java.lang.String)
	 */
	public void addValue(String newValue) {
		this.values.add(newValue);
		this.getListenerHandler().notifyPropertyChange(this.propertyDefinition, null, newValue);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty#getValueIterator()
	 */
	public Iterator<String> getValueIterator() {
		return this.values.iterator();
	}

	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IListPropertyDefinition) propertyDefinition;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
	 */
	public boolean canVisualiseProperty() {
		return this.propertyDefinition.isVisualisable();
	}

	public IListAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.copyListProperty(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visitProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IVisitor)
	 */
	public void visitProperty(IAnnotationPropertyVisitor visitor) {
		visitor.visitListProperty(this);
	}
}
