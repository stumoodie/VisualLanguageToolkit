package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

public interface IShapeNode {

	IModel getModel();
	
	IChildModel getChildModel();
	
	int getNumLinks();
	/**
	 * Provides and iterator for all the links associated with this shape.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILinkAttribute> linkIterator();

	int getNumSourceLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a source.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILinkAttribute> sourceLinkIterator();

	int getNumTargetLinks();
	
	IShapeNode getParentShape();
	
	/**
	 * Provides and iterator for all the links where this shape is a target.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILinkAttribute> targetLinkIterator();

	int getNumChildren();
	
	/**
	 * Provides and iterator for all the children of this shape.
	 * @return An new iterator to the collection of child shapes. 
	 */
	Iterator<IShapeNode> childShapeIterator();
	
	int getNumLabels();
	
	Iterator<ILabelAttribute> labelIterator();
	
}
