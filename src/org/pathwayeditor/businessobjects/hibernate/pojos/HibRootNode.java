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

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(model=");
		builder.append(this.getModel());
		builder.append(", index=");
		builder.append(this.getIndex());
		builder.append(", removed=");
		builder.append(this.isRemoved());
		builder.append(", attribute=");
		builder.append(this.getAttribute());
		builder.append(")");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNode#removalAction()
	 */
	@Override
	protected void removalAction(boolean removed) {
		// will never be removed so do nothing.
	}
}
