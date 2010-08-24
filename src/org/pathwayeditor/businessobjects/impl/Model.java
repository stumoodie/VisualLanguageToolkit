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

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.newimpl.CompoundGraph;
import uk.ac.ed.inf.graph.util.IFilterCriteria;
import uk.ac.ed.inf.graph.util.impl.FilteredIterator;

/**
 * @author smoodie
 *
 */
public class Model implements IModel {

	private final ICompoundGraph compoundGraph;
	private final ICanvas canvas;
	private IFilterCriteria<ICompoundNode> labelCriteria;
	private IFilterCriteria<ICompoundNode> shapeCriteria;
	
	public Model(ICanvas canvas, IRootAttribute rootAttribute){
		this.canvas = canvas;
		this.compoundGraph = new CompoundGraph(rootAttribute);
		this.labelCriteria = new IFilterCriteria<ICompoundNode>(){
			@Override
			public boolean matched(ICompoundNode testObj) {
				return testObj instanceof ILabelNode;
			}
		};
		this.shapeCriteria = new IFilterCriteria<ICompoundNode>(){
			@Override
			public boolean matched(ICompoundNode testObj) {
				return testObj instanceof IShapeNode;
			}
		};
	}
	
//	/**
//	 * @param canvas2
//	 * @param graph
//	 */
//	public Model(Canvas canvas, Model otherModel) {
//		// TODO Auto-generated constructor stub
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#addModelChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener)
	 */
	@Override
	public void addModelChangeListener(IModelChangeListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#removeModelChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener)
	 */
	@Override
	public void removeModelChangeListener(IModelChangeListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#modelChangeListenerIterator()
	 */
	@Override
	public Iterator<IModelChangeListener> modelChangeListenerIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee#setListenersEnabled(boolean)
	 */
	@Override
	public void setListenersEnabled(boolean enabled) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee#areListenersEnabled()
	 */
	@Override
	public boolean areListenersEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getCanvas()
	 */
	@Override
	public ICanvas getCanvas() {
		return this.canvas;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getRootNode()
	 */
	@Override
	public IRootNode getRootNode() {
		return new RootNode(this.compoundGraph.getRoot());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#createCopy(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	@Override
	public IModel createCopy(ICanvas canvas) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkEdgeFactory()
	 */
	@Override
	public ILinkEdgeFactory linkEdgeFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getCurrentState()
	 */
	@Override
	public IGraphMomento getCurrentState() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#restoreToState(org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento)
	 */
	@Override
	public void restoreToState(IGraphMomento stateToRestore) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#newSelectionFactory()
	 */
	@Override
	public ISelectionFactory newSelectionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#canRemoveSelection(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)
	 */
	@Override
	public boolean canRemoveSelection(IDrawingElementSelection selection) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#removeSubgraph(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)
	 */
	@Override
	public void removeSubgraph(IDrawingElementSelection selection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#drawingNodeIterator()
	 */
	@Override
	public Iterator<IDrawingNode> drawingNodeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#shapeNodeIterator()
	 */
	@Override
	public Iterator<IShapeNode> shapeNodeIterator() {
		FilteredIterator<ICompoundNode> filteredIter = new FilteredIterator<ICompoundNode>(this.compoundGraph.nodeIterator(), shapeCriteria);
		return new IterationCaster<IShapeNode, ICompoundNode>(filteredIter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#labelNodeIterator()
	 */
	@Override
	public Iterator<ILabelNode> labelNodeIterator() {
		FilteredIterator<ICompoundNode> filteredIter = new FilteredIterator<ICompoundNode>(this.compoundGraph.nodeIterator(), labelCriteria);
		return new IterationCaster<ILabelNode, ICompoundNode>(filteredIter);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#linkEdgeIterator()
	 */
	@Override
	public Iterator<ILinkEdge> linkEdgeIterator() {
		return new IterationCaster<ILinkEdge, ICompoundEdge>(this.compoundGraph.edgeIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#isValid()
	 */
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
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
		int count = 0;
		Iterator<IShapeNode> iter = this.shapeNodeIterator();
		while(iter.hasNext()) {
			iter.next();
			count++;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numLabelNodes()
	 */
	@Override
	public int numLabelNodes() {
		int count = 0;
		Iterator<ILabelNode> iter = this.labelNodeIterator();
		while(iter.hasNext()) {
			iter.next();
			count++;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#numLinkEdges()
	 */
	@Override
	public int numLinkEdges() {
		return this.compoundGraph.numEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IModel#getCompoundGraph()
	 */
	@Override
	public ICompoundGraph getCompoundGraph() {
		return this.compoundGraph;
	}

}
