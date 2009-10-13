package org.pathwayeditor.figure.geometry;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullCalculatorSingleRoundedRectangleTest {
	private static final int EXPECTED_NUM_HULL_POINTS = 16;
	private ConvexHullCalculator testInstance;

	@Before
	public void setUp() throws Exception {
		this.testInstance = new ConvexHullCalculator();
		this.testInstance.addRoundRectangle(0.0, 0.0, 60.0, 40.0, 8.0, 10.0);
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
	public final void testCalculate() throws IOException {
		this.testInstance.calculate();
		ConvexHullRenderer renderer = new ConvexHullRenderer(this.testInstance.getOriginalPoints(), this.testInstance.getConvexHull());
		renderer.writeAsPsFile(new File("test.ps"));
//		Writer writer = new FileWriter("originalPoints.ps");
//		this.testInstance.writeOriginalPoints(writer);
//		writer.close();
//		Writer hullWriter = new FileWriter("hullPoints.ps");
//		this.testInstance.writeHullPoints(hullWriter);
//		hullWriter.close();
		assertEquals("same number of points", EXPECTED_NUM_HULL_POINTS, this.testInstance.getConvexHull().numPoints());
	}

}
