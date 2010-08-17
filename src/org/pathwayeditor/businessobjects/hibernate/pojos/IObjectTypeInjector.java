/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;


/**
 * @author smoodie
 *
 */
public interface IObjectTypeInjector {

	void inject(HibShapeAttribute attribute) throws InconsistentNotationDefinitionException;
	
	void inject(HibLinkAttribute attribute) throws InconsistentNotationDefinitionException;
	
	void inject(HibLinkTerminus attribute) throws InconsistentNotationDefinitionException;
	
	void inject(RootAttribute attribute) throws InconsistentNotationDefinitionException;
	
}
