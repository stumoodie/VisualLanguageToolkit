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
import static org.junit.Assert.fail;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class ShapeNodeTest {
	private Mockery mockery;
	private ICompoundNode mockCompoundNode;
	private IShapeNode testInstance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.mockCompoundNode = this.mockery.mock(ICompoundNode.class, "mockCompoundNode");
		this.testInstance = new ShapeNode(this.mockCompoundNode);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
		this.mockery = null;
		this.mockCompoundNode = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeNode#ShapeNode(uk.ac.ed.inf.graph.compound.ICompoundNode)}.
	 */
	@Test(expected=NullPointerException.class)
	public void testShapeNode() {
		new ShapeNode(null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeNode#getParentNode()}.
	 */
	@Test
	public void testGetParentNode() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeNode#sourceLinkIterator()}.
	 */
	@Test
	public void testSourceLinkIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeNode#targetLinkIterator()}.
	 */
	@Test
	public void testTargetLinkIterator() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeNode#getAttribute()}.
	 */
	@Test
	public void testGetAttribute() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeNode#getNumSourceLinks()}.
	 */
	@Test
	public void testGetNumSourceLinks() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.ShapeNode#getNumTargetLinks()}.
	 */
	@Test
	public void testGetNumTargetLinks() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.TypedCommonNode#TypedCommonNode(uk.ac.ed.inf.graph.compound.ICompoundNode)}.
	 */
	@Test
	public void testTypedCommonNode() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.TypedCommonNode#canParent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)}.
	 */
	@Test
	public void testCanParentIDrawingNode() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.TypedCommonNode#canParent(org.pathwayeditor.businessobjects.typedefn.INodeObjectType)}.
	 */
	@Test
	public void testCanParentINodeObjectType() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.TypedCommonNode#isValidChildOf(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)}.
	 */
	@Test
	public void testIsValidChildOfIDrawingNode() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.TypedCommonNode#isValidChildOf(org.pathwayeditor.businessobjects.typedefn.INodeObjectType)}.
	 */
	@Test
	public void testIsValidChildOfINodeObjectType() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#getModel()}.
	 */
	@Test
	public void testGetModel() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#isRemoved()}.
	 */
	@Test
	public void testIsRemoved() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#markRemoved(boolean)}.
	 */
	@Test
	public void testMarkRemoved() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#getUniqueIndex()}.
	 */
	@Test
	public void testGetUniqueIndex() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#getLevel()}.
	 */
	@Test
	public void testGetLevel() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#getIndex()}.
	 */
	@Test
	public void testGetIndex() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#getSubModel()}.
	 */
	@Test
	public void testGetSubModel() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#isDescendent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)}.
	 */
	@Test
	public void testIsDescendent() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.CommonNode#getCompoundGraphElement()}.
	 */
	@Test
	public void testGetCompoundGraphElement() {
		assertEquals("expected node", this.mockCompoundNode, this.testInstance.getCompoundGraphElement());
	}

}