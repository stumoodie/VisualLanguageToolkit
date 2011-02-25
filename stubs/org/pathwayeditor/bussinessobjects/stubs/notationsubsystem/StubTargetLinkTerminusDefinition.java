/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;

/**
 * @author Stuart Moodie
 *
 */
public class StubTargetLinkTerminusDefinition implements ILinkTerminusDefinition {
	private final ILinkTerminusDefaults sourceTermDefaults = new StubTargetTerminusDefaults();
	private final ILinkObjectType owningType;

	public StubTargetLinkTerminusDefinition(ILinkObjectType owningType){
		this.owningType = owningType;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getEditableAttributes()
	 */
	@Override
	public EnumSet<LinkTermEditableAttributes> getEditableAttributes() {
		return EnumSet.noneOf(LinkTermEditableAttributes.class);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getLinkEndCode()
	 */
	@Override
	public LinkTermType getLinkEndCode() {
		return LinkTermType.TARGET;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getLinkTerminusDefaults()
	 */
	@Override
	public ILinkTerminusDefaults getDefaultAttributes() {
		return this.sourceTermDefaults ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getOwningObjectType()
	 */
	@Override
	public ILinkObjectType getOwningObjectType() {
		return this.owningType;
	}

}
