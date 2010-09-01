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
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

import uk.ac.ed.inf.graph.compound.IElementAttribute;
import uk.ac.ed.inf.graph.compound.IElementAttributeCopyFactory;
import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class CanvasElementAttributeCopyFactoryCanCopyTest {
	private Mockery mockery;
	private IElementAttributeCopyFactory testInstance;
	private CanvasTestFixture testFixture;
	private IShapeAttribute copyAttribute;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testInstance = new CanvasElementAttributeCopyFactory(new IndexCounter());
		this.mockery = new JUnit4Mockery();
		NotationSubsystemFixture notationFixture = new NotationSubsystemFixture(mockery);
		notationFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", notationFixture.getNotationSubsystem());
		this.testFixture.buildFixture();
		copyAttribute = testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		this.mockery.checking(new Expectations(){{
			exactly(1).of(copyAttribute).visit(with(any(ICanvasElementAttributeVisitor.class))); will(CanvasTestFixture.visitShapeAttribute());
		}});
		this.testInstance.setElementToCopy(copyAttribute);
		this.testInstance.setDestinationAttribute(testFixture.getRootAttribute());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
		this.mockery = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasElementAttributeCopyFactory#CanvasElementAttributeCopyFactory(uk.ac.ed.inf.graph.util.IndexCounter)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCanvasElementAttributeCopyFactory() {
		new CanvasElementAttributeCopyFactory(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasElementAttributeCopyFactory#canCreateAttribute()}.
	 */
	@Test
	public void testCanCreateAttribute() {
		assertTrue("can create", this.testInstance.canCreateAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasElementAttributeCopyFactory#getDestinationAttribute()}.
	 */
	@Test
	public void testGetDestinationAttribute() {
		assertEquals("expected", this.testFixture.getRootAttribute(), this.testInstance.getDestinationAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasElementAttributeCopyFactory#createAttribute()}.
	 */
	@Test
	public void testCreateAttribute() {
		IElementAttribute newAtt = this.testInstance.createAttribute();
		assertNotNull("new att exists", newAtt);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CanvasElementAttributeCopyFactory#getElementToCopy()}.
	 */
	@Test
	public void testGetElementToCopy() {
		assertEquals("expected", this.copyAttribute, this.testInstance.getElementToCopy());
	}

}
