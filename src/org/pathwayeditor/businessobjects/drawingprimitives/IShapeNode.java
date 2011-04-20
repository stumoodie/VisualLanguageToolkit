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

package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;


/**
 * 
 * IShapeNode is an interface that defines the shape node. This interface should be
 * implemented as a facade wrapping an ICompoundNode that is associated with an
 * <code>IShapeAttribute</code>. This provides convenience methods and provides
 * a degree of backwards compatibility with the previous implementation of the
 * BusinessObjects.  
 *
 * @author Stuart Moodie
 *
 */
public interface IShapeNode extends ITypedDrawingNode {
	/**
	 * Gets the {@link IShapeAttribute} that is connected to the particular Shape Node.
	 * @return the ShapeAttribute.
	 */
	@Override
	IShapeAttribute getAttribute();

	/**
	 * Get an iterator to the source links associated with this node.
	 * @return the iterator to the compound edges, which cannot be null.
	 */
	Iterator<ICompoundEdge> sourceLinkIterator();

	/**
	 * Get the number of source links (out edges) associated with this shape node.
	 * @return the number of source links.
	 */
	int getNumSourceLinks();

	/**
	 * Get an iterator to the target links associated with this node.
	 * @return the iterator to the compound edges, which cannot be null.
	 */
	Iterator<ICompoundEdge> targetLinkIterator();
	
	/**
	 * Gets the number of the links that are targeting this Shape node.
	 * @return the number of links.
	 */
	int getNumTargetLinks();
	
}
