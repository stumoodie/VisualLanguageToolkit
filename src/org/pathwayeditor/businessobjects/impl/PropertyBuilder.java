/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

/**
 * @author Stuart Moodie
 *
 */
public class PropertyBuilder implements IPropertyBuilder {
	private final AnnotatedCanvasAttribute owner;
	
	public PropertyBuilder(AnnotatedCanvasAttribute owner){
		this.owner = owner;
	}
	
	
	@Override
	public IAnnotatedObject getOwner() {
		return this.owner;
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IPropertyVisitor#createHtmlProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition)
//	 */
//	public IHtmlAnnotationProperty createHtmlProperty(IHtmlPropertyDefinition propDefn) {
//		IHtmlAnnotationProperty anHTMLProperty = new HibRichTextProperty(owner, propDefn);
//		return anHTMLProperty ;
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IPropertyVisitor#createPlainTextProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition)
	 */
	@Override
	public IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn) {
		IPlainTextAnnotationProperty aTextProperty = new TextProperty(owner,  propDefn); 
		return aTextProperty ;
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyHtmlProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition)
//	 */
//	public IHtmlAnnotationProperty copyHtmlProperty(IHtmlAnnotationProperty other) {
//		return new HibRichTextProperty(owner, (HibRichTextProperty)other);
//	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyNumberProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition)
	 */
	@Override
	public INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other) {
		return new NumberProperty(owner, (NumberProperty)other);
	}

	@Override
	public IIntegerAnnotationProperty copyIntegerProperty(IIntegerAnnotationProperty other) {
		return new IntegerProperty(owner, (IntegerProperty)other);
	}

	@Override
	public IBooleanAnnotationProperty copyBooleanProperty(IBooleanAnnotationProperty other) {
		return new BooleanProperty(owner, (BooleanProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyPlainTextProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition)
	 */
	@Override
	public IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other) {
		return new TextProperty(owner, (TextProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#createNumberProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition)
	 */
	@Override
	public INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn) {
		INumberAnnotationProperty aNumberProperty =  new NumberProperty(owner,  propDefn); 
		return aNumberProperty ;
	}

	@Override
	public IIntegerAnnotationProperty createIntegerProperty(IIntegerPropertyDefinition propDefn) {
		IIntegerAnnotationProperty aNumberProperty =  new IntegerProperty(owner,  propDefn); 
		return aNumberProperty ;
	}

	@Override
	public IBooleanAnnotationProperty createBooleanProperty(IBooleanPropertyDefinition propDefn) {
		IBooleanAnnotationProperty aNumberProperty =  new BooleanProperty(owner,  propDefn); 
		return aNumberProperty ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#copyListProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty)
	 */
	@Override
	public IListAnnotationProperty copyListProperty(IListAnnotationProperty other) {
		return new ListProperty(owner, (ListProperty)other);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder#createListProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition)
	 */
	@Override
	public IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn) {
		IListAnnotationProperty aListProperty =  new ListProperty(owner,  propDefn); 
		return aListProperty ;
	}
	
}
