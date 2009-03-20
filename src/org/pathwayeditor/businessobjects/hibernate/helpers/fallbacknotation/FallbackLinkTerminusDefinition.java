package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;


class FallbackLinkTerminusDefinition implements ILinkTerminusDefinition {
	private static final ILinkTerminusDefaults TERMINUS_DEFAULTS = new FallbackLinkTerminusDefaults();
	private final ILinkObjectType owningObjectType;
	private final LinkTermType linkEndType;
	
	public FallbackLinkTerminusDefinition(LinkTermType linkEndType, ILinkObjectType owningObjectType) {
		super();
		this.owningObjectType = owningObjectType;
		this.linkEndType = linkEndType;
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
		return this.linkEndType;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getLinkTerminusDefaults()
	 */
	public ILinkTerminusDefaults getDefaultAttributes() {
		return TERMINUS_DEFAULTS;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition#getOwningObjectType()
	 */
	public ILinkObjectType getOwningObjectType() {
		return this.owningObjectType;
	}

}
