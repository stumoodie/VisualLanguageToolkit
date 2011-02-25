/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;

import java.util.EnumSet;


public interface ILinkObjectType extends IObjectType {
	public static enum LinkEditableAttributes{ COLOUR, LINE_STYLE, LINE_WIDTH };
	
	/**
	 * Returns the unique identifier for the link object type, which must be a positive integer.
	 * @return the unique id, which must comply with the postcondition: <code>getUniqueId() > 0</code>.
	 */
	@Override
	int getUniqueId();
	
	/**
	 * Gets the connection rules for this link.
	 * @return A non-null instance of the connection rules.
	 */
	ILinkConnectionRules getLinkConnectionRules();

	/**
	 * Gets the default attributes for the link.
	 * @return the default attributes class, which cannot be null.
	 */
	ILinkAttributeDefaults getDefaultAttributes();
	
	/**
	 * Get the source terminus definition.
	 * @return the definition, which cannot be null.
	 */
	ILinkTerminusDefinition getSourceTerminusDefinition();
	
	/**
	 * Get the target terminus definition.
	 * @return the definition, which cannot be null.
	 */
	ILinkTerminusDefinition getTargetTerminusDefinition();
	
	/**
	 * Get the editable attributes for this type.
	 * @return the set of editable attributes, which cannot be null, but can be empty.
	 */
	EnumSet<LinkEditableAttributes> getEditableAttributes();
}
