/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * @author smoodie
 *
 */
public interface IHibNotationFactory {

	HibNotation getNotation(INotation notation);

	HibObjectType getObjectType(IObjectType objectType);
}
