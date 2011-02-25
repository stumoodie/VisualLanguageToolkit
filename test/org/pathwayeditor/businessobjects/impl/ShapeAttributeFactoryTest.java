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

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttributeFactory;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author Stuart Moodie
 *
 */
@RunWith(JMock.class)
public class ShapeAttributeFactoryTest {
	private Mockery mockery;
	private CanvasTestFixture testFactory;
	private NotationSubsystemFixture notationSubsystemFixture;
	private IShapeAttributeFactory testInstance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationSubsystemFixture = new NotationSubsystemFixture(mockery); 
		this.notationSubsystemFixture.buildFixture();
		this.testFactory = new CanvasTestFixture(mockery, "", notationSubsystemFixture.getNotationSubsystem());
		this.testFactory.buildFixture();
		this.testInstance = new ShapeAttributeFactory(new IndexCounter());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.testFactory = null;
		this.notationSubsystemFixture = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#ShapeAttributeFactory(IndexCounter)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testShapeAttributeFactory() {
		new ShapeAttributeFactory(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#setObjectType(org.pathwayeditor.businessobjects.typedefn.IShapeObjectType)}.
	 */
	@Test
	public void testSetObjectType() {
		INotationSyntaxService syntaxService = this.notationSubsystemFixture.getNotationSubsystem().getSyntaxService();
		IShapeObjectType expectedType = syntaxService.getShapeObjectType(CanvasTestFixture.SHAPE1_ATT_OT);
		this.testInstance.setObjectType(expectedType);
		assertEquals("expected ot", expectedType, this.testInstance.getObjectType());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCanCreateAttribute() {
		assertFalse("cannot create att", this.testInstance.canCreateAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#createAttribute()}.
	 */
	@Test(expected=IllegalStateException.class)
	public void testCreateAttribute() {
		this.testInstance.createAttribute();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetDestinationAttribute() {
		assertNull("nothing set", this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeAttributeFactory#getObjectType()}.
	 */
	@Test
	public void testGetObjectType() {
		assertNull("no object type", this.testInstance.getObjectType());
	}

}
