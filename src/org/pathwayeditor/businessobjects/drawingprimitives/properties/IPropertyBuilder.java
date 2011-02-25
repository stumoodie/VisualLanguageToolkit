/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;


/**
 * @author Stuart Moodie
 *
 */
public interface IPropertyBuilder {

	/**
	 * The owner of objects that are to be created.
	 * @return the owner of the properties buing built, which cannot be null.
	 */
	IAnnotatedObject getOwner();
	
	IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn);

//	IHtmlAnnotationProperty createHtmlProperty(IHtmlPropertyDefinition propDefn);

	INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn);

	IIntegerAnnotationProperty createIntegerProperty(IIntegerPropertyDefinition propDefn);

	IBooleanAnnotationProperty createBooleanProperty(IBooleanPropertyDefinition propDefn);

	IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn);

	IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other);

//	IHtmlAnnotationProperty copyHtmlProperty(IHtmlAnnotationProperty other);

	INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other);

	IIntegerAnnotationProperty copyIntegerProperty(IIntegerAnnotationProperty other);

	IBooleanAnnotationProperty copyBooleanProperty(IBooleanAnnotationProperty other);

	IListAnnotationProperty copyListProperty(IListAnnotationProperty other);
}
