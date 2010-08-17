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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdgeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener;

import uk.ac.ed.inf.graph.compound.IChildCompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundGraphCopyBuilder;
import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author smoodie
 *
 */
public class SubModel implements ISubModel {
	private final IChildCompoundGraph childCompoundGraph;
	
	public SubModel(IChildCompoundGraph childCompoundGraph){
		this.childCompoundGraph = childCompoundGraph;
	}
	
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#getModel()
	 */
	@Override
	public IModel getModel() {
		return getMapper().getModel(this.childCompoundGraph.getSuperGraph());
	}

	private IBusinessObjectGraphElementMapper getMapper(){
		return ((RootAttribute)this.childCompoundGraph.getRoot().getAttribute()).getCanvas().getMapper();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#getRootNode()
	 */
	@Override
	public IDrawingNode getRootNode() {
		return getMapper().getDrawingNode((ICompoundNode)this.childCompoundGraph.getRoot());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#shapeNodeFactory()
	 */
	@Override
	public IShapeNodeFactory shapeNodeFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#linkEdgeFactory()
	 */
	@Override
	public ILinkEdgeFactory linkEdgeFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#canCopyHere(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)
	 */
	@Override
	public boolean canCopyHere(IDrawingElementSelection canvasObjectSelection) {
		ICompoundGraphCopyBuilder copyBuilder = this.childCompoundGraph.newCopyBuilder();
		copyBuilder.setElementAttributeFactory(null); //TODO
		copyBuilder.setSourceSubgraph(canvasObjectSelection.getSubGraph());
		return copyBuilder.canCopyHere();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#copyHere(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)
	 */
	@Override
	public void copyHere(IDrawingElementSelection canvasObjectSelection) {
		ICompoundGraphCopyBuilder copyBuilder = this.childCompoundGraph.newCopyBuilder();
		copyBuilder.setElementAttributeFactory(null); //TODO
		copyBuilder.setSourceSubgraph(canvasObjectSelection.getSubGraph());
		copyBuilder.makeCopy();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#getCopiedElements()
	 */
	@Override
	public IDrawingElementSelection getCopiedElements() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#canMoveHere(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)
	 */
	@Override
	public boolean canMoveHere(IDrawingElementSelection canvasObjectSelection) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#moveHere(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)
	 */
	@Override
	public void moveHere(IDrawingElementSelection canvasObjectSelection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#getMovedElements()
	 */
	@Override
	public IDrawingElementSelection getMovedElements() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#numShapeNodes()
	 */
	@Override
	public int numShapeNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#numLinkEdges()
	 */
	@Override
	public int numLinkEdges() {
		return this.childCompoundGraph.numEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#numLabelNodes()
	 */
	@Override
	public int numLabelNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#preOrderTraveralIterator()
	 */
	@Override
	public Iterator<IDrawingNode> preOrderTraveralIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#levelOrderTraveralIterator()
	 */
	@Override
	public Iterator<IDrawingNode> levelOrderTraveralIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#drawingNodeIterator()
	 */
	@Override
	public Iterator<IDrawingNode> drawingNodeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#shapeNodeIterator()
	 */
	@Override
	public Iterator<IShapeNode> shapeNodeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#linkIterator()
	 */
	@Override
	public Iterator<ILinkEdge> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#labelIterator()
	 */
	@Override
	public Iterator<ILabelNode> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISubModel#getChildCompoundGraph()
	 */
	@Override
	public IChildCompoundGraph getChildCompoundGraph() {
		return this.childCompoundGraph;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelSubModel#labelNodeFactory()
	 */
	@Override
	public ILabelNodeFactory labelNodeFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
