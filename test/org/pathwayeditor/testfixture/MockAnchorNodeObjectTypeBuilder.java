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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.rendering.GenericFont;

/**
 * @author Stuart Moodie
 *
 */
public class MockAnchorNodeObjectTypeBuilder {
	private final Mockery mockery;
	private String objectTypeName;
	private int index;
	private INotationSyntaxService syntaxService;
	private Colour fillColour = Colour.BLACK;
	private Colour fontColour = Colour.WHITE;
	private Colour lineColour = Colour.BLUE;
	private GenericFont font = new GenericFont();
	private LineStyle lineStyle = LineStyle.DASH_DOT_DOT;
	private double lineWidth = 3.1;
	private String shapeDefn = "10.0 11.0 13.0 14.6 rect";
	private Dimension size = new Dimension(11.0, 13.4);
	private IObjectTypeParentingRules anchorNodeTypeParenting;
	private IAnchorNodeObjectType objectType;
	private IAnchorNodeAttributeDefaults anchorNodeAttributeDefaults;

	public MockAnchorNodeObjectTypeBuilder(Mockery mock, INotationSyntaxService syntaxService, int idx, String objectTypeName){
		this.mockery = mock;
		this.index = idx;
		this.objectTypeName = objectTypeName;
		this.syntaxService = syntaxService;
		this.anchorNodeTypeParenting = null;
	}
	
	public void setDefaultFillColour(Colour fillColour){
		this.fillColour = fillColour;
	}
	
	public void setDefaultFontColour(Colour fontColour){
		this.fontColour = fontColour;
	}
	
	public void setDefaultFont(GenericFont font){
		this.font = font;
	}
	
	public void setDefaultLineColour(Colour lineColour){
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
	
	
	
	
	public void build(){
		objectType = mockery.mock(IAnchorNodeObjectType.class, objectTypeName);
		anchorNodeAttributeDefaults = this.mockery.mock(IAnchorNodeAttributeDefaults.class, createAttDefaultsName());
		this.mockery.checking(new Expectations(){{
			allowing(objectType).getUniqueId(); will(returnValue(index));
			allowing(objectType).getSyntaxService(); will(returnValue(syntaxService));
			allowing(objectType).getName(); will(returnValue("rootName"));
			allowing(objectType).getDescription(); will(returnValue("Descn"));
			allowing(objectType).getDefaultAttributes(); will(returnValue(anchorNodeAttributeDefaults));
			
			allowing(anchorNodeAttributeDefaults).getFillColour(); will(returnValue(fillColour));
			allowing(anchorNodeAttributeDefaults).getLineColour(); will(returnValue(lineColour));
			allowing(anchorNodeAttributeDefaults).getFontColour(); will(returnValue(fontColour));
			allowing(anchorNodeAttributeDefaults).getFont(); will(returnValue(font));
			allowing(anchorNodeAttributeDefaults).getLineStyle(); will(returnValue(lineStyle));
			allowing(anchorNodeAttributeDefaults).getLineWidth(); will(returnValue(lineWidth));
			allowing(anchorNodeAttributeDefaults).getShapeDefinition(); will(returnValue(shapeDefn));
			allowing(anchorNodeAttributeDefaults).getSize(); will(returnValue(size));
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

	public void buildParentingRules(final IObjectType ... children) {
		this.anchorNodeTypeParenting = this.mockery.mock(IObjectTypeParentingRules.class, createParentingRulesName());
		this.mockery.checking(new Expectations(){{
			allowing(anchorNodeTypeParenting).getObjectType(); will(returnValue(objectType));
			allowing(anchorNodeTypeParenting).isValidChild(with(not(isOneOf(children)))); will(returnValue(false));
			allowing(anchorNodeTypeParenting).isValidChild(with(isOneOf(children))); will(returnValue(true));

			allowing(objectType).getParentingRules(); will(returnValue(anchorNodeTypeParenting));
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

	public IAnchorNodeObjectType getObjectType(){
		return this.objectType;
	}
}
