/**
 * 
 */
package org.pathwaueditor.bussinessobjects.stubs;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter;

/**
 * @author smoodie
 *
 */
public class StubPropertyDefinitionFilter implements IPropertyDefinitionFilter {
	private final Set<IPropertyDefinition> retVal = Collections.emptySet();
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getAllProperties()
	 */
	public Set<IPropertyDefinition> getAllProperties() {
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getAllPropertiesIterator()
	 */
	public Iterator<IPropertyDefinition> getAllPropertiesIterator() {
		return retVal.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getEditableProperties()
	 */
	public Set<IPropertyDefinition> getEditableProperties() {
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getFormattedTextProperties()
	 */
	public Set<IPropertyDefinition> getFormattedTextProperties() {
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getNumberProperties()
	 */
	public Set<IPropertyDefinition> getNumberProperties() {
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getObjectType()
	 */
	public IObjectType getObjectType() {
		throw new UnsupportedOperationException("not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getTextProperties()
	 */
	public Set<IPropertyDefinition> getTextProperties() {
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter#getVisualisableProperties()
	 */
	public Set<IPropertyDefinition> getVisualisableProperties() {
		return retVal;
	}
}
