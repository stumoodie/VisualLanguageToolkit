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

	/**
	 * 
	 */
	public StubRootObjectType(INotationSyntaxService syntaxService) {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IRootObjectType#getParentingRules()
	 */
	public IRootObjectParentingRules getParentingRules() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IRootObjectType#getUniqueId()
	 */
	public int getUniqueId() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
	 */
	public INotationSyntaxService getSyntaxService() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IObjectType o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
