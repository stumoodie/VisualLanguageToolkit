/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.testfixture.CanvasTestFixture;
import org.pathwayeditor.testfixture.IObjectConstructor;
import org.pathwayeditor.testfixture.IteratorTestUtility;
import org.pathwayeditor.testfixture.NotationSubsystemFixture;

/**
 * @author Stuart Moodie
 *
 */
public class TestModel {
	private static final String INVALID_NAME1 = " ajhd";
	private static final String INVALID_NAME2 = "ajhd ";
	private static final String INVALID_NAME3 = "&ajhd";
	private static final String VALID_CANVAS_NAME = "12weabdsvAHSGH 3 AJHD  AJJ_16472";
	private static final String EXPECTED_NAME = "TEST CANVAS";
	private static final int EXPECTED_NUM_ATTS = 5;
	private static final int MISSING_ATT_IDX = -1;
	private static final int LAST_CUR_SER_IDX = 2;
	private static final int EXPECTED_ROOT_IDX = 0;
	private static final int EXPECTED_NUM_SHAPE_ATTS = 2;
	private static final int EXPECTED_NUM_LABEL_ATTS = 1;
	private static final int EXPECTED_NUM_LINK_ATTS = 1;
	private IModel testInstance;
	private NotationSubsystemFixture notationFixture;
	private Mockery mockery;
	private CanvasTestFixture testFixture;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockery = new JUnit4Mockery();
		this.notationFixture = new NotationSubsystemFixture(mockery);
		this.notationFixture.buildFixture();
		this.testFixture = new CanvasTestFixture(mockery, "", this.notationFixture.getNotationSubsystem());
		this.testFixture.redefineBuilder(CanvasTestFixture.MODEL_ID, new IObjectConstructor<IModel>(){

			@Override
			public IModel create() {
				testInstance = new Model(EXPECTED_NAME, notationFixture.getNotationSubsystem(), EXPECTED_ROOT_IDX, LAST_CUR_SER_IDX);
				return testInstance;
			}

			@Override
			public boolean build() {
				IShapeAttribute shape1Att = testFixture.getObject(CanvasTestFixture.SHAPE1_ATT_ID);
				testInstance.addCanvasAttribute(shape1Att);
				IShapeAttribute shape2Att = testFixture.getObject(CanvasTestFixture.SHAPE2_ATT_ID);
				testInstance.addCanvasAttribute(shape2Att);
				ILabelAttribute label9Att = testFixture.getObject(CanvasTestFixture.LABEL9_ATT_ID);
				testInstance.addCanvasAttribute(label9Att);
				ILinkAttribute link1Att = testFixture.getObject(CanvasTestFixture.LINK1_ATT_ID);
				testInstance.addCanvasAttribute(link1Att);
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("expected name", EXPECTED_NAME, this.testInstance.getName());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#isValidName(java.lang.String)}.
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
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#checkValidName(java.lang.String)}.
	 */
	@Test
	public void testCheckValidName() {
		assertFalse("is invalid when null", Model.checkValidName(null));
		assertFalse("is invalid when empty string", Model.checkValidName(""));
		assertTrue("is valid name", Model.checkValidName(VALID_CANVAS_NAME));
		assertFalse("is invalid name", Model.checkValidName(INVALID_NAME1));
		assertFalse("is invalid name", Model.checkValidName(INVALID_NAME2));
		assertFalse("is invalid name", Model.checkValidName(INVALID_NAME3));
		assertTrue("is valid name", Model.checkValidName(EXPECTED_NAME));
	}


	@Test
	public void testSetName() {
		this.testInstance.setName(VALID_CANVAS_NAME);
		assertEquals("expected name", VALID_CANVAS_NAME, this.testInstance.getName());
		assertFalse("expected to have changed", EXPECTED_NAME.equals(this.testInstance.getName()));
	}

	@Test
	public void testNumCanvasAttributes() {
		assertEquals("expected num attributes", EXPECTED_NUM_ATTS, this.testInstance.numCanvasAttributes());
	}

	@Test
	public void testNumShapeAttributes() {
		assertEquals("expected num attributes", EXPECTED_NUM_SHAPE_ATTS, this.testInstance.numShapeAttributes());
	}

	@Test
	public void testNumLabelAttributes() {
		assertEquals("expected num attributes", EXPECTED_NUM_LABEL_ATTS, this.testInstance.numLabelAttributes());
	}

	@Test
	public void testNumLinkAttributes() {
		assertEquals("expected num attributes", EXPECTED_NUM_LINK_ATTS, this.testInstance.numLinkAttributes());
	}

	@Test
	public void testCanvasAttributeIterator() {
		List<ICanvasElementAttribute> testArray = new ArrayList<ICanvasElementAttribute>();
		testArray.add(this.testFixture.getModel().getRootAttribute());
		testArray.addAll(Arrays.asList(this.testFixture.getObjectArray(new ICanvasElementAttribute[0],
				CanvasTestFixture.SHAPE1_ATT_ID, CanvasTestFixture.SHAPE2_ATT_ID, CanvasTestFixture.LABEL9_ATT_ID, CanvasTestFixture.LINK1_ATT_ID)));
		IteratorTestUtility<ICanvasElementAttribute> iterTest = new IteratorTestUtility<ICanvasElementAttribute>(testArray);
		iterTest.testSortedIterator(this.testInstance.canvasAttributeIterator());
	}

	@Test
	public void testLinkAttributeIterator() {
		IteratorTestUtility<ILinkAttribute> iterTest = new IteratorTestUtility<ILinkAttribute>(this.testFixture.getObjectArray(new ILinkAttribute[0], CanvasTestFixture.LINK1_ATT_ID));
		iterTest.testSortedIterator(this.testInstance.linkAttributeIterator());
	}

	@Test
	public void testShapeAttributeIterator() {
		IteratorTestUtility<IShapeAttribute> iterTest = new IteratorTestUtility<IShapeAttribute>(this.testFixture.getObjectArray(new IShapeAttribute[0], CanvasTestFixture.SHAPE1_ATT_ID,
				CanvasTestFixture.SHAPE2_ATT_ID));
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
	public void testAddCanvasAttribute() {
		final ICanvasElementAttribute newCanvasAttribute = this.mockery.mock(ICanvasElementAttribute.class, "newCanvasAttribute");
		final int newIdx = EXPECTED_NUM_ATTS+1;
		this.mockery.checking(new Expectations(){{
			allowing(newCanvasAttribute).getCreationSerial(); will(returnValue(newIdx));
			allowing(newCanvasAttribute).getModel(); will(returnValue(testInstance));
			allowing(newCanvasAttribute).compareTo(with(not(equalTo(newCanvasAttribute)))); will(returnValue(1));
			allowing(newCanvasAttribute).compareTo(with(equalTo(newCanvasAttribute))); will(returnValue(0));
			allowing(newCanvasAttribute).isRemoved(); will(returnValue(false));
		}});
		this.testInstance.addCanvasAttribute(newCanvasAttribute);
		assertEquals("found att", newCanvasAttribute, this.testInstance.findAttribute(newIdx));
		assertEquals("num atts expected", EXPECTED_NUM_ATTS+1, this.testInstance.numCanvasAttributes());
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#linkAttributeFactory()}.
	 */
	@Test
	public void testLinkAttributeFactory() {
		assertNotNull("link att factory exists", this.testInstance.linkAttributeFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#labelAttributeFactory()}.
	 */
	@Test
	public void testLabelAttributeFactory() {
		assertNotNull("label att factory exists", this.testInstance.labelAttributeFactory());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.Model#getNotationSubsystem()}.
	 */
	@Test
	public void testGetNotationSubsystem() {
		assertEquals("expected ot", this.notationFixture.getNotationSubsystem(), this.testInstance.getNotationSubsystem());
	}

}
