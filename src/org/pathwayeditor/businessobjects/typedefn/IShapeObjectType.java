/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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
	 * @return the default attributes, which cannot be null.
	 */
	IShapeAttributeDefaults getDefaultAttributes();
	
	/**
	 * Is the line colour editable
	 * @return the set of editble attributes.
	 */
	EnumSet<EditableShapeAttributes> getEditableAttributes();

}
