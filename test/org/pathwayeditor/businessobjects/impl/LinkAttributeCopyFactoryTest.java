/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * @author Stuart Moodie
 *
 */
public class LinkAttributeCopyFactoryTest {
	private Mockery mockery;
	private CanvasTestFixture testFixture;
	private NotationSubsystemFixture notationFixture;
	private IElementAttributeFactory testInstance;
	private ILinkAttribute linkToCopy;
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
		this.linkToCopy = this.testFixture.getObject(CanvasTestFixture.LINK1_ATT_ID);
		this.testInstance = new LinkAttributeCopyFactory(linkToCopy);
		this.testInstance.setDestinationAttribute(this.otherFixture.getModel().getRootAttribute());
		IShapeAttribute shape1Att = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		IShapeAttribute shape2Att = this.testFixture.getObject(CanvasTestFixture.SHAPE2_ATT_ID);
		this.testInstance.setOutAttribute(shape1Att);
		this.testInstance.setInAttribute(shape2Att);
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
		this.linkToCopy = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeCopyFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCanCreateAttribute() {
		assertTrue("can create", this.testInstance.canCreateAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeCopyFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)}.
	 */
	@Test
	public void testSetDestinationAttribute() {
		IShapeAttribute shapeAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		this.testInstance.setDestinationAttribute(shapeAtt);
		assertEquals("expected", shapeAtt, this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeCopyFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetDestinationAttribute() {
		assertEquals("expected", this.otherFixture.getModel().getRootAttribute(), this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeCopyFactory#createAttribute()}.
	 */
	@Test
	public void testCreateAttribute() {
		final IModel rootAtt = this.otherFixture.getModel();
		this.mockery.checking(new Expectations(){{
			one(rootAtt).addCanvasAttribute(with(any(ILabelAttribute.class)));
		}});
		ILinkAttribute newAtt = (ILinkAttribute)this.testInstance.createAttribute();
		assertNotNull("exists", newAtt);
		assertEquals("expected too att", this.otherFixture.getModel(), newAtt.getModel());
	}

}
