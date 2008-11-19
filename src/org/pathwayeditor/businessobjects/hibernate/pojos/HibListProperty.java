package org.pathwayeditor.businessobjects.hibernate.pojos;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * ListProperty generated by hbm2java
 */
public class HibListProperty extends HibProperty implements IListAnnotationProperty {
	private static final long serialVersionUID = 3772272140640145846L;

	private List<String> values = new ArrayList<String>(0);
	private IListPropertyDefinition propertyDefinition;
	
	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibListProperty() {
	}

	public HibListProperty(HibCanvas hibCanvas, int creationSerial, IListPropertyDefinition propDefn) {
		super(hibCanvas, creationSerial);
		this.propertyDefinition = propDefn;
	}

	public HibListProperty(HibCanvas hibCanvas, int creationSerial, HibListProperty other) {
		super(hibCanvas, creationSerial, other);
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

	// end of extra code specified in the hbm.xml files

}
