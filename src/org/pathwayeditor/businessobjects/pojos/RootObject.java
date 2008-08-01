/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootObject;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeModel;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShape;

import uk.ed.inf.graph.compound.impl.CompoundNode;

/**
 * @author smoodie
 *
 */
public class RootObject implements IRootObject {
	private final CompoundNode rootNode;
	private final ShapeModel shapeModel;
	private final Canvas canvas;
	private final int creationSerial;
	
	public RootObject(Canvas canvas, CompoundNode rootNode, int creationSerial){
		this.rootNode = rootNode;
		this.creationSerial = creationSerial;
		this.canvas = canvas;
		this.shapeModel = new ShapeModel(this.rootNode.getChildCigraph());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#childShapeIterator()
	 */
	public Iterator<IShape> childShapeIterator() {
		return new NodeIterator<IShape, HibShape>(this.rootNode.childIterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getNumChildren()
	 */
	public int getNumShapes() {
		return this.rootNode.getChildCigraph().getNumNodes();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootObject#getShapeModel()
	 */
	public IShapeModel getShapeModel() {
		return this.shapeModel;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject#getCanvas()
	 */
	public ICanvas getCanvas() {
		return this.canvas;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasObject#getCreationSerial()
	 */
	public int getCreationSerial() {
		return this.creationSerial;
	}
}
