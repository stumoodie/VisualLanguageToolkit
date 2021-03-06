/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package org.pathwayeditor.figure.geometry;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullIntersectionTest {
	private IConvexHull testInstance;
	private IConvexHull otherInstance;
	private IConvexHull enclosedInstance;
	
	
	private IConvexHull createConvexHull(){
		List<Point> retVal = new ArrayList<Point>(); 
		retVal.add(new Point(40.0, 1.0));
		retVal.add(new Point(40.0, 20.0));
		retVal.add(new Point(20.0, 60.0));
		retVal.add(new Point(1.0, 20.0));
		retVal.add(new Point(1.0, 1.0));
		retVal.add(new Point(20.0, -10.0));
		return new ConvexHull(retVal);
	}
	
	private IConvexHull creatEnclosedConvexHull(){
		List<Point> retVal = new ArrayList<Point>(); 
		retVal.add(new Point(10.0, 10.0));
		retVal.add(new Point(20.0, 10.0));
		retVal.add(new Point(20.0, 20.0));
		retVal.add(new Point(10.0, 20.0));
		return new ConvexHull(retVal);
	}
	
	@Before
	public void setUp() throws Exception {
		this.testInstance = createConvexHull();
		this.otherInstance = createConvexHull();
		this.enclosedInstance = creatEnclosedConvexHull();
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
		this.otherInstance = null;
		this.enclosedInstance = null;
	}

	@Test
	public void testExactlyOverlappingShape(){
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(testInstance));
	}

	@Test
	public void testEnclosedHull(){
		assertTrue("Overlaps", this.testInstance.hullsIntersect(this.enclosedInstance));
		assertTrue("Overlaps: reciprocal", this.enclosedInstance.hullsIntersect(testInstance));
	}

	@Test
	public void testOverlappingShape() throws IOException{
		this.otherInstance = this.otherInstance.translate(10.0, 10.0);
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testJustOverlappingShape() throws IOException{
		this.otherInstance = this.otherInstance.translate(39.0, 10.0);
		ConvexHullIntersectionRenderer renderer = new ConvexHullIntersectionRenderer(this.testInstance, this.otherInstance);
		renderer.writeAsPsFile(new File("test.ps"));
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testJustOverlappingAgainShape(){
		this.otherInstance = this.otherInstance.translate(0.0, -70.0);
		assertTrue("Overlaps", this.testInstance.hullsIntersect(otherInstance));
		assertTrue("Overlaps: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}

	@Test
	public void testNonOverlappingShape(){
		this.otherInstance = this.otherInstance.translate(50.0, 10.0);
		assertFalse("Not Overlapping", this.testInstance.hullsIntersect(otherInstance));
		assertFalse("Not Overlaping: reciprocal", this.otherInstance.hullsIntersect(this.testInstance));
	}
}
