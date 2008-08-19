/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObjectSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IChildCompoundGraph;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalChildCompoundGraph;

/**
 * @author smoodie
 *
 */
public class ShapeModel extends ArchetypalChildCompoundGraph implements IChildCompoundGraph {
	private final ShapeFactory shapeFactory;
	private final ChildModelLinkFactory childLinkFactory;
	
	public ShapeModel(CommonModelNode root){
		super(root, new CopyBuilder());
		this.shapeFactory = new ShapeFactory(root);
		this.childLinkFactory = new ChildModelLinkFactory(root);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#canCopyHere(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph)
	 */
	public boolean canCopyHere(ICanvasObjectSelection canvasObjectSelection) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#canCreateShape(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public boolean canCreateShape(IShapeObjectType newShapeType) {
		return this.getRootNode().getObjectType().getParentingRules().isValidChild(newShapeType);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#canMoveHere(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph)
	 */
	public boolean canMoveHere(ICanvasObjectSelection canvasObjectSelection) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#copyHere(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph)
	 */
	public void copyHere(ICanvasObjectSelection canvasObjectSelection) {
		SelectionSubgraph selection = (SelectionSubgraph)canvasObjectSelection;
		super.copyHere(selection.createSubgraph());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#createShape(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public IShapeAttribute createShape(IShapeObjectType shapeType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#getModel()
	 */
	public Model getModel() {
		return (Model)super.getSuperGraph();
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
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#getNumShapes()
	 */
	public int getNumShapes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#labelIterator()
	 */
	public Iterator<ILabelAttribute> labelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#linkIterator()
	 */
	public Iterator<ILinkAttribute> linkIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#moveHere(org.pathwayeditor.businessobjects.drawingprimitives.ISelectionSubgraph)
	 */
	public void moveHere(ICanvasObjectSelection canvasObjectSelection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel#shapeIterator()
	 */
	public Iterator<IShapeAttribute> shapeIterator() {
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

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.IModifiableChildCompoundGraph#edgeFactory()
	 */
	public ChildModelLinkFactory edgeFactory() {
		return this.childLinkFactory;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.IModifiableChildCompoundGraph#nodeFactory()
	 */
	public ShapeFactory nodeFactory() {
		return this.shapeFactory;
	}
	
	@Override
	public CommonModelNode getRootNode(){
		return (CommonModelNode)super.getRootNode();
	}
}
