package org.pathwayeditor.businessobjects.typedefn;


/**
 * Define the link end appearance and default properties. The link end has a decorator at the end of
 *  the link which is typically an arrowhead or some kind and a decorator at the terminus of the link
 *  which can be any of the shape primitives. The offset defines the gap between both of these. The line
 *  drawn for the link finished at the link end decorator (hence the name).
 *    
 * @author smoodie
 *
 */
public interface ILinkEndDefinition {
	public static enum LinkEndType {
		SOURCE, TARGET
	}

	/**
	 * Get the object type that this definition belongs to.
	 * @return A non-null instance of the object type.
	 */
	ILinkObjectType getOwningType();
	
	/**
	 * The type of link end, i.e. target or source.
	 * @return
	 */
	LinkEndType getLinkEndCode();
		
	/**
	 * Get the terminus decorator definition.
	 * @return
	 */
	ILinkTerminusDecorator getTerminusDecorator();
	
	/**
	 * Get the link end decorator definition.
	 * @return
	 */
	ILinkEndDecorator getLinkEndDecorator();
	
	/**
	 * Get the offset value. The default is zero.
	 * @return
	 */
	int getOffset();
	
	/**
	 * Get the property filter to access the properties.
	 * @return Returns a property filter. Guaranteed to be not null.
	 */
	IPropertyDefinitionFilter getPropertiesFilter();

	/**
	 * Should the offset attribute be editable?  
	 * @return
	 */
	boolean isOffsetEditable();
}
