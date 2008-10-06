package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;


class FallbackShapeParentingRules implements IShapeParentingRules {
	private IShapeObjectType parentObjectType;
	
	public FallbackShapeParentingRules(IShapeObjectType parentObjectType){
		this.parentObjectType = parentObjectType;
	}

	public IShapeObjectType getObjectType() {
		return this.parentObjectType;
	}

	public boolean isValidChild(IObjectType possibleChild) {
		return false;
	}

}
