/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabel;
import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.impl.ChildCompoundGraph;
import uk.ed.inf.graph.compound.impl.CompoundNode;
import uk.ed.inf.graph.compound.impl.CompoundNodeFactory;

/**
 * @author smoodie
 *
 */
public class ShapeModel implements IShapeModel {
	private final ChildCompoundGraph childGraph;
	
	public ShapeModel(ChildCompoundGraph childGraph){
		this.childGraph = childGraph;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#canCopyHere(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph)
	 */
	public boolean canCopyHere(ISelectionSubgraph selection) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#canCreateShape(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public boolean canCreateShape(IShapeObjectType newShapeType) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#canMoveHere(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph)
	 */
	public boolean canMoveHere(ISelectionSubgraph selection) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#copyHere(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph)
	 */
	public void copyHere(ISelectionSubgraph selection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#createShape(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public IShape createShape(IShapeObjectType shapeType) {
		CompoundNodeFactory nodeFactory = this.childGraph.nodeFactory();
		Shape shape = new Shape(shapeType);
		nodeFactory.setColourHandler(new ShapeNodeColourHandler(shape));
		CompoundNode node = nodeFactory.createNode();
		shape.setNode(node);
		return shape;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#getModel()
	 */
	public IModel getModel() {
		// TODO: 
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#getNumLabels()
	 */
	public int getNumLabels() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#getNumLinks()
	 */
	public int getNumLinks() {
		return this.childGraph.getNumEdges();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#getNumShapes()
	 */
	public int getNumShapes() {
		return this.childGraph.getNumNodes();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#labelIterator()
	 */
	public Iterator<ILabel> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#linkIterator()
	 */
	public Iterator<ILink> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#moveHere(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph)
	 */
	public void moveHere(ISelectionSubgraph selection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#shapeIterator()
	 */
	public Iterator<IShape> shapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrdering#getZOrderedIterator()
	 */
	public Iterator<IZOrderedObject> getZOrderedIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrdering#shiftOneBackwards(org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject)
	 */
	public void shiftOneBackwards(IZOrderedObject shape) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrdering#shiftOneForward(org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject)
	 */
	public void shiftOneForward(IZOrderedObject shape) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrdering#shiftToBack(org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject)
	 */
	public void shiftToBack(IZOrderedObject shape) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrdering#shiftToFront(org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject)
	 */
	public void shiftToFront(IZOrderedObject shape) {
		// TODO Auto-generated method stub

	}

}
