/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.figure.geometry.Point;

/**
 * BendPointContainerTest
 *
 * @author Stuart Moodie
 *
 */
@RunWith(JMock.class)
public class BendPointContainerPopulatedTest {
	private static final Point EXPECTED_BP1 = new Point(45.0, 89.0);
	private static final Point EXPECTED_DELTA = new Point(2.0, 5.0);
	private static final Point EXPECTED_START = new Point(36, 49);
	private static final Point EXPECTED_END = new Point(27, 382);
	private static final Point EXPECTED_BP2 = new Point(58, 20);
	private static final Point EXPECTED_BP3 = new Point(293, 382);
	private static final Point EXPECTED_BP4 = new Point(983, 459);
	private static final int EXPECTED_BP4_IDX = 3;
	private static final int EXPECTED_NUM_BPS = 3;
	private static final int EXPECTED_NUM_CURVES = 4;
	private static final ICurveSegment EXPECTED_SEG1 = new StraightLineCurveSegment(EXPECTED_START, EXPECTED_BP1);
	private static final ICurveSegment EXPECTED_SEG2 = new StraightLineCurveSegment(EXPECTED_BP1, EXPECTED_BP2);
	private static final ICurveSegment EXPECTED_SEG3 = new StraightLineCurveSegment(EXPECTED_BP2, EXPECTED_BP3);
	private static final ICurveSegment EXPECTED_SEG4 = new StraightLineCurveSegment(EXPECTED_BP3, EXPECTED_END);
	private Mockery mockery;
	private BendPointContainer testInstance;
	private ILinkAttribute testLinkAttribute;
	
	
	public BendPointContainerPopulatedTest(){
		
	}
	
	@Before
	public void setUp(){
		this.mockery = new JUnit4Mockery();
		this.testLinkAttribute = this.mockery.mock(ILinkAttribute.class, "testLinkAttribute");
		this.testInstance = new BendPointContainer(testLinkAttribute);
		this.testInstance.getFirstCurveSegment().setStartPoint(EXPECTED_START);
		this.testInstance.getLastCurveSegment().setEndPoint(EXPECTED_END);
		this.testInstance.createNewBendPoint(EXPECTED_BP1);
		this.testInstance.createNewBendPoint(EXPECTED_BP2);
		this.testInstance.createNewBendPoint(EXPECTED_BP3);
	}

