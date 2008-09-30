/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.typedefn.IDefaultLabelAttributes;
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
		IDefaultLabelAttributes defaultLabelAttributes = property.getDefinition().getLabelDefaults(); 
		int newSerial = this.getModel().getCanvas().getAttributeSerialCounter().nextIndex();
		this.changeLabelAttribute(new HibLabelAttribute(this.getModel().getCanvas(), newSerial, property, defaultLabelAttributes));
	}
	
	
	/**
	 * @param hibLabelAttribute
	 */
	public void changeLabelAttribute(HibLabelAttribute hibLabelAttribute) {
		if(hibLabelAttribute != null){
			hibLabelAttribute.getLabelNode().setAttribute(null);
		}
		if(this.labelAttribute != null){
			this.labelAttribute.setLabelNode(null);
		}
		this.labelAttribute = hibLabelAttribute;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode#getAttribute()
	 */
	public HibLabelAttribute getAttribute() {
		return this.labelAttribute;
	}

	public void setAttribute(HibLabelAttribute labelAttribute) {
		this.labelAttribute = labelAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getObjectType()
	 */
	public INodeObjectType getObjectType() {
		return this.labelAttribute.getObjectType();
	}
	
	
}
