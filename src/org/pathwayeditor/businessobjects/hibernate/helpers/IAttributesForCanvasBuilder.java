/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * @author nhanlon
 *         Given a HibCanvas, iterate through its associated LinkAttributes, ensuring they (and their entities) are initialised by Hibernate and
 *         then injecting their properties with corresponding property definitions
 */
public interface IAttributesForCanvasBuilder {
	
	/**
	 * @param hibCanvas
	 * Given a HibCanvas, iterate through its associated Attributes, ensuring they (and their entities) are initialised by Hibernate
	 */
	public void initAttributes(HibCanvas hibCanvas);
	
	/**
	 * @param objectType not null but may not have any property definition
	 * @param shapeAttr not null but may not have any properties
	 *  Iterate through all the property definitions for a given objectType and inject them into their corresponding properties
	 *  which are attached to a given ICanvasAttribute.
	 */
	public void injectPropertyDefinitions(IObjectType objectType, ICanvasAttribute shapeAttr);

}
