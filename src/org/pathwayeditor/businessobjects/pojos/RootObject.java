/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObject;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;

/**
 * @author smoodie
 *
 */
public class RootObject extends CommonCanvasObject implements IRootObject {
	private ShapeModel shapeModel;
	
	/**
	 * @param superGraph
	 * @param index
	 */
	public RootObject(Model superGraph, int index) {
		super(superGraph, index);
	}

	/**
	 * @param model
	 * @param newIndexValue
	 * @param otherRootNode
	 */
	public RootObject(Model model, int newIndexValue, RootObject otherRootNode) {
		super(model, newIndexValue);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode#getChildCigraph()
	 */
	@Override
	public ShapeModel getChildCigraph() {
		return this.shapeModel;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#childShapeIterator()
	 */
	public Iterator<IShape> childShapeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getNumShapes()
	 */
	public int getNumShapes() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getShapeModel()
	 */
	public IShapeModel getShapeModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject#getCanvas()
	 */
	public ICanvas getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject#getCreationSerial()
	 */
	public int getCreationSerial() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode#createChildCompoundGraph(uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode)
	 */
	@Override
	protected void createChildCompoundGraph(ArchetypalCompoundNode rootNode) {
		this.shapeModel = new ShapeModel((RootObject)rootNode);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.CommonCanvasObject#getObjectType()
	 */
	@Override
	public INodeObjectType getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

}
