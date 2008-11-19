/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubLinkAttributeDefaultsWithRichText;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubSourceLinkTerminusDefinition;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubTargetLinkTerminusDefinition;

/**
 * @author smoodie
 *
 */
public class StubLinkObjectType implements ILinkObjectType {
	public static final String EXPECTED_NAME = "linkType";
	public static final String EXPECTED_DESCRIPTION = "linkType description";
	public static final int EXPECTED_UNIQUE_ID = 1;
	
	private final ILinkTerminusDefinition sourceTermDefn = new StubSourceLinkTerminusDefinition(this);
	private final ILinkTerminusDefinition targetTermDefn = new StubTargetLinkTerminusDefinition(this);
	private final ILinkConnectionRules connectionRules = new StubLinkConnectionRules(this); 
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getDefaultLinkAttributes()
	 */
	public ILinkAttributeDefaults getDefaultLinkAttributes() {
		return new StubLinkAttributeDefaultsWithRichText();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getEditiableAttributes()
	 */
	public EnumSet<LinkEditableAttributes> getEditiableAttributes() {
		return EnumSet.noneOf(LinkEditableAttributes.class);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getLinkConnectionRules()
	 */
	public ILinkConnectionRules getLinkConnectionRules() {
		return this.connectionRules;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getSourceTerminusDefinition()
	 */
	public ILinkTerminusDefinition getSourceTerminusDefinition() {
		return this.sourceTermDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getTargetTerminusDefinition()
	 */
	public ILinkTerminusDefinition getTargetTerminusDefinition() {
		return this.targetTermDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDefiningContext()
	 */
	public INotationSyntaxService getSyntaxService() {
		throw new UnsupportedOperationException("not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	public String getDescription() {
		return EXPECTED_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	public String getName() {
		return EXPECTED_NAME;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IObjectType o) {
		throw new UnsupportedOperationException("do not use!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getUniqueId()
	 */
	public int getUniqueId() {
		return EXPECTED_UNIQUE_ID;
	}

}
