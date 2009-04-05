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
