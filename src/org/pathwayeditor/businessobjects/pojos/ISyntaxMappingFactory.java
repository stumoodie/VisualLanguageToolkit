/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * @author smoodie
 *
 */
public interface ISyntaxMappingFactory {

	/**
	 * @param objectType
	 * @return
	 */
	HibObjectType createHibLinkObjectType(ILinkObjectType objectType);

	/**
	 * @param objectType
	 * @return
	 */
	ILinkObjectType getLinkObjectType(HibObjectType objectType);

}