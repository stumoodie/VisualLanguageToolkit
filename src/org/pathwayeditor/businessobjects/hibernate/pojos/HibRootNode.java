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
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibRootNode() {
		super();
	}

	/**
	 * Constructor that creates a new instance of the root node. 
	 * @param model
	 * @param nodeIdx
	 * @param objectType
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
