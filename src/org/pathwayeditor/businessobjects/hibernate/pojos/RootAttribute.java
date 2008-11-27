/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

/**
 * @author smoodie
 *
 */
public class RootAttribute implements ICanvasAttribute {
	private final HibCanvas canvas;
	private final int creationSerial;
	private final IRootObjectType objectType;
	private IRootNode rootNode;
	
	public RootAttribute(HibCanvas canvas, int creationSerial, IRootObjectType objectType) {
		this.canvas = canvas;
		this.creationSerial = creationSerial;
		this.objectType = objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getCanvas()
	 */
	public HibCanvas getCanvas() {
		return this.canvas;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getCreationSerial()
	 */
	public int getCreationSerial() {
		return this.creationSerial;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
	 */
	public IRootObjectType getObjectType() {
		return this.objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#hasProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean hasProperty(IPropertyDefinition property) {
		return false;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("[canvas=");
		builder.append(this.getCanvas());
		builder.append(", serial=");
		builder.append(this.getCreationSerial());
		builder.append("]");
		return builder.toString();
	}

	void setRootNode(IRootNode rootNode) {
		this.rootNode = rootNode;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getCurrentDrawingElement()
	 */
	public IDrawingElement getCurrentDrawingElement() {
		return this.rootNode;
	}

	public boolean isValid() {
		return this.objectType != null && this.canvas != null
			&& this.creationSerial < 0 && this.canvas.findAttribute(this.creationSerial) == null;
	}
}
