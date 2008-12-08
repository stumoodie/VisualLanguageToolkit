package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public interface IShapeNode extends IDrawingNode, IZOrderedObject, INodeChangeListenee {
	/**
	 * Gets the number of the links that are sourcing from this Shape node.
	 * @return the number of links.
	 */
	int getNumSourceLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a source.
	 * @return an iterator with all the links. 
	 */
	Iterator<ILinkEdge> sourceLinkIterator();
	
	/**
	 * Gets the number of the links that are targeting this Shape node.
	 * @return the number of links.
	 */
	int getNumTargetLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a target.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILinkEdge> targetLinkIterator();
	
		/**
	 * Gets the {@link IShapeAttribute} that is connected to the particular Shape Node.
	 * @return the ShapeAttribute.
	 */
	IShapeAttribute getAttribute();
	
	IShapeObjectType getObjectType();
}
