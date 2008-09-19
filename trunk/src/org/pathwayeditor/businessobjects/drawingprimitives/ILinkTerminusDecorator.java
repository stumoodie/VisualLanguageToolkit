package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


public interface ILinkTerminusDecorator {
	
	/**
	 * Gets the {@link RGB} representation of the background color of this Terminus Decorator.
	 * @return
	 */
	RGB getFillColor () ;
	
	/**
	 * Sets the backgroung color for this Terminus Decorator.
	 * @param newColor the new color. Cannot be null.
	 */
	void setFillColor ( RGB newColor ) ;

	/**
	 * Gets the {@link RGB} representation of the line color of this Terminus Decorator.
	 * @return
	 */
	RGB getLineColor () ;

	/**
	 * Sets the line color for this Terminus Decorator.
	 * @param newColor the new color. Cannot be null.
	 */
	void setLineColor ( RGB newColor ) ;

	/**
	 * Returns a {@link Size} representation for the size of this Link terminus decorator.
	 * @return the size. Cannot be null.
	 */
	Size getSize () ;

	/**
	 * Sets the size for this Terminus Decorator.
	 * @param newSize the new size. Cannot be null.
	 */
	void setSize ( Size newSize) ;
	
	/**
	 * Returns a the numeric values that represents the size of this decorator's border line.
	 * @return the line size.
	 */
	int getLineSize () ;
	
	/**
	 * Sets the size of the border line for this decorator.
	 * @param newLineSize the size of the border. Cannot be less than zero.
	 */
	void setLineSize (int newLineSize) ;
	
	/**
	 * Gets the line style of this decorator.
	 * @return the line style. Cannot be null.
	 */
	LineStyle getLineStyle();
	
	/**
	 * Sets the line style the border line for this decorator.
	 * @param lineStyle the style of the border. Cannot be null.
	 */	
	void setLineStyle(LineStyle lineStyle);
	
	/**
	 * Gets the line width of this decorator.
	 * @return the line with.
	 */	
	int getLineWidth () ;

	/**
	 * Sets the line width for this decorator.
	 * @param newWidth the new width. Cannot be less then zero.
	 */	
	void setLineWidth ( int newWidth) ;
	
	/**
	 * Gets the object type related with this decorator.
	 * @return the object type.
	 */	
	IShapeObjectType getObjectType();
	
	/**
	 * Gets the link terminus that owns that decorator
	 * @return the parent link terminus.
	 */	
	ILinkTerminus getOwningLinkTerminus () ;
	
	
}
