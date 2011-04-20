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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullCalculatorSingleRectangleTest {
//	private static final Instruction TEST_INSTRUCTIONS[] = { new Instruction(0.0), new Instruction(0.0), new Instruction(50.0), new Instruction(60.0), new Instruction(OpCodes.RECT) }; 
	private static final Point EXPECTED_HULL_POINTS[] = { new Point(50.0, 60.0), new Point(0.0, 60.0), new Point(0.0, 0.0), new Point(50.0, 0.0) }; 
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

	public final void testGetConvexHullPoints(){
		assertNull("no hull", this.testInstance.getConvexHull());
	}
	
	@Test
	public final void testCalculate() {
		this.testInstance.calculate();
		List<Point> actualHull = this.testInstance.getConvexHull().getPoints();
		assertEquals("expected hull points", Arrays.asList(EXPECTED_HULL_POINTS), actualHull);
	}

}
