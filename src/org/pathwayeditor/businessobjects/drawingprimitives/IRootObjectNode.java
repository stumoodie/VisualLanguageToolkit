/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface IRootObjectNode {
	
	ICompoundGraph getGraph();
	
	int getIndex();
	
	IChildCompoundGraph getChildCompoundGraph();
	
	int getNumShapes();
	
	/**
	 * Provides and iterator for all the children of this shape.
	 * @return An new iterator to the collection of child shapes. 
	 */
	Iterator<IShapeAttribute> childShapeIterator();
}
