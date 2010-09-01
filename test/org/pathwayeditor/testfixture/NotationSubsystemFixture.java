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

import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.not;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class NotationSubsystemFixture {
	public static final int ROOT_TYPE_ID = 0;
	public static final int SHAPE_TYPE_A_ID = 1;
	public static final int SHAPE_TYPE_B_ID = 2;
	public static final int SHAPE_TYPE_C_ID = 3;
	public static final int LINK_TYPE_D_ID = 4;
	public static final int LINK_TYPE_E_ID = 5;
	public static final String SHAPE_TYPE_A_PROP_NAME = "shapeTypeAName";
	public static final String SHAPE_TYPE_B_PROP_NAME = "shapeTypeBName";
	
	private final Mockery mockery;
	private INotationSubsystem notationSubsystem;
	private INotationSyntaxService syntaxService;
	private IRootObjectType rootType;
	private IShapeObjectType shapeTypeA;
	private IShapeObjectType shapeTypeB;
	private IShapeObjectType shapeTypeC;
	private ILinkObjectType linkTypeD;
	private ILinkObjectType linkTypeE;
	private IRootObjectParentingRules rootTypeParenting;
	private ILinkConnectionRules linkTypeDConnections;
	private ILinkConnectionRules linkTypeEConnections;
	
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
		this.linkTypeD = mockery.mock(ILinkObjectType.class, "linkTypeD");
		this.linkTypeE = mockery.mock(ILinkObjectType.class, "linkTypeE");
		this.rootTypeParenting = mockery.mock(IRootObjectParentingRules.class, "rootTypeParenting");
		this.linkTypeDConnections = mockery.mock(ILinkConnectionRules.class, "linkTypeDConnections");
		this.linkTypeEConnections = mockery.mock(ILinkConnectionRules.class, "linkTypeEConnections");
		
		MockShapeObjectTypeBuilder showObjectTypeABuilder = new MockShapeObjectTypeBuilder(mockery, syntaxService, SHAPE_TYPE_A_ID, "shapeTypeA");
		showObjectTypeABuilder.addTextProperty(SHAPE_TYPE_A_PROP_NAME, "PropNameAValue");
		showObjectTypeABuilder.build();
		this.shapeTypeA = showObjectTypeABuilder.getObjectType();
		MockShapeObjectTypeBuilder showObjectTypeBBuilder = new MockShapeObjectTypeBuilder(mockery, syntaxService, SHAPE_TYPE_B_ID, "shapeTypeB");
		showObjectTypeBBuilder.addTextProperty(SHAPE_TYPE_B_PROP_NAME, "PropNameBValue");
		showObjectTypeBBuilder.build();
		this.shapeTypeB = showObjectTypeBBuilder.getObjectType();
		MockShapeObjectTypeBuilder showObjectTypeCBuilder = new MockShapeObjectTypeBuilder(mockery, syntaxService, SHAPE_TYPE_C_ID, "shapeTypeC");
		showObjectTypeCBuilder.build();
		this.shapeTypeC = showObjectTypeCBuilder.getObjectType();
		showObjectTypeABuilder.buildParentingRules(shapeTypeA, shapeTypeC);
		showObjectTypeBBuilder.buildParentingRules(shapeTypeA, shapeTypeB);
		showObjectTypeCBuilder.buildParentingRules();
		MockLinkObjectTypeBuilder linkTypeDBuilder = new MockLinkObjectTypeBuilder(mockery, syntaxService, LINK_TYPE_D_ID, linkTypeD, this.linkTypeDConnections);
		linkTypeDBuilder.setSources(this.shapeTypeA);
		linkTypeDBuilder.setTargets(this.shapeTypeA, this.shapeTypeB);
		linkTypeDBuilder.build();
		MockLinkObjectTypeBuilder linkTypeEBuilder = new MockLinkObjectTypeBuilder(mockery, syntaxService, LINK_TYPE_E_ID, linkTypeE, this.linkTypeEConnections);
		linkTypeEBuilder.setSources(this.shapeTypeB);
		linkTypeEBuilder.setTargets(this.shapeTypeA);
		linkTypeEBuilder.build();
		this.mockery.checking(new Expectations(){{
			allowing(notationSubsystem).getSyntaxService(); will(returnValue(syntaxService));
			
			allowing(syntaxService).getNotationSubsystem(); will(returnValue(notationSubsystem));
			allowing(syntaxService).getRootObjectType(); will(returnValue(rootType));
			allowing(syntaxService).getShapeObjectType(SHAPE_TYPE_A_ID); will(returnValue(shapeTypeA));
			allowing(syntaxService).getShapeObjectType(SHAPE_TYPE_B_ID); will(returnValue(shapeTypeB));
			allowing(syntaxService).getShapeObjectType(SHAPE_TYPE_C_ID); will(returnValue(shapeTypeC));
			allowing(syntaxService).getLinkObjectType(LINK_TYPE_D_ID); will(returnValue(linkTypeD));
			allowing(syntaxService).getLinkObjectType(LINK_TYPE_E_ID); will(returnValue(linkTypeE));
			allowing(syntaxService).getObjectType(ROOT_TYPE_ID); will(returnValue(rootType));
			allowing(syntaxService).getObjectType(SHAPE_TYPE_A_ID); will(returnValue(shapeTypeA));
			allowing(syntaxService).getObjectType(SHAPE_TYPE_B_ID); will(returnValue(shapeTypeB));
			allowing(syntaxService).getObjectType(SHAPE_TYPE_C_ID); will(returnValue(shapeTypeC));
			allowing(syntaxService).getObjectType(LINK_TYPE_D_ID); will(returnValue(linkTypeD));
			allowing(syntaxService).getObjectType(LINK_TYPE_E_ID); will(returnValue(linkTypeE));
			
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