	@After
	public void tearDown(){
		this.mockery = null;
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#containsBendPoint(int)}.
	 */
	@Test
	public void testContainsBendPoint() {
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(-3));
		assertTrue("Has bendpoint", this.testInstance.containsBendPoint(0));
		assertTrue("Has bendpoint", this.testInstance.containsBendPoint(1));
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(3));
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(6));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetBendPoint() {
		assertEquals("expected val", EXPECTED_BP1, this.testInstance.getBendPoint(EXPECTED_NUM_BPS));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#removeBendPoint(int)}.
	 */
	@Test
	public void testRemoveFirstBendPoint() {
		int oldNumBps = this.testInstance.numBendPoints();
		int oldNumCurves = this.testInstance.numCurveSegments();
		this.testInstance.removeBendPoint(0);
		assertEquals("Decremented bps", oldNumBps-1, this.testInstance.numBendPoints());
		assertEquals("Decremented curves", oldNumCurves-1, this.testInstance.numCurveSegments());
		assertTrue("Has bendpoint", this.testInstance.containsBendPoint(0));
		assertTrue("Has bendpoint", this.testInstance.containsBendPoint(1));
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(2));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(0));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(1));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(2));
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(3));
		assertEquals("expected bp", EXPECTED_BP2, this.testInstance.getBendPoint(0));
		assertEquals("expected bp", EXPECTED_BP3, this.testInstance.getBendPoint(1));
		assertEquals("Expected curve seg", new StraightLineCurveSegment(EXPECTED_START, EXPECTED_BP2), this.testInstance.getCurveSegment(0));
		assertEquals("Expected curve seg", EXPECTED_SEG3, this.testInstance.getCurveSegment(1));
		assertEquals("Expected curve seg", EXPECTED_SEG4, this.testInstance.getCurveSegment(2));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#removeBendPoint(int)}.
	 */
	@Test
	public void testRemoveMiddleBendPoint() {
		int oldNumBps = this.testInstance.numBendPoints();
		int oldNumCurves = this.testInstance.numCurveSegments();
		this.testInstance.removeBendPoint(1);
		assertEquals("Decremented bps", oldNumBps-1, this.testInstance.numBendPoints());
		assertEquals("Decremented curves", oldNumCurves-1, this.testInstance.numCurveSegments());
		assertTrue("Has bendpoint", this.testInstance.containsBendPoint(0));
		assertTrue("Has bendpoint", this.testInstance.containsBendPoint(1));
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(2));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(0));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(1));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(2));
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(3));
		assertEquals("expected bp", EXPECTED_BP1, this.testInstance.getBendPoint(0));
		assertEquals("expected bp", EXPECTED_BP3, this.testInstance.getBendPoint(1));
		assertEquals("Expected curve seg", EXPECTED_SEG1, this.testInstance.getCurveSegment(0));
		assertEquals("Expected curve seg", new StraightLineCurveSegment(EXPECTED_BP1, EXPECTED_BP3), this.testInstance.getCurveSegment(1));
		assertEquals("Expected curve seg", EXPECTED_SEG4, this.testInstance.getCurveSegment(2));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#removeBendPoint(int)}.
	 */
	@Test
	public void testRemoveLastBendPoint() {
		int oldNumBps = this.testInstance.numBendPoints();
		int oldNumCurves = this.testInstance.numCurveSegments();
		this.testInstance.removeBendPoint(2);
		assertEquals("Decremented bps", oldNumBps-1, this.testInstance.numBendPoints());
		assertEquals("Decremented curves", oldNumCurves-1, this.testInstance.numCurveSegments());
		assertTrue("Has bendpoint", this.testInstance.containsBendPoint(0));
		assertTrue("Has bendpoint", this.testInstance.containsBendPoint(1));
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(2));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(0));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(1));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(2));
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(3));
		assertEquals("expected bp", EXPECTED_BP1, this.testInstance.getBendPoint(0));
		assertEquals("expected bp", EXPECTED_BP2, this.testInstance.getBendPoint(1));
		assertEquals("Expected curve seg", EXPECTED_SEG1, this.testInstance.getCurveSegment(0));
		assertEquals("Expected curve seg", EXPECTED_SEG2, this.testInstance.getCurveSegment(1));
		assertEquals("Expected curve seg", new StraightLineCurveSegment(EXPECTED_BP2, EXPECTED_END), this.testInstance.getCurveSegment(2));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#containsCurveSegment(int)}.
	 */
	@Test
	public void testContainsCurveSegment() {
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(-1));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(0));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(1));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(2));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(3));
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(4));
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(6));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#createNewBendPoint(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testCreateNewBendPointPoint() {
		int prevNumBendPoints = this.testInstance.numBendPoints();
		int prevCurveSegs = this.testInstance.numCurveSegments();
		this.testInstance.createNewBendPoint(EXPECTED_BP4);
		assertEquals("bp count incremented", prevNumBendPoints+1, this.testInstance.numBendPoints());
		assertEquals("curve seg count incremented", prevCurveSegs+1, this.testInstance.numCurveSegments());
		assertEquals("expected bp", EXPECTED_BP4, this.testInstance.getBendPoint(EXPECTED_BP4_IDX));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#createNewBendPoint(int, org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testCreateNewBendPointIntPointAtBeginning() {
		int idx = 0;
		int prevNumBendPoints = this.testInstance.numBendPoints();
		int prevCurveSegs = this.testInstance.numCurveSegments();
		this.testInstance.createNewBendPoint(idx, EXPECTED_BP3);
		assertEquals("bp count incremented", prevNumBendPoints+1, this.testInstance.numBendPoints());
		assertEquals("curve seg count incremented", prevCurveSegs+1, this.testInstance.numCurveSegments());
		assertEquals("expected bp", EXPECTED_BP3, this.testInstance.getBendPoint(idx));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#createNewBendPoint(int, org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testCreateNewBendPointIntPointInMiddle() {
		int idx = 1;
		int prevNumBendPoints = this.testInstance.numBendPoints();
		int prevCurveSegs = this.testInstance.numCurveSegments();
		this.testInstance.createNewBendPoint(idx, EXPECTED_BP3);
		assertEquals("bp count incremented", prevNumBendPoints+1, this.testInstance.numBendPoints());
		assertEquals("curve seg count incremented", prevCurveSegs+1, this.testInstance.numCurveSegments());
		assertEquals("expected bp", EXPECTED_BP3, this.testInstance.getBendPoint(idx));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#createNewBendPoint(int, org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testCreateNewBendPointIntPointInvalidIdx() {
		int idx = 4;
		this.testInstance.createNewBendPoint(idx, EXPECTED_BP1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#bendPointIterator()}.
	 */
	@Test
	public void testBendPointIterator() {
		Iterator<Point> iter = this.testInstance.bendPointIterator();
		assertTrue("Has", iter.hasNext());
		assertEquals("expected bp", EXPECTED_BP1, iter.next());
		assertEquals("expected bp", EXPECTED_BP2, iter.next());
		assertEquals("expected bp", EXPECTED_BP3, iter.next());
			assertFalse("No bps", iter.hasNext());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#numBendPoints()}.
	 */
	@Test
	public void testNumBendPoints() {
		assertEquals("No bendpounts", EXPECTED_NUM_BPS, this.testInstance.numBendPoints());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#numCurveSegments()}.
	 */
	@Test
	public void testNumCurveSegments() {
		assertEquals("Expected curves", EXPECTED_NUM_CURVES, this.testInstance.numCurveSegments());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getLinkAttribute()}.
	 */
	@Test
	public void testGetLinkAttribute() {
		assertEquals("Expected link att", this.testLinkAttribute, this.testInstance.getLinkAttribute());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#translateAll(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testTranslateAll() {
		this.testInstance.translateAll(EXPECTED_DELTA);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#curveIterator()}.
	 */
	@Test
	public void testCurveIterator() {
		Iterator<ICurveSegment> iter = this.testInstance.curveIterator();
		assertTrue("Has", iter.hasNext());
		assertEquals("expected seg", EXPECTED_SEG1, iter.next());
		assertEquals("expected seg", EXPECTED_SEG2, iter.next());
		assertEquals("expected seg", EXPECTED_SEG3, iter.next());
		assertEquals("expected seg", EXPECTED_SEG4, iter.next());
		assertFalse("No curve seg", iter.hasNext());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getFirstCurveSegment()}.
	 */
	@Test
	public void testGetFirstCurveSegment() {
		assertEquals("Expected seg", EXPECTED_SEG1, this.testInstance.getFirstCurveSegment());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getFirstCurveSegment()}.
	 */
	@Test
	public void testGetLastCurveSegment() {
		assertEquals("Expected seg", EXPECTED_SEG4, this.testInstance.getLastCurveSegment());
	}

}
