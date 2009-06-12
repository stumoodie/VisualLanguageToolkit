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
