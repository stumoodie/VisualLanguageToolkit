/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

/**
 * @author nhanlon
 *
 */
public class HibRootNode extends HibCompoundNode implements IRootNode {
	private HibRootAttribute canvasAttribute = null;
	
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
	 */
	public HibRootNode(HibModel model, int nodeIdx, HibRootAttribute rootAttribute) {
		super(model, null, nodeIdx);
		this.canvasAttribute = rootAttribute;
		this.canvasAttribute.setRootNode(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getAttribute()
	 */
	public HibRootAttribute getAttribute() {
		return this.canvasAttribute;
	}

	void setAttribute(HibRootAttribute rootAttribute) {
		this.canvasAttribute = rootAttribute;
	}
	
	@Override
	public HibRootNode getParentNode() {
		return this;
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode#isValid()
	 */
	@Override
	public boolean isValid() {
		return this.canvasAttribute != null && this.canvasAttribute.isValid()
			// althought the compound graph expects the root node to return a reference to itself
			// the hibernate schema expects the parent to have a root that it null.
			&& super.getHibParentNode() == null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLabelSubModel()
	 */
	public ISubModel getLabelSubModel() {
		return this.getSubModel();
	}
}
