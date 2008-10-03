package org.pathwayeditor.businessobjects.typedefn;

import java.util.EnumSet;


public interface IShapeObjectType extends INodeObjectType {
	public static enum EditableShapeAttributes { 
		LINE_COLOUR, LINE_STYLE, LINE_WIDTH, FILL_COLOUR, SHAPE_SIZE, SHAPE_TYPE
	}; 
	
	/**
	 * Returns the unique identifier for the link object type, which must be a positive integer.
	 * @return the unique id, which must comply with the postcondition: <code>getUniqueId() > 0</code>. 
	 */
	int getUniqueId();

	/**
	 * Get the parenting rules of the shape
	 */
	IShapeParentingRules getParentingRules();

	/**
	 * Gets the default attrributes for this object type.
	 * @return
	 */
	IShapeAttributeDefaults getDefaultAttributes();
	
	/**
	 * Is the line colour editable
	 * @return
	 */
	EnumSet<EditableShapeAttributes> getEditableAttributes();

}
