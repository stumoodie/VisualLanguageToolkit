/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.Iterator;

import org.hibernate.Hibernate;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkAttribute;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * @author nhanlon Given a HibCanvas, iterate through its associated LinkAttributes, ensuring they (and their entities) are initialised by Hibernate and
 *         then injecting their properties with corresponding property definitions
 */
public class LinkAttributesForCanvasBuilder  implements IAttributesForCanvasBuilder{

	public void initAttributes(HibCanvas hibCanvas) {
		for (HibLinkAttribute shapeAttr : hibCanvas.getLinkAttributes()) {
			Hibernate.initialize(shapeAttr);
			ILinkObjectType objectType = hibCanvas.getNotationSubsystem().getSyntaxService().getLinkObjectType(
					shapeAttr.getHibObjectType().getUniqueId());
			shapeAttr.setObjectType(objectType);
			Hibernate.initialize(shapeAttr.getHibLinkProperties());
			Hibernate.initialize(shapeAttr.getBendPoints());
			injectPropertyDefinitions(objectType, shapeAttr);
		}
	}

	
	public void injectPropertyDefinitions(IObjectType objectType, ICanvasAttribute shapeAttr) {
		Iterator<IPropertyDefinition> it = ((ILinkObjectType) objectType).getDefaultLinkAttributes().propertyDefinitionIterator();
		while (it.hasNext()) {
			IPropertyDefinition definition = it.next();
			if (((HibLinkAttribute) shapeAttr).getHibLinkProperties().keySet() == null
					|| ((HibLinkAttribute) shapeAttr).getHibLinkProperties().keySet().isEmpty())
				;
			else {
				HibProperty property = (HibProperty) ((HibLinkAttribute) shapeAttr).getProperty(definition);
				property.setPropertyDefinition(definition);
			}
		}

	}
}
