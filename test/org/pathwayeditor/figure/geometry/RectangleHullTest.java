/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author smoodie
 *
 */
public class RectangleHullTest {
	private static final Envelope EXPECTED_ENVELOPE = new Envelope(25, 30, 60, 90);
	private IConvexHull testInstance;
	private IConvexHull otherHull;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testInstance  = new RectangleHull(EXPECTED_ENVELOPE);
		ConvexHullBuilder builder = new ConvexHullBuilder();
		builder.addPoint(new Point(15, 15));
		builder.addPoint(new Point(30, 35));
		builder.addPoint(new Point(45, 135));
		builder.addPoint(new Point(-15, 35));
		builder.calcCurrEnvelope();
		this.otherHull = builder.getConvexHull();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.RectangleHull#containsPoint(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Test
	public void testContainsPoint() {
		assertTrue("expected inside", this.testInstance.containsPoint(new Point(30, 40)));
		assertTrue("expected inside", this.testInstance.containsPoint(new Point(25, 40)));
		assertTrue("expected inside", this.testInstance.containsPoint(new Point(55, 91)));
		assertTrue("expected inside", this.testInstance.containsPoint(new Point(45, 40)));
		assertFalse("expected outside", this.testInstance.containsPoint(new Point(55, 121)));
		assertFalse("expected outside", this.testInstance.containsPoint(new Point(-10, 10)));
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.RectangleHull#getArea()}.
	 */
	@Test
	public void testGetArea() {
		double expectedArea = 5400.0;
		assertEquals("expected area", expectedArea, this.testInstance.getArea(), 0.0001);
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.RectangleHull#getCentre()}.
	 */
	@Test
	public void testGetCentre() {
		Point expectedCentre = new Point(55, 75);
		assertEquals("expected area", expectedCentre, this.testInstance.getCentre());
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.RectangleHull#hullsIntersect(org.pathwayeditor.figure.geometry.IConvexHull)}.
	 */
	@Test
	public void testHullsIntersect() {
		assertTrue("hulls intersect", this.testInstance.hullsIntersect(otherHull));
		assertTrue("hulls intersect", this.otherHull.hullsIntersect(this.testInstance));
	}

}
