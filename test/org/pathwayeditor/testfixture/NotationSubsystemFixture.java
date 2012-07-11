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

import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.jmock.api.Invocation;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author Stuart Moodie
 *
 */
public class NotationSubsystemFixture {
	public static final int ROOT_TYPE_ID = 0;
	public static final int SHAPE_TYPE_A_ID = 1;
	public static final int SHAPE_TYPE_B_ID = 2;
	public static final int SHAPE_TYPE_C_ID = 3;
	public static final int LINK_TYPE_D_ID = 4;
	public static final int LINK_TYPE_E_ID = 5;
	public static final int LABEL_TYPE_ID = 6;
	public static final String SHAPE_TYPE_A_PROP_NAME = "shapeTypeAName";
	public static final String SHAPE_TYPE_B_PROP_NAME = "shapeTypeBName";
	public static final int ANCHOR_NODE_TYPE_ID = 7;
	
	private final Mockery mockery;
	private INotationSubsystem notationSubsystem;
	private INotationSyntaxService syntaxService;
	private IRootObjectType rootType;
	private IShapeObjectType shapeTypeA;
	private IShapeObjectType shapeTypeB;
	private IShapeObjectType shapeTypeC;
	private ILinkObjectType linkTypeD;
	private ILinkObjectType linkTypeE;
	private IObjectTypeParentingRules rootTypeParenting;
	private INotation notation;
	private ILabelObjectType labelObjectType;
	private IAnchorNodeObjectType anchorNodeType;
	
	public static class CreatePropertyAction implements Action {
//		private IPlainTextPropertyDefinition defn;
		
		public CreatePropertyAction(){
//			this.defn = builder;
		}
		
		@Override
		public void describeTo(Description descn) {
			descn.appendText("get removal state");
		}

		@Override
		public IPlainTextAnnotationProperty invoke(Invocation invocation) throws Throwable {
			IPropertyBuilder builder = (IPropertyBuilder)invocation.getParameter(0);
			IPlainTextPropertyDefinition testPropDefn = (IPlainTextPropertyDefinition)invocation.getInvokedObject();
			IPlainTextAnnotationProperty retVal = builder.createPlainTextProperty(testPropDefn);
			return retVal;
		}
		
	}
	
	public static Action buildTextProperty(){
		return new CreatePropertyAction();
	}

	
	public NotationSubsystemFixture(Mockery mockery){
		this.mockery = mockery;
	}
	
	public Mockery getMockery(){
		return this.mockery;
	}
	
