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

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author smoodie
 *
 */
public class ShapeNodeFacade implements IShapeNode {
	private final ICompoundNode graphShapeNode;
	
	public ShapeNodeFacade(ICompoundNode graphShapeNode){
		this.graphShapeNode = graphShapeNode;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getGraphElement()
	 */
	@Override
	public ICompoundNode getGraphElement() {
		return this.graphShapeNode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getAttribute()
	 */
	@Override
	public IShapeAttribute getAttribute() {
		return (IShapeAttribute)this.graphShapeNode.getAttribute();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#sourceLinkIterator()
	 */
	@Override
	public Iterator<ICompoundEdge> sourceLinkIterator() {
		return this.graphShapeNode.getOutEdgeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#targetLinkIterator()
	 */
	@Override
	public Iterator<ICompoundEdge> targetLinkIterator() {
		return this.graphShapeNode.getInEdgeIterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.graphShapeNode == null) ? 0 : this.graphShapeNode.hashCode());
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
		if (!(obj instanceof IShapeNode)) {
			return false;
		}
		IShapeNode other = (IShapeNode) obj;
		if (this.graphShapeNode == null) {
			if (other.getGraphElement() != null) {
				return false;
			}
		} else if (!this.graphShapeNode.equals(other.getGraphElement())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.graphShapeNode.getLevel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getUniqueIndex()
	 */
	@Override
	public long getUniqueIndex() {
		return this.graphShapeNode.getIndex();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getNumSourceLinks()
	 */
	@Override
	public int getNumSourceLinks() {
		return this.graphShapeNode.getOutDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode#getNumTargetLinks()
	 */
	@Override
	public int getNumTargetLinks() {
		return this.graphShapeNode.getInDegree();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#isDescendent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement)
	 */
	@Override
	public boolean isDescendent(IDrawingElement testElement) {
		return this.graphShapeNode.isDescendent(testElement.getGraphElement());
	}

}
