/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * @author Stuart Moodie
 *
 */
@RunWith(JMock.class)
public class ShapeAttributeMoveFactoryDifferentRootTest {
	private Mockery mockery;
	private NotationSubsystemFixture notationFixture;
	private CanvasTestFixture testFixture;
	private IElementAttributeFactory testInstance;
	private IShapeAttribute attributeToMove;
	private CanvasTestFixture otherFixture;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationFixture = new NotationSubsystemFixture(mockery);
		this.notationFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", notationFixture.getNotationSubsystem());
		this.testFixture.buildFixture();
		this.otherFixture = new CanvasTestFixture(mockery, "other", notationFixture.getNotationSubsystem());
		this.otherFixture.buildFixture();
		this.attributeToMove = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		this.testInstance = new TypedAttributeMoveFactory(attributeToMove);
//		this.testInstance.setElementToMove(attributeToMove);
		this.testInstance.setDestinationAttribute(this.otherFixture.getModel().getRootAttribute());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.notationFixture = null;
		this.testFixture = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.TypedAttributeMoveFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCanCreateAttribute() {
		assertTrue("cannot create", this.testInstance.canCreateAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.TypedAttributeMoveFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetDestinationAttribute() {
		assertEquals("expected", this.otherFixture.getModel().getRootAttribute(), this.testInstance.getDestinationAttribute());
	}

}
