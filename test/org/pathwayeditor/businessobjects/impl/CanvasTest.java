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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.GeneralIteratorTestUtility;
import org.pathwayeditor.testfixture.IObjectConstructor;
import org.pathwayeditor.testfixture.IteratorTestUtility;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class CanvasTest {
	private static final String EXPECTED_REPO_NAME = "expected repo name...";
	private static final int EXPECTED_INODE = 0;
	private static final String EXPECTED_CANVAS_NAME = "EXPECTED CANVAS NAME";
	private static final int EXPECTED_SERIAL_COUNTER_IDX = 1;
	private static final int MISSING_ATT_IDX = -1;
	private static final String INVALID_CANVAS_NAME1 = " ajhd";
	private static final String INVALID_CANVAS_NAME2 = "ajhd ";
	private static final String INVALID_CANVAS_NAME3 = "&ajhd";
	private static final String VALID_CANVAS_NAME = "12weabdsvAHSGH 3 AJHD  AJJ_16472";
	private static final int EXPECTED_NUM_ATTS = 4;
	private static final int EXPECTED_NUM_LISTENERS = 1;
	private ICanvas testInstance;
	private CanvasTestFixture testFixture;
	private Mockery mockery;
	private NotationSubsystemFixture notationTestFixture;
	private ICanvasPropertyChangeListener testListener;
	
	public CanvasTest(){
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationTestFixture = new NotationSubsystemFixture(mockery);
		this.notationTestFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", this.notationTestFixture.getNotationSubsystem());
		this.testFixture.redefineBuilder(CanvasTestFixture.CANVAS_ID, new IObjectConstructor<ICanvas>(){

			@Override
			public ICanvas create() {
				testInstance = new Canvas(EXPECTED_REPO_NAME, EXPECTED_INODE, notationTestFixture.getNotationSubsystem(), EXPECTED_CANVAS_NAME);
				return testInstance;
			}

			@Override
			public boolean build() {
				IShapeAttribute att1 = testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
				testInstance.addCanvasAttribute(att1);
				ILabelAttribute lab1 = testFixture.getObject(CanvasTestFixture.LABEL9_ATT_ID);
				testInstance.addCanvasAttribute(lab1);
				ILinkAttribute link1 = testFixture.getObject(CanvasTestFixture.LINK1_ATT_ID);
				testInstance.addCanvasAttribute(link1);
				return true;
			}
			
		});
		this.testFixture.redefineBuilder(CanvasTestFixture.ROOT_ATT_ID, new IObjectConstructor<IRootAttribute>(){
			@Override
			public IRootAttribute create() {
				return testInstance.getRootAttribute();
			}

			@Override
			public boolean build() {
				return true;
			}
		});
		this.testFixture.buildFixture();
		this.testListener = new ICanvasPropertyChangeListener() {

			@Override
			public void propertyChange(ICanvasPropertyChangeEvent e) {
			}
			
		};
		this.testInstance.addChangeListener(this.testListener);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.testFixture = null;
		this.notationTestFixture = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#Canvas(java.lang.String, int, org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem, java.lang.String)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCanvasNull() {
		new Canvas(null, EXPECTED_INODE, null, null);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getNotationSubsystem()}.
	 */
	@Test
	public void testGetNotationSubsystem() {
		assertEquals("expected value", this.notationTestFixture.getNotationSubsystem(), this.testInstance.getNotationSubsystem());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getGridSize()}.
	 */
	@Test
	public void testGetGridSize() {
		assertEquals("expected value", Canvas.DEFAULT_GRID_SIZE, this.testInstance.getGridSize());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#setGridSize(org.pathwayeditor.figure.geometry.Dimension)}.
	 */
	@Test
	public void testSetGridSize() {
		Dimension newGridSize = Canvas.DEFAULT_GRID_SIZE.expand(5.0, 6.0);
		this.testInstance.setGridSize(newGridSize);
		assertEquals("expected value", newGridSize, this.testInstance.getGridSize());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#isGridEnabled()}.
	 */
	@Test
	public void testIsGridEnabled() {
		assertFalse("grid not enabled", this.testInstance.isGridEnabled());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#setGridEnabled(boolean)}.
	 */
	@Test
	public void testSetGridEnabled() {
		this.testInstance.setGridEnabled(true);
		assertTrue("grid enabled", this.testInstance.isGridEnabled());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getBackgroundColour()}.
	 */
	@Test
	public void testGetBackgroundColour() {
		assertEquals("expected value", Canvas.DEFAULT_BACKGROUND_COLOUR, this.testInstance.getBackgroundColour());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#isSnapToGridOn()}.
	 */
	@Test
	public void testIsSnapToGridOn() {
		assertFalse("no snap", this.testInstance.isSnapToGridOn());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#setBackgroundColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)}.
	 */
	@Test
	public void testSetBackgroundColour() {
		this.testInstance.setBackgroundColour(RGB.GREEN);
		assertEquals("expected value", RGB.GREEN, this.testInstance.getBackgroundColour());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#setSnapToGrid(boolean)}.
	 */
	@Test
	public void testSetSnapToGrid() {
		this.testInstance.setSnapToGrid(true);
		assertTrue("snap", this.testInstance.isSnapToGridOn());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getCanvasSize()}.
	 */
	@Test
	public void testGetCanvasSize() {
		assertEquals("expected value", Canvas.DEFAULT_CANVAS_SIZE, this.testInstance.getCanvasSize());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#setCanvasSize(org.pathwayeditor.figure.geometry.Dimension)}.
	 */
	@Test
	public void testSetCanvasSize() {
		Dimension expectedValue = Canvas.DEFAULT_CANVAS_SIZE.expand(10.0, 9.0);
		this.testInstance.setCanvasSize(expectedValue);
		assertEquals("expected value", expectedValue, this.testInstance.getCanvasSize());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getRepositoryName()}.
	 */
	@Test
	public void testGetRepositoryName() {
		assertEquals("expected value", EXPECTED_REPO_NAME, this.testInstance.getRepositoryName());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getCreationSerialCounter()}.
	 */
	@Test
	public void testGetCreationSerialCounter() {
		assertEquals("expected value", EXPECTED_SERIAL_COUNTER_IDX, this.testInstance.getCreationSerialCounter().getLastIndex());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#copyHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCopyHere() {
		this.testInstance.copyHere(testInstance);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getModel()}.
	 */
	@Test
	public void testGetModel() {
		assertNotNull("model", this.testInstance.getModel());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener)}.
	 */
	@Test
	public void testAddChangeListener() {
		this.testInstance.addChangeListener(new ICanvasPropertyChangeListener() {
			
			@Override
			public void propertyChange(ICanvasPropertyChangeEvent e) {
				
			}
		});
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS+1, this.testInstance.numChangeListeners());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#listenerIterator()}.
	 */
	@Test
	public void testListenerIterator() {
		GeneralIteratorTestUtility<ICanvasPropertyChangeListener> testIter = new GeneralIteratorTestUtility<ICanvasPropertyChangeListener>(this.testListener);
		testIter.testIterator(this.testInstance.listenerIterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener)}.
	 */
	@Test
	public void testRemoveChangeListener() {
		this.testInstance.removeChangeListener(testListener);
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS-1, this.testInstance.numChangeListeners());
		GeneralIteratorTestUtility<ICanvasPropertyChangeListener> testIter = new GeneralIteratorTestUtility<ICanvasPropertyChangeListener>();
		testIter.testIterator(this.testInstance.listenerIterator());
	}
	
	@Test
	public void testNumChangeListeners(){
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS, this.testInstance.numChangeListeners());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#containsLabelAttribute(int)}.
	 */
	@Test
	public void testContainsLabelAttribute() {
		assertTrue("has att", this.testInstance.containsLabelAttribute(CanvasTestFixture.LABEL9_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLabelAttribute(MISSING_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLabelAttribute(CanvasTestFixture.ROOT_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLabelAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#containsLinkAttribute(int)}.
	 */
	@Test
	public void testContainsLinkAttribute() {
		assertTrue("has att", this.testInstance.containsLinkAttribute(CanvasTestFixture.LINK1_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLinkAttribute(MISSING_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLinkAttribute(CanvasTestFixture.ROOT_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLinkAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#containsShapeAttribute(int)}.
	 */
	@Test
	public void testContainsShapeAttribute() {
		assertTrue("has att", this.testInstance.containsShapeAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
		assertFalse("no att", this.testInstance.containsShapeAttribute(MISSING_ATT_IDX));
		assertFalse("no att", this.testInstance.containsShapeAttribute(CanvasTestFixture.ROOT_ATT_IDX));
		assertFalse("no att", this.testInstance.containsShapeAttribute(CanvasTestFixture.LINK1_ATT_IDX));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#findAttribute(int)}.
	 */
	@Test
	public void testFindAttribute() {
		ICanvasAttribute expectedAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		assertEquals("expected value", expectedAtt, this.testInstance.findAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
		assertNull("no value found", this.testInstance.findAttribute(MISSING_ATT_IDX));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getLabelAttribute(int)}.
	 */
	@Test
	public void testGetLabelAttribute() {
		ICanvasAttribute expectedAtt = this.testFixture.getObject(CanvasTestFixture.LABEL9_ATT_ID);
		assertEquals("expected value", expectedAtt, this.testInstance.getLabelAttribute(CanvasTestFixture.LABEL9_ATT_IDX));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getLinkAttribute(int)}.
	 */
	@Test
	public void testGetLinkAttribute() {
		ICanvasAttribute expectedAtt = this.testFixture.getObject(CanvasTestFixture.LINK1_ATT_ID);
		assertEquals("expected value", expectedAtt, this.testInstance.getLinkAttribute(CanvasTestFixture.LINK1_ATT_IDX));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getShapeAttribute(int)}.
	 */
	@Test
	public void testGetShapeAttribute() {
		ICanvasAttribute expectedAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		assertEquals("expected value", expectedAtt, this.testInstance.getShapeAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertFalse("Not empty", this.testInstance.isEmpty());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#canCopyHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)}.
	 */
	@Test
	public void testCanCopyHere() {
		assertFalse("can't copy null", this.testInstance.canCopyHere(null));
		assertFalse("can't copy to self", this.testInstance.canCopyHere(this.testInstance));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getINode()}.
	 */
	@Test
	public void testGetINode() {
		assertEquals("expected inode", EXPECTED_INODE, this.testInstance.getINode());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("expected name", EXPECTED_CANVAS_NAME, this.testInstance.getName());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#isValidName(java.lang.String)}.
	 */
	@Test
	public void testIsValidName() {
		assertFalse("is invalid when null", this.testInstance.isValidName(null));
		assertFalse("is invalid when empty string", this.testInstance.isValidName(""));
		assertTrue("is valid name", this.testInstance.isValidName(VALID_CANVAS_NAME));
		assertFalse("is invalid name", this.testInstance.isValidName(INVALID_CANVAS_NAME1));
		assertFalse("is invalid name", this.testInstance.isValidName(INVALID_CANVAS_NAME2));
		assertFalse("is invalid name", this.testInstance.isValidName(INVALID_CANVAS_NAME3));
		assertTrue("is valid name", this.testInstance.isValidName(EXPECTED_CANVAS_NAME));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#checkValidName(java.lang.String)}.
	 */
	@Test
	public void testCheckValidName() {
		assertFalse("is invalid when null", Canvas.checkValidName(null));
		assertFalse("is invalid when empty string", Canvas.checkValidName(""));
		assertTrue("is valid name", Canvas.checkValidName(VALID_CANVAS_NAME));
		assertFalse("is invalid name", Canvas.checkValidName(INVALID_CANVAS_NAME1));
		assertFalse("is invalid name", Canvas.checkValidName(INVALID_CANVAS_NAME2));
		assertFalse("is invalid name", Canvas.checkValidName(INVALID_CANVAS_NAME3));
		assertTrue("is valid name", Canvas.checkValidName(EXPECTED_CANVAS_NAME));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		this.testInstance.setName(VALID_CANVAS_NAME);
		assertEquals("expected name", VALID_CANVAS_NAME, this.testInstance.getName());
		assertFalse("expecte to have changed", EXPECTED_CANVAS_NAME.equals(this.testInstance.getName()));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#areListenersEnabled()}.
	 */
	@Test
	public void testAreListenersEnabled() {
		assertTrue("listeners enabled", this.testInstance.areListenersEnabled());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#setListenersEnabled(boolean)}.
	 */
	@Test
	public void testSetListenersEnabled() {
		this.testInstance.setListenersEnabled(false);
		assertFalse("listeners not enabled", this.testInstance.areListenersEnabled());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#numCanvasAttributes()}.
	 */
	@Test
	public void testNumCanvasAttributes() {
		assertEquals("expected num attributes", EXPECTED_NUM_ATTS, this.testInstance.numCanvasAttributes());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#canvasAttributeIterator()}.
	 */
	@Test
	public void testCanvasAttributeIterator() {
		IteratorTestUtility<ICanvasAttribute> iterTest = new IteratorTestUtility<ICanvasAttribute>(this.testFixture.getObjectArray(new ICanvasAttribute[0], CanvasTestFixture.ROOT_ATT_ID,
				CanvasTestFixture.SHAPE1_ATT_ID, CanvasTestFixture.LABEL9_ATT_ID, CanvasTestFixture.LINK1_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.canvasAttributeIterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#linkAttributeIterator()}.
	 */
	@Test
	public void testLinkAttributeIterator() {
		IteratorTestUtility<ILinkAttribute> iterTest = new IteratorTestUtility<ILinkAttribute>(this.testFixture.getObjectArray(new ILinkAttribute[0], CanvasTestFixture.LINK1_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.linkAttributeIterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#linkTerminusIterator()}.
	 */
	@Test
	public void testLinkTerminusIterator() {
		IteratorTestUtility<ILinkTerminus> iterTest = new IteratorTestUtility<ILinkTerminus>(this.testFixture.getObjectArray(new ILinkTerminus[0]));
		iterTest.testSortedIterator(this.testInstance.linkTerminusIterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#shapeAttributeIterator()}.
	 */
	@Test
	public void testShapeAttributeIterator() {
		IteratorTestUtility<IShapeAttribute> iterTest = new IteratorTestUtility<IShapeAttribute>(this.testFixture.getObjectArray(new IShapeAttribute[0], CanvasTestFixture.SHAPE1_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.shapeAttributeIterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#labelAttributeIterator()}.
	 */
	@Test
	public void testLabelAttributeIterator() {
		IteratorTestUtility<ILabelAttribute> iterTest = new IteratorTestUtility<ILabelAttribute>(this.testFixture.getObjectArray(new ILabelAttribute[0], CanvasTestFixture.LABEL9_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.labelAttributeIterator());
	}

	@Test(expected=ClassCastException.class)
	public void testGetLabelAttributeWrongType() {
		this.testInstance.getLabelAttribute(CanvasTestFixture.SHAPE1_ATT_IDX);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetLabelAttributeNoAtt() {
		this.testInstance.getLabelAttribute(MISSING_ATT_IDX);
	}

	@Test(expected=ClassCastException.class)
	public void testGetLinkAttributeWrongType() {
		this.testInstance.getLinkAttribute(CanvasTestFixture.SHAPE1_ATT_IDX);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetLinkAttributeNoAtt() {
		this.testInstance.getLinkAttribute(MISSING_ATT_IDX);
	}

	@Test(expected=ClassCastException.class)
	public void testGetShapeAttributeWrongType() {
		this.testInstance.getShapeAttribute(CanvasTestFixture.LINK1_ATT_IDX);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetShapeAttributeNoAtt() {
		this.testInstance.getShapeAttribute(MISSING_ATT_IDX);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#compareTo(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)}.
	 */
	@Test
	public void testCompareTo() {
		assertEquals("compare to self", 0, this.testInstance.compareTo(testInstance));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#getMapper()}.
	 */
	@Test
	public void testGetMapper() {
		assertNotNull("Mapper", this.testInstance.getMapper());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Canvas#addCanvasAttribute(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute)}.
	 */
	@Test
	public void testAddCanvasAttribute() {
		final ICanvasAttribute newCanvasAttribute = this.mockery.mock(ICanvasAttribute.class, "newCanvasAttribute");
		final int newIdx = EXPECTED_NUM_ATTS+1;
		this.mockery.checking(new Expectations(){{
			allowing(newCanvasAttribute).getCreationSerial(); will(returnValue(newIdx));
			allowing(newCanvasAttribute).getCanvas(); will(returnValue(testInstance));
			allowing(newCanvasAttribute).compareTo(with(not(equalTo(newCanvasAttribute)))); will(returnValue(1));
			allowing(newCanvasAttribute).compareTo(with(equalTo(newCanvasAttribute))); will(returnValue(0));
			allowing(newCanvasAttribute).isRemoved(); will(returnValue(false));
		}});
		this.testInstance.addCanvasAttribute(newCanvasAttribute);
		assertEquals("found att", newCanvasAttribute, this.testInstance.findAttribute(newIdx));
		assertEquals("num atts expected", EXPECTED_NUM_ATTS+1, this.testInstance.numCanvasAttributes());
	}

}
