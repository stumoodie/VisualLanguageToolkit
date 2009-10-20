/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibAnnotatedCanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IAnnotatedCanvasAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class StandardObjectTypeInjector implements IObjectTypeInjector {
	private final INotationSubsystem notationSubsystem;
		
	public StandardObjectTypeInjector(INotationSubsystem notationFactory) {
		this.notationSubsystem = notationFactory;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector#inject(org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute, org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)
	 */
	public void inject(HibShapeAttribute attribute) throws InconsistentNotationDefinitionException {
		IShapeObjectType objectType = this.notationSubsystem.getSyntaxService().getShapeObjectType(attribute.getHibObjectType().getUniqueId());
		attribute.setObjectType(objectType);
		injectPropertyDefinitions(attribute, objectType.getDefaultAttributes());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector#inject(org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute, org.pathwayeditor.businessobjects.typedefn.ILinkObjectType)
	 */
	public void inject(HibLinkAttribute attribute) throws InconsistentNotationDefinitionException {
		ILinkObjectType objectType = this.notationSubsystem.getSyntaxService().getLinkObjectType(attribute.getHibObjectType().getUniqueId());
		attribute.setObjectType(objectType);
		injectPropertyDefinitions(attribute, objectType.getDefaultAttributes());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector#inject(org.pathwayeditor.businessobjects.hibernate.pojos.HibRootAttribute, org.pathwayeditor.businessobjects.typedefn.IRootObjectType)
	 */
	public void inject(HibRootAttribute attribute) throws InconsistentNotationDefinitionException {
		IRootObjectType objectType = this.notationSubsystem.getSyntaxService().getRootObjectType();
		if(objectType.getUniqueId() != attribute.getHibObjectType().getUniqueId())
			throw new InconsistentNotationDefinitionException("The root object has a different id to that expected: id=" + attribute.getHibObjectType().getUniqueId());
			
		attribute.setObjectType(objectType);
	}

	protected void injectPropertyDefinitions(HibAnnotatedCanvasAttribute attribute, IAnnotatedCanvasAttributeDefaults defaults) throws InconsistentNotationDefinitionException {
		Iterator<IPropertyDefinition> it = defaults.propertyDefinitionIterator();
		int propCntr = 0;
		while (it.hasNext()) {
			IPropertyDefinition definition = it.next();
			if(attribute.containsProperty(definition.getName())){
				HibProperty property = (HibProperty)attribute.getProperty(definition.getName());
				property.setPropertyDefinition(definition);
				propCntr++;
			}
			else{
				throw new InconsistentNotationDefinitionException("The object type has property definitions which have no matching property in this Shape Attribute");
			}
		}
		if(propCntr != attribute.getProperties().size()) {
			throw new InconsistentNotationDefinitionException("Object inconsistent with object type. Cannot find definitions for some properties");
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector#inject(org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus, org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults)
	 */
	public void inject(HibLinkTerminus attribute) throws InconsistentNotationDefinitionException {
		ILinkObjectType objectType = this.notationSubsystem.getSyntaxService().getLinkObjectType(attribute.getOwningLink().getHibObjectType().getUniqueId());
		ILinkTerminusDefinition terminusDefn = objectType.getSourceTerminusDefinition();
		if(attribute.getLinkTermType().equals(LinkTermType.TARGET)){
			terminusDefn = objectType.getTargetTerminusDefinition();
		}
		injectPropertyDefinitions(attribute, terminusDefn.getDefaultAttributes());
		if(terminusDefn != null && (!terminusDefn.getOwningObjectType().equals(objectType))){
			throw new IllegalArgumentException("terminusDefn must belong to the same object type as the link owning this terminus and be for the correct link terminus type.");
		}
		attribute.setTerminusDefinition(terminusDefn);
	}
}
