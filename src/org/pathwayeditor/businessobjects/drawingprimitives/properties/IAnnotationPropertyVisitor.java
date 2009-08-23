/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * @author smoodie
 *
 */
public interface IAnnotationPropertyVisitor {

	void visitNumberProperty(INumberAnnotationProperty property);
	
	void visitIntegerProperty(IIntegerAnnotationProperty property);
	
	void visitPlainTextProperty(IPlainTextAnnotationProperty property);
	
	void visitHtmlProperty(IHtmlAnnotationProperty property);
	
	void visitBooleanProperty(IBooleanAnnotationProperty property);

	void visitListProperty(IListAnnotationProperty property);
}
