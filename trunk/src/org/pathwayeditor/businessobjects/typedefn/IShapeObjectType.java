package org.pathwayeditor.businessobjects.typedefn;

import java.util.EnumSet;


public interface IShapeObjectType extends INodeObjectType {
	public static enum EditableShapeAttributes { 
		LINE_COLOUR, LINE_STYLE, LINE_WIDTH, FILL_COLOUR, SHAPE_SIZE, SHAPE_TYPE
	}; 
	
	/**
	 * Get the parenting rules of the shape
	 */
	IShapeParentingRules getParentingRules();

	IDefaultShapeAttributes getDefaultAttributes();
	
	/**
	 * Is the line colour editable
	 * @return
	 */
	EnumSet<EditableShapeAttributes> getEditableAttributes();

}
