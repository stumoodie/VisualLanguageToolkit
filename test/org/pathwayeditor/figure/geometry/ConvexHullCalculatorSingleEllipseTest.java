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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvexHullCalculatorSingleEllipseTest {
	private static final int EXPECTED_NUM_HULL_POINTS = 25;
	private ConvexHullCalculator testInstance;

	@Before
	public void setUp() throws Exception {
		this.testInstance = new ConvexHullCalculator();
		this.testInstance.addOval(0.0, 0.0, 60.0, 40.0);
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	public final void testGetConvexHullPoints(){
		assertNull("no hull", this.testInstance.getConvexHull());
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
		assertTrue("contains point", this.testInstance.getConvexHull().containsPoint(new Point(10.0, 10.0)));
	}

}
