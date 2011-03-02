/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import uk.ac.ed.inf.graph.compound.IChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * ISubModel is an interface defining the sub model of a model. A submodel can be though
 * of a model contained within another model. In this case this interface should be implemented
 * as a facade to an {@link IChildCompoundGraph}. This facade provides convenience methods
 * that provide access to the sub-model in terms of links, shapes and labels.
 * 
 * @author Stuart Moodie
 *
 */
public interface ISubModel {

	/**
	 * Returns the number of drawing elements that are owned by this submodel.
	 * @return the number of drawing elements.
	 */
	int numDrawingElements();
	
	/**
	 * Returns the number of drawing nodes that are owned by this submode.
	 * @return the number of drawing nodes.
	 */
	int numDrawingNodes();
	
	/**
	 * Number of shapes owned by this submodel.
	 * @return the number of shapes.
	 */
	int numShapeNodes();
	
	/**
	 * Num links owned by this shape model. 
	 * @return the number of links.
	 */
	int numLinkEdges();
	
	/**
	 * Number of labels owned by the shape that owns this submodel.
	 * @return number of labels.
	 */
	int numLabelNodes();
	
	
	/**
	 * Iterators over all the drawing nodes in this submodel 
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> drawingNodeIterator();

	/**
	 * Traverses over all the shapes in the sub-model
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> shapeNodeIterator();
	
	/**
	 * Traverses over the list of links in this sub-model.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundEdge> linkIterator();

	/**
	 * Traverses over the labels in this sub-model.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> labelIterator();

	/**
	 * Get the child compound graph that is wrapped by this instance.
	 * @return the child compound graph, which cannot be null.
	 */
	IChildCompoundGraph getChildCompoundGraph();

}
