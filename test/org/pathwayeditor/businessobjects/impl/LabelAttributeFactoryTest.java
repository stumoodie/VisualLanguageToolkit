/*
Copyright 2009-2011, Court of the University of Edinburgh
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
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author Stuart Moodie
 *
 */
@RunWith(JMock.class)
public class LabelAttributeFactoryTest {
	private Mockery mockery;
	private NotationSubsystemFixture notationFixture;
	private CanvasTestFixture testFixture;
	private ILabelAttributeFactory testInstance;
	
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
		this.testInstance = new LabelAttributeFactory(new IndexCounter(100));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.testFixture = null;
		this.notationFixture = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#setProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)}.
	 */
	@Test
	public void testSetProperty() {
		IShapeAttribute shapeAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		IAnnotationProperty prop = shapeAtt.getProperty(NotationSubsystemFixture.SHAPE_TYPE_A_PROP_NAME);
		this.testInstance.setProperty(prop);
		assertEquals("expected value", prop, this.testInstance.getProperty());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCanCreateAttribute() {
		assertFalse("can create", this.testInstance.canCreateAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)}.
	 */
	@Test
	public void testSetDestinationAttribute() {
		IShapeAttribute shapeAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		this.testInstance.setDestinationAttribute(shapeAtt);
		assertEquals("expected value", shapeAtt, this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetDestinationAttribute() {
		assertNull("not set", this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LabelAttributeFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetProperty() {
		assertNull("not set", this.testInstance.getProperty());
	}

}
