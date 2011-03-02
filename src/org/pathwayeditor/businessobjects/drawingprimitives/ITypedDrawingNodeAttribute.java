/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

/**
 * ITypedDrawingNodeAttribute is an attribite of a node that has a node object type
 * associated with it.
 * 
 * @author Stuart Moodie
 *
 */
public interface ITypedDrawingNodeAttribute extends IDrawingNodeAttribute {

	@Override
	INodeObjectType getObjectType();
}
