/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
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
	private static final double DOUBLE_TOLERANCE = 1e-10;
	private Envelope testInstance;
	private Envelope altTestInstance;
	
	
	@Before
	public void doSetUp(){
		this.testInstance = new Envelope(EXPECTED_X, EXPECTED_Y, EXPECTED_WIDTH, EXPECTED_HEIGHT);
		this.altTestInstance = new Envelope(77.0, 90, 86, 71);
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
		{
			Envelope intersectingEnv = new Envelope(-8.9, -9.1, 1.9, 1.9);
			assertFalse("intersects", this.altTestInstance.intersects(intersectingEnv));
			assertFalse("reciprocal case intersects", intersectingEnv.intersects(this.altTestInstance));
		}
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
	
	@Test
	public void testCalcTransformationJustTranslation(){
		Envelope transformedEnv = new Envelope(EXPECTED_X+10, EXPECTED_Y+10, EXPECTED_WIDTH, EXPECTED_HEIGHT);
		Transformation expectedTransform = new Transformation(new Point(10, 10), new Scale(1, 1));
		Transformation actualTransform = this.testInstance.calcTransformation(transformedEnv);
		assertEquals("expected transform", expectedTransform, actualTransform);
	}
	
	@Test
	public void testCalcTransformationTranslationAndScaling(){
		Envelope transformedEnv = new Envelope(EXPECTED_X+10.0, EXPECTED_Y+1.53, EXPECTED_WIDTH*0.75, EXPECTED_HEIGHT*1.2);
		Transformation expectedTransform = new Transformation(new Point(10.0, 1.53), new Scale(0.75, 1.2));
		Transformation actualTransform = this.testInstance.calcTransformation(transformedEnv);
		assertEquals("expected transform x", expectedTransform.getTranslation().getX(), actualTransform.getTranslation().getX(), DOUBLE_TOLERANCE);
		assertEquals("expected transform 7", expectedTransform.getTranslation().getY(), actualTransform.getTranslation().getY(), DOUBLE_TOLERANCE);
		assertEquals("scale ok", expectedTransform.getScale(), actualTransform.getScale());
	}
	
	@Test
	public void testCalcTransformationNoTranslationOrScaling(){
		Envelope transformedEnv = this.testInstance;
		Transformation expectedTransform = new Transformation(new Point(0, 0), new Scale(1.0, 1.0));
		Transformation actualTransform = this.testInstance.calcTransformation(transformedEnv);
		assertEquals("expected transform", expectedTransform, actualTransform);
	}
	
	@Test
	public void testApplyTransformationJustTranslation(){
		Envelope expectedEnv = new Envelope(EXPECTED_X+10, EXPECTED_Y+10, EXPECTED_WIDTH, EXPECTED_HEIGHT);
		Transformation transform = new Transformation(new Point(10, 10), new Scale(1, 1));
		Envelope actualEnv = this.testInstance.applyTransformation(transform);
		assertEquals("expected transformed envelope", expectedEnv, actualEnv);
	}
	
	@Test
	public void testApplyTransformationTranslationAndScaling(){
		Envelope expectedEnv = new Envelope(EXPECTED_X+10.0, EXPECTED_Y+1.53, EXPECTED_WIDTH*0.75, EXPECTED_HEIGHT*1.2);
		Transformation expectedTransform = new Transformation(new Point(10.0, 1.53), new Scale(0.75, 1.2));
		Envelope actualEnv = this.testInstance.applyTransformation(expectedTransform);
		assertEquals("expected transformed envelope", expectedEnv, actualEnv);
	}
	
	@Test
	public void testApplyTransformationNoTranslationOrScaling(){
		Envelope expectedEnv = this.testInstance;
		Transformation expectedTransform = new Transformation(new Point(0, 0), new Scale(1.0, 1.0));
		Envelope actualEnv = this.testInstance.applyTransformation(expectedTransform);
		assertEquals("expected transformed envelope", expectedEnv, actualEnv);
	}
	
}
