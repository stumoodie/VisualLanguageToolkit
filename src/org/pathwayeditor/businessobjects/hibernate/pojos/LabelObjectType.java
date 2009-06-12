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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;

/**
 * @author smoodie
 *
 */
public class LabelObjectType implements INodeObjectType {
	private static final int LABEL_UNIQUE_ID = -1;
	private static final String NAME = "LabelOT";
	
	private final INotationSyntaxService notation;
	private final String name;
	private final IObjectTypeParentingRules parentingRules;
	
	public LabelObjectType(INotationSyntaxService notation){
		this.notation = notation;
		this.name = NAME;
		this.parentingRules = new LabelDummyParentingRules(this);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDefiningContext()
	 */
	public INotationSyntaxService getSyntaxService() {
		return this.notation;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	public String getDescription() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IObjectType o) {
		return this.getName().compareTo(o.getName());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.notation == null) ? 0 : this.notation.hashCode());
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LabelObjectType))
			return false;
		LabelObjectType other = (LabelObjectType) obj;
		if (this.notation == null) {
			if (other.notation != null)
				return false;
		} else if (!this.notation.equals(other.notation))
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.INodeObjectType#getParentingRules()
	 */
	public IObjectTypeParentingRules getParentingRules() {
		return this.parentingRules;
	}

	private static class LabelDummyParentingRules implements IObjectTypeParentingRules {
		private final IObjectType objectType;
		
		LabelDummyParentingRules(IObjectType objectType){
			this.objectType = objectType;
		}
		
		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules#getObjectType()
		 */
		public IObjectType getObjectType() {
			return this.objectType;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules#isValidChild(org.pathwayeditor.businessobjects.typedefn.IObjectType)
		 */
		public boolean isValidChild(IObjectType possibleChild) {
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getUniqueId()
	 */
	public int getUniqueId() {
		return LABEL_UNIQUE_ID;
	}
}
