package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.contextadapter.IContext;

/**
 * @author smoodie
 *
 */
public interface IObjectType extends Comparable<IObjectType> {

	IContext getDefiningContext();
	
	String getName();

	/**
	 * Description of the object type
	 * @return
	 */
	String getDescription();

	boolean equals(Object other);
	
	int hashCode();
}
