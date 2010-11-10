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

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.util.IFilterCriteria;
import uk.ac.ed.inf.graph.util.impl.FilteredIterator;

/**
 * @author smoodie
 *
 */
public class ModelFacade implements IModel {
	private final ICompoundGraph compoundGraph;

	public ModelFacade(ICompoundGraph compoundGraph){
		this.compoundGraph = compoundGraph;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#shapeNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> shapeNodeIterator() {
		FilteredIterator<ICompoundNode> iter = new FilteredIterator<ICompoundNode>(this.compoundGraph.nodeIterator(),
				new IFilterCriteria<ICompoundNode>() {
					@Override
					public boolean matched(ICompoundNode testObj) {
						return testObj.getAttribute() instanceof IShapeAttribute;
					}
				});
		return iter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#labelNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> labelNodeIterator() {
		FilteredIterator<ICompoundNode> iter = new FilteredIterator<ICompoundNode>(this.compoundGraph.nodeIterator(),
				new IFilterCriteria<ICompoundNode>() {
					@Override
					public boolean matched(ICompoundNode testObj) {
						return testObj.getAttribute() instanceof ILabelAttribute;
					}
				});
		return iter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkEdgeIterator()
	 */
	@Override
	public Iterator<ICompoundEdge> linkEdgeIterator() {
		return this.compoundGraph.edgeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numDrawingElements()
	 */
	@Override
	public int numDrawingElements() {
		return this.compoundGraph.numElements();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numDrawingNodes()
	 */
	@Override
	public int numDrawingNodes() {
		return this.compoundGraph.numNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numShapeNodes()
	 */
	@Override
	public int numShapeNodes() {
		int retVal = 0;
		Iterator<ICompoundNode> shapeNodeIterator = this.shapeNodeIterator();
		while(shapeNodeIterator.hasNext()){
			shapeNodeIterator.next();
			retVal++;
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numLabelNodes()
	 */
	@Override
	public int numLabelNodes() {
		int retVal = 0;
		Iterator<ICompoundNode> labelNodeIterator = this.labelNodeIterator();
		while(labelNodeIterator.hasNext()){
			labelNodeIterator.next();
			retVal++;
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numLinkEdges()
	 */
	@Override
	public int numLinkEdges() {
		return this.compoundGraph.numEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#drawingNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> drawingNodeIterator() {
		return this.compoundGraph.nodeIterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.compoundGraph == null) ? 0 : this.compoundGraph.hashCode());
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
		if (!(obj instanceof IModel)) {
			return false;
		}
		IModel other = (IModel) obj;
		if (this.compoundGraph == null) {
			if (other.getGraph() != null) {
				return false;
			}
		} else if (!this.compoundGraph.equals(other.getGraph())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getGraph()
	 */
	@Override
	public ICompoundGraph getGraph() {
		return this.compoundGraph;
	}

}
