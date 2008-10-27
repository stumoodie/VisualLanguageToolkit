/**
 * 
 */
package org.pathwaueditor.bussinessobjects.stubs;

import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;

/**
 * @author ntsorman
 *
 */
public class StubFallBackParentingRules implements IShapeParentingRules {
	
	private IShapeObjectType parentObjectType = new GeneralStubObjectType () ;
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules#getObjectType()
	 */
	public IShapeObjectType getObjectType() {
		return parentObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules#isValidChild(org.pathwayeditor.businessobjects.typedefn.IObjectType)
	 */
	public boolean isValidChild(IObjectType possibleChild) {
		return false;
	}

}
