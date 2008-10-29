package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

public class FallbackObjectTypeParentingRules implements
		IRootObjectParentingRules {
	private final IRootObjectType owningType;
	
	public FallbackObjectTypeParentingRules(IRootObjectType owningType){
		this.owningType = owningType;
	}
	
	public IRootObjectType getObjectType() {
		return this.owningType;
	}

	public boolean isValidChild(IObjectType possibleChild) {
		return false;
	}

}
