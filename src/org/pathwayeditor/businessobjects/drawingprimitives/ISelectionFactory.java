/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.ISubCompoundGraphFactory;

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
