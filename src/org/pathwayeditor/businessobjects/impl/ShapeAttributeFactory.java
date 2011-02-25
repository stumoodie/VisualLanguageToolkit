/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author Stuart Moodie
 *
 */
public class ShapeAttributeFactory implements IShapeAttributeFactory {
	private IShapeObjectType shapeObjectType;
	private ICanvasElementAttribute destinationAttribute;
	private final IndexCounter creationSerialCounter;
	private IElementAttribute outAttribute;
	private IElementAttribute inAttribute;
	private Integer preferredCreationSerial;
	
	public ShapeAttributeFactory(IndexCounter creationSerialCounter){
		if(creationSerialCounter == null) throw new IllegalArgumentException("Creation serial cannot be null");
		
		this.destinationAttribute = null;
		this.shapeObjectType = null;
		this.creationSerialCounter = creationSerialCounter;
	}
	
	@Override
	public void setObjectType(IShapeObjectType objectType){
		this.shapeObjectType = objectType;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.shapeObjectType != null && this.destinationAttribute != null &&
			this.destinationAttribute.getObjectType().getParentingRules().isValidChild(shapeObjectType);
	}

	@Override
	public IShapeAttribute createAttribute() {
		if(!this.canCreateAttribute()) throw new IllegalStateException("cannot create attribute");
	
		Integer currSerial = this.preferredCreationSerial;
		if(currSerial == null){
			currSerial = creationSerialCounter.nextIndex();
		}
		return new ShapeAttribute(this.destinationAttribute.getModel(), currSerial.intValue(), shapeObjectType);
	}

	@Override
	public IShapeObjectType getObjectType() {
		return this.shapeObjectType;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setDestinationAttribute(IElementAttribute attribute) {
		this.destinationAttribute = (ICanvasElementAttribute)attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getDestinationAttribute()
	 */
	@Override
	public ICanvasElementAttribute getDestinationAttribute() {
		return this.destinationAttribute;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setOutAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setOutAttribute(IElementAttribute attribute) {
		this.outAttribute = attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getOutAttribute()
	 */
	@Override
	public IElementAttribute getOutAttribute() {
		return this.outAttribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setInAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setInAttribute(IElementAttribute attribute) {
		this.inAttribute = attribute;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#getInAttribute()
	 */
	@Override
	public IElementAttribute getInAttribute() {
		return this.inAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttributeFactory#setPreferredCreationSerial(int)
	 */
	@Override
	public void setPreferredCreationSerial(Integer creationSerial) {
		this.preferredCreationSerial = creationSerial;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttributeFactory#getPreferredCreationSerial()
	 */
	@Override
	public Integer getPreferredCreationSerial() {
		return this.preferredCreationSerial;
	}

}
