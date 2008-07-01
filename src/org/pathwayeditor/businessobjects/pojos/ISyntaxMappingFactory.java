/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.contectadapter.IContext;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibContext;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public interface ISyntaxMappingFactory {

	HibContext createHibContext(IContext context);
	
	/**
	 * @param objectType
	 * @return
	 */
	HibObjectType createHibObjectType(IObjectType objectType);

	
	/**
	 * @param objectType
	 * @return
	 */
	ILinkObjectType getLinkObjectType(HibObjectType hibObjectType);

	/**
	 * @param objectType
	 * @return
	 */
	IShapeObjectType getShapeObjectType(HibObjectType hibObjectType);

	IRootObjectType getRootObjectType(HibObjectType hibObjectType);
	
	IContext getContext(HibContext hibContext);
}