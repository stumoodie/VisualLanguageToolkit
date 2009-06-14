package org.pathwayeditor.figure.geometry;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullCalculatorMultipleShapesTest {
	private static final int EXPECTED_NUM_HULL_POINTS = 21;
	private ConvexHullCalculator testInstance;

	@Before
	public void setUp() throws Exception {
		this.testInstance = new ConvexHullCalculator();
		this.testInstance.addOval(0.0, 0.0, 60.0, 40.0);
		this.testInstance.addRectangle(30.0, 10.0, 50.0, 60.0);
		this.testInstance.addPolygon(new double[]{40.0, 40.0, 80.0, 40.0, 60.0, 90.0});
		this.testInstance.addPolyline(new double[]{50.0, 50.0, 90.0, 50.0, 70.0, 100.0});
		this.testInstance.addArc(-10.0, -90.0, 90.0, 50.0, 47.0, 120.0);
		this.testInstance.addLine(-10.0, -20.0, -5.0, -20.0);
		this.testInstance.addPoint(-10.0, -50.0);
		this.testInstance.addRoundRectangle(-10.0, 50.0, 50.0, 60.0, 20.0, 10.0);
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
