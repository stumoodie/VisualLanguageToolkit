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

import java.util.Iterator;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;


/**
 * @author smoodie
 *
 */
public interface IModel {

	/**
	 * Returns all the drawing nodes in this model.
	 * @return the drawing node iterator.
	 */
	Iterator<ICompoundNode> drawingNodeIterator();
	
	/**
	 * Returns a shape node iterator for all the shape nodes held in this model.
	 * @return the shape node iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> shapeNodeIterator();
	
	
	/**
	 * Returns an iterator for all the label nodes held in this model.
	 * @return the label node iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> labelNodeIterator();
	
	/**
	 * Returns an iterator for all the link edges in this model
	 * @return the link edge iterator.
	 */
	Iterator<ICompoundEdge> linkEdgeIterator();
	
	/**
	 * Provides the number of drawing elements in this model.
	 * @return the number of drawing elements.
	 */
	int numDrawingElements();
	
	/**
	 * Provides the number of drawing nodes in this model.
	 * @return the number of drawing nodes.
	 */
	int numDrawingNodes();
	
	/**
	 * Provides the number of shape nodes in this model.
	 * @return the number of shape nodes.
	 */
	int numShapeNodes();
	
	/**
	 * Provides the number of label nodes in this model.
	 * @return the number of label nodes.
	 */
	int numLabelNodes();
	
	
	/**
	 * Provides the number of link edges in this model.
	 * @return the number of link edges.
	 */
	int numLinkEdges();

	ICompoundGraph getGraph();
}
