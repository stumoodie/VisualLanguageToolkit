/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class StubLinkAObjectType implements ILinkObjectType {
	private static final EnumSet<LinkEditableAttributes> EDITABLE_ATTRIBUTES = EnumSet.of(LinkEditableAttributes.COLOUR);
	public static final int UNIQUE_ID = 5;
	public static final String DESCRIPTION = "Link A Object Type";
	public static final String NAME = "Link A";
	private final INotationSyntaxService syntaxService;
	private final ILinkAttributeDefaults linkAttributeDefaults;
	private final ILinkConnectionRules connectionRules;
	private final ILinkTerminusDefinition sourceTerminusDefn;
	private final ILinkTerminusDefinition targetTerminusDefn;
	
	/**
	 * @param stubNotationSyntaxService
	 */
	public StubLinkAObjectType(INotationSyntaxService stubNotationSyntaxService) {
		this.syntaxService = stubNotationSyntaxService;
		this.linkAttributeDefaults = new StubLinkAttributeDefaultsWithRichText();
		this.sourceTerminusDefn = new StubSourceLinkTerminusDefinition(this);
		this.targetTerminusDefn = new StubTargetLinkTerminusDefinition(this);
		this.connectionRules = new ILinkConnectionRules(){

			public ILinkObjectType getLinkObjectType() {
				return StubLinkAObjectType.this;
			}

			public boolean isValidSource(IShapeObjectType source) {
				return true;
			}

			public boolean isValidTarget(IShapeObjectType source, IShapeObjectType target) {
				return true;
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getDefaultLinkAttributes()
	 */
	public ILinkAttributeDefaults getDefaultAttributes() {
		return this.linkAttributeDefaults;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getEditiableAttributes()
	 */
	public EnumSet<LinkEditableAttributes> getEditableAttributes() {
		return EDITABLE_ATTRIBUTES;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getLinkConnectionRules()
	 */
	public ILinkConnectionRules getLinkConnectionRules() {
		return connectionRules;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getSourceTerminusDefinition()
	 */
	public ILinkTerminusDefinition getSourceTerminusDefinition() {
		return this.sourceTerminusDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getTargetTerminusDefinition()
	 */
	public ILinkTerminusDefinition getTargetTerminusDefinition() {
		return this.targetTerminusDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getUniqueId()
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

}
