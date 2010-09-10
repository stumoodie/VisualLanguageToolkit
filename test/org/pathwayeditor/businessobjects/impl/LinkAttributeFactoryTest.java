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
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttributeFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class LinkAttributeFactoryTest {
	private Mockery mockery;
	private ILinkAttributeFactory testInstance;
	private CanvasTestFixture canvasTestFixture;
	private IShapeAttribute shapeAttributeSrc;
	private IShapeAttribute shapeAttributeTgt;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		NotationSubsystemFixture notationFixture = new NotationSubsystemFixture(mockery);
		notationFixture.buildFixture();
		canvasTestFixture = new CanvasTestFixture(mockery, "", notationFixture.getNotationSubsystem());
		canvasTestFixture.buildFixture();
		this.testInstance = new LinkAttributeFactory(new IndexCounter());
		shapeAttributeSrc = canvasTestFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		shapeAttributeTgt = canvasTestFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		this.testInstance.setOutAttribute(shapeAttributeSrc);
		this.testInstance.setInAttribute(shapeAttributeTgt);
		this.testInstance.setDestinationAttribute(canvasTestFixture.getRootAttribute());
		this.testInstance.setObjectType(notationFixture.getNotationSubsystem().getSyntaxService().getLinkObjectType(NotationSubsystemFixture.LINK_TYPE_D_ID));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
		this.canvasTestFixture = null;
		this.mockery = null;
		this.shapeAttributeSrc = null;
		this.shapeAttributeTgt = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCanCreateAttribute() {
		assertTrue("can create", this.testInstance.canCreateAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeFactory#setDestinationAttribute(uk.ac.ed.inf.graph.compound.IElementAttribute)}.
	 */
	@Test
	public void testSetDestinationAttribute() {
		this.testInstance.setDestinationAttribute(shapeAttributeSrc);
		assertEquals("expected", shapeAttributeSrc, this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetDestinationAttribute() {
		assertEquals("expected", this.canvasTestFixture.getRootAttribute(), this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeFactory#getOutAttribute()}.
	 */
	@Test
	public void testGetOutAttribute() {
		assertEquals("expected", this.shapeAttributeSrc, this.testInstance.getOutAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeFactory#getInAttribute()}.
	 */
	@Test
	public void testGetInAttribute() {
		assertEquals("expected", this.shapeAttributeTgt, this.testInstance.getInAttribute());
	}

	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.LinkAttributeFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCreateAttribute() {
		final IRootAttribute rootAtt = this.canvasTestFixture.getRootAttribute();
		this.mockery.checking(new Expectations(){{
			allowing(rootAtt).addCanvasAttribute(with(any(ILinkAttribute.class)));
		}});
		ILinkAttribute actualAtt = this.testInstance.createAttribute();
		assertNotNull("created", actualAtt);
	}

	
}
