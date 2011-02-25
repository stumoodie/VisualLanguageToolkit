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

package org.pathwayeditor.testfixture;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;
import org.pathwayeditor.figure.geometry.Dimension;

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
	private RGB lineColour = RGB.RED;
	private LineStyle lineStyle = LineStyle.SOLID;
	private double lineWidth = 2.3;
	private RGB fillColour = RGB.BLUE;
	private boolean hasNoBorder = false;
	private boolean hasNoFill = false;

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

	public void build(){
		this.attributeDefaults = mockery.mock(ILabelAttributeDefaults.class, createAttDefaultsName());
		this.mockery.checking(new Expectations(){{
			allowing(objectType).getUniqueId(); will(returnValue(index));
			allowing(objectType).getSyntaxService(); will(returnValue(syntaxService));
			allowing(objectType).getName(); will(returnValue("rootName"));
			allowing(objectType).getDescription(); will(returnValue("Descn"));
			allowing(objectType).getParentingRules(); will(returnValue(parentingRules));
			allowing(objectType).getDefaultAttributes(); will(returnValue(attributeDefaults));

//			allowing(retVal).getFillColour(); will(returnValue(RGB.RED));
//			allowing(retVal).getLineColour(); will(returnValue(RGB.BLUE));
//			allowing(retVal).getLineStyle(); will(returnValue(LineStyle.SOLID));
//			allowing(retVal).getLineWidth(); will(returnValue(2.3));
			allowing(attributeDefaults).getMinimumSize(); will(returnValue(new Dimension(20.0, 23.4)));
//			allowing(retVal).hasNoBorder(); will(returnValue(false));
//			allowing(retVal).hasNoFill(); will(returnValue(false));
//			allowing(retVal).getLabelLocationPolicy(); will(returnValue(LabelLocationPolicy.CENTRE));
			allowing(attributeDefaults).getLineColour(); will(returnValue(lineColour));
			allowing(attributeDefaults).getLineStyle(); will(returnValue(lineStyle));
			allowing(attributeDefaults).getLineWidth(); will(returnValue(lineWidth));
			allowing(attributeDefaults).getFillColour(); will(returnValue(fillColour ));
			allowing(attributeDefaults).hasNoBorder(); will(returnValue(hasNoBorder));
			allowing(attributeDefaults).hasNoFill(); will(returnValue(hasNoFill ));
		}});
	}
	
	public ILabelObjectType getObjectType(){
		return this.objectType;
	}
}
