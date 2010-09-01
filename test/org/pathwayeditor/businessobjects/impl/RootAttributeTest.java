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
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeResizedEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeTranslationEvent;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
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
public class RootAttributeTest {
	private static final String INVALID_NAME1 = " ajhd";
	private static final String INVALID_NAME2 = "ajhd ";
	private static final String INVALID_NAME3 = "&ajhd";
	private static final String VALID_CANVAS_NAME = "12weabdsvAHSGH 3 AJHD  AJJ_16472";
	private static final String EXPECTED_NAME = "TEST CANVAS";
	private static final Envelope EXPECTED_BOUNDS = new Envelope(RootAttribute.INITIAL_POS, RootAttribute.INITIAL_SIZE);
	private static final int EXPECTED_NUM_ATTS = 3;
	private static final int MISSING_ATT_IDX = -1;
	private static final int EXPECTED_NUM_LISTENERS = 1;
	private IRootAttribute testInstance;
	private NotationSubsystemFixture notationFixture;
	private Mockery mockery;
	private ICanvasAttributePropertyChangeListener testListener;
	private CanvasTestFixture testFixture;
	private IDrawingNodeAttributeListener testDrawingNodeListener;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationFixture = new NotationSubsystemFixture(mockery);
		this.notationFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", this.notationFixture.getNotationSubsystem());
		this.testFixture.redefineBuilder(CanvasTestFixture.ROOT_ATT_ID, new IObjectConstructor<IRootAttribute>(){

			@Override
			public IRootAttribute create() {
				testInstance = new RootAttribute(EXPECTED_NAME, notationFixture.getNotationSubsystem().getSyntaxService().getRootObjectType());
				return testInstance;
			}

			@Override
			public boolean build() {
				IShapeAttribute shape1Att = testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
				testInstance.addCanvasAttribute(shape1Att);
				ILabelAttribute label9Att = testFixture.getObject(CanvasTestFixture.LABEL9_ATT_ID);
				testInstance.addCanvasAttribute(label9Att);
				ILinkAttribute link1Att = testFixture.getObject(CanvasTestFixture.LINK1_ATT_ID);
				testInstance.addCanvasAttribute(link1Att);
				return true;
			}
			
		});
		this.testFixture.buildFixture();
		this.testListener = new ICanvasAttributePropertyChangeListener() {

			@Override
			public void propertyChange(ICanvasAttributePropertyChangeEvent e) {
			}
			
		};
		this.testInstance.addChangeListener(this.testListener);
		this.testDrawingNodeListener = new IDrawingNodeAttributeListener() {

			@Override
			public void nodeTranslated(IDrawingNodeAttributeTranslationEvent e) {
				
			}

			@Override
			public void nodeResized(IDrawingNodeAttributeResizedEvent e) {
				
			}
		};
		this.testInstance.addDrawingNodeAttributeListener(testDrawingNodeListener);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.mockery = null;
		this.testInstance = null;
		this.testDrawingNodeListener = null;
		this.testListener = null;
		this.testFixture = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("expected name", EXPECTED_NAME, this.testInstance.getName());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getObjectType()}.
	 */
	@Test
	public void testGetObjectType() {
		assertEquals("expected ot", this.notationFixture.getNotationSubsystem().getSyntaxService().getRootObjectType(), this.testInstance.getObjectType());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getBounds()}.
	 */
	@Test
	public void testGetBounds() {
		assertEquals("expected bounds", EXPECTED_BOUNDS, this.testInstance.getBounds());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getBackgroundColour()}.
	 */
	@Test
	public void testGetBackgroundColour() {
		assertEquals("expected bg col", RootAttribute.DEFAULT_BACKGROUND_COLOUR, this.testInstance.getBackgroundColour());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#setBackgroundColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)}.
	 */
	@Test
	public void testSetBackgroundColour() {
		RGB newCol = RGB.GREEN;
		assertFalse("not same as default", RootAttribute.DEFAULT_BACKGROUND_COLOUR.equals(newCol));
		this.testInstance.setBackgroundColour(newCol);
		assertEquals("expected bg col", newCol, this.testInstance.getBackgroundColour());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#setBounds(org.pathwayeditor.figure.geometry.Envelope)}.
	 */
	@Test
	public void testSetBounds() {
		Envelope newBounds = new Envelope(-101, -99, 103.9983, 0.999);
		assertFalse("not default", EXPECTED_BOUNDS.equals(newBounds));
		this.testInstance.setBounds(newBounds);
		assertEquals("bounds set", newBounds, this.testInstance.getBounds());
		
	}

	@Test
	public void testAddChangeListener() {
		this.testInstance.addChangeListener(new ICanvasAttributePropertyChangeListener() {
			
			@Override
			public void propertyChange(ICanvasAttributePropertyChangeEvent e) {
				
			}
		});
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS+1, this.testInstance.getChangeListeners().size());
	}

	@Test
	public void testGetChangeListeners() {
		GeneralIteratorTestUtility<ICanvasAttributePropertyChangeListener> testIter = new GeneralIteratorTestUtility<ICanvasAttributePropertyChangeListener>(this.testListener);
		testIter.testIterator(this.testInstance.getChangeListeners().iterator());
	}

	@Test
	public void testRemoveChangeListener() {
		this.testInstance.removeChangeListener(testListener);
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS-1, this.testInstance.getChangeListeners().size());
		GeneralIteratorTestUtility<ICanvasAttributePropertyChangeListener> testIter = new GeneralIteratorTestUtility<ICanvasAttributePropertyChangeListener>();
		testIter.testIterator(this.testInstance.getChangeListeners().iterator());
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)}.
	 */
	@Test
	public void testResize() {
		this.testInstance.resize(new Point(25, 10), new Dimension(9, 12));
		assertEquals("no resize", EXPECTED_BOUNDS, this.testInstance.getBounds());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#translate(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testTranslate() {
		this.testInstance.translate(new Point(103.0, -38762.0));
		assertEquals("no translation", EXPECTED_BOUNDS, this.testInstance.getBounds());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#isValidName(java.lang.String)}.
	 */
	@Test
	public void testIsValidName() {
		assertFalse("is invalid when null", this.testInstance.isValidName(null));
		assertFalse("is invalid when empty string", this.testInstance.isValidName(""));
		assertTrue("is valid name", this.testInstance.isValidName(VALID_CANVAS_NAME));
		assertFalse("is invalid name", this.testInstance.isValidName(INVALID_NAME1));
		assertFalse("is invalid name", this.testInstance.isValidName(INVALID_NAME2));
		assertFalse("is invalid name", this.testInstance.isValidName(INVALID_NAME3));
		assertTrue("is valid name", this.testInstance.isValidName(EXPECTED_NAME));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#checkValidName(java.lang.String)}.
	 */
	@Test
	public void testCheckValidName() {
		assertFalse("is invalid when null", RootAttribute.checkValidName(null));
		assertFalse("is invalid when empty string", RootAttribute.checkValidName(""));
		assertTrue("is valid name", RootAttribute.checkValidName(VALID_CANVAS_NAME));
		assertFalse("is invalid name", RootAttribute.checkValidName(INVALID_NAME1));
		assertFalse("is invalid name", RootAttribute.checkValidName(INVALID_NAME2));
		assertFalse("is invalid name", RootAttribute.checkValidName(INVALID_NAME3));
		assertTrue("is valid name", RootAttribute.checkValidName(EXPECTED_NAME));
	}


//	/**
//	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getCreationSerialCounter()}.
//	 */
//	@Test
//	public void testGetCreationSerialCounter() {
//		assertEquals("expected counter", this.testCounter, this.testInstance.getCreationSerialCounter());
//	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#addDrawingNodeAttributeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener)}.
	 */
	@Test
	public void testAddDrawingNodeAttributeListener() {
		this.testInstance.addDrawingNodeAttributeListener(new IDrawingNodeAttributeListener() {
			@Override
			public void nodeTranslated(IDrawingNodeAttributeTranslationEvent e) {
			}
			@Override
			public void nodeResized(IDrawingNodeAttributeResizedEvent e) {
			}
		});
		assertEquals("expected num listeners", EXPECTED_NUM_LISTENERS+1, this.testInstance.getDrawingNodeAttributeListeners().size());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getDrawingNodeAttributeListeners()}.
	 */
	@Test
	public void testGetDrawingNodeAttributeListeners() {
		GeneralIteratorTestUtility<IDrawingNodeAttributeListener> testIter = new GeneralIteratorTestUtility<IDrawingNodeAttributeListener>(this.testDrawingNodeListener);
		testIter.testIterator(this.testInstance.getDrawingNodeAttributeListeners().iterator());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#removeDrawingNodeAttributeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener)}.
	 */
	@Test
	public void testRemoveDrawingNodeAttributeListener() {
		this.testInstance.removeDrawingNodeAttributeListener(testDrawingNodeListener);
		assertTrue("listeners removed", this.testInstance.getDrawingNodeAttributeListeners().isEmpty());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getAttributeFactoryFactory()}.
	 */
	@Test
	public void testAttributeFactoryFactory() {
		assertNotNull("factory exists", this.testInstance.getAttributeFactoryFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.RootAttribute#getRootAttribute()}.
	 */
	@Test
	public void testGetRootAttribute() {
		assertEquals("expected root att", this.testInstance, this.testInstance.getRootAttribute());
	}
	
	@Test
	public void testGetCreationSerial(){
		assertEquals("expected creation serial", RootAttribute.ROOT_IDX, this.testInstance.getCreationSerial());
	}

	@Test
	public void testSetName() {
		this.testInstance.setName(VALID_CANVAS_NAME);
		assertEquals("expected name", VALID_CANVAS_NAME, this.testInstance.getName());
		assertFalse("expecte to have changed", EXPECTED_NAME.equals(this.testInstance.getName()));
	}

	@Test
	public void testNumCanvasAttributes() {
		assertEquals("expected num attributes", EXPECTED_NUM_ATTS, this.testInstance.numCanvasAttributes());
	}

	@Test
	public void testCanvasAttributeIterator() {
		IteratorTestUtility<ICanvasElementAttribute> iterTest = new IteratorTestUtility<ICanvasElementAttribute>(this.testFixture.getObjectArray(new ICanvasElementAttribute[0], CanvasTestFixture.SHAPE1_ATT_ID,
				CanvasTestFixture.LABEL9_ATT_ID, CanvasTestFixture.LINK1_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.canvasAttributeIterator());
	}

	@Test
	public void testLinkAttributeIterator() {
		IteratorTestUtility<ILinkAttribute> iterTest = new IteratorTestUtility<ILinkAttribute>(this.testFixture.getObjectArray(new ILinkAttribute[0], CanvasTestFixture.LINK1_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.linkAttributeIterator());
	}

	@Test
	public void testLinkTerminusIterator() {
		IteratorTestUtility<ILinkTerminus> iterTest = new IteratorTestUtility<ILinkTerminus>(this.testFixture.getObjectArray(new ILinkTerminus[0]));
		iterTest.testSortedIterator(this.testInstance.linkTerminusIterator());
	}

	@Test
	public void testShapeAttributeIterator() {
		IteratorTestUtility<IShapeAttribute> iterTest = new IteratorTestUtility<IShapeAttribute>(this.testFixture.getObjectArray(new IShapeAttribute[0], CanvasTestFixture.SHAPE1_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.shapeAttributeIterator());
	}

	@Test
	public void testLabelAttributeIterator() {
		IteratorTestUtility<ILabelAttribute> iterTest = new IteratorTestUtility<ILabelAttribute>(this.testFixture.getObjectArray(new ILabelAttribute[0], CanvasTestFixture.LABEL9_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.labelAttributeIterator());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetLabelAttributeWrongType() {
		this.testInstance.getLabelAttribute(CanvasTestFixture.SHAPE1_ATT_IDX);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetLabelAttributeNoAtt() {
		this.testInstance.getLabelAttribute(MISSING_ATT_IDX);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetLinkAttributeWrongType() {
		this.testInstance.getLinkAttribute(CanvasTestFixture.SHAPE1_ATT_IDX);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetLinkAttributeNoAtt() {
		this.testInstance.getLinkAttribute(MISSING_ATT_IDX);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetShapeAttributeWrongType() {
		this.testInstance.getShapeAttribute(CanvasTestFixture.LINK1_ATT_IDX);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetShapeAttributeNoAtt() {
		this.testInstance.getShapeAttribute(MISSING_ATT_IDX);
	}

	@Test
	public void testContainsLabelAttribute() {
		assertTrue("has att", this.testInstance.containsLabelAttribute(CanvasTestFixture.LABEL9_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLabelAttribute(MISSING_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLabelAttribute(CanvasTestFixture.ROOT_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLabelAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
	}

	@Test
	public void testContainsLinkAttribute() {
		assertTrue("has att", this.testInstance.containsLinkAttribute(CanvasTestFixture.LINK1_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLinkAttribute(MISSING_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLinkAttribute(CanvasTestFixture.ROOT_ATT_IDX));
		assertFalse("no att", this.testInstance.containsLinkAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
	}

	@Test
	public void testContainsShapeAttribute() {
		assertTrue("has att", this.testInstance.containsShapeAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
		assertFalse("no att", this.testInstance.containsShapeAttribute(MISSING_ATT_IDX));
		assertFalse("no att", this.testInstance.containsShapeAttribute(CanvasTestFixture.ROOT_ATT_IDX));
		assertFalse("no att", this.testInstance.containsShapeAttribute(CanvasTestFixture.LINK1_ATT_IDX));
	}

	@Test
	public void testFindAttribute() {
		ICanvasElementAttribute expectedAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		assertEquals("expected value", expectedAtt, this.testInstance.findAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
		assertNull("no value found", this.testInstance.findAttribute(MISSING_ATT_IDX));
	}

	@Test
	public void testGetLabelAttribute() {
		ICanvasElementAttribute expectedAtt = this.testFixture.getObject(CanvasTestFixture.LABEL9_ATT_ID);
		assertEquals("expected value", expectedAtt, this.testInstance.getLabelAttribute(CanvasTestFixture.LABEL9_ATT_IDX));
	}

	@Test
	public void testGetLinkAttribute() {
		ICanvasElementAttribute expectedAtt = this.testFixture.getObject(CanvasTestFixture.LINK1_ATT_ID);
		assertEquals("expected value", expectedAtt, this.testInstance.getLinkAttribute(CanvasTestFixture.LINK1_ATT_IDX));
	}

	@Test
	public void testGetShapeAttribute() {
		ICanvasElementAttribute expectedAtt = this.testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
		assertEquals("expected value", expectedAtt, this.testInstance.getShapeAttribute(CanvasTestFixture.SHAPE1_ATT_IDX));
	}

	@Test
	public void testCompareTo() {
		assertEquals("compare to self", 0, this.testInstance.compareTo(testInstance));
	}

	@Test
	public void testAddCanvasAttribute() {
		final ICanvasElementAttribute newCanvasAttribute = this.mockery.mock(ICanvasElementAttribute.class, "newCanvasAttribute");
		final int newIdx = EXPECTED_NUM_ATTS+1;
		this.mockery.checking(new Expectations(){{
			allowing(newCanvasAttribute).getCreationSerial(); will(returnValue(newIdx));
			allowing(newCanvasAttribute).getRootAttribute(); will(returnValue(testInstance));
			allowing(newCanvasAttribute).compareTo(with(not(equalTo(newCanvasAttribute)))); will(returnValue(1));
			allowing(newCanvasAttribute).compareTo(with(equalTo(newCanvasAttribute))); will(returnValue(0));
			allowing(newCanvasAttribute).isRemoved(); will(returnValue(false));
		}});
		this.testInstance.addCanvasAttribute(newCanvasAttribute);
		assertEquals("found att", newCanvasAttribute, this.testInstance.findAttribute(newIdx));
		assertEquals("num atts expected", EXPECTED_NUM_ATTS+1, this.testInstance.numCanvasAttributes());
	}
	
	@Test
	public void testIsRemoved(){
		assertTrue("removed", this.testInstance.isRemoved());
	}
	
	@Test
	public void testGetCurrentElement(){
		assertNull("no current element", this.testInstance.getCurrentElement());
	}
}
