/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

/**
 * @author nhanlon
 *
 */
public class HibRootNode extends HibCompoundNode implements IRootNode {
	private RootAttribute canvasAttribute = null;
	
	/**
	 * @param model
	 * @param rootNode
	 * @param nodeIdxIdx
	 */
	public HibRootNode(HibModel model, int nodeIdx, IRootObjectType objectType) {
		super(model, null, nodeIdx);
		HibCanvas canvas = model.getCanvas();
		this.canvasAttribute = new RootAttribute(canvas, canvas.getAttributeSerialCounter().nextIndex(), objectType);
	}

	public void setObjectType(IRootObjectType objectType){
		HibCanvas canvas = this.getModel().getCanvas();
		int nextIndex = canvas.getAttributeSerialCounter().nextIndex();
		this.canvasAttribute = new RootAttribute(canvas, nextIndex, objectType);
	}
	
	/**
	 * 
	 */
	HibRootNode() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getAttribute()
	 */
	public ICanvasAttribute getAttribute() {
		return this.canvasAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getObjectType()
	 */
	public IRootObjectType getObjectType() {
		return this.canvasAttribute.getObjectType();
	}

}
