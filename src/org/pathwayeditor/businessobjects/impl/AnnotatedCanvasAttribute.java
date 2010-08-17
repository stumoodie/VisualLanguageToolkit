/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IAnnotatedCanvasAttributeDefaults;

/**
 * @author smoodie
 *
 */
public abstract class AnnotatedCanvasAttribute extends CanvasAttribute implements IAnnotatedObject {
	private final Logger logger = Logger.getLogger(this.getClass());
	private Set<IAnnotationProperty> hibProperties = new HashSet<IAnnotationProperty>(0);

	protected AnnotatedCanvasAttribute(ICanvas canvas, int creationSerial, IAnnotatedCanvasAttributeDefaults defaults) {
		super(canvas, creationSerial);
		final IPropertyBuilder propertyBuilder = new PropertyBuilder(this);
		final Iterator<IPropertyDefinition> propIter = defaults.propertyDefinitionIterator();
		while(propIter.hasNext()){
			IPropertyDefinition propDefn = propIter.next();
			this.hibProperties.add(propDefn.createProperty(propertyBuilder));
		}
	}
	

	protected AnnotatedCanvasAttribute(ICanvas newCanvas, int newCreationSerial, AnnotatedCanvasAttribute other) {
		super(newCanvas, newCreationSerial);
		final IPropertyBuilder propertyBuilder = new PropertyBuilder(this);
		for (IAnnotationProperty property : other.hibProperties) {
			IPropertyDefinition defn = property.getDefinition(); 
			this.hibProperties.add(defn.copyProperty(propertyBuilder, property));
		}
	}
	
	protected boolean arePropertiesValid(IAnnotatedCanvasAttributeDefaults defaults) {
		// check properties initialised
		Iterator<IPropertyDefinition> it = defaults.propertyDefinitionIterator();
		boolean retVal = true;
		int propCntr = 0;
		while (it.hasNext() && retVal) {
			IPropertyDefinition definition = it.next();
			AnnotationProperty property = (AnnotationProperty)this.findProperty(definition.getName());
			if (property == null) {
				logger.error(
						"The object type has property definitions which have no matching property in this Shape Attribute");
				retVal = false;
			}
			else {
				property.setPropertyDefinition(definition);
				propCntr++;
			}
		}
		if (retVal && propCntr != this.hibProperties.size()) {
			logger.error(
					"Object inconsistent with object type. Cannot find definitions for some properties");
			retVal = false;
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getProperty(java.lang.String)
	 */
	@Override
	public IAnnotationProperty getProperty(String propertyName) {
		IAnnotationProperty retVal = this.findProperty(propertyName);
		if(retVal == null) {
			throw new IllegalArgumentException("Property is not held by this canvas attribute=" + this + ",name=" + propertyName);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition)
	 */
	@Override
	public IAnnotationProperty getProperty(IPropertyDefinition propDefn) {
		IAnnotationProperty retVal = this.findProperty(propDefn);
		if(retVal == null) {
			throw new IllegalArgumentException("propDefn is not held by this canvas attribute");
		}
		return retVal;
	}
	
	private IAnnotationProperty findProperty(IPropertyDefinition propDefn) {
		IAnnotationProperty retVal = null;
		for(IAnnotationProperty prop : this.hibProperties) {
			if(prop.getDefinition().equals(propDefn)) {
				retVal = prop;
			}
		}
		return retVal;
	}

	private IAnnotationProperty findProperty(String propName) {
		IAnnotationProperty retVal = null;
		for(IAnnotationProperty prop : this.hibProperties) {
			if(prop.getDefinition().getName().equals(propName)) {
				retVal = prop;
			}
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#propertyIterator()
	 */
	@Override
	public Iterator<IAnnotationProperty> propertyIterator() {
		return this.hibProperties.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	@Override
	public boolean containsProperty(IPropertyDefinition propDefn) {
		return this.findProperty(propDefn) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(java.lang.String)
	 */
	@Override
	public boolean containsProperty(String propName) {
		return this.findProperty(propName) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#numProperties()
	 */
	@Override
	public int numProperties() {
		return this.hibProperties.size();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	@Override
	public boolean containsProperty(IAnnotationProperty property) {
		boolean retVal = false;
		if(property != null) {
			retVal = this.hibProperties.contains(property);
		}
		return retVal;
	}

}
