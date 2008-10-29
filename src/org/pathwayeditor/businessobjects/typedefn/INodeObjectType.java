/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

/**
 * @author smoodie
 *
 */
public interface INodeObjectType extends IObjectType {

	/**
	 * Get the parenting rules for this node object type, which must exist.
	 * @return the parenting rules, which cannot be null.
	 */
	IObjectTypeParentingRules getParentingRules();
	
}
