/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;

/**
 * @author smoodie
 *
 */
public class StubShapeCParentOfShapeDObjectType implements IShapeObjectType {
	public static final int UNIQUE_ID = 3;
	public static final String DESCN = "Shape C Test ObjectType";
	public static final String NAME = "Shape C";
	public static final EnumSet<EditableShapeAttributes> EDITABLE_ATTRIBUTES = EnumSet.noneOf(EditableShapeAttributes.class);
	private final INotationSyntaxService syntaxService;
	private final IShapeAttributeDefaults attributeDefaults;
	
	/**
	 * @param stubNotationSyntaxService
	 */
	public  StubShapeCParentOfShapeDObjectType(StubNotationSyntaxService stubNotationSyntaxService) {
		this.syntaxService = stubNotationSyntaxService;
		this.attributeDefaults = new StubShapeAttributeDefaults();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getDefaultAttributes()
	 */
	public IShapeAttributeDefaults getDefaultAttributes() {
		return this.attributeDefaults;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getEditableAttributes()
	 */
	public EnumSet<EditableShapeAttributes> getEditableAttributes() {
		return EDITABLE_ATTRIBUTES;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getParentingRules()
	 */
	public IShapeParentingRules getParentingRules() {
		return new IShapeParentingRules(){

			public IShapeObjectType getObjectType() {
				return StubShapeCParentOfShapeDObjectType.this;
			}

			public boolean isValidChild(IObjectType possibleChild) {
				return getUniqueId() == StubShapeDChildOfShapeCObjectType.UNIQUE_ID;
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getUniqueId()
	 */
	public int getUniqueId() {
		return UNIQUE_ID;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	public String getDescription() {
		return DESCN;
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