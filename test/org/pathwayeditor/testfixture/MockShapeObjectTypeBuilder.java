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


package org.pathwayeditor.testfixture;

import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.not;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * @author Stuart Moodie
 *
 */
public class MockShapeObjectTypeBuilder {
	private interface IPropValueCreator {
		
		IPlainTextPropertyDefinition createDefn(String name, String value, boolean isVisualisable, boolean isAlwaysDisplayed);
		
		INumberPropertyDefinition createDefn(String name, Number value, boolean isVisualisable, boolean isAlwaysDisplayed);
		
	}
	
	private interface IPropValues<T> {
		String getName();
		
		T getValue();
		
		IPropertyDefinition create(IPropValueCreator propValueCreator);
	}
	
	
	private final Mockery mockery;
	private String objectTypeName;
	private int index;
	private INotationSyntaxService syntaxService;
	private RGB fillColour = RGB.BLACK;
	private RGB lineColour = RGB.BLUE;
	private LineStyle lineStyle = LineStyle.DASH_DOT_DOT;
	private double lineWidth = 3.1;
	private String shapeDefn = "10.0 11.0 13.0 14.6 rect";
	private Dimension size = new Dimension(11.0, 13.4);
	private IShapeParentingRules shapeTypeParenting;
	private final List<IPropertyDefinition> propertyDefs = new ArrayList<IPropertyDefinition>();
	private List<IPropValues<? extends Object>> propCreationDefs = new ArrayList<IPropValues<? extends Object>>();
	private IShapeObjectType objectType;
	private IShapeAttributeDefaults shapeAttributeDefaults;

	public MockShapeObjectTypeBuilder(Mockery mock, INotationSyntaxService syntaxService, int idx, String objectTypeName){
		this.mockery = mock;
		this.index = idx;
		this.objectTypeName = objectTypeName;
		this.syntaxService = syntaxService;
		this.shapeTypeParenting = null;
	}
	
	public void setDefaultFillColour(RGB fillColour){
		this.fillColour = fillColour;
	}
	
	public void setDefaultLineColour(RGB lineColour){
		this.lineColour = lineColour;
	}
	
	public void setDefaultLineStyle(LineStyle lineStyle){
		this.lineStyle = lineStyle;
	}
	
	public void setDefaultShapeDefn(String shapeDefn){
		this.shapeDefn = shapeDefn;
	}
	
	
	public void setDefaultSize(Dimension size){
		this.size = size;
	}
	
	
	
	public void addTextProperty(final String name, final String value, final boolean isVisualisable, final boolean isAlwaysDisplayed){
		this.propCreationDefs.add(new IPropValues<String>() {

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getValue() {
				return value;
			}

			@Override
			public IPropertyDefinition create(IPropValueCreator propValueCreator) {
				return propValueCreator.createDefn(name, value, isVisualisable, isAlwaysDisplayed);
			}
		});
	}
	
	public void build(){
		objectType = mockery.mock(IShapeObjectType.class, objectTypeName);
		shapeAttributeDefaults = this.mockery.mock(IShapeAttributeDefaults.class, createAttDefaultsName());
		this.mockery.checking(new Expectations(){{
			allowing(objectType).getUniqueId(); will(returnValue(index));
			allowing(objectType).getSyntaxService(); will(returnValue(syntaxService));
			allowing(objectType).getName(); will(returnValue("rootName"));
			allowing(objectType).getDescription(); will(returnValue("Descn"));
			allowing(objectType).getDefaultAttributes(); will(returnValue(shapeAttributeDefaults));
			
			allowing(shapeAttributeDefaults).getFillColour(); will(returnValue(fillColour));
			allowing(shapeAttributeDefaults).getLineColour(); will(returnValue(lineColour));
			allowing(shapeAttributeDefaults).getLineStyle(); will(returnValue(lineStyle));
			allowing(shapeAttributeDefaults).getLineWidth(); will(returnValue(lineWidth));
			allowing(shapeAttributeDefaults).getShapeDefinition(); will(returnValue(shapeDefn));
			allowing(shapeAttributeDefaults).getSize(); will(returnValue(size));
		}});
		buildProperties();
		this.mockery.checking(new Expectations(){{
			allowing(shapeAttributeDefaults).propertyDefinitionIterator(); will(returnIterator(propertyDefs));
		}});
	}
	
