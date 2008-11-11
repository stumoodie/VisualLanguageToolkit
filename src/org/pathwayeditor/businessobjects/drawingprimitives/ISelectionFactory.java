/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface ISelectionFactory {

	/** 
	 * Add a shape to the selection 
	 * @param selectedNode the drawing node to be selected. Cannot be null.
	 * @throws NullPointerException if selectedShape is null.
	 */
	void addDrawingNode(IDrawingNode selectedNode);

	/**
	 * Add a link to the selection
	 * @param selectedLink the link to be selected. Cannot be null.
	 * @throws NullPointerException if selectedLink is null.
	 */
	void addLink(ILinkEdge selectedLink);
	
	/**
	 * Iterator over all the drawing nodes added to this selection factory. 
	 * @return the iterator, which cannot be null.
	 */
	Iterator<IDrawingNode> drawingNodeIterator();
	
	/**
	 * Gets the number of drawing nodes added to this factory.
	 * @return the number of drawing nodes.
	 */
	int numDrawingNodes();
	
	/**
	 * Iterator over all the link edges added to this selection factory. 
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ILinkEdge> linkEdgeIterator();
	
	/**
	 * Creates a new selection object based on the nodes and edges added to this factory.
	 * It is effectively a "view" of the model.
	 * @return the drawing element selection object which cannot be null, but may be empty.
	 */
	IDrawingElementSelection createSelection();
	
	/**
	 * Gets the number of link edges added to this factory.
	 * @return the number of link edges.
	 */
	int numLinkEdges();
	
	/**
	 * Gets the model that this selection refers to.
	 * @return the model which cannot be null.
	 */
	IModel getModel();
}
