/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

/**
 * @author smoodie
 *
 */
public interface ITypedDrawingNodeAttribute extends IDrawingNodeAttribute {

	@Override
	INodeObjectType getObjectType();
}
