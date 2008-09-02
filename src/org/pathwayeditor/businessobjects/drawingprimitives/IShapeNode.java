package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import uk.ed.inf.graph.compound.impl.CompoundGraph;

public interface IShapeNode extends IZOrderedObject {

	
	/**
	 * Gets the {@link CompoundGraph} this shape node belongs to/
	 * @return the Compound Graph. Cannot be null.
	 */
	ICompoundGraph getGraph();
	
	/**
	 * Gets the index of this Shape node in the graph.
	 * @return the index number.
	 */
	int getIndex();
	
	
	IChildCompoundGraph getChildModel();
	
	/**
	 * Gets the number of the links that are targeting this Shape node.
	 * @return the number of links.
	 */
	int getNumLinks();
	
	/**
	 * Provides and iterator for all the links associated with this shape.
	 * @return an iterator with all the links. 
	 */
	Iterator<ILinkAttribute> linkIterator();
	
	/**
	 * Gets the number of the links that are sourcing from this Shape node.
	 * @return the number of links.
	 */
	int getNumSourceLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a source.
	 * @return an iterator with all the links. 
	 */
	Iterator<ILinkAttribute> sourceLinkIterator();
	
	/**
	 * Gets the number of the links that are targeting this Shape node.
	 * @return the number of links.
	 */
	int getNumTargetLinks();
	
	/**
	 * Gets the Shape node that is the parent of the current Shape node.
	 * @return the parent node.
	 */
	IShapeNode getParentShape();
	
	/**
	 * Provides and iterator for all the links where this shape is a target.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILinkAttribute> targetLinkIterator();
	
	/**
	 * Gets the numerical value of the children of this Shape Node.
	 * @return the number of the children.
	 */
	int getNumChildren();
	
	/**
	 * Provides and iterator for all the children of this shape.
	 * @return An new iterator to the collection of child shapes. 
	 */
	Iterator<IShapeNode> childShapeIterator();
	
	/**
	 * Gets the numerical value of the labels of this Shape Node.
	 * @return the number of the labels.
	 */	
	int getNumLabels();
	
	/**
	 * Gets an {@link Iterator} containing all the Labels related to this Shape Node.
	 * @return an iterator with all the Labels.
	 */
	Iterator<ILabelAttribute> labelIterator();
	
	/**
	 * Gets the {@link ShapeAttribute} that is connected to the particular Shape Node.
	 * @return the ShapeAttribute.
	 */
	IShapeAttribute getAttribute();
}