	/**
	 * @return
	 */
	private String createAttDefaultsName() {
		StringBuilder buf = new StringBuilder(this.objectTypeName);
		buf.append("AttributeDefaults");
		return buf.toString();
	}

	/**
	 * 
	 */
	private void buildProperties() {
		for(IPropValues<? extends Object> propCreationValue : this.propCreationDefs){
			this.propertyDefs.add(propCreationValue.create(new IPropValueCreator() {
				@Override
				public INumberPropertyDefinition createDefn(final String name, final Number value, final boolean isVisualisable, final boolean isAlwaysDisplayed) {
					final String propName = createPropName(name);
					final INumberPropertyDefinition propDefn = mockery.mock(INumberPropertyDefinition.class, propName);
					mockery.checking(new Expectations(){{
						allowing(propDefn).getName(); will(returnValue(name));
						allowing(propDefn).getDisplayName(); will(returnValue(name));
						allowing(propDefn).getDefaultValue(); will(returnValue(value));
						allowing(shapeAttributeDefaults).getPropertyDefinition(propName); will(returnValue(propDefn));
					}});
					return propDefn;
				}
				
				@Override
				public IPlainTextPropertyDefinition createDefn(final String name, final String value, final boolean isVisualisable, final boolean isAlwaysDisplayed) {
					final String propName = createPropName(name);
					final IPlainTextPropertyDefinition propDefn = mockery.mock(IPlainTextPropertyDefinition.class, propName);
					mockery.checking(new Expectations(){{
						allowing(propDefn).getName(); will(returnValue(propName));
						allowing(propDefn).getDisplayName(); will(returnValue(name));
						allowing(propDefn).getDefaultValue(); will(returnValue(value));
						allowing(shapeAttributeDefaults).getPropertyDefinition(propName); will(returnValue(propDefn));
					}});
					return propDefn;
				}
			}));
		}
	}


//	/**
//	 * @param propDefn
//	 * @return
//	 */
//	protected ILabelAttributeDefaults createLabelDefaults(String name) {
//		final ILabelAttributeDefaults retVal = mockery.mock(ILabelAttributeDefaults.class, createLabelAttDefsName(name));
//		mockery.checking(new Expectations(){{
//			allowing(retVal).getFillColour(); will(returnValue(RGB.RED));
//			allowing(retVal).getLineColour(); will(returnValue(RGB.BLUE));
//			allowing(retVal).getLineStyle(); will(returnValue(LineStyle.SOLID));
//			allowing(retVal).getLineWidth(); will(returnValue(2.3));
//			allowing(retVal).getMinimumSize(); will(returnValue(new Dimension(20.0, 23.4)));
//			allowing(retVal).hasNoBorder(); will(returnValue(false));
//			allowing(retVal).hasNoFill(); will(returnValue(false));
//			allowing(retVal).getLabelLocationPolicy(); will(returnValue(LabelLocationPolicy.CENTRE));
//		}});
//		return retVal;
//	}

//	/**
//	 * @param name
//	 * @return
//	 */
//	private String createLabelAttDefsName(String name) {
//		StringBuilder buf = new StringBuilder(this.objectTypeName);
//		buf.append(name);
//		return buf.toString();
//	}

	public void buildParentingRules(final INodeObjectType ... children) {
		this.shapeTypeParenting = this.mockery.mock(IShapeParentingRules.class, createParentingRulesName());
		this.mockery.checking(new Expectations(){{
			allowing(shapeTypeParenting).getObjectType(); will(returnValue(objectType));
			allowing(shapeTypeParenting).isValidChild(with(not(isOneOf(children)))); will(returnValue(false));
			allowing(shapeTypeParenting).isValidChild(with(isOneOf(children))); will(returnValue(true));

			allowing(objectType).getParentingRules(); will(returnValue(shapeTypeParenting));
		}});
	}


	/**
	 * @param objectTypeName2
	 * @return
	 */
	private String createParentingRulesName() {
		StringBuilder buf = new StringBuilder(this.objectTypeName);
		buf.append("ParentingRules");
		return buf.toString();
	}

	/**
	 * @param objectTypeName2
	 * @return
	 */
	private String createPropName(String propName) {
//		StringBuilder buf = new StringBuilder(objectTypeName);
		StringBuilder buf = new StringBuilder();
		buf.append(propName);
		return buf.toString();
	}


	public IShapeObjectType getObjectType(){
		return this.objectType;
	}
}
