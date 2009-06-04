package org.pathwayeditor.figure.geometry;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.figure.geometry.ConvexHullCalculator;
import org.pathwayeditor.figure.geometry.ConvexHullIntersectionRenderer;
import org.pathwayeditor.figure.geometry.IConvexHull;

public class ConvexHullCalculatorIntersectionTest {
	private IConvexHull testInstance;
	private IConvexHull otherInstance;
	
		
	@Before
	public void setUp() throws Exception {
		ConvexHullCalculator testCalc = new ConvexHullCalculator();
		testCalc.addRoundRectangle(20.0, 20.0, 60.0, 40.0, 8.0, 12.0);
		testCalc.calculate();
		this.testInstance = testCalc.getConvexHull();
		ConvexHullCalculator otherCalc = new ConvexHullCalculator();
		otherCalc.addRectangle(0.0, 0.0, 200.0, 200.0);
		otherCalc.calculate();
		this.otherInstance = otherCalc.getConvexHull();
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
		this.otherInstance = null;
	}

	@Test
	public void testExactlyOverlappingShape() throws IOException{
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(testInstance));
	}

	@Test
	public void testOverlappingShape(){
		this.otherInstance = this.otherInstance.translate(10.0, 10.0);
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testJustOverlappingShape(){
		this.otherInstance = this.otherInstance.translate(-80.0, 0.0);
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testJustOverlappingAgainShape() throws IOException{
		this.otherInstance = this.otherInstance.translate(0.0, 60.0);
		ConvexHullIntersectionRenderer renderer = new ConvexHullIntersectionRenderer(this.testInstance, this.otherInstance);
		renderer.writeAsPsFile(new File("test.ps"));
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testNonOverlappingShape(){
		this.otherInstance = this.otherInstance.translate(100.0, 180.0);
		assertFalse("Not Overlapping", this.testInstance.hullsIntersect(otherInstance));
		assertFalse("Not Overlaping: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}
}
