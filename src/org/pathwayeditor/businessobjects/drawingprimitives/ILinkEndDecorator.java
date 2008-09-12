package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ArrowheadStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.typedefn.ILinkEndDefinition;

public interface ILinkEndDecorator {

	/**
	 * Gets the {@link ILinkTerminus} this decorator is assosiated with.
	 * @return the link terminus.
	 */
	ILinkTerminus getLinkTerminus();
	
	/**
	 * Gets the {@link ILinkEndDefinition} this decorator is assosiated with.
	 * @return the ILinkEndDefinition.
	 */
	ILinkEndDefinition getLinkEndDefinition();
	
	/**
	 * Returns a {@link Size} representation for the size of this decorator.
	 * @return the size. Cannot be null.
	 */
	Size getSize () ;
	
	/**
	 * Sets the new size for this decorator.
	 * @param size the new size. Cannot be null
	 * @throws IllegalArgumentException if URL is null.
	 */
	void setSize ( Size size ) ;
	
	/**
	 * Gets the {@link ArrowheadStyle} of this decorator.
	 * @return the ArrowheadStyle.
	 */
	ArrowheadStyle getDecoratorType () ;
	
	/**
	 * Sets the style of the Arrowhead for this decorator.
	 * @param arrowheadStyle the new style. Cannot be null.
	 * @throws IllegalArgumentException if arrowheadStyle is null.
	 */
	void setDecoratorType ( ArrowheadStyle arrowheadStyle  );
	
}
