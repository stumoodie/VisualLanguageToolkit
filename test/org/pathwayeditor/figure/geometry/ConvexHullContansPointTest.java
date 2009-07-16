package org.pathwayeditor.figure.geometry;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullContansPointTest {
	private IConvexHull testInstance;
	
	
	private IConvexHull createConvexHull(){
		List<Point> retVal = new LinkedList<Point>();
		retVal.add(new Point(0.0, 0.0));
		retVal.add(new Point(20.0, -10.0));
		retVal.add(new Point(40.0, 0.0));
		retVal.add(new Point(40.0, 20.0));
		retVal.add(new Point(20.0, 60.0));
		retVal.add(new Point(0.0, 20.0));
		return new ConvexHull(retVal);
	}
	
	@Before
	public void setUp() throws Exception {
		this.testInstance = createConvexHull();
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	@Test
	public void testContainsPoint(){
		assertTrue("Contains point", this.testInstance.containsPoint(new Point(20.0, 20.0)));
		assertTrue("Contains point", this.testInstance.containsPoint(new Point(0.01, 20.0)));
	}

	@Test
	public void testNotContainedPoint(){
		assertFalse("Not contained point", this.testInstance.containsPoint(new Point(-20.0, 20.0)));
		assertFalse("Not contained point", this.testInstance.containsPoint(new Point(-20.0, -20.0)));
		assertFalse("Not contained point", this.testInstance.containsPoint(new Point(-1.0, 20.0)));
		assertFalse("Not contained point", this.testInstance.containsPoint(new Point(5.0, 50.0)));
	}
	
	@Test
	public void testContainedOnBoundary(){
		assertTrue("contained point", this.testInstance.containsPoint(new Point(0.0, 20.0)));
		assertTrue("contained point", this.testInstance.containsPoint(new Point(20.0, -10.0)));
	}
}
