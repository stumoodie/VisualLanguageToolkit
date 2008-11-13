/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

/**
 * @author nhanlon
 *
 */
public class HibLabelNode extends HibCompoundNode implements ILabelNode {
	private HibLabelAttribute labelAttribute ;
	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibLabelNode(){
		super();
	}
	
	
	public HibLabelNode(HibCompoundNode parent, int index, HibProperty property){
		super(parent.getModel(), parent, index);
		ILabelAttributeDefaults labelAttributeDefaults = property.getDefinition().getLabelDefaults(); 
		int newSerial = this.getModel().getCanvas().getAttributeSerialCounter().nextIndex();
		this.changeLabelAttribute(new HibLabelAttribute(this.getModel().getCanvas(), newSerial, property, labelAttributeDefaults));
	}
	
	
	/**
	 * @param hibLabelAttribute
	 */
	public void changeLabelAttribute(HibLabelAttribute hibLabelAttribute) {
		if(this.labelAttribute != null){
			this.labelAttribute.setLabelNode(null) ;
		}
		if(hibLabelAttribute != null){
			hibLabelAttribute.setLabelNode(this);
		}
		this.labelAttribute = hibLabelAttribute;

	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode#getAttribute()
	 */
	public HibLabelAttribute getAttribute() {
		return this.labelAttribute;
	}

	void setAttribute(HibLabelAttribute labelAttribute) {
		this.labelAttribute = labelAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getObjectType()
	 */
	public INodeObjectType getObjectType() {
		return this.labelAttribute.getObjectType();
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
		if(removed){
			this.getModel().notifyNodeStructureChange(ModelStructureChangeType.DELETED, this);
		}
		else{
			this.getModel().notifyNodeStructureChange(ModelStructureChangeType.ADDED, this);
		}
	}

	
}
