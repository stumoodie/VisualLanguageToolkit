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


package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author Stuart Moodie
 *
 */
@RunWith(JMock.class)
public class ShapeAttributeFactoryParentingCopyTest {
	private Mockery mockery;
	private CanvasTestFixture testFixture;
	private NotationSubsystemFixture notationSubsystemFixture;
	private IShapeAttributeFactory testInstance;
	private IShapeAttribute expectedParentAttribute;
	private IShapeObjectType expectedObjectType;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationSubsystemFixture = new NotationSubsystemFixture(mockery); 
		this.notationSubsystemFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", notationSubsystemFixture.getNotationSubsystem());
		this.testFixture.buildFixture();
		this.expectedParentAttribute = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		this.testInstance = new ShapeAttributeFactory(new IndexCounter());
		this.expectedObjectType = notationSubsystemFixture.getNotationSubsystem().getSyntaxService().getShapeObjectType(NotationSubsystemFixture.SHAPE_TYPE_C_ID);
		this.testInstance.setDestinationAttribute(expectedParentAttribute);
		this.testInstance.setObjectType(this.expectedObjectType);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.testFixture = null;
		this.notationSubsystemFixture = null;
		this.testInstance = null;
		this.expectedParentAttribute = null;
		this.expectedObjectType = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#ShapeAttributeFactory(IndexCounter)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testShapeAttributeFactory() {
		new ShapeAttributeFactory(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCanCreateAttribute() {
		assertTrue("can create att", this.testInstance.canCreateAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#createAttribute()}.
	 */
	@Test
	public void testCreateAttribute() {
		final IModel rootAtt = this.testFixture.getModel();
		this.mockery.checking(new Expectations(){{
			one(rootAtt).addCanvasAttribute(with(any(IShapeAttribute.class)));
		}});
		IShapeAttribute newAtt = this.testInstance.createAttribute();
		assertNotNull("new instance not null", newAtt);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetParentAttribute() {
		assertEquals("expected parent", this.expectedParentAttribute, this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#getObjectType()}.
	 */
	@Test
	public void testGetObjectType() {
		assertEquals("expected object type", this.expectedObjectType, this.testInstance.getObjectType());
	}

}
