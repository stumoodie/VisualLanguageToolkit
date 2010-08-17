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

package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ITypedDrawingNode;

import uk.ac.ed.inf.graph.compound.IRootCompoundNode;

/**
 * @author smoodie
 *
 */
public class RootNode extends TypedCommonNode implements IRootNode {
	
	public RootNode(IRootCompoundNode rootCompoundNode){
		super(rootCompoundNode);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getParentNode()
	 */
	@Override
	public ITypedDrawingNode getParentNode() {
		return this;
	}

	@Override
	public IRootAttribute getAttribute() {
		return (IRootAttribute)this.getCompoundGraphElement().getAttribute();
	}

}
