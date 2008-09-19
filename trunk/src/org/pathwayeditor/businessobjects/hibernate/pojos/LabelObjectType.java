/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.contextadapter.IContext;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;

/**
 * @author smoodie
 *
 */
public class LabelObjectType implements INodeObjectType {
	private static final String NAME = "LabelOT";
	private final IContext context;
	private final String name;
	private final IObjectTypeParentingRules parentingRules;
	
	public LabelObjectType(IContext context){
		this.context = context;
		this.name = NAME;
		this.parentingRules = new LabelDummyParentingRules(this);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDefiningContext()
	 */
	public IContext getDefiningContext() {
		return this.context;
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
				+ ((this.context == null) ? 0 : this.context.hashCode());
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
		if (this.context == null) {
			if (other.context != null)
				return false;
		} else if (!this.context.equals(other.context))
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
}
