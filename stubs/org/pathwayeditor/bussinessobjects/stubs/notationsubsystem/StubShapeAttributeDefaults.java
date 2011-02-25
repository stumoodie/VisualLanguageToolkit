/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * @author Stuart Moodie
 *
 */
public abstract class StubShapeAttributeDefaults implements IShapeAttributeDefaults {
	public   RGB    FILL_COLOR = new RGB ( 100 , 100 , 100 ) ;
	public   RGB    LINE_COLOR = new RGB ( 150 , 150 , 150 ) ;
	public   LineStyle LINE_STYLE = LineStyle.DASH_DOT ;     
	public   int LINE_WIDTH = 1 ;
	public   String PRIMITIVE_SHAPE_TYPE = "curbounds oval";
	public   Dimension SIZE = new Dimension ( 50 , 50 ) ;
	
	
	@Override
	public String getShapeDefinition(){
		return PRIMITIVE_SHAPE_TYPE;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getFillColour()
	 */
	@Override
	public RGB getFillColour() {
		return FILL_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineColour()
	 */
	@Override
	public RGB getLineColour() {
		return LINE_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineStyle()
	 */
	@Override
	public LineStyle getLineStyle() {
		return LINE_STYLE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineWidth()
	 */
	@Override
	public double getLineWidth() {
		return LINE_WIDTH;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getSize()
	 */
	@Override
	public Dimension getSize() {
		return SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#propertyIterator()
	 */
	@Override
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return getpropdefns().iterator();
	}

	@Override
	public boolean containsPropertyDefinition(String name) {
		return findPropDefn(name) != null;
	}

	IPropertyDefinition findPropDefn(String name){
		IPropertyDefinition retVal = null;
		for(IPropertyDefinition propDefn : this.getpropdefns()){
			if(propDefn.getName().equals(name)){
				retVal = propDefn;
				break;
			}
		}
		return retVal;
	}
	
	@Override
	public IPropertyDefinition getPropertyDefinition(String name) {
		return findPropDefn(name);
	}

	@Override
	public int numPropertyDefinitions() {
		return this.getpropdefns().size();
	}

	/**
	 * @return
	 */
	protected abstract Set<IPropertyDefinition> getpropdefns() ;

	@Override
	public boolean containsPropertyDefinition(IPropertyDefinition propDefn){
		return propDefn != null && this.getpropdefns().contains(propDefn);
	}
}
