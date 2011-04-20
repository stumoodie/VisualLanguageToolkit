/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundEdgeFactory;

/**
 * ILinkEdgeFactory is an interface that defines a factory to create links. This should be implemented
 * as a facade that wraps an instance of {@link ICompoundEdgeFactory}. The interface provides convenience methods
 * hide some of the complexity of creating a compound edge associated with a ILinkAttribute. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ILinkEdgeFactory {

	/**
	 * Get the compound edge factory wrapped by this instance.
	 * @return the compound edge factory.
	 */
	ICompoundEdgeFactory getGraphEdgeFactory();
	
	/**
	 * Tests if the source and target node pair are non-null and belong to the same canvas as this
	 * factory. 
	 * @return true if the node pair is valid, false otherwise.
	 */
	boolean isValidShapeNodePair(IShapeNode source, IShapeNode target);
	
	/**
	 * Sets the node pair to be used when creating the new link. 
	 * @param source the source node, which cannot be null.
	 * @param target the target node, which cannot be null.
	 * @throws IllegalArgumentException if <code>isValidNodePair(source, target) == false</code>.
	 */
	void setShapeNodePair(IShapeNode source, IShapeNode target);
	
	/**
	 * Sets the object type to use when creating the new node.
	 * @param objectType the object type, which can be null.
	 */
	void setObjectType(ILinkObjectType objectType);
	
	/**
	 * Returns the currently set object type.
	 * @return the current object type, which can be null.
	 */
	ILinkObjectType getCurrentObjectType();
	
	/**
	 * Tests if the link can be created given the nodes that are specified and the object type.
	 * @return true of the link can be created, false otherwise.
	 */
	boolean canCreateLink();
	
	/**
	 * Creates a new link edge using the node pair and object type set in this factory.
	 * @return the newly created edge added to the sub-canvas specified by <code>getOwningSubCanvas</code>
	 * @throws IllegalStateException if <code>canCreateLink() == false</code>.
	 */
	ILinkEdge createLinkEdge();
}
