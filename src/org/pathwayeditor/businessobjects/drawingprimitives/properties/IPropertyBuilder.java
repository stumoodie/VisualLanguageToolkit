/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;


/**
 * @author smoodie
 *
 */
public interface IPropertyBuilder {

	/**
	 * The owner of objects that are to be created.
	 * @return the owner of the properties buing built, which cannot be null.
	 */
	IAnnotatedObject getOwner();
	
	IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn);

	IHtmlAnnotationProperty createHtmlProperty(IHtmlPropertyDefinition propDefn);

	INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn);

	IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn);

	IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other);

	IHtmlAnnotationProperty copyHtmlProperty(IHtmlAnnotationProperty other);

	INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other);

	IListAnnotationProperty copyListProperty(IListAnnotationProperty other);
}
