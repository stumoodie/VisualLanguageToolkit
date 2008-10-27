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
 * @author smoodie
 *
 */
public class StubSourceLinkTerminusDefinition implements ILinkTerminusDefinition {
	private final ILinkTerminusDefaults sourceTermDefaults = new StubSourceTerminusDefaults();
	private final ILinkObjectType owningType;

	public StubSourceLinkTerminusDefinition(ILinkObjectType owningType){
		this.owningType = owningType;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getEditableAttributes()
	 */
	public EnumSet<LinkTermEditableAttributes> getEditableAttributes() {
		return EnumSet.noneOf(LinkTermEditableAttributes.class);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getLinkEndCode()
	 */
	public LinkTermType getLinkEndCode() {
		return LinkTermType.SOURCE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getLinkTerminusDefaults()
	 */
	public ILinkTerminusDefaults getLinkTerminusDefaults() {
		return this.sourceTermDefaults ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getOwningObjectType()
	 */
	public ILinkObjectType getOwningObjectType() {
		return this.owningType;
	}

}
