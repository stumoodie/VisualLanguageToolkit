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

package org.pathwayeditor.businessobjects.exchange;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.exchange.castor.Canvas;
import org.pathwayeditor.businessobjects.impl.Model;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;


/**
 * @author Stuart Moodie
 *
 */
public class XMLMarshallingLinkChildrenTest {
	private IModel graph;
	private Mockery mockery;

	
	@Before
	public void setUp(){
		this.mockery = new JUnit4Mockery();
		NotationSubsystemFixture notationFixture = new NotationSubsystemFixture(mockery);
		notationFixture.buildFixture();
		INotationSyntaxService syntaxService = notationFixture.getNotationSubsystem().getSyntaxService();
//		IRootAttribute rootAtt = new RootAttribute("testModel", syntaxService.getRootObjectType());
		this.graph = new Model("testModel", notationFixture.getNotationSubsystem());
		final IShapeObjectType shapeOt = syntaxService.getShapeObjectType(NotationSubsystemFixture.SHAPE_TYPE_A_ID);
		final IPropertyDefinition shapeTypeAName = shapeOt.getDefaultAttributes().getPropertyDefinition(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME);
		this.mockery.checking(new Expectations(){{
			allowing(shapeTypeAName).createProperty(with(any(IPropertyBuilder.class))); will(NotationSubsystemFixture.buildTextProperty());
		}});
		ShapeBuilder builder = new ShapeBuilder(this.graph.getRootAttribute(), shapeOt);
		builder.setNamePropertyName(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME);
		IShapeAttribute shapeA = builder.create("ShapeA");
		IShapeAttribute shapeB = builder.create("ShapeB");
		IShapeAttribute shapeC = builder.create("ShapeC");
		LinkBuilder linkBuilder = new LinkBuilder(this.graph.getRootAttribute(), syntaxService.getLinkObjectType(NotationSubsystemFixture.LINK_TYPE_D_ID));
		ILinkAttribute link1 = linkBuilder.create(shapeA, shapeB);
		linkBuilder.create(shapeA, shapeC);
		LabelBuilder labelBuilder = new LabelBuilder(shapeA, NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME);
		labelBuilder.create();
		labelBuilder.setOwningAttribute(shapeB);
		labelBuilder.create();
		AnchorNodeBuilder anchorNodeBuilder = new AnchorNodeBuilder(link1, syntaxService.getAnchorNodeObjectType(NotationSubsystemFixture.ANCHOR_NODE_TYPE_ID));
		IAnchorNodeAttribute anchorNodeAtt = anchorNodeBuilder.create();
		linkBuilder.create(anchorNodeAtt, shapeB);
	}
	
	
	@After
	public void tearDown(){
		this.mockery = null;
	}
	
	
	@Test
	public void testMarshalling() throws IOException, MarshalException, ValidationException{
		Writer out = null;
		try {
			CanvasMarshaller builder = new CanvasMarshaller();
			builder.setModel(graph);
			builder.buildCanvas();
			Canvas xmlCanvas = builder.getXmlTopLevel();
			xmlCanvas.validate();
			assertTrue("valid XML", xmlCanvas.isValid());
			out = new FileWriter("test.xml");
			builder.write(out);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
