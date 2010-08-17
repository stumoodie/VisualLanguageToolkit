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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

import uk.ac.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class ModelTest {
	private IModel testInstance;
	private Mockery mockery;
	private ICanvas mockCanvas;
	private IRootObjectType mockRootObjectType;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		
		mockCanvas = this.mockery.mock(ICanvas.class, "mockCanvas");
		mockRootObjectType = this.mockery.mock(IRootObjectType.class, "rootObjectType");
		final IndexCounter serialCounter = new IndexCounter();
		this.mockery.checking(new Expectations(){{
			allowing(mockCanvas).getCreationSerialCounter(); will(returnValue(serialCounter));
		}});
		this.testInstance = new Model(mockCanvas, mockRootObjectType);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#Model(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas, org.pathwayeditor.businessobjects.typedefn.IRootObjectType)}.
	 */
	@Test(expected=NullPointerException.class)
	public void testModel() {
		new Model(null, null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#addModelChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener)}.
	 */
	@Test
	public void testAddModelChangeListener() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#removeModelChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListener)}.
	 */
	@Test
	public void testRemoveModelChangeListener() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#modelChangeListenerIterator()}.
	 */
	@Test
	public void testModelChangeListenerIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#setListenersEnabled(boolean)}.
	 */
	@Test
	public void testSetListenersEnabled() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#areListenersEnabled()}.
	 */
	@Test
	public void testAreListenersEnabled() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#getCanvas()}.
	 */
	@Test
	public void testGetCanvas() {
		assertEquals("expected canvas", this.mockCanvas, this.testInstance.getCanvas());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#getRootNode()}.
	 */
	@Test
	public void testGetRootNode() {
		assertNotNull("root created", this.testInstance.getRootNode());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#createCopy(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)}.
	 */
	@Test
	public void testCreateCopy() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#linkEdgeFactory()}.
	 */
	@Test
	public void testLinkEdgeFactory() {
		assertNotNull("expected", this.testInstance.linkEdgeFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#getCurrentState()}.
	 */
	@Test
	public void testGetCurrentState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#restoreToState(org.pathwayeditor.businessobjects.drawingprimitives.IGraphMomento)}.
	 */
	@Test
	public void testRestoreToState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#newSelectionFactory()}.
	 */
	@Test
	public void testNewSelectionFactory() {
		assertNotNull("expected", this.testInstance.newSelectionFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#canRemoveSelection(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)}.
	 */
	@Test
	public void testCanRemoveSelection() {
		assertFalse("expected", this.testInstance.canRemoveSelection(null));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#removeSubgraph(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection)}.
	 */
	@Test
	public void testRemoveSubgraph() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#drawingNodeIterator()}.
	 */
	@Test
	public void testDrawingNodeIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#shapeNodeIterator()}.
	 */
	@Test
	public void testShapeNodeIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#labelNodeIterator()}.
	 */
	@Test
	public void testLabelNodeIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#linkEdgeIterator()}.
	 */
	@Test
	public void testLinkEdgeIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#isValid()}.
	 */
	@Test
	public void testIsValid() {
		assertTrue("valid", this.testInstance.isValid());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#numDrawingElements()}.
	 */
	@Test
	public void testNumDrawingElements() {
		assertEquals("expected num elements", this.testInstance.numDrawingElements());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#numDrawingNodes()}.
	 */
	@Test
	public void testNumDrawingNodes() {
		assertEquals("expected num elements", this.testInstance.numDrawingNodes());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#numShapeNodes()}.
	 */
	@Test
	public void testNumShapeNodes() {
		assertEquals("expected num elements", this.testInstance.numShapeNodes());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#numLabelNodes()}.
	 */
	@Test
	public void testNumLabelNodes() {
		assertEquals("expected num elements", this.testInstance.numLabelNodes());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#numLinkEdges()}.
	 */
	@Test
	public void testNumLinkEdges() {
		assertEquals("expected num elements", this.testInstance.numLinkEdges());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#getCompoundGraph()}.
	 */
	@Test
	public void testGetCompoundGraph() {
		assertNotNull("compound graph created", this.testInstance.getCompoundGraph());
	}

}
