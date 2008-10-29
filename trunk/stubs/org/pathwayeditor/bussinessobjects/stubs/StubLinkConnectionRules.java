/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class StubLinkConnectionRules implements ILinkConnectionRules {
	private final ILinkObjectType owningType;
	
	
	public StubLinkConnectionRules(ILinkObjectType owningType){
		this.owningType = owningType;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules#getLinkObjectType()
	 */
	public ILinkObjectType getLinkObjectType() {
		return this.owningType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules#isValidSource(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public boolean isValidSource(IShapeObjectType source) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules#isValidTarget(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType, org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public boolean isValidTarget(IShapeObjectType source, IShapeObjectType target) {
		return true;
	}

}
