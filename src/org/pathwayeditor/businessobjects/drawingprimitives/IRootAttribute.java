/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

/**
 * @author smoodie
 *
 */
public interface IRootAttribute extends ITypedDrawingNodeAttribute {

	@Override
	IRootNode getCurrentDrawingElement();

	@Override
	IRootObjectType getObjectType();

}
