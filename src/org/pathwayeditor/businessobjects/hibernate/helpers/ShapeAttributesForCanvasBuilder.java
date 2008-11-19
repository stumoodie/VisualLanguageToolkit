/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.Iterator;

import org.hibernate.Hibernate;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeAttribute;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author nhanlon Given a HibCanvas, iterate through its associated ShapeAttributes, ensuring they (and their entities) are initialised by 
 * Hibernate and then injecting their properties with corresponding property definitions
 * 
 */
public class ShapeAttributesForCanvasBuilder implements IAttributesForCanvasBuilder {

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.hibernate.helpers.IAttributesForCanvasBuilder#initAttributes(org.pathwayeditor.businessobjects.hibernate.pojos
	 * .HibCanvas)
	 */
	public void initAttributes(HibCanvas canvas) {
		for (HibShapeAttribute shapeAttr : canvas.getShapeAttributes()) {
			Hibernate.initialize(shapeAttr);
			IShapeObjectType objectType = canvas.getNotationSubsystem().getSyntaxService().getShapeObjectType(
					shapeAttr.getHibObjectType().getUniqueId());
			shapeAttr.setShapeObjectType(objectType);
			Hibernate.initialize(shapeAttr.getProperties());
			injectPropertyDefinitions(objectType, shapeAttr);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.hibernate.helpers.IAttributesForCanvasBuilder#injectPropertyDefinitions(org.pathwayeditor.businessobjects.typedefn
	 * .IObjectType, org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute)
	 */
	public void injectPropertyDefinitions(IObjectType objectType, ICanvasAttribute shapeAttr) {
		Iterator<IPropertyDefinition> it = ((IShapeObjectType) objectType).getDefaultAttributes().propertyDefinitionIterator();
		while (it.hasNext()) {
			IPropertyDefinition definition = it.next();
			if (((HibShapeAttribute) shapeAttr).getProperties().keySet() == null
					|| ((HibShapeAttribute) shapeAttr).getProperties().keySet().isEmpty())
				;
			else {
				HibProperty property = (HibProperty) ((HibShapeAttribute) shapeAttr).getProperty(definition);
				property.setPropertyDefinition(definition);
			}
		}
	}

}
