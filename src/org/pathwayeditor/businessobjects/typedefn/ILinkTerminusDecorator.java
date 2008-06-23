package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.IPrimitiveShape;
import org.pathwayeditor.businessobjects.LineStyle;

/**
 * The link terminus decorator definition. This interface provide the definition of how the terminus of
 * the link (the point where the link is anchored to the shape) is decorated. This is used as part of a
 * link end defintion.
 * @author smoodie
 *
 */
public interface ILinkTerminusDecorator {

	/**
	 * Get the owning link end definition. 
	 * @return a non-null instance.
	 */
	ILinkEndDefinition getParentDefinition();

	/**
	 * get the shape type of rhe decorator.
	 * @return the shape type.
	 */
	IPrimitiveShape getDecoratorType();

	/**
	 * Get the width of the shape. 
	 * @return a positive integer value.
	 */
	int getSizeWidth();

	/**
	 * Get the height of the shape. 
	 * @return a positive integer value.
	 */
	int getSizeHeight();

	/**
	 * Get the width of the line (edge) of the shape.
	 * @return a positive integer value.
	 */
	int getLineWidth();

	/**
	 * Get the style of the line.
	 * @return the non-null line style.
	 */
	LineStyle getLineStyle();

	/**
	 * Get the transparency of the shape.
	 * @return a transparency value between 0 and 100.
	 */
	int getFillTransparency();

	/**
	 * Get the fill and line RGB red colour component.
	 * @return a value in the range 0-255.
	 */
	int getColourRed();

	/**
	 * Get the fill and line RGB green colour component.
	 * @return a value in the range 0-255.
	 */
	int getColourGreen();

	/**
	 * Get the fill and line RGB blue colour component.
	 * @return a value in the range 0-255.
	 */
	int getColourBlue();
		
	/**
	 * Can the line style be edited?
	 * @return true if it can be edited, false otherwise.
	 */
	boolean isLineStyleEditable();
	
	/**
	 * Can the line width be edited?
	 * @return true if it can be edited, false otherwise.
	 */
	boolean isLineWidthEditable();

	/**
	 * Can the fill properties be edited?
	 * @return true if it can be edited, false otherwise.
	 */
	boolean isColourEditable();
	
	/**
	 * Can the shape size be edited?
	 * @return true if it can be edited, false otherwise.
	 */
	boolean isSizeEditable();
	
	/**
	 * Can the shape type be edited?
	 * @return true if it can be edited, false otherwise.
	 */
	boolean isShapeTypeEditable();
}
