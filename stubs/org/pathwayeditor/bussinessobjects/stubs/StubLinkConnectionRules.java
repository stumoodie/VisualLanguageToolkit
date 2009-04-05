/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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
