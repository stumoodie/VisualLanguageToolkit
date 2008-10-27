/**
 * 
 */
package org.pathwaueditor.bussinessobjects.stubs;

import java.util.EnumSet;

import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;

/**
 * @author ntsorman
 *
 */
public class StubShapeObjectType implements IShapeObjectType{
	
	private static final IShapeAttributeDefaults ATTRIBUTE_DEFAULTS = new FallbackShapeAttributeDefaults();
	private final IShapeParentingRules parentingRules = new StubFallBackParentingRules();
	private final INotationSyntaxService syntaxService = new StubNotationSyntaxService (new StubNotationSubSystem()) ;
	
	public final int EXPECTED_UNIQUE_ID = 1 ;
	public final String EXPECTED_DESCRIPTION = "objectDescr" ;
	public final String EXPECTED_NAME = "objectName" ;

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getDefaultAttributes()
	 */
	public IShapeAttributeDefaults getDefaultAttributes() {
		// TODO Auto-generated method stub
		return ATTRIBUTE_DEFAULTS;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getEditableAttributes()
	 */
	public EnumSet<EditableShapeAttributes> getEditableAttributes() {
		return EnumSet.noneOf(EditableShapeAttributes.class);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getParentingRules()
	 */
	public IShapeParentingRules getParentingRules() {
		return parentingRules;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getUniqueId()
	 */
	public int getUniqueId() {
		return EXPECTED_UNIQUE_ID;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	public String getDescription() {
		return EXPECTED_DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	public String getName() {
		return EXPECTED_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
	 */
	public INotationSyntaxService getSyntaxService() {
		return syntaxService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IObjectType o) {
		return 0;
	}

}
