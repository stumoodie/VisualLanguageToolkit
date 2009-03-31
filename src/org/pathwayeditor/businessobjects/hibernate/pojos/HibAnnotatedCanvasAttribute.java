/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.hibernate.helpers.PropertyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.IAnnotatedCanvasAttributeDefaults;

/**
 * @author smoodie
 *
 */
public abstract class HibAnnotatedCanvasAttribute extends HibCanvasAttribute implements IAnnotatedObject {
	private final Logger logger = Logger.getLogger(this.getClass());
	private Set<HibProperty> hibProperties = new HashSet<HibProperty>(0);

	protected HibAnnotatedCanvasAttribute() {
		super();
	}
	
	protected HibAnnotatedCanvasAttribute(HibCanvas canvas, int creationSerial, IAnnotatedCanvasAttributeDefaults defaults) {
		super(canvas, creationSerial);
		final IPropertyBuilder propertyBuilder = new PropertyBuilder(this);
		final Iterator<IPropertyDefinition> propIter = defaults.propertyDefinitionIterator();
		while(propIter.hasNext()){
			IPropertyDefinition propDefn = propIter.next();
			this.hibProperties.add((HibProperty)propDefn.createProperty(propertyBuilder));
		}
	}
	

	protected HibAnnotatedCanvasAttribute(HibCanvas newCanvas, int newCreationSerial, HibAnnotatedCanvasAttribute other) {
		super(newCanvas, newCreationSerial);
		final IPropertyBuilder propertyBuilder = new PropertyBuilder(this);
		for (HibProperty property : other.hibProperties) {
			IPropertyDefinition defn = property.getDefinition(); 
			this.hibProperties.add((HibProperty)defn.copyProperty(propertyBuilder, property));
		}
	}
	
	protected void injectPropertyDefinitions(IAnnotatedCanvasAttributeDefaults defaults) throws InconsistentNotationDefinitionException {
		Iterator<IPropertyDefinition> it = defaults.propertyDefinitionIterator();
		int propCntr = 0;
		while (it.hasNext()) {
			IPropertyDefinition definition = it.next();
			HibProperty property = (HibProperty)this.findProperty(definition.getName());
			if(property==null) {
					throw new InconsistentNotationDefinitionException("The object type has property definitions which have no matching property in this Shape Attribute");
			}
			property.setPropertyDefinition(definition);
			propCntr++;
		}
		if(propCntr != this.hibProperties.size()) {
			throw new InconsistentNotationDefinitionException("Object inconsistent with object type. Cannot find definitions for some properties");
		}
	}

	protected boolean arePropertiesValid(IAnnotatedCanvasAttributeDefaults defaults) {
		// check properties initialised
		Iterator<IPropertyDefinition> it = defaults.propertyDefinitionIterator();
		boolean retVal = true;
		int propCntr = 0;
		while (it.hasNext() && retVal) {
			IPropertyDefinition definition = it.next();
			HibProperty property = (HibProperty)this.findProperty(definition.getName());
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
	public HibProperty getProperty(String propertyName) {
		HibProperty retVal = this.findProperty(propertyName);
		if(retVal == null) {
			throw new IllegalArgumentException("property name is not held by this canvas attribute");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition)
	 */
	public HibProperty getProperty(IPropertyDefinition propDefn) {
		HibProperty retVal = this.findProperty(propDefn);
		if(retVal == null) {
			throw new IllegalArgumentException("propDefn is not held by this canvas attribute");
		}
		return retVal;
	}
	
	private HibProperty findProperty(IPropertyDefinition propDefn) {
		HibProperty retVal = null;
		for(HibProperty prop : this.hibProperties) {
			if(prop.getDefinition().equals(propDefn)) {
				retVal = prop;
			}
		}
		return retVal;
	}

	private HibProperty findProperty(String propName) {
		HibProperty retVal = null;
		for(HibProperty prop : this.hibProperties) {
			if(prop.getName().equals(propName)) {
				retVal = prop;
			}
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#propertyIterator()
	 */
	public Iterator<IAnnotationProperty> propertyIterator() {
		return new IterationCaster<IAnnotationProperty, HibProperty>(this.hibProperties.iterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean containsProperty(IPropertyDefinition propDefn) {
		return this.findProperty(propDefn) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(java.lang.String)
	 */
	public boolean containsProperty(String propName) {
		return this.findProperty(propName) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#numProperties()
	 */
	public int numProperties() {
		return this.hibProperties.size();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public boolean containsProperty(IAnnotationProperty property) {
		boolean retVal = false;
		if(property != null) {
			retVal = this.hibProperties.contains(property);
		}
		return retVal;
	}

	public Set<HibProperty> getProperties(){
		return this.hibProperties;
	}
	
	void setProperties(Set<HibProperty> properties) {
		this.hibProperties = properties;
	}
}
