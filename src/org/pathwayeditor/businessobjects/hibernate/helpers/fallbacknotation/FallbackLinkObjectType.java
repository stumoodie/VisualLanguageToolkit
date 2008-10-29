package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

public class FallbackLinkObjectType implements ILinkObjectType {
	private static final ILinkAttributeDefaults DEFAULT_ATTRIBUTES = new FallbackLinkAttributeDefaults();
	
	private final HibObjectType hibObjectType;
	private final INotationSyntaxService syntaxService;
	private final ILinkConnectionRules linkConnectionRules;
	private final ILinkTerminusDefinition sourceDefinition;
	private final ILinkTerminusDefinition targetDefinition;

	
	public FallbackLinkObjectType(INotationSyntaxService syntaxService, HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType;
		this.syntaxService = syntaxService;
		this.linkConnectionRules = new FallbackLinkConnectionRules(this);
		this.sourceDefinition = new FallbackLinkTerminusDefinition(LinkTermType.SOURCE, this);
		this.targetDefinition = new FallbackLinkTerminusDefinition(LinkTermType.TARGET, this);
	}

	public String getDescription() {
		return this.hibObjectType.getDescription();
	}

	public ILinkConnectionRules getLinkConnectionRules() {
		return this.linkConnectionRules;
	}

	public ILinkTerminusDefinition getLinkSource() {
		return this.sourceDefinition;
	}

	public ILinkTerminusDefinition getLinkTarget() {
		return this.targetDefinition;
	}

	public String getName() {
		return this.hibObjectType.getName();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getDefaultLinkAttributes()
	 */
	public ILinkAttributeDefaults getDefaultLinkAttributes() {
		return DEFAULT_ATTRIBUTES;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getEditiableAttributes()
	 */
	public EnumSet<LinkEditableAttributes> getEditiableAttributes() {
		return EnumSet.noneOf(LinkEditableAttributes.class);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getSourceTerminusDefinition()
	 */
	public ILinkTerminusDefinition getSourceTerminusDefinition() {
		return this.sourceDefinition;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getTargetTerminusDefinition()
	 */
	public ILinkTerminusDefinition getTargetTerminusDefinition() {
		return this.targetDefinition;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getUniqueId()
	 */
	public int getUniqueId() {
		return this.hibObjectType.getUniqueId();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
	 */
	public INotationSyntaxService getSyntaxService() {
		return this.syntaxService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IObjectType o) {
		return this.getUniqueId() == o.getUniqueId() ? 0 : this.getUniqueId() < o.getUniqueId() ? -1 : 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.syntaxService == null) ? 0 : this.syntaxService
						.hashCode());
		result = prime * result + this.getUniqueId();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FallbackLinkObjectType))
			return false;
		FallbackLinkObjectType other = (FallbackLinkObjectType) obj;
		if (this.syntaxService == null) {
			if (other.syntaxService != null)
				return false;
		} else if (!this.syntaxService.equals(other.syntaxService))
			return false;
		if (this.getUniqueId() != other.getUniqueId())
			return false;
		return true;
	}

}
