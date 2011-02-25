/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
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
 * @author Stuart Moodie
 *
 */
public class StubShapeCObjectType implements IShapeObjectType {
	public static final int UNIQUE_ID = 3;
	public static final String DESCN = "Shape C Test ObjectType";
	public static final String NAME = "Shape C";
	public static final EnumSet<EditableShapeAttributes> EDITABLE_ATTRIBUTES = EnumSet.noneOf(EditableShapeAttributes.class);
	private final INotationSyntaxService syntaxService;
	private final IShapeAttributeDefaults attributeDefaults;
	private final int id = UNIQUE_ID;
	
	/**
	 * @param stubNotationSyntaxService
	 */
	public  StubShapeCObjectType(StubNotationSyntaxService stubNotationSyntaxService) {
		this.syntaxService = stubNotationSyntaxService;
		this.attributeDefaults = new StubShapeAttributeDefaultsWithListPropertyDefinition();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getDefaultAttributes()
	 */
	@Override
	public IShapeAttributeDefaults getDefaultAttributes() {
		return this.attributeDefaults;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getEditableAttributes()
	 */
	@Override
	public EnumSet<EditableShapeAttributes> getEditableAttributes() {
		return EDITABLE_ATTRIBUTES;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getParentingRules()
	 */
	@Override
	public IShapeParentingRules getParentingRules() {
		return new IShapeParentingRules(){

			@Override
			public IShapeObjectType getObjectType() {
				return StubShapeCObjectType.this;
			}

			@Override
			public boolean isValidChild(IObjectType possibleChild) {
				return StubShapeAObjectType.UNIQUE_ID == possibleChild.getUniqueId()
				|| StubShapeBObjectType.UNIQUE_ID == possibleChild.getUniqueId()
				|| StubShapeCObjectType.UNIQUE_ID == possibleChild.getUniqueId()
				|| StubShapeDObjectType.UNIQUE_ID == possibleChild.getUniqueId();
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getUniqueId()
	 */
	@Override
	public int getUniqueId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	@Override
	public String getDescription() {
		return DESCN;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
	 */
	@Override
	public INotationSyntaxService getSyntaxService() {
		return this.syntaxService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IObjectType o) {
		return this.getUniqueId() < o.getUniqueId() ? -1 : this.getUniqueId() > o.getUniqueId() ? 1 : 0;
	}

}
