/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment;
import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeObjectType;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * AnchorNodeAttributeFactory
 *
 * @author Stuart Moodie
 *
 */
public class AnchorNodeAttributeFactory implements IAnchorNodeAttributeFactory {
	private IAnchorNodeObjectType shapeObjectType;
	private ICanvasElementAttribute destinationAttribute;
	private final IndexCounter creationSerialCounter;
	private IElementAttribute outAttribute;
	private IElementAttribute inAttribute;
	private Integer preferredCreationSerial;
	private ICurveSegment curveSegment;

	/**
	 * @param creationSerialCounter
	 */
	public AnchorNodeAttributeFactory(IndexCounter creationSerialCounter) {
		this.destinationAttribute = null;
		this.shapeObjectType = null;
		this.creationSerialCounter = creationSerialCounter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttributeFactory#setPreferredCreationSerial(java.lang.Integer)
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

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.shapeObjectType != null && this.destinationAttribute != null &&
				this.destinationAttribute.getObjectType().getParentingRules().isValidChild(shapeObjectType);
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
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#createAttribute()
	 */
	@Override
	public IElementAttribute createAttribute() {
		if(!this.canCreateAttribute()) throw new IllegalStateException("cannot create attribute");
		
		Integer currSerial = this.preferredCreationSerial;
		if(currSerial == null){
			currSerial = creationSerialCounter.nextIndex();
		}
		return new AnchorNodeAttribute(this.destinationAttribute.getModel(), currSerial.intValue(), this.curveSegment, shapeObjectType);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setOutAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setOutAttribute(IElementAttribute attribute) {
		
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttributeFactory#setObjectType(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	@Override
	public void setObjectType(IAnchorNodeObjectType linkEndObjectType) {
		this.shapeObjectType = linkEndObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttributeFactory#setAssociateCurveSegment(org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment)
	 */
	@Override
	public void setAssociateCurveSegment(ICurveSegment associatedCurveSegment) {
		this.curveSegment = associatedCurveSegment;
	}

	@Override
	public IAnchorNodeObjectType getObjectType() {
		return this.shapeObjectType;
	}

	@Override
	public ICurveSegment getAssociatedCurveSegment() {
		return this.curveSegment;
	}

}
