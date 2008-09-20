/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;


/**
 * @author smoodie
 *
 */
public interface IPropertyBuilder {

	IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn);

	IHtmlAnnotationProperty createHtmlProperty(IHtmlPropertyDefinition propDefn);

	INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn);

	IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn);

	IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other);

	IHtmlAnnotationProperty copyHtmlProperty(IHtmlAnnotationProperty other);

	INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other);

	IListAnnotationProperty copyListProperty(IListAnnotationProperty other);
}
