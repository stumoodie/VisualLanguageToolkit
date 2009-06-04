package org.pathwayeditor.figure.geometry;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullCalculatorSingleRectangleTest {
//	private static final Instruction TEST_INSTRUCTIONS[] = { new Instruction(0.0), new Instruction(0.0), new Instruction(50.0), new Instruction(60.0), new Instruction(OpCodes.RECT) }; 
	private static final Point EXPECTED_HULL_POINTS[] = { new Point(50.0, 0.0), new Point(0.0, 0.0), new Point(0.0, 60.0), new Point(50.0, 60.0) }; 
	private ConvexHullCalculator testInstance;

	@Before
	public void setUp() throws Exception {
		this.testInstance = new ConvexHullCalculator();
		this.testInstance.addRectangle(0.0, 0.0, 50.0, 60.0);
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	@Test(expected=IllegalStateException.class)
	public final void testGetConvexHullPoints(){
		this.testInstance.getConvexHull();
	}
	
	@Test
	public final void testCalculate() {
		this.testInstance.calculate();
		List<Point> actualHull = this.testInstance.getConvexHull().getPoints();
		assertEquals("expected hull points", Arrays.asList(EXPECTED_HULL_POINTS), actualHull);
	}

}
