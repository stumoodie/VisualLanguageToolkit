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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.rendering.GenericFont;

/**
 * @author Stuart Moodie
 *
 */
public class MockLabelObjectTypeBuilder {
	private final Mockery mockery;
	private ILabelObjectType objectType;
	private IObjectTypeParentingRules parentingRules;
	private int index;
	private INotationSyntaxService syntaxService;
	private ILabelAttributeDefaults attributeDefaults;
	private String name;
	private Colour lineColour = Colour.RED;
	private Colour fontColour = Colour.WHITE;
	private GenericFont font = new GenericFont();
	private LineStyle lineStyle = LineStyle.SOLID;
	private double lineWidth = 2.3;
	private Colour fillColour = Colour.BLUE;

	public MockLabelObjectTypeBuilder(Mockery mock, INotationSyntaxService syntaxService, int idx, String name){
		this.mockery = mock;
		this.name = name;
		this.objectType = mock.mock(ILabelObjectType.class, name);
		this.parentingRules = mock.mock(IObjectTypeParentingRules.class, createParentingRulesName(name));
		this.index = idx;
		this.syntaxService = syntaxService;
	}
	
	
	/**
	 * @param name
	 * @return
	 */
	private String createParentingRulesName(String name) {
		StringBuilder buf = new StringBuilder(name);
		buf.append("LabelParentingRules");
		return buf.toString();
	}


	private String createAttDefaultsName() {
		StringBuilder buf = new StringBuilder(name);
		buf.append("AttributeDefaults");
		return buf.toString();
	}

	public void setDefaultFontColour(Colour fontColour){
		this.fontColour = fontColour;
	}
	
	public void setDefaultFont(GenericFont font){
		this.font = font;
	}
	
	public void build(){
		this.attributeDefaults = mockery.mock(ILabelAttributeDefaults.class, createAttDefaultsName());
		this.mockery.checking(new Expectations(){{
			allowing(objectType).getUniqueId(); will(returnValue(index));
			allowing(objectType).getSyntaxService(); will(returnValue(syntaxService));
			allowing(objectType).getName(); will(returnValue("rootName"));
			allowing(objectType).getDescription(); will(returnValue("Descn"));
			allowing(objectType).getParentingRules(); will(returnValue(parentingRules));
			allowing(objectType).getDefaultAttributes(); will(returnValue(attributeDefaults));

			allowing(attributeDefaults).getMinimumSize(); will(returnValue(new Dimension(20.0, 23.4)));
			allowing(attributeDefaults).getLineColour(); will(returnValue(lineColour));
			allowing(attributeDefaults).getFontColour(); will(returnValue(fontColour));
			allowing(attributeDefaults).getFont(); will(returnValue(font));
			allowing(attributeDefaults).getLineStyle(); will(returnValue(lineStyle));
			allowing(attributeDefaults).getLineWidth(); will(returnValue(lineWidth));
			allowing(attributeDefaults).getFillColour(); will(returnValue(fillColour ));
			allowing(attributeDefaults).getDisplayFormat(); will(returnValue(null));
		}});
	}
	
	public ILabelObjectType getObjectType(){
		return this.objectType;
	}
}
