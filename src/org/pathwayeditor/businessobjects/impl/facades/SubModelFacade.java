/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl.facades;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;

import uk.ac.ed.inf.graph.compound.IChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.util.IFilterCriteria;
import uk.ac.ed.inf.graph.util.impl.FilteredIterator;

/**
 * @author Stuart Moodie
 *
 */
public class SubModelFacade implements ISubModel {
	private final IChildCompoundGraph childCompoundGraph;
	
	public SubModelFacade(IChildCompoundGraph childCompoundGraph){
		this.childCompoundGraph = childCompoundGraph;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#numDrawingElements()
	 */
	@Override
	public int numDrawingElements() {
		return this.childCompoundGraph.numElements();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#numDrawingNodes()
	 */
	@Override
	public int numDrawingNodes() {
		return this.childCompoundGraph.numNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#numShapeNodes()
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#numLinkEdges()
	 */
	@Override
	public int numLinkEdges() {
		int retVal = 0;
		Iterator<ICompoundEdge> linkEdgeIterator = this.linkIterator();
		while(linkEdgeIterator.hasNext()){
			linkEdgeIterator.next();
			retVal++;
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#numLabelNodes()
	 */
	@Override
	public int numLabelNodes() {
		int retVal = 0;
		Iterator<ICompoundNode> labelNodeIterator = this.labelIterator();
		while(labelNodeIterator.hasNext()){
			labelNodeIterator.next();
			retVal++;
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#drawingNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> drawingNodeIterator() {
		return this.childCompoundGraph.nodeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#shapeNodeIterator()
	 */
	@Override
	public Iterator<ICompoundNode> shapeNodeIterator() {
		FilteredIterator<ICompoundNode> iter = new FilteredIterator<ICompoundNode>(this.childCompoundGraph.nodeIterator(),
				new IFilterCriteria<ICompoundNode>(){

					@Override
					public boolean matched(ICompoundNode testObj) {
						return testObj.getAttribute() instanceof IShapeAttribute;
					}
			
		});
		return iter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#linkIterator()
	 */
	@Override
	public Iterator<ICompoundEdge> linkIterator() {
		return this.childCompoundGraph.edgeIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#labelIterator()
	 */
	@Override
	public Iterator<ICompoundNode> labelIterator() {
		FilteredIterator<ICompoundNode> iter = new FilteredIterator<ICompoundNode>(this.childCompoundGraph.nodeIterator(),
				new IFilterCriteria<ICompoundNode>(){
					@Override
					public boolean matched(ICompoundNode testObj) {
						return testObj.getAttribute() instanceof ILabelAttribute;
					}
		});
		return iter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.childCompoundGraph == null) ? 0 : this.childCompoundGraph.hashCode());
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
		if (!(obj instanceof ISubModel)) {
			return false;
		}
		ISubModel other = (ISubModel) obj;
		if (this.childCompoundGraph == null) {
			if (other.getChildCompoundGraph() != null) {
				return false;
			}
		} else if (!this.childCompoundGraph.equals(other.getChildCompoundGraph())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#getChildCompoundGraph()
	 */
	@Override
	public IChildCompoundGraph getChildCompoundGraph() {
		return this.childCompoundGraph;
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.childCompoundGraph);
		buf.append(")");
		return buf.toString();
	}
}
