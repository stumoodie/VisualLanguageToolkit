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
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public interface IShapeNode extends IDrawingNode, IZOrderedObject, INodeChangeListenee {
	/**
	 * Gets the number of the links that are sourcing from this Shape node.
	 * @return the number of links.
	 */
	int getNumSourceLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a source.
	 * @return an iterator with all the links. 
	 */
	Iterator<ILinkEdge> sourceLinkIterator();
	
	/**
	 * Gets the number of the links that are targeting this Shape node.
	 * @return the number of links.
	 */
	int getNumTargetLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a target.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILinkEdge> targetLinkIterator();
	
		/**
	 * Gets the {@link IShapeAttribute} that is connected to the particular Shape Node.
	 * @return the ShapeAttribute.
	 */
	IShapeAttribute getAttribute();
	
	IShapeObjectType getObjectType();
}
