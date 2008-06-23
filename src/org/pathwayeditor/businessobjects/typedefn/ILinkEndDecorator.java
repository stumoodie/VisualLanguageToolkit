package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.ArrowheadStyle;

/**
 * The definition of a link end decorator. Part of the definition of a link end.
 * The link end decorator is typically the arrowhead at the end of the line drawing the link.  
 * @author smoodie
 * @see ILinkObjectType
 */
public interface ILinkEndDecorator {

	/**
	 * Get the link end definition that this decorator definition is part of. 
	 * @return a non-null link end definition instance.
	 */
	ILinkEndDefinition getParentDefinition();

	/**
	 * Get the arrowhead style associated with the decorator.
	 * @return A non-null instance of an arrowhead type.
	 */
	ArrowheadStyle getDecoratorType();

	/**
	 * Get the width of the decorator (arrowhead typically). 
	 * @return a non-negative integer value.
	 */
	int getDecoratorWidth();

	/**
	 * Get the height of the decorator.
	 * @return a non-negative integer value.
	 */
	int getDecoratorHeight();

	/**
	 * States whether the size attributes of this decorator should be editable.
	 * @return true if they can be edited, false otherwise.
	 */
	boolean isDecoratorSizeEditable();
	
	/**
	 * States whether the decorator type of this decorator can be changed (editable).
	 * @return true if it can be edited, false otherwise.
	 */
	boolean isDecoratorTypeEditable();
}
