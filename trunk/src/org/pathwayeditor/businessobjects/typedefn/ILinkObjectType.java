package org.pathwayeditor.businessobjects.typedefn;

import java.util.EnumSet;


public interface ILinkObjectType extends IObjectType {
	public static enum LinkEditableAttributes{ COLOUR, LINE_STYLE, LINE_WIDTH };
	
	/**
	 * Gets the connection rules for this link.
	 * @return A non-null instance of the connection rules.
	 */
	ILinkConnectionRules getLinkConnectionRules();

	IDefaultLinkAttributes getDefaultLinkAttributes();
	
	ILinkTerminusDefinition getSourceTerminusDefinition();
	
	ILinkTerminusDefinition getTargetTerminusDefinition();
	
	EnumSet<LinkEditableAttributes> getEditiableAttributes();
}
