/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * @author Stuart Moodie
 *
 */
public class ElementAttributeMoveFactory implements IElementAttributeFactory {
	private final IElementAttribute attributeToMove;
	private IElementAttribute destinationAttribute;
	private IElementAttribute outAttribute;
	private IElementAttribute inAttribute;
	
	
	public ElementAttributeMoveFactory(IElementAttribute attributeToMove){
		this.attributeToMove = attributeToMove;
		this.destinationAttribute = null;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#canCreateAttribute()
	 */
	@Override
	public boolean canCreateAttribute() {
		return this.destinationAttribute != null && this.attributeToMove != null;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttributeFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)
	 */
	@Override
	public void setDestinationAttribute(IElementAttribute attribute) {
		this.destinationAttribute = attribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeMoveFactory#getDestinationAttribute()
	 */
	@Override
	public IElementAttribute getDestinationAttribute() {
		return this.destinationAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeMoveFactory#createAttribute()
	 */
	@Override
	public IElementAttribute createAttribute() {
		return this.attributeToMove;
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

}
