/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;


/**
 * @author smoodie
 *
 */
public interface IDrawingNode extends IDrawingElement {

	int getIndex();
	
	ISubModel getSubModel();
	
	IDrawingNode getParent();
	
	INodeObjectType getObjectType();
	
//	Iterator<IDrawingNode> levelOrderIterator();
}
