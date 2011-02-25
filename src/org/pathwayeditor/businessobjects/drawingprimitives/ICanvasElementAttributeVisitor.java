/*
Copyright 2009-2011, Court of the University of Edinburgh

*/

package org.pathwayeditor.businessobjects.drawingprimitives;


/**
 * ICanvasElementAttributeVisitor a instance that defines a visitor to be used as part of a Visitor Pattern.
 * Classes should implement this interface and then provide implementations for each of the subtypes of ICanvasElementAttribute
 * specified in the methods. 
 * 
 * @see ICanvasElementAttribute
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasElementAttributeVisitor {
	
	/**
	 * Provides and implementation for the IRootAttribute and will be called by classes implementing
	 * IRootAttribute.
	 * 
	 * @param attribute the rootAttribute, which should not be null.
	 */
	void visitRoot(IRootAttribute attribute);
	
	/**
	 * Provides and implementation for the IShapeAttribute and will be called by classes implementing
	 * IShapeAttribute.
	 * 
	 * @param attribute the shapeAttribute, which should not be null.
	 */
	void visitShape(IShapeAttribute attribute);
	
	/**
	 * Provides and implementation for the ILinkAttribute and will be called by classes implementing
	 * IRootAttribute.
	 * 
	 * @param attribute the linkAttribute, which should not be null.
	 */
	void visitLink(ILinkAttribute attribute);
	
	/**
	 * Provides and implementation for the ILabelAttribute and will be called by classes implementing
	 * ILabelAttribute.
	 * 
	 * @param attribute the labelAttribute, which should not be null.
	 */
	void visitLabel(ILabelAttribute attribute);
	
}
