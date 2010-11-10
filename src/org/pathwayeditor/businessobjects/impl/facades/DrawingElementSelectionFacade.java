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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ISubCompoundGraph;
import uk.ac.ed.inf.graph.util.IFilterCriteria;
import uk.ac.ed.inf.graph.util.impl.FilteredIterator;

/**
 * @author smoodie
 *
 */
public class DrawingElementSelectionFacade implements IDrawingElementSelection {
	private ISubCompoundGraph subCompoundGraph;
	
	public DrawingElementSelectionFacade(ISubCompoundGraph subCompoundGraph){
		this.subCompoundGraph = subCompoundGraph;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#numTopDrawingNodes()
	 */
	@Override
	public int numTopDrawingNodes() {
		return this.subCompoundGraph.getNumTopNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#topDrawingNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> topDrawingNodeIterator() {
		return this.subCompoundGraph.topNodeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#isConsistentSnapShot()
	 */
	@Override
	public boolean isConsistentSnapShot() {
		return this.subCompoundGraph.isConsistentSnapShot();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#getNumNodes()
	 */
	@Override
	public int getNumNodes() {
		return this.subCompoundGraph.getNumNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#drawingNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> drawingNodeIterator() {
		return this.subCompoundGraph.nodeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#shapeNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> shapeNodeIterator() {
		FilteredIterator<ICompoundNode> iter = new FilteredIterator<ICompoundNode>(this.subCompoundGraph.nodeIterator(), new IFilterCriteria<ICompoundNode>() {
			@Override
			public boolean matched(ICompoundNode testObj) {
				return testObj.getAttribute() instanceof IShapeAttribute;
			}
		});
		return iter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#labelNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> labelNodeIterator() {
		FilteredIterator<ICompoundNode> iter = new FilteredIterator<ICompoundNode>(this.subCompoundGraph.nodeIterator(), new IFilterCriteria<ICompoundNode>() {
			@Override
			public boolean matched(ICompoundNode testObj) {
				return testObj.getAttribute() instanceof ILabelAttribute;
			}
		});
		return iter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#getNumEdges()
	 */
	@Override
	public int getNumEdges() {
		return this.getNumEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#linkEdgeIterator()
	 */
	@Override
	public Iterator<ICompoundEdge> linkEdgeIterator() {
		return this.subCompoundGraph.edgeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#containsNode(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)
	 */
	@Override
	public boolean containsNode(IDrawingNode node) {
		return this.subCompoundGraph.containsNode(node.getGraphElement());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#containsEdge(org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge)
	 */
	@Override
	public boolean containsEdge(ILinkEdge linkEdge) {
		return this.subCompoundGraph.containsEdge(linkEdge.getGraphElement());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#hasDanglingEdges()
	 */
	@Override
	public boolean hasDanglingEdges() {
		return !this.subCompoundGraph.isInducedSubgraph();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection#getSubgraph()
	 */
	@Override
	public ISubCompoundGraph getSubgraph() {
		return this.subCompoundGraph;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.subCompoundGraph == null) ? 0 : this.subCompoundGraph.hashCode());
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
		if (!(obj instanceof IDrawingElementSelection)) {
			return false;
		}
		IDrawingElementSelection other = (IDrawingElementSelection) obj;
		if (this.subCompoundGraph == null) {
			if (other.getSubgraph() != null) {
				return false;
			}
		} else if (!this.subCompoundGraph.equals(other.getSubgraph())) {
			return false;
		}
		return true;
	}

}
