/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

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
public class BoundsTest {
	private static final int EXPECTED_X = 87;
	private static final int EXPECTED_Y = 56;
	private static final int EXPECTED_WIDTH = 35;
	private static final int EXPECTED_HEIGHT = 56;
	private static final Location EXPECTED_TRANSFORMED_POINT = new Location(37, 14);
	private static final Bounds NEW_BOUNDS = new Bounds(10, 10, 80, 50);
	private static final Location ORIG_POINT = new Location(99, 60);
	private static final Location INBOUNDS_POINT = new Location(90, 60);
	private static final Location OUTBOUNDS_POINT = new Location(10, 10);
	private static final Location ON_EDGE_BOUNDS_POINT = new Location(122, 112);
	private Bounds testInstance;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testInstance = new Bounds(EXPECTED_X, EXPECTED_Y, EXPECTED_WIDTH, EXPECTED_HEIGHT);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds#getOrigin()}.
	 */
	@Test
	public final void testGetOrigin() {
		Location expectedValue = new Location(EXPECTED_X, EXPECTED_Y);
		assertEquals("correct location", expectedValue, this.testInstance.getOrigin());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds#getHorizontalCorner()}.
	 */
	@Test
	public final void testGetHorizontalCorner() {
		Location expectedValue = new Location(EXPECTED_X+EXPECTED_WIDTH, EXPECTED_Y);
		assertEquals("correct location", expectedValue, this.testInstance.getHorizontalCorner());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds#getVerticalCorner()}.
	 */
	@Test
	public final void testGetVerticalCorner() {
		Location expectedValue = new Location(EXPECTED_X, EXPECTED_Y+EXPECTED_HEIGHT);
		assertEquals("correct location", expectedValue, this.testInstance.getVerticalCorner());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds#getDiagonalCorner()}.
	 */
	@Test
	public final void testGetDiagonalCorner() {
		Location expectedValue = new Location(EXPECTED_X+EXPECTED_WIDTH, EXPECTED_Y+EXPECTED_HEIGHT);
		assertEquals("correct location", expectedValue, this.testInstance.getDiagonalCorner());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds#getSize()}.
	 */
	@Test
	public final void testGetSize() {
		Size expectedValue = new Size(EXPECTED_WIDTH, EXPECTED_HEIGHT);
		assertEquals("correct location", expectedValue, this.testInstance.getSize());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds#isPointWithinBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)}.
	 */
	@Test
	public final void testIsPointWithinBounds() {
		assertTrue("point in bounds", this.testInstance.isPointWithinBounds(INBOUNDS_POINT));
		assertTrue("point in bounds", this.testInstance.isPointWithinBounds(ON_EDGE_BOUNDS_POINT));
		assertFalse("point in bounds", this.testInstance.isPointWithinBounds(OUTBOUNDS_POINT));
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds#transformPointToNewBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)}.
	 */
	@Test
	public final void testTransformPointToNewBounds() {
		assertEquals("expected transformation", EXPECTED_TRANSFORMED_POINT, this.testInstance.transformPointToNewBounds(ORIG_POINT, NEW_BOUNDS));
	}

}
