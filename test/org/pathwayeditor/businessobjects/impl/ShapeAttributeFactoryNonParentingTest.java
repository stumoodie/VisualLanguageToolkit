/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class ShapeAttributeFactoryNonParentingTest {
	private Mockery mockery;
	private CanvasTestFixture testFactory;
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
		this.testFactory = new CanvasTestFixture(mockery, "", notationSubsystemFixture.getNotationSubsystem());
		this.testFactory.buildFixture();
		this.expectedParentAttribute = this.testFactory.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		this.testInstance = new ShapeAttributeFactory(new IndexCounter());
		this.testInstance.setDestinationAttribute(expectedParentAttribute);
		this.expectedObjectType = notationSubsystemFixture.getNotationSubsystem().getSyntaxService().getShapeObjectType(NotationSubsystemFixture.SHAPE_TYPE_B_ID);
		this.testInstance.setObjectType(this.expectedObjectType);
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
