package org.pathwayeditor.businessobjects.typedefn;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;


/**
 * Define the link end appearance and default properties. The link end has a decorator at the end of
 *  the link which is typically an arrowhead or some kind and a decorator at the terminus of the link
 *  which can be any of the shape primitives. The offset defines the gap between both of these. The line
 *  drawn for the link finished at the link end decorator (hence the name).
 *    
 * @author smoodie
 *
 */
public interface ILinkTerminusDefinition {
	public static enum LinkTermEditableAttributes{ OFFSET, END_COLOUR, END_SIZE, END_DECORATOR_TYPE,
		TERM_COLOUR, TERM_SIZE, TERM_DECORATOR_TYPE };
	
	/**
	 * Get the link defaults object object that this definition belongs to.
	 * @return A non-null instance of the object type.
	 */
	ILinkObjectType getOwningObjectType();
	
	/**
	 * The type of link end, i.e. target or source.
	 * @return the terminus type, which cannot be null.
	 */
	LinkTermType getLinkEndCode();
			
	ILinkTerminusDefaults getLinkTerminusDefaults();
	
	EnumSet<LinkTermEditableAttributes> getEditableAttributes();
}
