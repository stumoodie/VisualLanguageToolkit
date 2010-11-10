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

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author smoodie
 *
 */
public class LinkEdgeFacade implements ILinkEdge {
	private ICompoundEdge linkEdge;
	
	public LinkEdgeFacade(ICompoundEdge linkEdge){
		this.linkEdge = linkEdge;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getGraphElement()
	 */
	@Override
	public ICompoundEdge getGraphElement() {
		return this.linkEdge;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getAttribute()
	 */
	@Override
	public ILinkAttribute getAttribute() {
		return (ILinkAttribute)this.linkEdge.getAttribute();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getSourceShape()
	 */
	@Override
	public ICompoundNode getSourceShape() {
		return this.linkEdge.getConnectedNodes().getOutNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getTargetShape()
	 */
	@Override
	public ICompoundNode getTargetShape() {
		return this.linkEdge.getConnectedNodes().getInNode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.linkEdge == null) ? 0 : this.linkEdge.hashCode());
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
		if (!(obj instanceof ILinkEdge)) {
			return false;
		}
		ILinkEdge other = (ILinkEdge) obj;
		if (this.linkEdge == null) {
			if (other.getGraphElement() != null) {
				return false;
			}
		} else if (!this.linkEdge.equals(other.getGraphElement())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.linkEdge.getLevel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getUniqueIndex()
	 */
	@Override
	public long getUniqueIndex() {
		return this.linkEdge.getIndex();
	}

}
