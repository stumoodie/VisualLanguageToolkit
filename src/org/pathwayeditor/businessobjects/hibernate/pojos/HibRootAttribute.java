/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

/**
 * @author smoodie
 *
 */
public class HibRootAttribute extends HibCanvasAttribute {
	private HibObjectType hibObjectType;
	private IRootObjectType objectType;
	private IRootNode rootNode;

	/**
	 * @deprecated Only to be used by hibernate, not application code.
	 */
	HibRootAttribute(){
		
	}
	
	public HibRootAttribute(HibCanvas canvas, int creationSerial, IRootObjectType objectType, HibObjectType hibObjectType) {
		super(canvas, creationSerial);
		this.objectType = objectType;
		this.hibObjectType = hibObjectType;
	}

	public HibRootAttribute(HibCanvas canvas, int creationSerial, HibRootAttribute otherAttribute) {
		super(canvas, creationSerial);
		this.objectType = otherAttribute.getObjectType();
		this.hibObjectType = otherAttribute.getHibObjectType();
	}

	@Override
	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
		this.objectType = (IRootObjectType)objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
	 */
	public IRootObjectType getObjectType() {
		return this.objectType;
	}

	void setRootNode(IRootNode rootNode) {
		this.rootNode = rootNode;
	}
	
	IRootNode getRootNode() {
		return this.rootNode;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getCurrentDrawingElement()
	 */
	public IDrawingElement getCurrentDrawingElement() {
		return this.rootNode;
	}

	public boolean isValid() {
		return this.getObjectType() != null;
	}

	/**
	 * @param hibObjectType the hibObjectType to set
	 */
	void setHibObjectType(HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType;
	}

	/**
	 * @return the hibObjectType
	 */
	public HibObjectType getHibObjectType() {
		return hibObjectType;
	}

}
