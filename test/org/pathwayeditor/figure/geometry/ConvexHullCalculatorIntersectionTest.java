/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.figure.geometry;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullCalculatorIntersectionTest {
	private IConvexHull testInstance;
	private IConvexHull otherInstance;
	
		
	@Before
	public void setUp() throws Exception {
		ConvexHullCalculator testCalc = new ConvexHullCalculator();
		testCalc.addPolygon(new double[]{0.0, 0.0, 20.0, 0.0, 20.0, 20.0});
		testCalc.calculate();
		this.testInstance = testCalc.getConvexHull();
		ConvexHullCalculator otherCalc = new ConvexHullCalculator();
		otherCalc.addPolygon(new double[]{0.0, 1.0, 19.0, 20.0, 0.0, 20.0});
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
		assertTrue("Overlaps", this.testInstance.hullsIntersect(testInstance));
		assertTrue("Overlaps: reciprocal", this.testInstance.hullsIntersect(testInstance));
		assertTrue("Overlaps", this.otherInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(otherInstance));
	}

	@Test
	public void testOverlappingShape() throws IOException{
		this.otherInstance = this.otherInstance.translate(new Point(10.0, 1.0));
//		ConvexHullIntersectionRenderer renderer = new ConvexHullIntersectionRenderer(this.testInstance, this.otherInstance);
//		renderer.writeAsPsFile(new File("test.ps"));
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testJustOverlappingShape() throws IOException{
		this.otherInstance = this.otherInstance.translate(new Point(1.0, 0.0));
//		ConvexHullIntersectionRenderer renderer = new ConvexHullIntersectionRenderer(this.testInstance, this.otherInstance);
//		renderer.writeAsPsFile(new File("test.ps"));
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testJustOverlappingAgainShape() throws IOException{
		this.otherInstance = this.otherInstance.translate(new Point(-18.0, -19.0));
//		ConvexHullIntersectionRenderer renderer = new ConvexHullIntersectionRenderer(this.testInstance, this.otherInstance);
//		renderer.writeAsPsFile(new File("test.ps"));
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testNonOverlappingShape() throws IOException{
//		this.otherInstance = this.otherInstance.translate(-1.0, 1.0);
//		ConvexHullIntersectionRenderer renderer = new ConvexHullIntersectionRenderer(this.testInstance, this.otherInstance);
//		renderer.writeAsPsFile(new File("test.ps"));
		assertFalse("Not Overlapping", this.testInstance.hullsIntersect(otherInstance));
		assertFalse("Not Overlaping: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testNonOverlappingReactangularShape() throws IOException{
		IConvexHull rectHull = new RectangleHull(new Envelope(0.0, 20.0, 5.0, 5.0));
		ConvexHullIntersectionRenderer renderer = new ConvexHullIntersectionRenderer(this.testInstance, rectHull);
		renderer.writeAsPsFile(new File("test.ps"));
		assertFalse("Not Overlapping", this.testInstance.hullsIntersect(rectHull));
		assertFalse("Not Overlaping: reciprocal", rectHull.hullsIntersect(this.testInstance));
	}

	@Test
	public void testOverlappingReactangularShape() throws IOException{
//		this.otherInstance = this.otherInstance.translate(-1.0, 1.0);
		IConvexHull rectHull = new RectangleHull(new Envelope(10.0, 10.0, 5.0, 5.0));
//		ConvexHullIntersectionRenderer renderer = new ConvexHullIntersectionRenderer(this.testInstance, this.otherInstance);
//		renderer.writeAsPsFile(new File("test.ps"));
		assertTrue("Overlapping", this.testInstance.hullsIntersect(rectHull));
		assertTrue("Overlaping: reciprocal", rectHull.hullsIntersect(this.testInstance));
	}
}
