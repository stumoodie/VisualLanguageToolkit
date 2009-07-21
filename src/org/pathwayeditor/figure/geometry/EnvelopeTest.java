/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author smoodie
 *
 */
public class EnvelopeTest {
	private static final double EXPECTED_Y = -73.0;
	private static final double EXPECTED_X = 179.0;
	private static final double EXPECTED_WIDTH = 61;
	private static final double EXPECTED_HEIGHT = 41;
	private static final Point EXPECTED_HORIZONTAL_CORNER = new Point(240.0, -73.0);
	private static final Point EXPECTED_VERTICAL_CORNER = new Point(179.0, -32.0);
	private static final Point EXPECTED_DIAGONAL_CORNER = new Point(240.0, -32.0);
	private Envelope testInstance;
	
	
	@Before
	public void doSetUp(){
		this.testInstance = new Envelope(EXPECTED_X, EXPECTED_Y, EXPECTED_WIDTH, EXPECTED_HEIGHT);
	}
	
	
	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#getDimension()}.
	 */
	@Test
	public void testGetDimension() {
		assertEquals("expected dim", new Dimension(EXPECTED_WIDTH, EXPECTED_HEIGHT), this.testInstance.getDimension());
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#getOrigin()}.
	 */
	@Test
	public void testGetOrigin() {
		assertEquals("expected origin", new Point(EXPECTED_X, EXPECTED_Y), this.testInstance.getOrigin());
	}

	@Test
	public void testIntersectsWithItself() {
		assertTrue("intersects", this.testInstance.intersects(this.testInstance));
	}

	@Test
	public void testIntersectsWithOverlappingEnv() {
		Envelope intersectingEnv = new Envelope(EXPECTED_X+10, EXPECTED_Y+10, EXPECTED_WIDTH, EXPECTED_HEIGHT);
		assertTrue("intersects", this.testInstance.intersects(intersectingEnv));
		assertTrue("reciprocal case intersects", intersectingEnv.intersects(this.testInstance));
	}

	@Test
	public void testIntersectsWithEnvelopedEnv() {
		Envelope intersectingEnv = new Envelope(EXPECTED_X+10, EXPECTED_Y+10, EXPECTED_WIDTH-10, EXPECTED_HEIGHT-10);
		assertTrue("intersects", this.testInstance.intersects(intersectingEnv));
		assertTrue("reciprocal case intersects", intersectingEnv.intersects(this.testInstance));
	}

	@Test
	public void testIntersectsWithLargerEnvelopingEnv() {
		Envelope intersectingEnv = new Envelope(EXPECTED_X-10, EXPECTED_Y-10, EXPECTED_WIDTH+20, EXPECTED_HEIGHT+20);
		assertTrue("intersects", this.testInstance.intersects(intersectingEnv));
		assertTrue("reciprocal case intersects", intersectingEnv.intersects(this.testInstance));
	}

	@Test
	public void testIntersectsNotFound() {
		{
			Envelope intersectingEnv = new Envelope(EXPECTED_X+100, EXPECTED_Y, EXPECTED_WIDTH, EXPECTED_HEIGHT);
			assertFalse("intersects", this.testInstance.intersects(intersectingEnv));
			assertFalse("reciprocal case intersects", intersectingEnv.intersects(this.testInstance));
		}
		{
			Envelope intersectingEnv = new Envelope(EXPECTED_X, EXPECTED_Y+100, EXPECTED_WIDTH, EXPECTED_HEIGHT);
			assertFalse("intersects", this.testInstance.intersects(intersectingEnv));
			assertFalse("reciprocal case intersects", intersectingEnv.intersects(this.testInstance));
		}
		{
			Envelope intersectingEnv = new Envelope(EXPECTED_X-100, EXPECTED_Y, EXPECTED_WIDTH, EXPECTED_HEIGHT);
			assertFalse("intersects", this.testInstance.intersects(intersectingEnv));
			assertFalse("reciprocal case intersects", intersectingEnv.intersects(this.testInstance));
		}
		{
			Envelope intersectingEnv = new Envelope(EXPECTED_X, EXPECTED_Y-100, EXPECTED_WIDTH, EXPECTED_HEIGHT);
			assertFalse("intersects", this.testInstance.intersects(intersectingEnv));
			assertFalse("reciprocal case intersects", intersectingEnv.intersects(this.testInstance));
		}
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#transformPointToNewEnvelope(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Envelope)}.
	 */
	@Ignore @Test
	public void testTransformPointToNewEnvelope() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#equals(java.lang.Object)}.
	 */
	@Ignore @Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#getHorizontalCorner()}.
	 */
	@Test
	public void testGetHorizontalCorner() {
		assertEquals("expected value", EXPECTED_HORIZONTAL_CORNER, this.testInstance.getHorizontalCorner());
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#getVerticalCorner()}.
	 */
	@Test
	public void testGetVerticalCorner() {
		assertEquals("expected value", EXPECTED_VERTICAL_CORNER, this.testInstance.getVerticalCorner());
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#getDiagonalCorner()}.
	 */
	@Test
	public void testGetDiagonalCorner() {
		assertEquals("expected value", EXPECTED_DIAGONAL_CORNER, this.testInstance.getDiagonalCorner());
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#containsPoint(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Ignore @Test
	public void testContainsPoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#contains(org.pathwayeditor.figure.geometry.Envelope)}.
	 */
	@Test
	public void testContains() {
		Envelope intersectingEnv = new Envelope(EXPECTED_X+10, EXPECTED_Y+10, EXPECTED_WIDTH-10, EXPECTED_HEIGHT-10);
		assertTrue("contains", this.testInstance.contains(intersectingEnv));
		assertFalse("reciprocal case not contains", intersectingEnv.contains(this.testInstance));
	}

	@Test
	public void testContainsWithOverlappingEnv(){
		Envelope intersectingEnv = new Envelope(EXPECTED_X+10, EXPECTED_Y+10, EXPECTED_WIDTH, EXPECTED_HEIGHT);
		assertFalse("not contains", this.testInstance.contains(intersectingEnv));
		assertFalse("reciprocal case not contains", intersectingEnv.contains(this.testInstance));
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#translate(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Ignore @Test
	public void testTranslate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#changeOrigin(org.pathwayeditor.figure.geometry.Point)}.
	 */
	@Ignore @Test
	public void testChangeOrigin() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pathwayeditor.figure.geometry.Envelope#changeDimension(org.pathwayeditor.figure.geometry.Dimension)}.
	 */
	@Ignore @Test
	public void testChangeDimension() {
		fail("Not yet implemented");
	}

}
