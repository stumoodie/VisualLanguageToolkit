package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService;

/**
 * @author smoodie
 *
 */
public interface IObjectType extends Comparable<IObjectType> {

	INotationSyntaxService getDefiningContext();
	
	String getName();

	/**
	 * Description of the object type
	 * @return
	 */
	String getDescription();

	boolean equals(Object other);
	
	int hashCode();
}
