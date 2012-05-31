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
public class BendPointContainerEmptyTest {
	private static final Point EXPECTED_BP1 = new Point(45.0, 89.0);
	private static final Point EXPECTED_DELTA = new Point(2.0, 5.0);
	private static final Point EXPECTED_START = Point.ORIGIN;
	private static final Point EXPECTED_END = Point.ORIGIN;
	private Mockery mockery;
	private BendPointContainer testInstance;
	private ILinkAttribute testLinkAttribute;
	
	
	public BendPointContainerEmptyTest(){
		
	}
	
	@Before
	public void setUp(){
		this.mockery = new JUnit4Mockery();
		this.testLinkAttribute = this.mockery.mock(ILinkAttribute.class, "testLinkAttribute");
		this.testInstance = new BendPointContainer(testLinkAttribute);
	}

	@After
	public void tearDown(){
		this.mockery = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#containsBendPoint(int)}.
	 */
	@Test
	public void testContainsBendPoint() {
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(-1));
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(0));
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(1));
		assertFalse("No bendpoint", this.testInstance.containsBendPoint(6));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#containsCurveSegment(int)}.
	 */
	@Test
	public void testContainsCurveSegment() {
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(-1));
		assertTrue("Has curve seg", this.testInstance.containsCurveSegment(0));
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(1));
		assertFalse("No curve seg", this.testInstance.containsCurveSegment(6));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getBendPoint(int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetBendPoint() {
		this.testInstance.getBendPoint(0);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getCurveSegment}.
	 */
	@Test
	public void testGetCurveSegment() {
		ICurveSegment expectedCurveSeg = new StraightLineCurveSegment(EXPECTED_START, EXPECTED_END);
		assertEquals("expected curve seg", expectedCurveSeg, this.testInstance.getCurveSegment(0));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getCurveSegment}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetCurveSegmentFail1() {
		this.testInstance.getCurveSegment(1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getCurveSegment}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetCurveSegmentFail2() {
		this.testInstance.getCurveSegment(-1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#removeBendPoint(int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveBendPoint() {
		this.testInstance.removeBendPoint(0);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#createNewBendPoint(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testCreateNewBendPointPoint() {
		int prevNumBendPoints = this.testInstance.numBendPoints();
		int prevCurveSegs = this.testInstance.numCurveSegments();
		this.testInstance.createNewBendPoint(EXPECTED_BP1);
		assertEquals("bp count incremented", prevNumBendPoints+1, this.testInstance.numBendPoints());
		assertEquals("curve seg count incremented", prevCurveSegs+1, this.testInstance.numCurveSegments());
		assertEquals("expected bp", EXPECTED_BP1, this.testInstance.getBendPoint(0));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#createNewBendPoint(int, org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testCreateNewBendPointIntPoint() {
		int idx = 0;
		int prevNumBendPoints = this.testInstance.numBendPoints();
		int prevCurveSegs = this.testInstance.numCurveSegments();
		this.testInstance.createNewBendPoint(idx, EXPECTED_BP1);
		assertEquals("bp count incremented", prevNumBendPoints+1, this.testInstance.numBendPoints());
		assertEquals("curve seg count incremented", prevCurveSegs+1, this.testInstance.numCurveSegments());
		assertEquals("expected bp", EXPECTED_BP1, this.testInstance.getBendPoint(idx));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#createNewBendPoint(int, org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testCreateNewBendPointIntPointInvalidIdx() {
		int idx = 1;
		this.testInstance.createNewBendPoint(idx, EXPECTED_BP1);
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#bendPointIterator()}.
	 */
	@Test
	public void testBendPointIterator() {
		Iterator<Point> iter = this.testInstance.bendPointIterator();
		assertFalse("No bp", iter.hasNext());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#numBendPoints()}.
	 */
	@Test
	public void testNumBendPoints() {
		assertEquals("No bendpounts", 0, this.testInstance.numBendPoints());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#numCurveSegments()}.
	 */
	@Test
	public void testNumCurveSegments() {
		assertEquals("Expected curves", 1, this.testInstance.numCurveSegments());
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
		assertTrue("Has curve", iter.hasNext());
		ICurveSegment expectedCurveSeg = new StraightLineCurveSegment(EXPECTED_START, EXPECTED_END);
		assertEquals("Expected seg", expectedCurveSeg, iter.next());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getFirstCurveSegment()}.
	 */
	@Test
	public void testGetFirstCurveSegment() {
		ICurveSegment expectedCurveSeg = new StraightLineCurveSegment(EXPECTED_START, EXPECTED_END);
		assertEquals("Expected seg", expectedCurveSeg, this.testInstance.getFirstCurveSegment());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.impl.BendPointContainer#getFirstCurveSegment()}.
	 */
	@Test
	public void testGetLastCurveSegment() {
		ICurveSegment expectedCurveSeg = new StraightLineCurveSegment(EXPECTED_START, EXPECTED_END);
		assertEquals("Expected seg", expectedCurveSeg, this.testInstance.getLastCurveSegment());
	}

}
