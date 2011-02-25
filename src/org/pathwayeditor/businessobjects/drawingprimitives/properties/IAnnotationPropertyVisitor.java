/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * @author Stuart Moodie
 *
 */
public interface IAnnotationPropertyVisitor {

	void visitIntegerAnnotationProperty(IIntegerAnnotationProperty prop);
	
	void visitBooleanAnnotationProperty(IBooleanAnnotationProperty prop);
	
	void visitPlainTextAnnotationProperty(IPlainTextAnnotationProperty prop);
	
	void visitNumberAnnotationProperty(INumberAnnotationProperty prop);
	
	void visitListAnnotationProperty(IListAnnotationProperty prop);
	
}
