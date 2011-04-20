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

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;


/**
 * 
 * ILinkEdge is an interface defining a link egde. This is a facade that wraps an
 * instance of @{link ICompoundEdge} and makes manipulation of link more convenient. 
 *
 * @author Stuart Moodie
 *
 */
public interface ILinkEdge extends IDrawingElement {
	
	/**
	 * Gets the {@link ILinkAttribute} relates to this LinkEdge. 
	 * @return the ILinkAttribute. Cannot be null.
	 */
	@Override
	ILinkAttribute getAttribute();
	
	@Override
	ICompoundEdge getGraphElement();

	/**
	 * Gets the source (out node) for the link.
	 * @return the source compound node, which cannot be null.
	 */
	ICompoundNode getSourceShape();

	/**
	 * Gets the target (in node) for the link.
	 * @return the target compound nod, which cannot be null. 
	 */
	ICompoundNode getTargetShape();
	
}
