/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * IAnnotationPropertyVisitor is an interface that is used as part of the Visitor Design Pattern. It 
 * should be implemented by classes that wish to provide sub-type specific implementations of sub-types of
 * {@link IAnnotationProperty}. It provides a safer alternative to an if ... else ... if block. A method is only called
 * by an instance of the subtype matching the methods argument. So, for example, an implementation of <code>IIntegerAnnotationProperty</code>
 * will only ever invoke the method <code>visitIntegerAnnotationProperty()</code>.  
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnnotationPropertyVisitor {

	/**
	 * An integer property implementation.
	 * @param prop the integer property invoking this method, which will not be null.
	 */
	void visitIntegerAnnotationProperty(IIntegerAnnotationProperty prop);
	
	/**
	 * An boolean property implementation.
	 * @param prop the boolean property invoking this method, which will not be null.
	 */
	void visitBooleanAnnotationProperty(IBooleanAnnotationProperty prop);
	
	/**
	 * An plain text property implementation.
	 * @param prop the plain text property invoking this method, which will not be null.
	 */
	void visitPlainTextAnnotationProperty(IPlainTextAnnotationProperty prop);
	
	/**
	 * An number property implementation.
	 * @param prop the number property invoking this method, which will not be null.
	 */
	void visitNumberAnnotationProperty(INumberAnnotationProperty prop);
	
	/**
	 * An list property implementation.
	 * @param prop the list property invoking this method, which will not be null.
	 */
	void visitListAnnotationProperty(IListAnnotationProperty prop);
	
}
