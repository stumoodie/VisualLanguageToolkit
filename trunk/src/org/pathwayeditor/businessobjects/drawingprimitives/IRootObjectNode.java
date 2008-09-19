package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface IRootObjectNode {
	
	
	/**
	 * Gets the graph this Label Node is assosiated with.
	 * @return the related graph.
	 */
	ICompoundGraph getGraph();
	
	/**
	 * Gets the index number for this object node.
	 * @return the index number.
	 */
	int getIndex();
	
	/**
	 * Gets the Compount Graph that this the child of this Object Node.
	 * @return
	 */
	IChildCompoundGraph getChildCompoundGraph();
	
	/**
	 * Gets the the number of shapes that are connected to this Root Object Node
	 * @return the number of links.
	 */
	int getNumShapes();
	
	/**
	 * Provides and iterator for all the children of this shape.
	 * @return An new iterator to the collection of child shapes. 
	 */
	Iterator<IShapeAttribute> childShapeIterator();
}
