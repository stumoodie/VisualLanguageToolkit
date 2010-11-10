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

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author smoodie
 *
 */
public class DrawingNodeFacade implements IDrawingNode {
	private final ICompoundNode graphNode;
	
	public DrawingNodeFacade(ICompoundNode graphNode){
		this.graphNode = graphNode;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getGraphElement()
	 */
	@Override
	public ICompoundNode getGraphElement() {
		return this.graphNode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getAttribute()
	 */
	@Override
	public IDrawingNodeAttribute getAttribute() {
		return (IDrawingNodeAttribute)this.graphNode.getAttribute();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.graphNode == null) ? 0 : this.graphNode.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IDrawingNode)) {
			return false;
		}
		IDrawingNode other = (IDrawingNode) obj;
		if (this.graphNode == null) {
			if (other.getGraphElement() != null) {
				return false;
			}
		} else if (!this.graphNode.equals(other.getGraphElement())) {
			return false;
		}
		return true;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.graphNode.getLevel();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getUniqueIndex()
	 */
	@Override
	public long getUniqueIndex() {
		return this.graphNode.getIndex();
	}

}
