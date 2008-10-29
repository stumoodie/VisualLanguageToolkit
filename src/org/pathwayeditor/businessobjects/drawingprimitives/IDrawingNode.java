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
	
	ISubModel getSubCanvas();
	
	IDrawingNode getParent();
	
	INodeObjectType getObjectType();
	
//	Iterator<IDrawingNode> levelOrderIterator();
}
