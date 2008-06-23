package org.pathwayeditor.businessobjects.typedefn;


/**
 * Use to query the syntax rules for a link. The idea is that an instance of the interface is associated
 *  with a Link and is queried used by the link and its clients to ensure it is syntactically correct.  
 * @author smoodie
 *
 */
public interface ILinkConnectionRules {
	
	/**
	 * Test if the source type is a valid source to the link.
	 * @param source A non null source object type.
	 * @return True if the it is a valid source, false otherwise.
	 * @throws IllegalArgumentException if source is null.
	 */
	boolean isValidSource(IShapeObjectType source);
	
	/**
	 * Test if the combination of source and target can be joined by a link.
	 * @param source A non-null source object type.
	 * @param target A non-null target object type.
	 * @return True if both can for a link.
	 * @throws IllegalArgumentException if source and target are null.
	 */
	boolean isValidTarget(IShapeObjectType source, IShapeObjectType target);
	
	/**
	 * Get the link object type that these rule are applicable for.
	 * @return Returns a valid instance of <code>ILinkObjectType</code>. Cannot be null.
	 */
	ILinkObjectType getLinkObjectType(); 
}
