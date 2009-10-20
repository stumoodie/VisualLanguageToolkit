/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackIntegerPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackNumberPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibAnnotatedCanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibBooleanProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibIntegerProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibListProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNumberProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibTextProperty;
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
public class FallbackObjectTypeInjector implements IObjectTypeInjector {
	private final INotationSubsystem notationSubsystem;
		
	public FallbackObjectTypeInjector(INotationSubsystem notationFactory) {
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
		for(HibProperty hibProperty : attribute.getProperties()){
			hibProperty.visit(new IAnnotationPropertyVisitor(){

				public void visitBooleanAnnotationProperty(IBooleanAnnotationProperty prop) {
					HibBooleanProperty hibProp = (HibBooleanProperty)prop; 
					hibProp.setPropertyDefinition(new FallbackBooleanPropertyDefinition(hibProp));
				}

				public void visitIntegerAnnotationProperty(IIntegerAnnotationProperty prop) {
					HibIntegerProperty hibProp = (HibIntegerProperty)prop; 
					hibProp.setPropertyDefinition(new FallbackIntegerPropertyDefinition(hibProp));
				}

				public void visitListAnnotationProperty(IListAnnotationProperty prop) {
					HibListProperty hibProp = (HibListProperty)prop; 
					hibProp.setPropertyDefinition(new FallbackListPropertyDefinition(hibProp));
				}

				public void visitNumberAnnotationProperty(INumberAnnotationProperty prop) {
					HibNumberProperty hibProp = (HibNumberProperty)prop; 
					hibProp.setPropertyDefinition(new FallbackNumberPropertyDefinition(hibProp));
				}

				public void visitPlainTextAnnotationProperty(IPlainTextAnnotationProperty prop) {
					HibTextProperty hibProp = (HibTextProperty)prop; 
					hibProp.setPropertyDefinition(new FallbackPlainTextPropertyDefinition(hibProp));
				}
				
			});
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector#inject(org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkTerminus, org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults)
	 */
	public void inject(HibLinkTerminus attribute) throws InconsistentNotationDefinitionException {
		ILinkObjectType objectType = this.notationSubsystem.getSyntaxService().getLinkObjectType(attribute.getHibObjectType().getUniqueId());
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
