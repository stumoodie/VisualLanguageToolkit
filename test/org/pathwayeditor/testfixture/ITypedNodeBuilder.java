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

package org.pathwayeditor.testfixture;

import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;

/**
 * @author smoodie
 *
 */
public interface ITypedNodeBuilder extends IDrawingNodeBuilder {

	@Override
	ITypedDrawingNode getNode();

	ITypedNodeConstructor getNodeConstructor();

	/**
	 * @param nodeConstructor
	 */
	void setNodeConstructor(ITypedNodeConstructor nodeConstructor);
	
}
