/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

/**
 * @author nhanlon
 *
 */
public class StubListPropertyDefinition extends StubPropertyDefinition implements IListPropertyDefinition {
	public static final List <String> DEFAULT_VALUE = new ArrayList<String>(Arrays.asList(new String []{"1","2'"}));
	public static final String NAME = "ShapeLinkProperty";
	public static final boolean IS_EDITABLE = false;
	public static final boolean IS_VISUALISABLE = true;
	
	public StubListPropertyDefinition() {
		super(NAME, IS_EDITABLE);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#copyProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder, org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	@Override
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty otherProperty) {	
		return propertyBuilder.copyListProperty((IListAnnotationProperty) otherProperty);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#createProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder)
	 */
	@Override
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createListProperty(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getDefaultValue()
	 */
	@Override
	public List<String> getDefaultValue() {
		return DEFAULT_VALUE;
	}

}
