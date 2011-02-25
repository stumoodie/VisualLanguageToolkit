/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class ListProperty extends AnnotationProperty implements IListAnnotationProperty {
	private static final long serialVersionUID = 3772272140640145846L;

	private List<String> values;
	private IListPropertyDefinition propertyDefinition;
	
	public ListProperty(AnnotatedCanvasAttribute owner, IListPropertyDefinition propDefn) {
		super(owner);
		this.values = new ArrayList<String>();
		this.propertyDefinition = propDefn;
	}

	public ListProperty(AnnotatedCanvasAttribute owner, ListProperty other) {
		super(owner);
		this.propertyDefinition = other.propertyDefinition;
		this.values = new ArrayList<String>(other.values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getDefinition()
	 */
	@Override
	public IListPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getValue()
	 */
	@Override
	public List<String> getValue() {
		return this.values;
	}
	
	void setValue(List<String> value){
		this.values = value;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty#addValue(java.lang.String)
	 */
	@Override
	public void addValue(String newValue) {
		List<String> oldValue = this.values;
		this.values = new ArrayList<String>(oldValue);
		this.values.add(newValue);
		this.getListenerHandler().notifyPropertyChange(null, newValue);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty#getValueIterator()
	 */
	@Override
	public Iterator<String> getValueIterator() {
		return this.values.iterator();
	}

	@Override
	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IListPropertyDefinition) propertyDefinition;
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
		visitor.visitListAnnotationProperty(this);
	}

}
