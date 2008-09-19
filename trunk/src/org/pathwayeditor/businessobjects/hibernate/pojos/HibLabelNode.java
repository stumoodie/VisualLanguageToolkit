/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.typedefn.IDefaultLabelAttributes;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

/**
 * @author nhanlon
 *
 */
public class HibLabelNode extends HibCompoundNode implements ILabelNode {
	private HibLabelAttribute labelAttribute ;
	
	HibLabelNode(){
		super();
	}
	
	
	public HibLabelNode(HibCompoundNode parent, int index, HibProperty property){
		super(parent.getModel(), parent, index);
		IDefaultLabelAttributes defaultLabelAttributes = property.getDefinition().getLabelDefaults(); 
		this.labelAttribute = new HibLabelAttribute(this.getModel().getCanvas(), property, defaultLabelAttributes);
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode#getAttribute()
	 */
	public ILabelAttribute getAttribute() {
		return this.labelAttribute;
	}

	public HibLabelAttribute getLabelAttribute() {
		return this.labelAttribute;
	}

	public void setLabelAttribute(HibLabelAttribute labelAttribute) {
		this.labelAttribute = labelAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getFirstObject()
	 */
	public IZOrderedObject getFirstObject() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getLastObject()
	 */
	public IZOrderedObject getLastObject() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getNextObject()
	 */
	public IZOrderedObject getNextObject() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getPreviousObject()
	 */
	public IZOrderedObject getPreviousObject() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getObjectType()
	 */
	public INodeObjectType getObjectType() {
		return this.labelAttribute.getObjectType();
	}
	
	
}
