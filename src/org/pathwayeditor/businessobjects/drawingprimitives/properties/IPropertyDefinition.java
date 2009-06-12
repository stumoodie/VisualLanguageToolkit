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
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;

public interface IPropertyDefinition {

	/**
	 * The property value as an object appropriate for its type.
	 * This can be redefined by implementing classes. 
	 * @return An object type appropriate for the property type. Guaranteed to be non-null. 
	 */
	Object getDefaultValue();
	
	/**
	 * The property name
	 * @return S string that is not null.
	 */
	String getName();
	
	/**
	 * Is the property visualisable
	 * @return True if it is, false otherwise.
	 */
	boolean isVisualisable();

	
	/**
	 * Is the property editable?
	 * @return True if it is, false otherwise.
	 */
	boolean isEditable();
	
	ILabelAttributeDefaults getLabelDefaults();
	
	IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder);

	/**
	 * @param propertyBuilder
	 * @return the copied annotation property.
	 */
	IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty otherProperty);
}
