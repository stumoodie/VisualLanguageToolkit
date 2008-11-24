/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

/**
 * @author smoodie
 *
 */
public class StubRootObjectType implements IRootObjectType {
	private static final int UNIQUE_ID = 0;
	public static final String DESCRIPTION = "Root Object Type Description";
	public static final String NAME = "Root Object Type";
	private final INotationSyntaxService syntaxService;
	
	/**
	 * 
	 */
	public StubRootObjectType(INotationSyntaxService syntaxService) {
		this.syntaxService = syntaxService;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IRootObjectType#getParentingRules()
	 */
	public IRootObjectParentingRules getParentingRules() {
		return new RootObjectParentingRules();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IRootObjectType#getUniqueId()
	 */
	public int getUniqueId() {
		return UNIQUE_ID;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	public String getDescription() {
		return DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
	 */
	public INotationSyntaxService getSyntaxService() {
		return this.syntaxService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IObjectType o) {
		return this.getUniqueId() < o.getUniqueId() ? -1 : this.getUniqueId() > o.getUniqueId() ? 1 : 0;
	}

	private class RootObjectParentingRules implements IRootObjectParentingRules {

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules#getObjectType()
		 */
		public IRootObjectType getObjectType() {
			return StubRootObjectType.this;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules#isValidChild(org.pathwayeditor.businessobjects.typedefn.IObjectType)
		 */
		public boolean isValidChild(IObjectType possibleChild) {
			boolean retVal = false;
			if(getObjectType().getUniqueId() == StubShapeAObjectType.UNIQUE_ID
					|| getObjectType().getUniqueId() == StubShapeCObjectType.UNIQUE_ID){
				retVal = true; 
			}
			return retVal;
		}
		
	}
}
