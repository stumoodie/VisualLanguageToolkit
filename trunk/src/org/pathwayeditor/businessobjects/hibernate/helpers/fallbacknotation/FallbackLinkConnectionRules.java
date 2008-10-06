package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


class FallbackLinkConnectionRules implements ILinkConnectionRules {
	private final ILinkObjectType owningObjectType;
	
	public FallbackLinkConnectionRules(ILinkObjectType owningObjectType) {
		this.owningObjectType = owningObjectType;
	}

	public ILinkObjectType getLinkObjectType() {
		return this.owningObjectType;
	}

	public boolean isValidSource(IShapeObjectType source) {
		return false;
	}

	public boolean isValidTarget(IShapeObjectType source, IShapeObjectType target) {
		return false;
	}

}
