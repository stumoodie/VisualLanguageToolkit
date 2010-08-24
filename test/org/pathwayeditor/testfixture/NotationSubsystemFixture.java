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
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;

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
	private IShapeParentingRules shapeTypeAParenting;
	private IShapeParentingRules shapeTypeBParenting;
	private IShapeParentingRules shapeTypeCParenting;
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
		this.shapeTypeA = mockery.mock(IShapeObjectType.class, "shapeTypeA");
		this.shapeTypeB = mockery.mock(IShapeObjectType.class, "shapeTypeB");
		this.shapeTypeC = mockery.mock(IShapeObjectType.class, "shapeTypeC");
		this.linkTypeD = mockery.mock(ILinkObjectType.class, "linkTypeD");
		this.linkTypeE = mockery.mock(ILinkObjectType.class, "linkTypeE");
		this.rootTypeParenting = mockery.mock(IRootObjectParentingRules.class, "rootTypeParenting");
		this.shapeTypeAParenting = mockery.mock(IShapeParentingRules.class, "shapeTypeAParenting");
		this.shapeTypeBParenting = mockery.mock(IShapeParentingRules.class, "shapeTypeBParenting");
		this.shapeTypeCParenting = mockery.mock(IShapeParentingRules.class, "shapeTypeCParenting");
		this.linkTypeDConnections = mockery.mock(ILinkConnectionRules.class, "linkTypeDConnections");
		this.linkTypeEConnections = mockery.mock(ILinkConnectionRules.class, "linkTypeEConnections");
		
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
		
		defineNodeObjectType(SHAPE_TYPE_A_ID, shapeTypeA, shapeTypeAParenting, shapeTypeA, shapeTypeC);
		defineNodeObjectType(SHAPE_TYPE_A_ID, shapeTypeB, shapeTypeBParenting, shapeTypeA, shapeTypeB);
		defineNodeObjectType(SHAPE_TYPE_A_ID, shapeTypeC, shapeTypeCParenting);
		MockLinkObjectTypeBuilder linkTypeDBuilder = new MockLinkObjectTypeBuilder(mockery, syntaxService, LINK_TYPE_D_ID, linkTypeD, this.linkTypeDConnections);
		linkTypeDBuilder.setSources(this.shapeTypeA);
		linkTypeDBuilder.setTargets(this.shapeTypeA, this.shapeTypeB);
		linkTypeDBuilder.build();
		MockLinkObjectTypeBuilder linkTypeEBuilder = new MockLinkObjectTypeBuilder(mockery, syntaxService, LINK_TYPE_E_ID, linkTypeE, this.linkTypeEConnections);
		linkTypeEBuilder.setSources(this.shapeTypeB);
		linkTypeEBuilder.setTargets(this.shapeTypeA);
		linkTypeEBuilder.build();
	}

	private void defineNodeObjectType(final int idx, final IShapeObjectType objectType, final IShapeParentingRules shapeTypeParenting, final INodeObjectType ... children){
		this.mockery.checking(new Expectations(){{
			allowing(objectType).getUniqueId(); will(returnValue(idx));
			allowing(objectType).getSyntaxService(); will(returnValue(syntaxService));
			allowing(objectType).getName(); will(returnValue("rootName"));
			allowing(objectType).getDescription(); will(returnValue("Descn"));
			allowing(objectType).getParentingRules(); will(returnValue(shapeTypeParenting));
			
			allowing(shapeTypeParenting).getObjectType(); will(returnValue(objectType));
			allowing(shapeTypeParenting).isValidChild(with(not(isOneOf(children)))); will(returnValue(false));
			allowing(shapeTypeParenting).isValidChild(with(isOneOf(children))); will(returnValue(true));
		}});
	}
	
	public INotationSubsystem getNotationSubsystem(){
		return this.notationSubsystem;
	}
}
