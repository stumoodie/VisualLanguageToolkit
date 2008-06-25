/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface IRootObject {

	IShapeModel getShapeModel();
	
	int getNumLinks();
	/**
	 * Provides and iterator for all the links associated with this shape.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILink> linkIterator();

	int getNumOutLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a source.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILink> outLinkIterator();

	int getNumInLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a target.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILink> inLinkIterator();

	int getNumChildren();
	
	/**
	 * Provides and iterator for all the children of this shape.
	 * @return An new iterator to the collection of child shapes. 
	 */
	Iterator<IShape> childShapeIterator();
}
