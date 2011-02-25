/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

/**
 * @author Stuart Moodie
 *
 */
public interface ITypedDrawingNodeAttribute extends IDrawingNodeAttribute {

	@Override
	INodeObjectType getObjectType();
}
