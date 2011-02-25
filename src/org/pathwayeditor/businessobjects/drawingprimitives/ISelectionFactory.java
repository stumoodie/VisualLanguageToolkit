/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.ISubCompoundGraphFactory;

/**
 * @author Stuart Moodie
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
	
//	/**
//	 * Iterator over all the drawing nodes added to this selection factory. 
//	 * @return the iterator, which cannot be null.
//	 */
//	Iterator<ICompoundNode> drawingNodeIterator();
//	
//	/**
//	 * Gets the number of drawing nodes added to this factory.
//	 * @return the number of drawing nodes.
//	 */
//	int numDrawingNodes();
//	
//	/**
//	 * Iterator over all the link edges added to this selection factory. 
//	 * @return the iterator, which cannot be null.
//	 */
//	Iterator<ICompoundEdge> linkEdgeIterator();
	
//	/**
//	 * Gets the number of link edges added to this factory.
//	 * @return the number of link edges.
//	 */
//	int numLinkEdges();

	/**
	 * Creates a new selection object based on the nodes and edges added to this factory.
	 * It is effectively a "view" of the model. A general selection is suitable for removal operations
	 * and can contain unattached links. It should not be used to generate a selection used in a copy or
	 * move operation.
	 * @return the drawing element selection object which cannot be null, but may be empty.
	 */
	IDrawingElementSelection createGeneralSelection();
	
	/**
	 * Creates a new selection object based on the nodes and edges added to this factory.
	 * It is effectively a "view" of the model. This can be used for copy and move operations.
	 * @return the drawing element selection object which cannot be null, but may be empty.
	 */
	IDrawingElementSelection createEdgeExcludedSelection();
	
	ISubCompoundGraphFactory getSubgraphFactory();
}
