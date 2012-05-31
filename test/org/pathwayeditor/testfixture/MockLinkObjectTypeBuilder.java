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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * @author Stuart Moodie
 *
 */
public class MockLinkObjectTypeBuilder {
	private interface IPropValueCreator {
		
		IPlainTextPropertyDefinition createDefn(String name, String value);
		
		INumberPropertyDefinition createDefn(String name, Number value);
		
	}
	
	private interface IPropValues<T> {
		String getName();
		
		T getValue();
		
		IPropertyDefinition create(IPropValueCreator propValueCreator);
	}
	
	private final Mockery mockery;
	private IShapeObjectType[] sources;
	private IShapeObjectType[] targets;
	private ILinkObjectType objectType;
	private ILinkConnectionRules connectionRules;
	private int index;
	private INotationSyntaxService syntaxService;
	private ILinkAttributeDefaults attributeDefaults;
	private String name;
	private Colour lineColour = Colour.GREEN;
	private LineStyle lineStyle = LineStyle.DASH_DOT;
	private double lineWidth = 3.9;
	private final List<IPropertyDefinition> propertyDefs = new ArrayList<IPropertyDefinition>();
	private List<IPropValues<? extends Object>> propCreationDefs = new ArrayList<IPropValues<? extends Object>>();

	public MockLinkObjectTypeBuilder(Mockery mock, INotationSyntaxService syntaxService, int idx, String name){
		this.mockery = mock;
		this.name = name;
		this.objectType = mock.mock(ILinkObjectType.class, name);
		this.connectionRules = mock.mock(ILinkConnectionRules.class, createConnectionRulesName(name));
		this.index = idx;
		this.syntaxService = syntaxService;
	}
	
	
	/**
	 * @param name
	 * @return
	 */
	private String createConnectionRulesName(String name) {
		StringBuilder buf = new StringBuilder(name);
		buf.append("LinkConnRules");
		return buf.toString();
	}


	public void setSources(IShapeObjectType ... sources){
		this.sources = sources;
	}
	
	public void setTargets(IShapeObjectType ... targets){
		this.targets = targets;
	}
	
	
	private String createAttDefaultsName() {
		StringBuilder buf = new StringBuilder(name);
		buf.append("AttributeDefaults");
		return buf.toString();
	}

	public void build(){
		this.attributeDefaults = mockery.mock(ILinkAttributeDefaults.class, createAttDefaultsName());
		final ILinkTerminusDefinition srcTermDefn = mockery.mock(ILinkTerminusDefinition.class, this.name + "SrcTermDefn");
		final ILinkTerminusDefaults srcTermDefaults = mockery.mock(ILinkTerminusDefaults.class, this.name + "SrcTermDefaults");
		final ILinkTerminusDefinition tgtTermDefn = mockery.mock(ILinkTerminusDefinition.class, this.name + "TgtTermDefn");
		final ILinkTerminusDefaults tgtTermDefaults = mockery.mock(ILinkTerminusDefaults.class, this.name + "TgtTermDefaults");
		this.mockery.checking(new Expectations(){{
			allowing(objectType).getUniqueId(); will(returnValue(index));
			allowing(objectType).getSyntaxService(); will(returnValue(syntaxService));
			allowing(objectType).getName(); will(returnValue("rootName"));
			allowing(objectType).getDescription(); will(returnValue("Descn"));
			allowing(objectType).getLinkConnectionRules(); will(returnValue(connectionRules));
			allowing(objectType).getDefaultAttributes(); will(returnValue(attributeDefaults));
			allowing(objectType).getSourceTerminusDefinition(); will(returnValue(srcTermDefn));
			allowing(objectType).getTargetTerminusDefinition(); will(returnValue(tgtTermDefn));

			allowing(srcTermDefn).getDefaultAttributes(); will(returnValue(srcTermDefaults));
			allowing(tgtTermDefn).getDefaultAttributes(); will(returnValue(tgtTermDefaults));
			
			allowing(srcTermDefaults).getEndDecoratorType(); will(returnValue(LinkEndDecoratorShape.ARROW));
			allowing(srcTermDefaults).getEndSize(); will(returnValue(new Dimension(23.0, 13.2)));
			allowing(srcTermDefaults).getGap(); will(returnValue(3.3));

			allowing(tgtTermDefaults).getEndDecoratorType(); will(returnValue(LinkEndDecoratorShape.BAR));
			allowing(tgtTermDefaults).getEndSize(); will(returnValue(new Dimension(3.0, 10.2)));
			allowing(tgtTermDefaults).getGap(); will(returnValue(2.4));
			
			allowing(attributeDefaults).getLineColour(); will(returnValue(lineColour));
			allowing(attributeDefaults).getLineStyle(); will(returnValue(lineStyle));
			allowing(attributeDefaults).getLineWidth(); will(returnValue(lineWidth));

			allowing(connectionRules).isValidSource(with(isOneOf(sources))); will(returnValue(true));
			allowing(connectionRules).isValidSource(with(not(isOneOf(sources)))); will(returnValue(false));
			allowing(connectionRules).isValidTarget(with(isOneOf(sources)), with(isOneOf(targets))); will(returnValue(true));
			allowing(connectionRules).isValidTarget(with(not(isOneOf(sources))), with(any(IShapeObjectType.class))); will(returnValue(false));
			allowing(connectionRules).isValidTarget(with(isOneOf(sources)), with(not(isOneOf(targets)))); will(returnValue(false));
		}});
		buildProperties();
		this.mockery.checking(new Expectations(){{
			allowing(attributeDefaults).propertyDefinitionIterator(); will(returnIterator(propertyDefs));
		}});
	}
	
	private void buildProperties() {
		for(IPropValues<? extends Object> propCreationValue : this.propCreationDefs){
			this.propertyDefs.add(propCreationValue.create(new IPropValueCreator() {
				@Override
				public INumberPropertyDefinition createDefn(final String name, final Number value) {
					final String propName = createPropName(name);
					final INumberPropertyDefinition propDefn = mockery.mock(INumberPropertyDefinition.class, propName);
					mockery.checking(new Expectations(){{
						allowing(propDefn).getName(); will(returnValue(name));
						allowing(propDefn).getDisplayName(); will(returnValue(name));
						allowing(propDefn).getDefaultValue(); will(returnValue(value));
						allowing(attributeDefaults).getPropertyDefinition(propName); will(returnValue(propDefn));
					}});
					return propDefn;
				}
				
				@Override
				public IPlainTextPropertyDefinition createDefn(final String name, final String value) {
					final String propName = createPropName(name);
					final IPlainTextPropertyDefinition propDefn = mockery.mock(IPlainTextPropertyDefinition.class, propName);
					mockery.checking(new Expectations(){{
						allowing(propDefn).getName(); will(returnValue(propName));
						allowing(propDefn).getDisplayName(); will(returnValue(name));
						allowing(propDefn).getDefaultValue(); will(returnValue(value));
						allowing(attributeDefaults).getPropertyDefinition(propName); will(returnValue(propDefn));
					}});
					return propDefn;
				}
			}));
		}
	}

	public void addTextProperty(final String name, final String value){
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
				return propValueCreator.createDefn(name, value);
			}
		});
	}
	
	private String createPropName(String propName) {
//		StringBuilder buf = new StringBuilder(objectTypeName);
		StringBuilder buf = new StringBuilder();
		buf.append(propName);
		return buf.toString();
	}

	public ILinkObjectType getObjectType(){
		return this.objectType;
	}
}