	public void buildFixture(){
		this.notationSubsystem = mockery.mock(INotationSubsystem.class, "notationSubsystem");
		this.syntaxService = mockery.mock(INotationSyntaxService.class, "syntaxService");
		this.rootType = mockery.mock(IRootObjectType.class, "rootType");
		this.rootTypeParenting = mockery.mock(IObjectTypeParentingRules.class, "rootTypeParenting");
		this.notation = mockery.mock(INotation.class, "notation");
		
		MockShapeObjectTypeBuilder showObjectTypeABuilder = new MockShapeObjectTypeBuilder(mockery, syntaxService, SHAPE_TYPE_A_ID, "shapeTypeA");
		showObjectTypeABuilder.addTextProperty(SHAPE_TYPE_A_PROP_NAME, "PropNameAValue", true, true);
		showObjectTypeABuilder.build();
		this.shapeTypeA = showObjectTypeABuilder.getObjectType();
		MockShapeObjectTypeBuilder showObjectTypeBBuilder = new MockShapeObjectTypeBuilder(mockery, syntaxService, SHAPE_TYPE_B_ID, "shapeTypeB");
		showObjectTypeBBuilder.addTextProperty(SHAPE_TYPE_B_PROP_NAME, "PropNameBValue", true, false);
		showObjectTypeBBuilder.build();
		this.shapeTypeB = showObjectTypeBBuilder.getObjectType();
		MockShapeObjectTypeBuilder showObjectTypeCBuilder = new MockShapeObjectTypeBuilder(mockery, syntaxService, SHAPE_TYPE_C_ID, "shapeTypeC");
		showObjectTypeCBuilder.build();
		this.shapeTypeC = showObjectTypeCBuilder.getObjectType();
		showObjectTypeABuilder.buildParentingRules(shapeTypeA, shapeTypeC);
		showObjectTypeBBuilder.buildParentingRules(shapeTypeA, shapeTypeB);
		showObjectTypeCBuilder.buildParentingRules();
		MockLinkObjectTypeBuilder linkTypeDBuilder = new MockLinkObjectTypeBuilder(mockery, syntaxService, LINK_TYPE_D_ID, "linkTypeD");
		linkTypeDBuilder.build();
		this.linkTypeD = linkTypeDBuilder.getObjectType();
		MockLinkObjectTypeBuilder linkTypeEBuilder = new MockLinkObjectTypeBuilder(mockery, syntaxService, LINK_TYPE_E_ID, "linkTypeE");
		linkTypeEBuilder.build();
		this.linkTypeE = linkTypeEBuilder.getObjectType();
		MockLabelObjectTypeBuilder labelTypeBuilder = new MockLabelObjectTypeBuilder(mockery, syntaxService, LABEL_TYPE_ID, "labelType");
		labelTypeBuilder.build();
		this.labelObjectType = labelTypeBuilder.getObjectType();
		MockAnchorNodeObjectTypeBuilder anchorNodeTypeBuilder = new MockAnchorNodeObjectTypeBuilder(mockery, syntaxService, ANCHOR_NODE_TYPE_ID, "anchorNodeType");
		anchorNodeTypeBuilder.build();
		this.anchorNodeType = anchorNodeTypeBuilder.getObjectType();
		linkTypeDBuilder.buildParentingRules(this.anchorNodeType);
		linkTypeDBuilder.setSources(this.shapeTypeA, this.anchorNodeType);
		linkTypeDBuilder.setTargets(this.shapeTypeA, this.shapeTypeB);
		linkTypeDBuilder.buildConnectionRules();
		linkTypeEBuilder.buildParentingRules(this.anchorNodeType);
		linkTypeEBuilder.setSources(this.shapeTypeB);
		linkTypeEBuilder.setTargets(this.shapeTypeA);
		linkTypeEBuilder.buildConnectionRules();
		this.mockery.checking(new Expectations(){{
			allowing(notationSubsystem).getNotation(); will(returnValue(notation));
			allowing(notationSubsystem).getSyntaxService(); will(returnValue(syntaxService));
			
			allowing(syntaxService).getNotationSubsystem(); will(returnValue(notationSubsystem));
			allowing(syntaxService).getRootObjectType(); will(returnValue(rootType));
			allowing(syntaxService).getShapeObjectType(SHAPE_TYPE_A_ID); will(returnValue(shapeTypeA));
			allowing(syntaxService).getShapeObjectType(SHAPE_TYPE_B_ID); will(returnValue(shapeTypeB));
			allowing(syntaxService).getShapeObjectType(SHAPE_TYPE_C_ID); will(returnValue(shapeTypeC));
			allowing(syntaxService).getLinkObjectType(LINK_TYPE_D_ID); will(returnValue(linkTypeD));
			allowing(syntaxService).getLinkObjectType(LINK_TYPE_E_ID); will(returnValue(linkTypeE));
			allowing(syntaxService).getAnchorNodeObjectType(ANCHOR_NODE_TYPE_ID); will(returnValue(anchorNodeType));
			allowing(syntaxService).getObjectType(ROOT_TYPE_ID); will(returnValue(rootType));
			allowing(syntaxService).getObjectType(SHAPE_TYPE_A_ID); will(returnValue(shapeTypeA));
			allowing(syntaxService).getObjectType(SHAPE_TYPE_B_ID); will(returnValue(shapeTypeB));
			allowing(syntaxService).getObjectType(SHAPE_TYPE_C_ID); will(returnValue(shapeTypeC));
			allowing(syntaxService).getObjectType(LINK_TYPE_D_ID); will(returnValue(linkTypeD));
			allowing(syntaxService).getObjectType(LINK_TYPE_E_ID); will(returnValue(linkTypeE));
			allowing(syntaxService).getObjectType(ANCHOR_NODE_TYPE_ID); will(returnValue(anchorNodeType));
			allowing(syntaxService).objectTypeIterator(); will(returnIterator(rootType, shapeTypeA, shapeTypeB, shapeTypeC, linkTypeD, linkTypeE, anchorNodeType));
			allowing(syntaxService).getNotation(); will(returnValue(notation));
			allowing(syntaxService).getLabelObjectType(LABEL_TYPE_ID); will(returnValue(labelObjectType));
			allowing(syntaxService).getLabelObjectTypeByProperty(with(any(IPropertyDefinition.class))); will(returnValue(labelObjectType));
			allowing(syntaxService).isVisualisableProperty(with(any(IPropertyDefinition.class))); will(returnValue(true));
			
			allowing(notation).getDescription(); will(returnValue("Test Fixture Notation"));
			allowing(notation).getDisplayName(); will(returnValue("Fixture Notation"));
			allowing(notation).getQualifiedName(); will(returnValue("org.pathwayeditor.businessobjects.notation.testfixture"));
			allowing(notation).getVersion(); will(returnValue(new Version(1, 0, 0)));
			
			allowing(rootType).getUniqueId(); will(returnValue(ROOT_TYPE_ID));
			allowing(rootType).getSyntaxService(); will(returnValue(syntaxService));
			allowing(rootType).getName(); will(returnValue("rootName"));
			allowing(rootType).getDescription(); will(returnValue("Descn"));
			allowing(rootType).getParentingRules(); will(returnValue(rootTypeParenting));
			
			allowing(rootTypeParenting).getObjectType(); will(returnValue(rootType));
			allowing(rootTypeParenting).isValidChild(with(not(isOneOf(shapeTypeA, shapeTypeB, shapeTypeC)))); will(returnValue(false));
			allowing(rootTypeParenting).isValidChild(with(isOneOf(shapeTypeA, shapeTypeB, shapeTypeC))); will(returnValue(true));
		}});
	}

	public INotationSubsystem getNotationSubsystem(){
		return this.notationSubsystem;
	}
}
