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

package org.pathwayeditor.figure.rendering;

import org.pathwayeditor.figure.geometry.Envelope;

/**
 * IAnchorLocatorFactory ia an interface that defines a factory for creating
 * new instances of anchor locators. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnchorLocatorFactory {

	/**
	 * Creates a new link anchor locator and uses the the convex hull of the owning shape
	 * in its calculations.  
	 * @return the new anchor locator
	 */
	IAnchorLocator createAnchorLocator();
	
	/**
	 * Creates a new link anchor locator that uses the convex hull provides for its
	 * calculations.
	 * @param newBounds the to calculate from, which cannot be null.
	 * @return the new anchor locator.
	 * @throws IllegalArgumentException if <code>newHull == null</code>.
	 */
	IAnchorLocator createAnchorLocator(Envelope newBounds);
	
}
